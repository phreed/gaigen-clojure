(ns geometric-algebra.c3-space
  (:require [geometric-algebra.blade :as bl]
            [geometric-algebra.c3-blade :as c3bl]))


(defn prod
  "Compute the product of two multi-vectors.
  A multivector is represented with a hash where
  the keys are the blade identifies and the values
  are their coefficients."
  [b1 b2]
  {:terms
   (reduce #(update-in %1 [(first %2)] (fnil + 0) (second %2)) {}
           (reduce into
                   (for [term1 (:terms b1)
                         term2 (:terms b2)]
                     (c3bl/prod-bitmap term1 term2))))})


#_(defn inner
  "Calculate the inner product between two coordinates

  ac - bitwise representation of coordinate
  bc - bitwise representation of coordinate
  returns a blade

  The inner product is dependent of the metric."
  [ac bc metric]
  (if (zero? (bit-and ac bc)) (product ac bc) (make 0 0.0)))


(defn parse
  "parse a map representing a multivector keyed
  with labels (aliases) into a map keyed with bitmaps.
  e.g. (parse {:s 1, :e2 6, :ni 18}) "
  [mv]
  (into {}
        (map (fn [[k v]]
               (vector (c3bl/alias->bitmap k) v))
             mv)))

(defn to-alias
  [mv]
  (into {} (map (fn [[k v]] [(c3bl/bitmap->alias k) v]) mv)))
