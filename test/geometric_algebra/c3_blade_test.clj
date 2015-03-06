(ns geometric-algebra.c3-blade-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra
             [c3-blade :as c3bl]]))

(tt/facts
 "check the basis-blade"
 (tt/fact
  "how many elements"
  (count c3bl/prod-bitkey-lookup) => (* 32 32))

 (tt/fact
  "e1 * e2 => e12"
  (c3bl/prod-bitkey 2r00001 2r00010) => [[1 2r00011]])

 (tt/fact
  "e2 * e1 => -e12"
  (c3bl/prod-bitkey 2r00010 2r00001) => [[-1 2r00011]])

 (tt/fact
  "ni * ni => 0"
  (c3bl/prod-bitkey 2r01000 2r01000) => [])

 (tt/fact
  "no * ni => -1"
  (c3bl/prod-bitkey 2r01000 2r10000) => [[-1 2r00000] [+1 2r11000] ])
 )
