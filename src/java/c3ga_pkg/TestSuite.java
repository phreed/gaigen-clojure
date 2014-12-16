/*
Gaigen 2.5 Test Suite
*/
/*
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

*/
package c3ga_pkg;
public class TestSuite  extends  c3ga
{ 
// Missing dependencies definitions:
/**
 * Returns random vectorE3GA with a scale in the interval [0, scale)
 */
public final static vectorE3GA random_vectorE3GA_dont_mangle_1_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	vectorE3GA tmp = new vectorE3GA();
	double n, mul, lc;
	boolean nullBlade;
	double re1 = -1.0 + 2.0 * genrand(), re2 = -1.0 + 2.0 * genrand(), re3 = -1.0 + 2.0 * genrand();
	tmp.m_e1 = re1;
	tmp.m_e2 = re2;
	tmp.m_e3 = re3;

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_vectorE3GA_dont_mangle_1_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_vectorE3GA_dont_mangle_1_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3 // e3
		);
}
/**
 * Returns random vectorE3GA with a scale in the interval [0, scale)
 */
public final static vectorE3GA random_vectorE3GA_dont_mangle_1(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_vectorE3GA_dont_mangle_1_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns scalar product of normalizedPoint and normalizedPoint.
 */
public final static double sp_dont_mangle_3(final normalizedPoint a, final normalizedPoint b)
{
	return (a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3-a.m_ni-b.m_ni);

}


/**
 * Generates a random versor.
 * The scale is uniformly distributed over the interval [0 1).
 * The maximum non-zero grade-part is 'grade'.
 * 
 * Only the basis vectors marked in 'basisVectorBitmap' will be used to generate the versor/blade.
 * Use 'basisVectorBitmap = -1' (the default) to use all basisvectors.
 * @return random_versor_dont_mangle_6_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_versor_dont_mangle_6_returns_mv(double scale, int grade, int basisVectorBitmap) {
	double minimumNorm = 0.01;
	double largestCoordinate = 4.0;
	return random_versor_dont_mangle_6_returns_mv_ex(scale, grade, basisVectorBitmap, minimumNorm, scale * largestCoordinate);
}

/**
 * This version of random_versor_dont_mangle_6_returns_mv() has extra arguments which help to avoid
 * near-singular blades / versors.
 * 
 * Near-singular blades / versors are avoid by testing the norm and largest coordinate
 * of the random blade / versor. If the test does not pass, the function recursively
 * tries to generate another random blade / versor.
 * 
 * 'minimumNorm' is the minimum allowed norm of the blade/versor before scaling. 
 * 'minimumNorm' must be > 0.0 for versors.
 * 
 * 'largestCoordinate' is the largest coordinate allowed after scaling.
 * 
 * @return random_versor_dont_mangle_6_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_versor_dont_mangle_6_returns_mv_ex(double scale, int _grade, int basisVectorBitmap, 
		double minimumNorm, double largestCoordinate) 
{
	mv randomVector = new mv();
	//, tmp1, tmp2;
	double[] randomValues = new double[5];
	//double n2, mul;
	int grade = _grade;
	
	// set IR (intermediate result) to 1
	mv IR = new mv (1.0);

	while (grade > 0) {	// loop until grade == 0
		// fill array with random values
		for (int i = 0; i < 5; i++) 
			randomValues[i] = ((basisVectorBitmap & (1 << i)) == 0)
				? 0.0 
				: (-1.0 + 2.0 * genrand());
		
		// make it a multivector:
		randomVector.set(GroupBitmap.GRADE_1, randomValues);
		
		// multiply 
		IR = gp(IR, randomVector);
		
		// lower grade
		grade--;
	}
	
	// compute norm/multiplier, apply it, or recurse if we happened to create a near-null versor
	double n2 = norm_returns_scalar(IR);
	if ((double)Math.abs(n2) > minimumNorm * minimumNorm) {
		if (n2 != 0.0) {
			double mul = scale * genrand() / n2;
			if (IR.largestCoordinate() * mul < largestCoordinate)
				return gp(IR, mul);
		}
		else if (IR.largestCoordinate() < largestCoordinate)
			return IR;
	}
	
	// try again:
	return random_versor_dont_mangle_6_returns_mv_ex(scale, _grade, basisVectorBitmap, minimumNorm, largestCoordinate); 
}
/**
 * Returns random normalizedPoint with a scale in the interval [0, scale)
 */
public final static normalizedPoint random_normalizedPoint_dont_mangle_10_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	normalizedPoint tmp = new normalizedPoint();
	double n, mul, lc;
	boolean nullBlade;
	double re1 = -1.0 + 2.0 * genrand(), re2 = -1.0 + 2.0 * genrand(), re3 = -1.0 + 2.0 * genrand(), rni = -1.0 + 2.0 * genrand();
	tmp.m_e1 = re1;
	tmp.m_e2 = re2;
	tmp.m_e3 = re3;
	tmp.m_ni = rni;

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_normalizedPoint_dont_mangle_10_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_normalizedPoint_dont_mangle_10_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3, // e3
			mul*tmp.m_ni // ni
		);
}
/**
 * Returns random normalizedPoint with a scale in the interval [0, scale)
 */
public final static normalizedPoint random_normalizedPoint_dont_mangle_10(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_normalizedPoint_dont_mangle_10_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random dualSphere with a scale in the interval [0, scale)
 */
public final static dualSphere random_dualSphere_dont_mangle_12_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	dualSphere tmp = new dualSphere();
	double n, mul, lc;
	boolean nullBlade;
	double rno = -1.0 + 2.0 * genrand(), re1 = -1.0 + 2.0 * genrand(), re2 = -1.0 + 2.0 * genrand(), re3 = -1.0 + 2.0 * genrand(), rni = -1.0 + 2.0 * genrand();
	tmp.m_no = rno;
	tmp.m_e1 = re1;
	tmp.m_e2 = re2;
	tmp.m_e3 = re3;
	tmp.m_ni = rni;

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_dualSphere_dont_mangle_12_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_dualSphere_dont_mangle_12_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			mul*tmp.m_no, // no
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3, // e3
			mul*tmp.m_ni // ni
		);
}
/**
 * Returns random dualSphere with a scale in the interval [0, scale)
 */
public final static dualSphere random_dualSphere_dont_mangle_12(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_dualSphere_dont_mangle_12_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random bivectorE3GA with a scale in the interval [0, scale)
 */
public final static bivectorE3GA random_bivectorE3GA_dont_mangle_13_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	bivectorE3GA tmp = new bivectorE3GA();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_e2 = (r0_e1*r1_e2-r0_e2*r1_e1);
	tmp.m_e2_e3 = (r0_e2*r1_e3-r0_e3*r1_e2);
	tmp.m_e3_e1 = -(r0_e1*r1_e3-r0_e3*r1_e1);

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_bivectorE3GA_dont_mangle_13_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_bivectorE3GA_dont_mangle_13_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			mul*tmp.m_e1_e2, // e1_e2
			mul*tmp.m_e2_e3, // e2_e3
			mul*tmp.m_e3_e1 // e3_e1
		);
}
/**
 * Returns random bivectorE3GA with a scale in the interval [0, scale)
 */
public final static bivectorE3GA random_bivectorE3GA_dont_mangle_13(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_bivectorE3GA_dont_mangle_13_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random plane with a scale in the interval [0, scale)
 */
public final static plane random_plane_dont_mangle_15_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	plane tmp = new plane();
	double n, mul, lc;
	boolean nullBlade;
	double re1_e2_e3_ni = -1.0 + 2.0 * genrand(), rno_e2_e3_ni = -1.0 + 2.0 * genrand(), rno_e1_e3_ni = -1.0 + 2.0 * genrand(), rno_e1_e2_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_e2_e3_ni = re1_e2_e3_ni;
	tmp.m_no_e2_e3_ni = rno_e2_e3_ni;
	tmp.m_no_e1_e3_ni = rno_e1_e3_ni;
	tmp.m_no_e1_e2_ni = rno_e1_e2_ni;

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_plane_dont_mangle_15_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_plane_dont_mangle_15_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			mul*tmp.m_e1_e2_e3_ni, // e1_e2_e3_ni
			mul*tmp.m_no_e2_e3_ni, // no_e2_e3_ni
			mul*tmp.m_no_e1_e3_ni, // no_e1_e3_ni
			mul*tmp.m_no_e1_e2_ni // no_e1_e2_ni
		);
}
/**
 * Returns random plane with a scale in the interval [0, scale)
 */
public final static plane random_plane_dont_mangle_15(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_plane_dont_mangle_15_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random line with a scale in the interval [0, scale)
 */
public final static line random_line_dont_mangle_17_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	line tmp = new line();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_e2_ni = (r0_e1*r1_e2*r2_ni-r0_e1*r1_ni*r2_e2-r0_e2*r1_e1*r2_ni+r0_e2*r1_ni*r2_e1+r0_ni*r1_e1*r2_e2-r0_ni*r1_e2*r2_e1);
	tmp.m_e1_e3_ni = (r0_e1*r1_e3*r2_ni-r0_e1*r1_ni*r2_e3-r0_e3*r1_e1*r2_ni+r0_e3*r1_ni*r2_e1+r0_ni*r1_e1*r2_e3-r0_ni*r1_e3*r2_e1);
	tmp.m_e2_e3_ni = (r0_e2*r1_e3*r2_ni-r0_e2*r1_ni*r2_e3-r0_e3*r1_e2*r2_ni+r0_e3*r1_ni*r2_e2+r0_ni*r1_e2*r2_e3-r0_ni*r1_e3*r2_e2);
	tmp.m_e1_no_ni = -(r0_e1*r1_ni*r2_no-r0_e1*r1_no*r2_ni-r0_ni*r1_e1*r2_no+r0_ni*r1_no*r2_e1+r0_no*r1_e1*r2_ni-r0_no*r1_ni*r2_e1);
	tmp.m_e2_no_ni = -(r0_e2*r1_ni*r2_no-r0_e2*r1_no*r2_ni-r0_ni*r1_e2*r2_no+r0_ni*r1_no*r2_e2+r0_no*r1_e2*r2_ni-r0_no*r1_ni*r2_e2);
	tmp.m_e3_no_ni = -(r0_e3*r1_ni*r2_no-r0_e3*r1_no*r2_ni-r0_ni*r1_e3*r2_no+r0_ni*r1_no*r2_e3+r0_no*r1_e3*r2_ni-r0_no*r1_ni*r2_e3);

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_line_dont_mangle_17_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_line_dont_mangle_17_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			mul*tmp.m_e1_e2_ni, // e1_e2_ni
			mul*tmp.m_e1_e3_ni, // e1_e3_ni
			mul*tmp.m_e2_e3_ni, // e2_e3_ni
			mul*tmp.m_e1_no_ni, // e1_no_ni
			mul*tmp.m_e2_no_ni, // e2_no_ni
			mul*tmp.m_e3_no_ni // e3_no_ni
		);
}
/**
 * Returns random line with a scale in the interval [0, scale)
 */
public final static line random_line_dont_mangle_17(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_line_dont_mangle_17_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random circle with a scale in the interval [0, scale)
 */
public final static circle random_circle_dont_mangle_18_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	circle tmp = new circle();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand();
	tmp.m_no_e1_e2 = (r0_e1*r1_e2*r2_no-r0_e1*r1_no*r2_e2-r0_e2*r1_e1*r2_no+r0_e2*r1_no*r2_e1+r0_no*r1_e1*r2_e2-r0_no*r1_e2*r2_e1);
	tmp.m_no_e1_e3 = (r0_e1*r1_e3*r2_no-r0_e1*r1_no*r2_e3-r0_e3*r1_e1*r2_no+r0_e3*r1_no*r2_e1+r0_no*r1_e1*r2_e3-r0_no*r1_e3*r2_e1);
	tmp.m_no_e2_e3 = (r0_e2*r1_e3*r2_no-r0_e2*r1_no*r2_e3-r0_e3*r1_e2*r2_no+r0_e3*r1_no*r2_e2+r0_no*r1_e2*r2_e3-r0_no*r1_e3*r2_e2);
	tmp.m_e1_e2_e3 = (r0_e1*r1_e2*r2_e3-r0_e1*r1_e3*r2_e2-r0_e2*r1_e1*r2_e3+r0_e2*r1_e3*r2_e1+r0_e3*r1_e1*r2_e2-r0_e3*r1_e2*r2_e1);
	tmp.m_no_e1_ni = (r0_e1*r1_ni*r2_no-r0_e1*r1_no*r2_ni-r0_ni*r1_e1*r2_no+r0_ni*r1_no*r2_e1+r0_no*r1_e1*r2_ni-r0_no*r1_ni*r2_e1);
	tmp.m_no_e2_ni = (r0_e2*r1_ni*r2_no-r0_e2*r1_no*r2_ni-r0_ni*r1_e2*r2_no+r0_ni*r1_no*r2_e2+r0_no*r1_e2*r2_ni-r0_no*r1_ni*r2_e2);
	tmp.m_e1_e2_ni = (r0_e1*r1_e2*r2_ni-r0_e1*r1_ni*r2_e2-r0_e2*r1_e1*r2_ni+r0_e2*r1_ni*r2_e1+r0_ni*r1_e1*r2_e2-r0_ni*r1_e2*r2_e1);
	tmp.m_no_e3_ni = (r0_e3*r1_ni*r2_no-r0_e3*r1_no*r2_ni-r0_ni*r1_e3*r2_no+r0_ni*r1_no*r2_e3+r0_no*r1_e3*r2_ni-r0_no*r1_ni*r2_e3);
	tmp.m_e1_e3_ni = (r0_e1*r1_e3*r2_ni-r0_e1*r1_ni*r2_e3-r0_e3*r1_e1*r2_ni+r0_e3*r1_ni*r2_e1+r0_ni*r1_e1*r2_e3-r0_ni*r1_e3*r2_e1);
	tmp.m_e2_e3_ni = (r0_e2*r1_e3*r2_ni-r0_e2*r1_ni*r2_e3-r0_e3*r1_e2*r2_ni+r0_e3*r1_ni*r2_e2+r0_ni*r1_e2*r2_e3-r0_ni*r1_e3*r2_e2);

	n = norm_dont_mangle_750_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_circle_dont_mangle_18_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_circle_dont_mangle_18_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			mul*tmp.m_no_e1_e2, // no_e1_e2
			mul*tmp.m_no_e1_e3, // no_e1_e3
			mul*tmp.m_no_e2_e3, // no_e2_e3
			mul*tmp.m_e1_e2_e3, // e1_e2_e3
			mul*tmp.m_no_e1_ni, // no_e1_ni
			mul*tmp.m_no_e2_ni, // no_e2_ni
			mul*tmp.m_e1_e2_ni, // e1_e2_ni
			mul*tmp.m_no_e3_ni, // no_e3_ni
			mul*tmp.m_e1_e3_ni, // e1_e3_ni
			mul*tmp.m_e2_e3_ni // e2_e3_ni
		);
}
/**
 * Returns random circle with a scale in the interval [0, scale)
 */
public final static circle random_circle_dont_mangle_18(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_circle_dont_mangle_18_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random e1_t with a scale in the interval [0, scale)
 */
public final static e1_t random_e1_t_dont_mangle_21_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new e1_t(		);
}
/**
 * Returns random e1_t with a scale in the interval [0, scale)
 */
public final static e1_t random_e1_t_dont_mangle_21(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_e1_t_dont_mangle_21_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random e2_t with a scale in the interval [0, scale)
 */
public final static e2_t random_e2_t_dont_mangle_22_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new e2_t(		);
}
/**
 * Returns random e2_t with a scale in the interval [0, scale)
 */
public final static e2_t random_e2_t_dont_mangle_22(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_e2_t_dont_mangle_22_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random I5_t with a scale in the interval [0, scale)
 */
public final static I5_t random_I5_t_dont_mangle_23_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new I5_t(		);
}
/**
 * Returns random I5_t with a scale in the interval [0, scale)
 */
public final static I5_t random_I5_t_dont_mangle_23(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_I5_t_dont_mangle_23_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random oddVersor with a scale in the interval [0, scale)
 */
public final static oddVersor random_oddVersor_dont_mangle_30_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	oddVersor tmp = new oddVersor();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand(), 
			r3_no = -1.0 + 2.0 * genrand(), r3_e1 = -1.0 + 2.0 * genrand(), r3_e2 = -1.0 + 2.0 * genrand(), r3_e3 = -1.0 + 2.0 * genrand(), r3_ni = -1.0 + 2.0 * genrand(), 
			r4_no = -1.0 + 2.0 * genrand(), r4_e1 = -1.0 + 2.0 * genrand(), r4_e2 = -1.0 + 2.0 * genrand(), r4_e3 = -1.0 + 2.0 * genrand(), r4_ni = -1.0 + 2.0 * genrand();
	tmp.m_no = (r0_e1*r1_e1*r2_e1*r3_e1*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_no-r0_e1*r1_e1*r2_e3*r3_no*r4_e3+r0_e1*r1_e1*r2_no*r3_e1*r4_e1+r0_e1*r1_e1*r2_no*r3_e2*r4_e2+r0_e1*r1_e1*r2_no*r3_e3*r4_e3+-2.0*r0_e1*r1_e1*r2_no*r3_ni*r4_no-r0_e1*r1_e2*r2_e1*r3_e2*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e3*r4_no+r0_e1*r1_e3*r2_e1*r3_no*r4_e3+r0_e1*r1_e3*r2_e3*r3_e1*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e1-r0_e1*r1_e3*r2_no*r3_e1*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_e1-r0_e1*r1_no*r2_e1*r3_e1*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_e2-r0_e1*r1_no*r2_e1*r3_e3*r4_e3+2.0*r0_e1*r1_no*r2_e1*r3_ni*r4_no+r0_e1*r1_no*r2_e2*r3_e1*r4_e2-r0_e1*r1_no*r2_e2*r3_e2*r4_e1+r0_e1*r1_no*r2_e3*r3_e1*r4_e3-r0_e1*r1_no*r2_e3*r3_e3*r4_e1+-2.0*r0_e1*r1_no*r2_ni*r3_e1*r4_no+2.0*r0_e1*r1_no*r2_ni*r3_no*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_no-r0_e2*r1_e2*r2_e3*r3_no*r4_e3+r0_e2*r1_e2*r2_no*r3_e1*r4_e1+r0_e2*r1_e2*r2_no*r3_e2*r4_e2+r0_e2*r1_e2*r2_no*r3_e3*r4_e3+-2.0*r0_e2*r1_e2*r2_no*r3_ni*r4_no-r0_e2*r1_e3*r2_e2*r3_e3*r4_no+r0_e2*r1_e3*r2_e2*r3_no*r4_e3+r0_e2*r1_e3*r2_e3*r3_e2*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_e2-r0_e2*r1_e3*r2_no*r3_e2*r4_e3+r0_e2*r1_e3*r2_no*r3_e3*r4_e2-r0_e2*r1_no*r2_e1*r3_e1*r4_e2+r0_e2*r1_no*r2_e1*r3_e2*r4_e1-r0_e2*r1_no*r2_e2*r3_e1*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_e2-r0_e2*r1_no*r2_e2*r3_e3*r4_e3+2.0*r0_e2*r1_no*r2_e2*r3_ni*r4_no+r0_e2*r1_no*r2_e3*r3_e2*r4_e3-r0_e2*r1_no*r2_e3*r3_e3*r4_e2+-2.0*r0_e2*r1_no*r2_ni*r3_e2*r4_no+2.0*r0_e2*r1_no*r2_ni*r3_no*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_no-r0_e3*r1_e1*r2_e1*r3_no*r4_e3-r0_e3*r1_e1*r2_e3*r3_e1*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e1+r0_e3*r1_e1*r2_no*r3_e1*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_e1+r0_e3*r1_e2*r2_e2*r3_e3*r4_no-r0_e3*r1_e2*r2_e2*r3_no*r4_e3-r0_e3*r1_e2*r2_e3*r3_e2*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_e2+r0_e3*r1_e2*r2_no*r3_e2*r4_e3-r0_e3*r1_e2*r2_no*r3_e3*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_no-r0_e3*r1_e3*r2_e3*r3_no*r4_e3+r0_e3*r1_e3*r2_no*r3_e1*r4_e1+r0_e3*r1_e3*r2_no*r3_e2*r4_e2+r0_e3*r1_e3*r2_no*r3_e3*r4_e3+-2.0*r0_e3*r1_e3*r2_no*r3_ni*r4_no-r0_e3*r1_no*r2_e1*r3_e1*r4_e3+r0_e3*r1_no*r2_e1*r3_e3*r4_e1-r0_e3*r1_no*r2_e2*r3_e2*r4_e3+r0_e3*r1_no*r2_e2*r3_e3*r4_e2-r0_e3*r1_no*r2_e3*r3_e1*r4_e1-r0_e3*r1_no*r2_e3*r3_e2*r4_e2-r0_e3*r1_no*r2_e3*r3_e3*r4_e3+2.0*r0_e3*r1_no*r2_e3*r3_ni*r4_no+-2.0*r0_e3*r1_no*r2_ni*r3_e3*r4_no+2.0*r0_e3*r1_no*r2_ni*r3_no*r4_e3+r0_no*r1_e1*r2_e1*r3_e1*r4_e1+r0_no*r1_e1*r2_e1*r3_e2*r4_e2+r0_no*r1_e1*r2_e1*r3_e3*r4_e3+-2.0*r0_no*r1_e1*r2_e1*r3_ni*r4_no-r0_no*r1_e1*r2_e2*r3_e1*r4_e2+r0_no*r1_e1*r2_e2*r3_e2*r4_e1-r0_no*r1_e1*r2_e3*r3_e1*r4_e3+r0_no*r1_e1*r2_e3*r3_e3*r4_e1+2.0*r0_no*r1_e1*r2_ni*r3_e1*r4_no+-2.0*r0_no*r1_e1*r2_ni*r3_no*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_e2-r0_no*r1_e2*r2_e1*r3_e2*r4_e1+r0_no*r1_e2*r2_e2*r3_e1*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_e2+r0_no*r1_e2*r2_e2*r3_e3*r4_e3+-2.0*r0_no*r1_e2*r2_e2*r3_ni*r4_no-r0_no*r1_e2*r2_e3*r3_e2*r4_e3+r0_no*r1_e2*r2_e3*r3_e3*r4_e2+2.0*r0_no*r1_e2*r2_ni*r3_e2*r4_no+-2.0*r0_no*r1_e2*r2_ni*r3_no*r4_e2+r0_no*r1_e3*r2_e1*r3_e1*r4_e3-r0_no*r1_e3*r2_e1*r3_e3*r4_e1+r0_no*r1_e3*r2_e2*r3_e2*r4_e3-r0_no*r1_e3*r2_e2*r3_e3*r4_e2+r0_no*r1_e3*r2_e3*r3_e1*r4_e1+r0_no*r1_e3*r2_e3*r3_e2*r4_e2+r0_no*r1_e3*r2_e3*r3_e3*r4_e3+-2.0*r0_no*r1_e3*r2_e3*r3_ni*r4_no+2.0*r0_no*r1_e3*r2_ni*r3_e3*r4_no+-2.0*r0_no*r1_e3*r2_ni*r3_no*r4_e3+-2.0*r0_no*r1_ni*r2_e1*r3_e1*r4_no+2.0*r0_no*r1_ni*r2_e1*r3_no*r4_e1+-2.0*r0_no*r1_ni*r2_e2*r3_e2*r4_no+2.0*r0_no*r1_ni*r2_e2*r3_no*r4_e2+-2.0*r0_no*r1_ni*r2_e3*r3_e3*r4_no+2.0*r0_no*r1_ni*r2_e3*r3_no*r4_e3+-2.0*r0_no*r1_ni*r2_no*r3_e1*r4_e1+-2.0*r0_no*r1_ni*r2_no*r3_e2*r4_e2+-2.0*r0_no*r1_ni*r2_no*r3_e3*r4_e3+4.0*r0_no*r1_ni*r2_no*r3_ni*r4_no);
	tmp.m_e1 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e1+r0_e1*r1_e1*r2_e1*r3_e2*r4_e2+r0_e1*r1_e1*r2_e1*r3_e3*r4_e3-r0_e1*r1_e1*r2_e1*r3_ni*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_ni-r0_e1*r1_e1*r2_e2*r3_e1*r4_e2+r0_e1*r1_e1*r2_e2*r3_e2*r4_e1-r0_e1*r1_e1*r2_e3*r3_e1*r4_e3+r0_e1*r1_e1*r2_e3*r3_e3*r4_e1+r0_e1*r1_e1*r2_ni*r3_e1*r4_no-r0_e1*r1_e1*r2_ni*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_e2-r0_e1*r1_e2*r2_e1*r3_e2*r4_e1+r0_e1*r1_e2*r2_e2*r3_e1*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_e2+r0_e1*r1_e2*r2_e2*r3_e3*r4_e3-r0_e1*r1_e2*r2_e2*r3_ni*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_ni-r0_e1*r1_e2*r2_e3*r3_e2*r4_e3+r0_e1*r1_e2*r2_e3*r3_e3*r4_e2+r0_e1*r1_e2*r2_ni*r3_e2*r4_no-r0_e1*r1_e2*r2_ni*r3_no*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_e3-r0_e1*r1_e3*r2_e1*r3_e3*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_e3-r0_e1*r1_e3*r2_e2*r3_e3*r4_e2+r0_e1*r1_e3*r2_e3*r3_e1*r4_e1+r0_e1*r1_e3*r2_e3*r3_e2*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_e3-r0_e1*r1_e3*r2_e3*r3_ni*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_ni+r0_e1*r1_e3*r2_ni*r3_e3*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_ni-r0_e1*r1_e3*r2_no*r3_ni*r4_e3-r0_e1*r1_ni*r2_e1*r3_e1*r4_no+r0_e1*r1_ni*r2_e1*r3_no*r4_e1-r0_e1*r1_ni*r2_e2*r3_e2*r4_no+r0_e1*r1_ni*r2_e2*r3_no*r4_e2-r0_e1*r1_ni*r2_e3*r3_e3*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_e3-r0_e1*r1_ni*r2_no*r3_e1*r4_e1-r0_e1*r1_ni*r2_no*r3_e2*r4_e2-r0_e1*r1_ni*r2_no*r3_e3*r4_e3+2.0*r0_e1*r1_ni*r2_no*r3_ni*r4_no-r0_e1*r1_no*r2_e1*r3_e1*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e2-r0_e1*r1_no*r2_e3*r3_e3*r4_ni+r0_e1*r1_no*r2_e3*r3_ni*r4_e3-r0_e1*r1_no*r2_ni*r3_e1*r4_e1-r0_e1*r1_no*r2_ni*r3_e2*r4_e2-r0_e1*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e1*r1_no*r2_ni*r3_no*r4_ni-r0_e2*r1_e1*r2_e1*r3_e1*r4_e2+r0_e2*r1_e1*r2_e1*r3_e2*r4_e1-r0_e2*r1_e1*r2_e2*r3_e1*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_e2-r0_e2*r1_e1*r2_e2*r3_e3*r4_e3+r0_e2*r1_e1*r2_e2*r3_ni*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_ni+r0_e2*r1_e1*r2_e3*r3_e2*r4_e3-r0_e2*r1_e1*r2_e3*r3_e3*r4_e2-r0_e2*r1_e1*r2_ni*r3_e2*r4_no+r0_e2*r1_e1*r2_ni*r3_no*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e2+r0_e2*r1_e2*r2_e1*r3_e1*r4_e1+r0_e2*r1_e2*r2_e1*r3_e2*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_e3-r0_e2*r1_e2*r2_e1*r3_ni*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_ni-r0_e2*r1_e2*r2_e2*r3_e1*r4_e2+r0_e2*r1_e2*r2_e2*r3_e2*r4_e1-r0_e2*r1_e2*r2_e3*r3_e1*r4_e3+r0_e2*r1_e2*r2_e3*r3_e3*r4_e1+r0_e2*r1_e2*r2_ni*r3_e1*r4_no-r0_e2*r1_e2*r2_ni*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_e3+r0_e2*r1_e3*r2_e1*r3_e3*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_e3-r0_e2*r1_e3*r2_e2*r3_e3*r4_e1-r0_e2*r1_e3*r2_e3*r3_e1*r4_e2+r0_e2*r1_e3*r2_e3*r3_e2*r4_e1+r0_e2*r1_ni*r2_e1*r3_e2*r4_no-r0_e2*r1_ni*r2_e1*r3_no*r4_e2-r0_e2*r1_ni*r2_e2*r3_e1*r4_no+r0_e2*r1_ni*r2_e2*r3_no*r4_e1+r0_e2*r1_ni*r2_no*r3_e1*r4_e2-r0_e2*r1_ni*r2_no*r3_e2*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e2-r0_e2*r1_no*r2_e2*r3_e1*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_e3+r0_e3*r1_e1*r2_e1*r3_e3*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_e3+r0_e3*r1_e1*r2_e2*r3_e3*r4_e2-r0_e3*r1_e1*r2_e3*r3_e1*r4_e1-r0_e3*r1_e1*r2_e3*r3_e2*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_e3+r0_e3*r1_e1*r2_e3*r3_ni*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_ni-r0_e3*r1_e1*r2_ni*r3_e3*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_ni+r0_e3*r1_e1*r2_no*r3_ni*r4_e3+r0_e3*r1_e2*r2_e1*r3_e2*r4_e3-r0_e3*r1_e2*r2_e1*r3_e3*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_e3+r0_e3*r1_e2*r2_e2*r3_e3*r4_e1+r0_e3*r1_e2*r2_e3*r3_e1*r4_e2-r0_e3*r1_e2*r2_e3*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e1*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_e2+r0_e3*r1_e3*r2_e1*r3_e3*r4_e3-r0_e3*r1_e3*r2_e1*r3_ni*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_ni-r0_e3*r1_e3*r2_e2*r3_e1*r4_e2+r0_e3*r1_e3*r2_e2*r3_e2*r4_e1-r0_e3*r1_e3*r2_e3*r3_e1*r4_e3+r0_e3*r1_e3*r2_e3*r3_e3*r4_e1+r0_e3*r1_e3*r2_ni*r3_e1*r4_no-r0_e3*r1_e3*r2_ni*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e1+r0_e3*r1_ni*r2_e1*r3_e3*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_e3-r0_e3*r1_ni*r2_e3*r3_e1*r4_no+r0_e3*r1_ni*r2_e3*r3_no*r4_e1+r0_e3*r1_ni*r2_no*r3_e1*r4_e3-r0_e3*r1_ni*r2_no*r3_e3*r4_e1+r0_e3*r1_no*r2_e1*r3_e3*r4_ni-r0_e3*r1_no*r2_e1*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e1*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e1+r0_e3*r1_no*r2_ni*r3_e1*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e1+r0_ni*r1_e1*r2_e1*r3_e1*r4_no-r0_ni*r1_e1*r2_e1*r3_no*r4_e1+r0_ni*r1_e1*r2_e2*r3_e2*r4_no-r0_ni*r1_e1*r2_e2*r3_no*r4_e2+r0_ni*r1_e1*r2_e3*r3_e3*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_e3+r0_ni*r1_e1*r2_no*r3_e1*r4_e1+r0_ni*r1_e1*r2_no*r3_e2*r4_e2+r0_ni*r1_e1*r2_no*r3_e3*r4_e3+-2.0*r0_ni*r1_e1*r2_no*r3_ni*r4_no-r0_ni*r1_e2*r2_e1*r3_e2*r4_no+r0_ni*r1_e2*r2_e1*r3_no*r4_e2+r0_ni*r1_e2*r2_e2*r3_e1*r4_no-r0_ni*r1_e2*r2_e2*r3_no*r4_e1-r0_ni*r1_e2*r2_no*r3_e1*r4_e2+r0_ni*r1_e2*r2_no*r3_e2*r4_e1-r0_ni*r1_e3*r2_e1*r3_e3*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_e3+r0_ni*r1_e3*r2_e3*r3_e1*r4_no-r0_ni*r1_e3*r2_e3*r3_no*r4_e1-r0_ni*r1_e3*r2_no*r3_e1*r4_e3+r0_ni*r1_e3*r2_no*r3_e3*r4_e1-r0_ni*r1_no*r2_e1*r3_e1*r4_e1-r0_ni*r1_no*r2_e1*r3_e2*r4_e2-r0_ni*r1_no*r2_e1*r3_e3*r4_e3+2.0*r0_ni*r1_no*r2_e1*r3_ni*r4_no+r0_ni*r1_no*r2_e2*r3_e1*r4_e2-r0_ni*r1_no*r2_e2*r3_e2*r4_e1+r0_ni*r1_no*r2_e3*r3_e1*r4_e3-r0_ni*r1_no*r2_e3*r3_e3*r4_e1+-2.0*r0_ni*r1_no*r2_ni*r3_e1*r4_no+2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e2+r0_no*r1_e1*r2_e3*r3_e3*r4_ni-r0_no*r1_e1*r2_e3*r3_ni*r4_e3+r0_no*r1_e1*r2_ni*r3_e1*r4_e1+r0_no*r1_e1*r2_ni*r3_e2*r4_e2+r0_no*r1_e1*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e1*r2_ni*r3_no*r4_ni-r0_no*r1_e2*r2_e1*r3_e2*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e2+r0_no*r1_e2*r2_e2*r3_e1*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e1-r0_no*r1_e3*r2_e1*r3_e3*r4_ni+r0_no*r1_e3*r2_e1*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e1*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e1-r0_no*r1_e3*r2_ni*r3_e1*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e1-r0_no*r1_ni*r2_e1*r3_e1*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e2-r0_no*r1_ni*r2_e1*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e1*r3_no*r4_ni+r0_no*r1_ni*r2_e2*r3_e1*r4_e2-r0_no*r1_ni*r2_e2*r3_e2*r4_e1+r0_no*r1_ni*r2_e3*r3_e1*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e1+-2.0*r0_no*r1_ni*r2_no*r3_e1*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e1);
	tmp.m_e2 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e2-r0_e1*r1_e1*r2_e1*r3_e2*r4_e1+r0_e1*r1_e1*r2_e2*r3_e1*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_e2+r0_e1*r1_e1*r2_e2*r3_e3*r4_e3-r0_e1*r1_e1*r2_e2*r3_ni*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_ni-r0_e1*r1_e1*r2_e3*r3_e2*r4_e3+r0_e1*r1_e1*r2_e3*r3_e3*r4_e2+r0_e1*r1_e1*r2_ni*r3_e2*r4_no-r0_e1*r1_e1*r2_ni*r3_no*r4_e2+r0_e1*r1_e1*r2_no*r3_e2*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e2-r0_e1*r1_e2*r2_e1*r3_e1*r4_e1-r0_e1*r1_e2*r2_e1*r3_e2*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_e3+r0_e1*r1_e2*r2_e1*r3_ni*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_ni+r0_e1*r1_e2*r2_e2*r3_e1*r4_e2-r0_e1*r1_e2*r2_e2*r3_e2*r4_e1+r0_e1*r1_e2*r2_e3*r3_e1*r4_e3-r0_e1*r1_e2*r2_e3*r3_e3*r4_e1-r0_e1*r1_e2*r2_ni*r3_e1*r4_no+r0_e1*r1_e2*r2_ni*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_ni+r0_e1*r1_e2*r2_no*r3_ni*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_e3-r0_e1*r1_e3*r2_e1*r3_e3*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_e3+r0_e1*r1_e3*r2_e2*r3_e3*r4_e1+r0_e1*r1_e3*r2_e3*r3_e1*r4_e2-r0_e1*r1_e3*r2_e3*r3_e2*r4_e1-r0_e1*r1_ni*r2_e1*r3_e2*r4_no+r0_e1*r1_ni*r2_e1*r3_no*r4_e2+r0_e1*r1_ni*r2_e2*r3_e1*r4_no-r0_e1*r1_ni*r2_e2*r3_no*r4_e1-r0_e1*r1_ni*r2_no*r3_e1*r4_e2+r0_e1*r1_ni*r2_no*r3_e2*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e2+r0_e1*r1_no*r2_e2*r3_e1*r4_ni-r0_e1*r1_no*r2_e2*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e2+r0_e1*r1_no*r2_ni*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e1*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_e2+r0_e2*r1_e1*r2_e1*r3_e3*r4_e3-r0_e2*r1_e1*r2_e1*r3_ni*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_ni-r0_e2*r1_e1*r2_e2*r3_e1*r4_e2+r0_e2*r1_e1*r2_e2*r3_e2*r4_e1-r0_e2*r1_e1*r2_e3*r3_e1*r4_e3+r0_e2*r1_e1*r2_e3*r3_e3*r4_e1+r0_e2*r1_e1*r2_ni*r3_e1*r4_no-r0_e2*r1_e1*r2_ni*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_ni-r0_e2*r1_e1*r2_no*r3_ni*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_e2-r0_e2*r1_e2*r2_e1*r3_e2*r4_e1+r0_e2*r1_e2*r2_e2*r3_e1*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_e2+r0_e2*r1_e2*r2_e2*r3_e3*r4_e3-r0_e2*r1_e2*r2_e2*r3_ni*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_ni-r0_e2*r1_e2*r2_e3*r3_e2*r4_e3+r0_e2*r1_e2*r2_e3*r3_e3*r4_e2+r0_e2*r1_e2*r2_ni*r3_e2*r4_no-r0_e2*r1_e2*r2_ni*r3_no*r4_e2+r0_e2*r1_e2*r2_no*r3_e2*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_e3-r0_e2*r1_e3*r2_e1*r3_e3*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_e3-r0_e2*r1_e3*r2_e2*r3_e3*r4_e2+r0_e2*r1_e3*r2_e3*r3_e1*r4_e1+r0_e2*r1_e3*r2_e3*r3_e2*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_e3-r0_e2*r1_e3*r2_e3*r3_ni*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_ni+r0_e2*r1_e3*r2_ni*r3_e3*r4_no-r0_e2*r1_e3*r2_ni*r3_no*r4_e3+r0_e2*r1_e3*r2_no*r3_e3*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e3-r0_e2*r1_ni*r2_e1*r3_e1*r4_no+r0_e2*r1_ni*r2_e1*r3_no*r4_e1-r0_e2*r1_ni*r2_e2*r3_e2*r4_no+r0_e2*r1_ni*r2_e2*r3_no*r4_e2-r0_e2*r1_ni*r2_e3*r3_e3*r4_no+r0_e2*r1_ni*r2_e3*r3_no*r4_e3-r0_e2*r1_ni*r2_no*r3_e1*r4_e1-r0_e2*r1_ni*r2_no*r3_e2*r4_e2-r0_e2*r1_ni*r2_no*r3_e3*r4_e3+2.0*r0_e2*r1_ni*r2_no*r3_ni*r4_no-r0_e2*r1_no*r2_e1*r3_e1*r4_ni+r0_e2*r1_no*r2_e1*r3_ni*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e2-r0_e2*r1_no*r2_e3*r3_e3*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e3-r0_e2*r1_no*r2_ni*r3_e1*r4_e1-r0_e2*r1_no*r2_ni*r3_e2*r4_e2-r0_e2*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e2*r1_no*r2_ni*r3_no*r4_ni-r0_e3*r1_e1*r2_e1*r3_e2*r4_e3+r0_e3*r1_e1*r2_e1*r3_e3*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_e3-r0_e3*r1_e1*r2_e2*r3_e3*r4_e1-r0_e3*r1_e1*r2_e3*r3_e1*r4_e2+r0_e3*r1_e1*r2_e3*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_e3+r0_e3*r1_e2*r2_e1*r3_e3*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_e3+r0_e3*r1_e2*r2_e2*r3_e3*r4_e2-r0_e3*r1_e2*r2_e3*r3_e1*r4_e1-r0_e3*r1_e2*r2_e3*r3_e2*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_e3+r0_e3*r1_e2*r2_e3*r3_ni*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_ni-r0_e3*r1_e2*r2_ni*r3_e3*r4_no+r0_e3*r1_e2*r2_ni*r3_no*r4_e3-r0_e3*r1_e2*r2_no*r3_e3*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e3+r0_e3*r1_e3*r2_e1*r3_e1*r4_e2-r0_e3*r1_e3*r2_e1*r3_e2*r4_e1+r0_e3*r1_e3*r2_e2*r3_e1*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_e2+r0_e3*r1_e3*r2_e2*r3_e3*r4_e3-r0_e3*r1_e3*r2_e2*r3_ni*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_ni-r0_e3*r1_e3*r2_e3*r3_e2*r4_e3+r0_e3*r1_e3*r2_e3*r3_e3*r4_e2+r0_e3*r1_e3*r2_ni*r3_e2*r4_no-r0_e3*r1_e3*r2_ni*r3_no*r4_e2+r0_e3*r1_e3*r2_no*r3_e2*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e2+r0_e3*r1_ni*r2_e2*r3_e3*r4_no-r0_e3*r1_ni*r2_e2*r3_no*r4_e3-r0_e3*r1_ni*r2_e3*r3_e2*r4_no+r0_e3*r1_ni*r2_e3*r3_no*r4_e2+r0_e3*r1_ni*r2_no*r3_e2*r4_e3-r0_e3*r1_ni*r2_no*r3_e3*r4_e2+r0_e3*r1_no*r2_e2*r3_e3*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e2*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e2+r0_ni*r1_e1*r2_e1*r3_e2*r4_no-r0_ni*r1_e1*r2_e1*r3_no*r4_e2-r0_ni*r1_e1*r2_e2*r3_e1*r4_no+r0_ni*r1_e1*r2_e2*r3_no*r4_e1+r0_ni*r1_e1*r2_no*r3_e1*r4_e2-r0_ni*r1_e1*r2_no*r3_e2*r4_e1+r0_ni*r1_e2*r2_e1*r3_e1*r4_no-r0_ni*r1_e2*r2_e1*r3_no*r4_e1+r0_ni*r1_e2*r2_e2*r3_e2*r4_no-r0_ni*r1_e2*r2_e2*r3_no*r4_e2+r0_ni*r1_e2*r2_e3*r3_e3*r4_no-r0_ni*r1_e2*r2_e3*r3_no*r4_e3+r0_ni*r1_e2*r2_no*r3_e1*r4_e1+r0_ni*r1_e2*r2_no*r3_e2*r4_e2+r0_ni*r1_e2*r2_no*r3_e3*r4_e3+-2.0*r0_ni*r1_e2*r2_no*r3_ni*r4_no-r0_ni*r1_e3*r2_e2*r3_e3*r4_no+r0_ni*r1_e3*r2_e2*r3_no*r4_e3+r0_ni*r1_e3*r2_e3*r3_e2*r4_no-r0_ni*r1_e3*r2_e3*r3_no*r4_e2-r0_ni*r1_e3*r2_no*r3_e2*r4_e3+r0_ni*r1_e3*r2_no*r3_e3*r4_e2-r0_ni*r1_no*r2_e1*r3_e1*r4_e2+r0_ni*r1_no*r2_e1*r3_e2*r4_e1-r0_ni*r1_no*r2_e2*r3_e1*r4_e1-r0_ni*r1_no*r2_e2*r3_e2*r4_e2-r0_ni*r1_no*r2_e2*r3_e3*r4_e3+2.0*r0_ni*r1_no*r2_e2*r3_ni*r4_no+r0_ni*r1_no*r2_e3*r3_e2*r4_e3-r0_ni*r1_no*r2_e3*r3_e3*r4_e2+-2.0*r0_ni*r1_no*r2_ni*r3_e2*r4_no+2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e2+r0_no*r1_e1*r2_e1*r3_e2*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e2-r0_no*r1_e1*r2_e2*r3_e1*r4_ni+r0_no*r1_e1*r2_e2*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e2-r0_no*r1_e1*r2_ni*r3_e2*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_ni-r0_no*r1_e2*r2_e1*r3_ni*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e2+r0_no*r1_e2*r2_e3*r3_e3*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e3+r0_no*r1_e2*r2_ni*r3_e1*r4_e1+r0_no*r1_e2*r2_ni*r3_e2*r4_e2+r0_no*r1_e2*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e2*r2_ni*r3_no*r4_ni-r0_no*r1_e3*r2_e2*r3_e3*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e2*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e2-r0_no*r1_ni*r2_e1*r3_e1*r4_e2+r0_no*r1_ni*r2_e1*r3_e2*r4_e1-r0_no*r1_ni*r2_e2*r3_e1*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e2-r0_no*r1_ni*r2_e2*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e2*r3_no*r4_ni+r0_no*r1_ni*r2_e3*r3_e2*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e2+-2.0*r0_no*r1_ni*r2_no*r3_e2*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e2);
	tmp.m_e3 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e3-r0_e1*r1_e1*r2_e1*r3_e3*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_e3-r0_e1*r1_e1*r2_e2*r3_e3*r4_e2+r0_e1*r1_e1*r2_e3*r3_e1*r4_e1+r0_e1*r1_e1*r2_e3*r3_e2*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_e3-r0_e1*r1_e1*r2_e3*r3_ni*r4_no-r0_e1*r1_e1*r2_e3*r3_no*r4_ni+r0_e1*r1_e1*r2_ni*r3_e3*r4_no-r0_e1*r1_e1*r2_ni*r3_no*r4_e3+r0_e1*r1_e1*r2_no*r3_e3*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e3-r0_e1*r1_e2*r2_e1*r3_e2*r4_e3+r0_e1*r1_e2*r2_e1*r3_e3*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_e3-r0_e1*r1_e2*r2_e2*r3_e3*r4_e1-r0_e1*r1_e2*r2_e3*r3_e1*r4_e2+r0_e1*r1_e2*r2_e3*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e1*r4_e1-r0_e1*r1_e3*r2_e1*r3_e2*r4_e2-r0_e1*r1_e3*r2_e1*r3_e3*r4_e3+r0_e1*r1_e3*r2_e1*r3_ni*r4_no+r0_e1*r1_e3*r2_e1*r3_no*r4_ni+r0_e1*r1_e3*r2_e2*r3_e1*r4_e2-r0_e1*r1_e3*r2_e2*r3_e2*r4_e1+r0_e1*r1_e3*r2_e3*r3_e1*r4_e3-r0_e1*r1_e3*r2_e3*r3_e3*r4_e1-r0_e1*r1_e3*r2_ni*r3_e1*r4_no+r0_e1*r1_e3*r2_ni*r3_no*r4_e1-r0_e1*r1_e3*r2_no*r3_e1*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e1-r0_e1*r1_ni*r2_e1*r3_e3*r4_no+r0_e1*r1_ni*r2_e1*r3_no*r4_e3+r0_e1*r1_ni*r2_e3*r3_e1*r4_no-r0_e1*r1_ni*r2_e3*r3_no*r4_e1-r0_e1*r1_ni*r2_no*r3_e1*r4_e3+r0_e1*r1_ni*r2_no*r3_e3*r4_e1-r0_e1*r1_no*r2_e1*r3_e3*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e1*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_e3-r0_e2*r1_e1*r2_e1*r3_e3*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_e3+r0_e2*r1_e1*r2_e2*r3_e3*r4_e1+r0_e2*r1_e1*r2_e3*r3_e1*r4_e2-r0_e2*r1_e1*r2_e3*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_e3-r0_e2*r1_e2*r2_e1*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_e3-r0_e2*r1_e2*r2_e2*r3_e3*r4_e2+r0_e2*r1_e2*r2_e3*r3_e1*r4_e1+r0_e2*r1_e2*r2_e3*r3_e2*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_e3-r0_e2*r1_e2*r2_e3*r3_ni*r4_no-r0_e2*r1_e2*r2_e3*r3_no*r4_ni+r0_e2*r1_e2*r2_ni*r3_e3*r4_no-r0_e2*r1_e2*r2_ni*r3_no*r4_e3+r0_e2*r1_e2*r2_no*r3_e3*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e3-r0_e2*r1_e3*r2_e1*r3_e1*r4_e2+r0_e2*r1_e3*r2_e1*r3_e2*r4_e1-r0_e2*r1_e3*r2_e2*r3_e1*r4_e1-r0_e2*r1_e3*r2_e2*r3_e2*r4_e2-r0_e2*r1_e3*r2_e2*r3_e3*r4_e3+r0_e2*r1_e3*r2_e2*r3_ni*r4_no+r0_e2*r1_e3*r2_e2*r3_no*r4_ni+r0_e2*r1_e3*r2_e3*r3_e2*r4_e3-r0_e2*r1_e3*r2_e3*r3_e3*r4_e2-r0_e2*r1_e3*r2_ni*r3_e2*r4_no+r0_e2*r1_e3*r2_ni*r3_no*r4_e2-r0_e2*r1_e3*r2_no*r3_e2*r4_ni+r0_e2*r1_e3*r2_no*r3_ni*r4_e2-r0_e2*r1_ni*r2_e2*r3_e3*r4_no+r0_e2*r1_ni*r2_e2*r3_no*r4_e3+r0_e2*r1_ni*r2_e3*r3_e2*r4_no-r0_e2*r1_ni*r2_e3*r3_no*r4_e2-r0_e2*r1_ni*r2_no*r3_e2*r4_e3+r0_e2*r1_ni*r2_no*r3_e3*r4_e2-r0_e2*r1_no*r2_e2*r3_e3*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e3+r0_e2*r1_no*r2_e3*r3_e2*r4_ni-r0_e2*r1_no*r2_e3*r3_ni*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e3+r0_e2*r1_no*r2_ni*r3_e3*r4_e2+r0_e3*r1_e1*r2_e1*r3_e1*r4_e1+r0_e3*r1_e1*r2_e1*r3_e2*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_e3-r0_e3*r1_e1*r2_e1*r3_ni*r4_no-r0_e3*r1_e1*r2_e1*r3_no*r4_ni-r0_e3*r1_e1*r2_e2*r3_e1*r4_e2+r0_e3*r1_e1*r2_e2*r3_e2*r4_e1-r0_e3*r1_e1*r2_e3*r3_e1*r4_e3+r0_e3*r1_e1*r2_e3*r3_e3*r4_e1+r0_e3*r1_e1*r2_ni*r3_e1*r4_no-r0_e3*r1_e1*r2_ni*r3_no*r4_e1+r0_e3*r1_e1*r2_no*r3_e1*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e1+r0_e3*r1_e2*r2_e1*r3_e1*r4_e2-r0_e3*r1_e2*r2_e1*r3_e2*r4_e1+r0_e3*r1_e2*r2_e2*r3_e1*r4_e1+r0_e3*r1_e2*r2_e2*r3_e2*r4_e2+r0_e3*r1_e2*r2_e2*r3_e3*r4_e3-r0_e3*r1_e2*r2_e2*r3_ni*r4_no-r0_e3*r1_e2*r2_e2*r3_no*r4_ni-r0_e3*r1_e2*r2_e3*r3_e2*r4_e3+r0_e3*r1_e2*r2_e3*r3_e3*r4_e2+r0_e3*r1_e2*r2_ni*r3_e2*r4_no-r0_e3*r1_e2*r2_ni*r3_no*r4_e2+r0_e3*r1_e2*r2_no*r3_e2*r4_ni-r0_e3*r1_e2*r2_no*r3_ni*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_e3-r0_e3*r1_e3*r2_e1*r3_e3*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_e3-r0_e3*r1_e3*r2_e2*r3_e3*r4_e2+r0_e3*r1_e3*r2_e3*r3_e1*r4_e1+r0_e3*r1_e3*r2_e3*r3_e2*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_e3-r0_e3*r1_e3*r2_e3*r3_ni*r4_no-r0_e3*r1_e3*r2_e3*r3_no*r4_ni+r0_e3*r1_e3*r2_ni*r3_e3*r4_no-r0_e3*r1_e3*r2_ni*r3_no*r4_e3+r0_e3*r1_e3*r2_no*r3_e3*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e3-r0_e3*r1_ni*r2_e1*r3_e1*r4_no+r0_e3*r1_ni*r2_e1*r3_no*r4_e1-r0_e3*r1_ni*r2_e2*r3_e2*r4_no+r0_e3*r1_ni*r2_e2*r3_no*r4_e2-r0_e3*r1_ni*r2_e3*r3_e3*r4_no+r0_e3*r1_ni*r2_e3*r3_no*r4_e3-r0_e3*r1_ni*r2_no*r3_e1*r4_e1-r0_e3*r1_ni*r2_no*r3_e2*r4_e2-r0_e3*r1_ni*r2_no*r3_e3*r4_e3+2.0*r0_e3*r1_ni*r2_no*r3_ni*r4_no-r0_e3*r1_no*r2_e1*r3_e1*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e1-r0_e3*r1_no*r2_e2*r3_e2*r4_ni+r0_e3*r1_no*r2_e2*r3_ni*r4_e2-r0_e3*r1_no*r2_e3*r3_e3*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e3-r0_e3*r1_no*r2_ni*r3_e1*r4_e1-r0_e3*r1_no*r2_ni*r3_e2*r4_e2-r0_e3*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e3*r1_no*r2_ni*r3_no*r4_ni+r0_ni*r1_e1*r2_e1*r3_e3*r4_no-r0_ni*r1_e1*r2_e1*r3_no*r4_e3-r0_ni*r1_e1*r2_e3*r3_e1*r4_no+r0_ni*r1_e1*r2_e3*r3_no*r4_e1+r0_ni*r1_e1*r2_no*r3_e1*r4_e3-r0_ni*r1_e1*r2_no*r3_e3*r4_e1+r0_ni*r1_e2*r2_e2*r3_e3*r4_no-r0_ni*r1_e2*r2_e2*r3_no*r4_e3-r0_ni*r1_e2*r2_e3*r3_e2*r4_no+r0_ni*r1_e2*r2_e3*r3_no*r4_e2+r0_ni*r1_e2*r2_no*r3_e2*r4_e3-r0_ni*r1_e2*r2_no*r3_e3*r4_e2+r0_ni*r1_e3*r2_e1*r3_e1*r4_no-r0_ni*r1_e3*r2_e1*r3_no*r4_e1+r0_ni*r1_e3*r2_e2*r3_e2*r4_no-r0_ni*r1_e3*r2_e2*r3_no*r4_e2+r0_ni*r1_e3*r2_e3*r3_e3*r4_no-r0_ni*r1_e3*r2_e3*r3_no*r4_e3+r0_ni*r1_e3*r2_no*r3_e1*r4_e1+r0_ni*r1_e3*r2_no*r3_e2*r4_e2+r0_ni*r1_e3*r2_no*r3_e3*r4_e3+-2.0*r0_ni*r1_e3*r2_no*r3_ni*r4_no-r0_ni*r1_no*r2_e1*r3_e1*r4_e3+r0_ni*r1_no*r2_e1*r3_e3*r4_e1-r0_ni*r1_no*r2_e2*r3_e2*r4_e3+r0_ni*r1_no*r2_e2*r3_e3*r4_e2-r0_ni*r1_no*r2_e3*r3_e1*r4_e1-r0_ni*r1_no*r2_e3*r3_e2*r4_e2-r0_ni*r1_no*r2_e3*r3_e3*r4_e3+2.0*r0_ni*r1_no*r2_e3*r3_ni*r4_no+-2.0*r0_ni*r1_no*r2_ni*r3_e3*r4_no+2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e3+r0_no*r1_e1*r2_e1*r3_e3*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e1*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e1+r0_no*r1_e2*r2_e2*r3_e3*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e3-r0_no*r1_e2*r2_e3*r3_e2*r4_ni+r0_no*r1_e2*r2_e3*r3_ni*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e3-r0_no*r1_e2*r2_ni*r3_e3*r4_e2+r0_no*r1_e3*r2_e1*r3_e1*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e1+r0_no*r1_e3*r2_e2*r3_e2*r4_ni-r0_no*r1_e3*r2_e2*r3_ni*r4_e2+r0_no*r1_e3*r2_e3*r3_e3*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e3+r0_no*r1_e3*r2_ni*r3_e1*r4_e1+r0_no*r1_e3*r2_ni*r3_e2*r4_e2+r0_no*r1_e3*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e3*r2_ni*r3_no*r4_ni-r0_no*r1_ni*r2_e1*r3_e1*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e3+r0_no*r1_ni*r2_e2*r3_e3*r4_e2-r0_no*r1_ni*r2_e3*r3_e1*r4_e1-r0_no*r1_ni*r2_e3*r3_e2*r4_e2-r0_no*r1_ni*r2_e3*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e3*r3_no*r4_ni+-2.0*r0_no*r1_ni*r2_no*r3_e3*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e3);
	tmp.m_ni = (r0_e1*r1_e1*r2_e1*r3_e1*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_ni-r0_e1*r1_e1*r2_e2*r3_ni*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_ni-r0_e1*r1_e1*r2_e3*r3_ni*r4_e3+r0_e1*r1_e1*r2_ni*r3_e1*r4_e1+r0_e1*r1_e1*r2_ni*r3_e2*r4_e2+r0_e1*r1_e1*r2_ni*r3_e3*r4_e3+-2.0*r0_e1*r1_e1*r2_ni*r3_no*r4_ni-r0_e1*r1_e2*r2_e1*r3_e2*r4_ni+r0_e1*r1_e2*r2_e1*r3_ni*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e1-r0_e1*r1_e2*r2_ni*r3_e1*r4_e2+r0_e1*r1_e2*r2_ni*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e3*r4_ni+r0_e1*r1_e3*r2_e1*r3_ni*r4_e3+r0_e1*r1_e3*r2_e3*r3_e1*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e1-r0_e1*r1_e3*r2_ni*r3_e1*r4_e3+r0_e1*r1_e3*r2_ni*r3_e3*r4_e1-r0_e1*r1_ni*r2_e1*r3_e1*r4_e1-r0_e1*r1_ni*r2_e1*r3_e2*r4_e2-r0_e1*r1_ni*r2_e1*r3_e3*r4_e3+2.0*r0_e1*r1_ni*r2_e1*r3_no*r4_ni+r0_e1*r1_ni*r2_e2*r3_e1*r4_e2-r0_e1*r1_ni*r2_e2*r3_e2*r4_e1+r0_e1*r1_ni*r2_e3*r3_e1*r4_e3-r0_e1*r1_ni*r2_e3*r3_e3*r4_e1+-2.0*r0_e1*r1_ni*r2_no*r3_e1*r4_ni+2.0*r0_e1*r1_ni*r2_no*r3_ni*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_ni-r0_e2*r1_e1*r2_e1*r3_ni*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e1+r0_e2*r1_e1*r2_ni*r3_e1*r4_e2-r0_e2*r1_e1*r2_ni*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_ni-r0_e2*r1_e2*r2_e2*r3_ni*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_ni-r0_e2*r1_e2*r2_e3*r3_ni*r4_e3+r0_e2*r1_e2*r2_ni*r3_e1*r4_e1+r0_e2*r1_e2*r2_ni*r3_e2*r4_e2+r0_e2*r1_e2*r2_ni*r3_e3*r4_e3+-2.0*r0_e2*r1_e2*r2_ni*r3_no*r4_ni-r0_e2*r1_e3*r2_e2*r3_e3*r4_ni+r0_e2*r1_e3*r2_e2*r3_ni*r4_e3+r0_e2*r1_e3*r2_e3*r3_e2*r4_ni-r0_e2*r1_e3*r2_e3*r3_ni*r4_e2-r0_e2*r1_e3*r2_ni*r3_e2*r4_e3+r0_e2*r1_e3*r2_ni*r3_e3*r4_e2-r0_e2*r1_ni*r2_e1*r3_e1*r4_e2+r0_e2*r1_ni*r2_e1*r3_e2*r4_e1-r0_e2*r1_ni*r2_e2*r3_e1*r4_e1-r0_e2*r1_ni*r2_e2*r3_e2*r4_e2-r0_e2*r1_ni*r2_e2*r3_e3*r4_e3+2.0*r0_e2*r1_ni*r2_e2*r3_no*r4_ni+r0_e2*r1_ni*r2_e3*r3_e2*r4_e3-r0_e2*r1_ni*r2_e3*r3_e3*r4_e2+-2.0*r0_e2*r1_ni*r2_no*r3_e2*r4_ni+2.0*r0_e2*r1_ni*r2_no*r3_ni*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_ni-r0_e3*r1_e1*r2_e1*r3_ni*r4_e3-r0_e3*r1_e1*r2_e3*r3_e1*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e1+r0_e3*r1_e1*r2_ni*r3_e1*r4_e3-r0_e3*r1_e1*r2_ni*r3_e3*r4_e1+r0_e3*r1_e2*r2_e2*r3_e3*r4_ni-r0_e3*r1_e2*r2_e2*r3_ni*r4_e3-r0_e3*r1_e2*r2_e3*r3_e2*r4_ni+r0_e3*r1_e2*r2_e3*r3_ni*r4_e2+r0_e3*r1_e2*r2_ni*r3_e2*r4_e3-r0_e3*r1_e2*r2_ni*r3_e3*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_ni-r0_e3*r1_e3*r2_e2*r3_ni*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_ni-r0_e3*r1_e3*r2_e3*r3_ni*r4_e3+r0_e3*r1_e3*r2_ni*r3_e1*r4_e1+r0_e3*r1_e3*r2_ni*r3_e2*r4_e2+r0_e3*r1_e3*r2_ni*r3_e3*r4_e3+-2.0*r0_e3*r1_e3*r2_ni*r3_no*r4_ni-r0_e3*r1_ni*r2_e1*r3_e1*r4_e3+r0_e3*r1_ni*r2_e1*r3_e3*r4_e1-r0_e3*r1_ni*r2_e2*r3_e2*r4_e3+r0_e3*r1_ni*r2_e2*r3_e3*r4_e2-r0_e3*r1_ni*r2_e3*r3_e1*r4_e1-r0_e3*r1_ni*r2_e3*r3_e2*r4_e2-r0_e3*r1_ni*r2_e3*r3_e3*r4_e3+2.0*r0_e3*r1_ni*r2_e3*r3_no*r4_ni+-2.0*r0_e3*r1_ni*r2_no*r3_e3*r4_ni+2.0*r0_e3*r1_ni*r2_no*r3_ni*r4_e3+r0_ni*r1_e1*r2_e1*r3_e1*r4_e1+r0_ni*r1_e1*r2_e1*r3_e2*r4_e2+r0_ni*r1_e1*r2_e1*r3_e3*r4_e3+-2.0*r0_ni*r1_e1*r2_e1*r3_no*r4_ni-r0_ni*r1_e1*r2_e2*r3_e1*r4_e2+r0_ni*r1_e1*r2_e2*r3_e2*r4_e1-r0_ni*r1_e1*r2_e3*r3_e1*r4_e3+r0_ni*r1_e1*r2_e3*r3_e3*r4_e1+2.0*r0_ni*r1_e1*r2_no*r3_e1*r4_ni+-2.0*r0_ni*r1_e1*r2_no*r3_ni*r4_e1+r0_ni*r1_e2*r2_e1*r3_e1*r4_e2-r0_ni*r1_e2*r2_e1*r3_e2*r4_e1+r0_ni*r1_e2*r2_e2*r3_e1*r4_e1+r0_ni*r1_e2*r2_e2*r3_e2*r4_e2+r0_ni*r1_e2*r2_e2*r3_e3*r4_e3+-2.0*r0_ni*r1_e2*r2_e2*r3_no*r4_ni-r0_ni*r1_e2*r2_e3*r3_e2*r4_e3+r0_ni*r1_e2*r2_e3*r3_e3*r4_e2+2.0*r0_ni*r1_e2*r2_no*r3_e2*r4_ni+-2.0*r0_ni*r1_e2*r2_no*r3_ni*r4_e2+r0_ni*r1_e3*r2_e1*r3_e1*r4_e3-r0_ni*r1_e3*r2_e1*r3_e3*r4_e1+r0_ni*r1_e3*r2_e2*r3_e2*r4_e3-r0_ni*r1_e3*r2_e2*r3_e3*r4_e2+r0_ni*r1_e3*r2_e3*r3_e1*r4_e1+r0_ni*r1_e3*r2_e3*r3_e2*r4_e2+r0_ni*r1_e3*r2_e3*r3_e3*r4_e3+-2.0*r0_ni*r1_e3*r2_e3*r3_no*r4_ni+2.0*r0_ni*r1_e3*r2_no*r3_e3*r4_ni+-2.0*r0_ni*r1_e3*r2_no*r3_ni*r4_e3+-2.0*r0_ni*r1_no*r2_e1*r3_e1*r4_ni+2.0*r0_ni*r1_no*r2_e1*r3_ni*r4_e1+-2.0*r0_ni*r1_no*r2_e2*r3_e2*r4_ni+2.0*r0_ni*r1_no*r2_e2*r3_ni*r4_e2+-2.0*r0_ni*r1_no*r2_e3*r3_e3*r4_ni+2.0*r0_ni*r1_no*r2_e3*r3_ni*r4_e3+-2.0*r0_ni*r1_no*r2_ni*r3_e1*r4_e1+-2.0*r0_ni*r1_no*r2_ni*r3_e2*r4_e2+-2.0*r0_ni*r1_no*r2_ni*r3_e3*r4_e3+4.0*r0_ni*r1_no*r2_ni*r3_no*r4_ni);
	tmp.m_no_e1_e2 = (r0_e1*r1_e1*r2_e1*r3_e2*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_no+r0_e1*r1_e1*r2_e2*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_e2-r0_e1*r1_e1*r2_no*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_no-r0_e1*r1_e2*r2_e1*r3_no*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_e3+r0_e1*r1_e2*r2_no*r3_e1*r4_e1+r0_e1*r1_e2*r2_no*r3_e2*r4_e2+r0_e1*r1_e2*r2_no*r3_e3*r4_e3+-2.0*r0_e1*r1_e2*r2_no*r3_ni*r4_no-r0_e1*r1_e3*r2_e2*r3_e3*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_e3+r0_e1*r1_e3*r2_e3*r3_e2*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_e2-r0_e1*r1_no*r2_e1*r3_e1*r4_e2+r0_e1*r1_no*r2_e1*r3_e2*r4_e1-r0_e1*r1_no*r2_e2*r3_e1*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_e2-r0_e1*r1_no*r2_e2*r3_e3*r4_e3+2.0*r0_e1*r1_no*r2_e2*r3_ni*r4_no+r0_e1*r1_no*r2_e3*r3_e2*r4_e3-r0_e1*r1_no*r2_e3*r3_e3*r4_e2+-2.0*r0_e1*r1_no*r2_ni*r3_e2*r4_no+2.0*r0_e1*r1_no*r2_ni*r3_no*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_no+r0_e2*r1_e1*r2_e1*r3_no*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_e3-r0_e2*r1_e1*r2_no*r3_e1*r4_e1-r0_e2*r1_e1*r2_no*r3_e2*r4_e2-r0_e2*r1_e1*r2_no*r3_e3*r4_e3+2.0*r0_e2*r1_e1*r2_no*r3_ni*r4_no+r0_e2*r1_e2*r2_e1*r3_e2*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_no+r0_e2*r1_e2*r2_e2*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_e2-r0_e2*r1_e2*r2_no*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e3*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_e3-r0_e2*r1_e3*r2_e3*r3_e1*r4_no+r0_e2*r1_e3*r2_e3*r3_no*r4_e1+r0_e2*r1_e3*r2_no*r3_e1*r4_e3-r0_e2*r1_e3*r2_no*r3_e3*r4_e1+r0_e2*r1_no*r2_e1*r3_e1*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_e2+r0_e2*r1_no*r2_e1*r3_e3*r4_e3+-2.0*r0_e2*r1_no*r2_e1*r3_ni*r4_no-r0_e2*r1_no*r2_e2*r3_e1*r4_e2+r0_e2*r1_no*r2_e2*r3_e2*r4_e1-r0_e2*r1_no*r2_e3*r3_e1*r4_e3+r0_e2*r1_no*r2_e3*r3_e3*r4_e1+2.0*r0_e2*r1_no*r2_ni*r3_e1*r4_no+-2.0*r0_e2*r1_no*r2_ni*r3_no*r4_e1+r0_e3*r1_e1*r2_e2*r3_e3*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_e3-r0_e3*r1_e1*r2_e3*r3_e2*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_e3+r0_e3*r1_e2*r2_e3*r3_e1*r4_no-r0_e3*r1_e2*r2_e3*r3_no*r4_e1-r0_e3*r1_e2*r2_no*r3_e1*r4_e3+r0_e3*r1_e2*r2_no*r3_e3*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_no+r0_e3*r1_e3*r2_e2*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_e2-r0_e3*r1_e3*r2_no*r3_e2*r4_e1-r0_e3*r1_no*r2_e1*r3_e2*r4_e3+r0_e3*r1_no*r2_e1*r3_e3*r4_e2+r0_e3*r1_no*r2_e2*r3_e1*r4_e3-r0_e3*r1_no*r2_e2*r3_e3*r4_e1-r0_e3*r1_no*r2_e3*r3_e1*r4_e2+r0_e3*r1_no*r2_e3*r3_e2*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_e2-r0_no*r1_e1*r2_e1*r3_e2*r4_e1+r0_no*r1_e1*r2_e2*r3_e1*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_e2+r0_no*r1_e1*r2_e2*r3_e3*r4_e3+-2.0*r0_no*r1_e1*r2_e2*r3_ni*r4_no-r0_no*r1_e1*r2_e3*r3_e2*r4_e3+r0_no*r1_e1*r2_e3*r3_e3*r4_e2+2.0*r0_no*r1_e1*r2_ni*r3_e2*r4_no+-2.0*r0_no*r1_e1*r2_ni*r3_no*r4_e2-r0_no*r1_e2*r2_e1*r3_e1*r4_e1-r0_no*r1_e2*r2_e1*r3_e2*r4_e2-r0_no*r1_e2*r2_e1*r3_e3*r4_e3+2.0*r0_no*r1_e2*r2_e1*r3_ni*r4_no+r0_no*r1_e2*r2_e2*r3_e1*r4_e2-r0_no*r1_e2*r2_e2*r3_e2*r4_e1+r0_no*r1_e2*r2_e3*r3_e1*r4_e3-r0_no*r1_e2*r2_e3*r3_e3*r4_e1+-2.0*r0_no*r1_e2*r2_ni*r3_e1*r4_no+2.0*r0_no*r1_e2*r2_ni*r3_no*r4_e1+r0_no*r1_e3*r2_e1*r3_e2*r4_e3-r0_no*r1_e3*r2_e1*r3_e3*r4_e2-r0_no*r1_e3*r2_e2*r3_e1*r4_e3+r0_no*r1_e3*r2_e2*r3_e3*r4_e1+r0_no*r1_e3*r2_e3*r3_e1*r4_e2-r0_no*r1_e3*r2_e3*r3_e2*r4_e1+-2.0*r0_no*r1_ni*r2_e1*r3_e2*r4_no+2.0*r0_no*r1_ni*r2_e1*r3_no*r4_e2+2.0*r0_no*r1_ni*r2_e2*r3_e1*r4_no+-2.0*r0_no*r1_ni*r2_e2*r3_no*r4_e1+-2.0*r0_no*r1_ni*r2_no*r3_e1*r4_e2+2.0*r0_no*r1_ni*r2_no*r3_e2*r4_e1);
	tmp.m_no_e1_e3 = (r0_e1*r1_e1*r2_e1*r3_e3*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e3-r0_e1*r1_e1*r2_e3*r3_e1*r4_no+r0_e1*r1_e1*r2_e3*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_e3-r0_e1*r1_e1*r2_no*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e3*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e3-r0_e1*r1_e2*r2_e3*r3_e2*r4_no+r0_e1*r1_e2*r2_e3*r3_no*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_e3-r0_e1*r1_e2*r2_no*r3_e3*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_no-r0_e1*r1_e3*r2_e1*r3_no*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_no-r0_e1*r1_e3*r2_e2*r3_no*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e3+r0_e1*r1_e3*r2_no*r3_e1*r4_e1+r0_e1*r1_e3*r2_no*r3_e2*r4_e2+r0_e1*r1_e3*r2_no*r3_e3*r4_e3+-2.0*r0_e1*r1_e3*r2_no*r3_ni*r4_no-r0_e1*r1_no*r2_e1*r3_e1*r4_e3+r0_e1*r1_no*r2_e1*r3_e3*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_e3+r0_e1*r1_no*r2_e2*r3_e3*r4_e2-r0_e1*r1_no*r2_e3*r3_e1*r4_e1-r0_e1*r1_no*r2_e3*r3_e2*r4_e2-r0_e1*r1_no*r2_e3*r3_e3*r4_e3+2.0*r0_e1*r1_no*r2_e3*r3_ni*r4_no+-2.0*r0_e1*r1_no*r2_ni*r3_e3*r4_no+2.0*r0_e1*r1_no*r2_ni*r3_no*r4_e3-r0_e2*r1_e1*r2_e2*r3_e3*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e3+r0_e2*r1_e1*r2_e3*r3_e2*r4_no-r0_e2*r1_e1*r2_e3*r3_no*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_e3+r0_e2*r1_e1*r2_no*r3_e3*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e3-r0_e2*r1_e2*r2_e3*r3_e1*r4_no+r0_e2*r1_e2*r2_e3*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_e3-r0_e2*r1_e2*r2_no*r3_e3*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_no+r0_e2*r1_e3*r2_e1*r3_no*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_no-r0_e2*r1_e3*r2_e2*r3_no*r4_e1-r0_e2*r1_e3*r2_no*r3_e1*r4_e2+r0_e2*r1_e3*r2_no*r3_e2*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_e3-r0_e2*r1_no*r2_e1*r3_e3*r4_e2-r0_e2*r1_no*r2_e2*r3_e1*r4_e3+r0_e2*r1_no*r2_e2*r3_e3*r4_e1+r0_e2*r1_no*r2_e3*r3_e1*r4_e2-r0_e2*r1_no*r2_e3*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_no+r0_e3*r1_e1*r2_e1*r3_no*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_no+r0_e3*r1_e1*r2_e2*r3_no*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e3-r0_e3*r1_e1*r2_no*r3_e1*r4_e1-r0_e3*r1_e1*r2_no*r3_e2*r4_e2-r0_e3*r1_e1*r2_no*r3_e3*r4_e3+2.0*r0_e3*r1_e1*r2_no*r3_ni*r4_no+r0_e3*r1_e2*r2_e1*r3_e2*r4_no-r0_e3*r1_e2*r2_e1*r3_no*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_no+r0_e3*r1_e2*r2_e2*r3_no*r4_e1+r0_e3*r1_e2*r2_no*r3_e1*r4_e2-r0_e3*r1_e2*r2_no*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e3*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e3-r0_e3*r1_e3*r2_e3*r3_e1*r4_no+r0_e3*r1_e3*r2_e3*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_e3-r0_e3*r1_e3*r2_no*r3_e3*r4_e1+r0_e3*r1_no*r2_e1*r3_e1*r4_e1+r0_e3*r1_no*r2_e1*r3_e2*r4_e2+r0_e3*r1_no*r2_e1*r3_e3*r4_e3+-2.0*r0_e3*r1_no*r2_e1*r3_ni*r4_no-r0_e3*r1_no*r2_e2*r3_e1*r4_e2+r0_e3*r1_no*r2_e2*r3_e2*r4_e1-r0_e3*r1_no*r2_e3*r3_e1*r4_e3+r0_e3*r1_no*r2_e3*r3_e3*r4_e1+2.0*r0_e3*r1_no*r2_ni*r3_e1*r4_no+-2.0*r0_e3*r1_no*r2_ni*r3_no*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_e3-r0_no*r1_e1*r2_e1*r3_e3*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_e3-r0_no*r1_e1*r2_e2*r3_e3*r4_e2+r0_no*r1_e1*r2_e3*r3_e1*r4_e1+r0_no*r1_e1*r2_e3*r3_e2*r4_e2+r0_no*r1_e1*r2_e3*r3_e3*r4_e3+-2.0*r0_no*r1_e1*r2_e3*r3_ni*r4_no+2.0*r0_no*r1_e1*r2_ni*r3_e3*r4_no+-2.0*r0_no*r1_e1*r2_ni*r3_no*r4_e3-r0_no*r1_e2*r2_e1*r3_e2*r4_e3+r0_no*r1_e2*r2_e1*r3_e3*r4_e2+r0_no*r1_e2*r2_e2*r3_e1*r4_e3-r0_no*r1_e2*r2_e2*r3_e3*r4_e1-r0_no*r1_e2*r2_e3*r3_e1*r4_e2+r0_no*r1_e2*r2_e3*r3_e2*r4_e1-r0_no*r1_e3*r2_e1*r3_e1*r4_e1-r0_no*r1_e3*r2_e1*r3_e2*r4_e2-r0_no*r1_e3*r2_e1*r3_e3*r4_e3+2.0*r0_no*r1_e3*r2_e1*r3_ni*r4_no+r0_no*r1_e3*r2_e2*r3_e1*r4_e2-r0_no*r1_e3*r2_e2*r3_e2*r4_e1+r0_no*r1_e3*r2_e3*r3_e1*r4_e3-r0_no*r1_e3*r2_e3*r3_e3*r4_e1+-2.0*r0_no*r1_e3*r2_ni*r3_e1*r4_no+2.0*r0_no*r1_e3*r2_ni*r3_no*r4_e1+-2.0*r0_no*r1_ni*r2_e1*r3_e3*r4_no+2.0*r0_no*r1_ni*r2_e1*r3_no*r4_e3+2.0*r0_no*r1_ni*r2_e3*r3_e1*r4_no+-2.0*r0_no*r1_ni*r2_e3*r3_no*r4_e1+-2.0*r0_no*r1_ni*r2_no*r3_e1*r4_e3+2.0*r0_no*r1_ni*r2_no*r3_e3*r4_e1);
	tmp.m_no_e2_e3 = (r0_e1*r1_e1*r2_e2*r3_e3*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_e3-r0_e1*r1_e1*r2_e3*r3_e2*r4_no+r0_e1*r1_e1*r2_e3*r3_no*r4_e2+r0_e1*r1_e1*r2_no*r3_e2*r4_e3-r0_e1*r1_e1*r2_no*r3_e3*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_e3+r0_e1*r1_e2*r2_e3*r3_e1*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_e3+r0_e1*r1_e2*r2_no*r3_e3*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_no-r0_e1*r1_e3*r2_e1*r3_no*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_e1+r0_e1*r1_e3*r2_no*r3_e1*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_e3+r0_e1*r1_no*r2_e1*r3_e3*r4_e2+r0_e1*r1_no*r2_e2*r3_e1*r4_e3-r0_e1*r1_no*r2_e2*r3_e3*r4_e1-r0_e1*r1_no*r2_e3*r3_e1*r4_e2+r0_e1*r1_no*r2_e3*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e3*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_e3-r0_e2*r1_e1*r2_e3*r3_e1*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_e3-r0_e2*r1_e1*r2_no*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e3*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_e3-r0_e2*r1_e2*r2_e3*r3_e2*r4_no+r0_e2*r1_e2*r2_e3*r3_no*r4_e2+r0_e2*r1_e2*r2_no*r3_e2*r4_e3-r0_e2*r1_e2*r2_no*r3_e3*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_no-r0_e2*r1_e3*r2_e2*r3_no*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_e3+r0_e2*r1_e3*r2_no*r3_e1*r4_e1+r0_e2*r1_e3*r2_no*r3_e2*r4_e2+r0_e2*r1_e3*r2_no*r3_e3*r4_e3+-2.0*r0_e2*r1_e3*r2_no*r3_ni*r4_no-r0_e2*r1_no*r2_e1*r3_e1*r4_e3+r0_e2*r1_no*r2_e1*r3_e3*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_e3+r0_e2*r1_no*r2_e2*r3_e3*r4_e2-r0_e2*r1_no*r2_e3*r3_e1*r4_e1-r0_e2*r1_no*r2_e3*r3_e2*r4_e2-r0_e2*r1_no*r2_e3*r3_e3*r4_e3+2.0*r0_e2*r1_no*r2_e3*r3_ni*r4_no+-2.0*r0_e2*r1_no*r2_ni*r3_e3*r4_no+2.0*r0_e2*r1_no*r2_ni*r3_no*r4_e3-r0_e3*r1_e1*r2_e1*r3_e2*r4_no+r0_e3*r1_e1*r2_e1*r3_no*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_e1-r0_e3*r1_e1*r2_no*r3_e1*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_no+r0_e3*r1_e2*r2_e2*r3_no*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_e3-r0_e3*r1_e2*r2_no*r3_e1*r4_e1-r0_e3*r1_e2*r2_no*r3_e2*r4_e2-r0_e3*r1_e2*r2_no*r3_e3*r4_e3+2.0*r0_e3*r1_e2*r2_no*r3_ni*r4_no+r0_e3*r1_e3*r2_e2*r3_e3*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_e3-r0_e3*r1_e3*r2_e3*r3_e2*r4_no+r0_e3*r1_e3*r2_e3*r3_no*r4_e2+r0_e3*r1_e3*r2_no*r3_e2*r4_e3-r0_e3*r1_e3*r2_no*r3_e3*r4_e2+r0_e3*r1_no*r2_e1*r3_e1*r4_e2-r0_e3*r1_no*r2_e1*r3_e2*r4_e1+r0_e3*r1_no*r2_e2*r3_e1*r4_e1+r0_e3*r1_no*r2_e2*r3_e2*r4_e2+r0_e3*r1_no*r2_e2*r3_e3*r4_e3+-2.0*r0_e3*r1_no*r2_e2*r3_ni*r4_no-r0_e3*r1_no*r2_e3*r3_e2*r4_e3+r0_e3*r1_no*r2_e3*r3_e3*r4_e2+2.0*r0_e3*r1_no*r2_ni*r3_e2*r4_no+-2.0*r0_e3*r1_no*r2_ni*r3_no*r4_e2+r0_no*r1_e1*r2_e1*r3_e2*r4_e3-r0_no*r1_e1*r2_e1*r3_e3*r4_e2-r0_no*r1_e1*r2_e2*r3_e1*r4_e3+r0_no*r1_e1*r2_e2*r3_e3*r4_e1+r0_no*r1_e1*r2_e3*r3_e1*r4_e2-r0_no*r1_e1*r2_e3*r3_e2*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_e3-r0_no*r1_e2*r2_e1*r3_e3*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_e3-r0_no*r1_e2*r2_e2*r3_e3*r4_e2+r0_no*r1_e2*r2_e3*r3_e1*r4_e1+r0_no*r1_e2*r2_e3*r3_e2*r4_e2+r0_no*r1_e2*r2_e3*r3_e3*r4_e3+-2.0*r0_no*r1_e2*r2_e3*r3_ni*r4_no+2.0*r0_no*r1_e2*r2_ni*r3_e3*r4_no+-2.0*r0_no*r1_e2*r2_ni*r3_no*r4_e3-r0_no*r1_e3*r2_e1*r3_e1*r4_e2+r0_no*r1_e3*r2_e1*r3_e2*r4_e1-r0_no*r1_e3*r2_e2*r3_e1*r4_e1-r0_no*r1_e3*r2_e2*r3_e2*r4_e2-r0_no*r1_e3*r2_e2*r3_e3*r4_e3+2.0*r0_no*r1_e3*r2_e2*r3_ni*r4_no+r0_no*r1_e3*r2_e3*r3_e2*r4_e3-r0_no*r1_e3*r2_e3*r3_e3*r4_e2+-2.0*r0_no*r1_e3*r2_ni*r3_e2*r4_no+2.0*r0_no*r1_e3*r2_ni*r3_no*r4_e2+-2.0*r0_no*r1_ni*r2_e2*r3_e3*r4_no+2.0*r0_no*r1_ni*r2_e2*r3_no*r4_e3+2.0*r0_no*r1_ni*r2_e3*r3_e2*r4_no+-2.0*r0_no*r1_ni*r2_e3*r3_no*r4_e2+-2.0*r0_no*r1_ni*r2_no*r3_e2*r4_e3+2.0*r0_no*r1_ni*r2_no*r3_e3*r4_e2);
	tmp.m_e1_e2_e3 = (r0_e1*r1_e1*r2_e1*r3_e2*r4_e3-r0_e1*r1_e1*r2_e1*r3_e3*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_e3+r0_e1*r1_e1*r2_e2*r3_e3*r4_e1+r0_e1*r1_e1*r2_e3*r3_e1*r4_e2-r0_e1*r1_e1*r2_e3*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_e3-r0_e1*r1_e2*r2_e1*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_e3-r0_e1*r1_e2*r2_e2*r3_e3*r4_e2+r0_e1*r1_e2*r2_e3*r3_e1*r4_e1+r0_e1*r1_e2*r2_e3*r3_e2*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_e3-r0_e1*r1_e2*r2_e3*r3_ni*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_ni+r0_e1*r1_e2*r2_ni*r3_e3*r4_no-r0_e1*r1_e2*r2_ni*r3_no*r4_e3+r0_e1*r1_e2*r2_no*r3_e3*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e3-r0_e1*r1_e3*r2_e1*r3_e1*r4_e2+r0_e1*r1_e3*r2_e1*r3_e2*r4_e1-r0_e1*r1_e3*r2_e2*r3_e1*r4_e1-r0_e1*r1_e3*r2_e2*r3_e2*r4_e2-r0_e1*r1_e3*r2_e2*r3_e3*r4_e3+r0_e1*r1_e3*r2_e2*r3_ni*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_ni+r0_e1*r1_e3*r2_e3*r3_e2*r4_e3-r0_e1*r1_e3*r2_e3*r3_e3*r4_e2-r0_e1*r1_e3*r2_ni*r3_e2*r4_no+r0_e1*r1_e3*r2_ni*r3_no*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e2-r0_e1*r1_ni*r2_e2*r3_e3*r4_no+r0_e1*r1_ni*r2_e2*r3_no*r4_e3+r0_e1*r1_ni*r2_e3*r3_e2*r4_no-r0_e1*r1_ni*r2_e3*r3_no*r4_e2-r0_e1*r1_ni*r2_no*r3_e2*r4_e3+r0_e1*r1_ni*r2_no*r3_e3*r4_e2-r0_e1*r1_no*r2_e2*r3_e3*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e2*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e2-r0_e1*r1_no*r2_ni*r3_e2*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_e3+r0_e2*r1_e1*r2_e1*r3_e3*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_e3+r0_e2*r1_e1*r2_e2*r3_e3*r4_e2-r0_e2*r1_e1*r2_e3*r3_e1*r4_e1-r0_e2*r1_e1*r2_e3*r3_e2*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_e3+r0_e2*r1_e1*r2_e3*r3_ni*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_ni-r0_e2*r1_e1*r2_ni*r3_e3*r4_no+r0_e2*r1_e1*r2_ni*r3_no*r4_e3-r0_e2*r1_e1*r2_no*r3_e3*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e3+r0_e2*r1_e2*r2_e1*r3_e2*r4_e3-r0_e2*r1_e2*r2_e1*r3_e3*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_e3+r0_e2*r1_e2*r2_e2*r3_e3*r4_e1+r0_e2*r1_e2*r2_e3*r3_e1*r4_e2-r0_e2*r1_e2*r2_e3*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e1*r4_e1+r0_e2*r1_e3*r2_e1*r3_e2*r4_e2+r0_e2*r1_e3*r2_e1*r3_e3*r4_e3-r0_e2*r1_e3*r2_e1*r3_ni*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_ni-r0_e2*r1_e3*r2_e2*r3_e1*r4_e2+r0_e2*r1_e3*r2_e2*r3_e2*r4_e1-r0_e2*r1_e3*r2_e3*r3_e1*r4_e3+r0_e2*r1_e3*r2_e3*r3_e3*r4_e1+r0_e2*r1_e3*r2_ni*r3_e1*r4_no-r0_e2*r1_e3*r2_ni*r3_no*r4_e1+r0_e2*r1_e3*r2_no*r3_e1*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e1+r0_e2*r1_ni*r2_e1*r3_e3*r4_no-r0_e2*r1_ni*r2_e1*r3_no*r4_e3-r0_e2*r1_ni*r2_e3*r3_e1*r4_no+r0_e2*r1_ni*r2_e3*r3_no*r4_e1+r0_e2*r1_ni*r2_no*r3_e1*r4_e3-r0_e2*r1_ni*r2_no*r3_e3*r4_e1+r0_e2*r1_no*r2_e1*r3_e3*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e3-r0_e2*r1_no*r2_e3*r3_e1*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e3-r0_e2*r1_no*r2_ni*r3_e3*r4_e1+r0_e3*r1_e1*r2_e1*r3_e1*r4_e2-r0_e3*r1_e1*r2_e1*r3_e2*r4_e1+r0_e3*r1_e1*r2_e2*r3_e1*r4_e1+r0_e3*r1_e1*r2_e2*r3_e2*r4_e2+r0_e3*r1_e1*r2_e2*r3_e3*r4_e3-r0_e3*r1_e1*r2_e2*r3_ni*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_ni-r0_e3*r1_e1*r2_e3*r3_e2*r4_e3+r0_e3*r1_e1*r2_e3*r3_e3*r4_e2+r0_e3*r1_e1*r2_ni*r3_e2*r4_no-r0_e3*r1_e1*r2_ni*r3_no*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e2-r0_e3*r1_e2*r2_e1*r3_e1*r4_e1-r0_e3*r1_e2*r2_e1*r3_e2*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_e3+r0_e3*r1_e2*r2_e1*r3_ni*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_ni+r0_e3*r1_e2*r2_e2*r3_e1*r4_e2-r0_e3*r1_e2*r2_e2*r3_e2*r4_e1+r0_e3*r1_e2*r2_e3*r3_e1*r4_e3-r0_e3*r1_e2*r2_e3*r3_e3*r4_e1-r0_e3*r1_e2*r2_ni*r3_e1*r4_no+r0_e3*r1_e2*r2_ni*r3_no*r4_e1-r0_e3*r1_e2*r2_no*r3_e1*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_e3-r0_e3*r1_e3*r2_e1*r3_e3*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_e3+r0_e3*r1_e3*r2_e2*r3_e3*r4_e1+r0_e3*r1_e3*r2_e3*r3_e1*r4_e2-r0_e3*r1_e3*r2_e3*r3_e2*r4_e1-r0_e3*r1_ni*r2_e1*r3_e2*r4_no+r0_e3*r1_ni*r2_e1*r3_no*r4_e2+r0_e3*r1_ni*r2_e2*r3_e1*r4_no-r0_e3*r1_ni*r2_e2*r3_no*r4_e1-r0_e3*r1_ni*r2_no*r3_e1*r4_e2+r0_e3*r1_ni*r2_no*r3_e2*r4_e1-r0_e3*r1_no*r2_e1*r3_e2*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e2+r0_e3*r1_no*r2_e2*r3_e1*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e1-r0_e3*r1_no*r2_ni*r3_e1*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e1+r0_ni*r1_e1*r2_e2*r3_e3*r4_no-r0_ni*r1_e1*r2_e2*r3_no*r4_e3-r0_ni*r1_e1*r2_e3*r3_e2*r4_no+r0_ni*r1_e1*r2_e3*r3_no*r4_e2+r0_ni*r1_e1*r2_no*r3_e2*r4_e3-r0_ni*r1_e1*r2_no*r3_e3*r4_e2-r0_ni*r1_e2*r2_e1*r3_e3*r4_no+r0_ni*r1_e2*r2_e1*r3_no*r4_e3+r0_ni*r1_e2*r2_e3*r3_e1*r4_no-r0_ni*r1_e2*r2_e3*r3_no*r4_e1-r0_ni*r1_e2*r2_no*r3_e1*r4_e3+r0_ni*r1_e2*r2_no*r3_e3*r4_e1+r0_ni*r1_e3*r2_e1*r3_e2*r4_no-r0_ni*r1_e3*r2_e1*r3_no*r4_e2-r0_ni*r1_e3*r2_e2*r3_e1*r4_no+r0_ni*r1_e3*r2_e2*r3_no*r4_e1+r0_ni*r1_e3*r2_no*r3_e1*r4_e2-r0_ni*r1_e3*r2_no*r3_e2*r4_e1-r0_ni*r1_no*r2_e1*r3_e2*r4_e3+r0_ni*r1_no*r2_e1*r3_e3*r4_e2+r0_ni*r1_no*r2_e2*r3_e1*r4_e3-r0_ni*r1_no*r2_e2*r3_e3*r4_e1-r0_ni*r1_no*r2_e3*r3_e1*r4_e2+r0_ni*r1_no*r2_e3*r3_e2*r4_e1+r0_no*r1_e1*r2_e2*r3_e3*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e2*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e2+r0_no*r1_e1*r2_ni*r3_e2*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e2-r0_no*r1_e2*r2_e1*r3_e3*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e3+r0_no*r1_e2*r2_e3*r3_e1*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e3+r0_no*r1_e2*r2_ni*r3_e3*r4_e1+r0_no*r1_e3*r2_e1*r3_e2*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e2-r0_no*r1_e3*r2_e2*r3_e1*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e1+r0_no*r1_e3*r2_ni*r3_e1*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e2+r0_no*r1_ni*r2_e2*r3_e1*r4_e3-r0_no*r1_ni*r2_e2*r3_e3*r4_e1-r0_no*r1_ni*r2_e3*r3_e1*r4_e2+r0_no*r1_ni*r2_e3*r3_e2*r4_e1);
	tmp.m_no_e1_ni = (r0_e1*r1_e1*r2_e1*r3_ni*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e1*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e1+r0_e1*r1_e2*r2_e2*r3_ni*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_ni-r0_e1*r1_e2*r2_ni*r3_e2*r4_no+r0_e1*r1_e2*r2_ni*r3_no*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e2+r0_e1*r1_e3*r2_e3*r3_ni*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_ni-r0_e1*r1_e3*r2_ni*r3_e3*r4_no+r0_e1*r1_e3*r2_ni*r3_no*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_ni-r0_e1*r1_e3*r2_no*r3_ni*r4_e3+r0_e1*r1_ni*r2_e1*r3_e1*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e1+r0_e1*r1_ni*r2_e2*r3_e2*r4_no-r0_e1*r1_ni*r2_e2*r3_no*r4_e2+r0_e1*r1_ni*r2_e3*r3_e3*r4_no-r0_e1*r1_ni*r2_e3*r3_no*r4_e3+r0_e1*r1_ni*r2_no*r3_e1*r4_e1+r0_e1*r1_ni*r2_no*r3_e2*r4_e2+r0_e1*r1_ni*r2_no*r3_e3*r4_e3+-2.0*r0_e1*r1_ni*r2_no*r3_ni*r4_no-r0_e1*r1_no*r2_e1*r3_e1*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e2-r0_e1*r1_no*r2_e3*r3_e3*r4_ni+r0_e1*r1_no*r2_e3*r3_ni*r4_e3-r0_e1*r1_no*r2_ni*r3_e1*r4_e1-r0_e1*r1_no*r2_ni*r3_e2*r4_e2-r0_e1*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e1*r1_no*r2_ni*r3_no*r4_ni-r0_e2*r1_e1*r2_e2*r3_ni*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_ni+r0_e2*r1_e1*r2_ni*r3_e2*r4_no-r0_e2*r1_e1*r2_ni*r3_no*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e2+r0_e2*r1_e2*r2_e1*r3_ni*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e1*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e1-r0_e2*r1_ni*r2_e1*r3_e2*r4_no+r0_e2*r1_ni*r2_e1*r3_no*r4_e2+r0_e2*r1_ni*r2_e2*r3_e1*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e1-r0_e2*r1_ni*r2_no*r3_e1*r4_e2+r0_e2*r1_ni*r2_no*r3_e2*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e2-r0_e2*r1_no*r2_e2*r3_e1*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e1-r0_e3*r1_e1*r2_e3*r3_ni*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_ni+r0_e3*r1_e1*r2_ni*r3_e3*r4_no-r0_e3*r1_e1*r2_ni*r3_no*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_ni+r0_e3*r1_e1*r2_no*r3_ni*r4_e3+r0_e3*r1_e3*r2_e1*r3_ni*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e1*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e1-r0_e3*r1_ni*r2_e1*r3_e3*r4_no+r0_e3*r1_ni*r2_e1*r3_no*r4_e3+r0_e3*r1_ni*r2_e3*r3_e1*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e1-r0_e3*r1_ni*r2_no*r3_e1*r4_e3+r0_e3*r1_ni*r2_no*r3_e3*r4_e1+r0_e3*r1_no*r2_e1*r3_e3*r4_ni-r0_e3*r1_no*r2_e1*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e1*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e1+r0_e3*r1_no*r2_ni*r3_e1*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e1-r0_ni*r1_e1*r2_e1*r3_e1*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e1-r0_ni*r1_e1*r2_e2*r3_e2*r4_no+r0_ni*r1_e1*r2_e2*r3_no*r4_e2-r0_ni*r1_e1*r2_e3*r3_e3*r4_no+r0_ni*r1_e1*r2_e3*r3_no*r4_e3-r0_ni*r1_e1*r2_no*r3_e1*r4_e1-r0_ni*r1_e1*r2_no*r3_e2*r4_e2-r0_ni*r1_e1*r2_no*r3_e3*r4_e3+2.0*r0_ni*r1_e1*r2_no*r3_ni*r4_no+r0_ni*r1_e2*r2_e1*r3_e2*r4_no-r0_ni*r1_e2*r2_e1*r3_no*r4_e2-r0_ni*r1_e2*r2_e2*r3_e1*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e1+r0_ni*r1_e2*r2_no*r3_e1*r4_e2-r0_ni*r1_e2*r2_no*r3_e2*r4_e1+r0_ni*r1_e3*r2_e1*r3_e3*r4_no-r0_ni*r1_e3*r2_e1*r3_no*r4_e3-r0_ni*r1_e3*r2_e3*r3_e1*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e1+r0_ni*r1_e3*r2_no*r3_e1*r4_e3-r0_ni*r1_e3*r2_no*r3_e3*r4_e1+r0_ni*r1_no*r2_e1*r3_e1*r4_e1+r0_ni*r1_no*r2_e1*r3_e2*r4_e2+r0_ni*r1_no*r2_e1*r3_e3*r4_e3+-2.0*r0_ni*r1_no*r2_e1*r3_ni*r4_no-r0_ni*r1_no*r2_e2*r3_e1*r4_e2+r0_ni*r1_no*r2_e2*r3_e2*r4_e1-r0_ni*r1_no*r2_e3*r3_e1*r4_e3+r0_ni*r1_no*r2_e3*r3_e3*r4_e1+2.0*r0_ni*r1_no*r2_ni*r3_e1*r4_no+-2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e2+r0_no*r1_e1*r2_e3*r3_e3*r4_ni-r0_no*r1_e1*r2_e3*r3_ni*r4_e3+r0_no*r1_e1*r2_ni*r3_e1*r4_e1+r0_no*r1_e1*r2_ni*r3_e2*r4_e2+r0_no*r1_e1*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e1*r2_ni*r3_no*r4_ni-r0_no*r1_e2*r2_e1*r3_e2*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e2+r0_no*r1_e2*r2_e2*r3_e1*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e1-r0_no*r1_e3*r2_e1*r3_e3*r4_ni+r0_no*r1_e3*r2_e1*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e1*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e1-r0_no*r1_e3*r2_ni*r3_e1*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e1-r0_no*r1_ni*r2_e1*r3_e1*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e2-r0_no*r1_ni*r2_e1*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e1*r3_no*r4_ni+r0_no*r1_ni*r2_e2*r3_e1*r4_e2-r0_no*r1_ni*r2_e2*r3_e2*r4_e1+r0_no*r1_ni*r2_e3*r3_e1*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e1+-2.0*r0_no*r1_ni*r2_no*r3_e1*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e1);
	tmp.m_no_e2_ni = (r0_e1*r1_e1*r2_e2*r3_ni*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e2*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e2+r0_e1*r1_e1*r2_no*r3_e2*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e2-r0_e1*r1_e2*r2_e1*r3_ni*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_ni+r0_e1*r1_e2*r2_ni*r3_e1*r4_no-r0_e1*r1_e2*r2_ni*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_ni+r0_e1*r1_e2*r2_no*r3_ni*r4_e1+r0_e1*r1_ni*r2_e1*r3_e2*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e2-r0_e1*r1_ni*r2_e2*r3_e1*r4_no+r0_e1*r1_ni*r2_e2*r3_no*r4_e1+r0_e1*r1_ni*r2_no*r3_e1*r4_e2-r0_e1*r1_ni*r2_no*r3_e2*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e2+r0_e1*r1_no*r2_e2*r3_e1*r4_ni-r0_e1*r1_no*r2_e2*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e2+r0_e1*r1_no*r2_ni*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_ni*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_ni-r0_e2*r1_e1*r2_ni*r3_e1*r4_no+r0_e2*r1_e1*r2_ni*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_ni-r0_e2*r1_e1*r2_no*r3_ni*r4_e1+r0_e2*r1_e2*r2_e2*r3_ni*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e2*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e2+r0_e2*r1_e2*r2_no*r3_e2*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e2+r0_e2*r1_e3*r2_e3*r3_ni*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_ni-r0_e2*r1_e3*r2_ni*r3_e3*r4_no+r0_e2*r1_e3*r2_ni*r3_no*r4_e3+r0_e2*r1_e3*r2_no*r3_e3*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e3+r0_e2*r1_ni*r2_e1*r3_e1*r4_no-r0_e2*r1_ni*r2_e1*r3_no*r4_e1+r0_e2*r1_ni*r2_e2*r3_e2*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e2+r0_e2*r1_ni*r2_e3*r3_e3*r4_no-r0_e2*r1_ni*r2_e3*r3_no*r4_e3+r0_e2*r1_ni*r2_no*r3_e1*r4_e1+r0_e2*r1_ni*r2_no*r3_e2*r4_e2+r0_e2*r1_ni*r2_no*r3_e3*r4_e3+-2.0*r0_e2*r1_ni*r2_no*r3_ni*r4_no-r0_e2*r1_no*r2_e1*r3_e1*r4_ni+r0_e2*r1_no*r2_e1*r3_ni*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e2-r0_e2*r1_no*r2_e3*r3_e3*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e3-r0_e2*r1_no*r2_ni*r3_e1*r4_e1-r0_e2*r1_no*r2_ni*r3_e2*r4_e2-r0_e2*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e2*r1_no*r2_ni*r3_no*r4_ni-r0_e3*r1_e2*r2_e3*r3_ni*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_ni+r0_e3*r1_e2*r2_ni*r3_e3*r4_no-r0_e3*r1_e2*r2_ni*r3_no*r4_e3-r0_e3*r1_e2*r2_no*r3_e3*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e3+r0_e3*r1_e3*r2_e2*r3_ni*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e2*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e2+r0_e3*r1_e3*r2_no*r3_e2*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e2-r0_e3*r1_ni*r2_e2*r3_e3*r4_no+r0_e3*r1_ni*r2_e2*r3_no*r4_e3+r0_e3*r1_ni*r2_e3*r3_e2*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e2-r0_e3*r1_ni*r2_no*r3_e2*r4_e3+r0_e3*r1_ni*r2_no*r3_e3*r4_e2+r0_e3*r1_no*r2_e2*r3_e3*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e2*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e2-r0_ni*r1_e1*r2_e1*r3_e2*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e2+r0_ni*r1_e1*r2_e2*r3_e1*r4_no-r0_ni*r1_e1*r2_e2*r3_no*r4_e1-r0_ni*r1_e1*r2_no*r3_e1*r4_e2+r0_ni*r1_e1*r2_no*r3_e2*r4_e1-r0_ni*r1_e2*r2_e1*r3_e1*r4_no+r0_ni*r1_e2*r2_e1*r3_no*r4_e1-r0_ni*r1_e2*r2_e2*r3_e2*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e2-r0_ni*r1_e2*r2_e3*r3_e3*r4_no+r0_ni*r1_e2*r2_e3*r3_no*r4_e3-r0_ni*r1_e2*r2_no*r3_e1*r4_e1-r0_ni*r1_e2*r2_no*r3_e2*r4_e2-r0_ni*r1_e2*r2_no*r3_e3*r4_e3+2.0*r0_ni*r1_e2*r2_no*r3_ni*r4_no+r0_ni*r1_e3*r2_e2*r3_e3*r4_no-r0_ni*r1_e3*r2_e2*r3_no*r4_e3-r0_ni*r1_e3*r2_e3*r3_e2*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e2+r0_ni*r1_e3*r2_no*r3_e2*r4_e3-r0_ni*r1_e3*r2_no*r3_e3*r4_e2+r0_ni*r1_no*r2_e1*r3_e1*r4_e2-r0_ni*r1_no*r2_e1*r3_e2*r4_e1+r0_ni*r1_no*r2_e2*r3_e1*r4_e1+r0_ni*r1_no*r2_e2*r3_e2*r4_e2+r0_ni*r1_no*r2_e2*r3_e3*r4_e3+-2.0*r0_ni*r1_no*r2_e2*r3_ni*r4_no-r0_ni*r1_no*r2_e3*r3_e2*r4_e3+r0_ni*r1_no*r2_e3*r3_e3*r4_e2+2.0*r0_ni*r1_no*r2_ni*r3_e2*r4_no+-2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e2+r0_no*r1_e1*r2_e1*r3_e2*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e2-r0_no*r1_e1*r2_e2*r3_e1*r4_ni+r0_no*r1_e1*r2_e2*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e2-r0_no*r1_e1*r2_ni*r3_e2*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_ni-r0_no*r1_e2*r2_e1*r3_ni*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e2+r0_no*r1_e2*r2_e3*r3_e3*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e3+r0_no*r1_e2*r2_ni*r3_e1*r4_e1+r0_no*r1_e2*r2_ni*r3_e2*r4_e2+r0_no*r1_e2*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e2*r2_ni*r3_no*r4_ni-r0_no*r1_e3*r2_e2*r3_e3*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e2*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e2-r0_no*r1_ni*r2_e1*r3_e1*r4_e2+r0_no*r1_ni*r2_e1*r3_e2*r4_e1-r0_no*r1_ni*r2_e2*r3_e1*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e2-r0_no*r1_ni*r2_e2*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e2*r3_no*r4_ni+r0_no*r1_ni*r2_e3*r3_e2*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e2+-2.0*r0_no*r1_ni*r2_no*r3_e2*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e2);
	tmp.m_e1_e2_ni = (r0_e1*r1_e1*r2_e1*r3_e2*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_ni+r0_e1*r1_e1*r2_e2*r3_ni*r4_e1+r0_e1*r1_e1*r2_ni*r3_e1*r4_e2-r0_e1*r1_e1*r2_ni*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_ni-r0_e1*r1_e2*r2_e1*r3_ni*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_ni-r0_e1*r1_e2*r2_e3*r3_ni*r4_e3+r0_e1*r1_e2*r2_ni*r3_e1*r4_e1+r0_e1*r1_e2*r2_ni*r3_e2*r4_e2+r0_e1*r1_e2*r2_ni*r3_e3*r4_e3+-2.0*r0_e1*r1_e2*r2_ni*r3_no*r4_ni-r0_e1*r1_e3*r2_e2*r3_e3*r4_ni+r0_e1*r1_e3*r2_e2*r3_ni*r4_e3+r0_e1*r1_e3*r2_e3*r3_e2*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e2-r0_e1*r1_e3*r2_ni*r3_e2*r4_e3+r0_e1*r1_e3*r2_ni*r3_e3*r4_e2-r0_e1*r1_ni*r2_e1*r3_e1*r4_e2+r0_e1*r1_ni*r2_e1*r3_e2*r4_e1-r0_e1*r1_ni*r2_e2*r3_e1*r4_e1-r0_e1*r1_ni*r2_e2*r3_e2*r4_e2-r0_e1*r1_ni*r2_e2*r3_e3*r4_e3+2.0*r0_e1*r1_ni*r2_e2*r3_no*r4_ni+r0_e1*r1_ni*r2_e3*r3_e2*r4_e3-r0_e1*r1_ni*r2_e3*r3_e3*r4_e2+-2.0*r0_e1*r1_ni*r2_no*r3_e2*r4_ni+2.0*r0_e1*r1_ni*r2_no*r3_ni*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_ni+r0_e2*r1_e1*r2_e1*r3_ni*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_ni+r0_e2*r1_e1*r2_e3*r3_ni*r4_e3-r0_e2*r1_e1*r2_ni*r3_e1*r4_e1-r0_e2*r1_e1*r2_ni*r3_e2*r4_e2-r0_e2*r1_e1*r2_ni*r3_e3*r4_e3+2.0*r0_e2*r1_e1*r2_ni*r3_no*r4_ni+r0_e2*r1_e2*r2_e1*r3_e2*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_ni+r0_e2*r1_e2*r2_e2*r3_ni*r4_e1+r0_e2*r1_e2*r2_ni*r3_e1*r4_e2-r0_e2*r1_e2*r2_ni*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e3*r4_ni-r0_e2*r1_e3*r2_e1*r3_ni*r4_e3-r0_e2*r1_e3*r2_e3*r3_e1*r4_ni+r0_e2*r1_e3*r2_e3*r3_ni*r4_e1+r0_e2*r1_e3*r2_ni*r3_e1*r4_e3-r0_e2*r1_e3*r2_ni*r3_e3*r4_e1+r0_e2*r1_ni*r2_e1*r3_e1*r4_e1+r0_e2*r1_ni*r2_e1*r3_e2*r4_e2+r0_e2*r1_ni*r2_e1*r3_e3*r4_e3+-2.0*r0_e2*r1_ni*r2_e1*r3_no*r4_ni-r0_e2*r1_ni*r2_e2*r3_e1*r4_e2+r0_e2*r1_ni*r2_e2*r3_e2*r4_e1-r0_e2*r1_ni*r2_e3*r3_e1*r4_e3+r0_e2*r1_ni*r2_e3*r3_e3*r4_e1+2.0*r0_e2*r1_ni*r2_no*r3_e1*r4_ni+-2.0*r0_e2*r1_ni*r2_no*r3_ni*r4_e1+r0_e3*r1_e1*r2_e2*r3_e3*r4_ni-r0_e3*r1_e1*r2_e2*r3_ni*r4_e3-r0_e3*r1_e1*r2_e3*r3_e2*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e2+r0_e3*r1_e1*r2_ni*r3_e2*r4_e3-r0_e3*r1_e1*r2_ni*r3_e3*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_ni+r0_e3*r1_e2*r2_e1*r3_ni*r4_e3+r0_e3*r1_e2*r2_e3*r3_e1*r4_ni-r0_e3*r1_e2*r2_e3*r3_ni*r4_e1-r0_e3*r1_e2*r2_ni*r3_e1*r4_e3+r0_e3*r1_e2*r2_ni*r3_e3*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_ni+r0_e3*r1_e3*r2_e2*r3_ni*r4_e1+r0_e3*r1_e3*r2_ni*r3_e1*r4_e2-r0_e3*r1_e3*r2_ni*r3_e2*r4_e1-r0_e3*r1_ni*r2_e1*r3_e2*r4_e3+r0_e3*r1_ni*r2_e1*r3_e3*r4_e2+r0_e3*r1_ni*r2_e2*r3_e1*r4_e3-r0_e3*r1_ni*r2_e2*r3_e3*r4_e1-r0_e3*r1_ni*r2_e3*r3_e1*r4_e2+r0_e3*r1_ni*r2_e3*r3_e2*r4_e1+r0_ni*r1_e1*r2_e1*r3_e1*r4_e2-r0_ni*r1_e1*r2_e1*r3_e2*r4_e1+r0_ni*r1_e1*r2_e2*r3_e1*r4_e1+r0_ni*r1_e1*r2_e2*r3_e2*r4_e2+r0_ni*r1_e1*r2_e2*r3_e3*r4_e3+-2.0*r0_ni*r1_e1*r2_e2*r3_no*r4_ni-r0_ni*r1_e1*r2_e3*r3_e2*r4_e3+r0_ni*r1_e1*r2_e3*r3_e3*r4_e2+2.0*r0_ni*r1_e1*r2_no*r3_e2*r4_ni+-2.0*r0_ni*r1_e1*r2_no*r3_ni*r4_e2-r0_ni*r1_e2*r2_e1*r3_e1*r4_e1-r0_ni*r1_e2*r2_e1*r3_e2*r4_e2-r0_ni*r1_e2*r2_e1*r3_e3*r4_e3+2.0*r0_ni*r1_e2*r2_e1*r3_no*r4_ni+r0_ni*r1_e2*r2_e2*r3_e1*r4_e2-r0_ni*r1_e2*r2_e2*r3_e2*r4_e1+r0_ni*r1_e2*r2_e3*r3_e1*r4_e3-r0_ni*r1_e2*r2_e3*r3_e3*r4_e1+-2.0*r0_ni*r1_e2*r2_no*r3_e1*r4_ni+2.0*r0_ni*r1_e2*r2_no*r3_ni*r4_e1+r0_ni*r1_e3*r2_e1*r3_e2*r4_e3-r0_ni*r1_e3*r2_e1*r3_e3*r4_e2-r0_ni*r1_e3*r2_e2*r3_e1*r4_e3+r0_ni*r1_e3*r2_e2*r3_e3*r4_e1+r0_ni*r1_e3*r2_e3*r3_e1*r4_e2-r0_ni*r1_e3*r2_e3*r3_e2*r4_e1+-2.0*r0_ni*r1_no*r2_e1*r3_e2*r4_ni+2.0*r0_ni*r1_no*r2_e1*r3_ni*r4_e2+2.0*r0_ni*r1_no*r2_e2*r3_e1*r4_ni+-2.0*r0_ni*r1_no*r2_e2*r3_ni*r4_e1+-2.0*r0_ni*r1_no*r2_ni*r3_e1*r4_e2+2.0*r0_ni*r1_no*r2_ni*r3_e2*r4_e1);
	tmp.m_no_e3_ni = (r0_e1*r1_e1*r2_e3*r3_ni*r4_no-r0_e1*r1_e1*r2_e3*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e3*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e3+r0_e1*r1_e1*r2_no*r3_e3*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e3-r0_e1*r1_e3*r2_e1*r3_ni*r4_no+r0_e1*r1_e3*r2_e1*r3_no*r4_ni+r0_e1*r1_e3*r2_ni*r3_e1*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_e1-r0_e1*r1_e3*r2_no*r3_e1*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e1+r0_e1*r1_ni*r2_e1*r3_e3*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e3-r0_e1*r1_ni*r2_e3*r3_e1*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_e1+r0_e1*r1_ni*r2_no*r3_e1*r4_e3-r0_e1*r1_ni*r2_no*r3_e3*r4_e1-r0_e1*r1_no*r2_e1*r3_e3*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e1*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e1+r0_e2*r1_e2*r2_e3*r3_ni*r4_no-r0_e2*r1_e2*r2_e3*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e3*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e3+r0_e2*r1_e2*r2_no*r3_e3*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e3-r0_e2*r1_e3*r2_e2*r3_ni*r4_no+r0_e2*r1_e3*r2_e2*r3_no*r4_ni+r0_e2*r1_e3*r2_ni*r3_e2*r4_no-r0_e2*r1_e3*r2_ni*r3_no*r4_e2-r0_e2*r1_e3*r2_no*r3_e2*r4_ni+r0_e2*r1_e3*r2_no*r3_ni*r4_e2+r0_e2*r1_ni*r2_e2*r3_e3*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e3-r0_e2*r1_ni*r2_e3*r3_e2*r4_no+r0_e2*r1_ni*r2_e3*r3_no*r4_e2+r0_e2*r1_ni*r2_no*r3_e2*r4_e3-r0_e2*r1_ni*r2_no*r3_e3*r4_e2-r0_e2*r1_no*r2_e2*r3_e3*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e3+r0_e2*r1_no*r2_e3*r3_e2*r4_ni-r0_e2*r1_no*r2_e3*r3_ni*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e3+r0_e2*r1_no*r2_ni*r3_e3*r4_e2+r0_e3*r1_e1*r2_e1*r3_ni*r4_no-r0_e3*r1_e1*r2_e1*r3_no*r4_ni-r0_e3*r1_e1*r2_ni*r3_e1*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_e1+r0_e3*r1_e1*r2_no*r3_e1*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e1+r0_e3*r1_e2*r2_e2*r3_ni*r4_no-r0_e3*r1_e2*r2_e2*r3_no*r4_ni-r0_e3*r1_e2*r2_ni*r3_e2*r4_no+r0_e3*r1_e2*r2_ni*r3_no*r4_e2+r0_e3*r1_e2*r2_no*r3_e2*r4_ni-r0_e3*r1_e2*r2_no*r3_ni*r4_e2+r0_e3*r1_e3*r2_e3*r3_ni*r4_no-r0_e3*r1_e3*r2_e3*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e3*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e3+r0_e3*r1_e3*r2_no*r3_e3*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e3+r0_e3*r1_ni*r2_e1*r3_e1*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_e1+r0_e3*r1_ni*r2_e2*r3_e2*r4_no-r0_e3*r1_ni*r2_e2*r3_no*r4_e2+r0_e3*r1_ni*r2_e3*r3_e3*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e3+r0_e3*r1_ni*r2_no*r3_e1*r4_e1+r0_e3*r1_ni*r2_no*r3_e2*r4_e2+r0_e3*r1_ni*r2_no*r3_e3*r4_e3+-2.0*r0_e3*r1_ni*r2_no*r3_ni*r4_no-r0_e3*r1_no*r2_e1*r3_e1*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e1-r0_e3*r1_no*r2_e2*r3_e2*r4_ni+r0_e3*r1_no*r2_e2*r3_ni*r4_e2-r0_e3*r1_no*r2_e3*r3_e3*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e3-r0_e3*r1_no*r2_ni*r3_e1*r4_e1-r0_e3*r1_no*r2_ni*r3_e2*r4_e2-r0_e3*r1_no*r2_ni*r3_e3*r4_e3+2.0*r0_e3*r1_no*r2_ni*r3_no*r4_ni-r0_ni*r1_e1*r2_e1*r3_e3*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e3+r0_ni*r1_e1*r2_e3*r3_e1*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_e1-r0_ni*r1_e1*r2_no*r3_e1*r4_e3+r0_ni*r1_e1*r2_no*r3_e3*r4_e1-r0_ni*r1_e2*r2_e2*r3_e3*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e3+r0_ni*r1_e2*r2_e3*r3_e2*r4_no-r0_ni*r1_e2*r2_e3*r3_no*r4_e2-r0_ni*r1_e2*r2_no*r3_e2*r4_e3+r0_ni*r1_e2*r2_no*r3_e3*r4_e2-r0_ni*r1_e3*r2_e1*r3_e1*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_e1-r0_ni*r1_e3*r2_e2*r3_e2*r4_no+r0_ni*r1_e3*r2_e2*r3_no*r4_e2-r0_ni*r1_e3*r2_e3*r3_e3*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e3-r0_ni*r1_e3*r2_no*r3_e1*r4_e1-r0_ni*r1_e3*r2_no*r3_e2*r4_e2-r0_ni*r1_e3*r2_no*r3_e3*r4_e3+2.0*r0_ni*r1_e3*r2_no*r3_ni*r4_no+r0_ni*r1_no*r2_e1*r3_e1*r4_e3-r0_ni*r1_no*r2_e1*r3_e3*r4_e1+r0_ni*r1_no*r2_e2*r3_e2*r4_e3-r0_ni*r1_no*r2_e2*r3_e3*r4_e2+r0_ni*r1_no*r2_e3*r3_e1*r4_e1+r0_ni*r1_no*r2_e3*r3_e2*r4_e2+r0_ni*r1_no*r2_e3*r3_e3*r4_e3+-2.0*r0_ni*r1_no*r2_e3*r3_ni*r4_no+2.0*r0_ni*r1_no*r2_ni*r3_e3*r4_no+-2.0*r0_ni*r1_no*r2_ni*r3_no*r4_e3+r0_no*r1_e1*r2_e1*r3_e3*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e1*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e1+r0_no*r1_e2*r2_e2*r3_e3*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e3-r0_no*r1_e2*r2_e3*r3_e2*r4_ni+r0_no*r1_e2*r2_e3*r3_ni*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e3-r0_no*r1_e2*r2_ni*r3_e3*r4_e2+r0_no*r1_e3*r2_e1*r3_e1*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e1+r0_no*r1_e3*r2_e2*r3_e2*r4_ni-r0_no*r1_e3*r2_e2*r3_ni*r4_e2+r0_no*r1_e3*r2_e3*r3_e3*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e3+r0_no*r1_e3*r2_ni*r3_e1*r4_e1+r0_no*r1_e3*r2_ni*r3_e2*r4_e2+r0_no*r1_e3*r2_ni*r3_e3*r4_e3+-2.0*r0_no*r1_e3*r2_ni*r3_no*r4_ni-r0_no*r1_ni*r2_e1*r3_e1*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e3+r0_no*r1_ni*r2_e2*r3_e3*r4_e2-r0_no*r1_ni*r2_e3*r3_e1*r4_e1-r0_no*r1_ni*r2_e3*r3_e2*r4_e2-r0_no*r1_ni*r2_e3*r3_e3*r4_e3+2.0*r0_no*r1_ni*r2_e3*r3_no*r4_ni+-2.0*r0_no*r1_ni*r2_no*r3_e3*r4_ni+2.0*r0_no*r1_ni*r2_no*r3_ni*r4_e3);
	tmp.m_e1_e3_ni = (r0_e1*r1_e1*r2_e1*r3_e3*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e3-r0_e1*r1_e1*r2_e3*r3_e1*r4_ni+r0_e1*r1_e1*r2_e3*r3_ni*r4_e1+r0_e1*r1_e1*r2_ni*r3_e1*r4_e3-r0_e1*r1_e1*r2_ni*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e3*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e3-r0_e1*r1_e2*r2_e3*r3_e2*r4_ni+r0_e1*r1_e2*r2_e3*r3_ni*r4_e2+r0_e1*r1_e2*r2_ni*r3_e2*r4_e3-r0_e1*r1_e2*r2_ni*r3_e3*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_ni-r0_e1*r1_e3*r2_e1*r3_ni*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_ni-r0_e1*r1_e3*r2_e2*r3_ni*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e3+r0_e1*r1_e3*r2_ni*r3_e1*r4_e1+r0_e1*r1_e3*r2_ni*r3_e2*r4_e2+r0_e1*r1_e3*r2_ni*r3_e3*r4_e3+-2.0*r0_e1*r1_e3*r2_ni*r3_no*r4_ni-r0_e1*r1_ni*r2_e1*r3_e1*r4_e3+r0_e1*r1_ni*r2_e1*r3_e3*r4_e1-r0_e1*r1_ni*r2_e2*r3_e2*r4_e3+r0_e1*r1_ni*r2_e2*r3_e3*r4_e2-r0_e1*r1_ni*r2_e3*r3_e1*r4_e1-r0_e1*r1_ni*r2_e3*r3_e2*r4_e2-r0_e1*r1_ni*r2_e3*r3_e3*r4_e3+2.0*r0_e1*r1_ni*r2_e3*r3_no*r4_ni+-2.0*r0_e1*r1_ni*r2_no*r3_e3*r4_ni+2.0*r0_e1*r1_ni*r2_no*r3_ni*r4_e3-r0_e2*r1_e1*r2_e2*r3_e3*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e3+r0_e2*r1_e1*r2_e3*r3_e2*r4_ni-r0_e2*r1_e1*r2_e3*r3_ni*r4_e2-r0_e2*r1_e1*r2_ni*r3_e2*r4_e3+r0_e2*r1_e1*r2_ni*r3_e3*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e3-r0_e2*r1_e2*r2_e3*r3_e1*r4_ni+r0_e2*r1_e2*r2_e3*r3_ni*r4_e1+r0_e2*r1_e2*r2_ni*r3_e1*r4_e3-r0_e2*r1_e2*r2_ni*r3_e3*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_ni+r0_e2*r1_e3*r2_e1*r3_ni*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_ni-r0_e2*r1_e3*r2_e2*r3_ni*r4_e1-r0_e2*r1_e3*r2_ni*r3_e1*r4_e2+r0_e2*r1_e3*r2_ni*r3_e2*r4_e1+r0_e2*r1_ni*r2_e1*r3_e2*r4_e3-r0_e2*r1_ni*r2_e1*r3_e3*r4_e2-r0_e2*r1_ni*r2_e2*r3_e1*r4_e3+r0_e2*r1_ni*r2_e2*r3_e3*r4_e1+r0_e2*r1_ni*r2_e3*r3_e1*r4_e2-r0_e2*r1_ni*r2_e3*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_ni+r0_e3*r1_e1*r2_e1*r3_ni*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_ni+r0_e3*r1_e1*r2_e2*r3_ni*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e3-r0_e3*r1_e1*r2_ni*r3_e1*r4_e1-r0_e3*r1_e1*r2_ni*r3_e2*r4_e2-r0_e3*r1_e1*r2_ni*r3_e3*r4_e3+2.0*r0_e3*r1_e1*r2_ni*r3_no*r4_ni+r0_e3*r1_e2*r2_e1*r3_e2*r4_ni-r0_e3*r1_e2*r2_e1*r3_ni*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_ni+r0_e3*r1_e2*r2_e2*r3_ni*r4_e1+r0_e3*r1_e2*r2_ni*r3_e1*r4_e2-r0_e3*r1_e2*r2_ni*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e3*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e3-r0_e3*r1_e3*r2_e3*r3_e1*r4_ni+r0_e3*r1_e3*r2_e3*r3_ni*r4_e1+r0_e3*r1_e3*r2_ni*r3_e1*r4_e3-r0_e3*r1_e3*r2_ni*r3_e3*r4_e1+r0_e3*r1_ni*r2_e1*r3_e1*r4_e1+r0_e3*r1_ni*r2_e1*r3_e2*r4_e2+r0_e3*r1_ni*r2_e1*r3_e3*r4_e3+-2.0*r0_e3*r1_ni*r2_e1*r3_no*r4_ni-r0_e3*r1_ni*r2_e2*r3_e1*r4_e2+r0_e3*r1_ni*r2_e2*r3_e2*r4_e1-r0_e3*r1_ni*r2_e3*r3_e1*r4_e3+r0_e3*r1_ni*r2_e3*r3_e3*r4_e1+2.0*r0_e3*r1_ni*r2_no*r3_e1*r4_ni+-2.0*r0_e3*r1_ni*r2_no*r3_ni*r4_e1+r0_ni*r1_e1*r2_e1*r3_e1*r4_e3-r0_ni*r1_e1*r2_e1*r3_e3*r4_e1+r0_ni*r1_e1*r2_e2*r3_e2*r4_e3-r0_ni*r1_e1*r2_e2*r3_e3*r4_e2+r0_ni*r1_e1*r2_e3*r3_e1*r4_e1+r0_ni*r1_e1*r2_e3*r3_e2*r4_e2+r0_ni*r1_e1*r2_e3*r3_e3*r4_e3+-2.0*r0_ni*r1_e1*r2_e3*r3_no*r4_ni+2.0*r0_ni*r1_e1*r2_no*r3_e3*r4_ni+-2.0*r0_ni*r1_e1*r2_no*r3_ni*r4_e3-r0_ni*r1_e2*r2_e1*r3_e2*r4_e3+r0_ni*r1_e2*r2_e1*r3_e3*r4_e2+r0_ni*r1_e2*r2_e2*r3_e1*r4_e3-r0_ni*r1_e2*r2_e2*r3_e3*r4_e1-r0_ni*r1_e2*r2_e3*r3_e1*r4_e2+r0_ni*r1_e2*r2_e3*r3_e2*r4_e1-r0_ni*r1_e3*r2_e1*r3_e1*r4_e1-r0_ni*r1_e3*r2_e1*r3_e2*r4_e2-r0_ni*r1_e3*r2_e1*r3_e3*r4_e3+2.0*r0_ni*r1_e3*r2_e1*r3_no*r4_ni+r0_ni*r1_e3*r2_e2*r3_e1*r4_e2-r0_ni*r1_e3*r2_e2*r3_e2*r4_e1+r0_ni*r1_e3*r2_e3*r3_e1*r4_e3-r0_ni*r1_e3*r2_e3*r3_e3*r4_e1+-2.0*r0_ni*r1_e3*r2_no*r3_e1*r4_ni+2.0*r0_ni*r1_e3*r2_no*r3_ni*r4_e1+-2.0*r0_ni*r1_no*r2_e1*r3_e3*r4_ni+2.0*r0_ni*r1_no*r2_e1*r3_ni*r4_e3+2.0*r0_ni*r1_no*r2_e3*r3_e1*r4_ni+-2.0*r0_ni*r1_no*r2_e3*r3_ni*r4_e1+-2.0*r0_ni*r1_no*r2_ni*r3_e1*r4_e3+2.0*r0_ni*r1_no*r2_ni*r3_e3*r4_e1);
	tmp.m_e2_e3_ni = (r0_e1*r1_e1*r2_e2*r3_e3*r4_ni-r0_e1*r1_e1*r2_e2*r3_ni*r4_e3-r0_e1*r1_e1*r2_e3*r3_e2*r4_ni+r0_e1*r1_e1*r2_e3*r3_ni*r4_e2+r0_e1*r1_e1*r2_ni*r3_e2*r4_e3-r0_e1*r1_e1*r2_ni*r3_e3*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_ni+r0_e1*r1_e2*r2_e1*r3_ni*r4_e3+r0_e1*r1_e2*r2_e3*r3_e1*r4_ni-r0_e1*r1_e2*r2_e3*r3_ni*r4_e1-r0_e1*r1_e2*r2_ni*r3_e1*r4_e3+r0_e1*r1_e2*r2_ni*r3_e3*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_ni-r0_e1*r1_e3*r2_e1*r3_ni*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_ni+r0_e1*r1_e3*r2_e2*r3_ni*r4_e1+r0_e1*r1_e3*r2_ni*r3_e1*r4_e2-r0_e1*r1_e3*r2_ni*r3_e2*r4_e1-r0_e1*r1_ni*r2_e1*r3_e2*r4_e3+r0_e1*r1_ni*r2_e1*r3_e3*r4_e2+r0_e1*r1_ni*r2_e2*r3_e1*r4_e3-r0_e1*r1_ni*r2_e2*r3_e3*r4_e1-r0_e1*r1_ni*r2_e3*r3_e1*r4_e2+r0_e1*r1_ni*r2_e3*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e3*r4_ni-r0_e2*r1_e1*r2_e1*r3_ni*r4_e3-r0_e2*r1_e1*r2_e3*r3_e1*r4_ni+r0_e2*r1_e1*r2_e3*r3_ni*r4_e1+r0_e2*r1_e1*r2_ni*r3_e1*r4_e3-r0_e2*r1_e1*r2_ni*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e3*r4_ni-r0_e2*r1_e2*r2_e2*r3_ni*r4_e3-r0_e2*r1_e2*r2_e3*r3_e2*r4_ni+r0_e2*r1_e2*r2_e3*r3_ni*r4_e2+r0_e2*r1_e2*r2_ni*r3_e2*r4_e3-r0_e2*r1_e2*r2_ni*r3_e3*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_ni-r0_e2*r1_e3*r2_e1*r3_ni*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_ni-r0_e2*r1_e3*r2_e2*r3_ni*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_ni-r0_e2*r1_e3*r2_e3*r3_ni*r4_e3+r0_e2*r1_e3*r2_ni*r3_e1*r4_e1+r0_e2*r1_e3*r2_ni*r3_e2*r4_e2+r0_e2*r1_e3*r2_ni*r3_e3*r4_e3+-2.0*r0_e2*r1_e3*r2_ni*r3_no*r4_ni-r0_e2*r1_ni*r2_e1*r3_e1*r4_e3+r0_e2*r1_ni*r2_e1*r3_e3*r4_e1-r0_e2*r1_ni*r2_e2*r3_e2*r4_e3+r0_e2*r1_ni*r2_e2*r3_e3*r4_e2-r0_e2*r1_ni*r2_e3*r3_e1*r4_e1-r0_e2*r1_ni*r2_e3*r3_e2*r4_e2-r0_e2*r1_ni*r2_e3*r3_e3*r4_e3+2.0*r0_e2*r1_ni*r2_e3*r3_no*r4_ni+-2.0*r0_e2*r1_ni*r2_no*r3_e3*r4_ni+2.0*r0_e2*r1_ni*r2_no*r3_ni*r4_e3-r0_e3*r1_e1*r2_e1*r3_e2*r4_ni+r0_e3*r1_e1*r2_e1*r3_ni*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_ni-r0_e3*r1_e1*r2_e2*r3_ni*r4_e1-r0_e3*r1_e1*r2_ni*r3_e1*r4_e2+r0_e3*r1_e1*r2_ni*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_ni+r0_e3*r1_e2*r2_e1*r3_ni*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_ni+r0_e3*r1_e2*r2_e2*r3_ni*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_ni+r0_e3*r1_e2*r2_e3*r3_ni*r4_e3-r0_e3*r1_e2*r2_ni*r3_e1*r4_e1-r0_e3*r1_e2*r2_ni*r3_e2*r4_e2-r0_e3*r1_e2*r2_ni*r3_e3*r4_e3+2.0*r0_e3*r1_e2*r2_ni*r3_no*r4_ni+r0_e3*r1_e3*r2_e2*r3_e3*r4_ni-r0_e3*r1_e3*r2_e2*r3_ni*r4_e3-r0_e3*r1_e3*r2_e3*r3_e2*r4_ni+r0_e3*r1_e3*r2_e3*r3_ni*r4_e2+r0_e3*r1_e3*r2_ni*r3_e2*r4_e3-r0_e3*r1_e3*r2_ni*r3_e3*r4_e2+r0_e3*r1_ni*r2_e1*r3_e1*r4_e2-r0_e3*r1_ni*r2_e1*r3_e2*r4_e1+r0_e3*r1_ni*r2_e2*r3_e1*r4_e1+r0_e3*r1_ni*r2_e2*r3_e2*r4_e2+r0_e3*r1_ni*r2_e2*r3_e3*r4_e3+-2.0*r0_e3*r1_ni*r2_e2*r3_no*r4_ni-r0_e3*r1_ni*r2_e3*r3_e2*r4_e3+r0_e3*r1_ni*r2_e3*r3_e3*r4_e2+2.0*r0_e3*r1_ni*r2_no*r3_e2*r4_ni+-2.0*r0_e3*r1_ni*r2_no*r3_ni*r4_e2+r0_ni*r1_e1*r2_e1*r3_e2*r4_e3-r0_ni*r1_e1*r2_e1*r3_e3*r4_e2-r0_ni*r1_e1*r2_e2*r3_e1*r4_e3+r0_ni*r1_e1*r2_e2*r3_e3*r4_e1+r0_ni*r1_e1*r2_e3*r3_e1*r4_e2-r0_ni*r1_e1*r2_e3*r3_e2*r4_e1+r0_ni*r1_e2*r2_e1*r3_e1*r4_e3-r0_ni*r1_e2*r2_e1*r3_e3*r4_e1+r0_ni*r1_e2*r2_e2*r3_e2*r4_e3-r0_ni*r1_e2*r2_e2*r3_e3*r4_e2+r0_ni*r1_e2*r2_e3*r3_e1*r4_e1+r0_ni*r1_e2*r2_e3*r3_e2*r4_e2+r0_ni*r1_e2*r2_e3*r3_e3*r4_e3+-2.0*r0_ni*r1_e2*r2_e3*r3_no*r4_ni+2.0*r0_ni*r1_e2*r2_no*r3_e3*r4_ni+-2.0*r0_ni*r1_e2*r2_no*r3_ni*r4_e3-r0_ni*r1_e3*r2_e1*r3_e1*r4_e2+r0_ni*r1_e3*r2_e1*r3_e2*r4_e1-r0_ni*r1_e3*r2_e2*r3_e1*r4_e1-r0_ni*r1_e3*r2_e2*r3_e2*r4_e2-r0_ni*r1_e3*r2_e2*r3_e3*r4_e3+2.0*r0_ni*r1_e3*r2_e2*r3_no*r4_ni+r0_ni*r1_e3*r2_e3*r3_e2*r4_e3-r0_ni*r1_e3*r2_e3*r3_e3*r4_e2+-2.0*r0_ni*r1_e3*r2_no*r3_e2*r4_ni+2.0*r0_ni*r1_e3*r2_no*r3_ni*r4_e2+-2.0*r0_ni*r1_no*r2_e2*r3_e3*r4_ni+2.0*r0_ni*r1_no*r2_e2*r3_ni*r4_e3+2.0*r0_ni*r1_no*r2_e3*r3_e2*r4_ni+-2.0*r0_ni*r1_no*r2_e3*r3_ni*r4_e2+-2.0*r0_ni*r1_no*r2_ni*r3_e2*r4_e3+2.0*r0_ni*r1_no*r2_ni*r3_e3*r4_e2);
	tmp.m_no_e1_e2_e3_ni = (r0_e1*r1_e2*r2_e3*r3_ni*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_ni-r0_e1*r1_e2*r2_ni*r3_e3*r4_no+r0_e1*r1_e2*r2_ni*r3_no*r4_e3+r0_e1*r1_e2*r2_no*r3_e3*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e3-r0_e1*r1_e3*r2_e2*r3_ni*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_ni+r0_e1*r1_e3*r2_ni*r3_e2*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e2+r0_e1*r1_ni*r2_e2*r3_e3*r4_no-r0_e1*r1_ni*r2_e2*r3_no*r4_e3-r0_e1*r1_ni*r2_e3*r3_e2*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_e2+r0_e1*r1_ni*r2_no*r3_e2*r4_e3-r0_e1*r1_ni*r2_no*r3_e3*r4_e2-r0_e1*r1_no*r2_e2*r3_e3*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e2*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e2-r0_e1*r1_no*r2_ni*r3_e2*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e2-r0_e2*r1_e1*r2_e3*r3_ni*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_ni+r0_e2*r1_e1*r2_ni*r3_e3*r4_no-r0_e2*r1_e1*r2_ni*r3_no*r4_e3-r0_e2*r1_e1*r2_no*r3_e3*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e3+r0_e2*r1_e3*r2_e1*r3_ni*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_ni-r0_e2*r1_e3*r2_ni*r3_e1*r4_no+r0_e2*r1_e3*r2_ni*r3_no*r4_e1+r0_e2*r1_e3*r2_no*r3_e1*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e1-r0_e2*r1_ni*r2_e1*r3_e3*r4_no+r0_e2*r1_ni*r2_e1*r3_no*r4_e3+r0_e2*r1_ni*r2_e3*r3_e1*r4_no-r0_e2*r1_ni*r2_e3*r3_no*r4_e1-r0_e2*r1_ni*r2_no*r3_e1*r4_e3+r0_e2*r1_ni*r2_no*r3_e3*r4_e1+r0_e2*r1_no*r2_e1*r3_e3*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e3-r0_e2*r1_no*r2_e3*r3_e1*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e3-r0_e2*r1_no*r2_ni*r3_e3*r4_e1+r0_e3*r1_e1*r2_e2*r3_ni*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_ni-r0_e3*r1_e1*r2_ni*r3_e2*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e2-r0_e3*r1_e2*r2_e1*r3_ni*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_ni+r0_e3*r1_e2*r2_ni*r3_e1*r4_no-r0_e3*r1_e2*r2_ni*r3_no*r4_e1-r0_e3*r1_e2*r2_no*r3_e1*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e1+r0_e3*r1_ni*r2_e1*r3_e2*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_e2-r0_e3*r1_ni*r2_e2*r3_e1*r4_no+r0_e3*r1_ni*r2_e2*r3_no*r4_e1+r0_e3*r1_ni*r2_no*r3_e1*r4_e2-r0_e3*r1_ni*r2_no*r3_e2*r4_e1-r0_e3*r1_no*r2_e1*r3_e2*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e2+r0_e3*r1_no*r2_e2*r3_e1*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e1-r0_e3*r1_no*r2_ni*r3_e1*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e1-r0_ni*r1_e1*r2_e2*r3_e3*r4_no+r0_ni*r1_e1*r2_e2*r3_no*r4_e3+r0_ni*r1_e1*r2_e3*r3_e2*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_e2-r0_ni*r1_e1*r2_no*r3_e2*r4_e3+r0_ni*r1_e1*r2_no*r3_e3*r4_e2+r0_ni*r1_e2*r2_e1*r3_e3*r4_no-r0_ni*r1_e2*r2_e1*r3_no*r4_e3-r0_ni*r1_e2*r2_e3*r3_e1*r4_no+r0_ni*r1_e2*r2_e3*r3_no*r4_e1+r0_ni*r1_e2*r2_no*r3_e1*r4_e3-r0_ni*r1_e2*r2_no*r3_e3*r4_e1-r0_ni*r1_e3*r2_e1*r3_e2*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_e2+r0_ni*r1_e3*r2_e2*r3_e1*r4_no-r0_ni*r1_e3*r2_e2*r3_no*r4_e1-r0_ni*r1_e3*r2_no*r3_e1*r4_e2+r0_ni*r1_e3*r2_no*r3_e2*r4_e1+r0_ni*r1_no*r2_e1*r3_e2*r4_e3-r0_ni*r1_no*r2_e1*r3_e3*r4_e2-r0_ni*r1_no*r2_e2*r3_e1*r4_e3+r0_ni*r1_no*r2_e2*r3_e3*r4_e1+r0_ni*r1_no*r2_e3*r3_e1*r4_e2-r0_ni*r1_no*r2_e3*r3_e2*r4_e1+r0_no*r1_e1*r2_e2*r3_e3*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e2*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e2+r0_no*r1_e1*r2_ni*r3_e2*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e2-r0_no*r1_e2*r2_e1*r3_e3*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e3+r0_no*r1_e2*r2_e3*r3_e1*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e3+r0_no*r1_e2*r2_ni*r3_e3*r4_e1+r0_no*r1_e3*r2_e1*r3_e2*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e2-r0_no*r1_e3*r2_e2*r3_e1*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e1+r0_no*r1_e3*r2_ni*r3_e1*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e2+r0_no*r1_ni*r2_e2*r3_e1*r4_e3-r0_no*r1_ni*r2_e2*r3_e3*r4_e1-r0_no*r1_ni*r2_e3*r3_e1*r4_e2+r0_no*r1_ni*r2_e3*r3_e2*r4_e1);

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = false;
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_oddVersor_dont_mangle_30_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_oddVersor_dont_mangle_30_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			mul*tmp.m_no, // no
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3, // e3
			mul*tmp.m_ni, // ni
			mul*tmp.m_no_e1_e2, // no_e1_e2
			mul*tmp.m_no_e1_e3, // no_e1_e3
			mul*tmp.m_no_e2_e3, // no_e2_e3
			mul*tmp.m_e1_e2_e3, // e1_e2_e3
			mul*tmp.m_no_e1_ni, // no_e1_ni
			mul*tmp.m_no_e2_ni, // no_e2_ni
			mul*tmp.m_e1_e2_ni, // e1_e2_ni
			mul*tmp.m_no_e3_ni, // no_e3_ni
			mul*tmp.m_e1_e3_ni, // e1_e3_ni
			mul*tmp.m_e2_e3_ni, // e2_e3_ni
			mul*tmp.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);
}
/**
 * Returns random oddVersor with a scale in the interval [0, scale)
 */
public final static oddVersor random_oddVersor_dont_mangle_30(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_oddVersor_dont_mangle_30_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random rotorE3GA with a scale in the interval [0, scale)
 */
public final static rotorE3GA random_rotorE3GA_dont_mangle_34_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	rotorE3GA tmp = new rotorE3GA();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_scalar = (r0_e1*r1_e1+r0_e2*r1_e2+r0_e3*r1_e3);
	tmp.m_e1_e2 = (r0_e1*r1_e2-r0_e2*r1_e1);
	tmp.m_e2_e3 = (r0_e2*r1_e3-r0_e3*r1_e2);
	tmp.m_e3_e1 = -(r0_e1*r1_e3-r0_e3*r1_e1);

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = false;
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_rotorE3GA_dont_mangle_34_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_rotorE3GA_dont_mangle_34_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			mul*tmp.m_scalar, // scalar
			mul*tmp.m_e1_e2, // e1_e2
			mul*tmp.m_e2_e3, // e2_e3
			mul*tmp.m_e3_e1 // e3_e1
		);
}
/**
 * Returns random rotorE3GA with a scale in the interval [0, scale)
 */
public final static rotorE3GA random_rotorE3GA_dont_mangle_34(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_rotorE3GA_dont_mangle_34_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random noni_t with a scale in the interval [0, scale)
 */
public final static noni_t random_noni_t_dont_mangle_37_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new noni_t(		);
}
/**
 * Returns random noni_t with a scale in the interval [0, scale)
 */
public final static noni_t random_noni_t_dont_mangle_37(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_noni_t_dont_mangle_37_ex(scale, minimumNorm, scale * largestCoordinate);
}


/**
 * Generates a random blade.
 * The scale is uniformly distributed over the interval [0 1).
 * The maximum non-zero grade-part is 'grade'.
 * 
 * Only the basis vectors marked in 'basisVectorBitmap' will be used to generate the versor/blade.
 * Use 'basisVectorBitmap = -1' (the default) to use all basisvectors.
 * @return random_blade_dont_mangle_40_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_blade_dont_mangle_40_returns_mv(double scale, int grade, int basisVectorBitmap) {
	double minimumNorm = 0.01;
	double largestCoordinate = 4.0;
	return random_blade_dont_mangle_40_returns_mv_ex(scale, grade, basisVectorBitmap, minimumNorm, scale * largestCoordinate);
}

/**
 * This version of random_blade_dont_mangle_40_returns_mv() has extra arguments which help to avoid
 * near-singular blades / versors.
 * 
 * Near-singular blades / versors are avoid by testing the norm and largest coordinate
 * of the random blade / versor. If the test does not pass, the function recursively
 * tries to generate another random blade / versor.
 * 
 * 'minimumNorm' is the minimum allowed norm of the blade/versor before scaling. 
 * 'minimumNorm' must be > 0.0 for versors.
 * 
 * 'largestCoordinate' is the largest coordinate allowed after scaling.
 * 
 * @return random_blade_dont_mangle_40_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_blade_dont_mangle_40_returns_mv_ex(double scale, int _grade, int basisVectorBitmap, 
		double minimumNorm, double largestCoordinate) 
{
	mv randomVector = new mv();
	//, tmp1, tmp2;
	double[] randomValues = new double[5];
	//double n2, mul;
	int grade = _grade;
	
	// set IR (intermediate result) to 1
	mv IR = new mv (1.0);

	while (grade > 0) {	// loop until grade == 0
		// fill array with random values
		for (int i = 0; i < 5; i++) 
			randomValues[i] = ((basisVectorBitmap & (1 << i)) == 0)
				? 0.0 
				: (-1.0 + 2.0 * genrand());
		
		// make it a multivector:
		randomVector.set(GroupBitmap.GRADE_1, randomValues);
		
		// multiply 
		IR = op(IR, randomVector);
		
		// lower grade
		grade--;
	}
	
	// compute norm/multiplier, apply it, or recurse if we happened to create a near-null versor
	double n2 = norm_returns_scalar(IR);
	if ((double)Math.abs(n2) > minimumNorm * minimumNorm) {
		if (n2 != 0.0) {
			double mul = scale * genrand() / n2;
			if (IR.largestCoordinate() * mul < largestCoordinate)
				return gp(IR, mul);
		}
		else if (IR.largestCoordinate() < largestCoordinate)
			return IR;
	}
	
	// try again:
	return random_blade_dont_mangle_40_returns_mv_ex(scale, _grade, basisVectorBitmap, minimumNorm, largestCoordinate); 
}
/**
 * Returns random sphere with a scale in the interval [0, scale)
 */
public final static sphere random_sphere_dont_mangle_46_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	sphere tmp = new sphere();
	double n, mul, lc;
	boolean nullBlade;
	double re1_e2_e3_ni = -1.0 + 2.0 * genrand(), rno_e2_e3_ni = -1.0 + 2.0 * genrand(), rno_e1_e3_ni = -1.0 + 2.0 * genrand(), rno_e1_e2_ni = -1.0 + 2.0 * genrand(), rno_e1_e2_e3 = -1.0 + 2.0 * genrand();
	tmp.m_e1_e2_e3_ni = re1_e2_e3_ni;
	tmp.m_no_e2_e3_ni = rno_e2_e3_ni;
	tmp.m_no_e1_e3_ni = rno_e1_e3_ni;
	tmp.m_no_e1_e2_ni = rno_e1_e2_ni;
	tmp.m_no_e1_e2_e3 = rno_e1_e2_e3;

	n = norm_dont_mangle_753_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_sphere_dont_mangle_46_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_sphere_dont_mangle_46_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			mul*tmp.m_e1_e2_e3_ni, // e1_e2_e3_ni
			mul*tmp.m_no_e2_e3_ni, // no_e2_e3_ni
			mul*tmp.m_no_e1_e3_ni, // no_e1_e3_ni
			mul*tmp.m_no_e1_e2_ni, // no_e1_e2_ni
			mul*tmp.m_no_e1_e2_e3 // no_e1_e2_e3
		);
}
/**
 * Returns random sphere with a scale in the interval [0, scale)
 */
public final static sphere random_sphere_dont_mangle_46(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_sphere_dont_mangle_46_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random no_t with a scale in the interval [0, scale)
 */
public final static no_t random_no_t_dont_mangle_52_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new no_t(		);
}
/**
 * Returns random no_t with a scale in the interval [0, scale)
 */
public final static no_t random_no_t_dont_mangle_52(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_no_t_dont_mangle_52_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random flatPoint with a scale in the interval [0, scale)
 */
public final static flatPoint random_flatPoint_dont_mangle_56_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	flatPoint tmp = new flatPoint();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_ni = (r0_e1*r1_ni-r0_ni*r1_e1);
	tmp.m_e2_ni = (r0_e2*r1_ni-r0_ni*r1_e2);
	tmp.m_e3_ni = (r0_e3*r1_ni-r0_ni*r1_e3);
	tmp.m_no_ni = (-r0_ni*r1_no+r0_no*r1_ni);

	n = norm_dont_mangle_755_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_flatPoint_dont_mangle_56_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_flatPoint_dont_mangle_56_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			mul*tmp.m_e1_ni, // e1_ni
			mul*tmp.m_e2_ni, // e2_ni
			mul*tmp.m_e3_ni, // e3_ni
			mul*tmp.m_no_ni // no_ni
		);
}
/**
 * Returns random flatPoint with a scale in the interval [0, scale)
 */
public final static flatPoint random_flatPoint_dont_mangle_56(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_flatPoint_dont_mangle_56_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns versor inverse of a using default metric.
 */
public final static rotorE3GA versorInverse_dont_mangle_67(final rotorE3GA a)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar/((_n2_)), // scalar
			-a.m_e1_e2/((_n2_)), // e1_e2
			-a.m_e2_e3/((_n2_)), // e2_e3
			-a.m_e3_e1/((_n2_)) // e3_e1
		);
}
/**
 * Returns random ni_t with a scale in the interval [0, scale)
 */
public final static ni_t random_ni_t_dont_mangle_90_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new ni_t(		);
}
/**
 * Returns random ni_t with a scale in the interval [0, scale)
 */
public final static ni_t random_ni_t_dont_mangle_90(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_ni_t_dont_mangle_90_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random evenVersor with a scale in the interval [0, scale)
 */
public final static evenVersor random_evenVersor_dont_mangle_98_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	evenVersor tmp = new evenVersor();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand(), 
			r3_no = -1.0 + 2.0 * genrand(), r3_e1 = -1.0 + 2.0 * genrand(), r3_e2 = -1.0 + 2.0 * genrand(), r3_e3 = -1.0 + 2.0 * genrand(), r3_ni = -1.0 + 2.0 * genrand();
	tmp.m_scalar = (r0_e1*r1_e1*r2_e1*r3_e1+r0_e1*r1_e1*r2_e2*r3_e2+r0_e1*r1_e1*r2_e3*r3_e3-r0_e1*r1_e1*r2_ni*r3_no-r0_e1*r1_e1*r2_no*r3_ni-r0_e1*r1_e2*r2_e1*r3_e2+r0_e1*r1_e2*r2_e2*r3_e1-r0_e1*r1_e3*r2_e1*r3_e3+r0_e1*r1_e3*r2_e3*r3_e1+r0_e1*r1_ni*r2_e1*r3_no-r0_e1*r1_ni*r2_no*r3_e1+r0_e1*r1_no*r2_e1*r3_ni-r0_e1*r1_no*r2_ni*r3_e1+r0_e2*r1_e1*r2_e1*r3_e2-r0_e2*r1_e1*r2_e2*r3_e1+r0_e2*r1_e2*r2_e1*r3_e1+r0_e2*r1_e2*r2_e2*r3_e2+r0_e2*r1_e2*r2_e3*r3_e3-r0_e2*r1_e2*r2_ni*r3_no-r0_e2*r1_e2*r2_no*r3_ni-r0_e2*r1_e3*r2_e2*r3_e3+r0_e2*r1_e3*r2_e3*r3_e2+r0_e2*r1_ni*r2_e2*r3_no-r0_e2*r1_ni*r2_no*r3_e2+r0_e2*r1_no*r2_e2*r3_ni-r0_e2*r1_no*r2_ni*r3_e2+r0_e3*r1_e1*r2_e1*r3_e3-r0_e3*r1_e1*r2_e3*r3_e1+r0_e3*r1_e2*r2_e2*r3_e3-r0_e3*r1_e2*r2_e3*r3_e2+r0_e3*r1_e3*r2_e1*r3_e1+r0_e3*r1_e3*r2_e2*r3_e2+r0_e3*r1_e3*r2_e3*r3_e3-r0_e3*r1_e3*r2_ni*r3_no-r0_e3*r1_e3*r2_no*r3_ni+r0_e3*r1_ni*r2_e3*r3_no-r0_e3*r1_ni*r2_no*r3_e3+r0_e3*r1_no*r2_e3*r3_ni-r0_e3*r1_no*r2_ni*r3_e3-r0_ni*r1_e1*r2_e1*r3_no+r0_ni*r1_e1*r2_no*r3_e1-r0_ni*r1_e2*r2_e2*r3_no+r0_ni*r1_e2*r2_no*r3_e2-r0_ni*r1_e3*r2_e3*r3_no+r0_ni*r1_e3*r2_no*r3_e3-r0_ni*r1_no*r2_e1*r3_e1-r0_ni*r1_no*r2_e2*r3_e2-r0_ni*r1_no*r2_e3*r3_e3+2.0*r0_ni*r1_no*r2_ni*r3_no-r0_no*r1_e1*r2_e1*r3_ni+r0_no*r1_e1*r2_ni*r3_e1-r0_no*r1_e2*r2_e2*r3_ni+r0_no*r1_e2*r2_ni*r3_e2-r0_no*r1_e3*r2_e3*r3_ni+r0_no*r1_e3*r2_ni*r3_e3-r0_no*r1_ni*r2_e1*r3_e1-r0_no*r1_ni*r2_e2*r3_e2-r0_no*r1_ni*r2_e3*r3_e3+2.0*r0_no*r1_ni*r2_no*r3_ni);
	tmp.m_no_e1 = (-r0_e1*r1_e1*r2_e1*r3_no+r0_e1*r1_e1*r2_no*r3_e1-r0_e1*r1_e2*r2_e2*r3_no+r0_e1*r1_e2*r2_no*r3_e2-r0_e1*r1_e3*r2_e3*r3_no+r0_e1*r1_e3*r2_no*r3_e3-r0_e1*r1_no*r2_e1*r3_e1-r0_e1*r1_no*r2_e2*r3_e2-r0_e1*r1_no*r2_e3*r3_e3+2.0*r0_e1*r1_no*r2_ni*r3_no+r0_e2*r1_e1*r2_e2*r3_no-r0_e2*r1_e1*r2_no*r3_e2-r0_e2*r1_e2*r2_e1*r3_no+r0_e2*r1_e2*r2_no*r3_e1+r0_e2*r1_no*r2_e1*r3_e2-r0_e2*r1_no*r2_e2*r3_e1+r0_e3*r1_e1*r2_e3*r3_no-r0_e3*r1_e1*r2_no*r3_e3-r0_e3*r1_e3*r2_e1*r3_no+r0_e3*r1_e3*r2_no*r3_e1+r0_e3*r1_no*r2_e1*r3_e3-r0_e3*r1_no*r2_e3*r3_e1+r0_no*r1_e1*r2_e1*r3_e1+r0_no*r1_e1*r2_e2*r3_e2+r0_no*r1_e1*r2_e3*r3_e3+-2.0*r0_no*r1_e1*r2_ni*r3_no-r0_no*r1_e2*r2_e1*r3_e2+r0_no*r1_e2*r2_e2*r3_e1-r0_no*r1_e3*r2_e1*r3_e3+r0_no*r1_e3*r2_e3*r3_e1+2.0*r0_no*r1_ni*r2_e1*r3_no+-2.0*r0_no*r1_ni*r2_no*r3_e1);
	tmp.m_no_e2 = (-r0_e1*r1_e1*r2_e2*r3_no+r0_e1*r1_e1*r2_no*r3_e2+r0_e1*r1_e2*r2_e1*r3_no-r0_e1*r1_e2*r2_no*r3_e1-r0_e1*r1_no*r2_e1*r3_e2+r0_e1*r1_no*r2_e2*r3_e1-r0_e2*r1_e1*r2_e1*r3_no+r0_e2*r1_e1*r2_no*r3_e1-r0_e2*r1_e2*r2_e2*r3_no+r0_e2*r1_e2*r2_no*r3_e2-r0_e2*r1_e3*r2_e3*r3_no+r0_e2*r1_e3*r2_no*r3_e3-r0_e2*r1_no*r2_e1*r3_e1-r0_e2*r1_no*r2_e2*r3_e2-r0_e2*r1_no*r2_e3*r3_e3+2.0*r0_e2*r1_no*r2_ni*r3_no+r0_e3*r1_e2*r2_e3*r3_no-r0_e3*r1_e2*r2_no*r3_e3-r0_e3*r1_e3*r2_e2*r3_no+r0_e3*r1_e3*r2_no*r3_e2+r0_e3*r1_no*r2_e2*r3_e3-r0_e3*r1_no*r2_e3*r3_e2+r0_no*r1_e1*r2_e1*r3_e2-r0_no*r1_e1*r2_e2*r3_e1+r0_no*r1_e2*r2_e1*r3_e1+r0_no*r1_e2*r2_e2*r3_e2+r0_no*r1_e2*r2_e3*r3_e3+-2.0*r0_no*r1_e2*r2_ni*r3_no-r0_no*r1_e3*r2_e2*r3_e3+r0_no*r1_e3*r2_e3*r3_e2+2.0*r0_no*r1_ni*r2_e2*r3_no+-2.0*r0_no*r1_ni*r2_no*r3_e2);
	tmp.m_no_e3 = (-r0_e1*r1_e1*r2_e3*r3_no+r0_e1*r1_e1*r2_no*r3_e3+r0_e1*r1_e3*r2_e1*r3_no-r0_e1*r1_e3*r2_no*r3_e1-r0_e1*r1_no*r2_e1*r3_e3+r0_e1*r1_no*r2_e3*r3_e1-r0_e2*r1_e2*r2_e3*r3_no+r0_e2*r1_e2*r2_no*r3_e3+r0_e2*r1_e3*r2_e2*r3_no-r0_e2*r1_e3*r2_no*r3_e2-r0_e2*r1_no*r2_e2*r3_e3+r0_e2*r1_no*r2_e3*r3_e2-r0_e3*r1_e1*r2_e1*r3_no+r0_e3*r1_e1*r2_no*r3_e1-r0_e3*r1_e2*r2_e2*r3_no+r0_e3*r1_e2*r2_no*r3_e2-r0_e3*r1_e3*r2_e3*r3_no+r0_e3*r1_e3*r2_no*r3_e3-r0_e3*r1_no*r2_e1*r3_e1-r0_e3*r1_no*r2_e2*r3_e2-r0_e3*r1_no*r2_e3*r3_e3+2.0*r0_e3*r1_no*r2_ni*r3_no+r0_no*r1_e1*r2_e1*r3_e3-r0_no*r1_e1*r2_e3*r3_e1+r0_no*r1_e2*r2_e2*r3_e3-r0_no*r1_e2*r2_e3*r3_e2+r0_no*r1_e3*r2_e1*r3_e1+r0_no*r1_e3*r2_e2*r3_e2+r0_no*r1_e3*r2_e3*r3_e3+-2.0*r0_no*r1_e3*r2_ni*r3_no+2.0*r0_no*r1_ni*r2_e3*r3_no+-2.0*r0_no*r1_ni*r2_no*r3_e3);
	tmp.m_e1_e2 = (r0_e1*r1_e1*r2_e1*r3_e2-r0_e1*r1_e1*r2_e2*r3_e1+r0_e1*r1_e2*r2_e1*r3_e1+r0_e1*r1_e2*r2_e2*r3_e2+r0_e1*r1_e2*r2_e3*r3_e3-r0_e1*r1_e2*r2_ni*r3_no-r0_e1*r1_e2*r2_no*r3_ni-r0_e1*r1_e3*r2_e2*r3_e3+r0_e1*r1_e3*r2_e3*r3_e2+r0_e1*r1_ni*r2_e2*r3_no-r0_e1*r1_ni*r2_no*r3_e2+r0_e1*r1_no*r2_e2*r3_ni-r0_e1*r1_no*r2_ni*r3_e2-r0_e2*r1_e1*r2_e1*r3_e1-r0_e2*r1_e1*r2_e2*r3_e2-r0_e2*r1_e1*r2_e3*r3_e3+r0_e2*r1_e1*r2_ni*r3_no+r0_e2*r1_e1*r2_no*r3_ni+r0_e2*r1_e2*r2_e1*r3_e2-r0_e2*r1_e2*r2_e2*r3_e1+r0_e2*r1_e3*r2_e1*r3_e3-r0_e2*r1_e3*r2_e3*r3_e1-r0_e2*r1_ni*r2_e1*r3_no+r0_e2*r1_ni*r2_no*r3_e1-r0_e2*r1_no*r2_e1*r3_ni+r0_e2*r1_no*r2_ni*r3_e1+r0_e3*r1_e1*r2_e2*r3_e3-r0_e3*r1_e1*r2_e3*r3_e2-r0_e3*r1_e2*r2_e1*r3_e3+r0_e3*r1_e2*r2_e3*r3_e1+r0_e3*r1_e3*r2_e1*r3_e2-r0_e3*r1_e3*r2_e2*r3_e1-r0_ni*r1_e1*r2_e2*r3_no+r0_ni*r1_e1*r2_no*r3_e2+r0_ni*r1_e2*r2_e1*r3_no-r0_ni*r1_e2*r2_no*r3_e1-r0_ni*r1_no*r2_e1*r3_e2+r0_ni*r1_no*r2_e2*r3_e1-r0_no*r1_e1*r2_e2*r3_ni+r0_no*r1_e1*r2_ni*r3_e2+r0_no*r1_e2*r2_e1*r3_ni-r0_no*r1_e2*r2_ni*r3_e1-r0_no*r1_ni*r2_e1*r3_e2+r0_no*r1_ni*r2_e2*r3_e1);
	tmp.m_e2_e3 = (r0_e1*r1_e1*r2_e2*r3_e3-r0_e1*r1_e1*r2_e3*r3_e2-r0_e1*r1_e2*r2_e1*r3_e3+r0_e1*r1_e2*r2_e3*r3_e1+r0_e1*r1_e3*r2_e1*r3_e2-r0_e1*r1_e3*r2_e2*r3_e1+r0_e2*r1_e1*r2_e1*r3_e3-r0_e2*r1_e1*r2_e3*r3_e1+r0_e2*r1_e2*r2_e2*r3_e3-r0_e2*r1_e2*r2_e3*r3_e2+r0_e2*r1_e3*r2_e1*r3_e1+r0_e2*r1_e3*r2_e2*r3_e2+r0_e2*r1_e3*r2_e3*r3_e3-r0_e2*r1_e3*r2_ni*r3_no-r0_e2*r1_e3*r2_no*r3_ni+r0_e2*r1_ni*r2_e3*r3_no-r0_e2*r1_ni*r2_no*r3_e3+r0_e2*r1_no*r2_e3*r3_ni-r0_e2*r1_no*r2_ni*r3_e3-r0_e3*r1_e1*r2_e1*r3_e2+r0_e3*r1_e1*r2_e2*r3_e1-r0_e3*r1_e2*r2_e1*r3_e1-r0_e3*r1_e2*r2_e2*r3_e2-r0_e3*r1_e2*r2_e3*r3_e3+r0_e3*r1_e2*r2_ni*r3_no+r0_e3*r1_e2*r2_no*r3_ni+r0_e3*r1_e3*r2_e2*r3_e3-r0_e3*r1_e3*r2_e3*r3_e2-r0_e3*r1_ni*r2_e2*r3_no+r0_e3*r1_ni*r2_no*r3_e2-r0_e3*r1_no*r2_e2*r3_ni+r0_e3*r1_no*r2_ni*r3_e2-r0_ni*r1_e2*r2_e3*r3_no+r0_ni*r1_e2*r2_no*r3_e3+r0_ni*r1_e3*r2_e2*r3_no-r0_ni*r1_e3*r2_no*r3_e2-r0_ni*r1_no*r2_e2*r3_e3+r0_ni*r1_no*r2_e3*r3_e2-r0_no*r1_e2*r2_e3*r3_ni+r0_no*r1_e2*r2_ni*r3_e3+r0_no*r1_e3*r2_e2*r3_ni-r0_no*r1_e3*r2_ni*r3_e2-r0_no*r1_ni*r2_e2*r3_e3+r0_no*r1_ni*r2_e3*r3_e2);
	tmp.m_e3_e1 = -(r0_e1*r1_e1*r2_e1*r3_e3-r0_e1*r1_e1*r2_e3*r3_e1+r0_e1*r1_e2*r2_e2*r3_e3-r0_e1*r1_e2*r2_e3*r3_e2+r0_e1*r1_e3*r2_e1*r3_e1+r0_e1*r1_e3*r2_e2*r3_e2+r0_e1*r1_e3*r2_e3*r3_e3-r0_e1*r1_e3*r2_ni*r3_no-r0_e1*r1_e3*r2_no*r3_ni+r0_e1*r1_ni*r2_e3*r3_no-r0_e1*r1_ni*r2_no*r3_e3+r0_e1*r1_no*r2_e3*r3_ni-r0_e1*r1_no*r2_ni*r3_e3-r0_e2*r1_e1*r2_e2*r3_e3+r0_e2*r1_e1*r2_e3*r3_e2+r0_e2*r1_e2*r2_e1*r3_e3-r0_e2*r1_e2*r2_e3*r3_e1-r0_e2*r1_e3*r2_e1*r3_e2+r0_e2*r1_e3*r2_e2*r3_e1-r0_e3*r1_e1*r2_e1*r3_e1-r0_e3*r1_e1*r2_e2*r3_e2-r0_e3*r1_e1*r2_e3*r3_e3+r0_e3*r1_e1*r2_ni*r3_no+r0_e3*r1_e1*r2_no*r3_ni+r0_e3*r1_e2*r2_e1*r3_e2-r0_e3*r1_e2*r2_e2*r3_e1+r0_e3*r1_e3*r2_e1*r3_e3-r0_e3*r1_e3*r2_e3*r3_e1-r0_e3*r1_ni*r2_e1*r3_no+r0_e3*r1_ni*r2_no*r3_e1-r0_e3*r1_no*r2_e1*r3_ni+r0_e3*r1_no*r2_ni*r3_e1-r0_ni*r1_e1*r2_e3*r3_no+r0_ni*r1_e1*r2_no*r3_e3+r0_ni*r1_e3*r2_e1*r3_no-r0_ni*r1_e3*r2_no*r3_e1-r0_ni*r1_no*r2_e1*r3_e3+r0_ni*r1_no*r2_e3*r3_e1-r0_no*r1_e1*r2_e3*r3_ni+r0_no*r1_e1*r2_ni*r3_e3+r0_no*r1_e3*r2_e1*r3_ni-r0_no*r1_e3*r2_ni*r3_e1-r0_no*r1_ni*r2_e1*r3_e3+r0_no*r1_ni*r2_e3*r3_e1);
	tmp.m_e1_ni = (r0_e1*r1_e1*r2_e1*r3_ni-r0_e1*r1_e1*r2_ni*r3_e1+r0_e1*r1_e2*r2_e2*r3_ni-r0_e1*r1_e2*r2_ni*r3_e2+r0_e1*r1_e3*r2_e3*r3_ni-r0_e1*r1_e3*r2_ni*r3_e3+r0_e1*r1_ni*r2_e1*r3_e1+r0_e1*r1_ni*r2_e2*r3_e2+r0_e1*r1_ni*r2_e3*r3_e3+-2.0*r0_e1*r1_ni*r2_no*r3_ni-r0_e2*r1_e1*r2_e2*r3_ni+r0_e2*r1_e1*r2_ni*r3_e2+r0_e2*r1_e2*r2_e1*r3_ni-r0_e2*r1_e2*r2_ni*r3_e1-r0_e2*r1_ni*r2_e1*r3_e2+r0_e2*r1_ni*r2_e2*r3_e1-r0_e3*r1_e1*r2_e3*r3_ni+r0_e3*r1_e1*r2_ni*r3_e3+r0_e3*r1_e3*r2_e1*r3_ni-r0_e3*r1_e3*r2_ni*r3_e1-r0_e3*r1_ni*r2_e1*r3_e3+r0_e3*r1_ni*r2_e3*r3_e1-r0_ni*r1_e1*r2_e1*r3_e1-r0_ni*r1_e1*r2_e2*r3_e2-r0_ni*r1_e1*r2_e3*r3_e3+2.0*r0_ni*r1_e1*r2_no*r3_ni+r0_ni*r1_e2*r2_e1*r3_e2-r0_ni*r1_e2*r2_e2*r3_e1+r0_ni*r1_e3*r2_e1*r3_e3-r0_ni*r1_e3*r2_e3*r3_e1+-2.0*r0_ni*r1_no*r2_e1*r3_ni+2.0*r0_ni*r1_no*r2_ni*r3_e1);
	tmp.m_e2_ni = (r0_e1*r1_e1*r2_e2*r3_ni-r0_e1*r1_e1*r2_ni*r3_e2-r0_e1*r1_e2*r2_e1*r3_ni+r0_e1*r1_e2*r2_ni*r3_e1+r0_e1*r1_ni*r2_e1*r3_e2-r0_e1*r1_ni*r2_e2*r3_e1+r0_e2*r1_e1*r2_e1*r3_ni-r0_e2*r1_e1*r2_ni*r3_e1+r0_e2*r1_e2*r2_e2*r3_ni-r0_e2*r1_e2*r2_ni*r3_e2+r0_e2*r1_e3*r2_e3*r3_ni-r0_e2*r1_e3*r2_ni*r3_e3+r0_e2*r1_ni*r2_e1*r3_e1+r0_e2*r1_ni*r2_e2*r3_e2+r0_e2*r1_ni*r2_e3*r3_e3+-2.0*r0_e2*r1_ni*r2_no*r3_ni-r0_e3*r1_e2*r2_e3*r3_ni+r0_e3*r1_e2*r2_ni*r3_e3+r0_e3*r1_e3*r2_e2*r3_ni-r0_e3*r1_e3*r2_ni*r3_e2-r0_e3*r1_ni*r2_e2*r3_e3+r0_e3*r1_ni*r2_e3*r3_e2-r0_ni*r1_e1*r2_e1*r3_e2+r0_ni*r1_e1*r2_e2*r3_e1-r0_ni*r1_e2*r2_e1*r3_e1-r0_ni*r1_e2*r2_e2*r3_e2-r0_ni*r1_e2*r2_e3*r3_e3+2.0*r0_ni*r1_e2*r2_no*r3_ni+r0_ni*r1_e3*r2_e2*r3_e3-r0_ni*r1_e3*r2_e3*r3_e2+-2.0*r0_ni*r1_no*r2_e2*r3_ni+2.0*r0_ni*r1_no*r2_ni*r3_e2);
	tmp.m_e3_ni = (r0_e1*r1_e1*r2_e3*r3_ni-r0_e1*r1_e1*r2_ni*r3_e3-r0_e1*r1_e3*r2_e1*r3_ni+r0_e1*r1_e3*r2_ni*r3_e1+r0_e1*r1_ni*r2_e1*r3_e3-r0_e1*r1_ni*r2_e3*r3_e1+r0_e2*r1_e2*r2_e3*r3_ni-r0_e2*r1_e2*r2_ni*r3_e3-r0_e2*r1_e3*r2_e2*r3_ni+r0_e2*r1_e3*r2_ni*r3_e2+r0_e2*r1_ni*r2_e2*r3_e3-r0_e2*r1_ni*r2_e3*r3_e2+r0_e3*r1_e1*r2_e1*r3_ni-r0_e3*r1_e1*r2_ni*r3_e1+r0_e3*r1_e2*r2_e2*r3_ni-r0_e3*r1_e2*r2_ni*r3_e2+r0_e3*r1_e3*r2_e3*r3_ni-r0_e3*r1_e3*r2_ni*r3_e3+r0_e3*r1_ni*r2_e1*r3_e1+r0_e3*r1_ni*r2_e2*r3_e2+r0_e3*r1_ni*r2_e3*r3_e3+-2.0*r0_e3*r1_ni*r2_no*r3_ni-r0_ni*r1_e1*r2_e1*r3_e3+r0_ni*r1_e1*r2_e3*r3_e1-r0_ni*r1_e2*r2_e2*r3_e3+r0_ni*r1_e2*r2_e3*r3_e2-r0_ni*r1_e3*r2_e1*r3_e1-r0_ni*r1_e3*r2_e2*r3_e2-r0_ni*r1_e3*r2_e3*r3_e3+2.0*r0_ni*r1_e3*r2_no*r3_ni+-2.0*r0_ni*r1_no*r2_e3*r3_ni+2.0*r0_ni*r1_no*r2_ni*r3_e3);
	tmp.m_no_ni = (-r0_e1*r1_e1*r2_ni*r3_no+r0_e1*r1_e1*r2_no*r3_ni+r0_e1*r1_ni*r2_e1*r3_no-r0_e1*r1_ni*r2_no*r3_e1-r0_e1*r1_no*r2_e1*r3_ni+r0_e1*r1_no*r2_ni*r3_e1-r0_e2*r1_e2*r2_ni*r3_no+r0_e2*r1_e2*r2_no*r3_ni+r0_e2*r1_ni*r2_e2*r3_no-r0_e2*r1_ni*r2_no*r3_e2-r0_e2*r1_no*r2_e2*r3_ni+r0_e2*r1_no*r2_ni*r3_e2-r0_e3*r1_e3*r2_ni*r3_no+r0_e3*r1_e3*r2_no*r3_ni+r0_e3*r1_ni*r2_e3*r3_no-r0_e3*r1_ni*r2_no*r3_e3-r0_e3*r1_no*r2_e3*r3_ni+r0_e3*r1_no*r2_ni*r3_e3-r0_ni*r1_e1*r2_e1*r3_no+r0_ni*r1_e1*r2_no*r3_e1-r0_ni*r1_e2*r2_e2*r3_no+r0_ni*r1_e2*r2_no*r3_e2-r0_ni*r1_e3*r2_e3*r3_no+r0_ni*r1_e3*r2_no*r3_e3-r0_ni*r1_no*r2_e1*r3_e1-r0_ni*r1_no*r2_e2*r3_e2-r0_ni*r1_no*r2_e3*r3_e3+2.0*r0_ni*r1_no*r2_ni*r3_no+r0_no*r1_e1*r2_e1*r3_ni-r0_no*r1_e1*r2_ni*r3_e1+r0_no*r1_e2*r2_e2*r3_ni-r0_no*r1_e2*r2_ni*r3_e2+r0_no*r1_e3*r2_e3*r3_ni-r0_no*r1_e3*r2_ni*r3_e3+r0_no*r1_ni*r2_e1*r3_e1+r0_no*r1_ni*r2_e2*r3_e2+r0_no*r1_ni*r2_e3*r3_e3+-2.0*r0_no*r1_ni*r2_no*r3_ni);
	tmp.m_e1_e2_e3_ni = (r0_e1*r1_e2*r2_e3*r3_ni-r0_e1*r1_e2*r2_ni*r3_e3-r0_e1*r1_e3*r2_e2*r3_ni+r0_e1*r1_e3*r2_ni*r3_e2+r0_e1*r1_ni*r2_e2*r3_e3-r0_e1*r1_ni*r2_e3*r3_e2-r0_e2*r1_e1*r2_e3*r3_ni+r0_e2*r1_e1*r2_ni*r3_e3+r0_e2*r1_e3*r2_e1*r3_ni-r0_e2*r1_e3*r2_ni*r3_e1-r0_e2*r1_ni*r2_e1*r3_e3+r0_e2*r1_ni*r2_e3*r3_e1+r0_e3*r1_e1*r2_e2*r3_ni-r0_e3*r1_e1*r2_ni*r3_e2-r0_e3*r1_e2*r2_e1*r3_ni+r0_e3*r1_e2*r2_ni*r3_e1+r0_e3*r1_ni*r2_e1*r3_e2-r0_e3*r1_ni*r2_e2*r3_e1-r0_ni*r1_e1*r2_e2*r3_e3+r0_ni*r1_e1*r2_e3*r3_e2+r0_ni*r1_e2*r2_e1*r3_e3-r0_ni*r1_e2*r2_e3*r3_e1-r0_ni*r1_e3*r2_e1*r3_e2+r0_ni*r1_e3*r2_e2*r3_e1);
	tmp.m_no_e2_e3_ni = (-r0_e2*r1_e3*r2_ni*r3_no+r0_e2*r1_e3*r2_no*r3_ni+r0_e2*r1_ni*r2_e3*r3_no-r0_e2*r1_ni*r2_no*r3_e3-r0_e2*r1_no*r2_e3*r3_ni+r0_e2*r1_no*r2_ni*r3_e3+r0_e3*r1_e2*r2_ni*r3_no-r0_e3*r1_e2*r2_no*r3_ni-r0_e3*r1_ni*r2_e2*r3_no+r0_e3*r1_ni*r2_no*r3_e2+r0_e3*r1_no*r2_e2*r3_ni-r0_e3*r1_no*r2_ni*r3_e2-r0_ni*r1_e2*r2_e3*r3_no+r0_ni*r1_e2*r2_no*r3_e3+r0_ni*r1_e3*r2_e2*r3_no-r0_ni*r1_e3*r2_no*r3_e2-r0_ni*r1_no*r2_e2*r3_e3+r0_ni*r1_no*r2_e3*r3_e2+r0_no*r1_e2*r2_e3*r3_ni-r0_no*r1_e2*r2_ni*r3_e3-r0_no*r1_e3*r2_e2*r3_ni+r0_no*r1_e3*r2_ni*r3_e2+r0_no*r1_ni*r2_e2*r3_e3-r0_no*r1_ni*r2_e3*r3_e2);
	tmp.m_no_e1_e3_ni = (-r0_e1*r1_e3*r2_ni*r3_no+r0_e1*r1_e3*r2_no*r3_ni+r0_e1*r1_ni*r2_e3*r3_no-r0_e1*r1_ni*r2_no*r3_e3-r0_e1*r1_no*r2_e3*r3_ni+r0_e1*r1_no*r2_ni*r3_e3+r0_e3*r1_e1*r2_ni*r3_no-r0_e3*r1_e1*r2_no*r3_ni-r0_e3*r1_ni*r2_e1*r3_no+r0_e3*r1_ni*r2_no*r3_e1+r0_e3*r1_no*r2_e1*r3_ni-r0_e3*r1_no*r2_ni*r3_e1-r0_ni*r1_e1*r2_e3*r3_no+r0_ni*r1_e1*r2_no*r3_e3+r0_ni*r1_e3*r2_e1*r3_no-r0_ni*r1_e3*r2_no*r3_e1-r0_ni*r1_no*r2_e1*r3_e3+r0_ni*r1_no*r2_e3*r3_e1+r0_no*r1_e1*r2_e3*r3_ni-r0_no*r1_e1*r2_ni*r3_e3-r0_no*r1_e3*r2_e1*r3_ni+r0_no*r1_e3*r2_ni*r3_e1+r0_no*r1_ni*r2_e1*r3_e3-r0_no*r1_ni*r2_e3*r3_e1);
	tmp.m_no_e1_e2_ni = (-r0_e1*r1_e2*r2_ni*r3_no+r0_e1*r1_e2*r2_no*r3_ni+r0_e1*r1_ni*r2_e2*r3_no-r0_e1*r1_ni*r2_no*r3_e2-r0_e1*r1_no*r2_e2*r3_ni+r0_e1*r1_no*r2_ni*r3_e2+r0_e2*r1_e1*r2_ni*r3_no-r0_e2*r1_e1*r2_no*r3_ni-r0_e2*r1_ni*r2_e1*r3_no+r0_e2*r1_ni*r2_no*r3_e1+r0_e2*r1_no*r2_e1*r3_ni-r0_e2*r1_no*r2_ni*r3_e1-r0_ni*r1_e1*r2_e2*r3_no+r0_ni*r1_e1*r2_no*r3_e2+r0_ni*r1_e2*r2_e1*r3_no-r0_ni*r1_e2*r2_no*r3_e1-r0_ni*r1_no*r2_e1*r3_e2+r0_ni*r1_no*r2_e2*r3_e1+r0_no*r1_e1*r2_e2*r3_ni-r0_no*r1_e1*r2_ni*r3_e2-r0_no*r1_e2*r2_e1*r3_ni+r0_no*r1_e2*r2_ni*r3_e1+r0_no*r1_ni*r2_e1*r3_e2-r0_no*r1_ni*r2_e2*r3_e1);
	tmp.m_no_e1_e2_e3 = (-r0_e1*r1_e2*r2_e3*r3_no+r0_e1*r1_e2*r2_no*r3_e3+r0_e1*r1_e3*r2_e2*r3_no-r0_e1*r1_e3*r2_no*r3_e2-r0_e1*r1_no*r2_e2*r3_e3+r0_e1*r1_no*r2_e3*r3_e2+r0_e2*r1_e1*r2_e3*r3_no-r0_e2*r1_e1*r2_no*r3_e3-r0_e2*r1_e3*r2_e1*r3_no+r0_e2*r1_e3*r2_no*r3_e1+r0_e2*r1_no*r2_e1*r3_e3-r0_e2*r1_no*r2_e3*r3_e1-r0_e3*r1_e1*r2_e2*r3_no+r0_e3*r1_e1*r2_no*r3_e2+r0_e3*r1_e2*r2_e1*r3_no-r0_e3*r1_e2*r2_no*r3_e1-r0_e3*r1_no*r2_e1*r3_e2+r0_e3*r1_no*r2_e2*r3_e1+r0_no*r1_e1*r2_e2*r3_e3-r0_no*r1_e1*r2_e3*r3_e2-r0_no*r1_e2*r2_e1*r3_e3+r0_no*r1_e2*r2_e3*r3_e1+r0_no*r1_e3*r2_e1*r3_e2-r0_no*r1_e3*r2_e2*r3_e1);

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = false;
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_evenVersor_dont_mangle_98_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_evenVersor_dont_mangle_98_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			mul*tmp.m_scalar, // scalar
			mul*tmp.m_no_e1, // no_e1
			mul*tmp.m_no_e2, // no_e2
			mul*tmp.m_no_e3, // no_e3
			mul*tmp.m_e1_e2, // e1_e2
			mul*tmp.m_e2_e3, // e2_e3
			mul*tmp.m_e3_e1, // e3_e1
			mul*tmp.m_e1_ni, // e1_ni
			mul*tmp.m_e2_ni, // e2_ni
			mul*tmp.m_e3_ni, // e3_ni
			mul*tmp.m_no_ni, // no_ni
			mul*tmp.m_e1_e2_e3_ni, // e1_e2_e3_ni
			mul*tmp.m_no_e2_e3_ni, // no_e2_e3_ni
			mul*tmp.m_no_e1_e3_ni, // no_e1_e3_ni
			mul*tmp.m_no_e1_e2_ni, // no_e1_e2_ni
			mul*tmp.m_no_e1_e2_e3 // no_e1_e2_e3
		);
}
/**
 * Returns random evenVersor with a scale in the interval [0, scale)
 */
public final static evenVersor random_evenVersor_dont_mangle_98(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_evenVersor_dont_mangle_98_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns versor inverse of a using default metric.
 */
public final static evenVersor versorInverse_dont_mangle_100(final evenVersor a)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar/((_n2_)), // scalar
			-a.m_no_e1/((_n2_)), // no_e1
			-a.m_no_e2/((_n2_)), // no_e2
			-a.m_no_e3/((_n2_)), // no_e3
			-a.m_e1_e2/((_n2_)), // e1_e2
			-a.m_e2_e3/((_n2_)), // e2_e3
			-a.m_e3_e1/((_n2_)), // e3_e1
			-a.m_e1_ni/((_n2_)), // e1_ni
			-a.m_e2_ni/((_n2_)), // e2_ni
			-a.m_e3_ni/((_n2_)), // e3_ni
			-a.m_no_ni/((_n2_)), // no_ni
			a.m_e1_e2_e3_ni/((_n2_)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((_n2_)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((_n2_)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((_n2_)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((_n2_)) // no_e1_e2_e3
		);
}
/**
 * Returns random I5i_t with a scale in the interval [0, scale)
 */
public final static I5i_t random_I5i_t_dont_mangle_123_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new I5i_t(		);
}
/**
 * Returns random I5i_t with a scale in the interval [0, scale)
 */
public final static I5i_t random_I5i_t_dont_mangle_123(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_I5i_t_dont_mangle_123_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns versor inverse of a using default metric.
 */
public final static oddVersor versorInverse_dont_mangle_127(final oddVersor a)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no/((_n2_)), // no
			a.m_e1/((_n2_)), // e1
			a.m_e2/((_n2_)), // e2
			a.m_e3/((_n2_)), // e3
			a.m_ni/((_n2_)), // ni
			-a.m_no_e1_e2/((_n2_)), // no_e1_e2
			-a.m_no_e1_e3/((_n2_)), // no_e1_e3
			-a.m_no_e2_e3/((_n2_)), // no_e2_e3
			-a.m_e1_e2_e3/((_n2_)), // e1_e2_e3
			-a.m_no_e1_ni/((_n2_)), // no_e1_ni
			-a.m_no_e2_ni/((_n2_)), // no_e2_ni
			-a.m_e1_e2_ni/((_n2_)), // e1_e2_ni
			-a.m_no_e3_ni/((_n2_)), // no_e3_ni
			-a.m_e1_e3_ni/((_n2_)), // e1_e3_ni
			-a.m_e2_e3_ni/((_n2_)), // e2_e3_ni
			a.m_no_e1_e2_e3_ni/((_n2_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a * b * inverse(a) using euclidean metric.
 */
public final static mv applyVersor_dont_mangle_146(final mv_if a, final mv_if b)
{
	return extractGrade(gp_em(gp_em(a, b), versorInverse_em(a)), b.to_mv().gu());
}
/**
 * Returns random rotorE3GA with a scale in the interval [0, scale)
 */
public final static rotorE3GA random_rotorE3GA_dont_mangle_147_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	rotorE3GA tmp = new rotorE3GA();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_scalar = (r0_e1*r1_e1+r0_e2*r1_e2+r0_e3*r1_e3);
	tmp.m_e1_e2 = (r0_e1*r1_e2-r0_e2*r1_e1);
	tmp.m_e2_e3 = (r0_e2*r1_e3-r0_e3*r1_e2);
	tmp.m_e3_e1 = -(r0_e1*r1_e3-r0_e3*r1_e1);

	n = norm_em_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = false;
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_rotorE3GA_dont_mangle_147_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_rotorE3GA_dont_mangle_147_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			mul*tmp.m_scalar, // scalar
			mul*tmp.m_e1_e2, // e1_e2
			mul*tmp.m_e2_e3, // e2_e3
			mul*tmp.m_e3_e1 // e3_e1
		);
}
/**
 * Returns random rotorE3GA with a scale in the interval [0, scale)
 */
public final static rotorE3GA random_rotorE3GA_dont_mangle_147(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_rotorE3GA_dont_mangle_147_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random normalizedPoint with a scale in the interval [0, scale)
 */
public final static normalizedPoint random_normalizedPoint_dont_mangle_148_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	normalizedPoint tmp = new normalizedPoint();
	double n, mul, lc;
	boolean nullBlade;
	double re1 = -1.0 + 2.0 * genrand(), re2 = -1.0 + 2.0 * genrand(), re3 = -1.0 + 2.0 * genrand(), rni = -1.0 + 2.0 * genrand();
	tmp.m_e1 = re1;
	tmp.m_e2 = re2;
	tmp.m_e3 = re3;
	tmp.m_ni = rni;

	n = norm_dont_mangle_757_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_normalizedPoint_dont_mangle_148_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_normalizedPoint_dont_mangle_148_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3, // e3
			mul*tmp.m_ni // ni
		);
}
/**
 * Returns random normalizedPoint with a scale in the interval [0, scale)
 */
public final static normalizedPoint random_normalizedPoint_dont_mangle_148(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_normalizedPoint_dont_mangle_148_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns versor inverse of a using euclidean metric.
 */
public final static rotorE3GA versorInverse_dont_mangle_149(final rotorE3GA a)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar/((_n2_)), // scalar
			-a.m_e1_e2/((_n2_)), // e1_e2
			-a.m_e2_e3/((_n2_)), // e2_e3
			-a.m_e3_e1/((_n2_)) // e3_e1
		);
}
/**
 * Returns random oddVersor with a scale in the interval [0, scale)
 */
public final static oddVersor random_oddVersor_dont_mangle_205_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	oddVersor tmp = new oddVersor();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand(), 
			r3_no = -1.0 + 2.0 * genrand(), r3_e1 = -1.0 + 2.0 * genrand(), r3_e2 = -1.0 + 2.0 * genrand(), r3_e3 = -1.0 + 2.0 * genrand(), r3_ni = -1.0 + 2.0 * genrand(), 
			r4_no = -1.0 + 2.0 * genrand(), r4_e1 = -1.0 + 2.0 * genrand(), r4_e2 = -1.0 + 2.0 * genrand(), r4_e3 = -1.0 + 2.0 * genrand(), r4_ni = -1.0 + 2.0 * genrand();
	tmp.m_no = (r0_e1*r1_e1*r2_e1*r3_e1*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_no-r0_e1*r1_e1*r2_e3*r3_no*r4_e3+r0_e1*r1_e1*r2_ni*r3_ni*r4_no-r0_e1*r1_e1*r2_ni*r3_no*r4_ni+r0_e1*r1_e1*r2_no*r3_e1*r4_e1+r0_e1*r1_e1*r2_no*r3_e2*r4_e2+r0_e1*r1_e1*r2_no*r3_e3*r4_e3+r0_e1*r1_e1*r2_no*r3_ni*r4_ni+r0_e1*r1_e1*r2_no*r3_no*r4_no-r0_e1*r1_e2*r2_e1*r3_e2*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e3*r4_no+r0_e1*r1_e3*r2_e1*r3_no*r4_e3+r0_e1*r1_e3*r2_e3*r3_e1*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e1-r0_e1*r1_e3*r2_no*r3_e1*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_e1-r0_e1*r1_ni*r2_e1*r3_ni*r4_no+r0_e1*r1_ni*r2_e1*r3_no*r4_ni+r0_e1*r1_ni*r2_ni*r3_e1*r4_no-r0_e1*r1_ni*r2_ni*r3_no*r4_e1-r0_e1*r1_ni*r2_no*r3_e1*r4_ni+r0_e1*r1_ni*r2_no*r3_ni*r4_e1-r0_e1*r1_no*r2_e1*r3_e1*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_e2-r0_e1*r1_no*r2_e1*r3_e3*r4_e3-r0_e1*r1_no*r2_e1*r3_ni*r4_ni-r0_e1*r1_no*r2_e1*r3_no*r4_no+r0_e1*r1_no*r2_e2*r3_e1*r4_e2-r0_e1*r1_no*r2_e2*r3_e2*r4_e1+r0_e1*r1_no*r2_e3*r3_e1*r4_e3-r0_e1*r1_no*r2_e3*r3_e3*r4_e1+r0_e1*r1_no*r2_ni*r3_e1*r4_ni-r0_e1*r1_no*r2_ni*r3_ni*r4_e1+r0_e1*r1_no*r2_no*r3_e1*r4_no-r0_e1*r1_no*r2_no*r3_no*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_no-r0_e2*r1_e2*r2_e3*r3_no*r4_e3+r0_e2*r1_e2*r2_ni*r3_ni*r4_no-r0_e2*r1_e2*r2_ni*r3_no*r4_ni+r0_e2*r1_e2*r2_no*r3_e1*r4_e1+r0_e2*r1_e2*r2_no*r3_e2*r4_e2+r0_e2*r1_e2*r2_no*r3_e3*r4_e3+r0_e2*r1_e2*r2_no*r3_ni*r4_ni+r0_e2*r1_e2*r2_no*r3_no*r4_no-r0_e2*r1_e3*r2_e2*r3_e3*r4_no+r0_e2*r1_e3*r2_e2*r3_no*r4_e3+r0_e2*r1_e3*r2_e3*r3_e2*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_e2-r0_e2*r1_e3*r2_no*r3_e2*r4_e3+r0_e2*r1_e3*r2_no*r3_e3*r4_e2-r0_e2*r1_ni*r2_e2*r3_ni*r4_no+r0_e2*r1_ni*r2_e2*r3_no*r4_ni+r0_e2*r1_ni*r2_ni*r3_e2*r4_no-r0_e2*r1_ni*r2_ni*r3_no*r4_e2-r0_e2*r1_ni*r2_no*r3_e2*r4_ni+r0_e2*r1_ni*r2_no*r3_ni*r4_e2-r0_e2*r1_no*r2_e1*r3_e1*r4_e2+r0_e2*r1_no*r2_e1*r3_e2*r4_e1-r0_e2*r1_no*r2_e2*r3_e1*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_e2-r0_e2*r1_no*r2_e2*r3_e3*r4_e3-r0_e2*r1_no*r2_e2*r3_ni*r4_ni-r0_e2*r1_no*r2_e2*r3_no*r4_no+r0_e2*r1_no*r2_e3*r3_e2*r4_e3-r0_e2*r1_no*r2_e3*r3_e3*r4_e2+r0_e2*r1_no*r2_ni*r3_e2*r4_ni-r0_e2*r1_no*r2_ni*r3_ni*r4_e2+r0_e2*r1_no*r2_no*r3_e2*r4_no-r0_e2*r1_no*r2_no*r3_no*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_no-r0_e3*r1_e1*r2_e1*r3_no*r4_e3-r0_e3*r1_e1*r2_e3*r3_e1*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e1+r0_e3*r1_e1*r2_no*r3_e1*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_e1+r0_e3*r1_e2*r2_e2*r3_e3*r4_no-r0_e3*r1_e2*r2_e2*r3_no*r4_e3-r0_e3*r1_e2*r2_e3*r3_e2*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_e2+r0_e3*r1_e2*r2_no*r3_e2*r4_e3-r0_e3*r1_e2*r2_no*r3_e3*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_no-r0_e3*r1_e3*r2_e3*r3_no*r4_e3+r0_e3*r1_e3*r2_ni*r3_ni*r4_no-r0_e3*r1_e3*r2_ni*r3_no*r4_ni+r0_e3*r1_e3*r2_no*r3_e1*r4_e1+r0_e3*r1_e3*r2_no*r3_e2*r4_e2+r0_e3*r1_e3*r2_no*r3_e3*r4_e3+r0_e3*r1_e3*r2_no*r3_ni*r4_ni+r0_e3*r1_e3*r2_no*r3_no*r4_no-r0_e3*r1_ni*r2_e3*r3_ni*r4_no+r0_e3*r1_ni*r2_e3*r3_no*r4_ni+r0_e3*r1_ni*r2_ni*r3_e3*r4_no-r0_e3*r1_ni*r2_ni*r3_no*r4_e3-r0_e3*r1_ni*r2_no*r3_e3*r4_ni+r0_e3*r1_ni*r2_no*r3_ni*r4_e3-r0_e3*r1_no*r2_e1*r3_e1*r4_e3+r0_e3*r1_no*r2_e1*r3_e3*r4_e1-r0_e3*r1_no*r2_e2*r3_e2*r4_e3+r0_e3*r1_no*r2_e2*r3_e3*r4_e2-r0_e3*r1_no*r2_e3*r3_e1*r4_e1-r0_e3*r1_no*r2_e3*r3_e2*r4_e2-r0_e3*r1_no*r2_e3*r3_e3*r4_e3-r0_e3*r1_no*r2_e3*r3_ni*r4_ni-r0_e3*r1_no*r2_e3*r3_no*r4_no+r0_e3*r1_no*r2_ni*r3_e3*r4_ni-r0_e3*r1_no*r2_ni*r3_ni*r4_e3+r0_e3*r1_no*r2_no*r3_e3*r4_no-r0_e3*r1_no*r2_no*r3_no*r4_e3+r0_ni*r1_e1*r2_e1*r3_ni*r4_no-r0_ni*r1_e1*r2_e1*r3_no*r4_ni-r0_ni*r1_e1*r2_ni*r3_e1*r4_no+r0_ni*r1_e1*r2_ni*r3_no*r4_e1+r0_ni*r1_e1*r2_no*r3_e1*r4_ni-r0_ni*r1_e1*r2_no*r3_ni*r4_e1+r0_ni*r1_e2*r2_e2*r3_ni*r4_no-r0_ni*r1_e2*r2_e2*r3_no*r4_ni-r0_ni*r1_e2*r2_ni*r3_e2*r4_no+r0_ni*r1_e2*r2_ni*r3_no*r4_e2+r0_ni*r1_e2*r2_no*r3_e2*r4_ni-r0_ni*r1_e2*r2_no*r3_ni*r4_e2+r0_ni*r1_e3*r2_e3*r3_ni*r4_no-r0_ni*r1_e3*r2_e3*r3_no*r4_ni-r0_ni*r1_e3*r2_ni*r3_e3*r4_no+r0_ni*r1_e3*r2_ni*r3_no*r4_e3+r0_ni*r1_e3*r2_no*r3_e3*r4_ni-r0_ni*r1_e3*r2_no*r3_ni*r4_e3+r0_ni*r1_ni*r2_e1*r3_e1*r4_no-r0_ni*r1_ni*r2_e1*r3_no*r4_e1+r0_ni*r1_ni*r2_e2*r3_e2*r4_no-r0_ni*r1_ni*r2_e2*r3_no*r4_e2+r0_ni*r1_ni*r2_e3*r3_e3*r4_no-r0_ni*r1_ni*r2_e3*r3_no*r4_e3+r0_ni*r1_ni*r2_ni*r3_ni*r4_no-r0_ni*r1_ni*r2_ni*r3_no*r4_ni+r0_ni*r1_ni*r2_no*r3_e1*r4_e1+r0_ni*r1_ni*r2_no*r3_e2*r4_e2+r0_ni*r1_ni*r2_no*r3_e3*r4_e3+r0_ni*r1_ni*r2_no*r3_ni*r4_ni+r0_ni*r1_ni*r2_no*r3_no*r4_no-r0_ni*r1_no*r2_e1*r3_e1*r4_ni+r0_ni*r1_no*r2_e1*r3_ni*r4_e1-r0_ni*r1_no*r2_e2*r3_e2*r4_ni+r0_ni*r1_no*r2_e2*r3_ni*r4_e2-r0_ni*r1_no*r2_e3*r3_e3*r4_ni+r0_ni*r1_no*r2_e3*r3_ni*r4_e3-r0_ni*r1_no*r2_ni*r3_e1*r4_e1-r0_ni*r1_no*r2_ni*r3_e2*r4_e2-r0_ni*r1_no*r2_ni*r3_e3*r4_e3-r0_ni*r1_no*r2_ni*r3_ni*r4_ni-r0_ni*r1_no*r2_ni*r3_no*r4_no+r0_ni*r1_no*r2_no*r3_ni*r4_no-r0_ni*r1_no*r2_no*r3_no*r4_ni+r0_no*r1_e1*r2_e1*r3_e1*r4_e1+r0_no*r1_e1*r2_e1*r3_e2*r4_e2+r0_no*r1_e1*r2_e1*r3_e3*r4_e3+r0_no*r1_e1*r2_e1*r3_ni*r4_ni+r0_no*r1_e1*r2_e1*r3_no*r4_no-r0_no*r1_e1*r2_e2*r3_e1*r4_e2+r0_no*r1_e1*r2_e2*r3_e2*r4_e1-r0_no*r1_e1*r2_e3*r3_e1*r4_e3+r0_no*r1_e1*r2_e3*r3_e3*r4_e1-r0_no*r1_e1*r2_ni*r3_e1*r4_ni+r0_no*r1_e1*r2_ni*r3_ni*r4_e1-r0_no*r1_e1*r2_no*r3_e1*r4_no+r0_no*r1_e1*r2_no*r3_no*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_e2-r0_no*r1_e2*r2_e1*r3_e2*r4_e1+r0_no*r1_e2*r2_e2*r3_e1*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_e2+r0_no*r1_e2*r2_e2*r3_e3*r4_e3+r0_no*r1_e2*r2_e2*r3_ni*r4_ni+r0_no*r1_e2*r2_e2*r3_no*r4_no-r0_no*r1_e2*r2_e3*r3_e2*r4_e3+r0_no*r1_e2*r2_e3*r3_e3*r4_e2-r0_no*r1_e2*r2_ni*r3_e2*r4_ni+r0_no*r1_e2*r2_ni*r3_ni*r4_e2-r0_no*r1_e2*r2_no*r3_e2*r4_no+r0_no*r1_e2*r2_no*r3_no*r4_e2+r0_no*r1_e3*r2_e1*r3_e1*r4_e3-r0_no*r1_e3*r2_e1*r3_e3*r4_e1+r0_no*r1_e3*r2_e2*r3_e2*r4_e3-r0_no*r1_e3*r2_e2*r3_e3*r4_e2+r0_no*r1_e3*r2_e3*r3_e1*r4_e1+r0_no*r1_e3*r2_e3*r3_e2*r4_e2+r0_no*r1_e3*r2_e3*r3_e3*r4_e3+r0_no*r1_e3*r2_e3*r3_ni*r4_ni+r0_no*r1_e3*r2_e3*r3_no*r4_no-r0_no*r1_e3*r2_ni*r3_e3*r4_ni+r0_no*r1_e3*r2_ni*r3_ni*r4_e3-r0_no*r1_e3*r2_no*r3_e3*r4_no+r0_no*r1_e3*r2_no*r3_no*r4_e3+r0_no*r1_ni*r2_e1*r3_e1*r4_ni-r0_no*r1_ni*r2_e1*r3_ni*r4_e1+r0_no*r1_ni*r2_e2*r3_e2*r4_ni-r0_no*r1_ni*r2_e2*r3_ni*r4_e2+r0_no*r1_ni*r2_e3*r3_e3*r4_ni-r0_no*r1_ni*r2_e3*r3_ni*r4_e3+r0_no*r1_ni*r2_ni*r3_e1*r4_e1+r0_no*r1_ni*r2_ni*r3_e2*r4_e2+r0_no*r1_ni*r2_ni*r3_e3*r4_e3+r0_no*r1_ni*r2_ni*r3_ni*r4_ni+r0_no*r1_ni*r2_ni*r3_no*r4_no-r0_no*r1_ni*r2_no*r3_ni*r4_no+r0_no*r1_ni*r2_no*r3_no*r4_ni+r0_no*r1_no*r2_e1*r3_e1*r4_no-r0_no*r1_no*r2_e1*r3_no*r4_e1+r0_no*r1_no*r2_e2*r3_e2*r4_no-r0_no*r1_no*r2_e2*r3_no*r4_e2+r0_no*r1_no*r2_e3*r3_e3*r4_no-r0_no*r1_no*r2_e3*r3_no*r4_e3+r0_no*r1_no*r2_ni*r3_ni*r4_no-r0_no*r1_no*r2_ni*r3_no*r4_ni+r0_no*r1_no*r2_no*r3_e1*r4_e1+r0_no*r1_no*r2_no*r3_e2*r4_e2+r0_no*r1_no*r2_no*r3_e3*r4_e3+r0_no*r1_no*r2_no*r3_ni*r4_ni+r0_no*r1_no*r2_no*r3_no*r4_no);
	tmp.m_e1 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e1+r0_e1*r1_e1*r2_e1*r3_e2*r4_e2+r0_e1*r1_e1*r2_e1*r3_e3*r4_e3+r0_e1*r1_e1*r2_e1*r3_ni*r4_ni+r0_e1*r1_e1*r2_e1*r3_no*r4_no-r0_e1*r1_e1*r2_e2*r3_e1*r4_e2+r0_e1*r1_e1*r2_e2*r3_e2*r4_e1-r0_e1*r1_e1*r2_e3*r3_e1*r4_e3+r0_e1*r1_e1*r2_e3*r3_e3*r4_e1-r0_e1*r1_e1*r2_ni*r3_e1*r4_ni+r0_e1*r1_e1*r2_ni*r3_ni*r4_e1-r0_e1*r1_e1*r2_no*r3_e1*r4_no+r0_e1*r1_e1*r2_no*r3_no*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_e2-r0_e1*r1_e2*r2_e1*r3_e2*r4_e1+r0_e1*r1_e2*r2_e2*r3_e1*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_e2+r0_e1*r1_e2*r2_e2*r3_e3*r4_e3+r0_e1*r1_e2*r2_e2*r3_ni*r4_ni+r0_e1*r1_e2*r2_e2*r3_no*r4_no-r0_e1*r1_e2*r2_e3*r3_e2*r4_e3+r0_e1*r1_e2*r2_e3*r3_e3*r4_e2-r0_e1*r1_e2*r2_ni*r3_e2*r4_ni+r0_e1*r1_e2*r2_ni*r3_ni*r4_e2-r0_e1*r1_e2*r2_no*r3_e2*r4_no+r0_e1*r1_e2*r2_no*r3_no*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_e3-r0_e1*r1_e3*r2_e1*r3_e3*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_e3-r0_e1*r1_e3*r2_e2*r3_e3*r4_e2+r0_e1*r1_e3*r2_e3*r3_e1*r4_e1+r0_e1*r1_e3*r2_e3*r3_e2*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_e3+r0_e1*r1_e3*r2_e3*r3_ni*r4_ni+r0_e1*r1_e3*r2_e3*r3_no*r4_no-r0_e1*r1_e3*r2_ni*r3_e3*r4_ni+r0_e1*r1_e3*r2_ni*r3_ni*r4_e3-r0_e1*r1_e3*r2_no*r3_e3*r4_no+r0_e1*r1_e3*r2_no*r3_no*r4_e3+r0_e1*r1_ni*r2_e1*r3_e1*r4_ni-r0_e1*r1_ni*r2_e1*r3_ni*r4_e1+r0_e1*r1_ni*r2_e2*r3_e2*r4_ni-r0_e1*r1_ni*r2_e2*r3_ni*r4_e2+r0_e1*r1_ni*r2_e3*r3_e3*r4_ni-r0_e1*r1_ni*r2_e3*r3_ni*r4_e3+r0_e1*r1_ni*r2_ni*r3_e1*r4_e1+r0_e1*r1_ni*r2_ni*r3_e2*r4_e2+r0_e1*r1_ni*r2_ni*r3_e3*r4_e3+r0_e1*r1_ni*r2_ni*r3_ni*r4_ni+r0_e1*r1_ni*r2_ni*r3_no*r4_no-r0_e1*r1_ni*r2_no*r3_ni*r4_no+r0_e1*r1_ni*r2_no*r3_no*r4_ni+r0_e1*r1_no*r2_e1*r3_e1*r4_no-r0_e1*r1_no*r2_e1*r3_no*r4_e1+r0_e1*r1_no*r2_e2*r3_e2*r4_no-r0_e1*r1_no*r2_e2*r3_no*r4_e2+r0_e1*r1_no*r2_e3*r3_e3*r4_no-r0_e1*r1_no*r2_e3*r3_no*r4_e3+r0_e1*r1_no*r2_ni*r3_ni*r4_no-r0_e1*r1_no*r2_ni*r3_no*r4_ni+r0_e1*r1_no*r2_no*r3_e1*r4_e1+r0_e1*r1_no*r2_no*r3_e2*r4_e2+r0_e1*r1_no*r2_no*r3_e3*r4_e3+r0_e1*r1_no*r2_no*r3_ni*r4_ni+r0_e1*r1_no*r2_no*r3_no*r4_no-r0_e2*r1_e1*r2_e1*r3_e1*r4_e2+r0_e2*r1_e1*r2_e1*r3_e2*r4_e1-r0_e2*r1_e1*r2_e2*r3_e1*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_e2-r0_e2*r1_e1*r2_e2*r3_e3*r4_e3-r0_e2*r1_e1*r2_e2*r3_ni*r4_ni-r0_e2*r1_e1*r2_e2*r3_no*r4_no+r0_e2*r1_e1*r2_e3*r3_e2*r4_e3-r0_e2*r1_e1*r2_e3*r3_e3*r4_e2+r0_e2*r1_e1*r2_ni*r3_e2*r4_ni-r0_e2*r1_e1*r2_ni*r3_ni*r4_e2+r0_e2*r1_e1*r2_no*r3_e2*r4_no-r0_e2*r1_e1*r2_no*r3_no*r4_e2+r0_e2*r1_e2*r2_e1*r3_e1*r4_e1+r0_e2*r1_e2*r2_e1*r3_e2*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_e3+r0_e2*r1_e2*r2_e1*r3_ni*r4_ni+r0_e2*r1_e2*r2_e1*r3_no*r4_no-r0_e2*r1_e2*r2_e2*r3_e1*r4_e2+r0_e2*r1_e2*r2_e2*r3_e2*r4_e1-r0_e2*r1_e2*r2_e3*r3_e1*r4_e3+r0_e2*r1_e2*r2_e3*r3_e3*r4_e1-r0_e2*r1_e2*r2_ni*r3_e1*r4_ni+r0_e2*r1_e2*r2_ni*r3_ni*r4_e1-r0_e2*r1_e2*r2_no*r3_e1*r4_no+r0_e2*r1_e2*r2_no*r3_no*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_e3+r0_e2*r1_e3*r2_e1*r3_e3*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_e3-r0_e2*r1_e3*r2_e2*r3_e3*r4_e1-r0_e2*r1_e3*r2_e3*r3_e1*r4_e2+r0_e2*r1_e3*r2_e3*r3_e2*r4_e1-r0_e2*r1_ni*r2_e1*r3_e2*r4_ni+r0_e2*r1_ni*r2_e1*r3_ni*r4_e2+r0_e2*r1_ni*r2_e2*r3_e1*r4_ni-r0_e2*r1_ni*r2_e2*r3_ni*r4_e1-r0_e2*r1_ni*r2_ni*r3_e1*r4_e2+r0_e2*r1_ni*r2_ni*r3_e2*r4_e1-r0_e2*r1_no*r2_e1*r3_e2*r4_no+r0_e2*r1_no*r2_e1*r3_no*r4_e2+r0_e2*r1_no*r2_e2*r3_e1*r4_no-r0_e2*r1_no*r2_e2*r3_no*r4_e1-r0_e2*r1_no*r2_no*r3_e1*r4_e2+r0_e2*r1_no*r2_no*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_e3+r0_e3*r1_e1*r2_e1*r3_e3*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_e3+r0_e3*r1_e1*r2_e2*r3_e3*r4_e2-r0_e3*r1_e1*r2_e3*r3_e1*r4_e1-r0_e3*r1_e1*r2_e3*r3_e2*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_e3-r0_e3*r1_e1*r2_e3*r3_ni*r4_ni-r0_e3*r1_e1*r2_e3*r3_no*r4_no+r0_e3*r1_e1*r2_ni*r3_e3*r4_ni-r0_e3*r1_e1*r2_ni*r3_ni*r4_e3+r0_e3*r1_e1*r2_no*r3_e3*r4_no-r0_e3*r1_e1*r2_no*r3_no*r4_e3+r0_e3*r1_e2*r2_e1*r3_e2*r4_e3-r0_e3*r1_e2*r2_e1*r3_e3*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_e3+r0_e3*r1_e2*r2_e2*r3_e3*r4_e1+r0_e3*r1_e2*r2_e3*r3_e1*r4_e2-r0_e3*r1_e2*r2_e3*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e1*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_e2+r0_e3*r1_e3*r2_e1*r3_e3*r4_e3+r0_e3*r1_e3*r2_e1*r3_ni*r4_ni+r0_e3*r1_e3*r2_e1*r3_no*r4_no-r0_e3*r1_e3*r2_e2*r3_e1*r4_e2+r0_e3*r1_e3*r2_e2*r3_e2*r4_e1-r0_e3*r1_e3*r2_e3*r3_e1*r4_e3+r0_e3*r1_e3*r2_e3*r3_e3*r4_e1-r0_e3*r1_e3*r2_ni*r3_e1*r4_ni+r0_e3*r1_e3*r2_ni*r3_ni*r4_e1-r0_e3*r1_e3*r2_no*r3_e1*r4_no+r0_e3*r1_e3*r2_no*r3_no*r4_e1-r0_e3*r1_ni*r2_e1*r3_e3*r4_ni+r0_e3*r1_ni*r2_e1*r3_ni*r4_e3+r0_e3*r1_ni*r2_e3*r3_e1*r4_ni-r0_e3*r1_ni*r2_e3*r3_ni*r4_e1-r0_e3*r1_ni*r2_ni*r3_e1*r4_e3+r0_e3*r1_ni*r2_ni*r3_e3*r4_e1-r0_e3*r1_no*r2_e1*r3_e3*r4_no+r0_e3*r1_no*r2_e1*r3_no*r4_e3+r0_e3*r1_no*r2_e3*r3_e1*r4_no-r0_e3*r1_no*r2_e3*r3_no*r4_e1-r0_e3*r1_no*r2_no*r3_e1*r4_e3+r0_e3*r1_no*r2_no*r3_e3*r4_e1-r0_ni*r1_e1*r2_e1*r3_e1*r4_ni+r0_ni*r1_e1*r2_e1*r3_ni*r4_e1-r0_ni*r1_e1*r2_e2*r3_e2*r4_ni+r0_ni*r1_e1*r2_e2*r3_ni*r4_e2-r0_ni*r1_e1*r2_e3*r3_e3*r4_ni+r0_ni*r1_e1*r2_e3*r3_ni*r4_e3-r0_ni*r1_e1*r2_ni*r3_e1*r4_e1-r0_ni*r1_e1*r2_ni*r3_e2*r4_e2-r0_ni*r1_e1*r2_ni*r3_e3*r4_e3-r0_ni*r1_e1*r2_ni*r3_ni*r4_ni-r0_ni*r1_e1*r2_ni*r3_no*r4_no+r0_ni*r1_e1*r2_no*r3_ni*r4_no-r0_ni*r1_e1*r2_no*r3_no*r4_ni+r0_ni*r1_e2*r2_e1*r3_e2*r4_ni-r0_ni*r1_e2*r2_e1*r3_ni*r4_e2-r0_ni*r1_e2*r2_e2*r3_e1*r4_ni+r0_ni*r1_e2*r2_e2*r3_ni*r4_e1+r0_ni*r1_e2*r2_ni*r3_e1*r4_e2-r0_ni*r1_e2*r2_ni*r3_e2*r4_e1+r0_ni*r1_e3*r2_e1*r3_e3*r4_ni-r0_ni*r1_e3*r2_e1*r3_ni*r4_e3-r0_ni*r1_e3*r2_e3*r3_e1*r4_ni+r0_ni*r1_e3*r2_e3*r3_ni*r4_e1+r0_ni*r1_e3*r2_ni*r3_e1*r4_e3-r0_ni*r1_e3*r2_ni*r3_e3*r4_e1+r0_ni*r1_ni*r2_e1*r3_e1*r4_e1+r0_ni*r1_ni*r2_e1*r3_e2*r4_e2+r0_ni*r1_ni*r2_e1*r3_e3*r4_e3+r0_ni*r1_ni*r2_e1*r3_ni*r4_ni+r0_ni*r1_ni*r2_e1*r3_no*r4_no-r0_ni*r1_ni*r2_e2*r3_e1*r4_e2+r0_ni*r1_ni*r2_e2*r3_e2*r4_e1-r0_ni*r1_ni*r2_e3*r3_e1*r4_e3+r0_ni*r1_ni*r2_e3*r3_e3*r4_e1-r0_ni*r1_ni*r2_ni*r3_e1*r4_ni+r0_ni*r1_ni*r2_ni*r3_ni*r4_e1-r0_ni*r1_ni*r2_no*r3_e1*r4_no+r0_ni*r1_ni*r2_no*r3_no*r4_e1-r0_ni*r1_no*r2_e1*r3_ni*r4_no+r0_ni*r1_no*r2_e1*r3_no*r4_ni+r0_ni*r1_no*r2_ni*r3_e1*r4_no-r0_ni*r1_no*r2_ni*r3_no*r4_e1-r0_ni*r1_no*r2_no*r3_e1*r4_ni+r0_ni*r1_no*r2_no*r3_ni*r4_e1-r0_no*r1_e1*r2_e1*r3_e1*r4_no+r0_no*r1_e1*r2_e1*r3_no*r4_e1-r0_no*r1_e1*r2_e2*r3_e2*r4_no+r0_no*r1_e1*r2_e2*r3_no*r4_e2-r0_no*r1_e1*r2_e3*r3_e3*r4_no+r0_no*r1_e1*r2_e3*r3_no*r4_e3-r0_no*r1_e1*r2_ni*r3_ni*r4_no+r0_no*r1_e1*r2_ni*r3_no*r4_ni-r0_no*r1_e1*r2_no*r3_e1*r4_e1-r0_no*r1_e1*r2_no*r3_e2*r4_e2-r0_no*r1_e1*r2_no*r3_e3*r4_e3-r0_no*r1_e1*r2_no*r3_ni*r4_ni-r0_no*r1_e1*r2_no*r3_no*r4_no+r0_no*r1_e2*r2_e1*r3_e2*r4_no-r0_no*r1_e2*r2_e1*r3_no*r4_e2-r0_no*r1_e2*r2_e2*r3_e1*r4_no+r0_no*r1_e2*r2_e2*r3_no*r4_e1+r0_no*r1_e2*r2_no*r3_e1*r4_e2-r0_no*r1_e2*r2_no*r3_e2*r4_e1+r0_no*r1_e3*r2_e1*r3_e3*r4_no-r0_no*r1_e3*r2_e1*r3_no*r4_e3-r0_no*r1_e3*r2_e3*r3_e1*r4_no+r0_no*r1_e3*r2_e3*r3_no*r4_e1+r0_no*r1_e3*r2_no*r3_e1*r4_e3-r0_no*r1_e3*r2_no*r3_e3*r4_e1+r0_no*r1_ni*r2_e1*r3_ni*r4_no-r0_no*r1_ni*r2_e1*r3_no*r4_ni-r0_no*r1_ni*r2_ni*r3_e1*r4_no+r0_no*r1_ni*r2_ni*r3_no*r4_e1+r0_no*r1_ni*r2_no*r3_e1*r4_ni-r0_no*r1_ni*r2_no*r3_ni*r4_e1+r0_no*r1_no*r2_e1*r3_e1*r4_e1+r0_no*r1_no*r2_e1*r3_e2*r4_e2+r0_no*r1_no*r2_e1*r3_e3*r4_e3+r0_no*r1_no*r2_e1*r3_ni*r4_ni+r0_no*r1_no*r2_e1*r3_no*r4_no-r0_no*r1_no*r2_e2*r3_e1*r4_e2+r0_no*r1_no*r2_e2*r3_e2*r4_e1-r0_no*r1_no*r2_e3*r3_e1*r4_e3+r0_no*r1_no*r2_e3*r3_e3*r4_e1-r0_no*r1_no*r2_ni*r3_e1*r4_ni+r0_no*r1_no*r2_ni*r3_ni*r4_e1-r0_no*r1_no*r2_no*r3_e1*r4_no+r0_no*r1_no*r2_no*r3_no*r4_e1);
	tmp.m_e2 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e2-r0_e1*r1_e1*r2_e1*r3_e2*r4_e1+r0_e1*r1_e1*r2_e2*r3_e1*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_e2+r0_e1*r1_e1*r2_e2*r3_e3*r4_e3+r0_e1*r1_e1*r2_e2*r3_ni*r4_ni+r0_e1*r1_e1*r2_e2*r3_no*r4_no-r0_e1*r1_e1*r2_e3*r3_e2*r4_e3+r0_e1*r1_e1*r2_e3*r3_e3*r4_e2-r0_e1*r1_e1*r2_ni*r3_e2*r4_ni+r0_e1*r1_e1*r2_ni*r3_ni*r4_e2-r0_e1*r1_e1*r2_no*r3_e2*r4_no+r0_e1*r1_e1*r2_no*r3_no*r4_e2-r0_e1*r1_e2*r2_e1*r3_e1*r4_e1-r0_e1*r1_e2*r2_e1*r3_e2*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_e3-r0_e1*r1_e2*r2_e1*r3_ni*r4_ni-r0_e1*r1_e2*r2_e1*r3_no*r4_no+r0_e1*r1_e2*r2_e2*r3_e1*r4_e2-r0_e1*r1_e2*r2_e2*r3_e2*r4_e1+r0_e1*r1_e2*r2_e3*r3_e1*r4_e3-r0_e1*r1_e2*r2_e3*r3_e3*r4_e1+r0_e1*r1_e2*r2_ni*r3_e1*r4_ni-r0_e1*r1_e2*r2_ni*r3_ni*r4_e1+r0_e1*r1_e2*r2_no*r3_e1*r4_no-r0_e1*r1_e2*r2_no*r3_no*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_e3-r0_e1*r1_e3*r2_e1*r3_e3*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_e3+r0_e1*r1_e3*r2_e2*r3_e3*r4_e1+r0_e1*r1_e3*r2_e3*r3_e1*r4_e2-r0_e1*r1_e3*r2_e3*r3_e2*r4_e1+r0_e1*r1_ni*r2_e1*r3_e2*r4_ni-r0_e1*r1_ni*r2_e1*r3_ni*r4_e2-r0_e1*r1_ni*r2_e2*r3_e1*r4_ni+r0_e1*r1_ni*r2_e2*r3_ni*r4_e1+r0_e1*r1_ni*r2_ni*r3_e1*r4_e2-r0_e1*r1_ni*r2_ni*r3_e2*r4_e1+r0_e1*r1_no*r2_e1*r3_e2*r4_no-r0_e1*r1_no*r2_e1*r3_no*r4_e2-r0_e1*r1_no*r2_e2*r3_e1*r4_no+r0_e1*r1_no*r2_e2*r3_no*r4_e1+r0_e1*r1_no*r2_no*r3_e1*r4_e2-r0_e1*r1_no*r2_no*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e1*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_e2+r0_e2*r1_e1*r2_e1*r3_e3*r4_e3+r0_e2*r1_e1*r2_e1*r3_ni*r4_ni+r0_e2*r1_e1*r2_e1*r3_no*r4_no-r0_e2*r1_e1*r2_e2*r3_e1*r4_e2+r0_e2*r1_e1*r2_e2*r3_e2*r4_e1-r0_e2*r1_e1*r2_e3*r3_e1*r4_e3+r0_e2*r1_e1*r2_e3*r3_e3*r4_e1-r0_e2*r1_e1*r2_ni*r3_e1*r4_ni+r0_e2*r1_e1*r2_ni*r3_ni*r4_e1-r0_e2*r1_e1*r2_no*r3_e1*r4_no+r0_e2*r1_e1*r2_no*r3_no*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_e2-r0_e2*r1_e2*r2_e1*r3_e2*r4_e1+r0_e2*r1_e2*r2_e2*r3_e1*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_e2+r0_e2*r1_e2*r2_e2*r3_e3*r4_e3+r0_e2*r1_e2*r2_e2*r3_ni*r4_ni+r0_e2*r1_e2*r2_e2*r3_no*r4_no-r0_e2*r1_e2*r2_e3*r3_e2*r4_e3+r0_e2*r1_e2*r2_e3*r3_e3*r4_e2-r0_e2*r1_e2*r2_ni*r3_e2*r4_ni+r0_e2*r1_e2*r2_ni*r3_ni*r4_e2-r0_e2*r1_e2*r2_no*r3_e2*r4_no+r0_e2*r1_e2*r2_no*r3_no*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_e3-r0_e2*r1_e3*r2_e1*r3_e3*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_e3-r0_e2*r1_e3*r2_e2*r3_e3*r4_e2+r0_e2*r1_e3*r2_e3*r3_e1*r4_e1+r0_e2*r1_e3*r2_e3*r3_e2*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_e3+r0_e2*r1_e3*r2_e3*r3_ni*r4_ni+r0_e2*r1_e3*r2_e3*r3_no*r4_no-r0_e2*r1_e3*r2_ni*r3_e3*r4_ni+r0_e2*r1_e3*r2_ni*r3_ni*r4_e3-r0_e2*r1_e3*r2_no*r3_e3*r4_no+r0_e2*r1_e3*r2_no*r3_no*r4_e3+r0_e2*r1_ni*r2_e1*r3_e1*r4_ni-r0_e2*r1_ni*r2_e1*r3_ni*r4_e1+r0_e2*r1_ni*r2_e2*r3_e2*r4_ni-r0_e2*r1_ni*r2_e2*r3_ni*r4_e2+r0_e2*r1_ni*r2_e3*r3_e3*r4_ni-r0_e2*r1_ni*r2_e3*r3_ni*r4_e3+r0_e2*r1_ni*r2_ni*r3_e1*r4_e1+r0_e2*r1_ni*r2_ni*r3_e2*r4_e2+r0_e2*r1_ni*r2_ni*r3_e3*r4_e3+r0_e2*r1_ni*r2_ni*r3_ni*r4_ni+r0_e2*r1_ni*r2_ni*r3_no*r4_no-r0_e2*r1_ni*r2_no*r3_ni*r4_no+r0_e2*r1_ni*r2_no*r3_no*r4_ni+r0_e2*r1_no*r2_e1*r3_e1*r4_no-r0_e2*r1_no*r2_e1*r3_no*r4_e1+r0_e2*r1_no*r2_e2*r3_e2*r4_no-r0_e2*r1_no*r2_e2*r3_no*r4_e2+r0_e2*r1_no*r2_e3*r3_e3*r4_no-r0_e2*r1_no*r2_e3*r3_no*r4_e3+r0_e2*r1_no*r2_ni*r3_ni*r4_no-r0_e2*r1_no*r2_ni*r3_no*r4_ni+r0_e2*r1_no*r2_no*r3_e1*r4_e1+r0_e2*r1_no*r2_no*r3_e2*r4_e2+r0_e2*r1_no*r2_no*r3_e3*r4_e3+r0_e2*r1_no*r2_no*r3_ni*r4_ni+r0_e2*r1_no*r2_no*r3_no*r4_no-r0_e3*r1_e1*r2_e1*r3_e2*r4_e3+r0_e3*r1_e1*r2_e1*r3_e3*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_e3-r0_e3*r1_e1*r2_e2*r3_e3*r4_e1-r0_e3*r1_e1*r2_e3*r3_e1*r4_e2+r0_e3*r1_e1*r2_e3*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_e3+r0_e3*r1_e2*r2_e1*r3_e3*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_e3+r0_e3*r1_e2*r2_e2*r3_e3*r4_e2-r0_e3*r1_e2*r2_e3*r3_e1*r4_e1-r0_e3*r1_e2*r2_e3*r3_e2*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_e3-r0_e3*r1_e2*r2_e3*r3_ni*r4_ni-r0_e3*r1_e2*r2_e3*r3_no*r4_no+r0_e3*r1_e2*r2_ni*r3_e3*r4_ni-r0_e3*r1_e2*r2_ni*r3_ni*r4_e3+r0_e3*r1_e2*r2_no*r3_e3*r4_no-r0_e3*r1_e2*r2_no*r3_no*r4_e3+r0_e3*r1_e3*r2_e1*r3_e1*r4_e2-r0_e3*r1_e3*r2_e1*r3_e2*r4_e1+r0_e3*r1_e3*r2_e2*r3_e1*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_e2+r0_e3*r1_e3*r2_e2*r3_e3*r4_e3+r0_e3*r1_e3*r2_e2*r3_ni*r4_ni+r0_e3*r1_e3*r2_e2*r3_no*r4_no-r0_e3*r1_e3*r2_e3*r3_e2*r4_e3+r0_e3*r1_e3*r2_e3*r3_e3*r4_e2-r0_e3*r1_e3*r2_ni*r3_e2*r4_ni+r0_e3*r1_e3*r2_ni*r3_ni*r4_e2-r0_e3*r1_e3*r2_no*r3_e2*r4_no+r0_e3*r1_e3*r2_no*r3_no*r4_e2-r0_e3*r1_ni*r2_e2*r3_e3*r4_ni+r0_e3*r1_ni*r2_e2*r3_ni*r4_e3+r0_e3*r1_ni*r2_e3*r3_e2*r4_ni-r0_e3*r1_ni*r2_e3*r3_ni*r4_e2-r0_e3*r1_ni*r2_ni*r3_e2*r4_e3+r0_e3*r1_ni*r2_ni*r3_e3*r4_e2-r0_e3*r1_no*r2_e2*r3_e3*r4_no+r0_e3*r1_no*r2_e2*r3_no*r4_e3+r0_e3*r1_no*r2_e3*r3_e2*r4_no-r0_e3*r1_no*r2_e3*r3_no*r4_e2-r0_e3*r1_no*r2_no*r3_e2*r4_e3+r0_e3*r1_no*r2_no*r3_e3*r4_e2-r0_ni*r1_e1*r2_e1*r3_e2*r4_ni+r0_ni*r1_e1*r2_e1*r3_ni*r4_e2+r0_ni*r1_e1*r2_e2*r3_e1*r4_ni-r0_ni*r1_e1*r2_e2*r3_ni*r4_e1-r0_ni*r1_e1*r2_ni*r3_e1*r4_e2+r0_ni*r1_e1*r2_ni*r3_e2*r4_e1-r0_ni*r1_e2*r2_e1*r3_e1*r4_ni+r0_ni*r1_e2*r2_e1*r3_ni*r4_e1-r0_ni*r1_e2*r2_e2*r3_e2*r4_ni+r0_ni*r1_e2*r2_e2*r3_ni*r4_e2-r0_ni*r1_e2*r2_e3*r3_e3*r4_ni+r0_ni*r1_e2*r2_e3*r3_ni*r4_e3-r0_ni*r1_e2*r2_ni*r3_e1*r4_e1-r0_ni*r1_e2*r2_ni*r3_e2*r4_e2-r0_ni*r1_e2*r2_ni*r3_e3*r4_e3-r0_ni*r1_e2*r2_ni*r3_ni*r4_ni-r0_ni*r1_e2*r2_ni*r3_no*r4_no+r0_ni*r1_e2*r2_no*r3_ni*r4_no-r0_ni*r1_e2*r2_no*r3_no*r4_ni+r0_ni*r1_e3*r2_e2*r3_e3*r4_ni-r0_ni*r1_e3*r2_e2*r3_ni*r4_e3-r0_ni*r1_e3*r2_e3*r3_e2*r4_ni+r0_ni*r1_e3*r2_e3*r3_ni*r4_e2+r0_ni*r1_e3*r2_ni*r3_e2*r4_e3-r0_ni*r1_e3*r2_ni*r3_e3*r4_e2+r0_ni*r1_ni*r2_e1*r3_e1*r4_e2-r0_ni*r1_ni*r2_e1*r3_e2*r4_e1+r0_ni*r1_ni*r2_e2*r3_e1*r4_e1+r0_ni*r1_ni*r2_e2*r3_e2*r4_e2+r0_ni*r1_ni*r2_e2*r3_e3*r4_e3+r0_ni*r1_ni*r2_e2*r3_ni*r4_ni+r0_ni*r1_ni*r2_e2*r3_no*r4_no-r0_ni*r1_ni*r2_e3*r3_e2*r4_e3+r0_ni*r1_ni*r2_e3*r3_e3*r4_e2-r0_ni*r1_ni*r2_ni*r3_e2*r4_ni+r0_ni*r1_ni*r2_ni*r3_ni*r4_e2-r0_ni*r1_ni*r2_no*r3_e2*r4_no+r0_ni*r1_ni*r2_no*r3_no*r4_e2-r0_ni*r1_no*r2_e2*r3_ni*r4_no+r0_ni*r1_no*r2_e2*r3_no*r4_ni+r0_ni*r1_no*r2_ni*r3_e2*r4_no-r0_ni*r1_no*r2_ni*r3_no*r4_e2-r0_ni*r1_no*r2_no*r3_e2*r4_ni+r0_ni*r1_no*r2_no*r3_ni*r4_e2-r0_no*r1_e1*r2_e1*r3_e2*r4_no+r0_no*r1_e1*r2_e1*r3_no*r4_e2+r0_no*r1_e1*r2_e2*r3_e1*r4_no-r0_no*r1_e1*r2_e2*r3_no*r4_e1-r0_no*r1_e1*r2_no*r3_e1*r4_e2+r0_no*r1_e1*r2_no*r3_e2*r4_e1-r0_no*r1_e2*r2_e1*r3_e1*r4_no+r0_no*r1_e2*r2_e1*r3_no*r4_e1-r0_no*r1_e2*r2_e2*r3_e2*r4_no+r0_no*r1_e2*r2_e2*r3_no*r4_e2-r0_no*r1_e2*r2_e3*r3_e3*r4_no+r0_no*r1_e2*r2_e3*r3_no*r4_e3-r0_no*r1_e2*r2_ni*r3_ni*r4_no+r0_no*r1_e2*r2_ni*r3_no*r4_ni-r0_no*r1_e2*r2_no*r3_e1*r4_e1-r0_no*r1_e2*r2_no*r3_e2*r4_e2-r0_no*r1_e2*r2_no*r3_e3*r4_e3-r0_no*r1_e2*r2_no*r3_ni*r4_ni-r0_no*r1_e2*r2_no*r3_no*r4_no+r0_no*r1_e3*r2_e2*r3_e3*r4_no-r0_no*r1_e3*r2_e2*r3_no*r4_e3-r0_no*r1_e3*r2_e3*r3_e2*r4_no+r0_no*r1_e3*r2_e3*r3_no*r4_e2+r0_no*r1_e3*r2_no*r3_e2*r4_e3-r0_no*r1_e3*r2_no*r3_e3*r4_e2+r0_no*r1_ni*r2_e2*r3_ni*r4_no-r0_no*r1_ni*r2_e2*r3_no*r4_ni-r0_no*r1_ni*r2_ni*r3_e2*r4_no+r0_no*r1_ni*r2_ni*r3_no*r4_e2+r0_no*r1_ni*r2_no*r3_e2*r4_ni-r0_no*r1_ni*r2_no*r3_ni*r4_e2+r0_no*r1_no*r2_e1*r3_e1*r4_e2-r0_no*r1_no*r2_e1*r3_e2*r4_e1+r0_no*r1_no*r2_e2*r3_e1*r4_e1+r0_no*r1_no*r2_e2*r3_e2*r4_e2+r0_no*r1_no*r2_e2*r3_e3*r4_e3+r0_no*r1_no*r2_e2*r3_ni*r4_ni+r0_no*r1_no*r2_e2*r3_no*r4_no-r0_no*r1_no*r2_e3*r3_e2*r4_e3+r0_no*r1_no*r2_e3*r3_e3*r4_e2-r0_no*r1_no*r2_ni*r3_e2*r4_ni+r0_no*r1_no*r2_ni*r3_ni*r4_e2-r0_no*r1_no*r2_no*r3_e2*r4_no+r0_no*r1_no*r2_no*r3_no*r4_e2);
	tmp.m_e3 = (r0_e1*r1_e1*r2_e1*r3_e1*r4_e3-r0_e1*r1_e1*r2_e1*r3_e3*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_e3-r0_e1*r1_e1*r2_e2*r3_e3*r4_e2+r0_e1*r1_e1*r2_e3*r3_e1*r4_e1+r0_e1*r1_e1*r2_e3*r3_e2*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_e3+r0_e1*r1_e1*r2_e3*r3_ni*r4_ni+r0_e1*r1_e1*r2_e3*r3_no*r4_no-r0_e1*r1_e1*r2_ni*r3_e3*r4_ni+r0_e1*r1_e1*r2_ni*r3_ni*r4_e3-r0_e1*r1_e1*r2_no*r3_e3*r4_no+r0_e1*r1_e1*r2_no*r3_no*r4_e3-r0_e1*r1_e2*r2_e1*r3_e2*r4_e3+r0_e1*r1_e2*r2_e1*r3_e3*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_e3-r0_e1*r1_e2*r2_e2*r3_e3*r4_e1-r0_e1*r1_e2*r2_e3*r3_e1*r4_e2+r0_e1*r1_e2*r2_e3*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e1*r4_e1-r0_e1*r1_e3*r2_e1*r3_e2*r4_e2-r0_e1*r1_e3*r2_e1*r3_e3*r4_e3-r0_e1*r1_e3*r2_e1*r3_ni*r4_ni-r0_e1*r1_e3*r2_e1*r3_no*r4_no+r0_e1*r1_e3*r2_e2*r3_e1*r4_e2-r0_e1*r1_e3*r2_e2*r3_e2*r4_e1+r0_e1*r1_e3*r2_e3*r3_e1*r4_e3-r0_e1*r1_e3*r2_e3*r3_e3*r4_e1+r0_e1*r1_e3*r2_ni*r3_e1*r4_ni-r0_e1*r1_e3*r2_ni*r3_ni*r4_e1+r0_e1*r1_e3*r2_no*r3_e1*r4_no-r0_e1*r1_e3*r2_no*r3_no*r4_e1+r0_e1*r1_ni*r2_e1*r3_e3*r4_ni-r0_e1*r1_ni*r2_e1*r3_ni*r4_e3-r0_e1*r1_ni*r2_e3*r3_e1*r4_ni+r0_e1*r1_ni*r2_e3*r3_ni*r4_e1+r0_e1*r1_ni*r2_ni*r3_e1*r4_e3-r0_e1*r1_ni*r2_ni*r3_e3*r4_e1+r0_e1*r1_no*r2_e1*r3_e3*r4_no-r0_e1*r1_no*r2_e1*r3_no*r4_e3-r0_e1*r1_no*r2_e3*r3_e1*r4_no+r0_e1*r1_no*r2_e3*r3_no*r4_e1+r0_e1*r1_no*r2_no*r3_e1*r4_e3-r0_e1*r1_no*r2_no*r3_e3*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_e3-r0_e2*r1_e1*r2_e1*r3_e3*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_e3+r0_e2*r1_e1*r2_e2*r3_e3*r4_e1+r0_e2*r1_e1*r2_e3*r3_e1*r4_e2-r0_e2*r1_e1*r2_e3*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_e3-r0_e2*r1_e2*r2_e1*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_e3-r0_e2*r1_e2*r2_e2*r3_e3*r4_e2+r0_e2*r1_e2*r2_e3*r3_e1*r4_e1+r0_e2*r1_e2*r2_e3*r3_e2*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_e3+r0_e2*r1_e2*r2_e3*r3_ni*r4_ni+r0_e2*r1_e2*r2_e3*r3_no*r4_no-r0_e2*r1_e2*r2_ni*r3_e3*r4_ni+r0_e2*r1_e2*r2_ni*r3_ni*r4_e3-r0_e2*r1_e2*r2_no*r3_e3*r4_no+r0_e2*r1_e2*r2_no*r3_no*r4_e3-r0_e2*r1_e3*r2_e1*r3_e1*r4_e2+r0_e2*r1_e3*r2_e1*r3_e2*r4_e1-r0_e2*r1_e3*r2_e2*r3_e1*r4_e1-r0_e2*r1_e3*r2_e2*r3_e2*r4_e2-r0_e2*r1_e3*r2_e2*r3_e3*r4_e3-r0_e2*r1_e3*r2_e2*r3_ni*r4_ni-r0_e2*r1_e3*r2_e2*r3_no*r4_no+r0_e2*r1_e3*r2_e3*r3_e2*r4_e3-r0_e2*r1_e3*r2_e3*r3_e3*r4_e2+r0_e2*r1_e3*r2_ni*r3_e2*r4_ni-r0_e2*r1_e3*r2_ni*r3_ni*r4_e2+r0_e2*r1_e3*r2_no*r3_e2*r4_no-r0_e2*r1_e3*r2_no*r3_no*r4_e2+r0_e2*r1_ni*r2_e2*r3_e3*r4_ni-r0_e2*r1_ni*r2_e2*r3_ni*r4_e3-r0_e2*r1_ni*r2_e3*r3_e2*r4_ni+r0_e2*r1_ni*r2_e3*r3_ni*r4_e2+r0_e2*r1_ni*r2_ni*r3_e2*r4_e3-r0_e2*r1_ni*r2_ni*r3_e3*r4_e2+r0_e2*r1_no*r2_e2*r3_e3*r4_no-r0_e2*r1_no*r2_e2*r3_no*r4_e3-r0_e2*r1_no*r2_e3*r3_e2*r4_no+r0_e2*r1_no*r2_e3*r3_no*r4_e2+r0_e2*r1_no*r2_no*r3_e2*r4_e3-r0_e2*r1_no*r2_no*r3_e3*r4_e2+r0_e3*r1_e1*r2_e1*r3_e1*r4_e1+r0_e3*r1_e1*r2_e1*r3_e2*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_e3+r0_e3*r1_e1*r2_e1*r3_ni*r4_ni+r0_e3*r1_e1*r2_e1*r3_no*r4_no-r0_e3*r1_e1*r2_e2*r3_e1*r4_e2+r0_e3*r1_e1*r2_e2*r3_e2*r4_e1-r0_e3*r1_e1*r2_e3*r3_e1*r4_e3+r0_e3*r1_e1*r2_e3*r3_e3*r4_e1-r0_e3*r1_e1*r2_ni*r3_e1*r4_ni+r0_e3*r1_e1*r2_ni*r3_ni*r4_e1-r0_e3*r1_e1*r2_no*r3_e1*r4_no+r0_e3*r1_e1*r2_no*r3_no*r4_e1+r0_e3*r1_e2*r2_e1*r3_e1*r4_e2-r0_e3*r1_e2*r2_e1*r3_e2*r4_e1+r0_e3*r1_e2*r2_e2*r3_e1*r4_e1+r0_e3*r1_e2*r2_e2*r3_e2*r4_e2+r0_e3*r1_e2*r2_e2*r3_e3*r4_e3+r0_e3*r1_e2*r2_e2*r3_ni*r4_ni+r0_e3*r1_e2*r2_e2*r3_no*r4_no-r0_e3*r1_e2*r2_e3*r3_e2*r4_e3+r0_e3*r1_e2*r2_e3*r3_e3*r4_e2-r0_e3*r1_e2*r2_ni*r3_e2*r4_ni+r0_e3*r1_e2*r2_ni*r3_ni*r4_e2-r0_e3*r1_e2*r2_no*r3_e2*r4_no+r0_e3*r1_e2*r2_no*r3_no*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_e3-r0_e3*r1_e3*r2_e1*r3_e3*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_e3-r0_e3*r1_e3*r2_e2*r3_e3*r4_e2+r0_e3*r1_e3*r2_e3*r3_e1*r4_e1+r0_e3*r1_e3*r2_e3*r3_e2*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_e3+r0_e3*r1_e3*r2_e3*r3_ni*r4_ni+r0_e3*r1_e3*r2_e3*r3_no*r4_no-r0_e3*r1_e3*r2_ni*r3_e3*r4_ni+r0_e3*r1_e3*r2_ni*r3_ni*r4_e3-r0_e3*r1_e3*r2_no*r3_e3*r4_no+r0_e3*r1_e3*r2_no*r3_no*r4_e3+r0_e3*r1_ni*r2_e1*r3_e1*r4_ni-r0_e3*r1_ni*r2_e1*r3_ni*r4_e1+r0_e3*r1_ni*r2_e2*r3_e2*r4_ni-r0_e3*r1_ni*r2_e2*r3_ni*r4_e2+r0_e3*r1_ni*r2_e3*r3_e3*r4_ni-r0_e3*r1_ni*r2_e3*r3_ni*r4_e3+r0_e3*r1_ni*r2_ni*r3_e1*r4_e1+r0_e3*r1_ni*r2_ni*r3_e2*r4_e2+r0_e3*r1_ni*r2_ni*r3_e3*r4_e3+r0_e3*r1_ni*r2_ni*r3_ni*r4_ni+r0_e3*r1_ni*r2_ni*r3_no*r4_no-r0_e3*r1_ni*r2_no*r3_ni*r4_no+r0_e3*r1_ni*r2_no*r3_no*r4_ni+r0_e3*r1_no*r2_e1*r3_e1*r4_no-r0_e3*r1_no*r2_e1*r3_no*r4_e1+r0_e3*r1_no*r2_e2*r3_e2*r4_no-r0_e3*r1_no*r2_e2*r3_no*r4_e2+r0_e3*r1_no*r2_e3*r3_e3*r4_no-r0_e3*r1_no*r2_e3*r3_no*r4_e3+r0_e3*r1_no*r2_ni*r3_ni*r4_no-r0_e3*r1_no*r2_ni*r3_no*r4_ni+r0_e3*r1_no*r2_no*r3_e1*r4_e1+r0_e3*r1_no*r2_no*r3_e2*r4_e2+r0_e3*r1_no*r2_no*r3_e3*r4_e3+r0_e3*r1_no*r2_no*r3_ni*r4_ni+r0_e3*r1_no*r2_no*r3_no*r4_no-r0_ni*r1_e1*r2_e1*r3_e3*r4_ni+r0_ni*r1_e1*r2_e1*r3_ni*r4_e3+r0_ni*r1_e1*r2_e3*r3_e1*r4_ni-r0_ni*r1_e1*r2_e3*r3_ni*r4_e1-r0_ni*r1_e1*r2_ni*r3_e1*r4_e3+r0_ni*r1_e1*r2_ni*r3_e3*r4_e1-r0_ni*r1_e2*r2_e2*r3_e3*r4_ni+r0_ni*r1_e2*r2_e2*r3_ni*r4_e3+r0_ni*r1_e2*r2_e3*r3_e2*r4_ni-r0_ni*r1_e2*r2_e3*r3_ni*r4_e2-r0_ni*r1_e2*r2_ni*r3_e2*r4_e3+r0_ni*r1_e2*r2_ni*r3_e3*r4_e2-r0_ni*r1_e3*r2_e1*r3_e1*r4_ni+r0_ni*r1_e3*r2_e1*r3_ni*r4_e1-r0_ni*r1_e3*r2_e2*r3_e2*r4_ni+r0_ni*r1_e3*r2_e2*r3_ni*r4_e2-r0_ni*r1_e3*r2_e3*r3_e3*r4_ni+r0_ni*r1_e3*r2_e3*r3_ni*r4_e3-r0_ni*r1_e3*r2_ni*r3_e1*r4_e1-r0_ni*r1_e3*r2_ni*r3_e2*r4_e2-r0_ni*r1_e3*r2_ni*r3_e3*r4_e3-r0_ni*r1_e3*r2_ni*r3_ni*r4_ni-r0_ni*r1_e3*r2_ni*r3_no*r4_no+r0_ni*r1_e3*r2_no*r3_ni*r4_no-r0_ni*r1_e3*r2_no*r3_no*r4_ni+r0_ni*r1_ni*r2_e1*r3_e1*r4_e3-r0_ni*r1_ni*r2_e1*r3_e3*r4_e1+r0_ni*r1_ni*r2_e2*r3_e2*r4_e3-r0_ni*r1_ni*r2_e2*r3_e3*r4_e2+r0_ni*r1_ni*r2_e3*r3_e1*r4_e1+r0_ni*r1_ni*r2_e3*r3_e2*r4_e2+r0_ni*r1_ni*r2_e3*r3_e3*r4_e3+r0_ni*r1_ni*r2_e3*r3_ni*r4_ni+r0_ni*r1_ni*r2_e3*r3_no*r4_no-r0_ni*r1_ni*r2_ni*r3_e3*r4_ni+r0_ni*r1_ni*r2_ni*r3_ni*r4_e3-r0_ni*r1_ni*r2_no*r3_e3*r4_no+r0_ni*r1_ni*r2_no*r3_no*r4_e3-r0_ni*r1_no*r2_e3*r3_ni*r4_no+r0_ni*r1_no*r2_e3*r3_no*r4_ni+r0_ni*r1_no*r2_ni*r3_e3*r4_no-r0_ni*r1_no*r2_ni*r3_no*r4_e3-r0_ni*r1_no*r2_no*r3_e3*r4_ni+r0_ni*r1_no*r2_no*r3_ni*r4_e3-r0_no*r1_e1*r2_e1*r3_e3*r4_no+r0_no*r1_e1*r2_e1*r3_no*r4_e3+r0_no*r1_e1*r2_e3*r3_e1*r4_no-r0_no*r1_e1*r2_e3*r3_no*r4_e1-r0_no*r1_e1*r2_no*r3_e1*r4_e3+r0_no*r1_e1*r2_no*r3_e3*r4_e1-r0_no*r1_e2*r2_e2*r3_e3*r4_no+r0_no*r1_e2*r2_e2*r3_no*r4_e3+r0_no*r1_e2*r2_e3*r3_e2*r4_no-r0_no*r1_e2*r2_e3*r3_no*r4_e2-r0_no*r1_e2*r2_no*r3_e2*r4_e3+r0_no*r1_e2*r2_no*r3_e3*r4_e2-r0_no*r1_e3*r2_e1*r3_e1*r4_no+r0_no*r1_e3*r2_e1*r3_no*r4_e1-r0_no*r1_e3*r2_e2*r3_e2*r4_no+r0_no*r1_e3*r2_e2*r3_no*r4_e2-r0_no*r1_e3*r2_e3*r3_e3*r4_no+r0_no*r1_e3*r2_e3*r3_no*r4_e3-r0_no*r1_e3*r2_ni*r3_ni*r4_no+r0_no*r1_e3*r2_ni*r3_no*r4_ni-r0_no*r1_e3*r2_no*r3_e1*r4_e1-r0_no*r1_e3*r2_no*r3_e2*r4_e2-r0_no*r1_e3*r2_no*r3_e3*r4_e3-r0_no*r1_e3*r2_no*r3_ni*r4_ni-r0_no*r1_e3*r2_no*r3_no*r4_no+r0_no*r1_ni*r2_e3*r3_ni*r4_no-r0_no*r1_ni*r2_e3*r3_no*r4_ni-r0_no*r1_ni*r2_ni*r3_e3*r4_no+r0_no*r1_ni*r2_ni*r3_no*r4_e3+r0_no*r1_ni*r2_no*r3_e3*r4_ni-r0_no*r1_ni*r2_no*r3_ni*r4_e3+r0_no*r1_no*r2_e1*r3_e1*r4_e3-r0_no*r1_no*r2_e1*r3_e3*r4_e1+r0_no*r1_no*r2_e2*r3_e2*r4_e3-r0_no*r1_no*r2_e2*r3_e3*r4_e2+r0_no*r1_no*r2_e3*r3_e1*r4_e1+r0_no*r1_no*r2_e3*r3_e2*r4_e2+r0_no*r1_no*r2_e3*r3_e3*r4_e3+r0_no*r1_no*r2_e3*r3_ni*r4_ni+r0_no*r1_no*r2_e3*r3_no*r4_no-r0_no*r1_no*r2_ni*r3_e3*r4_ni+r0_no*r1_no*r2_ni*r3_ni*r4_e3-r0_no*r1_no*r2_no*r3_e3*r4_no+r0_no*r1_no*r2_no*r3_no*r4_e3);
	tmp.m_ni = (r0_e1*r1_e1*r2_e1*r3_e1*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e1+r0_e1*r1_e1*r2_e2*r3_e2*r4_ni-r0_e1*r1_e1*r2_e2*r3_ni*r4_e2+r0_e1*r1_e1*r2_e3*r3_e3*r4_ni-r0_e1*r1_e1*r2_e3*r3_ni*r4_e3+r0_e1*r1_e1*r2_ni*r3_e1*r4_e1+r0_e1*r1_e1*r2_ni*r3_e2*r4_e2+r0_e1*r1_e1*r2_ni*r3_e3*r4_e3+r0_e1*r1_e1*r2_ni*r3_ni*r4_ni+r0_e1*r1_e1*r2_ni*r3_no*r4_no-r0_e1*r1_e1*r2_no*r3_ni*r4_no+r0_e1*r1_e1*r2_no*r3_no*r4_ni-r0_e1*r1_e2*r2_e1*r3_e2*r4_ni+r0_e1*r1_e2*r2_e1*r3_ni*r4_e2+r0_e1*r1_e2*r2_e2*r3_e1*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e1-r0_e1*r1_e2*r2_ni*r3_e1*r4_e2+r0_e1*r1_e2*r2_ni*r3_e2*r4_e1-r0_e1*r1_e3*r2_e1*r3_e3*r4_ni+r0_e1*r1_e3*r2_e1*r3_ni*r4_e3+r0_e1*r1_e3*r2_e3*r3_e1*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e1-r0_e1*r1_e3*r2_ni*r3_e1*r4_e3+r0_e1*r1_e3*r2_ni*r3_e3*r4_e1-r0_e1*r1_ni*r2_e1*r3_e1*r4_e1-r0_e1*r1_ni*r2_e1*r3_e2*r4_e2-r0_e1*r1_ni*r2_e1*r3_e3*r4_e3-r0_e1*r1_ni*r2_e1*r3_ni*r4_ni-r0_e1*r1_ni*r2_e1*r3_no*r4_no+r0_e1*r1_ni*r2_e2*r3_e1*r4_e2-r0_e1*r1_ni*r2_e2*r3_e2*r4_e1+r0_e1*r1_ni*r2_e3*r3_e1*r4_e3-r0_e1*r1_ni*r2_e3*r3_e3*r4_e1+r0_e1*r1_ni*r2_ni*r3_e1*r4_ni-r0_e1*r1_ni*r2_ni*r3_ni*r4_e1+r0_e1*r1_ni*r2_no*r3_e1*r4_no-r0_e1*r1_ni*r2_no*r3_no*r4_e1+r0_e1*r1_no*r2_e1*r3_ni*r4_no-r0_e1*r1_no*r2_e1*r3_no*r4_ni-r0_e1*r1_no*r2_ni*r3_e1*r4_no+r0_e1*r1_no*r2_ni*r3_no*r4_e1+r0_e1*r1_no*r2_no*r3_e1*r4_ni-r0_e1*r1_no*r2_no*r3_ni*r4_e1+r0_e2*r1_e1*r2_e1*r3_e2*r4_ni-r0_e2*r1_e1*r2_e1*r3_ni*r4_e2-r0_e2*r1_e1*r2_e2*r3_e1*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e1+r0_e2*r1_e1*r2_ni*r3_e1*r4_e2-r0_e2*r1_e1*r2_ni*r3_e2*r4_e1+r0_e2*r1_e2*r2_e1*r3_e1*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e1+r0_e2*r1_e2*r2_e2*r3_e2*r4_ni-r0_e2*r1_e2*r2_e2*r3_ni*r4_e2+r0_e2*r1_e2*r2_e3*r3_e3*r4_ni-r0_e2*r1_e2*r2_e3*r3_ni*r4_e3+r0_e2*r1_e2*r2_ni*r3_e1*r4_e1+r0_e2*r1_e2*r2_ni*r3_e2*r4_e2+r0_e2*r1_e2*r2_ni*r3_e3*r4_e3+r0_e2*r1_e2*r2_ni*r3_ni*r4_ni+r0_e2*r1_e2*r2_ni*r3_no*r4_no-r0_e2*r1_e2*r2_no*r3_ni*r4_no+r0_e2*r1_e2*r2_no*r3_no*r4_ni-r0_e2*r1_e3*r2_e2*r3_e3*r4_ni+r0_e2*r1_e3*r2_e2*r3_ni*r4_e3+r0_e2*r1_e3*r2_e3*r3_e2*r4_ni-r0_e2*r1_e3*r2_e3*r3_ni*r4_e2-r0_e2*r1_e3*r2_ni*r3_e2*r4_e3+r0_e2*r1_e3*r2_ni*r3_e3*r4_e2-r0_e2*r1_ni*r2_e1*r3_e1*r4_e2+r0_e2*r1_ni*r2_e1*r3_e2*r4_e1-r0_e2*r1_ni*r2_e2*r3_e1*r4_e1-r0_e2*r1_ni*r2_e2*r3_e2*r4_e2-r0_e2*r1_ni*r2_e2*r3_e3*r4_e3-r0_e2*r1_ni*r2_e2*r3_ni*r4_ni-r0_e2*r1_ni*r2_e2*r3_no*r4_no+r0_e2*r1_ni*r2_e3*r3_e2*r4_e3-r0_e2*r1_ni*r2_e3*r3_e3*r4_e2+r0_e2*r1_ni*r2_ni*r3_e2*r4_ni-r0_e2*r1_ni*r2_ni*r3_ni*r4_e2+r0_e2*r1_ni*r2_no*r3_e2*r4_no-r0_e2*r1_ni*r2_no*r3_no*r4_e2+r0_e2*r1_no*r2_e2*r3_ni*r4_no-r0_e2*r1_no*r2_e2*r3_no*r4_ni-r0_e2*r1_no*r2_ni*r3_e2*r4_no+r0_e2*r1_no*r2_ni*r3_no*r4_e2+r0_e2*r1_no*r2_no*r3_e2*r4_ni-r0_e2*r1_no*r2_no*r3_ni*r4_e2+r0_e3*r1_e1*r2_e1*r3_e3*r4_ni-r0_e3*r1_e1*r2_e1*r3_ni*r4_e3-r0_e3*r1_e1*r2_e3*r3_e1*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e1+r0_e3*r1_e1*r2_ni*r3_e1*r4_e3-r0_e3*r1_e1*r2_ni*r3_e3*r4_e1+r0_e3*r1_e2*r2_e2*r3_e3*r4_ni-r0_e3*r1_e2*r2_e2*r3_ni*r4_e3-r0_e3*r1_e2*r2_e3*r3_e2*r4_ni+r0_e3*r1_e2*r2_e3*r3_ni*r4_e2+r0_e3*r1_e2*r2_ni*r3_e2*r4_e3-r0_e3*r1_e2*r2_ni*r3_e3*r4_e2+r0_e3*r1_e3*r2_e1*r3_e1*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e1+r0_e3*r1_e3*r2_e2*r3_e2*r4_ni-r0_e3*r1_e3*r2_e2*r3_ni*r4_e2+r0_e3*r1_e3*r2_e3*r3_e3*r4_ni-r0_e3*r1_e3*r2_e3*r3_ni*r4_e3+r0_e3*r1_e3*r2_ni*r3_e1*r4_e1+r0_e3*r1_e3*r2_ni*r3_e2*r4_e2+r0_e3*r1_e3*r2_ni*r3_e3*r4_e3+r0_e3*r1_e3*r2_ni*r3_ni*r4_ni+r0_e3*r1_e3*r2_ni*r3_no*r4_no-r0_e3*r1_e3*r2_no*r3_ni*r4_no+r0_e3*r1_e3*r2_no*r3_no*r4_ni-r0_e3*r1_ni*r2_e1*r3_e1*r4_e3+r0_e3*r1_ni*r2_e1*r3_e3*r4_e1-r0_e3*r1_ni*r2_e2*r3_e2*r4_e3+r0_e3*r1_ni*r2_e2*r3_e3*r4_e2-r0_e3*r1_ni*r2_e3*r3_e1*r4_e1-r0_e3*r1_ni*r2_e3*r3_e2*r4_e2-r0_e3*r1_ni*r2_e3*r3_e3*r4_e3-r0_e3*r1_ni*r2_e3*r3_ni*r4_ni-r0_e3*r1_ni*r2_e3*r3_no*r4_no+r0_e3*r1_ni*r2_ni*r3_e3*r4_ni-r0_e3*r1_ni*r2_ni*r3_ni*r4_e3+r0_e3*r1_ni*r2_no*r3_e3*r4_no-r0_e3*r1_ni*r2_no*r3_no*r4_e3+r0_e3*r1_no*r2_e3*r3_ni*r4_no-r0_e3*r1_no*r2_e3*r3_no*r4_ni-r0_e3*r1_no*r2_ni*r3_e3*r4_no+r0_e3*r1_no*r2_ni*r3_no*r4_e3+r0_e3*r1_no*r2_no*r3_e3*r4_ni-r0_e3*r1_no*r2_no*r3_ni*r4_e3+r0_ni*r1_e1*r2_e1*r3_e1*r4_e1+r0_ni*r1_e1*r2_e1*r3_e2*r4_e2+r0_ni*r1_e1*r2_e1*r3_e3*r4_e3+r0_ni*r1_e1*r2_e1*r3_ni*r4_ni+r0_ni*r1_e1*r2_e1*r3_no*r4_no-r0_ni*r1_e1*r2_e2*r3_e1*r4_e2+r0_ni*r1_e1*r2_e2*r3_e2*r4_e1-r0_ni*r1_e1*r2_e3*r3_e1*r4_e3+r0_ni*r1_e1*r2_e3*r3_e3*r4_e1-r0_ni*r1_e1*r2_ni*r3_e1*r4_ni+r0_ni*r1_e1*r2_ni*r3_ni*r4_e1-r0_ni*r1_e1*r2_no*r3_e1*r4_no+r0_ni*r1_e1*r2_no*r3_no*r4_e1+r0_ni*r1_e2*r2_e1*r3_e1*r4_e2-r0_ni*r1_e2*r2_e1*r3_e2*r4_e1+r0_ni*r1_e2*r2_e2*r3_e1*r4_e1+r0_ni*r1_e2*r2_e2*r3_e2*r4_e2+r0_ni*r1_e2*r2_e2*r3_e3*r4_e3+r0_ni*r1_e2*r2_e2*r3_ni*r4_ni+r0_ni*r1_e2*r2_e2*r3_no*r4_no-r0_ni*r1_e2*r2_e3*r3_e2*r4_e3+r0_ni*r1_e2*r2_e3*r3_e3*r4_e2-r0_ni*r1_e2*r2_ni*r3_e2*r4_ni+r0_ni*r1_e2*r2_ni*r3_ni*r4_e2-r0_ni*r1_e2*r2_no*r3_e2*r4_no+r0_ni*r1_e2*r2_no*r3_no*r4_e2+r0_ni*r1_e3*r2_e1*r3_e1*r4_e3-r0_ni*r1_e3*r2_e1*r3_e3*r4_e1+r0_ni*r1_e3*r2_e2*r3_e2*r4_e3-r0_ni*r1_e3*r2_e2*r3_e3*r4_e2+r0_ni*r1_e3*r2_e3*r3_e1*r4_e1+r0_ni*r1_e3*r2_e3*r3_e2*r4_e2+r0_ni*r1_e3*r2_e3*r3_e3*r4_e3+r0_ni*r1_e3*r2_e3*r3_ni*r4_ni+r0_ni*r1_e3*r2_e3*r3_no*r4_no-r0_ni*r1_e3*r2_ni*r3_e3*r4_ni+r0_ni*r1_e3*r2_ni*r3_ni*r4_e3-r0_ni*r1_e3*r2_no*r3_e3*r4_no+r0_ni*r1_e3*r2_no*r3_no*r4_e3+r0_ni*r1_ni*r2_e1*r3_e1*r4_ni-r0_ni*r1_ni*r2_e1*r3_ni*r4_e1+r0_ni*r1_ni*r2_e2*r3_e2*r4_ni-r0_ni*r1_ni*r2_e2*r3_ni*r4_e2+r0_ni*r1_ni*r2_e3*r3_e3*r4_ni-r0_ni*r1_ni*r2_e3*r3_ni*r4_e3+r0_ni*r1_ni*r2_ni*r3_e1*r4_e1+r0_ni*r1_ni*r2_ni*r3_e2*r4_e2+r0_ni*r1_ni*r2_ni*r3_e3*r4_e3+r0_ni*r1_ni*r2_ni*r3_ni*r4_ni+r0_ni*r1_ni*r2_ni*r3_no*r4_no-r0_ni*r1_ni*r2_no*r3_ni*r4_no+r0_ni*r1_ni*r2_no*r3_no*r4_ni+r0_ni*r1_no*r2_e1*r3_e1*r4_no-r0_ni*r1_no*r2_e1*r3_no*r4_e1+r0_ni*r1_no*r2_e2*r3_e2*r4_no-r0_ni*r1_no*r2_e2*r3_no*r4_e2+r0_ni*r1_no*r2_e3*r3_e3*r4_no-r0_ni*r1_no*r2_e3*r3_no*r4_e3+r0_ni*r1_no*r2_ni*r3_ni*r4_no-r0_ni*r1_no*r2_ni*r3_no*r4_ni+r0_ni*r1_no*r2_no*r3_e1*r4_e1+r0_ni*r1_no*r2_no*r3_e2*r4_e2+r0_ni*r1_no*r2_no*r3_e3*r4_e3+r0_ni*r1_no*r2_no*r3_ni*r4_ni+r0_ni*r1_no*r2_no*r3_no*r4_no-r0_no*r1_e1*r2_e1*r3_ni*r4_no+r0_no*r1_e1*r2_e1*r3_no*r4_ni+r0_no*r1_e1*r2_ni*r3_e1*r4_no-r0_no*r1_e1*r2_ni*r3_no*r4_e1-r0_no*r1_e1*r2_no*r3_e1*r4_ni+r0_no*r1_e1*r2_no*r3_ni*r4_e1-r0_no*r1_e2*r2_e2*r3_ni*r4_no+r0_no*r1_e2*r2_e2*r3_no*r4_ni+r0_no*r1_e2*r2_ni*r3_e2*r4_no-r0_no*r1_e2*r2_ni*r3_no*r4_e2-r0_no*r1_e2*r2_no*r3_e2*r4_ni+r0_no*r1_e2*r2_no*r3_ni*r4_e2-r0_no*r1_e3*r2_e3*r3_ni*r4_no+r0_no*r1_e3*r2_e3*r3_no*r4_ni+r0_no*r1_e3*r2_ni*r3_e3*r4_no-r0_no*r1_e3*r2_ni*r3_no*r4_e3-r0_no*r1_e3*r2_no*r3_e3*r4_ni+r0_no*r1_e3*r2_no*r3_ni*r4_e3-r0_no*r1_ni*r2_e1*r3_e1*r4_no+r0_no*r1_ni*r2_e1*r3_no*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_no+r0_no*r1_ni*r2_e2*r3_no*r4_e2-r0_no*r1_ni*r2_e3*r3_e3*r4_no+r0_no*r1_ni*r2_e3*r3_no*r4_e3-r0_no*r1_ni*r2_ni*r3_ni*r4_no+r0_no*r1_ni*r2_ni*r3_no*r4_ni-r0_no*r1_ni*r2_no*r3_e1*r4_e1-r0_no*r1_ni*r2_no*r3_e2*r4_e2-r0_no*r1_ni*r2_no*r3_e3*r4_e3-r0_no*r1_ni*r2_no*r3_ni*r4_ni-r0_no*r1_ni*r2_no*r3_no*r4_no+r0_no*r1_no*r2_e1*r3_e1*r4_ni-r0_no*r1_no*r2_e1*r3_ni*r4_e1+r0_no*r1_no*r2_e2*r3_e2*r4_ni-r0_no*r1_no*r2_e2*r3_ni*r4_e2+r0_no*r1_no*r2_e3*r3_e3*r4_ni-r0_no*r1_no*r2_e3*r3_ni*r4_e3+r0_no*r1_no*r2_ni*r3_e1*r4_e1+r0_no*r1_no*r2_ni*r3_e2*r4_e2+r0_no*r1_no*r2_ni*r3_e3*r4_e3+r0_no*r1_no*r2_ni*r3_ni*r4_ni+r0_no*r1_no*r2_ni*r3_no*r4_no-r0_no*r1_no*r2_no*r3_ni*r4_no+r0_no*r1_no*r2_no*r3_no*r4_ni);
	tmp.m_no_e1_e2 = (r0_e1*r1_e1*r2_e1*r3_e2*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_no+r0_e1*r1_e1*r2_e2*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_e2-r0_e1*r1_e1*r2_no*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_no-r0_e1*r1_e2*r2_e1*r3_no*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_e3+r0_e1*r1_e2*r2_ni*r3_ni*r4_no-r0_e1*r1_e2*r2_ni*r3_no*r4_ni+r0_e1*r1_e2*r2_no*r3_e1*r4_e1+r0_e1*r1_e2*r2_no*r3_e2*r4_e2+r0_e1*r1_e2*r2_no*r3_e3*r4_e3+r0_e1*r1_e2*r2_no*r3_ni*r4_ni+r0_e1*r1_e2*r2_no*r3_no*r4_no-r0_e1*r1_e3*r2_e2*r3_e3*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_e3+r0_e1*r1_e3*r2_e3*r3_e2*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_e2-r0_e1*r1_ni*r2_e2*r3_ni*r4_no+r0_e1*r1_ni*r2_e2*r3_no*r4_ni+r0_e1*r1_ni*r2_ni*r3_e2*r4_no-r0_e1*r1_ni*r2_ni*r3_no*r4_e2-r0_e1*r1_ni*r2_no*r3_e2*r4_ni+r0_e1*r1_ni*r2_no*r3_ni*r4_e2-r0_e1*r1_no*r2_e1*r3_e1*r4_e2+r0_e1*r1_no*r2_e1*r3_e2*r4_e1-r0_e1*r1_no*r2_e2*r3_e1*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_e2-r0_e1*r1_no*r2_e2*r3_e3*r4_e3-r0_e1*r1_no*r2_e2*r3_ni*r4_ni-r0_e1*r1_no*r2_e2*r3_no*r4_no+r0_e1*r1_no*r2_e3*r3_e2*r4_e3-r0_e1*r1_no*r2_e3*r3_e3*r4_e2+r0_e1*r1_no*r2_ni*r3_e2*r4_ni-r0_e1*r1_no*r2_ni*r3_ni*r4_e2+r0_e1*r1_no*r2_no*r3_e2*r4_no-r0_e1*r1_no*r2_no*r3_no*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_no+r0_e2*r1_e1*r2_e1*r3_no*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_e3-r0_e2*r1_e1*r2_ni*r3_ni*r4_no+r0_e2*r1_e1*r2_ni*r3_no*r4_ni-r0_e2*r1_e1*r2_no*r3_e1*r4_e1-r0_e2*r1_e1*r2_no*r3_e2*r4_e2-r0_e2*r1_e1*r2_no*r3_e3*r4_e3-r0_e2*r1_e1*r2_no*r3_ni*r4_ni-r0_e2*r1_e1*r2_no*r3_no*r4_no+r0_e2*r1_e2*r2_e1*r3_e2*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_no+r0_e2*r1_e2*r2_e2*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_e2-r0_e2*r1_e2*r2_no*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e3*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_e3-r0_e2*r1_e3*r2_e3*r3_e1*r4_no+r0_e2*r1_e3*r2_e3*r3_no*r4_e1+r0_e2*r1_e3*r2_no*r3_e1*r4_e3-r0_e2*r1_e3*r2_no*r3_e3*r4_e1+r0_e2*r1_ni*r2_e1*r3_ni*r4_no-r0_e2*r1_ni*r2_e1*r3_no*r4_ni-r0_e2*r1_ni*r2_ni*r3_e1*r4_no+r0_e2*r1_ni*r2_ni*r3_no*r4_e1+r0_e2*r1_ni*r2_no*r3_e1*r4_ni-r0_e2*r1_ni*r2_no*r3_ni*r4_e1+r0_e2*r1_no*r2_e1*r3_e1*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_e2+r0_e2*r1_no*r2_e1*r3_e3*r4_e3+r0_e2*r1_no*r2_e1*r3_ni*r4_ni+r0_e2*r1_no*r2_e1*r3_no*r4_no-r0_e2*r1_no*r2_e2*r3_e1*r4_e2+r0_e2*r1_no*r2_e2*r3_e2*r4_e1-r0_e2*r1_no*r2_e3*r3_e1*r4_e3+r0_e2*r1_no*r2_e3*r3_e3*r4_e1-r0_e2*r1_no*r2_ni*r3_e1*r4_ni+r0_e2*r1_no*r2_ni*r3_ni*r4_e1-r0_e2*r1_no*r2_no*r3_e1*r4_no+r0_e2*r1_no*r2_no*r3_no*r4_e1+r0_e3*r1_e1*r2_e2*r3_e3*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_e3-r0_e3*r1_e1*r2_e3*r3_e2*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_e3+r0_e3*r1_e2*r2_e3*r3_e1*r4_no-r0_e3*r1_e2*r2_e3*r3_no*r4_e1-r0_e3*r1_e2*r2_no*r3_e1*r4_e3+r0_e3*r1_e2*r2_no*r3_e3*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_no+r0_e3*r1_e3*r2_e2*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_e2-r0_e3*r1_e3*r2_no*r3_e2*r4_e1-r0_e3*r1_no*r2_e1*r3_e2*r4_e3+r0_e3*r1_no*r2_e1*r3_e3*r4_e2+r0_e3*r1_no*r2_e2*r3_e1*r4_e3-r0_e3*r1_no*r2_e2*r3_e3*r4_e1-r0_e3*r1_no*r2_e3*r3_e1*r4_e2+r0_e3*r1_no*r2_e3*r3_e2*r4_e1+r0_ni*r1_e1*r2_e2*r3_ni*r4_no-r0_ni*r1_e1*r2_e2*r3_no*r4_ni-r0_ni*r1_e1*r2_ni*r3_e2*r4_no+r0_ni*r1_e1*r2_ni*r3_no*r4_e2+r0_ni*r1_e1*r2_no*r3_e2*r4_ni-r0_ni*r1_e1*r2_no*r3_ni*r4_e2-r0_ni*r1_e2*r2_e1*r3_ni*r4_no+r0_ni*r1_e2*r2_e1*r3_no*r4_ni+r0_ni*r1_e2*r2_ni*r3_e1*r4_no-r0_ni*r1_e2*r2_ni*r3_no*r4_e1-r0_ni*r1_e2*r2_no*r3_e1*r4_ni+r0_ni*r1_e2*r2_no*r3_ni*r4_e1+r0_ni*r1_ni*r2_e1*r3_e2*r4_no-r0_ni*r1_ni*r2_e1*r3_no*r4_e2-r0_ni*r1_ni*r2_e2*r3_e1*r4_no+r0_ni*r1_ni*r2_e2*r3_no*r4_e1+r0_ni*r1_ni*r2_no*r3_e1*r4_e2-r0_ni*r1_ni*r2_no*r3_e2*r4_e1-r0_ni*r1_no*r2_e1*r3_e2*r4_ni+r0_ni*r1_no*r2_e1*r3_ni*r4_e2+r0_ni*r1_no*r2_e2*r3_e1*r4_ni-r0_ni*r1_no*r2_e2*r3_ni*r4_e1-r0_ni*r1_no*r2_ni*r3_e1*r4_e2+r0_ni*r1_no*r2_ni*r3_e2*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_e2-r0_no*r1_e1*r2_e1*r3_e2*r4_e1+r0_no*r1_e1*r2_e2*r3_e1*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_e2+r0_no*r1_e1*r2_e2*r3_e3*r4_e3+r0_no*r1_e1*r2_e2*r3_ni*r4_ni+r0_no*r1_e1*r2_e2*r3_no*r4_no-r0_no*r1_e1*r2_e3*r3_e2*r4_e3+r0_no*r1_e1*r2_e3*r3_e3*r4_e2-r0_no*r1_e1*r2_ni*r3_e2*r4_ni+r0_no*r1_e1*r2_ni*r3_ni*r4_e2-r0_no*r1_e1*r2_no*r3_e2*r4_no+r0_no*r1_e1*r2_no*r3_no*r4_e2-r0_no*r1_e2*r2_e1*r3_e1*r4_e1-r0_no*r1_e2*r2_e1*r3_e2*r4_e2-r0_no*r1_e2*r2_e1*r3_e3*r4_e3-r0_no*r1_e2*r2_e1*r3_ni*r4_ni-r0_no*r1_e2*r2_e1*r3_no*r4_no+r0_no*r1_e2*r2_e2*r3_e1*r4_e2-r0_no*r1_e2*r2_e2*r3_e2*r4_e1+r0_no*r1_e2*r2_e3*r3_e1*r4_e3-r0_no*r1_e2*r2_e3*r3_e3*r4_e1+r0_no*r1_e2*r2_ni*r3_e1*r4_ni-r0_no*r1_e2*r2_ni*r3_ni*r4_e1+r0_no*r1_e2*r2_no*r3_e1*r4_no-r0_no*r1_e2*r2_no*r3_no*r4_e1+r0_no*r1_e3*r2_e1*r3_e2*r4_e3-r0_no*r1_e3*r2_e1*r3_e3*r4_e2-r0_no*r1_e3*r2_e2*r3_e1*r4_e3+r0_no*r1_e3*r2_e2*r3_e3*r4_e1+r0_no*r1_e3*r2_e3*r3_e1*r4_e2-r0_no*r1_e3*r2_e3*r3_e2*r4_e1+r0_no*r1_ni*r2_e1*r3_e2*r4_ni-r0_no*r1_ni*r2_e1*r3_ni*r4_e2-r0_no*r1_ni*r2_e2*r3_e1*r4_ni+r0_no*r1_ni*r2_e2*r3_ni*r4_e1+r0_no*r1_ni*r2_ni*r3_e1*r4_e2-r0_no*r1_ni*r2_ni*r3_e2*r4_e1+r0_no*r1_no*r2_e1*r3_e2*r4_no-r0_no*r1_no*r2_e1*r3_no*r4_e2-r0_no*r1_no*r2_e2*r3_e1*r4_no+r0_no*r1_no*r2_e2*r3_no*r4_e1+r0_no*r1_no*r2_no*r3_e1*r4_e2-r0_no*r1_no*r2_no*r3_e2*r4_e1);
	tmp.m_no_e1_e3 = (r0_e1*r1_e1*r2_e1*r3_e3*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_e3-r0_e1*r1_e1*r2_e3*r3_e1*r4_no+r0_e1*r1_e1*r2_e3*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_e3-r0_e1*r1_e1*r2_no*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e3*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_e3-r0_e1*r1_e2*r2_e3*r3_e2*r4_no+r0_e1*r1_e2*r2_e3*r3_no*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_e3-r0_e1*r1_e2*r2_no*r3_e3*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_no-r0_e1*r1_e3*r2_e1*r3_no*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_no-r0_e1*r1_e3*r2_e2*r3_no*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_e3+r0_e1*r1_e3*r2_ni*r3_ni*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_ni+r0_e1*r1_e3*r2_no*r3_e1*r4_e1+r0_e1*r1_e3*r2_no*r3_e2*r4_e2+r0_e1*r1_e3*r2_no*r3_e3*r4_e3+r0_e1*r1_e3*r2_no*r3_ni*r4_ni+r0_e1*r1_e3*r2_no*r3_no*r4_no-r0_e1*r1_ni*r2_e3*r3_ni*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_ni+r0_e1*r1_ni*r2_ni*r3_e3*r4_no-r0_e1*r1_ni*r2_ni*r3_no*r4_e3-r0_e1*r1_ni*r2_no*r3_e3*r4_ni+r0_e1*r1_ni*r2_no*r3_ni*r4_e3-r0_e1*r1_no*r2_e1*r3_e1*r4_e3+r0_e1*r1_no*r2_e1*r3_e3*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_e3+r0_e1*r1_no*r2_e2*r3_e3*r4_e2-r0_e1*r1_no*r2_e3*r3_e1*r4_e1-r0_e1*r1_no*r2_e3*r3_e2*r4_e2-r0_e1*r1_no*r2_e3*r3_e3*r4_e3-r0_e1*r1_no*r2_e3*r3_ni*r4_ni-r0_e1*r1_no*r2_e3*r3_no*r4_no+r0_e1*r1_no*r2_ni*r3_e3*r4_ni-r0_e1*r1_no*r2_ni*r3_ni*r4_e3+r0_e1*r1_no*r2_no*r3_e3*r4_no-r0_e1*r1_no*r2_no*r3_no*r4_e3-r0_e2*r1_e1*r2_e2*r3_e3*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_e3+r0_e2*r1_e1*r2_e3*r3_e2*r4_no-r0_e2*r1_e1*r2_e3*r3_no*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_e3+r0_e2*r1_e1*r2_no*r3_e3*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_e3-r0_e2*r1_e2*r2_e3*r3_e1*r4_no+r0_e2*r1_e2*r2_e3*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_e3-r0_e2*r1_e2*r2_no*r3_e3*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_no+r0_e2*r1_e3*r2_e1*r3_no*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_no-r0_e2*r1_e3*r2_e2*r3_no*r4_e1-r0_e2*r1_e3*r2_no*r3_e1*r4_e2+r0_e2*r1_e3*r2_no*r3_e2*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_e3-r0_e2*r1_no*r2_e1*r3_e3*r4_e2-r0_e2*r1_no*r2_e2*r3_e1*r4_e3+r0_e2*r1_no*r2_e2*r3_e3*r4_e1+r0_e2*r1_no*r2_e3*r3_e1*r4_e2-r0_e2*r1_no*r2_e3*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_no+r0_e3*r1_e1*r2_e1*r3_no*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_no+r0_e3*r1_e1*r2_e2*r3_no*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_e3-r0_e3*r1_e1*r2_ni*r3_ni*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_ni-r0_e3*r1_e1*r2_no*r3_e1*r4_e1-r0_e3*r1_e1*r2_no*r3_e2*r4_e2-r0_e3*r1_e1*r2_no*r3_e3*r4_e3-r0_e3*r1_e1*r2_no*r3_ni*r4_ni-r0_e3*r1_e1*r2_no*r3_no*r4_no+r0_e3*r1_e2*r2_e1*r3_e2*r4_no-r0_e3*r1_e2*r2_e1*r3_no*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_no+r0_e3*r1_e2*r2_e2*r3_no*r4_e1+r0_e3*r1_e2*r2_no*r3_e1*r4_e2-r0_e3*r1_e2*r2_no*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e3*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_e3-r0_e3*r1_e3*r2_e3*r3_e1*r4_no+r0_e3*r1_e3*r2_e3*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_e3-r0_e3*r1_e3*r2_no*r3_e3*r4_e1+r0_e3*r1_ni*r2_e1*r3_ni*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_ni-r0_e3*r1_ni*r2_ni*r3_e1*r4_no+r0_e3*r1_ni*r2_ni*r3_no*r4_e1+r0_e3*r1_ni*r2_no*r3_e1*r4_ni-r0_e3*r1_ni*r2_no*r3_ni*r4_e1+r0_e3*r1_no*r2_e1*r3_e1*r4_e1+r0_e3*r1_no*r2_e1*r3_e2*r4_e2+r0_e3*r1_no*r2_e1*r3_e3*r4_e3+r0_e3*r1_no*r2_e1*r3_ni*r4_ni+r0_e3*r1_no*r2_e1*r3_no*r4_no-r0_e3*r1_no*r2_e2*r3_e1*r4_e2+r0_e3*r1_no*r2_e2*r3_e2*r4_e1-r0_e3*r1_no*r2_e3*r3_e1*r4_e3+r0_e3*r1_no*r2_e3*r3_e3*r4_e1-r0_e3*r1_no*r2_ni*r3_e1*r4_ni+r0_e3*r1_no*r2_ni*r3_ni*r4_e1-r0_e3*r1_no*r2_no*r3_e1*r4_no+r0_e3*r1_no*r2_no*r3_no*r4_e1+r0_ni*r1_e1*r2_e3*r3_ni*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_ni-r0_ni*r1_e1*r2_ni*r3_e3*r4_no+r0_ni*r1_e1*r2_ni*r3_no*r4_e3+r0_ni*r1_e1*r2_no*r3_e3*r4_ni-r0_ni*r1_e1*r2_no*r3_ni*r4_e3-r0_ni*r1_e3*r2_e1*r3_ni*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_ni+r0_ni*r1_e3*r2_ni*r3_e1*r4_no-r0_ni*r1_e3*r2_ni*r3_no*r4_e1-r0_ni*r1_e3*r2_no*r3_e1*r4_ni+r0_ni*r1_e3*r2_no*r3_ni*r4_e1+r0_ni*r1_ni*r2_e1*r3_e3*r4_no-r0_ni*r1_ni*r2_e1*r3_no*r4_e3-r0_ni*r1_ni*r2_e3*r3_e1*r4_no+r0_ni*r1_ni*r2_e3*r3_no*r4_e1+r0_ni*r1_ni*r2_no*r3_e1*r4_e3-r0_ni*r1_ni*r2_no*r3_e3*r4_e1-r0_ni*r1_no*r2_e1*r3_e3*r4_ni+r0_ni*r1_no*r2_e1*r3_ni*r4_e3+r0_ni*r1_no*r2_e3*r3_e1*r4_ni-r0_ni*r1_no*r2_e3*r3_ni*r4_e1-r0_ni*r1_no*r2_ni*r3_e1*r4_e3+r0_ni*r1_no*r2_ni*r3_e3*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_e3-r0_no*r1_e1*r2_e1*r3_e3*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_e3-r0_no*r1_e1*r2_e2*r3_e3*r4_e2+r0_no*r1_e1*r2_e3*r3_e1*r4_e1+r0_no*r1_e1*r2_e3*r3_e2*r4_e2+r0_no*r1_e1*r2_e3*r3_e3*r4_e3+r0_no*r1_e1*r2_e3*r3_ni*r4_ni+r0_no*r1_e1*r2_e3*r3_no*r4_no-r0_no*r1_e1*r2_ni*r3_e3*r4_ni+r0_no*r1_e1*r2_ni*r3_ni*r4_e3-r0_no*r1_e1*r2_no*r3_e3*r4_no+r0_no*r1_e1*r2_no*r3_no*r4_e3-r0_no*r1_e2*r2_e1*r3_e2*r4_e3+r0_no*r1_e2*r2_e1*r3_e3*r4_e2+r0_no*r1_e2*r2_e2*r3_e1*r4_e3-r0_no*r1_e2*r2_e2*r3_e3*r4_e1-r0_no*r1_e2*r2_e3*r3_e1*r4_e2+r0_no*r1_e2*r2_e3*r3_e2*r4_e1-r0_no*r1_e3*r2_e1*r3_e1*r4_e1-r0_no*r1_e3*r2_e1*r3_e2*r4_e2-r0_no*r1_e3*r2_e1*r3_e3*r4_e3-r0_no*r1_e3*r2_e1*r3_ni*r4_ni-r0_no*r1_e3*r2_e1*r3_no*r4_no+r0_no*r1_e3*r2_e2*r3_e1*r4_e2-r0_no*r1_e3*r2_e2*r3_e2*r4_e1+r0_no*r1_e3*r2_e3*r3_e1*r4_e3-r0_no*r1_e3*r2_e3*r3_e3*r4_e1+r0_no*r1_e3*r2_ni*r3_e1*r4_ni-r0_no*r1_e3*r2_ni*r3_ni*r4_e1+r0_no*r1_e3*r2_no*r3_e1*r4_no-r0_no*r1_e3*r2_no*r3_no*r4_e1+r0_no*r1_ni*r2_e1*r3_e3*r4_ni-r0_no*r1_ni*r2_e1*r3_ni*r4_e3-r0_no*r1_ni*r2_e3*r3_e1*r4_ni+r0_no*r1_ni*r2_e3*r3_ni*r4_e1+r0_no*r1_ni*r2_ni*r3_e1*r4_e3-r0_no*r1_ni*r2_ni*r3_e3*r4_e1+r0_no*r1_no*r2_e1*r3_e3*r4_no-r0_no*r1_no*r2_e1*r3_no*r4_e3-r0_no*r1_no*r2_e3*r3_e1*r4_no+r0_no*r1_no*r2_e3*r3_no*r4_e1+r0_no*r1_no*r2_no*r3_e1*r4_e3-r0_no*r1_no*r2_no*r3_e3*r4_e1);
	tmp.m_no_e2_e3 = (r0_e1*r1_e1*r2_e2*r3_e3*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_e3-r0_e1*r1_e1*r2_e3*r3_e2*r4_no+r0_e1*r1_e1*r2_e3*r3_no*r4_e2+r0_e1*r1_e1*r2_no*r3_e2*r4_e3-r0_e1*r1_e1*r2_no*r3_e3*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_e3+r0_e1*r1_e2*r2_e3*r3_e1*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_e3+r0_e1*r1_e2*r2_no*r3_e3*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_no-r0_e1*r1_e3*r2_e1*r3_no*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_e1+r0_e1*r1_e3*r2_no*r3_e1*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_e3+r0_e1*r1_no*r2_e1*r3_e3*r4_e2+r0_e1*r1_no*r2_e2*r3_e1*r4_e3-r0_e1*r1_no*r2_e2*r3_e3*r4_e1-r0_e1*r1_no*r2_e3*r3_e1*r4_e2+r0_e1*r1_no*r2_e3*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e3*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_e3-r0_e2*r1_e1*r2_e3*r3_e1*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_e3-r0_e2*r1_e1*r2_no*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e3*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_e3-r0_e2*r1_e2*r2_e3*r3_e2*r4_no+r0_e2*r1_e2*r2_e3*r3_no*r4_e2+r0_e2*r1_e2*r2_no*r3_e2*r4_e3-r0_e2*r1_e2*r2_no*r3_e3*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_no-r0_e2*r1_e3*r2_e2*r3_no*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_e3+r0_e2*r1_e3*r2_ni*r3_ni*r4_no-r0_e2*r1_e3*r2_ni*r3_no*r4_ni+r0_e2*r1_e3*r2_no*r3_e1*r4_e1+r0_e2*r1_e3*r2_no*r3_e2*r4_e2+r0_e2*r1_e3*r2_no*r3_e3*r4_e3+r0_e2*r1_e3*r2_no*r3_ni*r4_ni+r0_e2*r1_e3*r2_no*r3_no*r4_no-r0_e2*r1_ni*r2_e3*r3_ni*r4_no+r0_e2*r1_ni*r2_e3*r3_no*r4_ni+r0_e2*r1_ni*r2_ni*r3_e3*r4_no-r0_e2*r1_ni*r2_ni*r3_no*r4_e3-r0_e2*r1_ni*r2_no*r3_e3*r4_ni+r0_e2*r1_ni*r2_no*r3_ni*r4_e3-r0_e2*r1_no*r2_e1*r3_e1*r4_e3+r0_e2*r1_no*r2_e1*r3_e3*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_e3+r0_e2*r1_no*r2_e2*r3_e3*r4_e2-r0_e2*r1_no*r2_e3*r3_e1*r4_e1-r0_e2*r1_no*r2_e3*r3_e2*r4_e2-r0_e2*r1_no*r2_e3*r3_e3*r4_e3-r0_e2*r1_no*r2_e3*r3_ni*r4_ni-r0_e2*r1_no*r2_e3*r3_no*r4_no+r0_e2*r1_no*r2_ni*r3_e3*r4_ni-r0_e2*r1_no*r2_ni*r3_ni*r4_e3+r0_e2*r1_no*r2_no*r3_e3*r4_no-r0_e2*r1_no*r2_no*r3_no*r4_e3-r0_e3*r1_e1*r2_e1*r3_e2*r4_no+r0_e3*r1_e1*r2_e1*r3_no*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_e1-r0_e3*r1_e1*r2_no*r3_e1*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_no+r0_e3*r1_e2*r2_e2*r3_no*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_e3-r0_e3*r1_e2*r2_ni*r3_ni*r4_no+r0_e3*r1_e2*r2_ni*r3_no*r4_ni-r0_e3*r1_e2*r2_no*r3_e1*r4_e1-r0_e3*r1_e2*r2_no*r3_e2*r4_e2-r0_e3*r1_e2*r2_no*r3_e3*r4_e3-r0_e3*r1_e2*r2_no*r3_ni*r4_ni-r0_e3*r1_e2*r2_no*r3_no*r4_no+r0_e3*r1_e3*r2_e2*r3_e3*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_e3-r0_e3*r1_e3*r2_e3*r3_e2*r4_no+r0_e3*r1_e3*r2_e3*r3_no*r4_e2+r0_e3*r1_e3*r2_no*r3_e2*r4_e3-r0_e3*r1_e3*r2_no*r3_e3*r4_e2+r0_e3*r1_ni*r2_e2*r3_ni*r4_no-r0_e3*r1_ni*r2_e2*r3_no*r4_ni-r0_e3*r1_ni*r2_ni*r3_e2*r4_no+r0_e3*r1_ni*r2_ni*r3_no*r4_e2+r0_e3*r1_ni*r2_no*r3_e2*r4_ni-r0_e3*r1_ni*r2_no*r3_ni*r4_e2+r0_e3*r1_no*r2_e1*r3_e1*r4_e2-r0_e3*r1_no*r2_e1*r3_e2*r4_e1+r0_e3*r1_no*r2_e2*r3_e1*r4_e1+r0_e3*r1_no*r2_e2*r3_e2*r4_e2+r0_e3*r1_no*r2_e2*r3_e3*r4_e3+r0_e3*r1_no*r2_e2*r3_ni*r4_ni+r0_e3*r1_no*r2_e2*r3_no*r4_no-r0_e3*r1_no*r2_e3*r3_e2*r4_e3+r0_e3*r1_no*r2_e3*r3_e3*r4_e2-r0_e3*r1_no*r2_ni*r3_e2*r4_ni+r0_e3*r1_no*r2_ni*r3_ni*r4_e2-r0_e3*r1_no*r2_no*r3_e2*r4_no+r0_e3*r1_no*r2_no*r3_no*r4_e2+r0_ni*r1_e2*r2_e3*r3_ni*r4_no-r0_ni*r1_e2*r2_e3*r3_no*r4_ni-r0_ni*r1_e2*r2_ni*r3_e3*r4_no+r0_ni*r1_e2*r2_ni*r3_no*r4_e3+r0_ni*r1_e2*r2_no*r3_e3*r4_ni-r0_ni*r1_e2*r2_no*r3_ni*r4_e3-r0_ni*r1_e3*r2_e2*r3_ni*r4_no+r0_ni*r1_e3*r2_e2*r3_no*r4_ni+r0_ni*r1_e3*r2_ni*r3_e2*r4_no-r0_ni*r1_e3*r2_ni*r3_no*r4_e2-r0_ni*r1_e3*r2_no*r3_e2*r4_ni+r0_ni*r1_e3*r2_no*r3_ni*r4_e2+r0_ni*r1_ni*r2_e2*r3_e3*r4_no-r0_ni*r1_ni*r2_e2*r3_no*r4_e3-r0_ni*r1_ni*r2_e3*r3_e2*r4_no+r0_ni*r1_ni*r2_e3*r3_no*r4_e2+r0_ni*r1_ni*r2_no*r3_e2*r4_e3-r0_ni*r1_ni*r2_no*r3_e3*r4_e2-r0_ni*r1_no*r2_e2*r3_e3*r4_ni+r0_ni*r1_no*r2_e2*r3_ni*r4_e3+r0_ni*r1_no*r2_e3*r3_e2*r4_ni-r0_ni*r1_no*r2_e3*r3_ni*r4_e2-r0_ni*r1_no*r2_ni*r3_e2*r4_e3+r0_ni*r1_no*r2_ni*r3_e3*r4_e2+r0_no*r1_e1*r2_e1*r3_e2*r4_e3-r0_no*r1_e1*r2_e1*r3_e3*r4_e2-r0_no*r1_e1*r2_e2*r3_e1*r4_e3+r0_no*r1_e1*r2_e2*r3_e3*r4_e1+r0_no*r1_e1*r2_e3*r3_e1*r4_e2-r0_no*r1_e1*r2_e3*r3_e2*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_e3-r0_no*r1_e2*r2_e1*r3_e3*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_e3-r0_no*r1_e2*r2_e2*r3_e3*r4_e2+r0_no*r1_e2*r2_e3*r3_e1*r4_e1+r0_no*r1_e2*r2_e3*r3_e2*r4_e2+r0_no*r1_e2*r2_e3*r3_e3*r4_e3+r0_no*r1_e2*r2_e3*r3_ni*r4_ni+r0_no*r1_e2*r2_e3*r3_no*r4_no-r0_no*r1_e2*r2_ni*r3_e3*r4_ni+r0_no*r1_e2*r2_ni*r3_ni*r4_e3-r0_no*r1_e2*r2_no*r3_e3*r4_no+r0_no*r1_e2*r2_no*r3_no*r4_e3-r0_no*r1_e3*r2_e1*r3_e1*r4_e2+r0_no*r1_e3*r2_e1*r3_e2*r4_e1-r0_no*r1_e3*r2_e2*r3_e1*r4_e1-r0_no*r1_e3*r2_e2*r3_e2*r4_e2-r0_no*r1_e3*r2_e2*r3_e3*r4_e3-r0_no*r1_e3*r2_e2*r3_ni*r4_ni-r0_no*r1_e3*r2_e2*r3_no*r4_no+r0_no*r1_e3*r2_e3*r3_e2*r4_e3-r0_no*r1_e3*r2_e3*r3_e3*r4_e2+r0_no*r1_e3*r2_ni*r3_e2*r4_ni-r0_no*r1_e3*r2_ni*r3_ni*r4_e2+r0_no*r1_e3*r2_no*r3_e2*r4_no-r0_no*r1_e3*r2_no*r3_no*r4_e2+r0_no*r1_ni*r2_e2*r3_e3*r4_ni-r0_no*r1_ni*r2_e2*r3_ni*r4_e3-r0_no*r1_ni*r2_e3*r3_e2*r4_ni+r0_no*r1_ni*r2_e3*r3_ni*r4_e2+r0_no*r1_ni*r2_ni*r3_e2*r4_e3-r0_no*r1_ni*r2_ni*r3_e3*r4_e2+r0_no*r1_no*r2_e2*r3_e3*r4_no-r0_no*r1_no*r2_e2*r3_no*r4_e3-r0_no*r1_no*r2_e3*r3_e2*r4_no+r0_no*r1_no*r2_e3*r3_no*r4_e2+r0_no*r1_no*r2_no*r3_e2*r4_e3-r0_no*r1_no*r2_no*r3_e3*r4_e2);
	tmp.m_e1_e2_e3 = (r0_e1*r1_e1*r2_e1*r3_e2*r4_e3-r0_e1*r1_e1*r2_e1*r3_e3*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_e3+r0_e1*r1_e1*r2_e2*r3_e3*r4_e1+r0_e1*r1_e1*r2_e3*r3_e1*r4_e2-r0_e1*r1_e1*r2_e3*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_e3-r0_e1*r1_e2*r2_e1*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_e3-r0_e1*r1_e2*r2_e2*r3_e3*r4_e2+r0_e1*r1_e2*r2_e3*r3_e1*r4_e1+r0_e1*r1_e2*r2_e3*r3_e2*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_e3+r0_e1*r1_e2*r2_e3*r3_ni*r4_ni+r0_e1*r1_e2*r2_e3*r3_no*r4_no-r0_e1*r1_e2*r2_ni*r3_e3*r4_ni+r0_e1*r1_e2*r2_ni*r3_ni*r4_e3-r0_e1*r1_e2*r2_no*r3_e3*r4_no+r0_e1*r1_e2*r2_no*r3_no*r4_e3-r0_e1*r1_e3*r2_e1*r3_e1*r4_e2+r0_e1*r1_e3*r2_e1*r3_e2*r4_e1-r0_e1*r1_e3*r2_e2*r3_e1*r4_e1-r0_e1*r1_e3*r2_e2*r3_e2*r4_e2-r0_e1*r1_e3*r2_e2*r3_e3*r4_e3-r0_e1*r1_e3*r2_e2*r3_ni*r4_ni-r0_e1*r1_e3*r2_e2*r3_no*r4_no+r0_e1*r1_e3*r2_e3*r3_e2*r4_e3-r0_e1*r1_e3*r2_e3*r3_e3*r4_e2+r0_e1*r1_e3*r2_ni*r3_e2*r4_ni-r0_e1*r1_e3*r2_ni*r3_ni*r4_e2+r0_e1*r1_e3*r2_no*r3_e2*r4_no-r0_e1*r1_e3*r2_no*r3_no*r4_e2+r0_e1*r1_ni*r2_e2*r3_e3*r4_ni-r0_e1*r1_ni*r2_e2*r3_ni*r4_e3-r0_e1*r1_ni*r2_e3*r3_e2*r4_ni+r0_e1*r1_ni*r2_e3*r3_ni*r4_e2+r0_e1*r1_ni*r2_ni*r3_e2*r4_e3-r0_e1*r1_ni*r2_ni*r3_e3*r4_e2+r0_e1*r1_no*r2_e2*r3_e3*r4_no-r0_e1*r1_no*r2_e2*r3_no*r4_e3-r0_e1*r1_no*r2_e3*r3_e2*r4_no+r0_e1*r1_no*r2_e3*r3_no*r4_e2+r0_e1*r1_no*r2_no*r3_e2*r4_e3-r0_e1*r1_no*r2_no*r3_e3*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_e3+r0_e2*r1_e1*r2_e1*r3_e3*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_e3+r0_e2*r1_e1*r2_e2*r3_e3*r4_e2-r0_e2*r1_e1*r2_e3*r3_e1*r4_e1-r0_e2*r1_e1*r2_e3*r3_e2*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_e3-r0_e2*r1_e1*r2_e3*r3_ni*r4_ni-r0_e2*r1_e1*r2_e3*r3_no*r4_no+r0_e2*r1_e1*r2_ni*r3_e3*r4_ni-r0_e2*r1_e1*r2_ni*r3_ni*r4_e3+r0_e2*r1_e1*r2_no*r3_e3*r4_no-r0_e2*r1_e1*r2_no*r3_no*r4_e3+r0_e2*r1_e2*r2_e1*r3_e2*r4_e3-r0_e2*r1_e2*r2_e1*r3_e3*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_e3+r0_e2*r1_e2*r2_e2*r3_e3*r4_e1+r0_e2*r1_e2*r2_e3*r3_e1*r4_e2-r0_e2*r1_e2*r2_e3*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e1*r4_e1+r0_e2*r1_e3*r2_e1*r3_e2*r4_e2+r0_e2*r1_e3*r2_e1*r3_e3*r4_e3+r0_e2*r1_e3*r2_e1*r3_ni*r4_ni+r0_e2*r1_e3*r2_e1*r3_no*r4_no-r0_e2*r1_e3*r2_e2*r3_e1*r4_e2+r0_e2*r1_e3*r2_e2*r3_e2*r4_e1-r0_e2*r1_e3*r2_e3*r3_e1*r4_e3+r0_e2*r1_e3*r2_e3*r3_e3*r4_e1-r0_e2*r1_e3*r2_ni*r3_e1*r4_ni+r0_e2*r1_e3*r2_ni*r3_ni*r4_e1-r0_e2*r1_e3*r2_no*r3_e1*r4_no+r0_e2*r1_e3*r2_no*r3_no*r4_e1-r0_e2*r1_ni*r2_e1*r3_e3*r4_ni+r0_e2*r1_ni*r2_e1*r3_ni*r4_e3+r0_e2*r1_ni*r2_e3*r3_e1*r4_ni-r0_e2*r1_ni*r2_e3*r3_ni*r4_e1-r0_e2*r1_ni*r2_ni*r3_e1*r4_e3+r0_e2*r1_ni*r2_ni*r3_e3*r4_e1-r0_e2*r1_no*r2_e1*r3_e3*r4_no+r0_e2*r1_no*r2_e1*r3_no*r4_e3+r0_e2*r1_no*r2_e3*r3_e1*r4_no-r0_e2*r1_no*r2_e3*r3_no*r4_e1-r0_e2*r1_no*r2_no*r3_e1*r4_e3+r0_e2*r1_no*r2_no*r3_e3*r4_e1+r0_e3*r1_e1*r2_e1*r3_e1*r4_e2-r0_e3*r1_e1*r2_e1*r3_e2*r4_e1+r0_e3*r1_e1*r2_e2*r3_e1*r4_e1+r0_e3*r1_e1*r2_e2*r3_e2*r4_e2+r0_e3*r1_e1*r2_e2*r3_e3*r4_e3+r0_e3*r1_e1*r2_e2*r3_ni*r4_ni+r0_e3*r1_e1*r2_e2*r3_no*r4_no-r0_e3*r1_e1*r2_e3*r3_e2*r4_e3+r0_e3*r1_e1*r2_e3*r3_e3*r4_e2-r0_e3*r1_e1*r2_ni*r3_e2*r4_ni+r0_e3*r1_e1*r2_ni*r3_ni*r4_e2-r0_e3*r1_e1*r2_no*r3_e2*r4_no+r0_e3*r1_e1*r2_no*r3_no*r4_e2-r0_e3*r1_e2*r2_e1*r3_e1*r4_e1-r0_e3*r1_e2*r2_e1*r3_e2*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_e3-r0_e3*r1_e2*r2_e1*r3_ni*r4_ni-r0_e3*r1_e2*r2_e1*r3_no*r4_no+r0_e3*r1_e2*r2_e2*r3_e1*r4_e2-r0_e3*r1_e2*r2_e2*r3_e2*r4_e1+r0_e3*r1_e2*r2_e3*r3_e1*r4_e3-r0_e3*r1_e2*r2_e3*r3_e3*r4_e1+r0_e3*r1_e2*r2_ni*r3_e1*r4_ni-r0_e3*r1_e2*r2_ni*r3_ni*r4_e1+r0_e3*r1_e2*r2_no*r3_e1*r4_no-r0_e3*r1_e2*r2_no*r3_no*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_e3-r0_e3*r1_e3*r2_e1*r3_e3*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_e3+r0_e3*r1_e3*r2_e2*r3_e3*r4_e1+r0_e3*r1_e3*r2_e3*r3_e1*r4_e2-r0_e3*r1_e3*r2_e3*r3_e2*r4_e1+r0_e3*r1_ni*r2_e1*r3_e2*r4_ni-r0_e3*r1_ni*r2_e1*r3_ni*r4_e2-r0_e3*r1_ni*r2_e2*r3_e1*r4_ni+r0_e3*r1_ni*r2_e2*r3_ni*r4_e1+r0_e3*r1_ni*r2_ni*r3_e1*r4_e2-r0_e3*r1_ni*r2_ni*r3_e2*r4_e1+r0_e3*r1_no*r2_e1*r3_e2*r4_no-r0_e3*r1_no*r2_e1*r3_no*r4_e2-r0_e3*r1_no*r2_e2*r3_e1*r4_no+r0_e3*r1_no*r2_e2*r3_no*r4_e1+r0_e3*r1_no*r2_no*r3_e1*r4_e2-r0_e3*r1_no*r2_no*r3_e2*r4_e1-r0_ni*r1_e1*r2_e2*r3_e3*r4_ni+r0_ni*r1_e1*r2_e2*r3_ni*r4_e3+r0_ni*r1_e1*r2_e3*r3_e2*r4_ni-r0_ni*r1_e1*r2_e3*r3_ni*r4_e2-r0_ni*r1_e1*r2_ni*r3_e2*r4_e3+r0_ni*r1_e1*r2_ni*r3_e3*r4_e2+r0_ni*r1_e2*r2_e1*r3_e3*r4_ni-r0_ni*r1_e2*r2_e1*r3_ni*r4_e3-r0_ni*r1_e2*r2_e3*r3_e1*r4_ni+r0_ni*r1_e2*r2_e3*r3_ni*r4_e1+r0_ni*r1_e2*r2_ni*r3_e1*r4_e3-r0_ni*r1_e2*r2_ni*r3_e3*r4_e1-r0_ni*r1_e3*r2_e1*r3_e2*r4_ni+r0_ni*r1_e3*r2_e1*r3_ni*r4_e2+r0_ni*r1_e3*r2_e2*r3_e1*r4_ni-r0_ni*r1_e3*r2_e2*r3_ni*r4_e1-r0_ni*r1_e3*r2_ni*r3_e1*r4_e2+r0_ni*r1_e3*r2_ni*r3_e2*r4_e1+r0_ni*r1_ni*r2_e1*r3_e2*r4_e3-r0_ni*r1_ni*r2_e1*r3_e3*r4_e2-r0_ni*r1_ni*r2_e2*r3_e1*r4_e3+r0_ni*r1_ni*r2_e2*r3_e3*r4_e1+r0_ni*r1_ni*r2_e3*r3_e1*r4_e2-r0_ni*r1_ni*r2_e3*r3_e2*r4_e1-r0_no*r1_e1*r2_e2*r3_e3*r4_no+r0_no*r1_e1*r2_e2*r3_no*r4_e3+r0_no*r1_e1*r2_e3*r3_e2*r4_no-r0_no*r1_e1*r2_e3*r3_no*r4_e2-r0_no*r1_e1*r2_no*r3_e2*r4_e3+r0_no*r1_e1*r2_no*r3_e3*r4_e2+r0_no*r1_e2*r2_e1*r3_e3*r4_no-r0_no*r1_e2*r2_e1*r3_no*r4_e3-r0_no*r1_e2*r2_e3*r3_e1*r4_no+r0_no*r1_e2*r2_e3*r3_no*r4_e1+r0_no*r1_e2*r2_no*r3_e1*r4_e3-r0_no*r1_e2*r2_no*r3_e3*r4_e1-r0_no*r1_e3*r2_e1*r3_e2*r4_no+r0_no*r1_e3*r2_e1*r3_no*r4_e2+r0_no*r1_e3*r2_e2*r3_e1*r4_no-r0_no*r1_e3*r2_e2*r3_no*r4_e1-r0_no*r1_e3*r2_no*r3_e1*r4_e2+r0_no*r1_e3*r2_no*r3_e2*r4_e1+r0_no*r1_no*r2_e1*r3_e2*r4_e3-r0_no*r1_no*r2_e1*r3_e3*r4_e2-r0_no*r1_no*r2_e2*r3_e1*r4_e3+r0_no*r1_no*r2_e2*r3_e3*r4_e1+r0_no*r1_no*r2_e3*r3_e1*r4_e2-r0_no*r1_no*r2_e3*r3_e2*r4_e1);
	tmp.m_no_e1_ni = (r0_e1*r1_e1*r2_e1*r3_ni*r4_no-r0_e1*r1_e1*r2_e1*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e1*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e1+r0_e1*r1_e1*r2_no*r3_e1*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e1+r0_e1*r1_e2*r2_e2*r3_ni*r4_no-r0_e1*r1_e2*r2_e2*r3_no*r4_ni-r0_e1*r1_e2*r2_ni*r3_e2*r4_no+r0_e1*r1_e2*r2_ni*r3_no*r4_e2+r0_e1*r1_e2*r2_no*r3_e2*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e2+r0_e1*r1_e3*r2_e3*r3_ni*r4_no-r0_e1*r1_e3*r2_e3*r3_no*r4_ni-r0_e1*r1_e3*r2_ni*r3_e3*r4_no+r0_e1*r1_e3*r2_ni*r3_no*r4_e3+r0_e1*r1_e3*r2_no*r3_e3*r4_ni-r0_e1*r1_e3*r2_no*r3_ni*r4_e3+r0_e1*r1_ni*r2_e1*r3_e1*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e1+r0_e1*r1_ni*r2_e2*r3_e2*r4_no-r0_e1*r1_ni*r2_e2*r3_no*r4_e2+r0_e1*r1_ni*r2_e3*r3_e3*r4_no-r0_e1*r1_ni*r2_e3*r3_no*r4_e3+r0_e1*r1_ni*r2_ni*r3_ni*r4_no-r0_e1*r1_ni*r2_ni*r3_no*r4_ni+r0_e1*r1_ni*r2_no*r3_e1*r4_e1+r0_e1*r1_ni*r2_no*r3_e2*r4_e2+r0_e1*r1_ni*r2_no*r3_e3*r4_e3+r0_e1*r1_ni*r2_no*r3_ni*r4_ni+r0_e1*r1_ni*r2_no*r3_no*r4_no-r0_e1*r1_no*r2_e1*r3_e1*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e1-r0_e1*r1_no*r2_e2*r3_e2*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e2-r0_e1*r1_no*r2_e3*r3_e3*r4_ni+r0_e1*r1_no*r2_e3*r3_ni*r4_e3-r0_e1*r1_no*r2_ni*r3_e1*r4_e1-r0_e1*r1_no*r2_ni*r3_e2*r4_e2-r0_e1*r1_no*r2_ni*r3_e3*r4_e3-r0_e1*r1_no*r2_ni*r3_ni*r4_ni-r0_e1*r1_no*r2_ni*r3_no*r4_no+r0_e1*r1_no*r2_no*r3_ni*r4_no-r0_e1*r1_no*r2_no*r3_no*r4_ni-r0_e2*r1_e1*r2_e2*r3_ni*r4_no+r0_e2*r1_e1*r2_e2*r3_no*r4_ni+r0_e2*r1_e1*r2_ni*r3_e2*r4_no-r0_e2*r1_e1*r2_ni*r3_no*r4_e2-r0_e2*r1_e1*r2_no*r3_e2*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e2+r0_e2*r1_e2*r2_e1*r3_ni*r4_no-r0_e2*r1_e2*r2_e1*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e1*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e1+r0_e2*r1_e2*r2_no*r3_e1*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e1-r0_e2*r1_ni*r2_e1*r3_e2*r4_no+r0_e2*r1_ni*r2_e1*r3_no*r4_e2+r0_e2*r1_ni*r2_e2*r3_e1*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e1-r0_e2*r1_ni*r2_no*r3_e1*r4_e2+r0_e2*r1_ni*r2_no*r3_e2*r4_e1+r0_e2*r1_no*r2_e1*r3_e2*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e2-r0_e2*r1_no*r2_e2*r3_e1*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e1-r0_e3*r1_e1*r2_e3*r3_ni*r4_no+r0_e3*r1_e1*r2_e3*r3_no*r4_ni+r0_e3*r1_e1*r2_ni*r3_e3*r4_no-r0_e3*r1_e1*r2_ni*r3_no*r4_e3-r0_e3*r1_e1*r2_no*r3_e3*r4_ni+r0_e3*r1_e1*r2_no*r3_ni*r4_e3+r0_e3*r1_e3*r2_e1*r3_ni*r4_no-r0_e3*r1_e3*r2_e1*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e1*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e1+r0_e3*r1_e3*r2_no*r3_e1*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e1-r0_e3*r1_ni*r2_e1*r3_e3*r4_no+r0_e3*r1_ni*r2_e1*r3_no*r4_e3+r0_e3*r1_ni*r2_e3*r3_e1*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e1-r0_e3*r1_ni*r2_no*r3_e1*r4_e3+r0_e3*r1_ni*r2_no*r3_e3*r4_e1+r0_e3*r1_no*r2_e1*r3_e3*r4_ni-r0_e3*r1_no*r2_e1*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e1*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e1+r0_e3*r1_no*r2_ni*r3_e1*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e1-r0_ni*r1_e1*r2_e1*r3_e1*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e1-r0_ni*r1_e1*r2_e2*r3_e2*r4_no+r0_ni*r1_e1*r2_e2*r3_no*r4_e2-r0_ni*r1_e1*r2_e3*r3_e3*r4_no+r0_ni*r1_e1*r2_e3*r3_no*r4_e3-r0_ni*r1_e1*r2_ni*r3_ni*r4_no+r0_ni*r1_e1*r2_ni*r3_no*r4_ni-r0_ni*r1_e1*r2_no*r3_e1*r4_e1-r0_ni*r1_e1*r2_no*r3_e2*r4_e2-r0_ni*r1_e1*r2_no*r3_e3*r4_e3-r0_ni*r1_e1*r2_no*r3_ni*r4_ni-r0_ni*r1_e1*r2_no*r3_no*r4_no+r0_ni*r1_e2*r2_e1*r3_e2*r4_no-r0_ni*r1_e2*r2_e1*r3_no*r4_e2-r0_ni*r1_e2*r2_e2*r3_e1*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e1+r0_ni*r1_e2*r2_no*r3_e1*r4_e2-r0_ni*r1_e2*r2_no*r3_e2*r4_e1+r0_ni*r1_e3*r2_e1*r3_e3*r4_no-r0_ni*r1_e3*r2_e1*r3_no*r4_e3-r0_ni*r1_e3*r2_e3*r3_e1*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e1+r0_ni*r1_e3*r2_no*r3_e1*r4_e3-r0_ni*r1_e3*r2_no*r3_e3*r4_e1+r0_ni*r1_ni*r2_e1*r3_ni*r4_no-r0_ni*r1_ni*r2_e1*r3_no*r4_ni-r0_ni*r1_ni*r2_ni*r3_e1*r4_no+r0_ni*r1_ni*r2_ni*r3_no*r4_e1+r0_ni*r1_ni*r2_no*r3_e1*r4_ni-r0_ni*r1_ni*r2_no*r3_ni*r4_e1+r0_ni*r1_no*r2_e1*r3_e1*r4_e1+r0_ni*r1_no*r2_e1*r3_e2*r4_e2+r0_ni*r1_no*r2_e1*r3_e3*r4_e3+r0_ni*r1_no*r2_e1*r3_ni*r4_ni+r0_ni*r1_no*r2_e1*r3_no*r4_no-r0_ni*r1_no*r2_e2*r3_e1*r4_e2+r0_ni*r1_no*r2_e2*r3_e2*r4_e1-r0_ni*r1_no*r2_e3*r3_e1*r4_e3+r0_ni*r1_no*r2_e3*r3_e3*r4_e1-r0_ni*r1_no*r2_ni*r3_e1*r4_ni+r0_ni*r1_no*r2_ni*r3_ni*r4_e1-r0_ni*r1_no*r2_no*r3_e1*r4_no+r0_ni*r1_no*r2_no*r3_no*r4_e1+r0_no*r1_e1*r2_e1*r3_e1*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e1+r0_no*r1_e1*r2_e2*r3_e2*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e2+r0_no*r1_e1*r2_e3*r3_e3*r4_ni-r0_no*r1_e1*r2_e3*r3_ni*r4_e3+r0_no*r1_e1*r2_ni*r3_e1*r4_e1+r0_no*r1_e1*r2_ni*r3_e2*r4_e2+r0_no*r1_e1*r2_ni*r3_e3*r4_e3+r0_no*r1_e1*r2_ni*r3_ni*r4_ni+r0_no*r1_e1*r2_ni*r3_no*r4_no-r0_no*r1_e1*r2_no*r3_ni*r4_no+r0_no*r1_e1*r2_no*r3_no*r4_ni-r0_no*r1_e2*r2_e1*r3_e2*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e2+r0_no*r1_e2*r2_e2*r3_e1*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e1-r0_no*r1_e3*r2_e1*r3_e3*r4_ni+r0_no*r1_e3*r2_e1*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e1*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e1-r0_no*r1_e3*r2_ni*r3_e1*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e1-r0_no*r1_ni*r2_e1*r3_e1*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e2-r0_no*r1_ni*r2_e1*r3_e3*r4_e3-r0_no*r1_ni*r2_e1*r3_ni*r4_ni-r0_no*r1_ni*r2_e1*r3_no*r4_no+r0_no*r1_ni*r2_e2*r3_e1*r4_e2-r0_no*r1_ni*r2_e2*r3_e2*r4_e1+r0_no*r1_ni*r2_e3*r3_e1*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e1+r0_no*r1_ni*r2_ni*r3_e1*r4_ni-r0_no*r1_ni*r2_ni*r3_ni*r4_e1+r0_no*r1_ni*r2_no*r3_e1*r4_no-r0_no*r1_ni*r2_no*r3_no*r4_e1+r0_no*r1_no*r2_e1*r3_ni*r4_no-r0_no*r1_no*r2_e1*r3_no*r4_ni-r0_no*r1_no*r2_ni*r3_e1*r4_no+r0_no*r1_no*r2_ni*r3_no*r4_e1+r0_no*r1_no*r2_no*r3_e1*r4_ni-r0_no*r1_no*r2_no*r3_ni*r4_e1);
	tmp.m_no_e2_ni = (r0_e1*r1_e1*r2_e2*r3_ni*r4_no-r0_e1*r1_e1*r2_e2*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e2*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e2+r0_e1*r1_e1*r2_no*r3_e2*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e2-r0_e1*r1_e2*r2_e1*r3_ni*r4_no+r0_e1*r1_e2*r2_e1*r3_no*r4_ni+r0_e1*r1_e2*r2_ni*r3_e1*r4_no-r0_e1*r1_e2*r2_ni*r3_no*r4_e1-r0_e1*r1_e2*r2_no*r3_e1*r4_ni+r0_e1*r1_e2*r2_no*r3_ni*r4_e1+r0_e1*r1_ni*r2_e1*r3_e2*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e2-r0_e1*r1_ni*r2_e2*r3_e1*r4_no+r0_e1*r1_ni*r2_e2*r3_no*r4_e1+r0_e1*r1_ni*r2_no*r3_e1*r4_e2-r0_e1*r1_ni*r2_no*r3_e2*r4_e1-r0_e1*r1_no*r2_e1*r3_e2*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e2+r0_e1*r1_no*r2_e2*r3_e1*r4_ni-r0_e1*r1_no*r2_e2*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e2+r0_e1*r1_no*r2_ni*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_ni*r4_no-r0_e2*r1_e1*r2_e1*r3_no*r4_ni-r0_e2*r1_e1*r2_ni*r3_e1*r4_no+r0_e2*r1_e1*r2_ni*r3_no*r4_e1+r0_e2*r1_e1*r2_no*r3_e1*r4_ni-r0_e2*r1_e1*r2_no*r3_ni*r4_e1+r0_e2*r1_e2*r2_e2*r3_ni*r4_no-r0_e2*r1_e2*r2_e2*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e2*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e2+r0_e2*r1_e2*r2_no*r3_e2*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e2+r0_e2*r1_e3*r2_e3*r3_ni*r4_no-r0_e2*r1_e3*r2_e3*r3_no*r4_ni-r0_e2*r1_e3*r2_ni*r3_e3*r4_no+r0_e2*r1_e3*r2_ni*r3_no*r4_e3+r0_e2*r1_e3*r2_no*r3_e3*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e3+r0_e2*r1_ni*r2_e1*r3_e1*r4_no-r0_e2*r1_ni*r2_e1*r3_no*r4_e1+r0_e2*r1_ni*r2_e2*r3_e2*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e2+r0_e2*r1_ni*r2_e3*r3_e3*r4_no-r0_e2*r1_ni*r2_e3*r3_no*r4_e3+r0_e2*r1_ni*r2_ni*r3_ni*r4_no-r0_e2*r1_ni*r2_ni*r3_no*r4_ni+r0_e2*r1_ni*r2_no*r3_e1*r4_e1+r0_e2*r1_ni*r2_no*r3_e2*r4_e2+r0_e2*r1_ni*r2_no*r3_e3*r4_e3+r0_e2*r1_ni*r2_no*r3_ni*r4_ni+r0_e2*r1_ni*r2_no*r3_no*r4_no-r0_e2*r1_no*r2_e1*r3_e1*r4_ni+r0_e2*r1_no*r2_e1*r3_ni*r4_e1-r0_e2*r1_no*r2_e2*r3_e2*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e2-r0_e2*r1_no*r2_e3*r3_e3*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e3-r0_e2*r1_no*r2_ni*r3_e1*r4_e1-r0_e2*r1_no*r2_ni*r3_e2*r4_e2-r0_e2*r1_no*r2_ni*r3_e3*r4_e3-r0_e2*r1_no*r2_ni*r3_ni*r4_ni-r0_e2*r1_no*r2_ni*r3_no*r4_no+r0_e2*r1_no*r2_no*r3_ni*r4_no-r0_e2*r1_no*r2_no*r3_no*r4_ni-r0_e3*r1_e2*r2_e3*r3_ni*r4_no+r0_e3*r1_e2*r2_e3*r3_no*r4_ni+r0_e3*r1_e2*r2_ni*r3_e3*r4_no-r0_e3*r1_e2*r2_ni*r3_no*r4_e3-r0_e3*r1_e2*r2_no*r3_e3*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e3+r0_e3*r1_e3*r2_e2*r3_ni*r4_no-r0_e3*r1_e3*r2_e2*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e2*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e2+r0_e3*r1_e3*r2_no*r3_e2*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e2-r0_e3*r1_ni*r2_e2*r3_e3*r4_no+r0_e3*r1_ni*r2_e2*r3_no*r4_e3+r0_e3*r1_ni*r2_e3*r3_e2*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e2-r0_e3*r1_ni*r2_no*r3_e2*r4_e3+r0_e3*r1_ni*r2_no*r3_e3*r4_e2+r0_e3*r1_no*r2_e2*r3_e3*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e3-r0_e3*r1_no*r2_e3*r3_e2*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e3-r0_e3*r1_no*r2_ni*r3_e3*r4_e2-r0_ni*r1_e1*r2_e1*r3_e2*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e2+r0_ni*r1_e1*r2_e2*r3_e1*r4_no-r0_ni*r1_e1*r2_e2*r3_no*r4_e1-r0_ni*r1_e1*r2_no*r3_e1*r4_e2+r0_ni*r1_e1*r2_no*r3_e2*r4_e1-r0_ni*r1_e2*r2_e1*r3_e1*r4_no+r0_ni*r1_e2*r2_e1*r3_no*r4_e1-r0_ni*r1_e2*r2_e2*r3_e2*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e2-r0_ni*r1_e2*r2_e3*r3_e3*r4_no+r0_ni*r1_e2*r2_e3*r3_no*r4_e3-r0_ni*r1_e2*r2_ni*r3_ni*r4_no+r0_ni*r1_e2*r2_ni*r3_no*r4_ni-r0_ni*r1_e2*r2_no*r3_e1*r4_e1-r0_ni*r1_e2*r2_no*r3_e2*r4_e2-r0_ni*r1_e2*r2_no*r3_e3*r4_e3-r0_ni*r1_e2*r2_no*r3_ni*r4_ni-r0_ni*r1_e2*r2_no*r3_no*r4_no+r0_ni*r1_e3*r2_e2*r3_e3*r4_no-r0_ni*r1_e3*r2_e2*r3_no*r4_e3-r0_ni*r1_e3*r2_e3*r3_e2*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e2+r0_ni*r1_e3*r2_no*r3_e2*r4_e3-r0_ni*r1_e3*r2_no*r3_e3*r4_e2+r0_ni*r1_ni*r2_e2*r3_ni*r4_no-r0_ni*r1_ni*r2_e2*r3_no*r4_ni-r0_ni*r1_ni*r2_ni*r3_e2*r4_no+r0_ni*r1_ni*r2_ni*r3_no*r4_e2+r0_ni*r1_ni*r2_no*r3_e2*r4_ni-r0_ni*r1_ni*r2_no*r3_ni*r4_e2+r0_ni*r1_no*r2_e1*r3_e1*r4_e2-r0_ni*r1_no*r2_e1*r3_e2*r4_e1+r0_ni*r1_no*r2_e2*r3_e1*r4_e1+r0_ni*r1_no*r2_e2*r3_e2*r4_e2+r0_ni*r1_no*r2_e2*r3_e3*r4_e3+r0_ni*r1_no*r2_e2*r3_ni*r4_ni+r0_ni*r1_no*r2_e2*r3_no*r4_no-r0_ni*r1_no*r2_e3*r3_e2*r4_e3+r0_ni*r1_no*r2_e3*r3_e3*r4_e2-r0_ni*r1_no*r2_ni*r3_e2*r4_ni+r0_ni*r1_no*r2_ni*r3_ni*r4_e2-r0_ni*r1_no*r2_no*r3_e2*r4_no+r0_ni*r1_no*r2_no*r3_no*r4_e2+r0_no*r1_e1*r2_e1*r3_e2*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e2-r0_no*r1_e1*r2_e2*r3_e1*r4_ni+r0_no*r1_e1*r2_e2*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e2-r0_no*r1_e1*r2_ni*r3_e2*r4_e1+r0_no*r1_e2*r2_e1*r3_e1*r4_ni-r0_no*r1_e2*r2_e1*r3_ni*r4_e1+r0_no*r1_e2*r2_e2*r3_e2*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e2+r0_no*r1_e2*r2_e3*r3_e3*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e3+r0_no*r1_e2*r2_ni*r3_e1*r4_e1+r0_no*r1_e2*r2_ni*r3_e2*r4_e2+r0_no*r1_e2*r2_ni*r3_e3*r4_e3+r0_no*r1_e2*r2_ni*r3_ni*r4_ni+r0_no*r1_e2*r2_ni*r3_no*r4_no-r0_no*r1_e2*r2_no*r3_ni*r4_no+r0_no*r1_e2*r2_no*r3_no*r4_ni-r0_no*r1_e3*r2_e2*r3_e3*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e3+r0_no*r1_e3*r2_e3*r3_e2*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e3+r0_no*r1_e3*r2_ni*r3_e3*r4_e2-r0_no*r1_ni*r2_e1*r3_e1*r4_e2+r0_no*r1_ni*r2_e1*r3_e2*r4_e1-r0_no*r1_ni*r2_e2*r3_e1*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e2-r0_no*r1_ni*r2_e2*r3_e3*r4_e3-r0_no*r1_ni*r2_e2*r3_ni*r4_ni-r0_no*r1_ni*r2_e2*r3_no*r4_no+r0_no*r1_ni*r2_e3*r3_e2*r4_e3-r0_no*r1_ni*r2_e3*r3_e3*r4_e2+r0_no*r1_ni*r2_ni*r3_e2*r4_ni-r0_no*r1_ni*r2_ni*r3_ni*r4_e2+r0_no*r1_ni*r2_no*r3_e2*r4_no-r0_no*r1_ni*r2_no*r3_no*r4_e2+r0_no*r1_no*r2_e2*r3_ni*r4_no-r0_no*r1_no*r2_e2*r3_no*r4_ni-r0_no*r1_no*r2_ni*r3_e2*r4_no+r0_no*r1_no*r2_ni*r3_no*r4_e2+r0_no*r1_no*r2_no*r3_e2*r4_ni-r0_no*r1_no*r2_no*r3_ni*r4_e2);
	tmp.m_e1_e2_ni = (r0_e1*r1_e1*r2_e1*r3_e2*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e2-r0_e1*r1_e1*r2_e2*r3_e1*r4_ni+r0_e1*r1_e1*r2_e2*r3_ni*r4_e1+r0_e1*r1_e1*r2_ni*r3_e1*r4_e2-r0_e1*r1_e1*r2_ni*r3_e2*r4_e1+r0_e1*r1_e2*r2_e1*r3_e1*r4_ni-r0_e1*r1_e2*r2_e1*r3_ni*r4_e1+r0_e1*r1_e2*r2_e2*r3_e2*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e2+r0_e1*r1_e2*r2_e3*r3_e3*r4_ni-r0_e1*r1_e2*r2_e3*r3_ni*r4_e3+r0_e1*r1_e2*r2_ni*r3_e1*r4_e1+r0_e1*r1_e2*r2_ni*r3_e2*r4_e2+r0_e1*r1_e2*r2_ni*r3_e3*r4_e3+r0_e1*r1_e2*r2_ni*r3_ni*r4_ni+r0_e1*r1_e2*r2_ni*r3_no*r4_no-r0_e1*r1_e2*r2_no*r3_ni*r4_no+r0_e1*r1_e2*r2_no*r3_no*r4_ni-r0_e1*r1_e3*r2_e2*r3_e3*r4_ni+r0_e1*r1_e3*r2_e2*r3_ni*r4_e3+r0_e1*r1_e3*r2_e3*r3_e2*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e2-r0_e1*r1_e3*r2_ni*r3_e2*r4_e3+r0_e1*r1_e3*r2_ni*r3_e3*r4_e2-r0_e1*r1_ni*r2_e1*r3_e1*r4_e2+r0_e1*r1_ni*r2_e1*r3_e2*r4_e1-r0_e1*r1_ni*r2_e2*r3_e1*r4_e1-r0_e1*r1_ni*r2_e2*r3_e2*r4_e2-r0_e1*r1_ni*r2_e2*r3_e3*r4_e3-r0_e1*r1_ni*r2_e2*r3_ni*r4_ni-r0_e1*r1_ni*r2_e2*r3_no*r4_no+r0_e1*r1_ni*r2_e3*r3_e2*r4_e3-r0_e1*r1_ni*r2_e3*r3_e3*r4_e2+r0_e1*r1_ni*r2_ni*r3_e2*r4_ni-r0_e1*r1_ni*r2_ni*r3_ni*r4_e2+r0_e1*r1_ni*r2_no*r3_e2*r4_no-r0_e1*r1_ni*r2_no*r3_no*r4_e2+r0_e1*r1_no*r2_e2*r3_ni*r4_no-r0_e1*r1_no*r2_e2*r3_no*r4_ni-r0_e1*r1_no*r2_ni*r3_e2*r4_no+r0_e1*r1_no*r2_ni*r3_no*r4_e2+r0_e1*r1_no*r2_no*r3_e2*r4_ni-r0_e1*r1_no*r2_no*r3_ni*r4_e2-r0_e2*r1_e1*r2_e1*r3_e1*r4_ni+r0_e2*r1_e1*r2_e1*r3_ni*r4_e1-r0_e2*r1_e1*r2_e2*r3_e2*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e2-r0_e2*r1_e1*r2_e3*r3_e3*r4_ni+r0_e2*r1_e1*r2_e3*r3_ni*r4_e3-r0_e2*r1_e1*r2_ni*r3_e1*r4_e1-r0_e2*r1_e1*r2_ni*r3_e2*r4_e2-r0_e2*r1_e1*r2_ni*r3_e3*r4_e3-r0_e2*r1_e1*r2_ni*r3_ni*r4_ni-r0_e2*r1_e1*r2_ni*r3_no*r4_no+r0_e2*r1_e1*r2_no*r3_ni*r4_no-r0_e2*r1_e1*r2_no*r3_no*r4_ni+r0_e2*r1_e2*r2_e1*r3_e2*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e2-r0_e2*r1_e2*r2_e2*r3_e1*r4_ni+r0_e2*r1_e2*r2_e2*r3_ni*r4_e1+r0_e2*r1_e2*r2_ni*r3_e1*r4_e2-r0_e2*r1_e2*r2_ni*r3_e2*r4_e1+r0_e2*r1_e3*r2_e1*r3_e3*r4_ni-r0_e2*r1_e3*r2_e1*r3_ni*r4_e3-r0_e2*r1_e3*r2_e3*r3_e1*r4_ni+r0_e2*r1_e3*r2_e3*r3_ni*r4_e1+r0_e2*r1_e3*r2_ni*r3_e1*r4_e3-r0_e2*r1_e3*r2_ni*r3_e3*r4_e1+r0_e2*r1_ni*r2_e1*r3_e1*r4_e1+r0_e2*r1_ni*r2_e1*r3_e2*r4_e2+r0_e2*r1_ni*r2_e1*r3_e3*r4_e3+r0_e2*r1_ni*r2_e1*r3_ni*r4_ni+r0_e2*r1_ni*r2_e1*r3_no*r4_no-r0_e2*r1_ni*r2_e2*r3_e1*r4_e2+r0_e2*r1_ni*r2_e2*r3_e2*r4_e1-r0_e2*r1_ni*r2_e3*r3_e1*r4_e3+r0_e2*r1_ni*r2_e3*r3_e3*r4_e1-r0_e2*r1_ni*r2_ni*r3_e1*r4_ni+r0_e2*r1_ni*r2_ni*r3_ni*r4_e1-r0_e2*r1_ni*r2_no*r3_e1*r4_no+r0_e2*r1_ni*r2_no*r3_no*r4_e1-r0_e2*r1_no*r2_e1*r3_ni*r4_no+r0_e2*r1_no*r2_e1*r3_no*r4_ni+r0_e2*r1_no*r2_ni*r3_e1*r4_no-r0_e2*r1_no*r2_ni*r3_no*r4_e1-r0_e2*r1_no*r2_no*r3_e1*r4_ni+r0_e2*r1_no*r2_no*r3_ni*r4_e1+r0_e3*r1_e1*r2_e2*r3_e3*r4_ni-r0_e3*r1_e1*r2_e2*r3_ni*r4_e3-r0_e3*r1_e1*r2_e3*r3_e2*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e2+r0_e3*r1_e1*r2_ni*r3_e2*r4_e3-r0_e3*r1_e1*r2_ni*r3_e3*r4_e2-r0_e3*r1_e2*r2_e1*r3_e3*r4_ni+r0_e3*r1_e2*r2_e1*r3_ni*r4_e3+r0_e3*r1_e2*r2_e3*r3_e1*r4_ni-r0_e3*r1_e2*r2_e3*r3_ni*r4_e1-r0_e3*r1_e2*r2_ni*r3_e1*r4_e3+r0_e3*r1_e2*r2_ni*r3_e3*r4_e1+r0_e3*r1_e3*r2_e1*r3_e2*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e2-r0_e3*r1_e3*r2_e2*r3_e1*r4_ni+r0_e3*r1_e3*r2_e2*r3_ni*r4_e1+r0_e3*r1_e3*r2_ni*r3_e1*r4_e2-r0_e3*r1_e3*r2_ni*r3_e2*r4_e1-r0_e3*r1_ni*r2_e1*r3_e2*r4_e3+r0_e3*r1_ni*r2_e1*r3_e3*r4_e2+r0_e3*r1_ni*r2_e2*r3_e1*r4_e3-r0_e3*r1_ni*r2_e2*r3_e3*r4_e1-r0_e3*r1_ni*r2_e3*r3_e1*r4_e2+r0_e3*r1_ni*r2_e3*r3_e2*r4_e1+r0_ni*r1_e1*r2_e1*r3_e1*r4_e2-r0_ni*r1_e1*r2_e1*r3_e2*r4_e1+r0_ni*r1_e1*r2_e2*r3_e1*r4_e1+r0_ni*r1_e1*r2_e2*r3_e2*r4_e2+r0_ni*r1_e1*r2_e2*r3_e3*r4_e3+r0_ni*r1_e1*r2_e2*r3_ni*r4_ni+r0_ni*r1_e1*r2_e2*r3_no*r4_no-r0_ni*r1_e1*r2_e3*r3_e2*r4_e3+r0_ni*r1_e1*r2_e3*r3_e3*r4_e2-r0_ni*r1_e1*r2_ni*r3_e2*r4_ni+r0_ni*r1_e1*r2_ni*r3_ni*r4_e2-r0_ni*r1_e1*r2_no*r3_e2*r4_no+r0_ni*r1_e1*r2_no*r3_no*r4_e2-r0_ni*r1_e2*r2_e1*r3_e1*r4_e1-r0_ni*r1_e2*r2_e1*r3_e2*r4_e2-r0_ni*r1_e2*r2_e1*r3_e3*r4_e3-r0_ni*r1_e2*r2_e1*r3_ni*r4_ni-r0_ni*r1_e2*r2_e1*r3_no*r4_no+r0_ni*r1_e2*r2_e2*r3_e1*r4_e2-r0_ni*r1_e2*r2_e2*r3_e2*r4_e1+r0_ni*r1_e2*r2_e3*r3_e1*r4_e3-r0_ni*r1_e2*r2_e3*r3_e3*r4_e1+r0_ni*r1_e2*r2_ni*r3_e1*r4_ni-r0_ni*r1_e2*r2_ni*r3_ni*r4_e1+r0_ni*r1_e2*r2_no*r3_e1*r4_no-r0_ni*r1_e2*r2_no*r3_no*r4_e1+r0_ni*r1_e3*r2_e1*r3_e2*r4_e3-r0_ni*r1_e3*r2_e1*r3_e3*r4_e2-r0_ni*r1_e3*r2_e2*r3_e1*r4_e3+r0_ni*r1_e3*r2_e2*r3_e3*r4_e1+r0_ni*r1_e3*r2_e3*r3_e1*r4_e2-r0_ni*r1_e3*r2_e3*r3_e2*r4_e1+r0_ni*r1_ni*r2_e1*r3_e2*r4_ni-r0_ni*r1_ni*r2_e1*r3_ni*r4_e2-r0_ni*r1_ni*r2_e2*r3_e1*r4_ni+r0_ni*r1_ni*r2_e2*r3_ni*r4_e1+r0_ni*r1_ni*r2_ni*r3_e1*r4_e2-r0_ni*r1_ni*r2_ni*r3_e2*r4_e1+r0_ni*r1_no*r2_e1*r3_e2*r4_no-r0_ni*r1_no*r2_e1*r3_no*r4_e2-r0_ni*r1_no*r2_e2*r3_e1*r4_no+r0_ni*r1_no*r2_e2*r3_no*r4_e1+r0_ni*r1_no*r2_no*r3_e1*r4_e2-r0_ni*r1_no*r2_no*r3_e2*r4_e1-r0_no*r1_e1*r2_e2*r3_ni*r4_no+r0_no*r1_e1*r2_e2*r3_no*r4_ni+r0_no*r1_e1*r2_ni*r3_e2*r4_no-r0_no*r1_e1*r2_ni*r3_no*r4_e2-r0_no*r1_e1*r2_no*r3_e2*r4_ni+r0_no*r1_e1*r2_no*r3_ni*r4_e2+r0_no*r1_e2*r2_e1*r3_ni*r4_no-r0_no*r1_e2*r2_e1*r3_no*r4_ni-r0_no*r1_e2*r2_ni*r3_e1*r4_no+r0_no*r1_e2*r2_ni*r3_no*r4_e1+r0_no*r1_e2*r2_no*r3_e1*r4_ni-r0_no*r1_e2*r2_no*r3_ni*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_no+r0_no*r1_ni*r2_e1*r3_no*r4_e2+r0_no*r1_ni*r2_e2*r3_e1*r4_no-r0_no*r1_ni*r2_e2*r3_no*r4_e1-r0_no*r1_ni*r2_no*r3_e1*r4_e2+r0_no*r1_ni*r2_no*r3_e2*r4_e1+r0_no*r1_no*r2_e1*r3_e2*r4_ni-r0_no*r1_no*r2_e1*r3_ni*r4_e2-r0_no*r1_no*r2_e2*r3_e1*r4_ni+r0_no*r1_no*r2_e2*r3_ni*r4_e1+r0_no*r1_no*r2_ni*r3_e1*r4_e2-r0_no*r1_no*r2_ni*r3_e2*r4_e1);
	tmp.m_no_e3_ni = (r0_e1*r1_e1*r2_e3*r3_ni*r4_no-r0_e1*r1_e1*r2_e3*r3_no*r4_ni-r0_e1*r1_e1*r2_ni*r3_e3*r4_no+r0_e1*r1_e1*r2_ni*r3_no*r4_e3+r0_e1*r1_e1*r2_no*r3_e3*r4_ni-r0_e1*r1_e1*r2_no*r3_ni*r4_e3-r0_e1*r1_e3*r2_e1*r3_ni*r4_no+r0_e1*r1_e3*r2_e1*r3_no*r4_ni+r0_e1*r1_e3*r2_ni*r3_e1*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_e1-r0_e1*r1_e3*r2_no*r3_e1*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e1+r0_e1*r1_ni*r2_e1*r3_e3*r4_no-r0_e1*r1_ni*r2_e1*r3_no*r4_e3-r0_e1*r1_ni*r2_e3*r3_e1*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_e1+r0_e1*r1_ni*r2_no*r3_e1*r4_e3-r0_e1*r1_ni*r2_no*r3_e3*r4_e1-r0_e1*r1_no*r2_e1*r3_e3*r4_ni+r0_e1*r1_no*r2_e1*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e1*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e1-r0_e1*r1_no*r2_ni*r3_e1*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e1+r0_e2*r1_e2*r2_e3*r3_ni*r4_no-r0_e2*r1_e2*r2_e3*r3_no*r4_ni-r0_e2*r1_e2*r2_ni*r3_e3*r4_no+r0_e2*r1_e2*r2_ni*r3_no*r4_e3+r0_e2*r1_e2*r2_no*r3_e3*r4_ni-r0_e2*r1_e2*r2_no*r3_ni*r4_e3-r0_e2*r1_e3*r2_e2*r3_ni*r4_no+r0_e2*r1_e3*r2_e2*r3_no*r4_ni+r0_e2*r1_e3*r2_ni*r3_e2*r4_no-r0_e2*r1_e3*r2_ni*r3_no*r4_e2-r0_e2*r1_e3*r2_no*r3_e2*r4_ni+r0_e2*r1_e3*r2_no*r3_ni*r4_e2+r0_e2*r1_ni*r2_e2*r3_e3*r4_no-r0_e2*r1_ni*r2_e2*r3_no*r4_e3-r0_e2*r1_ni*r2_e3*r3_e2*r4_no+r0_e2*r1_ni*r2_e3*r3_no*r4_e2+r0_e2*r1_ni*r2_no*r3_e2*r4_e3-r0_e2*r1_ni*r2_no*r3_e3*r4_e2-r0_e2*r1_no*r2_e2*r3_e3*r4_ni+r0_e2*r1_no*r2_e2*r3_ni*r4_e3+r0_e2*r1_no*r2_e3*r3_e2*r4_ni-r0_e2*r1_no*r2_e3*r3_ni*r4_e2-r0_e2*r1_no*r2_ni*r3_e2*r4_e3+r0_e2*r1_no*r2_ni*r3_e3*r4_e2+r0_e3*r1_e1*r2_e1*r3_ni*r4_no-r0_e3*r1_e1*r2_e1*r3_no*r4_ni-r0_e3*r1_e1*r2_ni*r3_e1*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_e1+r0_e3*r1_e1*r2_no*r3_e1*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e1+r0_e3*r1_e2*r2_e2*r3_ni*r4_no-r0_e3*r1_e2*r2_e2*r3_no*r4_ni-r0_e3*r1_e2*r2_ni*r3_e2*r4_no+r0_e3*r1_e2*r2_ni*r3_no*r4_e2+r0_e3*r1_e2*r2_no*r3_e2*r4_ni-r0_e3*r1_e2*r2_no*r3_ni*r4_e2+r0_e3*r1_e3*r2_e3*r3_ni*r4_no-r0_e3*r1_e3*r2_e3*r3_no*r4_ni-r0_e3*r1_e3*r2_ni*r3_e3*r4_no+r0_e3*r1_e3*r2_ni*r3_no*r4_e3+r0_e3*r1_e3*r2_no*r3_e3*r4_ni-r0_e3*r1_e3*r2_no*r3_ni*r4_e3+r0_e3*r1_ni*r2_e1*r3_e1*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_e1+r0_e3*r1_ni*r2_e2*r3_e2*r4_no-r0_e3*r1_ni*r2_e2*r3_no*r4_e2+r0_e3*r1_ni*r2_e3*r3_e3*r4_no-r0_e3*r1_ni*r2_e3*r3_no*r4_e3+r0_e3*r1_ni*r2_ni*r3_ni*r4_no-r0_e3*r1_ni*r2_ni*r3_no*r4_ni+r0_e3*r1_ni*r2_no*r3_e1*r4_e1+r0_e3*r1_ni*r2_no*r3_e2*r4_e2+r0_e3*r1_ni*r2_no*r3_e3*r4_e3+r0_e3*r1_ni*r2_no*r3_ni*r4_ni+r0_e3*r1_ni*r2_no*r3_no*r4_no-r0_e3*r1_no*r2_e1*r3_e1*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e1-r0_e3*r1_no*r2_e2*r3_e2*r4_ni+r0_e3*r1_no*r2_e2*r3_ni*r4_e2-r0_e3*r1_no*r2_e3*r3_e3*r4_ni+r0_e3*r1_no*r2_e3*r3_ni*r4_e3-r0_e3*r1_no*r2_ni*r3_e1*r4_e1-r0_e3*r1_no*r2_ni*r3_e2*r4_e2-r0_e3*r1_no*r2_ni*r3_e3*r4_e3-r0_e3*r1_no*r2_ni*r3_ni*r4_ni-r0_e3*r1_no*r2_ni*r3_no*r4_no+r0_e3*r1_no*r2_no*r3_ni*r4_no-r0_e3*r1_no*r2_no*r3_no*r4_ni-r0_ni*r1_e1*r2_e1*r3_e3*r4_no+r0_ni*r1_e1*r2_e1*r3_no*r4_e3+r0_ni*r1_e1*r2_e3*r3_e1*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_e1-r0_ni*r1_e1*r2_no*r3_e1*r4_e3+r0_ni*r1_e1*r2_no*r3_e3*r4_e1-r0_ni*r1_e2*r2_e2*r3_e3*r4_no+r0_ni*r1_e2*r2_e2*r3_no*r4_e3+r0_ni*r1_e2*r2_e3*r3_e2*r4_no-r0_ni*r1_e2*r2_e3*r3_no*r4_e2-r0_ni*r1_e2*r2_no*r3_e2*r4_e3+r0_ni*r1_e2*r2_no*r3_e3*r4_e2-r0_ni*r1_e3*r2_e1*r3_e1*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_e1-r0_ni*r1_e3*r2_e2*r3_e2*r4_no+r0_ni*r1_e3*r2_e2*r3_no*r4_e2-r0_ni*r1_e3*r2_e3*r3_e3*r4_no+r0_ni*r1_e3*r2_e3*r3_no*r4_e3-r0_ni*r1_e3*r2_ni*r3_ni*r4_no+r0_ni*r1_e3*r2_ni*r3_no*r4_ni-r0_ni*r1_e3*r2_no*r3_e1*r4_e1-r0_ni*r1_e3*r2_no*r3_e2*r4_e2-r0_ni*r1_e3*r2_no*r3_e3*r4_e3-r0_ni*r1_e3*r2_no*r3_ni*r4_ni-r0_ni*r1_e3*r2_no*r3_no*r4_no+r0_ni*r1_ni*r2_e3*r3_ni*r4_no-r0_ni*r1_ni*r2_e3*r3_no*r4_ni-r0_ni*r1_ni*r2_ni*r3_e3*r4_no+r0_ni*r1_ni*r2_ni*r3_no*r4_e3+r0_ni*r1_ni*r2_no*r3_e3*r4_ni-r0_ni*r1_ni*r2_no*r3_ni*r4_e3+r0_ni*r1_no*r2_e1*r3_e1*r4_e3-r0_ni*r1_no*r2_e1*r3_e3*r4_e1+r0_ni*r1_no*r2_e2*r3_e2*r4_e3-r0_ni*r1_no*r2_e2*r3_e3*r4_e2+r0_ni*r1_no*r2_e3*r3_e1*r4_e1+r0_ni*r1_no*r2_e3*r3_e2*r4_e2+r0_ni*r1_no*r2_e3*r3_e3*r4_e3+r0_ni*r1_no*r2_e3*r3_ni*r4_ni+r0_ni*r1_no*r2_e3*r3_no*r4_no-r0_ni*r1_no*r2_ni*r3_e3*r4_ni+r0_ni*r1_no*r2_ni*r3_ni*r4_e3-r0_ni*r1_no*r2_no*r3_e3*r4_no+r0_ni*r1_no*r2_no*r3_no*r4_e3+r0_no*r1_e1*r2_e1*r3_e3*r4_ni-r0_no*r1_e1*r2_e1*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e1*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e1+r0_no*r1_e1*r2_ni*r3_e1*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e1+r0_no*r1_e2*r2_e2*r3_e3*r4_ni-r0_no*r1_e2*r2_e2*r3_ni*r4_e3-r0_no*r1_e2*r2_e3*r3_e2*r4_ni+r0_no*r1_e2*r2_e3*r3_ni*r4_e2+r0_no*r1_e2*r2_ni*r3_e2*r4_e3-r0_no*r1_e2*r2_ni*r3_e3*r4_e2+r0_no*r1_e3*r2_e1*r3_e1*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e1+r0_no*r1_e3*r2_e2*r3_e2*r4_ni-r0_no*r1_e3*r2_e2*r3_ni*r4_e2+r0_no*r1_e3*r2_e3*r3_e3*r4_ni-r0_no*r1_e3*r2_e3*r3_ni*r4_e3+r0_no*r1_e3*r2_ni*r3_e1*r4_e1+r0_no*r1_e3*r2_ni*r3_e2*r4_e2+r0_no*r1_e3*r2_ni*r3_e3*r4_e3+r0_no*r1_e3*r2_ni*r3_ni*r4_ni+r0_no*r1_e3*r2_ni*r3_no*r4_no-r0_no*r1_e3*r2_no*r3_ni*r4_no+r0_no*r1_e3*r2_no*r3_no*r4_ni-r0_no*r1_ni*r2_e1*r3_e1*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e1-r0_no*r1_ni*r2_e2*r3_e2*r4_e3+r0_no*r1_ni*r2_e2*r3_e3*r4_e2-r0_no*r1_ni*r2_e3*r3_e1*r4_e1-r0_no*r1_ni*r2_e3*r3_e2*r4_e2-r0_no*r1_ni*r2_e3*r3_e3*r4_e3-r0_no*r1_ni*r2_e3*r3_ni*r4_ni-r0_no*r1_ni*r2_e3*r3_no*r4_no+r0_no*r1_ni*r2_ni*r3_e3*r4_ni-r0_no*r1_ni*r2_ni*r3_ni*r4_e3+r0_no*r1_ni*r2_no*r3_e3*r4_no-r0_no*r1_ni*r2_no*r3_no*r4_e3+r0_no*r1_no*r2_e3*r3_ni*r4_no-r0_no*r1_no*r2_e3*r3_no*r4_ni-r0_no*r1_no*r2_ni*r3_e3*r4_no+r0_no*r1_no*r2_ni*r3_no*r4_e3+r0_no*r1_no*r2_no*r3_e3*r4_ni-r0_no*r1_no*r2_no*r3_ni*r4_e3);
	tmp.m_e1_e3_ni = (r0_e1*r1_e1*r2_e1*r3_e3*r4_ni-r0_e1*r1_e1*r2_e1*r3_ni*r4_e3-r0_e1*r1_e1*r2_e3*r3_e1*r4_ni+r0_e1*r1_e1*r2_e3*r3_ni*r4_e1+r0_e1*r1_e1*r2_ni*r3_e1*r4_e3-r0_e1*r1_e1*r2_ni*r3_e3*r4_e1+r0_e1*r1_e2*r2_e2*r3_e3*r4_ni-r0_e1*r1_e2*r2_e2*r3_ni*r4_e3-r0_e1*r1_e2*r2_e3*r3_e2*r4_ni+r0_e1*r1_e2*r2_e3*r3_ni*r4_e2+r0_e1*r1_e2*r2_ni*r3_e2*r4_e3-r0_e1*r1_e2*r2_ni*r3_e3*r4_e2+r0_e1*r1_e3*r2_e1*r3_e1*r4_ni-r0_e1*r1_e3*r2_e1*r3_ni*r4_e1+r0_e1*r1_e3*r2_e2*r3_e2*r4_ni-r0_e1*r1_e3*r2_e2*r3_ni*r4_e2+r0_e1*r1_e3*r2_e3*r3_e3*r4_ni-r0_e1*r1_e3*r2_e3*r3_ni*r4_e3+r0_e1*r1_e3*r2_ni*r3_e1*r4_e1+r0_e1*r1_e3*r2_ni*r3_e2*r4_e2+r0_e1*r1_e3*r2_ni*r3_e3*r4_e3+r0_e1*r1_e3*r2_ni*r3_ni*r4_ni+r0_e1*r1_e3*r2_ni*r3_no*r4_no-r0_e1*r1_e3*r2_no*r3_ni*r4_no+r0_e1*r1_e3*r2_no*r3_no*r4_ni-r0_e1*r1_ni*r2_e1*r3_e1*r4_e3+r0_e1*r1_ni*r2_e1*r3_e3*r4_e1-r0_e1*r1_ni*r2_e2*r3_e2*r4_e3+r0_e1*r1_ni*r2_e2*r3_e3*r4_e2-r0_e1*r1_ni*r2_e3*r3_e1*r4_e1-r0_e1*r1_ni*r2_e3*r3_e2*r4_e2-r0_e1*r1_ni*r2_e3*r3_e3*r4_e3-r0_e1*r1_ni*r2_e3*r3_ni*r4_ni-r0_e1*r1_ni*r2_e3*r3_no*r4_no+r0_e1*r1_ni*r2_ni*r3_e3*r4_ni-r0_e1*r1_ni*r2_ni*r3_ni*r4_e3+r0_e1*r1_ni*r2_no*r3_e3*r4_no-r0_e1*r1_ni*r2_no*r3_no*r4_e3+r0_e1*r1_no*r2_e3*r3_ni*r4_no-r0_e1*r1_no*r2_e3*r3_no*r4_ni-r0_e1*r1_no*r2_ni*r3_e3*r4_no+r0_e1*r1_no*r2_ni*r3_no*r4_e3+r0_e1*r1_no*r2_no*r3_e3*r4_ni-r0_e1*r1_no*r2_no*r3_ni*r4_e3-r0_e2*r1_e1*r2_e2*r3_e3*r4_ni+r0_e2*r1_e1*r2_e2*r3_ni*r4_e3+r0_e2*r1_e1*r2_e3*r3_e2*r4_ni-r0_e2*r1_e1*r2_e3*r3_ni*r4_e2-r0_e2*r1_e1*r2_ni*r3_e2*r4_e3+r0_e2*r1_e1*r2_ni*r3_e3*r4_e2+r0_e2*r1_e2*r2_e1*r3_e3*r4_ni-r0_e2*r1_e2*r2_e1*r3_ni*r4_e3-r0_e2*r1_e2*r2_e3*r3_e1*r4_ni+r0_e2*r1_e2*r2_e3*r3_ni*r4_e1+r0_e2*r1_e2*r2_ni*r3_e1*r4_e3-r0_e2*r1_e2*r2_ni*r3_e3*r4_e1-r0_e2*r1_e3*r2_e1*r3_e2*r4_ni+r0_e2*r1_e3*r2_e1*r3_ni*r4_e2+r0_e2*r1_e3*r2_e2*r3_e1*r4_ni-r0_e2*r1_e3*r2_e2*r3_ni*r4_e1-r0_e2*r1_e3*r2_ni*r3_e1*r4_e2+r0_e2*r1_e3*r2_ni*r3_e2*r4_e1+r0_e2*r1_ni*r2_e1*r3_e2*r4_e3-r0_e2*r1_ni*r2_e1*r3_e3*r4_e2-r0_e2*r1_ni*r2_e2*r3_e1*r4_e3+r0_e2*r1_ni*r2_e2*r3_e3*r4_e1+r0_e2*r1_ni*r2_e3*r3_e1*r4_e2-r0_e2*r1_ni*r2_e3*r3_e2*r4_e1-r0_e3*r1_e1*r2_e1*r3_e1*r4_ni+r0_e3*r1_e1*r2_e1*r3_ni*r4_e1-r0_e3*r1_e1*r2_e2*r3_e2*r4_ni+r0_e3*r1_e1*r2_e2*r3_ni*r4_e2-r0_e3*r1_e1*r2_e3*r3_e3*r4_ni+r0_e3*r1_e1*r2_e3*r3_ni*r4_e3-r0_e3*r1_e1*r2_ni*r3_e1*r4_e1-r0_e3*r1_e1*r2_ni*r3_e2*r4_e2-r0_e3*r1_e1*r2_ni*r3_e3*r4_e3-r0_e3*r1_e1*r2_ni*r3_ni*r4_ni-r0_e3*r1_e1*r2_ni*r3_no*r4_no+r0_e3*r1_e1*r2_no*r3_ni*r4_no-r0_e3*r1_e1*r2_no*r3_no*r4_ni+r0_e3*r1_e2*r2_e1*r3_e2*r4_ni-r0_e3*r1_e2*r2_e1*r3_ni*r4_e2-r0_e3*r1_e2*r2_e2*r3_e1*r4_ni+r0_e3*r1_e2*r2_e2*r3_ni*r4_e1+r0_e3*r1_e2*r2_ni*r3_e1*r4_e2-r0_e3*r1_e2*r2_ni*r3_e2*r4_e1+r0_e3*r1_e3*r2_e1*r3_e3*r4_ni-r0_e3*r1_e3*r2_e1*r3_ni*r4_e3-r0_e3*r1_e3*r2_e3*r3_e1*r4_ni+r0_e3*r1_e3*r2_e3*r3_ni*r4_e1+r0_e3*r1_e3*r2_ni*r3_e1*r4_e3-r0_e3*r1_e3*r2_ni*r3_e3*r4_e1+r0_e3*r1_ni*r2_e1*r3_e1*r4_e1+r0_e3*r1_ni*r2_e1*r3_e2*r4_e2+r0_e3*r1_ni*r2_e1*r3_e3*r4_e3+r0_e3*r1_ni*r2_e1*r3_ni*r4_ni+r0_e3*r1_ni*r2_e1*r3_no*r4_no-r0_e3*r1_ni*r2_e2*r3_e1*r4_e2+r0_e3*r1_ni*r2_e2*r3_e2*r4_e1-r0_e3*r1_ni*r2_e3*r3_e1*r4_e3+r0_e3*r1_ni*r2_e3*r3_e3*r4_e1-r0_e3*r1_ni*r2_ni*r3_e1*r4_ni+r0_e3*r1_ni*r2_ni*r3_ni*r4_e1-r0_e3*r1_ni*r2_no*r3_e1*r4_no+r0_e3*r1_ni*r2_no*r3_no*r4_e1-r0_e3*r1_no*r2_e1*r3_ni*r4_no+r0_e3*r1_no*r2_e1*r3_no*r4_ni+r0_e3*r1_no*r2_ni*r3_e1*r4_no-r0_e3*r1_no*r2_ni*r3_no*r4_e1-r0_e3*r1_no*r2_no*r3_e1*r4_ni+r0_e3*r1_no*r2_no*r3_ni*r4_e1+r0_ni*r1_e1*r2_e1*r3_e1*r4_e3-r0_ni*r1_e1*r2_e1*r3_e3*r4_e1+r0_ni*r1_e1*r2_e2*r3_e2*r4_e3-r0_ni*r1_e1*r2_e2*r3_e3*r4_e2+r0_ni*r1_e1*r2_e3*r3_e1*r4_e1+r0_ni*r1_e1*r2_e3*r3_e2*r4_e2+r0_ni*r1_e1*r2_e3*r3_e3*r4_e3+r0_ni*r1_e1*r2_e3*r3_ni*r4_ni+r0_ni*r1_e1*r2_e3*r3_no*r4_no-r0_ni*r1_e1*r2_ni*r3_e3*r4_ni+r0_ni*r1_e1*r2_ni*r3_ni*r4_e3-r0_ni*r1_e1*r2_no*r3_e3*r4_no+r0_ni*r1_e1*r2_no*r3_no*r4_e3-r0_ni*r1_e2*r2_e1*r3_e2*r4_e3+r0_ni*r1_e2*r2_e1*r3_e3*r4_e2+r0_ni*r1_e2*r2_e2*r3_e1*r4_e3-r0_ni*r1_e2*r2_e2*r3_e3*r4_e1-r0_ni*r1_e2*r2_e3*r3_e1*r4_e2+r0_ni*r1_e2*r2_e3*r3_e2*r4_e1-r0_ni*r1_e3*r2_e1*r3_e1*r4_e1-r0_ni*r1_e3*r2_e1*r3_e2*r4_e2-r0_ni*r1_e3*r2_e1*r3_e3*r4_e3-r0_ni*r1_e3*r2_e1*r3_ni*r4_ni-r0_ni*r1_e3*r2_e1*r3_no*r4_no+r0_ni*r1_e3*r2_e2*r3_e1*r4_e2-r0_ni*r1_e3*r2_e2*r3_e2*r4_e1+r0_ni*r1_e3*r2_e3*r3_e1*r4_e3-r0_ni*r1_e3*r2_e3*r3_e3*r4_e1+r0_ni*r1_e3*r2_ni*r3_e1*r4_ni-r0_ni*r1_e3*r2_ni*r3_ni*r4_e1+r0_ni*r1_e3*r2_no*r3_e1*r4_no-r0_ni*r1_e3*r2_no*r3_no*r4_e1+r0_ni*r1_ni*r2_e1*r3_e3*r4_ni-r0_ni*r1_ni*r2_e1*r3_ni*r4_e3-r0_ni*r1_ni*r2_e3*r3_e1*r4_ni+r0_ni*r1_ni*r2_e3*r3_ni*r4_e1+r0_ni*r1_ni*r2_ni*r3_e1*r4_e3-r0_ni*r1_ni*r2_ni*r3_e3*r4_e1+r0_ni*r1_no*r2_e1*r3_e3*r4_no-r0_ni*r1_no*r2_e1*r3_no*r4_e3-r0_ni*r1_no*r2_e3*r3_e1*r4_no+r0_ni*r1_no*r2_e3*r3_no*r4_e1+r0_ni*r1_no*r2_no*r3_e1*r4_e3-r0_ni*r1_no*r2_no*r3_e3*r4_e1-r0_no*r1_e1*r2_e3*r3_ni*r4_no+r0_no*r1_e1*r2_e3*r3_no*r4_ni+r0_no*r1_e1*r2_ni*r3_e3*r4_no-r0_no*r1_e1*r2_ni*r3_no*r4_e3-r0_no*r1_e1*r2_no*r3_e3*r4_ni+r0_no*r1_e1*r2_no*r3_ni*r4_e3+r0_no*r1_e3*r2_e1*r3_ni*r4_no-r0_no*r1_e3*r2_e1*r3_no*r4_ni-r0_no*r1_e3*r2_ni*r3_e1*r4_no+r0_no*r1_e3*r2_ni*r3_no*r4_e1+r0_no*r1_e3*r2_no*r3_e1*r4_ni-r0_no*r1_e3*r2_no*r3_ni*r4_e1-r0_no*r1_ni*r2_e1*r3_e3*r4_no+r0_no*r1_ni*r2_e1*r3_no*r4_e3+r0_no*r1_ni*r2_e3*r3_e1*r4_no-r0_no*r1_ni*r2_e3*r3_no*r4_e1-r0_no*r1_ni*r2_no*r3_e1*r4_e3+r0_no*r1_ni*r2_no*r3_e3*r4_e1+r0_no*r1_no*r2_e1*r3_e3*r4_ni-r0_no*r1_no*r2_e1*r3_ni*r4_e3-r0_no*r1_no*r2_e3*r3_e1*r4_ni+r0_no*r1_no*r2_e3*r3_ni*r4_e1+r0_no*r1_no*r2_ni*r3_e1*r4_e3-r0_no*r1_no*r2_ni*r3_e3*r4_e1);
	tmp.m_e2_e3_ni = (r0_e1*r1_e1*r2_e2*r3_e3*r4_ni-r0_e1*r1_e1*r2_e2*r3_ni*r4_e3-r0_e1*r1_e1*r2_e3*r3_e2*r4_ni+r0_e1*r1_e1*r2_e3*r3_ni*r4_e2+r0_e1*r1_e1*r2_ni*r3_e2*r4_e3-r0_e1*r1_e1*r2_ni*r3_e3*r4_e2-r0_e1*r1_e2*r2_e1*r3_e3*r4_ni+r0_e1*r1_e2*r2_e1*r3_ni*r4_e3+r0_e1*r1_e2*r2_e3*r3_e1*r4_ni-r0_e1*r1_e2*r2_e3*r3_ni*r4_e1-r0_e1*r1_e2*r2_ni*r3_e1*r4_e3+r0_e1*r1_e2*r2_ni*r3_e3*r4_e1+r0_e1*r1_e3*r2_e1*r3_e2*r4_ni-r0_e1*r1_e3*r2_e1*r3_ni*r4_e2-r0_e1*r1_e3*r2_e2*r3_e1*r4_ni+r0_e1*r1_e3*r2_e2*r3_ni*r4_e1+r0_e1*r1_e3*r2_ni*r3_e1*r4_e2-r0_e1*r1_e3*r2_ni*r3_e2*r4_e1-r0_e1*r1_ni*r2_e1*r3_e2*r4_e3+r0_e1*r1_ni*r2_e1*r3_e3*r4_e2+r0_e1*r1_ni*r2_e2*r3_e1*r4_e3-r0_e1*r1_ni*r2_e2*r3_e3*r4_e1-r0_e1*r1_ni*r2_e3*r3_e1*r4_e2+r0_e1*r1_ni*r2_e3*r3_e2*r4_e1+r0_e2*r1_e1*r2_e1*r3_e3*r4_ni-r0_e2*r1_e1*r2_e1*r3_ni*r4_e3-r0_e2*r1_e1*r2_e3*r3_e1*r4_ni+r0_e2*r1_e1*r2_e3*r3_ni*r4_e1+r0_e2*r1_e1*r2_ni*r3_e1*r4_e3-r0_e2*r1_e1*r2_ni*r3_e3*r4_e1+r0_e2*r1_e2*r2_e2*r3_e3*r4_ni-r0_e2*r1_e2*r2_e2*r3_ni*r4_e3-r0_e2*r1_e2*r2_e3*r3_e2*r4_ni+r0_e2*r1_e2*r2_e3*r3_ni*r4_e2+r0_e2*r1_e2*r2_ni*r3_e2*r4_e3-r0_e2*r1_e2*r2_ni*r3_e3*r4_e2+r0_e2*r1_e3*r2_e1*r3_e1*r4_ni-r0_e2*r1_e3*r2_e1*r3_ni*r4_e1+r0_e2*r1_e3*r2_e2*r3_e2*r4_ni-r0_e2*r1_e3*r2_e2*r3_ni*r4_e2+r0_e2*r1_e3*r2_e3*r3_e3*r4_ni-r0_e2*r1_e3*r2_e3*r3_ni*r4_e3+r0_e2*r1_e3*r2_ni*r3_e1*r4_e1+r0_e2*r1_e3*r2_ni*r3_e2*r4_e2+r0_e2*r1_e3*r2_ni*r3_e3*r4_e3+r0_e2*r1_e3*r2_ni*r3_ni*r4_ni+r0_e2*r1_e3*r2_ni*r3_no*r4_no-r0_e2*r1_e3*r2_no*r3_ni*r4_no+r0_e2*r1_e3*r2_no*r3_no*r4_ni-r0_e2*r1_ni*r2_e1*r3_e1*r4_e3+r0_e2*r1_ni*r2_e1*r3_e3*r4_e1-r0_e2*r1_ni*r2_e2*r3_e2*r4_e3+r0_e2*r1_ni*r2_e2*r3_e3*r4_e2-r0_e2*r1_ni*r2_e3*r3_e1*r4_e1-r0_e2*r1_ni*r2_e3*r3_e2*r4_e2-r0_e2*r1_ni*r2_e3*r3_e3*r4_e3-r0_e2*r1_ni*r2_e3*r3_ni*r4_ni-r0_e2*r1_ni*r2_e3*r3_no*r4_no+r0_e2*r1_ni*r2_ni*r3_e3*r4_ni-r0_e2*r1_ni*r2_ni*r3_ni*r4_e3+r0_e2*r1_ni*r2_no*r3_e3*r4_no-r0_e2*r1_ni*r2_no*r3_no*r4_e3+r0_e2*r1_no*r2_e3*r3_ni*r4_no-r0_e2*r1_no*r2_e3*r3_no*r4_ni-r0_e2*r1_no*r2_ni*r3_e3*r4_no+r0_e2*r1_no*r2_ni*r3_no*r4_e3+r0_e2*r1_no*r2_no*r3_e3*r4_ni-r0_e2*r1_no*r2_no*r3_ni*r4_e3-r0_e3*r1_e1*r2_e1*r3_e2*r4_ni+r0_e3*r1_e1*r2_e1*r3_ni*r4_e2+r0_e3*r1_e1*r2_e2*r3_e1*r4_ni-r0_e3*r1_e1*r2_e2*r3_ni*r4_e1-r0_e3*r1_e1*r2_ni*r3_e1*r4_e2+r0_e3*r1_e1*r2_ni*r3_e2*r4_e1-r0_e3*r1_e2*r2_e1*r3_e1*r4_ni+r0_e3*r1_e2*r2_e1*r3_ni*r4_e1-r0_e3*r1_e2*r2_e2*r3_e2*r4_ni+r0_e3*r1_e2*r2_e2*r3_ni*r4_e2-r0_e3*r1_e2*r2_e3*r3_e3*r4_ni+r0_e3*r1_e2*r2_e3*r3_ni*r4_e3-r0_e3*r1_e2*r2_ni*r3_e1*r4_e1-r0_e3*r1_e2*r2_ni*r3_e2*r4_e2-r0_e3*r1_e2*r2_ni*r3_e3*r4_e3-r0_e3*r1_e2*r2_ni*r3_ni*r4_ni-r0_e3*r1_e2*r2_ni*r3_no*r4_no+r0_e3*r1_e2*r2_no*r3_ni*r4_no-r0_e3*r1_e2*r2_no*r3_no*r4_ni+r0_e3*r1_e3*r2_e2*r3_e3*r4_ni-r0_e3*r1_e3*r2_e2*r3_ni*r4_e3-r0_e3*r1_e3*r2_e3*r3_e2*r4_ni+r0_e3*r1_e3*r2_e3*r3_ni*r4_e2+r0_e3*r1_e3*r2_ni*r3_e2*r4_e3-r0_e3*r1_e3*r2_ni*r3_e3*r4_e2+r0_e3*r1_ni*r2_e1*r3_e1*r4_e2-r0_e3*r1_ni*r2_e1*r3_e2*r4_e1+r0_e3*r1_ni*r2_e2*r3_e1*r4_e1+r0_e3*r1_ni*r2_e2*r3_e2*r4_e2+r0_e3*r1_ni*r2_e2*r3_e3*r4_e3+r0_e3*r1_ni*r2_e2*r3_ni*r4_ni+r0_e3*r1_ni*r2_e2*r3_no*r4_no-r0_e3*r1_ni*r2_e3*r3_e2*r4_e3+r0_e3*r1_ni*r2_e3*r3_e3*r4_e2-r0_e3*r1_ni*r2_ni*r3_e2*r4_ni+r0_e3*r1_ni*r2_ni*r3_ni*r4_e2-r0_e3*r1_ni*r2_no*r3_e2*r4_no+r0_e3*r1_ni*r2_no*r3_no*r4_e2-r0_e3*r1_no*r2_e2*r3_ni*r4_no+r0_e3*r1_no*r2_e2*r3_no*r4_ni+r0_e3*r1_no*r2_ni*r3_e2*r4_no-r0_e3*r1_no*r2_ni*r3_no*r4_e2-r0_e3*r1_no*r2_no*r3_e2*r4_ni+r0_e3*r1_no*r2_no*r3_ni*r4_e2+r0_ni*r1_e1*r2_e1*r3_e2*r4_e3-r0_ni*r1_e1*r2_e1*r3_e3*r4_e2-r0_ni*r1_e1*r2_e2*r3_e1*r4_e3+r0_ni*r1_e1*r2_e2*r3_e3*r4_e1+r0_ni*r1_e1*r2_e3*r3_e1*r4_e2-r0_ni*r1_e1*r2_e3*r3_e2*r4_e1+r0_ni*r1_e2*r2_e1*r3_e1*r4_e3-r0_ni*r1_e2*r2_e1*r3_e3*r4_e1+r0_ni*r1_e2*r2_e2*r3_e2*r4_e3-r0_ni*r1_e2*r2_e2*r3_e3*r4_e2+r0_ni*r1_e2*r2_e3*r3_e1*r4_e1+r0_ni*r1_e2*r2_e3*r3_e2*r4_e2+r0_ni*r1_e2*r2_e3*r3_e3*r4_e3+r0_ni*r1_e2*r2_e3*r3_ni*r4_ni+r0_ni*r1_e2*r2_e3*r3_no*r4_no-r0_ni*r1_e2*r2_ni*r3_e3*r4_ni+r0_ni*r1_e2*r2_ni*r3_ni*r4_e3-r0_ni*r1_e2*r2_no*r3_e3*r4_no+r0_ni*r1_e2*r2_no*r3_no*r4_e3-r0_ni*r1_e3*r2_e1*r3_e1*r4_e2+r0_ni*r1_e3*r2_e1*r3_e2*r4_e1-r0_ni*r1_e3*r2_e2*r3_e1*r4_e1-r0_ni*r1_e3*r2_e2*r3_e2*r4_e2-r0_ni*r1_e3*r2_e2*r3_e3*r4_e3-r0_ni*r1_e3*r2_e2*r3_ni*r4_ni-r0_ni*r1_e3*r2_e2*r3_no*r4_no+r0_ni*r1_e3*r2_e3*r3_e2*r4_e3-r0_ni*r1_e3*r2_e3*r3_e3*r4_e2+r0_ni*r1_e3*r2_ni*r3_e2*r4_ni-r0_ni*r1_e3*r2_ni*r3_ni*r4_e2+r0_ni*r1_e3*r2_no*r3_e2*r4_no-r0_ni*r1_e3*r2_no*r3_no*r4_e2+r0_ni*r1_ni*r2_e2*r3_e3*r4_ni-r0_ni*r1_ni*r2_e2*r3_ni*r4_e3-r0_ni*r1_ni*r2_e3*r3_e2*r4_ni+r0_ni*r1_ni*r2_e3*r3_ni*r4_e2+r0_ni*r1_ni*r2_ni*r3_e2*r4_e3-r0_ni*r1_ni*r2_ni*r3_e3*r4_e2+r0_ni*r1_no*r2_e2*r3_e3*r4_no-r0_ni*r1_no*r2_e2*r3_no*r4_e3-r0_ni*r1_no*r2_e3*r3_e2*r4_no+r0_ni*r1_no*r2_e3*r3_no*r4_e2+r0_ni*r1_no*r2_no*r3_e2*r4_e3-r0_ni*r1_no*r2_no*r3_e3*r4_e2-r0_no*r1_e2*r2_e3*r3_ni*r4_no+r0_no*r1_e2*r2_e3*r3_no*r4_ni+r0_no*r1_e2*r2_ni*r3_e3*r4_no-r0_no*r1_e2*r2_ni*r3_no*r4_e3-r0_no*r1_e2*r2_no*r3_e3*r4_ni+r0_no*r1_e2*r2_no*r3_ni*r4_e3+r0_no*r1_e3*r2_e2*r3_ni*r4_no-r0_no*r1_e3*r2_e2*r3_no*r4_ni-r0_no*r1_e3*r2_ni*r3_e2*r4_no+r0_no*r1_e3*r2_ni*r3_no*r4_e2+r0_no*r1_e3*r2_no*r3_e2*r4_ni-r0_no*r1_e3*r2_no*r3_ni*r4_e2-r0_no*r1_ni*r2_e2*r3_e3*r4_no+r0_no*r1_ni*r2_e2*r3_no*r4_e3+r0_no*r1_ni*r2_e3*r3_e2*r4_no-r0_no*r1_ni*r2_e3*r3_no*r4_e2-r0_no*r1_ni*r2_no*r3_e2*r4_e3+r0_no*r1_ni*r2_no*r3_e3*r4_e2+r0_no*r1_no*r2_e2*r3_e3*r4_ni-r0_no*r1_no*r2_e2*r3_ni*r4_e3-r0_no*r1_no*r2_e3*r3_e2*r4_ni+r0_no*r1_no*r2_e3*r3_ni*r4_e2+r0_no*r1_no*r2_ni*r3_e2*r4_e3-r0_no*r1_no*r2_ni*r3_e3*r4_e2);
	tmp.m_no_e1_e2_e3_ni = (r0_e1*r1_e2*r2_e3*r3_ni*r4_no-r0_e1*r1_e2*r2_e3*r3_no*r4_ni-r0_e1*r1_e2*r2_ni*r3_e3*r4_no+r0_e1*r1_e2*r2_ni*r3_no*r4_e3+r0_e1*r1_e2*r2_no*r3_e3*r4_ni-r0_e1*r1_e2*r2_no*r3_ni*r4_e3-r0_e1*r1_e3*r2_e2*r3_ni*r4_no+r0_e1*r1_e3*r2_e2*r3_no*r4_ni+r0_e1*r1_e3*r2_ni*r3_e2*r4_no-r0_e1*r1_e3*r2_ni*r3_no*r4_e2-r0_e1*r1_e3*r2_no*r3_e2*r4_ni+r0_e1*r1_e3*r2_no*r3_ni*r4_e2+r0_e1*r1_ni*r2_e2*r3_e3*r4_no-r0_e1*r1_ni*r2_e2*r3_no*r4_e3-r0_e1*r1_ni*r2_e3*r3_e2*r4_no+r0_e1*r1_ni*r2_e3*r3_no*r4_e2+r0_e1*r1_ni*r2_no*r3_e2*r4_e3-r0_e1*r1_ni*r2_no*r3_e3*r4_e2-r0_e1*r1_no*r2_e2*r3_e3*r4_ni+r0_e1*r1_no*r2_e2*r3_ni*r4_e3+r0_e1*r1_no*r2_e3*r3_e2*r4_ni-r0_e1*r1_no*r2_e3*r3_ni*r4_e2-r0_e1*r1_no*r2_ni*r3_e2*r4_e3+r0_e1*r1_no*r2_ni*r3_e3*r4_e2-r0_e2*r1_e1*r2_e3*r3_ni*r4_no+r0_e2*r1_e1*r2_e3*r3_no*r4_ni+r0_e2*r1_e1*r2_ni*r3_e3*r4_no-r0_e2*r1_e1*r2_ni*r3_no*r4_e3-r0_e2*r1_e1*r2_no*r3_e3*r4_ni+r0_e2*r1_e1*r2_no*r3_ni*r4_e3+r0_e2*r1_e3*r2_e1*r3_ni*r4_no-r0_e2*r1_e3*r2_e1*r3_no*r4_ni-r0_e2*r1_e3*r2_ni*r3_e1*r4_no+r0_e2*r1_e3*r2_ni*r3_no*r4_e1+r0_e2*r1_e3*r2_no*r3_e1*r4_ni-r0_e2*r1_e3*r2_no*r3_ni*r4_e1-r0_e2*r1_ni*r2_e1*r3_e3*r4_no+r0_e2*r1_ni*r2_e1*r3_no*r4_e3+r0_e2*r1_ni*r2_e3*r3_e1*r4_no-r0_e2*r1_ni*r2_e3*r3_no*r4_e1-r0_e2*r1_ni*r2_no*r3_e1*r4_e3+r0_e2*r1_ni*r2_no*r3_e3*r4_e1+r0_e2*r1_no*r2_e1*r3_e3*r4_ni-r0_e2*r1_no*r2_e1*r3_ni*r4_e3-r0_e2*r1_no*r2_e3*r3_e1*r4_ni+r0_e2*r1_no*r2_e3*r3_ni*r4_e1+r0_e2*r1_no*r2_ni*r3_e1*r4_e3-r0_e2*r1_no*r2_ni*r3_e3*r4_e1+r0_e3*r1_e1*r2_e2*r3_ni*r4_no-r0_e3*r1_e1*r2_e2*r3_no*r4_ni-r0_e3*r1_e1*r2_ni*r3_e2*r4_no+r0_e3*r1_e1*r2_ni*r3_no*r4_e2+r0_e3*r1_e1*r2_no*r3_e2*r4_ni-r0_e3*r1_e1*r2_no*r3_ni*r4_e2-r0_e3*r1_e2*r2_e1*r3_ni*r4_no+r0_e3*r1_e2*r2_e1*r3_no*r4_ni+r0_e3*r1_e2*r2_ni*r3_e1*r4_no-r0_e3*r1_e2*r2_ni*r3_no*r4_e1-r0_e3*r1_e2*r2_no*r3_e1*r4_ni+r0_e3*r1_e2*r2_no*r3_ni*r4_e1+r0_e3*r1_ni*r2_e1*r3_e2*r4_no-r0_e3*r1_ni*r2_e1*r3_no*r4_e2-r0_e3*r1_ni*r2_e2*r3_e1*r4_no+r0_e3*r1_ni*r2_e2*r3_no*r4_e1+r0_e3*r1_ni*r2_no*r3_e1*r4_e2-r0_e3*r1_ni*r2_no*r3_e2*r4_e1-r0_e3*r1_no*r2_e1*r3_e2*r4_ni+r0_e3*r1_no*r2_e1*r3_ni*r4_e2+r0_e3*r1_no*r2_e2*r3_e1*r4_ni-r0_e3*r1_no*r2_e2*r3_ni*r4_e1-r0_e3*r1_no*r2_ni*r3_e1*r4_e2+r0_e3*r1_no*r2_ni*r3_e2*r4_e1-r0_ni*r1_e1*r2_e2*r3_e3*r4_no+r0_ni*r1_e1*r2_e2*r3_no*r4_e3+r0_ni*r1_e1*r2_e3*r3_e2*r4_no-r0_ni*r1_e1*r2_e3*r3_no*r4_e2-r0_ni*r1_e1*r2_no*r3_e2*r4_e3+r0_ni*r1_e1*r2_no*r3_e3*r4_e2+r0_ni*r1_e2*r2_e1*r3_e3*r4_no-r0_ni*r1_e2*r2_e1*r3_no*r4_e3-r0_ni*r1_e2*r2_e3*r3_e1*r4_no+r0_ni*r1_e2*r2_e3*r3_no*r4_e1+r0_ni*r1_e2*r2_no*r3_e1*r4_e3-r0_ni*r1_e2*r2_no*r3_e3*r4_e1-r0_ni*r1_e3*r2_e1*r3_e2*r4_no+r0_ni*r1_e3*r2_e1*r3_no*r4_e2+r0_ni*r1_e3*r2_e2*r3_e1*r4_no-r0_ni*r1_e3*r2_e2*r3_no*r4_e1-r0_ni*r1_e3*r2_no*r3_e1*r4_e2+r0_ni*r1_e3*r2_no*r3_e2*r4_e1+r0_ni*r1_no*r2_e1*r3_e2*r4_e3-r0_ni*r1_no*r2_e1*r3_e3*r4_e2-r0_ni*r1_no*r2_e2*r3_e1*r4_e3+r0_ni*r1_no*r2_e2*r3_e3*r4_e1+r0_ni*r1_no*r2_e3*r3_e1*r4_e2-r0_ni*r1_no*r2_e3*r3_e2*r4_e1+r0_no*r1_e1*r2_e2*r3_e3*r4_ni-r0_no*r1_e1*r2_e2*r3_ni*r4_e3-r0_no*r1_e1*r2_e3*r3_e2*r4_ni+r0_no*r1_e1*r2_e3*r3_ni*r4_e2+r0_no*r1_e1*r2_ni*r3_e2*r4_e3-r0_no*r1_e1*r2_ni*r3_e3*r4_e2-r0_no*r1_e2*r2_e1*r3_e3*r4_ni+r0_no*r1_e2*r2_e1*r3_ni*r4_e3+r0_no*r1_e2*r2_e3*r3_e1*r4_ni-r0_no*r1_e2*r2_e3*r3_ni*r4_e1-r0_no*r1_e2*r2_ni*r3_e1*r4_e3+r0_no*r1_e2*r2_ni*r3_e3*r4_e1+r0_no*r1_e3*r2_e1*r3_e2*r4_ni-r0_no*r1_e3*r2_e1*r3_ni*r4_e2-r0_no*r1_e3*r2_e2*r3_e1*r4_ni+r0_no*r1_e3*r2_e2*r3_ni*r4_e1+r0_no*r1_e3*r2_ni*r3_e1*r4_e2-r0_no*r1_e3*r2_ni*r3_e2*r4_e1-r0_no*r1_ni*r2_e1*r3_e2*r4_e3+r0_no*r1_ni*r2_e1*r3_e3*r4_e2+r0_no*r1_ni*r2_e2*r3_e1*r4_e3-r0_no*r1_ni*r2_e2*r3_e3*r4_e1-r0_no*r1_ni*r2_e3*r3_e1*r4_e2+r0_no*r1_ni*r2_e3*r3_e2*r4_e1);

	n = norm_em_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = false;
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_oddVersor_dont_mangle_205_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_oddVersor_dont_mangle_205_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			mul*tmp.m_no, // no
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3, // e3
			mul*tmp.m_ni, // ni
			mul*tmp.m_no_e1_e2, // no_e1_e2
			mul*tmp.m_no_e1_e3, // no_e1_e3
			mul*tmp.m_no_e2_e3, // no_e2_e3
			mul*tmp.m_e1_e2_e3, // e1_e2_e3
			mul*tmp.m_no_e1_ni, // no_e1_ni
			mul*tmp.m_no_e2_ni, // no_e2_ni
			mul*tmp.m_e1_e2_ni, // e1_e2_ni
			mul*tmp.m_no_e3_ni, // no_e3_ni
			mul*tmp.m_e1_e3_ni, // e1_e3_ni
			mul*tmp.m_e2_e3_ni, // e2_e3_ni
			mul*tmp.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);
}
/**
 * Returns random oddVersor with a scale in the interval [0, scale)
 */
public final static oddVersor random_oddVersor_dont_mangle_205(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_oddVersor_dont_mangle_205_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random vectorE3GA with a scale in the interval [0, scale)
 */
public final static vectorE3GA random_vectorE3GA_dont_mangle_206_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	vectorE3GA tmp = new vectorE3GA();
	double n, mul, lc;
	boolean nullBlade;
	double re1 = -1.0 + 2.0 * genrand(), re2 = -1.0 + 2.0 * genrand(), re3 = -1.0 + 2.0 * genrand();
	tmp.m_e1 = re1;
	tmp.m_e2 = re2;
	tmp.m_e3 = re3;

	n = norm_em_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_vectorE3GA_dont_mangle_206_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_vectorE3GA_dont_mangle_206_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			mul*tmp.m_e1, // e1
			mul*tmp.m_e2, // e2
			mul*tmp.m_e3 // e3
		);
}
/**
 * Returns random vectorE3GA with a scale in the interval [0, scale)
 */
public final static vectorE3GA random_vectorE3GA_dont_mangle_206(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_vectorE3GA_dont_mangle_206_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns versor inverse of a using euclidean metric.
 */
public final static oddVersor versorInverse_dont_mangle_207(final oddVersor a)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2*a.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3*a.m_e3+a.m_ni*a.m_ni+a.m_no*a.m_no+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni);

	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no/((_n2_)), // no
			a.m_e1/((_n2_)), // e1
			a.m_e2/((_n2_)), // e2
			a.m_e3/((_n2_)), // e3
			a.m_ni/((_n2_)), // ni
			-a.m_no_e1_e2/((_n2_)), // no_e1_e2
			-a.m_no_e1_e3/((_n2_)), // no_e1_e3
			-a.m_no_e2_e3/((_n2_)), // no_e2_e3
			-a.m_e1_e2_e3/((_n2_)), // e1_e2_e3
			-a.m_no_e1_ni/((_n2_)), // no_e1_ni
			-a.m_no_e2_ni/((_n2_)), // no_e2_ni
			-a.m_e1_e2_ni/((_n2_)), // e1_e2_ni
			-a.m_no_e3_ni/((_n2_)), // no_e3_ni
			-a.m_e1_e3_ni/((_n2_)), // e1_e3_ni
			-a.m_e2_e3_ni/((_n2_)), // e2_e3_ni
			a.m_no_e1_e2_e3_ni/((_n2_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns random I3_t with a scale in the interval [0, scale)
 */
public final static I3_t random_I3_t_dont_mangle_257_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new I3_t(		);
}
/**
 * Returns random I3_t with a scale in the interval [0, scale)
 */
public final static I3_t random_I3_t_dont_mangle_257(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_I3_t_dont_mangle_257_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random pointPair with a scale in the interval [0, scale)
 */
public final static pointPair random_pointPair_dont_mangle_273_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	pointPair tmp = new pointPair();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_no_e1 = (-r0_e1*r1_no+r0_no*r1_e1);
	tmp.m_no_e2 = (-r0_e2*r1_no+r0_no*r1_e2);
	tmp.m_no_e3 = (-r0_e3*r1_no+r0_no*r1_e3);
	tmp.m_e1_e2 = (r0_e1*r1_e2-r0_e2*r1_e1);
	tmp.m_e2_e3 = (r0_e2*r1_e3-r0_e3*r1_e2);
	tmp.m_e3_e1 = -(r0_e1*r1_e3-r0_e3*r1_e1);
	tmp.m_e1_ni = (r0_e1*r1_ni-r0_ni*r1_e1);
	tmp.m_e2_ni = (r0_e2*r1_ni-r0_ni*r1_e2);
	tmp.m_e3_ni = (r0_e3*r1_ni-r0_ni*r1_e3);
	tmp.m_no_ni = (-r0_ni*r1_no+r0_no*r1_ni);

	n = norm_dont_mangle_759_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_pointPair_dont_mangle_273_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_pointPair_dont_mangle_273_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			mul*tmp.m_no_e1, // no_e1
			mul*tmp.m_no_e2, // no_e2
			mul*tmp.m_no_e3, // no_e3
			mul*tmp.m_e1_e2, // e1_e2
			mul*tmp.m_e2_e3, // e2_e3
			mul*tmp.m_e3_e1, // e3_e1
			mul*tmp.m_e1_ni, // e1_ni
			mul*tmp.m_e2_ni, // e2_ni
			mul*tmp.m_e3_ni, // e3_ni
			mul*tmp.m_no_ni // no_ni
		);
}
/**
 * Returns random pointPair with a scale in the interval [0, scale)
 */
public final static pointPair random_pointPair_dont_mangle_273(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_pointPair_dont_mangle_273_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns undual of mv using euclidean metric.
 */
public final static mv undual_dont_mangle_277_returns_mv(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (cc[5] == null) cc[5] = new double[1];
		undual_euclidean_0_5(ac[0], cc[5]);
	}
	
	if (ac[1] != null) {
		if (cc[4] == null) cc[4] = new double[5];
		undual_euclidean_1_4(ac[1], cc[4]);
	}
	
	if (ac[2] != null) {
		if (cc[3] == null) cc[3] = new double[10];
		undual_euclidean_2_3(ac[2], cc[3]);
	}
	
	if (ac[3] != null) {
		if (cc[2] == null) cc[2] = new double[10];
		undual_euclidean_3_2(ac[3], cc[2]);
	}
	
	if (ac[4] != null) {
		if (cc[1] == null) cc[1] = new double[5];
		undual_euclidean_4_1(ac[4], cc[1]);
	}
	
	if (ac[5] != null) {
		if (cc[0] == null) cc[0] = new double[1];
		undual_euclidean_5_0(ac[5], cc[0]);
	}
	
	return new mv(cc);
}
/**
 * Returns dual of mv using euclidean metric.
 */
public final static mv dual_dont_mangle_279_returns_mv(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (cc[5] == null) cc[5] = new double[1];
		dual_euclidean_0_5(ac[0], cc[5]);
	}
	
	if (ac[1] != null) {
		if (cc[4] == null) cc[4] = new double[5];
		dual_euclidean_1_4(ac[1], cc[4]);
	}
	
	if (ac[2] != null) {
		if (cc[3] == null) cc[3] = new double[10];
		dual_euclidean_2_3(ac[2], cc[3]);
	}
	
	if (ac[3] != null) {
		if (cc[2] == null) cc[2] = new double[10];
		dual_euclidean_3_2(ac[3], cc[2]);
	}
	
	if (ac[4] != null) {
		if (cc[1] == null) cc[1] = new double[5];
		dual_euclidean_4_1(ac[4], cc[1]);
	}
	
	if (ac[5] != null) {
		if (cc[0] == null) cc[0] = new double[1];
		dual_euclidean_5_0(ac[5], cc[0]);
	}
	
	return new mv(cc);
}
/**
 * Returns random e3_t with a scale in the interval [0, scale)
 */
public final static e3_t random_e3_t_dont_mangle_330_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	return new e3_t(		);
}
/**
 * Returns random e3_t with a scale in the interval [0, scale)
 */
public final static e3_t random_e3_t_dont_mangle_330(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_e3_t_dont_mangle_330_ex(scale, minimumNorm, scale * largestCoordinate);
}


/**
 * Generates a random versor.
 * The scale is uniformly distributed over the interval [0 1).
 * The maximum non-zero grade-part is 'grade'.
 * 
 * Only the basis vectors marked in 'basisVectorBitmap' will be used to generate the versor/blade.
 * Use 'basisVectorBitmap = -1' (the default) to use all basisvectors.
 * @return random_versor_dont_mangle_371_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_versor_dont_mangle_371_returns_mv(double scale, int grade, int basisVectorBitmap) {
	double minimumNorm = 0.01;
	double largestCoordinate = 4.0;
	return random_versor_dont_mangle_371_returns_mv_ex(scale, grade, basisVectorBitmap, minimumNorm, scale * largestCoordinate);
}

/**
 * This version of random_versor_dont_mangle_371_returns_mv() has extra arguments which help to avoid
 * near-singular blades / versors.
 * 
 * Near-singular blades / versors are avoid by testing the norm and largest coordinate
 * of the random blade / versor. If the test does not pass, the function recursively
 * tries to generate another random blade / versor.
 * 
 * 'minimumNorm' is the minimum allowed norm of the blade/versor before scaling. 
 * 'minimumNorm' must be > 0.0 for versors.
 * 
 * 'largestCoordinate' is the largest coordinate allowed after scaling.
 * 
 * @return random_versor_dont_mangle_371_returns_mv_ex(arg1, scale, grade, basisVectorBitmap, 0.01, scale * 4.0)
 */
public static mv random_versor_dont_mangle_371_returns_mv_ex(double scale, int _grade, int basisVectorBitmap, 
		double minimumNorm, double largestCoordinate) 
{
	mv randomVector = new mv();
	//, tmp1, tmp2;
	double[] randomValues = new double[5];
	//double n2, mul;
	int grade = _grade;
	
	// set IR (intermediate result) to 1
	mv IR = new mv (1.0);

	while (grade > 0) {	// loop until grade == 0
		// fill array with random values
		for (int i = 0; i < 5; i++) 
			randomValues[i] = ((basisVectorBitmap & (1 << i)) == 0)
				? 0.0 
				: (-1.0 + 2.0 * genrand());
		
		// make it a multivector:
		randomVector.set(GroupBitmap.GRADE_1, randomValues);
		
		// multiply 
		IR = gp_em(IR, randomVector);
		
		// lower grade
		grade--;
	}
	
	// compute norm/multiplier, apply it, or recurse if we happened to create a near-null versor
	double n2 = norm_em_returns_scalar(IR);
	if ((double)Math.abs(n2) > minimumNorm * minimumNorm) {
		if (n2 != 0.0) {
			double mul = scale * genrand() / n2;
			if (IR.largestCoordinate() * mul < largestCoordinate)
				return gp(IR, mul);
		}
		else if (IR.largestCoordinate() < largestCoordinate)
			return IR;
	}
	
	// try again:
	return random_versor_dont_mangle_371_returns_mv_ex(scale, _grade, basisVectorBitmap, minimumNorm, largestCoordinate); 
}
/**
 * Returns random pseudoscalar with a scale in the interval [0, scale)
 */
public final static pseudoscalar random_pseudoscalar_dont_mangle_396_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	pseudoscalar tmp = new pseudoscalar();
	double n, mul, lc;
	boolean nullBlade;
	double rno_e1_e2_e3_ni = -1.0 + 2.0 * genrand();
	tmp.m_no_e1_e2_e3_ni = rno_e1_e2_e3_ni;

	n = norm_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_pseudoscalar_dont_mangle_396_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_pseudoscalar_dont_mangle_396_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			mul*tmp.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);
}
/**
 * Returns random pseudoscalar with a scale in the interval [0, scale)
 */
public final static pseudoscalar random_pseudoscalar_dont_mangle_396(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_pseudoscalar_dont_mangle_396_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random freeVector with a scale in the interval [0, scale)
 */
public final static freeVector random_freeVector_dont_mangle_480_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	freeVector tmp = new freeVector();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_ni = (r0_e1*r1_ni-r0_ni*r1_e1);
	tmp.m_e2_ni = (r0_e2*r1_ni-r0_ni*r1_e2);
	tmp.m_e3_ni = (r0_e3*r1_ni-r0_ni*r1_e3);

	n = norm_dont_mangle_761_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_freeVector_dont_mangle_480_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_freeVector_dont_mangle_480_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			mul*tmp.m_e1_ni, // e1_ni
			mul*tmp.m_e2_ni, // e2_ni
			mul*tmp.m_e3_ni // e3_ni
		);
}
/**
 * Returns random freeVector with a scale in the interval [0, scale)
 */
public final static freeVector random_freeVector_dont_mangle_480(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_freeVector_dont_mangle_480_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns random freeBivector with a scale in the interval [0, scale)
 */
public final static freeBivector random_freeBivector_dont_mangle_588_ex(final double scale, final double minimumNorm, final double largestCoordinate)
{
	freeBivector tmp = new freeBivector();
	double n, mul, lc;
	boolean nullBlade;
	double r0_no = -1.0 + 2.0 * genrand(), r0_e1 = -1.0 + 2.0 * genrand(), r0_e2 = -1.0 + 2.0 * genrand(), r0_e3 = -1.0 + 2.0 * genrand(), r0_ni = -1.0 + 2.0 * genrand(), 
			r1_no = -1.0 + 2.0 * genrand(), r1_e1 = -1.0 + 2.0 * genrand(), r1_e2 = -1.0 + 2.0 * genrand(), r1_e3 = -1.0 + 2.0 * genrand(), r1_ni = -1.0 + 2.0 * genrand(), 
			r2_no = -1.0 + 2.0 * genrand(), r2_e1 = -1.0 + 2.0 * genrand(), r2_e2 = -1.0 + 2.0 * genrand(), r2_e3 = -1.0 + 2.0 * genrand(), r2_ni = -1.0 + 2.0 * genrand();
	tmp.m_e1_e2_ni = (r0_e1*r1_e2*r2_ni-r0_e1*r1_ni*r2_e2-r0_e2*r1_e1*r2_ni+r0_e2*r1_ni*r2_e1+r0_ni*r1_e1*r2_e2-r0_ni*r1_e2*r2_e1);
	tmp.m_e2_e3_ni = (r0_e2*r1_e3*r2_ni-r0_e2*r1_ni*r2_e3-r0_e3*r1_e2*r2_ni+r0_e3*r1_ni*r2_e2+r0_ni*r1_e2*r2_e3-r0_ni*r1_e3*r2_e2);
	tmp.m_e3_e1_ni = -(r0_e1*r1_e3*r2_ni-r0_e1*r1_ni*r2_e3-r0_e3*r1_e1*r2_ni+r0_e3*r1_ni*r2_e1+r0_ni*r1_e1*r2_e3-r0_ni*r1_e3*r2_e1);

	n = norm_dont_mangle_762_returns_scalar(tmp);
	lc = tmp.largestCoordinate();
	nullBlade = ((n == 0.0) && (lc != 0.0));
	if ((n < minimumNorm) && (!nullBlade)) {
		return random_freeBivector_dont_mangle_588_ex(scale, minimumNorm, largestCoordinate);
	}
	if (n < 0.0001) {
		mul = 1.0;
	}
	else {
		mul = scale * (-1.0 + 2.0 * genrand()) / n;
		if ((lc * Math.abs(mul)) > largestCoordinate ) {
			return random_freeBivector_dont_mangle_588_ex(scale, minimumNorm, largestCoordinate);
		}
	}
	return new freeBivector(freeBivector.coord_e1e2ni_e2e3ni_e3e1ni,
			mul*tmp.m_e1_e2_ni, // e1_e2_ni
			mul*tmp.m_e2_e3_ni, // e2_e3_ni
			mul*tmp.m_e3_e1_ni // e3_e1_ni
		);
}
/**
 * Returns random freeBivector with a scale in the interval [0, scale)
 */
public final static freeBivector random_freeBivector_dont_mangle_588(final double scale)
{
	double minimumNorm = 0.0001;
	double largestCoordinate = 4.0;
	return random_freeBivector_dont_mangle_588_ex(scale, minimumNorm, scale * largestCoordinate);
}
/**
 * Returns scalar product of mv and mv.
 */
public final static double sp_dont_mangle_719_returns_mv(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	cc[0] = new double[1];
	if (ac[0] != null) {
		if (bc[0] != null) {
			gp_euclidean_0_0_0(ac[0], bc[0], cc[0]);
		}
	}
	if (ac[1] != null) {
		if (bc[1] != null) {
			gp_euclidean_1_1_0(ac[1], bc[1], cc[0]);
		}
	}
	if (ac[2] != null) {
		if (bc[2] != null) {
			gp_euclidean_2_2_0(ac[2], bc[2], cc[0]);
		}
	}
	if (ac[3] != null) {
		if (bc[3] != null) {
			gp_euclidean_3_3_0(ac[3], bc[3], cc[0]);
		}
	}
	if (ac[4] != null) {
		if (bc[4] != null) {
			gp_euclidean_4_4_0(ac[4], bc[4], cc[0]);
		}
	}
	if (ac[5] != null) {
		if (bc[5] != null) {
			gp_euclidean_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return cc[0][0];
}
/**
 * Returns norm of circle using default metric.
 */
public final static double norm_dont_mangle_750(final circle a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_750_returns_scalar(final circle a) {
	return norm_dont_mangle_750(a);
}
/**
 * Returns norm of e2_t using default metric.
 */
public final static double norm_dont_mangle_751(final e2_t a)
{
	return Math.abs(1.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_751_returns_scalar(final e2_t a) {
	return norm_dont_mangle_751(a);
}
/**
 * Returns norm of I5_t using default metric.
 */
public final static double norm_dont_mangle_752(final I5_t a)
{
	return Math.abs(-1.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_752_returns_scalar(final I5_t a) {
	return norm_dont_mangle_752(a);
}
/**
 * Returns norm of sphere using default metric.
 */
public final static double norm_dont_mangle_753(final sphere a)
{
	return Math.abs(Math.sqrt(Math.abs((2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_753_returns_scalar(final sphere a) {
	return norm_dont_mangle_753(a);
}
/**
 * Returns norm of no_t using default metric.
 */
public final static double norm_dont_mangle_754(final no_t a)
{
	return Math.abs(0.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_754_returns_scalar(final no_t a) {
	return norm_dont_mangle_754(a);
}
/**
 * Returns norm of flatPoint using default metric.
 */
public final static double norm_dont_mangle_755(final flatPoint a)
{
	return Math.abs(Math.sqrt(Math.abs(-a.m_no_ni*a.m_no_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_755_returns_scalar(final flatPoint a) {
	return norm_dont_mangle_755(a);
}
/**
 * Returns norm of ni_t using default metric.
 */
public final static double norm_dont_mangle_756(final ni_t a)
{
	return Math.abs(0.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_756_returns_scalar(final ni_t a) {
	return norm_dont_mangle_756(a);
}
/**
 * Returns norm of normalizedPoint using euclidean metric.
 */
public final static double norm_dont_mangle_757(final normalizedPoint a)
{
	return Math.abs(Math.sqrt((1.0+a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+a.m_ni*a.m_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_757_returns_scalar(final normalizedPoint a) {
	return norm_dont_mangle_757(a);
}
/**
 * Returns norm of I3_t using default metric.
 */
public final static double norm_dont_mangle_758(final I3_t a)
{
	return Math.abs(1.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_758_returns_scalar(final I3_t a) {
	return norm_dont_mangle_758(a);
}
/**
 * Returns norm of pointPair using default metric.
 */
public final static double norm_dont_mangle_759(final pointPair a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_ni*a.m_no_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_759_returns_scalar(final pointPair a) {
	return norm_dont_mangle_759(a);
}
/**
 * Returns norm of e3_t using default metric.
 */
public final static double norm_dont_mangle_760(final e3_t a)
{
	return Math.abs(1.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_760_returns_scalar(final e3_t a) {
	return norm_dont_mangle_760(a);
}
/**
 * Returns norm of freeVector using default metric.
 */
public final static double norm_dont_mangle_761(final freeVector a)
{
	return Math.abs(0.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_761_returns_scalar(final freeVector a) {
	return norm_dont_mangle_761(a);
}
/**
 * Returns norm of freeBivector using default metric.
 */
public final static double norm_dont_mangle_762(final freeBivector a)
{
	return Math.abs(0.0);

}
/**
 * internal conversion function
 */
public final static double norm_dont_mangle_762_returns_scalar(final freeBivector a) {
	return norm_dont_mangle_762(a);
}
// Testing code declarations:
// Testing code inline definitions:
// Testing code definitions:

static int test_metric_default_mv(int NB_TESTS_SCALER) 
{
	int i, j;
	double[] arr = new double[5];
	double dif;
	mv A;
	mv[] bv = new mv[5];
	double[] M = new double[]{
		0.0, 0.0, 0.0, 0.0, -1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0, 0.0, 0.0, 0.0, 0.0	}; // metric matrix

	// get all basis vectors

	c3ga.zero_5(arr);
	arr[0] = 1.0;
	bv[0] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[1] = 1.0;
	bv[1] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[2] = 1.0;
	bv[2] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[3] = 1.0;
	bv[3] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[4] = 1.0;
	bv[4] = new mv(GroupBitmap.GROUP_1, arr);

	for (i = 0; i < 5; i++) {
		for (j = 0; j < 5; j++) {
			A = gp(bv[i], bv[j]);
			dif = M[i * 5 + j] - A.get_scalar();
			if ((dif < -1E-14) || (dif > 1E-14)) {
				Console.WriteLine("test_metric_default_mv() test failed for " + BasisVectorNames[i] + " " + BasisVectorNames[j]);
				return 0;
			}
		}
	}
	
	return 1;
}

static int test_metric_euclidean_mv(int NB_TESTS_SCALER) 
{
	int i, j;
	double[] arr = new double[5];
	double dif;
	mv A;
	mv[] bv = new mv[5];
	double[] M = new double[]{
		1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0	}; // metric matrix

	// get all basis vectors

	c3ga.zero_5(arr);
	arr[0] = 1.0;
	bv[0] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[1] = 1.0;
	bv[1] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[2] = 1.0;
	bv[2] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[3] = 1.0;
	bv[3] = new mv(GroupBitmap.GROUP_1, arr);

	c3ga.zero_5(arr);
	arr[4] = 1.0;
	bv[4] = new mv(GroupBitmap.GROUP_1, arr);

	for (i = 0; i < 5; i++) {
		for (j = 0; j < 5; j++) {
			A = gp_em(bv[i], bv[j]);
			dif = M[i * 5 + j] - A.get_scalar();
			if ((dif < -1E-14) || (dif > 1E-14)) {
				Console.WriteLine("test_metric_euclidean_mv() test failed for " + BasisVectorNames[i] + " " + BasisVectorNames[j]);
				return 0;
			}
		}
	}
	
	return 1;
}

static int test_parse_mv(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 128;
	mv A, B, C;
	String str;
	
	int i, basisVectorBitmap = -1;

	for (i = 0; i < NB_LOOPS; i++) {
		A = random_versor_dont_mangle_6_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		str = A.toString(
			"%2.20e"
		
		);
		
		try {
			B = parse(str);
		} catch (ParseException ex) {
			Console.WriteLine("parse() test failed: " + ex.toString());
			return 0; // failure
		}

		C = subtract(A, B);

		if (C.largestCoordinate() > 1E-14) {
			Console.WriteLine("parse() test failed (largest coordinate: " + C.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	
	return 1; // success
}

static int test_genrand_double(int NB_TESTS_SCALER) 
{
	int NB_BINS = 256;
	int NB_LOOPS = NB_BINS * 1024;
	int[] bins = new int[256];
	double avg = 0.0;
	double r;
	int idx, i;
	
	// generate a lot of random values, compute average (should be 0.5) and fill bins
	for (i = 0; i < NB_LOOPS; i++) {
		r = genrand();
		avg += r;
		idx = (int)(r * (double)NB_BINS);
		if (idx >= NB_BINS) idx = NB_BINS-1;
		bins[idx]++;
	}
	avg /= (double)NB_LOOPS;
	
	if ((avg < 0.49) || (avg > 0.51)) {
		Console.WriteLine("Random number generator genrand() likely failed: average is " + (double)avg + " instead of 0.5.");
		return 0; // failure
	}
	
	for (i = 0; i < NB_BINS; i++) {
		if ((bins[i] < (6 * NB_LOOPS / NB_BINS / 8)) ||
			(bins[i] > (10 * NB_LOOPS / NB_BINS / 8))) {
			Console.WriteLine("It is very likely that the random number generator genrand() failed:");
			Console.WriteLine("The distribution is not uniform (bin " + i + " of " + NB_BINS + ", value=" + bins[i] + ", expected value=" + (NB_LOOPS / NB_BINS) + ")");
			return 0; // failure
		}
	}
	
	return 1; // success
}

static int test_cgaPoint_dont_mangle_763(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	vectorE3GA V;
	normalizedPoint pt;
	double f;
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		V = random_vectorE3GA_dont_mangle_1(genrand());
		pt = cgaPoint(V);
		
		// check if null vector (todo: what if user overrides the scalar product to return an SMV type?)
		f = sp_dont_mangle_3(pt, pt);
		if ((double)Math.abs(f) > 1E-14) {
			Console.WriteLine("cgaPoint() test failed\n");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_cgaPoint_dont_mangle_765(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint pt;
	double f;
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		pt = cgaPoint(genrand(), genrand(), genrand());
		
		// check if null vector (todo: what if user overrides the scalar product to return an SMV type?)
		f = sp_dont_mangle_3(pt, pt);
		if ((double)Math.abs(f) > 1E-14) {
			Console.WriteLine("cgaPoint() test failed\n");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_randomCgaPoint_dont_mangle_766(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint pt;
	double f;
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		pt = randomCgaPoint(genrand());
		
		// check if null vector (todo: what if user overrides the scalar product to return an SMV type?)
		f = sp_dont_mangle_3(pt, pt);
		if ((double)Math.abs(f) > 1E-14) {
			Console.WriteLine("randomCgaPoint() test failed\n");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_cgaPointDistance_dont_mangle_767(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	double[] C1 = new double[3], C2 = new double[3];
	normalizedPoint pt1, pt2;
	normalizedPoint argPt1;
	normalizedPoint argPt2;
	double d1, d2, dif;
	int i, j;
	mv tmp;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random coordinate for point, and compute distance 'd1' of points
		d1 = 0.0;
		for (j = 0; j < 3; j++) {
			C1[j] = genrand();
			C2[j] = genrand();
			d1 += (C1[j] - C2[j]) * (C1[j] - C2[j]);
		}
		d1 = Math.sqrt(d1);
		
		// initialize points
		pt1 = cgaPoint(C1[0], C1[1], C1[2]);
		pt2 = cgaPoint(C2[0], C2[1], C2[2]);

		// convert points to the argument types of the distance function (this is usually not required, but doesn't hurt to do so anyway)
		tmp = new mv(pt1);
		argPt1 = new normalizedPoint(tmp);

		tmp = new mv(pt2);
		argPt2 = new normalizedPoint(tmp);

		// compute distance of points using cgaPointDistance()
		d2 = cgaPointDistance(argPt1, argPt2);
	
		// compare results
		dif = Math.abs(d1 - d2);
		if (dif > 1E-13) {
			Console.WriteLine("cgaPointDistance() test failed (dif = " + (double)dif + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_cgaPointDistance_dont_mangle_764(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	double[] C1 = new double[3], C2 = new double[3];
	normalizedPoint pt1, pt2;
	dualSphere argPt1;
	dualSphere argPt2;
	double d1, d2, dif;
	int i, j;
	mv tmp;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random coordinate for point, and compute distance 'd1' of points
		d1 = 0.0;
		for (j = 0; j < 3; j++) {
			C1[j] = genrand();
			C2[j] = genrand();
			d1 += (C1[j] - C2[j]) * (C1[j] - C2[j]);
		}
		d1 = Math.sqrt(d1);
		
		// initialize points
		pt1 = cgaPoint(C1[0], C1[1], C1[2]);
		pt2 = cgaPoint(C2[0], C2[1], C2[2]);

		// convert points to the argument types of the distance function (this is usually not required, but doesn't hurt to do so anyway)
		tmp = new mv(pt1);
		argPt1 = new dualSphere(tmp);

		tmp = new mv(pt2);
		argPt2 = new dualSphere(tmp);

		// compute distance of points using cgaPointDistance()
		d2 = cgaPointDistance(argPt1, argPt2);
	
		// compare results
		dif = Math.abs(d1 - d2);
		if (dif > 1E-13) {
			Console.WriteLine("cgaPointDistance() test failed (dif = " + (double)dif + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_add_dont_mangle_768(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C;
	int i, g;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);
		
		// B = -A
		B = negate(A);
		
		C = add(A, B);
		
		// check
		if (C.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}
		
	}
	return 1; // success
}

static int test_add_dont_mangle_777(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	vectorE3GA A;
	vectorE3GA B;
	vectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_773(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	vectorE3GA A;
	normalizedPoint B;
	normalizedPoint C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_normalizedPoint_dont_mangle_10(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_772(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	vectorE3GA A;
	dualSphere B;
	dualSphere C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_dualSphere_dont_mangle_12(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_770(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	bivectorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_769(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	plane A;
	plane B;
	plane C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_plane_dont_mangle_15(s);
		B = random_plane_dont_mangle_15(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_771(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	line A;
	circle B;
	circle C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_line_dont_mangle_17(s);
		B = random_circle_dont_mangle_18(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_774(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 13;
	circle A;
	vectorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_circle_dont_mangle_18(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_775(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e1_t A;
	e2_t B;
	vectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_e1_t_dont_mangle_21(s);
		B = random_e2_t_dont_mangle_22(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_add_dont_mangle_776(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 11;
	I5_t A;
	circle B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_I5_t_dont_mangle_23(s);
		B = random_circle_dont_mangle_18(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_778(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C;
	int i, g;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);
		
		B = A;
		
		C = subtract(A, B);
		
		// check
		if (C.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}
		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_779(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	vectorE3GA A;
	vectorE3GA B;
	vectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_780(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	bivectorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_781(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	oddVersor A;
	oddVersor B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_oddVersor_dont_mangle_30(s);
		B = random_oddVersor_dont_mangle_30(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_782(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 9;
	line A;
	vectorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_line_dont_mangle_17(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_783(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	rotorE3GA A;
	rotorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_784(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	rotorE3GA A;
	noni_t B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_noni_t_dont_mangle_37(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_785(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 11;
	I5_t A;
	circle B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_I5_t_dont_mangle_23(s);
		B = random_circle_dont_mangle_18(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_applyOM_dont_mangle_786(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	double[] OMmatrix = new double[5 * 5];
	int i, d, d2;
	int nbRandomVectors;
	om randomOM;
	mv[] randomVectors = new mv[5];
	mv[] OMrandomVectors = new mv[5];
	mv op1, op2, dif;
	int basisVectorBitmap = -1;
	int vectorGrade = 1;

	for (i = 0; i < NB_LOOPS; i++) {
		// init random outermorphism matrix
		for (d2 = 0; d2 < 5 * 5; d2++)
			OMmatrix[d2] = genrand();
		
		// init random OM
		randomOM = new om(OMmatrix);
		
		// get n < 5 random vectors stored in GMV
		nbRandomVectors = (int)(5.0 * genrand());
		if (nbRandomVectors < 1) nbRandomVectors = 1;
		for (d = 0; d < nbRandomVectors; d++) {
			randomVectors[d] = random_blade_dont_mangle_40_returns_mv(genrand(), vectorGrade, basisVectorBitmap);
			OMrandomVectors[d] = applyOM(randomOM, randomVectors[d]);
		}
		
		// compute outer product of randomVectors, OMrandomVectors
		op1 = randomVectors[0];
		op2 = OMrandomVectors[0];
		for (d = 1; d < nbRandomVectors; d++) {
			op1 = op(op1, randomVectors[d]);
			op2 = op(op2, OMrandomVectors[d]);
		}
		
		// apply OM to op1
		op1 = applyOM(randomOM, op1);
		
		// test equality
		dif = subtract(op1, op2);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_787(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	om testOM;
	normalizedPoint randomDomainSmv;
	dualSphere rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new om(refOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_normalizedPoint_dont_mangle_10(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new dualSphere(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_788(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	om testOM;
	circle randomDomainSmv;
	circle rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new om(refOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_circle_dont_mangle_18(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new circle(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_789(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	om testOM;
	sphere randomDomainSmv;
	sphere rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new om(refOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_sphere_dont_mangle_46(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new sphere(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_790(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	grade1OM_E3GA testOM;
	vectorE3GA randomDomainSmv;
	vectorE3GA rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new grade1OM_E3GA(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_vectorE3GA_dont_mangle_1(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new vectorE3GA(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_791(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	grade1OM_E3GA testOM;
	e1_t randomDomainSmv;
	vectorE3GA rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new grade1OM_E3GA(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_e1_t_dont_mangle_21(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new vectorE3GA(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_792(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	grade1OM_E3GA testOM;
	no_t randomDomainSmv;
	double rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new grade1OM_E3GA(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_no_t_dont_mangle_52(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = mv2.get_scalar();
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_793(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	grade1OM_E3GA testOM;
	normalizedPoint randomDomainSmv;
	vectorE3GA rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new grade1OM_E3GA(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_normalizedPoint_dont_mangle_10(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new vectorE3GA(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_794(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	flatPointOM testOM;
	flatPoint randomDomainSmv;
	flatPoint rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new flatPointOM(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_flatPoint_dont_mangle_56(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new flatPoint(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyOM_dont_mangle_795(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	dualSphere[] rangeVectors = new dualSphere[5];
	om refOM;
	flatPointOM testOM;
	noni_t randomDomainSmv;
	flatPoint rangeSmv, tmp;
	mv mv1, mv2, mv3, dif;
	int d, i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// init two range OMs (GOM and SOM)
		for (d = 0; d < 5; d++) {
			rangeVectors[d] = random_dualSphere_dont_mangle_12(genrand());
		}
		refOM = new om(rangeVectors[0], rangeVectors[1], rangeVectors[2], rangeVectors[3], rangeVectors[4]);
		testOM = new flatPointOM(refOM);
		refOM = new om(testOM);
		
		
		// apply OM directly to domainSMV
		randomDomainSmv = random_noni_t_dont_mangle_37(genrand());
		
		rangeSmv = applyOM(testOM, randomDomainSmv);
		mv3 = new mv(rangeSmv);
		
		// convert domain SMV to GMV, apply to GMV
		mv1 = new mv(randomDomainSmv);
		mv2 = applyOM(refOM, mv1);
		
		// get rid of extra coordinates outside the range of the testOM
 
		tmp = new flatPoint(mv2);
		mv2 = new mv(tmp);
 
		
		// test equality
		dif = subtract(mv2, mv3);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyOM() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
		
	return 1; // success
}

static int test_applyVersor_dont_mangle_796(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	double baseScale = 0.5;
	int g, i;
	double s;
	mv V, IV, X, Y, VX, VY, IVVX, XdY, VXdVY, dif;
	int versorBasisVectorBitmap = 14; // note: random versors restricted to Euclidean basis vectors.
	int bladeBasisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor and its inverse. Optionally make versor unit.
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		V = random_versor_dont_mangle_6_returns_mv(s, g, versorBasisVectorBitmap);
		
		// avoid 'near'-singular versors
		if (V.largestCoordinate() > 2.0)
			continue;		
		
		IV = versorInverse(V);

		// get two random blades		
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		X = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);
		s = baseScale + genrand();
		Y = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);

		// apply versor to blades
		VX = new mv(applyVersor(V, X));
		VY = new mv(applyVersor(V, Y));
		
		// compute inner product
		XdY = mhip(X, Y);
		VXdVY = mhip(VX, VY);
		
		// see if inner products are equal (versor application should not change metric)
		dif = subtract(XdY, VXdVY);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (metric test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
		
		// apply inverse transformation to VX
		IVVX = applyVersor(IV, VX);
		
		// see if X equals IVVX
		dif = subtract(X, IVVX);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (inverse test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_applyUnitVersor_dont_mangle_797(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	double baseScale = 0.5;
	int g, i;
	double s;
	mv V, IV, X, Y, VX, VY, IVVX, XdY, VXdVY, dif;
	mv tmp;
	int versorBasisVectorBitmap = 14; // note: random versors restricted to Euclidean basis vectors.
	int bladeBasisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor and its inverse. Optionally make versor unit.
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		V = random_versor_dont_mangle_6_returns_mv(s, g, versorBasisVectorBitmap);
		
		// avoid 'near'-singular versors
		if (V.largestCoordinate() > 2.0)
			continue;		
		
		tmp = unit(V);
		V = tmp;
		IV = versorInverse(V);

		// get two random blades		
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		X = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);
		s = baseScale + genrand();
		Y = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);

		// apply versor to blades
		VX = new mv(applyUnitVersor(V, X));
		VY = new mv(applyUnitVersor(V, Y));
		
		// compute inner product
		XdY = mhip(X, Y);
		VXdVY = mhip(VX, VY);
		
		// see if inner products are equal (versor application should not change metric)
		dif = subtract(XdY, VXdVY);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (metric test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
		
		// apply inverse transformation to VX
		IVVX = applyUnitVersor(IV, VX);
		
		// see if X equals IVVX
		dif = subtract(X, IVVX);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (inverse test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_applyVersorWI_dont_mangle_798(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	double baseScale = 0.5;
	int g, i;
	double s;
	mv V, IV, X, Y, VX, VY, IVVX, XdY, VXdVY, dif;
	mv tmp;
	int versorBasisVectorBitmap = 14; // note: random versors restricted to Euclidean basis vectors.
	int bladeBasisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor and its inverse. Optionally make versor unit.
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		V = random_versor_dont_mangle_6_returns_mv(s, g, versorBasisVectorBitmap);
		
		// avoid 'near'-singular versors
		if (V.largestCoordinate() > 2.0)
			continue;		
		
		tmp = unit(V);
		V = tmp;
		IV = versorInverse(V);

		// get two random blades		
		s = baseScale + genrand();
		g = (int)(genrand() * 5.5);
		X = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);
		s = baseScale + genrand();
		Y = random_blade_dont_mangle_40_returns_mv(s, g, bladeBasisVectorBitmap);

		// apply versor to blades
		VX = new mv(applyVersorWI(V, X, IV));
		VY = new mv(applyVersorWI(V, Y, IV));
		
		// compute inner product
		XdY = mhip(X, Y);
		VXdVY = mhip(VX, VY);
		
		// see if inner products are equal (versor application should not change metric)
		dif = subtract(XdY, VXdVY);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (metric test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
		
		// apply inverse transformation to VX
		IVVX = applyVersorWI(IV, VX, V);
		
		// see if X equals IVVX
		dif = subtract(X, IVVX);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (inverse test) (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_applyUnitVersor_dont_mangle_799(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	vectorE3GA X;
	vectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new vectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_800(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_801(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_802(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	bivectorE3GA X;
	bivectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new bivectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_803(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	line X;
	line VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new line(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_804(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	plane X;
	plane VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_plane_dont_mangle_15(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new plane(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_805(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	noni_t X;
	flatPoint VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_noni_t_dont_mangle_37(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new flatPoint(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_806(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	no_t X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_no_t_dont_mangle_52(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_807(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	ni_t X;
	dualPlane VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_ni_t_dont_mangle_90(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualPlane(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_808(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	rotorE3GA X;
	rotorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_rotorE3GA_dont_mangle_34(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new rotorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_809(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	I5_t X;
	pseudoscalar VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_I5_t_dont_mangle_23(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pseudoscalar(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_810(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	vectorE3GA X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_811(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_812(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_813(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	bivectorE3GA X;
	pointPair VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pointPair(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_814(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	line X;
	circle VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new circle(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_815(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	sphere X;
	sphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_sphere_dont_mangle_46(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new sphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_816(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	ni_t X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_ni_t_dont_mangle_90(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_817(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	rotorE3GA X;
	evenVersor VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_rotorE3GA_dont_mangle_34(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new evenVersor(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_818(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor tmp;
	evenVersor IV;
	I5i_t X;
	pseudoscalar VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_I5i_t_dont_mangle_123(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pseudoscalar(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_819(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	vectorE3GA X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_820(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_821(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_822(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	bivectorE3GA X;
	pointPair VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pointPair(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_823(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	line X;
	circle VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new circle(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyUnitVersor_dont_mangle_824(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor tmp;
	oddVersor IV;
	plane X;
	sphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_plane_dont_mangle_15(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyUnitVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new sphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyUnitVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_825(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	vectorE3GA X;
	vectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new vectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_826(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_147(genrand());
		
		IV = versorInverse_dont_mangle_149(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_148(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor_dont_mangle_146(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_827(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_828(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	bivectorE3GA X;
	bivectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new bivectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_829(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	line X;
	line VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new line(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_830(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	plane X;
	plane VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_plane_dont_mangle_15(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new plane(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_831(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	noni_t X;
	flatPoint VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_noni_t_dont_mangle_37(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new flatPoint(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_832(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	no_t X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_no_t_dont_mangle_52(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_833(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	ni_t X;
	dualPlane VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_ni_t_dont_mangle_90(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualPlane(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_834(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	rotorE3GA X;
	rotorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_rotorE3GA_dont_mangle_34(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new rotorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_835(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA IV;
	I5_t X;
	pseudoscalar VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_I5_t_dont_mangle_23(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pseudoscalar(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_836(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	vectorE3GA X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_837(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_838(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_839(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	bivectorE3GA X;
	pointPair VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pointPair(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_840(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	line X;
	circle VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new circle(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_841(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	sphere X;
	sphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_sphere_dont_mangle_46(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new sphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_842(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	ni_t X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_ni_t_dont_mangle_90(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_843(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	rotorE3GA X;
	evenVersor VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_rotorE3GA_dont_mangle_34(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new evenVersor(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_844(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor V;
	evenVersor IV;
	I5i_t X;
	pseudoscalar VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_evenVersor_dont_mangle_98(genrand());
		
		IV = versorInverse_dont_mangle_100(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_I5i_t_dont_mangle_123(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pseudoscalar(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_845(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	vectorE3GA X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_205(genrand());
		
		IV = versorInverse_dont_mangle_207(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_206(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor_dont_mangle_146(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_846(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_847(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_848(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	bivectorE3GA X;
	pointPair VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pointPair(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_849(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	line X;
	circle VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new circle(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersor_dont_mangle_850(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor V;
	oddVersor IV;
	plane X;
	sphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_oddVersor_dont_mangle_30(genrand());
		
		IV = versorInverse_dont_mangle_127(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_plane_dont_mangle_15(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersor(V, X);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new sphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersor() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_851(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	vectorE3GA X;
	vectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_vectorE3GA_dont_mangle_1(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new vectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_852(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	normalizedPoint X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_normalizedPoint_dont_mangle_10(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_853(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	dualSphere X;
	dualSphere VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_dualSphere_dont_mangle_12(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new dualSphere(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_854(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	bivectorE3GA X;
	bivectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_bivectorE3GA_dont_mangle_13(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new bivectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_855(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	line X;
	line VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_line_dont_mangle_17(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new line(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_856(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	plane X;
	plane VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_plane_dont_mangle_15(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new plane(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_857(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	e1_t X;
	vectorE3GA VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_e1_t_dont_mangle_21(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new vectorE3GA(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_applyVersorWI_dont_mangle_858(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA V;
	rotorE3GA tmp;
	rotorE3GA IV;
	I5_t X;
	pseudoscalar VX, tmpVX2;
	mv gmvV, gmvX, gmvVX, gmvVX2, dif; // gmvIV, 
	int i;

	for (i = 0; i < NB_LOOPS; i++) {
		// generate random versor of target type, make unit if required, invert
		V = random_rotorE3GA_dont_mangle_34(genrand());
		
		// Test if norm2(V) is positive, otherwise do not perform the test.
		// (because with a negative norm2(v), the reverse is not the inverse)
		if (norm2_returns_scalar(V) <= 0.0) continue;

		tmp = unit(V);
		V = tmp;
		IV = versorInverse_dont_mangle_67(V);

		// avoid near-singular versors
		if ((V.largestCoordinate() > 2.0) ||
			(IV.largestCoordinate() > 2.0))
			continue;
		 

		// generate random SMV 
		X = random_I5_t_dont_mangle_23(genrand());

		//  apply random versor to random SMV, convert to GMV
		VX = applyVersorWI(V, X, IV);
		gmvVX2 = new mv(VX);

		// convert all to GMV type, apply versor too as GMV
		gmvV = new mv(V);
//		gmvIV = new mv(IV);
		gmvX = new mv(X);
		gmvVX = applyVersor(gmvV, gmvX);
		
		// convert GMV back and forth to return type to fix possible constant coordinates
		tmpVX2 = new pseudoscalar(gmvVX);
		gmvVX = new mv(tmpVX2);
		
		// see if VX equals gmvVX
		dif = subtract(gmvVX, gmvVX2);
		if (dif.largestCoordinate() > (1E-12 )) {
			Console.WriteLine("applyVersorWI() test failed (largest coordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}


static int test_div_dont_mangle_859(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	int i;
	mv A, B, C, dif;
	double divider;
	
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_blade_dont_mangle_40_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		C = gp(B, divider);
		
		// see if C equals A
		dif = subtract(C, A);
		if (dif.largestCoordinate() > (1E-14 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_860(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	int i;
	vectorE3GA A;
	vectorE3GA B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_861(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	int i;
	normalizedPoint A;
	dualSphere B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_862(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	int i;
	bivectorE3GA A;
	bivectorE3GA B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_863(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	int i;
	line A;
	line B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_line_dont_mangle_17(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_864(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	int i;
	plane A;
	plane B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_plane_dont_mangle_15(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_865(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	int i;
	sphere A;
	sphere B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_sphere_dont_mangle_46(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_866(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	int i;
	I5_t A;
	pseudoscalar B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_I5_t_dont_mangle_23(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_867(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	int i;
	evenVersor A;
	evenVersor B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_evenVersor_dont_mangle_98(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_868(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	int i;
	oddVersor A;
	oddVersor B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_oddVersor_dont_mangle_30(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_869(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	int i;
	I3_t A;
	circle B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_I3_t_dont_mangle_257(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_div_dont_mangle_870(int NB_TESTS_SCALER) {
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	int i;
	noni_t A;
	flatPoint B;
	mv gmvA, gmvB, C, dif;
	double divider;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random smv
		A = random_noni_t_dont_mangle_37(genrand());
		
		divider = 0.01 + genrand();
		
		B = div(A, divider);
		
		gmvB = new mv(B);
		C = gp(gmvB, divider);
		
		gmvA = new mv(A);
		
		// see if C equals A
		dif = subtract(C, gmvA);
		if (dif.largestCoordinate() > (1E-13 )) {
			Console.WriteLine("div() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}

	return 1; // success
}

static int test_dual_dont_mangle_871(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, dif;
	int i;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_blade_dont_mangle_40_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		B = dual(A);
		
		C = undual(B);
		
		// check if equal to original:
		dif = subtract(A, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_872(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, dif;
	int i;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_blade_dont_mangle_40_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		B = undual(A);
		
		C = dual(B);
		
		// check if equal to original:
		dif = subtract(A, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_875(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	plane B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_876(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	plane B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_877(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	sphere B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_878(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	sphere B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_879(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	line B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_880(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	line B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_881(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	oddVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_882(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	oddVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_883(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	evenVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_884(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	evenVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_885(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	oddVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_886(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	oddVersor B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_887(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	circle B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pointPair_dont_mangle_273(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_888(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	circle B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pointPair_dont_mangle_273(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_889(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	dualPlane B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_plane_dont_mangle_15(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_890(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	dualPlane B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_plane_dont_mangle_15(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_891(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	circle A;
	pointPair B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_circle_dont_mangle_18(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual_dont_mangle_277_returns_mv(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_892(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	circle A;
	pointPair B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_circle_dont_mangle_18(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual_dont_mangle_279_returns_mv(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_893(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	plane B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_e1_t_dont_mangle_21(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_894(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I3_t A;
	noni_t B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I3_t_dont_mangle_257(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_dual_dont_mangle_895(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5_t A;
	double B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I5_t_dont_mangle_23(genrand());
		gmvA = new mv(A);
		
		B = dual(A);
		gmvB = new mv(B);
		
		C = undual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("dual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_undual_dont_mangle_896(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5i_t A;
	double B;
	mv gmvA, gmvB, C, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I5i_t_dont_mangle_123(genrand());
		gmvA = new mv(A);
		
		B = undual(A);
		gmvB = new mv(B);
		
		C = dual(gmvB);
		
		// check if equal to original:
		dif = subtract(gmvA, C);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("undual() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_897(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C;
	double s, eps = 0.2;
	int g, i;
	boolean e, l;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);
		B = random_versor_dont_mangle_6_returns_mv(1.1 * eps, g, basisVectorBitmap);
		C = add(B, A);
		
		// check if equals thinks A if is equal to itself
		e = equals(A, A, eps);
		if (!e) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}
		
		// check if equals thinks A if is equal C
		e = equals(A, C, eps);
		
		// use mv_largestCoordinate() to verify
		l = !(B.largestCoordinate() > eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_898(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	vectorE3GA A;
	vectorE3GA B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_899(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	bivectorE3GA A;
	bivectorE3GA B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_900(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	rotorE3GA A;
	rotorE3GA B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_901(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	bivectorE3GA A;
	rotorE3GA B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_902(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	rotorE3GA A;
	bivectorE3GA B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_903(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	rotorE3GA A;
	line B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_line_dont_mangle_17(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_904(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 20;
	evenVersor A;
	plane B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_plane_dont_mangle_15(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_905(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	circle A;
	line B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_circle_dont_mangle_18(s);
		B = random_line_dont_mangle_17(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_906(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	plane A;
	plane B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_plane_dont_mangle_15(s);
		B = random_plane_dont_mangle_15(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_907(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	normalizedPoint A;
	normalizedPoint B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_normalizedPoint_dont_mangle_10(s);
		B = random_normalizedPoint_dont_mangle_10(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_908(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e1_t A;
	e1_t B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	boolean e1, e2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_e1_t_dont_mangle_21(s);
		B = random_e1_t_dont_mangle_21(s);
		
		// check if equals thinks A if is equal to itself
		e1 = equals(A, A, eps);
		e2 = equals(B, B, eps);
		if ((!e1) || (!e2)) {
			Console.WriteLine("equals() failed (variable not equal to itself)\n");
			return 0; // failure
		}		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_909(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5_t A;
	I5i_t B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_I5_t_dont_mangle_23(s);
		B = random_I5i_t_dont_mangle_123(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_equals_dont_mangle_910(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 11;
	noni_t A;
	pointPair B;
	mv gA, gB, gC;
	double s, eps = 0.5; // note the really large epsilon
	int i;
	boolean e, l;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_noni_t_dont_mangle_37(s);
		B = random_pointPair_dont_mangle_273(s);
		
		// convert A and B to gmv
		gA = new mv(A);
		gB = new mv(B);
		
		// gC = gB - gA, get largest coord
		gC = subtract(gB, gA);
		// use largestCoordinate() to verify
		l = !(gC.largestCoordinate() > eps);
		
		// check if equals thinks A if is equal B
		e = equals(A, B, eps);
		
		if (e != l) {
			Console.WriteLine("equals() test failed\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade_dont_mangle_911(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, G0, G1, G2, G3, G4, G5;
	int i;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_versor_dont_mangle_6_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		// split it up into groups/grades:
		G0 = extractGrade(A, GroupBitmap.GROUP_0);
		G1 = extractGrade(A, GroupBitmap.GROUP_1);
		G2 = extractGrade(A, GroupBitmap.GROUP_2);
		G3 = extractGrade(A, GroupBitmap.GROUP_3);
		G4 = extractGrade(A, GroupBitmap.GROUP_4);
		G5 = extractGrade(A, GroupBitmap.GROUP_5);
		// sum all into 'B'
		B = new mv();
		B = add(B, G0);
		B = add(B, G1);
		B = add(B, G2);
		B = add(B, G3);
		B = add(B, G4);
		B = add(B, G5);

		// check if equal to original:
		C = subtract(A, B);
		if (C.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade() test failed (largestCoordinate = " + (double)C.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade2_dont_mangle_912(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D;
	int i;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_versor_dont_mangle_6_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		B = extractGrade2(A);
		
		C = extractGrade(A, 0 | GroupBitmap.GROUP_0 | GroupBitmap.GROUP_1 | GroupBitmap.GROUP_3 | GroupBitmap.GROUP_4 | GroupBitmap.GROUP_5);
		
		// sum all into 'B'
		D = add(B, C);

		// check if equal to original:
		C = subtract(A, D);
		if (C.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade2() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade0_dont_mangle_913(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	rotorE3GA A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gB = new mv(extractGrade0(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_0);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade0() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade2_dont_mangle_914(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	rotorE3GA A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gB = new mv(extractGrade2(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_2);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade2() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade1_dont_mangle_915(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	oddVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gB = new mv(extractGrade1(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_1);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade1() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade3_dont_mangle_916(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	oddVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gB = new mv(extractGrade3(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_3);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade3() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade4_dont_mangle_917(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	oddVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gB = new mv(extractGrade4(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_4);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade4() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade0_dont_mangle_918(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	evenVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gB = new mv(extractGrade0(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_0);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade0() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade1_dont_mangle_919(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	evenVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gB = new mv(extractGrade1(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_1);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade1() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade4_dont_mangle_920(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 17;
	evenVersor A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gB = new mv(extractGrade4(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_4);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade4() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade0_dont_mangle_921(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	normalizedPoint A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gB = new mv(extractGrade0(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_0);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade0() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade1_dont_mangle_922(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	normalizedPoint A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gB = new mv(extractGrade1(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_1);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade1() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade2_dont_mangle_923(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	normalizedPoint A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gB = new mv(extractGrade2(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_2);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade2() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade3_dont_mangle_924(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	dualSphere A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_dualSphere_dont_mangle_12(genrand());
		
		gB = new mv(extractGrade3(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_3);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade3() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade4_dont_mangle_925(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	normalizedPoint A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gB = new mv(extractGrade4(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_4);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade4() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade5_dont_mangle_926(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	normalizedPoint A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gB = new mv(extractGrade5(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_5);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade5() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade0_dont_mangle_927(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e1_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_e1_t_dont_mangle_21(genrand());
		
		gB = new mv(extractGrade0(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_0);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade0() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade1_dont_mangle_928(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e2_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_e2_t_dont_mangle_22(genrand());
		
		gB = new mv(extractGrade1(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_1);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade1() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade2_dont_mangle_929(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e3_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_e3_t_dont_mangle_330(genrand());
		
		gB = new mv(extractGrade2(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_2);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade2() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade3_dont_mangle_930(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	no_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_no_t_dont_mangle_52(genrand());
		
		gB = new mv(extractGrade3(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_3);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade3() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade4_dont_mangle_931(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e1_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_e1_t_dont_mangle_21(genrand());
		
		gB = new mv(extractGrade4(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_4);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade4() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade0_dont_mangle_932(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_I5_t_dont_mangle_23(genrand());
		
		gB = new mv(extractGrade0(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_0);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade0() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade1_dont_mangle_933(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5i_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_I5i_t_dont_mangle_123(genrand());
		
		gB = new mv(extractGrade1(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_1);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade1() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade2_dont_mangle_934(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_I5_t_dont_mangle_23(genrand());
		
		gB = new mv(extractGrade2(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_2);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade2() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade3_dont_mangle_935(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5i_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_I5i_t_dont_mangle_123(genrand());
		
		gB = new mv(extractGrade3(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_3);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade3() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_extractGrade4_dont_mangle_936(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	I5_t A;
	mv gA, gB, gC, gD;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		A = random_I5_t_dont_mangle_23(genrand());
		
		gB = new mv(extractGrade4(A));

		gA = new mv(A);
		gC = extractGrade(gA, 0 | GroupBitmap.GROUP_4);
		
		// check if equal to original:
		gD = subtract(gB, gC);
		if (gD.largestCoordinate() > 1E-14) {
			Console.WriteLine("extractGrade4() test failed");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gp_dont_mangle_937(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 128;
	mv A, B, C, D, E, V1, V2;
	int i;
	int o;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		o = (genrand() < 0.5) ? 0 : 1; // even or odd?
		A = random_versor_dont_mangle_6_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		B = random_versor_dont_mangle_6_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		C = random_versor_dont_mangle_6_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		
		{ // test (A+B) C = A C + B C
			// D = A + B
			D = add(A, B);
			// V1 = D C
			V1 = gp(D, C);
			// D = A C
			D = gp(A, C);
			// E = B C
			E = gp(B, C);
			// V2 = D + E
			V2 = add(D, E);
			// test equality
			D = subtract(V1, V2);
			// use mv_largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-11) {
				Console.WriteLine("gp() test failed on '(A+B) C = A C + B C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}
		
		{ // test A (B+C) = A B + A C
			// D = B + C
			D = add(B, C);
			// V1 = A D
			V1 = gp(A, D);
			// D = A B
			D = gp(A, B);
			// E = A C
			E = gp(A, C);
			// V2 = D + E
			V2 = add(D, E);
			// test equality
			D = subtract(V1, V2);
			// use largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-12) {
				Console.WriteLine("gp() test failed on 'A (B+C) = A B + A C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}
		
		{ // test A (B C) = (A B) C
			// D = B C
			D = gp(B, C);
			// V1 = A D
			V1 = gp(A, D);
			// D = A B
			D = gp(A, B);
			// V2 = D C
			V2 = gp(D, C);
			// test equality
			D = subtract(V1, V2);
			// use largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-12) {
				Console.WriteLine("gp() test failed on 'A (B C) = (A B) C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_938(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	vectorE3GA A;
	vectorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_939(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	rotorE3GA A;
	vectorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_940(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	circle A;
	line B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_circle_dont_mangle_18(s);
		B = random_line_dont_mangle_17(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_941(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	rotorE3GA A;
	line B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_line_dont_mangle_17(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_942(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	vectorE3GA A;
	rotorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_943(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	rotorE3GA A;
	rotorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_944(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	plane A;
	rotorE3GA B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_plane_dont_mangle_15(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_945(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 20;
	plane A;
	oddVersor B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_plane_dont_mangle_15(s);
		B = random_oddVersor_dont_mangle_30(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_946(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	bivectorE3GA A;
	bivectorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_947(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 21;
	evenVersor A;
	dualSphere B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_dualSphere_dont_mangle_12(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_948(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	normalizedPoint A;
	normalizedPoint B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_normalizedPoint_dont_mangle_10(s);
		B = random_normalizedPoint_dont_mangle_10(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_949(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 20;
	flatPoint A;
	oddVersor B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_flatPoint_dont_mangle_56(s);
		B = random_oddVersor_dont_mangle_30(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_950(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e1_t A;
	e2_t B;
	bivectorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_e1_t_dont_mangle_21(s);
		B = random_e2_t_dont_mangle_22(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_951(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e3_t A;
	ni_t B;
	freeVector C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_e3_t_dont_mangle_330(s);
		B = random_ni_t_dont_mangle_90(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_952(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	no_t A;
	ni_t B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_no_t_dont_mangle_52(s);
		B = random_ni_t_dont_mangle_90(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_953(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 2;
	e3_t A;
	I5i_t B;
	plane C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_e3_t_dont_mangle_330(s);
		B = random_I5i_t_dont_mangle_123(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_954(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 128;
	mv A, B, C, D, E, V1, V2;
	int i;
	int o;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		o = (genrand() < 0.5) ? 0 : 1; // even or odd?
		A = random_versor_dont_mangle_371_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		B = random_versor_dont_mangle_371_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		C = random_versor_dont_mangle_371_returns_mv(genrand(), ((int)(genrand() * 5.5) & 0xFFFE) + o, basisVectorBitmap);
		
		{ // test (A+B) C = A C + B C
			// D = A + B
			D = add(A, B);
			// V1 = D C
			V1 = gp_em(D, C);
			// D = A C
			D = gp_em(A, C);
			// E = B C
			E = gp_em(B, C);
			// V2 = D + E
			V2 = add(D, E);
			// test equality
			D = subtract(V1, V2);
			// use mv_largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-11) {
				Console.WriteLine("gp_em() test failed on '(A+B) C = A C + B C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}
		
		{ // test A (B+C) = A B + A C
			// D = B + C
			D = add(B, C);
			// V1 = A D
			V1 = gp_em(A, D);
			// D = A B
			D = gp_em(A, B);
			// E = A C
			E = gp_em(A, C);
			// V2 = D + E
			V2 = add(D, E);
			// test equality
			D = subtract(V1, V2);
			// use largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-12) {
				Console.WriteLine("gp_em() test failed on 'A (B+C) = A B + A C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}
		
		{ // test A (B C) = (A B) C
			// D = B C
			D = gp_em(B, C);
			// V1 = A D
			V1 = gp_em(A, D);
			// D = A B
			D = gp_em(A, B);
			// V2 = D C
			V2 = gp_em(D, C);
			// test equality
			D = subtract(V1, V2);
			// use largestCoordinate() to verify
			if (D.largestCoordinate() > 1E-12) {
				Console.WriteLine("gp_em() test failed on 'A (B C) = (A B) C' (dif=" + D.largestCoordinate() + ")");
				return 0; // failure
			}
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_955(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	vectorE3GA A;
	vectorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_956(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	rotorE3GA A;
	vectorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_vectorE3GA_dont_mangle_1(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_957(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 9;
	vectorE3GA A;
	line B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_line_dont_mangle_17(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_958(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 14;
	rotorE3GA A;
	circle B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_circle_dont_mangle_18(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_959(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 7;
	vectorE3GA A;
	rotorE3GA B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_vectorE3GA_dont_mangle_1(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_960(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	rotorE3GA A;
	rotorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_961(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 8;
	plane A;
	rotorE3GA B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_plane_dont_mangle_15(s);
		B = random_rotorE3GA_dont_mangle_34(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_962(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 15;
	sphere A;
	circle B;
	oddVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_sphere_dont_mangle_46(s);
		B = random_circle_dont_mangle_18(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_em_dont_mangle_963(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	bivectorE3GA A;
	bivectorE3GA B;
	rotorE3GA C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		B = random_bivectorE3GA_dont_mangle_13(s);
		
		// A gp B
		C = gp_em(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp_em(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp_em() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_964(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, tmp, randomBlade;
	int i, j;
	int basisVectorBitmap = -1;
	int gradeBitmap1, gradeBitmap2, nbBlades, grade;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get sum of random blades, keep track of grades used
		gradeBitmap1 = 0;
		A = new mv();
		nbBlades = (int)(genrand() * 5.5);
		for (j = 0; j < nbBlades; j++) {
			grade = (int)(genrand() * 5.5);
			gradeBitmap1 |= 1 << grade;
			randomBlade = random_blade_dont_mangle_40_returns_mv(1.0, grade, basisVectorBitmap);
			tmp = add(A, randomBlade);
			A = tmp;
		}
		
		gradeBitmap2 = gradeBitmap(A, 0.0);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_966(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA;
	rotorE3GA A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_rotorE3GA_dont_mangle_34(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_965(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA;
	oddVersor A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_oddVersor_dont_mangle_30(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_967(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA;
	evenVersor A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_evenVersor_dont_mangle_98(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_968(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA;
	vectorE3GA A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_vectorE3GA_dont_mangle_1(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_969(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA;
	bivectorE3GA A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_970(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA;
	pseudoscalar A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_pseudoscalar_dont_mangle_396(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_971(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	mv gmvA;
	circle A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_circle_dont_mangle_18(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_972(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	mv gmvA;
	pointPair A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_pointPair_dont_mangle_273(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_973(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA;
	e1_t A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_e1_t_dont_mangle_21(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_974(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA;
	e2_t A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_e2_t_dont_mangle_22(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_975(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA;
	noni_t A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_noni_t_dont_mangle_37(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeBitmap_dont_mangle_976(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA;
	I5i_t A;
	int i;
	int gradeBitmap1, gradeBitmap2;
	double threshold;
	
	for (i = 0; i < NB_LOOPS; i++) {
		threshold = 0.01 * genrand();
		A = random_I5i_t_dont_mangle_123(1.0);
		
		gradeBitmap1 = gradeBitmap(A, threshold);
		
		gmvA = new mv(A);
		
		gradeBitmap2 = gradeBitmap(gmvA, threshold);
		
		// check if grade bitmaps match
		if (gradeBitmap1 != gradeBitmap2) {
			Console.WriteLine("gradeBitmap() test failed (grade bitmap " + gradeBitmap1 + " vs " + gradeBitmap2 + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_977(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, dif;
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);
		
		// copy it to another versor
		B = new mv(A);
		
		// set coordinates of B to random values (which may not be zero)
		for (g = 0; g < 6; g++) {
			if (B.m_c[g] == null) continue;
			for (int e = 0; e < B.m_c[g].length; e++) {
				B.m_c[g][e] = 0.5 + genrand();
			}
		}
		
		// do hadamard product
		C = hp(A, B);
		
		// invert coordinates of B manually
		for (g = 0; g < 6; g++) {
			if (B.m_c[g] == null) continue;
			for (int e = 0; e < B.m_c[g].length; e++) {
				B.m_c[g][e] = 1.0 / B.m_c[g][e];
			}
		}

		// do inverse hadamard product
		D = hp(C, B);
		
		// check if equal to original:
		dif = subtract(A, D);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_978(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	vectorE3GA B;
	vectorE3GA C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_979(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	bivectorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_980(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA, gmvB, gmvC, gmvD;
	rotorE3GA A;
	rotorE3GA B;
	rotorE3GA C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_981(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA, gmvB, gmvC, gmvD;
	oddVersor A;
	oddVersor B;
	oddVersor C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_982(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	oddVersor B;
	vectorE3GA C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_983(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA, gmvB, gmvC, gmvD;
	evenVersor A;
	evenVersor B;
	evenVersor C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_984(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	bivectorE3GA A;
	rotorE3GA B;
	bivectorE3GA C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_985(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	mv gmvA, gmvB, gmvC, gmvD;
	circle A;
	evenVersor B;
	double C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_circle_dont_mangle_18(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_986(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	pseudoscalar A;
	oddVersor B;
	pseudoscalar C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_987(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA, gmvB, gmvC, gmvD;
	rotorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_988(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA, gmvB, gmvC, gmvD;
	evenVersor A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	double vC, vD, Q;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("hp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_989(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	mv gmvA, gmvB, gmvC, gmvD;
	sphere A;
	plane B;
	plane C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_sphere_dont_mangle_46(1.0);
		B = random_plane_dont_mangle_15(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_990(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	mv gmvA, gmvB, gmvC, gmvD;
	normalizedPoint A;
	e1_t B;
	vectorE3GA C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_normalizedPoint_dont_mangle_10(1.0);
		B = random_e1_t_dont_mangle_21(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_991(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	e1_t B;
	vectorE3GA C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_e1_t_dont_mangle_21(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_992(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	I5_t A;
	pseudoscalar B;
	pseudoscalar C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5_t_dont_mangle_23(1.0);
		B = random_pseudoscalar_dont_mangle_396(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hp_dont_mangle_993(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	pseudoscalar A;
	I5i_t B;
	pseudoscalar C;
	int i;
	mv dif;


	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_I5i_t_dont_mangle_123(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		
		// do hadamard product (SMV)
		C = hp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = hp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_994(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, dif;
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);
		
		// copy it to another versor
		B = new mv(A);
		
		// set coordinates of B to random values (which may not be zero)
		for (g = 0; g < 6; g++) {
			if (B.m_c[g] == null) continue;
			for (int e = 0; e < B.m_c[g].length; e++) {
				B.m_c[g][e] = 0.5 + genrand();
			}
		}
		
		// do hadamard product
		C = ihp(A, B);
		
		// invert coordinates of B manually
		for (g = 0; g < 6; g++) {
			if (B.m_c[g] == null) continue;
			for (int e = 0; e < B.m_c[g].length; e++) {
				B.m_c[g][e] = 1.0 / B.m_c[g][e];
			}
		}

		// do inverse hadamard product
		D = ihp(C, B);
		
		// check if equal to original:
		dif = subtract(A, D);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_995(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	vectorE3GA B;
	vectorE3GA C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_vectorE3GA_dont_mangle_1(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_996(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	bivectorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_997(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA, gmvB, gmvC, gmvD;
	rotorE3GA A;
	rotorE3GA B;
	rotorE3GA C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_rotorE3GA_dont_mangle_34(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_998(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	mv gmvA, gmvB, gmvC, gmvD;
	circle A;
	oddVersor B;
	circle C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_oddVersor_dont_mangle_30(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_circle_dont_mangle_18(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_999(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	oddVersor B;
	vectorE3GA C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_oddVersor_dont_mangle_30(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1000(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA, gmvB, gmvC, gmvD;
	evenVersor A;
	evenVersor B;
	evenVersor C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_evenVersor_dont_mangle_98(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1001(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	bivectorE3GA A;
	rotorE3GA B;
	bivectorE3GA C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_rotorE3GA_dont_mangle_34(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1002(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA, gmvB, gmvC, gmvD;
	plane A;
	evenVersor B;
	plane C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_evenVersor_dont_mangle_98(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_plane_dont_mangle_15(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1003(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	pseudoscalar A;
	oddVersor B;
	pseudoscalar C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_oddVersor_dont_mangle_30(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1004(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	mv gmvA, gmvB, gmvC, gmvD;
	rotorE3GA A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1005(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	mv gmvA, gmvB, gmvC, gmvD;
	evenVersor A;
	bivectorE3GA B;
	bivectorE3GA C;
	int i;
	boolean ok;
	double vC, vD, Q;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		vC = gmvC.get_scalar();
		vD = gmvD.get_scalar();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no();
		vD = gmvD.get_no();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1();
		vD = gmvD.get_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2();
		vD = gmvD.get_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3();
		vD = gmvD.get_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_ni();
		vD = gmvD.get_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1();
		vD = gmvD.get_no_e1();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2();
		vD = gmvD.get_no_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2();
		vD = gmvD.get_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3();
		vD = gmvD.get_no_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3();
		vD = gmvD.get_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3();
		vD = gmvD.get_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_ni();
		vD = gmvD.get_no_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_ni();
		vD = gmvD.get_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_ni();
		vD = gmvD.get_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e3_ni();
		vD = gmvD.get_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2();
		vD = gmvD.get_no_e1_e2();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3();
		vD = gmvD.get_no_e1_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3();
		vD = gmvD.get_no_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3();
		vD = gmvD.get_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_ni();
		vD = gmvD.get_no_e1_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_ni();
		vD = gmvD.get_no_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_ni();
		vD = gmvD.get_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e3_ni();
		vD = gmvD.get_no_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e3_ni();
		vD = gmvD.get_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e2_e3_ni();
		vD = gmvD.get_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3();
		vD = gmvD.get_no_e1_e2_e3();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_ni();
		vD = gmvD.get_no_e1_e2_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e3_ni();
		vD = gmvD.get_no_e1_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e2_e3_ni();
		vD = gmvD.get_no_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_e1_e2_e3_ni();
		vD = gmvD.get_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
		vC = gmvC.get_no_e1_e2_e3_ni();
		vD = gmvD.get_no_e1_e2_e3_ni();
		if ((vD < -1E-14) || (vD > 1E-14)) {
			Q = vC / vD;
			if (Q < 0.0) Q = -Q;
			if (((Q - 1.0) < -1E-14) || ((Q - 1.0) > 1E-14)) {
				Console.WriteLine("ihp() test failed (using alternate test, Q = " + (double)Q + ")\n");
				return 0; // failure
			}
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1006(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	mv gmvA, gmvB, gmvC, gmvD;
	dualSphere A;
	normalizedPoint B;
	dualSphere C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_normalizedPoint_dont_mangle_10(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_dualSphere_dont_mangle_12(1.0);
		B = random_normalizedPoint_dont_mangle_10(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1007(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	mv gmvA, gmvB, gmvC, gmvD;
	normalizedPoint A;
	normalizedPoint B;
	normalizedPoint C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_normalizedPoint_dont_mangle_10(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_normalizedPoint_dont_mangle_10(1.0);
		B = random_normalizedPoint_dont_mangle_10(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1008(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	mv gmvA, gmvB, gmvC, gmvD;
	vectorE3GA A;
	e1_t B;
	vectorE3GA C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_e1_t_dont_mangle_21(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_e1_t_dont_mangle_21(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1009(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	I5_t A;
	pseudoscalar B;
	pseudoscalar C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_pseudoscalar_dont_mangle_396(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5_t_dont_mangle_23(1.0);
		B = random_pseudoscalar_dont_mangle_396(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_ihp_dont_mangle_1010(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	mv gmvA, gmvB, gmvC, gmvD;
	pseudoscalar A;
	I5i_t B;
	pseudoscalar C;
	int i;
	boolean ok;
	mv dif;

	// initialize a lookup table which tells us which coordinates should not be non-null	
	boolean[] nonNull2 = new boolean[32];
	for (i = 0; i < 100; i++) {
		B = random_I5i_t_dont_mangle_123(1.0);
		gmvB = new mv(B);
		int nonNull2idx = 0;
		for (int g = 0; g < 6; g++) {
			if (gmvB.m_c[g] == null) continue;
			for (int e = 0; e < gmvB.m_c[g].length; e++) {
				if (gmvB.m_c[g][e] != 0.0) nonNull2[nonNull2idx] = true;
				nonNull2idx++;
			}
		}
	}

	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_I5i_t_dont_mangle_123(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
		
		// make sure that none of the 'B' coordinates are 0, because then inverse hadamard won't work
		ok = true;

		{		
			int nonNull2idx = 0;
			for (int g = 0; g < 6; g++) {
				if (gmvB.m_c[g] == null) continue;
				for (int e = 0; e < gmvB.m_c[g].length; e++) {
					if (gmvB.m_c[g][e] == 0.0) {
						if (nonNull2[nonNull2idx]) ok = false;
						else gmvB.m_c[g][e] = 1E+306;
						nonNull2idx++;
					}
				}
			}
		}
		
		if (!ok) continue; // some SMV coordinate was set to 0 by the random generator (very rare)
		
		// do hadamard product (SMV)
		C = ihp(A, B);
		gmvC = new mv(C);
		
		// do hadamard product (GMV)
		gmvD = ihp(gmvA, gmvB);
		
		// check if equal to original:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("ihp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")\n");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_increment_dont_mangle_1011(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, one;
	int i, g;
	int basisVectorBitmap = -1;

	one = new mv(1.0);

	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(genrand() + 0.5, g, basisVectorBitmap);
		
		// increment/decrement
		B = increment(A);
		
		// undo the increment/decrement
		C = subtract(B, one);
		
		// see if (A - (B-1)) == 0
		D = subtract(A, C);
		
		if (D.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed (largestCoordinate of D = " + (double)D.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_increment_dont_mangle_1014(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	rotorE3GA C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		
		// increment or decrement
		C = increment(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = increment(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_increment_dont_mangle_1013(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	rotorE3GA C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		
		// increment or decrement
		C = increment(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = increment(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_increment_dont_mangle_1012(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	evenVersor C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		
		// increment or decrement
		C = increment(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = increment(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_increment_dont_mangle_1015(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	evenVersor C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_pointPair_dont_mangle_273(s);
		
		// increment or decrement
		C = increment(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = increment(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_increment_dont_mangle_1016(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	evenVersor C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_noni_t_dont_mangle_37(s);
		
		// increment or decrement
		C = increment(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = increment(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("increment() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1017(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, one;
	int i, g;
	int basisVectorBitmap = -1;

	one = new mv(-1.0);

	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(genrand() + 0.5, g, basisVectorBitmap);
		
		// increment/decrement
		B = decrement(A);
		
		// undo the increment/decrement
		C = subtract(B, one);
		
		// see if (A - (B-1)) == 0
		D = subtract(A, C);
		
		if (D.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed (largestCoordinate of D = " + (double)D.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1018(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	rotorE3GA C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_bivectorE3GA_dont_mangle_13(s);
		
		// increment or decrement
		C = decrement(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = decrement(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1019(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	rotorE3GA C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_rotorE3GA_dont_mangle_34(s);
		
		// increment or decrement
		C = decrement(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = decrement(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1020(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	evenVersor C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		
		// increment or decrement
		C = decrement(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = decrement(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1021(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	evenVersor C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_noni_t_dont_mangle_37(s);
		
		// increment or decrement
		C = decrement(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = decrement(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_decrement_dont_mangle_1022(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	translator C;
	mv gA, gC1, gC2;
	double s;
	
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_freeVector_dont_mangle_480(s);
		
		// increment or decrement
		C = decrement(A);
		
		// convert A and C to gmv
		gA = new mv(A);
		gC1 = new mv(C);
		
		gC2 = decrement(gA);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-14) {
			Console.WriteLine("decrement() test failed\n");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_sp_dont_mangle_1023(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using sp()
		C = new mv(sp(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		E = extractGrade(D, Grades[0]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1025(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using lc()
		C = new mv(lc(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		if (ga > gb) E = new mv(0.0);
		else E = extractGrade(D, Grades[gd]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1026(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using rc()
		C = new mv(rc(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		if (ga < gb) E = new mv(0.0);
		else E = extractGrade(D, Grades[gd]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1024(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using hip()
		C = new mv(hip(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		if ((ga == 0) || (gb == 0)) E = new mv(0.0);
		else E = extractGrade(D, Grades[gd]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1027(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using mhip()
		C = new mv(mhip(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		E = extractGrade(D, Grades[gd]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1028(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	vectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1029(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	plane B;
//	line C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_plane_dont_mangle_15(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1030(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	line B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_line_dont_mangle_17(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1031(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	vectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1032(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	vectorE3GA B;
//	plane C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1033(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	oddVersor B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1034(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	vectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1035(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	vectorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1036(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	vectorE3GA B;
//	evenVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1037(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	vectorE3GA B;
//	vectorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1038(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	pseudoscalar B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_pseudoscalar_dont_mangle_396(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1039(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	evenVersor B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1040(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	bivectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1041(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	plane B;
//	line C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_plane_dont_mangle_15(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1042(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	line B;
//	flatPoint C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_line_dont_mangle_17(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1043(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	rotorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1044(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	rotorE3GA B;
//	rotorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1045(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	rotorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1046(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	rotorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1047(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	rotorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1048(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	rotorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_e1_t_dont_mangle_21(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1049(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e2_t A;
	rotorE3GA B;
//	vectorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_e2_t_dont_mangle_22(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1050(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5_t A;
	rotorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5_t_dont_mangle_23(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1051(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5i_t A;
	rotorE3GA B;
//	line C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5i_t_dont_mangle_123(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1052(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	rotorE3GA B;
//	dualSphere C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_no_t_dont_mangle_52(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1053(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	e1_t B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_e1_t_dont_mangle_21(1.0);
		B = random_e1_t_dont_mangle_21(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1054(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e2_t A;
	e3_t B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_e2_t_dont_mangle_22(1.0);
		B = random_e3_t_dont_mangle_330(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1055(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5_t A;
	I5i_t B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5_t_dont_mangle_23(1.0);
		B = random_I5i_t_dont_mangle_123(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1056(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5i_t A;
	ni_t B;
//	plane C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5i_t_dont_mangle_123(1.0);
		B = random_ni_t_dont_mangle_90(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1057(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	e1_t B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_no_t_dont_mangle_52(1.0);
		B = random_e1_t_dont_mangle_21(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sp_dont_mangle_1058(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	plane B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_plane_dont_mangle_15(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using sp()
		gmvC = new mv(sp(A, B));
		
		// perform GMV version 
		gmvD = new mv(sp(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_lc_dont_mangle_1059(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	line B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_line_dont_mangle_17(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using lc()
		gmvC = new mv(lc(A, B));
		
		// perform GMV version 
		gmvD = new mv(lc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("lc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_rc_dont_mangle_1060(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	evenVersor B;
//	rotorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_evenVersor_dont_mangle_98(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using rc()
		gmvC = new mv(rc(A, B));
		
		// perform GMV version 
		gmvD = new mv(rc(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("rc() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_hip_dont_mangle_1061(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	bivectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using hip()
		gmvC = new mv(hip(A, B));
		
		// perform GMV version 
		gmvD = new mv(hip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("hip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_mhip_dont_mangle_1062(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	pseudoscalar B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_pseudoscalar_dont_mangle_396(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using mhip()
		gmvC = new mv(mhip(A, B));
		
		// perform GMV version 
		gmvD = new mv(mhip(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("mhip() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1063(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, reverseA, tmp;
	
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		// compute norm
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(A);
		tmp = gp(A, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-14) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1069(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1068(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1066(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_line_dont_mangle_17(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1070(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_plane_dont_mangle_15(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1065(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1064(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1067(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1071(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1072(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_dualSphere_dont_mangle_12(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1073(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1074(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_e1_t_dont_mangle_21(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1075(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_noni_t_dont_mangle_37(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_dont_mangle_1076(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5i_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I5i_t_dont_mangle_123(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1077(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, reverseA, tmp;
	
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		// compute norm
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(A);
		tmp = gp(A, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-14) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1078(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1079(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1080(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1081(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_dualSphere_dont_mangle_12(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1082(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	circle A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_circle_dont_mangle_18(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1083(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	sphere A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_sphere_dont_mangle_46(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1084(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1085(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1086(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1087(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1088(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e3_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_e3_t_dont_mangle_330(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1089(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_no_t_dont_mangle_52(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_dont_mangle_1090(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I5_t_dont_mangle_23(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1091(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, reverseA, tmp;
	
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		// compute norm
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(A);
		tmp = gp_em(A, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-14) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1092(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1093(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1094(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeVector_dont_mangle_480(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1095(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeBivector A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeBivector_dont_mangle_588(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1096(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1097(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1098(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm_em_dont_mangle_1099(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		n2 = (double)Math.sqrt(Math.abs(n2));
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1100(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, reverseA, tmp;
	
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		// compute norm
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(A);
		tmp = gp_em(A, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-14) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1101(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1102(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1103(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_line_dont_mangle_17(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1104(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_plane_dont_mangle_15(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1105(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1106(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_oddVersor_dont_mangle_30(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1107(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_evenVersor_dont_mangle_98(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1108(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1109(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_no_t_dont_mangle_52(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1110(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_I5_t_dont_mangle_23(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_norm2_em_dont_mangle_1111(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	mv gmvA, reverseA, tmp;
	
	int i;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_e1_t_dont_mangle_21(genrand());
		
		gmvA = new mv(A);
		
		n1 = norm2_em(A);
		
		// compute norm manually (A . reverse(A))
		reverseA = reverse(gmvA);
		tmp = gp_em(gmvA, reverseA);
		n2 = tmp.get_scalar();
		
		// check if equal to original:
		if (Math.abs(n1 - n2) > 1E-13) {
			Console.WriteLine("norm2_em() test failed (difference = " + (double)Math.abs(n1 - n2) + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1112_1(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, C, D, E, dif;
	int i, ga, gb, gd;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		s = genrand();
		gb = (int)(genrand() * 5.5);
		B = random_blade_dont_mangle_40_returns_mv(s, gb, basisVectorBitmap);
		
		// compute product using op()
		C = new mv(op(A, B));
		
		// simulate product using geometric product & grade part selection
		D = gp(A, B);
		gd = (ga > gb) ? ga - gb : gb - ga;
		E = extractGrade(D,  Grades[ga + gb]);

		// check if equal:
		dif = subtract(C, E);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1112_2(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B;
	int i, ga;
	double s;
	int basisVectorBitmap = -1;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		ga = (int)(genrand() * 5.5);
		if (ga == 0) continue; // do not perform this test for grade 0
		A = random_blade_dont_mangle_40_returns_mv(s, ga, basisVectorBitmap);
		
		// compute A ^ A (should be zero)
		B = op(A, A);
		
		// check if B is zero:
		if (B.largestCoordinate() > 1E-13) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)B.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1113(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	vectorE3GA B;
//	bivectorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1114(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	vectorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_vectorE3GA_dont_mangle_1(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1115(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	line B;
//	plane C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_line_dont_mangle_17(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1116(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	circle B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_circle_dont_mangle_18(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1117(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	rotorE3GA B;
//	oddVersor C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1118(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	rotorE3GA B;
//	rotorE3GA C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1119(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	rotorE3GA B;
//	plane C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_plane_dont_mangle_15(1.0);
		B = random_rotorE3GA_dont_mangle_34(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1120(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	circle A;
	pointPair B;
//	pseudoscalar C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_circle_dont_mangle_18(1.0);
		B = random_pointPair_dont_mangle_273(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1121(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	bivectorE3GA B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(1.0);
		B = random_bivectorE3GA_dont_mangle_13(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1122(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	oddVersor B;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(1.0);
		B = random_oddVersor_dont_mangle_30(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1123(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	ni_t B;
//	noni_t C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_no_t_dont_mangle_52(1.0);
		B = random_ni_t_dont_mangle_90(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_op_dont_mangle_1124(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	ni_t A;
	no_t B;
//	flatPoint C;
	

	mv gmvA, gmvB, gmvC, gmvD, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_ni_t_dont_mangle_90(1.0);
		B = random_no_t_dont_mangle_52(1.0);
		
		// convert smv to GMV
		gmvA = new mv(A);
		gmvB = new mv(B);
				
		// compute product using op()
		gmvC = new mv(op(A, B));
		
		// perform GMV version 
		gmvD = new mv(op(gmvA, gmvB));

		// check if equal:
		dif = subtract(gmvC, gmvD);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("op() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_exp_dont_mangle_1126(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, expA, sinhA, coshA, S, dif, tmp1; //, tmp2;
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade of grade 2
		s = 2.0 * genrand();
		g = 2;
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		if (genrand() > 0.5) { // make sure that 'A' is not always a blade
			s = genrand();
			tmp1 = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);	
			A = add(A, tmp1);
			//A = tmp2;
		}

		// apply sinh, cosh, exp functions
		expA = exp(A, order);
		sinhA = sinh(A, order);
		coshA = cosh(A, order);
		
		// sum sinh and cosh
		S = add(coshA, sinhA);
		
		// test that sinh+cosh == exp
		dif = subtract(expA, S);
		if (dif.largestCoordinate() > 0.00031622776601683783) { // sinh, cosh precision is very low
			Console.WriteLine("exp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_exp_dont_mangle_1128(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	evenVersor B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pointPair_dont_mangle_273(genrand());
		
		// compute sin, cos or exp
		B = exp(A, 12);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = exp(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("exp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_exp_dont_mangle_1127(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	normalizedTranslator B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeVector_dont_mangle_480(genrand());
		
		// compute sin, cos or exp
		B = exp(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = exp(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("exp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_exp_dont_mangle_1129(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	evenVersor B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_noni_t_dont_mangle_37(genrand());
		
		// compute sin, cos or exp
		B = exp(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = exp(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("exp() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cosh_dont_mangle_1130(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, expA, sinhA, coshA, S, dif, tmp1; //, tmp2;
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade of grade 2
		s = 2.0 * genrand();
		g = 2;
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		if (genrand() > 0.5) { // make sure that 'A' is not always a blade
			s = genrand();
			tmp1 = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);	
			A = add(A, tmp1);
			//A = tmp2;
		}

		// apply sinh, cosh, exp functions
		expA = exp(A, order);
		sinhA = sinh(A, order);
		coshA = cosh(A, order);
		
		// sum sinh and cosh
		S = add(coshA, sinhA);
		
		// test that sinh+cosh == exp
		dif = subtract(expA, S);
		if (dif.largestCoordinate() > 0.00031622776601683783) { // sinh, cosh precision is very low
			Console.WriteLine("cosh() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cosh_dont_mangle_1131(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	flatPoint A;
	double B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_flatPoint_dont_mangle_56(genrand());
		
		// compute sin, cos or exp
		B = cosh(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = cosh(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("cosh() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sinh_dont_mangle_1132(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, expA, sinhA, coshA, S, dif, tmp1; //, tmp2;
	int i, g;
	int basisVectorBitmap = -1;
	double s;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade of grade 2
		s = 2.0 * genrand();
		g = 2;
		A = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);
		
		if (genrand() > 0.5) { // make sure that 'A' is not always a blade
			s = genrand();
			tmp1 = random_blade_dont_mangle_40_returns_mv(s, g, basisVectorBitmap);	
			A = add(A, tmp1);
			//A = tmp2;
		}

		// apply sinh, cosh, exp functions
		expA = exp(A, order);
		sinhA = sinh(A, order);
		coshA = cosh(A, order);
		
		// sum sinh and cosh
		S = add(coshA, sinhA);
		
		// test that sinh+cosh == exp
		dif = subtract(expA, S);
		if (dif.largestCoordinate() > 0.00031622776601683783) { // sinh, cosh precision is very low
			Console.WriteLine("sinh() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sinh_dont_mangle_1133(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	bivectorE3GA B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		// compute sin, cos or exp
		B = sinh(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sinh(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sinh() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sinh_dont_mangle_1134(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	freeVector B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeVector_dont_mangle_480(genrand());
		
		// compute sin, cos or exp
		B = sinh(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sinh(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sinh() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cos_dont_mangle_1136(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	double B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		// compute sin, cos or exp
		B = cos(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = cos(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("cos() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cos_dont_mangle_1137(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	evenVersor B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pointPair_dont_mangle_273(genrand());
		
		// compute sin, cos or exp
		B = cos(A, 12);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = cos(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("cos() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cos_dont_mangle_1138(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	double B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeVector_dont_mangle_480(genrand());
		
		// compute sin, cos or exp
		B = cos(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = cos(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("cos() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cos_dont_mangle_1139(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	double B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_noni_t_dont_mangle_37(genrand());
		
		// compute sin, cos or exp
		B = cos(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = cos(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("cos() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sin_dont_mangle_1141(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	bivectorE3GA B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		// compute sin, cos or exp
		B = sin(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sin(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sin() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sin_dont_mangle_1142(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	evenVersor B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_pointPair_dont_mangle_273(genrand());
		
		// compute sin, cos or exp
		B = sin(A, 12);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sin(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sin() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sin_dont_mangle_1143(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	freeVector B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_freeVector_dont_mangle_480(genrand());
		
		// compute sin, cos or exp
		B = sin(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sin(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sin() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_sin_dont_mangle_1144(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	flatPoint B;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	int order = 12;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_noni_t_dont_mangle_37(genrand());
		
		// compute sin, cos or exp
		B = sin(A);
		gmvB = new mv(B);
		
		// compute sin, cos or exp using GMV
		gmvA = new mv(A); 
		gmvC = sin(gmvA, order);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-12) {
			Console.WriteLine("sin() test failed (largestCoordinate = " + (double)dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1145(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, GA, GB;
	int i, c, n, g;
	int basisVectorBitmap = -1;
	double s, dif;
	int[] signTable = new int[]{-1, -1, -1, -1, -1, -1};
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);

		// apply function
		B = negate(A);
		
		// check grade
		for (n = 0; n <= 5; n++) {
			GA = extractGrade(A, Grades[n]);
			GB = extractGrade(B, Grades[n]);
			
			// check if grade usage matches
			if (GA.gu() != GB.gu()) {
				Console.WriteLine("negate() test failed (grade usage does not match)");
				return 0; // failure
			}
			
			// check each coordinate of each groups which is still present after the grade selection
			for (int m = 0; m < 6; m++) {
				if (GA.m_c[m] != null) {
					for (c = 0; c < GA.m_c[m].length; c++) {
						dif = (double)Math.abs(GA.m_c[m][c] * (double)signTable[n] - GB.m_c[m][c]);
						if (dif > 1E-14) {
							Console.WriteLine("negate() test failed (dif = " + dif + ")");
							return 0; // failure
						}
					}
				}
			}
		}
		
	}
	return 1; // success
}

static int test_negate_dont_mangle_1146(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1147(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_freeVector_dont_mangle_480(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1148(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1149(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_dualSphere_dont_mangle_12(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1150(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_line_dont_mangle_17(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1151(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	sphere A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_sphere_dont_mangle_46(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1152(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1153(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1154(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1155(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_pseudoscalar_dont_mangle_396(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1156(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_e1_t_dont_mangle_21(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1157(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e2_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_e2_t_dont_mangle_22(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_negate_dont_mangle_1158(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_noni_t_dont_mangle_37(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(negate(A));
		
		// compute via GMV
		gmvC = negate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("negate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1160(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, GA, GB;
	int i, c, n, g;
	int basisVectorBitmap = -1;
	double s, dif;
	int[] signTable = new int[]{1, 1, -1, -1, 1, 1};
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);

		// apply function
		B = reverse(A);
		
		// check grade
		for (n = 0; n <= 5; n++) {
			GA = extractGrade(A, Grades[n]);
			GB = extractGrade(B, Grades[n]);
			
			// check if grade usage matches
			if (GA.gu() != GB.gu()) {
				Console.WriteLine("reverse() test failed (grade usage does not match)");
				return 0; // failure
			}
			
			// check each coordinate of each groups which is still present after the grade selection
			for (int m = 0; m < 6; m++) {
				if (GA.m_c[m] != null) {
					for (c = 0; c < GA.m_c[m].length; c++) {
						dif = (double)Math.abs(GA.m_c[m][c] * (double)signTable[n] - GB.m_c[m][c]);
						if (dif > 1E-14) {
							Console.WriteLine("reverse() test failed (dif = " + dif + ")");
							return 0; // failure
						}
					}
				}
			}
		}
		
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1161(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1162(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1163(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1164(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_dualSphere_dont_mangle_12(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1165(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_line_dont_mangle_17(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1166(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	circle A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_circle_dont_mangle_18(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1167(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1168(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1169(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1170(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_pseudoscalar_dont_mangle_396(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1172(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_e1_t_dont_mangle_21(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1173(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e3_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_e3_t_dont_mangle_330(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_reverse_dont_mangle_1174(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I5i_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_I5i_t_dont_mangle_123(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(reverse(A));
		
		// compute via GMV
		gmvC = reverse(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("reverse() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1175(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, GA, GB;
	int i, c, n, g;
	int basisVectorBitmap = -1;
	double s, dif;
	int[] signTable = new int[]{1, -1, -1, 1, 1, -1};
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);

		// apply function
		B = cliffordConjugate(A);
		
		// check grade
		for (n = 0; n <= 5; n++) {
			GA = extractGrade(A, Grades[n]);
			GB = extractGrade(B, Grades[n]);
			
			// check if grade usage matches
			if (GA.gu() != GB.gu()) {
				Console.WriteLine("cliffordConjugate() test failed (grade usage does not match)");
				return 0; // failure
			}
			
			// check each coordinate of each groups which is still present after the grade selection
			for (int m = 0; m < 6; m++) {
				if (GA.m_c[m] != null) {
					for (c = 0; c < GA.m_c[m].length; c++) {
						dif = (double)Math.abs(GA.m_c[m][c] * (double)signTable[n] - GB.m_c[m][c]);
						if (dif > 1E-14) {
							Console.WriteLine("cliffordConjugate() test failed (dif = " + dif + ")");
							return 0; // failure
						}
					}
				}
			}
		}
		
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1176(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1177(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 10;
	pointPair A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_pointPair_dont_mangle_273(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1178(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1179(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	freeVector A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_freeVector_dont_mangle_480(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1180(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_line_dont_mangle_17(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1181(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_plane_dont_mangle_15(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1182(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1183(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1184(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1185(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_pseudoscalar_dont_mangle_396(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1187(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	e1_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_e1_t_dont_mangle_21(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1188(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	noni_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_noni_t_dont_mangle_37(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_cliffordConjugate_dont_mangle_1189(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I3_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_I3_t_dont_mangle_257(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(cliffordConjugate(A));
		
		// compute via GMV
		gmvC = cliffordConjugate(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("cliffordConjugate() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1190(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, B, GA, GB;
	int i, c, n, g;
	int basisVectorBitmap = -1;
	double s, dif;
	int[] signTable = new int[]{1, -1, 1, -1, 1, -1};
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		s = genrand();
		g = (int)(genrand() * 5.5);
		A = random_versor_dont_mangle_6_returns_mv(s, g, basisVectorBitmap);

		// apply function
		B = gradeInvolution(A);
		
		// check grade
		for (n = 0; n <= 5; n++) {
			GA = extractGrade(A, Grades[n]);
			GB = extractGrade(B, Grades[n]);
			
			// check if grade usage matches
			if (GA.gu() != GB.gu()) {
				Console.WriteLine("gradeInvolution() test failed (grade usage does not match)");
				return 0; // failure
			}
			
			// check each coordinate of each groups which is still present after the grade selection
			for (int m = 0; m < 6; m++) {
				if (GA.m_c[m] != null) {
					for (c = 0; c < GA.m_c[m].length; c++) {
						dif = (double)Math.abs(GA.m_c[m][c] * (double)signTable[n] - GB.m_c[m][c]);
						if (dif > 1E-14) {
							Console.WriteLine("gradeInvolution() test failed (dif = " + dif + ")");
							return 0; // failure
						}
					}
				}
			}
		}
		
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1191(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	vectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_vectorE3GA_dont_mangle_1(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1192(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 3;
	bivectorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1193(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	normalizedPoint A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_normalizedPoint_dont_mangle_10(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1194(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 5;
	dualSphere A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_dualSphere_dont_mangle_12(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1195(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 6;
	line A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_line_dont_mangle_17(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1196(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	plane A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_plane_dont_mangle_15(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1197(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 4;
	rotorE3GA A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_rotorE3GA_dont_mangle_34(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1198(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	oddVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_oddVersor_dont_mangle_30(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1199(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 16;
	evenVersor A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_evenVersor_dont_mangle_98(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1200(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	pseudoscalar A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_pseudoscalar_dont_mangle_396(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1202(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	no_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_no_t_dont_mangle_52(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1203(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	ni_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_ni_t_dont_mangle_90(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_gradeInvolution_dont_mangle_1204(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 1;
	I3_t A;
	mv gmvA, gmvB, gmvC, dif;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random Smv
		A = random_I3_t_dont_mangle_257(genrand());
		gmvA = new mv(A);
		
		gmvB = new mv(gradeInvolution(A));
		
		// compute via GMV
		gmvC = gradeInvolution(gmvA);
		
		// check if equal:
		dif = subtract(gmvC, gmvB);
		if (dif.largestCoordinate() > 1E-14) {
			Console.WriteLine("gradeInvolution() test failed (largestCoordinate = " + dif.largestCoordinate() + ")");
			return 0; // failure
		}
	}
	return 1; // success
}

static int test_unit_dont_mangle_1205(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, UA, RUA;
	int i;
	int basisVectorBitmap = -1;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_blade_dont_mangle_40_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		UA = unit(A);
		RUA = reverse(UA);
		n = sp(RUA, UA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1209(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	vectorE3GA A;
	vectorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1206(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	bivectorE3GA A;
	bivectorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1207(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	line A;
	line UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_line_dont_mangle_17(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1208(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	sphere A;
	sphere UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_sphere_dont_mangle_46(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1210(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	rotorE3GA A;
	rotorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1211(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	oddVersor A;
	oddVersor UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1212(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	evenVersor A;
	evenVersor UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1213(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	normalizedPoint A;
	dualSphere UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_normalizedPoint_dont_mangle_10(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1214(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	dualSphere A;
	dualSphere UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_dualSphere_dont_mangle_12(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1215(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	pseudoscalar A;
	pseudoscalar UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1216(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	pointPair A;
	pointPair UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pointPair_dont_mangle_273(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1217(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	e2_t A;
	e2_t UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_e2_t_dont_mangle_22(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_dont_mangle_1218(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	I5_t A;
	I5_t UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_I5_t_dont_mangle_23(genrand());
		
		UA = unit(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1219(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv A, UA, RUA;
	int i;
	int basisVectorBitmap = -1;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random blade
		A = random_blade_dont_mangle_40_returns_mv(genrand(), (int)(genrand() * 5.5), basisVectorBitmap);
		
		UA = unit_em(A);
		RUA = reverse(UA);
		n = sp_dont_mangle_719_returns_mv(RUA, UA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1220(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	vectorE3GA A;
	vectorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_vectorE3GA_dont_mangle_1(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1221(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	bivectorE3GA A;
	bivectorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_bivectorE3GA_dont_mangle_13(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1222(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	circle A;
	circle UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_circle_dont_mangle_18(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1223(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	plane A;
	plane UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_plane_dont_mangle_15(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1224(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	rotorE3GA A;
	rotorE3GA UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_rotorE3GA_dont_mangle_34(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1225(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	oddVersor A;
	oddVersor UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_oddVersor_dont_mangle_30(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1226(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	evenVersor A;
	evenVersor UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_evenVersor_dont_mangle_98(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_unit_em_dont_mangle_1227(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	pseudoscalar A;
	pseudoscalar UA;
	mv gmvUA, RUA;
	int i;
	double n;
	
	for (i = 0; i < NB_LOOPS; i++) {
		A = random_pseudoscalar_dont_mangle_396(genrand());
		
		UA = unit_em(A);
		
		gmvUA = new mv(UA);
		
		RUA = reverse(gmvUA);
		n = sp_dont_mangle_719_returns_mv(RUA, gmvUA);
		
		if ((double)(Math.abs(n) - 1.0) > 1E-12) {
			Console.WriteLine("unit_em() test failed (|norm|-1 = " + (double)(Math.abs((double)n) - 1.0) + ")");
			return 0; // failure
		}

	}
	return 1; // success
}

static int test_add_dont_mangle_1228(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	evenVersor A;
	evenVersor B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_evenVersor_dont_mangle_98(s);
		
		// add/subtract A and B
		
		C = add(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = add(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("add() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_1229(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 20;
	pointPair A;
	pointPair B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_pointPair_dont_mangle_273(s);
		B = random_pointPair_dont_mangle_273(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_subtract_dont_mangle_1230(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	evenVersor A;
	evenVersor B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_evenVersor_dont_mangle_98(s);
		
		// add/subtract A and B
		
		C = subtract(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = subtract(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("subtract() test failed");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_1231(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 26;
	evenVersor A;
	pointPair B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_pointPair_dont_mangle_273(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_gp_dont_mangle_1232(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	evenVersor A;
	evenVersor B;
	evenVersor C;
	mv gA, gB, gC1, gC2;
	
	double s;
	int i;
	
	for (i = 0; i < NB_LOOPS; i++) {
		s = genrand();
		A = random_evenVersor_dont_mangle_98(s);
		B = random_evenVersor_dont_mangle_98(s);
		
		// A gp B
		C = gp(A, B);
		gC1 = new mv(C);
		
		// convert all A and B to gmv and add/subtract as GMVs
		gA = new mv(A);
		gB = new mv(B);
		gC2 = gp(gA, gB);
		
		// see if result is equal up to precision:
		gA = subtract(gC1, gC2);
		if (gA.largestCoordinate() > 1E-13) {
			Console.WriteLine("gp() test failed (largestCoordinate = " + gA.largestCoordinate() + ")");
			return 0; // failure
		}		
	}
	return 1; // success
}

static int test_versorInverse_dont_mangle_1235(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv V, VI, VVI, VIV, X, Y;
	int i;
	int basisVectorBitmap = -1;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		V = random_versor_dont_mangle_6_returns_mv(genrand() + 0.5, (int)(genrand() * 5.5), basisVectorBitmap);
		
		// avoid 'near'-singular versors
		if (V.largestCoordinate() > 2.0)
			continue;
		
		// compute inverse
		VI = versorInverse(V);
		
		// compute (inverse(V) V) and (V inverse(V))
		VIV = gp(VI, V);
		VVI = gp(V, VI);
		
		// check that scalar parts are 1
		n1 = VIV.get_scalar();
		n2 = VVI.get_scalar();
		
		if (Math.abs(n1 - 1.0) > 1E-11) {
			Console.WriteLine("versorInverse() test failed |VI . V - 1|= " + (double)Math.abs(n1 - 1.0) + ")");
			return 0; // failure
		}
		
		if (Math.abs(n2 - 1.0) > 1E-11) {
			Console.WriteLine("versorInverse() test failed ( |V . VI - 1| = " + (double)Math.abs(n2 - 1.0) + ")");
			return 0; // failure
		}
		
		// check that other grade parts are zero:
		X = extractGrade(VIV, GroupBitmap.ALL_GROUPS ^ GroupBitmap.GROUP_0);
		Y = extractGrade(VVI, GroupBitmap.ALL_GROUPS ^ GroupBitmap.GROUP_0);
		
		if (X.largestCoordinate() > 1E-11) {
			Console.WriteLine("versorInverse() test failed (largestCoordinate of VIV = " + (double)X.largestCoordinate() + ")");
			return 0; // failure
		}
		
		if (Y.largestCoordinate() > 1E-11) {
			Console.WriteLine("versorInverse() test failed (largestCoordinate of VVI = " + (double)Y.largestCoordinate() + ")");
			return 0; // failure
		}
		
	}
	return 1; // success
}

static int test_versorInverse_em_dont_mangle_1236(int NB_TESTS_SCALER) 
{
	int NB_LOOPS = 100 + NB_TESTS_SCALER / 32;
	mv V, VI, VVI, VIV, X, Y;
	int i;
	int basisVectorBitmap = -1;
	double n1, n2;
	
	for (i = 0; i < NB_LOOPS; i++) {
		// get random versor
		V = random_versor_dont_mangle_6_returns_mv(genrand() + 0.5, (int)(genrand() * 5.5), basisVectorBitmap);
		
		// avoid 'near'-singular versors
		if (V.largestCoordinate() > 2.0)
			continue;
		
		// compute inverse
		VI = versorInverse_em(V);
		
		// compute (inverse(V) V) and (V inverse(V))
		VIV = gp_em(VI, V);
		VVI = gp_em(V, VI);
		
		// check that scalar parts are 1
		n1 = VIV.get_scalar();
		n2 = VVI.get_scalar();
		
		if (Math.abs(n1 - 1.0) > 1E-11) {
			Console.WriteLine("versorInverse_em() test failed |VI . V - 1|= " + (double)Math.abs(n1 - 1.0) + ")");
			return 0; // failure
		}
		
		if (Math.abs(n2 - 1.0) > 1E-11) {
			Console.WriteLine("versorInverse_em() test failed ( |V . VI - 1| = " + (double)Math.abs(n2 - 1.0) + ")");
			return 0; // failure
		}
		
		// check that other grade parts are zero:
		X = extractGrade(VIV, GroupBitmap.ALL_GROUPS ^ GroupBitmap.GROUP_0);
		Y = extractGrade(VVI, GroupBitmap.ALL_GROUPS ^ GroupBitmap.GROUP_0);
		
		if (X.largestCoordinate() > 1E-11) {
			Console.WriteLine("versorInverse_em() test failed (largestCoordinate of VIV = " + (double)X.largestCoordinate() + ")");
			return 0; // failure
		}
		
		if (Y.largestCoordinate() > 1E-11) {
			Console.WriteLine("versorInverse_em() test failed (largestCoordinate of VVI = " + (double)Y.largestCoordinate() + ")");
			return 0; // failure
		}
		
	}
	return 1; // success
}

	static class ConsoleInternal {
		public ConsoleInternal() {}
		public void WriteLine(String line) {
			System.out.println(line);
		}
	}
	
	static ConsoleInternal Console = new ConsoleInternal();

public static void main(String[] args)
{
	int retVal = 0;
	// The number of tests will be proportional to this value.
	// This should become a command-line option
	int NB_TESTS_SCALER = 16384;
	
	// seed random number generators with current time
	c3ga.genrand_timeSeed();

	// run all test functions
	if (test_metric_default_mv(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_metric_euclidean_mv(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_parse_mv(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_genrand_double(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cgaPoint_dont_mangle_763(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cgaPoint_dont_mangle_765(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_randomCgaPoint_dont_mangle_766(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cgaPointDistance_dont_mangle_767(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cgaPointDistance_dont_mangle_764(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_768(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_777(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_773(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_772(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_770(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_769(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_771(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_774(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_775(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_776(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_778(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_779(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_780(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_781(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_782(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_783(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_784(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_785(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_786(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_787(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_788(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_789(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_790(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_791(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_792(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_793(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_794(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyOM_dont_mangle_795(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_796(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_797(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_798(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_799(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_800(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_801(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_802(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_803(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_804(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_805(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_806(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_807(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_808(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_809(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_810(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_811(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_812(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_813(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_814(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_815(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_816(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_817(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_818(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_819(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_820(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_821(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_822(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_823(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyUnitVersor_dont_mangle_824(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_825(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_826(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_827(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_828(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_829(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_830(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_831(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_832(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_833(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_834(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_835(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_836(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_837(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_838(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_839(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_840(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_841(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_842(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_843(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_844(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_845(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_846(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_847(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_848(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_849(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersor_dont_mangle_850(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_851(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_852(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_853(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_854(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_855(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_856(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_857(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_applyVersorWI_dont_mangle_858(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_859(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_860(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_861(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_862(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_863(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_864(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_865(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_866(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_867(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_868(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_869(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_div_dont_mangle_870(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_871(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_872(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_875(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_876(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_877(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_878(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_879(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_880(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_881(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_882(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_883(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_884(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_885(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_886(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_887(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_888(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_889(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_890(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_891(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_892(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_893(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_894(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_dual_dont_mangle_895(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_undual_dont_mangle_896(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_897(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_898(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_899(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_900(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_901(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_902(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_903(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_904(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_905(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_906(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_907(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_908(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_909(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_equals_dont_mangle_910(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade_dont_mangle_911(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade2_dont_mangle_912(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade0_dont_mangle_913(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade2_dont_mangle_914(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade1_dont_mangle_915(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade3_dont_mangle_916(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade4_dont_mangle_917(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade0_dont_mangle_918(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade1_dont_mangle_919(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade4_dont_mangle_920(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade0_dont_mangle_921(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade1_dont_mangle_922(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade2_dont_mangle_923(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade3_dont_mangle_924(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade4_dont_mangle_925(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade5_dont_mangle_926(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade0_dont_mangle_927(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade1_dont_mangle_928(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade2_dont_mangle_929(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade3_dont_mangle_930(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade4_dont_mangle_931(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade0_dont_mangle_932(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade1_dont_mangle_933(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade2_dont_mangle_934(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade3_dont_mangle_935(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_extractGrade4_dont_mangle_936(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_937(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_938(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_939(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_940(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_941(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_942(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_943(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_944(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_945(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_946(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_947(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_948(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_949(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_950(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_951(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_952(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_953(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_954(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_955(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_956(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_957(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_958(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_959(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_960(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_961(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_962(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_em_dont_mangle_963(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_964(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_966(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_965(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_967(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_968(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_969(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_970(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_971(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_972(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_973(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_974(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_975(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeBitmap_dont_mangle_976(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_977(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_978(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_979(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_980(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_981(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_982(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_983(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_984(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_985(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_986(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_987(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_988(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_989(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_990(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_991(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_992(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hp_dont_mangle_993(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_994(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_995(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_996(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_997(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_998(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_999(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1000(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1001(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1002(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1003(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1004(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1005(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1006(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1007(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1008(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1009(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_ihp_dont_mangle_1010(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1011(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1014(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1013(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1012(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1015(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_increment_dont_mangle_1016(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1017(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1018(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1019(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1020(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1021(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_decrement_dont_mangle_1022(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1023(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1025(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1026(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1024(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1027(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1028(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1029(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1030(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1031(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1032(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1033(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1034(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1035(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1036(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1037(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1038(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1039(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1040(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1041(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1042(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1043(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1044(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1045(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1046(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1047(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1048(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1049(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1050(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1051(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1052(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1053(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1054(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1055(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1056(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1057(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sp_dont_mangle_1058(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_lc_dont_mangle_1059(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_rc_dont_mangle_1060(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_hip_dont_mangle_1061(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_mhip_dont_mangle_1062(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1063(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1069(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1068(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1066(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1070(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1065(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1064(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1067(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1071(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1072(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1073(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1074(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1075(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_dont_mangle_1076(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1077(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1078(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1079(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1080(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1081(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1082(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1083(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1084(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1085(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1086(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1087(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1088(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1089(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_dont_mangle_1090(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1091(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1092(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1093(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1094(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1095(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1096(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1097(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1098(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm_em_dont_mangle_1099(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1100(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1101(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1102(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1103(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1104(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1105(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1106(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1107(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1108(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1109(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1110(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_norm2_em_dont_mangle_1111(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1112_1(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1112_2(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1113(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1114(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1115(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1116(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1117(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1118(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1119(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1120(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1121(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1122(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1123(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_op_dont_mangle_1124(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_exp_dont_mangle_1126(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_exp_dont_mangle_1128(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_exp_dont_mangle_1127(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_exp_dont_mangle_1129(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cosh_dont_mangle_1130(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cosh_dont_mangle_1131(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sinh_dont_mangle_1132(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sinh_dont_mangle_1133(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sinh_dont_mangle_1134(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cos_dont_mangle_1136(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cos_dont_mangle_1137(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cos_dont_mangle_1138(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cos_dont_mangle_1139(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sin_dont_mangle_1141(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sin_dont_mangle_1142(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sin_dont_mangle_1143(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_sin_dont_mangle_1144(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1145(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1146(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1147(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1148(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1149(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1150(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1151(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1152(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1153(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1154(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1155(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1156(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1157(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_negate_dont_mangle_1158(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1160(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1161(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1162(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1163(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1164(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1165(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1166(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1167(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1168(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1169(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1170(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1172(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1173(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_reverse_dont_mangle_1174(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1175(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1176(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1177(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1178(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1179(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1180(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1181(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1182(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1183(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1184(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1185(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1187(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1188(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_cliffordConjugate_dont_mangle_1189(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1190(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1191(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1192(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1193(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1194(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1195(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1196(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1197(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1198(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1199(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1200(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1202(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1203(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gradeInvolution_dont_mangle_1204(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1205(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1209(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1206(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1207(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1208(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1210(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1211(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1212(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1213(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1214(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1215(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1216(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1217(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_dont_mangle_1218(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1219(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1220(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1221(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1222(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1223(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1224(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1225(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1226(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_unit_em_dont_mangle_1227(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_add_dont_mangle_1228(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_1229(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_subtract_dont_mangle_1230(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_1231(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_gp_dont_mangle_1232(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_versorInverse_dont_mangle_1235(NB_TESTS_SCALER) == 0) retVal = -1;
	if (test_versorInverse_em_dont_mangle_1236(NB_TESTS_SCALER) == 0) retVal = -1;

	if (retVal != 0) Console.WriteLine("Test failed.\n");
	else Console.WriteLine("Done.\n");	

	System.exit(retVal);
}
} // end of class TestSuite
