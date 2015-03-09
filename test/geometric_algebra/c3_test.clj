(ns geometric-algebra.c3-test
  "Test the helper functions for conformal model."
  (:require [midje.sweet :as tt]
            [geometric-algebra.generator [space :as ga]]
            [geometric-algebra [blade :as bb]]))

(tt/facts
 "check the c3 geometric space"

 (tt/fact
  "using basis-count to get the basis size"
  (ga/basis-count [1 1 1]) => 8))
