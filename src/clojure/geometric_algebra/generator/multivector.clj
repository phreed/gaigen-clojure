(ns geometric-algebra.generator.multivector
  "The generic multivector"
  (:require [geometric-algebra.generator.context :as ctx]
            [clojure.math.combinatorics :as combo]))


(defn derive-terms [basis]
  (combo/subsets basis))

(defmacro def-mv
  "The multi-vector specifies the properties of the general multivector.
  It is the foundational element which describes functions used
  for more specialized elements.

  These may have constant assignments
  then all basis blades must have a constant assignment.
  An example of a multivector definition is

  (def-mv normalized-point {:constant true}
  {[:no] 1} #{[:e1] [:e2] [:e3]})

  The attributes of a smv element are:
  - name.
  The name of the specialized multivector type, for example vector.
  - const (optional).
  Can either be true or false. When true, the type is
  a constant type with no variable coordinates.
  In that case, all basis blades must have a constant value assigned to it.
  If the const attribute is not specified it is assumed to be false.
  A constant with the name will be generated and the actual
  name of the type will have an t suffix.
  See *special-multivector.clj* for details.
  "
  [ctx name settings & options]
  (let [basis (:basis settings)]
   `(defrecord ~name [])))


;; given a basis {o 1 2 3 n} the following record is formed.
(defrecord MV [s
               eo e1 e2 e3 en
               eo1 eo2 eo3 eon e12 e13 e1n e23 e2n e3n
               eo12 eo13 eo1n eo23 eo2n eo3n e123 e12n e13n e23n
               eo123 eo12n eo13n eo23n e123n
               eo123n])



