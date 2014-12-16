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
 * This class can hold a general multivector.
 * 
 * The coordinates are stored in type double.
 * 
 * There are 6 coordinate groups:
 * group 0:1  (grade 0).
 * group 1:no, e1, e2, e3, ni  (grade 1).
 * group 2:no^e1, no^e2, e1^e2, no^e3, e1^e3, e2^e3, no^ni, e1^ni, e2^ni, e3^ni  (grade 2).
 * group 3:no^e1^e2, no^e1^e3, no^e2^e3, e1^e2^e3, no^e1^ni, no^e2^ni, e1^e2^ni, no^e3^ni, e1^e3^ni, e2^e3^ni  (grade 3).
 * group 4:no^e1^e2^e3, no^e1^e2^ni, no^e1^e3^ni, no^e2^e3^ni, e1^e2^e3^ni  (grade 4).
 * group 5:no^e1^e2^e3^ni  (grade 5).
 * 
 * 32 doubles are allocated inside the struct.
 * 
 */
public class mv  implements  mv_if
{ 

    /** the coordinates */
	protected double[][] m_c = new double[6][]; 
	


    /**
	* Constructs a new mv with value 0.
     */
	public mv() {set();}

    /**
	 * Copy constructor.
     */
	public mv(final mv A) {set(A);}


    /**
	 * Constructs a new mv with scalar value 'scalar'.
     */
	public mv(final double scalar) {set(scalar);}

    /** 
     * Constructs a new mv from compressed 'coordinates'.
 	 * @param gu bitwise OR of the GRADEs or GROUPs that are non-zero.
	 * @param coordinates compressed coordinates.
     */
	public mv(final int gu, final double[] coordinates) {set(gu, coordinates);}

    /** 
     * Constructs a new mv from array of array of 'coordinates'.
	 * @param coordinates The coordinates, one array for each group/grade. Make sure the
	 * array length match the size of the groups. Entries may be null.
     */
	public mv(final double[][] coordinates) {set(coordinates);}
	
    /** Converts a no_t to a mv. */
	public mv(final no_t A) {set(A);}
    /** Converts a e1_t to a mv. */
	public mv(final e1_t A) {set(A);}
    /** Converts a e2_t to a mv. */
	public mv(final e2_t A) {set(A);}
    /** Converts a e3_t to a mv. */
	public mv(final e3_t A) {set(A);}
    /** Converts a ni_t to a mv. */
	public mv(final ni_t A) {set(A);}
    /** Converts a noni_t to a mv. */
	public mv(final noni_t A) {set(A);}
    /** Converts a I3_t to a mv. */
	public mv(final I3_t A) {set(A);}
    /** Converts a I5_t to a mv. */
	public mv(final I5_t A) {set(A);}
    /** Converts a I5i_t to a mv. */
	public mv(final I5i_t A) {set(A);}
    /** Converts a vectorE3GA to a mv. */
	public mv(final vectorE3GA A) {set(A);}
    /** Converts a bivectorE3GA to a mv. */
	public mv(final bivectorE3GA A) {set(A);}
    /** Converts a rotorE3GA to a mv. */
	public mv(final rotorE3GA A) {set(A);}
    /** Converts a normalizedPoint to a mv. */
	public mv(final normalizedPoint A) {set(A);}
    /** Converts a dualSphere to a mv. */
	public mv(final dualSphere A) {set(A);}
    /** Converts a dualPlane to a mv. */
	public mv(final dualPlane A) {set(A);}
    /** Converts a freeVector to a mv. */
	public mv(final freeVector A) {set(A);}
    /** Converts a freeBivector to a mv. */
	public mv(final freeBivector A) {set(A);}
    /** Converts a flatPoint to a mv. */
	public mv(final flatPoint A) {set(A);}
    /** Converts a pointPair to a mv. */
	public mv(final pointPair A) {set(A);}
    /** Converts a line to a mv. */
	public mv(final line A) {set(A);}
    /** Converts a circle to a mv. */
	public mv(final circle A) {set(A);}
    /** Converts a sphere to a mv. */
	public mv(final sphere A) {set(A);}
    /** Converts a plane to a mv. */
	public mv(final plane A) {set(A);}
    /** Converts a pseudoscalar to a mv. */
	public mv(final pseudoscalar A) {set(A);}
    /** Converts a normalizedTranslator to a mv. */
	public mv(final normalizedTranslator A) {set(A);}
    /** Converts a translator to a mv. */
	public mv(final translator A) {set(A);}
    /** Converts a RBM to a mv. */
	public mv(final RBM A) {set(A);}
    /** Converts a evenVersor to a mv. */
	public mv(final evenVersor A) {set(A);}
    /** Converts a oddVersor to a mv. */
	public mv(final oddVersor A) {set(A);}


	/** returns group usage bitmap. */
	public final int gu() {
		return 
			((m_c[0] == null) ? 0 : GroupBitmap.GROUP_0) |
			((m_c[1] == null) ? 0 : GroupBitmap.GROUP_1) |
			((m_c[2] == null) ? 0 : GroupBitmap.GROUP_2) |
			((m_c[3] == null) ? 0 : GroupBitmap.GROUP_3) |
			((m_c[4] == null) ? 0 : GroupBitmap.GROUP_4) |
			((m_c[5] == null) ? 0 : GroupBitmap.GROUP_5) |
			0;
	}
	
    /**
	 * Returns array of array of coordinates.
	 * Each entry contain the coordinates for one group/grade.
     */
	public final double[][] c() { return m_c; }
	
	/**
	 * sets this to 0.
	 */
	public void set() {
		m_c[0] = null;
		m_c[1] = null;
		m_c[2] = null;
		m_c[3] = null;
		m_c[4] = null;
		m_c[5] = null;
	}
	/**
	 * sets this to scalar value.
	 */
	public void set(double val) {
		allocateGroups(GroupBitmap.GROUP_0);
		m_c[0][0] = val;
	}
	/**
	 * sets this coordinates in 'arr'.
	 * @param gu bitwise or of the GROUPs and GRADEs which are present in 'arr'.
	 * @param arr compressed coordinates.
	 */
	public void set(int gu, double[] arr) {
		allocateGroups(gu);
		int idx = 0;
		if ((gu & GroupBitmap.GROUP_0) != 0) {
			for (int i = 0; i < 1; i++)
				m_c[0][i] = arr[idx + i];
			idx += 1;
		}
		if ((gu & GroupBitmap.GROUP_1) != 0) {
			for (int i = 0; i < 5; i++)
				m_c[1][i] = arr[idx + i];
			idx += 5;
		}
		if ((gu & GroupBitmap.GROUP_2) != 0) {
			for (int i = 0; i < 10; i++)
				m_c[2][i] = arr[idx + i];
			idx += 10;
		}
		if ((gu & GroupBitmap.GROUP_3) != 0) {
			for (int i = 0; i < 10; i++)
				m_c[3][i] = arr[idx + i];
			idx += 10;
		}
		if ((gu & GroupBitmap.GROUP_4) != 0) {
			for (int i = 0; i < 5; i++)
				m_c[4][i] = arr[idx + i];
			idx += 5;
		}
		if ((gu & GroupBitmap.GROUP_5) != 0) {
			for (int i = 0; i < 1; i++)
				m_c[5][i] = arr[idx + i];
			idx += 1;
		}
	}
	/**
	 * sets this coordinates in 'arr'. 
	 * 'arr' is kept, so changes to 'arr' will be reflected in the value of this multivector. Make sure 'arr' has length 6 and each subarray has the length of the respective group/grade
	 * @param arr coordinates.
	 */
	public void set(double[][] arr) {
		m_c = arr;
	}
	/**
	 * sets this to multivector value.
	 */
	public void set(mv src) {
		allocateGroups(src.gu());
		if (m_c[0] != null) {
			c3ga.copy_1(m_c[0], src.m_c[0]);
		}
		if (m_c[1] != null) {
			c3ga.copy_5(m_c[1], src.m_c[1]);
		}
		if (m_c[2] != null) {
			c3ga.copy_10(m_c[2], src.m_c[2]);
		}
		if (m_c[3] != null) {
			c3ga.copy_10(m_c[3], src.m_c[3]);
		}
		if (m_c[4] != null) {
			c3ga.copy_5(m_c[4], src.m_c[4]);
		}
		if (m_c[5] != null) {
			c3ga.copy_1(m_c[5], src.m_c[5]);
		}
	}

	/**
	 * sets this to no_t value.
	 */
	public void set(no_t src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = 1.0;
		ptr[1] = ptr[2] = ptr[3] = ptr[4] = 0.0;
	}

	/**
	 * sets this to e1_t value.
	 */
	public void set(e1_t src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = ptr[2] = ptr[3] = ptr[4] = 0.0;
		ptr[1] = 1.0;
	}

	/**
	 * sets this to e2_t value.
	 */
	public void set(e2_t src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = ptr[1] = ptr[3] = ptr[4] = 0.0;
		ptr[2] = 1.0;
	}

	/**
	 * sets this to e3_t value.
	 */
	public void set(e3_t src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = ptr[1] = ptr[2] = ptr[4] = 0.0;
		ptr[3] = 1.0;
	}

	/**
	 * sets this to ni_t value.
	 */
	public void set(ni_t src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = 0.0;
		ptr[4] = 1.0;
	}

	/**
	 * sets this to noni_t value.
	 */
	public void set(noni_t src) {
		allocateGroups(GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = ptr[7] = ptr[8] = ptr[9] = 0.0;
		ptr[6] = 1.0;
	}

	/**
	 * sets this to I3_t value.
	 */
	public void set(I3_t src) {
		allocateGroups(GroupBitmap.GROUP_3);
		double[] ptr;

		ptr = m_c[3];
		ptr[0] = ptr[1] = ptr[2] = ptr[4] = ptr[5] = ptr[6] = ptr[7] = ptr[8] = ptr[9] = 0.0;
		ptr[3] = 1.0;
	}

	/**
	 * sets this to I5_t value.
	 */
	public void set(I5_t src) {
		allocateGroups(GroupBitmap.GROUP_5);
		double[] ptr;

		ptr = m_c[5];
		ptr[0] = 1.0;
	}

	/**
	 * sets this to I5i_t value.
	 */
	public void set(I5i_t src) {
		allocateGroups(GroupBitmap.GROUP_5);
		double[] ptr;

		ptr = m_c[5];
		ptr[0] = -1.0;
	}

	/**
	 * sets this to vectorE3GA value.
	 */
	public void set(vectorE3GA src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = ptr[4] = 0.0;
		ptr[1] = src.m_e1;
		ptr[2] = src.m_e2;
		ptr[3] = src.m_e3;
	}

	/**
	 * sets this to bivectorE3GA value.
	 */
	public void set(bivectorE3GA src) {
		allocateGroups(GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[3] = ptr[6] = ptr[7] = ptr[8] = ptr[9] = 0.0;
		ptr[2] = src.m_e1_e2;
		ptr[4] = -src.m_e3_e1;
		ptr[5] = src.m_e2_e3;
	}

	/**
	 * sets this to rotorE3GA value.
	 */
	public void set(rotorE3GA src) {
		allocateGroups(GroupBitmap.GROUP_0|GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[0];
		ptr[0] = src.m_scalar;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[3] = ptr[6] = ptr[7] = ptr[8] = ptr[9] = 0.0;
		ptr[2] = src.m_e1_e2;
		ptr[4] = -src.m_e3_e1;
		ptr[5] = src.m_e2_e3;
	}

	/**
	 * sets this to normalizedPoint value.
	 */
	public void set(normalizedPoint src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = 1.0;
		ptr[1] = src.m_e1;
		ptr[2] = src.m_e2;
		ptr[3] = src.m_e3;
		ptr[4] = src.m_ni;
	}

	/**
	 * sets this to dualSphere value.
	 */
	public void set(dualSphere src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = src.m_no;
		ptr[1] = src.m_e1;
		ptr[2] = src.m_e2;
		ptr[3] = src.m_e3;
		ptr[4] = src.m_ni;
	}

	/**
	 * sets this to dualPlane value.
	 */
	public void set(dualPlane src) {
		allocateGroups(GroupBitmap.GROUP_1);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = 0.0;
		ptr[1] = src.m_e1;
		ptr[2] = src.m_e2;
		ptr[3] = src.m_e3;
		ptr[4] = src.m_ni;
	}

	/**
	 * sets this to freeVector value.
	 */
	public void set(freeVector src) {
		allocateGroups(GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = ptr[6] = 0.0;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;
	}

	/**
	 * sets this to freeBivector value.
	 */
	public void set(freeBivector src) {
		allocateGroups(GroupBitmap.GROUP_3);
		double[] ptr;

		ptr = m_c[3];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = ptr[7] = 0.0;
		ptr[6] = src.m_e1_e2_ni;
		ptr[8] = -src.m_e3_e1_ni;
		ptr[9] = src.m_e2_e3_ni;
	}

	/**
	 * sets this to flatPoint value.
	 */
	public void set(flatPoint src) {
		allocateGroups(GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = 0.0;
		ptr[6] = src.m_no_ni;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;
	}

	/**
	 * sets this to pointPair value.
	 */
	public void set(pointPair src) {
		allocateGroups(GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[2];
		ptr[0] = src.m_no_e1;
		ptr[1] = src.m_no_e2;
		ptr[2] = src.m_e1_e2;
		ptr[3] = src.m_no_e3;
		ptr[4] = -src.m_e3_e1;
		ptr[5] = src.m_e2_e3;
		ptr[6] = src.m_no_ni;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;
	}

	/**
	 * sets this to line value.
	 */
	public void set(line src) {
		allocateGroups(GroupBitmap.GROUP_3);
		double[] ptr;

		ptr = m_c[3];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = 0.0;
		ptr[4] = -src.m_e1_no_ni;
		ptr[5] = -src.m_e2_no_ni;
		ptr[6] = src.m_e1_e2_ni;
		ptr[7] = -src.m_e3_no_ni;
		ptr[8] = src.m_e1_e3_ni;
		ptr[9] = src.m_e2_e3_ni;
	}

	/**
	 * sets this to circle value.
	 */
	public void set(circle src) {
		allocateGroups(GroupBitmap.GROUP_3);
		double[] ptr;

		ptr = m_c[3];
		ptr[0] = src.m_no_e1_e2;
		ptr[1] = src.m_no_e1_e3;
		ptr[2] = src.m_no_e2_e3;
		ptr[3] = src.m_e1_e2_e3;
		ptr[4] = src.m_no_e1_ni;
		ptr[5] = src.m_no_e2_ni;
		ptr[6] = src.m_e1_e2_ni;
		ptr[7] = src.m_no_e3_ni;
		ptr[8] = src.m_e1_e3_ni;
		ptr[9] = src.m_e2_e3_ni;
	}

	/**
	 * sets this to sphere value.
	 */
	public void set(sphere src) {
		allocateGroups(GroupBitmap.GROUP_4);
		double[] ptr;

		ptr = m_c[4];
		ptr[0] = src.m_no_e1_e2_e3;
		ptr[1] = src.m_no_e1_e2_ni;
		ptr[2] = src.m_no_e1_e3_ni;
		ptr[3] = src.m_no_e2_e3_ni;
		ptr[4] = src.m_e1_e2_e3_ni;
	}

	/**
	 * sets this to plane value.
	 */
	public void set(plane src) {
		allocateGroups(GroupBitmap.GROUP_4);
		double[] ptr;

		ptr = m_c[4];
		ptr[0] = 0.0;
		ptr[1] = src.m_no_e1_e2_ni;
		ptr[2] = src.m_no_e1_e3_ni;
		ptr[3] = src.m_no_e2_e3_ni;
		ptr[4] = src.m_e1_e2_e3_ni;
	}

	/**
	 * sets this to pseudoscalar value.
	 */
	public void set(pseudoscalar src) {
		allocateGroups(GroupBitmap.GROUP_5);
		double[] ptr;

		ptr = m_c[5];
		ptr[0] = src.m_no_e1_e2_e3_ni;
	}

	/**
	 * sets this to normalizedTranslator value.
	 */
	public void set(normalizedTranslator src) {
		allocateGroups(GroupBitmap.GROUP_0|GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[0];
		ptr[0] = 1.0;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = ptr[6] = 0.0;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;
	}

	/**
	 * sets this to translator value.
	 */
	public void set(translator src) {
		allocateGroups(GroupBitmap.GROUP_0|GroupBitmap.GROUP_2);
		double[] ptr;

		ptr = m_c[0];
		ptr[0] = src.m_scalar;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = ptr[4] = ptr[5] = ptr[6] = 0.0;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;
	}

	/**
	 * sets this to RBM value.
	 */
	public void set(RBM src) {
		allocateGroups(GroupBitmap.GROUP_0|GroupBitmap.GROUP_2|GroupBitmap.GROUP_4);
		double[] ptr;

		ptr = m_c[0];
		ptr[0] = src.m_scalar;

		ptr = m_c[2];
		ptr[0] = ptr[1] = ptr[3] = ptr[6] = 0.0;
		ptr[2] = src.m_e1_e2;
		ptr[4] = -src.m_e3_e1;
		ptr[5] = src.m_e2_e3;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;

		ptr = m_c[4];
		ptr[0] = ptr[1] = ptr[2] = ptr[3] = 0.0;
		ptr[4] = src.m_e1_e2_e3_ni;
	}

	/**
	 * sets this to evenVersor value.
	 */
	public void set(evenVersor src) {
		allocateGroups(GroupBitmap.GROUP_0|GroupBitmap.GROUP_2|GroupBitmap.GROUP_4);
		double[] ptr;

		ptr = m_c[0];
		ptr[0] = src.m_scalar;

		ptr = m_c[2];
		ptr[0] = src.m_no_e1;
		ptr[1] = src.m_no_e2;
		ptr[2] = src.m_e1_e2;
		ptr[3] = src.m_no_e3;
		ptr[4] = -src.m_e3_e1;
		ptr[5] = src.m_e2_e3;
		ptr[6] = src.m_no_ni;
		ptr[7] = src.m_e1_ni;
		ptr[8] = src.m_e2_ni;
		ptr[9] = src.m_e3_ni;

		ptr = m_c[4];
		ptr[0] = src.m_no_e1_e2_e3;
		ptr[1] = src.m_no_e1_e2_ni;
		ptr[2] = src.m_no_e1_e3_ni;
		ptr[3] = src.m_no_e2_e3_ni;
		ptr[4] = src.m_e1_e2_e3_ni;
	}

	/**
	 * sets this to oddVersor value.
	 */
	public void set(oddVersor src) {
		allocateGroups(GroupBitmap.GROUP_1|GroupBitmap.GROUP_3|GroupBitmap.GROUP_5);
		double[] ptr;

		ptr = m_c[1];
		ptr[0] = src.m_no;
		ptr[1] = src.m_e1;
		ptr[2] = src.m_e2;
		ptr[3] = src.m_e3;
		ptr[4] = src.m_ni;

		ptr = m_c[3];
		ptr[0] = src.m_no_e1_e2;
		ptr[1] = src.m_no_e1_e3;
		ptr[2] = src.m_no_e2_e3;
		ptr[3] = src.m_e1_e2_e3;
		ptr[4] = src.m_no_e1_ni;
		ptr[5] = src.m_no_e2_ni;
		ptr[6] = src.m_e1_e2_ni;
		ptr[7] = src.m_no_e3_ni;
		ptr[8] = src.m_e1_e3_ni;
		ptr[9] = src.m_e2_e3_ni;

		ptr = m_c[5];
		ptr[0] = src.m_no_e1_e2_e3_ni;
	}
	/**
	 * Returns the scalar coordinate of this mv
	 */
	public final double get_scalar()  {
		return (m_c[0] == null) ? 0.0: m_c[0][0];
	}
	/**
	 * Returns the no coordinate of this mv
	 */
	public final double get_no()  {
		return (m_c[1] == null) ? 0.0: m_c[1][0];
	}
	/**
	 * Returns the e1 coordinate of this mv
	 */
	public final double get_e1()  {
		return (m_c[1] == null) ? 0.0: m_c[1][1];
	}
	/**
	 * Returns the e2 coordinate of this mv
	 */
	public final double get_e2()  {
		return (m_c[1] == null) ? 0.0: m_c[1][2];
	}
	/**
	 * Returns the e3 coordinate of this mv
	 */
	public final double get_e3()  {
		return (m_c[1] == null) ? 0.0: m_c[1][3];
	}
	/**
	 * Returns the ni coordinate of this mv
	 */
	public final double get_ni()  {
		return (m_c[1] == null) ? 0.0: m_c[1][4];
	}
	/**
	 * Returns the no_e1 coordinate of this mv
	 */
	public final double get_no_e1()  {
		return (m_c[2] == null) ? 0.0: m_c[2][0];
	}
	/**
	 * Returns the no_e2 coordinate of this mv
	 */
	public final double get_no_e2()  {
		return (m_c[2] == null) ? 0.0: m_c[2][1];
	}
	/**
	 * Returns the e1_e2 coordinate of this mv
	 */
	public final double get_e1_e2()  {
		return (m_c[2] == null) ? 0.0: m_c[2][2];
	}
	/**
	 * Returns the no_e3 coordinate of this mv
	 */
	public final double get_no_e3()  {
		return (m_c[2] == null) ? 0.0: m_c[2][3];
	}
	/**
	 * Returns the e1_e3 coordinate of this mv
	 */
	public final double get_e1_e3()  {
		return (m_c[2] == null) ? 0.0: m_c[2][4];
	}
	/**
	 * Returns the e2_e3 coordinate of this mv
	 */
	public final double get_e2_e3()  {
		return (m_c[2] == null) ? 0.0: m_c[2][5];
	}
	/**
	 * Returns the no_ni coordinate of this mv
	 */
	public final double get_no_ni()  {
		return (m_c[2] == null) ? 0.0: m_c[2][6];
	}
	/**
	 * Returns the e1_ni coordinate of this mv
	 */
	public final double get_e1_ni()  {
		return (m_c[2] == null) ? 0.0: m_c[2][7];
	}
	/**
	 * Returns the e2_ni coordinate of this mv
	 */
	public final double get_e2_ni()  {
		return (m_c[2] == null) ? 0.0: m_c[2][8];
	}
	/**
	 * Returns the e3_ni coordinate of this mv
	 */
	public final double get_e3_ni()  {
		return (m_c[2] == null) ? 0.0: m_c[2][9];
	}
	/**
	 * Returns the no_e1_e2 coordinate of this mv
	 */
	public final double get_no_e1_e2()  {
		return (m_c[3] == null) ? 0.0: m_c[3][0];
	}
	/**
	 * Returns the no_e1_e3 coordinate of this mv
	 */
	public final double get_no_e1_e3()  {
		return (m_c[3] == null) ? 0.0: m_c[3][1];
	}
	/**
	 * Returns the no_e2_e3 coordinate of this mv
	 */
	public final double get_no_e2_e3()  {
		return (m_c[3] == null) ? 0.0: m_c[3][2];
	}
	/**
	 * Returns the e1_e2_e3 coordinate of this mv
	 */
	public final double get_e1_e2_e3()  {
		return (m_c[3] == null) ? 0.0: m_c[3][3];
	}
	/**
	 * Returns the no_e1_ni coordinate of this mv
	 */
	public final double get_no_e1_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][4];
	}
	/**
	 * Returns the no_e2_ni coordinate of this mv
	 */
	public final double get_no_e2_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][5];
	}
	/**
	 * Returns the e1_e2_ni coordinate of this mv
	 */
	public final double get_e1_e2_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][6];
	}
	/**
	 * Returns the no_e3_ni coordinate of this mv
	 */
	public final double get_no_e3_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][7];
	}
	/**
	 * Returns the e1_e3_ni coordinate of this mv
	 */
	public final double get_e1_e3_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][8];
	}
	/**
	 * Returns the e2_e3_ni coordinate of this mv
	 */
	public final double get_e2_e3_ni()  {
		return (m_c[3] == null) ? 0.0: m_c[3][9];
	}
	/**
	 * Returns the no_e1_e2_e3 coordinate of this mv
	 */
	public final double get_no_e1_e2_e3()  {
		return (m_c[4] == null) ? 0.0: m_c[4][0];
	}
	/**
	 * Returns the no_e1_e2_ni coordinate of this mv
	 */
	public final double get_no_e1_e2_ni()  {
		return (m_c[4] == null) ? 0.0: m_c[4][1];
	}
	/**
	 * Returns the no_e1_e3_ni coordinate of this mv
	 */
	public final double get_no_e1_e3_ni()  {
		return (m_c[4] == null) ? 0.0: m_c[4][2];
	}
	/**
	 * Returns the no_e2_e3_ni coordinate of this mv
	 */
	public final double get_no_e2_e3_ni()  {
		return (m_c[4] == null) ? 0.0: m_c[4][3];
	}
	/**
	 * Returns the e1_e2_e3_ni coordinate of this mv
	 */
	public final double get_e1_e2_e3_ni()  {
		return (m_c[4] == null) ? 0.0: m_c[4][4];
	}
	/**
	 * Returns the no_e1_e2_e3_ni coordinate of this mv
	 */
	public final double get_no_e1_e2_e3_ni()  {
		return (m_c[5] == null) ? 0.0: m_c[5][0];
	}

	/** 
	 * Reserves memory for the groups specified by 'gu'.
	 * Keeps old memory (and values) when possible. 
	 */
	private final void allocateGroups(final int gu) {
		for (int i = 0; (1 << i) <= gu; i++) {
			if (((1 << i) & gu) != 0) {
				if (m_c[i] == null)
					m_c[i] = new double[c3ga.MvSize[1 << i]];
			}
			else m_c[i] = null;
		}		
	}

	/**
	 *  Reserves memory for coordinate GROUP_0.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_0() {
		if (m_c[0] == null) {
			m_c[0] = new double[1];
		}
	}
	/**
	 *  Reserves memory for coordinate GROUP_1.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_1() {
		if (m_c[1] == null) {
			m_c[1] = new double[5];
		}
	}
	/**
	 *  Reserves memory for coordinate GROUP_2.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_2() {
		if (m_c[2] == null) {
			m_c[2] = new double[10];
		}
	}
	/**
	 *  Reserves memory for coordinate GROUP_3.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_3() {
		if (m_c[3] == null) {
			m_c[3] = new double[10];
		}
	}
	/**
	 *  Reserves memory for coordinate GROUP_4.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_4() {
		if (m_c[4] == null) {
			m_c[4] = new double[5];
		}
	}
	/**
	 *  Reserves memory for coordinate GROUP_5.
	 *  If the group is already present, nothing changes.
	 *  If the group is not present, memory is allocated for the new group,
	 *  and the coordinates for the group are set to zero.
	 */
	private final void reserveGroup_5() {
		if (m_c[5] == null) {
			m_c[5] = new double[1];
		}
	}
	/// Sets the scalar coordinate of this mv.
	public final void set_scalar(double val)  {
		reserveGroup_0();
		m_c[0][0] =  val;
	}
	/// Sets the no coordinate of this mv.
	public final void set_no(double val)  {
		reserveGroup_1();
		m_c[1][0] =  val;
	}
	/// Sets the e1 coordinate of this mv.
	public final void set_e1(double val)  {
		reserveGroup_1();
		m_c[1][1] =  val;
	}
	/// Sets the e2 coordinate of this mv.
	public final void set_e2(double val)  {
		reserveGroup_1();
		m_c[1][2] =  val;
	}
	/// Sets the e3 coordinate of this mv.
	public final void set_e3(double val)  {
		reserveGroup_1();
		m_c[1][3] =  val;
	}
	/// Sets the ni coordinate of this mv.
	public final void set_ni(double val)  {
		reserveGroup_1();
		m_c[1][4] =  val;
	}
	/// Sets the no_e1 coordinate of this mv.
	public final void set_no_e1(double val)  {
		reserveGroup_2();
		m_c[2][0] =  val;
	}
	/// Sets the no_e2 coordinate of this mv.
	public final void set_no_e2(double val)  {
		reserveGroup_2();
		m_c[2][1] =  val;
	}
	/// Sets the e1_e2 coordinate of this mv.
	public final void set_e1_e2(double val)  {
		reserveGroup_2();
		m_c[2][2] =  val;
	}
	/// Sets the no_e3 coordinate of this mv.
	public final void set_no_e3(double val)  {
		reserveGroup_2();
		m_c[2][3] =  val;
	}
	/// Sets the e1_e3 coordinate of this mv.
	public final void set_e1_e3(double val)  {
		reserveGroup_2();
		m_c[2][4] =  val;
	}
	/// Sets the e2_e3 coordinate of this mv.
	public final void set_e2_e3(double val)  {
		reserveGroup_2();
		m_c[2][5] =  val;
	}
	/// Sets the no_ni coordinate of this mv.
	public final void set_no_ni(double val)  {
		reserveGroup_2();
		m_c[2][6] =  val;
	}
	/// Sets the e1_ni coordinate of this mv.
	public final void set_e1_ni(double val)  {
		reserveGroup_2();
		m_c[2][7] =  val;
	}
	/// Sets the e2_ni coordinate of this mv.
	public final void set_e2_ni(double val)  {
		reserveGroup_2();
		m_c[2][8] =  val;
	}
	/// Sets the e3_ni coordinate of this mv.
	public final void set_e3_ni(double val)  {
		reserveGroup_2();
		m_c[2][9] =  val;
	}
	/// Sets the no_e1_e2 coordinate of this mv.
	public final void set_no_e1_e2(double val)  {
		reserveGroup_3();
		m_c[3][0] =  val;
	}
	/// Sets the no_e1_e3 coordinate of this mv.
	public final void set_no_e1_e3(double val)  {
		reserveGroup_3();
		m_c[3][1] =  val;
	}
	/// Sets the no_e2_e3 coordinate of this mv.
	public final void set_no_e2_e3(double val)  {
		reserveGroup_3();
		m_c[3][2] =  val;
	}
	/// Sets the e1_e2_e3 coordinate of this mv.
	public final void set_e1_e2_e3(double val)  {
		reserveGroup_3();
		m_c[3][3] =  val;
	}
	/// Sets the no_e1_ni coordinate of this mv.
	public final void set_no_e1_ni(double val)  {
		reserveGroup_3();
		m_c[3][4] =  val;
	}
	/// Sets the no_e2_ni coordinate of this mv.
	public final void set_no_e2_ni(double val)  {
		reserveGroup_3();
		m_c[3][5] =  val;
	}
	/// Sets the e1_e2_ni coordinate of this mv.
	public final void set_e1_e2_ni(double val)  {
		reserveGroup_3();
		m_c[3][6] =  val;
	}
	/// Sets the no_e3_ni coordinate of this mv.
	public final void set_no_e3_ni(double val)  {
		reserveGroup_3();
		m_c[3][7] =  val;
	}
	/// Sets the e1_e3_ni coordinate of this mv.
	public final void set_e1_e3_ni(double val)  {
		reserveGroup_3();
		m_c[3][8] =  val;
	}
	/// Sets the e2_e3_ni coordinate of this mv.
	public final void set_e2_e3_ni(double val)  {
		reserveGroup_3();
		m_c[3][9] =  val;
	}
	/// Sets the no_e1_e2_e3 coordinate of this mv.
	public final void set_no_e1_e2_e3(double val)  {
		reserveGroup_4();
		m_c[4][0] =  val;
	}
	/// Sets the no_e1_e2_ni coordinate of this mv.
	public final void set_no_e1_e2_ni(double val)  {
		reserveGroup_4();
		m_c[4][1] =  val;
	}
	/// Sets the no_e1_e3_ni coordinate of this mv.
	public final void set_no_e1_e3_ni(double val)  {
		reserveGroup_4();
		m_c[4][2] =  val;
	}
	/// Sets the no_e2_e3_ni coordinate of this mv.
	public final void set_no_e2_e3_ni(double val)  {
		reserveGroup_4();
		m_c[4][3] =  val;
	}
	/// Sets the e1_e2_e3_ni coordinate of this mv.
	public final void set_e1_e2_e3_ni(double val)  {
		reserveGroup_4();
		m_c[4][4] =  val;
	}
	/// Sets the no_e1_e2_e3_ni coordinate of this mv.
	public final void set_no_e1_e2_e3_ni(double val)  {
		reserveGroup_5();
		m_c[5][0] =  val;
	}

	/** returns the absolute largest coordinate.*/
	public double largestCoordinate() {
		double maxValue = 0.0, C;
		for (int g = 0; g < m_c.length; g++) {
			if (m_c[g] != null) {
				double[] Cg = m_c[g];
				for (int b = 0; b < Cg.length; b++) {
					C = Math.abs(Cg[b]);
					if (C > maxValue) {
						maxValue = C;
					}
				}
			}
		}
		return maxValue;
	}
	
	/** returns the absolute largest coordinate (entry [0]), and the corresponding basis blade bitmap (entry [1])  */
	public double[] largestBasisBlade() {
		double maxC = -1.0, C;

		int idx = 0; // global index into coordinates (run from 0 to 32).
		int bm; // bitmap of basis blade
		bm = 0;
		
		for (int g = 0; g < m_c.length; g++) {
			if (m_c[g] != null) {
				double[] Cg = m_c[g];
				for (int b = 0; b < m_c[g].length; b++) {
					C = Math.abs(Cg[b]);
					if (C > maxC) {
						maxC = C;
						bm = c3ga.BasisElementBitmapByIndex[idx];
					}
					idx++;
				}
			
			}
			else idx += c3ga.GroupSize[g];
		}

		return new double[]{maxC, (double)bm};
	} // end of largestBasisBlade()
	
	/**
	 * Releases memory for (near-)zero groups/grades.
	 * This also speeds up subsequent operations, because those do not have to process the released groups/grades anymore.
	 * @param eps A positive threshold value.
	 * Coordinates which are smaller than epsilon are considered to be zero.
	 */
	public final void compress(double eps)  {
		if ((m_c[0] != null) && c3ga.zeroGroup_0(m_c[0], eps))
			m_c[0] = null;
		if ((m_c[1] != null) && c3ga.zeroGroup_1(m_c[1], eps))
			m_c[1] = null;
		if ((m_c[2] != null) && c3ga.zeroGroup_2(m_c[2], eps))
			m_c[2] = null;
		if ((m_c[3] != null) && c3ga.zeroGroup_3(m_c[3], eps))
			m_c[3] = null;
		if ((m_c[4] != null) && c3ga.zeroGroup_4(m_c[4], eps))
			m_c[4] = null;
		if ((m_c[5] != null) && c3ga.zeroGroup_5(m_c[5], eps))
			m_c[5] = null;
	}

	/**
	 * Returns this multivector, converted to a string.
	 * The floating point formatter is controlled via c3ga.setStringFormat().
	 */
	public final String toString() {
		return c3ga.string(this);
	}
	
	/**
	 * Returns this multivector, converted to a string.
	 * The floating point formatter is "%f".
	 */
	public final String toString_f() {
		return toString("%f");
	}
	
	/**
	 * Returns this multivector, converted to a string.
	 * The floating point formatter is "%e".
	 */
	public final String toString_e() {
		return toString("%e");
	}
	
	/**
	 * Returns this multivector, converted to a string.
	 * The floating point formatter is "%2.20e".
	 */
	public final String toString_e20() {
		return toString("%2.20e");
	}
	
	/**
	 * Returns this multivector, converted to a string.
	 * @param fp floating point format. Use 'null' for the default format (see c3ga.setStringFormat()).
	 */
	public final String toString(final String fp) {
		return c3ga.string(this, fp);
	}

	public final mv to_mv() {
		return this;
	}
} // end of class mv
