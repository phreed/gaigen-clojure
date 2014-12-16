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
 * Domain grade 1: e1, e2, e3.
 * Domain grade 2: .
 * Domain grade 3: .
 * Domain grade 4: .
 * Domain grade 5: .
 * 
 * The range and domain are equal.
 * 
 */
public class grade1OM_E3GA 
{ 
	/**
	 * Matrix for grade 1; the size is 3 x 3
	 */
	protected double[] m_m1 = new double[9];

	/** Constructs a new grade1OM_E3GA, set to identity. */
	public grade1OM_E3GA() { setIdentity(); }

	/** Copy constructor. */
	public grade1OM_E3GA(final grade1OM_E3GA M) { set(M); }

	/** Constructor from matrix. */
	public grade1OM_E3GA(final double[] M) { set(M); }

	/** Constructor from matrix. */
	public grade1OM_E3GA(final double[] M, final boolean transposed) { if (transposed) { setTranspose(M); } else { set(M); } }
	
	/** Constructor from images of basis vectors. */
	public grade1OM_E3GA(final vectorE3GA ie1, final vectorE3GA ie2, final vectorE3GA ie3)
		{ set(ie1, ie2, ie3); }

	/** 
	 * Converts a om to a grade1OM_E3GA.
	 * Warning: coordinates which cannot be represented are silenty lost.
	 */
	public grade1OM_E3GA(final om M) { set(M); }




/**
 * Sets grade1OM_E3GA to identity.
 */
public final void setIdentity()
{
	m_m1[0] = 1.0;
	m_m1[3] = m_m1[6] = 0.0;

	m_m1[1] = m_m1[7] = 0.0;
	m_m1[4] = 1.0;

	m_m1[2] = m_m1[5] = 0.0;
	m_m1[8] = 1.0;

}

/**
 * Copies grade1OM_E3GA.
 */
public final void set(final grade1OM_E3GA src)
{
	m_m1[0] = src.m_m1[0];
	m_m1[3] = src.m_m1[3];
	m_m1[6] = src.m_m1[6];

	m_m1[1] = src.m_m1[1];
	m_m1[4] = src.m_m1[4];
	m_m1[7] = src.m_m1[7];

	m_m1[2] = src.m_m1[2];
	m_m1[5] = src.m_m1[5];
	m_m1[8] = src.m_m1[8];

}
/**
 * Sets grade1OM_E3GA from images of the domain vectors.
 */
public final void set(final vectorE3GA ie1, final vectorE3GA ie2, final vectorE3GA ie3)
{
	m_m1[0] = ie1.m_e1;
	m_m1[3] = ie1.m_e2;
	m_m1[6] = ie1.m_e3;

	m_m1[1] = ie2.m_e1;
	m_m1[4] = ie2.m_e2;
	m_m1[7] = ie2.m_e3;

	m_m1[2] = ie3.m_e1;
	m_m1[5] = ie3.m_e2;
	m_m1[8] = ie3.m_e3;

}
/**
 * Sets grade1OM_E3GA from a matrix.
 */
public final void set(final double[] M)
{
	m_m1[0] = M[0];
	m_m1[3] = M[3];
	m_m1[6] = M[6];

	m_m1[1] = M[1];
	m_m1[4] = M[4];
	m_m1[7] = M[7];

	m_m1[2] = M[2];
	m_m1[5] = M[5];
	m_m1[8] = M[8];

}
/**
 * Sets grade1OM_E3GA from a transposed matrix.
 */
public final void setTranspose(final double[] M)
{
	m_m1[0] = M[0];
	m_m1[3] = M[1];
	m_m1[6] = M[2];

	m_m1[1] = M[3];
	m_m1[4] = M[4];
	m_m1[7] = M[5];

	m_m1[2] = M[6];
	m_m1[5] = M[7];
	m_m1[8] = M[8];

}
/**
 * Copies a om to a grade1OM_E3GA
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(om src) {
	m_m1[0] =  src.m_m1[6];
	m_m1[1] =  src.m_m1[7];
	m_m1[2] =  src.m_m1[8];
	m_m1[3] =  src.m_m1[11];
	m_m1[4] =  src.m_m1[12];
	m_m1[5] =  src.m_m1[13];
	m_m1[6] =  src.m_m1[16];
	m_m1[7] =  src.m_m1[17];
	m_m1[8] =  src.m_m1[18];
}
} // end of class grade1OM_E3GA
