(ns geometric-algebra.generator.basis-blade
  "The generic basis blade.
  Each term in a multivector is keyed by a
  basis blade and has a real valued magnitude."
  (:require [geometric-algebra.generator.context :as ctx]
            [core-aux.pending :as aux]
            [clojure.math.combinatorics :as combo]))

(defrecord BasisBlade [^int blade ^double weight])

(defn make
  "Data structure representing a blade (coordinate + scale factor)

  bc - bitwise representation of coordinate
  wt - scale factor"
  [bc wt] (->BasisBlade bc wt))

(defn basis-blade-sign
  "This function computes the sign change
  due to reordering of two basis blades
  into canonical order.

  a - bitwise representation of coordinate
  b - bitwise representation of coordinate

  An even number of swaps => return +1.0
  An odd number of swaps  => return -1.0

  see geometric-algebra Fig. 19.1"
  [a b]
  (loop [a (bit-shift-right a 1), sum 0]
    (if (= 0 a)
      (if (even? sum) +1 -1)
      (recur (bit-shift-right a 1)
             (+ sum (aux/bit-count (bit-and a b)))))))

(def metric {#{2r10000 2r00001} -1,
             #{2r01000}  1,
             #{2r00100}  1,
             #{2r00010}  1})

(defn basis-blade-diagonal-product
  "Geometric product of basis blades.
  (see geometric-algebra fig. 19.2)
  This function computes the outer
  and inner (metric) products
  for a diagonal basis."
  [a b outer?]
  (let [a-blade (:blade a), b-blade (:blade b)]
    (if (and outer?
             ((complement zero?) (bit-and a-blade b-blade)))
      []
      (make (bit-xor a-blade b-blade)
            (* (basis-blade-sign a-blade b-blade)
               (:weight a) (:weight b))) )))

(defn basis-blade-outer-product [a b]
  (basis-blade-orthometric-product a b true))

(defn basis-blade-product [a b]
  (basis-blade-orthometric-product a b false))

(defn basis-blade-product-diagonalized
  "Geometric product of basis blades.
  (see geometric-algebra fig. 19.2)
  This function computes the outer
  and inner (metric) products
  for a diagonal basis."
  [a b]
  (let [a-blade (:blade a), b-blade (:blade b)]
    (make (bit-xor a-blade b-blade)
          (* (basis-blade-sign a-blade b-blade)
             (:weight a) (:weight b))) ))

(defn basis-blade-outer-product
  "The outer product is independent of the metric."
  [a b]
  (let [a-blade (:blade a), b-blade (:blade b)]
    (when (zero? (bit-and a-blade b-blade))
      (basis-blade-product-diagonalized a b))))

(defn basis-blade-total-product
  "The total product.
  The non-diagonal nature of
  the metric comes into play.
  The blades are checked for diagonality and
  if they pass then the basic function can be called.
  If it is not diagonal then the basis
  needs to be diagonalized first.
  Diagonalization may introduce multiple
  terms in the result."
  [a b]
  (basis-blade-product-diagonalized a b))


(defn total-product
 "Computes the total-product of two multivectors.
  This is simply the aggregate of the
  total-products of all pairings of the multivectors terms."
  [a b]
  (loop [pairs (for [at (:bases a), bt (:bases b)] [at bt])
         res {}]
    (if (empty? pairs)
      res
      (let [terms (apply basis-blade-total-product (first pairs))]
      (recur (rest pairs)
             (apply assoc res terms))))))



(defrecord Type [key bases name generated? dual?])

(defn make->type
  ""
  [key bases name] (->Type key bases name false false))


(defn classname [name] (str "_" name))

(defn basis-blade-grade
  "Calculate the grade of a coordinate
  bc - bitwise representation of coordinate.
  Count the number of active bits."
  [b]
  (loop [cnt 0 b b]
    (if (= 0 b)
      cnt
      (let [b1 (bit-shift-right b 1)
            incr (if (bit-test b 0) 1 0)]
        (recur (+ cnt incr) b1)))))



(defn product
  "Calculate the product between two coordinates
  a - bitwise representation of coordinate
  b - bitwise representation of coordinate
  returns a blade."
  [a  b]
  (make (bit-xor a b) (basis-blade-sign a b)))


(defn outer
  "Calculate the outer product between two coordinates
  a - bitwise representation of coordinate
  b - bitwise representation of coordinate
  returns a blade."
  [a b]
  (if (= 0 (bit-and a b)) (product a b) (make 0 0)))

(defn involute
  "Derive the involute blade given a bitwise representation of the coordinate."
  [x]
  (make x (if (odd? (basis-blade-grade x)) -1 1)))

(defn reversed [x]
  (let [g (basis-blade-grade x)
        o (/ (* g (dec g)) 2)]
    (make x (if (odd? o) -1 1))))

(defn conjugate [x]
  (let [g (basis-blade-grade x)
        o (/ (* g (inc g)) 2)]
    (make x (if (odd? o) -1 1))))


(defn basis-string
  "Calculate the name of a coordinate
  b - bitwise representation of coordinate.
  2r01101 => e134 "
  [basis]
  (loop [b basis, n 0, res ""]
    (if (> 1 b)
      (cond (> 1 n) "s"
            :else (str "e" res))
      (recur (bit-shift-right b 1)
             (inc n)
             (if (bit-test b 0) (str res (inc n)) res)) )))


(defn basis-bit
  "The inverse of basis-string.
  Given a string compute the basis.
  e134 => 2r1101
  s => 2r0000 "
  [basis-name]
  (if (= "s" basis-name) 0
    (reduce
     (fn [w c]
       (if (Character/isDigit c)
         (bit-set w (dec (Character/digit c 10)))
         w))
     0 basis-name)))

(defn basis-bits
  "Given a seq of basis names build a seq of bitwise coordinates."
  [bases]
  (map #(basis-bit %) bases))


(defn basis-names
  "Sort the list of basis-coordinates into a list of basis-strings."
  [ty] (map basis-string (sort < ty)))

(defn key-check
  "Compare two lists of names"
  [k1 k2] (= k1 k2))

(defn order
  "Return the order of the supplied multi-vector."
  [c]
  (let [tblades (map [] #(Integer/parseInt %) c)
        ordered-tblades (sort < tblades)]
    {:blades ordered-tblades, :inst c}))

(defn compress
  "Collect like terms for a set of blades.
  Given a seq of blades, if two terms have
  the same bit encoding sum their weights. "
  [xs]
  (->> xs
       (group-by :bitmap)
       (map (fn [[k v]]
              (make k
                           (reduce #(+ %1 (:weight %2)) 0.0 v))))
       (into [])))

(def print-lines
  "Write out the specified number of lines from the input."
  (let [eol #".*(?:(?:\r\n|\n|\r)|$)"
        writer (ref str)]
    (fn [text from to]
      (->>
       (re-seq eol text)
       (drop from)
       (take (- to from))
       (map-indexed #(@writer (inc %1) "\t" %2))))))


(defmacro versor->create
  [name-space props]
  `(do
     (defn ~(symbol "i*") [~(symbol 'a) ~(symbol 'b)] )
     (defn ~(symbol "o*") [~(symbol 'a) ~(symbol 'b)] )
     (defn ~(symbol "t*") [~(symbol 'a) ~(symbol 'b)] )
     (defn ~(symbol "dual") [~(symbol 'a)] )
     (defn ~(symbol "pnt")
       [~(symbol 'x) ~(symbol 'y) ~(symbol 'z)
        ~(symbol 'no) ~(symbol 'ni) ]
       {:e1 ~(symbol 'x) :e2 ~(symbol 'y) :e3 ~(symbol 'z) :e4 ~(symbol 'no) :e5 ~(symbol 'ni)})
     ))


