(ns geometric-algebra.blade
  "The generic basis blade.
  Each term in a multivector is keyed by a
  basis blade and has a real valued magnitude.
  (see 'geometric-algebra/blade_test.clg' for
    test guided development"
  (:require [geometric-algebra.generator.context :as ctx]
            [core-aux.pending :as aux]
            [clojure.math.combinatorics :as combo]))

(defrecord Blade [^int blade ^int grade ^double weight])

(defn class-name [id] (str "_" id))

(defn grade
  "Calculate the grade of a coordinate.
  bc - bitwise representation of coordinate.

  Count the number of active bits."
  [bc] (aux/bit-count bc))


(defn make
  "Data structure representing a blade (coordinate + scale factor)

  bc - bitwise representation of coordinate
  wt - scale factor"
  [bc wt] (->Blade bc (grade bc) wt))

(defn sign
  "This function computes the sign change
  due to reordering of two basis blades
  into canonical order.

  ac - bitwise representation of coordinate
  bc - bitwise representation of coordinate

  An even number of swaps => return +1.0
  An odd number of swaps  => return -1.0

  see geometric-algebra Fig. 19.1"
  [ac bc]
  (loop [n (bit-shift-right ac 1), sum 0]
    (if (= 0 n)
      (if (even? sum) +1 -1)
      (recur (bit-shift-right n 1)
             (+ sum (grade (bit-and n bc)))))))


(defn product
  "Calculate the product between two coordinates.
  (see geometric-algebra fig. 19.2)
  This function computes the product
  for a diagonal metric.
  It returns a blade.

  In order for memoization to work correctly
  the weight is not used but only the sign."
  [ac bc] (make (bit-xor ac bc) (sign ac bc)))


(defn outer
  "Calculate the outer product between two coordinates

  ac - bitwise representation of coordinate
  bc - bitwise representation of coordinate
  returns a blade

  The outer product is independent of the metric.
  Hence the multiplication is always diagonal."
  [ac bc]
  (if (zero? (bit-and ac bc)) (product ac bc) (make 0 0.0)))

(defn involute
  "make an involute blade from the coordinate."
  [xc] (make xc (if (even? (grade xc)) +1 -1)))

(defn re-verse
  "make an reverse blade from the coordinate."
  [xc]
  (let [x-grade (grade xc)
        double-grade (/ (* x-grade (dec x-grade) 2))]
    (make xc (if (even? double-grade) +1 -1))))

(defn conjugate
  "make an conjugate blade from the coordinate."
  [xc]
  (let [x-grade (grade xc)
        double-grade (/ (* x-grade (inc x-grade) 2))]
    (make xc (if (even? double-grade) +1 -1))))



(defn to-name
  "Calculate the name of a single coordinate
  bc - bitwise representation of coordinate.
  2r01101 => e134
  (see space.js::basisString)"
  [basis]
  (loop [b basis, n 0, res ""]
    (if (> 1 b)
      (cond (> 1 n) "s"
            :else (str "e" res))
      (recur (bit-shift-right b 1)
             (inc n)
             (if (bit-test b 0) (str res (inc n)) res)) )))


(defn to-bitmap
  "The inverse of to-string.
  Given a string compute the basis.
  e134 => 2r1101
  s => 2r0000
  (see space.js::basisBit)"
  [basis-name]
  (if (= "s" basis-name) 0
    (reduce
     (fn [w c]
       (if (Character/isDigit c)
         (bit-set w (dec (Character/digit c 10)))
         w))
     0 basis-name)))

(defn to-multi-bits
  "Given a seq of basis names build a seq of bitwise coordinates.
  (see space.js::basisBits)"
  [bases]
  (map #(to-bitmap %) bases))


(defn basis-multi-names
  "Sort the list of basis-coordinates into a list of basis-strings.
  (see space.js::basisNames)"
  [ty] (map to-name (sort < ty)))

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


;; (defmacro versor->create
;;   [name-space props]
;;   `(do
;;      (defn ~(symbol "i*") [~(symbol 'a) ~(symbol 'b)] )
;;      (defn ~(symbol "o*") [~(symbol 'a) ~(symbol 'b)] )
;;      (defn ~(symbol "t*") [~(symbol 'a) ~(symbol 'b)] )
;;      (defn ~(symbol "dual") [~(symbol 'a)] )
;;      (defn ~(symbol "pnt")
;;        [~(symbol 'x) ~(symbol 'y) ~(symbol 'z)
;;         ~(symbol 'no) ~(symbol 'ni) ]
;;        {:e1 ~(symbol 'x) :e2 ~(symbol 'y) :e3 ~(symbol 'z) :e4 ~(symbol 'no) :e5 ~(symbol 'ni)})
;;      ))


