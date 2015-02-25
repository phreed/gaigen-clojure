;; gorilla-repl.fileformat = 1

;; **
;;; # Basis Blade Exploration
;;; 
;;; The basis blade is the fundamental component of geometric-algebra.
;; **

;; @@
(ns blade-exploration
  (:require [gorilla-plot.core :as plot]
            [geometric-algebra.generator.blade :as bb]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(bb/sign 2r10101 2r01010)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>-1</span>","value":"-1"}
;; <=

;; @@

;; @@
