var space = function() {

var foreach = function(t, f) {
	for(var i=0; i < t.length; ++i) f(t[i], i);
}

/*	Data structure representing a blade (coordinate + scale factor)
	b - bitwise representation of coordinate
	wt - scale factor
*/
var blade = function(b, wt) {
	return { id:b, w:wt };
}

var type = function(key, bases, name) {
	return { key:key, bases:bases, name:name, generated:false, dual:false };
}

var classname = function(name) {
	return "_"+name;
}

/*	Calculate the grade of a coordinate
	b - bitwise representation of coordinate
*/
var grade = function(b) {
	var n = 0;
	while(b != 0) {
		if( (b&1)==1 ) n += 1;
		b >>= 1;
	}
	return n;
}

/*	Calculate the sign of the product of two coordinates
	a - bitwise representation of coordinate
	b - bitwise representation of coordinate
*/
var sign = function(a, b) {
	var n = a>>1;
	var sum = 0;
	while(n != 0) {
		sum += grade(n&b)
		n >>= 1;
	}
	if((sum&1)==0) return 1;
	else return -1;
}

/*	Calculate the product between two coordinates
	a - bitwise representation of coordinate
	b - bitwise representation of coordinate
	returns a blade
*/
var product = function(a, b) {
	var res = a^b;
	var s = sign(a, b);
	return blade(res, s);
}

/*	Calculate the outer product between two coordinates
	a - bitwise representation of coordinate
	b - bitwise representation of coordinate
	returns a blade
*/
var outer = function(a, b) {
	if((a&b)!=0) return blade(0, 0);
	else return product(a, b);
}

var involute = function(x) {
	var g = grade(x);
	var n = Math.pow(-1, g);
	return blade(x, n);
}

var reverse = function(x) {
	var g = grade(x);
	var n = Math.pow(-1, (g*(g-1)/2.0));
	return blade(x, n);
}

var conjugate = function(x) {
	var g = grade(x);
	var n = Math.pow(-1, (g*(g+1)/2.0));
	return blade(x, n);
}


/*	Calculate the name of a coordinate
	b - bitwise representation of coordinate
*/
var basisString = function(b) {
	var n=0;
	var res = "";
	while(b != 0) {
		n += 1;
		if((b&1) == 1) res += n;
		b >>= 1;
	}
	if(n > 0) return "e"+res;
	else return "s";
}

var basisBit = function(name) {
	if(name == "s") return 0;

	var x = 0;
	var lastn = parseInt(name.substr(name.length-1));
	for(var i=lastn; i > 0; --i) {
		x <<= 1;
		if(name.search(i) >= 0) x += 1;
	}
	return x;
}

var basisBits = function(bases) {
	var ids = [];
	for(var i=0; i < bases.length; ++i) {
		ids[i] = basisBit(bases[i]);
	}
	return ids;
}

var basisNames = function(ty) {
	ty.sort(function(a, b) {
		return (a<b) ? -1 : 1;
	});

	var coords = [];
	for(var i=0; i < ty.length; ++i) {
		coords[i] = basisString(ty[i])
	}
	return coords;
}


var keyCheck = function(k1, k2) {
	if(k1.length != k2.length) return false;
	for(var i=0; i < k1.length; ++i) {
		if(k1[i] != k2[i]) return false;
	}
	return true;
}

var order = function(c) {
	var tblades = [];
	for(var i in c) {
		tblades[tblades.length] = parseInt(i);
	}
	tblades.sort(function(a, b) {
		return (a<b) ? -1 : 1;
	});
	return {
		blades: tblades,
		inst: c
	};
}

var compress = function(x) {
	var tally = {};

	// collect like terms
	for(var i=0; i < x.length; ++i) {
		var iv = x[i];
		if(tally[iv.id]) {
			tally[iv.id].w += iv.w;
		}
		else {
			tally[iv.id] = blade(iv.id, iv.w);
		}
	}

	var res = [];
	for(var id in tally) {
		var iv = tally[id];
		if(iv.w != 0) {
			res.push(iv);
		}
	}
	return res;
}

var printLines = function(text, from, to) {
	var lines = text.match(/^.*((\r\n|\n|\r)|$)/gm);
	from = from || 0;
	to = to || lines.length;

	for(var i=from; i < to; ++i) {
		console.log((i+1)+"\t"+lines[i].substr(0, lines[i].length-1));
	}
}


/*
Representation of a GA space
Given a set of properties:
e.g.
 {metric:[1, 1, 1, 1, -1],
	types: [
		{ name:"Vec3", bases:["e1", "e2", "e3"] },
		{ name:"Biv3", bases:["e12", "e13", "e23"] },
		{ name:"Tri3", bases:["e123"] },
		{ name:"Pss", bases:["e12345"] },
		{ name:"Rot", bases:["s", "e12", "e13", "e23"] },
		{ name:"Pnt", bases:["e1", "e2", "e3", "e4", "e5"], dual:true },
	],
	conformal:true}

produces this:

 metric(Array): [1 1 1 1 -1]
 basis(Array): <all permutations of 5 bits keyed by sorted grade>
 initialized: true

<spaces>
 subspaces(Array):
   0: <grade 1>
     name: "Vec"
     bases: [2r00001 2r00010 2r00100 2r01000 2r10000]
   1: <grade 2>
     name: "Biv"
     bases: [2r00011 ... 2r11000]
   ...
   4: <grade 5>
     name: "Penta"
     bases: [2r11111]

 types(Object):
   Biv:
     alias: "Par"
     dual: true
     generated: true
     name: "Biv"
     bases(Array): [<all grade 2 blades>]
       3 : 2r00011
       5 : 2r00101
       6 : 2r00110
       9 : 2r01001
       10: 2r01010
       12: 2r01100
       17: 2r10001
       18: 2r10010
       20: 2r10100
       24: 2r11000
     key(Bits): <each blade is assigned a bit>
       2r 001 000 100 100 001 011 001 101 000

 values(Object):
   em: 2r10000
   ep: 2r01000
   eplane: 2r11000
   ni: 2r10000
   no: 2r01000


<std types>
 Vec: function (e1, e2, ..., e5) {
        return new _Vec(e1, e2, ..., e5); }
 Biv: function (e12, e13, ..., e45) {
        return new _Biv(e12, e13, ..., e45); }

<custom types>
 Vec3: function (e1, e2, e3) {
        return new _Vec3(e12, e13, e23); }
 Biv3: function (e12, e13, e23) {
        return new _Biv3(e12, e13, e23); }
 Pnt: function (e1, e2, e3, e4, e5) {
        return new _Vec(e1, e2, e3, e4, e5); }

<std operators>
 Ori: _e4 :: 0: 1, type: "e5"
 Inf: _e5 :: 0: 1, type: "e5"
   [add: cast: gp: ip: op: sub: conjucate: constructor:
    div: inverse: involute: isdual: re: reverse:
    toArray: toString: ]

 Dr(Object):
   elem: function(d) { return C3.Ori.ip(d.involute());}

 Fl(Object):
   dir: function(a) { return a.isdual() ? C3.e5(1).op(a) : C3.e5(-1).ip(a); }
   line: function(p1,p2) { return p1.op(p2.op(C3.Inf); }
   loc: function(a,p) { if (a.isdual())
                             return C3.Pnt(p.op(a).div(a));
                        else return C3.Pnt(p.ip(a).div(a));
   plane: function(pos,drv) { return C3.dual(pos.ip(drv)); }

 Gen(Object):
   dil: function (amt) { return C3.Dil(cosh(amt*0.5), sinh(amt*0.5)); }
   log: function (m) { <complex> }
   mot: function (dll) { <complex> }
   ratio: function (a,b,t) { <complex> }
   ratioDll: function (a,b,t) { <complex> }
   rot: function (b) { <complex> }

 Op(Object):
   bst: function(pp) { <complex boost> }
   pj: function(a,b) { return C3[a.type](a.ip(b).div(b)); }
   rj: function(a,b) { return a.op(b).div(b); }
   trs: function(x,y,z) { return C3.Trs(1, -0.5*x, -0.5*y, -0.58z); }

 Ro(Object):
   car: function(a) { return a.op(C3.Inf); }
   cen: function(a) { <complex boost> }
   circle: function(cen,dir,r) { <complex boost> }
   dls: function(x,y,z,r) { <complex boost> }
   dst: function(a,b) { <complex boost> }
   ipoint: function(x,y,z) { <complex boost> }
   loc: function(a) { <complex boost> }
   point: function(x,y,z) { <complex boost> }
   radius: function(a) { <complex boost> }
   size: function(a) { <complex boost> }
   split: function(pp) { <complex boost> }
   sqd: function(a,b) { <complex boost> }

 Ta(Object):
   dir: function(el) { return C3.Inf.ip(el).op(C3.Inf); }
   loc: function(el) { return C3.Vec(el.div(C3.e5(-1).ip(el))); }


 api(Object):
   classes(Object):
     Biv: function(e12, e13, ..., e45) {
            this.type = "Biv";
            if (typeof e12 == "object")
                 this.cast(e12);
            else { this[0] = e12; ...; this[9] = e45; } }
     e1: function(e1) {
            this.type = "e1";
            if (typeof e1 == "object") this.cast(e1); else { this[0] = e1; } }
   constructors(Object):
     Biv: function(e12, e13, ..., e45) { return new _Biv(e12, e13,...,e45); }
     e12: function(e12) { return new _e12(e12); }


 s: function(el) { return new _s(el); }
 e1: function (el) { return new _e1(el));
 e12: function (el) { return new _e12(el));

 dot: function (el) { return el.ip(el));
 dual: function (el) { return el.gp(C3.Pss(-1));
 duale: function (el) { return el.gp(C3.Tri(-1));


 mag: function(el) { return Math.sqrt(Math.abs(C3.wt(el))); }
 norm: function(el) { var a = C3.rwt(el); if (a<0) return 0; return Math.sqrt(a); }
 rdot: function(el) { return el.ip(el.reverse()); }
 rnorm: function(el) { return <stuff>; }
 runit: function(el) { return <stuff>; }
 rwt: function(el) { return <stuff>; }
 uduale: function(el) { return <stuff>; }
 udual: function(el) { return <stuff>; }
 unit: function(el) { return <stuff>; }
 wt: function(el) { return C3.dot(el, el)[0]; }

 products(Object): [a map of precooked blade products]
   <key>:
      basis: <key> e.g. 2r01100 = 12
      id: <name> e.g. "e34"
      conjugate(Object): id: 0, w: 1
      involute(object): id: 12, w: 1
      reverse(object): id: 12, w: -1
      gp(Object): 0: [id: 12, w: 1], 1: [id: 13, w 1], ...,
                  16: [id: 4, w: -1][id: 28, w: 1], ...
      ip(Object): 20: [id: 0, w: 1], 21: [id: 1, w: 1], ...
      op(Object): 2: [id: 14, w: 1], ...


*/
var Space = function(props) {
  // set some defaults
	props = props || {};
	props.metric = props.metric || [1, 1, 1];
	props.types = props.types || [];
	props.binops = props.binops || [];

	this.metric = props.metric;
  // the basis is a generated vector of bitmaps
	this.basis = this.buildBasis();

	this.types = this.buildTypes();
	if(props.conformal) {
		this.values = this.buildConformalValues();
		this.products = this.buildConformal();
	}
	else {
		this.products = this.buildEuclidean();
	}
	this.subspaces = this.buildSubspaces();
	this.registerSubspaces();
	this.createTypes(props.types);

	this.api = this.generate(props);
	for(var name in this.api.constructors) {
		this[name] = this.api.constructors[name];
	}
	this.initialized = true;
}

/*
  Generates a function body based on the props

  var aip = { classes:{}, constructors:{} };
  code
  api.constructors.<name> = "<name>";
  api.classes.<name> = "_<name>";

  */
Space.prototype.generate = function(props) {
	var binopCode = this.generateBinops(props.binops);
	var typeCode = this.generateRegisteredTypes();
	var typeCodeAliases = {};
	for(var name in typeCode) {
		var ty = this.types[name];
		if(ty.alias && name == ty.alias) {
			typeCodeAliases[name] = typeCode[name];
		}
	}
	var functionBody = ["var api = { classes:{}, constructors:{} };"];
	for(var name in typeCode) {
		if(!typeCodeAliases[name]) {
			var code = typeCode[name];
			functionBody.push([
					code,
					"api.constructors."+name+" = "+name+";",
					"api.classes."+name+" = "+classname(name)+";"
				].join("\n")
			);
			if(this.types[name].alias) {
				var aliasName = this.types[name].alias;
				var aliasCode = typeCodeAliases[aliasName];
				functionBody.push([
						aliasCode,
						"api.constructors."+aliasName+" = "+aliasName+";",
						"api.classes."+aliasName+" = "+classname(aliasName)+";"
					].join("\n")
				);
			}
		}
	}

	functionBody = functionBody.concat(binopCode);
	functionBody.push("return api;");
	var f = new Function("space", functionBody.join("\n\n"));
	return f(this);
}

Space.prototype.metricProduct = function(a, b) {
	var tmp = product(a, b);
	var bs = a&b;
	var i = 1;
	while(bs != 0) {
		if((bs&1) == 1) tmp.w *= this.metric[i-1];
		bs >>= 1;
		++i;
	}
	return tmp;
}

Space.prototype.metricInner = function(a, b) {
	var tmp = this.metricProduct(a, b);
	var g = grade(b) - grade(a);
	if(grade(a) > grade(b) || grade(tmp.id) != g) {
		return blade(0, 0);
	}
	else {
		return tmp;
	}
}

/*	Create a key capable of representing all coordinates in a metric
	b - (optional) bitwise representation of coordinate
*/
Space.prototype.key = function(b) {
	var nkeys = Math.ceil(this.basis.length/32)
	var key = [];
	for(var i=0; i < nkeys; ++i) key[i] = 0;

	if(b != undefined) {
		var k = Math.ceil((b+1)/32);
		var shft = (b+1) - 32*(k-1);
		key[k-1] = 1<<shft-1;
	}

	return key;
}

Space.prototype.basesKey = function(bases) {
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

/*	Construct the bitwise representation for the coordinate basis
*/
Space.prototype.buildBasis = function() {
	// initialize with the scalar
	var basis = [0];
	var basisMap = {0:true};

	// build the coordinate blades (e1, e2, e3, ...)
	var nb = 1;
	for(var i=0; i < this.metric.length; ++i) {
		basis[basis.length] = nb;
		basisMap[nb] = true;
		nb <<= 1;
	}

	// build the bivectors (e12, e23, ...)
	for(var i=0; i < basis.length; ++i) {
		for(var j=0; j < basis.length; ++j) {
			if(i!=j) {
				var r = outer(basis[i], basis[j]);
				if((r.id!=0) && !basisMap[r.id]) {
					basis[basis.length] = r.id;
					basisMap[r.id] = true;
				}
			}
		}
	}

	// sort the basis by grade
	basis.sort(function(a, b) {
		var l = grade(a)-1/a;
		var r = grade(b)-1/b;
		return (l<r) ? -1 : 1;
	});

	return basis;
}

/*
Given a set of basis names
construct a type for each basis.
*/
Space.prototype.buildTypes = function() {
	var types = {};
	for(var i=0; i < this.basis.length; ++i) {
		var b = this.basis[i];
		var key = this.key(b);
		var name = basisString(b);
		types[name] = type(key, [b], name);
	}
	return types;
}

Space.prototype.bladeTable = function() {
	var S = {};
	for(var i=0; i < this.basis.length; ++i) {
		var b = this.basis[i];
		var name = basisString(b);
		S[b] = {
			id: name,
			basis: b,
			gp: {}, op: {}, ip: {}
		};
	}
	return S
}

// Check For presence of Minkowskian Basis
Space.prototype.checkMink = function(x) {
	var v = x & this.values.eplane;
	if((v == 0) || (v == this.values.eplane)) {
		return false;
	}
	else {
		return true
	}
}

Space.prototype.buildEuclidean = function() {
	var S = this.bladeTable();
	for(var i=0; i < this.basis.length; ++i) {
		var iv = this.basis[i];
		for(var j=0; j < this.basis.length; ++j) {
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

// Push into e+.e- Minkowskian diagonal metric from a null basis (for calculating metric products)
Space.prototype.pushMink = function(x) {
	if((x&this.values.no)==this.values.no) {
		var t = x^this.values.no;
		return [
			blade(t^this.values.ep, 0.5),
			blade(t^this.values.em, 0.5)
		];
	}
	else if((x&this.values.ni)==this.values.ni) {
		var t = x^this.values.ni;
		return [
			blade(t^this.values.ep, -1),
			blade(t^this.values.em, 1)
		];
	}
}

// Pop back into degenerate null basis from nondegenerate Minkowskian (after xor-ing)
Space.prototype.popMink = function(x) {
	if((x&this.values.ep)==this.values.ep) {
		var t = x^this.values.ep;
		return [
			blade(t^this.values.no, 1),
			blade(t^this.values.ni, -0.5)
		];
	}
	else if((x&this.values.em)==this.values.em) {
		var t = x^this.values.em;
		return [
			blade(t^this.values.no, 1),
			blade(t^this.values.ni, 0.5)
		];
	}
}

Space.prototype.accumMink = function(blades) {
	var res = [];
	for(var i=0; i < blades.length; ++i) {
		var iv = blades[i];
		if(this.checkMink(iv.id)) {
			var minkBlades = this.popMink(iv.id);
			for(var j=0; j < minkBlades.length; ++j) {
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

Space.prototype.buildConformalBinop = function(S, iv, jv) {
	// get list of blades in minkowskian (diagonal) metric
	var tmpA = this.checkMink(iv) ? this.pushMink(iv) : [blade(iv, 1)];
	var tmpB = this.checkMink(jv) ? this.pushMink(jv) : [blade(jv, 1)];

	var gpTally = [];
	var opTally = [];
	var ipTally = [];
	for(var a=0; a < tmpA.length; ++a) {
		var av = tmpA[a];
		for(var b=0; b < tmpB.length; ++b) {
			var bv = tmpB[b];
			// calculate products in mink metric
			var gp = this.metricProduct(av.id, bv.id);
			var op = outer(av.id, bv.id);
			var ip = this.metricInner(av.id, bv.id);

			// push onto tally stack
			gpTally.push(blade(gp.id, gp.w*av.w*bv.w));
			opTally.push(blade(op.id, op.w*av.w*bv.w));
			ipTally.push(blade(ip.id, ip.w*av.w*bv.w));
		}
	}

	var gpPop = this.accumMink(compress(gpTally));
	var opPop = this.accumMink(compress(opTally));
	var ipPop = this.accumMink(compress(ipTally));

	S[iv].gp[jv] = compress(gpPop);
	S[iv].op[jv] = compress(opPop);
	S[iv].ip[jv] = compress(ipPop);
}

Space.prototype.buildConformalValues = function() {
	var no = 1<<(this.metric.length-2);
	var ni = 1<<(this.metric.length-1);
	return {
		no: no,
		ni: ni,
		ep: no,
		em: ni,
		eplane: no|ni
	}
}

Space.prototype.buildConformal = function() {
	var S = this.bladeTable();
	for(var i=0; i < this.basis.length; ++i) {
		var ib = this.basis[i];
		S[ib].involute = involute(ib)
		S[ib].reverse = reverse(ib)
		S[ib].conjugate = conjugate(ib)

		for(var j=0; j < this.basis.length; ++j) {
			var jb = this.basis[j];
			this.buildConformalBinop(S, ib, jb)
		}
	}
	return S
}

var _subspaceNames = ["Vec", "Biv", "Tri", "Quad", "Penta", "Hexa", "Hepta", "Octo"];
Space.prototype.buildSubspaces = function() {
	var subspaces = [];
	for(var i=0; i < this.metric.length; ++i) {
		subspaces[i] = {
			name: _subspaceNames[i],
			bases: []
		};
	}

	for(var i=0; i < this.basis.length; ++i) {
		var b = this.basis[i];
		var g = grade(b);
		if(g > 0) {
			var bases = subspaces[g-1].bases;
			bases[bases.length] = b;
		}
	}
	return subspaces;
}

Space.prototype.registerSubspaces = function() {
	for(var i=0; i < this.subspaces.length; ++i) {
		var iv = this.subspaces[i];
		this.types[iv.name] = type(this.basesKey(iv.bases), iv.bases, iv.name);
	}
}

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

Space.prototype.createType = function(bases, name, aliasExisting) {
	var key = this.basesKey(bases);
	for(var tyname in this.types) {
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

Space.prototype.productList = function(bases1, bases2, opname) {
	var tally = [];

	// fetch table pairs of values in types
	var idx = 0
	for(var i=0; i < bases1.length; ++i) {
		var iv = bases1[i];
		for(var j=0; j < bases2.length; ++j) {
			var jv = bases2[j];

			var prod = this.products[iv][opname][jv]
			for(var k=0; k < prod.length; ++k) {
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

/*
  Given a name 'foo' produces a string
  containing code.

  var
*/
Space.prototype.generateType = function(name) {
	var ty = this.types[name];
	var coords = basisNames(ty.bases);

	var getfields = [];
	var setfields = [];
	foreach(coords, function(v, i) {
		getfields[i] = "this["+i+"]";
		setfields[i] = getfields[i]+" = "+v;
	});

	var model = {
		name: name,
		classname: classname(name),
		parameters: coords.join(", "),
		coords: coords,
		getfields: getfields.join(", "),
		setfields: setfields,
		isdual: ty.dual,
	};
	var create = [
		"var "+model.name+" = function("+model.parameters+") {",
		"\treturn new "+model.classname+"("+model.parameters+");",
		"}"
	].join("\n");

	var def = [
		"var "+model.classname+" = function("+model.parameters+") {",
		"\tthis.type = \""+model.name+"\";",
		"\tif(typeof "+coords[0]+" == \"object\") {",
		"\t\tthis.cast("+coords[0]+");",
		"\t}",
		"\telse {",
		model.setfields.join("\n"),
		"\t}",
		"};",
		"",
		model.classname+".prototype._cast = {};",
		model.classname+".prototype._ip = {};",
		model.classname+".prototype._op = {};",
		model.classname+".prototype._gp = {};",
		model.classname+".prototype._add = {};",
		model.classname+".prototype._sub = {};",
		"",
		model.classname+".prototype.inverse = function() {",
		"\tvar rev = this.reverse();",
		"\tvar sca = this.gp(rev)[0];",
		"\treturn rev.gp(1/sca);",
		"}",
		"",
		model.classname+".prototype.ip = function(b) {",
		"\tif(!this._ip[b.type]) {",
		"\t\tspace.createBinop('ip', this.type, b.type);",
		"\t}",
		"\treturn this._ip[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.op = function(b) {",
		"\tif(!this._op[b.type]) {",
		"\t\tspace.createBinop('op', this.type, b.type);",
		"\t}",
		"\treturn this._op[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.gp = function(b) {",
		"\tif(typeof b == \"number\") {",
		"\t\tb = space.s(b);",
		"\t}",
		"\tif(!this._gp[b.type]) {",
		"\t\tspace.createBinop('gp', this.type, b.type);",
		"\t}",
		"\treturn this._gp[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.sp = function(b) {",
		//"\tvar v = b.inverse().gp(this).gp(b);",
		"\tvar v = b.gp(this).gp(b.reverse());",
		"\treturn "+"new this.__proto__.constructor(v);",
		"}",
		"",
		model.classname+".prototype.re = function(b) {",
		"\tvar v = b.gp(this.involute()).gp(b.reverse());",
		"\treturn "+"new this.__proto__.constructor(v);",
		"}",
		"",
		model.classname+".prototype.div = function(b) {",
		"\treturn this.gp(b.inverse());",
		"}",
		"",
		model.classname+".prototype.add = function(b) {",
		"\tif(typeof b == \"number\") {",
		"\t\tb = space.s(b);",
		"\t}",
		"\tif(!this._add[b.type]) {",
		"\t\tspace.createAffineOp('add', this.type, b.type);",
		"\t}",
		"\treturn this._add[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.sub = function(b) {",
		"\tif(typeof b == \"number\") {",
		"\t\tb = space.s(b);",
		"\t}",
		"\tif(!this._sub[b.type]) {",
		"\t\tspace.createAffineOp('sub', this.type, b.type);",
		"\t}",
		"\treturn this._sub[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.toArray = function() {",
		"\treturn ["+model.getfields+"];",
		"}",
		"",
		model.classname+".prototype.toString = function() {",
		"\treturn \""+model.name+"(\" + this.toArray().join(\", \") + \")\";",
		"}",
		model.classname+".prototype.cast = function(b) {",
		"\tif(!this._cast[b.type]) {",
		"\t\tspace.createCast(this.type, b.type);",
		"\t}",
		"\treturn this._cast[b.type].call(this, b);",
		"}",
		"",
		model.classname+".prototype.isdual = function() {",
		"\treturn "+model.isdual+";",
		"}",
		"",
		].join("\n");

	var code = [def];

	code.push(this.generateUnop("reverse", name));
	code.push(this.generateUnop("involute", name));
	code.push(this.generateUnop("conjugate", name));
	code.push(create);

	ty.generated = true;

	return code.join("\n\n");
}

Space.prototype.createCast = function(toName, fromName) {
	var toTy = this.types[toName]
	var fromTy = this.types[fromName]

	var fromCoordMap = {}
	foreach(fromTy.bases, function(v, i) {
		fromCoordMap[v] = i;
	});

	var ops = [];
	foreach(toTy.bases, function(v, i) {
		var src;
		if(typeof fromCoordMap[v] == "number") src = "b["+fromCoordMap[v]+"]";
		else src = "0"
		ops[i] = "this["+i+"] = "+src+";"
	});

	var model = {
		classname: classname(toName),
		fromTy:fromName,
		ops: ops.join("\n")
	};


	var code = [
		model.classname+".prototype._cast."+model.fromTy+" = function(b) {",
		model.ops,
		"};"
	].join("\n");

	var f = new Function(classname(toName), code);
	f(this.api.classes[toName]);
}

Space.prototype.generateUnop = function(opname, tyname) {
	var ty = this.types[tyname]
	var coords = basisNames(ty.bases);

	var _this = this;
	var ops = [];
	foreach(ty.bases, function(v, i) {
		var blade = _this.products[v][opname];
		ops[i] = ((blade.w>0) ? "" : "-") + "this["+i+"]";
	});

	var model = {
		classname: classname(tyname),
		opname: opname,
		ops: ops.join(", ")
	};
	return [
		model.classname+".prototype."+model.opname+" = function() {",
		"\treturn new "+model.classname+"("+model.ops+");",
		"};"
	].join("\n");
}

Space.prototype.binopResultType = function(opname, tyname1, tyname2) {
	var ty1 = this.types[tyname1];
	var ty2 = this.types[tyname2];

	var op = this.productList(ty1.bases, ty2.bases, opname);
	var tynameRes
	if(op.blades.length == 0) {
		tynameRes = "s";
	}
	else {
	 	tynameRes = this.createType(op.blades, tyname1+tyname2+"_"+opname, false);
	}
	return tynameRes;
}

Space.prototype.generateBinop = function(opname, tyname1, tyname2) {
	var ty1 = this.types[tyname1];
	var ty2 = this.types[tyname2];

	var op = this.productList(ty1.bases, ty2.bases, opname);
	var tynameRes = this.binopResultType(opname, tyname1, tyname2);

	var tyRes = this.types[tynameRes];
	if(!tyRes) {
		console.log("ERROR: gentype " + tyname1+tyname2+"_"+opname, op.blades);
	}
	else if(this.initialized && !tyRes.generated) {
		// TODO: consolidate this with the generate() function
		var code = this.generateType(tynameRes);
		var functionBody = ["var api = { classes:{}, constructors:{} };"];
		functionBody.push([
				code,
				"api.constructors."+tynameRes+" = "+tynameRes+";",
				"api.classes."+tynameRes+" = "+classname(tynameRes)+";"
			].join("\n")
		);

		functionBody.push("return api;");
		var f = new Function("space", functionBody.join("\n\n"));
		var api = f(this);
		for(var name in api.classes) {
			this.api.classes[name] = api.classes[name];
		}
		for(var name in api.constructors) {
			this.api.constructors[name] = api.constructors[name];
		}
	}

	var ops = [];
	if(op.blades.length == 0) {
		ops[0] = "0";
	}
	else {
		for(var i=0; i < op.blades.length; ++i) {
			var blade = op.blades[i];
			var inst = op.inst[blade];
			var instbops = [];
			for(var j=0; j < inst.length; ++j) {
				var instop = inst[j];
				var bop = "this["+instop.a+"]*b["+instop.b+"]";
				if(instop.r.w < 0) bop = "-"+bop;
				instbops.push(bop);
			}
			ops.push(instbops.join(" + "));
		}
	}

	var model = {
		classname1: classname(tyname1),
		tyname2: tyname2,
		opname: opname,
		tynameRes: tynameRes,
		ops: ops.join(",\n")
	};
	return [
		model.classname1+".prototype._"+model.opname+"."+model.tyname2+" = function(b) {",
		"\treturn "+model.tynameRes+"("+model.ops+");",
		"};"
	].join("\n");
}

Space.prototype.createBinop = function(opname, tyname1, tyname2) {
	var resultType = this.binopResultType(opname, tyname1, tyname2);
	var code = this.generateBinop(opname, tyname1, tyname2);
	var f = new Function(classname(tyname1), resultType, code);
	f(this.api.classes[tyname1], this.api.constructors[resultType]);
}

Space.prototype.createAffineOp = function(opname, tyname1, tyname2) {
	var opsym = opname == "add" ? "+" : "-";

	var ty1 = this.types[tyname1];
	var ty2 = this.types[tyname2];
	var bases1Map = {};
	var bases2Map = {};
	var basesMap = {};
	for(var i=0; i < ty1.bases.length; ++i) {
		bases1Map[ ty1.bases[i] ] = i;
		basesMap[ ty1.bases[i] ] = ty1.bases[i];
	}
	for(var i=0; i < ty2.bases.length; ++i) {
		bases2Map[ ty2.bases[i] ] = i;
		basesMap[ ty2.bases[i] ] = ty2.bases[i];
	}
	var bases = [];
	for(var name in basesMap) {
		bases.push(basesMap[name]);
	}
	bases.sort(function(a, b) {
		return (a<b) ? -1 : 1;
	});
	var ops = [];

	for(var i=0; i < bases.length; ++i) {
		var operands = [];
		var second = false;
		if(bases1Map[ bases[i] ] != undefined) {
			operands.push("this["+bases1Map[ bases[i] ]+"]");
		}
		if(bases2Map[ bases[i] ] != undefined) {
			second = true;
			operands.push("b["+bases2Map[ bases[i] ]+"]");
		}
		var op;
		if(operands.length == 2) {
			op = operands.join(opsym);
		}
		else {
			op = operands[0];
			if(second && opname == "sub") {
				op = opsym+op;
			}
		}
		ops[i] = op;
	}

	var tynameRes = this.createType(bases, tyname1+tyname2+"_"+opname, false);
	var tyRes = this.types[tynameRes];
	if(this.initialized && !tyRes.generated) {
		// TODO: consolidate this with the generate() function
		var code = this.generateType(tynameRes);
		var functionBody = ["var api = { classes:{}, constructors:{} };"];
		functionBody.push([
				code,
				"api.constructors."+tynameRes+" = "+tynameRes+";",
				"api.classes."+tynameRes+" = "+classname(tynameRes)+";"
			].join("\n")
		);

		functionBody.push("return api;");
		var f = new Function("space", functionBody.join("\n\n"));
		var api = f(this);
		for(var name in api.classes) {
			this.api.classes[name] = api.classes[name];
		}
		for(var name in api.constructors) {
			this.api.constructors[name] = api.constructors[name];
		}
	}

	var model = {
		classname1: classname(tyname1),
		tyname2: tyname2,
		opname: opname,
		tynameRes: tynameRes,
		ops: ops.join(", ")
	};

	var code = [
		model.classname1+".prototype._"+model.opname+"."+model.tyname2+" = function(b) {",
		"\treturn "+model.tynameRes+"("+model.ops+");",
		"};"
	].join("\n");

	var f = new Function(classname(tyname1), tynameRes, code);
	f(this.api.classes[tyname1], this.api.constructors[tynameRes]);
}

Space.prototype.generateRegisteredTypes = function() {
	var code = {};
	for(var name in this.types) {
		var ty = this.types[name];
		if(!ty.generated) {
			code[name] = this.generateType(name);
		}
		else {
			code[name] = [
				"var "+classname(name)+" = "+classname(ty.name)+";",
				"var "+name+" = "+ty.name+";"
			].join("\n");
		}
	}
	return code;
}

Space.prototype.generateBinops = function(binops) {
	var _this = this;
	var code = [];
	foreach(binops, function(v, i) {
		code[i] = _this.generateBinop(v.op, v.types[0], v.types[1]);
	});
	return code;
}

Space.prototype.createTypes = function(types) {
	for(var i=0; i < types.length; ++i) {
		var ty = types[i];
		var name = this.createType(basisBits(ty.bases), ty.name, true);
		if(ty.dual != undefined) {
			this.types[name].dual = ty.dual;
		}
	}
}

Space.prototype.ip = function(a, b) {
	return a.ip(b);
}

Space.prototype.op = function(a, b) {
	return a.op(b);
}

Space.prototype.gp = function(a, b) {
	if(typeof a == "number") {
		a = this.s(a);
	}
	return a.gp(b);
}

Space.prototype.sp = function(a, b) {
	return a.sp(b);
}

Space.prototype.isSubType = function(tyname1, tyname2) {
	var bases1 = this.types[tyname1].bases;
	var bases2 = this.types[tyname2].bases;

	var bases1Map = {};
	for(var i=0; i < bases1.length; ++i) {
		bases1Map[ bases1[i] ] = true;
	}
	for(var i=0; i < bases2.length; ++i) {
		if(!bases1Map[ bases2[i] ]) {
			return false;
		}
	}
	return true;
}

return {
	create: function(props) {
		return new Space(props);
	}
};
}();

