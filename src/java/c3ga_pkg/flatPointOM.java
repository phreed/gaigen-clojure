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
/**
 * This class can hold a specialized outermorphism.
 * 
 * The coordinates are stored in type double.
 * 
 * There are 6 matrices, one for each grade.
 * The columns of these matrices are the range of the outermorphism.
 * Matrices are stored in row-major order. So the coordinates of rows are stored contiguously.
 * Domain grade 1: .
 * Domain grade 2: e1^ni, e2^ni, e3^ni, no^ni.
 * Domain grade 3: .
 * Domain grade 4: .
 * Domain grade 5: .
 * 
 * The range and domain are equal.
 * 
 */
public class flatPointOM 
{ 
	/**
	 * Matrix for grade 2; the size is 4 x 4
	 */
	protected double[] m_m2 = new double[16];

	/** Constructs a new flatPointOM, set to identity. */
	public flatPointOM() { setIdentity(); }

	/** Copy constructor. */
	public flatPointOM(final flatPointOM M) { set(M); }

	/** Constructor from matrix. */
	public flatPointOM(final double[] M) { set(M); }

	/** Constructor from matrix. */
	public flatPointOM(final double[] M, final boolean transposed) { if (transposed) { setTranspose(M); } else { set(M); } }
	
	/** Constructor from images of basis vectors. */
	public flatPointOM(final dualSphere ino, final dualSphere ie1, final dualSphere ie2, final dualSphere ie3, final dualSphere ini)
		{ set(ino, ie1, ie2, ie3, ini); }

	/** 
	 * Converts a om to a flatPointOM.
	 * Warning: coordinates which cannot be represented are silenty lost.
	 */
	public flatPointOM(final om M) { set(M); }




/**
 * Sets flatPointOM to identity.
 */
public final void setIdentity()
{
	m_m2[0] = 1.0;
	m_m2[4] = m_m2[8] = m_m2[12] = 0.0;

	m_m2[1] = m_m2[9] = m_m2[13] = 0.0;
	m_m2[5] = 1.0;

	m_m2[2] = m_m2[6] = m_m2[14] = 0.0;
	m_m2[10] = 1.0;

	m_m2[3] = m_m2[7] = m_m2[11] = 0.0;
	m_m2[15] = 1.0;

}

/**
 * Copies flatPointOM.
 */
public final void set(final flatPointOM src)
{
	m_m2[0] = src.m_m2[0];
	m_m2[4] = src.m_m2[4];
	m_m2[8] = src.m_m2[8];
	m_m2[12] = src.m_m2[12];

	m_m2[1] = src.m_m2[1];
	m_m2[5] = src.m_m2[5];
	m_m2[9] = src.m_m2[9];
	m_m2[13] = src.m_m2[13];

	m_m2[2] = src.m_m2[2];
	m_m2[6] = src.m_m2[6];
	m_m2[10] = src.m_m2[10];
	m_m2[14] = src.m_m2[14];

	m_m2[3] = src.m_m2[3];
	m_m2[7] = src.m_m2[7];
	m_m2[11] = src.m_m2[11];
	m_m2[15] = src.m_m2[15];

}
/**
 * Sets flatPointOM from images of the domain vectors.
 */
public final void set(final dualSphere ino, final dualSphere ie1, final dualSphere ie2, final dualSphere ie3, final dualSphere ini)
{
	m_m2[0] = (ie1.m_e1*ini.m_ni-ie1.m_ni*ini.m_e1);
	m_m2[4] = (ie1.m_e2*ini.m_ni-ie1.m_ni*ini.m_e2);
	m_m2[8] = (ie1.m_e3*ini.m_ni-ie1.m_ni*ini.m_e3);
	m_m2[12] = (-ie1.m_ni*ini.m_no+ie1.m_no*ini.m_ni);

	m_m2[1] = (ie2.m_e1*ini.m_ni-ie2.m_ni*ini.m_e1);
	m_m2[5] = (ie2.m_e2*ini.m_ni-ie2.m_ni*ini.m_e2);
	m_m2[9] = (ie2.m_e3*ini.m_ni-ie2.m_ni*ini.m_e3);
	m_m2[13] = (-ie2.m_ni*ini.m_no+ie2.m_no*ini.m_ni);

	m_m2[2] = (ie3.m_e1*ini.m_ni-ie3.m_ni*ini.m_e1);
	m_m2[6] = (ie3.m_e2*ini.m_ni-ie3.m_ni*ini.m_e2);
	m_m2[10] = (ie3.m_e3*ini.m_ni-ie3.m_ni*ini.m_e3);
	m_m2[14] = (-ie3.m_ni*ini.m_no+ie3.m_no*ini.m_ni);

	m_m2[3] = (-ini.m_e1*ino.m_ni+ini.m_ni*ino.m_e1);
	m_m2[7] = (-ini.m_e2*ino.m_ni+ini.m_ni*ino.m_e2);
	m_m2[11] = (-ini.m_e3*ino.m_ni+ini.m_ni*ino.m_e3);
	m_m2[15] = (ini.m_ni*ino.m_no-ini.m_no*ino.m_ni);

}
/**
 * Sets flatPointOM from a matrix.
 */
public final void set(final double[] M)
{
	m_m2[0] = (-M[21]*M[9]+M[24]*M[6]);
	m_m2[4] = (M[11]*M[24]-M[14]*M[21]);
	m_m2[8] = (M[16]*M[24]-M[19]*M[21]);
	m_m2[12] = (M[1]*M[24]-M[21]*M[4]);

	m_m2[1] = (-M[22]*M[9]+M[24]*M[7]);
	m_m2[5] = (M[12]*M[24]-M[14]*M[22]);
	m_m2[9] = (M[17]*M[24]-M[19]*M[22]);
	m_m2[13] = (M[2]*M[24]-M[22]*M[4]);

	m_m2[2] = (-M[23]*M[9]+M[24]*M[8]);
	m_m2[6] = (M[13]*M[24]-M[14]*M[23]);
	m_m2[10] = (M[18]*M[24]-M[19]*M[23]);
	m_m2[14] = (-M[23]*M[4]+M[24]*M[3]);

	m_m2[3] = (-M[20]*M[9]+M[24]*M[5]);
	m_m2[7] = (M[10]*M[24]-M[14]*M[20]);
	m_m2[11] = (M[15]*M[24]-M[19]*M[20]);
	m_m2[15] = (M[0]*M[24]-M[20]*M[4]);

}
/**
 * Sets flatPointOM from a transposed matrix.
 */
public final void setTranspose(final double[] M)
{
	m_m2[0] = (-M[21]*M[9]+M[24]*M[6]);
	m_m2[4] = (-M[22]*M[9]+M[24]*M[7]);
	m_m2[8] = (-M[23]*M[9]+M[24]*M[8]);
	m_m2[12] = (-M[20]*M[9]+M[24]*M[5]);

	m_m2[1] = (M[11]*M[24]-M[14]*M[21]);
	m_m2[5] = (M[12]*M[24]-M[14]*M[22]);
	m_m2[9] = (M[13]*M[24]-M[14]*M[23]);
	m_m2[13] = (M[10]*M[24]-M[14]*M[20]);

	m_m2[2] = (M[16]*M[24]-M[19]*M[21]);
	m_m2[6] = (M[17]*M[24]-M[19]*M[22]);
	m_m2[10] = (M[18]*M[24]-M[19]*M[23]);
	m_m2[14] = (M[15]*M[24]-M[19]*M[20]);

	m_m2[3] = (M[1]*M[24]-M[21]*M[4]);
	m_m2[7] = (M[2]*M[24]-M[22]*M[4]);
	m_m2[11] = (-M[23]*M[4]+M[24]*M[3]);
	m_m2[15] = (M[0]*M[24]-M[20]*M[4]);

}
/**
 * Copies a om to a flatPointOM
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(om src) {
	m_m2[0] =  src.m_m2[77];
	m_m2[1] =  src.m_m2[78];
	m_m2[2] =  src.m_m2[79];
	m_m2[3] =  src.m_m2[76];
	m_m2[4] =  src.m_m2[87];
	m_m2[5] =  src.m_m2[88];
	m_m2[6] =  src.m_m2[89];
	m_m2[7] =  src.m_m2[86];
	m_m2[8] =  src.m_m2[97];
	m_m2[9] =  src.m_m2[98];
	m_m2[10] =  src.m_m2[99];
	m_m2[11] =  src.m_m2[96];
	m_m2[12] =  src.m_m2[67];
	m_m2[13] =  src.m_m2[68];
	m_m2[14] =  src.m_m2[69];
	m_m2[15] =  src.m_m2[66];
}
} // end of class flatPointOM
