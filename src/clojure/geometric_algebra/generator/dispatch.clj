(ns geometric-algebra.generator.dispatch
  "This provides the details for type converters."
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
;;       The optional metric attribute specifies the usage
;;       of a nondefault metric (case insensitive).
;;       By default, the metric "default" is used.
;;       By using this attribute a different metric may be used
;;       for the function, e.g., metric="euclidean".
;;  - :doc
;;       Use the this optional attribute to add any extra
;;       user comments to the function documentation.
;;       For example, one could use the comment to explain
;;       what a certain function is used for.
  (:require [geometric-algebra.generator.context :as ctx-ns]
            [geometric-algebra.generator.multivector :as mv-ns]
            [geometric-algebra.generator.special-multivector :as smv-ns]
            [geometric-algebra.generator.constant :as const-ns]
            [geometric-algebra.generator.outer-morphism :as om-ns]
            [geometric-algebra.generator.converter :as conv-ns] ))

(defmacro def-random-double
  [& options]
  ;; {:output-name "genrand" :optionGen "libc"})
  )

(defmacro def-add
  "Requisition an add multi-function
  ==============================

  add  : add two multi-vectors
  "
  [mv1 mv2] nil)

(defmacro def-subtract
  "Requisition a *subtract* multi-function
  ==============================

  subtract  : subtract two multi-vectors
  "
  [mv1 mv2] nil)

(defmacro def-apply-om
  "Requisition an *apply-om* multi-function
  ==============================

  - apply-om  : apply an outermorphism to a mv
  "
  [om mv] nil)

(defmacro def-apply-versor
  "Requisition an *apply-versor* multi-function
  ==============================

  - apply-versor  : apply (sandwich product) a versor to a mv
  This can take a custom :metric in {:euclidean ?}
  "
  [ver1 mv1 & options] nil)

(defmacro def-apply-unit-versor
  "Requisition an *unit-versor* multi-function
  ==============================

  - apply-unit-versor : same as apply-versor but with the unit assumption (careful).
  "
  [uv1 mv1 & options] nil)

(defmacro def-apply-versor-wi
  "Requisition an *apply-versor-wi* multi-function
  ==============================

  - apply-versor-wi : another efficiency consideration
  "
  [mv1 mv2 mv3 & options] nil)

(defmacro def-cga-point
  "Requisition a *cga-point* multi-function
  ==============================

  - cga-point : construct a conformal point
  "
  [vect & options] nil)

(defmacro def-cga-point-distance
  "Requisition a *cga-point-distance* multi-function
  ==============================

  - cga-point-distance : a scalar representing the distance between centers of mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-random-point
  "Requisition a *random-point* multi-function
  ==============================

  - random-point : constract a random conformal point
  "
  [] nil)

(defmacro def-div
  "Requisition a *div* multi-function
  ==============================

  - div : divide a mv by a scalar
  "
  [mv alpha & options] nil)

(defmacro def-dual
  "Requisition a *dual* multi-function
  ==============================

  - dual : compute the dual of a mv
  "
  [mv & options] nil)

(defmacro def-undual
  "Requisition a *undual* multi-function
  ==============================

  - undual : compute the undual of a mv
  "
  [mv & options] nil)

(defmacro def-equals
  "Requisition an *equals* multi-function
  ==============================

  - equals : check for equality with a difference threshold
  "
  [mv1 mv2 & options] nil)
 ;; `(defmethod 'equals [mv1 mv2 threshhold]
 ;;    nil) )

(defmacro def-extract-grades
  "Requisition a *extract-grades* multi-function
  ==============================

  - extract-grade : extracts a vector of the grade parts from a mv
  The first form returns a vector of all the grades
  regardless of order (unless specified in the options).

  options:
  - order -
  "
  [mv & options] nil)

(defmacro def-extract-grade
  "Requisition a *extract-grade* multi-function
  ==============================

  extract-grade : extracts a vector of the grade parts from a mv
  Only returns the grades of the specified order.
  "
  [order mv & options] nil)

(defmacro def-grade-bitmap
  "Requisition a *grade-bitmap* multi-function
  ==============================

  - grade-bitmap : extracts the grade bitmap for a mv
  "
  [mv & options]  nil)

(defmacro def-gp
  "Requisition a *gp* multi-function
  ==============================

  - gp : computes the geometric product of a pair of mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-hp
  "Requisition a *hp* multi-function
  ==============================

  - hp : computes the hadamard product of two mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-inv-gp
  "Requisition a *inv-gp* multi-function
  ==============================

  - igp : computes the inverse geometric product of two mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-inv-hp
  "Requisition a *inv-hp* multi-function
  ==============================

  - ihp : computes the inverse hadamard product of two mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-incr
  "Requisition a *incr* multi-function
  ==============================

  - incr : increment the mv
  "
  [mv1 & options] nil)

(defmacro def-decr
  "Requisition a *decr* multi-function
  ==============================

  - decr : decrement the mv
  "
  [mv1 & options] nil)

(defmacro def-hip
  "Requisition a *hip* multi-function
  ==============================

  - hip : compute the Hestenes inner product of an mv pair
  "
  [mv1 mv2 & options] nil)

(defmacro def-mhip
  "Requisition a *mhip* multi-function
  ==============================

  - mhip : compute the modified Hestenes inner product of an mv pair
  "
  [mv1 mv2 & options] nil)

(defmacro def-lc
  "Requisition a *lc* multi-function
  ==============================

  - lc : compute the left contraction inner product of an mv pair
  "
  [mv1 mv2 & options] nil)

(defmacro def-rc
  "Requisition a *rc* multi-function
  ==============================

  - rc : compute the right contraction inner product of an mv pair
  "
  [mv1 mv2 & options] nil)

(defmacro def-sp
  "Requisition a *sp* multi-function
  ==============================

  - sp : compute the scalar product of two mv
  "
  [mv1 mv2 & options] nil)

(defmacro def-log
  "Requisition a *log* multi-function
  ==============================

  - log : compute the logarithm of a rotor. The rotor type is specified
  "
  [mv1 type & options] nil)

(defmacro def-exp
  "Requisition a *exp* multi-function
  ==============================

  - exp : compute the exponential of a mv
  The exp functions can generate more efficient
  code when it knows what the sign of the square of the argument is.
  In that case, one may use for example *square?* = 1.0.

  options:
  - square -
  "
  [mv1 & options] nil)

(defmacro def-norm
  "Requisition a *norm* multi-function
  ==============================

  - norm : compute the norm (magnitude) of a mv
  "
  [mv1 & options] nil)

(defmacro def-norm2
  "Requisition a *norm2* multi-function
  ==============================

  - norm2 : compute the squared norm of a mv
  "
  [mv1 & options] nil)

(defmacro def-op
  "Requisition a *op* multi-function
  ==============================

  - op : compute the outer produce of an mv pair
  "
  [mv1 mv2 & options] nil)

(defmacro def-random-blade
  "Requisition a *random-blade* multi-function
  ==============================

  - random-blade : create a random blade
  "
  [ & options] nil)

(defmacro def-random-versor
  "Requisition a *random-versor* multi-function
  ==============================

  - random-versor :
  "
  [ & options] nil)

(defmacro def-random-scalar
  "Requisition a *random-scalar* multi-function
  ==============================

  - random-scalar :
  "
  [ & options] nil)

(defmacro def-random-smv
  "Requisition a *random-smv* multi-function
  ==============================

  - random-smv : takes the type of mv to generate
  "
  [ & options] nil)

(defmacro def-sas
  "Requisition a *sas* multi-function
  ==============================

  - sas : scale and add scalar, scals a mv by a factor then adds a scalar.
  "
  [mv1 & options] nil)
 ;; `(defmethod sas [mv1 scale addend] nil))

(defmacro def-sin
  "Requisition a *sin* multi-function
  ==============================

  - sin : compute the scalar sine of a mv.
  A custom metric may be specified.
  "
  [mv1 & options] nil)

(defmacro def-cos
  "Requisition a *cos* multi-function
  ==============================

  - cos : compute the scalar cosine of a mv
  "
  [mv1 & options] nil)

(defmacro def-sinh
  "Requisition a *sinh* multi-function
  ==============================

  - sinh : compute the scalar hyperbolic sine of a mv
  "
  [mv1 & options] nil)

(defmacro def-cosh
  "Requisition a *cosh* multi-function
  ==============================

  - cosh : compute the scalar hyperbolic cosine of a mv
  "
  [mv1 & options] nil)

(defmacro def-negate
  "Requisition a *negate* multi-function
  ==============================

  - negate : negate a mv
  "
  [mv1 & options] nil)

(defmacro def-reverse
  "Requisition a *reverse* multi-function
  ==============================

  - reverse : reverse a mv
  "
  [mv1 & options] nil)

(defmacro def-clifford-conjugate
  "Requisition a *clifford-conjugate* multi-function
  ==============================

  - clifford-conjugate : compute the Clifford conjugate of a mv
  "
  [mv1 & options] nil)

(defmacro def-grade-involution
  "Requisition a *grade-involution* multi-function
  ==============================

  - grade-involution : compute the grade involution of a mv
  "
  [mv1 & options] nil)

(defmacro def-unit
  "Requisition a *unit* multi-function
  ==============================

  - unit : computes the unit of a mv, suitable for use by apply-unit-versor
  "
  [mv1 & options] nil)

(defmacro def-inv-versor
  "Requisition a *inv-versor* multi-function
  ==============================

  - versor-inverse : compute the versor inverse of a mv
  "
  [mv1 & options] nil)

(defmacro def-zero?
  "Requisition a ** multi-function
  ==============================

  - zero : check if a mv is zero, via a threshold
  "
  [mv1 threshhold & options] nil)


