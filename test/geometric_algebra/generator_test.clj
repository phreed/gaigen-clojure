(ns geometric-algebra.generator-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra.generator.basis-blade :as bb]))


(tt/facts
 "check the basis-blade cannonical signs"
 (tt/fact
  "using (e0^e2^ei)^(e1^e3)"
  (bb/basis-blade-sign 2r10101 2r01010) => -1)
 (tt/fact
  "using (e0^e1^e2)^(e3^ei)"
  (bb/basis-blade-sign 2r00111 2r11000) => +1)
 (tt/fact
  "using e2^e1"
  (bb/basis-blade-sign 2r00100 2r00010) => -1))


(tt/facts
 "check the basis-blade products"

 (tt/fact
  "using (2*em^e2^ep)*(e3^e1)"
  (bb/basis-blade-product (bb/make 2r10101 2) (bb/make 2r01010 -1))
  => {:blade 2r11111 :weight 2.0})

 (tt/fact
  "using (3*em^e1^e2)*(2*e3^ep)"
  (bb/basis-blade-product (bb/make 2r00111 3) (bb/make 2r11000 2))
  => {:blade 2r11111 :weight 6.0}))


(tt/facts
 "check the basis-blade outer products"

 (tt/fact
  "using (2*em^e2^ep)^(e3^e2)"
  (bb/basis-blade-outer-product (bb/make 2r10101 2) (bb/make 2r01100 -1))
  => [])

 (tt/fact
  "using (3*em^e1^e2)^(2*e3^ep)"
  (bb/basis-blade-outer-product (bb/make 2r00111 3) (bb/make 2r11000 2))
  => {:blade 2r11111 :weight 6.0}))
