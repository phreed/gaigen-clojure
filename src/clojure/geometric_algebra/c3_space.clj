(ns geometric-algebra.c3-space)

(def blade-product-table
  {:key-bits [2r00000 2r00001 2r00010 2r00100 2r01000 2r10000 2r00011 2r00101 2r00110 2r01001 2r10001 2r01010 2r10010 2r01100 2r10100 2r11000 2r00111 2r01011 2r10011 2r01101 2r10101 2r11001 2r01110 2r10110 2r11010 2r11100 2r01111 2r10111 2r11011 2r11101 2r11110 2r11111]
   :key-name [:000x00 :100x00 :010x00 :001x00 :000x10 :000x01 :110x00 :101x00 :011x00 :100x10 :100x01 :010x10 :010x01 :001x10 :001x01 :000x11 :111x00 :110x10 :110x01 :101x10 :101x01 :100x11 :011x10 :011x01 :010x11 :001x11 :111x10 :111x01 :110x11 :101x11 :011x11 :111x11]
   :product
   [
[2r00000 :000x00 [[+1 :000x00]][[+1 :100x00]][[+1 :010x00]][[+1 :001x00]][[+1 :000x10]][[+1 :000x01]][[+1 :110x00]][[+1 :101x00]][[+1 :011x00]][[+1 :100x10]][[+1 :100x01]][[+1 :010x10]][[+1 :010x01]][[+1 :001x10]][[+1 :001x01]][[+1 :000x11]][[+1 :111x00]][[+1 :110x10]][[+1 :110x01]][[+1 :101x10]][[+1 :101x01]][[+1 :100x11]][[+1 :011x10]][[+1 :011x01]][[+1 :010x11]][[+1 :001x11]][[+1 :111x10]][[+1 :111x01]][[+1 :110x11]][[+1 :101x11]][[+1 :011x11]][[+1 :111x11]]]
[2r00001 :100x00 [[+1 :100x00]][[+1 :000x00]][[+1 :110x00]][[+1 :101x00]][[+1 :100x10]][[+1 :100x01]][[+1 :010x00]][[+1 :001x00]][[+1 :111x00]][[+1 :000x10]][[+1 :000x01]][[+1 :110x10]][[+1 :110x01]][[+1 :101x10]][[+1 :101x01]][[+1 :100x11]][[+1 :011x00]][[+1 :010x10]][[+1 :010x01]][[+1 :001x10]][[+1 :001x01]][[+1 :000x11]][[+1 :111x10]][[+1 :111x01]][[+1 :110x11]][[+1 :101x11]][[+1 :011x10]][[+1 :011x01]][[+1 :010x11]][[+1 :001x11]][[+1 :111x11]][[+1 :011x11]]]
[2r00010 :010x00 [[+1 :010x00]][[-1 :110x00]][[+1 :000x00]][[+1 :011x00]][[+1 :010x10]][[+1 :010x01]][[-1 :100x00]][[-1 :111x00]][[+1 :001x00]][[-1 :110x10]][[-1 :110x01]][[+1 :000x10]][[+1 :000x01]][[+1 :011x10]][[+1 :011x01]][[+1 :010x11]][[-1 :101x00]][[-1 :100x10]][[-1 :100x01]][[-1 :111x10]][[-1 :111x01]][[-1 :110x11]][[+1 :001x10]][[+1 :001x01]][[+1 :000x11]][[+1 :011x11]][[-1 :101x10]][[-1 :101x01]][[-1 :100x11]][[-1 :111x11]][[+1 :001x11]][[-1 :101x11]]]
[2r00100 :001x00 [[+1 :001x00]][[-1 :101x00]][[-1 :011x00]][[+1 :000x00]][[+1 :001x10]][[+1 :001x01]][[-1 :111x00]][[-1 :100x00]][[-1 :010x00]][[-1 :101x10]][[-1 :101x01]][[-1 :011x10]][][[+1 :000x10]][[+1 :000x01]][[+1 :001x11]][[+1 :110x00]][[+1 :111x10]][[+1 :111x01]][[-1 :100x10]][[-1 :100x01]][[-1 :101x11]][[-1 :010x10]][[-1 :010x01]][[-1 :011x11]][[+1 :000x11]][[+1 :110x10]][[+1 :110x01]][[+1 :111x11]][[-1 :100x11]][[-1 :010x11]][[+1 :110x11]]]
[2r01000 :000x10 [[+1 :000x10]][[-1 :100x10]][[-1 :010x10]][[-1 :001x10]][][[-1 :000x00] [+1 :000x11]][[+1 :110x10]][[+1 :101x10]][[+1 :011x10]][][[+1 :100x00] [-1 :100x11]][][[+1 :010x00] [-1 :010x11]][][[+1 :001x00] [-1 :001x11]][[+1 :000x10]][[-1 :111x10]][][[-1 :110x00] [+1 :110x11]][][[-1 :101x00] [+1 :101x11]][[-1 :100x10]][][[-1 :011x00] [+1 :011x11]][[-1 :010x10]][[-1 :001x10]][][[+1 :111x00] [-1 :111x11]][[+1 :110x10]][[+1 :101x10]][[+1 :011x10]][[-1 :111x10]]]
[2r10000 :000x01 [[+1 :000x01]][[-1 :100x01]][[-1 :010x01]][[-1 :001x01]][[-1 :000x00] [-1 :000x11]][][[+1 :110x01]][[+1 :101x01]][[+1 :011x01]][[+1 :100x00] [+1 :100x11]][][[+1 :010x00] [+1 :010x11]][][[+1 :001x00] [+1 :001x11]][][[-1 :000x01]][[-1 :111x01]][[-1 :110x00] [-1 :110x11]][][[-1 :101x00] [-1 :101x11]][][[-1 :100x01]][[-1 :011x00] [-1 :011x11]][][[+1 :010x01]][[+1 :001x01]][[+1 :111x00] [+1 :111x11]][][[-1 :110x01]][[-1 :011x01]][[-1 :011x01]][[+1 :111x01]] ]
[2r00011 :110x00 [[+1 :110x00]][[-1 :010x00]][[+1 :100x00]][[+1 :111x00]][[+1 :110x10]][[+1 :110x01]][[-1 :000x00]][[-1 :011x00]][[+1 :101x00]][[-1 :010x10]][[-1 :010x01]][[+1 :100x10]][[+1 :100x01]][[+1 :111x10]][[+1 :111x01]][[+1 :110x11]][[-1 :001x00]][[-1 :000x10]][[-1 :000x01]][[-1 :011x10]][[-1 :011x01]][[-1 :010x11]][[+1 :101x10]][[+1 :101x01]][[+1 :100x11]][[+1 :111x11]][[-1 :001x10]][[-1 :001x01]][[-1 :000x11]][[-1 :011x11]][[+1 :101x11]][[-1 :001x11]]]
[2r00101 :101x00 [[+1 :101x00]][[-1 :001x00]][[-1 :111x00]][[+1 :100x00]][[+1 :101x10]][[+1 :101x01]][[+1 :011x00]][[-1 :000x00]][[-1 :110x00]][[-1 :001x10]][[-1 :001x01]][[-1 :111x10]][[-1 :111x01]][[+1 :100x10]][[+1 :100x01]][[+1 :101x11]][[+1 :010x00]][[+1 :011x10]][[+1 :011x10]][[-1 :000x10]][[-1 :000x01]][[-1 :001x11]][[-1 :110x10]][[-1 :110x01]][[-1 :111x11]][[+1 :100x11]][[+1 :010x10]][[+1 :010x01]][[+1 :011x11]][[+1 :000x11]][[-1 :110x11]][[+1 :010x11]]]
[2r00110 :011x00 [[+1 :011x00]][[+1 :111x00]][[-1 :001x00]][[+1 :010x00]][[-1 :011x10]][[+1 :011x01]][[-1 :101x00]][[+1 :110x00]][[-1 :000x00]][[+1 :111x10]][[+1 :111x01]][[-1 :001x10]][[-1 :001x01]][[+1 :010x10]][[+1 :010x01]][[+1 :011x11]][[-1 :100x00]][[-1 :101x10]][[-1 :101x01]][[+1 :110x10]][[+1 :110x01]][[+1 :111x11]][[-1 :000x10]][[-1 :000x01]][[-1 :001x11]][[+1 :010x11]][[-1 :100x10]][[-1 :100x01]][[-1 :101x11]][[+1 :110x11]][[-1 :000x11]][[-1 :100x11]]]
[2r01001 :100x10 [[+1 :100x10]][[-1 :000x10]][[-1 :110x10]][[-1 :101x10]][][[-1 :100x00] [+1 :100x11]][[+1 :010x10]][[+1 :001x10]][[+1 :111x10]][][[+1 :000x00] [-1 :000x11]][][[+1 :110x00] [-1 :110x11]][][[+1 :101x00] [-1 :101x11]][[+1 :100x10]][[-1 :011x10]][][[-1 :010x00] [+1 :010x11]][][[-1 :001x00] [+1 :001x11]][[-1 :000x10]][][[-1 :111x00] [+1 :111x11]][[-1 :110x10]][[-1 :101x10]][][[+1 :011x00] [-1 :011x11]][[+1 :010x10]][[+1 :001x10]][[+1 :111x10]][[-1 :011x10]]]
[2r10001 :100x01 [[+1 :100x01]][[-1 :000x01]][[-1 :110x01]][[-1 :101x01]][[-1 :100x00] [-1 :100x11]][][[+1 :010x01]][[+1 :001x01]][[+1 :111x01]][[+1 :000x00] [+1 :000x11]][][[+1 :110x00] [+1 :110x11]][][[+1 :101x00] [+1 :101x11]][][[-1 :100x01]][[-1 :011x01]][[-1 :010x00] [-1 :010x11]][][[-1 :001x00] [-1 :001x11]][][[+1 :000x01]][[-1 :111x00] [-1 :111x11]][][[+1 :110x01]][[+1 :101x01]][[+1 :011x00] [+1 :011x11]][][[-1 :010x01]][[-1 :001x01]][[-1 :111x01]][[+1 :011x01]]]
[2r01010 :010x10 [[+1 :010x10]][[+1 :110x10]][[-1 :000x10]][[-1 :011x10]][][[-1 :010x00] [+1 :010x11]][[-1 :100x10]][[-1 :111x10]][[+1 :001x10]][][[-1 :110x00] [+1 :110x11]][][[+1 :000x00] [-1 :000x11]][][[+1 :011x00] [-1 :011x11]][[+1 :010x10]][[+1 :101x10]][][[+1 :100x00] [-1 :100x11]][][[+1 :111x00] [-1 :111x11]][[+1 :110x10]][][[-1 :001x00] [+1 :001x11]][[-1 :000x10]][[-1 :011x10]][][[-1 :101x00] [+1 :101x11]][[-1 :100x10]][[-1 :111x10]][[+1 :001x10]][[+1 :101x10]]]
[2r10010 :010x01 [[+1 :010x01]][[+1 :110x01]][[-1 :000x01]][[-1 :011x01]][[-1 :010x00] [-1 :010x11]][][[-1 :100x01]][[-1 :111x01]][[+1 :001x01]][[-1 :101x00] [-1 :101x11]][][[+1 :000x00] [+1 :000x11]][][[+1 :011x00] [+1 :011x11]][][[-1 :010x01]][[+1 :101x01]][[+1 :100x00] [+1 :100x11]][][[+1 :111x00] [+1 :111x11]][][[-1 :110x01]][[-1 :001x00] [-1 :001x11]][][[+1 :000x01]][[+1 :011x01]][[-1 :101x00] [-1 :101x11]][][[+1 :100x01]][[+1 :111x01]][[-1 :001x01]][[-1 :101x01]]]
[2r01100 :001x10 [[+1 :001x10]][[+1 :101x10]][[+1 :011x10]][[-1 :000x10]][][[-1 :001x00] [+1 :001x11]][[+1 :111x10]][[-1 :100x10]][[-1 :010x10]][][[-1 :101x00] [+1 :101x11]][][[-1 :011x00] [+1 :011x11]][][[+1 :000x00] [-1 :000x11]][[+1 :001x10]][[-1 :110x10]][][[-1 :111x00] [+1 :111x11]][][[+1 :100x00] [-1 :100x11]][[+1 :101x10]][][[+1 :010x00] [-1 :010x11]][[+1 :011x10]][[-1 :000x10]][][[+1 :110x00] [-1 :110x11]][[+1 :111x10]][[-1 :100x10]][[-1 :010x10]][[-1 :110x10]]]
[2r10100 :001x01 [[+1 :001x01]][[+1 :101x01]][[+1 :011x01]][[-1 :000x01]][[-1 :001x00] [-1 :001x11]][][[+1 :111x01]][[-1 :100x01]][[-1 :010x01]][[-1 :101x00] [-1 :101x11]][][[-1 :011x00] [-1 :011x11]][][[+1 :000x00] [+1 :000x11]][][[-1 :001x01]][[-1 :110x01]][[-1 :111x00] [-1 :111x11]][][[+1 :100x00] [+1 :100x11]][][[-1 :101x01]][[+1 :010x00] [+1 :010x11]][][[-1 :011x01]][[+1 :000x01]][[+1 :110x00] [+1 :110x11]][][[-1 :111x01]][[+1 :100x01]][[+1 :010x01]][[+1 :110x11]]]
[2r11000 :000x11 [[+1 :000x11]][[+1 :100x11]][[+1 :010x11]][[+1 :001x11]][[-1 :000x10]][[+1 :000x01]][[+1 :110x11]][[+1 :101x11]][[+1 :011x11]][[-1 :100x10]][[+1 :100x01]][[-1 :010x10]][[+1 :010x01]][[-1 :001x10]][[+1 :001x01]][[+1 :000x00]][[+1 :111x11]][[-1 :110x10]][[+1 :110x01]][[-1 :101x10]][[+1 :101x01]][[+1 :100x00]][[-1 :011x10]][[+1 :011x01]][[+1 :010x00]][[+1 :001x00]][[-1 :111x10]][[+1 :111x01]][[+1 :110x00]][[+1 :101x00]][[+1 :011x00]][[+1 :111x00]]]
[2r00111 :111x00 [[+1 :111x00]][[+1 :011x00]][[-1 :011x00]][[+1 :110x00]][[+1 :111x10]][[+1 :111x01]][[-1 :001x00]][[+1 :010x00]][[-1 :100x00]][[+1 :011x10]][[+1 :011x01]][[-1 :101x10]][[-1 :101x01]][[+1 :110x10]][[+1 :110x01]][[+1 :111x11]][[-1 :000x00]][[-1 :001x10]][[-1 :001x01]][[+1 :010x10]][[+1 :010x01]][[+1 :011x11]][[-1 :100x10]][[-1 :100x01]][[-1 :101x11]][[+1 :110x11]][[-1 :000x10]][[-1 :000x01]][[-1 :001x11]][[+1 :010x11]][[-1 :100x11]][[-1 :000x11]]]
[2r01011 :110x10 [[+1 :110x10]][[+1 :010x10]][[-1 :100x10]][[-1 :111x10]][][[-1 :110x00] [+1 :110x11]][[-1 :000x10]][[-1 :011x10]][[+1 :101x10]][ ][[-1 :010x00] [-1 :010x11]][ ][[+1 :100x00] [-1 :100x11]][ ][[+1 :111x00] [-1 :111x11]][[+1 :110x10]][[+1 :001x10]][][[+1 :000x00] [-1 :000x11]][][[+1 :011x00] [-1 :011x11]][[+1 :010x10]][][[-1 :101x00] [+1 :101x11]][[-1 :100x10]][[-1 :111x10]][][[-1 :001x00] [+1 :001x11]][[-1 :000x10]][[-1 :011x10]][[+1 :101x10]][[+1 :001x10]]]
[2r10011 :110x01 [[+1 :110x01]][[+1 :010x01]][[-1 :100x01]][[-1 :111x01]][[-1 :110x00] [-1 :110x11]][][[-1 :000x01]][[-1 :011x01]][[+1 :101x01]][[-1 :010x00] [-1 :010x11]][ ][[+1 :100x00] [+1 :100x11]][ ][[+1 :111x00] [+1 :111x11]][ ][[-1 :110x01]][[+1 :001x01]][[+1 :000x00] [+1 :000x11]][][[+1 :011x00] [+1 :011x11]][][[-1 :010x01]][[-1 :101x00] [-1 :101x11]][][[+1 :100x01]][[+1 :111x01]][[-1 :001x00] [-1 :001x11]][][[+1 :000x01]][[+1 :011x01]][[-1 :101x01]][[-1 :001x01]]]
[2r01101 :101x10 [[+1 :101x10]][[+1 :001x10]][[+1 :111x10]][[-1 :100x10]][][[-1 :101x00] [+1 :101x11]][[+1 :011x10]][[-1 :000x10]][[-1 :110x10]][ ][[-1 :001x00] [-1 :001x11]][ ][[-1 :111x00] [+1 :111x11]][ ][[+1 :100x00] [-1 :100x11]][[-1 :101x10]][[-1 :010x10]][][[-1 :011x00] [+1 :011x11]][][[+1 :000x00] [-1 :000x11]][[+1 :001x10]][][[+1 :110x00] [-1 :110x11]][[+1 :111x10]][[-1 :100x10]][][[-1 :010x00] [+1 :010x11]][[+1 :011x10]][[-1 :000x10]][[-1 :110x10]][[-1 :010x10]]]
[2r10101 :101x01 [[+1 :101x01]][[+1 :001x01]][[+1 :111x01]][[-1 :100x01]][[-1 :101x00] [-1 :101x11]][][[+1 :011x01]][[-1 :000x01]][[-1 :110x01]][[-1 :001x00] [-1 :001x11]][ ][[-1 :111x00] [-1 :111x11]][ ][[+1 :100x00] [+1 :100x11]][ ][[-1 :101x01]][[-1 :010x01]][[-1 :011x00] [-1 :011x11]][][[+1 :000x00] [+1 :000x11]][][[-1 :001x01]][[+1 :110x00] [+1 :110x11]][][[-1 :111x01]][[+1 :100x01]][[+1 :010x00] [+1 :010x11]][][[-1 :011x01]][[+1 :000x01]][[+1 :110x01]][[+1 :010x01]]]
[2r11001 :100x11 [[+1 :100x11]][[+1 :000x11]][[+1 :110x11]][[+1 :101x11]][[-1 :100x10]][[+1 :100x01]][[+1 :010x11]][[+1 :001x11]][[+1 :111x11]][[-1 :000x10]][[+1 :000x01]][[-1 :110x10]][[+1 :110x01]][[-1 :101x10]][[+1 :101x01]][[+1 :100x00]][[+1 :011x11]][[-1 :010x10]][[+1 :010x01]][[-1 :001x10]][[+1 :001x01]][[+1 :000x00]][[-1 :111x10]][[+1 :111x01]][[+1 :110x00]][[+1 :101x00]][[-1 :011x10]][[+1 :011x01]][[+1 :010x00]][[+1 :001x00]][[+1 :111x00]][[+1 :011x00]]]
[2r01110 :011x10 [[+1 :011x10]][[-1 :111x10]][[+1 :001x10]][[-1 :010x10]][][[-1 :011x00] [+1 :011x11]][[-1 :101x10]][[+1 :110x10]][[-1 :000x10]][ ][[+1 :111x00] [-1 :111x11]][ ][[-1 :001x00] [+1 :001x11]][ ][[+1 :010x00] [-1 :010x11]][[+1 :011x10]][[+1 :100x10]][][[+1 :101x00] [-1 :101x11]][][[-1 :110x00] [+1 :110x11]][[-1 :111x10]][][[+1 :000x00] [-1 :000x11]][[+1 :001x10]][[-1 :010x10]][][[-1 :100x00] [+1 :100x11]][[+1 :101x10]][[+1 :110x10]][[-1 :000x10]][[+1 :100x10]]]
[2r10110 :011x01 [[+1 :011x01]][[-1 :111x01]][[+1 :001x01]][[-1 :010x01]][[-1 :011x00] [-1 :011x11]][][[-1 :101x01]][[+1 :110x01]][[-1 :000x01]][[+1 :111x00] [+1 :111x11]][ ][[-1 :001x00] [-1 :001x11]][ ][[+1 :010x00] [+1 :010x11]][ ][[-1 :011x01]][[+1 :100x01]][[+1 :101x00] [+1 :101x11]][][[-1 :110x00] [-1 :110x11]][][[+1 :111x01]][[+1 :000x00] [+1 :000x11]][][[-1 :001x01]][[+1 :010x10]][[-1 :100x00] [-1 :100x11]][][[-1 :101x01]][[-1 :110x01]][[+1 :000x01]][[-1 :100x01]]]
[2r11010 :010x11 [[+1 :010x11]][[-1 :110x11]][[+1 :000x11]][[+1 :011x11]][[-1 :010x10]][[+1 :010x01]][[-1 :100x11]][[-1 :111x11]][[+1 :001x11]][[+1 :110x10]][[-1 :110x01]][[-1 :000x10]][[+1 :000x01]][[-1 :011x10]][[+1 :011x01]][[+1 :010x00]][[-1 :101x11]][[+1 :100x10]][[-1 :100x01]][[+1 :111x10]][[-1 :111x01]][[-1 :110x00]][[-1 :001x10]][[+1 :001x01]][[+1 :000x00]][[+1 :011x00]][[+1 :101x10]][[-1 :101x01]][[-1 :100x00]][[-1 :111x00]][[+1 :001x00]][[-1 :101x00]]]
[2r11100 :001x11 [[+1 :001x11]][[-1 :101x11]][[-1 :011x11]][[+1 :000x11]][[-1 :001x10]][[+1 :001x01]][[+1 :111x11]][[-1 :100x11]][[-1 :010x11]][[+1 :101x10]][[-1 :101x01]][[+1 :011x10]][[-1 :011x01]][[-1 :000x10]][[+1 :000x01]][[+1 :001x00]][[+1 :110x11]][[-1 :111x10]][[+1 :111x01]][[+1 :100x10]][[-1 :100x01]][[-1 :101x00]][[+1 :010x10]][[-1 :010x01]][[-1 :011x00]][[+1 :000x00]][[-1 :110x10]][[+1 :110x01]][[+1 :111x00]][[-1 :100x00]][[-1 :010x00]][[+1 :110x00]]]
[2r01111 :111x10 [[+1 :111x10]][[-1 :011x10]][[+1 :101x10]][[-1 :110x10]][][[-1 :111x00] [+1 :111x11]][[-1 :001x10]][[+1 :010x10]][[-1 :100x10]][ ][[+1 :011x00] [-1 :011x11]][ ][[-1 :101x00] [+1 :101x11]][ ][[+1 :110x00] [-1 :110x11]][[+1 :111x10]][[+1 :000x10]][][[+1 :001x00] [-1 :001x11]][][[-1 :010x00] [+1 :010x11]][[-1 :011x10]][][[+1 :100x00] [-1 :100x11]][[+1 :101x10]][[-1 :110x10]][][[-1 :000x00] [+1 :000x11]][[-1 :001x10]][[+1 :010x10]][[-1 :100x10]][[+1 :000x10]]]
[2r10111 :111x01 [[+1 :111x01]][[-1 :011x01]][[+1 :101x01]][[-1 :110x01]][[-1 :111x00] [-1 :111x11]][][[-1 :001x01]][[+1 :010x01]][[-1 :100x01]][[+1 :011x00] [+1 :011x11]][ ][[-1 :101x00] [-1 :101x11]][ ][[+1 :110x00] [+1 :110x11]][ ][[-1 :111x01]][[+1 :000x01]][[+1 :001x00] [+1 :001x11]][][[-1 :010x00] [-1 :010x11]][][[+1 :011x01]][[+1 :100x00] [+1 :100x11]][][[-1 :101x01]][[+1 :110x01]][[-1 :000x00] [-1 :000x11]][][[+1 :001x01]][[-1 :010x01]][[+1 :100x01]][[-1 :000x01]]]
[2r11011 :110x11 [[+1 :110x11]][[-1 :010x11]][[+1 :100x11]][[+1 :111x11]][[-1 :110x10]][[+1 :110x01]][[-1 :000x11]][[-1 :011x11]][[+1 :101x11]][[+1 :010x10]][[-1 :010x01]][[-1 :100x10]][[+1 :100x01]][[-1 :111x10]][[+1 :111x01]][[+1 :110x00]][[-1 :001x11]][[+1 :100x10]][[-1 :000x01]][[+1 :011x10]][[-1 :011x01]][[-1 :010x00]][[-1 :101x10]][[+1 :101x01]][[+1 :100x00]][[+1 :111x00]][[+1 :001x10]][[-1 :001x01]][[-1 :000x00]][[-1 :011x00]][[+1 :101x00]][[-1 :001x00]]]
[2r11101 :101x11 [[+1 :101x11]][[-1 :001x11]][[-1 :111x11]][[+1 :100x11]][[-1 :101x10]][[+1 :101x01]][[+1 :011x11]][[-1 :000x11]][[-1 :110x11]][[+1 :001x10]][[-1 :001x01]][[+1 :111x10]][[-1 :111x01]][[-1 :100x10]][[+1 :100x01]][[+1 :101x00]][[+1 :010x11]][[-1 :011x10]][[+1 :011x01]][[+1 :000x10]][[-1 :000x01]][[-1 :001x00]][[+1 :110x10]][[-1 :110x01]][[-1 :111x00]][[+1 :100x00]][[-1 :010x10]][[+1 :010x01]][[+1 :011x00]][[-1 :000x00]][[-1 :110x00]][[+1 :010x00]]]
[2r11110 :011x11 [[+1 :011x11]][[+1 :111x11]][[-1 :001x11]][[+1 :010x11]][[-1 :011x10]][[+1 :011x01]][[-1 :101x11]][[+1 :110x11]][[-1 :000x11]][[-1 :111x10]][[+1 :111x01]][[+1 :001x10]][[-1 :001x01]][[-1 :010x10]][[+1 :010x01]][[+1 :011x00]][[-1 :100x11]][[+1 :101x10]][[-1 :101x01]][[-1 :110x10]][[+1 :110x01]][[+1 :111x00]][[+1 :000x10]][[-1 :000x01]][[-1 :001x00]][[+1 :010x00]][[+1 :100x10]][[-1 :100x01]][[-1 :101x00]][[+1 :110x00]][[-1 :000x00]][[-1 :100x00]]]
[2r11111 :111x11 [[+1 :111x11]][[+1 :011x11]][[+1 :101x11]][[+1 :101x11]][[-1 :111x10]][[+1 :111x01]][[-1 :001x11]][[+1 :010x11]][[-1 :100x11]][[-1 :011x10]][[+1 :011x01]][[+1 :101x10]][[-1 :101x01]][[-1 :110x10]][[+1 :110x01]][[+1 :111x00]][[-1 :000x11]][[+1 :001x10]][[-1 :001x01]][[-1 :010x10]][[+1 :010x01]][[+1 :011x00]][[+1 :100x10]][[-1 :100x01]][[-1 :101x00]][[+1 :110x00]][[+1 :000x10]][[-1 :000x01]][[-1 :001x00]][[+1 :010x00]][[-1 :100x00]][[-1 :000x00]]]
]})


(defn make-blade-lookup
  "build a lookup table where the keys are tuples of the blade keys."
  [tab]
  (let [kn2s (:key-name tab)]
    (for [kix (range (count kn2s))
          row (:product tab)]
      (let [prods (subvec row 2)
            kn1 (second row)
            cell (get prods kix)
            kn2 (get kn2s kix)]
        [[kn1 kn2] cell]))))

(def c3-blade-prod
  (into
   {}
   (make-blade-lookup
    c3-blade-product-table)))
