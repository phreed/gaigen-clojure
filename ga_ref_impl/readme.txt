This directory contains the Reference Implementation from the book
Geometric Algebra for Computer Science: 
An Object Oriented Approach To Geometry.

The main content is in the subspace.basis directory.
subspace.util and subspace.metric are just some support classes.
You can run 'java test' in this directory just to see if all
classes are present and working.

Do make sure you have the Colt packages (colt.jar) in you classpath.
It is used for its linear algebra implementation.
You can get it from:
http://dsd.lbl.gov/~hoschek/colt/

You can view the source code as HTML at:
http://www.geometricalgebra.net

The most interesting files are:

BasisBlade.java                    Geometric algebra for weighted basis blades (Chapter 19)
Multivector.java                   Geometric algebra for multivector, inverse, exp, sin, cos, etc (Chapters 20, 21)
MeetJoin.java                      Meet and Join algorithm (Section 21.7)
Metric.java                        Metric, Eigenvalue Decomposition (Section 19.4)
MultivectorType.java               Determinining the 'multivector type' of a multivector (Section 21.5)
Util.java                          Blade factorization (Section 21.6), rotationMatrixToRotor, orthogonalization w.r.t. any metric

--Daniel Fontijne (fontijne@science.uva.nl)

Last change: 20070104

