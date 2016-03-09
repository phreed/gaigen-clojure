(ns subspace.basis.basis-blade
  (:require [inner-product-types :as ipt])
  (:import [java.util ArrayList]))

(defrecord BasisBlade
  [bitmap scale]
  InnerProductTypes
  [])

(defn make-blade [b s] (BasisBlade. b s))
(defn make-unit-blade [b] (BasisBlade. b 1.0))
(defn make-scalar-blade [s] (BasisBlade. 0 s))
(defn make-zero-blade [] (BasisBlade. 0 0.0))
(defn copy-blade [a] (BasisBlade. (:bitmap a) (:scale a)))

(defn canonical-reordering-sign
  [a-orig b-orig]
  (loop [a (unsigned-bit-shift-right a-orig 1)
         sum 0]
    (if (zero? a)
      (if (zero? (bit-and sum 1)) 1.0 -1.0)
      (recur
        (unsigned-bit-shift-right a 1)
        (sub/bit-count (bit-and a b))))))

(defn g*-o*
  "return, for 'a' and 'b', either
  their geometric product or
  their outer product."
  [a b type]
  (if (and (= type :outer)
         (not (zero? (bit-and a b))))
    (make-zero-blade)
    (let [a-bm (:bitmap a) b-bm (:bitmap b)
          t-bm (bit-xor a-bm b-bm)
          sign (canonical-reordering-sign a-bm b-bm)]
      (make-blade t-bm (* sign (:scale a) (:scale b))))))

(defn outer-product [a b] (g*-o* a b :outer))
(defn geometric-product [a b] (g*-o* a b :total))

(defn o* [a b] (outer-product a b))
(defn g* [a b] (geometric-product a b))

(defn g*
  "shortcut to geometricProduct()"
  [a b metric] (geometric-product-metric a b metric))

(defn reverse
  "return reverse of this basis blade"
  [a]
  (let [g (grade a)
        sc (minus-one-pow (* 1/2 g (dec (g))))]
    (make-blade (:bitmap a) (* (:scale a) sc))))

(defn grade-inversion
  "return grade inversion of this basis blade"
  [a]
  (let [sc (* (minus-one-pow (grade a) (:scale a)))]
    (make-blade (:bitmap a) sc)))

(defn grade
  "return the grade of the blade."
  [a] (sub/bit-count (:bitmap a)))

(defn minus-one-pow [a i]
  (if (zero? (bit-and i 1)) 1 -1))

(defn equals [a b]
  (and ; check that types match
       (= (:bitmap a) (:bitmap b))
       (= (:scale a) (:scale b))))

(defn hash-code [a]
  (bit-xor  (.hashCode (Integer. (:bitmap a)))
            (.hashCode (Double. (:scale a)))))



(defn clifford-conjugate
  "return clifford conjugate of this basis blade"
  [a]
  (let [g (grade a)
        sc (* 1/2 g (inc g))]
    (make-blade (:bitmap a) sc)))

(defn geometric-product-limited
  "Computes the geometric product of two basis blades
  in limited non-Euclidean metric.
  @param m is an array of doubles giving
  the metric for each basis vector."
  [a b metric]
  (let [res (geometric-product a b)
        bm (bit-and (:bitmap a) (:bitmap b))]
    (loop [ix 0, bitmap bm, scale (:scale res)]
      (if (zero? bitmap)
        (make-blade (:bitmap res) scale)
        (recur (inc ix)
          (unsigned-bit-shift-right bitmap 1)
          (* scale (get metric ix)))))))

(defn g*l [a b metric] (geometric-product-limited a b metric))

(defn geometric-product-arbitrary
  "Computes the geometric product of two basis blades
  in arbitary non-Euclidean metric.
  @param M is an instance of Metric giving the metric
  (and precomputed eigen vectors).
  @return an ArrayList, because the result does not
  have to be a single BasisBlade anymore . . ."
  [a b metric]
  (let [a-eigen (to-eigen-basis metric a)
        b-eigen (to-eigen-basis metric b)
        eigen-metric (get-eigen-metric metric)]
    (loop [result {}, ix 0, jx 0]
      (cond
        (> ix (size a-eigen))
        (to-metric-basis result)

        (> jx (size b-eigen))
        (recur result (inc ix) 0)

        :else
        (let [a-blade (get a-eigen ix)
              b-blade (get b-eigen jx)])
        (recur
          ;; WE NEED SOME WORK HERE
          (conj result (geometric-product a-blade b-blade eigen-metric))
          ix (inc jx))))))

(defmulti inner-product-filter
  "Applies the rules to turn a geometric product into an inner product
   @param ga Grade of argument 'a'
   @param gb Grade of argument 'b'
   @param r the basis blade to be filter
   @param type the type of inner product required:
   LEFT_CONTRACTION,RIGHT_CONTRACTION, HESTENES_INNER_PRODUCT or MODIFIED_HESTENES_INNER_PRODUCT
   @return Either a 0 basis blade, or 'r'"
  (fn [ga gb blade type] type))


(defmethod inner-product-filter ipt/LEFT_CONTRACTION
  [ga gb blade type]
  (if (or (> ga gb) (not= (grade r) (- gb ga)))
    (make-zero-blade)
    r))

(defmethod inner-product-filter ipt/RIGHT_CONTRACTION
  [ga gb blade type]
  (if (or (< ga gb) (not= (grade r) (- ga gb)))
    (make-zero-blade)
    r))

(defmethod inner-product-filter ipt/HESTENES_INNER_PRODUCT
  [ga gb blade type]
  (cond
    (or (zero? ga) (zero? gb)) (make-zero-blade)
    (= (Math/abs (- ga gb) (grade r))) r
    :else (make-zero-blade)))

(defmethod inner-product-filter ipt/MODIFIED_HESTENES_INNER_PRODUCT
  [ga gb blade type]
  (if (= (Math/abs (- ga gb) (grade r)))
    r (make-zero-blade)))


(defn round
  "Rounds the scalar part of <code>this</code> to the nearest multiple X of <code>multipleOf</code>,
  if |X - what| <= epsilon. This is useful when eigenbasis is used to perform products in arbitrary
  metric, which leads to small roundof errors. You don't want to keep these roundof errors if your
  are computing a multiplication table.

  @returns a new basis blade if a change is required."
  [a multiple-of epsilon]
  (let [original (:scale a)
        candidate (sudu/round original multiple-of epsilon)]
    (if (= original candidate)
      original
      (make-blade))))

(defn inner-product-limited
  "Computes the inner product of two basis blades in
  limited non-Euclidean metric.
  @param m is an array of doubles giving the metric for each basis vector.
  @param type gives the type of inner product:
  LEFT_CONTRACTION,RIGHT_CONTRACTION,
  HESTENES_INNER_PRODUCT or MODIFIED_HESTENES_INNER_PRODUCT."
  [a b metric type]
  {:pre [(vector? metric)]}
  (inner-product-filter (grade a) (grade b)
                        (geometric-product-limited a b metric)
                        type))

(defn inner-product-arbitrary
  "Computes the inner product of two basis blades in
  arbitary non-Euclidean metric.
  @param M is an instance of Metric giving the metric
  (and precomputed eigen vectors).
  @param type gives the type of inner product:
  LEFT_CONTRACTION,RIGHT_CONTRACTION,
  HESTENES_INNER_PRODUCT or MODIFIED_HESTENES_INNER_PRODUCT.
  @return an ArrayList, because the result does not have to be a single BasisBlade anymore . . .

  Todo:
  No need to return an ArrayList here?
  Result is always a single blade?"
  [a b metric type]
  ;{:pre [(is-record-of-type-metric?)]}
  (inner-product-filter (grade a) (grade b)
                        (geometric-product-arbitrary a b metric)
                        type))

(def i* [a b type] (inner-product a b type))
(def i*l [a b metric type] (inner-product-limited a b metric type))
(def i*a [a b metric type] (inner-product-arbitrary a b metric type))
