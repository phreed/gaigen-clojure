(ns geometric-algebra.c3-space-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra
             [c3-blade :as c3bl]
             [c3-space :as c3sp]]))

(tt/facts
 "check the naming"

 (tt/fact
  "convert bit representation to text."
  (c3bl/bitmap->label 2r01010) => :010x10)

 (tt/fact
  "convert text representation to bits."
  (c3bl/label->bitmap :010x11) => 2r11010)
 )
