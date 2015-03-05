#_(ns wip-test
  "Test miscellaneous things"
  (:require [midje.sweet :as tt]
            [clojure.math.combinatorics :as comb]
            [geometric-algebra.generator
             [space :as ga]
             [blade :as bb]]))

#_(tt/fact
 "check the multivector operations"
 (1 => 1))

; (ga/def-basis-vector-names mybasis [:no :e1 :e2 :e3 :ni] {:default true} )


(; comb/combinations default-basis-names 2)



; (def-constant vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})
