(ns geometric-algebra.generator.special-multivector
  "This provides the details for the special multivector.
   The specialized-multi-vector for any typed multi-vector"
  (:require [geometric-algebra.generator.context :as ctx]
            [clojure.math.combinatorics :as comb]))


(defmacro def-smv
  "The *smv* element should contain the basis blades of the type.
  These may have constant assignments, and if the type is constant,


  (def-smv normalized-point {:constant true}
  {[:no] 1} #{[:e1] [:e2] [:e3]})
  "
  ([ctx smv-name settings] nil )
  ([ctx smv-name options settings] nil))

(defmacro def-blade
  "The specialized-multi-vector blade
  ================================

  See *def-smv for details."
  ([ctx blade-name settings] nil )
  ([ctx blade-name options settings] nil))

(defmacro def-versor
  "The specialized-multi-vector versor
  =================================

  See *def-smv for details."
  ([ctx versor-name settings] nil )
  ([ctx versor-name options settings] nil))

(defmacro def-rotor
  "The specialized-multi-vector rotor
  ================================

  See *def-smv for details."
  ([ctx rotor-name settings] nil )
  ([ctx rotor-name options settings] nil))

