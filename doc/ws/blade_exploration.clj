;; gorilla-repl.fileformat = 1

;; **
;;; # Basis Blade Exploration
;;; 
;;; The basis blade is the fundamental component of geometric-algebra.
;; **

;; @@
(ns blade-exploration
  (:require [gorilla-plot.core :as plot]
            [geometric-algebra 
             [blade :as bl]
             [c3-blade :as c3bl]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; The general blade has methods like ...
;; **

;; **
;;; ... grade which determines the dimension of the blade.
;; **

;; @@
(bl/grade 2r10101)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>3</span>","value":"3"}
;; <=

;; **
;;; ... sign, which determines the sign of the product.
;; **

;; @@
(bl/sign 2r10101 2r01010)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"}
;; <=

;; **
;;; The product of two blades requires the metric. 
;;; In the following case the metric is for the conformal model.
;; **

;; @@
(c3bl/prod-bitmap [2 2r10101] [1 2r01010])
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-2</span>","value":"-2"},{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"}],"value":"[-2 7]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-2</span>","value":"-2"},{"type":"html","content":"<span class='clj-long'>31</span>","value":"31"}],"value":"[-2 31]"}],"value":"[[-2 7] [-2 31]]"}
;; <=

;; **
;;; The result is a multi-vector consisting of two parts.
;;; The first part is -:e123n-- and the second -:e123nio.
;;; 
;; **

;; @@
(c3bl/prod-label :e1-3n-o :e-2-ni-)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"},{"type":"html","content":"<span class='clj-keyword'>:e123n--</span>","value":":e123n--"}],"value":"[-1 :e123n--]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"},{"type":"html","content":"<span class='clj-keyword'>:e123nio</span>","value":":e123nio"}],"value":"[-1 :e123nio]"}],"value":"[[-1 :e123n--] [-1 :e123nio]]"}
;; <=

;; **
;;; Alias may also be used.
;;; This is done by expanding the alias into its canonical form.
;; **

;; @@
(c3bl/expand-alias :e2nio)

(c3bl/prod-label :e13no :e2ni)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"},{"type":"html","content":"<span class='clj-keyword'>:e123n--</span>","value":":e123n--"}],"value":"[-1 :e123n--]"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"},{"type":"html","content":"<span class='clj-keyword'>:e123nio</span>","value":":e123nio"}],"value":"[-1 :e123nio]"}],"value":"[[-1 :e123n--] [-1 :e123nio]]"}
;; <=

;; **
;;; Here we can see the first few keys of the product-label-lookup table.
;; **

;; @@
(keys (take 10 c3bl/prod-label-lookup))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>([:e1--n-o :e-23n--] [:e1--ni- :e-23n--] [:e-23nio :e---ni-] [:e-23n-- :e1--n--] [:e1-3ni- :e--3n-o] [:e-23ni- :e1--ni-] [:e-2-n-o :e-23nio] [:e--3nio :e1--n-o] [:e--3ni- :e---n-o] [:e---ni- :e--3n--])</span>","value":"([:e1--n-o :e-23n--] [:e1--ni- :e-23n--] [:e-23nio :e---ni-] [:e-23n-- :e1--n--] [:e1-3ni- :e--3n-o] [:e-23ni- :e1--ni-] [:e-2-n-o :e-23nio] [:e--3nio :e1--n-o] [:e--3ni- :e---n-o] [:e---ni- :e--3n--])"}
;; <=

;; @@

;; @@

;; @@

;; @@

;; @@

;; @@
