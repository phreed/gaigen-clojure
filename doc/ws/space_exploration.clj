;; gorilla-repl.fileformat = 1

;; **
;;; # Space Exploration
;;; 
;;; The vector space is also fundamental to geometric-algebra.
;; **

;; @@
(ns blade-exploration
  (:require [gorilla-plot.core :as plot]
            [geometric-algebra
             [c3-blade :as c3bl]
             [c3-space :as c3sp]]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; The conformal vector space contains multi-vectors.
;;; Given two multivectors there product is the sum of the product of their components.
;; **

;; @@
(def a-pnt {:terms (c3sp/parse {:s 1, :e2 6, :ni 18})})
(def b-pnt {:terms (c3sp/parse {:s 1, :e2 -1, :ni 2})})
a-pnt
b-pnt
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:terms</span>","value":":terms"},{"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>8</span>","value":"8"},{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}],"value":"[8 2]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>2</span>","value":"2"},{"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"}],"value":"[2 -1]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-long'>1</span>","value":"1"}],"value":"[0 1]"}],"value":"{8 2, 2 -1, 0 1}"}],"value":"[:terms {8 2, 2 -1, 0 1}]"}],"value":"{:terms {8 2, 2 -1, 0 1}}"}
;; <=

;; @@

(c3sp/prod a-pnt b-pnt)

(-> (c3sp/prod a-pnt b-pnt)
     :terms
     c3sp/to-alias)

 
(c3bl/alias->bitmap :e2nio)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>26</span>","value":"26"}
;; <=

;; @@

;; @@
