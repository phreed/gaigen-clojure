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
(defn prod
  "compute the product of two blades"
  [b1 b2]
  (reduce #(apply assoc %1 (first %2)) {} 
    (for [term1 (seq (:terms b1))
          term2 (seq (:terms b2))]
       (mapv (fn [[v k]] (vector k v)) 
             (c3bl/prod-bitmap term1 term2)))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;blade-exploration/prod</span>","value":"#'blade-exploration/prod"}
;; <=

;; @@
(defn parse [mv] (into {} (map (fn [[v k]] (vector (c3bl/bitmap<-alias k) v)) mv)))

(def a-sph {:terms (parse {1 :s, 6 :e2, 18 :ni})})
a-sph
(def b-sph {:terms (parse {1 :s, -2 :e2, 2 :ni})})
b-sph

(for [[t1 c1] (seq (:terms a-sph))] (str " t: " t1 " c: " c1))
                        
(def res(for [[term1 coef1] (seq (:terms a-sph))
              [term2 coef2] (seq (:terms b-sph))]
    (c3bl/prod-bitmap [coef1 term1] [coef2 term2])))
res
 
(prod a-sph b-sph)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>16</span>","value":"16"},{"type":"html","content":"<span class='clj-long'>-64</span>","value":"-64"}],"value":"[16 -64]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>23</span>","value":"23"},{"type":"html","content":"<span class='clj-long'>-16</span>","value":"-16"}],"value":"[23 -16]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>19</span>","value":"19"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[19 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>4</span>","value":"4"},{"type":"html","content":"<span class='clj-long'>-16</span>","value":"-16"}],"value":"[4 -16]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>27</span>","value":"27"},{"type":"html","content":"<span class='clj-long'>-4</span>","value":"-4"}],"value":"[27 -4]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>7</span>","value":"7"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[7 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>3</span>","value":"3"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[3 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>30</span>","value":"30"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[30 0]"},{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"},{"type":"html","content":"<span class='clj-long'>0</span>","value":"0"}],"value":"[0 0]"}],"value":"{16 -64, 23 -16, 19 0, 4 -16, 27 -4, 7 0, 3 0, 30 0, 0 0}"}
;; <=

;; @@

;; @@

;; @@

;; @@
