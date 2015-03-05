(ns geometric-algebra.blade-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra
             [blade :as bb]]))


(tt/facts
 "check the basis-blade cannonical signs"
 (tt/fact
  "using (e0^e2^ei)^(e1^e3)"
  (bb/sign 2r10101 2r01010) => -1)
 (tt/fact
  "using (e0^e1^e2)^(e3^ei)"
  (bb/sign 2r00111 2r11000) => +1)
 (tt/fact
  "using e2^e1"
  (bb/sign 2r00100 2r00010) => -1))


(tt/facts
 "check the basis-blade products"

 (tt/fact
  "using (2*em^e2^ep)*(e3^e1)"
  (bb/product 2r10101 2r01010)
  => {:blade 2r11111 :grade 5 :weight -1.0})

 (tt/fact
  "using (3*em^e1^e2)*(2*e3^ep)"
  (bb/product 2r00111 2r11000)
  => {:blade 2r11111 :grade 5 :weight 1.0}))


(tt/facts
 "check the basis-blade outer products"

 (tt/fact
  "using (2*em^e2^ep)^(e3^e2) which has no outer product"
  (bb/outer 2r10101 2r01100)
  => {:blade 0 :grade 0 :weight 0.0})

 (tt/fact
  "using (3*em^e1^e2)^(2*e3^ep)"
  (bb/outer 2r00111 2r11000)
  => {:blade 2r11111 :grade 5 :weight 1.0}))

(tt/facts
 "check the basis-blade names"

 (tt/fact
  "using (2*em^e2^ep)^(e3^e2) which has no outer product"
  (bb/to-name 2r10101) => "e135")

 (tt/fact
  "using (3*em^e1^e2)^(2*e3^ep)"
  (bb/outer 2r00111 2r11000)
  => {:blade 2r11111 :grade 5 :weight 1.0}))
