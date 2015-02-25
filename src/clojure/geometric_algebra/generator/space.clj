(ns geometric-algebra.generator.space
  "Representation of a GA space. "
  (:require [geometric-algebra.generator
             [context :as ctx]
             [blade :as bb]]
            [clojure.math.combinatorics :as combo]))

(defrecord SpaceType [llave basis nombre generated?? dual?])

(defn make-type
  "Data structure representing a space type

  bc - bitwise representation of coordinate
  wt - scale factor"
  [llave basis nombre] (->SpaceType llave basis nombre false false))

(defn basis-count
  "Given a vector of metrics compute the basis-length.
  It is just 2**<dimensions-in-metric>."
  [metric] (reduce * (repeat (count metric) 2)))


(defn build-basis
  "Construct the bitwise representation for the coordinate basis.
  This takes a vector representing the metrics
  for the diagonal basis coordinate blades and returns:
  - a vector whose values are the bitmaps assigned to blades.
  The entries are sorted by grade and numerical value.

  To assist in this process a map whose keys
  are the basis blades and values indicate that
  the blade is defined."
  [metric]
  (vec (sort-by bb/grade
                (range 1 (basis-count metric) ))))


(defn key-bit
  "Create a key capable of representing
  all coordinates in a metric by an individual bit.

  b - (optional) bitwise representation of coordinate.
  The key consists of a vector of integers with just
  one bit on."
  [basis & b]
  (let [nkeys (int (Math/ceil (/ (count basis) 32)))
        hkey (vec (repeat nkeys 0))]
    (if (empty? b)
      hkey   ; no blade just return empty key
      (let [bc (inc (first b))
            k (dec (int (Math/ceil (/ bc 32))))
            shft (bit-shift-left 1 (dec (- bc (* 32 k))))]
        (assoc hkey k shft)))))


(defn build-basic-types
  "Construct a map of the blade data types.
  The idea is that it is convenient to have
  each blade be a recognized type."
  [basis]
  (into {}
        (map (fn [ba]
         (let [nombre (bb/to-name ba)]
           [nombre (make-type (key-bit basis ba) [ba] nombre)]))
       basis)))



(defmacro generate-type
  "Generates the functions for the specified type.
  Takes a SpaceType object as its argument.

  The functions generated are:
  make : the constructor
  re : reverse of the mv
  inv : inverse of the mv
  ip : inner produced of the mv pair
  op : outer produced of the mv pair
  gp : geometric produced of the mv pair
  sp : scalar produced of the mv and scalar pair
  div : the geometric product of the first mv with the inverse of the second
  add : add the mv pair
  sub : subtract the second mv from the first
  to-array : ?
  to-string : prepare a string suitable for printing
  recast : cast to another type of mv (may be lossy or add defaults)
  dual? : is this the dual representation (or direct 'false')
  "
  [type-spec]
  (let [llave (get type-spec :llave nil)
        coords (bb/to-name (get type-spec :basis nil))
        nombre (get type-spec :nombre nil)
        generated?? (get type-spec :generated??)
        dual? (get type-spec :dual?)

        get-field (mapv #(identity %) coords)
        set-field nil #_(mapv #(identity %) coords) ]
    ;; this should be a macro that defs an object
    ;; that has these functions as members of a map.
    (letfn
       [(make [] :not-implemented)
        (re [mv] :not-implemented)
        (inv [mv]
             (let [rev (re mv), isca (/ 1 (first (gp mv rev)))]
                (gp rev isca)))
        (ip [mv1 mv2] :not-implemented)
        (op [mv1 mv2] :not-implemented)
        (gp [mv1 mv2] :not-implemented)
        (sp [mv1 mv2] :not-implemented)
        (div [mv1 mv2] :not-implemented)
        (add [mv1 mv2] :not-implemented)
        (sub [mv1 mv2] :not-implemented)
        (to-vector [mv] :not-implemented)
        (to-str [mv] :not-implemented)
        (recast [mv] :not-implemented)
        (dual? [mv] :not-implemented) ]

      {:make make, :reverse re, :inverse inv,
       :ip ip, :op op, :gp gp, :sp sp, :div div,
       :add add, :subtract sub,
       :to-vec to-vector, :to-str to-str,
       :cast recast, :dual? dual?} )))

(defn generate-registered-types
  ""
  []
  )

(defn generate-binop
  "Generate a binary operation
  specific for a pair of types.
  e.g. geometric-product of a vector and a bivector.
  (see versor.js::generateBinop)"
  [opname type-name1 type-name2]
  :unimplemented )

(defmacro generate-api
  "Generate the functions for the various types.
  [ref: versor.js:generate]
  "
  [binops type-specs]
  nil)

;;   #_(let [binop-code (for [{op :op, [type1 type2] :types} binops]
;;                      (generate-binop op type1 type2))
;;         type-code (generate-registered-types binops)
;;         type-code-aliases
;;         (into
;;          {} (map #([% (type-code %)])
;;                  (filter
;;                   (fn [tn]
;;                     (let [ty (get type-specs tn {})]
;;                       (cond
;;                        (complement contains? ty :alias) false
;;                        (not= tn (:alias ty)) false
;;                        :else true)))
;;                                type-code))) ]


;;     (loop [function-body {:classes {}, :constructors {}},
;;            tname (first type-code), tname-rest (rest type-code)]
;;           (if (contains? type-code-aliases tname)
;;             (let [fb [(get type-code tname)]


;;   )


(defn create-spec-types
  "Generate the specialized types.
  [ref: versor.js: createTypes]"
  [type-spec] :not-implemented )

(defn ip
  "inner product"
  [a b]
  )

(defn op
  "outer product"
  [a b]
  )

(defn gp
  "geometric product"
  [a b]
  )

(defn sp
  "scalar product"
  [a b]
  )

(defn is-sub-type?
  ""
  [type-name1 type-name2]
  )

(defn build-conformal-values
  "The last two basis in basics are
  the conformal basics.
  [ref: versor.js: buildConformalValues]"
  [metric]
  (let [cnt (count metric)
        no (bit-shift-left 1 (- cnt 2))
        ni (bit-shift-left 1 (dec cnt))]
    {:no no, :ni ni, :ep no, :em ni,
     :eplane (bit-or no ni)} ) )

(defn blade-table
  "
  [ref: versor.js: bladeTable]"
  [basez]
  (into
   {}
   (map #([% {:id (bb/to-name %), :basis %,
              :gp {}, :op {}, :ip {}}])
        basez)))


(defn build-conformal-binop
  "
  [ref: versor.js: buildConformalBinop]"
  [s iv jv]
  )

(defn build-conformal
  "Build the conformal tranlations.
  [ref: versor.js: buildConformal]"
  [metric]
  :not-implemented )

(defn build-euclidean
  "Build the euclidean space tranlations.
  [ref: versor.js::Space.prototype.buildEuclidean]"
  [metric]
  :not-implemented )

(defn populate-subspaces
  "Identify the active subspaces and populate them.
  [ref: versor.js: buildSubspaces]"
  [metric basics]
  (loop [subspaces (mapv #({:id %2, :basis []})
                         metric
                         [:v1 :v2 :v3 :v4 :v5
                          :v6 :v7 :v8 :v9 ])
         basis basics]
    (let [coord (first basis)
          grade-index (dec (bb/grade coord))]

      (recur (update-in subspaces
                        [grade-index :basis]
                        #(conj coord))
             (rest basis)) )))

(defn register-subspaces
  ""
  [subspaces]

  )


(defmacro make-space [props]
  "Define the space, or context for the algebra.

  This function generates other functions for the
  various specialized geometric types.
  [ref: versor.js: Space]"
  (let [metric (get props :metric [1 1 1])
        binops (get props :binops [])
        basics (build-basis metric)
        basic-types (build-basic-types basics)
        [products conformals]
        (if (get props :conformal? false)
          [(build-conformal) (build-conformal-values)]
          [(build-euclidean) nil])
        subspaces (populate-subspaces metric basics)
        _ (register-subspaces subspaces)
        spec-types (create-spec-types (get props :types []))
        spec-api (generate-api binops spec-types) ]

    {:metric metric, :basis basics,
     :types (merge basic-types spec-types),
     :products products, :conformals conformals,
     :subspaces subspaces, :typed-api spec-api}))



(defn metric-product
  ""
  [a b]
  )


;;   Space.prototype.metricProduct = function(a, b)
;;   {
;;   var tmp = product(a, b);
;;   var bs = a&b;
;;   var i = 1;
;;   while(bs != 0)
;;   {
;;   if((bs&1) == 1) tmp.w *= this.metric[i-1];
;;   bs >>= 1;
;;   ++i;
;;   }
;;   return tmp;
;;   }


(defn metric-inner
  ""
  [a b]
  )
(comment "
  Space.prototype.metricInner = function(a, b)
  {
  var tmp = this.metricProduct(a, b);
  var g = grade(b) - grade(a);
  if(grade(a) > grade(b) || grade(tmp.id) != g)
  {
  return blade(0, 0);
  }
  else
  {
  return tmp;
  }
  }
  ")
(defn base-key
  ""
  [bazes]
  )
(comment "
  Space.prototype.basesKey = function(bases)

  {
  var key = this.key();
  for(var i=0; i < bases.length; ++i) {
  var b = bases[i];
  var ty = this.types[basisString(b)];
  for(var k=0; k < ty.key.length; ++k) {
  key[k] = key[k] | ty.key[k];
  }
  }
  return key;
  }
  ")

(defn check-mink
  "Check For presence of Minkowskian Basis"
  [x]
  )
(comment "
  Space.prototype.checkMink = function(x)
  {
  var v = x & this.values.eplane;
  if((v == 0) || (v == this.values.eplane)) {
  return false;
  }
  else {
  return true
  }
  }
  ")

(defn build-euclidean
  ""
  []
  )
(comment "

  Space.prototype.buildEuclidean = function()
  {
  var S = this.bladeTable();
  for(var i=0; i < this.basis.length; ++i)
  {
  var iv = this.basis[i];
  for(var j=0; j < this.basis.length; ++j)
  {
  var jv = this.basis[j];
  var gp = this.metricProduct(iv, jv);
  var op = outer(iv, jv);
  var ip = this.metricInner(iv, jv);
  S[iv].gp[jv] = [gp];
  S[iv].op[jv] = [op];
  S[iv].ip[jv] = [ip];
  S[iv].involute = involute(iv);
  S[iv].reverse = reverse(iv);
  S[iv].conjugate = conjugate(iv);
  }
  }
  return S;
  }

  ")


(defn push-mink
  "Push into e+.e- Minkowskian diagonal metric from
  a null basis (for calculating metric products)"
  [x]
  )
(comment "
  Space.prototype.pushMink = function(x)
  {
  if((x&this.values.no)==this.values.no)
  {
  var t = x^this.values.no;
  return [
  blade(t^this.values.ep, 0.5),
  blade(t^this.values.em, 0.5)
  ];
  }
  else if((x&this.values.ni)==this.values.ni)
  {
  var t = x^this.values.ni;
  return [
  blade(t^this.values.ep, -1),
  blade(t^this.values.em, 1)
  ];
  }
  }
  ")

(defn pop-mink
  "Pop back into degenerate null basis from
  nondegenerate Minkowskian (after xor-ing)"
  [x]
  )
(comment "
  Space.prototype.popMink = function(x)
  {
  if((x&this.values.ep)==this.values.ep)
  {
  var t = x^this.values.ep;
  return [
  blade(t^this.values.no, 1),
  blade(t^this.values.ni, -0.5)
  ];
  }
  else if((x&this.values.em)==this.values.em)
  {
  var t = x^this.values.em;
  return [
  blade(t^this.values.no, 1),
  blade(t^this.values.ni, 0.5)
  ];
  }
  }
  ")

(defn accum-mink
  ""
  [blades]
  )
(comment "
  Space.prototype.accumMink = function(blades)
  {
  var res = [];
  for(var i=0; i < blades.length; ++i)
  {
  var iv = blades[i];
  if(this.checkMink(iv.id))
  {
  var minkBlades = this.popMink(iv.id);
  for(var j=0; j < minkBlades.length; ++j)
  {
  var jv = minkBlades[j];
  jv.w *= iv.w;
  }
  res = res.concat(minkBlades);
  }
  else {
  res.push(iv);
  }
  }
  return res;
  }
  ")


(defn alias-type
  ""
  [oty nty]
  )
(comment "
  Space.prototype.aliasType = function(oty, nty) {
  this.types[nty] = this.types[oty];
  this.types[oty].alias = nty;
  //this.types[oty].alias = nty
  /*delete this.types[oty];
  // rename subspace if necessary
  for(var i=0; i < this.subspaces.length; ++i) {
  var subs = this.subspaces[i];
  if(subs.name == oty) {
  subs.name = nty
  break;
  }
  }

  */
  }
  ")

(defn create-type
  ""
  [bazes iname alias-exists?]
  )
(comment "
  Space.prototype.createType = function(bases, name, aliasExisting)
  {
  var key = this.basesKey(bases);
  for(var tyname in this.types)
  {
  var ty = this.types[tyname];
  if(keyCheck(key, ty.key)) {
  if(aliasExisting) {
  this.aliasType(tyname, name)
  return name;
  }
  else {
  return tyname;
  }
  }
  }
  this.types[name] = type(key, bases, name);
  return name;
  }
  ")

(defn product-list
  ""
  [bases1 bases2 op-name]
  )
(comment "
  Space.prototype.productList = function(bases1, bases2, opname) {
  var tally = [];
  // fetch table pairs of values in types
  var idx = 0
  for(var i=0; i < bases1.length; ++i)
  {
  var iv = bases1[i];
  for(var j=0; j < bases2.length; ++j)
  {
  var jv = bases2[j];
  var prod = this.products[iv][opname][jv]
  for(var k=0; k < prod.length; ++k)
  {

  var instruction = {
  a: i, b: j,
  ida: basisString(iv),
  idb: basisString(jv),
  r: prod[k]
  };
  tally[idx] = instruction;
  idx++;
  }
  }
  }
  var combined = {};
  // check for similar ids in the tally, or if weight is 0
  for(var i=0; i < tally.length; ++i) {
  var instruction = tally[i];
  if(instruction.r.w == 0) continue;
  var b = instruction.r.id;
  if(combined[b]) {
  var instructions = combined[b];
  instructions[instructions.length] = instruction;
  }
  else {
  combined[b] = [instruction];
  }
  }
  return order(combined);
  }

  ")

(defn create-cast
  ""
  [to-name from-name]
  )
(comment "
  Space.prototype.createCast = function(toName, fromName)
  {
  var toTy = this.types[toName]
  var fromTy = this.types[fromName]
  var fromCoordMap = {}
  foreach(fromTy.bases, function(v, i) {
  fromCoordMap[v] = i;
  });
  var ops = [];
  foreach(toTy.bases, function(v, i) {
  var src;
  if(typeof fromCoordMap[v] == \"number\") src = \"b[\"+fromCoordMap[v]+\"]\";
  else src = \"0\"
  ops[i] = \"this[\"+i+\"] = \"+src+\";\"
  });
  var model = {
  classname: classname(toName),
  fromTy:fromName,
  ops: ops.join(\"\n\")
  };
  var code = [
  model.classname+\".prototype._cast.\"+model.fromTy+\" = function(b) {\",
  model.ops,
  \"};\"
  ].join(\"\n\");
  var f = new Function(classname(toName), code);
  f(this.api.classes[toName]);
  }
  ")

(defn generate-unop
  ""
  [op-name ty-name]
  )

;;   Space.prototype.generateUnop = function(opname, tyname)
;;   {
;;    var ty = this.types[tyname]
;;    var coords = basisNames(ty.bases);
;;    var _this = this;
;;    var ops = [];
;;    foreach(ty.bases, function(v, i) {
;;                                      var blade = _this.products[v][opname];
;;                                      ops[i] = ((blade.w>0) ? "" : "-") + "this["+i+"]";
;;                                      });
;;    var model = {
;;                 classname: classname(tyname),
;;                 opname: opname,
;;                 ops: ops.join(", ")
;;                 };
;;    return [
;;            model.classname+".prototype."+model.opname+" = function() {",
;;            "\treturn new "+model.classname+"("+model.ops+");",
;;            "};"
;;            ].join("\n");
;;    }

(defn binop-result-type
  ""
  [op-name type-name1 type-name2]
  )

;;   Space.prototype.binopResultType = function(opname, tyname1, tyname2)
;;   {
;;    var ty1 = this.types[tyname1];
;;    var ty2 = this.types[tyname2];
;;    var op = this.productList(ty1.bases, ty2.bases, opname);
;;    var tynameRes
;;    if(op.blades.length == 0) {
;;                               tynameRes = "s";
;;                               }
;;    else {
;;          tynameRes = this.createType(op.blades, tyname1+tyname2+"_"+opname, false);
;;          }
;;    return tynameRes;
;;    }

(defn generated-binop
  ""
  [op-name type-name1 type-name2]
  )

;;   Space.prototype.generateBinop = function(opname, tyname1, tyname2)
;;   {
;;    var ty1 = this.types[tyname1];
;;    var ty2 = this.types[tyname2];
;;    var op = this.productList(ty1.bases, ty2.bases, opname);
;;    var tynameRes = this.binopResultType(opname, tyname1, tyname2);
;;    var tyRes = this.types[tynameRes];
;;    if(!tyRes) {
;;                console.log("ERROR: gentype " + tyname1+tyname2+"_"+opname, op.blades);
;;                }
;;    else if(this.initialized && !tyRes.generated) {
;;       // TODO: consolidate this with the generate() function
;;       var code = this.generateType(tynameRes);
;;       var functionBody = ["var api = { classes:{}, constructors:{} };"];
;;       functionBody.push([
;;                          code,
;;                          "api.constructors."+tynameRes+" = "+tynameRes+";",
;;                          "api.classes."+tynameRes+" = "+classname(tynameRes)+";"
;;                          ].join("\n")
;;                         );
;;       functionBody.push("return api;");
;;       var f = new Function("space", functionBody.join("\n\n"));
;;       var api = f(this);
;;       for(var name in api.classes) {
;;                                     this.api.classes[name] = api.classes[name];
;;                                     }
;;       for(var name in api.constructors) {
;;                                          this.api.constructors[name] = api.constructors[name];
;;                                          }
;;       }
;;    var ops = [];
;;    if(op.blades.length == 0) {
;;       ops[0] = "0";
;;       }
;;    else {
;;          for(var i=0; i < op.blades.length; ++i) {
;;                  var blade = op.blades[i];
;;                  var inst = op.inst[blade];
;;                  var instbops = [];
;;                  for(var j=0; j < inst.length; ++j) {
;;                          var instop = inst[j];
;;                          var bop = "this["+instop.a+"]*b["+instop.b+"]";
;;                          if(instop.r.w < 0) bop = "-"+bop;
;;                          instbops.push(bop);
;;                          }
;;                          ops.push(instbops.join(" + "));
;;                          }
;;                          }


;;                          var model = {
;;                                       classname1: classname(tyname1),
;;                                       tyname2: tyname2,
;;                                       opname: opname,
;;                                       tynameRes: tynameRes,
;;                                       ops: ops.join(",\n")
;;                                       };
;;                          return [
;;                                  model.classname1+".prototype._"+model.opname+"."+model.tyname2+" = function(b) {",
;;                                  "\treturn "+model.tynameRes+"("+model.ops+");",
;;                                  "};"
;;                                  ].join("\n");
;;                          }


(defn create-binop
  ""
  [op-name type-name1 type-name2]
  )

;;  Space.prototype.createBinop = function(opname, tyname1, tyname2) {
;;    var resultType = this.binopResultType(opname, tyname1, tyname2);
;;    var code = this.generateBinop(opname, tyname1, tyname2);
;;    var f = new Function(classname(tyname1), resultType, code);
;;    f(this.api.classes[tyname1], this.api.constructors[resultType]);
;;    }

(defn create-affine-op
  ""
  [op-name type-name1 type-name2]
  )

;;  Space.prototype.createAffineOp = function(opname, tyname1, tyname2) {
;;     var opsym = opname == "add" ? "+" : "-";
;;     var ty1 = this.types[tyname1];
;;     var ty2 = this.types[tyname2];
;;     var bases1Map = {};
;;     var bases2Map = {};
;;     var basesMap = {};
;;     for(var i=0; i < ty1.bases.length; ++i) {
;;             bases1Map[ ty1.bases[i] ] = i;
;;             basesMap[ ty1.bases[i] ] = ty1.bases[i];
;;             }
;;             for(var i=0; i < ty2.bases.length; ++i) {
;;                     bases2Map[ ty2.bases[i] ] = i;
;;                     basesMap[ ty2.bases[i] ] = ty2.bases[i];
;;                     }
;;                     var bases = [];
;;                     for(var name in basesMap) {
;;                                                bases.push(basesMap[name]);
;;                                                }
;;                     bases.sort(function(a, b) {
;;                                                return (a<b) ? -1 : 1;
;;                                                });
;;                     var ops = [];
;;                     for(var i=0; i < bases.length; ++i) {
;;                             var operands = [];
;;                             var second = false;
;;                             if(bases1Map[ bases[i] ] != undefined) {
;;     operands.push("this["+bases1Map[ bases[i] ]+"]");
;;     }
;;     if(bases2Map[ bases[i] ] != undefined) {
;;     second = true;
;;     operands.push("b["+bases2Map[ bases[i] ]+"]");
;;     }
;;     var op;
;;     if(operands.length == 2) {
;;                               op = operands.join(opsym);
;;                               }
;;     else {
;;           op = operands[0];
;;           if(second && opname == "sub") {
;;  op = opsym+op;
;;  }
;;           }
;;     ops[i] = op;
;;     }
;;     var tynameRes = this.createType(bases, tyname1+tyname2+"_"+opname, false);
;;     var tyRes = this.types[tynameRes];
;;     if(this.initialized && !tyRes.generated) {
;;       // TODO: consolidate this with the generate() function
;;       var code = this.generateType(tynameRes);
;;       var functionBody = ["var api = { classes:{}, constructors:{} };"];
;;       functionBody.push([
;;                          code,
;;                          "api.constructors."+tynameRes+" = "+tynameRes+";",
;;                          "api.classes."+tynameRes+" = "+classname(tynameRes)+";"
;;                          ].join("\n")
;;                         );
;;       functionBody.push("return api;");
;;       var f = new Function("space", functionBody.join("\n\n"));
;;       var api = f(this);
;;       for(var name in api.classes) {
;;           this.api.classes[name] = api.classes[name];
;;           }
;;       for(var name in api.constructors) {
;;          this.api.constructors[name] = api.constructors[name];
;;              }
;;       }
;;       var model = {
;;                    classname1: classname(tyname1),
;;                    tyname2: tyname2,
;;                    opname: opname,
;;                    tynameRes: tynameRes,
;;                    ops: ops.join(", ")
;;                    };
;;       var code = [
;;                   model.classname1+".prototype._"+model.opname+"."+model.tyname2+" = function(b) {",
;;                   "\treturn "+model.tynameRes+"("+model.ops+");",
;;                   "};"
;;                   ].join("\n");
;;       var f = new Function(classname(tyname1), tynameRes, code);
;;       f(this.api.classes[tyname1], this.api.constructors[tynameRes]);
;;       }


