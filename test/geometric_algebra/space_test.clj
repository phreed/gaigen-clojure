(ns geometric-algebra.space-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra.generator [space :as ga]]
            [geometric-algebra [blade :as bb]]))

(tt/facts
 "check the basis building"

 (tt/fact
  "using basis-count to get the basis size"
  (ga/basis-count [1 1 1]) => 8)

 (tt/fact
  "using euclidean metric"
  (ga/build-basis [1 1 1])
  => [1 2 4  ; grade 1
      3 5 6  ; grade 2
      7])    ; grade 3

 (tt/fact
  "using 5 dimensional metric with a minkowski basis"
  (ga/build-basis [1 1 1 1 -1])
  => [; grade 0 = the scalar
      2r00000
      ; grade 1 = vectors
      2r00001 2r00010 2r00100 2r01000 2r10000
      ; grade 2 = bivector
      2r00011 2r00101 2r00110 2r01001 2r01010 2r01100
      2r10001 2r10010 2r10100 2r11000
      ; grade 3 = trivector
      2r00111 2r01011 2r01101 2r01110
      2r10011 2r10101 2r10110 2r11001 2r11010 2r11100
      ; grade 4 = quadvector
      2r01111 2r10111 2r11011 2r11101 2r11110
      ; grade 5 = the psuedoscalar
      2r11111 ]))

(tt/facts
 "check the multivector-key generation"
 (let [metric [1 1 1 1 -1]
       basics (ga/build-basis metric)]

 (tt/fact
  "populate the subspaces with blades"
  (ga/populate-subspaces metric basics)
  => [2r1])
   ))

(tt/facts
 "check the multivector-key generation"
 (let [c3-basis (ga/build-basis [1 1 1 1 -1])]

 (tt/fact
  "building hash for..."
  (ga/key-bit c3-basis 2r00000) => [2r1])

 (tt/fact
  "building hash for..."
  (ga/key-bit c3-basis 2r00001) => [2r10])

 (tt/fact
  "building hash for..."
  (ga/key-bit c3-basis 2r00010) => [2r100])

 (tt/fact
  "building hash for..."
  (ga/key-bit c3-basis 2r01010) => [2r10000000000])

 (tt/fact
  "building hash for..."
  (ga/key-bit c3-basis 2r11011) => [(bit-shift-left 1 27)]) ))



(tt/facts
 "check the type building"

 (let [basis (ga/build-basis [1 1])
       single (second basis)]

 (tt/fact
  "using conformal metric to generate types."
  (ga/make-type (ga/key-bit basis single)
                [single]
                (bb/to-name single))
  => {:basis [2], :dual? false, :generated?? false,
      :llave [4], :nombre "e2"})

 (tt/fact
  "using conformal metric to generate types."
  (ga/build-basic-types basis)
  => {"e1" {:basis [1] :dual? false :generated?? false :llave [2] :nombre "e1"}
      "e2" {:basis [2] :dual? false :generated?? false :llave [4] :nombre "e2"}
      "e12" {:basis [3] :dual? false :generated?? false :llave [8] :nombre "e12"}})


 (tt/fact
  "using conformal metric to generate types."
  (ga/create-spec-types
   { :metric [1 1 1 1 -1]
     :types [
		{ :name "Vec3", :bases ["e1", "e2", "e3"] },
		{ :name "Biv3", :bases ["e12", "e13", "e23"] },
		{ :name "Tri3", :bases ["e123"] },
		{ :name "Pss", :bases ["e12345"] },
		{ :name "Rot", :bases ["s", "e12", "e13", "e23"] },
		{ :name "Pnt", :bases ["e1", "e2", "e3", "e4", "e5"], :dual true },
		{ :name "Dlp", :bases ["e1", "e2", "e3", "e5"], :dual true },
		{ :name "Pln", :bases ["e1235", "e1245", "e1345", "e2345"] },
		{ :name "Sph", :bases ["e1235", "e1234", "e1245", "e1345", "e2345"] },
		{ :name "Dll", :bases ["e12", "e13", "e23", "e15", "e25", "e35"], :dual true },
		{ :name "Lin", :bases ["e145", "e245", "e345", "e125", "e135", "e235"] },
		{ :name "Flp", :bases ["e15", "e25", "e35", "e45"] },
		{ :name "Par", :bases ["e12", "e13", "e23", "e14", "e24", "e34", "e15", "e25", "e35", "e45"], :dual true },
		{ :name "Cir", :bases ["e123", "e145", "e245", "e345", "e124", "e134", "e234", "e125", "e135", "e235"] },
		{ :name "Bst", :bases ["s", "e12", "e13", "e23", "e14", "e24", "e34", "e15", "e25", "e35", "e45"] },
		{ :name "Dil", :bases ["s", "e45"] },
		{ :name "Mot", :bases ["s", "e12", "e13", "e23", "e15", "e25", "e35", "e1235"] },
		{ :name "Trs", :bases ["s", "e15", "e25", "e35"] },
		{ :name "Drv", :bases ["e15", "e25", "e35"] },
		{ :name "Drb", :bases ["e125", "e135", "e235"] },
		{ :name "Drt", :bases ["e1235"] },
		{ :name "Tnv", :bases ["e14", "e24", "e34"] } ]})
  => [])))
