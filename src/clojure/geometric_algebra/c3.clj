(ns geometric-algebra.c3
  (:require [geometric-algebra.generator.context :as ctx-ns]
            [geometric-algebra.generator.multivector :as mv-ns]
            [geometric-algebra.generator.special-multivector :as smv-ns]
            [geometric-algebra.generator.constant :as const-ns]
            [geometric-algebra.generator.outer-morphism :as om-ns]
            [geometric-algebra.generator.converter :as conv-ns]
            [geometric-algebra.generator.dispatch :as ga-ns]))


(ctx-ns/def-spec default-spec
  :license :gpl
  :language :clojure
  :namespace "c3ga"
  :coord-storage :variables
  :default-operator-bindings true
  :dimension 5
  :report-usage false
  :gmv-code :expand
  :parser :builtin
  :test-suite true
  :copyright "Fred Eisele, Based on Gaigen 2.5 Test Suite")

(ctx-ns/def-output-directory default-output-directory)
(ctx-ns/def-output-file-rename default-file-rename-map)


(ctx-ns/def-inline default-inline
  :constructors true
  :setters true
  :assigns true
  :operators true
  :functions true)


(ctx-ns/def-float-type default-float-type :double)


(ctx-ns/def-basis conformal-basis [:no :e1 :e2 :e3 :ni] {:default true} )
(ctx-ns/def-basis euclidean-basis [:e1 :e2 :e3] {:default true} )


(ctx-ns/def-metric conformal-metric {:default true :round false}
  {[:no :ni] -1
   [:e1 :e1] 1 [:e2 :e2] 1 [:e3 :e3] 1})

(ctx-ns/def-metric euclidean-metric {:round false}
  {[:no :no] 1 [:e1 :e1] 1 [:e2 :e2] 1 [:e3 :e3] 1 [:ni :ni] 1})

(ctx-ns/def-context ctx
  :metric conformal-metric
  :basis conformal-basis
  :spec default-spec
  :inline default-inline :output default-output-directory
  :rename-map default-file-rename-map
  :real-type default-float-type)

(ctx-ns/def-context etx
  :metric euclidean-metric
  :basis euclidean-basis
  :spec default-spec
  :inline default-inline :output default-output-directory
  :rename-map default-file-rename-map
  :real-type default-float-type)


;; define the basic multi-vector factory.
(mv-ns/def-mv ctx mv {:compress "by-grade" :coordinateOrder "default" :memAlloc "full"})


;; define the specialized multi-vector factories.
(smv-ns/def-blade ctx no {:const true} {[:no] 1})
(smv-ns/def-blade ctx e1 {:const true} {[:e1] 1})
(smv-ns/def-blade ctx e2 {:const true} {[:e2] 1})
(smv-ns/def-blade ctx e3 {:const true} {[:e3] 1})
(smv-ns/def-blade ctx ni {:const true} {[:ni] 1})

(smv-ns/def-blade ctx no-ni {:const true} {[:no :ni] 1})
(smv-ns/def-blade ctx I3 {:const true} {[:e1 :e2 :e3] 1})
(smv-ns/def-blade ctx I5 {:const true} {[:no :e1 :e2 :e3 :ni] 1})
(smv-ns/def-blade ctx inv-I5 {:const true} {[:no :e1 :e2 :e3 :ni] -1})

(smv-ns/def-blade ctx vector-e3ga #{[:e1] [:e2] [:e3]})
(smv-ns/def-blade ctx bivector-e3ga #{[:e1 :e2] [:e2 :e3] [:e3 :e1]})

(smv-ns/def-blade ctx normalized-point {[:no] 1} #{[:e1] [:e2] [:e3] [:ni]})
(smv-ns/def-blade ctx dual-sphere #{[:no] [:e1] [:e2] [:e3] [:ni]})
(smv-ns/def-blade ctx dual-plane #{[:e1] [:e2] [:e3] [:ni]})
(smv-ns/def-blade ctx free-vector #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})
(smv-ns/def-blade ctx free-bivector  #{[:e1 :e2 :ni] [:e2 :e3 :ni] [:e3 :e1 :ni]})

(smv-ns/def-blade ctx flat-point
  #{[e1 :ni] [e2 :ni] [:e3 :ni] [:no :ni]})

(smv-ns/def-blade ctx point-pair
  #{[:no :e1] [:no :e2] [:no :e3]
    [:e1 :e2] [:e2 :e3] [:e3 :e1]
    [:e1 :ni] [:e2 :ni] [:e3 :ni]
    [:no :ni]})

(smv-ns/def-blade ctx line
  #{[:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni]
    [:e1 :no :ni] [:e2 :no :ni] [:e3 :no :ni]})

(smv-ns/def-blade ctx circle
  #{[:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
    [:e1 :e2 :e3]
    [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
    [:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni] })

(smv-ns/def-blade ctx sphere
  #{[:e1 :e2 :e3 :ni] [:no :e1 :e2 :e3]
    [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(smv-ns/def-blade ctx plane
  #{[:e1 :e2 :e3 :ni] [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(smv-ns/def-blade ctx pseudoscalar #{[:no :e1 :e2 :e3 :ni]})

(smv-ns/def-versor ctx rotor-e3ga #{:scalar [:e1 :e2] [:e2 :e3] [:e3 :e1]})

(smv-ns/def-versor ctx normalized-translator
  {:scalar 1.0} #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})

(smv-ns/def-versor ctx translator
  #{:scalar [:e1 :ni] [:e2 :ni] [:e3 :ni]})

(smv-ns/def-versor ctx RBM
  #{:scalar
    [:e1 :e2] [:e2 :e3] [:e3 :e1]
    [:e1 :ni] [:e2 :ni] [:e3 :ni]
    [:e1 :e2 :e3 :ni]})

(smv-ns/def-versor ctx even-versor
  #{:scalar
    [:no :e1] [:no :e2] [:no :e3]
    [:e1 :e2] [:e2 :e3] [:e3 :e1]
    [:e1 :ni] [:e2 :ni] [:e3 :ni]
    [:no :ni]
    [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]
    [:no :e1 :e2 :e3]
    [:e1 :e2 :e3 :ni]})

(smv-ns/def-versor ctx odd-versor
  {[:no] [:e1] [:e2] [:e3] [:ni]
   [:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
   [:e1 :e2 :e3]
   [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
   [:e1 :e2 :ni]
   [:e1 :e3 :ni] [:e2 :e3 :ni]
   [:no :e1 :e2 :e3 :ni] })


;; define some commmonly used constants.
(const-ns/def-constant ctx vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})
(const-ns/def-constant ctx vector-e2 {:type "vector-e3ga"} {[:e1] 0 [:e2] 1 [:e3] 0})
(const-ns/def-constant ctx vector-e3 {:type "vector-e3ga"} {[:e1] 0 [:e2] 0 [:e3] 1})

(const-ns/def-constant ctx point-at-origin
  "The point at the origin . . ."
  {:type "normalized-point"} {[:no] 1})

(const-ns/def-constant ctx point-at-infinity
  "The point at infinity . . ."
  {:type "dual-sphere"} {[:ni] 1})

;; the outermorphism
(om-ns/def-om ctx om)

(om-ns/def-som ctx grade1-om-e3ga :domain #{[:e1] [:e2] [:e3]})
(om-ns/def-som ctx grade1-om :domain #{[:no] [:e1] [:e2] [:e3] [:ni]})

(om-ns/def-som ctx flat-point-om
  :domain #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]}
  :range #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]} )



(conv-ns/def-con normalized-point vector-e3ga
  {:arg-source-name "P"
   :doc "Extracts vector part of point"})

(conv-ns/def-con dual-sphere vector-e3ga
  {:arg-source-name "S"
   :doc "Extracts vector part of dual sphere"})

(conv-ns/def-con rotor-e3ga bivector-e3ga
  {:doc "Extracts Euclidean bivector part of Euclidean rotor"})

(conv-ns/def-con normalized-point dual-sphere
  {:arg-source-name "P"
   :doc "Converts a normalized point to a dual sphere"
   :rename "point-to-sphere"})

(conv-ns/def-con point-pair even-versor)

(ga-ns/def-random-double {:output-name "genrand" :optionGen "libc"})

(ga-ns/def-cga-point vector-e3ga)
(ga-ns/def-cga-point [:double :double :double] {:origin no :infinity ni} )

(ga-ns/def-random-point )

(ga-ns/def-cga-point-distance normalized-point normalized-point )
(ga-ns/def-cga-point-distance dual-sphere dual-sphere )

(ga-ns/def-add mv mv)
(ga-ns/def-add vector-e3ga vector-e3ga)
(ga-ns/def-add vector-e3ga normalized-point)
(ga-ns/def-add vector-e3ga dual-sphere)
(ga-ns/def-add bivector-e3ga bivector-e3ga)
(ga-ns/def-add plane plane)
(ga-ns/def-add line circle)
(ga-ns/def-add circle vector-e3ga)
(ga-ns/def-add e1 e2)
(ga-ns/def-add I5 circle)


(ga-ns/def-subtract mv mv)
(ga-ns/def-subtract vector-e3ga vector-e3ga)
(ga-ns/def-subtract bivector-e3ga bivector-e3ga)
(ga-ns/def-subtract odd-versor odd-versor)
(ga-ns/def-subtract line vector-e3ga)
(ga-ns/def-subtract rotor-e3ga rotor-e3ga)
(ga-ns/def-subtract rotor-e3ga no-ni)
(ga-ns/def-subtract I5 circle)

(ga-ns/def-apply-om om mv)
(ga-ns/def-apply-om om normalized-point)
(ga-ns/def-apply-om om circle)
(ga-ns/def-apply-om om sphere)

(ga-ns/def-apply-om grade1-om-e3ga vector-e3ga)
(ga-ns/def-apply-om grade1-om-e3ga e1)
(ga-ns/def-apply-om grade1-om-e3ga no)
(ga-ns/def-apply-om grade1-om-e3ga normalized-point)

(ga-ns/def-apply-om flat-point-om flat-point)
(ga-ns/def-apply-om flat-point-om no-ni)

(ga-ns/def-apply-versor mv mv)
(ga-ns/def-apply-unit-versor mv mv)
(ga-ns/def-apply-versor-wi mv mv mv)

(ga-ns/def-apply-unit-versor rotor-e3ga vector-e3ga)
(ga-ns/def-apply-unit-versor rotor-e3ga normalized-point)
(ga-ns/def-apply-unit-versor rotor-e3ga dual-sphere)
(ga-ns/def-apply-unit-versor rotor-e3ga bivector-e3ga)
(ga-ns/def-apply-unit-versor rotor-e3ga line)
(ga-ns/def-apply-unit-versor rotor-e3ga plane)
(ga-ns/def-apply-unit-versor rotor-e3ga no-ni)
(ga-ns/def-apply-unit-versor rotor-e3ga no)
(ga-ns/def-apply-unit-versor rotor-e3ga ni)
(ga-ns/def-apply-unit-versor rotor-e3ga rotor-e3ga)
(ga-ns/def-apply-unit-versor rotor-e3ga I5)

(ga-ns/def-apply-unit-versor even-versor vector-e3ga)
(ga-ns/def-apply-unit-versor even-versor normalized-point)
(ga-ns/def-apply-unit-versor even-versor dual-sphere)
(ga-ns/def-apply-unit-versor even-versor bivector-e3ga)
(ga-ns/def-apply-unit-versor even-versor line)
(ga-ns/def-apply-unit-versor even-versor sphere)
(ga-ns/def-apply-unit-versor even-versor ni)
(ga-ns/def-apply-unit-versor even-versor rotor-e3ga)
(ga-ns/def-apply-unit-versor even-versor  inv-I5)

(ga-ns/def-apply-unit-versor odd-versor vector-e3ga)
(ga-ns/def-apply-unit-versor odd-versor normalized-point)
(ga-ns/def-apply-unit-versor odd-versor dual-sphere)
(ga-ns/def-apply-unit-versor odd-versor bivector-e3ga)
(ga-ns/def-apply-unit-versor odd-versor line)
(ga-ns/def-apply-unit-versor odd-versor plane)

(ga-ns/def-apply-versor rotor-e3ga vector-e3ga)
(ga-ns/def-apply-versor rotor-e3ga normalized-point {:metric euclidean})
(ga-ns/def-apply-versor rotor-e3ga dual-sphere)
(ga-ns/def-apply-versor rotor-e3ga bivector-e3ga)
(ga-ns/def-apply-versor rotor-e3ga line)
(ga-ns/def-apply-versor rotor-e3ga plane)
(ga-ns/def-apply-versor rotor-e3ga no-ni)
(ga-ns/def-apply-versor rotor-e3ga no)
(ga-ns/def-apply-versor rotor-e3ga ni)
(ga-ns/def-apply-versor rotor-e3ga rotor-e3ga)
(ga-ns/def-apply-versor rotor-e3ga I5)

(ga-ns/def-apply-versor even-versor vector-e3ga)
(ga-ns/def-apply-versor even-versor normalized-point)
(ga-ns/def-apply-versor even-versor dual-sphere)
(ga-ns/def-apply-versor even-versor bivector-e3ga)
(ga-ns/def-apply-versor even-versor line)
(ga-ns/def-apply-versor even-versor sphere)
(ga-ns/def-apply-versor even-versor ni)
(ga-ns/def-apply-versor even-versor rotor-e3ga)
(ga-ns/def-apply-versor even-versor  inv-I5)

(ga-ns/def-apply-versor odd-versor vector-e3ga  {:metric euclidean})
(ga-ns/def-apply-versor odd-versor normalized-point)
(ga-ns/def-apply-versor odd-versor dual-sphere)
(ga-ns/def-apply-versor odd-versor bivector-e3ga)
(ga-ns/def-apply-versor odd-versor line)
(ga-ns/def-apply-versor odd-versor plane)

(ga-ns/def-apply-versor-wi rotor-e3ga vector-e3ga rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga normalized-point rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga dual-sphere rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga bivector-e3ga rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga line rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga plane rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga e1 rotor-e3ga)
(ga-ns/def-apply-versor-wi rotor-e3ga I5 rotor-e3ga)

(ga-ns/def-div mv :double)

(ga-ns/def-div vector-e3ga :double)
(ga-ns/def-div normalized-point :double)
(ga-ns/def-div bivector-e3ga :double)
(ga-ns/def-div line :double)
(ga-ns/def-div plane :double)
(ga-ns/def-div sphere :double)
(ga-ns/def-div I5 :double)
(ga-ns/def-div even-versor :double)
(ga-ns/def-div odd-versor :double)
(ga-ns/def-div I3 :double)
(ga-ns/def-div no-ni :double)

(ga-ns/def-dual mv)
(ga-ns/def-undual mv)

(ga-ns/def-dual :double)
(ga-ns/def-undual :double)
(ga-ns/def-dual vector-e3ga)
(ga-ns/def-undual vector-e3ga)
(ga-ns/def-dual normalized-point)
(ga-ns/def-undual normalized-point)
(ga-ns/def-dual bivector-e3ga)
(ga-ns/def-undual bivector-e3ga)
(ga-ns/def-dual rotor-e3ga)
(ga-ns/def-undual rotor-e3ga)
(ga-ns/def-dual odd-versor)
(ga-ns/def-undual odd-versor)
(ga-ns/def-dual even-versor)
(ga-ns/def-undual even-versor)
(ga-ns/def-dual point-pair)
(ga-ns/def-undual point-pair)
(ga-ns/def-dual plane)
(ga-ns/def-undual plane)
(ga-ns/def-dual circle {:metric euclidean})
(ga-ns/def-undual circle {:metric euclidean})
(ga-ns/def-dual e1)
(ga-ns/def-undual I3)
(ga-ns/def-dual I5)
(ga-ns/def-undual  inv-I5)


(ga-ns/def-equals mv mv)
(ga-ns/def-equals vector-e3ga vector-e3ga)
(ga-ns/def-equals bivector-e3ga bivector-e3ga)
(ga-ns/def-equals rotor-e3ga rotor-e3ga)
(ga-ns/def-equals bivector-e3ga rotor-e3ga)
(ga-ns/def-equals rotor-e3ga bivector-e3ga)
(ga-ns/def-equals rotor-e3ga line)
(ga-ns/def-equals even-versor plane)
(ga-ns/def-equals circle line)
(ga-ns/def-equals plane plane)
(ga-ns/def-equals normalized-point normalized-point)
(ga-ns/def-equals e1 e1)
(ga-ns/def-equals I5  inv-I5)
(ga-ns/def-equals no-ni point-pair)

(ga-ns/def-extract-grades mv)
(ga-ns/def-extract-grade 2 mv)
(ga-ns/def-extract-grade 0 rotor-e3ga)
(ga-ns/def-extract-grade 2 rotor-e3ga)
(ga-ns/def-extract-grade 1 odd-versor)
(ga-ns/def-extract-grade 3 odd-versor)
(ga-ns/def-extract-grade 4 odd-versor)
(ga-ns/def-extract-grade 0 even-versor)
(ga-ns/def-extract-grade 1 even-versor)
(ga-ns/def-extract-grade 4 even-versor)
(ga-ns/def-extract-grade 0 normalized-point)
(ga-ns/def-extract-grade 1 normalized-point)
(ga-ns/def-extract-grade 2 normalized-point)
(ga-ns/def-extract-grade 3 dual-sphere)
(ga-ns/def-extract-grade 4 normalized-point)
(ga-ns/def-extract-grade 5 normalized-point)

(ga-ns/def-extract-grade 0 e1)
(ga-ns/def-extract-grade 1 e2)
(ga-ns/def-extract-grade 2 e3)
(ga-ns/def-extract-grade 3 no)
(ga-ns/def-extract-grade 4 e1)

(ga-ns/def-extract-grade 0 I5)
(ga-ns/def-extract-grade 1 inv-I5)
(ga-ns/def-extract-grade 2 I5)
(ga-ns/def-extract-grade 3 inv-I5)
(ga-ns/def-extract-grade 4 I5)

(ga-ns/def-gp mv mv)

(ga-ns/def-gp vector-e3ga vector-e3ga)
(ga-ns/def-gp rotor-e3ga vector-e3ga)
(ga-ns/def-gp circle line)
(ga-ns/def-gp rotor-e3ga line)
(ga-ns/def-gp vector-e3ga rotor-e3ga)
(ga-ns/def-gp rotor-e3ga rotor-e3ga)
(ga-ns/def-gp plane rotor-e3ga)
(ga-ns/def-gp plane odd-versor)
(ga-ns/def-gp bivector-e3ga bivector-e3ga)
(ga-ns/def-gp even-versor dual-sphere)
(ga-ns/def-gp normalized-point normalized-point)
(ga-ns/def-gp flat-point odd-versor)
(ga-ns/def-gp e1 e2)
(ga-ns/def-gp e3 ni)
(ga-ns/def-gp no ni)
(ga-ns/def-gp e3 inv-I5)


(ga-ns/def-gp mv mv {:rename "gp-em" :metric euclidean})

(ga-ns/def-gp vector-e3ga vector-e3ga {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp rotor-e3ga vector-e3ga {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp vector-e3ga line {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp rotor-e3ga circle {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp vector-e3ga rotor-e3ga {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp rotor-e3ga rotor-e3ga {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp plane rotor-e3ga {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp sphere circle {:rename "gp-em" :metric euclidean})
(ga-ns/def-gp bivector-e3ga bivector-e3ga {:rename "gp-em" :metric euclidean})

(ga-ns/def-grade-bitmap mv)

(ga-ns/def-grade-bitmap rotor-e3ga )
(ga-ns/def-grade-bitmap odd-versor )
(ga-ns/def-grade-bitmap even-versor )
(ga-ns/def-grade-bitmap vector-e3ga )
(ga-ns/def-grade-bitmap bivector-e3ga )
(ga-ns/def-grade-bitmap pseudoscalar )
(ga-ns/def-grade-bitmap circle )
(ga-ns/def-grade-bitmap point-pair )
(ga-ns/def-grade-bitmap e1 )
(ga-ns/def-grade-bitmap e2 )
(ga-ns/def-grade-bitmap no-ni )
(ga-ns/def-grade-bitmap inv-I5 )


(ga-ns/def-hp mv mv)

(ga-ns/def-hp vector-e3ga vector-e3ga)
(ga-ns/def-hp bivector-e3ga bivector-e3ga)
(ga-ns/def-hp rotor-e3ga rotor-e3ga)
(ga-ns/def-hp odd-versor odd-versor)
(ga-ns/def-hp vector-e3ga odd-versor)
(ga-ns/def-hp even-versor even-versor)
(ga-ns/def-hp bivector-e3ga rotor-e3ga)
(ga-ns/def-hp circle even-versor)
(ga-ns/def-hp pseudoscalar odd-versor)
(ga-ns/def-hp rotor-e3ga bivector-e3ga)
(ga-ns/def-hp even-versor bivector-e3ga)
(ga-ns/def-hp sphere plane)
(ga-ns/def-hp normalized-point e1)
(ga-ns/def-hp vector-e3ga e1)
(ga-ns/def-hp I5 pseudoscalar)
(ga-ns/def-hp pseudoscalar  inv-I5)

(ga-ns/def-inv-hp mv mv)
(ga-ns/def-inv-hp vector-e3ga vector-e3ga)
(ga-ns/def-inv-hp bivector-e3ga bivector-e3ga)
(ga-ns/def-inv-hp rotor-e3ga rotor-e3ga)
(ga-ns/def-inv-hp circle odd-versor)
(ga-ns/def-inv-hp vector-e3ga odd-versor)
(ga-ns/def-inv-hp even-versor even-versor)
(ga-ns/def-inv-hp bivector-e3ga rotor-e3ga)
(ga-ns/def-inv-hp plane even-versor)
(ga-ns/def-inv-hp pseudoscalar odd-versor)
(ga-ns/def-inv-hp rotor-e3ga bivector-e3ga)
(ga-ns/def-inv-hp even-versor bivector-e3ga)
(ga-ns/def-inv-hp dual-sphere normalized-point)
(ga-ns/def-inv-hp normalized-point normalized-point)
(ga-ns/def-inv-hp vector-e3ga e1)
(ga-ns/def-inv-hp I5 pseudoscalar)
(ga-ns/def-inv-hp pseudoscalar inv-I5)

(ga-ns/def-incr mv)
(ga-ns/def-incr bivector-e3ga)
(ga-ns/def-incr rotor-e3ga)
(ga-ns/def-incr even-versor)
(ga-ns/def-incr point-pair)
(ga-ns/def-incr no-ni)

(ga-ns/def-decr mv)
(ga-ns/def-decr bivector-e3ga)
(ga-ns/def-decr rotor-e3ga)
(ga-ns/def-decr even-versor)
(ga-ns/def-decr no-ni)
(ga-ns/def-decr free-vector)

(ga-ns/def-sp mv mv)
(ga-ns/def-lc mv mv)
(ga-ns/def-rc mv mv)
(ga-ns/def-hip mv mv)
(ga-ns/def-mhip mv mv)

(ga-ns/def-sp vector-e3ga vector-e3ga)
(ga-ns/def-lc vector-e3ga plane)
(ga-ns/def-rc vector-e3ga line)
(ga-ns/def-hip vector-e3ga vector-e3ga)
(ga-ns/def-mhip pseudoscalar vector-e3ga)

(ga-ns/def-sp bivector-e3ga odd-versor)
(ga-ns/def-lc bivector-e3ga vector-e3ga)
(ga-ns/def-rc even-versor vector-e3ga)
(ga-ns/def-hip odd-versor vector-e3ga)
(ga-ns/def-mhip bivector-e3ga vector-e3ga)

(ga-ns/def-sp vector-e3ga pseudoscalar)
(ga-ns/def-lc vector-e3ga even-versor)
(ga-ns/def-rc vector-e3ga bivector-e3ga)
(ga-ns/def-hip vector-e3ga plane)
(ga-ns/def-mhip vector-e3ga line)

(ga-ns/def-sp even-versor rotor-e3ga)
(ga-ns/def-lc even-versor rotor-e3ga)
(ga-ns/def-rc odd-versor rotor-e3ga)
(ga-ns/def-hip odd-versor rotor-e3ga)
(ga-ns/def-mhip odd-versor rotor-e3ga)

(ga-ns/def-sp e1 rotor-e3ga)
(ga-ns/def-lc e2 rotor-e3ga)
(ga-ns/def-rc I5 rotor-e3ga)
(ga-ns/def-hip  inv-I5 rotor-e3ga)
(ga-ns/def-mhip no rotor-e3ga)

(ga-ns/def-sp e1 e1)
(ga-ns/def-lc e2 e3)
(ga-ns/def-rc I5 inv-I5)
(ga-ns/def-hip inv-I5 ni)
(ga-ns/def-mhip no e1)

(ga-ns/def-sp rotor-e3ga plane)
(ga-ns/def-lc rotor-e3ga line)
(ga-ns/def-rc rotor-e3ga even-versor)
(ga-ns/def-hip rotor-e3ga bivector-e3ga)
(ga-ns/def-mhip rotor-e3ga pseudoscalar)

(ga-ns/def-norm mv)

(ga-ns/def-norm vector-e3ga)
(ga-ns/def-norm bivector-e3ga)
(ga-ns/def-norm line)
(ga-ns/def-norm plane)
(ga-ns/def-norm rotor-e3ga)
(ga-ns/def-norm odd-versor)
(ga-ns/def-norm even-versor)
(ga-ns/def-norm normalized-point)
(ga-ns/def-norm dual-sphere)
(ga-ns/def-norm pseudoscalar)
(ga-ns/def-norm e1)
(ga-ns/def-norm no-ni)
(ga-ns/def-norm inv-I5)

(ga-ns/def-norm2 mv)

(ga-ns/def-norm2 vector-e3ga)
(ga-ns/def-norm2 bivector-e3ga)
(ga-ns/def-norm2 normalized-point)
(ga-ns/def-norm2 dual-sphere)
(ga-ns/def-norm2 circle)
(ga-ns/def-norm2 sphere)
(ga-ns/def-norm2 rotor-e3ga)
(ga-ns/def-norm2 odd-versor)
(ga-ns/def-norm2 even-versor)
(ga-ns/def-norm2 pseudoscalar)
(ga-ns/def-norm2 e3)
(ga-ns/def-norm2 no)
(ga-ns/def-norm2 I5)

(ga-ns/def-norm mv {:rename "norm-em" :metric euclidean})

(ga-ns/def-norm vector-e3ga {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm bivector-e3ga {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm free-vector {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm free-bivector {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm rotor-e3ga {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm odd-versor {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm even-versor {:rename "norm-em" :metric euclidean})
(ga-ns/def-norm pseudoscalar {:rename "norm-em" :metric euclidean})

(ga-ns/def-norm2  mv {:rename "norm-em" :metric euclidean})

(ga-ns/def-norm2 vector-e3ga {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 bivector-e3ga {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 line {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 plane {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 rotor-e3ga {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 odd-versor {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 even-versor {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 pseudoscalar {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 no {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 I5 {:rename "norm2-em" :metric euclidean})
(ga-ns/def-norm2 e1 {:rename "norm2-em" :metric euclidean})

(ga-ns/def-op mv mv)

(ga-ns/def-op vector-e3ga vector-e3ga)
(ga-ns/def-op rotor-e3ga vector-e3ga)
(ga-ns/def-op vector-e3ga line)
(ga-ns/def-op rotor-e3ga circle)
(ga-ns/def-op vector-e3ga rotor-e3ga)
(ga-ns/def-op rotor-e3ga rotor-e3ga)
(ga-ns/def-op plane rotor-e3ga)
(ga-ns/def-op circle point-pair)
(ga-ns/def-op bivector-e3ga bivector-e3ga)
(ga-ns/def-op pseudoscalar odd-versor)
(ga-ns/def-op no ni)
(ga-ns/def-op ni no)


(ga-ns/def-gp mv :double)
(ga-ns/def-sas mv)

(ga-ns/def-exp mv)
(ga-ns/def-exp point-pair)
(ga-ns/def-exp free-vector)
(ga-ns/def-exp no-ni)
(ga-ns/def-cosh mv)
(ga-ns/def-cosh flat-point)
(ga-ns/def-sinh mv)
(ga-ns/def-sinh bivector-e3ga)
(ga-ns/def-sinh free-vector)
(ga-ns/def-cos mv)
(ga-ns/def-cos bivector-e3ga)
(ga-ns/def-cos point-pair)
(ga-ns/def-cos free-vector)
(ga-ns/def-cos no-ni)
(ga-ns/def-sin mv)
(ga-ns/def-sin bivector-e3ga)
(ga-ns/def-sin point-pair)
(ga-ns/def-sin free-vector)
(ga-ns/def-sin no-ni)


(ga-ns/def-negate mv)

(ga-ns/def-negate vector-e3ga)
(ga-ns/def-negate free-vector)
(ga-ns/def-negate normalized-point)
(ga-ns/def-negate dual-sphere)
(ga-ns/def-negate line)
(ga-ns/def-negate sphere)
(ga-ns/def-negate rotor-e3ga)
(ga-ns/def-negate odd-versor)
(ga-ns/def-negate even-versor)
(ga-ns/def-negate pseudoscalar)
(ga-ns/def-negate e1)
(ga-ns/def-negate e2)
(ga-ns/def-negate no-ni)
(ga-ns/def-negate :double)

(ga-ns/def-reverse mv)
(ga-ns/def-reverse vector-e3ga)
(ga-ns/def-reverse bivector-e3ga)
(ga-ns/def-reverse normalized-point)
(ga-ns/def-reverse dual-sphere)
(ga-ns/def-reverse line)
(ga-ns/def-reverse circle)
(ga-ns/def-reverse rotor-e3ga)
(ga-ns/def-reverse odd-versor)
(ga-ns/def-reverse even-versor)
(ga-ns/def-reverse pseudoscalar)
(ga-ns/def-reverse :double)
(ga-ns/def-reverse e1)
(ga-ns/def-reverse e3)
(ga-ns/def-reverse inv-I5)

(ga-ns/def-clifford-conjugate mv)
(ga-ns/def-clifford-conjugate vector-e3ga)
(ga-ns/def-clifford-conjugate point-pair)
(ga-ns/def-clifford-conjugate normalized-point)
(ga-ns/def-clifford-conjugate free-vector)
(ga-ns/def-clifford-conjugate line)
(ga-ns/def-clifford-conjugate plane)
(ga-ns/def-clifford-conjugate rotor-e3ga)
(ga-ns/def-clifford-conjugate odd-versor)
(ga-ns/def-clifford-conjugate even-versor)
(ga-ns/def-clifford-conjugate pseudoscalar)
(ga-ns/def-clifford-conjugate :double)
(ga-ns/def-clifford-conjugate e1)
(ga-ns/def-clifford-conjugate no-ni)
(ga-ns/def-clifford-conjugate I3)

(ga-ns/def-grade-involution mv)
(ga-ns/def-grade-involution vector-e3ga)
(ga-ns/def-grade-involution bivector-e3ga)
(ga-ns/def-grade-involution normalized-point)
(ga-ns/def-grade-involution dual-sphere)
(ga-ns/def-grade-involution line)
(ga-ns/def-grade-involution plane)
(ga-ns/def-grade-involution rotor-e3ga)
(ga-ns/def-grade-involution odd-versor)
(ga-ns/def-grade-involution even-versor)
(ga-ns/def-grade-involution pseudoscalar)
(ga-ns/def-grade-involution :double)
(ga-ns/def-grade-involution no)
(ga-ns/def-grade-involution ni)
(ga-ns/def-grade-involution I3)


(ga-ns/def-unit mv)
(ga-ns/def-unit vector-e3ga)
(ga-ns/def-unit bivector-e3ga)
(ga-ns/def-unit line)
(ga-ns/def-unit sphere)
(ga-ns/def-unit rotor-e3ga)
(ga-ns/def-unit odd-versor)
(ga-ns/def-unit even-versor)
(ga-ns/def-unit normalized-point)
(ga-ns/def-unit dual-sphere)
(ga-ns/def-unit pseudoscalar)
(ga-ns/def-unit point-pair)
(ga-ns/def-unit e2)
(ga-ns/def-unit I5)

(ga-ns/def-unit mv {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit vector-e3ga {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit bivector-e3ga {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit circle {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit plane {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit rotor-e3ga {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit odd-versor {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit even-versor {:rename "unit-em" :metric euclidean})
(ga-ns/def-unit pseudoscalar {:rename "unit-em" :metric euclidean})

(ga-ns/def-add even-versor even-versor)
(ga-ns/def-subtract even-versor even-versor)

(ga-ns/def-gp point-pair point-pair  {:return-type even-versor})
(ga-ns/def-gp even-versor point-pair {:return-type even-versor})
(ga-ns/def-gp even-versor even-versor {:return-type even-versor})

(ga-ns/def-sas point-pair)
(ga-ns/def-gp even-versor :double)
(ga-ns/def-gp point-pair :double)


(ga-ns/def-inv-versor mv)
(ga-ns/def-inv-versor mv {:rename "inv-versor-em" :metric euclidean})



