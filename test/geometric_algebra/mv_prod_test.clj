(ns geometric-algebra.mv-prod-test
  "Test the geometric algebra implementation"
  (:require [midje.sweet :as tt]
            [geometric-algebra.generator [space :as ga]]
            [geometric-algebra [blade :as bb]]))


#_(def c3
  (ga/make-space
   { :metric [1 1 1 1 -1]
     :binops []
     :conformal? true
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
		{ :name "Tnv", :bases ["e14", "e24", "e34"] } ]}))

(tt/facts
 "check the multivector operations"
 (let [sca1 3
       sca2 -7
       vect ()]

 ;(tt/fact
 ; "using basis-count to get the basis size"
 ; (ga/ [1 1 1]) => 8)

 (tt/fact
  "using euclidean metric"
  (ga/build-basis [1 1 1])
  => [1 2 4  ; grade 1
      3 5 6  ; grade 2
      7])    ; grade 3

 (tt/fact
  "using conformal metric"
  (ga/build-basis [1 1 1 1 -1])
  => [ 1  2  4  8 16                ; grade 1
       3  5  6  9 10 12 17 18 20 24 ; grade 2
       7 11 13 14 19 21 22 25 26 28 ; grade 3
      15 23 27 29 30                ; grade 4
      31]))                         ; grade 5
 )

