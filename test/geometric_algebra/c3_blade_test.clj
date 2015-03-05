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
  (get c3bl/prod-bitkey-lookup 2r0000100010) => [[1 2r00011]])

 (tt/fact
  "e2 * e1 => -e12"
  (get c3bl/prod-bitkey-lookup 2r0001000001) => [[-1 2r00011]])

 (tt/fact
  "ni * ni => 0"
  (get c3bl/prod-bitkey-lookup 2r0100001000) => [])

 (tt/fact
  "no * ni => -1"
  (get c3bl/prod-bitkey-lookup 2r0100010000) => [[-1 2r00000] [+1 2r11000] ])
 )
