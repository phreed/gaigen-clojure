(ns geometric-algebra.generator.outer-morphism
  "The outer-morphism
  ====================

  This provides the details for the generic multivector."
  (:require [geometric-algebra.generator.context :as ctx]
            [clojure.math.combinatorics :as comb]))



(defmacro def-om
  "Specifies the general outermorphism matrix representation type.
  This allows for efficient application of linear
  transformations using the applyOM function.
  The outermorphism has a domain and a range, both of which may be specified,
  but they can also be left to the defaults.
  An example of an outermorphism with default coordinate order is:
  (def-om om)
  A 3-D example of an outermorphism with a custom domain and range is:
  (def-om om
  :domain #{[:scalar] [:e1] [:e2] [:e3]
  [:e1 :e2] [:e2 :e3] [:e3 :e1]
  [:e1 :e2 :e3]}
  :range #{[:scalar] [:e1] [:e2] [:e3]
  [:e1 :e2] [:e2 :e3] [:e3 :e1]
  [:e1 :e2 :e3]})
  In this example, it was redundant to specify the range
  since it is identical to the domain.
  Leaving the range element out would have the same effect.
  Note that all basis blades must be present in a
  general outermorphism’s range and domain.
  The attributes of a om element are:
  - name.
  The name of the outermorphism type, for example om.
  If :domain or :range are specified the default values are used.
  When only :range is not specified it is identical to :domain.
  "
  [ctx om-name & options] nil)

(defmacro def-som
  "The specialized outer-morphism
  ================================

  A som element specifies a specialized outermorphism.
  It is pretty much that same as a general outermorphism
  except it does not need to have all basis blades in its domain and range.
  "
  [ctx som-name settings & options] nil)
