(ns geometric-algebra.generator.constant
  "This provides the details for the constant.
  The geometric constant."
  (:require [geometric-algebra.generator.context :as ctx]
            [clojure.math.combinatorics :as comb]))



(defmacro def-constant
  "This element is used to generate a constant value in the output.
  This is useful is you want a constant value of non-constant type.
  The constant has a name, a type, and a value.
  Coordinates which are zero do not need to be specified.
  The attributes of a constant element are:
  - name. The name of the constant.
  - type. The type of the constant.
  Currently only specialized multivector constants are supported (smv).
  The constant element contains the values of the
  coordinates of the constant, and optionally a comment element.
  "
  [ctx const-name settings & options]
  `(def ~const-name nil))
