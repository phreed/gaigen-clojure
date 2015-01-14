
(ns geometric-algebra.generator.context
  "This provides the details for the geometric-algebra context."
  (:require [clojure.math.combinatorics :as combo]))


(defmacro def-spec
  "The Specification
  ==================

  - license #{ :custom :gpl :bsd }
  The license of the generated code.
  The license is case insensitive.
  If the license is custom, a custom-license element
  is expected later on in the specification.

  - language #{ :clojure :c :cpp :java :csharp :python :matlab }
  (the fact that a value is valid does not means
  that it is actually implemented . . .).
  The language names are case insensitive.

  - namespace (string)
  The name and the namespace/package of the generated code
  (always required, because it is also used as a
  prefix/part of generated filenames).

  - coord-storage  #{ :array :variables }
  The value can be :array (coordinates are stored in arrays)
  or :variables (one variable for each coordinate).
  Determines whether coordinates are stored in arrays or in single variables.
  This only applies to specialized multivectors.

  - default-operator-bindings (boolean)
  If true, the default operator bindings for the output language are used
  (for example, the + symbol is bound to the add function).

  - dimension (integer)
  The dimension of the space of the algebra. Must be >= 1.
  For values above 7, consider using :gmv-code :runtime, see below.
  Values above 12 probably lead to code that
  cannot be compiled because it is too large.

  - test-suite (boolean)
  Whether to generate extra code to test the generated code.

  - report-usage (boolean)
  When true, print statements are added to the
  code to report usage of non-optimized functions
  (i.e., functions involving specialized multivectors
  which were implicitly converted to general multivectors).
  Also, a member variable is added to the general multivector
  type which keeps track of the original specialized type of the multivector.
  This option has no effect in the C language
  because it does not support implicit conversion.

  - gmv-code #{ :expand :run-time }
  The code for general multivectors can be very large.
  For example a geometric product of two GMVs in 10-D takes
  on the order of 2^10 * 2^10 multiplications and additions.
  If the code for this product is written explicitly into the code
  (the default option), the code size would also be in the order of megabytes.
  Because of this, Gaigen 1 and Gaigen 2 could only generate algebras up to about 7-D.
  To overcome this limitation, Gaigen 2.5 supports *:run-time*
  computation of geometric products and all other functions without explicitly
  generating code for every single multiply/add.
  The default option expand writes out all code, is fast,
  but cannot realistically be used above 7-D.
  The option *:run-time* performs the computations at run-time,
  using (among others) tables which must be initialized at startup.
  If the option *:run-time* is used, the metric must be diagonal.
  To compute the tables of a nondiagonal metric,
  symmetric eigenvalue computation is required, and
  it would be a burden to require eigenvalue code for every output language.
  Note that the *:run-time* code takes approximately
  twice as much time as the *:expanded* code.

  - parser #{ :none :builtin :antlr }
  What type of multivector string parser to generate.
  The default is :none.
  Other options are :builtin (for a parser hand-written for Gaigen 2.5) and
  :antlr for an ANTLR based parser.
  Both these parsers have the same functionality and interface,
  but their internal implementation is different.
  For the ANTLR parser, you need to invoke java org.antlr.Tool
  on the generated .g grammar and link with the ANTLR run-time.

  - copyright
  The copyright notice of the generated code.
  "
  [spec & attrs]
  `(def ~spec (hash-map ~@attrs)))

(defmacro def-custom-license
  "The custom license text.
  =========================

  This element must be present when *:license :custom*.
  The text is copied verbatim to the top of each the generated file. "
  [license text]
  `(def ~license ~text))

(defmacro def-output-directory
  "Where the generated files should go.
  =====================================

  The path attribute sets the directory where the output should go.
  By default, the output goes to the current working directory. "
  ([out-dir] `(def ~out-dir (System/getProperty "user.dir")))
  ([out-dir path] `(def ~out-dir ~path)))

(defmacro def-output-file-rename
  "Rename output file from default
  ================================

  Allows the name of individual generated files to be modified.
  For example, if the code generator would generate a file named foo.cpp,
  but the user wants this file to be named bar.cpp, then setting
  the key to \"foo.cpp\" and the value to \"bar.cpp\" causes
  the alternate filename to be written.
  File names do not include the directory.
  "
  [mapper & rename-map]
  `(def ~mapper (hash-map ~@rename-map)))


(defmacro def-inline
  "What types of functions to in-line.
  Possible attributes are:
  - constructors - setters - assigns - operators - functions.
  These are boolean attributes, and are *false* by default."
  [inliner & attrib]
  `(def ~inliner (hash-map ~@attrib)))


(defmacro def-float-type
  "The floating point implementation type
  =======================================
  - :double - IEEE double when available
  - :float - IEEE float when available"
  [name type]
  `(def ~name ~type))


(defmacro def-basis
  "Basis Vector Names
  ===================

  The basis definition lists the names of basis vectors of the algebra.
  The number of basis vectors must match the dimension, N, of the space."
  ([name names options]
   (if (and (contains? options :default)
            (:default options))
     `(def ~name ~names))))

(defn outer-term
  "
    ^ |    no           e1           e2           e3           ni
  ----------------------------------------
   no |     0        no^e1(eo1)   no^e2(eo2)   no^e3(eo3)   no^ni(eon)
   e1 | e1^no(-eo1)      0        e1^e2(e12)   e1^e3(e13)   e1^ni(e1n)
   e2 | e2^no(-eo2)  e2^e1(-e12)      0        e2^e3(e23)   e2^ni(e2n)
   e3 | e3^no(-eo3)  e3^e1(-e13)  e3^e2(-e23)      0        e3^ni(e3n)
   ni | ni^no(-eon)  ni^e1(-e1n)  ni^e2(-e2n)  ni^e3(-eo2)      0


  This relationship is independent of metric.
  This suggests that a simple function can
  generate the term coefficients."
  [tn1 tn2]
  (println "not implemented") )

(defn inner-term
  "
    ^ |    no   e1   e2   e3   ni
  ---------------------------------
   no |     0    0    0    0   -1
   e1 |     0    1    0    0    0
   e2 |     0    0    1    0    0
   e3 |     0    0    0    1    0
   ni |    -1    0    0    0    0

  The sparseness suggests that only the non-zero
  elements need to be specified."
  [tn1 tn2]
  (println "not implemented") )

(defmacro def-metric
  " The metric
  ==========

  A metric element specifies the inner product between a pair of basis vectors.
  By default, all inner product between basis vectors are assumed to be 0.
  By using metric elements, one can set the inner product to different values.
  Inside a single algebra, different metrics can be used,
  e.g. a conformal one and a Euclidean one.
  Having a Euclidean metric is useful,
  e.g., for blade factorization algorithms.
  This line says that the inner product between basis vectors :no and :ni is -1.
  Inside function elements, a non-default metric name may be specified
  by using the :metric <name> attribute, e.g., :metric conformal.


  - :round (boolean)
  Due to floating point round-off errors in eigenvalue computation, values
  or coordinates that should be (e.g.) 1.0 may become (e.g., 1 + 1e −16).
  This makes the generated code less efficient,
  is annoying to read and propagates the round-off errors.
  For that reason, there is the option to round coordinates after a metric product.
  The default is to round, but when the final metric is diagonal,
  it is forced to no rounding because there is not need to use it in that case.
  When rounding is enabled, coordinates which are
  very close to an integer value are rounded to that value. The threshold
  for being *very close* is 1e-14.
  "
  [metric-name options settings]
  `(def ~metric-name ~settings))

(defmacro def-context
  "The context
  ============

  The context receives and includes:
  - :basis - the names of the basis vectors
  - :metric - a summary of the basis products
  - :spec default-spec
  - :inline default-inline
  - :output default-output-directory
  - :rename-map default-file-rename-map
  - :real-type default-float-type
  These properties are created by their respective constructors.

  The context generates some useful items as well:
  - :prod-map - given a basis and a metric a
                central product map is created.
  "
  [context-name & foci]
  `(def ~context-name (hash-map ~@foci)))

