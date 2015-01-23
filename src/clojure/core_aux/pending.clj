(ns core-aux.pending
  "Functions that I expect to eventually find there
  way into clojure core but for some reason are
  not there yet.")


(defn bit-count
  "Faster bit count than that proposed by Kernigan.
  The Hamming-weight."
  [a]
  (Integer/bitCount a))
