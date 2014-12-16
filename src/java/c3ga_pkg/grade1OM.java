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
 * Domain grade 1: no, e1, e2, e3, ni.
 * Domain grade 2: .
 * Domain grade 3: .
 * Domain grade 4: .
 * Domain grade 5: .
 * 
 * The range and domain are equal.
 * 
 */
public class grade1OM 
{ 
	/**
	 * Matrix for grade 1; the size is 5 x 5
	 */
	protected double[] m_m1 = new double[25];

	/** Constructs a new grade1OM, set to identity. */
	public grade1OM() { setIdentity(); }

	/** Copy constructor. */
	public grade1OM(final grade1OM M) { set(M); }

	/** Constructor from matrix. */
	public grade1OM(final double[] M) { set(M); }

	/** Constructor from matrix. */
	public grade1OM(final double[] M, final boolean transposed) { if (transposed) { setTranspose(M); } else { set(M); } }
	
	/** Constructor from images of basis vectors. */
	public grade1OM(final dualSphere ino, final dualSphere ie1, final dualSphere ie2, final dualSphere ie3, final dualSphere ini)
		{ set(ino, ie1, ie2, ie3, ini); }

	/** 
	 * Converts a om to a grade1OM.
	 * Warning: coordinates which cannot be represented are silenty lost.
	 */
	public grade1OM(final om M) { set(M); }




/**
 * Sets grade1OM to identity.
 */
public final void setIdentity()
{
	m_m1[0] = 1.0;
	m_m1[5] = m_m1[10] = m_m1[15] = m_m1[20] = 0.0;

	m_m1[1] = m_m1[11] = m_m1[16] = m_m1[21] = 0.0;
	m_m1[6] = 1.0;

	m_m1[2] = m_m1[7] = m_m1[17] = m_m1[22] = 0.0;
	m_m1[12] = 1.0;

	m_m1[3] = m_m1[8] = m_m1[13] = m_m1[23] = 0.0;
	m_m1[18] = 1.0;

	m_m1[4] = m_m1[9] = m_m1[14] = m_m1[19] = 0.0;
	m_m1[24] = 1.0;

}

/**
 * Copies grade1OM.
 */
public final void set(final grade1OM src)
{
	m_m1[0] = src.m_m1[0];
	m_m1[5] = src.m_m1[5];
	m_m1[10] = src.m_m1[10];
	m_m1[15] = src.m_m1[15];
	m_m1[20] = src.m_m1[20];

	m_m1[1] = src.m_m1[1];
	m_m1[6] = src.m_m1[6];
	m_m1[11] = src.m_m1[11];
	m_m1[16] = src.m_m1[16];
	m_m1[21] = src.m_m1[21];

	m_m1[2] = src.m_m1[2];
	m_m1[7] = src.m_m1[7];
	m_m1[12] = src.m_m1[12];
	m_m1[17] = src.m_m1[17];
	m_m1[22] = src.m_m1[22];

	m_m1[3] = src.m_m1[3];
	m_m1[8] = src.m_m1[8];
	m_m1[13] = src.m_m1[13];
	m_m1[18] = src.m_m1[18];
	m_m1[23] = src.m_m1[23];

	m_m1[4] = src.m_m1[4];
	m_m1[9] = src.m_m1[9];
	m_m1[14] = src.m_m1[14];
	m_m1[19] = src.m_m1[19];
	m_m1[24] = src.m_m1[24];

}
/**
 * Sets grade1OM from images of the domain vectors.
 */
public final void set(final dualSphere ino, final dualSphere ie1, final dualSphere ie2, final dualSphere ie3, final dualSphere ini)
{
	m_m1[0] = ino.m_no;
	m_m1[5] = ino.m_e1;
	m_m1[10] = ino.m_e2;
	m_m1[15] = ino.m_e3;
	m_m1[20] = ino.m_ni;

	m_m1[1] = ie1.m_no;
	m_m1[6] = ie1.m_e1;
	m_m1[11] = ie1.m_e2;
	m_m1[16] = ie1.m_e3;
	m_m1[21] = ie1.m_ni;

	m_m1[2] = ie2.m_no;
	m_m1[7] = ie2.m_e1;
	m_m1[12] = ie2.m_e2;
	m_m1[17] = ie2.m_e3;
	m_m1[22] = ie2.m_ni;

	m_m1[3] = ie3.m_no;
	m_m1[8] = ie3.m_e1;
	m_m1[13] = ie3.m_e2;
	m_m1[18] = ie3.m_e3;
	m_m1[23] = ie3.m_ni;

	m_m1[4] = ini.m_no;
	m_m1[9] = ini.m_e1;
	m_m1[14] = ini.m_e2;
	m_m1[19] = ini.m_e3;
	m_m1[24] = ini.m_ni;

}
/**
 * Sets grade1OM from a matrix.
 */
public final void set(final double[] M)
{
	m_m1[0] = M[0];
	m_m1[5] = M[5];
	m_m1[10] = M[10];
	m_m1[15] = M[15];
	m_m1[20] = M[20];

	m_m1[1] = M[1];
	m_m1[6] = M[6];
	m_m1[11] = M[11];
	m_m1[16] = M[16];
	m_m1[21] = M[21];

	m_m1[2] = M[2];
	m_m1[7] = M[7];
	m_m1[12] = M[12];
	m_m1[17] = M[17];
	m_m1[22] = M[22];

	m_m1[3] = M[3];
	m_m1[8] = M[8];
	m_m1[13] = M[13];
	m_m1[18] = M[18];
	m_m1[23] = M[23];

	m_m1[4] = M[4];
	m_m1[9] = M[9];
	m_m1[14] = M[14];
	m_m1[19] = M[19];
	m_m1[24] = M[24];

}
/**
 * Sets grade1OM from a transposed matrix.
 */
public final void setTranspose(final double[] M)
{
	m_m1[0] = M[0];
	m_m1[5] = M[1];
	m_m1[10] = M[2];
	m_m1[15] = M[3];
	m_m1[20] = M[4];

	m_m1[1] = M[5];
	m_m1[6] = M[6];
	m_m1[11] = M[7];
	m_m1[16] = M[8];
	m_m1[21] = M[9];

	m_m1[2] = M[10];
	m_m1[7] = M[11];
	m_m1[12] = M[12];
	m_m1[17] = M[13];
	m_m1[22] = M[14];

	m_m1[3] = M[15];
	m_m1[8] = M[16];
	m_m1[13] = M[17];
	m_m1[18] = M[18];
	m_m1[23] = M[19];

	m_m1[4] = M[20];
	m_m1[9] = M[21];
	m_m1[14] = M[22];
	m_m1[19] = M[23];
	m_m1[24] = M[24];

}
/**
 * Copies a om to a grade1OM
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(om src) {
	m_m1[0] =  src.m_m1[0];
	m_m1[1] =  src.m_m1[1];
	m_m1[2] =  src.m_m1[2];
	m_m1[3] =  src.m_m1[3];
	m_m1[4] =  src.m_m1[4];
	m_m1[5] =  src.m_m1[5];
	m_m1[6] =  src.m_m1[6];
	m_m1[7] =  src.m_m1[7];
	m_m1[8] =  src.m_m1[8];
	m_m1[9] =  src.m_m1[9];
	m_m1[10] =  src.m_m1[10];
	m_m1[11] =  src.m_m1[11];
	m_m1[12] =  src.m_m1[12];
	m_m1[13] =  src.m_m1[13];
	m_m1[14] =  src.m_m1[14];
	m_m1[15] =  src.m_m1[15];
	m_m1[16] =  src.m_m1[16];
	m_m1[17] =  src.m_m1[17];
	m_m1[18] =  src.m_m1[18];
	m_m1[19] =  src.m_m1[19];
	m_m1[20] =  src.m_m1[20];
	m_m1[21] =  src.m_m1[21];
	m_m1[22] =  src.m_m1[22];
	m_m1[23] =  src.m_m1[23];
	m_m1[24] =  src.m_m1[24];
}
} // end of class grade1OM
