(ns gafcs.gaigen
  "This is based on the gaigen code generator and the book
  Geometric Algebra for Computer Science.
  For examples see gafcs/c3ga.clj")

(defmacro def-g25spec [] nil)

(defmacro def-inline [] nil)

(defmacro def-float-type
  [type]
  `(def float-type ))

(defmacro def-basis-vector-names
  "Basis Vector Names
  ===================

 The basisVectorNames definition lists the names of basis vectors of the algebra.
 The number of basis vectors must match the dimension N of the space.
 "
  [name names]
  `(def name names))


(defmacro def-metric
" The metric
  ==========

 A metric element specifies the inner product
 between a pair of basis vectors.
 By default, all inner product between basis vectors are assumed to be 0.
 By using metric elements, one can set the inner product to different values.
 Inside a single algebra, different metrics can be used,
   e.g. a conformal one and a Euclidean one.
 Having a Euclidean metric is useful,
   e.g., for blade factorization algorithms.
 This line says that the inner product between basis vectors :no and :ni is -1.
 Inside function elements, a non-default metric name may be specified
 by using the :metric <name> attribute, e.g., :metric conformal.

 - :round.
 Due to floating point round-off errors in eigenvalue computation, values
 or coordinates that should be (e.g.) 1.0 may become (e.g., 1 + 1e −16).
 This makes the generated code less efficient, is annoying to read and propagates the round-off errors.
 For that reason, there is the option to round coordinates after a metric product.
 The default is to round, but when the final metric is diagonal,
 it is forced to no rounding because there is not need to use it in that case.
 When rounding is enabled, coordinates which are
 very close to an integer value are rounded to that value. The threshold
 for being *very close* is 1e-14.
  "
  [name basis options settings]
  (let [x settings]
  `(def (symbol "metric-" name) )))

(defmacro def-mv
"The multi-vector
 ================

 The multi-vector specifies the properties of the general multivector.
 It is one of the most involved elements.
 See *def-smv* for details.
 "
  ([name] nil)
  ([name options settings] nil))


(defmacro def-smv
"The specialized-multi-vector for any other type of multi-vector
  ==============================================================

 The *smv* element should contain the basis blades of the type.
 These may have constant assignments, and if the type is constant,
 then all basis blades must have a constant assignment.
 An example of a specialized multivector definition is

     (def-smv normalized-point {:constant true}
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
     "
  ([name settings] nil )
  ([name options settings] nil))

(defmacro def-smv-blade
"The specialized-multi-vector blade
  ================================

  See *def-smv for details."
  ([name settings] nil )
  ([name options settings] nil))

(defmacro def-smv-versor
"The specialized-multi-vector versor
  =================================

  See *def-smv for details."
  ([name settings] nil )
  ([name options settings] nil))

(defmacro def-smv-rotor
"The specialized-multi-vector rotor
  ================================

  See *def-smv for details."
  ([name settings] nil )
  ([name options settings] nil))



(defmacro def-constant
  "The geometric constant
  =======================

 This element is used to generate a constant value in the output.
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
  [name options settings]
  vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})



(def-constant vector-e1 {:type "vector-e3ga"} {[:e1] 1 [:e2] 0 [:e3] 0})

(defmacro def-om
  "The outer-morphism
  ====================

 Specifies the general outermorphism matrix representation type.
 This allows for efficient application of linear transformations using the applyOM function.
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
 Note that all basis blades must be present in an general outermorphism’s range and domain.
 The attributes of a om element are:
 - name.
     The name of the outermorphism type, for example om.
 If :domain or :range are specified the default values are used.
 When only :range is not specified it is identical to :domain.
  "
  ([name] nil)
  ([name args] nil))

(defmacro def-som
  "The specialized outer-morphism
  ================================

 A som element specifies a specialized outermorphism.
 It is pretty much that same as a general outermorphism
 except it does not need to have all basis blades in its domain and range.
  "
  ([name] nil)
  ([name args] nil))


(defmacro def-con
  "Requisition a conversion function
  ==================================

 Requests a conversion function to the code generator back-end.
 By default each smv has convertors to and from mv.
 Specific convertors from smv to smv can be requested.

 options:
  - arg-nameN.
       Specifies the name of argument N.
       This only affects the name of the argument inside the generated function.
       Specifying this name may be superfluous, but it can improve readability,
       especially for code completion.
  "
  [name options settings]
  nil)

;; Requests a multi-function to the code generator back-end.
;; options:
;;  - :rename
;;       Changes the name of the generated function to the value of the attribute.
;;       For example, allows you to rename a function gp to geometricProduct.
;;       Sometimes this attribute is required to avoid name-clashes,
;;       for example if you want the define the same function for two different metrics.
;;  - :return-type
;;       By default, the code generator will determine the return type
;;       of the functions it generates, but it is possible to override
;;       this default by setting it explicitly.
;;       The return type should be the name of a specialized multivector.
;;       However, the return type may also be scalar or any of the floating
;;       point typenames used in the algebra.
;;       If the return type is scalar, then a float will be returned,
;;       automatically adapted to the floating point type of the function.
;;  - :float-type
;;       Multiple float-type attributes may be present in a single function element.
;;       By default, the code generator will generate code for all floating point
;;       types of the specification, using the float-type attribute(s)
;;       this may be limited to only the set of listed floating point types.
;;  - :metric
;;       The optional metric attribute specifies the usage of a nondefault metric (case insensitive).
;;       By default, the metric "default" is used.
;;       By using this attribute a different metric may be used for the function, e.g., metric="euclidean".
;;  - doc-string
;;       Use the this optional attribute to add any extra user comments to the function documentation.
;;       For example, one could use the comment to explain what a certain function is used for.

(defmacro def-fun-add
  "Requisition an add multi-function
  ==============================

  add  : add two multi-vectors
  "
  [mv1 mv2] nil)

(defmacro def-fun-subtract
  "Requisition a *subtract* multi-function
  ==============================

  subtract  : subtract two multi-vectors
  "
  [mv1 mv2] nil)

(defmacro def-fun-apply-om
  "Requisition an *apply-om* multi-function
  ==============================

 - apply-om  : apply an outermorphism to a mv
  "
  [om mv] nil)

(defmacro def-fun-apply-versor
  "Requisition an *apply-versor* multi-function
  ==============================

 - apply-versor  : apply (sandwich product) a versor to a mv
        This can take a custom :metric in {:euclidean ?}
  "
  [ver1 mv1] nil)

(defmacro def-fun-unit-versor
  "Requisition an *unit-versor* multi-function
  ==============================

 - apply-unit-versor : same as apply-versor but with the unit assumption (careful).
  "
  [uv1 mv1] nil)

(defmacro def-fun-apply-versor-wi
  "Requisition an *apply-versor-wi* multi-function
  ==============================

 - apply-versor-wi : another efficiency consideration
  "
  [mv1 mv2 mv3] nil)

(defmacro def-fun-cga-point
  "Requisition a *cga-point* multi-function
  ==============================

 - cga-point : construct a conformal point
  "
  [x1 x2 x3] nil)

(defmacro def-fun-cga-point-distance
  "Requisition a *cga-point-distance* multi-function
  ==============================

 - cga-point-distance : a scalar representing the distance between centers of mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-random-point
  "Requisition a *random-point* multi-function
  ==============================

 - random-point : constract a random conformal point
  "
  [] nil)

(defmacro def-fun-div
  "Requisition a *div* multi-function
  ==============================

 - div : divide a mv by a scalar
  "
  [mv alpha] nil)

(defmacro def-fun-dual
  "Requisition a *dual* multi-function
  ==============================

 - dual : compute the dual of a mv
  "
  [mv] nil)

(defmacro def-fun-undual
  "Requisition a *undual* multi-function
  ==============================

 - undual : compute the undual of a mv
  "
  [mv] nil)

(defmacro def-fun-equals
  "Requisition an *equals* multi-function
  ==============================

 - equals : check for equality with a difference threshold
  "
  [mv1 mv2]
  `(defmethod equals [mv1 mv2 threshhold]
     nil) )

(defmacro def-fun-extract-grade
  "Requisition a *extract-grade* multi-function
  ==============================

 - extract-grade : extracts a vector of the grade parts from a mv
  The first form returns a vector of all the grades.
  The second form only returns the grades of the specified order.
  "
  ([mv] nil)
  ([order mv] nil))

(defmacro def-fun-grade-bitmap
  "Requisition a *grade-bitmap* multi-function
  ==============================

 - grade-bitmap : extracts the grade bitmap for a mv
  "
  [mv] nil)

(defmacro def-fun-gp
  "Requisition a *gp* multi-function
  ==============================

 - gp : computes the geometric product of a pair of mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-hp
  "Requisition a *hp* multi-function
  ==============================

 - hp : computes the hadamard product of two mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-inv-gp
  "Requisition a *inv-gp* multi-function
  ==============================

 - igp : computes the inverse geometric product of two mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-inv-hp
  "Requisition a *inv-hp* multi-function
  ==============================

 - ihp : computes the inverse hadamard product of two mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-incr
  "Requisition a *incr* multi-function
  ==============================

 - incr : increment the mv
  "
  [mv1] nil)

(defmacro def-fun-decr
  "Requisition a *decr* multi-function
  ==============================

 - decr : decrement the mv
  "
  [mv1] nil)

(defmacro def-fun-hip
  "Requisition a *hip* multi-function
  ==============================

 - hip : compute the Hestenes inner product of an mv pair
  "
  [mv1 mv2] nil)

(defmacro def-fun-mhip
  "Requisition a *mhip* multi-function
  ==============================

 - mhip : compute the modified Hestenes inner product of an mv pair
  "
  [mv1 mv2] nil)

(defmacro def-fun-lc
  "Requisition a *lc* multi-function
  ==============================

 - lc : compute the left contraction inner product of an mv pair
  "
  [mv1 mv2] nil)

(defmacro def-fun-rc
  "Requisition a *rc* multi-function
  ==============================

 - rc : compute the right contraction inner product of an mv pair
  "
  [mv1 mv2] nil)

(defmacro def-fun-sp
  "Requisition a *sp* multi-function
  ==============================

 - sp : compute the scalar product of two mv
  "
  [mv1 mv2] nil)

(defmacro def-fun-log
  "Requisition a *log* multi-function
  ==============================

 - log : compute the logarithm of a rotor. The rotor type is specified
  "
  [mv1 type] nil)

(defmacro def-fun-exp
  "Requisition a *exp* multi-function
  ==============================

 - exp : compute the exponential of a mv
     The exp functions can generate more efficient
     code when it knows what the sign of the square of the argument is.
     In that case, one may use for example *square?* = 1.0.
  "
  ([mv1] nil)
  ([mv1 square?] nil))

(defmacro def-fun-norm
  "Requisition a *norm* multi-function
  ==============================

 - norm : compute the norm (magnitude) of a mv
  "
  [mv1] nil)

(defmacro def-fun-norm2
  "Requisition a *norm2* multi-function
  ==============================

 - norm2 : compute the squared norm of a mv
  "
  [mv1] nil)

(defmacro def-fun-op
  "Requisition a *op* multi-function
  ==============================

 - op : compute the outer produce of an mv pair
  "
  [mv1 mv2] nil)

(defmacro def-fun-random-blade
  "Requisition a *random-blade* multi-function
  ==============================

 - random-blade : create a random blade
  "
  [] nil)

(defmacro def-fun-random-versor
  "Requisition a *random-versor* multi-function
  ==============================

 - random-versor :
  "
  [] nil)

(defmacro def-fun-random-scalar
  "Requisition a *random-scalar* multi-function
  ==============================

 - random-scalar :
  "
  [] nil)

(defmacro def-fun-random-smv
  "Requisition a *random-smv* multi-function
  ==============================

 - random-smv : takes the type of mv to generate
  "
  [] nil)

(defmacro def-fun-sas
  "Requisition a *sas* multi-function
  ==============================

 - sas : scale and add scalar, scals a mv by a factor then adds a scalar.
  "
  [mv1]
  `(defmethod sas [mv1 scale addend] nil))

(defmacro def-fun-sin
  "Requisition a *sin* multi-function
  ==============================

 - sin : compute the scalar sine of a mv.
  A custom metric may be specified.
  "
  ([mv1] nil)
  ([mv1 options] nil))

(defmacro def-fun-cos
  "Requisition a *cos* multi-function
  ==============================

 - cos : compute the scalar cosine of a mv
  "
  ([mv1] nil)
  ([mv1 options] nil))

(defmacro def-fun-sinh
  "Requisition a *sinh* multi-function
  ==============================

 - sinh : compute the scalar hyperbolic sine of a mv
  "
  ([mv1] nil)
  ([mv1 options] nil))

(defmacro def-fun-cosh
  "Requisition a *cosh* multi-function
  ==============================

 - cosh : compute the scalar hyperbolic cosine of a mv
  "
  ([mv1] nil)
  ([mv1 options] nil))

(defmacro def-fun-negate
  "Requisition a *negate* multi-function
  ==============================

 - negate : negate a mv
  "
  [mv1] nil)

(defmacro def-fun-reverse
  "Requisition a *reverse* multi-function
  ==============================

 - reverse : reverse a mv
  "
  [mv1] nil)

(defmacro def-fun-clifford-conjugate
  "Requisition a *clifford-conjugate* multi-function
  ==============================

 - clifford-conjugate : compute the Clifford conjugate of a mv
  "
  [mv1] nil)

(defmacro def-fun-grade-involution
  "Requisition a *grade-involution* multi-function
  ==============================

 - grade-involution : compute the grade involution of a mv
  "
  [mv1] nil)

(defmacro def-fun-unit
  "Requisition a *unit* multi-function
  ==============================

 - unit : computes the unit of a mv, suitable for use by apply-unit-versor
  "
  [mv1] nil)

(defmacro def-fun-inv-versor
  "Requisition a *inv-versor* multi-function
  ==============================

 - versor-inverse : compute the versor inverse of a mv
  "
  [mv1] nil)

(defmacro def-fun-zero?
  "Requisition a ** multi-function
  ==============================

 - zero : check if a mv is zero, via a threshold
  "
  [mv1 threshhold] nil)



