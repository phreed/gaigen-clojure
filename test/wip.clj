(ns geometric-algebra.core-test
  (:require [clojure.test :refer :all]
            [geometric-algebra.core :refer :all]
            [clojure.math.combinatorics :as comb]))


(ga/def-basis-vector-names mybasis [:no :e1 :e2 :e3 :ni] {:default true} )


(comb/combinations default-basis-names 2)



(def-constant vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})
