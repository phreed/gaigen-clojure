(ns geometric-algebra.c3-space-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra
             [c3-blade :as c3bl]
             [c3-space :as c3sp]]))

(tt/facts
 "check the product"

 (let [a-pnt {:terms (c3sp/parse {:s 1, :e2 6, :ni 18})}
       b-pnt {:terms (c3sp/parse {:s 1, :e2 -1, :ni 2})}]

   (tt/fact
    "Make sure a-pnt parsed correctly"
    a-pnt => {:terms {0 1, 2 6, 8 18}})

   (tt/fact
    "Make sure b-pnt parsed correctly"
    b-pnt => {:terms {0 1, 2 -1, 8 2}})

   (tt/fact
    "Compute the product of multivectors."
    (c3sp/prod a-pnt b-pnt) => {:terms {0 -5, 2 5, 8 20, 10 30}})

   (tt/fact
    "convert bitmap to text representation."
    (-> (c3sp/prod a-pnt b-pnt)
        :terms
        c3sp/to-alias) => {:s -5, :e2 5, :ni 20, :e2ni 30})

   (tt/fact
    "convert text representation to bits."
    (c3bl/alias->bitmap :e2nio) => 2r11010) ))
