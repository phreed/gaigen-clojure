(ns geometric-algebra.c3-space
  (:require [geometric-algebra.blade :as bl]
            [geometric-algebra.c3-blade :as c3bl]))

(defn prod
  "compute the product of two blades"
  [b1 b2]
  (for [term1 (:terms b1)
        term2 (:terms b2)]
    (c3bl/product term1 term2)))


#_(defn inner
  "Calculate the inner product between two coordinates

  ac - bitwise representation of coordinate
  bc - bitwise representation of coordinate
  returns a blade

  The inner product is dependent of the metric."
  [ac bc metric]
  (if (zero? (bit-and ac bc)) (product ac bc) (make 0 0.0)))
