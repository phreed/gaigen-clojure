(ns gafcs.c3ga )

(def-g25spec
 :license "gpl"
 :language "clojure"
 :namespace "c3ga"
 :coordStorage "variables"
 :defaultOperatorBindings true
 :dimension 5
 :reportUsage false
 :gmvCode "expand"
 :parser "builtin"
 :testSuite true
 :copyright "Derived from Gaigen 2.5 Test Suite")



(def-inline
 :constructors true
 :set true
 :assign true
 :operators true
 :functions true)


(def-float-type :type "double")

;; The basisVectorNames definition lists the names of basis vectors of the algebra.
;; The number of basis vectors must match the dimension N of the space.

(def-basis-vector-names [:no :e1 :e2 :e3 :ni] )

;; A metric element specifies the inner product
;; between a pair of basis vectors.
;; By default, all inner product between basis vectors are assumed to be 0.
;; By using metric elements, one can set the inner product to different values.
;; Inside a single algebra, different metrics can be used,
;;   e.g. a conformal one and a Euclidean one.
;; Having a Euclidean metric is useful,
;;   e.g., for blade factorization algorithms.
;; This line says that the inner product between basis vectors :no and :ni is -1.
;; Inside function elements, a non-default metric name may be specified
;; by using the :metric <name> attribute, e.g., :metric conformal.
;;
;; - :round.
;; Due to floating point round-off errors in eigenvalue computation, values
;; or coordinates that should be (e.g.) 1.0 may become (e.g., 1 + 1e −16).
;; This makes the generated code less efficient, is annoying to read and propagates the round-off errors.
;; For that reason, there is the option to round coordinates after a metric product.
;; The default is to round, but when the final metric is diagonal,
;; it is forced to no rounding because there is not need to use it in that case.
;; When rounding is enabled, coordinates which are
;; very close to an integer value are rounded to that value. The threshold
;; for being "very close" is 1e-14.


(def-metric conformal {:default true :round false}
  {[:no :ni] -1
   [:e1 :e1] 1 [:e2 :e2] 1 [:e3 :e3] 1})

(def-metric euclidean {:round false}
  {[:no :no] 1 [:e1 :e1] 1 [:e2 :e2] 1 [. :e3 :e3] 1 [. :ni :ni] 1})


;; The multi-vector specifies the properties of the general multivector.
;; It is one of the most involved elements.
(def-mv mv :compress "byGrade" :coordinateOrder "default" :memAlloc="full")



;; The specialized-multi-vector
;; The smv element should contain the basis blades of the type.
;; These may have constant assignments, and if the type is constant,
;; then all basis blades must have a constant assignment.
;; An example of a specialized multivector definition is
;;     (def-smv normalized-point" :type "blade"}
;;        {[:no] 1} #{[:e1] [:e2] [:e3]})
;; The attributes of a smv element are:
;; - name.
;;     The name of the specialized multivector type, for example vector.
;; - const (optional).
;;     Can either be true or false. When true, the type is
;;     a constant type with no variable coordinates.
;;     In that case, all basis blades must have a constant value assigned to it.
;;     If the const attribute is not specified it is assumed to be false.
;;     A constant with the name will be generated and the actual
;;     name of the type will have an t suffix.
;; - type.
;;     The type attribute specified whether instances of
;;     the smv class will contain only blades (:type "blade"),
;;     rotors (:type "rotor"), versors (:type "versor") or any type of
;;     multivector (:type "multivector") value.
;;     This may be used for optimizations and for sanity checks by the code generator.

(def-smv no {:const true :type "blade"} {[:no] 1})
(def-smv e1 {:const true :type "blade"} {[:e1] 1})
(def-smv e2 {:const true :type "blade"} {[:e2] 1})
(def-smv e3 {:const true :type "blade"} {[:e3] 1})
(def-smv ni {:const true :type "blade"} {[:ni] 1})

(def-smv no-ni {:const true :type "blade"} {[:no :ni] 1})
(def-smv I3 {:const true :type "blade"} {[:e1 :e2 :e3] 1})
(def-smv I5 {:const true :type "blade"} {[:no :e1 :e2 :e3 :ni] 1})
(def-smv I5-i {:const true :type "blade"} {[:no :e1 :e2 :e3 :ni] -1})

(def-smv vector-e3ga {:type "blade"} #{[:e1] [:e2] [:e3]})
(def-smv bivector-e3ga {:type "blade"} #{[:e1 :e2] [:e2 :e3] [:e3 :e1]})
(def-smv rotor-e3ga {:type "versor"} #{:scalar [:e1 :e2] [:e2 :e3] [:e3 :e1]})

(def-smv normalized-point {:type "blade"} {[:no] 1} #{[:e1] [:e2] [:e3] [:ni]})
(def-smv dual-sphere {:type "blade"} #{[:no] [:e1] [:e2] [:e3] [:ni]})
(def-smv dual-plane {:type "blade"} #{[:e1] [:e2] [:e3] [:ni]})
(def-smv free-vector {:type "blade"} #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})
(def-smv free-bivector {:type "blade"} #{[:e1 :e2 :ni] [:e2 :e3 :ni] [:e3 :e1 :ni]})

(def-smv flat-point {:type "blade"}
     #{[e1 :ni] [e2 :ni] [:e3 :ni] [:no :ni]})

(def-smv point-pair {:type "blade"}
     #{[:no :e1] [:no :e2] [:no :e3]
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:no :ni]})

(def-smv line {:type "blade"}
     #{[:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni]
      [:e1 :no :ni] [:e2 :no :ni] [:e3 :no :ni]})

(def-smv circle {:type "blade"}
     #{[:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
      [:e1 :e2 :e3]
      [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
      [:e1 :e2 :ni] [:e1 :e3 :ni] [:e2 :e3 :ni] })

(def-smv sphere {:type "blade"}
     #{[:e1 :e2 :e3 :ni] [:no :e1 :e2 :e3]
      [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(def-smv plane {:type "blade"}
     #{[:e1 :e2 :e3 :ni] [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]})

(def-smv pseudoscalar {:type "blade"} #{[:no :e1 :e2 :e3 :ni]})

(def-smv normalized-translator {:type "versor"}
     {:scalar 1.0} #{[:e1 :ni] [:e2 :ni] [:e3 :ni]})

(def-smv translator {:type "versor"} #{:scalar [:e1 :ni] [:e2 :ni] [:e3 :ni]})

(def-smv RBM {:type "versor"}
     #{:scalar
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:e1 :e2 :e3 :ni]})

(def-smv even-versor {:type "versor"}
     #{:scalar
      [:no :e1] [:no :e2] [:no :e3]
      [:e1 :e2] [:e2 :e3] [:e3 :e1]
      [:e1 :ni] [:e2 :ni] [:e3 :ni]
      [:no :ni]
      [:no :e2 :e3 :ni] [:no :e1 :e3 :ni] [:no :e1 :e2 :ni]
      [:no :e1 :e2 :e3]
      [:e1 :e2 :e3 :ni]})

(def-smv odd-versor {:type "versor"}
     {[:no] [:e1] [:e2] [:e3] [:ni]
      [:no :e1 :e2] [:no :e1 :e3] [:no :e2 :e3]
      [:e1 :e2 :e3]
      [:no :e1 :ni] [:no :e2 :ni] [:no :e3 :ni]
      [:e1 :e2 :ni]
      [:e1 :e3 :ni] [:e2 :e3 :ni]
      [:no :e1 :e2 :e3 :ni] })

;; This element is used to generate a constant value in the output.
;; This is useful is you want a constant value of non-constant type.
;; The constant has a name, a type, and a value.
;; Coordinates which are zero do not need to be specified.
;; The attributes of a constant element are:
;; - name. The name of the constant.
;; - type. The type of the constant.
;;         Currently only specialized multivector constants are supported (smv).
;; The constant element contains the values of the
;; coordinates of the constant, and optionally a comment element.

(def-constant vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})
(def-constant vector-e2 {:type "vector-e3ga"} {[:e1] 0 [:e2] 1 [:e3] 0})
(def-constant vector-e3 {:type "vector-e3ga"} {[:e1] 0 [:e2] 0 [:e3] 1})

(def-constant point-at-origin
  "The point at the origin . . ."
  {:type "normalized-point"} {[:no] 1})

(def-constant point-at-infinity
  "The point at infinity . . ."
  {:type "dual-sphere"} {[:ni] 1})

;; Specifies the general outermorphism matrix representation type.
;; This allows for efficient application of linear transformations using the applyOM function.
;; The outermorphism has a domain and a range, both of which may be specified,
;; but they can also be left to the defaults.
;; An example of an outermorphism with default coordinate order is:
;;   (def-om om)
;; A 3-D example of an outermorphism with a custom domain and range is:
;;   (def-om om
;;     :domain #{[:scalar] [:e1] [:e2] [:e3]
;;               [:e1 :e2] [:e2 :e3] [:e3 :e1]
;;               [:e1 :e2 :e3]}
;;     :range #{[:scalar] [:e1] [:e2] [:e3]
;;               [:e1 :e2] [:e2 :e3] [:e3 :e1]
;;               [:e1 :e2 :e3]})
;; In this example, it was redundant to specify the range
;; since it is identical to the domain.
;; Leaving the range element out would have the same effect.
;; Note that all basis blades must be present in an general outermorphism’s range and domain.
;; The attributes of a om element are:
;; - name.
;;     The name of the outermorphism type, for example om.
;; If :domain or :range are specified the default values are used.
;; When only :range is not specified it is identical to :domain.

(def-om om)

;; A som element specifies a specialized outermorphism.
;; It is pretty much that same as a general outermorphism
;; except it does not need to have all basis blades in its domain and range.

(def-som grade1OM_E3GA :domain #{[:e1] [:e2] [:e3]})
(def-som grade1OM :domain #{[:no] [:e1] [:e2] [:e3] [:ni]})

(def-som flat-pointOM
      :domain #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]}
      :range #{[:e1 :ni] [:e2 :ni] [:e3 :ni] [:no :ni]} )

;; def-fun requests a multi-function to the code generator back-end.
;; def-con requests a conversion function to the code generator back-end.
;;
;; to implement a specific multi-function for specific arguments.
;; The following multi-functions are supported:
;; - add  : add two multi-vectors
;; - subtract  : subtract two multi-vectors
;; - apply-om  : apply an outermorphism to a mv
;; - apply-versor  : apply (sandwich product) a versor to a mv
;;        This can take a custom :metric in {:euclidean ?}
;; - apply-unit-versor : same as apply-versor but with the unit assumption (careful).
;; - apply-versor-wi : another efficiency consideration
;; - cga-point : construct a conformal point
;; - cga-point-distance : a scalar representing the distance between centers of mv
;; - random-point : constract a random conformal point
;; - div : divide a mv by a scalar
;; - dual : compute the dual of a mv
;; - undual : compute the undual of a mv
;; - equals : check for equality with a difference threshold
;; - extract-grade : extracts a vector of the grade parts from a mv
;; - extract-grade-x : extracts a vector of a specified grade part
;; - grade-bitmap : extracts the grade bitmap for a mv
;; - gp : computes the geometric product of a pair of mv
;; - hp : computes the hadamard product of two mv
;; - igp : computes the inverse geometric product of two mv
;; - ihp : computes the inverse hadamard product of two mv
;; - incr : increment the mv
;; - decr : decrement the mv
;; - hip : compute the Hestenes inner product of an mv pair
;; - mhip : compute the modified Hestenes inner product of an mv pair
;; - lc : compute the left contraction inner product of an mv pair
;; - rc : compute the right contraction inner product of an mv pair
;; - sp : compute the scalar product of two mv
;; - log : compute the logarithm of a rotor. The rotor type is specified
;; - exp : compute the exponential of a mv
;; - norm : compute the norm (magnitude) of a mv
;; - norm2 : compute the squared norm of a mv
;; - op : compute the outer produce of an mv pair
;; - random-blade : create a random blade
;; - random-versor :
;; - random-scalar :
;; - random-smv : takes the type of mv to generate
;; - sas : scale and add scalar, scals a mv by a factor then adds a scalar.
;; - sin : compute the scalar sine of a mv
;; - cos : compute the scalar cosine of a mv
;; - sinh : compute the scalar hyperbolic sine of a mv
;; - cosh : compute the scalar hyperbolic cosine of a mv
;; - negate : negate a mv
;; - reverse : reverse a mv
;; - clifford-conjugate : compute the Clifford conjugate of a mv
;; - grade-involution : compute the grade involution of a mv
;; - unit : computes the unit of a mv, suitable for use by apply-unit-versor
;; - versor-inverse : compute the versor inverse of a mv
;; - zero : check if a mv is zero, via a threshold
;;
;;
;; By default each smv has convertors to and from mv.
;; Specific convertors from smv to smv can be requested.
;;
;; The attributes are:
;; – name.
;;   The name of the function, as it is known to the code generator.
;; This name is also the name of the generated function unless
;; an :output-name attribute is specified.
;; To generate a converter (‘underscore constructor’), the name of the
;; function should be an underscore plus the name of the destination
;; type, e.g., vector-e3ga.
;; This first (and only) argument should be the source type.
;; - output-name. Optional.
;;      Changes the name of the generated function to the value of the attribute.
;;      For example, allows you to rename a function gp to geometricProduct.
;;      Sometimes this attribute is required to avoid name-clashes,
;;      for example if you want the define the same function for two different metrics.
;; - return-type. Optional.
;;      By default, the code generator will determine the return type
;;      of the functions it generates, but it is possible to override
;;      this default by setting it explicitly.
;;      The return type should be the name of a specialized multivector.
;;      However, the return type may also be scalar or any of the floating
;;      point typenames used in the algebra.
;;      If the return type is scalar, then a float will be returned,
;;      automatically adapted to the floating point type of the function.
;; - argN.
;;      Specifies the type of argument N. If no argN attribute is given,
;;      the code generator will fill in the default (general) types automatically.
;;      Otherwise, the correct number of argN attributes should be
;;      specified for the function (running from 1 up to the number of arguments of the function).
;;      Not all combinations of argument types are possible.
;;      For example, it is not possible to mix general and specialized multivectors.
;;      It is possible to mix floats and general multivectors though.
;; - arg-nameN.
;;      Specifies the name of argument N.
;;      This only affects the name of the argument inside the generated function.
;;      Specifying this name may be superfluous, but it can improve readability,
;;      especially for code completion.
;; - optionX.
;;      Specifies an option X.
;;      For example, the exp functions can generate more efficient
;;      code when it knows what the sign of the square of the argument is.
;;      In that case, one may use for example :option-square 1.0.
;; - float-type.
;;      Multiple float-type attributes may be present in a single function element.
;;      By default, the code generator will generate code for all floating point
;;      types of the specification, but using the floatType attribute(s)
;;      this may be limited to only the set of listed floating point types.
;; - metric.
;;      The optional metric attribute specifies the usage of a nondefault metric (case insensitive).
;;      By default, the metric "default" is used.
;;      By using this attribute a different metric may be used for the function, e.g., metric="euclidean".
;; - comment.
;;      Use the this optional attribute to add any extra user comments to the function documentation.
;;      For example, one could use the comment to explain what a certain function is used for.


(def-fun random-double :output-name "genrand" :optionGen "libc")

(def-con _vector-e3ga
  "Extras vector part of point"
  :arg1 "normalized-point" :argName1 "P")

(def-con _vector-e3ga
  "Extras vector part of dual sphere"
  :arg1 "dual-sphere" :argName1 "S" )

(def-con _bivector-e3ga
  "Extracts Euclidean bivector part of Euclidean rotor"
  :arg1 "rotor-e3ga" :float-type "double" )

(def-con _dual-sphere
  "Converts a normalized point to a dual sphere"
  :output-name "pointToSphere" :arg1 "normalized-point"
  :argName1="P" :float-type "double")

(def-con _even-versor :arg1 "point-pair")


(def-fun cga-point :arg1 "vector-e3ga" :float-type "double")
(def-fun cga-point :arg1 "double" :arg2 "double" :arg3 "double"
           :option-origin "no" :option-infinity "ni" :float-type "double")

(def-fun random-point :float-type "double")

(def-fun cga-point-distance :arg1 "normalized-point" :arg2 "normalized-point" :float-type "double")
(def-fun cga-point-distance :arg1 "dual-sphere" :arg2 "dual-sphere" :float-type "double")

(def-fun add :arg1 "mv" :arg2 "mv")
(def-fun add :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun add :arg1 "vector-e3ga" :arg2 "normalized-point")
(def-fun add :arg1 "vector-e3ga" :arg2 "dual-sphere")
(def-fun add :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun add :arg1 "plane" :arg2 "plane")
(def-fun add :arg1 "line" :arg2 "circle")
(def-fun add :arg1 "circle" :arg2 "vector-e3ga")
(def-fun add :arg1 "e1" :arg2 "e2")
(def-fun add :arg1 "I5" :arg2 "circle")


(def-fun subtract :arg1 "mv" :arg2 "mv")
(def-fun subtract :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun subtract :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun subtract :arg1 "odd-versor" :arg2 "odd-versor")
(def-fun subtract :arg1 "line" :arg2 "vector-e3ga")
(def-fun subtract :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun subtract :arg1 "rotor-e3ga" :arg2 "no-ni")
(def-fun subtract :arg1 "I5" :arg2 "circle")

(def-fun apply-om :arg1 "om" :arg2 "mv")
(def-fun apply-om :arg1 "om" :arg2 "normalized-point")
(def-fun apply-om :arg1 "om" :arg2 "circle")
(def-fun apply-om :arg1 "om" :arg2 "sphere")

(def-fun apply-om :arg1 "grade1OM_E3GA" :arg2 "vector-e3ga")
(def-fun apply-om :arg1 "grade1OM_E3GA" :arg2 "e1")
(def-fun apply-om :arg1 "grade1OM_E3GA" :arg2 "no")
(def-fun apply-om :arg1 "grade1OM_E3GA" :arg2 "normalized-point")

(def-fun apply-om :arg1 "flat-pointOM" :arg2 "flat-point")
(def-fun apply-om :arg1 "flat-pointOM" :arg2 "no-ni")

(def-fun apply-versor :arg1 "mv" :arg2 "mv")
(def-fun apply-unit-versor :arg1 "mv" :arg2 "mv")
(def-fun apply-versor-wi :arg1 "mv" :arg2 "mv" :arg3 "mv")

(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "vector-e3ga")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "normalized-point")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "dual-sphere")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "bivector-e3ga")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "line")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "plane")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "no-ni")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "no")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "ni")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun apply-unit-versor :arg1 "rotor-e3ga" :arg2 "I5")

(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "vector-e3ga")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "normalized-point")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "dual-sphere")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "bivector-e3ga")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "line")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "sphere")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "ni")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "rotor-e3ga")
(def-fun apply-unit-versor :arg1 "even-versor" :arg2 "I5-i")

(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "vector-e3ga")
(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "normalized-point")
(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "dual-sphere")
(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "bivector-e3ga")
(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "line")
(def-fun apply-unit-versor :arg1 "odd-versor" :arg2 "plane")

(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "vector-e3ga")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "normalized-point"  :metric "euclidean")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "dual-sphere")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "bivector-e3ga")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "line")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "plane")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "no-ni")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "no")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "ni")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun apply-versor :arg1 "rotor-e3ga" :arg2 "I5")

(def-fun apply-versor :arg1 "even-versor" :arg2 "vector-e3ga")
(def-fun apply-versor :arg1 "even-versor" :arg2 "normalized-point")
(def-fun apply-versor :arg1 "even-versor" :arg2 "dual-sphere")
(def-fun apply-versor :arg1 "even-versor" :arg2 "bivector-e3ga")
(def-fun apply-versor :arg1 "even-versor" :arg2 "line")
(def-fun apply-versor :arg1 "even-versor" :arg2 "sphere")
(def-fun apply-versor :arg1 "even-versor" :arg2 "ni")
(def-fun apply-versor :arg1 "even-versor" :arg2 "rotor-e3ga")
(def-fun apply-versor :arg1 "even-versor" :arg2 "I5-i")

(def-fun apply-versor :arg1 "odd-versor" :arg2 "vector-e3ga"  :metric "euclidean")
(def-fun apply-versor :arg1 "odd-versor" :arg2 "normalized-point")
(def-fun apply-versor :arg1 "odd-versor" :arg2 "dual-sphere")
(def-fun apply-versor :arg1 "odd-versor" :arg2 "bivector-e3ga")
(def-fun apply-versor :arg1 "odd-versor" :arg2 "line")
(def-fun apply-versor :arg1 "odd-versor" :arg2 "plane")

(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "vector-e3ga" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "normalized-point" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "dual-sphere" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "bivector-e3ga" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "line" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "plane" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "e1" :arg3 "rotor-e3ga")
(def-fun apply-versor-wi :arg1 "rotor-e3ga" :arg2 "I5" :arg3 "rotor-e3ga")

(def-fun div :arg1 "mv" :arg2 "double" :float-type "double")

(def-fun div :arg1 "vector-e3ga" :arg2 "double" :float-type "double")
(def-fun div :arg1 "normalized-point" :arg2 "double" :float-type "double")
(def-fun div :arg1 "bivector-e3ga" :arg2 "double" :float-type "double")
(def-fun div :arg1 "line" :arg2 "double" :float-type "double")
(def-fun div :arg1 "plane" :arg2 "double" :float-type "double")
(def-fun div :arg1 "sphere" :arg2 "double" :float-type "double")
(def-fun div :arg1 "I5" :arg2 "double" :float-type "double")
(def-fun div :arg1 "even-versor" :arg2 "double" :float-type "double")
(def-fun div :arg1 "odd-versor" :arg2 "double" :float-type "double")
(def-fun div :arg1 "I3" :arg2 "double" :float-type "double")
(def-fun div :arg1 "no-ni" :arg2 "double" :float-type "double")

(def-fun dual :arg1 "mv")
(def-fun undual :arg1 "mv")

(def-fun dual :arg1 "double" :float-type "double")
(def-fun undual :arg1 "double" :float-type "double")
(def-fun dual :arg1 "vector-e3ga")
(def-fun undual :arg1 "vector-e3ga")
(def-fun dual :arg1 "normalized-point")
(def-fun undual :arg1 "normalized-point")
(def-fun dual :arg1 "bivector-e3ga")
(def-fun undual :arg1 "bivector-e3ga")
(def-fun dual :arg1 "rotor-e3ga")
(def-fun undual :arg1 "rotor-e3ga")
(def-fun dual :arg1 "odd-versor")
(def-fun undual :arg1 "odd-versor")
(def-fun dual :arg1 "even-versor")
(def-fun undual :arg1 "even-versor")
(def-fun dual :arg1 "point-pair")
(def-fun undual :arg1 "point-pair")
(def-fun dual :arg1 "plane")
(def-fun undual :arg1 "plane")
(def-fun dual :arg1 "circle" :metric "euclidean")
(def-fun undual :arg1 "circle" :metric "euclidean")
(def-fun dual :arg1 "e1")
(def-fun undual :arg1 "I3")
(def-fun dual :arg1 "I5")
(def-fun undual :arg1 "I5-i")


(def-fun equals :arg1 "mv" :arg2 "mv" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "vector-e3ga" :arg2 "vector-e3ga" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "bivector-e3ga" :arg2 "bivector-e3ga" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "rotor-e3ga" :arg2 "rotor-e3ga" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "bivector-e3ga" :arg2 "rotor-e3ga" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "rotor-e3ga" :arg2 "bivector-e3ga" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "rotor-e3ga" :arg2 "line" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "even-versor" :arg2 "plane" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "circle" :arg2 "line" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "plane" :arg2 "plane" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "normalized-point" :arg2 "normalized-point" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "e1" :arg2 "e1" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "I5" :arg2 "I5-i" :arg3 "double" :float-type "double")
(def-fun equals :arg1 "no-ni" :arg2 "point-pair" :arg3 "double" :float-type "double")

(def-fun extract-grade :level :arg1 "mv")
(def-fun extract-grade :level 2 :arg1 "mv")
(def-fun extract-grade :level 0 :arg1 "rotor-e3ga")
(def-fun extract-grade :level 2 :arg1 "rotor-e3ga")
(def-fun extract-grade :level 1 :arg1 "odd-versor")
(def-fun extract-grade :level 3 :arg1 "odd-versor")
(def-fun extract-grade :level 4 :arg1 "odd-versor")
(def-fun extract-grade :level 0 :arg1 "even-versor")
(def-fun extract-grade :level 1 :arg1 "even-versor")
(def-fun extract-grade :level 4 :arg1 "even-versor")
(def-fun extract-grade :level 0 :arg1 "normalized-point")
(def-fun extract-grade :level 1 :arg1 "normalized-point")
(def-fun extract-grade :level 2 :arg1 "normalized-point")
(def-fun extract-grade :level 3 :arg1 "dual-sphere")
(def-fun extract-grade :level 4 :arg1 "normalized-point")
(def-fun extract-grade :level 5 :arg1 "normalized-point")

(def-fun extract-grade :level 0 :arg1 "e1")
(def-fun extract-grade :level 1 :arg1 "e2")
(def-fun extract-grade :level 2 :arg1 "e3")
(def-fun extract-grade :level 3 :arg1 "no")
(def-fun extract-grade :level 4 :arg1 "e1")

(def-fun extract-grade :level 0 :arg1 "I5")
(def-fun extract-grade :level 1 :arg1 "I5-i")
(def-fun extract-grade :level 2 :arg1 "I5")
(def-fun extract-grade :level 3 :arg1 "I5-i")
(def-fun extract-grade :level 4 :arg1 "I5")

(def-fun gp :arg1 "mv" :arg2 "mv")

(def-fun gp :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun gp :arg1 "rotor-e3ga" :arg2 "vector-e3ga")
(def-fun gp :arg1 "circle" :arg2 "line")
(def-fun gp :arg1 "rotor-e3ga" :arg2 "line")
(def-fun gp :arg1 "vector-e3ga" :arg2 "rotor-e3ga")
(def-fun gp :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun gp :arg1 "plane" :arg2 "rotor-e3ga")
(def-fun gp :arg1 "plane" :arg2 "odd-versor")
(def-fun gp :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun gp :arg1 "even-versor" :arg2 "dual-sphere")
(def-fun gp :arg1 "normalized-point" :arg2 "normalized-point")
(def-fun gp :arg1 "flat-point" :arg2 "odd-versor")
(def-fun gp :arg1 "e1" :arg2 "e2")
(def-fun gp :arg1 "e3" :arg2 "ni")
(def-fun gp :arg1 "no" :arg2 "ni")
(def-fun gp :arg1 "e3" :arg2 "I5-i")


(def-fun gp :output-name "gp_em" :arg1 "mv" :arg2 "mv" :metric "euclidean")

(def-fun gp :output-name "gp_em" :arg1 "vector-e3ga" :arg2 "vector-e3ga" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "rotor-e3ga" :arg2 "vector-e3ga" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "vector-e3ga" :arg2 "line" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "rotor-e3ga" :arg2 "circle" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "vector-e3ga" :arg2 "rotor-e3ga" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "rotor-e3ga" :arg2 "rotor-e3ga" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "plane" :arg2 "rotor-e3ga" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "sphere" :arg2 "circle" :metric "euclidean")
(def-fun gp :output-name "gp_em" :arg1 "bivector-e3ga" :arg2 "bivector-e3ga" :metric "euclidean")

(def-fun grade-bitmap :arg1 "mv" :arg2 "double" :float-type "double")

(def-fun grade-bitmap :arg1 "rotor-e3ga" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "odd-versor" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "even-versor" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "vector-e3ga" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "bivector-e3ga" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "pseudoscalar" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "circle" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "point-pair" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "e1" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "e2" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "no-ni" :arg2 "double" :float-type "double")
(def-fun grade-bitmap :arg1 "I5-i" :arg2 "double" :float-type "double")


(def-fun hp :arg1 "mv" :arg2 "mv")

(def-fun hp :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun hp :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun hp :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun hp :arg1 "odd-versor" :arg2 "odd-versor")
(def-fun hp :arg1 "vector-e3ga" :arg2 "odd-versor")
(def-fun hp :arg1 "even-versor" :arg2 "even-versor")
(def-fun hp :arg1 "bivector-e3ga" :arg2 "rotor-e3ga")
(def-fun hp :arg1 "circle" :arg2 "even-versor")
(def-fun hp :arg1 "pseudoscalar" :arg2 "odd-versor")
(def-fun hp :arg1 "rotor-e3ga" :arg2 "bivector-e3ga")
(def-fun hp :arg1 "even-versor" :arg2 "bivector-e3ga")
(def-fun hp :arg1 "sphere" :arg2 "plane")
(def-fun hp :arg1 "normalized-point" :arg2 "e1")
(def-fun hp :arg1 "vector-e3ga" :arg2 "e1")
(def-fun hp :arg1 "I5" :arg2 "pseudoscalar")
(def-fun hp :arg1 "pseudoscalar" :arg2 "I5-i")

(def-fun ihp :arg1 "mv" :arg2 "mv")
(def-fun ihp :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun ihp :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun ihp :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun ihp :arg1 "circle" :arg2 "odd-versor")
(def-fun ihp :arg1 "vector-e3ga" :arg2 "odd-versor")
(def-fun ihp :arg1 "even-versor" :arg2 "even-versor")
(def-fun ihp :arg1 "bivector-e3ga" :arg2 "rotor-e3ga")
(def-fun ihp :arg1 "plane" :arg2 "even-versor")
(def-fun ihp :arg1 "pseudoscalar" :arg2 "odd-versor")
(def-fun ihp :arg1 "rotor-e3ga" :arg2 "bivector-e3ga")
(def-fun ihp :arg1 "even-versor" :arg2 "bivector-e3ga")
(def-fun ihp :arg1 "dual-sphere" :arg2 "normalized-point")
(def-fun ihp :arg1 "normalized-point" :arg2 "normalized-point")
(def-fun ihp :arg1 "vector-e3ga" :arg2 "e1")
(def-fun ihp :arg1 "I5" :arg2 "pseudoscalar")
(def-fun ihp :arg1 "pseudoscalar" :arg2 "I5-i")

(def-fun incr :arg1 "mv")
(def-fun incr :arg1 "bivector-e3ga")
(def-fun incr :arg1 "rotor-e3ga")
(def-fun incr :arg1 "even-versor")
(def-fun incr :arg1 "point-pair")
(def-fun incr :arg1 "no-ni")

(def-fun decr :arg1 "mv")
(def-fun decr :arg1 "bivector-e3ga")
(def-fun decr :arg1 "rotor-e3ga")
(def-fun decr :arg1 "even-versor")
(def-fun decr :arg1 "no-ni")
(def-fun decr :arg1 "free-vector")

(def-fun sp :arg1 "mv" :arg2 "mv")
(def-fun lc :arg1 "mv" :arg2 "mv")
(def-fun rc :arg1 "mv" :arg2 "mv")
(def-fun hip :arg1 "mv" :arg2 "mv")
(def-fun mhip :arg1 "mv" :arg2 "mv")

(def-fun sp :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun lc :arg1 "vector-e3ga" :arg2 "plane")
(def-fun rc :arg1 "vector-e3ga" :arg2 "line")
(def-fun hip :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun mhip :arg1 "pseudoscalar" :arg2 "vector-e3ga")

(def-fun sp :arg1 "bivector-e3ga" :arg2 "odd-versor")
(def-fun lc :arg1 "bivector-e3ga" :arg2 "vector-e3ga")
(def-fun rc :arg1 "even-versor" :arg2 "vector-e3ga")
(def-fun hip :arg1 "odd-versor" :arg2 "vector-e3ga")
(def-fun mhip :arg1 "bivector-e3ga" :arg2 "vector-e3ga")

(def-fun sp :arg1 "vector-e3ga" :arg2 "pseudoscalar")
(def-fun lc :arg1 "vector-e3ga" :arg2 "even-versor")
(def-fun rc :arg1 "vector-e3ga" :arg2 "bivector-e3ga")
(def-fun hip :arg1 "vector-e3ga" :arg2 "plane")
(def-fun mhip :arg1 "vector-e3ga" :arg2 "line")

(def-fun sp :arg1 "even-versor" :arg2 "rotor-e3ga")
(def-fun lc :arg1 "even-versor" :arg2 "rotor-e3ga")
(def-fun rc :arg1 "odd-versor" :arg2 "rotor-e3ga")
(def-fun hip :arg1 "odd-versor" :arg2 "rotor-e3ga")
(def-fun mhip :arg1 "odd-versor" :arg2 "rotor-e3ga")

(def-fun sp :arg1 "e1" :arg2 "rotor-e3ga")
(def-fun lc :arg1 "e2" :arg2 "rotor-e3ga")
(def-fun rc :arg1 "I5" :arg2 "rotor-e3ga")
(def-fun hip :arg1 "I5-i" :arg2 "rotor-e3ga")
(def-fun mhip :arg1 "no" :arg2 "rotor-e3ga")

(def-fun sp :arg1 "e1" :arg2 "e1")
(def-fun lc :arg1 "e2" :arg2 "e3")
(def-fun rc :arg1 "I5" :arg2 "I5-i")
(def-fun hip :arg1 "I5-i" :arg2 "ni")
(def-fun mhip :arg1 "no" :arg2 "e1")

(def-fun sp :arg1 "rotor-e3ga" :arg2 "plane")
(def-fun lc :arg1 "rotor-e3ga" :arg2 "line")
(def-fun rc :arg1 "rotor-e3ga" :arg2 "even-versor")
(def-fun hip :arg1 "rotor-e3ga" :arg2 "bivector-e3ga")
(def-fun mhip :arg1 "rotor-e3ga" :arg2 "pseudoscalar")

(def-fun norm :arg1 "mv")

(def-fun norm :arg1 "vector-e3ga")
(def-fun norm :arg1 "bivector-e3ga")
(def-fun norm :arg1 "line")
(def-fun norm :arg1 "plane")
(def-fun norm :arg1 "rotor-e3ga")
(def-fun norm :arg1 "odd-versor")
(def-fun norm :arg1 "even-versor")
(def-fun norm :arg1 "normalized-point")
(def-fun norm :arg1 "dual-sphere")
(def-fun norm :arg1 "pseudoscalar")
(def-fun norm :arg1 "e1")
(def-fun norm :arg1 "no-ni")
(def-fun norm :arg1 "I5-i")

(def-fun norm2 :arg1 "mv")

(def-fun norm2 :arg1 "vector-e3ga")
(def-fun norm2 :arg1 "bivector-e3ga")
(def-fun norm2 :arg1 "normalized-point")
(def-fun norm2 :arg1 "dual-sphere")
(def-fun norm2 :arg1 "circle")
(def-fun norm2 :arg1 "sphere")
(def-fun norm2 :arg1 "rotor-e3ga")
(def-fun norm2 :arg1 "odd-versor")
(def-fun norm2 :arg1 "even-versor")
(def-fun norm2 :arg1 "pseudoscalar")
(def-fun norm2 :arg1 "e3")
(def-fun norm2 :arg1 "no")
(def-fun norm2 :arg1 "I5")

(def-fun norm :output-name "norm_em" :arg1 "mv" :metric "euclidean")

(def-fun norm :output-name "norm_em" :arg1 "vector-e3ga" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "bivector-e3ga" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "free-vector" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "free-bivector" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "rotor-e3ga" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "odd-versor" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "even-versor" :metric "euclidean")
(def-fun norm :output-name "norm_em" :arg1 "pseudoscalar" :metric "euclidean")

(def-fun norm2 :output-name "norm2_em" :arg1 "mv" :metric "euclidean")

(def-fun norm2 :output-name "norm2_em" :arg1 "vector-e3ga" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "bivector-e3ga" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "line" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "plane" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "rotor-e3ga" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "odd-versor" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "even-versor" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "pseudoscalar" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "no" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "I5" :metric "euclidean")
(def-fun norm2 :output-name "norm2_em" :arg1 "e1" :metric "euclidean")

(def-fun op :arg1 "mv" :arg2 "mv")

(def-fun op :arg1 "vector-e3ga" :arg2 "vector-e3ga")
(def-fun op :arg1 "rotor-e3ga" :arg2 "vector-e3ga")
(def-fun op :arg1 "vector-e3ga" :arg2 "line")
(def-fun op :arg1 "rotor-e3ga" :arg2 "circle")
(def-fun op :arg1 "vector-e3ga" :arg2 "rotor-e3ga")
(def-fun op :arg1 "rotor-e3ga" :arg2 "rotor-e3ga")
(def-fun op :arg1 "plane" :arg2 "rotor-e3ga")
(def-fun op :arg1 "circle" :arg2 "point-pair")
(def-fun op :arg1 "bivector-e3ga" :arg2 "bivector-e3ga")
(def-fun op :arg1 "pseudoscalar" :arg2 "odd-versor")
(def-fun op :arg1 "no" :arg2 "ni")
(def-fun op :arg1 "ni" :arg2 "no")


(def-fun gp :arg1 "mv" :arg2 "double" :float-type "double")
(def-fun sas :arg1 "mv" :arg2 "double" :arg3 "double" :float-type "double")

(def-fun exp :arg1 "mv")
(def-fun exp :arg1 "point-pair")
(def-fun exp :arg1 "free-vector")
(def-fun exp :arg1 "no-ni")
(def-fun cosh :arg1 "mv")
(def-fun cosh :arg1 "flat-point")
(def-fun sinh :arg1 "mv")
(def-fun sinh :arg1 "bivector-e3ga")
(def-fun sinh :arg1 "free-vector")
(def-fun cos :arg1 "mv")
(def-fun cos :arg1 "bivector-e3ga")
(def-fun cos :arg1 "point-pair")
(def-fun cos :arg1 "free-vector")
(def-fun cos :arg1 "no-ni")
(def-fun sin :arg1 "mv")
(def-fun sin :arg1 "bivector-e3ga")
(def-fun sin :arg1 "point-pair")
(def-fun sin :arg1 "free-vector")
(def-fun sin :arg1 "no-ni")


(def-fun negate :arg1 "mv")

(def-fun negate :arg1 "vector-e3ga")
(def-fun negate :arg1 "free-vector")
(def-fun negate :arg1 "normalized-point")
(def-fun negate :arg1 "dual-sphere")
(def-fun negate :arg1 "line")
(def-fun negate :arg1 "sphere")
(def-fun negate :arg1 "rotor-e3ga")
(def-fun negate :arg1 "odd-versor")
(def-fun negate :arg1 "even-versor")
(def-fun negate :arg1 "pseudoscalar")
(def-fun negate :arg1 "e1")
(def-fun negate :arg1 "e2")
(def-fun negate :arg1 "no-ni")
(def-fun negate :arg1 "double" :float-type "double")

(def-fun reverse :arg1 "mv")
(def-fun reverse :arg1 "vector-e3ga")
(def-fun reverse :arg1 "bivector-e3ga")
(def-fun reverse :arg1 "normalized-point")
(def-fun reverse :arg1 "dual-sphere")
(def-fun reverse :arg1 "line")
(def-fun reverse :arg1 "circle")
(def-fun reverse :arg1 "rotor-e3ga")
(def-fun reverse :arg1 "odd-versor")
(def-fun reverse :arg1 "even-versor")
(def-fun reverse :arg1 "pseudoscalar")
(def-fun reverse :arg1 "double" :float-type "double")
(def-fun reverse :arg1 "e1")
(def-fun reverse :arg1 "e3")
(def-fun reverse :arg1 "I5-i")

(def-fun clifford-conjugate :arg1 "mv")
(def-fun clifford-conjugate :arg1 "vector-e3ga")
(def-fun clifford-conjugate :arg1 "point-pair")
(def-fun clifford-conjugate :arg1 "normalized-point")
(def-fun clifford-conjugate :arg1 "free-vector")
(def-fun clifford-conjugate :arg1 "line")
(def-fun clifford-conjugate :arg1 "plane")
(def-fun clifford-conjugate :arg1 "rotor-e3ga")
(def-fun clifford-conjugate :arg1 "odd-versor")
(def-fun clifford-conjugate :arg1 "even-versor")
(def-fun clifford-conjugate :arg1 "pseudoscalar")
(def-fun clifford-conjugate :arg1 "double" :float-type "double")
(def-fun clifford-conjugate :arg1 "e1")
(def-fun clifford-conjugate :arg1 "no-ni")
(def-fun clifford-conjugate :arg1 "I3")

(def-fun grade-involution :arg1 "mv")
(def-fun grade-involution :arg1 "vector-e3ga")
(def-fun grade-involution :arg1 "bivector-e3ga")
(def-fun grade-involution :arg1 "normalized-point")
(def-fun grade-involution :arg1 "dual-sphere")
(def-fun grade-involution :arg1 "line")
(def-fun grade-involution :arg1 "plane")
(def-fun grade-involution :arg1 "rotor-e3ga")
(def-fun grade-involution :arg1 "odd-versor")
(def-fun grade-involution :arg1 "even-versor")
(def-fun grade-involution :arg1 "pseudoscalar")
(def-fun grade-involution :arg1 "double" :float-type "double")
(def-fun grade-involution :arg1 "no")
(def-fun grade-involution :arg1 "ni")
(def-fun grade-involution :arg1 "I3")


(def-fun unit :arg1 "mv")
(def-fun unit :arg1 "vector-e3ga")
(def-fun unit :arg1 "bivector-e3ga")
(def-fun unit :arg1 "line")
(def-fun unit :arg1 "sphere")
(def-fun unit :arg1 "rotor-e3ga")
(def-fun unit :arg1 "odd-versor")
(def-fun unit :arg1 "even-versor")
(def-fun unit :arg1 "normalized-point")
(def-fun unit :arg1 "dual-sphere")
(def-fun unit :arg1 "pseudoscalar")
(def-fun unit :arg1 "point-pair")
(def-fun unit :arg1 "e2")
(def-fun unit :arg1 "I5")

(def-fun unit :output-name "unit_em" :arg1 "mv" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "vector-e3ga" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "bivector-e3ga" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "circle" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "plane" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "rotor-e3ga" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "odd-versor" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "even-versor" :metric "euclidean")
(def-fun unit :output-name "unit_em" :arg1 "pseudoscalar" :metric "euclidean")

(def-fun add :arg1 "even-versor" :arg2 "even-versor")
(def-fun gp returnType="even-versor" :arg1 "point-pair" :arg2 "point-pair")
(def-fun subtract :arg1 "even-versor" :arg2 "even-versor")
(def-fun gp returnType="even-versor" :arg1 "even-versor" :arg2 "point-pair")
(def-fun gp returnType="even-versor" :arg1 "even-versor" :arg2 "even-versor")

(def-fun sas :arg1 "point-pair" :arg2 "double" :arg3 "double" :float-type "double")
(def-fun gp :arg1 "even-versor" :arg2 "double" :float-type "double")
(def-fun gp :arg1 "point-pair" :arg2 "double" :float-type "double")


(def-fun versor-inverse :arg1 "mv")
(def-fun versor-inverse :output-name "versorInverse_em" :arg1 "mv" :metric "euclidean")



