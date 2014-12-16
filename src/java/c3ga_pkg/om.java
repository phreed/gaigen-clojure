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
 * This class can hold a general outermorphism.
 * 
 * The coordinates are stored in type double.
 * 
 * There are 6 matrices, one for each grade.
 * The columns of these matrices are the range of the outermorphism.
 * Matrices are stored in row-major order. So the coordinates of rows are stored contiguously.
 * Domain grade 1: no, e1, e2, e3, ni.
 * Domain grade 2: no^e1, no^e2, e1^e2, no^e3, e1^e3, e2^e3, no^ni, e1^ni, e2^ni, e3^ni.
 * Domain grade 3: no^e1^e2, no^e1^e3, no^e2^e3, e1^e2^e3, no^e1^ni, no^e2^ni, e1^e2^ni, no^e3^ni, e1^e3^ni, e2^e3^ni.
 * Domain grade 4: no^e1^e2^e3, no^e1^e2^ni, no^e1^e3^ni, no^e2^e3^ni, e1^e2^e3^ni.
 * Domain grade 5: no^e1^e2^e3^ni.
 * 
 * The range and domain are equal.
 * 
 */
public class om 
{ 
	/**
	 * Matrix for grade 1; the size is 5 x 5
	 */
	protected double[] m_m1 = new double[25];
	/**
	 * Matrix for grade 2; the size is 10 x 10
	 */
	protected double[] m_m2 = new double[100];
	/**
	 * Matrix for grade 3; the size is 10 x 10
	 */
	protected double[] m_m3 = new double[100];
	/**
	 * Matrix for grade 4; the size is 5 x 5
	 */
	protected double[] m_m4 = new double[25];
	/**
	 * Matrix for grade 5; the size is 1 x 1
	 */
	protected double[] m_m5 = new double[1];

	/** Constructs a new om, set to identity. */
	public om() { setIdentity(); }

	/** Copy constructor. */
	public om(final om M) { set(M); }

	/** Constructor from matrix. */
	public om(final double[] M) { set(M); }

	/** Constructor from images of basis vectors. */
	public om(final  dualSphere ino,  dualSphere ie1,  dualSphere ie2,  dualSphere ie3,  dualSphere ini)
		{ set(ino, ie1, ie2, ie3, ini); }

	/** 
	 * Converts a grade1OM_E3GA to a om.
	 * Warning 1: coordinates which cannot be represented are silenty lost.
	 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
	 */
	public om(final grade1OM_E3GA M) { set(M); }
	
	/** 
	 * Converts a grade1OM to a om.
	 * Warning 1: coordinates which cannot be represented are silenty lost.
	 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
	 */
	public om(final grade1OM M) { set(M); }
	
	/** 
	 * Converts a flatPointOM to a om.
	 * Warning 1: coordinates which cannot be represented are silenty lost.
	 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
	 */
	public om(final flatPointOM M) { set(M); }
	

	public final void setIdentity() {
		c3ga.zero_N(m_m1, 25);

		c3ga.zero_N(m_m2, 100);

		c3ga.zero_N(m_m3, 100);

		c3ga.zero_N(m_m4, 25);

		c3ga.zero_1(m_m5);

		m_m1[0] = m_m1[6] = m_m1[12] = m_m1[18] = m_m1[24] = m_m2[0] = m_m2[11] = m_m2[22] = 
			m_m2[33] = m_m2[44] = m_m2[55] = m_m2[66] = m_m2[77] = m_m2[88] = m_m2[99] = m_m3[0] = 
			m_m3[11] = m_m3[22] = m_m3[33] = m_m3[44] = m_m3[55] = m_m3[66] = m_m3[77] = m_m3[88] = 
			m_m3[99] = m_m4[0] = m_m4[6] = m_m4[12] = m_m4[18] = m_m4[24] = m_m5[0] = 1.0;
	}

	void set(om src) {
		c3ga.copy_N(m_m1, src.m_m1, 25);

		c3ga.copy_N(m_m2, src.m_m2, 100);

		c3ga.copy_N(m_m3, src.m_m3, 100);

		c3ga.copy_N(m_m4, src.m_m4, 25);

		c3ga.copy_1(m_m5, src.m_m5);

	}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_0()
{
	m_m2[0] = (m_m1[0]*m_m1[6]-m_m1[1]*m_m1[5]);
	m_m2[10] = (m_m1[0]*m_m1[11]-m_m1[1]*m_m1[10]);
	m_m2[20] = (-m_m1[10]*m_m1[6]+m_m1[11]*m_m1[5]);
	m_m2[30] = (m_m1[0]*m_m1[16]-m_m1[1]*m_m1[15]);
	m_m2[40] = (-m_m1[15]*m_m1[6]+m_m1[16]*m_m1[5]);
	m_m2[50] = (m_m1[10]*m_m1[16]-m_m1[11]*m_m1[15]);
	m_m2[60] = (m_m1[0]*m_m1[21]-m_m1[1]*m_m1[20]);
	m_m2[70] = (-m_m1[20]*m_m1[6]+m_m1[21]*m_m1[5]);
	m_m2[80] = (m_m1[10]*m_m1[21]-m_m1[11]*m_m1[20]);
	m_m2[90] = (m_m1[15]*m_m1[21]-m_m1[16]*m_m1[20]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_1()
{
	m_m2[1] = (m_m1[0]*m_m1[7]-m_m1[2]*m_m1[5]);
	m_m2[11] = (m_m1[0]*m_m1[12]-m_m1[10]*m_m1[2]);
	m_m2[21] = (-m_m1[10]*m_m1[7]+m_m1[12]*m_m1[5]);
	m_m2[31] = (m_m1[0]*m_m1[17]-m_m1[15]*m_m1[2]);
	m_m2[41] = (-m_m1[15]*m_m1[7]+m_m1[17]*m_m1[5]);
	m_m2[51] = (m_m1[10]*m_m1[17]-m_m1[12]*m_m1[15]);
	m_m2[61] = (m_m1[0]*m_m1[22]-m_m1[2]*m_m1[20]);
	m_m2[71] = (-m_m1[20]*m_m1[7]+m_m1[22]*m_m1[5]);
	m_m2[81] = (m_m1[10]*m_m1[22]-m_m1[12]*m_m1[20]);
	m_m2[91] = (m_m1[15]*m_m1[22]-m_m1[17]*m_m1[20]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_2()
{
	m_m2[2] = (m_m1[1]*m_m1[7]-m_m1[2]*m_m1[6]);
	m_m2[12] = (m_m1[1]*m_m1[12]-m_m1[11]*m_m1[2]);
	m_m2[22] = (-m_m1[11]*m_m1[7]+m_m1[12]*m_m1[6]);
	m_m2[32] = (m_m1[1]*m_m1[17]-m_m1[16]*m_m1[2]);
	m_m2[42] = (-m_m1[16]*m_m1[7]+m_m1[17]*m_m1[6]);
	m_m2[52] = (m_m1[11]*m_m1[17]-m_m1[12]*m_m1[16]);
	m_m2[62] = (m_m1[1]*m_m1[22]-m_m1[2]*m_m1[21]);
	m_m2[72] = (-m_m1[21]*m_m1[7]+m_m1[22]*m_m1[6]);
	m_m2[82] = (m_m1[11]*m_m1[22]-m_m1[12]*m_m1[21]);
	m_m2[92] = (m_m1[16]*m_m1[22]-m_m1[17]*m_m1[21]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_3()
{
	m_m2[3] = (m_m1[0]*m_m1[8]-m_m1[3]*m_m1[5]);
	m_m2[13] = (m_m1[0]*m_m1[13]-m_m1[10]*m_m1[3]);
	m_m2[23] = (-m_m1[10]*m_m1[8]+m_m1[13]*m_m1[5]);
	m_m2[33] = (m_m1[0]*m_m1[18]-m_m1[15]*m_m1[3]);
	m_m2[43] = (-m_m1[15]*m_m1[8]+m_m1[18]*m_m1[5]);
	m_m2[53] = (m_m1[10]*m_m1[18]-m_m1[13]*m_m1[15]);
	m_m2[63] = (m_m1[0]*m_m1[23]-m_m1[20]*m_m1[3]);
	m_m2[73] = (-m_m1[20]*m_m1[8]+m_m1[23]*m_m1[5]);
	m_m2[83] = (m_m1[10]*m_m1[23]-m_m1[13]*m_m1[20]);
	m_m2[93] = (m_m1[15]*m_m1[23]-m_m1[18]*m_m1[20]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_4()
{
	m_m2[4] = (m_m1[1]*m_m1[8]-m_m1[3]*m_m1[6]);
	m_m2[14] = (m_m1[1]*m_m1[13]-m_m1[11]*m_m1[3]);
	m_m2[24] = (-m_m1[11]*m_m1[8]+m_m1[13]*m_m1[6]);
	m_m2[34] = (m_m1[1]*m_m1[18]-m_m1[16]*m_m1[3]);
	m_m2[44] = (-m_m1[16]*m_m1[8]+m_m1[18]*m_m1[6]);
	m_m2[54] = (m_m1[11]*m_m1[18]-m_m1[13]*m_m1[16]);
	m_m2[64] = (m_m1[1]*m_m1[23]-m_m1[21]*m_m1[3]);
	m_m2[74] = (-m_m1[21]*m_m1[8]+m_m1[23]*m_m1[6]);
	m_m2[84] = (m_m1[11]*m_m1[23]-m_m1[13]*m_m1[21]);
	m_m2[94] = (m_m1[16]*m_m1[23]-m_m1[18]*m_m1[21]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_5()
{
	m_m2[5] = (m_m1[2]*m_m1[8]-m_m1[3]*m_m1[7]);
	m_m2[15] = (-m_m1[12]*m_m1[3]+m_m1[13]*m_m1[2]);
	m_m2[25] = (-m_m1[12]*m_m1[8]+m_m1[13]*m_m1[7]);
	m_m2[35] = (-m_m1[17]*m_m1[3]+m_m1[18]*m_m1[2]);
	m_m2[45] = (-m_m1[17]*m_m1[8]+m_m1[18]*m_m1[7]);
	m_m2[55] = (m_m1[12]*m_m1[18]-m_m1[13]*m_m1[17]);
	m_m2[65] = (m_m1[2]*m_m1[23]-m_m1[22]*m_m1[3]);
	m_m2[75] = (-m_m1[22]*m_m1[8]+m_m1[23]*m_m1[7]);
	m_m2[85] = (m_m1[12]*m_m1[23]-m_m1[13]*m_m1[22]);
	m_m2[95] = (m_m1[17]*m_m1[23]-m_m1[18]*m_m1[22]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_6()
{
	m_m2[6] = (m_m1[0]*m_m1[9]-m_m1[4]*m_m1[5]);
	m_m2[16] = (m_m1[0]*m_m1[14]-m_m1[10]*m_m1[4]);
	m_m2[26] = (-m_m1[10]*m_m1[9]+m_m1[14]*m_m1[5]);
	m_m2[36] = (m_m1[0]*m_m1[19]-m_m1[15]*m_m1[4]);
	m_m2[46] = (-m_m1[15]*m_m1[9]+m_m1[19]*m_m1[5]);
	m_m2[56] = (m_m1[10]*m_m1[19]-m_m1[14]*m_m1[15]);
	m_m2[66] = (m_m1[0]*m_m1[24]-m_m1[20]*m_m1[4]);
	m_m2[76] = (-m_m1[20]*m_m1[9]+m_m1[24]*m_m1[5]);
	m_m2[86] = (m_m1[10]*m_m1[24]-m_m1[14]*m_m1[20]);
	m_m2[96] = (m_m1[15]*m_m1[24]-m_m1[19]*m_m1[20]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_7()
{
	m_m2[7] = (m_m1[1]*m_m1[9]-m_m1[4]*m_m1[6]);
	m_m2[17] = (m_m1[1]*m_m1[14]-m_m1[11]*m_m1[4]);
	m_m2[27] = (-m_m1[11]*m_m1[9]+m_m1[14]*m_m1[6]);
	m_m2[37] = (m_m1[1]*m_m1[19]-m_m1[16]*m_m1[4]);
	m_m2[47] = (-m_m1[16]*m_m1[9]+m_m1[19]*m_m1[6]);
	m_m2[57] = (m_m1[11]*m_m1[19]-m_m1[14]*m_m1[16]);
	m_m2[67] = (m_m1[1]*m_m1[24]-m_m1[21]*m_m1[4]);
	m_m2[77] = (-m_m1[21]*m_m1[9]+m_m1[24]*m_m1[6]);
	m_m2[87] = (m_m1[11]*m_m1[24]-m_m1[14]*m_m1[21]);
	m_m2[97] = (m_m1[16]*m_m1[24]-m_m1[19]*m_m1[21]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_8()
{
	m_m2[8] = (m_m1[2]*m_m1[9]-m_m1[4]*m_m1[7]);
	m_m2[18] = (-m_m1[12]*m_m1[4]+m_m1[14]*m_m1[2]);
	m_m2[28] = (-m_m1[12]*m_m1[9]+m_m1[14]*m_m1[7]);
	m_m2[38] = (-m_m1[17]*m_m1[4]+m_m1[19]*m_m1[2]);
	m_m2[48] = (-m_m1[17]*m_m1[9]+m_m1[19]*m_m1[7]);
	m_m2[58] = (m_m1[12]*m_m1[19]-m_m1[14]*m_m1[17]);
	m_m2[68] = (m_m1[2]*m_m1[24]-m_m1[22]*m_m1[4]);
	m_m2[78] = (-m_m1[22]*m_m1[9]+m_m1[24]*m_m1[7]);
	m_m2[88] = (m_m1[12]*m_m1[24]-m_m1[14]*m_m1[22]);
	m_m2[98] = (m_m1[17]*m_m1[24]-m_m1[19]*m_m1[22]);

}
/**
 * Sets grade 2 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_2_9()
{
	m_m2[9] = (m_m1[3]*m_m1[9]-m_m1[4]*m_m1[8]);
	m_m2[19] = (-m_m1[13]*m_m1[4]+m_m1[14]*m_m1[3]);
	m_m2[29] = (-m_m1[13]*m_m1[9]+m_m1[14]*m_m1[8]);
	m_m2[39] = (-m_m1[18]*m_m1[4]+m_m1[19]*m_m1[3]);
	m_m2[49] = (-m_m1[18]*m_m1[9]+m_m1[19]*m_m1[8]);
	m_m2[59] = (m_m1[13]*m_m1[19]-m_m1[14]*m_m1[18]);
	m_m2[69] = (-m_m1[23]*m_m1[4]+m_m1[24]*m_m1[3]);
	m_m2[79] = (-m_m1[23]*m_m1[9]+m_m1[24]*m_m1[8]);
	m_m2[89] = (m_m1[13]*m_m1[24]-m_m1[14]*m_m1[23]);
	m_m2[99] = (m_m1[18]*m_m1[24]-m_m1[19]*m_m1[23]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_0()
{
	m_m3[0] = (m_m1[0]*m_m2[22]+m_m1[10]*m_m2[2]-m_m1[5]*m_m2[12]);
	m_m3[10] = (m_m1[0]*m_m2[42]+m_m1[15]*m_m2[2]-m_m1[5]*m_m2[32]);
	m_m3[20] = (m_m1[0]*m_m2[52]-m_m1[10]*m_m2[32]+m_m1[15]*m_m2[12]);
	m_m3[30] = (-m_m1[10]*m_m2[42]+m_m1[15]*m_m2[22]+m_m1[5]*m_m2[52]);
	m_m3[40] = (m_m1[0]*m_m2[72]+m_m1[20]*m_m2[2]-m_m1[5]*m_m2[62]);
	m_m3[50] = (m_m1[0]*m_m2[82]-m_m1[10]*m_m2[62]+m_m1[20]*m_m2[12]);
	m_m3[60] = (-m_m1[10]*m_m2[72]+m_m1[20]*m_m2[22]+m_m1[5]*m_m2[82]);
	m_m3[70] = (m_m1[0]*m_m2[92]-m_m1[15]*m_m2[62]+m_m1[20]*m_m2[32]);
	m_m3[80] = (-m_m1[15]*m_m2[72]+m_m1[20]*m_m2[42]+m_m1[5]*m_m2[92]);
	m_m3[90] = (m_m1[10]*m_m2[92]-m_m1[15]*m_m2[82]+m_m1[20]*m_m2[52]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_1()
{
	m_m3[1] = (m_m1[0]*m_m2[24]+m_m1[10]*m_m2[4]-m_m1[5]*m_m2[14]);
	m_m3[11] = (m_m1[0]*m_m2[44]+m_m1[15]*m_m2[4]-m_m1[5]*m_m2[34]);
	m_m3[21] = (m_m1[0]*m_m2[54]-m_m1[10]*m_m2[34]+m_m1[15]*m_m2[14]);
	m_m3[31] = (-m_m1[10]*m_m2[44]+m_m1[15]*m_m2[24]+m_m1[5]*m_m2[54]);
	m_m3[41] = (m_m1[0]*m_m2[74]+m_m1[20]*m_m2[4]-m_m1[5]*m_m2[64]);
	m_m3[51] = (m_m1[0]*m_m2[84]-m_m1[10]*m_m2[64]+m_m1[20]*m_m2[14]);
	m_m3[61] = (-m_m1[10]*m_m2[74]+m_m1[20]*m_m2[24]+m_m1[5]*m_m2[84]);
	m_m3[71] = (m_m1[0]*m_m2[94]-m_m1[15]*m_m2[64]+m_m1[20]*m_m2[34]);
	m_m3[81] = (-m_m1[15]*m_m2[74]+m_m1[20]*m_m2[44]+m_m1[5]*m_m2[94]);
	m_m3[91] = (m_m1[10]*m_m2[94]-m_m1[15]*m_m2[84]+m_m1[20]*m_m2[54]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_2()
{
	m_m3[2] = (m_m1[0]*m_m2[25]+m_m1[10]*m_m2[5]-m_m1[5]*m_m2[15]);
	m_m3[12] = (m_m1[0]*m_m2[45]+m_m1[15]*m_m2[5]-m_m1[5]*m_m2[35]);
	m_m3[22] = (m_m1[0]*m_m2[55]-m_m1[10]*m_m2[35]+m_m1[15]*m_m2[15]);
	m_m3[32] = (-m_m1[10]*m_m2[45]+m_m1[15]*m_m2[25]+m_m1[5]*m_m2[55]);
	m_m3[42] = (m_m1[0]*m_m2[75]+m_m1[20]*m_m2[5]-m_m1[5]*m_m2[65]);
	m_m3[52] = (m_m1[0]*m_m2[85]-m_m1[10]*m_m2[65]+m_m1[20]*m_m2[15]);
	m_m3[62] = (-m_m1[10]*m_m2[75]+m_m1[20]*m_m2[25]+m_m1[5]*m_m2[85]);
	m_m3[72] = (m_m1[0]*m_m2[95]-m_m1[15]*m_m2[65]+m_m1[20]*m_m2[35]);
	m_m3[82] = (-m_m1[15]*m_m2[75]+m_m1[20]*m_m2[45]+m_m1[5]*m_m2[95]);
	m_m3[92] = (m_m1[10]*m_m2[95]-m_m1[15]*m_m2[85]+m_m1[20]*m_m2[55]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_3()
{
	m_m3[3] = (m_m1[1]*m_m2[25]+m_m1[11]*m_m2[5]-m_m1[6]*m_m2[15]);
	m_m3[13] = (m_m1[1]*m_m2[45]+m_m1[16]*m_m2[5]-m_m1[6]*m_m2[35]);
	m_m3[23] = (m_m1[1]*m_m2[55]-m_m1[11]*m_m2[35]+m_m1[16]*m_m2[15]);
	m_m3[33] = (-m_m1[11]*m_m2[45]+m_m1[16]*m_m2[25]+m_m1[6]*m_m2[55]);
	m_m3[43] = (m_m1[1]*m_m2[75]+m_m1[21]*m_m2[5]-m_m1[6]*m_m2[65]);
	m_m3[53] = (m_m1[1]*m_m2[85]-m_m1[11]*m_m2[65]+m_m1[21]*m_m2[15]);
	m_m3[63] = (-m_m1[11]*m_m2[75]+m_m1[21]*m_m2[25]+m_m1[6]*m_m2[85]);
	m_m3[73] = (m_m1[1]*m_m2[95]-m_m1[16]*m_m2[65]+m_m1[21]*m_m2[35]);
	m_m3[83] = (-m_m1[16]*m_m2[75]+m_m1[21]*m_m2[45]+m_m1[6]*m_m2[95]);
	m_m3[93] = (m_m1[11]*m_m2[95]-m_m1[16]*m_m2[85]+m_m1[21]*m_m2[55]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_4()
{
	m_m3[4] = (m_m1[0]*m_m2[27]+m_m1[10]*m_m2[7]-m_m1[5]*m_m2[17]);
	m_m3[14] = (m_m1[0]*m_m2[47]+m_m1[15]*m_m2[7]-m_m1[5]*m_m2[37]);
	m_m3[24] = (m_m1[0]*m_m2[57]-m_m1[10]*m_m2[37]+m_m1[15]*m_m2[17]);
	m_m3[34] = (-m_m1[10]*m_m2[47]+m_m1[15]*m_m2[27]+m_m1[5]*m_m2[57]);
	m_m3[44] = (m_m1[0]*m_m2[77]+m_m1[20]*m_m2[7]-m_m1[5]*m_m2[67]);
	m_m3[54] = (m_m1[0]*m_m2[87]-m_m1[10]*m_m2[67]+m_m1[20]*m_m2[17]);
	m_m3[64] = (-m_m1[10]*m_m2[77]+m_m1[20]*m_m2[27]+m_m1[5]*m_m2[87]);
	m_m3[74] = (m_m1[0]*m_m2[97]-m_m1[15]*m_m2[67]+m_m1[20]*m_m2[37]);
	m_m3[84] = (-m_m1[15]*m_m2[77]+m_m1[20]*m_m2[47]+m_m1[5]*m_m2[97]);
	m_m3[94] = (m_m1[10]*m_m2[97]-m_m1[15]*m_m2[87]+m_m1[20]*m_m2[57]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_5()
{
	m_m3[5] = (m_m1[0]*m_m2[28]+m_m1[10]*m_m2[8]-m_m1[5]*m_m2[18]);
	m_m3[15] = (m_m1[0]*m_m2[48]+m_m1[15]*m_m2[8]-m_m1[5]*m_m2[38]);
	m_m3[25] = (m_m1[0]*m_m2[58]-m_m1[10]*m_m2[38]+m_m1[15]*m_m2[18]);
	m_m3[35] = (-m_m1[10]*m_m2[48]+m_m1[15]*m_m2[28]+m_m1[5]*m_m2[58]);
	m_m3[45] = (m_m1[0]*m_m2[78]+m_m1[20]*m_m2[8]-m_m1[5]*m_m2[68]);
	m_m3[55] = (m_m1[0]*m_m2[88]-m_m1[10]*m_m2[68]+m_m1[20]*m_m2[18]);
	m_m3[65] = (-m_m1[10]*m_m2[78]+m_m1[20]*m_m2[28]+m_m1[5]*m_m2[88]);
	m_m3[75] = (m_m1[0]*m_m2[98]-m_m1[15]*m_m2[68]+m_m1[20]*m_m2[38]);
	m_m3[85] = (-m_m1[15]*m_m2[78]+m_m1[20]*m_m2[48]+m_m1[5]*m_m2[98]);
	m_m3[95] = (m_m1[10]*m_m2[98]-m_m1[15]*m_m2[88]+m_m1[20]*m_m2[58]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_6()
{
	m_m3[6] = (m_m1[1]*m_m2[28]+m_m1[11]*m_m2[8]-m_m1[6]*m_m2[18]);
	m_m3[16] = (m_m1[1]*m_m2[48]+m_m1[16]*m_m2[8]-m_m1[6]*m_m2[38]);
	m_m3[26] = (m_m1[1]*m_m2[58]-m_m1[11]*m_m2[38]+m_m1[16]*m_m2[18]);
	m_m3[36] = (-m_m1[11]*m_m2[48]+m_m1[16]*m_m2[28]+m_m1[6]*m_m2[58]);
	m_m3[46] = (m_m1[1]*m_m2[78]+m_m1[21]*m_m2[8]-m_m1[6]*m_m2[68]);
	m_m3[56] = (m_m1[1]*m_m2[88]-m_m1[11]*m_m2[68]+m_m1[21]*m_m2[18]);
	m_m3[66] = (-m_m1[11]*m_m2[78]+m_m1[21]*m_m2[28]+m_m1[6]*m_m2[88]);
	m_m3[76] = (m_m1[1]*m_m2[98]-m_m1[16]*m_m2[68]+m_m1[21]*m_m2[38]);
	m_m3[86] = (-m_m1[16]*m_m2[78]+m_m1[21]*m_m2[48]+m_m1[6]*m_m2[98]);
	m_m3[96] = (m_m1[11]*m_m2[98]-m_m1[16]*m_m2[88]+m_m1[21]*m_m2[58]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_7()
{
	m_m3[7] = (m_m1[0]*m_m2[29]+m_m1[10]*m_m2[9]-m_m1[5]*m_m2[19]);
	m_m3[17] = (m_m1[0]*m_m2[49]+m_m1[15]*m_m2[9]-m_m1[5]*m_m2[39]);
	m_m3[27] = (m_m1[0]*m_m2[59]-m_m1[10]*m_m2[39]+m_m1[15]*m_m2[19]);
	m_m3[37] = (-m_m1[10]*m_m2[49]+m_m1[15]*m_m2[29]+m_m1[5]*m_m2[59]);
	m_m3[47] = (m_m1[0]*m_m2[79]+m_m1[20]*m_m2[9]-m_m1[5]*m_m2[69]);
	m_m3[57] = (m_m1[0]*m_m2[89]-m_m1[10]*m_m2[69]+m_m1[20]*m_m2[19]);
	m_m3[67] = (-m_m1[10]*m_m2[79]+m_m1[20]*m_m2[29]+m_m1[5]*m_m2[89]);
	m_m3[77] = (m_m1[0]*m_m2[99]-m_m1[15]*m_m2[69]+m_m1[20]*m_m2[39]);
	m_m3[87] = (-m_m1[15]*m_m2[79]+m_m1[20]*m_m2[49]+m_m1[5]*m_m2[99]);
	m_m3[97] = (m_m1[10]*m_m2[99]-m_m1[15]*m_m2[89]+m_m1[20]*m_m2[59]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_8()
{
	m_m3[8] = (m_m1[1]*m_m2[29]+m_m1[11]*m_m2[9]-m_m1[6]*m_m2[19]);
	m_m3[18] = (m_m1[1]*m_m2[49]+m_m1[16]*m_m2[9]-m_m1[6]*m_m2[39]);
	m_m3[28] = (m_m1[1]*m_m2[59]-m_m1[11]*m_m2[39]+m_m1[16]*m_m2[19]);
	m_m3[38] = (-m_m1[11]*m_m2[49]+m_m1[16]*m_m2[29]+m_m1[6]*m_m2[59]);
	m_m3[48] = (m_m1[1]*m_m2[79]+m_m1[21]*m_m2[9]-m_m1[6]*m_m2[69]);
	m_m3[58] = (m_m1[1]*m_m2[89]-m_m1[11]*m_m2[69]+m_m1[21]*m_m2[19]);
	m_m3[68] = (-m_m1[11]*m_m2[79]+m_m1[21]*m_m2[29]+m_m1[6]*m_m2[89]);
	m_m3[78] = (m_m1[1]*m_m2[99]-m_m1[16]*m_m2[69]+m_m1[21]*m_m2[39]);
	m_m3[88] = (-m_m1[16]*m_m2[79]+m_m1[21]*m_m2[49]+m_m1[6]*m_m2[99]);
	m_m3[98] = (m_m1[11]*m_m2[99]-m_m1[16]*m_m2[89]+m_m1[21]*m_m2[59]);

}
/**
 * Sets grade 3 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_3_9()
{
	m_m3[9] = (m_m1[12]*m_m2[9]+m_m1[2]*m_m2[29]-m_m1[7]*m_m2[19]);
	m_m3[19] = (m_m1[17]*m_m2[9]+m_m1[2]*m_m2[49]-m_m1[7]*m_m2[39]);
	m_m3[29] = (-m_m1[12]*m_m2[39]+m_m1[17]*m_m2[19]+m_m1[2]*m_m2[59]);
	m_m3[39] = (-m_m1[12]*m_m2[49]+m_m1[17]*m_m2[29]+m_m1[7]*m_m2[59]);
	m_m3[49] = (m_m1[2]*m_m2[79]+m_m1[22]*m_m2[9]-m_m1[7]*m_m2[69]);
	m_m3[59] = (-m_m1[12]*m_m2[69]+m_m1[2]*m_m2[89]+m_m1[22]*m_m2[19]);
	m_m3[69] = (-m_m1[12]*m_m2[79]+m_m1[22]*m_m2[29]+m_m1[7]*m_m2[89]);
	m_m3[79] = (-m_m1[17]*m_m2[69]+m_m1[2]*m_m2[99]+m_m1[22]*m_m2[39]);
	m_m3[89] = (-m_m1[17]*m_m2[79]+m_m1[22]*m_m2[49]+m_m1[7]*m_m2[99]);
	m_m3[99] = (m_m1[12]*m_m2[99]-m_m1[17]*m_m2[89]+m_m1[22]*m_m2[59]);

}
/**
 * Sets grade 4 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_4_0()
{
	m_m4[0] = (m_m1[0]*m_m3[33]+m_m1[10]*m_m3[13]-m_m1[15]*m_m3[3]-m_m1[5]*m_m3[23]);
	m_m4[5] = (m_m1[0]*m_m3[63]+m_m1[10]*m_m3[43]-m_m1[20]*m_m3[3]-m_m1[5]*m_m3[53]);
	m_m4[10] = (m_m1[0]*m_m3[83]+m_m1[15]*m_m3[43]-m_m1[20]*m_m3[13]-m_m1[5]*m_m3[73]);
	m_m4[15] = (m_m1[0]*m_m3[93]-m_m1[10]*m_m3[73]+m_m1[15]*m_m3[53]-m_m1[20]*m_m3[23]);
	m_m4[20] = (-m_m1[10]*m_m3[83]+m_m1[15]*m_m3[63]-m_m1[20]*m_m3[33]+m_m1[5]*m_m3[93]);

}
/**
 * Sets grade 4 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_4_1()
{
	m_m4[1] = (m_m1[0]*m_m3[36]+m_m1[10]*m_m3[16]-m_m1[15]*m_m3[6]-m_m1[5]*m_m3[26]);
	m_m4[6] = (m_m1[0]*m_m3[66]+m_m1[10]*m_m3[46]-m_m1[20]*m_m3[6]-m_m1[5]*m_m3[56]);
	m_m4[11] = (m_m1[0]*m_m3[86]+m_m1[15]*m_m3[46]-m_m1[20]*m_m3[16]-m_m1[5]*m_m3[76]);
	m_m4[16] = (m_m1[0]*m_m3[96]-m_m1[10]*m_m3[76]+m_m1[15]*m_m3[56]-m_m1[20]*m_m3[26]);
	m_m4[21] = (-m_m1[10]*m_m3[86]+m_m1[15]*m_m3[66]-m_m1[20]*m_m3[36]+m_m1[5]*m_m3[96]);

}
/**
 * Sets grade 4 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_4_2()
{
	m_m4[2] = (m_m1[0]*m_m3[38]+m_m1[10]*m_m3[18]-m_m1[15]*m_m3[8]-m_m1[5]*m_m3[28]);
	m_m4[7] = (m_m1[0]*m_m3[68]+m_m1[10]*m_m3[48]-m_m1[20]*m_m3[8]-m_m1[5]*m_m3[58]);
	m_m4[12] = (m_m1[0]*m_m3[88]+m_m1[15]*m_m3[48]-m_m1[20]*m_m3[18]-m_m1[5]*m_m3[78]);
	m_m4[17] = (m_m1[0]*m_m3[98]-m_m1[10]*m_m3[78]+m_m1[15]*m_m3[58]-m_m1[20]*m_m3[28]);
	m_m4[22] = (-m_m1[10]*m_m3[88]+m_m1[15]*m_m3[68]-m_m1[20]*m_m3[38]+m_m1[5]*m_m3[98]);

}
/**
 * Sets grade 4 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_4_3()
{
	m_m4[3] = (m_m1[0]*m_m3[39]+m_m1[10]*m_m3[19]-m_m1[15]*m_m3[9]-m_m1[5]*m_m3[29]);
	m_m4[8] = (m_m1[0]*m_m3[69]+m_m1[10]*m_m3[49]-m_m1[20]*m_m3[9]-m_m1[5]*m_m3[59]);
	m_m4[13] = (m_m1[0]*m_m3[89]+m_m1[15]*m_m3[49]-m_m1[20]*m_m3[19]-m_m1[5]*m_m3[79]);
	m_m4[18] = (m_m1[0]*m_m3[99]-m_m1[10]*m_m3[79]+m_m1[15]*m_m3[59]-m_m1[20]*m_m3[29]);
	m_m4[23] = (-m_m1[10]*m_m3[89]+m_m1[15]*m_m3[69]-m_m1[20]*m_m3[39]+m_m1[5]*m_m3[99]);

}
/**
 * Sets grade 4 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_4_4()
{
	m_m4[4] = (m_m1[1]*m_m3[39]+m_m1[11]*m_m3[19]-m_m1[16]*m_m3[9]-m_m1[6]*m_m3[29]);
	m_m4[9] = (m_m1[1]*m_m3[69]+m_m1[11]*m_m3[49]-m_m1[21]*m_m3[9]-m_m1[6]*m_m3[59]);
	m_m4[14] = (m_m1[1]*m_m3[89]+m_m1[16]*m_m3[49]-m_m1[21]*m_m3[19]-m_m1[6]*m_m3[79]);
	m_m4[19] = (m_m1[1]*m_m3[99]-m_m1[11]*m_m3[79]+m_m1[16]*m_m3[59]-m_m1[21]*m_m3[29]);
	m_m4[24] = (-m_m1[11]*m_m3[89]+m_m1[16]*m_m3[69]-m_m1[21]*m_m3[39]+m_m1[6]*m_m3[99]);

}
/**
 * Sets grade 5 part of outermorphism matrix based on lower grade parts.
 */
public final void set_grade_5_0()
{
	m_m5[0] = (m_m1[0]*m_m4[24]+m_m1[10]*m_m4[14]-m_m1[15]*m_m4[9]+m_m1[20]*m_m4[4]-m_m1[5]*m_m4[19]);

}
/**
 * Sets om from images of the domain vectors.
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

	set_grade_2_0();
	set_grade_2_1();
	set_grade_2_2();
	set_grade_2_3();
	set_grade_2_4();
	set_grade_2_5();
	set_grade_2_6();
	set_grade_2_7();
	set_grade_2_8();
	set_grade_2_9();
	set_grade_3_0();
	set_grade_3_1();
	set_grade_3_2();
	set_grade_3_3();
	set_grade_3_4();
	set_grade_3_5();
	set_grade_3_6();
	set_grade_3_7();
	set_grade_3_8();
	set_grade_3_9();
	set_grade_4_0();
	set_grade_4_1();
	set_grade_4_2();
	set_grade_4_3();
	set_grade_4_4();
	set_grade_5_0();
}
/**
 * Sets om from a matrix
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

	set_grade_2_0();
	set_grade_2_1();
	set_grade_2_2();
	set_grade_2_3();
	set_grade_2_4();
	set_grade_2_5();
	set_grade_2_6();
	set_grade_2_7();
	set_grade_2_8();
	set_grade_2_9();
	set_grade_3_0();
	set_grade_3_1();
	set_grade_3_2();
	set_grade_3_3();
	set_grade_3_4();
	set_grade_3_5();
	set_grade_3_6();
	set_grade_3_7();
	set_grade_3_8();
	set_grade_3_9();
	set_grade_4_0();
	set_grade_4_1();
	set_grade_4_2();
	set_grade_4_3();
	set_grade_4_4();
	set_grade_5_0();
}
/**
 * Copies a grade1OM_E3GA to a om
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(grade1OM_E3GA src) {
	m_m1[6] =  src.m_m1[0];
	m_m1[7] =  src.m_m1[1];
	m_m1[8] =  src.m_m1[2];
	m_m1[11] =  src.m_m1[3];
	m_m1[12] =  src.m_m1[4];
	m_m1[13] =  src.m_m1[5];
	m_m1[16] =  src.m_m1[6];
	m_m1[17] =  src.m_m1[7];
	m_m1[18] =  src.m_m1[8];
	m_m1[0] = m_m1[1] = m_m1[2] = m_m1[3] = m_m1[4] = m_m1[5] = m_m1[9] = m_m1[10] = m_m1[14] = 
		m_m1[15] = m_m1[19] = m_m1[20] = m_m1[21] = m_m1[22] = m_m1[23] = m_m1[24] = m_m2[0] = m_m2[1] = 
		m_m2[2] = m_m2[3] = m_m2[4] = m_m2[5] = m_m2[6] = m_m2[7] = m_m2[8] = m_m2[9] = m_m2[10] = 
		m_m2[11] = m_m2[12] = m_m2[13] = m_m2[14] = m_m2[15] = m_m2[16] = m_m2[17] = m_m2[18] = m_m2[19] = 
		m_m2[20] = m_m2[21] = m_m2[22] = m_m2[23] = m_m2[24] = m_m2[25] = m_m2[26] = m_m2[27] = m_m2[28] = 
		m_m2[29] = m_m2[30] = m_m2[31] = m_m2[32] = m_m2[33] = m_m2[34] = m_m2[35] = m_m2[36] = m_m2[37] = 
		m_m2[38] = m_m2[39] = m_m2[40] = m_m2[41] = m_m2[42] = m_m2[43] = m_m2[44] = m_m2[45] = m_m2[46] = 
		m_m2[47] = m_m2[48] = m_m2[49] = m_m2[50] = m_m2[51] = m_m2[52] = m_m2[53] = m_m2[54] = m_m2[55] = 
		m_m2[56] = m_m2[57] = m_m2[58] = m_m2[59] = m_m2[60] = m_m2[61] = m_m2[62] = m_m2[63] = m_m2[64] = 
		m_m2[65] = m_m2[66] = m_m2[67] = m_m2[68] = m_m2[69] = m_m2[70] = m_m2[71] = m_m2[72] = m_m2[73] = 
		m_m2[74] = m_m2[75] = m_m2[76] = m_m2[77] = m_m2[78] = m_m2[79] = m_m2[80] = m_m2[81] = m_m2[82] = 
		m_m2[83] = m_m2[84] = m_m2[85] = m_m2[86] = m_m2[87] = m_m2[88] = m_m2[89] = m_m2[90] = m_m2[91] = 
		m_m2[92] = m_m2[93] = m_m2[94] = m_m2[95] = m_m2[96] = m_m2[97] = m_m2[98] = m_m2[99] = m_m3[0] = 
		m_m3[1] = m_m3[2] = m_m3[3] = m_m3[4] = m_m3[5] = m_m3[6] = m_m3[7] = m_m3[8] = m_m3[9] = 
		m_m3[10] = m_m3[11] = m_m3[12] = m_m3[13] = m_m3[14] = m_m3[15] = m_m3[16] = m_m3[17] = m_m3[18] = 
		m_m3[19] = m_m3[20] = m_m3[21] = m_m3[22] = m_m3[23] = m_m3[24] = m_m3[25] = m_m3[26] = m_m3[27] = 
		m_m3[28] = m_m3[29] = m_m3[30] = m_m3[31] = m_m3[32] = m_m3[33] = m_m3[34] = m_m3[35] = m_m3[36] = 
		m_m3[37] = m_m3[38] = m_m3[39] = m_m3[40] = m_m3[41] = m_m3[42] = m_m3[43] = m_m3[44] = m_m3[45] = 
		m_m3[46] = m_m3[47] = m_m3[48] = m_m3[49] = m_m3[50] = m_m3[51] = m_m3[52] = m_m3[53] = m_m3[54] = 
		m_m3[55] = m_m3[56] = m_m3[57] = m_m3[58] = m_m3[59] = m_m3[60] = m_m3[61] = m_m3[62] = m_m3[63] = 
		m_m3[64] = m_m3[65] = m_m3[66] = m_m3[67] = m_m3[68] = m_m3[69] = m_m3[70] = m_m3[71] = m_m3[72] = 
		m_m3[73] = m_m3[74] = m_m3[75] = m_m3[76] = m_m3[77] = m_m3[78] = m_m3[79] = m_m3[80] = m_m3[81] = 
		m_m3[82] = m_m3[83] = m_m3[84] = m_m3[85] = m_m3[86] = m_m3[87] = m_m3[88] = m_m3[89] = m_m3[90] = 
		m_m3[91] = m_m3[92] = m_m3[93] = m_m3[94] = m_m3[95] = m_m3[96] = m_m3[97] = m_m3[98] = m_m3[99] = 
		m_m4[0] = m_m4[1] = m_m4[2] = m_m4[3] = m_m4[4] = m_m4[5] = m_m4[6] = m_m4[7] = m_m4[8] = 
		m_m4[9] = m_m4[10] = m_m4[11] = m_m4[12] = m_m4[13] = m_m4[14] = m_m4[15] = m_m4[16] = m_m4[17] = 
		m_m4[18] = m_m4[19] = m_m4[20] = m_m4[21] = m_m4[22] = m_m4[23] = m_m4[24] = m_m5[0] = 0.0;
}
/**
 * Copies a grade1OM to a om
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(grade1OM src) {
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
	m_m2[0] = m_m2[1] = m_m2[2] = m_m2[3] = m_m2[4] = m_m2[5] = m_m2[6] = m_m2[7] = m_m2[8] = 
		m_m2[9] = m_m2[10] = m_m2[11] = m_m2[12] = m_m2[13] = m_m2[14] = m_m2[15] = m_m2[16] = m_m2[17] = 
		m_m2[18] = m_m2[19] = m_m2[20] = m_m2[21] = m_m2[22] = m_m2[23] = m_m2[24] = m_m2[25] = m_m2[26] = 
		m_m2[27] = m_m2[28] = m_m2[29] = m_m2[30] = m_m2[31] = m_m2[32] = m_m2[33] = m_m2[34] = m_m2[35] = 
		m_m2[36] = m_m2[37] = m_m2[38] = m_m2[39] = m_m2[40] = m_m2[41] = m_m2[42] = m_m2[43] = m_m2[44] = 
		m_m2[45] = m_m2[46] = m_m2[47] = m_m2[48] = m_m2[49] = m_m2[50] = m_m2[51] = m_m2[52] = m_m2[53] = 
		m_m2[54] = m_m2[55] = m_m2[56] = m_m2[57] = m_m2[58] = m_m2[59] = m_m2[60] = m_m2[61] = m_m2[62] = 
		m_m2[63] = m_m2[64] = m_m2[65] = m_m2[66] = m_m2[67] = m_m2[68] = m_m2[69] = m_m2[70] = m_m2[71] = 
		m_m2[72] = m_m2[73] = m_m2[74] = m_m2[75] = m_m2[76] = m_m2[77] = m_m2[78] = m_m2[79] = m_m2[80] = 
		m_m2[81] = m_m2[82] = m_m2[83] = m_m2[84] = m_m2[85] = m_m2[86] = m_m2[87] = m_m2[88] = m_m2[89] = 
		m_m2[90] = m_m2[91] = m_m2[92] = m_m2[93] = m_m2[94] = m_m2[95] = m_m2[96] = m_m2[97] = m_m2[98] = 
		m_m2[99] = m_m3[0] = m_m3[1] = m_m3[2] = m_m3[3] = m_m3[4] = m_m3[5] = m_m3[6] = m_m3[7] = 
		m_m3[8] = m_m3[9] = m_m3[10] = m_m3[11] = m_m3[12] = m_m3[13] = m_m3[14] = m_m3[15] = m_m3[16] = 
		m_m3[17] = m_m3[18] = m_m3[19] = m_m3[20] = m_m3[21] = m_m3[22] = m_m3[23] = m_m3[24] = m_m3[25] = 
		m_m3[26] = m_m3[27] = m_m3[28] = m_m3[29] = m_m3[30] = m_m3[31] = m_m3[32] = m_m3[33] = m_m3[34] = 
		m_m3[35] = m_m3[36] = m_m3[37] = m_m3[38] = m_m3[39] = m_m3[40] = m_m3[41] = m_m3[42] = m_m3[43] = 
		m_m3[44] = m_m3[45] = m_m3[46] = m_m3[47] = m_m3[48] = m_m3[49] = m_m3[50] = m_m3[51] = m_m3[52] = 
		m_m3[53] = m_m3[54] = m_m3[55] = m_m3[56] = m_m3[57] = m_m3[58] = m_m3[59] = m_m3[60] = m_m3[61] = 
		m_m3[62] = m_m3[63] = m_m3[64] = m_m3[65] = m_m3[66] = m_m3[67] = m_m3[68] = m_m3[69] = m_m3[70] = 
		m_m3[71] = m_m3[72] = m_m3[73] = m_m3[74] = m_m3[75] = m_m3[76] = m_m3[77] = m_m3[78] = m_m3[79] = 
		m_m3[80] = m_m3[81] = m_m3[82] = m_m3[83] = m_m3[84] = m_m3[85] = m_m3[86] = m_m3[87] = m_m3[88] = 
		m_m3[89] = m_m3[90] = m_m3[91] = m_m3[92] = m_m3[93] = m_m3[94] = m_m3[95] = m_m3[96] = m_m3[97] = 
		m_m3[98] = m_m3[99] = m_m4[0] = m_m4[1] = m_m4[2] = m_m4[3] = m_m4[4] = m_m4[5] = m_m4[6] = 
		m_m4[7] = m_m4[8] = m_m4[9] = m_m4[10] = m_m4[11] = m_m4[12] = m_m4[13] = m_m4[14] = m_m4[15] = 
		m_m4[16] = m_m4[17] = m_m4[18] = m_m4[19] = m_m4[20] = m_m4[21] = m_m4[22] = m_m4[23] = m_m4[24] = 
		m_m5[0] = 0.0;
}
/**
 * Copies a flatPointOM to a om
 * Warning 1: coordinates which cannot be represented are silenty lost.
 * Warning 2: coordinates which are not present in 'src' are set to zero in 'dst'.
 * 
 */
void set(flatPointOM src) {
	m_m2[66] =  src.m_m2[15];
	m_m2[67] =  src.m_m2[12];
	m_m2[68] =  src.m_m2[13];
	m_m2[69] =  src.m_m2[14];
	m_m2[76] =  src.m_m2[3];
	m_m2[77] =  src.m_m2[0];
	m_m2[78] =  src.m_m2[1];
	m_m2[79] =  src.m_m2[2];
	m_m2[86] =  src.m_m2[7];
	m_m2[87] =  src.m_m2[4];
	m_m2[88] =  src.m_m2[5];
	m_m2[89] =  src.m_m2[6];
	m_m2[96] =  src.m_m2[11];
	m_m2[97] =  src.m_m2[8];
	m_m2[98] =  src.m_m2[9];
	m_m2[99] =  src.m_m2[10];
	m_m1[0] = m_m1[1] = m_m1[2] = m_m1[3] = m_m1[4] = m_m1[5] = m_m1[6] = m_m1[7] = m_m1[8] = 
		m_m1[9] = m_m1[10] = m_m1[11] = m_m1[12] = m_m1[13] = m_m1[14] = m_m1[15] = m_m1[16] = m_m1[17] = 
		m_m1[18] = m_m1[19] = m_m1[20] = m_m1[21] = m_m1[22] = m_m1[23] = m_m1[24] = m_m2[0] = m_m2[1] = 
		m_m2[2] = m_m2[3] = m_m2[4] = m_m2[5] = m_m2[6] = m_m2[7] = m_m2[8] = m_m2[9] = m_m2[10] = 
		m_m2[11] = m_m2[12] = m_m2[13] = m_m2[14] = m_m2[15] = m_m2[16] = m_m2[17] = m_m2[18] = m_m2[19] = 
		m_m2[20] = m_m2[21] = m_m2[22] = m_m2[23] = m_m2[24] = m_m2[25] = m_m2[26] = m_m2[27] = m_m2[28] = 
		m_m2[29] = m_m2[30] = m_m2[31] = m_m2[32] = m_m2[33] = m_m2[34] = m_m2[35] = m_m2[36] = m_m2[37] = 
		m_m2[38] = m_m2[39] = m_m2[40] = m_m2[41] = m_m2[42] = m_m2[43] = m_m2[44] = m_m2[45] = m_m2[46] = 
		m_m2[47] = m_m2[48] = m_m2[49] = m_m2[50] = m_m2[51] = m_m2[52] = m_m2[53] = m_m2[54] = m_m2[55] = 
		m_m2[56] = m_m2[57] = m_m2[58] = m_m2[59] = m_m2[60] = m_m2[61] = m_m2[62] = m_m2[63] = m_m2[64] = 
		m_m2[65] = m_m2[70] = m_m2[71] = m_m2[72] = m_m2[73] = m_m2[74] = m_m2[75] = m_m2[80] = m_m2[81] = 
		m_m2[82] = m_m2[83] = m_m2[84] = m_m2[85] = m_m2[90] = m_m2[91] = m_m2[92] = m_m2[93] = m_m2[94] = 
		m_m2[95] = m_m3[0] = m_m3[1] = m_m3[2] = m_m3[3] = m_m3[4] = m_m3[5] = m_m3[6] = m_m3[7] = 
		m_m3[8] = m_m3[9] = m_m3[10] = m_m3[11] = m_m3[12] = m_m3[13] = m_m3[14] = m_m3[15] = m_m3[16] = 
		m_m3[17] = m_m3[18] = m_m3[19] = m_m3[20] = m_m3[21] = m_m3[22] = m_m3[23] = m_m3[24] = m_m3[25] = 
		m_m3[26] = m_m3[27] = m_m3[28] = m_m3[29] = m_m3[30] = m_m3[31] = m_m3[32] = m_m3[33] = m_m3[34] = 
		m_m3[35] = m_m3[36] = m_m3[37] = m_m3[38] = m_m3[39] = m_m3[40] = m_m3[41] = m_m3[42] = m_m3[43] = 
		m_m3[44] = m_m3[45] = m_m3[46] = m_m3[47] = m_m3[48] = m_m3[49] = m_m3[50] = m_m3[51] = m_m3[52] = 
		m_m3[53] = m_m3[54] = m_m3[55] = m_m3[56] = m_m3[57] = m_m3[58] = m_m3[59] = m_m3[60] = m_m3[61] = 
		m_m3[62] = m_m3[63] = m_m3[64] = m_m3[65] = m_m3[66] = m_m3[67] = m_m3[68] = m_m3[69] = m_m3[70] = 
		m_m3[71] = m_m3[72] = m_m3[73] = m_m3[74] = m_m3[75] = m_m3[76] = m_m3[77] = m_m3[78] = m_m3[79] = 
		m_m3[80] = m_m3[81] = m_m3[82] = m_m3[83] = m_m3[84] = m_m3[85] = m_m3[86] = m_m3[87] = m_m3[88] = 
		m_m3[89] = m_m3[90] = m_m3[91] = m_m3[92] = m_m3[93] = m_m3[94] = m_m3[95] = m_m3[96] = m_m3[97] = 
		m_m3[98] = m_m3[99] = m_m4[0] = m_m4[1] = m_m4[2] = m_m4[3] = m_m4[4] = m_m4[5] = m_m4[6] = 
		m_m4[7] = m_m4[8] = m_m4[9] = m_m4[10] = m_m4[11] = m_m4[12] = m_m4[13] = m_m4[14] = m_m4[15] = 
		m_m4[16] = m_m4[17] = m_m4[18] = m_m4[19] = m_m4[20] = m_m4[21] = m_m4[22] = m_m4[23] = m_m4[24] = 
		m_m5[0] = 0.0;
}
} // end of class om
