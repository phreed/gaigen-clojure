(ns geometric-algebra.c3-blade-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra
             [c3-blade :as c3bl]]))

(tt/facts
 "check the naming"

 (tt/fact
  "convert bit representation to text."
  (c3bl/bitmap->label 2r01010) => :e-2-ni-)

 (tt/fact
  "convert text representation to bits."
  (c3bl/label->bitmap :e-2-nio) => 2r11010)
 )

(tt/facts
 "check the basis-blade"
 (tt/fact
  "e1 * e2 => e12"
  (c3bl/prod-bitmap 2r00001 2r00010) => [[1 2r00011]])

 (tt/fact
  "e2 * e1 => -e12"
  (c3bl/prod-bitmap 2r00010 2r00001) => [[-1 2r00011]])

 (tt/fact
  "ni * ni => 0"
  (c3bl/prod-bitmap 2r01000 2r01000) => [])

 (tt/fact
  "no * ni => -1"
  (c3bl/prod-bitmap 2r01000 2r10000) => [[-1 2r00000] [+1 2r11000] ])

 (tt/fact
  "e12 * e23 => -1, orthogonal with euclidean metric (xor)"
  (c3bl/prod-bitmap 2r00011 2r00110) => [[+1 2r00101] ])

 (tt/fact
  "e12^ni * e23 => e13^ni"
  (c3bl/prod-bitmap 2r01011 2r00110) => [[+1 2r01101] ])

 (tt/fact
  "e12^ni * e23^ni => 0"
  (c3bl/prod-bitmap 2r01011 2r01110) => [])

 (tt/fact
  "e12^ni * e23^no => e13 + e13^E, "
  (c3bl/prod-bitmap 2r01011 2r10110) => [[-1 2r00101] [+1 2r11101]])

 (tt/fact
  "e12^ni * e23 => e13 + e13^E, "
  (c3bl/prod-bitmap 2r01011 2r10110) => [[-1 2r00101] [+1 2r11101]])
 )
