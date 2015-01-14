(ns geometric-algebra.generator.converter
  "Requisition a conversion function.
  This provides the details for type converters.
  These may be lossy converters."
  (:require [geometric-algebra.generator.context :as ctx]
            [clojure.math.combinatorics :as comb]))


(defmacro def-con
  "Requests a conversion function to the code generator back-end.
  By default each smv has convertors to and from mv.
  Specific convertors from smv to smv can be requested.
  args:
  - source
  The data-type to be converted
  - target
  The data-type to be produced

  options:
  - :arg-source-name
  Specifies the name of argument N.
  This only affects the name of the argument inside the generated function.
  Specifying this name may be superfluous, but it can improve readability,
  especially for code completion.
  - :doc
  Provides the doc-string for the object produced
  - :rename
  Changes the name of the generated function to the value of the attribute.
  For example, allows you to rename a function gp to geometricProduct.
  Sometimes this attribute is required to avoid name-clashes,
  for example if you want the define the same function for two different metrics.
  "
  ([source target] nil)
  ([source target options] nil) )

