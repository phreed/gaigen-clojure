(ns gafcs.c3ga
  (:require [gafcs.gaigen :as ga])

(def-g25spec
 :license "gpl"
 :language "clojure"
 :namespace "c3ga"
 :coord-storage "variables"
 :default-operator-bindings true
 :dimension 5
 :report-usage false
 :gmv-code "expand"
 :parser "builtin"
 :test-suite true
 :copyright "Based on Gaigen 2.5 Test Suite")



(def-inline
 :constructors true
 :set true
 :assign true
 :operators true
 :functions true)


(def-float-type double)


;; define mybasis
(def-basis-vector-names mybasis [:no :e1 :e2 :e3 :ni] {:default true} )



;; define conformal and euclidean metrics (dot product)
(def-metric conformal mybasis {:default true :round false}
  {[:no :ni] -1
   [:e1 :e1] 1 [:e2 :e2] 1 [:e3 :e3] 1})

(def-metric euclidean mybasis {:round false}
  {[:no :no] 1 [:e1 :e1] 1 [:e2 :e2] 1 [:e3 :e3] 1 [:ni :ni] 1})


;; define the basic multi-vector factory.
(def-mv mv {:compress "by-grade" :coordinateOrder "default" :memAlloc "full"})


;; define the specialized multi-vector factories.
(def-smv-blade no {:const true} {[:no] 1})
(def-smv-blade e1 {:const true} {[:e1] 1})
(def-smv-blade e2 {:const true} {[:e2] 1})
(def-smv-blade e3 {:const true} {[:e3] 1})
(def-smv-blade ni {:const true} {[:ni] 1})

(def-smv-blade no-ni {:const true} {[:no :ni] 1})
(def-smv-blade I3 {:const true} {[:e1 :e2 :e3] 1})
(def-smv-blade I5 {:const true} {[:no :e1 :e2 :e3 :ni] 1})
(def-smv-blade inv-I5 {:const true} {[:no :e1 :e2 :e3 :ni] -1})

(def-smv-blade vector-e3ga #{[:e1] [:e2] [:e3]})
(def-smv-blade bivector-e3ga #{[:e1 :e2] [:e2 :e3] [:e3 :e1]})
(def-smv-versor rotor-e3ga #{:scalar [:e1 :e2] [:e2 :e3] [:e3 :e1]})

(def-smv-blade normalized-point {[:no] 1} #{[:e1] [:e2] [:e3] [:ni]})
(def-smv-blade dual-sphere #{[:no] [:e1] [:e2] [:e3] [:ni]})
(def-smv-blade dual-plane #{[:e1] [:e2] [:e3] [:ni]})
(def-smv-blade free-vector #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})
(def-smv-blade free-bivector  #{[:e1 :e2 :ni] [:e2 :e3 :ni] [:e3 :e1 :ni]})

(def-smv-blade flat-point
     #{[e1 :ni] [e2 :ni] [:e3 :ni] [:no :ni]})

(def-smv-blade point-pair
     #{[:no :e1] [:no :e2] [:no :e3]
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:no :ni]})

(def-smv-blade line
     #{[:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni]
      [:e1 :no :ni] [:e2 :no :ni] [:e3 :no :ni]})

(def-smv-blade circle
     #{[:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
      [:e1 :e2 :e3]
      [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
      [:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni] })

(def-smv-blade sphere
     #{[:e1 :e2 :e3 :ni] [:no :e1 :e2 :e3]
      [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(def-smv-blade plane
     #{[:e1 :e2 :e3 :ni] [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(def-smv-blade pseudoscalar #{[:no :e1 :e2 :e3 :ni]})

(def-smv-versor normalized-translator
     {:scalar 1.0} #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})

(def-smv-versor translator
  #{:scalar [:e1 :ni] [:e2 :ni] [:e3 :ni]})

(def-smv-versor RBM
     #{:scalar
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:e1 :e2 :e3 :ni]})

(def-smv-versor even-versor
     #{:scalar
      [:no :e1] [:no :e2] [:no :e3]
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:no :ni]
      [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]
      [:no :e1 :e2 :e3]
      [:e1 :e2 :e3 :ni]})

(def-smv-versor odd-versor
     {[:no] [:e1] [:e2] [:e3] [:ni]
      [:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
      [:e1 :e2 :e3]
      [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
      [:e1 :e2 :ni]
      [:e1 :e3 :ni] [:e2 :e3 :ni]
      [:no :e1 :e2 :e3 :ni] })


;; define some commmonly used constants.
(def-constant vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})
(def-constant vector-e2 {:type "vector-e3ga"} {[:e1] 0 [:e2] 1 [:e3] 0})
(def-constant vector-e3 {:type "vector-e3ga"} {[:e1] 0 [:e2] 0 [:e3] 1})

(def-constant point-at-origin
  "The point at the origin . . ."
  {:type "normalized-point"} {[:no] 1})

(def-constant point-at-infinity
  "The point at infinity . . ."
  {:type "dual-sphere"} {[:ni] 1})

;; the outermorphism
(def-om om)

(def-som grade1-om-e3ga :domain #{[:e1] [:e2] [:e3]})
(def-som grade1-om :domain #{[:no] [:e1] [:e2] [:e3] [:ni]})

(def-som flat-point-om
      :domain #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]}
      :range #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]} )


(def-fun-random-double :output-name "genrand" :optionGen "libc")

(def-con _vector-e3ga
  "Extras vector part of point"
  normalized-point :argName1 "P")

(def-con _vector-e3ga
  "Extras vector part of dual sphere"
  dual-sphere :argName1 "S" )

(def-con _bivector-e3ga
  "Extracts Euclidean bivector part of Euclidean rotor"
  rotor-e3ga  )

(def-con _dual-sphere
  "Converts a normalized point to a dual sphere"
  normalized-point
  :output-name "pointToSphere"
  :argName1="P" )

(def-con _even-versor point-pair)


(def-fun-cga-point vector-e3ga)
(def-fun-cga-point [double double double]
           :origin no :infinity ni )

(def-fun-random-point )

(def-fun-cga-point-distance normalized-point normalized-point )
(def-fun-cga-point-distance dual-sphere dual-sphere )

(def-fun-add mv mv)
(def-fun-add vector-e3ga vector-e3ga)
(def-fun-add vector-e3ga normalized-point)
(def-fun-add vector-e3ga dual-sphere)
(def-fun-add bivector-e3ga bivector-e3ga)
(def-fun-add plane plane)
(def-fun-add line circle)
(def-fun-add circle vector-e3ga)
(def-fun-add e1 e2)
(def-fun-add I5 circle)


(def-fun-subtract mv mv)
(def-fun-subtract vector-e3ga vector-e3ga)
(def-fun-subtract bivector-e3ga bivector-e3ga)
(def-fun-subtract odd-versor odd-versor)
(def-fun-subtract line vector-e3ga)
(def-fun-subtract rotor-e3ga rotor-e3ga)
(def-fun-subtract rotor-e3ga no-ni)
(def-fun-subtract I5 circle)

(def-fun-apply-om om mv)
(def-fun-apply-om om normalized-point)
(def-fun-apply-om om circle)
(def-fun-apply-om om sphere)

(def-fun-apply-om grade1-om-e3ga vector-e3ga)
(def-fun-apply-om grade1-om-e3ga e1)
(def-fun-apply-om grade1-om-e3ga no)
(def-fun-apply-om grade1-om-e3ga normalized-point)

(def-fun-apply-om flat-point-om flat-point)
(def-fun-apply-om flat-point-om no-ni)

(def-fun-apply-versor mv mv)
(def-fun-apply-unit-versor mv mv)
(def-fun-apply-versor-wi mv mv mv)

(def-fun-apply-unit-versor rotor-e3ga vector-e3ga)
(def-fun-apply-unit-versor rotor-e3ga normalized-point)
(def-fun-apply-unit-versor rotor-e3ga dual-sphere)
(def-fun-apply-unit-versor rotor-e3ga bivector-e3ga)
(def-fun-apply-unit-versor rotor-e3ga line)
(def-fun-apply-unit-versor rotor-e3ga plane)
(def-fun-apply-unit-versor rotor-e3ga no-ni)
(def-fun-apply-unit-versor rotor-e3ga no)
(def-fun-apply-unit-versor rotor-e3ga ni)
(def-fun-apply-unit-versor rotor-e3ga rotor-e3ga)
(def-fun-apply-unit-versor rotor-e3ga I5)

(def-fun-apply-unit-versor even-versor vector-e3ga)
(def-fun-apply-unit-versor even-versor normalized-point)
(def-fun-apply-unit-versor even-versor dual-sphere)
(def-fun-apply-unit-versor even-versor bivector-e3ga)
(def-fun-apply-unit-versor even-versor line)
(def-fun-apply-unit-versor even-versor sphere)
(def-fun-apply-unit-versor even-versor ni)
(def-fun-apply-unit-versor even-versor rotor-e3ga)
(def-fun-apply-unit-versor even-versor  inv-I5)

(def-fun-apply-unit-versor odd-versor vector-e3ga)
(def-fun-apply-unit-versor odd-versor normalized-point)
(def-fun-apply-unit-versor odd-versor dual-sphere)
(def-fun-apply-unit-versor odd-versor bivector-e3ga)
(def-fun-apply-unit-versor odd-versor line)
(def-fun-apply-unit-versor odd-versor plane)

(def-fun-apply-versor rotor-e3ga vector-e3ga)
(def-fun-apply-versor rotor-e3ga normalized-point {:metric euclidean})
(def-fun-apply-versor rotor-e3ga dual-sphere)
(def-fun-apply-versor rotor-e3ga bivector-e3ga)
(def-fun-apply-versor rotor-e3ga line)
(def-fun-apply-versor rotor-e3ga plane)
(def-fun-apply-versor rotor-e3ga no-ni)
(def-fun-apply-versor rotor-e3ga no)
(def-fun-apply-versor rotor-e3ga ni)
(def-fun-apply-versor rotor-e3ga rotor-e3ga)
(def-fun-apply-versor rotor-e3ga I5)

(def-fun-apply-versor even-versor vector-e3ga)
(def-fun-apply-versor even-versor normalized-point)
(def-fun-apply-versor even-versor dual-sphere)
(def-fun-apply-versor even-versor bivector-e3ga)
(def-fun-apply-versor even-versor line)
(def-fun-apply-versor even-versor sphere)
(def-fun-apply-versor even-versor ni)
(def-fun-apply-versor even-versor rotor-e3ga)
(def-fun-apply-versor even-versor  inv-I5)

(def-fun-apply-versor odd-versor vector-e3ga  {:metric euclidean})
(def-fun-apply-versor odd-versor normalized-point)
(def-fun-apply-versor odd-versor dual-sphere)
(def-fun-apply-versor odd-versor bivector-e3ga)
(def-fun-apply-versor odd-versor line)
(def-fun-apply-versor odd-versor plane)

(def-fun-apply-versor-wi rotor-e3ga vector-e3ga rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga normalized-point rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga dual-sphere rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga bivector-e3ga rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga line rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga plane rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga e1 rotor-e3ga)
(def-fun-apply-versor-wi rotor-e3ga I5 rotor-e3ga)

(def-fun-div mv double)

(def-fun-div vector-e3ga double)
(def-fun-div normalized-point double)
(def-fun-div bivector-e3ga double)
(def-fun-div line double)
(def-fun-div plane double)
(def-fun-div sphere double)
(def-fun-div I5 double)
(def-fun-div even-versor double)
(def-fun-div odd-versor double)
(def-fun-div I3 double)
(def-fun-div no-ni double)

(def-fun-dual mv)
(def-fun-undual mv)

(def-fun-dual double)
(def-fun-undual double)
(def-fun-dual vector-e3ga)
(def-fun-undual vector-e3ga)
(def-fun-dual normalized-point)
(def-fun-undual normalized-point)
(def-fun-dual bivector-e3ga)
(def-fun-undual bivector-e3ga)
(def-fun-dual rotor-e3ga)
(def-fun-undual rotor-e3ga)
(def-fun-dual odd-versor)
(def-fun-undual odd-versor)
(def-fun-dual even-versor)
(def-fun-undual even-versor)
(def-fun-dual point-pair)
(def-fun-undual point-pair)
(def-fun-dual plane)
(def-fun-undual plane)
(def-fun-dual circle {:metric euclidean})
(def-fun-undual circle {:metric euclidean})
(def-fun-dual e1)
(def-fun-undual I3)
(def-fun-dual I5)
(def-fun-undual  inv-I5)


(def-fun-equals mv mv)
(def-fun-equals vector-e3ga vector-e3ga)
(def-fun-equals bivector-e3ga bivector-e3ga)
(def-fun-equals rotor-e3ga rotor-e3ga)
(def-fun-equals bivector-e3ga rotor-e3ga)
(def-fun-equals rotor-e3ga bivector-e3ga)
(def-fun-equals rotor-e3ga line)
(def-fun-equals even-versor plane)
(def-fun-equals circle line)
(def-fun-equals plane plane)
(def-fun-equals normalized-point normalized-point)
(def-fun-equals e1 e1)
(def-fun-equals I5  inv-I5)
(def-fun-equals no-ni point-pair)

(def-fun-extract-grade mv)
(def-fun-extract-grade 2 mv)
(def-fun-extract-grade 0 rotor-e3ga)
(def-fun-extract-grade 2 rotor-e3ga)
(def-fun-extract-grade 1 odd-versor)
(def-fun-extract-grade 3 odd-versor)
(def-fun-extract-grade 4 odd-versor)
(def-fun-extract-grade 0 even-versor)
(def-fun-extract-grade 1 even-versor)
(def-fun-extract-grade 4 even-versor)
(def-fun-extract-grade 0 normalized-point)
(def-fun-extract-grade 1 normalized-point)
(def-fun-extract-grade 2 normalized-point)
(def-fun-extract-grade 3 dual-sphere)
(def-fun-extract-grade 4 normalized-point)
(def-fun-extract-grade 5 normalized-point)

(def-fun-extract-grade 0 e1)
(def-fun-extract-grade 1 e2)
(def-fun-extract-grade 2 e3)
(def-fun-extract-grade 3 no)
(def-fun-extract-grade 4 e1)

(def-fun-extract-grade 0 I5)
(def-fun-extract-grade 1 inv-I5)
(def-fun-extract-grade 2 I5)
(def-fun-extract-grade 3 inv-I5)
(def-fun-extract-grade 4 I5)

(def-fun-gp mv mv)

(def-fun-gp vector-e3ga vector-e3ga)
(def-fun-gp rotor-e3ga vector-e3ga)
(def-fun-gp circle line)
(def-fun-gp rotor-e3ga line)
(def-fun-gp vector-e3ga rotor-e3ga)
(def-fun-gp rotor-e3ga rotor-e3ga)
(def-fun-gp plane rotor-e3ga)
(def-fun-gp plane odd-versor)
(def-fun-gp bivector-e3ga bivector-e3ga)
(def-fun-gp even-versor dual-sphere)
(def-fun-gp normalized-point normalized-point)
(def-fun-gp flat-point odd-versor)
(def-fun-gp e1 e2)
(def-fun-gp e3 ni)
(def-fun-gp no ni)
(def-fun-gp e3 inv-I5)


(def-fun-gp mv mv {:rename "gp-em" :metric euclidean})

(def-fun-gp vector-e3ga vector-e3ga {:rename "gp-em" :metric euclidean})
(def-fun-gp rotor-e3ga vector-e3ga {:rename "gp-em" :metric euclidean})
(def-fun-gp vector-e3ga line {:rename "gp-em" :metric euclidean})
(def-fun-gp rotor-e3ga circle {:rename "gp-em" :metric euclidean})
(def-fun-gp vector-e3ga rotor-e3ga {:rename "gp-em" :metric euclidean})
(def-fun-gp rotor-e3ga rotor-e3ga {:rename "gp-em" :metric euclidean})
(def-fun-gp plane rotor-e3ga {:rename "gp-em" :metric euclidean})
(def-fun-gp sphere circle {:rename "gp-em" :metric euclidean})
(def-fun-gp bivector-e3ga bivector-e3ga {:rename "gp-em" :metric euclidean})

(def-fun-grade-bitmap mv)

(def-fun-grade-bitmap rotor-e3ga )
(def-fun-grade-bitmap odd-versor )
(def-fun-grade-bitmap even-versor )
(def-fun-grade-bitmap vector-e3ga )
(def-fun-grade-bitmap bivector-e3ga )
(def-fun-grade-bitmap pseudoscalar )
(def-fun-grade-bitmap circle )
(def-fun-grade-bitmap point-pair )
(def-fun-grade-bitmap e1 )
(def-fun-grade-bitmap e2 )
(def-fun-grade-bitmap no-ni )
(def-fun-grade-bitmap inv-I5 )


(def-fun-hp mv mv)

(def-fun-hp vector-e3ga vector-e3ga)
(def-fun-hp bivector-e3ga bivector-e3ga)
(def-fun-hp rotor-e3ga rotor-e3ga)
(def-fun-hp odd-versor odd-versor)
(def-fun-hp vector-e3ga odd-versor)
(def-fun-hp even-versor even-versor)
(def-fun-hp bivector-e3ga rotor-e3ga)
(def-fun-hp circle even-versor)
(def-fun-hp pseudoscalar odd-versor)
(def-fun-hp rotor-e3ga bivector-e3ga)
(def-fun-hp even-versor bivector-e3ga)
(def-fun-hp sphere plane)
(def-fun-hp normalized-point e1)
(def-fun-hp vector-e3ga e1)
(def-fun-hp I5 pseudoscalar)
(def-fun-hp pseudoscalar  inv-I5)

(def-fun-ihp mv mv)
(def-fun-ihp vector-e3ga vector-e3ga)
(def-fun-ihp bivector-e3ga bivector-e3ga)
(def-fun-ihp rotor-e3ga rotor-e3ga)
(def-fun-ihp circle odd-versor)
(def-fun-ihp vector-e3ga odd-versor)
(def-fun-ihp even-versor even-versor)
(def-fun-ihp bivector-e3ga rotor-e3ga)
(def-fun-ihp plane even-versor)
(def-fun-ihp pseudoscalar odd-versor)
(def-fun-ihp rotor-e3ga bivector-e3ga)
(def-fun-ihp even-versor bivector-e3ga)
(def-fun-ihp dual-sphere normalized-point)
(def-fun-ihp normalized-point normalized-point)
(def-fun-ihp vector-e3ga e1)
(def-fun-ihp I5 pseudoscalar)
(def-fun-ihp pseudoscalar inv-I5)

(def-fun-incr mv)
(def-fun-incr bivector-e3ga)
(def-fun-incr rotor-e3ga)
(def-fun-incr even-versor)
(def-fun-incr point-pair)
(def-fun-incr no-ni)

(def-fun-decr mv)
(def-fun-decr bivector-e3ga)
(def-fun-decr rotor-e3ga)
(def-fun-decr even-versor)
(def-fun-decr no-ni)
(def-fun-decr free-vector)

(def-fun-sp mv mv)
(def-fun-lc mv mv)
(def-fun-rc mv mv)
(def-fun-hip mv mv)
(def-fun-mhip mv mv)

(def-fun-sp vector-e3ga vector-e3ga)
(def-fun-lc vector-e3ga plane)
(def-fun-rc vector-e3ga line)
(def-fun-hip vector-e3ga vector-e3ga)
(def-fun-mhip pseudoscalar vector-e3ga)

(def-fun-sp bivector-e3ga odd-versor)
(def-fun-lc bivector-e3ga vector-e3ga)
(def-fun-rc even-versor vector-e3ga)
(def-fun-hip odd-versor vector-e3ga)
(def-fun-mhip bivector-e3ga vector-e3ga)

(def-fun-sp vector-e3ga pseudoscalar)
(def-fun-lc vector-e3ga even-versor)
(def-fun-rc vector-e3ga bivector-e3ga)
(def-fun-hip vector-e3ga plane)
(def-fun-mhip vector-e3ga line)

(def-fun-sp even-versor rotor-e3ga)
(def-fun-lc even-versor rotor-e3ga)
(def-fun-rc odd-versor rotor-e3ga)
(def-fun-hip odd-versor rotor-e3ga)
(def-fun-mhip odd-versor rotor-e3ga)

(def-fun-sp e1 rotor-e3ga)
(def-fun-lc e2 rotor-e3ga)
(def-fun-rc I5 rotor-e3ga)
(def-fun-hip  inv-I5 rotor-e3ga)
(def-fun-mhip no rotor-e3ga)

(def-fun-sp e1 e1)
(def-fun-lc e2 e3)
(def-fun-rc I5 inv-I5)
(def-fun-hip inv-I5 ni)
(def-fun-mhip no e1)

(def-fun-sp rotor-e3ga plane)
(def-fun-lc rotor-e3ga line)
(def-fun-rc rotor-e3ga even-versor)
(def-fun-hip rotor-e3ga bivector-e3ga)
(def-fun-mhip rotor-e3ga pseudoscalar)

(def-fun-norm mv)

(def-fun-norm vector-e3ga)
(def-fun-norm bivector-e3ga)
(def-fun-norm line)
(def-fun-norm plane)
(def-fun-norm rotor-e3ga)
(def-fun-norm odd-versor)
(def-fun-norm even-versor)
(def-fun-norm normalized-point)
(def-fun-norm dual-sphere)
(def-fun-norm pseudoscalar)
(def-fun-norm e1)
(def-fun-norm no-ni)
(def-fun-norm inv-I5)

(def-fun-norm2 mv)

(def-fun-norm2 vector-e3ga)
(def-fun-norm2 bivector-e3ga)
(def-fun-norm2 normalized-point)
(def-fun-norm2 dual-sphere)
(def-fun-norm2 circle)
(def-fun-norm2 sphere)
(def-fun-norm2 rotor-e3ga)
(def-fun-norm2 odd-versor)
(def-fun-norm2 even-versor)
(def-fun-norm2 pseudoscalar)
(def-fun-norm2 e3)
(def-fun-norm2 no)
(def-fun-norm2 I5)

(def-fun-norm mv {:rename "norm-em" :metric euclidean})

(def-fun-norm vector-e3ga {:rename "norm-em" :metric euclidean})
(def-fun-norm bivector-e3ga {:rename "norm-em" :metric euclidean})
(def-fun-norm free-vector {:rename "norm-em" :metric euclidean})
(def-fun-norm free-bivector {:rename "norm-em" :metric euclidean})
(def-fun-norm rotor-e3ga {:rename "norm-em" :metric euclidean})
(def-fun-norm odd-versor {:rename "norm-em" :metric euclidean})
(def-fun-norm even-versor {:rename "norm-em" :metric euclidean})
(def-fun-norm pseudoscalar {:rename "norm-em" :metric euclidean})

(def-fun-norm2  mv {:rename "norm-em" :metric euclidean})

(def-fun-norm2 vector-e3ga {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 bivector-e3ga {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 line {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 plane {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 rotor-e3ga {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 odd-versor {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 even-versor {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 pseudoscalar {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 no {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 I5 {:rename "norm2-em" :metric euclidean})
(def-fun-norm2 e1 {:rename "norm2-em" :metric euclidean})

(def-fun-op mv mv)

(def-fun-op vector-e3ga vector-e3ga)
(def-fun-op rotor-e3ga vector-e3ga)
(def-fun-op vector-e3ga line)
(def-fun-op rotor-e3ga circle)
(def-fun-op vector-e3ga rotor-e3ga)
(def-fun-op rotor-e3ga rotor-e3ga)
(def-fun-op plane rotor-e3ga)
(def-fun-op circle point-pair)
(def-fun-op bivector-e3ga bivector-e3ga)
(def-fun-op pseudoscalar odd-versor)
(def-fun-op no ni)
(def-fun-op ni no)


(def-fun-gp mv double)
(def-fun-sas mv)

(def-fun-exp mv)
(def-fun-exp point-pair)
(def-fun-exp free-vector)
(def-fun-exp no-ni)
(def-fun-cosh mv)
(def-fun-cosh flat-point)
(def-fun-sinh mv)
(def-fun-sinh bivector-e3ga)
(def-fun-sinh free-vector)
(def-fun-cos mv)
(def-fun-cos bivector-e3ga)
(def-fun-cos point-pair)
(def-fun-cos free-vector)
(def-fun-cos no-ni)
(def-fun-sin mv)
(def-fun-sin bivector-e3ga)
(def-fun-sin point-pair)
(def-fun-sin free-vector)
(def-fun-sin no-ni)


(def-fun-negate mv)

(def-fun-negate vector-e3ga)
(def-fun-negate free-vector)
(def-fun-negate normalized-point)
(def-fun-negate dual-sphere)
(def-fun-negate line)
(def-fun-negate sphere)
(def-fun-negate rotor-e3ga)
(def-fun-negate odd-versor)
(def-fun-negate even-versor)
(def-fun-negate pseudoscalar)
(def-fun-negate e1)
(def-fun-negate e2)
(def-fun-negate no-ni)
(def-fun-negate double)

(def-fun-reverse mv)
(def-fun-reverse vector-e3ga)
(def-fun-reverse bivector-e3ga)
(def-fun-reverse normalized-point)
(def-fun-reverse dual-sphere)
(def-fun-reverse line)
(def-fun-reverse circle)
(def-fun-reverse rotor-e3ga)
(def-fun-reverse odd-versor)
(def-fun-reverse even-versor)
(def-fun-reverse pseudoscalar)
(def-fun-reverse double)
(def-fun-reverse e1)
(def-fun-reverse e3)
(def-fun-reverse inv-I5)

(def-fun-clifford-conjugate mv)
(def-fun-clifford-conjugate vector-e3ga)
(def-fun-clifford-conjugate point-pair)
(def-fun-clifford-conjugate normalized-point)
(def-fun-clifford-conjugate free-vector)
(def-fun-clifford-conjugate line)
(def-fun-clifford-conjugate plane)
(def-fun-clifford-conjugate rotor-e3ga)
(def-fun-clifford-conjugate odd-versor)
(def-fun-clifford-conjugate even-versor)
(def-fun-clifford-conjugate pseudoscalar)
(def-fun-clifford-conjugate double)
(def-fun-clifford-conjugate e1)
(def-fun-clifford-conjugate no-ni)
(def-fun-clifford-conjugate I3)

(def-fun-grade-involution mv)
(def-fun-grade-involution vector-e3ga)
(def-fun-grade-involution bivector-e3ga)
(def-fun-grade-involution normalized-point)
(def-fun-grade-involution dual-sphere)
(def-fun-grade-involution line)
(def-fun-grade-involution plane)
(def-fun-grade-involution rotor-e3ga)
(def-fun-grade-involution odd-versor)
(def-fun-grade-involution even-versor)
(def-fun-grade-involution pseudoscalar)
(def-fun-grade-involution double)
(def-fun-grade-involution no)
(def-fun-grade-involution ni)
(def-fun-grade-involution I3)


(def-fun-unit mv)
(def-fun-unit vector-e3ga)
(def-fun-unit bivector-e3ga)
(def-fun-unit line)
(def-fun-unit sphere)
(def-fun-unit rotor-e3ga)
(def-fun-unit odd-versor)
(def-fun-unit even-versor)
(def-fun-unit normalized-point)
(def-fun-unit dual-sphere)
(def-fun-unit pseudoscalar)
(def-fun-unit point-pair)
(def-fun-unit e2)
(def-fun-unit I5)

(def-fun-unit mv {:rename "unit-em" :metric euclidean})
(def-fun-unit vector-e3ga {:rename "unit-em" :metric euclidean})
(def-fun-unit bivector-e3ga {:rename "unit-em" :metric euclidean})
(def-fun-unit circle {:rename "unit-em" :metric euclidean})
(def-fun-unit plane {:rename "unit-em" :metric euclidean})
(def-fun-unit rotor-e3ga {:rename "unit-em" :metric euclidean})
(def-fun-unit odd-versor {:rename "unit-em" :metric euclidean})
(def-fun-unit even-versor {:rename "unit-em" :metric euclidean})
(def-fun-unit pseudoscalar {:rename "unit-em" :metric euclidean})

(def-fun-add even-versor even-versor)
(def-fun-subtract even-versor even-versor)

(def-fun-gp point-pair point-pair  {:return-type even-versor})
(def-fun-gp even-versor point-pair {:return-type even-versor})
(def-fun-gp even-versor even-versor {:return-type even-versor})

(def-fun-sas point-pair)
(def-fun-gp even-versor double)
(def-fun-gp point-pair double)


(def-fun-inv-versor mv)
(def-fun-inv-versor mv {:rename "inv-versor-em" :metric euclidean})



