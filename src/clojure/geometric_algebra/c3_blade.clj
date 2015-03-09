(ns geometric-algebra.c3-blade)

(def product-table
  "This table provides a summary of the geometric products of unit blades.
  The products are generally multi-vectors which are represented as
  a vector of terms, each term has a sign, magnitude and blade.
  The multivector contains the inner and outer products of the blades.
  An empty multivector indicates that both inner and outer products are 0.
  In most cases the multi-vector contains only the inner or outer product
  the other product being 0.
  The magnitude in all of these cases is 1."
  {:key-bitmap [2r00000 2r00001 2r00010 2r00100 2r01000 2r10000 2r00011 2r00101 2r00110 2r01001 2r10001 2r01010 2r10010 2r01100 2r10100 2r11000 2r00111 2r01011 2r10011 2r01101 2r10101 2r11001 2r01110 2r10110 2r11010 2r11100 2r01111 2r10111 2r11011 2r11101 2r11110 2r11111]
   :key-alias [:s :e1 :e2 :e3 :ni :no :e12 :e13 :e23 :e1ni :e1no :e2ni :e2no :e3ni :e3no :enio :e123 :e12ni :e12no :e13ni :e13no :e1nio :e23ni :e23no :e2nio :e3nio :e123ni :e123no :e12nio :e13nio :e23nio :e123nio]
   :key-label [:e---n-- :e1--n-- :e-2-n-- :e--3n-- :e---ni- :e---n-o :e12-n-- :e1-3n-- :e-23n-- :e1--ni- :e1--n-o :e-2-ni- :e-2-n-o :e--3ni- :e--3n-o :e---nio :e123n-- :e12-ni- :e12-n-o :e1-3ni- :e1-3n-o :e1--nio :e-23ni- :e-23n-o :e-2-nio :e--3nio :e123ni- :e123n-o :e12-nio :e1-3nio :e-23nio :e123nio]
   :product
   [
    [2r00000 :e---n-- [[+1 :e---n--]][[+1 :e1--n--]][[+1 :e-2-n--]][[+1 :e--3n--]][[+1 :e---ni-]][[+1 :e---n-o]][[+1 :e12-n--]][[+1 :e1-3n--]][[+1 :e-23n--]][[+1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---nio]][[+1 :e123n--]][[+1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--nio]][[+1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-nio]][[+1 :e--3nio]][[+1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-nio]][[+1 :e1-3nio]][[+1 :e-23nio]][[+1 :e123nio]]]
    [2r00001 :e1--n-- [[+1 :e1--n--]][[+1 :e---n--]][[+1 :e12-n--]][[+1 :e1-3n--]][[+1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e-2-n--]][[+1 :e--3n--]][[+1 :e123n--]][[+1 :e---ni-]][[+1 :e---n-o]][[+1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--nio]][[+1 :e-23n--]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---nio]][[+1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-nio]][[+1 :e1-3nio]][[+1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-nio]][[+1 :e--3nio]][[+1 :e123nio]][[+1 :e-23nio]]]
    [2r00010 :e-2-n-- [[+1 :e-2-n--]][[-1 :e12-n--]][[+1 :e---n--]][[+1 :e-23n--]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[-1 :e1--n--]][[-1 :e123n--]][[+1 :e--3n--]][[-1 :e12-ni-]][[-1 :e12-n-o]][[+1 :e---ni-]][[+1 :e---n-o]][[+1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-nio]][[-1 :e1-3n--]][[-1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e123ni-]][[-1 :e123n-o]][[-1 :e12-nio]][[+1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---nio]][[+1 :e-23nio]][[-1 :e1-3ni-]][[-1 :e1-3n-o]][[-1 :e1--nio]][[-1 :e123nio]][[+1 :e--3nio]][[-1 :e1-3nio]]]
    [2r00100 :e--3n-- [[+1 :e--3n--]][[-1 :e1-3n--]][[-1 :e-23n--]][[+1 :e---n--]][[+1 :e--3ni-]][[+1 :e--3n-o]][[-1 :e123n--]][[-1 :e1--n--]][[-1 :e-2-n--]][[-1 :e1-3ni-]][[-1 :e1-3n-o]][[-1 :e-23ni-]][][[+1 :e---ni-]][[+1 :e---n-o]][[+1 :e--3nio]][[+1 :e12-n--]][[+1 :e123ni-]][[+1 :e123n-o]][[-1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3nio]][[-1 :e-2-ni-]][[-1 :e-2-n-o]][[-1 :e-23nio]][[+1 :e---nio]][[+1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123nio]][[-1 :e1--nio]][[-1 :e-2-nio]][[+1 :e12-nio]]]
    [2r01000 :e---ni- [[+1 :e---ni-]][[-1 :e1--ni-]][[-1 :e-2-ni-]][[-1 :e--3ni-]][][[-1 :e---n--] [+1 :e---nio]][[+1 :e12-ni-]][[+1 :e1-3ni-]][[+1 :e-23ni-]][][[+1 :e1--n--] [-1 :e1--nio]][][[+1 :e-2-n--] [-1 :e-2-nio]][][[+1 :e--3n--] [-1 :e--3nio]][[+1 :e---ni-]][[-1 :e123ni-]][][[-1 :e12-n--] [+1 :e12-nio]][][[-1 :e1-3n--] [+1 :e1-3nio]][[-1 :e1--ni-]][][[-1 :e-23n--] [+1 :e-23nio]][[-1 :e-2-ni-]][[-1 :e--3ni-]][][[+1 :e123n--] [-1 :e123nio]][[+1 :e12-ni-]][[+1 :e1-3ni-]][[+1 :e-23ni-]][[-1 :e123ni-]]]
    [2r10000 :e---n-o [[+1 :e---n-o]][[-1 :e1--n-o]][[-1 :e-2-n-o]][[-1 :e--3n-o]][[-1 :e---n--] [-1 :e---nio]][][[+1 :e12-n-o]][[+1 :e1-3n-o]][[+1 :e-23n-o]][[+1 :e1--n--] [+1 :e1--nio]][][[+1 :e-2-n--] [+1 :e-2-nio]][][[+1 :e--3n--] [+1 :e--3nio]][][[-1 :e---n-o]][[-1 :e123n-o]][[-1 :e12-n--] [-1 :e12-nio]][][[-1 :e1-3n--] [-1 :e1-3nio]][][[-1 :e1--n-o]][[-1 :e-23n--] [-1 :e-23nio]][][[+1 :e-2-n-o]][[+1 :e--3n-o]][[+1 :e123n--] [+1 :e123nio]][][[-1 :e12-n-o]][[-1 :e-23n-o]][[-1 :e-23n-o]][[+1 :e123n-o]] ]
    [2r00011 :e12-n-- [[+1 :e12-n--]][[-1 :e-2-n--]][[+1 :e1--n--]][[+1 :e123n--]][[+1 :e12-ni-]][[+1 :e12-n-o]][[-1 :e---n--]][[-1 :e-23n--]][[+1 :e1-3n--]][[-1 :e-2-ni-]][[-1 :e-2-n-o]][[+1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-nio]][[-1 :e--3n--]][[-1 :e---ni-]][[-1 :e---n-o]][[-1 :e-23ni-]][[-1 :e-23n-o]][[-1 :e-2-nio]][[+1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--nio]][[+1 :e123nio]][[-1 :e--3ni-]][[-1 :e--3n-o]][[-1 :e---nio]][[-1 :e-23nio]][[+1 :e1-3nio]][[-1 :e--3nio]]]
    [2r00101 :e1-3n-- [[+1 :e1-3n--]][[-1 :e--3n--]][[-1 :e123n--]][[+1 :e1--n--]][[+1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e-23n--]][[-1 :e---n--]][[-1 :e12-n--]][[-1 :e--3ni-]][[-1 :e--3n-o]][[-1 :e123ni-]][[-1 :e123n-o]][[+1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e1-3nio]][[+1 :e-2-n--]][[+1 :e-23ni-]][[+1 :e-23ni-]][[-1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3nio]][[-1 :e12-ni-]][[-1 :e12-n-o]][[-1 :e123nio]][[+1 :e1--nio]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23nio]][[+1 :e---nio]][[-1 :e12-nio]][[+1 :e-2-nio]]]
    [2r00110 :e-23n-- [[+1 :e-23n--]][[+1 :e123n--]][[-1 :e--3n--]][[+1 :e-2-n--]][[-1 :e-23ni-]][[+1 :e-23n-o]][[-1 :e1-3n--]][[+1 :e12-n--]][[-1 :e---n--]][[+1 :e123ni-]][[+1 :e123n-o]][[-1 :e--3ni-]][[-1 :e--3n-o]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23nio]][[-1 :e1--n--]][[-1 :e1-3ni-]][[-1 :e1-3n-o]][[+1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123nio]][[-1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3nio]][[+1 :e-2-nio]][[-1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3nio]][[+1 :e12-nio]][[-1 :e---nio]][[-1 :e1--nio]]]
    [2r01001 :e1--ni- [[+1 :e1--ni-]][[-1 :e---ni-]][[-1 :e12-ni-]][[-1 :e1-3ni-]][][[-1 :e1--n--] [+1 :e1--nio]][[+1 :e-2-ni-]][[+1 :e--3ni-]][[+1 :e123ni-]][][[+1 :e---n--] [-1 :e---nio]][][[+1 :e12-n--] [-1 :e12-nio]][][[+1 :e1-3n--] [-1 :e1-3nio]][[+1 :e1--ni-]][[-1 :e-23ni-]][][[-1 :e-2-n--] [+1 :e-2-nio]][][[-1 :e--3n--] [+1 :e--3nio]][[-1 :e---ni-]][][[-1 :e123n--] [+1 :e123nio]][[-1 :e12-ni-]][[-1 :e1-3ni-]][][[+1 :e-23n--] [-1 :e-23nio]][[+1 :e-2-ni-]][[+1 :e--3ni-]][[+1 :e123ni-]][[-1 :e-23ni-]]]
    [2r10001 :e1--n-o [[+1 :e1--n-o]][[-1 :e---n-o]][[-1 :e12-n-o]][[-1 :e1-3n-o]][[-1 :e1--n--] [-1 :e1--nio]][][[+1 :e-2-n-o]][[+1 :e--3n-o]][[+1 :e123n-o]][[+1 :e---n--] [+1 :e---nio]][][[+1 :e12-n--] [+1 :e12-nio]][][[+1 :e1-3n--] [+1 :e1-3nio]][][[-1 :e1--n-o]][[-1 :e-23n-o]][[-1 :e-2-n--] [-1 :e-2-nio]][][[-1 :e--3n--] [-1 :e--3nio]][][[+1 :e---n-o]][[-1 :e123n--] [-1 :e123nio]][][[+1 :e12-n-o]][[+1 :e1-3n-o]][[+1 :e-23n--] [+1 :e-23nio]][][[-1 :e-2-n-o]][[-1 :e--3n-o]][[-1 :e123n-o]][[+1 :e-23n-o]]]
    [2r01010 :e-2-ni- [[+1 :e-2-ni-]][[+1 :e12-ni-]][[-1 :e---ni-]][[-1 :e-23ni-]][][[-1 :e-2-n--] [+1 :e-2-nio]][[-1 :e1--ni-]][[-1 :e123ni-]][[+1 :e--3ni-]][][[-1 :e12-n--] [+1 :e12-nio]][][[+1 :e---n--] [-1 :e---nio]][][[+1 :e-23n--] [-1 :e-23nio]][[+1 :e-2-ni-]][[+1 :e1-3ni-]][][[+1 :e1--n--] [-1 :e1--nio]][][[+1 :e123n--] [-1 :e123nio]][[+1 :e12-ni-]][][[-1 :e--3n--] [+1 :e--3nio]][[-1 :e---ni-]][[-1 :e-23ni-]][][[-1 :e1-3n--] [+1 :e1-3nio]][[-1 :e1--ni-]][[-1 :e123ni-]][[+1 :e--3ni-]][[+1 :e1-3ni-]]]
    [2r10010 :e-2-n-o [[+1 :e-2-n-o]][[+1 :e12-n-o]][[-1 :e---n-o]][[-1 :e-23n-o]][[-1 :e-2-n--] [-1 :e-2-nio]][][[-1 :e1--n-o]][[-1 :e123n-o]][[+1 :e--3n-o]][[-1 :e1-3n--] [-1 :e1-3nio]][][[+1 :e---n--] [+1 :e---nio]][][[+1 :e-23n--] [+1 :e-23nio]][][[-1 :e-2-n-o]][[+1 :e1-3n-o]][[+1 :e1--n--] [+1 :e1--nio]][][[+1 :e123n--] [+1 :e123nio]][][[-1 :e12-n-o]][[-1 :e--3n--] [-1 :e--3nio]][][[+1 :e---n-o]][[+1 :e-23n-o]][[-1 :e1-3n--] [-1 :e1-3nio]][][[+1 :e1--n-o]][[+1 :e123n-o]][[-1 :e--3n-o]][[-1 :e1-3n-o]]]
    [2r01100 :e--3ni- [[+1 :e--3ni-]][[+1 :e1-3ni-]][[+1 :e-23ni-]][[-1 :e---ni-]][][[-1 :e--3n--] [+1 :e--3nio]][[+1 :e123ni-]][[-1 :e1--ni-]][[-1 :e-2-ni-]][][[-1 :e1-3n--] [+1 :e1-3nio]][][[-1 :e-23n--] [+1 :e-23nio]][][[+1 :e---n--] [-1 :e---nio]][[+1 :e--3ni-]][[-1 :e12-ni-]][][[-1 :e123n--] [+1 :e123nio]][][[+1 :e1--n--] [-1 :e1--nio]][[+1 :e1-3ni-]][][[+1 :e-2-n--] [-1 :e-2-nio]][[+1 :e-23ni-]][[-1 :e---ni-]][][[+1 :e12-n--] [-1 :e12-nio]][[+1 :e123ni-]][[-1 :e1--ni-]][[-1 :e-2-ni-]][[-1 :e12-ni-]]]
    [2r10100 :e--3n-o [[+1 :e--3n-o]][[+1 :e1-3n-o]][[+1 :e-23n-o]][[-1 :e---n-o]][[-1 :e--3n--] [-1 :e--3nio]][][[+1 :e123n-o]][[-1 :e1--n-o]][[-1 :e-2-n-o]][[-1 :e1-3n--] [-1 :e1-3nio]][][[-1 :e-23n--] [-1 :e-23nio]][][[+1 :e---n--] [+1 :e---nio]][][[-1 :e--3n-o]][[-1 :e12-n-o]][[-1 :e123n--] [-1 :e123nio]][][[+1 :e1--n--] [+1 :e1--nio]][][[-1 :e1-3n-o]][[+1 :e-2-n--] [+1 :e-2-nio]][][[-1 :e-23n-o]][[+1 :e---n-o]][[+1 :e12-n--] [+1 :e12-nio]][][[-1 :e123n-o]][[+1 :e1--n-o]][[+1 :e-2-n-o]][[+1 :e12-nio]]]
    [2r11000 :e---nio [[+1 :e---nio]][[+1 :e1--nio]][[+1 :e-2-nio]][[+1 :e--3nio]][[-1 :e---ni-]][[+1 :e---n-o]][[+1 :e12-nio]][[+1 :e1-3nio]][[+1 :e-23nio]][[-1 :e1--ni-]][[+1 :e1--n-o]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[-1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---n--]][[+1 :e123nio]][[-1 :e12-ni-]][[+1 :e12-n-o]][[-1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--n--]][[-1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-n--]][[+1 :e--3n--]][[-1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-n--]][[+1 :e1-3n--]][[+1 :e-23n--]][[+1 :e123n--]]]
    [2r00111 :e123n-- [[+1 :e123n--]][[+1 :e-23n--]][[-1 :e-23n--]][[+1 :e12-n--]][[+1 :e123ni-]][[+1 :e123n-o]][[-1 :e--3n--]][[+1 :e-2-n--]][[-1 :e1--n--]][[+1 :e-23ni-]][[+1 :e-23n-o]][[-1 :e1-3ni-]][[-1 :e1-3n-o]][[+1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123nio]][[-1 :e---n--]][[-1 :e--3ni-]][[-1 :e--3n-o]][[+1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23nio]][[-1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3nio]][[+1 :e12-nio]][[-1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3nio]][[+1 :e-2-nio]][[-1 :e1--nio]][[-1 :e---nio]]]
    [2r01011 :e12-ni- [[+1 :e12-ni-]][[+1 :e-2-ni-]][[-1 :e1--ni-]][[-1 :e123ni-]][][[-1 :e12-n--] [+1 :e12-nio]][[-1 :e---ni-]][[-1 :e-23ni-]][[+1 :e1-3ni-]][ ][[-1 :e-2-n--] [-1 :e-2-nio]][ ][[+1 :e1--n--] [-1 :e1--nio]][ ][[+1 :e123n--] [-1 :e123nio]][[+1 :e12-ni-]][[+1 :e--3ni-]][][[+1 :e---n--] [-1 :e---nio]][][[+1 :e-23n--] [-1 :e-23nio]][[+1 :e-2-ni-]][][[-1 :e1-3n--] [+1 :e1-3nio]][[-1 :e1--ni-]][[-1 :e123ni-]][][[-1 :e--3n--] [+1 :e--3nio]][[-1 :e---ni-]][[-1 :e-23ni-]][[+1 :e1-3ni-]][[+1 :e--3ni-]]]
    [2r10011 :e12-n-o [[+1 :e12-n-o]][[+1 :e-2-n-o]][[-1 :e1--n-o]][[-1 :e123n-o]][[-1 :e12-n--] [-1 :e12-nio]][][[-1 :e---n-o]][[-1 :e-23n-o]][[+1 :e1-3n-o]][[-1 :e-2-n--] [-1 :e-2-nio]][ ][[+1 :e1--n--] [+1 :e1--nio]][ ][[+1 :e123n--] [+1 :e123nio]][ ][[-1 :e12-n-o]][[+1 :e--3n-o]][[+1 :e---n--] [+1 :e---nio]][][[+1 :e-23n--] [+1 :e-23nio]][][[-1 :e-2-n-o]][[-1 :e1-3n--] [-1 :e1-3nio]][][[+1 :e1--n-o]][[+1 :e123n-o]][[-1 :e--3n--] [-1 :e--3nio]][][[+1 :e---n-o]][[+1 :e-23n-o]][[-1 :e1-3n-o]][[-1 :e--3n-o]]]
    [2r01101 :e1-3ni- [[+1 :e1-3ni-]][[+1 :e--3ni-]][[+1 :e123ni-]][[-1 :e1--ni-]][][[-1 :e1-3n--] [+1 :e1-3nio]][[+1 :e-23ni-]][[-1 :e---ni-]][[-1 :e12-ni-]][ ][[-1 :e--3n--] [-1 :e--3nio]][ ][[-1 :e123n--] [+1 :e123nio]][ ][[+1 :e1--n--] [-1 :e1--nio]][[-1 :e1-3ni-]][[-1 :e-2-ni-]][][[-1 :e-23n--] [+1 :e-23nio]][][[+1 :e---n--] [-1 :e---nio]][[+1 :e--3ni-]][][[+1 :e12-n--] [-1 :e12-nio]][[+1 :e123ni-]][[-1 :e1--ni-]][][[-1 :e-2-n--] [+1 :e-2-nio]][[+1 :e-23ni-]][[-1 :e---ni-]][[-1 :e12-ni-]][[-1 :e-2-ni-]]]
    [2r10101 :e1-3n-o [[+1 :e1-3n-o]][[+1 :e--3n-o]][[+1 :e123n-o]][[-1 :e1--n-o]][[-1 :e1-3n--] [-1 :e1-3nio]][][[+1 :e-23n-o]][[-1 :e---n-o]][[-1 :e12-n-o]][[-1 :e--3n--] [-1 :e--3nio]][ ][[-1 :e123n--] [-1 :e123nio]][ ][[+1 :e1--n--] [+1 :e1--nio]][ ][[-1 :e1-3n-o]][[-1 :e-2-n-o]][[-1 :e-23n--] [-1 :e-23nio]][][[+1 :e---n--] [+1 :e---nio]][][[-1 :e--3n-o]][[+1 :e12-n--] [+1 :e12-nio]][][[-1 :e123n-o]][[+1 :e1--n-o]][[+1 :e-2-n--] [+1 :e-2-nio]][][[-1 :e-23n-o]][[+1 :e---n-o]][[+1 :e12-n-o]][[+1 :e-2-n-o]]]
    [2r11001 :e1--nio [[+1 :e1--nio]][[+1 :e---nio]][[+1 :e12-nio]][[+1 :e1-3nio]][[-1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e-2-nio]][[+1 :e--3nio]][[+1 :e123nio]][[-1 :e---ni-]][[+1 :e---n-o]][[-1 :e12-ni-]][[+1 :e12-n-o]][[-1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--n--]][[+1 :e-23nio]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[-1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---n--]][[-1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-n--]][[+1 :e1-3n--]][[-1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-n--]][[+1 :e--3n--]][[+1 :e123n--]][[+1 :e-23n--]]]
    [2r01110 :e-23ni- [[+1 :e-23ni-]][[-1 :e123ni-]][[+1 :e--3ni-]][[-1 :e-2-ni-]][][[-1 :e-23n--] [+1 :e-23nio]][[-1 :e1-3ni-]][[+1 :e12-ni-]][[-1 :e---ni-]][ ][[+1 :e123n--] [-1 :e123nio]][ ][[-1 :e--3n--] [+1 :e--3nio]][ ][[+1 :e-2-n--] [-1 :e-2-nio]][[+1 :e-23ni-]][[+1 :e1--ni-]][][[+1 :e1-3n--] [-1 :e1-3nio]][][[-1 :e12-n--] [+1 :e12-nio]][[-1 :e123ni-]][][[+1 :e---n--] [-1 :e---nio]][[+1 :e--3ni-]][[-1 :e-2-ni-]][][[-1 :e1--n--] [+1 :e1--nio]][[+1 :e1-3ni-]][[+1 :e12-ni-]][[-1 :e---ni-]][[+1 :e1--ni-]]]
    [2r10110 :e-23n-o [[+1 :e-23n-o]][[-1 :e123n-o]][[+1 :e--3n-o]][[-1 :e-2-n-o]][[-1 :e-23n--] [-1 :e-23nio]][][[-1 :e1-3n-o]][[+1 :e12-n-o]][[-1 :e---n-o]][[+1 :e123n--] [+1 :e123nio]][ ][[-1 :e--3n--] [-1 :e--3nio]][ ][[+1 :e-2-n--] [+1 :e-2-nio]][ ][[-1 :e-23n-o]][[+1 :e1--n-o]][[+1 :e1-3n--] [+1 :e1-3nio]][][[-1 :e12-n--] [-1 :e12-nio]][][[+1 :e123n-o]][[+1 :e---n--] [+1 :e---nio]][][[-1 :e--3n-o]][[+1 :e-2-ni-]][[-1 :e1--n--] [-1 :e1--nio]][][[-1 :e1-3n-o]][[-1 :e12-n-o]][[+1 :e---n-o]][[-1 :e1--n-o]]]
    [2r11010 :e-2-nio [[+1 :e-2-nio]][[-1 :e12-nio]][[+1 :e---nio]][[+1 :e-23nio]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[-1 :e1--nio]][[-1 :e123nio]][[+1 :e--3nio]][[+1 :e12-ni-]][[-1 :e12-n-o]][[-1 :e---ni-]][[+1 :e---n-o]][[-1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e-2-n--]][[-1 :e1-3nio]][[+1 :e1--ni-]][[-1 :e1--n-o]][[+1 :e123ni-]][[-1 :e123n-o]][[-1 :e12-n--]][[-1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e---n--]][[+1 :e-23n--]][[+1 :e1-3ni-]][[-1 :e1-3n-o]][[-1 :e1--n--]][[-1 :e123n--]][[+1 :e--3n--]][[-1 :e1-3n--]]]
    [2r11100 :e--3nio [[+1 :e--3nio]][[-1 :e1-3nio]][[-1 :e-23nio]][[+1 :e---nio]][[-1 :e--3ni-]][[+1 :e--3n-o]][[+1 :e123nio]][[-1 :e1--nio]][[-1 :e-2-nio]][[+1 :e1-3ni-]][[-1 :e1-3n-o]][[+1 :e-23ni-]][[-1 :e-23n-o]][[-1 :e---ni-]][[+1 :e---n-o]][[+1 :e--3n--]][[+1 :e12-nio]][[-1 :e123ni-]][[+1 :e123n-o]][[+1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3n--]][[+1 :e-2-ni-]][[-1 :e-2-n-o]][[-1 :e-23n--]][[+1 :e---n--]][[-1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123n--]][[-1 :e1--n--]][[-1 :e-2-n--]][[+1 :e12-n--]]]
    [2r01111 :e123ni- [[+1 :e123ni-]][[-1 :e-23ni-]][[+1 :e1-3ni-]][[-1 :e12-ni-]][][[-1 :e123n--] [+1 :e123nio]][[-1 :e--3ni-]][[+1 :e-2-ni-]][[-1 :e1--ni-]][ ][[+1 :e-23n--] [-1 :e-23nio]][ ][[-1 :e1-3n--] [+1 :e1-3nio]][ ][[+1 :e12-n--] [-1 :e12-nio]][[+1 :e123ni-]][[+1 :e---ni-]][][[+1 :e--3n--] [-1 :e--3nio]][][[-1 :e-2-n--] [+1 :e-2-nio]][[-1 :e-23ni-]][][[+1 :e1--n--] [-1 :e1--nio]][[+1 :e1-3ni-]][[-1 :e12-ni-]][][[-1 :e---n--] [+1 :e---nio]][[-1 :e--3ni-]][[+1 :e-2-ni-]][[-1 :e1--ni-]][[+1 :e---ni-]]]
    [2r10111 :e123n-o [[+1 :e123n-o]][[-1 :e-23n-o]][[+1 :e1-3n-o]][[-1 :e12-n-o]][[-1 :e123n--] [-1 :e123nio]][][[-1 :e--3n-o]][[+1 :e-2-n-o]][[-1 :e1--n-o]][[+1 :e-23n--] [+1 :e-23nio]][ ][[-1 :e1-3n--] [-1 :e1-3nio]][ ][[+1 :e12-n--] [+1 :e12-nio]][ ][[-1 :e123n-o]][[+1 :e---n-o]][[+1 :e--3n--] [+1 :e--3nio]][][[-1 :e-2-n--] [-1 :e-2-nio]][][[+1 :e-23n-o]][[+1 :e1--n--] [+1 :e1--nio]][][[-1 :e1-3n-o]][[+1 :e12-n-o]][[-1 :e---n--] [-1 :e---nio]][][[+1 :e--3n-o]][[-1 :e-2-n-o]][[+1 :e1--n-o]][[-1 :e---n-o]]]
    [2r11011 :e12-nio [[+1 :e12-nio]][[-1 :e-2-nio]][[+1 :e1--nio]][[+1 :e123nio]][[-1 :e12-ni-]][[+1 :e12-n-o]][[-1 :e---nio]][[-1 :e-23nio]][[+1 :e1-3nio]][[+1 :e-2-ni-]][[-1 :e-2-n-o]][[-1 :e1--ni-]][[+1 :e1--n-o]][[-1 :e123ni-]][[+1 :e123n-o]][[+1 :e12-n--]][[-1 :e--3nio]][[+1 :e1--ni-]][[-1 :e---n-o]][[+1 :e-23ni-]][[-1 :e-23n-o]][[-1 :e-2-n--]][[-1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e1--n--]][[+1 :e123n--]][[+1 :e--3ni-]][[-1 :e--3n-o]][[-1 :e---n--]][[-1 :e-23n--]][[+1 :e1-3n--]][[-1 :e--3n--]]]
    [2r11101 :e1-3nio [[+1 :e1-3nio]][[-1 :e--3nio]][[-1 :e123nio]][[+1 :e1--nio]][[-1 :e1-3ni-]][[+1 :e1-3n-o]][[+1 :e-23nio]][[-1 :e---nio]][[-1 :e12-nio]][[+1 :e--3ni-]][[-1 :e--3n-o]][[+1 :e123ni-]][[-1 :e123n-o]][[-1 :e1--ni-]][[+1 :e1--n-o]][[+1 :e1-3n--]][[+1 :e-2-nio]][[-1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3n--]][[+1 :e12-ni-]][[-1 :e12-n-o]][[-1 :e123n--]][[+1 :e1--n--]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23n--]][[-1 :e---n--]][[-1 :e12-n--]][[+1 :e-2-n--]]]
    [2r11110 :e-23nio [[+1 :e-23nio]][[+1 :e123nio]][[-1 :e--3nio]][[+1 :e-2-nio]][[-1 :e-23ni-]][[+1 :e-23n-o]][[-1 :e1-3nio]][[+1 :e12-nio]][[-1 :e---nio]][[-1 :e123ni-]][[+1 :e123n-o]][[+1 :e--3ni-]][[-1 :e--3n-o]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23n--]][[-1 :e1--nio]][[+1 :e1-3ni-]][[-1 :e1-3n-o]][[-1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123n--]][[+1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3n--]][[+1 :e-2-n--]][[+1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3n--]][[+1 :e12-n--]][[-1 :e---n--]][[-1 :e1--n--]]]
    [2r11111 :e123nio [[+1 :e123nio]][[+1 :e-23nio]][[+1 :e1-3nio]][[+1 :e1-3nio]][[-1 :e123ni-]][[+1 :e123n-o]][[-1 :e--3nio]][[+1 :e-2-nio]][[-1 :e1--nio]][[-1 :e-23ni-]][[+1 :e-23n-o]][[+1 :e1-3ni-]][[-1 :e1-3n-o]][[-1 :e12-ni-]][[+1 :e12-n-o]][[+1 :e123n--]][[-1 :e---nio]][[+1 :e--3ni-]][[-1 :e--3n-o]][[-1 :e-2-ni-]][[+1 :e-2-n-o]][[+1 :e-23n--]][[+1 :e1--ni-]][[-1 :e1--n-o]][[-1 :e1-3n--]][[+1 :e12-n--]][[+1 :e---ni-]][[-1 :e---n-o]][[-1 :e--3n--]][[+1 :e-2-n--]][[-1 :e1--n--]][[-1 :e---n--]]]
    ]})

(defn to-csv []
  (with-open [out (java.io.FileWriter. "c3_blade.csv")]
    (binding [*out* out]
    (println "heading")
    (let [tab product-table]
      (println "bitmap,," (interpose "," (:key-bitmap tab)))
      (println ",blade," (interpose "," (:key-label tab)))
      (doseq [row (:product tab)]
        (println (first row) "," (second row) "," (interpose "," (subvec row 2))) )))))

(to-csv)

(let
  [lookup
   (let [tab product-table]
     (into {} (map #(vector %1 %2)
                   (:key-label tab)
                   (:key-bitmap tab))))]
  (defn label->bitmap
    "convert a label key to a bitmap"
    [label] (get lookup label)))

(let
  [lookup
   (let [tab product-table]
     (into {} (map #(vector %1 %2)
                   (:key-bitmap tab)
                   (:key-label tab) )))]
  (defn bitmap->label
    "convert a bitmap key to a label"
    [keymap] (get lookup keymap)))

(defn make-label-lookup
  "build a lookup table where the keys are tuples of the blade keys."
  [tab]
  (into {}
  (let [kns (:key-label tab)]
    (for [kix (range (count kns))
          row (:product tab)]
      (let [prods (subvec row 2)
            kn1 (second row)
            cell (get prods kix)
            kn2 (get kns kix)]
        [[kn1 kn2] cell])))))


(let [alias-map (into {}
                      (map #(vector %1 %2)
                           (:key-alias product-table)
                           (:key-label product-table)))
      alias-map-inv (into {}
                      (map #(vector %1 %2)
                           (:key-label product-table)
                           (:key-alias product-table)))
      alias->bitmap (into {}
                      (map #(vector %1 %2)
                           (:key-alias product-table)
                           (:key-bitmap product-table)))]
  (def prod-alias-map alias-map)

  (defn expand-alias [bk]
    (if (contains? alias-map bk) (get alias-map bk) bk))
  (defn bitmap<-alias [al] (get alias->bitmap al))
  (defn compress-alias [bk] (get alias-map-inv bk)))

(let [lookup (make-label-lookup product-table)]
  (def prod-label-lookup lookup)
  (defn prod-label
    "Using a lookup table find the product
    of two blades.
    Build a lookup table,
    where a tuple is the key.
    The tuple consisting of the labels of
    the two keys."
    [bk1 bk2]
    (let [kn (mapv expand-alias [bk1 bk2])]
      (get lookup kn))))


(defn make-bitmap-lookup
  "build a lookup array where the index is a function
  of the bitmaps of the blade keys."
  [tab]
  (let [kbs (:key-bitmap tab)
        kns (:key-label tab)
        look (into {} (map #(vector %1 %2) kns kbs))]
    (for [kix (range (count kns))
          row (:product tab)]
      (let [prods (subvec row 2)
            kn1 (first row)
            kn2 (get kbs kix)
            cell (mapv #(let [[sign kn] %] [sign (get look kn)]) (get prods kix)) ]
        [(+ (bit-shift-left kn1 5)  kn2) cell]))))

(let [lookup
      (->> (make-bitmap-lookup product-table)
           (sort-by first)
           (map #(let [[bitmap payload] %] payload))
           vec)]

  (def prod-bitmap-lookup lookup)
  (defn prod-bitmap
    "given two bitmaps find their product.
    Build a lookup table,
    an array where the index is the key."
    [[co1 bk1] [co2 bk2]]
    (->> (get lookup (+ (bit-shift-left bk1 5) bk2))
          (mapv (fn [[v k]] (vector (* co1 co2 v) k)) ))) )



(defn product
  "compute the product of two blades"
  [b1 b2]
  (let [bm1 (:bitmap b1)
        bm2 (:bitmap b2)]
    (prod-bitmap bm1 bm2)))

