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
public class c3ga 
{ 

	static protected final String[] typenames = 
		new String[] {
			"mv",
			"double",
			"no_t",
			"e1_t",
			"e2_t",
			"e3_t",
			"ni_t",
			"noni_t",
			"I3_t",
			"I5_t",
			"I5i_t",
			"vectorE3GA",
			"bivectorE3GA",
			"rotorE3GA",
			"normalizedPoint",
			"dualSphere",
			"dualPlane",
			"freeVector",
			"freeBivector",
			"flatPoint",
			"pointPair",
			"line",
			"circle",
			"sphere",
			"plane",
			"pseudoscalar",
			"normalizedTranslator",
			"translator",
			"RBM",
			"evenVersor",
			"oddVersor"
		};
	public static final no_t no = new no_t();
	public static final e1_t e1 = new e1_t();
	public static final e2_t e2 = new e2_t();
	public static final e3_t e3 = new e3_t();
	public static final ni_t ni = new ni_t();
	public static final noni_t noni = new noni_t();
	public static final I3_t I3 = new I3_t();
	public static final I5_t I5 = new I5_t();
	public static final I5i_t I5i = new I5i_t();
	public static final vectorE3GA vectorE1 = new vectorE3GA(vectorE3GA.coord_e1_e2_e3, 1.0, 0.0, 0.0);
	public static final vectorE3GA vectorE2 = new vectorE3GA(vectorE3GA.coord_e1_e2_e3, 0.0, 1.0, 0.0);
	public static final vectorE3GA vectorE3 = new vectorE3GA(vectorE3GA.coord_e1_e2_e3, 0.0, 0.0, 1.0);
	public static final normalizedPoint pointAtOrigin = new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni, 0.0, 0.0, 0.0, 0.0);
	public static final dualSphere pointAtInfinity = new dualSphere(dualSphere.coord_no_e1_e2_e3_ni, 0.0, 0.0, 0.0, 0.0, 1.0);


	/**
	 * The dimension of the space
	 */
	public static final int SpaceDim = 5;
	/**
	 * Number of groups/grades of coordinates in a multivector
	 */
	public static final int NbGroups = 6;
	/**
	 * Is the metric of the space Euclidean? (false or true)
	 */
	public static final boolean MetricEuclidean = false;
	/**
	 * Names of the basis vectors.
	 */
	public static final String[] BasisVectorNames = new String[] {
		"no", "e1", "e2", "e3", "ni"
	};
	/**
	 * The constants for the grades, in an array.
	 */
	public static final int[] Grades = {GroupBitmap.GRADE_0, GroupBitmap.GRADE_1, GroupBitmap.GRADE_2, GroupBitmap.GRADE_3, GroupBitmap.GRADE_4, GroupBitmap.GRADE_5, 0, 0, 0, 0, 0, 0};
	/**
	 * The constants for the groups, in an array.
	 */
	public static final int[] Groups = {GroupBitmap.GROUP_0, GroupBitmap.GROUP_1, GroupBitmap.GROUP_2, GroupBitmap.GROUP_3, GroupBitmap.GROUP_4, GroupBitmap.GROUP_5};
	/**
	 * This array can be used to lookup the number of coordinates for a group part of a general multivector.
	 */
	public static final int[] GroupSize = { 1, 5, 10, 10, 5, 1 };
	/**
	 * This array can be used to lookup the number of coordinates based on a group usage bitmap.
	 */
	public static final int[] MvSize = new int[] {
		0, 1, 5, 6, 10, 11, 15, 16, 10, 11, 15, 16, 20, 21, 25, 26, 5, 6, 10, 11, 
		15, 16, 20, 21, 15, 16, 20, 21, 25, 26, 30, 31, 1, 2, 6, 7, 11, 12, 16, 17, 
		11, 12, 16, 17, 21, 22, 26, 27, 6, 7, 11, 12, 16, 17, 21, 22, 16, 17, 21, 22, 
		26, 27, 31, 32	};
	/**
	 * This array of integers contains the 'sign' (even/odd permutation of canonical order) of basis elements in the general multivector.
	 * Use it to answer 'what is the permutation of the coordinate at index [x]'?
	 */
	public static final double[] BasisElementSignByIndex = new double[]
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	/**
	 * This array of integers contains the 'sign' (even/odd permutation of canonical order) of basis elements in the general multivector.
	 * Use it to answer 'what is the permutation of the coordinate of bitmap [x]'?
	 */
	public static final double[] BasisElementSignByBitmap = new double[]
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	/**
	 * This array of integers contains the order of basis elements in the general multivector.
	 * Use it to answer: 'at what index do I find basis element [x] (x = basis vector bitmap)?'
	 */
	public static final int[] BasisElementIndexByBitmap = new int[]
		{0, 1, 2, 6, 3, 7, 8, 16, 4, 9, 10, 17, 11, 18, 19, 26, 5, 12, 13, 20, 14, 21, 22, 27, 15, 23, 24, 28, 25, 29, 30, 31};
	/**
	 * This array of integers contains the indices of basis elements in the general multivector.
	 * Use it to answer: 'what basis element do I find at index [x]'?
	 */
	public static final int[] BasisElementBitmapByIndex = new int[]
		{0, 1, 2, 4, 8, 16, 3, 5, 6, 9, 10, 12, 17, 18, 20, 24, 7, 11, 13, 14, 19, 21, 22, 25, 26, 28, 15, 23, 27, 29, 30, 31};
	/**
	 * This array of grade of each basis elements in the general multivector.
	 * Use it to answer: 'what is the grade of basis element bitmap [x]'?
	 */
	public static final int[] BasisElementGradeByBitmap = new int[]
		{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5};
	/**
	 * This array of group of each basis elements in the general multivector.
	 * Use it to answer: 'what is the group of basis element bitmap [x]'?
	 */
	public static final int[] BasisElementGroupByBitmap = new int[]
		{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5};
	/**
	 * This array of integers contains the order of basis elements in the general multivector.
	 * Use it to answer: 'what basis vectors are in the basis element at position [x]?
	 */
	public static final int[][] BasisElements = new int[][] {
		new int[] {-1},
		new int[] {0, -1},
		new int[] {1, -1},
		new int[] {2, -1},
		new int[] {3, -1},
		new int[] {4, -1},
		new int[] {0, 1, -1},
		new int[] {0, 2, -1},
		new int[] {1, 2, -1},
		new int[] {0, 3, -1},
		new int[] {1, 3, -1},
		new int[] {2, 3, -1},
		new int[] {0, 4, -1},
		new int[] {1, 4, -1},
		new int[] {2, 4, -1},
		new int[] {3, 4, -1},
		new int[] {0, 1, 2, -1},
		new int[] {0, 1, 3, -1},
		new int[] {0, 2, 3, -1},
		new int[] {1, 2, 3, -1},
		new int[] {0, 1, 4, -1},
		new int[] {0, 2, 4, -1},
		new int[] {1, 2, 4, -1},
		new int[] {0, 3, 4, -1},
		new int[] {1, 3, 4, -1},
		new int[] {2, 3, 4, -1},
		new int[] {0, 1, 2, 3, -1},
		new int[] {0, 1, 2, 4, -1},
		new int[] {0, 1, 3, 4, -1},
		new int[] {0, 2, 3, 4, -1},
		new int[] {1, 2, 3, 4, -1},
		new int[] {0, 1, 2, 3, 4, -1}
	};

    // I found sources on the internet which claim that java.util.Random is thread safe.
    // If it turns out not to be thread-safe, port the C# RNG code to Java.
    protected static final java.util.Random s_randomGenerator = new java.util.Random();
    
    protected static final double NextRandomDouble() {
		return s_randomGenerator.nextDouble();
    }

	/** Sets 1 doubles to zero. */
	protected final static void zero_1(final double[] dst) {
		dst[0]=0.0;
	}
	/** Copies 1 doubles from 'src' to 'dst.' */
	protected final static void copy_1(final double[] dst, final double[] src) {
			dst[0] = src[0];
	}
	/** Sets 2 doubles to zero. */
	protected final static void zero_2(final double[] dst) {
		dst[0]=dst[1]=0.0;
	}
	/** Copies 2 doubles from 'src' to 'dst.' */
	protected final static void copy_2(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
	}
	/** Sets 3 doubles to zero. */
	protected final static void zero_3(final double[] dst) {
		dst[0]=dst[1]=dst[2]=0.0;
	}
	/** Copies 3 doubles from 'src' to 'dst.' */
	protected final static void copy_3(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
	}
	/** Sets 4 doubles to zero. */
	protected final static void zero_4(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=0.0;
	}
	/** Copies 4 doubles from 'src' to 'dst.' */
	protected final static void copy_4(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
	}
	/** Sets 5 doubles to zero. */
	protected final static void zero_5(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=0.0;
	}
	/** Copies 5 doubles from 'src' to 'dst.' */
	protected final static void copy_5(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
	}
	/** Sets 6 doubles to zero. */
	protected final static void zero_6(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=0.0;
	}
	/** Copies 6 doubles from 'src' to 'dst.' */
	protected final static void copy_6(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
	}
	/** Sets 7 doubles to zero. */
	protected final static void zero_7(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=0.0;
	}
	/** Copies 7 doubles from 'src' to 'dst.' */
	protected final static void copy_7(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
	}
	/** Sets 8 doubles to zero. */
	protected final static void zero_8(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=0.0;
	}
	/** Copies 8 doubles from 'src' to 'dst.' */
	protected final static void copy_8(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
	}
	/** Sets 9 doubles to zero. */
	protected final static void zero_9(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=0.0;
	}
	/** Copies 9 doubles from 'src' to 'dst.' */
	protected final static void copy_9(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
	}
	/** Sets 10 doubles to zero. */
	protected final static void zero_10(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=0.0;
	}
	/** Copies 10 doubles from 'src' to 'dst.' */
	protected final static void copy_10(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
	}
	/** Sets 11 doubles to zero. */
	protected final static void zero_11(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=0.0;
	}
	/** Copies 11 doubles from 'src' to 'dst.' */
	protected final static void copy_11(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
	}
	/** Sets 12 doubles to zero. */
	protected final static void zero_12(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=dst[11]=0.0;
	}
	/** Copies 12 doubles from 'src' to 'dst.' */
	protected final static void copy_12(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
			dst[11] = src[11];
	}
	/** Sets 13 doubles to zero. */
	protected final static void zero_13(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=dst[11]=dst[12]=0.0;
	}
	/** Copies 13 doubles from 'src' to 'dst.' */
	protected final static void copy_13(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
			dst[11] = src[11];
			dst[12] = src[12];
	}
	/** Sets 14 doubles to zero. */
	protected final static void zero_14(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=dst[11]=dst[12]=dst[13]=0.0;
	}
	/** Copies 14 doubles from 'src' to 'dst.' */
	protected final static void copy_14(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
			dst[11] = src[11];
			dst[12] = src[12];
			dst[13] = src[13];
	}
	/** Sets 15 doubles to zero. */
	protected final static void zero_15(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=dst[11]=dst[12]=dst[13]=dst[14]=0.0;
	}
	/** Copies 15 doubles from 'src' to 'dst.' */
	protected final static void copy_15(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
			dst[11] = src[11];
			dst[12] = src[12];
			dst[13] = src[13];
			dst[14] = src[14];
	}
	/** Sets 16 doubles to zero. */
	protected final static void zero_16(final double[] dst) {
		dst[0]=dst[1]=dst[2]=dst[3]=dst[4]=dst[5]=dst[6]=dst[7]=dst[8]=dst[9]=dst[10]=dst[11]=dst[12]=dst[13]=dst[14]=dst[15]=0.0;
	}
	/** Copies 16 doubles from 'src' to 'dst.' */
	protected final static void copy_16(final double[] dst, final double[] src) {
			dst[0] = src[0];
			dst[1] = src[1];
			dst[2] = src[2];
			dst[3] = src[3];
			dst[4] = src[4];
			dst[5] = src[5];
			dst[6] = src[6];
			dst[7] = src[7];
			dst[8] = src[8];
			dst[9] = src[9];
			dst[10] = src[10];
			dst[11] = src[11];
			dst[12] = src[12];
			dst[13] = src[13];
			dst[14] = src[14];
			dst[15] = src[15];
	}
	/** Sets N doubles to zero. */
	protected final static void zero_N(final double[] dst, final int N) {
		for (int i = 0; i < N; i++)
			dst[i] = 0.0;
	}
	/** Copies N doubles from 'src' to 'dst'. */
	protected final static void copy_N(final double[] dst, final double[] src, final int N) {
		for (int i = 0; i < N; i++)
			dst[i] = src[i];
	}


	private final static String DEFAULT_FP = "%2.2f";
	private final static String DEFAULT_START = "";
	private final static String DEFAULT_END = "";
	private final static String DEFAULT_MUL = "*";
	private final static String DEFAULT_WEDGE = "^";
	private final static String DEFAULT_PLUS = " + ";
	private final static String DEFAULT_MINUS = " - ";

	/*
	 * These strings determine how the output of string() is formatted.
	 * You can alter them at runtime using setStringFormat(). 
	 */
	protected static String string_fp = DEFAULT_FP;
	protected static String string_start = DEFAULT_START;
	protected static String string_end = DEFAULT_END;
	protected static String string_mul = DEFAULT_MUL;
	protected static String string_wedge = DEFAULT_WEDGE;
	protected static String string_plus = DEFAULT_PLUS;
	protected static String string_minus = DEFAULT_MINUS;
	
	public final static String STRING_FP = "fp";
	public final static String STRING_START = "start";
	public final static String STRING_END = "end";
	public final static String STRING_MUL = "mul";
	public final static String STRING_WEDGE = "wedge";
	public final static String STRING_PLUS = "plus";
	public final static String STRING_MINUS= "minus";

	/**
	 * Sets the formatting of toString().
	 * 
	 * @param what What formatter to set. Valid values: STRING_FP, STRING_START, STRING_END, STRING_MUL, STRING_WEDGE, STRING_PLUS, STRING_MINUS.
	 * @param format The value for 'what'. Use 'null' to set the default value.
	 */
	public final static void setStringFormat(final String what, final String format) {
		if (what.equals(STRING_FP)) 
			string_fp = (format != null) ? format : DEFAULT_FP;
		else if (what.equals(STRING_START)) 
			string_start = (format != null) ? format : DEFAULT_START;
		else if (what.equals(STRING_END)) 
			string_end = (format != null) ? format : DEFAULT_END;
		else if (what.equals(STRING_MUL)) 
			string_mul = (format != null) ? format : DEFAULT_MUL;
		else if (what.equals(STRING_WEDGE)) 
			string_wedge = (format != null) ? format : DEFAULT_WEDGE;
		else if (what.equals(STRING_PLUS)) 
			string_plus = (format != null) ? format : DEFAULT_PLUS;
		else if (what.equals(STRING_MINUS)) 
			string_minus = (format != null) ? format : DEFAULT_MINUS;
		else throw new RuntimeException("invalid argument to setStringFormat(): " + what);
	}
	
   /** Converts a multivector to a String using default float format. */
	public final static String string(final mv_if value) {
		return string(value, null);
	}
	
   /** 
    * Converts a multivector to a String according to a float format like  "%2.2f"
	* @param fp floating point format. Use 'null' for the default format (see setStringFormat()).
	*/
	public final static String string(final mv_if value, String fp) {
		mv obj = value.to_mv();
		StringBuffer result = new StringBuffer();
		int ia = 0; // global index into coordinates (runs from 0 to 31)
		int cnt = 0; // how many coordinates printed so far

		// set up the floating point precision
		if (fp == null) fp = string_fp;

		// start the string
		result.append(string_start);

		// print all coordinates
		for (int g = 0; g < 6; g++) {
			double[] Cg = obj.m_c[g];
			if (Cg != null) {
				for (int b = 0; b < GroupSize[g]; b++) {
					double coord = (double)BasisElementSignByIndex[ia] * Cg[b];
					
					// goal: print [+|-]obj.m_c[k][* basisVector1 ^ ... ^ basisVectorN]
					
					String tmpFloatStr = String.format(fp, Math.abs(coord));

					if (Double.parseDouble(tmpFloatStr) != 0.0) {
						// print [+|-]
						result.append((coord >= 0.0) 
							? ((cnt>0) ? string_plus : "")
							: string_minus);
						// print obj.m_c[k]
						result.append(tmpFloatStr);

						if (g != 0) { // if not grade 0, print [* basisVector1 ^ ... ^ basisVectorN]
							result.append(string_mul);

							// print all basis vectors
							int bei = 0;
							while (BasisElements[ia][bei] >= 0) {
								if (bei > 0)
									result.append(string_wedge);
								result.append(BasisVectorNames[BasisElements[ia][bei]]);
								bei++;
							}
						}

						cnt++;
					}
					ia++;
				}
			}
			else ia += GroupSize[g];
		}

		// if no coordinates printed: 0
		if (cnt == 0) result.append("0");

		// end the string
		result.append(string_end);

		return result.toString();
	}
	

	/**
     *  Simple way to call parser (regardless of whether it is a builtin or ANTLR parser).
     *  
     *  Throws a ParseException on failure.
     *  
     *  When an ANTLR based parser throws an exception, 
     *  all details (like line number and cause) are lost. 
     *  If these details are required, call the ANTLR parser directly.
     * 
     *  @param str The multivector string to be parsed (can be output of mv.ToString()).
     *  @return Multivector value represented by 'str'.
     */
    public final static mv parse(String str) throws ParseException
    {
        return Parser.parse(str, "string");
    }
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 0 -> group 0)
	 */
	protected final static void gp_default_0_0_0(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 1 -> group 1)
	 */
	protected final static void gp_default_0_1_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[0]*B[1];
		C[2] += A[0]*B[2];
		C[3] += A[0]*B[3];
		C[4] += A[0]*B[4];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 2 -> group 2)
	 */
	protected final static void gp_default_0_2_2(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[0]*B[1];
		C[2] += A[0]*B[2];
		C[3] += A[0]*B[3];
		C[4] += A[0]*B[4];
		C[5] += A[0]*B[5];
		C[6] += A[0]*B[6];
		C[7] += A[0]*B[7];
		C[8] += A[0]*B[8];
		C[9] += A[0]*B[9];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 3 -> group 3)
	 */
	protected final static void gp_default_0_3_3(double[] A, double[] B, double[] C) {
		gp_default_0_2_2(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 4 -> group 4)
	 */
	protected final static void gp_default_0_4_4(double[] A, double[] B, double[] C) {
		gp_default_0_1_1(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 5 -> group 5)
	 */
	protected final static void gp_default_0_5_5(double[] A, double[] B, double[] C) {
		gp_default_0_0_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 0 -> group 1)
	 */
	protected final static void gp_default_1_0_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[1]*B[0];
		C[2] += A[2]*B[0];
		C[3] += A[3]*B[0];
		C[4] += A[4]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 1 -> group 0)
	 */
	protected final static void gp_default_1_1_0(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[4]+A[1]*B[1]+A[2]*B[2]+A[3]*B[3]-A[4]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 1 -> group 2)
	 */
	protected final static void gp_default_1_1_2(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[1]-A[1]*B[0]);
		C[1] += (A[0]*B[2]-A[2]*B[0]);
		C[2] += (A[1]*B[2]-A[2]*B[1]);
		C[3] += (A[0]*B[3]-A[3]*B[0]);
		C[4] += (A[1]*B[3]-A[3]*B[1]);
		C[5] += (A[2]*B[3]-A[3]*B[2]);
		C[6] += (A[0]*B[4]-A[4]*B[0]);
		C[7] += (A[1]*B[4]-A[4]*B[1]);
		C[8] += (A[2]*B[4]-A[4]*B[2]);
		C[9] += (A[3]*B[4]-A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 2 -> group 1)
	 */
	protected final static void gp_default_1_2_1(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[6]-A[1]*B[0]-A[2]*B[1]-A[3]*B[3]);
		C[1] += (A[0]*B[7]-A[2]*B[2]-A[3]*B[4]-A[4]*B[0]);
		C[2] += (A[0]*B[8]+A[1]*B[2]-A[3]*B[5]-A[4]*B[1]);
		C[3] += (A[0]*B[9]+A[1]*B[4]+A[2]*B[5]-A[4]*B[3]);
		C[4] += (A[1]*B[7]+A[2]*B[8]+A[3]*B[9]-A[4]*B[6]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 2 -> group 3)
	 */
	protected final static void gp_default_1_2_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]-A[1]*B[1]+A[2]*B[0]);
		C[1] += (A[0]*B[4]-A[1]*B[3]+A[3]*B[0]);
		C[2] += (A[0]*B[5]-A[2]*B[3]+A[3]*B[1]);
		C[3] += (A[1]*B[5]-A[2]*B[4]+A[3]*B[2]);
		C[4] += (A[0]*B[7]-A[1]*B[6]+A[4]*B[0]);
		C[5] += (A[0]*B[8]-A[2]*B[6]+A[4]*B[1]);
		C[6] += (A[1]*B[8]-A[2]*B[7]+A[4]*B[2]);
		C[7] += (A[0]*B[9]-A[3]*B[6]+A[4]*B[3]);
		C[8] += (A[1]*B[9]-A[3]*B[7]+A[4]*B[4]);
		C[9] += (A[2]*B[9]-A[3]*B[8]+A[4]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 3 -> group 2)
	 */
	protected final static void gp_default_1_3_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[4]+A[2]*B[0]+A[3]*B[1]);
		C[1] += (-A[0]*B[5]-A[1]*B[0]+A[3]*B[2]);
		C[2] += (-A[0]*B[6]+A[3]*B[3]-A[4]*B[0]);
		C[3] += (-A[0]*B[7]-A[1]*B[1]-A[2]*B[2]);
		C[4] += (-A[0]*B[8]-A[2]*B[3]-A[4]*B[1]);
		C[5] += (-A[0]*B[9]+A[1]*B[3]-A[4]*B[2]);
		C[6] += (-A[1]*B[4]-A[2]*B[5]-A[3]*B[7]);
		C[7] += (-A[2]*B[6]-A[3]*B[8]-A[4]*B[4]);
		C[8] += (A[1]*B[6]-A[3]*B[9]-A[4]*B[5]);
		C[9] += (A[1]*B[8]+A[2]*B[9]-A[4]*B[7]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 3 -> group 4)
	 */
	protected final static void gp_default_1_3_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[2]+A[2]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[6]-A[1]*B[5]+A[2]*B[4]-A[4]*B[0]);
		C[2] += (A[0]*B[8]-A[1]*B[7]+A[3]*B[4]-A[4]*B[1]);
		C[3] += (A[0]*B[9]-A[2]*B[7]+A[3]*B[5]-A[4]*B[2]);
		C[4] += (A[1]*B[9]-A[2]*B[8]+A[3]*B[6]-A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 4 -> group 3)
	 */
	protected final static void gp_default_1_4_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[2]+A[2]*B[0]);
		C[2] += (A[0]*B[3]-A[1]*B[0]);
		C[3] += (A[0]*B[4]-A[4]*B[0]);
		C[4] += (A[2]*B[1]+A[3]*B[2]);
		C[5] += (-A[1]*B[1]+A[3]*B[3]);
		C[6] += (A[3]*B[4]-A[4]*B[1]);
		C[7] += (-A[1]*B[2]-A[2]*B[3]);
		C[8] += (-A[2]*B[4]-A[4]*B[2]);
		C[9] += (A[1]*B[4]-A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 4 -> group 5)
	 */
	protected final static void gp_default_1_4_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[4]-A[1]*B[3]+A[2]*B[2]-A[3]*B[1]+A[4]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 5 -> group 4)
	 */
	protected final static void gp_default_1_5_4(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[0];
		C[1] += -A[3]*B[0];
		C[2] += A[2]*B[0];
		C[3] += -A[1]*B[0];
		C[4] += -A[4]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 0 -> group 2)
	 */
	protected final static void gp_default_2_0_2(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[1]*B[0];
		C[2] += A[2]*B[0];
		C[3] += A[3]*B[0];
		C[4] += A[4]*B[0];
		C[5] += A[5]*B[0];
		C[6] += A[6]*B[0];
		C[7] += A[7]*B[0];
		C[8] += A[8]*B[0];
		C[9] += A[9]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 1 -> group 1)
	 */
	protected final static void gp_default_2_1_1(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[1]+A[1]*B[2]+A[3]*B[3]-A[6]*B[0]);
		C[1] += (A[0]*B[4]+A[2]*B[2]+A[4]*B[3]-A[7]*B[0]);
		C[2] += (A[1]*B[4]-A[2]*B[1]+A[5]*B[3]-A[8]*B[0]);
		C[3] += (A[3]*B[4]-A[4]*B[1]-A[5]*B[2]-A[9]*B[0]);
		C[4] += (A[6]*B[4]-A[7]*B[1]-A[8]*B[2]-A[9]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 1 -> group 3)
	 */
	protected final static void gp_default_2_1_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]-A[1]*B[1]+A[2]*B[0]);
		C[1] += (A[0]*B[3]-A[3]*B[1]+A[4]*B[0]);
		C[2] += (A[1]*B[3]-A[3]*B[2]+A[5]*B[0]);
		C[3] += (A[2]*B[3]-A[4]*B[2]+A[5]*B[1]);
		C[4] += (A[0]*B[4]-A[6]*B[1]+A[7]*B[0]);
		C[5] += (A[1]*B[4]-A[6]*B[2]+A[8]*B[0]);
		C[6] += (A[2]*B[4]-A[7]*B[2]+A[8]*B[1]);
		C[7] += (A[3]*B[4]-A[6]*B[3]+A[9]*B[0]);
		C[8] += (A[4]*B[4]-A[7]*B[3]+A[9]*B[1]);
		C[9] += (A[5]*B[4]-A[8]*B[3]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 0)
	 */
	protected final static void gp_default_2_2_0(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[7]-A[1]*B[8]-A[2]*B[2]-A[3]*B[9]-A[4]*B[4]-A[5]*B[5]+A[6]*B[6]-A[7]*B[0]-A[8]*B[1]-A[9]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 2)
	 */
	protected final static void gp_default_2_2_2(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[6]-A[1]*B[2]+A[2]*B[1]-A[3]*B[4]+A[4]*B[3]-A[6]*B[0]);
		C[1] += (A[0]*B[2]+A[1]*B[6]-A[2]*B[0]-A[3]*B[5]+A[5]*B[3]-A[6]*B[1]);
		C[2] += (-A[0]*B[8]+A[1]*B[7]-A[4]*B[5]+A[5]*B[4]-A[7]*B[1]+A[8]*B[0]);
		C[3] += (A[0]*B[4]+A[1]*B[5]+A[3]*B[6]-A[4]*B[0]-A[5]*B[1]-A[6]*B[3]);
		C[4] += (-A[0]*B[9]+A[2]*B[5]+A[3]*B[7]-A[5]*B[2]-A[7]*B[3]+A[9]*B[0]);
		C[5] += (-A[1]*B[9]-A[2]*B[4]+A[3]*B[8]+A[4]*B[2]-A[8]*B[3]+A[9]*B[1]);
		C[6] += (A[0]*B[7]+A[1]*B[8]+A[3]*B[9]-A[7]*B[0]-A[8]*B[1]-A[9]*B[3]);
		C[7] += (A[2]*B[8]+A[4]*B[9]+A[6]*B[7]-A[7]*B[6]-A[8]*B[2]-A[9]*B[4]);
		C[8] += (-A[2]*B[7]+A[5]*B[9]+A[6]*B[8]+A[7]*B[2]-A[8]*B[6]-A[9]*B[5]);
		C[9] += (-A[4]*B[7]-A[5]*B[8]+A[6]*B[9]+A[7]*B[4]+A[8]*B[5]-A[9]*B[6]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 4)
	 */
	protected final static void gp_default_2_2_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[5]-A[1]*B[4]+A[2]*B[3]+A[3]*B[2]-A[4]*B[1]+A[5]*B[0]);
		C[1] += (A[0]*B[8]-A[1]*B[7]+A[2]*B[6]+A[6]*B[2]-A[7]*B[1]+A[8]*B[0]);
		C[2] += (A[0]*B[9]-A[3]*B[7]+A[4]*B[6]+A[6]*B[4]-A[7]*B[3]+A[9]*B[0]);
		C[3] += (A[1]*B[9]-A[3]*B[8]+A[5]*B[6]+A[6]*B[5]-A[8]*B[3]+A[9]*B[1]);
		C[4] += (A[2]*B[9]-A[4]*B[8]+A[5]*B[7]+A[7]*B[5]-A[8]*B[4]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 1)
	 */
	protected final static void gp_default_2_3_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[4]-A[1]*B[5]-A[2]*B[0]-A[3]*B[7]-A[4]*B[1]-A[5]*B[2]);
		C[1] += (-A[1]*B[6]-A[3]*B[8]-A[5]*B[3]-A[6]*B[4]+A[8]*B[0]+A[9]*B[1]);
		C[2] += (A[0]*B[6]-A[3]*B[9]+A[4]*B[3]-A[6]*B[5]-A[7]*B[0]+A[9]*B[2]);
		C[3] += (A[0]*B[8]+A[1]*B[9]-A[2]*B[3]-A[6]*B[7]-A[7]*B[1]-A[8]*B[2]);
		C[4] += (-A[2]*B[6]-A[4]*B[8]-A[5]*B[9]-A[7]*B[4]-A[8]*B[5]-A[9]*B[7]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 3)
	 */
	protected final static void gp_default_2_3_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[5]+A[1]*B[4]+A[3]*B[3]-A[4]*B[2]+A[5]*B[1]-A[6]*B[0]);
		C[1] += (-A[0]*B[7]-A[1]*B[3]+A[2]*B[2]+A[3]*B[4]-A[5]*B[0]-A[6]*B[1]);
		C[2] += (A[0]*B[3]-A[1]*B[7]-A[2]*B[1]+A[3]*B[5]+A[4]*B[0]-A[6]*B[2]);
		C[3] += (A[0]*B[9]-A[1]*B[8]+A[3]*B[6]-A[7]*B[2]+A[8]*B[1]-A[9]*B[0]);
		C[4] += (-A[1]*B[6]+A[2]*B[5]-A[3]*B[8]+A[4]*B[7]-A[8]*B[0]-A[9]*B[1]);
		C[5] += (A[0]*B[6]-A[2]*B[4]-A[3]*B[9]+A[5]*B[7]+A[7]*B[0]-A[9]*B[2]);
		C[6] += (-A[4]*B[9]+A[5]*B[8]+A[6]*B[6]-A[7]*B[5]+A[8]*B[4]-A[9]*B[3]);
		C[7] += (A[0]*B[8]+A[1]*B[9]-A[4]*B[4]-A[5]*B[5]+A[7]*B[1]+A[8]*B[2]);
		C[8] += (A[2]*B[9]-A[5]*B[6]+A[6]*B[8]-A[7]*B[7]+A[8]*B[3]+A[9]*B[4]);
		C[9] += (-A[2]*B[8]+A[4]*B[6]+A[6]*B[9]-A[7]*B[3]-A[8]*B[7]+A[9]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 5)
	 */
	protected final static void gp_default_2_3_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[9]-A[1]*B[8]+A[2]*B[7]+A[3]*B[6]-A[4]*B[5]+A[5]*B[4]-A[6]*B[3]+A[7]*B[2]-A[8]*B[1]+A[9]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 4 -> group 2)
	 */
	protected final static void gp_default_2_4_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[1]-A[3]*B[2]-A[5]*B[0]);
		C[1] += (A[0]*B[1]-A[3]*B[3]+A[4]*B[0]);
		C[2] += (-A[3]*B[4]+A[6]*B[1]-A[9]*B[0]);
		C[3] += (A[0]*B[2]+A[1]*B[3]-A[2]*B[0]);
		C[4] += (A[1]*B[4]+A[6]*B[2]+A[8]*B[0]);
		C[5] += (-A[0]*B[4]+A[6]*B[3]-A[7]*B[0]);
		C[6] += (-A[2]*B[1]-A[4]*B[2]-A[5]*B[3]);
		C[7] += (-A[5]*B[4]+A[8]*B[1]+A[9]*B[2]);
		C[8] += (A[4]*B[4]-A[7]*B[1]+A[9]*B[3]);
		C[9] += (-A[2]*B[4]-A[7]*B[2]-A[8]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 4 -> group 4)
	 */
	protected final static void gp_default_2_4_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[2]+A[3]*B[1]-A[6]*B[0]);
		C[1] += (A[3]*B[4]-A[4]*B[3]+A[5]*B[2]-A[9]*B[0]);
		C[2] += (-A[1]*B[4]+A[2]*B[3]-A[5]*B[1]+A[8]*B[0]);
		C[3] += (A[0]*B[4]-A[2]*B[2]+A[4]*B[1]-A[7]*B[0]);
		C[4] += (A[6]*B[4]-A[7]*B[3]+A[8]*B[2]-A[9]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 5 -> group 3)
	 */
	protected final static void gp_default_2_5_3(double[] A, double[] B, double[] C) {
		C[0] += -A[3]*B[0];
		C[1] += A[1]*B[0];
		C[2] += -A[0]*B[0];
		C[3] += -A[6]*B[0];
		C[4] += -A[5]*B[0];
		C[5] += A[4]*B[0];
		C[6] += -A[9]*B[0];
		C[7] += -A[2]*B[0];
		C[8] += A[8]*B[0];
		C[9] += -A[7]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 0 -> group 3)
	 */
	protected final static void gp_default_3_0_3(double[] A, double[] B, double[] C) {
		gp_default_2_0_2(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 1 -> group 2)
	 */
	protected final static void gp_default_3_1_2(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]+A[1]*B[3]-A[4]*B[0]);
		C[1] += (-A[0]*B[1]+A[2]*B[3]-A[5]*B[0]);
		C[2] += (-A[0]*B[4]+A[3]*B[3]-A[6]*B[0]);
		C[3] += (-A[1]*B[1]-A[2]*B[2]-A[7]*B[0]);
		C[4] += (-A[1]*B[4]-A[3]*B[2]-A[8]*B[0]);
		C[5] += (-A[2]*B[4]+A[3]*B[1]-A[9]*B[0]);
		C[6] += (-A[4]*B[1]-A[5]*B[2]-A[7]*B[3]);
		C[7] += (-A[4]*B[4]-A[6]*B[2]-A[8]*B[3]);
		C[8] += (-A[5]*B[4]+A[6]*B[1]-A[9]*B[3]);
		C[9] += (-A[7]*B[4]+A[8]*B[1]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 1 -> group 4)
	 */
	protected final static void gp_default_3_1_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[2]+A[2]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[4]-A[4]*B[2]+A[5]*B[1]-A[6]*B[0]);
		C[2] += (A[1]*B[4]-A[4]*B[3]+A[7]*B[1]-A[8]*B[0]);
		C[3] += (A[2]*B[4]-A[5]*B[3]+A[7]*B[2]-A[9]*B[0]);
		C[4] += (A[3]*B[4]-A[6]*B[3]+A[8]*B[2]-A[9]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 1)
	 */
	protected final static void gp_default_3_2_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[2]-A[1]*B[4]-A[2]*B[5]-A[4]*B[0]-A[5]*B[1]-A[7]*B[3]);
		C[1] += (A[0]*B[8]+A[1]*B[9]-A[3]*B[5]-A[4]*B[6]-A[6]*B[1]-A[8]*B[3]);
		C[2] += (-A[0]*B[7]+A[2]*B[9]+A[3]*B[4]-A[5]*B[6]+A[6]*B[0]-A[9]*B[3]);
		C[3] += (-A[1]*B[7]-A[2]*B[8]-A[3]*B[2]-A[7]*B[6]+A[8]*B[0]+A[9]*B[1]);
		C[4] += (-A[4]*B[7]-A[5]*B[8]-A[6]*B[2]-A[7]*B[9]-A[8]*B[4]-A[9]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 3)
	 */
	protected final static void gp_default_3_2_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[6]-A[1]*B[5]+A[2]*B[4]-A[3]*B[3]-A[4]*B[1]+A[5]*B[0]);
		C[1] += (A[0]*B[5]+A[1]*B[6]-A[2]*B[2]+A[3]*B[1]-A[4]*B[3]+A[7]*B[0]);
		C[2] += (-A[0]*B[4]+A[1]*B[2]+A[2]*B[6]-A[3]*B[0]-A[5]*B[3]+A[7]*B[1]);
		C[3] += (A[0]*B[9]-A[1]*B[8]+A[2]*B[7]-A[6]*B[3]+A[8]*B[1]-A[9]*B[0]);
		C[4] += (A[0]*B[8]+A[1]*B[9]-A[5]*B[2]+A[6]*B[1]-A[7]*B[4]+A[8]*B[3]);
		C[5] += (-A[0]*B[7]+A[2]*B[9]+A[4]*B[2]-A[6]*B[0]-A[7]*B[5]+A[9]*B[3]);
		C[6] += (A[3]*B[9]-A[4]*B[8]+A[5]*B[7]-A[6]*B[6]-A[8]*B[5]+A[9]*B[4]);
		C[7] += (-A[1]*B[7]-A[2]*B[8]+A[4]*B[4]+A[5]*B[5]-A[8]*B[0]-A[9]*B[1]);
		C[8] += (-A[3]*B[8]-A[4]*B[9]+A[6]*B[5]+A[7]*B[7]-A[8]*B[6]-A[9]*B[2]);
		C[9] += (A[3]*B[7]-A[5]*B[9]-A[6]*B[4]+A[7]*B[8]+A[8]*B[2]-A[9]*B[6]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 5)
	 */
	protected final static void gp_default_3_2_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[9]-A[1]*B[8]+A[2]*B[7]-A[3]*B[6]+A[4]*B[5]-A[5]*B[4]+A[6]*B[3]+A[7]*B[2]-A[8]*B[1]+A[9]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 0)
	 */
	protected final static void gp_default_3_3_0(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[6]+A[1]*B[8]+A[2]*B[9]-A[3]*B[3]+A[4]*B[4]+A[5]*B[5]+A[6]*B[0]+A[7]*B[7]+A[8]*B[1]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 2)
	 */
	protected final static void gp_default_3_3_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[5]-A[1]*B[7]-A[2]*B[3]+A[3]*B[2]+A[5]*B[0]+A[7]*B[1]);
		C[1] += (A[0]*B[4]+A[1]*B[3]-A[2]*B[7]-A[3]*B[1]-A[4]*B[0]+A[7]*B[2]);
		C[2] += (A[1]*B[9]-A[2]*B[8]+A[4]*B[5]-A[5]*B[4]+A[8]*B[2]-A[9]*B[1]);
		C[3] += (-A[0]*B[3]+A[1]*B[4]+A[2]*B[5]+A[3]*B[0]-A[4]*B[1]-A[5]*B[2]);
		C[4] += (-A[0]*B[9]+A[2]*B[6]+A[4]*B[7]-A[6]*B[2]-A[7]*B[4]+A[9]*B[0]);
		C[5] += (A[0]*B[8]-A[1]*B[6]+A[5]*B[7]+A[6]*B[1]-A[7]*B[5]-A[8]*B[0]);
		C[6] += (-A[0]*B[6]-A[1]*B[8]-A[2]*B[9]+A[6]*B[0]+A[8]*B[1]+A[9]*B[2]);
		C[7] += (-A[3]*B[9]+A[5]*B[6]-A[6]*B[5]+A[7]*B[8]-A[8]*B[7]+A[9]*B[3]);
		C[8] += (A[3]*B[8]-A[4]*B[6]+A[6]*B[4]+A[7]*B[9]-A[8]*B[3]-A[9]*B[7]);
		C[9] += (-A[3]*B[6]-A[4]*B[8]-A[5]*B[9]+A[6]*B[3]+A[8]*B[4]+A[9]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 4)
	 */
	protected final static void gp_default_3_3_4(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[7]+A[1]*B[5]-A[2]*B[4]-A[4]*B[2]+A[5]*B[1]-A[7]*B[0]);
		C[1] += (-A[1]*B[9]+A[2]*B[8]-A[3]*B[7]-A[7]*B[3]+A[8]*B[2]-A[9]*B[1]);
		C[2] += (A[0]*B[9]-A[2]*B[6]+A[3]*B[5]+A[5]*B[3]-A[6]*B[2]+A[9]*B[0]);
		C[3] += (-A[0]*B[8]+A[1]*B[6]-A[3]*B[4]-A[4]*B[3]+A[6]*B[1]-A[8]*B[0]);
		C[4] += (-A[4]*B[9]+A[5]*B[8]-A[6]*B[7]-A[7]*B[6]+A[8]*B[5]-A[9]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 4 -> group 1)
	 */
	protected final static void gp_default_3_4_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[1]-A[1]*B[2]-A[2]*B[3]+A[3]*B[0]);
		C[1] += (-A[2]*B[4]+A[5]*B[1]+A[7]*B[2]+A[9]*B[0]);
		C[2] += (A[1]*B[4]-A[4]*B[1]+A[7]*B[3]-A[8]*B[0]);
		C[3] += (-A[0]*B[4]-A[4]*B[2]-A[5]*B[3]+A[6]*B[0]);
		C[4] += (-A[3]*B[4]+A[6]*B[1]+A[8]*B[2]+A[9]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 4 -> group 3)
	 */
	protected final static void gp_default_3_4_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[3]+A[2]*B[2]-A[7]*B[0]);
		C[1] += (A[0]*B[3]-A[2]*B[1]+A[5]*B[0]);
		C[2] += (-A[0]*B[2]+A[1]*B[1]-A[4]*B[0]);
		C[3] += (-A[4]*B[3]+A[5]*B[2]-A[7]*B[1]);
		C[4] += (-A[2]*B[4]+A[3]*B[3]-A[9]*B[0]);
		C[5] += (A[1]*B[4]-A[3]*B[2]+A[8]*B[0]);
		C[6] += (-A[7]*B[4]+A[8]*B[3]-A[9]*B[2]);
		C[7] += (-A[0]*B[4]+A[3]*B[1]-A[6]*B[0]);
		C[8] += (A[5]*B[4]-A[6]*B[3]+A[9]*B[1]);
		C[9] += (-A[4]*B[4]+A[6]*B[2]-A[8]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 5 -> group 2)
	 */
	protected final static void gp_default_3_5_2(double[] A, double[] B, double[] C) {
		C[0] += A[2]*B[0];
		C[1] += -A[1]*B[0];
		C[2] += A[7]*B[0];
		C[3] += A[0]*B[0];
		C[4] += -A[5]*B[0];
		C[5] += A[4]*B[0];
		C[6] += A[3]*B[0];
		C[7] += A[9]*B[0];
		C[8] += -A[8]*B[0];
		C[9] += A[6]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 0 -> group 4)
	 */
	protected final static void gp_default_4_0_4(double[] A, double[] B, double[] C) {
		gp_default_1_0_1(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 1 -> group 3)
	 */
	protected final static void gp_default_4_1_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[0]);
		C[1] += (-A[0]*B[2]-A[2]*B[0]);
		C[2] += (A[0]*B[1]-A[3]*B[0]);
		C[3] += (A[0]*B[4]-A[4]*B[0]);
		C[4] += (-A[1]*B[2]-A[2]*B[3]);
		C[5] += (A[1]*B[1]-A[3]*B[3]);
		C[6] += (A[1]*B[4]-A[4]*B[3]);
		C[7] += (A[2]*B[1]+A[3]*B[2]);
		C[8] += (A[2]*B[4]+A[4]*B[2]);
		C[9] += (A[3]*B[4]-A[4]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 1 -> group 5)
	 */
	protected final static void gp_default_4_1_5(double[] A, double[] B, double[] C) {
		gp_default_1_4_5(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 2 -> group 2)
	 */
	protected final static void gp_default_4_2_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[5]-A[1]*B[1]-A[2]*B[3]);
		C[1] += (A[0]*B[4]+A[1]*B[0]-A[3]*B[3]);
		C[2] += (-A[0]*B[9]+A[1]*B[6]-A[4]*B[3]);
		C[3] += (-A[0]*B[2]+A[2]*B[0]+A[3]*B[1]);
		C[4] += (A[0]*B[8]+A[2]*B[6]+A[4]*B[1]);
		C[5] += (-A[0]*B[7]+A[3]*B[6]-A[4]*B[0]);
		C[6] += (-A[1]*B[2]-A[2]*B[4]-A[3]*B[5]);
		C[7] += (A[1]*B[8]+A[2]*B[9]-A[4]*B[5]);
		C[8] += (-A[1]*B[7]+A[3]*B[9]+A[4]*B[4]);
		C[9] += (-A[2]*B[7]-A[3]*B[8]-A[4]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 2 -> group 4)
	 */
	protected final static void gp_default_4_2_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[6]-A[1]*B[3]+A[2]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[9]-A[2]*B[5]+A[3]*B[4]-A[4]*B[3]);
		C[2] += (-A[0]*B[8]+A[1]*B[5]-A[3]*B[2]+A[4]*B[1]);
		C[3] += (A[0]*B[7]-A[1]*B[4]+A[2]*B[2]-A[4]*B[0]);
		C[4] += (A[1]*B[9]-A[2]*B[8]+A[3]*B[7]-A[4]*B[6]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 3 -> group 1)
	 */
	protected final static void gp_default_4_3_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[3]+A[1]*B[0]+A[2]*B[1]+A[3]*B[2]);
		C[1] += (-A[0]*B[9]-A[1]*B[5]-A[2]*B[7]+A[4]*B[2]);
		C[2] += (A[0]*B[8]+A[1]*B[4]-A[3]*B[7]-A[4]*B[1]);
		C[3] += (-A[0]*B[6]+A[2]*B[4]+A[3]*B[5]+A[4]*B[0]);
		C[4] += (-A[1]*B[6]-A[2]*B[8]-A[3]*B[9]+A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 3 -> group 3)
	 */
	protected final static void gp_default_4_3_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[7]+A[2]*B[2]-A[3]*B[1]);
		C[1] += (A[0]*B[5]-A[1]*B[2]+A[3]*B[0]);
		C[2] += (-A[0]*B[4]+A[1]*B[1]-A[2]*B[0]);
		C[3] += (-A[1]*B[7]+A[2]*B[5]-A[3]*B[4]);
		C[4] += (-A[0]*B[9]+A[3]*B[3]-A[4]*B[2]);
		C[5] += (A[0]*B[8]-A[2]*B[3]+A[4]*B[1]);
		C[6] += (-A[2]*B[9]+A[3]*B[8]-A[4]*B[7]);
		C[7] += (-A[0]*B[6]+A[1]*B[3]-A[4]*B[0]);
		C[8] += (A[1]*B[9]-A[3]*B[6]+A[4]*B[5]);
		C[9] += (-A[1]*B[8]+A[2]*B[6]-A[4]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 4 -> group 0)
	 */
	protected final static void gp_default_4_4_0(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[4]-A[1]*B[1]-A[2]*B[2]-A[3]*B[3]+A[4]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 4 -> group 2)
	 */
	protected final static void gp_default_4_4_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[3]+A[3]*B[0]);
		C[1] += (A[0]*B[2]-A[2]*B[0]);
		C[2] += (-A[2]*B[3]+A[3]*B[2]);
		C[3] += (-A[0]*B[1]+A[1]*B[0]);
		C[4] += (A[1]*B[3]-A[3]*B[1]);
		C[5] += (-A[1]*B[2]+A[2]*B[1]);
		C[6] += (-A[0]*B[4]+A[4]*B[0]);
		C[7] += (-A[3]*B[4]+A[4]*B[3]);
		C[8] += (A[2]*B[4]-A[4]*B[2]);
		C[9] += (-A[1]*B[4]+A[4]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 5 -> group 1)
	 */
	protected final static void gp_default_4_5_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[3]*B[0];
		C[2] += -A[2]*B[0];
		C[3] += A[1]*B[0];
		C[4] += A[4]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 0 -> group 5)
	 */
	protected final static void gp_default_5_0_5(double[] A, double[] B, double[] C) {
		gp_default_0_0_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 1 -> group 4)
	 */
	protected final static void gp_default_5_1_4(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[0];
		C[1] += -A[0]*B[3];
		C[2] += A[0]*B[2];
		C[3] += -A[0]*B[1];
		C[4] += -A[0]*B[4];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 2 -> group 3)
	 */
	protected final static void gp_default_5_2_3(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[3];
		C[1] += A[0]*B[1];
		C[2] += -A[0]*B[0];
		C[3] += -A[0]*B[6];
		C[4] += -A[0]*B[5];
		C[5] += A[0]*B[4];
		C[6] += -A[0]*B[9];
		C[7] += -A[0]*B[2];
		C[8] += A[0]*B[8];
		C[9] += -A[0]*B[7];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 3 -> group 2)
	 */
	protected final static void gp_default_5_3_2(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[2];
		C[1] += -A[0]*B[1];
		C[2] += A[0]*B[7];
		C[3] += A[0]*B[0];
		C[4] += -A[0]*B[5];
		C[5] += A[0]*B[4];
		C[6] += A[0]*B[3];
		C[7] += A[0]*B[9];
		C[8] += -A[0]*B[8];
		C[9] += A[0]*B[6];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 4 -> group 1)
	 */
	protected final static void gp_default_5_4_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[0]*B[3];
		C[2] += -A[0]*B[2];
		C[3] += A[0]*B[1];
		C[4] += A[0]*B[4];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 5 -> group 0)
	 */
	protected final static void gp_default_5_5_0(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 0 -> group 0)
	 */
	protected final static void gp_euclidean_0_0_0(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 1 -> group 1)
	 */
	protected final static void gp_euclidean_0_1_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[0]*B[1];
		C[2] += A[0]*B[2];
		C[3] += A[0]*B[3];
		C[4] += A[0]*B[4];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 2 -> group 2)
	 */
	protected final static void gp_euclidean_0_2_2(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[0]*B[1];
		C[2] += A[0]*B[2];
		C[3] += A[0]*B[3];
		C[4] += A[0]*B[4];
		C[5] += A[0]*B[5];
		C[6] += A[0]*B[6];
		C[7] += A[0]*B[7];
		C[8] += A[0]*B[8];
		C[9] += A[0]*B[9];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 3 -> group 3)
	 */
	protected final static void gp_euclidean_0_3_3(double[] A, double[] B, double[] C) {
		gp_euclidean_0_2_2(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 4 -> group 4)
	 */
	protected final static void gp_euclidean_0_4_4(double[] A, double[] B, double[] C) {
		gp_euclidean_0_1_1(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 0  x  group 5 -> group 5)
	 */
	protected final static void gp_euclidean_0_5_5(double[] A, double[] B, double[] C) {
		gp_euclidean_0_0_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 0 -> group 1)
	 */
	protected final static void gp_euclidean_1_0_1(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[1]*B[0];
		C[2] += A[2]*B[0];
		C[3] += A[3]*B[0];
		C[4] += A[4]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 1 -> group 0)
	 */
	protected final static void gp_euclidean_1_1_0(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[0]+A[1]*B[1]+A[2]*B[2]+A[3]*B[3]+A[4]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 1 -> group 2)
	 */
	protected final static void gp_euclidean_1_1_2(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[1]-A[1]*B[0]);
		C[1] += (A[0]*B[2]-A[2]*B[0]);
		C[2] += (A[1]*B[2]-A[2]*B[1]);
		C[3] += (A[0]*B[3]-A[3]*B[0]);
		C[4] += (A[1]*B[3]-A[3]*B[1]);
		C[5] += (A[2]*B[3]-A[3]*B[2]);
		C[6] += (A[0]*B[4]-A[4]*B[0]);
		C[7] += (A[1]*B[4]-A[4]*B[1]);
		C[8] += (A[2]*B[4]-A[4]*B[2]);
		C[9] += (A[3]*B[4]-A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 2 -> group 1)
	 */
	protected final static void gp_euclidean_1_2_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[0]-A[2]*B[1]-A[3]*B[3]-A[4]*B[6]);
		C[1] += (A[0]*B[0]-A[2]*B[2]-A[3]*B[4]-A[4]*B[7]);
		C[2] += (A[0]*B[1]+A[1]*B[2]-A[3]*B[5]-A[4]*B[8]);
		C[3] += (A[0]*B[3]+A[1]*B[4]+A[2]*B[5]-A[4]*B[9]);
		C[4] += (A[0]*B[6]+A[1]*B[7]+A[2]*B[8]+A[3]*B[9]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 2 -> group 3)
	 */
	protected final static void gp_euclidean_1_2_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]-A[1]*B[1]+A[2]*B[0]);
		C[1] += (A[0]*B[4]-A[1]*B[3]+A[3]*B[0]);
		C[2] += (A[0]*B[5]-A[2]*B[3]+A[3]*B[1]);
		C[3] += (A[1]*B[5]-A[2]*B[4]+A[3]*B[2]);
		C[4] += (A[0]*B[7]-A[1]*B[6]+A[4]*B[0]);
		C[5] += (A[0]*B[8]-A[2]*B[6]+A[4]*B[1]);
		C[6] += (A[1]*B[8]-A[2]*B[7]+A[4]*B[2]);
		C[7] += (A[0]*B[9]-A[3]*B[6]+A[4]*B[3]);
		C[8] += (A[1]*B[9]-A[3]*B[7]+A[4]*B[4]);
		C[9] += (A[2]*B[9]-A[3]*B[8]+A[4]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 3 -> group 2)
	 */
	protected final static void gp_euclidean_1_3_2(double[] A, double[] B, double[] C) {
		C[0] += (A[2]*B[0]+A[3]*B[1]+A[4]*B[4]);
		C[1] += (-A[1]*B[0]+A[3]*B[2]+A[4]*B[5]);
		C[2] += (A[0]*B[0]+A[3]*B[3]+A[4]*B[6]);
		C[3] += (-A[1]*B[1]-A[2]*B[2]+A[4]*B[7]);
		C[4] += (A[0]*B[1]-A[2]*B[3]+A[4]*B[8]);
		C[5] += (A[0]*B[2]+A[1]*B[3]+A[4]*B[9]);
		C[6] += (-A[1]*B[4]-A[2]*B[5]-A[3]*B[7]);
		C[7] += (A[0]*B[4]-A[2]*B[6]-A[3]*B[8]);
		C[8] += (A[0]*B[5]+A[1]*B[6]-A[3]*B[9]);
		C[9] += (A[0]*B[7]+A[1]*B[8]+A[2]*B[9]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 3 -> group 4)
	 */
	protected final static void gp_euclidean_1_3_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[2]+A[2]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[6]-A[1]*B[5]+A[2]*B[4]-A[4]*B[0]);
		C[2] += (A[0]*B[8]-A[1]*B[7]+A[3]*B[4]-A[4]*B[1]);
		C[3] += (A[0]*B[9]-A[2]*B[7]+A[3]*B[5]-A[4]*B[2]);
		C[4] += (A[1]*B[9]-A[2]*B[8]+A[3]*B[6]-A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 4 -> group 3)
	 */
	protected final static void gp_euclidean_1_4_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[3]*B[0]-A[4]*B[1]);
		C[1] += (A[2]*B[0]-A[4]*B[2]);
		C[2] += (-A[1]*B[0]-A[4]*B[3]);
		C[3] += (A[0]*B[0]-A[4]*B[4]);
		C[4] += (A[2]*B[1]+A[3]*B[2]);
		C[5] += (-A[1]*B[1]+A[3]*B[3]);
		C[6] += (A[0]*B[1]+A[3]*B[4]);
		C[7] += (-A[1]*B[2]-A[2]*B[3]);
		C[8] += (A[0]*B[2]-A[2]*B[4]);
		C[9] += (A[0]*B[3]+A[1]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 4 -> group 5)
	 */
	protected final static void gp_euclidean_1_4_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[4]-A[1]*B[3]+A[2]*B[2]-A[3]*B[1]+A[4]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 1  x  group 5 -> group 4)
	 */
	protected final static void gp_euclidean_1_5_4(double[] A, double[] B, double[] C) {
		C[0] += A[4]*B[0];
		C[1] += -A[3]*B[0];
		C[2] += A[2]*B[0];
		C[3] += -A[1]*B[0];
		C[4] += A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 0 -> group 2)
	 */
	protected final static void gp_euclidean_2_0_2(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[0];
		C[1] += A[1]*B[0];
		C[2] += A[2]*B[0];
		C[3] += A[3]*B[0];
		C[4] += A[4]*B[0];
		C[5] += A[5]*B[0];
		C[6] += A[6]*B[0];
		C[7] += A[7]*B[0];
		C[8] += A[8]*B[0];
		C[9] += A[9]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 1 -> group 1)
	 */
	protected final static void gp_euclidean_2_1_1(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[1]+A[1]*B[2]+A[3]*B[3]+A[6]*B[4]);
		C[1] += (-A[0]*B[0]+A[2]*B[2]+A[4]*B[3]+A[7]*B[4]);
		C[2] += (-A[1]*B[0]-A[2]*B[1]+A[5]*B[3]+A[8]*B[4]);
		C[3] += (-A[3]*B[0]-A[4]*B[1]-A[5]*B[2]+A[9]*B[4]);
		C[4] += (-A[6]*B[0]-A[7]*B[1]-A[8]*B[2]-A[9]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 1 -> group 3)
	 */
	protected final static void gp_euclidean_2_1_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]-A[1]*B[1]+A[2]*B[0]);
		C[1] += (A[0]*B[3]-A[3]*B[1]+A[4]*B[0]);
		C[2] += (A[1]*B[3]-A[3]*B[2]+A[5]*B[0]);
		C[3] += (A[2]*B[3]-A[4]*B[2]+A[5]*B[1]);
		C[4] += (A[0]*B[4]-A[6]*B[1]+A[7]*B[0]);
		C[5] += (A[1]*B[4]-A[6]*B[2]+A[8]*B[0]);
		C[6] += (A[2]*B[4]-A[7]*B[2]+A[8]*B[1]);
		C[7] += (A[3]*B[4]-A[6]*B[3]+A[9]*B[0]);
		C[8] += (A[4]*B[4]-A[7]*B[3]+A[9]*B[1]);
		C[9] += (A[5]*B[4]-A[8]*B[3]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 0)
	 */
	protected final static void gp_euclidean_2_2_0(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[0]-A[1]*B[1]-A[2]*B[2]-A[3]*B[3]-A[4]*B[4]-A[5]*B[5]-A[6]*B[6]-A[7]*B[7]-A[8]*B[8]-A[9]*B[9]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 2)
	 */
	protected final static void gp_euclidean_2_2_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[2]+A[2]*B[1]-A[3]*B[4]+A[4]*B[3]-A[6]*B[7]+A[7]*B[6]);
		C[1] += (A[0]*B[2]-A[2]*B[0]-A[3]*B[5]+A[5]*B[3]-A[6]*B[8]+A[8]*B[6]);
		C[2] += (-A[0]*B[1]+A[1]*B[0]-A[4]*B[5]+A[5]*B[4]-A[7]*B[8]+A[8]*B[7]);
		C[3] += (A[0]*B[4]+A[1]*B[5]-A[4]*B[0]-A[5]*B[1]-A[6]*B[9]+A[9]*B[6]);
		C[4] += (-A[0]*B[3]+A[2]*B[5]+A[3]*B[0]-A[5]*B[2]-A[7]*B[9]+A[9]*B[7]);
		C[5] += (-A[1]*B[3]-A[2]*B[4]+A[3]*B[1]+A[4]*B[2]-A[8]*B[9]+A[9]*B[8]);
		C[6] += (A[0]*B[7]+A[1]*B[8]+A[3]*B[9]-A[7]*B[0]-A[8]*B[1]-A[9]*B[3]);
		C[7] += (-A[0]*B[6]+A[2]*B[8]+A[4]*B[9]+A[6]*B[0]-A[8]*B[2]-A[9]*B[4]);
		C[8] += (-A[1]*B[6]-A[2]*B[7]+A[5]*B[9]+A[6]*B[1]+A[7]*B[2]-A[9]*B[5]);
		C[9] += (-A[3]*B[6]-A[4]*B[7]-A[5]*B[8]+A[6]*B[3]+A[7]*B[4]+A[8]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 2 -> group 4)
	 */
	protected final static void gp_euclidean_2_2_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[5]-A[1]*B[4]+A[2]*B[3]+A[3]*B[2]-A[4]*B[1]+A[5]*B[0]);
		C[1] += (A[0]*B[8]-A[1]*B[7]+A[2]*B[6]+A[6]*B[2]-A[7]*B[1]+A[8]*B[0]);
		C[2] += (A[0]*B[9]-A[3]*B[7]+A[4]*B[6]+A[6]*B[4]-A[7]*B[3]+A[9]*B[0]);
		C[3] += (A[1]*B[9]-A[3]*B[8]+A[5]*B[6]+A[6]*B[5]-A[8]*B[3]+A[9]*B[1]);
		C[4] += (A[2]*B[9]-A[4]*B[8]+A[5]*B[7]+A[7]*B[5]-A[8]*B[4]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 1)
	 */
	protected final static void gp_euclidean_2_3_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[2]*B[0]-A[4]*B[1]-A[5]*B[2]-A[7]*B[4]-A[8]*B[5]-A[9]*B[7]);
		C[1] += (A[1]*B[0]+A[3]*B[1]-A[5]*B[3]+A[6]*B[4]-A[8]*B[6]-A[9]*B[8]);
		C[2] += (-A[0]*B[0]+A[3]*B[2]+A[4]*B[3]+A[6]*B[5]+A[7]*B[6]-A[9]*B[9]);
		C[3] += (-A[0]*B[1]-A[1]*B[2]-A[2]*B[3]+A[6]*B[7]+A[7]*B[8]+A[8]*B[9]);
		C[4] += (-A[0]*B[4]-A[1]*B[5]-A[2]*B[6]-A[3]*B[7]-A[4]*B[8]-A[5]*B[9]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 3)
	 */
	protected final static void gp_euclidean_2_3_3(double[] A, double[] B, double[] C) {
		C[0] += (A[3]*B[3]-A[4]*B[2]+A[5]*B[1]+A[6]*B[6]-A[7]*B[5]+A[8]*B[4]);
		C[1] += (-A[1]*B[3]+A[2]*B[2]-A[5]*B[0]+A[6]*B[8]-A[7]*B[7]+A[9]*B[4]);
		C[2] += (A[0]*B[3]-A[2]*B[1]+A[4]*B[0]+A[6]*B[9]-A[8]*B[7]+A[9]*B[5]);
		C[3] += (-A[0]*B[2]+A[1]*B[1]-A[3]*B[0]+A[7]*B[9]-A[8]*B[8]+A[9]*B[6]);
		C[4] += (-A[1]*B[6]+A[2]*B[5]-A[3]*B[8]+A[4]*B[7]-A[8]*B[0]-A[9]*B[1]);
		C[5] += (A[0]*B[6]-A[2]*B[4]-A[3]*B[9]+A[5]*B[7]+A[7]*B[0]-A[9]*B[2]);
		C[6] += (-A[0]*B[5]+A[1]*B[4]-A[4]*B[9]+A[5]*B[8]-A[6]*B[0]-A[9]*B[3]);
		C[7] += (A[0]*B[8]+A[1]*B[9]-A[4]*B[4]-A[5]*B[5]+A[7]*B[1]+A[8]*B[2]);
		C[8] += (-A[0]*B[7]+A[2]*B[9]+A[3]*B[4]-A[5]*B[6]-A[6]*B[1]+A[8]*B[3]);
		C[9] += (-A[1]*B[7]-A[2]*B[8]+A[3]*B[5]+A[4]*B[6]-A[6]*B[2]-A[7]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 3 -> group 5)
	 */
	protected final static void gp_euclidean_2_3_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[9]-A[1]*B[8]+A[2]*B[7]+A[3]*B[6]-A[4]*B[5]+A[5]*B[4]-A[6]*B[3]+A[7]*B[2]-A[8]*B[1]+A[9]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 4 -> group 2)
	 */
	protected final static void gp_euclidean_2_4_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[5]*B[0]-A[8]*B[1]-A[9]*B[2]);
		C[1] += (A[4]*B[0]+A[7]*B[1]-A[9]*B[3]);
		C[2] += (-A[3]*B[0]-A[6]*B[1]-A[9]*B[4]);
		C[3] += (-A[2]*B[0]+A[7]*B[2]+A[8]*B[3]);
		C[4] += (A[1]*B[0]-A[6]*B[2]+A[8]*B[4]);
		C[5] += (-A[0]*B[0]-A[6]*B[3]-A[7]*B[4]);
		C[6] += (-A[2]*B[1]-A[4]*B[2]-A[5]*B[3]);
		C[7] += (A[1]*B[1]+A[3]*B[2]-A[5]*B[4]);
		C[8] += (-A[0]*B[1]+A[3]*B[3]+A[4]*B[4]);
		C[9] += (-A[0]*B[2]-A[1]*B[3]-A[2]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 4 -> group 4)
	 */
	protected final static void gp_euclidean_2_4_4(double[] A, double[] B, double[] C) {
		C[0] += (-A[6]*B[4]+A[7]*B[3]-A[8]*B[2]+A[9]*B[1]);
		C[1] += (A[3]*B[4]-A[4]*B[3]+A[5]*B[2]-A[9]*B[0]);
		C[2] += (-A[1]*B[4]+A[2]*B[3]-A[5]*B[1]+A[8]*B[0]);
		C[3] += (A[0]*B[4]-A[2]*B[2]+A[4]*B[1]-A[7]*B[0]);
		C[4] += (-A[0]*B[3]+A[1]*B[2]-A[3]*B[1]+A[6]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 2  x  group 5 -> group 3)
	 */
	protected final static void gp_euclidean_2_5_3(double[] A, double[] B, double[] C) {
		C[0] += -A[9]*B[0];
		C[1] += A[8]*B[0];
		C[2] += -A[7]*B[0];
		C[3] += A[6]*B[0];
		C[4] += -A[5]*B[0];
		C[5] += A[4]*B[0];
		C[6] += -A[3]*B[0];
		C[7] += -A[2]*B[0];
		C[8] += A[1]*B[0];
		C[9] += -A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 0 -> group 3)
	 */
	protected final static void gp_euclidean_3_0_3(double[] A, double[] B, double[] C) {
		gp_euclidean_2_0_2(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 1 -> group 2)
	 */
	protected final static void gp_euclidean_3_1_2(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[2]+A[1]*B[3]+A[4]*B[4]);
		C[1] += (-A[0]*B[1]+A[2]*B[3]+A[5]*B[4]);
		C[2] += (A[0]*B[0]+A[3]*B[3]+A[6]*B[4]);
		C[3] += (-A[1]*B[1]-A[2]*B[2]+A[7]*B[4]);
		C[4] += (A[1]*B[0]-A[3]*B[2]+A[8]*B[4]);
		C[5] += (A[2]*B[0]+A[3]*B[1]+A[9]*B[4]);
		C[6] += (-A[4]*B[1]-A[5]*B[2]-A[7]*B[3]);
		C[7] += (A[4]*B[0]-A[6]*B[2]-A[8]*B[3]);
		C[8] += (A[5]*B[0]+A[6]*B[1]-A[9]*B[3]);
		C[9] += (A[7]*B[0]+A[8]*B[1]+A[9]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 1 -> group 4)
	 */
	protected final static void gp_euclidean_3_1_4(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]-A[1]*B[2]+A[2]*B[1]-A[3]*B[0]);
		C[1] += (A[0]*B[4]-A[4]*B[2]+A[5]*B[1]-A[6]*B[0]);
		C[2] += (A[1]*B[4]-A[4]*B[3]+A[7]*B[1]-A[8]*B[0]);
		C[3] += (A[2]*B[4]-A[5]*B[3]+A[7]*B[2]-A[9]*B[0]);
		C[4] += (A[3]*B[4]-A[6]*B[3]+A[8]*B[2]-A[9]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 1)
	 */
	protected final static void gp_euclidean_3_2_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[2]-A[1]*B[4]-A[2]*B[5]-A[4]*B[7]-A[5]*B[8]-A[7]*B[9]);
		C[1] += (A[0]*B[1]+A[1]*B[3]-A[3]*B[5]+A[4]*B[6]-A[6]*B[8]-A[8]*B[9]);
		C[2] += (-A[0]*B[0]+A[2]*B[3]+A[3]*B[4]+A[5]*B[6]+A[6]*B[7]-A[9]*B[9]);
		C[3] += (-A[1]*B[0]-A[2]*B[1]-A[3]*B[2]+A[7]*B[6]+A[8]*B[7]+A[9]*B[8]);
		C[4] += (-A[4]*B[0]-A[5]*B[1]-A[6]*B[2]-A[7]*B[3]-A[8]*B[4]-A[9]*B[5]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 3)
	 */
	protected final static void gp_euclidean_3_2_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[5]+A[2]*B[4]-A[3]*B[3]-A[4]*B[8]+A[5]*B[7]-A[6]*B[6]);
		C[1] += (A[0]*B[5]-A[2]*B[2]+A[3]*B[1]-A[4]*B[9]+A[7]*B[7]-A[8]*B[6]);
		C[2] += (-A[0]*B[4]+A[1]*B[2]-A[3]*B[0]-A[5]*B[9]+A[7]*B[8]-A[9]*B[6]);
		C[3] += (A[0]*B[3]-A[1]*B[1]+A[2]*B[0]-A[6]*B[9]+A[8]*B[8]-A[9]*B[7]);
		C[4] += (A[0]*B[8]+A[1]*B[9]-A[5]*B[2]+A[6]*B[1]-A[7]*B[4]+A[8]*B[3]);
		C[5] += (-A[0]*B[7]+A[2]*B[9]+A[4]*B[2]-A[6]*B[0]-A[7]*B[5]+A[9]*B[3]);
		C[6] += (A[0]*B[6]+A[3]*B[9]-A[4]*B[1]+A[5]*B[0]-A[8]*B[5]+A[9]*B[4]);
		C[7] += (-A[1]*B[7]-A[2]*B[8]+A[4]*B[4]+A[5]*B[5]-A[8]*B[0]-A[9]*B[1]);
		C[8] += (A[1]*B[6]-A[3]*B[8]-A[4]*B[3]+A[6]*B[5]+A[7]*B[0]-A[9]*B[2]);
		C[9] += (A[2]*B[6]+A[3]*B[7]-A[5]*B[3]-A[6]*B[4]+A[7]*B[1]+A[8]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 2 -> group 5)
	 */
	protected final static void gp_euclidean_3_2_5(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[9]-A[1]*B[8]+A[2]*B[7]-A[3]*B[6]+A[4]*B[5]-A[5]*B[4]+A[6]*B[3]+A[7]*B[2]-A[8]*B[1]+A[9]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 0)
	 */
	protected final static void gp_euclidean_3_3_0(double[] A, double[] B, double[] C) {
		gp_euclidean_2_2_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 2)
	 */
	protected final static void gp_euclidean_3_3_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[2]*B[3]+A[3]*B[2]-A[5]*B[6]+A[6]*B[5]-A[7]*B[8]+A[8]*B[7]);
		C[1] += (A[1]*B[3]-A[3]*B[1]+A[4]*B[6]-A[6]*B[4]-A[7]*B[9]+A[9]*B[7]);
		C[2] += (-A[1]*B[2]+A[2]*B[1]-A[4]*B[5]+A[5]*B[4]-A[8]*B[9]+A[9]*B[8]);
		C[3] += (-A[0]*B[3]+A[3]*B[0]+A[4]*B[8]+A[5]*B[9]-A[8]*B[4]-A[9]*B[5]);
		C[4] += (A[0]*B[2]-A[2]*B[0]-A[4]*B[7]+A[6]*B[9]+A[7]*B[4]-A[9]*B[6]);
		C[5] += (-A[0]*B[1]+A[1]*B[0]-A[5]*B[7]-A[6]*B[8]+A[7]*B[5]+A[8]*B[6]);
		C[6] += (-A[0]*B[6]-A[1]*B[8]-A[2]*B[9]+A[6]*B[0]+A[8]*B[1]+A[9]*B[2]);
		C[7] += (A[0]*B[5]+A[1]*B[7]-A[3]*B[9]-A[5]*B[0]-A[7]*B[1]+A[9]*B[3]);
		C[8] += (-A[0]*B[4]+A[2]*B[7]+A[3]*B[8]+A[4]*B[0]-A[7]*B[2]-A[8]*B[3]);
		C[9] += (-A[1]*B[4]-A[2]*B[5]-A[3]*B[6]+A[4]*B[1]+A[5]*B[2]+A[6]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 3 -> group 4)
	 */
	protected final static void gp_euclidean_3_3_4(double[] A, double[] B, double[] C) {
		C[0] += (A[4]*B[9]-A[5]*B[8]+A[6]*B[7]+A[7]*B[6]-A[8]*B[5]+A[9]*B[4]);
		C[1] += (-A[1]*B[9]+A[2]*B[8]-A[3]*B[7]-A[7]*B[3]+A[8]*B[2]-A[9]*B[1]);
		C[2] += (A[0]*B[9]-A[2]*B[6]+A[3]*B[5]+A[5]*B[3]-A[6]*B[2]+A[9]*B[0]);
		C[3] += (-A[0]*B[8]+A[1]*B[6]-A[3]*B[4]-A[4]*B[3]+A[6]*B[1]-A[8]*B[0]);
		C[4] += (A[0]*B[7]-A[1]*B[5]+A[2]*B[4]+A[4]*B[2]-A[5]*B[1]+A[7]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 4 -> group 1)
	 */
	protected final static void gp_euclidean_3_4_1(double[] A, double[] B, double[] C) {
		C[0] += (A[3]*B[0]+A[6]*B[1]+A[8]*B[2]+A[9]*B[3]);
		C[1] += (-A[2]*B[0]-A[5]*B[1]-A[7]*B[2]+A[9]*B[4]);
		C[2] += (A[1]*B[0]+A[4]*B[1]-A[7]*B[3]-A[8]*B[4]);
		C[3] += (-A[0]*B[0]+A[4]*B[2]+A[5]*B[3]+A[6]*B[4]);
		C[4] += (-A[0]*B[1]-A[1]*B[2]-A[2]*B[3]-A[3]*B[4]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 4 -> group 3)
	 */
	protected final static void gp_euclidean_3_4_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[7]*B[4]+A[8]*B[3]-A[9]*B[2]);
		C[1] += (A[5]*B[4]-A[6]*B[3]+A[9]*B[1]);
		C[2] += (-A[4]*B[4]+A[6]*B[2]-A[8]*B[1]);
		C[3] += (A[4]*B[3]-A[5]*B[2]+A[7]*B[1]);
		C[4] += (-A[2]*B[4]+A[3]*B[3]-A[9]*B[0]);
		C[5] += (A[1]*B[4]-A[3]*B[2]+A[8]*B[0]);
		C[6] += (-A[1]*B[3]+A[2]*B[2]-A[7]*B[0]);
		C[7] += (-A[0]*B[4]+A[3]*B[1]-A[6]*B[0]);
		C[8] += (A[0]*B[3]-A[2]*B[1]+A[5]*B[0]);
		C[9] += (-A[0]*B[2]+A[1]*B[1]-A[4]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 3  x  group 5 -> group 2)
	 */
	protected final static void gp_euclidean_3_5_2(double[] A, double[] B, double[] C) {
		C[0] += -A[9]*B[0];
		C[1] += A[8]*B[0];
		C[2] += -A[7]*B[0];
		C[3] += -A[6]*B[0];
		C[4] += A[5]*B[0];
		C[5] += -A[4]*B[0];
		C[6] += A[3]*B[0];
		C[7] += -A[2]*B[0];
		C[8] += A[1]*B[0];
		C[9] += -A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 0 -> group 4)
	 */
	protected final static void gp_euclidean_4_0_4(double[] A, double[] B, double[] C) {
		gp_euclidean_1_0_1(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 1 -> group 3)
	 */
	protected final static void gp_euclidean_4_1_3(double[] A, double[] B, double[] C) {
		C[0] += (A[0]*B[3]+A[1]*B[4]);
		C[1] += (-A[0]*B[2]+A[2]*B[4]);
		C[2] += (A[0]*B[1]+A[3]*B[4]);
		C[3] += (-A[0]*B[0]+A[4]*B[4]);
		C[4] += (-A[1]*B[2]-A[2]*B[3]);
		C[5] += (A[1]*B[1]-A[3]*B[3]);
		C[6] += (-A[1]*B[0]-A[4]*B[3]);
		C[7] += (A[2]*B[1]+A[3]*B[2]);
		C[8] += (-A[2]*B[0]+A[4]*B[2]);
		C[9] += (-A[3]*B[0]-A[4]*B[1]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 1 -> group 5)
	 */
	protected final static void gp_euclidean_4_1_5(double[] A, double[] B, double[] C) {
		gp_euclidean_1_4_5(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 2 -> group 2)
	 */
	protected final static void gp_euclidean_4_2_2(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[5]-A[1]*B[8]-A[2]*B[9]);
		C[1] += (A[0]*B[4]+A[1]*B[7]-A[3]*B[9]);
		C[2] += (-A[0]*B[3]-A[1]*B[6]-A[4]*B[9]);
		C[3] += (-A[0]*B[2]+A[2]*B[7]+A[3]*B[8]);
		C[4] += (A[0]*B[1]-A[2]*B[6]+A[4]*B[8]);
		C[5] += (-A[0]*B[0]-A[3]*B[6]-A[4]*B[7]);
		C[6] += (-A[1]*B[2]-A[2]*B[4]-A[3]*B[5]);
		C[7] += (A[1]*B[1]+A[2]*B[3]-A[4]*B[5]);
		C[8] += (-A[1]*B[0]+A[3]*B[3]+A[4]*B[4]);
		C[9] += (-A[2]*B[0]-A[3]*B[1]-A[4]*B[2]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 2 -> group 4)
	 */
	protected final static void gp_euclidean_4_2_4(double[] A, double[] B, double[] C) {
		C[0] += (-A[1]*B[9]+A[2]*B[8]-A[3]*B[7]+A[4]*B[6]);
		C[1] += (A[0]*B[9]-A[2]*B[5]+A[3]*B[4]-A[4]*B[3]);
		C[2] += (-A[0]*B[8]+A[1]*B[5]-A[3]*B[2]+A[4]*B[1]);
		C[3] += (A[0]*B[7]-A[1]*B[4]+A[2]*B[2]-A[4]*B[0]);
		C[4] += (-A[0]*B[6]+A[1]*B[3]-A[2]*B[1]+A[3]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 3 -> group 1)
	 */
	protected final static void gp_euclidean_4_3_1(double[] A, double[] B, double[] C) {
		C[0] += (-A[0]*B[3]-A[1]*B[6]-A[2]*B[8]-A[3]*B[9]);
		C[1] += (A[0]*B[2]+A[1]*B[5]+A[2]*B[7]-A[4]*B[9]);
		C[2] += (-A[0]*B[1]-A[1]*B[4]+A[3]*B[7]+A[4]*B[8]);
		C[3] += (A[0]*B[0]-A[2]*B[4]-A[3]*B[5]-A[4]*B[6]);
		C[4] += (A[1]*B[0]+A[2]*B[1]+A[3]*B[2]+A[4]*B[3]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 3 -> group 3)
	 */
	protected final static void gp_euclidean_4_3_3(double[] A, double[] B, double[] C) {
		C[0] += (-A[2]*B[9]+A[3]*B[8]-A[4]*B[7]);
		C[1] += (A[1]*B[9]-A[3]*B[6]+A[4]*B[5]);
		C[2] += (-A[1]*B[8]+A[2]*B[6]-A[4]*B[4]);
		C[3] += (A[1]*B[7]-A[2]*B[5]+A[3]*B[4]);
		C[4] += (-A[0]*B[9]+A[3]*B[3]-A[4]*B[2]);
		C[5] += (A[0]*B[8]-A[2]*B[3]+A[4]*B[1]);
		C[6] += (-A[0]*B[7]+A[2]*B[2]-A[3]*B[1]);
		C[7] += (-A[0]*B[6]+A[1]*B[3]-A[4]*B[0]);
		C[8] += (A[0]*B[5]-A[1]*B[2]+A[3]*B[0]);
		C[9] += (-A[0]*B[4]+A[1]*B[1]-A[2]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 4 -> group 0)
	 */
	protected final static void gp_euclidean_4_4_0(double[] A, double[] B, double[] C) {
		gp_euclidean_1_1_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 4 -> group 2)
	 */
	protected final static void gp_euclidean_4_4_2(double[] A, double[] B, double[] C) {
		C[0] += (A[3]*B[4]-A[4]*B[3]);
		C[1] += (-A[2]*B[4]+A[4]*B[2]);
		C[2] += (A[2]*B[3]-A[3]*B[2]);
		C[3] += (A[1]*B[4]-A[4]*B[1]);
		C[4] += (-A[1]*B[3]+A[3]*B[1]);
		C[5] += (A[1]*B[2]-A[2]*B[1]);
		C[6] += (-A[0]*B[4]+A[4]*B[0]);
		C[7] += (A[0]*B[3]-A[3]*B[0]);
		C[8] += (-A[0]*B[2]+A[2]*B[0]);
		C[9] += (A[0]*B[1]-A[1]*B[0]);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 4  x  group 5 -> group 1)
	 */
	protected final static void gp_euclidean_4_5_1(double[] A, double[] B, double[] C) {
		gp_euclidean_1_5_4(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 0 -> group 5)
	 */
	protected final static void gp_euclidean_5_0_5(double[] A, double[] B, double[] C) {
		gp_euclidean_0_0_0(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 1 -> group 4)
	 */
	protected final static void gp_euclidean_5_1_4(double[] A, double[] B, double[] C) {
		C[0] += A[0]*B[4];
		C[1] += -A[0]*B[3];
		C[2] += A[0]*B[2];
		C[3] += -A[0]*B[1];
		C[4] += A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 2 -> group 3)
	 */
	protected final static void gp_euclidean_5_2_3(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[9];
		C[1] += A[0]*B[8];
		C[2] += -A[0]*B[7];
		C[3] += A[0]*B[6];
		C[4] += -A[0]*B[5];
		C[5] += A[0]*B[4];
		C[6] += -A[0]*B[3];
		C[7] += -A[0]*B[2];
		C[8] += A[0]*B[1];
		C[9] += -A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 3 -> group 2)
	 */
	protected final static void gp_euclidean_5_3_2(double[] A, double[] B, double[] C) {
		C[0] += -A[0]*B[9];
		C[1] += A[0]*B[8];
		C[2] += -A[0]*B[7];
		C[3] += -A[0]*B[6];
		C[4] += A[0]*B[5];
		C[5] += -A[0]*B[4];
		C[6] += A[0]*B[3];
		C[7] += -A[0]*B[2];
		C[8] += A[0]*B[1];
		C[9] += -A[0]*B[0];
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 4 -> group 1)
	 */
	protected final static void gp_euclidean_5_4_1(double[] A, double[] B, double[] C) {
		gp_euclidean_5_1_4(A, B, C);
	}
	/**
	 * Computes the partial geometric product of two multivectors (group 5  x  group 5 -> group 0)
	 */
	protected final static void gp_euclidean_5_5_0(double[] A, double[] B, double[] C) {
		gp_euclidean_0_0_0(A, B, C);
	}
	/**
	 * copies coordinates of group 0
	 */
	protected final static void copyGroup_0(double[] A, double[] C) {
		C[0] = A[0];
	}
	/**
	 * copies and multiplies (by s) coordinates of group 0
	 */
	protected final static void copyMul_0(double[] A, double[] C, double s) {
		C[0] = A[0]*s;
	}
	/**
	 * copies and divides (by s) coordinates of group 0
	 */
	protected final static void copyDiv_0(double[] A, double[] C, double s) {
		C[0] = A[0]/s;
	}
	/**
	 * adds coordinates of group 0 from variable A to C
	 */
	protected final static void add_0(double[] A, double[] C) {
		C[0] += A[0];
	}
	/**
	 * subtracts coordinates of group 0 in variable A from C
	 */
	protected final static void sub_0(double[] A, double[] C) {
		C[0] -= A[0];
	}
	/**
	 * negate coordinates of group 0 of variable A
	 */
	protected final static void neg_0(double[] A, double[] C) {
		C[0] = -A[0];
	}
	/**
	 * adds coordinates of group 0 of variables A and B
	 */
	protected final static void add2_0_0(double[] A, double[] B, double[] C) {
		C[0] = (A[0]+B[0]);
	}
	/**
	 * subtracts coordinates of group 0 of variables A from B
	 */
	protected final static void sub2_0_0(double[] A, double[] B, double[] C) {
		C[0] = (A[0]-B[0]);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 0 of variables A and B
	 */
	protected final static void hp_0_0(double[] A, double[] B, double[] C) {
		C[0] = A[0]*B[0];
	}
	/**
	 * performs coordinate-wise division of coordinates of group 0 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_0_0(double[] A, double[] B, double[] C) {
		C[0] = A[0]/((B[0]));
	}
	/**
	 * check for equality up to eps of coordinates of group 0 of variables A and B
	 */
	protected final static boolean equals_0_0(double[] A, double[] B, double eps) {
		if (((A[0] - B[0]) < -eps) || ((A[0] - B[0]) > eps)) return false;
	return true;
	}
	/**
	 * checks if coordinates of group 0 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_0(double[] A, double eps) {
		if ((A[0] < -eps) || (A[0] > eps)) return false;
		return true;
	}
	/**
	 * copies coordinates of group 1
	 */
	protected final static void copyGroup_1(double[] A, double[] C) {
		C[0] = A[0];
		C[1] = A[1];
		C[2] = A[2];
		C[3] = A[3];
		C[4] = A[4];
	}
	/**
	 * copies and multiplies (by s) coordinates of group 1
	 */
	protected final static void copyMul_1(double[] A, double[] C, double s) {
		C[0] = A[0]*s;
		C[1] = A[1]*s;
		C[2] = A[2]*s;
		C[3] = A[3]*s;
		C[4] = A[4]*s;
	}
	/**
	 * copies and divides (by s) coordinates of group 1
	 */
	protected final static void copyDiv_1(double[] A, double[] C, double s) {
		C[0] = A[0]/s;
		C[1] = A[1]/s;
		C[2] = A[2]/s;
		C[3] = A[3]/s;
		C[4] = A[4]/s;
	}
	/**
	 * adds coordinates of group 1 from variable A to C
	 */
	protected final static void add_1(double[] A, double[] C) {
		C[0] += A[0];
		C[1] += A[1];
		C[2] += A[2];
		C[3] += A[3];
		C[4] += A[4];
	}
	/**
	 * subtracts coordinates of group 1 in variable A from C
	 */
	protected final static void sub_1(double[] A, double[] C) {
		C[0] -= A[0];
		C[1] -= A[1];
		C[2] -= A[2];
		C[3] -= A[3];
		C[4] -= A[4];
	}
	/**
	 * negate coordinates of group 1 of variable A
	 */
	protected final static void neg_1(double[] A, double[] C) {
		C[0] = -A[0];
		C[1] = -A[1];
		C[2] = -A[2];
		C[3] = -A[3];
		C[4] = -A[4];
	}
	/**
	 * adds coordinates of group 1 of variables A and B
	 */
	protected final static void add2_1_1(double[] A, double[] B, double[] C) {
		C[0] = (A[0]+B[0]);
		C[1] = (A[1]+B[1]);
		C[2] = (A[2]+B[2]);
		C[3] = (A[3]+B[3]);
		C[4] = (A[4]+B[4]);
	}
	/**
	 * subtracts coordinates of group 1 of variables A from B
	 */
	protected final static void sub2_1_1(double[] A, double[] B, double[] C) {
		C[0] = (A[0]-B[0]);
		C[1] = (A[1]-B[1]);
		C[2] = (A[2]-B[2]);
		C[3] = (A[3]-B[3]);
		C[4] = (A[4]-B[4]);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 1 of variables A and B
	 */
	protected final static void hp_1_1(double[] A, double[] B, double[] C) {
		C[0] = A[0]*B[0];
		C[1] = A[1]*B[1];
		C[2] = A[2]*B[2];
		C[3] = A[3]*B[3];
		C[4] = A[4]*B[4];
	}
	/**
	 * performs coordinate-wise division of coordinates of group 1 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_1_1(double[] A, double[] B, double[] C) {
		C[0] = A[0]/((B[0]));
		C[1] = A[1]/((B[1]));
		C[2] = A[2]/((B[2]));
		C[3] = A[3]/((B[3]));
		C[4] = A[4]/((B[4]));
	}
	/**
	 * check for equality up to eps of coordinates of group 1 of variables A and B
	 */
	protected final static boolean equals_1_1(double[] A, double[] B, double eps) {
		if (((A[0] - B[0]) < -eps) || ((A[0] - B[0]) > eps)) return false;
		if (((A[1] - B[1]) < -eps) || ((A[1] - B[1]) > eps)) return false;
		if (((A[2] - B[2]) < -eps) || ((A[2] - B[2]) > eps)) return false;
		if (((A[3] - B[3]) < -eps) || ((A[3] - B[3]) > eps)) return false;
		if (((A[4] - B[4]) < -eps) || ((A[4] - B[4]) > eps)) return false;
	return true;
	}
	/**
	 * checks if coordinates of group 1 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_1(double[] A, double eps) {
		if ((A[0] < -eps) || (A[0] > eps)) return false;
		if ((A[1] < -eps) || (A[1] > eps)) return false;
		if ((A[2] < -eps) || (A[2] > eps)) return false;
		if ((A[3] < -eps) || (A[3] > eps)) return false;
		if ((A[4] < -eps) || (A[4] > eps)) return false;
		return true;
	}
	/**
	 * copies coordinates of group 2
	 */
	protected final static void copyGroup_2(double[] A, double[] C) {
		C[0] = A[0];
		C[1] = A[1];
		C[2] = A[2];
		C[3] = A[3];
		C[4] = A[4];
		C[5] = A[5];
		C[6] = A[6];
		C[7] = A[7];
		C[8] = A[8];
		C[9] = A[9];
	}
	/**
	 * copies and multiplies (by s) coordinates of group 2
	 */
	protected final static void copyMul_2(double[] A, double[] C, double s) {
		C[0] = A[0]*s;
		C[1] = A[1]*s;
		C[2] = A[2]*s;
		C[3] = A[3]*s;
		C[4] = A[4]*s;
		C[5] = A[5]*s;
		C[6] = A[6]*s;
		C[7] = A[7]*s;
		C[8] = A[8]*s;
		C[9] = A[9]*s;
	}
	/**
	 * copies and divides (by s) coordinates of group 2
	 */
	protected final static void copyDiv_2(double[] A, double[] C, double s) {
		C[0] = A[0]/s;
		C[1] = A[1]/s;
		C[2] = A[2]/s;
		C[3] = A[3]/s;
		C[4] = A[4]/s;
		C[5] = A[5]/s;
		C[6] = A[6]/s;
		C[7] = A[7]/s;
		C[8] = A[8]/s;
		C[9] = A[9]/s;
	}
	/**
	 * adds coordinates of group 2 from variable A to C
	 */
	protected final static void add_2(double[] A, double[] C) {
		C[0] += A[0];
		C[1] += A[1];
		C[2] += A[2];
		C[3] += A[3];
		C[4] += A[4];
		C[5] += A[5];
		C[6] += A[6];
		C[7] += A[7];
		C[8] += A[8];
		C[9] += A[9];
	}
	/**
	 * subtracts coordinates of group 2 in variable A from C
	 */
	protected final static void sub_2(double[] A, double[] C) {
		C[0] -= A[0];
		C[1] -= A[1];
		C[2] -= A[2];
		C[3] -= A[3];
		C[4] -= A[4];
		C[5] -= A[5];
		C[6] -= A[6];
		C[7] -= A[7];
		C[8] -= A[8];
		C[9] -= A[9];
	}
	/**
	 * negate coordinates of group 2 of variable A
	 */
	protected final static void neg_2(double[] A, double[] C) {
		C[0] = -A[0];
		C[1] = -A[1];
		C[2] = -A[2];
		C[3] = -A[3];
		C[4] = -A[4];
		C[5] = -A[5];
		C[6] = -A[6];
		C[7] = -A[7];
		C[8] = -A[8];
		C[9] = -A[9];
	}
	/**
	 * adds coordinates of group 2 of variables A and B
	 */
	protected final static void add2_2_2(double[] A, double[] B, double[] C) {
		C[0] = (A[0]+B[0]);
		C[1] = (A[1]+B[1]);
		C[2] = (A[2]+B[2]);
		C[3] = (A[3]+B[3]);
		C[4] = (A[4]+B[4]);
		C[5] = (A[5]+B[5]);
		C[6] = (A[6]+B[6]);
		C[7] = (A[7]+B[7]);
		C[8] = (A[8]+B[8]);
		C[9] = (A[9]+B[9]);
	}
	/**
	 * subtracts coordinates of group 2 of variables A from B
	 */
	protected final static void sub2_2_2(double[] A, double[] B, double[] C) {
		C[0] = (A[0]-B[0]);
		C[1] = (A[1]-B[1]);
		C[2] = (A[2]-B[2]);
		C[3] = (A[3]-B[3]);
		C[4] = (A[4]-B[4]);
		C[5] = (A[5]-B[5]);
		C[6] = (A[6]-B[6]);
		C[7] = (A[7]-B[7]);
		C[8] = (A[8]-B[8]);
		C[9] = (A[9]-B[9]);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 2 of variables A and B
	 */
	protected final static void hp_2_2(double[] A, double[] B, double[] C) {
		C[0] = A[0]*B[0];
		C[1] = A[1]*B[1];
		C[2] = A[2]*B[2];
		C[3] = A[3]*B[3];
		C[4] = A[4]*B[4];
		C[5] = A[5]*B[5];
		C[6] = A[6]*B[6];
		C[7] = A[7]*B[7];
		C[8] = A[8]*B[8];
		C[9] = A[9]*B[9];
	}
	/**
	 * performs coordinate-wise division of coordinates of group 2 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_2_2(double[] A, double[] B, double[] C) {
		C[0] = A[0]/((B[0]));
		C[1] = A[1]/((B[1]));
		C[2] = A[2]/((B[2]));
		C[3] = A[3]/((B[3]));
		C[4] = A[4]/((B[4]));
		C[5] = A[5]/((B[5]));
		C[6] = A[6]/((B[6]));
		C[7] = A[7]/((B[7]));
		C[8] = A[8]/((B[8]));
		C[9] = A[9]/((B[9]));
	}
	/**
	 * check for equality up to eps of coordinates of group 2 of variables A and B
	 */
	protected final static boolean equals_2_2(double[] A, double[] B, double eps) {
		if (((A[0] - B[0]) < -eps) || ((A[0] - B[0]) > eps)) return false;
		if (((A[1] - B[1]) < -eps) || ((A[1] - B[1]) > eps)) return false;
		if (((A[2] - B[2]) < -eps) || ((A[2] - B[2]) > eps)) return false;
		if (((A[3] - B[3]) < -eps) || ((A[3] - B[3]) > eps)) return false;
		if (((A[4] - B[4]) < -eps) || ((A[4] - B[4]) > eps)) return false;
		if (((A[5] - B[5]) < -eps) || ((A[5] - B[5]) > eps)) return false;
		if (((A[6] - B[6]) < -eps) || ((A[6] - B[6]) > eps)) return false;
		if (((A[7] - B[7]) < -eps) || ((A[7] - B[7]) > eps)) return false;
		if (((A[8] - B[8]) < -eps) || ((A[8] - B[8]) > eps)) return false;
		if (((A[9] - B[9]) < -eps) || ((A[9] - B[9]) > eps)) return false;
	return true;
	}
	/**
	 * checks if coordinates of group 2 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_2(double[] A, double eps) {
		if ((A[0] < -eps) || (A[0] > eps)) return false;
		if ((A[1] < -eps) || (A[1] > eps)) return false;
		if ((A[2] < -eps) || (A[2] > eps)) return false;
		if ((A[3] < -eps) || (A[3] > eps)) return false;
		if ((A[4] < -eps) || (A[4] > eps)) return false;
		if ((A[5] < -eps) || (A[5] > eps)) return false;
		if ((A[6] < -eps) || (A[6] > eps)) return false;
		if ((A[7] < -eps) || (A[7] > eps)) return false;
		if ((A[8] < -eps) || (A[8] > eps)) return false;
		if ((A[9] < -eps) || (A[9] > eps)) return false;
		return true;
	}
	/**
	 * copies coordinates of group 3
	 */
	protected final static void copyGroup_3(double[] A, double[] C) {
		copyGroup_2(A, C);
	}
	/**
	 * copies and multiplies (by s) coordinates of group 3
	 */
	protected final static void copyMul_3(double[] A, double[] C, double s) {
		copyMul_2(A, C, s);
	}
	/**
	 * copies and divides (by s) coordinates of group 3
	 */
	protected final static void copyDiv_3(double[] A, double[] C, double s) {
		copyDiv_2(A, C, s);
	}
	/**
	 * adds coordinates of group 3 from variable A to C
	 */
	protected final static void add_3(double[] A, double[] C) {
		add_2(A, C);
	}
	/**
	 * subtracts coordinates of group 3 in variable A from C
	 */
	protected final static void sub_3(double[] A, double[] C) {
		sub_2(A, C);
	}
	/**
	 * negate coordinates of group 3 of variable A
	 */
	protected final static void neg_3(double[] A, double[] C) {
		neg_2(A, C);
	}
	/**
	 * adds coordinates of group 3 of variables A and B
	 */
	protected final static void add2_3_3(double[] A, double[] B, double[] C) {
		add2_2_2(A, B, C);
	}
	/**
	 * subtracts coordinates of group 3 of variables A from B
	 */
	protected final static void sub2_3_3(double[] A, double[] B, double[] C) {
		sub2_2_2(A, B, C);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 3 of variables A and B
	 */
	protected final static void hp_3_3(double[] A, double[] B, double[] C) {
		hp_2_2(A, B, C);
	}
	/**
	 * performs coordinate-wise division of coordinates of group 3 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_3_3(double[] A, double[] B, double[] C) {
		ihp_2_2(A, B, C);
	}
	/**
	 * check for equality up to eps of coordinates of group 3 of variables A and B
	 */
	protected final static boolean equals_3_3(double[] A, double[] B, double eps) {
		return equals_2_2(A, B, eps);
	}
	/**
	 * checks if coordinates of group 3 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_3(double[] A, double eps) {
		return zeroGroup_2(A, eps);
	}
	/**
	 * copies coordinates of group 4
	 */
	protected final static void copyGroup_4(double[] A, double[] C) {
		copyGroup_1(A, C);
	}
	/**
	 * copies and multiplies (by s) coordinates of group 4
	 */
	protected final static void copyMul_4(double[] A, double[] C, double s) {
		copyMul_1(A, C, s);
	}
	/**
	 * copies and divides (by s) coordinates of group 4
	 */
	protected final static void copyDiv_4(double[] A, double[] C, double s) {
		copyDiv_1(A, C, s);
	}
	/**
	 * adds coordinates of group 4 from variable A to C
	 */
	protected final static void add_4(double[] A, double[] C) {
		add_1(A, C);
	}
	/**
	 * subtracts coordinates of group 4 in variable A from C
	 */
	protected final static void sub_4(double[] A, double[] C) {
		sub_1(A, C);
	}
	/**
	 * negate coordinates of group 4 of variable A
	 */
	protected final static void neg_4(double[] A, double[] C) {
		neg_1(A, C);
	}
	/**
	 * adds coordinates of group 4 of variables A and B
	 */
	protected final static void add2_4_4(double[] A, double[] B, double[] C) {
		add2_1_1(A, B, C);
	}
	/**
	 * subtracts coordinates of group 4 of variables A from B
	 */
	protected final static void sub2_4_4(double[] A, double[] B, double[] C) {
		sub2_1_1(A, B, C);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 4 of variables A and B
	 */
	protected final static void hp_4_4(double[] A, double[] B, double[] C) {
		hp_1_1(A, B, C);
	}
	/**
	 * performs coordinate-wise division of coordinates of group 4 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_4_4(double[] A, double[] B, double[] C) {
		ihp_1_1(A, B, C);
	}
	/**
	 * check for equality up to eps of coordinates of group 4 of variables A and B
	 */
	protected final static boolean equals_4_4(double[] A, double[] B, double eps) {
		return equals_1_1(A, B, eps);
	}
	/**
	 * checks if coordinates of group 4 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_4(double[] A, double eps) {
		return zeroGroup_1(A, eps);
	}
	/**
	 * copies coordinates of group 5
	 */
	protected final static void copyGroup_5(double[] A, double[] C) {
		copyGroup_0(A, C);
	}
	/**
	 * copies and multiplies (by s) coordinates of group 5
	 */
	protected final static void copyMul_5(double[] A, double[] C, double s) {
		copyMul_0(A, C, s);
	}
	/**
	 * copies and divides (by s) coordinates of group 5
	 */
	protected final static void copyDiv_5(double[] A, double[] C, double s) {
		copyDiv_0(A, C, s);
	}
	/**
	 * adds coordinates of group 5 from variable A to C
	 */
	protected final static void add_5(double[] A, double[] C) {
		add_0(A, C);
	}
	/**
	 * subtracts coordinates of group 5 in variable A from C
	 */
	protected final static void sub_5(double[] A, double[] C) {
		sub_0(A, C);
	}
	/**
	 * negate coordinates of group 5 of variable A
	 */
	protected final static void neg_5(double[] A, double[] C) {
		neg_0(A, C);
	}
	/**
	 * adds coordinates of group 5 of variables A and B
	 */
	protected final static void add2_5_5(double[] A, double[] B, double[] C) {
		add2_0_0(A, B, C);
	}
	/**
	 * subtracts coordinates of group 5 of variables A from B
	 */
	protected final static void sub2_5_5(double[] A, double[] B, double[] C) {
		sub2_0_0(A, B, C);
	}
	/**
	 * performs coordinate-wise multiplication of coordinates of group 5 of variables A and B
	 */
	protected final static void hp_5_5(double[] A, double[] B, double[] C) {
		hp_0_0(A, B, C);
	}
	/**
	 * performs coordinate-wise division of coordinates of group 5 of variables A and B
	 * (no checks for divide by zero are made)
	 */
	protected final static void ihp_5_5(double[] A, double[] B, double[] C) {
		ihp_0_0(A, B, C);
	}
	/**
	 * check for equality up to eps of coordinates of group 5 of variables A and B
	 */
	protected final static boolean equals_5_5(double[] A, double[] B, double eps) {
		return equals_0_0(A, B, eps);
	}
	/**
	 * checks if coordinates of group 5 of variable A are zero up to eps
	 */
	protected final static boolean zeroGroup_5(double[] A, double eps) {
		return zeroGroup_0(A, eps);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_0_5(double[] A, double[] C) {
		C[0] = -A[0];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_0_5(double[] A, double[] C) {
		C[0] = A[0];
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_1_4(double[] A, double[] C) {
		C[0] = A[0];
		C[1] = A[3];
		C[2] = -A[2];
		C[3] = A[1];
		C[4] = A[4];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_1_4(double[] A, double[] C) {
		C[0] = -A[0];
		C[1] = -A[3];
		C[2] = A[2];
		C[3] = -A[1];
		C[4] = -A[4];
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_2_3(double[] A, double[] C) {
		C[0] = A[3];
		C[1] = -A[1];
		C[2] = A[0];
		C[3] = A[6];
		C[4] = A[5];
		C[5] = -A[4];
		C[6] = A[9];
		C[7] = A[2];
		C[8] = -A[8];
		C[9] = A[7];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_2_3(double[] A, double[] C) {
		C[0] = -A[3];
		C[1] = A[1];
		C[2] = -A[0];
		C[3] = -A[6];
		C[4] = -A[5];
		C[5] = A[4];
		C[6] = -A[9];
		C[7] = -A[2];
		C[8] = A[8];
		C[9] = -A[7];
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_3_2(double[] A, double[] C) {
		C[0] = -A[2];
		C[1] = A[1];
		C[2] = -A[7];
		C[3] = -A[0];
		C[4] = A[5];
		C[5] = -A[4];
		C[6] = -A[3];
		C[7] = -A[9];
		C[8] = A[8];
		C[9] = -A[6];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_3_2(double[] A, double[] C) {
		C[0] = A[2];
		C[1] = -A[1];
		C[2] = A[7];
		C[3] = A[0];
		C[4] = -A[5];
		C[5] = A[4];
		C[6] = A[3];
		C[7] = A[9];
		C[8] = -A[8];
		C[9] = A[6];
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_4_1(double[] A, double[] C) {
		undual_default_1_4(A, C);
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_4_1(double[] A, double[] C) {
		dual_default_1_4(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_default_5_0(double[] A, double[] C) {
		undual_default_0_5(A, C);
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_default_5_0(double[] A, double[] C) {
		dual_default_0_5(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_0_5(double[] A, double[] C) {
		undual_default_0_5(A, C);
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_0_5(double[] A, double[] C) {
		undual_default_0_5(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_1_4(double[] A, double[] C) {
		C[0] = A[4];
		C[1] = -A[3];
		C[2] = A[2];
		C[3] = -A[1];
		C[4] = A[0];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_1_4(double[] A, double[] C) {
		dual_euclidean_1_4(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_2_3(double[] A, double[] C) {
		C[0] = -A[9];
		C[1] = A[8];
		C[2] = -A[7];
		C[3] = A[6];
		C[4] = -A[5];
		C[5] = A[4];
		C[6] = -A[3];
		C[7] = -A[2];
		C[8] = A[1];
		C[9] = -A[0];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_2_3(double[] A, double[] C) {
		dual_euclidean_2_3(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_3_2(double[] A, double[] C) {
		C[0] = -A[9];
		C[1] = A[8];
		C[2] = -A[7];
		C[3] = -A[6];
		C[4] = A[5];
		C[5] = -A[4];
		C[6] = A[3];
		C[7] = -A[2];
		C[8] = A[1];
		C[9] = -A[0];
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_3_2(double[] A, double[] C) {
		dual_euclidean_3_2(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_4_1(double[] A, double[] C) {
		dual_euclidean_1_4(A, C);
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_4_1(double[] A, double[] C) {
		dual_euclidean_1_4(A, C);
	}
	/**
	 * Computes the partial dual (w.r.t. full space) of a multivector.
	 */
	protected final static void dual_euclidean_5_0(double[] A, double[] C) {
		undual_default_0_5(A, C);
	}
	/**
	 * Computes the partial undual (w.r.t. full space) of a multivector.
	 */
	protected final static void undual_euclidean_5_0(double[] A, double[] C) {
		undual_default_0_5(A, C);
	}
	/**
	 * Computes the partial application of a general outermorphism to a general multivector
	 */
	protected static void applyGomGmv_1_1(om O, double[] A, double[] C) {
		C[0] = (A[0]*O.m_m1[0]+A[1]*O.m_m1[1]+A[2]*O.m_m1[2]+A[3]*O.m_m1[3]+A[4]*O.m_m1[4]);
		C[1] = (A[0]*O.m_m1[5]+A[1]*O.m_m1[6]+A[2]*O.m_m1[7]+A[3]*O.m_m1[8]+A[4]*O.m_m1[9]);
		C[2] = (A[0]*O.m_m1[10]+A[1]*O.m_m1[11]+A[2]*O.m_m1[12]+A[3]*O.m_m1[13]+A[4]*O.m_m1[14]);
		C[3] = (A[0]*O.m_m1[15]+A[1]*O.m_m1[16]+A[2]*O.m_m1[17]+A[3]*O.m_m1[18]+A[4]*O.m_m1[19]);
		C[4] = (A[0]*O.m_m1[20]+A[1]*O.m_m1[21]+A[2]*O.m_m1[22]+A[3]*O.m_m1[23]+A[4]*O.m_m1[24]);
	}
	/**
	 * Computes the partial application of a general outermorphism to a general multivector
	 */
	protected static void applyGomGmv_2_2(om O, double[] A, double[] C) {
		C[0] = (A[0]*O.m_m2[0]+A[1]*O.m_m2[1]+A[2]*O.m_m2[2]+A[3]*O.m_m2[3]+A[4]*O.m_m2[4]+A[5]*O.m_m2[5]+A[6]*O.m_m2[6]+A[7]*O.m_m2[7]+A[8]*O.m_m2[8]+A[9]*O.m_m2[9]);
		C[1] = (A[0]*O.m_m2[10]+A[1]*O.m_m2[11]+A[2]*O.m_m2[12]+A[3]*O.m_m2[13]+A[4]*O.m_m2[14]+A[5]*O.m_m2[15]+A[6]*O.m_m2[16]+A[7]*O.m_m2[17]+A[8]*O.m_m2[18]+A[9]*O.m_m2[19]);
		C[2] = (A[0]*O.m_m2[20]+A[1]*O.m_m2[21]+A[2]*O.m_m2[22]+A[3]*O.m_m2[23]+A[4]*O.m_m2[24]+A[5]*O.m_m2[25]+A[6]*O.m_m2[26]+A[7]*O.m_m2[27]+A[8]*O.m_m2[28]+A[9]*O.m_m2[29]);
		C[3] = (A[0]*O.m_m2[30]+A[1]*O.m_m2[31]+A[2]*O.m_m2[32]+A[3]*O.m_m2[33]+A[4]*O.m_m2[34]+A[5]*O.m_m2[35]+A[6]*O.m_m2[36]+A[7]*O.m_m2[37]+A[8]*O.m_m2[38]+A[9]*O.m_m2[39]);
		C[4] = (A[0]*O.m_m2[40]+A[1]*O.m_m2[41]+A[2]*O.m_m2[42]+A[3]*O.m_m2[43]+A[4]*O.m_m2[44]+A[5]*O.m_m2[45]+A[6]*O.m_m2[46]+A[7]*O.m_m2[47]+A[8]*O.m_m2[48]+A[9]*O.m_m2[49]);
		C[5] = (A[0]*O.m_m2[50]+A[1]*O.m_m2[51]+A[2]*O.m_m2[52]+A[3]*O.m_m2[53]+A[4]*O.m_m2[54]+A[5]*O.m_m2[55]+A[6]*O.m_m2[56]+A[7]*O.m_m2[57]+A[8]*O.m_m2[58]+A[9]*O.m_m2[59]);
		C[6] = (A[0]*O.m_m2[60]+A[1]*O.m_m2[61]+A[2]*O.m_m2[62]+A[3]*O.m_m2[63]+A[4]*O.m_m2[64]+A[5]*O.m_m2[65]+A[6]*O.m_m2[66]+A[7]*O.m_m2[67]+A[8]*O.m_m2[68]+A[9]*O.m_m2[69]);
		C[7] = (A[0]*O.m_m2[70]+A[1]*O.m_m2[71]+A[2]*O.m_m2[72]+A[3]*O.m_m2[73]+A[4]*O.m_m2[74]+A[5]*O.m_m2[75]+A[6]*O.m_m2[76]+A[7]*O.m_m2[77]+A[8]*O.m_m2[78]+A[9]*O.m_m2[79]);
		C[8] = (A[0]*O.m_m2[80]+A[1]*O.m_m2[81]+A[2]*O.m_m2[82]+A[3]*O.m_m2[83]+A[4]*O.m_m2[84]+A[5]*O.m_m2[85]+A[6]*O.m_m2[86]+A[7]*O.m_m2[87]+A[8]*O.m_m2[88]+A[9]*O.m_m2[89]);
		C[9] = (A[0]*O.m_m2[90]+A[1]*O.m_m2[91]+A[2]*O.m_m2[92]+A[3]*O.m_m2[93]+A[4]*O.m_m2[94]+A[5]*O.m_m2[95]+A[6]*O.m_m2[96]+A[7]*O.m_m2[97]+A[8]*O.m_m2[98]+A[9]*O.m_m2[99]);
	}
	/**
	 * Computes the partial application of a general outermorphism to a general multivector
	 */
	protected static void applyGomGmv_3_3(om O, double[] A, double[] C) {
		C[0] = (A[0]*O.m_m3[0]+A[1]*O.m_m3[1]+A[2]*O.m_m3[2]+A[3]*O.m_m3[3]+A[4]*O.m_m3[4]+A[5]*O.m_m3[5]+A[6]*O.m_m3[6]+A[7]*O.m_m3[7]+A[8]*O.m_m3[8]+A[9]*O.m_m3[9]);
		C[1] = (A[0]*O.m_m3[10]+A[1]*O.m_m3[11]+A[2]*O.m_m3[12]+A[3]*O.m_m3[13]+A[4]*O.m_m3[14]+A[5]*O.m_m3[15]+A[6]*O.m_m3[16]+A[7]*O.m_m3[17]+A[8]*O.m_m3[18]+A[9]*O.m_m3[19]);
		C[2] = (A[0]*O.m_m3[20]+A[1]*O.m_m3[21]+A[2]*O.m_m3[22]+A[3]*O.m_m3[23]+A[4]*O.m_m3[24]+A[5]*O.m_m3[25]+A[6]*O.m_m3[26]+A[7]*O.m_m3[27]+A[8]*O.m_m3[28]+A[9]*O.m_m3[29]);
		C[3] = (A[0]*O.m_m3[30]+A[1]*O.m_m3[31]+A[2]*O.m_m3[32]+A[3]*O.m_m3[33]+A[4]*O.m_m3[34]+A[5]*O.m_m3[35]+A[6]*O.m_m3[36]+A[7]*O.m_m3[37]+A[8]*O.m_m3[38]+A[9]*O.m_m3[39]);
		C[4] = (A[0]*O.m_m3[40]+A[1]*O.m_m3[41]+A[2]*O.m_m3[42]+A[3]*O.m_m3[43]+A[4]*O.m_m3[44]+A[5]*O.m_m3[45]+A[6]*O.m_m3[46]+A[7]*O.m_m3[47]+A[8]*O.m_m3[48]+A[9]*O.m_m3[49]);
		C[5] = (A[0]*O.m_m3[50]+A[1]*O.m_m3[51]+A[2]*O.m_m3[52]+A[3]*O.m_m3[53]+A[4]*O.m_m3[54]+A[5]*O.m_m3[55]+A[6]*O.m_m3[56]+A[7]*O.m_m3[57]+A[8]*O.m_m3[58]+A[9]*O.m_m3[59]);
		C[6] = (A[0]*O.m_m3[60]+A[1]*O.m_m3[61]+A[2]*O.m_m3[62]+A[3]*O.m_m3[63]+A[4]*O.m_m3[64]+A[5]*O.m_m3[65]+A[6]*O.m_m3[66]+A[7]*O.m_m3[67]+A[8]*O.m_m3[68]+A[9]*O.m_m3[69]);
		C[7] = (A[0]*O.m_m3[70]+A[1]*O.m_m3[71]+A[2]*O.m_m3[72]+A[3]*O.m_m3[73]+A[4]*O.m_m3[74]+A[5]*O.m_m3[75]+A[6]*O.m_m3[76]+A[7]*O.m_m3[77]+A[8]*O.m_m3[78]+A[9]*O.m_m3[79]);
		C[8] = (A[0]*O.m_m3[80]+A[1]*O.m_m3[81]+A[2]*O.m_m3[82]+A[3]*O.m_m3[83]+A[4]*O.m_m3[84]+A[5]*O.m_m3[85]+A[6]*O.m_m3[86]+A[7]*O.m_m3[87]+A[8]*O.m_m3[88]+A[9]*O.m_m3[89]);
		C[9] = (A[0]*O.m_m3[90]+A[1]*O.m_m3[91]+A[2]*O.m_m3[92]+A[3]*O.m_m3[93]+A[4]*O.m_m3[94]+A[5]*O.m_m3[95]+A[6]*O.m_m3[96]+A[7]*O.m_m3[97]+A[8]*O.m_m3[98]+A[9]*O.m_m3[99]);
	}
	/**
	 * Computes the partial application of a general outermorphism to a general multivector
	 */
	protected static void applyGomGmv_4_4(om O, double[] A, double[] C) {
		C[0] = (A[0]*O.m_m4[0]+A[1]*O.m_m4[1]+A[2]*O.m_m4[2]+A[3]*O.m_m4[3]+A[4]*O.m_m4[4]);
		C[1] = (A[0]*O.m_m4[5]+A[1]*O.m_m4[6]+A[2]*O.m_m4[7]+A[3]*O.m_m4[8]+A[4]*O.m_m4[9]);
		C[2] = (A[0]*O.m_m4[10]+A[1]*O.m_m4[11]+A[2]*O.m_m4[12]+A[3]*O.m_m4[13]+A[4]*O.m_m4[14]);
		C[3] = (A[0]*O.m_m4[15]+A[1]*O.m_m4[16]+A[2]*O.m_m4[17]+A[3]*O.m_m4[18]+A[4]*O.m_m4[19]);
		C[4] = (A[0]*O.m_m4[20]+A[1]*O.m_m4[21]+A[2]*O.m_m4[22]+A[3]*O.m_m4[23]+A[4]*O.m_m4[24]);
	}
	/**
	 * Computes the partial application of a general outermorphism to a general multivector
	 */
	protected static void applyGomGmv_5_5(om O, double[] A, double[] C) {
		C[0] = A[0]*O.m_m5[0];
	}
public final static vectorE3GA _vectorE3GA(final normalizedPoint P)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			P.m_e1, // e1
			P.m_e2, // e2
			P.m_e3 // e3
		);

}
public final static vectorE3GA _vectorE3GA(final dualSphere S)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			S.m_e1, // e1
			S.m_e2, // e2
			S.m_e3 // e3
		);

}
public final static bivectorE3GA _bivectorE3GA(final rotorE3GA a)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);

}
public final static dualSphere pointToSphere(final normalizedPoint P)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			1.0, // no
			P.m_e1, // e1
			P.m_e2, // e2
			P.m_e3, // e3
			P.m_ni // ni
		);

}
public final static evenVersor _evenVersor(final pointPair a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			0.0, // scalar
			a.m_no_e1, // no_e1
			a.m_no_e2, // no_e2
			a.m_no_e3, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni, // e3_ni
			a.m_no_ni, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Generates a random double in [0.0 1.0) interval using the java.util.Random
 */
public final static double genrand() {
	return (double)NextRandomDouble();
}
public final static void genrand_seed(int seed) {
	s_randomGenerator.setSeed(seed);
}

public final static void genrand_timeSeed() {
	genrand_seed((int)System.currentTimeMillis());
}

/**
 * Returns conformal point.
 */
public final static normalizedPoint cgaPoint(final vectorE3GA a)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			(0.5*a.m_e1*a.m_e1+0.5*a.m_e2*a.m_e2+0.5*a.m_e3*a.m_e3) // ni
		);

}
/**
 * Returns conformal point.
 */
public final static normalizedPoint cgaPoint(final double a, final double b, final double c)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			a, // e1
			b, // e2
			c, // e3
			(0.5*a*a+0.5*b*b+0.5*c*c) // ni
		);

}

/** Returns a random conformal point where the coordinates lie in the interval [-scale, scale). */
public final static normalizedPoint randomCgaPoint(final double scale) {
	double ce1 = scale * (-1.0 + 2.0 * genrand());
	double ce2 = scale * (-1.0 + 2.0 * genrand());
	double ce3 = scale * (-1.0 + 2.0 * genrand());
	return cgaPoint(ce1, ce2, ce3);
}
/**
 * Returns distance of two conformal points.
 */
public final static double cgaPointDistance(final normalizedPoint a, final normalizedPoint b)
{
	return Math.sqrt(Math.abs(-2.0*(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3-a.m_ni-b.m_ni)));

}
/**
 * Returns distance of two conformal points.
 */
public final static double cgaPointDistance(final dualSphere a, final dualSphere b)
{
	return Math.sqrt(Math.abs(-2.0*(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3-a.m_ni*b.m_no-a.m_no*b.m_ni)));

}
/**
 * Returns mv + mv.
 */
public final static mv add(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		if (bc[0] != null) {
			add2_0_0(ac[0], bc[0], cc[0]);
		}
		else copyGroup_0(ac[0], cc[0]);
	}
	else if (bc[0] != null) {
		cc[0] = new double[1];
		copyGroup_0(bc[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		if (bc[1] != null) {
			add2_1_1(ac[1], bc[1], cc[1]);
		}
		else copyGroup_1(ac[1], cc[1]);
	}
	else if (bc[1] != null) {
		cc[1] = new double[5];
		copyGroup_1(bc[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		if (bc[2] != null) {
			add2_2_2(ac[2], bc[2], cc[2]);
		}
		else copyGroup_2(ac[2], cc[2]);
	}
	else if (bc[2] != null) {
		cc[2] = new double[10];
		copyGroup_2(bc[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		if (bc[3] != null) {
			add2_3_3(ac[3], bc[3], cc[3]);
		}
		else copyGroup_3(ac[3], cc[3]);
	}
	else if (bc[3] != null) {
		cc[3] = new double[10];
		copyGroup_3(bc[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		if (bc[4] != null) {
			add2_4_4(ac[4], bc[4], cc[4]);
		}
		else copyGroup_4(ac[4], cc[4]);
	}
	else if (bc[4] != null) {
		cc[4] = new double[5];
		copyGroup_4(bc[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		if (bc[5] != null) {
			add2_5_5(ac[5], bc[5], cc[5]);
		}
		else copyGroup_5(ac[5], cc[5]);
	}
	else if (bc[5] != null) {
		cc[5] = new double[1];
		copyGroup_5(bc[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns vectorE3GA + vectorE3GA.
 */
public final static vectorE3GA add(final vectorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_e1+b.m_e1), // e1
			(a.m_e2+b.m_e2), // e2
			(a.m_e3+b.m_e3) // e3
		);

}
/**
 * Returns vectorE3GA + normalizedPoint.
 */
public final static normalizedPoint add(final vectorE3GA a, final normalizedPoint b)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			(a.m_e1+b.m_e1), // e1
			(a.m_e2+b.m_e2), // e2
			(a.m_e3+b.m_e3), // e3
			b.m_ni // ni
		);

}
/**
 * Returns vectorE3GA + dualSphere.
 */
public final static dualSphere add(final vectorE3GA a, final dualSphere b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			b.m_no, // no
			(a.m_e1+b.m_e1), // e1
			(a.m_e2+b.m_e2), // e2
			(a.m_e3+b.m_e3), // e3
			b.m_ni // ni
		);

}
/**
 * Returns bivectorE3GA + bivectorE3GA.
 */
public final static bivectorE3GA add(final bivectorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			(a.m_e1_e2+b.m_e1_e2), // e1_e2
			(a.m_e2_e3+b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1-b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns plane + plane.
 */
public final static plane add(final plane a, final plane b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			(a.m_e1_e2_e3_ni+b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(a.m_no_e2_e3_ni+b.m_no_e2_e3_ni), // no_e2_e3_ni
			(a.m_no_e1_e3_ni+b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_no_e1_e2_ni+b.m_no_e1_e2_ni) // no_e1_e2_ni
		);

}
/**
 * Returns line + circle.
 */
public final static circle add(final line a, final circle b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			b.m_no_e1_e2, // no_e1_e2
			b.m_no_e1_e3, // no_e1_e3
			b.m_no_e2_e3, // no_e2_e3
			b.m_e1_e2_e3, // e1_e2_e3
			(-a.m_e1_no_ni+b.m_no_e1_ni), // no_e1_ni
			(-a.m_e2_no_ni+b.m_no_e2_ni), // no_e2_ni
			(a.m_e1_e2_ni+b.m_e1_e2_ni), // e1_e2_ni
			(-a.m_e3_no_ni+b.m_no_e3_ni), // no_e3_ni
			(a.m_e1_e3_ni+b.m_e1_e3_ni), // e1_e3_ni
			(a.m_e2_e3_ni+b.m_e2_e3_ni) // e2_e3_ni
		);

}
/**
 * Returns circle + vectorE3GA.
 */
public final static oddVersor add(final circle a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			b.m_e1, // e1
			b.m_e2, // e2
			b.m_e3, // e3
			0.0, // ni
			a.m_no_e1_e2, // no_e1_e2
			a.m_no_e1_e3, // no_e1_e3
			a.m_no_e2_e3, // no_e2_e3
			a.m_e1_e2_e3, // e1_e2_e3
			a.m_no_e1_ni, // no_e1_ni
			a.m_no_e2_ni, // no_e2_ni
			a.m_e1_e2_ni, // e1_e2_ni
			a.m_no_e3_ni, // no_e3_ni
			a.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns e1_t + e2_t.
 */
public final static vectorE3GA add(final e1_t a, final e2_t b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			1.0, // e1
			1.0, // e2
			0.0 // e3
		);

}
/**
 * Returns I5_t + circle.
 */
public final static oddVersor add(final I5_t a, final circle b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			b.m_no_e1_e2, // no_e1_e2
			b.m_no_e1_e3, // no_e1_e3
			b.m_no_e2_e3, // no_e2_e3
			b.m_e1_e2_e3, // e1_e2_e3
			b.m_no_e1_ni, // no_e1_ni
			b.m_no_e2_ni, // no_e2_ni
			b.m_e1_e2_ni, // e1_e2_ni
			b.m_no_e3_ni, // no_e3_ni
			b.m_e1_e3_ni, // e1_e3_ni
			b.m_e2_e3_ni, // e2_e3_ni
			1.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns mv - mv.
 */
public final static mv subtract(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		if (bc[0] != null) {
			sub2_0_0(ac[0], bc[0], cc[0]);
		}
		else copyGroup_0(ac[0], cc[0]);
	}
	else if (bc[0] != null) {
		cc[0] = new double[1];
		neg_0(bc[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		if (bc[1] != null) {
			sub2_1_1(ac[1], bc[1], cc[1]);
		}
		else copyGroup_1(ac[1], cc[1]);
	}
	else if (bc[1] != null) {
		cc[1] = new double[5];
		neg_1(bc[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		if (bc[2] != null) {
			sub2_2_2(ac[2], bc[2], cc[2]);
		}
		else copyGroup_2(ac[2], cc[2]);
	}
	else if (bc[2] != null) {
		cc[2] = new double[10];
		neg_2(bc[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		if (bc[3] != null) {
			sub2_3_3(ac[3], bc[3], cc[3]);
		}
		else copyGroup_3(ac[3], cc[3]);
	}
	else if (bc[3] != null) {
		cc[3] = new double[10];
		neg_3(bc[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		if (bc[4] != null) {
			sub2_4_4(ac[4], bc[4], cc[4]);
		}
		else copyGroup_4(ac[4], cc[4]);
	}
	else if (bc[4] != null) {
		cc[4] = new double[5];
		neg_4(bc[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		if (bc[5] != null) {
			sub2_5_5(ac[5], bc[5], cc[5]);
		}
		else copyGroup_5(ac[5], cc[5]);
	}
	else if (bc[5] != null) {
		cc[5] = new double[1];
		neg_5(bc[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns vectorE3GA - vectorE3GA.
 */
public final static vectorE3GA subtract(final vectorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_e1-b.m_e1), // e1
			(a.m_e2-b.m_e2), // e2
			(a.m_e3-b.m_e3) // e3
		);

}
/**
 * Returns bivectorE3GA - bivectorE3GA.
 */
public final static bivectorE3GA subtract(final bivectorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			(a.m_e1_e2-b.m_e1_e2), // e1_e2
			(a.m_e2_e3-b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1+b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns oddVersor - oddVersor.
 */
public final static oddVersor subtract(final oddVersor a, final oddVersor b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no-b.m_no), // no
			(a.m_e1-b.m_e1), // e1
			(a.m_e2-b.m_e2), // e2
			(a.m_e3-b.m_e3), // e3
			(a.m_ni-b.m_ni), // ni
			(a.m_no_e1_e2-b.m_no_e1_e2), // no_e1_e2
			(a.m_no_e1_e3-b.m_no_e1_e3), // no_e1_e3
			(a.m_no_e2_e3-b.m_no_e2_e3), // no_e2_e3
			(a.m_e1_e2_e3-b.m_e1_e2_e3), // e1_e2_e3
			(a.m_no_e1_ni-b.m_no_e1_ni), // no_e1_ni
			(a.m_no_e2_ni-b.m_no_e2_ni), // no_e2_ni
			(a.m_e1_e2_ni-b.m_e1_e2_ni), // e1_e2_ni
			(a.m_no_e3_ni-b.m_no_e3_ni), // no_e3_ni
			(a.m_e1_e3_ni-b.m_e1_e3_ni), // e1_e3_ni
			(a.m_e2_e3_ni-b.m_e2_e3_ni), // e2_e3_ni
			(a.m_no_e1_e2_e3_ni-b.m_no_e1_e2_e3_ni) // no_e1_e2_e3_ni
		);

}
/**
 * Returns line - vectorE3GA.
 */
public final static oddVersor subtract(final line a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			-b.m_e1, // e1
			-b.m_e2, // e2
			-b.m_e3, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-a.m_e1_no_ni, // no_e1_ni
			-a.m_e2_no_ni, // no_e2_ni
			a.m_e1_e2_ni, // e1_e2_ni
			-a.m_e3_no_ni, // no_e3_ni
			a.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns rotorE3GA - rotorE3GA.
 */
public final static rotorE3GA subtract(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(a.m_scalar-b.m_scalar), // scalar
			(a.m_e1_e2-b.m_e1_e2), // e1_e2
			(a.m_e2_e3-b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1+b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns rotorE3GA - noni_t.
 */
public final static evenVersor subtract(final rotorE3GA a, final noni_t b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			-1.0, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns I5_t - circle.
 */
public final static oddVersor subtract(final I5_t a, final circle b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			-b.m_no_e1_e2, // no_e1_e2
			-b.m_no_e1_e3, // no_e1_e3
			-b.m_no_e2_e3, // no_e2_e3
			-b.m_e1_e2_e3, // e1_e2_e3
			-b.m_no_e1_ni, // no_e1_ni
			-b.m_no_e2_ni, // no_e2_ni
			-b.m_e1_e2_ni, // e1_e2_ni
			-b.m_no_e3_ni, // no_e3_ni
			-b.m_e1_e3_ni, // e1_e3_ni
			-b.m_e2_e3_ni, // e2_e3_ni
			1.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns om * mv.
 */
public final static mv applyOM(final om a, final mv_if b)
{
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (bc[0] != null) {
	}
	
	if (bc[1] != null) {
		if (cc[1] == null) cc[1] = new double[5];
		applyGomGmv_1_1(a, bc[1], cc[1]);
	}
	
	if (bc[2] != null) {
		if (cc[2] == null) cc[2] = new double[10];
		applyGomGmv_2_2(a, bc[2], cc[2]);
	}
	
	if (bc[3] != null) {
		if (cc[3] == null) cc[3] = new double[10];
		applyGomGmv_3_3(a, bc[3], cc[3]);
	}
	
	if (bc[4] != null) {
		if (cc[4] == null) cc[4] = new double[5];
		applyGomGmv_4_4(a, bc[4], cc[4]);
	}
	
	if (bc[5] != null) {
		if (cc[5] == null) cc[5] = new double[1];
		applyGomGmv_5_5(a, bc[5], cc[5]);
	}
	
	return new mv(cc);
}
/**
 * Returns om * normalizedPoint.
 */
public final static dualSphere applyOM(final om a, final normalizedPoint b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_m1[0]+a.m_m1[1]*b.m_e1+a.m_m1[2]*b.m_e2+a.m_m1[3]*b.m_e3+a.m_m1[4]*b.m_ni), // no
			(a.m_m1[5]+a.m_m1[6]*b.m_e1+a.m_m1[7]*b.m_e2+a.m_m1[8]*b.m_e3+a.m_m1[9]*b.m_ni), // e1
			(a.m_m1[10]+a.m_m1[11]*b.m_e1+a.m_m1[12]*b.m_e2+a.m_m1[13]*b.m_e3+a.m_m1[14]*b.m_ni), // e2
			(a.m_m1[15]+a.m_m1[16]*b.m_e1+a.m_m1[17]*b.m_e2+a.m_m1[18]*b.m_e3+a.m_m1[19]*b.m_ni), // e3
			(a.m_m1[20]+a.m_m1[21]*b.m_e1+a.m_m1[22]*b.m_e2+a.m_m1[23]*b.m_e3+a.m_m1[24]*b.m_ni) // ni
		);

}
/**
 * Returns om * circle.
 */
public final static circle applyOM(final om a, final circle b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			(a.m_m3[0]*b.m_no_e1_e2+a.m_m3[1]*b.m_no_e1_e3+a.m_m3[2]*b.m_no_e2_e3+a.m_m3[3]*b.m_e1_e2_e3+a.m_m3[4]*b.m_no_e1_ni+a.m_m3[5]*b.m_no_e2_ni+a.m_m3[6]*b.m_e1_e2_ni+a.m_m3[7]*b.m_no_e3_ni+a.m_m3[8]*b.m_e1_e3_ni+a.m_m3[9]*b.m_e2_e3_ni), // no_e1_e2
			(a.m_m3[10]*b.m_no_e1_e2+a.m_m3[11]*b.m_no_e1_e3+a.m_m3[12]*b.m_no_e2_e3+a.m_m3[13]*b.m_e1_e2_e3+a.m_m3[14]*b.m_no_e1_ni+a.m_m3[15]*b.m_no_e2_ni+a.m_m3[16]*b.m_e1_e2_ni+a.m_m3[17]*b.m_no_e3_ni+a.m_m3[18]*b.m_e1_e3_ni+a.m_m3[19]*b.m_e2_e3_ni), // no_e1_e3
			(a.m_m3[20]*b.m_no_e1_e2+a.m_m3[21]*b.m_no_e1_e3+a.m_m3[22]*b.m_no_e2_e3+a.m_m3[23]*b.m_e1_e2_e3+a.m_m3[24]*b.m_no_e1_ni+a.m_m3[25]*b.m_no_e2_ni+a.m_m3[26]*b.m_e1_e2_ni+a.m_m3[27]*b.m_no_e3_ni+a.m_m3[28]*b.m_e1_e3_ni+a.m_m3[29]*b.m_e2_e3_ni), // no_e2_e3
			(a.m_m3[30]*b.m_no_e1_e2+a.m_m3[31]*b.m_no_e1_e3+a.m_m3[32]*b.m_no_e2_e3+a.m_m3[33]*b.m_e1_e2_e3+a.m_m3[34]*b.m_no_e1_ni+a.m_m3[35]*b.m_no_e2_ni+a.m_m3[36]*b.m_e1_e2_ni+a.m_m3[37]*b.m_no_e3_ni+a.m_m3[38]*b.m_e1_e3_ni+a.m_m3[39]*b.m_e2_e3_ni), // e1_e2_e3
			(a.m_m3[40]*b.m_no_e1_e2+a.m_m3[41]*b.m_no_e1_e3+a.m_m3[42]*b.m_no_e2_e3+a.m_m3[43]*b.m_e1_e2_e3+a.m_m3[44]*b.m_no_e1_ni+a.m_m3[45]*b.m_no_e2_ni+a.m_m3[46]*b.m_e1_e2_ni+a.m_m3[47]*b.m_no_e3_ni+a.m_m3[48]*b.m_e1_e3_ni+a.m_m3[49]*b.m_e2_e3_ni), // no_e1_ni
			(a.m_m3[50]*b.m_no_e1_e2+a.m_m3[51]*b.m_no_e1_e3+a.m_m3[52]*b.m_no_e2_e3+a.m_m3[53]*b.m_e1_e2_e3+a.m_m3[54]*b.m_no_e1_ni+a.m_m3[55]*b.m_no_e2_ni+a.m_m3[56]*b.m_e1_e2_ni+a.m_m3[57]*b.m_no_e3_ni+a.m_m3[58]*b.m_e1_e3_ni+a.m_m3[59]*b.m_e2_e3_ni), // no_e2_ni
			(a.m_m3[60]*b.m_no_e1_e2+a.m_m3[61]*b.m_no_e1_e3+a.m_m3[62]*b.m_no_e2_e3+a.m_m3[63]*b.m_e1_e2_e3+a.m_m3[64]*b.m_no_e1_ni+a.m_m3[65]*b.m_no_e2_ni+a.m_m3[66]*b.m_e1_e2_ni+a.m_m3[67]*b.m_no_e3_ni+a.m_m3[68]*b.m_e1_e3_ni+a.m_m3[69]*b.m_e2_e3_ni), // e1_e2_ni
			(a.m_m3[70]*b.m_no_e1_e2+a.m_m3[71]*b.m_no_e1_e3+a.m_m3[72]*b.m_no_e2_e3+a.m_m3[73]*b.m_e1_e2_e3+a.m_m3[74]*b.m_no_e1_ni+a.m_m3[75]*b.m_no_e2_ni+a.m_m3[76]*b.m_e1_e2_ni+a.m_m3[77]*b.m_no_e3_ni+a.m_m3[78]*b.m_e1_e3_ni+a.m_m3[79]*b.m_e2_e3_ni), // no_e3_ni
			(a.m_m3[80]*b.m_no_e1_e2+a.m_m3[81]*b.m_no_e1_e3+a.m_m3[82]*b.m_no_e2_e3+a.m_m3[83]*b.m_e1_e2_e3+a.m_m3[84]*b.m_no_e1_ni+a.m_m3[85]*b.m_no_e2_ni+a.m_m3[86]*b.m_e1_e2_ni+a.m_m3[87]*b.m_no_e3_ni+a.m_m3[88]*b.m_e1_e3_ni+a.m_m3[89]*b.m_e2_e3_ni), // e1_e3_ni
			(a.m_m3[90]*b.m_no_e1_e2+a.m_m3[91]*b.m_no_e1_e3+a.m_m3[92]*b.m_no_e2_e3+a.m_m3[93]*b.m_e1_e2_e3+a.m_m3[94]*b.m_no_e1_ni+a.m_m3[95]*b.m_no_e2_ni+a.m_m3[96]*b.m_e1_e2_ni+a.m_m3[97]*b.m_no_e3_ni+a.m_m3[98]*b.m_e1_e3_ni+a.m_m3[99]*b.m_e2_e3_ni) // e2_e3_ni
		);

}
/**
 * Returns om * sphere.
 */
public final static sphere applyOM(final om a, final sphere b)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_m4[20]*b.m_no_e1_e2_e3+a.m_m4[21]*b.m_no_e1_e2_ni+a.m_m4[22]*b.m_no_e1_e3_ni+a.m_m4[23]*b.m_no_e2_e3_ni+a.m_m4[24]*b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(a.m_m4[15]*b.m_no_e1_e2_e3+a.m_m4[16]*b.m_no_e1_e2_ni+a.m_m4[17]*b.m_no_e1_e3_ni+a.m_m4[18]*b.m_no_e2_e3_ni+a.m_m4[19]*b.m_e1_e2_e3_ni), // no_e2_e3_ni
			(a.m_m4[10]*b.m_no_e1_e2_e3+a.m_m4[11]*b.m_no_e1_e2_ni+a.m_m4[12]*b.m_no_e1_e3_ni+a.m_m4[13]*b.m_no_e2_e3_ni+a.m_m4[14]*b.m_e1_e2_e3_ni), // no_e1_e3_ni
			(a.m_m4[5]*b.m_no_e1_e2_e3+a.m_m4[6]*b.m_no_e1_e2_ni+a.m_m4[7]*b.m_no_e1_e3_ni+a.m_m4[8]*b.m_no_e2_e3_ni+a.m_m4[9]*b.m_e1_e2_e3_ni), // no_e1_e2_ni
			(a.m_m4[0]*b.m_no_e1_e2_e3+a.m_m4[1]*b.m_no_e1_e2_ni+a.m_m4[2]*b.m_no_e1_e3_ni+a.m_m4[3]*b.m_no_e2_e3_ni+a.m_m4[4]*b.m_e1_e2_e3_ni) // no_e1_e2_e3
		);

}
/**
 * Returns grade1OM_E3GA * vectorE3GA.
 */
public final static vectorE3GA applyOM(final grade1OM_E3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_m1[0]*b.m_e1+a.m_m1[1]*b.m_e2+a.m_m1[2]*b.m_e3), // e1
			(a.m_m1[3]*b.m_e1+a.m_m1[4]*b.m_e2+a.m_m1[5]*b.m_e3), // e2
			(a.m_m1[6]*b.m_e1+a.m_m1[7]*b.m_e2+a.m_m1[8]*b.m_e3) // e3
		);

}
/**
 * Returns grade1OM_E3GA * e1_t.
 */
public final static vectorE3GA applyOM(final grade1OM_E3GA a, final e1_t b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_m1[0], // e1
			a.m_m1[3], // e2
			a.m_m1[6] // e3
		);

}
/**
 * Returns grade1OM_E3GA * no_t.
 */
public final static double applyOM(final grade1OM_E3GA a, final no_t b)
{
	return 0.0;

}
/**
 * Returns grade1OM_E3GA * normalizedPoint.
 */
public final static vectorE3GA applyOM(final grade1OM_E3GA a, final normalizedPoint b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_m1[0]*b.m_e1+a.m_m1[1]*b.m_e2+a.m_m1[2]*b.m_e3), // e1
			(a.m_m1[3]*b.m_e1+a.m_m1[4]*b.m_e2+a.m_m1[5]*b.m_e3), // e2
			(a.m_m1[6]*b.m_e1+a.m_m1[7]*b.m_e2+a.m_m1[8]*b.m_e3) // e3
		);

}
/**
 * Returns flatPointOM * flatPoint.
 */
public final static flatPoint applyOM(final flatPointOM a, final flatPoint b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			(a.m_m2[0]*b.m_e1_ni+a.m_m2[1]*b.m_e2_ni+a.m_m2[2]*b.m_e3_ni+a.m_m2[3]*b.m_no_ni), // e1_ni
			(a.m_m2[4]*b.m_e1_ni+a.m_m2[5]*b.m_e2_ni+a.m_m2[6]*b.m_e3_ni+a.m_m2[7]*b.m_no_ni), // e2_ni
			(a.m_m2[10]*b.m_e3_ni+a.m_m2[11]*b.m_no_ni+a.m_m2[8]*b.m_e1_ni+a.m_m2[9]*b.m_e2_ni), // e3_ni
			(a.m_m2[12]*b.m_e1_ni+a.m_m2[13]*b.m_e2_ni+a.m_m2[14]*b.m_e3_ni+a.m_m2[15]*b.m_no_ni) // no_ni
		);

}
/**
 * Returns flatPointOM * noni_t.
 */
public final static flatPoint applyOM(final flatPointOM a, final noni_t b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			a.m_m2[3], // e1_ni
			a.m_m2[7], // e2_ni
			a.m_m2[11], // e3_ni
			a.m_m2[15] // no_ni
		);

}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static mv applyVersor(final mv_if a, final mv_if b)
{
	return extractGrade(gp(gp(a, b), versorInverse(a)), b.to_mv().gu());
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static mv applyUnitVersor(final mv_if a, final mv_if b)
{
	return extractGrade(gp(gp(a, b), reverse(a)), b.to_mv().gu());
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static mv applyVersorWI(final mv_if a, final mv_if b, final mv_if c)
{
	return extractGrade(gp(gp(a, b), c), b.to_mv().gu());
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static vectorE3GA applyUnitVersor(final rotorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3) // e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final rotorE3GA a, final normalizedPoint b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar), // no
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3), // e3
			(a.m_e1_e2*a.m_e1_e2*b.m_ni+a.m_e2_e3*a.m_e2_e3*b.m_ni+a.m_e3_e1*a.m_e3_e1*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final rotorE3GA a, final dualSphere b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_e1_e2*a.m_e1_e2*b.m_no+a.m_e2_e3*a.m_e2_e3*b.m_no+a.m_e3_e1*a.m_e3_e1*b.m_no+a.m_scalar*a.m_scalar*b.m_no), // no
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3), // e3
			(a.m_e1_e2*a.m_e1_e2*b.m_ni+a.m_e2_e3*a.m_e2_e3*b.m_ni+a.m_e3_e1*a.m_e3_e1*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static bivectorE3GA applyUnitVersor(final rotorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e1_e2), // e1_e2
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1) // e3_e1
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static line applyUnitVersor(final rotorE3GA a, final line b)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_ni), // e1_e2_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_e2_e3_ni), // e2_e3_ni
			-(a.m_e1_e2*a.m_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e2_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e3_no_ni-a.m_scalar*a.m_scalar*b.m_e1_no_ni), // e1_no_ni
			-(a.m_e1_e2*a.m_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_no_ni-a.m_scalar*a.m_scalar*b.m_e2_no_ni), // e2_no_ni
			-(-a.m_e1_e2*a.m_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_no_ni-a.m_scalar*a.m_scalar*b.m_e3_no_ni) // e3_no_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static plane applyUnitVersor(final rotorE3GA a, final plane b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_no_e1_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_no_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_no_e2_e3_ni), // no_e2_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e2_ni+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_no_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e2_ni) // no_e1_e2_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static flatPoint applyUnitVersor(final rotorE3GA a, final noni_t b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			(a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar) // no_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final rotorE3GA a, final no_t b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar), // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0 // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualPlane applyUnitVersor(final rotorE3GA a, final ni_t b)
{
	return new dualPlane(dualPlane.coord_e1_e2_e3_ni,
			0.0, // e1
			0.0, // e2
			0.0, // e3
			(a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static rotorE3GA applyUnitVersor(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(a.m_e1_e2*a.m_e1_e2*b.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_scalar+a.m_scalar*a.m_scalar*b.m_scalar), // scalar
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e1_e2), // e1_e2
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1) // e3_e1
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static pseudoscalar applyUnitVersor(final rotorE3GA a, final I5_t b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			(a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final evenVersor a, final vectorE3GA b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3), // no
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3), // e3
			(-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final evenVersor a, final normalizedPoint b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_e1*b.m_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e2*a.m_no_e2*b.m_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+2.0*a.m_no_e3*a.m_no_e3*b.m_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3+a.m_no_ni*a.m_no_ni+-2.0*a.m_no_ni*a.m_scalar+a.m_scalar*a.m_scalar), // no
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_e2_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+2.0*a.m_e1_ni*a.m_no_ni+-2.0*a.m_e1_ni*a.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+2.0*a.m_e3_e1*a.m_e3_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e1_e3_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_ni*b.m_ni+2.0*a.m_no_e1*a.m_scalar*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e1_ni+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+-2.0*a.m_e2_e3*a.m_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_ni+-2.0*a.m_e2_ni*a.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_ni+2.0*a.m_no_e2*a.m_scalar*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+-2.0*a.m_e1_ni*a.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+2.0*a.m_e2_e3*a.m_e2_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3+2.0*a.m_e3_ni*a.m_no_ni+-2.0*a.m_e3_ni*a.m_scalar+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2+2.0*a.m_no_e3*a.m_no_ni*b.m_ni+2.0*a.m_no_e3*a.m_scalar*b.m_ni-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3), // e3
			(a.m_e1_e2*a.m_e1_e2*b.m_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e1_ni+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_ni+2.0*a.m_e2_ni*a.m_e2_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_e3_ni*a.m_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_ni+a.m_no_ni*a.m_no_ni*b.m_ni+2.0*a.m_no_ni*a.m_scalar*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final evenVersor a, final dualSphere b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(a.m_e1_e2*a.m_e1_e2*b.m_no+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_no+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_no+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_e1*b.m_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e2*a.m_no_e2*b.m_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+2.0*a.m_no_e3*a.m_no_e3*b.m_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3+a.m_no_ni*a.m_no_ni*b.m_no+-2.0*a.m_no_ni*a.m_scalar*b.m_no+a.m_scalar*a.m_scalar*b.m_no), // no
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_no+2.0*a.m_e1_e2*a.m_no_e2*b.m_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+2.0*a.m_e1_ni*a.m_no_ni*b.m_no+-2.0*a.m_e1_ni*a.m_scalar*b.m_no+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+2.0*a.m_e3_e1*a.m_e3_ni*b.m_no+-2.0*a.m_e3_e1*a.m_no_e3*b.m_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_ni*b.m_ni+2.0*a.m_no_e1*a.m_scalar*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e1_ni*b.m_no+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_no+2.0*a.m_e2_e3*a.m_no_e3*b.m_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_ni*b.m_no+-2.0*a.m_e2_ni*a.m_scalar*b.m_no+a.m_e3_e1*a.m_e3_e1*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_ni+2.0*a.m_no_e2*a.m_scalar*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2), // e2
			(a.m_e1_e2*a.m_e1_e2*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_no+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_no+-2.0*a.m_e2_e3*a.m_no_e2*b.m_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3+2.0*a.m_e3_ni*a.m_no_ni*b.m_no+-2.0*a.m_e3_ni*a.m_scalar*b.m_no+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2+2.0*a.m_no_e3*a.m_no_ni*b.m_ni+2.0*a.m_no_e3*a.m_scalar*b.m_ni-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3), // e3
			(a.m_e1_e2*a.m_e1_e2*b.m_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e1_ni*b.m_no+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_ni+2.0*a.m_e2_ni*a.m_e2_ni*b.m_no+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_e3_ni*a.m_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_ni+a.m_no_ni*a.m_no_ni*b.m_ni+2.0*a.m_no_ni*a.m_scalar*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static pointPair applyUnitVersor(final evenVersor a, final bivectorE3GA b)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			(2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3_e1+2.0*a.m_no_e3*a.m_scalar*b.m_e3_e1), // no_e1
			(2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3), // no_e2
			(2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3), // no_e3
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_e3-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_e2-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_e1-a.m_no_ni*a.m_no_ni*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e1_e2), // e1_e2
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_e2+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_e3-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e3_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_ni*a.m_no_ni*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_e1-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2+a.m_no_ni*a.m_no_ni*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1), // e3_e1
			(-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_scalar*b.m_e3_e1), // e1_ni
			(2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_ni*b.m_e1_e2+2.0*a.m_e1_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_e3), // e2_ni
			(2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3), // e3_ni
			(-2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e2_e3) // no_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static circle applyUnitVersor(final evenVersor a, final line b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			(-2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e2_ni+-4.0*a.m_no_e1*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_no_ni+4.0*a.m_no_e1*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_e2_no_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2_e3*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e2_ni+-4.0*a.m_no_e2*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e2_ni), // no_e1_e2
			(2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e3_ni+4.0*a.m_no_e1*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_no_ni+-4.0*a.m_no_e1_e2_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e2*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e1_no_ni), // no_e1_e3
			(-2.0*a.m_e1_e2*a.m_no_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_no_e1*a.m_no_e1*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_no_ni+-4.0*a.m_no_e1*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_no_ni+4.0*a.m_no_e1*a.m_no_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e3_no_ni+4.0*a.m_no_e1_e2_e3*a.m_no_e2*b.m_e1_e2_ni+4.0*a.m_no_e1_e2_e3*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_no_ni+-2.0*a.m_no_e2*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e2*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_no_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_no_ni), // no_e2_e3
			(-2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e2_no_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_no_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e3*a.m_scalar*b.m_e1_e2_ni), // e1_e2_e3
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_no_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3_no_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e1_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_no_ni-a.m_scalar*a.m_scalar*b.m_e1_no_ni), // no_e1_ni
			(a.m_e1_e2*a.m_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1_no_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_no_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e2_no_ni-a.m_scalar*a.m_scalar*b.m_e2_no_ni), // no_e2_ni
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_scalar*b.m_e2_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e2_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_e3_e1*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_ni), // e1_e2_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2_no_ni+a.m_no_ni*a.m_no_ni*b.m_e3_no_ni-a.m_scalar*a.m_scalar*b.m_e3_no_ni), // no_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_scalar*b.m_e3_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3_ni+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_e1_no_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_e2_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e2_ni*a.m_scalar*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_no_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e1_e2_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e2_e3_ni) // e2_e3_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static sphere applyUnitVersor(final evenVersor a, final sphere b)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_e1_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_e1_ni*a.m_no_ni*b.m_no_e2_e3_ni+-2.0*a.m_e1_ni*a.m_scalar*b.m_no_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_e2_ni*a.m_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_ni*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_scalar*b.m_no_e1_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_e3_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_e3_ni*a.m_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_no_e1_e2_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_no_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e2_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e2_e3_ni+2.0*a.m_e3_e1*a.m_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_no_e1_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni*b.m_no_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e2_e3_ni), // no_e2_e3_ni
			(-a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e2_e3_ni+2.0*a.m_e2_e3*a.m_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_no_e3*b.m_no_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_no_e2_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni-a.m_no_ni*a.m_no_ni*b.m_no_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_no_e3*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_e3*a.m_e2_ni*b.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e3*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_no_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_no_e1_e3_ni+-2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e1_e2_ni+2.0*a.m_e3_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_no_e1_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e3*a.m_scalar*b.m_e1_e2_e3_ni-a.m_no_ni*a.m_no_ni*b.m_no_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e2_ni), // no_e1_e2_ni
			(a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2*b.m_no_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_e3+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_e3*a.m_no_e3*b.m_no_e1_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_e1*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_no_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e3*a.m_scalar*b.m_no_e1_e2_ni+a.m_no_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_no_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_scalar*a.m_scalar*b.m_no_e1_e2_e3) // no_e1_e2_e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final evenVersor a, final ni_t b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(2.0*a.m_no_e1*a.m_no_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3+2.0*a.m_no_e2*a.m_no_e2+2.0*a.m_no_e3*a.m_no_e3), // no
			(2.0*a.m_e1_e2*a.m_no_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e3+2.0*a.m_no_e1*a.m_no_ni+2.0*a.m_no_e1*a.m_scalar+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3), // e1
			(-2.0*a.m_e1_e2*a.m_no_e1+2.0*a.m_e2_e3*a.m_no_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni+2.0*a.m_no_e2*a.m_no_ni+2.0*a.m_no_e2*a.m_scalar+2.0*a.m_no_e2_e3_ni*a.m_no_e3), // e2
			(2.0*a.m_e1_e2*a.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e2+2.0*a.m_e3_e1*a.m_no_e1+-2.0*a.m_no_e1*a.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni+2.0*a.m_no_e3*a.m_no_ni+2.0*a.m_no_e3*a.m_scalar), // e3
			(a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_ni+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2_e3_ni+a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_ni*a.m_no_ni+2.0*a.m_no_ni*a.m_scalar+a.m_scalar*a.m_scalar) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static evenVersor applyUnitVersor(final evenVersor a, final rotorE3GA b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_e1_e2*a.m_e1_e2*b.m_scalar+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_scalar+2.0*a.m_e1_ni*a.m_no_e1*b.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_scalar+2.0*a.m_e2_ni*a.m_no_e2*b.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_scalar+2.0*a.m_e3_ni*a.m_no_e3*b.m_scalar-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_scalar-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_scalar-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_scalar-a.m_no_ni*a.m_no_ni*b.m_scalar+a.m_scalar*a.m_scalar*b.m_scalar), // scalar
			(2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3_e1+2.0*a.m_no_e3*a.m_scalar*b.m_e3_e1), // no_e1
			(2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3), // no_e2
			(2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3), // no_e3
			(a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_e3-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_e2-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_e1-a.m_no_ni*a.m_no_ni*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e1_e2), // e1_e2
			(-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_e2+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_e3-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e3_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_ni*a.m_no_ni*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_e1-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2+a.m_no_ni*a.m_no_ni*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1), // e3_e1
			(-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_scalar*b.m_e3_e1), // e1_ni
			(2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_ni*b.m_e1_e2+2.0*a.m_e1_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_e3), // e2_ni
			(2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3), // e3_ni
			(-2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e2_e3), // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static pseudoscalar applyUnitVersor(final evenVersor a, final I5i_t b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			(-a.m_e1_e2*a.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1-a.m_e2_e3*a.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2-a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_ni*a.m_no_ni-a.m_scalar*a.m_scalar) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final oddVersor a, final vectorE3GA b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2), // no
			(a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2-a.m_e2*a.m_e2*b.m_e1+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1-a.m_e3*a.m_e3*b.m_e1+2.0*a.m_ni*a.m_no*b.m_e1+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1), // e1
			(-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e2*b.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2-a.m_e3*a.m_e3*b.m_e2+2.0*a.m_ni*a.m_no*b.m_e2+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2), // e2
			(-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3+a.m_e3*a.m_e3*b.m_e3+2.0*a.m_ni*a.m_no*b.m_e3+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3), // e3
			(2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_e3*a.m_ni*b.m_e3+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final oddVersor a, final normalizedPoint b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(-a.m_e1*a.m_e1+2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+-2.0*a.m_e1*a.m_no_e1_ni-a.m_e1_e2_e3*a.m_e1_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1-a.m_e2*a.m_e2+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e2*a.m_no_e2_ni-a.m_e3*a.m_e3+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e3_ni+-2.0*a.m_no*a.m_no*b.m_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1-a.m_no_e1_ni*a.m_no_e1_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni), // no
			(a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+-2.0*a.m_e1*a.m_ni+-2.0*a.m_e1*a.m_no*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e2+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni+-2.0*a.m_e1_e3_ni*a.m_e3+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni-a.m_e2*a.m_e2*b.m_e1+-2.0*a.m_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1-a.m_e3*a.m_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e1+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e1_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1), // e1
			(-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e1_e2_ni+2.0*a.m_e1*a.m_e2*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+-2.0*a.m_e2*a.m_ni+-2.0*a.m_e2*a.m_no*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e3+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni-a.m_e3*a.m_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e2+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e2_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2), // e2
			(-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_e1+2.0*a.m_e1*a.m_no_e1_e3*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_ni-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e2_e3_ni+2.0*a.m_e2*a.m_e3*b.m_e2+2.0*a.m_e2*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_ni+a.m_e3*a.m_e3*b.m_e3+-2.0*a.m_e3*a.m_ni+-2.0*a.m_e3*a.m_no*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e3+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3), // e3
			(-a.m_e1*a.m_e1*b.m_ni+2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1*a.m_no_e1_ni*b.m_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e1_e2_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1-a.m_e2*a.m_e2*b.m_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+2.0*a.m_e2*a.m_no_e2_ni*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2-a.m_e3*a.m_e3*b.m_ni+2.0*a.m_e3*a.m_ni*b.m_e3+2.0*a.m_e3*a.m_no_e3_ni*b.m_ni+-2.0*a.m_ni*a.m_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyUnitVersor(final oddVersor a, final dualSphere b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(-a.m_e1*a.m_e1*b.m_no+2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+-2.0*a.m_e1*a.m_no_e1_ni*b.m_no-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1-a.m_e2*a.m_e2*b.m_no+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e2*a.m_no_e2_ni*b.m_no-a.m_e3*a.m_e3*b.m_no+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e3_ni*b.m_no+-2.0*a.m_no*a.m_no*b.m_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1-a.m_no_e1_ni*a.m_no_e1_ni*b.m_no+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2-a.m_no_e2_ni*a.m_no_e2_ni*b.m_no-a.m_no_e3_ni*a.m_no_e3_ni*b.m_no), // no
			(a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+-2.0*a.m_e1*a.m_ni*b.m_no+-2.0*a.m_e1*a.m_no*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_no+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_no+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_no-a.m_e2*a.m_e2*b.m_e1+-2.0*a.m_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1-a.m_e3*a.m_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e1+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e1_ni*b.m_no+2.0*a.m_no*a.m_no_e1_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1), // e1
			(-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e1_e2_ni*b.m_no+2.0*a.m_e1*a.m_e2*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_no+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+-2.0*a.m_e2*a.m_ni*b.m_no+-2.0*a.m_e2*a.m_no*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_no+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_no-a.m_e3*a.m_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e2+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e2_ni*b.m_no+2.0*a.m_no*a.m_no_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2), // e2
			(-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_no+2.0*a.m_e1*a.m_e3*b.m_e1+2.0*a.m_e1*a.m_no_e1_e3*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_no-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e2_e3_ni*b.m_no+2.0*a.m_e2*a.m_e3*b.m_e2+2.0*a.m_e2*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_no+a.m_e3*a.m_e3*b.m_e3+-2.0*a.m_e3*a.m_ni*b.m_no+-2.0*a.m_e3*a.m_no*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e3+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_no+2.0*a.m_no*a.m_no_e3_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3), // e3
			(-a.m_e1*a.m_e1*b.m_ni+2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1*a.m_no_e1_ni*b.m_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e1_e3_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1-a.m_e2*a.m_e2*b.m_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+2.0*a.m_e2*a.m_no_e2_ni*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2-a.m_e3*a.m_e3*b.m_ni+2.0*a.m_e3*a.m_ni*b.m_e3+2.0*a.m_e3*a.m_no_e3_ni*b.m_ni+-2.0*a.m_ni*a.m_ni*b.m_no+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_ni) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static pointPair applyUnitVersor(final oddVersor a, final bivectorE3GA b)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			(-2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_e2*a.m_no*b.m_e1_e2+-2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e3*a.m_no*b.m_e3_e1+2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_no*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_e3+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2), // no_e1
			(-2.0*a.m_e1*a.m_no*b.m_e1_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_e3*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_no*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e2*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e3*a.m_no*b.m_e2_e3+2.0*a.m_e3*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_e3+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_e3), // no_e2
			(2.0*a.m_e1*a.m_no*b.m_e3_e1+-2.0*a.m_e1*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e2*a.m_no*b.m_e2_e3+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e2+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_no*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2_e3), // no_e3
			(-a.m_e1*a.m_e1*b.m_e1_e2+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1*a.m_e3*b.m_e2_e3+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1_e2-a.m_e2*a.m_e2*b.m_e1_e2+2.0*a.m_e2*a.m_e3*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2+a.m_e3*a.m_e3*b.m_e1_e2+-2.0*a.m_ni*a.m_no*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3_e1-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2), // e1_e2
			(a.m_e1*a.m_e1*b.m_e2_e3+2.0*a.m_e1*a.m_e2*b.m_e3_e1+2.0*a.m_e1*a.m_e3*b.m_e1_e2+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e3_e1-a.m_e2*a.m_e2*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2_e3-a.m_e3*a.m_e3*b.m_e2_e3+-2.0*a.m_ni*a.m_no*b.m_e2_e3+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1_e2-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e3_e1-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_e2+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_e3), // e2_e3
			-(a.m_e1*a.m_e1*b.m_e3_e1+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1*a.m_e2*b.m_e2_e3-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2_e3-a.m_e2*a.m_e2*b.m_e3_e1+-2.0*a.m_e2*a.m_e3*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3_e1+a.m_e3*a.m_e3*b.m_e3_e1+2.0*a.m_ni*a.m_no*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_e3*b.m_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3_e1+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3_e1), // e3_e1
			(2.0*a.m_e1*a.m_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1*a.m_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e2_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_e2*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e2_e3+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2*a.m_ni*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_e3*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e3*a.m_ni*b.m_e3_e1+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3_e1), // e1_ni
			(-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1*a.m_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_e3*a.m_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_ni*a.m_no_e3_ni*b.m_e2_e3), // e2_ni
			(2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_ni*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_e3*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_e2*a.m_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2_e3), // e3_ni
			(2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_no_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_e2*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2_e3) // no_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static circle applyUnitVersor(final oddVersor a, final line b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			(-2.0*a.m_e1*a.m_no*b.m_e2_no_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e2*a.m_no*b.m_e1_no_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_no*a.m_no*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+4.0*a.m_no*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e2_no_ni+-4.0*a.m_no*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e2_ni+-4.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_no_ni), // no_e1_e2
			(-2.0*a.m_e1*a.m_no*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_no_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_no_ni+2.0*a.m_e3*a.m_no*b.m_e1_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no*b.m_e1_e3_ni+-4.0*a.m_no*a.m_no_e1_e2*b.m_e2_e3_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e3_no_ni+4.0*a.m_no*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_no_ni), // no_e1_e3
			(2.0*a.m_e1*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e3*a.m_no*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no*b.m_e2_e3_ni+4.0*a.m_no*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e3_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_no_ni+-4.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e3_no_ni), // no_e2_e3
			(-2.0*a.m_e1*a.m_no*b.m_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_e2*a.m_no*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2*a.m_no_e1_ni*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_e1_no_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e3_ni), // e1_e2_e3
			(-a.m_e1*a.m_e1*b.m_e1_no_ni+-2.0*a.m_e1*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+a.m_e2*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e2*a.m_no*b.m_e1_e2_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1_no_ni+a.m_e3*a.m_e3*b.m_e1_no_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_ni*a.m_no*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_no_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni), // no_e1_ni
			(a.m_e1*a.m_e1*b.m_e2_no_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e2*b.m_e1_no_ni+2.0*a.m_e1*a.m_no*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_e3_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1_no_ni-a.m_e2*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e2*a.m_e3*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+a.m_e3*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no*b.m_e2_e3_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e3_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3_no_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni), // no_e2_ni
			(a.m_e1*a.m_e1*b.m_e1_e2_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_e1*a.m_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e2_e3_ni+2.0*a.m_e1*a.m_ni*b.m_e2_no_ni+-2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_e3*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni+a.m_e2*a.m_e2*b.m_e1_e2_ni+2.0*a.m_e2*a.m_e3*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_ni*b.m_e1_no_ni+-2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e1_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni-a.m_e3*a.m_e3*b.m_e1_e2_ni+2.0*a.m_e3*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e2_ni+-2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e2_ni*b.m_e1_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2_ni), // e1_e2_ni
			(a.m_e1*a.m_e1*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e1_no_ni+2.0*a.m_e1*a.m_no*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e2_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+a.m_e2*a.m_e2*b.m_e3_no_ni+-2.0*a.m_e2*a.m_e3*b.m_e2_no_ni+2.0*a.m_e2*a.m_no*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni-a.m_e3*a.m_e3*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2_no_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni), // no_e3_ni
			(a.m_e1*a.m_e1*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_e1*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1*a.m_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni-a.m_e2*a.m_e2*b.m_e1_e3_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e2*a.m_e3*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+a.m_e3*a.m_e3*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_ni*b.m_e1_no_ni+-2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_ni*a.m_no_e3_ni*b.m_e1_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1*a.m_e1*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_e1*a.m_e2*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+a.m_e2*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2*a.m_ni*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni+a.m_e3*a.m_e3*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_ni*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e3_ni*b.m_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_ni*a.m_no_e3_ni*b.m_e2_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_e3_ni) // e2_e3_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static sphere applyUnitVersor(final oddVersor a, final plane b)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(-a.m_e1*a.m_e1*b.m_e1_e2_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e1_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1*a.m_ni*b.m_no_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni-a.m_e2*a.m_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2*a.m_ni*b.m_no_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni-a.m_e3*a.m_e3*b.m_e1_e2_e3_ni+2.0*a.m_e3*a.m_ni*b.m_no_e1_e2_ni+2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_no_e2_e3_ni+2.0*a.m_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(a.m_e1*a.m_e1*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_e2*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_no*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni-a.m_e2*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni-a.m_e3*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni), // no_e2_e3_ni
			(-a.m_e1*a.m_e1*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+a.m_e2*a.m_e2*b.m_no_e1_e3_ni+-2.0*a.m_e2*a.m_e3*b.m_no_e1_e2_ni+2.0*a.m_e2*a.m_no*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_no_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni-a.m_e3*a.m_e3*b.m_no_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+-2.0*a.m_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni), // no_e1_e3_ni
			(-a.m_e1*a.m_e1*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_no_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no*b.m_no_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni-a.m_e2*a.m_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2*a.m_e3*b.m_no_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no*b.m_no_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+a.m_e3*a.m_e3*b.m_no_e1_e2_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni), // no_e1_e2_ni
			(2.0*a.m_e1*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2*a.m_no*b.m_no_e1_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e3*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_no*a.m_no*b.m_e1_e2_e3_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_no_e2_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_no_e1_e3_ni) // no_e1_e2_e3
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static vectorE3GA applyVersor(final rotorE3GA a, final vectorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_) // e3
		);
}
/**
 * Returns a * b * inverse(a) using euclidean metric.
 */
public final static dualSphere applyVersor(final rotorE3GA a, final normalizedPoint b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))/(_n2_), // no
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2*b.m_ni+a.m_e2_e3*a.m_e2_e3*b.m_ni+a.m_e3_e1*a.m_e3_e1*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final rotorE3GA a, final dualSphere b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((a.m_e1_e2*a.m_e1_e2*b.m_no+a.m_e2_e3*a.m_e2_e3*b.m_no+a.m_e3_e1*a.m_e3_e1*b.m_no+a.m_scalar*a.m_scalar*b.m_no))/(_n2_), // no
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2*b.m_ni+a.m_e2_e3*a.m_e2_e3*b.m_ni+a.m_e3_e1*a.m_e3_e1*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static bivectorE3GA applyVersor(final rotorE3GA a, final bivectorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e1_e2))/(_n2_), // e1_e2
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_e3
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1))/(_n2_) // e3_e1
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static line applyVersor(final rotorE3GA a, final line b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_ni))/(_n2_), // e1_e2_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e3_ni))/(_n2_), // e1_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_e2_e3_ni))/(_n2_), // e2_e3_ni
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e2_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e3_no_ni-a.m_scalar*a.m_scalar*b.m_e1_no_ni))/(_n2_), // e1_no_ni
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_no_ni-a.m_scalar*a.m_scalar*b.m_e2_no_ni))/(_n2_), // e2_no_ni
			(-(-a.m_e1_e2*a.m_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_no_ni-a.m_scalar*a.m_scalar*b.m_e3_no_ni))/(_n2_) // e3_no_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static plane applyVersor(final rotorE3GA a, final plane b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_e3_ni))/(_n2_), // e1_e2_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_no_e1_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_no_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_no_e2_e3_ni))/(_n2_), // no_e2_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e2_ni+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e3_ni))/(_n2_), // no_e1_e3_ni
			((a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_no_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e2_ni))/(_n2_) // no_e1_e2_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static flatPoint applyVersor(final rotorE3GA a, final noni_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			(0.0)/(_n2_), // e1_ni
			(0.0)/(_n2_), // e2_ni
			(0.0)/(_n2_), // e3_ni
			((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))/(_n2_) // no_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final rotorE3GA a, final no_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))/(_n2_), // no
			(0.0)/(_n2_), // e1
			(0.0)/(_n2_), // e2
			(0.0)/(_n2_), // e3
			(0.0)/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualPlane applyVersor(final rotorE3GA a, final ni_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new dualPlane(dualPlane.coord_e1_e2_e3_ni,
			(0.0)/(_n2_), // e1
			(0.0)/(_n2_), // e2
			(0.0)/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static rotorE3GA applyVersor(final rotorE3GA a, final rotorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			((a.m_e1_e2*a.m_e1_e2*b.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_scalar+a.m_scalar*a.m_scalar*b.m_scalar))/(_n2_), // scalar
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e1_e2))/(_n2_), // e1_e2
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_e3
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1))/(_n2_) // e3_e1
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static pseudoscalar applyVersor(final rotorE3GA a, final I5_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))/(_n2_) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final evenVersor a, final vectorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3))/(_n2_), // no
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+a.m_e3_e1*a.m_e3_e1*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_), // e3
			((-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final evenVersor a, final normalizedPoint b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_e1*b.m_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e2*a.m_no_e2*b.m_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+2.0*a.m_no_e3*a.m_no_e3*b.m_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3+a.m_no_ni*a.m_no_ni+-2.0*a.m_no_ni*a.m_scalar+a.m_scalar*a.m_scalar))/(_n2_), // no
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_e2_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+2.0*a.m_e1_ni*a.m_no_ni+-2.0*a.m_e1_ni*a.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+2.0*a.m_e3_e1*a.m_e3_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e1_e3_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_ni*b.m_ni+2.0*a.m_no_e1*a.m_scalar*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e1_ni+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+-2.0*a.m_e2_e3*a.m_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_ni+-2.0*a.m_e2_ni*a.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_ni+2.0*a.m_no_e2*a.m_scalar*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+-2.0*a.m_e1_ni*a.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+2.0*a.m_e2_e3*a.m_e2_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3+2.0*a.m_e3_ni*a.m_no_ni+-2.0*a.m_e3_ni*a.m_scalar+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2+2.0*a.m_no_e3*a.m_no_ni*b.m_ni+2.0*a.m_no_e3*a.m_scalar*b.m_ni-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2*b.m_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e1_ni+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_ni+2.0*a.m_e2_ni*a.m_e2_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_e3_ni*a.m_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_ni+a.m_no_ni*a.m_no_ni*b.m_ni+2.0*a.m_no_ni*a.m_scalar*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final evenVersor a, final dualSphere b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((a.m_e1_e2*a.m_e1_e2*b.m_no+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_no+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_no+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_e1*b.m_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1+2.0*a.m_no_e1*a.m_scalar*b.m_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e2*a.m_no_e2*b.m_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2+2.0*a.m_no_e2*a.m_scalar*b.m_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2+2.0*a.m_no_e3*a.m_no_e3*b.m_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3+2.0*a.m_no_e3*a.m_scalar*b.m_e3+a.m_no_ni*a.m_no_ni*b.m_no+-2.0*a.m_no_ni*a.m_scalar*b.m_no+a.m_scalar*a.m_scalar*b.m_no))/(_n2_), // no
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_no+2.0*a.m_e1_e2*a.m_no_e2*b.m_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3+2.0*a.m_e1_ni*a.m_no_ni*b.m_no+-2.0*a.m_e1_ni*a.m_scalar*b.m_no+a.m_e2_e3*a.m_e2_e3*b.m_e1+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1+2.0*a.m_e3_e1*a.m_e3_ni*b.m_no+-2.0*a.m_e3_e1*a.m_no_e3*b.m_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e3+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_no_e1*a.m_no_ni*b.m_ni+2.0*a.m_no_e1*a.m_scalar*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1-a.m_no_ni*a.m_no_ni*b.m_e1+a.m_scalar*a.m_scalar*b.m_e1))/(_n2_), // e1
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2+2.0*a.m_e1_e2*a.m_e1_ni*b.m_no+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e2+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_no+2.0*a.m_e2_e3*a.m_no_e3*b.m_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3+2.0*a.m_e2_ni*a.m_no_ni*b.m_no+-2.0*a.m_e2_ni*a.m_scalar*b.m_no+a.m_e3_e1*a.m_e3_e1*b.m_e2+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_ni+2.0*a.m_no_e2*a.m_scalar*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3-a.m_no_ni*a.m_no_ni*b.m_e2+a.m_scalar*a.m_scalar*b.m_e2))/(_n2_), // e2
			((a.m_e1_e2*a.m_e1_e2*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_no+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1-a.m_e2_e3*a.m_e2_e3*b.m_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_no+-2.0*a.m_e2_e3*a.m_no_e2*b.m_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3+2.0*a.m_e3_ni*a.m_no_ni*b.m_no+-2.0*a.m_e3_ni*a.m_scalar*b.m_no+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2+2.0*a.m_no_e3*a.m_no_ni*b.m_ni+2.0*a.m_no_e3*a.m_scalar*b.m_ni-a.m_no_ni*a.m_no_ni*b.m_e3+a.m_scalar*a.m_scalar*b.m_e3))/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2*b.m_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1+2.0*a.m_e1_ni*a.m_e1_ni*b.m_no+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e1+a.m_e2_e3*a.m_e2_e3*b.m_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_ni+2.0*a.m_e2_ni*a.m_e2_ni*b.m_no+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e2+a.m_e3_e1*a.m_e3_e1*b.m_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_ni+2.0*a.m_e3_ni*a.m_e3_ni*b.m_no+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_ni+a.m_no_ni*a.m_no_ni*b.m_ni+2.0*a.m_no_ni*a.m_scalar*b.m_ni+a.m_scalar*a.m_scalar*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static pointPair applyVersor(final evenVersor a, final bivectorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			((2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3_e1+2.0*a.m_no_e3*a.m_scalar*b.m_e3_e1))/(_n2_), // no_e1
			((2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3))/(_n2_), // no_e2
			((2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3))/(_n2_), // no_e3
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_e3-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_e2-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_e1-a.m_no_ni*a.m_no_ni*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e1_e2))/(_n2_), // e1_e2
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_e2+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_e3-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e3_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_ni*a.m_no_ni*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_e3
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_e1-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2+a.m_no_ni*a.m_no_ni*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1))/(_n2_), // e3_e1
			((-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_scalar*b.m_e3_e1))/(_n2_), // e1_ni
			((2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_ni*b.m_e1_e2+2.0*a.m_e1_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_ni
			((2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3))/(_n2_), // e3_ni
			((-2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e2_e3))/(_n2_) // no_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static circle applyVersor(final evenVersor a, final line b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			((-2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e2_ni+-4.0*a.m_no_e1*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_no_ni+4.0*a.m_no_e1*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_e2_no_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2_e3*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e2_ni+-4.0*a.m_no_e2*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e2_ni))/(_n2_), // no_e1_e2
			((2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e3_ni+4.0*a.m_no_e1*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_no_ni+-4.0*a.m_no_e1_e2_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e2*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e1_no_ni))/(_n2_), // no_e1_e3
			((-2.0*a.m_e1_e2*a.m_no_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_no_e1*a.m_no_e1*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_no_ni+-4.0*a.m_no_e1*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_no_ni+4.0*a.m_no_e1*a.m_no_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e3_no_ni+4.0*a.m_no_e1_e2_e3*a.m_no_e2*b.m_e1_e2_ni+4.0*a.m_no_e1_e2_e3*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_no_ni+-2.0*a.m_no_e2*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_no_e2*a.m_scalar*b.m_e3_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_no_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_no_ni))/(_n2_), // no_e2_e3
			((-2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e2_no_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_no_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e3*a.m_scalar*b.m_e1_e2_ni))/(_n2_), // e1_e2_e3
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_no_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e3_no_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e1_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_no_ni-a.m_scalar*a.m_scalar*b.m_e1_no_ni))/(_n2_), // no_e1_ni
			((a.m_e1_e2*a.m_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_no_ni+2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_no_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1_no_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2_ni+2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_no_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e2_no_ni-a.m_scalar*a.m_scalar*b.m_e2_no_ni))/(_n2_), // no_e2_ni
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_scalar*b.m_e2_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_e2_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_e3_e1*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_ni))/(_n2_), // e1_e2_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3_ni+-2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_no_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e3_no_ni+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_no_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_no_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e2_no_ni+a.m_no_ni*a.m_no_ni*b.m_e3_no_ni-a.m_scalar*a.m_scalar*b.m_e3_no_ni))/(_n2_), // no_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_scalar*b.m_e3_no_ni-a.m_e2_e3*a.m_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e1_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3_ni+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e3_ni+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_e1_no_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e1_e2_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e3_ni))/(_n2_), // e1_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_e3_no_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_no_ni+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_no_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2_ni*a.m_no_ni*b.m_e3_no_ni+2.0*a.m_e2_ni*a.m_scalar*b.m_e3_no_ni-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_ni*b.m_e1_e2_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_no_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_no_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e1_e2_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e2_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e2_e3_ni))/(_n2_) // e2_e3_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static sphere applyVersor(final evenVersor a, final sphere b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_e1_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_e1_ni*a.m_no_ni*b.m_no_e2_e3_ni+-2.0*a.m_e1_ni*a.m_scalar*b.m_no_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3*a.m_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_e2_ni*a.m_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_ni*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_scalar*b.m_no_e1_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_e1_e2_e3_ni+-2.0*a.m_e3_e1*a.m_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_e3_ni*a.m_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_scalar*b.m_no_e1_e2_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_ni*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_ni*a.m_scalar*b.m_e1_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_e1_e2_e3_ni))/(_n2_), // e1_e2_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2*a.m_scalar*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_no_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e1_e3_ni+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e2_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e2_e3_ni+2.0*a.m_e3_e1*a.m_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e3_e1*a.m_scalar*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_no_e1_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni*b.m_no_e2_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e2_e3_ni))/(_n2_), // no_e2_e3_ni
			((-a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2*a.m_e1_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2*a.m_scalar*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_no_e2_e3_ni+2.0*a.m_e2_e3*a.m_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e2_ni+2.0*a.m_e2_ni*a.m_no_e1*b.m_no_e2_e3_ni+-2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_no_e3*b.m_no_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e3_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_no_e2_e3_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_no_e1_e2_ni-a.m_no_ni*a.m_no_ni*b.m_no_e1_e3_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e3_ni))/(_n2_), // no_e1_e3_ni
			((a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_ni*a.m_e3_e1*b.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_no_e1_e2_ni+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_no_e3*b.m_no_e2_e3_ni-a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e2_e3*a.m_e2_ni*b.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3*a.m_scalar*b.m_no_e1_e3_ni+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e2_ni*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_ni*a.m_no_e3*b.m_no_e1_e3_ni-a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_ni+2.0*a.m_e3_e1*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_e3_e1*a.m_scalar*b.m_no_e2_e3_ni+-2.0*a.m_e3_ni*a.m_no_e1*b.m_no_e2_e3_ni+2.0*a.m_e3_ni*a.m_no_e2*b.m_no_e1_e3_ni+-2.0*a.m_e3_ni*a.m_no_e3*b.m_no_e1_e2_ni+2.0*a.m_e3_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_no_e1_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e3*a.m_no_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e3*a.m_scalar*b.m_e1_e2_e3_ni-a.m_no_ni*a.m_no_ni*b.m_no_e1_e2_ni+a.m_scalar*a.m_scalar*b.m_no_e1_e2_ni))/(_n2_), // no_e1_e2_ni
			((a.m_e1_e2*a.m_e1_e2*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_no_e1*b.m_no_e1_e3_ni+2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2*b.m_no_e2_e3_ni+a.m_e2_e3*a.m_e2_e3*b.m_no_e1_e2_e3+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e2_e3*a.m_no_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e2_e3*a.m_no_e3*b.m_no_e1_e3_ni+a.m_e3_e1*a.m_e3_e1*b.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1*b.m_no_e1_e2_ni+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e3_e1*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_e3_e1*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1*b.m_e1_e2_e3_ni+2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1*a.m_no_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1*a.m_scalar*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_no_e2_e3_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_no_e2_e3_ni+2.0*a.m_no_e2*a.m_no_e2*b.m_e1_e2_e3_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2*a.m_no_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e2*a.m_scalar*b.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e3*a.m_no_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e3*a.m_no_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e3*a.m_scalar*b.m_no_e1_e2_ni+a.m_no_ni*a.m_no_ni*b.m_no_e1_e2_e3+-2.0*a.m_no_ni*a.m_scalar*b.m_no_e1_e2_e3+a.m_scalar*a.m_scalar*b.m_no_e1_e2_e3))/(_n2_) // no_e1_e2_e3
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final evenVersor a, final ni_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((2.0*a.m_no_e1*a.m_no_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_e3+2.0*a.m_no_e2*a.m_no_e2+2.0*a.m_no_e3*a.m_no_e3))/(_n2_), // no
			((2.0*a.m_e1_e2*a.m_no_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3+-2.0*a.m_e3_e1*a.m_no_e3+2.0*a.m_no_e1*a.m_no_ni+2.0*a.m_no_e1*a.m_scalar+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_ni*a.m_no_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3))/(_n2_), // e1
			((-2.0*a.m_e1_e2*a.m_no_e1+2.0*a.m_e2_e3*a.m_no_e3+2.0*a.m_e3_e1*a.m_no_e1_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni+2.0*a.m_no_e2*a.m_no_ni+2.0*a.m_no_e2*a.m_scalar+2.0*a.m_no_e2_e3_ni*a.m_no_e3))/(_n2_), // e2
			((2.0*a.m_e1_e2*a.m_no_e1_e2_e3+-2.0*a.m_e2_e3*a.m_no_e2+2.0*a.m_e3_e1*a.m_no_e1+-2.0*a.m_no_e1*a.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni+-2.0*a.m_no_e2*a.m_no_e2_e3_ni+2.0*a.m_no_e3*a.m_no_ni+2.0*a.m_no_e3*a.m_scalar))/(_n2_), // e3
			((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2*a.m_no_e1_e2_ni+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2_e3_ni+a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_e1*a.m_no_e1_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_ni*a.m_no_ni+2.0*a.m_no_ni*a.m_scalar+a.m_scalar*a.m_scalar))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static evenVersor applyVersor(final evenVersor a, final rotorE3GA b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			((a.m_e1_e2*a.m_e1_e2*b.m_scalar+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_scalar+2.0*a.m_e1_ni*a.m_no_e1*b.m_scalar+a.m_e2_e3*a.m_e2_e3*b.m_scalar+2.0*a.m_e2_ni*a.m_no_e2*b.m_scalar+a.m_e3_e1*a.m_e3_e1*b.m_scalar+2.0*a.m_e3_ni*a.m_no_e3*b.m_scalar-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_scalar-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_scalar-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_scalar-a.m_no_ni*a.m_no_ni*b.m_scalar+a.m_scalar*a.m_scalar*b.m_scalar))/(_n2_), // scalar
			((2.0*a.m_e1_e2*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e1*b.m_e2_e3+2.0*a.m_e2_e3*a.m_no_e2*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e2_e3+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e2*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e3*a.m_no_ni*b.m_e3_e1+2.0*a.m_no_e3*a.m_scalar*b.m_e3_e1))/(_n2_), // no_e1
			((2.0*a.m_e1_e2*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_no_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e2*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_e1*a.m_no_e3*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e2*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_no_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_no_e3*a.m_scalar*b.m_e2_e3))/(_n2_), // no_e2
			((2.0*a.m_e1_e2*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_e2*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_no_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e1*a.m_no_ni*b.m_e3_e1+-2.0*a.m_no_e1*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3*a.m_no_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e3*b.m_e1_e2+-2.0*a.m_no_e1_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_e3*b.m_e3_e1+-2.0*a.m_no_e2*a.m_no_ni*b.m_e2_e3+2.0*a.m_no_e2*a.m_scalar*b.m_e2_e3+-2.0*a.m_no_e2_e3_ni*a.m_no_e3*b.m_e2_e3))/(_n2_), // no_e3
			((a.m_e1_e2*a.m_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e2_e3-a.m_e2_e3*a.m_e2_e3*b.m_e1_e2+-2.0*a.m_e2_e3*a.m_scalar*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e3*b.m_e3_e1-a.m_e3_e1*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e3_e1*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3*b.m_e1_e2-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e3_e1-a.m_no_ni*a.m_no_ni*b.m_e1_e2+a.m_scalar*a.m_scalar*b.m_e1_e2))/(_n2_), // e1_e2
			((-a.m_e1_e2*a.m_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e3_e1+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e3*b.m_e1_e2+a.m_e2_e3*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e2_e3-a.m_e3_e1*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_scalar*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3*b.m_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e3_e1+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_ni*b.m_e1_e2-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_ni*a.m_no_ni*b.m_e2_e3+a.m_scalar*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_e3
			(-(a.m_e1_e2*a.m_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e1_e2*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e2*b.m_e2_e3+a.m_e2_e3*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e2_e3*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e2_e3*a.m_scalar*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_e3*b.m_e1_e2-a.m_e3_e1*a.m_e3_e1*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_e3*b.m_e3_e1-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_ni*a.m_no_ni*b.m_e2_e3+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_no_e2_e3_ni*a.m_no_ni*b.m_e1_e2+a.m_no_ni*a.m_no_ni*b.m_e3_e1-a.m_scalar*a.m_scalar*b.m_e3_e1))/(_n2_), // e3_e1
			((-2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e1_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_ni*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_scalar*b.m_e3_e1))/(_n2_), // e1_ni
			((2.0*a.m_e1_e2*a.m_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e1_e2+-2.0*a.m_e1_e2*a.m_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e3_e1+2.0*a.m_e1_ni*a.m_e3_e1*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_ni*a.m_no_ni*b.m_e1_e2+2.0*a.m_e1_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_e2_e3*a.m_e2_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_e3_e1*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e2_e3_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e3_ni*a.m_scalar*b.m_e2_e3))/(_n2_), // e2_ni
			((2.0*a.m_e1_e2*a.m_e1_ni*b.m_e2_e3+2.0*a.m_e1_e2*a.m_e2_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_e3_e1*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3_ni*a.m_no_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_scalar*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_e2_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_ni*a.m_no_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_ni*b.m_e3_e1+-2.0*a.m_e1_ni*a.m_scalar*b.m_e3_e1+2.0*a.m_e2_e3*a.m_e3_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_e3_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_ni*b.m_e2_e3+2.0*a.m_e2_ni*a.m_scalar*b.m_e2_e3+2.0*a.m_e3_e1*a.m_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e1_e3_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e2_e3_ni*b.m_e2_e3))/(_n2_), // e3_ni
			((-2.0*a.m_e1_e2*a.m_no_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2*a.m_no_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1_e2*a.m_no_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1*b.m_e2_e3+-2.0*a.m_e1_e2_e3_ni*a.m_no_e2*b.m_e3_e1+-2.0*a.m_e1_e2_e3_ni*a.m_no_e3*b.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_ni*a.m_no_e2*b.m_e1_e2+-2.0*a.m_e1_ni*a.m_no_e3*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e2_ni*b.m_e3_e1+2.0*a.m_e2_e3*a.m_no_e1_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3*a.m_no_ni*b.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e1*b.m_e1_e2+2.0*a.m_e2_ni*a.m_no_e1_e2_e3*b.m_e3_e1+2.0*a.m_e2_ni*a.m_no_e3*b.m_e2_e3+-2.0*a.m_e3_e1*a.m_no_e1_e2_ni*b.m_e2_e3+2.0*a.m_e3_e1*a.m_no_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3_e1*a.m_no_ni*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1*b.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e3_ni*a.m_no_e2*b.m_e2_e3+-2.0*a.m_no_e1_e2_ni*a.m_scalar*b.m_e1_e2+2.0*a.m_no_e1_e3_ni*a.m_scalar*b.m_e3_e1+-2.0*a.m_no_e2_e3_ni*a.m_scalar*b.m_e2_e3))/(_n2_), // no_ni
			(0.0)/(_n2_), // e1_e2_e3_ni
			(0.0)/(_n2_), // no_e2_e3_ni
			(0.0)/(_n2_), // no_e1_e3_ni
			(0.0)/(_n2_), // no_e1_e2_ni
			(0.0)/(_n2_) // no_e1_e2_e3
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static pseudoscalar applyVersor(final evenVersor a, final I5i_t b)
{
	double _n2_ = (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			((-a.m_e1_e2*a.m_e1_e2+-2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+-2.0*a.m_e1_ni*a.m_no_e1-a.m_e2_e3*a.m_e2_e3+-2.0*a.m_e2_ni*a.m_no_e2-a.m_e3_e1*a.m_e3_e1+-2.0*a.m_e3_ni*a.m_no_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_ni*a.m_no_ni-a.m_scalar*a.m_scalar))/(_n2_) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a * b * inverse(a) using euclidean metric.
 */
public final static dualSphere applyVersor(final oddVersor a, final vectorE3GA b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2*a.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3*a.m_e3+a.m_ni*a.m_ni+a.m_no*a.m_no+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3))/(_n2_), // no
			((a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+a.m_e1_e2_ni*a.m_e1_e2_ni*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_ni*b.m_e2+a.m_e1_e3_ni*a.m_e1_e3_ni*b.m_e1+2.0*a.m_e1_e3_ni*a.m_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_ni*b.m_e3-a.m_e2*a.m_e2*b.m_e1-a.m_e2_e3_ni*a.m_e2_e3_ni*b.m_e1-a.m_e3*a.m_e3*b.m_e1-a.m_ni*a.m_ni*b.m_e1-a.m_no*a.m_no*b.m_e1+-2.0*a.m_no*a.m_no_e1_e2*b.m_e2+-2.0*a.m_no*a.m_no_e1_e3*b.m_e3+a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1+-2.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e3+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2+a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1+2.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e2+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1))/(_n2_), // e1
			((-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e2*b.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+a.m_e1_e2_ni*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1_e2_ni*a.m_ni*b.m_e1-a.m_e1_e3_ni*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e3_ni*a.m_e2_e3_ni*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+a.m_e2_e3_ni*a.m_e2_e3_ni*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_ni*b.m_e3-a.m_e3*a.m_e3*b.m_e2-a.m_ni*a.m_ni*b.m_e2-a.m_no*a.m_no*b.m_e2+2.0*a.m_no*a.m_no_e1_e2*b.m_e1+-2.0*a.m_no*a.m_no_e2_e3*b.m_e3+a.m_no_e1_e2*a.m_no_e1_e2*b.m_e2+2.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e3+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1-a.m_no_e1_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e1-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e2_e3*a.m_no_e2_e3*b.m_e2+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2))/(_n2_), // e2
			((-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1-a.m_e1_e2_ni*a.m_e1_e2_ni*b.m_e3+2.0*a.m_e1_e2_ni*a.m_e1_e3_ni*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_e2_e3_ni*b.m_e1+a.m_e1_e3_ni*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_ni*b.m_e1-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e3*b.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_ni*b.m_e2+a.m_e3*a.m_e3*b.m_e3-a.m_ni*a.m_ni*b.m_e3-a.m_no*a.m_no*b.m_e3+2.0*a.m_no*a.m_no_e1_e3*b.m_e1+2.0*a.m_no*a.m_no_e2_e3*b.m_e2-a.m_no_e1_e2*a.m_no_e1_e2*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e1+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e1_e3*a.m_no_e1_e3*b.m_e3-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e2_e3*a.m_no_e2_e3*b.m_e3-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3))/(_n2_), // e3
			((2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+2.0*a.m_e3*a.m_ni*b.m_e3+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final oddVersor a, final normalizedPoint b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((-a.m_e1*a.m_e1+2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+-2.0*a.m_e1*a.m_no_e1_ni-a.m_e1_e2_e3*a.m_e1_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1-a.m_e2*a.m_e2+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e2*a.m_no_e2_ni-a.m_e3*a.m_e3+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e3_ni+-2.0*a.m_no*a.m_no*b.m_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1-a.m_no_e1_ni*a.m_no_e1_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni))/(_n2_), // no
			((a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+-2.0*a.m_e1*a.m_ni+-2.0*a.m_e1*a.m_no*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e2+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni+-2.0*a.m_e1_e3_ni*a.m_e3+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni-a.m_e2*a.m_e2*b.m_e1+-2.0*a.m_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1-a.m_e3*a.m_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e1+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e1_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1))/(_n2_), // e1
			((-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e1_e2_ni+2.0*a.m_e1*a.m_e2*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+-2.0*a.m_e2*a.m_ni+-2.0*a.m_e2*a.m_no*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e3+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni-a.m_e3*a.m_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e2+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e2_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2))/(_n2_), // e2
			((-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_e1+2.0*a.m_e1*a.m_no_e1_e3*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_ni-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e2_e3_ni+2.0*a.m_e2*a.m_e3*b.m_e2+2.0*a.m_e2*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_ni+a.m_e3*a.m_e3*b.m_e3+-2.0*a.m_e3*a.m_ni+-2.0*a.m_e3*a.m_no*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e3+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3))/(_n2_), // e3
			((-a.m_e1*a.m_e1*b.m_ni+2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1*a.m_no_e1_ni*b.m_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e1_e2_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1-a.m_e2*a.m_e2*b.m_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+2.0*a.m_e2*a.m_no_e2_ni*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2-a.m_e3*a.m_e3*b.m_ni+2.0*a.m_e3*a.m_ni*b.m_e3+2.0*a.m_e3*a.m_no_e3_ni*b.m_ni+-2.0*a.m_ni*a.m_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static dualSphere applyVersor(final oddVersor a, final dualSphere b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			((-a.m_e1*a.m_e1*b.m_no+2.0*a.m_e1*a.m_no*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3+-2.0*a.m_e1*a.m_no_e1_ni*b.m_no-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1-a.m_e2*a.m_e2*b.m_no+2.0*a.m_e2*a.m_no*b.m_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1+2.0*a.m_e2*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e2*a.m_no_e2_ni*b.m_no-a.m_e3*a.m_e3*b.m_no+2.0*a.m_e3*a.m_no*b.m_e3+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e3_ni*b.m_no+-2.0*a.m_no*a.m_no*b.m_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1+2.0*a.m_no*a.m_no_e2_ni*b.m_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1-a.m_no_e1_ni*a.m_no_e1_ni*b.m_no+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2-a.m_no_e2_ni*a.m_no_e2_ni*b.m_no-a.m_no_e3_ni*a.m_no_e3_ni*b.m_no))/(_n2_), // no
			((a.m_e1*a.m_e1*b.m_e1+2.0*a.m_e1*a.m_e2*b.m_e2+2.0*a.m_e1*a.m_e3*b.m_e3+-2.0*a.m_e1*a.m_ni*b.m_no+-2.0*a.m_e1*a.m_no*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_no+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_no+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_no-a.m_e2*a.m_e2*b.m_e1+-2.0*a.m_e2*a.m_no_e1_e2*b.m_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1-a.m_e3*a.m_e3*b.m_e1+-2.0*a.m_e3*a.m_no_e1_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e1+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e1_ni*b.m_no+2.0*a.m_no*a.m_no_e1_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1))/(_n2_), // e1
			((-a.m_e1*a.m_e1*b.m_e2+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3+2.0*a.m_e1*a.m_e1_e2_ni*b.m_no+2.0*a.m_e1*a.m_e2*b.m_e1+2.0*a.m_e1*a.m_no_e1_e2*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_no+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1+a.m_e2*a.m_e2*b.m_e2+2.0*a.m_e2*a.m_e3*b.m_e3+-2.0*a.m_e2*a.m_ni*b.m_no+-2.0*a.m_e2*a.m_no*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_no+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_no-a.m_e3*a.m_e3*b.m_e2+-2.0*a.m_e3*a.m_no_e2_e3*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e2+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3+-2.0*a.m_ni*a.m_no_e2_ni*b.m_no+2.0*a.m_no*a.m_no_e2_ni*b.m_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2))/(_n2_), // e2
			((-a.m_e1*a.m_e1*b.m_e3+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_no+2.0*a.m_e1*a.m_e3*b.m_e1+2.0*a.m_e1*a.m_no_e1_e3*b.m_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_no+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_no-a.m_e2*a.m_e2*b.m_e3+2.0*a.m_e2*a.m_e2_e3_ni*b.m_no+2.0*a.m_e2*a.m_e3*b.m_e2+2.0*a.m_e2*a.m_no_e2_e3*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_no+a.m_e3*a.m_e3*b.m_e3+-2.0*a.m_e3*a.m_ni*b.m_no+-2.0*a.m_e3*a.m_no*b.m_ni+2.0*a.m_ni*a.m_no*b.m_e3+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_no+2.0*a.m_no*a.m_no_e3_ni*b.m_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3))/(_n2_), // e3
			((-a.m_e1*a.m_e1*b.m_ni+2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3+2.0*a.m_e1*a.m_ni*b.m_e1+2.0*a.m_e1*a.m_no_e1_ni*b.m_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_ni+-2.0*a.m_e1_e2_ni*a.m_e1_e2_ni*b.m_no+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1+-2.0*a.m_e1_e3_ni*a.m_e1_e3_ni*b.m_no+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1-a.m_e2*a.m_e2*b.m_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3+2.0*a.m_e2*a.m_ni*b.m_e2+2.0*a.m_e2*a.m_no_e2_ni*b.m_ni+-2.0*a.m_e2_e3_ni*a.m_e2_e3_ni*b.m_no+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2-a.m_e3*a.m_e3*b.m_ni+2.0*a.m_e3*a.m_ni*b.m_e3+2.0*a.m_e3*a.m_no_e3_ni*b.m_ni+-2.0*a.m_ni*a.m_ni*b.m_no+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_ni))/(_n2_) // ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static pointPair applyVersor(final oddVersor a, final bivectorE3GA b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			((-2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_e2*a.m_no*b.m_e1_e2+-2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e3*a.m_no*b.m_e3_e1+2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_no*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_e3+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2))/(_n2_), // no_e1
			((-2.0*a.m_e1*a.m_no*b.m_e1_e2+2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_e3*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_no*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e2*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e3*a.m_no*b.m_e2_e3+2.0*a.m_e3*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_e3+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_e3))/(_n2_), // no_e2
			((2.0*a.m_e1*a.m_no*b.m_e3_e1+-2.0*a.m_e1*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e2*a.m_no*b.m_e2_e3+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e2+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_no*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_no*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2_e3))/(_n2_), // no_e3
			((-a.m_e1*a.m_e1*b.m_e1_e2+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3_e1+2.0*a.m_e1*a.m_e3*b.m_e2_e3+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1_e2-a.m_e2*a.m_e2*b.m_e1_e2+2.0*a.m_e2*a.m_e3*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2+a.m_e3*a.m_e3*b.m_e1_e2+-2.0*a.m_ni*a.m_no*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3_e1-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3_e1+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3_e1-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2))/(_n2_), // e1_e2
			((a.m_e1*a.m_e1*b.m_e2_e3+2.0*a.m_e1*a.m_e2*b.m_e3_e1+2.0*a.m_e1*a.m_e3*b.m_e1_e2+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e3_e1-a.m_e2*a.m_e2*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2_e3-a.m_e3*a.m_e3*b.m_e2_e3+-2.0*a.m_ni*a.m_no*b.m_e2_e3+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1_e2-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e3_e1-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_e2+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_e3))/(_n2_), // e2_e3
			(-(a.m_e1*a.m_e1*b.m_e3_e1+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e2+-2.0*a.m_e1*a.m_e2*b.m_e2_e3-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2_e3-a.m_e2*a.m_e2*b.m_e3_e1+-2.0*a.m_e2*a.m_e3*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3_e1+a.m_e3*a.m_e3*b.m_e3_e1+2.0*a.m_ni*a.m_no*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_e3*b.m_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3_e1+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e2-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3_e1))/(_n2_), // e3_e1
			((2.0*a.m_e1*a.m_e1_e2_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e3_e1+2.0*a.m_e1*a.m_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e2_e3+2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_e2*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e2_e3+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2*a.m_ni*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_e3*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e3*a.m_ni*b.m_e3_e1+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_ni*a.m_no_e3_ni*b.m_e3_e1))/(_n2_), // e1_ni
			((-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e2_e3+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e3_e1+2.0*a.m_e1*a.m_ni*b.m_e1_e2+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e2_e3+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_e3*a.m_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_ni*a.m_no_e3_ni*b.m_e2_e3))/(_n2_), // e2_ni
			((2.0*a.m_e1*a.m_e1_e2_ni*b.m_e2_e3+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_ni*b.m_e3_e1+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e2_e3+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e3_e1+2.0*a.m_e1_e2_ni*a.m_e3*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_e2*b.m_e1_e2+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3+-2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e1_e2+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e3_e1+2.0*a.m_e2*a.m_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_e3*b.m_e2_e3+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e2_e3))/(_n2_), // e3_ni
			((2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e2_e3+2.0*a.m_e1*a.m_no_e2_ni*b.m_e1_e2+-2.0*a.m_e1*a.m_no_e3_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e2_e3+-2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e3_e1+-2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e1_e2+2.0*a.m_e1_e2_ni*a.m_no*b.m_e1_e2+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3_e1+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e3_e1+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2_e3+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1_e2+2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e3_e1+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e1_e2+2.0*a.m_e2*a.m_no_e3_ni*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no*b.m_e2_e3+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3_e1+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2+2.0*a.m_e3*a.m_no_e1_ni*b.m_e3_e1+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e2_e3+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1_e2+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3_e1+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2_e3))/(_n2_) // no_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static circle applyVersor(final oddVersor a, final line b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			((-2.0*a.m_e1*a.m_no*b.m_e2_no_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_no_ni+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e2*a.m_no*b.m_e1_no_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_no*a.m_no*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+4.0*a.m_no*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e2_no_ni+-4.0*a.m_no*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e2_ni+-4.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_no_ni))/(_n2_), // no_e1_e2
			((-2.0*a.m_e1*a.m_no*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_no_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_no_ni+2.0*a.m_e3*a.m_no*b.m_e1_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no*b.m_e1_e3_ni+-4.0*a.m_no*a.m_no_e1_e2*b.m_e2_e3_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e3_no_ni+4.0*a.m_no*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_no_ni+-4.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_no_ni))/(_n2_), // no_e1_e3
			((2.0*a.m_e1*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e3*a.m_no*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no*b.m_e2_e3_ni+4.0*a.m_no*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-4.0*a.m_no*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e3_no_ni+-4.0*a.m_no_e1_e2*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_no_ni+-4.0*a.m_no_e1_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e3_no_ni))/(_n2_), // no_e2_e3
			((-2.0*a.m_e1*a.m_no*b.m_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_e2*a.m_no*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2*a.m_no_e1_ni*b.m_e3_no_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_e1_no_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e3_ni))/(_n2_), // e1_e2_e3
			((-a.m_e1*a.m_e1*b.m_e1_no_ni+-2.0*a.m_e1*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+a.m_e2*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e2*a.m_no*b.m_e1_e2_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e1_no_ni+a.m_e3*a.m_e3*b.m_e1_no_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_no_e1_e2*b.m_e2_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_ni*a.m_no*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e1_e3*b.m_e3_no_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e3_no_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_no_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni))/(_n2_), // no_e1_ni
			((a.m_e1*a.m_e1*b.m_e2_no_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e2*b.m_e1_no_ni+2.0*a.m_e1*a.m_no*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e1_e3*b.m_e2_e3_ni+2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_e1_no_ni-a.m_e2*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e2*a.m_e3*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e2_no_ni+a.m_e3*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e3*a.m_no*b.m_e2_e3_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2_ni+2.0*a.m_ni*a.m_no*b.m_e2_no_ni+-2.0*a.m_ni*a.m_no_e1_e2*b.m_e1_no_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_e3_no_ni+-2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e3_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e3_no_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni))/(_n2_), // no_e2_ni
			((a.m_e1*a.m_e1*b.m_e1_e2_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_e1_no_ni+2.0*a.m_e1*a.m_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e2_e3_ni+2.0*a.m_e1*a.m_ni*b.m_e2_no_ni+-2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_e3*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e2_no_ni+a.m_e2*a.m_e2*b.m_e1_e2_ni+2.0*a.m_e2*a.m_e3*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_ni*b.m_e1_no_ni+-2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e1_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e1_no_ni-a.m_e3*a.m_e3*b.m_e1_e2_ni+2.0*a.m_e3*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e2_ni+-2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_ni*a.m_no_e2_ni*b.m_e1_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2_ni))/(_n2_), // e1_e2_ni
			((a.m_e1*a.m_e1*b.m_e3_no_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e2_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e1_no_ni+2.0*a.m_e1*a.m_no*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e1_e2*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_no_e2_e3*b.m_e1_e2_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_no*b.m_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_e3_no_ni+a.m_e2*a.m_e2*b.m_e3_no_ni+-2.0*a.m_e2*a.m_e3*b.m_e2_no_ni+2.0*a.m_e2*a.m_no*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e3_ni+2.0*a.m_e2*a.m_no_e1_e3*b.m_e1_e2_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_e1_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_e3_no_ni-a.m_e3*a.m_e3*b.m_e3_no_ni+2.0*a.m_e3*a.m_no_e1_e2*b.m_e1_e2_ni+2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_e3_no_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_e1_no_ni+-2.0*a.m_ni*a.m_no_e2_e3*b.m_e2_no_ni+2.0*a.m_no*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+-2.0*a.m_no_e1_e3*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_no_e1_ni*a.m_no_e2_e3*b.m_e1_e2_ni+2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e2_no_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni))/(_n2_), // no_e3_ni
			((a.m_e1*a.m_e1*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_e1_e3_ni*b.m_e1_no_ni+2.0*a.m_e1*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1*a.m_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_e2*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_e3*b.m_e2_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e2_no_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni-a.m_e2*a.m_e2*b.m_e1_e3_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_e1_no_ni+2.0*a.m_e2*a.m_e3*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_no_e3_ni*b.m_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+a.m_e3*a.m_e3*b.m_e1_e3_ni+-2.0*a.m_e3*a.m_ni*b.m_e1_no_ni+-2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_e3_no_ni+2.0*a.m_ni*a.m_no_e3_ni*b.m_e1_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e3_ni))/(_n2_), // e1_e3_ni
			((-a.m_e1*a.m_e1*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_e3_no_ni+2.0*a.m_e1*a.m_e1_e3_ni*b.m_e2_no_ni+2.0*a.m_e1*a.m_e2*b.m_e1_e3_ni+-2.0*a.m_e1*a.m_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1*a.m_e3*b.m_e1_e2_ni+2.0*a.m_e1*a.m_no_e1_ni*b.m_e2_e3_ni+-2.0*a.m_e1*a.m_no_e2_ni*b.m_e1_e3_ni+2.0*a.m_e1*a.m_no_e3_ni*b.m_e1_e2_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_ni*b.m_e1_no_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_ni*b.m_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e3_ni*b.m_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_e3*b.m_e1_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_e3_no_ni+-2.0*a.m_e1_e2_ni*a.m_no_e3_ni*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_e2*b.m_e1_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e3_no_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_e2_no_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_ni*b.m_e1_no_ni+a.m_e2*a.m_e2*b.m_e2_e3_ni+-2.0*a.m_e2*a.m_e2_e3_ni*b.m_e2_no_ni+2.0*a.m_e2*a.m_ni*b.m_e3_no_ni+2.0*a.m_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_ni+-2.0*a.m_e2*a.m_no_e1_ni*b.m_e1_e3_ni+-2.0*a.m_e2*a.m_no_e2_ni*b.m_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_e3*b.m_e3_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_ni*b.m_e1_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_e2_no_ni+2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_e3_no_ni+a.m_e3*a.m_e3*b.m_e2_e3_ni+-2.0*a.m_e3*a.m_ni*b.m_e2_no_ni+2.0*a.m_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e3_ni+2.0*a.m_e3*a.m_no_e1_ni*b.m_e1_e2_ni+-2.0*a.m_e3*a.m_no_e3_ni*b.m_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_e2_e3_ni*b.m_e1_no_ni+-2.0*a.m_ni*a.m_no_e2_ni*b.m_e3_no_ni+2.0*a.m_ni*a.m_no_e3_ni*b.m_e2_no_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_e1_e2_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_e1_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_e1_e3_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_e1_e2_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_e2_e3_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_e2_e3_ni))/(_n2_) // e2_e3_ni
		);
}
/**
 * Returns a * b * inverse(a) using default metric.
 */
public final static sphere applyVersor(final oddVersor a, final plane b)
{
	double _n2_ = (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			((-a.m_e1*a.m_e1*b.m_e1_e2_e3_ni+-2.0*a.m_e1*a.m_e1_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e1_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1*a.m_ni*b.m_no_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e1_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_e1_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni-a.m_e2*a.m_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2*a.m_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2*a.m_ni*b.m_no_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_e3*b.m_no_e1_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_e2_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni-a.m_e3*a.m_e3*b.m_e1_e2_e3_ni+2.0*a.m_e3*a.m_ni*b.m_no_e1_e2_ni+2.0*a.m_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_ni*b.m_no_e2_e3_ni+2.0*a.m_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_e1_e2_e3_ni))/(_n2_), // e1_e2_e3_ni
			((a.m_e1*a.m_e1*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_e2*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_no*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_e3*a.m_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni-a.m_e2*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni-a.m_e3*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni))/(_n2_), // no_e2_e3_ni
			((-a.m_e1*a.m_e1*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_e1_e2_e3*a.m_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+a.m_e2*a.m_e2*b.m_no_e1_e3_ni+-2.0*a.m_e2*a.m_e3*b.m_no_e1_e2_ni+2.0*a.m_e2*a.m_no*b.m_e1_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no*b.m_no_e1_e2_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni-a.m_e3*a.m_e3*b.m_no_e1_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e1_e3_ni+2.0*a.m_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+-2.0*a.m_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_e1_e2_e3_ni+-2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni))/(_n2_), // no_e1_e3_ni
			((-a.m_e1*a.m_e1*b.m_no_e1_e2_ni+-2.0*a.m_e1*a.m_e1_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_e3*b.m_no_e2_e3_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_e1_e2_e3_ni+a.m_e1_e2_e3*a.m_e1_e2_e3*b.m_no_e1_e2_ni+-2.0*a.m_e1_e2_e3*a.m_e2*b.m_no_e2_e3_ni+-2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no*b.m_no_e2_e3_ni+2.0*a.m_e1_e3_ni*a.m_no_e1_e2*b.m_no_e1_e3_ni+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3*b.m_no_e1_e2_ni-a.m_e2*a.m_e2*b.m_no_e1_e2_ni+-2.0*a.m_e2*a.m_e3*b.m_no_e1_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_e2_e3_ni*a.m_no*b.m_no_e1_e3_ni+2.0*a.m_e2_e3_ni*a.m_no_e1_e2*b.m_no_e2_e3_ni+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3*b.m_no_e1_e2_ni+a.m_e3*a.m_e3*b.m_no_e1_e2_ni+-2.0*a.m_e3*a.m_no*b.m_e1_e2_e3_ni+2.0*a.m_ni*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_ni*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_ni*a.m_no_e2_e3*b.m_no_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_ni*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_e1_e2_e3_ni+a.m_no_e1_ni*a.m_no_e1_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_ni*a.m_no_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_e1_e2_e3_ni+a.m_no_e2_ni*a.m_no_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2_ni*a.m_no_e3_ni*b.m_no_e1_e3_ni-a.m_no_e3_ni*a.m_no_e3_ni*b.m_no_e1_e2_ni))/(_n2_), // no_e1_e2_ni
			((2.0*a.m_e1*a.m_no*b.m_no_e2_e3_ni+-2.0*a.m_e1*a.m_no_e1_e2*b.m_no_e1_e3_ni+2.0*a.m_e1*a.m_no_e1_e3*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e2*b.m_no_e1_e2_ni+2.0*a.m_e1_e2_e3*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_e1_e2_e3*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_e2*a.m_no*b.m_no_e1_e3_ni+-2.0*a.m_e2*a.m_no_e1_e2*b.m_no_e2_e3_ni+2.0*a.m_e2*a.m_no_e2_e3*b.m_no_e1_e2_ni+2.0*a.m_e3*a.m_no*b.m_no_e1_e2_ni+-2.0*a.m_e3*a.m_no_e1_e3*b.m_no_e2_e3_ni+2.0*a.m_e3*a.m_no_e2_e3*b.m_no_e1_e3_ni+-2.0*a.m_no*a.m_no*b.m_e1_e2_e3_ni+2.0*a.m_no*a.m_no_e1_ni*b.m_no_e2_e3_ni+-2.0*a.m_no*a.m_no_e2_ni*b.m_no_e1_e3_ni+2.0*a.m_no*a.m_no_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_e2*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e2*a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e2*a.m_no_e1_ni*b.m_no_e1_e3_ni+-2.0*a.m_no_e1_e2*a.m_no_e2_ni*b.m_no_e2_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e1_e3*b.m_no_e1_e3_ni+2.0*a.m_no_e1_e2_e3_ni*a.m_no_e2_e3*b.m_no_e2_e3_ni+-2.0*a.m_no_e1_e3*a.m_no_e1_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e1_e3*a.m_no_e1_ni*b.m_no_e1_e2_ni+-2.0*a.m_no_e1_e3*a.m_no_e3_ni*b.m_no_e2_e3_ni+-2.0*a.m_no_e2_e3*a.m_no_e2_e3*b.m_e1_e2_e3_ni+2.0*a.m_no_e2_e3*a.m_no_e2_ni*b.m_no_e1_e2_ni+2.0*a.m_no_e2_e3*a.m_no_e3_ni*b.m_no_e1_e3_ni))/(_n2_) // no_e1_e2_e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static vectorE3GA applyVersorWI(final rotorE3GA a, final vectorE3GA b, final rotorE3GA c)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_e1_e2*b.m_e1*c.m_e1_e2+a.m_e1_e2*b.m_e2*c.m_scalar-a.m_e1_e2*b.m_e3*c.m_e2_e3-a.m_e2_e3*b.m_e1*c.m_e2_e3-a.m_e2_e3*b.m_e2*c.m_e3_e1-a.m_e2_e3*b.m_e3*c.m_e1_e2+a.m_e3_e1*b.m_e1*c.m_e3_e1-a.m_e3_e1*b.m_e2*c.m_e2_e3-a.m_e3_e1*b.m_e3*c.m_scalar+a.m_scalar*b.m_e1*c.m_scalar-a.m_scalar*b.m_e2*c.m_e1_e2+a.m_scalar*b.m_e3*c.m_e3_e1), // e1
			(-a.m_e1_e2*b.m_e1*c.m_scalar+a.m_e1_e2*b.m_e2*c.m_e1_e2-a.m_e1_e2*b.m_e3*c.m_e3_e1-a.m_e2_e3*b.m_e1*c.m_e3_e1+a.m_e2_e3*b.m_e2*c.m_e2_e3+a.m_e2_e3*b.m_e3*c.m_scalar-a.m_e3_e1*b.m_e1*c.m_e2_e3-a.m_e3_e1*b.m_e2*c.m_e3_e1-a.m_e3_e1*b.m_e3*c.m_e1_e2+a.m_scalar*b.m_e1*c.m_e1_e2+a.m_scalar*b.m_e2*c.m_scalar-a.m_scalar*b.m_e3*c.m_e2_e3), // e2
			(-a.m_e1_e2*b.m_e1*c.m_e2_e3-a.m_e1_e2*b.m_e2*c.m_e3_e1-a.m_e1_e2*b.m_e3*c.m_e1_e2-a.m_e2_e3*b.m_e1*c.m_e1_e2-a.m_e2_e3*b.m_e2*c.m_scalar+a.m_e2_e3*b.m_e3*c.m_e2_e3+a.m_e3_e1*b.m_e1*c.m_scalar-a.m_e3_e1*b.m_e2*c.m_e1_e2+a.m_e3_e1*b.m_e3*c.m_e3_e1-a.m_scalar*b.m_e1*c.m_e3_e1+a.m_scalar*b.m_e2*c.m_e2_e3+a.m_scalar*b.m_e3*c.m_scalar) // e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyVersorWI(final rotorE3GA a, final normalizedPoint b, final rotorE3GA c)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(-a.m_e1_e2*c.m_e1_e2-a.m_e2_e3*c.m_e2_e3-a.m_e3_e1*c.m_e3_e1+a.m_scalar*c.m_scalar), // no
			(a.m_e1_e2*b.m_e1*c.m_e1_e2+a.m_e1_e2*b.m_e2*c.m_scalar-a.m_e1_e2*b.m_e3*c.m_e2_e3-a.m_e2_e3*b.m_e1*c.m_e2_e3-a.m_e2_e3*b.m_e2*c.m_e3_e1-a.m_e2_e3*b.m_e3*c.m_e1_e2+a.m_e3_e1*b.m_e1*c.m_e3_e1-a.m_e3_e1*b.m_e2*c.m_e2_e3-a.m_e3_e1*b.m_e3*c.m_scalar+a.m_scalar*b.m_e1*c.m_scalar-a.m_scalar*b.m_e2*c.m_e1_e2+a.m_scalar*b.m_e3*c.m_e3_e1), // e1
			(-a.m_e1_e2*b.m_e1*c.m_scalar+a.m_e1_e2*b.m_e2*c.m_e1_e2-a.m_e1_e2*b.m_e3*c.m_e3_e1-a.m_e2_e3*b.m_e1*c.m_e3_e1+a.m_e2_e3*b.m_e2*c.m_e2_e3+a.m_e2_e3*b.m_e3*c.m_scalar-a.m_e3_e1*b.m_e1*c.m_e2_e3-a.m_e3_e1*b.m_e2*c.m_e3_e1-a.m_e3_e1*b.m_e3*c.m_e1_e2+a.m_scalar*b.m_e1*c.m_e1_e2+a.m_scalar*b.m_e2*c.m_scalar-a.m_scalar*b.m_e3*c.m_e2_e3), // e2
			(-a.m_e1_e2*b.m_e1*c.m_e2_e3-a.m_e1_e2*b.m_e2*c.m_e3_e1-a.m_e1_e2*b.m_e3*c.m_e1_e2-a.m_e2_e3*b.m_e1*c.m_e1_e2-a.m_e2_e3*b.m_e2*c.m_scalar+a.m_e2_e3*b.m_e3*c.m_e2_e3+a.m_e3_e1*b.m_e1*c.m_scalar-a.m_e3_e1*b.m_e2*c.m_e1_e2+a.m_e3_e1*b.m_e3*c.m_e3_e1-a.m_scalar*b.m_e1*c.m_e3_e1+a.m_scalar*b.m_e2*c.m_e2_e3+a.m_scalar*b.m_e3*c.m_scalar), // e3
			(-a.m_e1_e2*b.m_ni*c.m_e1_e2-a.m_e2_e3*b.m_ni*c.m_e2_e3-a.m_e3_e1*b.m_ni*c.m_e3_e1+a.m_scalar*b.m_ni*c.m_scalar) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static dualSphere applyVersorWI(final rotorE3GA a, final dualSphere b, final rotorE3GA c)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			(-a.m_e1_e2*b.m_no*c.m_e1_e2-a.m_e2_e3*b.m_no*c.m_e2_e3-a.m_e3_e1*b.m_no*c.m_e3_e1+a.m_scalar*b.m_no*c.m_scalar), // no
			(a.m_e1_e2*b.m_e1*c.m_e1_e2+a.m_e1_e2*b.m_e2*c.m_scalar-a.m_e1_e2*b.m_e3*c.m_e2_e3-a.m_e2_e3*b.m_e1*c.m_e2_e3-a.m_e2_e3*b.m_e2*c.m_e3_e1-a.m_e2_e3*b.m_e3*c.m_e1_e2+a.m_e3_e1*b.m_e1*c.m_e3_e1-a.m_e3_e1*b.m_e2*c.m_e2_e3-a.m_e3_e1*b.m_e3*c.m_scalar+a.m_scalar*b.m_e1*c.m_scalar-a.m_scalar*b.m_e2*c.m_e1_e2+a.m_scalar*b.m_e3*c.m_e3_e1), // e1
			(-a.m_e1_e2*b.m_e1*c.m_scalar+a.m_e1_e2*b.m_e2*c.m_e1_e2-a.m_e1_e2*b.m_e3*c.m_e3_e1-a.m_e2_e3*b.m_e1*c.m_e3_e1+a.m_e2_e3*b.m_e2*c.m_e2_e3+a.m_e2_e3*b.m_e3*c.m_scalar-a.m_e3_e1*b.m_e1*c.m_e2_e3-a.m_e3_e1*b.m_e2*c.m_e3_e1-a.m_e3_e1*b.m_e3*c.m_e1_e2+a.m_scalar*b.m_e1*c.m_e1_e2+a.m_scalar*b.m_e2*c.m_scalar-a.m_scalar*b.m_e3*c.m_e2_e3), // e2
			(-a.m_e1_e2*b.m_e1*c.m_e2_e3-a.m_e1_e2*b.m_e2*c.m_e3_e1-a.m_e1_e2*b.m_e3*c.m_e1_e2-a.m_e2_e3*b.m_e1*c.m_e1_e2-a.m_e2_e3*b.m_e2*c.m_scalar+a.m_e2_e3*b.m_e3*c.m_e2_e3+a.m_e3_e1*b.m_e1*c.m_scalar-a.m_e3_e1*b.m_e2*c.m_e1_e2+a.m_e3_e1*b.m_e3*c.m_e3_e1-a.m_scalar*b.m_e1*c.m_e3_e1+a.m_scalar*b.m_e2*c.m_e2_e3+a.m_scalar*b.m_e3*c.m_scalar), // e3
			(-a.m_e1_e2*b.m_ni*c.m_e1_e2-a.m_e2_e3*b.m_ni*c.m_e2_e3-a.m_e3_e1*b.m_ni*c.m_e3_e1+a.m_scalar*b.m_ni*c.m_scalar) // ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static bivectorE3GA applyVersorWI(final rotorE3GA a, final bivectorE3GA b, final rotorE3GA c)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2*c.m_e1_e2-a.m_e1_e2*b.m_e2_e3*c.m_e2_e3-a.m_e1_e2*b.m_e3_e1*c.m_e3_e1+a.m_e2_e3*b.m_e1_e2*c.m_e2_e3-a.m_e2_e3*b.m_e2_e3*c.m_e1_e2-a.m_e2_e3*b.m_e3_e1*c.m_scalar+a.m_e3_e1*b.m_e1_e2*c.m_e3_e1+a.m_e3_e1*b.m_e2_e3*c.m_scalar-a.m_e3_e1*b.m_e3_e1*c.m_e1_e2+a.m_scalar*b.m_e1_e2*c.m_scalar-a.m_scalar*b.m_e2_e3*c.m_e3_e1+a.m_scalar*b.m_e3_e1*c.m_e2_e3), // e1_e2
			(-a.m_e1_e2*b.m_e1_e2*c.m_e2_e3+a.m_e1_e2*b.m_e2_e3*c.m_e1_e2+a.m_e1_e2*b.m_e3_e1*c.m_scalar-a.m_e2_e3*b.m_e1_e2*c.m_e1_e2-a.m_e2_e3*b.m_e2_e3*c.m_e2_e3-a.m_e2_e3*b.m_e3_e1*c.m_e3_e1-a.m_e3_e1*b.m_e1_e2*c.m_scalar+a.m_e3_e1*b.m_e2_e3*c.m_e3_e1-a.m_e3_e1*b.m_e3_e1*c.m_e2_e3+a.m_scalar*b.m_e1_e2*c.m_e3_e1+a.m_scalar*b.m_e2_e3*c.m_scalar-a.m_scalar*b.m_e3_e1*c.m_e1_e2), // e2_e3
			-(a.m_e1_e2*b.m_e1_e2*c.m_e3_e1+a.m_e1_e2*b.m_e2_e3*c.m_scalar-a.m_e1_e2*b.m_e3_e1*c.m_e1_e2-a.m_e2_e3*b.m_e1_e2*c.m_scalar+a.m_e2_e3*b.m_e2_e3*c.m_e3_e1-a.m_e2_e3*b.m_e3_e1*c.m_e2_e3+a.m_e3_e1*b.m_e1_e2*c.m_e1_e2+a.m_e3_e1*b.m_e2_e3*c.m_e2_e3+a.m_e3_e1*b.m_e3_e1*c.m_e3_e1+a.m_scalar*b.m_e1_e2*c.m_e2_e3-a.m_scalar*b.m_e2_e3*c.m_e1_e2-a.m_scalar*b.m_e3_e1*c.m_scalar) // e3_e1
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static line applyVersorWI(final rotorE3GA a, final line b, final rotorE3GA c)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			(-a.m_e1_e2*b.m_e1_e2_ni*c.m_e1_e2+a.m_e1_e2*b.m_e1_e3_ni*c.m_e3_e1-a.m_e1_e2*b.m_e2_e3_ni*c.m_e2_e3+a.m_e2_e3*b.m_e1_e2_ni*c.m_e2_e3+a.m_e2_e3*b.m_e1_e3_ni*c.m_scalar-a.m_e2_e3*b.m_e2_e3_ni*c.m_e1_e2+a.m_e3_e1*b.m_e1_e2_ni*c.m_e3_e1+a.m_e3_e1*b.m_e1_e3_ni*c.m_e1_e2+a.m_e3_e1*b.m_e2_e3_ni*c.m_scalar+a.m_scalar*b.m_e1_e2_ni*c.m_scalar-a.m_scalar*b.m_e1_e3_ni*c.m_e2_e3-a.m_scalar*b.m_e2_e3_ni*c.m_e3_e1), // e1_e2_ni
			(a.m_e1_e2*b.m_e1_e2_ni*c.m_e3_e1+a.m_e1_e2*b.m_e1_e3_ni*c.m_e1_e2+a.m_e1_e2*b.m_e2_e3_ni*c.m_scalar-a.m_e2_e3*b.m_e1_e2_ni*c.m_scalar+a.m_e2_e3*b.m_e1_e3_ni*c.m_e2_e3+a.m_e2_e3*b.m_e2_e3_ni*c.m_e3_e1+a.m_e3_e1*b.m_e1_e2_ni*c.m_e1_e2-a.m_e3_e1*b.m_e1_e3_ni*c.m_e3_e1+a.m_e3_e1*b.m_e2_e3_ni*c.m_e2_e3+a.m_scalar*b.m_e1_e2_ni*c.m_e2_e3+a.m_scalar*b.m_e1_e3_ni*c.m_scalar-a.m_scalar*b.m_e2_e3_ni*c.m_e1_e2), // e1_e3_ni
			(-a.m_e1_e2*b.m_e1_e2_ni*c.m_e2_e3-a.m_e1_e2*b.m_e1_e3_ni*c.m_scalar+a.m_e1_e2*b.m_e2_e3_ni*c.m_e1_e2-a.m_e2_e3*b.m_e1_e2_ni*c.m_e1_e2+a.m_e2_e3*b.m_e1_e3_ni*c.m_e3_e1-a.m_e2_e3*b.m_e2_e3_ni*c.m_e2_e3-a.m_e3_e1*b.m_e1_e2_ni*c.m_scalar+a.m_e3_e1*b.m_e1_e3_ni*c.m_e2_e3+a.m_e3_e1*b.m_e2_e3_ni*c.m_e3_e1+a.m_scalar*b.m_e1_e2_ni*c.m_e3_e1+a.m_scalar*b.m_e1_e3_ni*c.m_e1_e2+a.m_scalar*b.m_e2_e3_ni*c.m_scalar), // e2_e3_ni
			-(-a.m_e1_e2*b.m_e1_no_ni*c.m_e1_e2-a.m_e1_e2*b.m_e2_no_ni*c.m_scalar+a.m_e1_e2*b.m_e3_no_ni*c.m_e2_e3+a.m_e2_e3*b.m_e1_no_ni*c.m_e2_e3+a.m_e2_e3*b.m_e2_no_ni*c.m_e3_e1+a.m_e2_e3*b.m_e3_no_ni*c.m_e1_e2-a.m_e3_e1*b.m_e1_no_ni*c.m_e3_e1+a.m_e3_e1*b.m_e2_no_ni*c.m_e2_e3+a.m_e3_e1*b.m_e3_no_ni*c.m_scalar-a.m_scalar*b.m_e1_no_ni*c.m_scalar+a.m_scalar*b.m_e2_no_ni*c.m_e1_e2-a.m_scalar*b.m_e3_no_ni*c.m_e3_e1), // e1_no_ni
			-(a.m_e1_e2*b.m_e1_no_ni*c.m_scalar-a.m_e1_e2*b.m_e2_no_ni*c.m_e1_e2+a.m_e1_e2*b.m_e3_no_ni*c.m_e3_e1+a.m_e2_e3*b.m_e1_no_ni*c.m_e3_e1-a.m_e2_e3*b.m_e2_no_ni*c.m_e2_e3-a.m_e2_e3*b.m_e3_no_ni*c.m_scalar+a.m_e3_e1*b.m_e1_no_ni*c.m_e2_e3+a.m_e3_e1*b.m_e2_no_ni*c.m_e3_e1+a.m_e3_e1*b.m_e3_no_ni*c.m_e1_e2-a.m_scalar*b.m_e1_no_ni*c.m_e1_e2-a.m_scalar*b.m_e2_no_ni*c.m_scalar+a.m_scalar*b.m_e3_no_ni*c.m_e2_e3), // e2_no_ni
			-(a.m_e1_e2*b.m_e1_no_ni*c.m_e2_e3+a.m_e1_e2*b.m_e2_no_ni*c.m_e3_e1+a.m_e1_e2*b.m_e3_no_ni*c.m_e1_e2+a.m_e2_e3*b.m_e1_no_ni*c.m_e1_e2+a.m_e2_e3*b.m_e2_no_ni*c.m_scalar-a.m_e2_e3*b.m_e3_no_ni*c.m_e2_e3-a.m_e3_e1*b.m_e1_no_ni*c.m_scalar+a.m_e3_e1*b.m_e2_no_ni*c.m_e1_e2-a.m_e3_e1*b.m_e3_no_ni*c.m_e3_e1+a.m_scalar*b.m_e1_no_ni*c.m_e3_e1-a.m_scalar*b.m_e2_no_ni*c.m_e2_e3-a.m_scalar*b.m_e3_no_ni*c.m_scalar) // e3_no_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static plane applyVersorWI(final rotorE3GA a, final plane b, final rotorE3GA c)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			(-a.m_e1_e2*b.m_e1_e2_e3_ni*c.m_e1_e2-a.m_e2_e3*b.m_e1_e2_e3_ni*c.m_e2_e3-a.m_e3_e1*b.m_e1_e2_e3_ni*c.m_e3_e1+a.m_scalar*b.m_e1_e2_e3_ni*c.m_scalar), // e1_e2_e3_ni
			(-a.m_e1_e2*b.m_no_e1_e2_ni*c.m_e2_e3-a.m_e1_e2*b.m_no_e1_e3_ni*c.m_scalar+a.m_e1_e2*b.m_no_e2_e3_ni*c.m_e1_e2-a.m_e2_e3*b.m_no_e1_e2_ni*c.m_e1_e2+a.m_e2_e3*b.m_no_e1_e3_ni*c.m_e3_e1-a.m_e2_e3*b.m_no_e2_e3_ni*c.m_e2_e3-a.m_e3_e1*b.m_no_e1_e2_ni*c.m_scalar+a.m_e3_e1*b.m_no_e1_e3_ni*c.m_e2_e3+a.m_e3_e1*b.m_no_e2_e3_ni*c.m_e3_e1+a.m_scalar*b.m_no_e1_e2_ni*c.m_e3_e1+a.m_scalar*b.m_no_e1_e3_ni*c.m_e1_e2+a.m_scalar*b.m_no_e2_e3_ni*c.m_scalar), // no_e2_e3_ni
			(a.m_e1_e2*b.m_no_e1_e2_ni*c.m_e3_e1+a.m_e1_e2*b.m_no_e1_e3_ni*c.m_e1_e2+a.m_e1_e2*b.m_no_e2_e3_ni*c.m_scalar-a.m_e2_e3*b.m_no_e1_e2_ni*c.m_scalar+a.m_e2_e3*b.m_no_e1_e3_ni*c.m_e2_e3+a.m_e2_e3*b.m_no_e2_e3_ni*c.m_e3_e1+a.m_e3_e1*b.m_no_e1_e2_ni*c.m_e1_e2-a.m_e3_e1*b.m_no_e1_e3_ni*c.m_e3_e1+a.m_e3_e1*b.m_no_e2_e3_ni*c.m_e2_e3+a.m_scalar*b.m_no_e1_e2_ni*c.m_e2_e3+a.m_scalar*b.m_no_e1_e3_ni*c.m_scalar-a.m_scalar*b.m_no_e2_e3_ni*c.m_e1_e2), // no_e1_e3_ni
			(-a.m_e1_e2*b.m_no_e1_e2_ni*c.m_e1_e2+a.m_e1_e2*b.m_no_e1_e3_ni*c.m_e3_e1-a.m_e1_e2*b.m_no_e2_e3_ni*c.m_e2_e3+a.m_e2_e3*b.m_no_e1_e2_ni*c.m_e2_e3+a.m_e2_e3*b.m_no_e1_e3_ni*c.m_scalar-a.m_e2_e3*b.m_no_e2_e3_ni*c.m_e1_e2+a.m_e3_e1*b.m_no_e1_e2_ni*c.m_e3_e1+a.m_e3_e1*b.m_no_e1_e3_ni*c.m_e1_e2+a.m_e3_e1*b.m_no_e2_e3_ni*c.m_scalar+a.m_scalar*b.m_no_e1_e2_ni*c.m_scalar-a.m_scalar*b.m_no_e1_e3_ni*c.m_e2_e3-a.m_scalar*b.m_no_e2_e3_ni*c.m_e3_e1) // no_e1_e2_ni
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static vectorE3GA applyVersorWI(final rotorE3GA a, final e1_t b, final rotorE3GA c)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_e1_e2*c.m_e1_e2-a.m_e2_e3*c.m_e2_e3+a.m_e3_e1*c.m_e3_e1+a.m_scalar*c.m_scalar), // e1
			(-a.m_e1_e2*c.m_scalar-a.m_e2_e3*c.m_e3_e1-a.m_e3_e1*c.m_e2_e3+a.m_scalar*c.m_e1_e2), // e2
			(-a.m_e1_e2*c.m_e2_e3-a.m_e2_e3*c.m_e1_e2+a.m_e3_e1*c.m_scalar-a.m_scalar*c.m_e3_e1) // e3
		);
}
/**
 * Returns a * b * reverse(a) using default metric. Only gives the correct result when the versor has a positive squared norm.
 * 
 */
public final static pseudoscalar applyVersorWI(final rotorE3GA a, final I5_t b, final rotorE3GA c)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			(-a.m_e1_e2*c.m_e1_e2-a.m_e2_e3*c.m_e2_e3-a.m_e3_e1*c.m_e3_e1+a.m_scalar*c.m_scalar) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a / b
 */
public final static mv div(final mv_if a, final double b)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyDiv_0(ac[0], cc[0], b);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyDiv_1(ac[1], cc[1], b);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyDiv_2(ac[2], cc[2], b);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyDiv_3(ac[3], cc[3], b);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyDiv_4(ac[4], cc[4], b);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyDiv_5(ac[5], cc[5], b);
	}
	return new mv(cc);
}
/**
 * Returns a / b
 */
public final static vectorE3GA div(final vectorE3GA a, final double b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1/((b)), // e1
			a.m_e2/((b)), // e2
			a.m_e3/((b)) // e3
		);
}
/**
 * Returns a / b
 */
public final static dualSphere div(final normalizedPoint a, final double b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			1.0 / (b), // no
			a.m_e1/((b)), // e1
			a.m_e2/((b)), // e2
			a.m_e3/((b)), // e3
			a.m_ni/((b)) // ni
		);
}
/**
 * Returns a / b
 */
public final static bivectorE3GA div(final bivectorE3GA a, final double b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((b)), // e1_e2
			a.m_e2_e3/((b)), // e2_e3
			a.m_e3_e1/((b)) // e3_e1
		);
}
/**
 * Returns a / b
 */
public final static line div(final line a, final double b)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			a.m_e1_e2_ni/((b)), // e1_e2_ni
			a.m_e1_e3_ni/((b)), // e1_e3_ni
			a.m_e2_e3_ni/((b)), // e2_e3_ni
			a.m_e1_no_ni/((b)), // e1_no_ni
			a.m_e2_no_ni/((b)), // e2_no_ni
			a.m_e3_no_ni/((b)) // e3_no_ni
		);
}
/**
 * Returns a / b
 */
public final static plane div(final plane a, final double b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni/((b)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((b)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((b)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((b)) // no_e1_e2_ni
		);
}
/**
 * Returns a / b
 */
public final static sphere div(final sphere a, final double b)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_e1_e2_e3_ni/((b)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((b)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((b)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((b)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((b)) // no_e1_e2_e3
		);
}
/**
 * Returns a / b
 */
public final static pseudoscalar div(final I5_t a, final double b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			1.0 / (b) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a / b
 */
public final static evenVersor div(final evenVersor a, final double b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar/((b)), // scalar
			a.m_no_e1/((b)), // no_e1
			a.m_no_e2/((b)), // no_e2
			a.m_no_e3/((b)), // no_e3
			a.m_e1_e2/((b)), // e1_e2
			a.m_e2_e3/((b)), // e2_e3
			a.m_e3_e1/((b)), // e3_e1
			a.m_e1_ni/((b)), // e1_ni
			a.m_e2_ni/((b)), // e2_ni
			a.m_e3_ni/((b)), // e3_ni
			a.m_no_ni/((b)), // no_ni
			a.m_e1_e2_e3_ni/((b)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((b)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((b)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((b)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((b)) // no_e1_e2_e3
		);
}
/**
 * Returns a / b
 */
public final static oddVersor div(final oddVersor a, final double b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no/((b)), // no
			a.m_e1/((b)), // e1
			a.m_e2/((b)), // e2
			a.m_e3/((b)), // e3
			a.m_ni/((b)), // ni
			a.m_no_e1_e2/((b)), // no_e1_e2
			a.m_no_e1_e3/((b)), // no_e1_e3
			a.m_no_e2_e3/((b)), // no_e2_e3
			a.m_e1_e2_e3/((b)), // e1_e2_e3
			a.m_no_e1_ni/((b)), // no_e1_ni
			a.m_no_e2_ni/((b)), // no_e2_ni
			a.m_e1_e2_ni/((b)), // e1_e2_ni
			a.m_no_e3_ni/((b)), // no_e3_ni
			a.m_e1_e3_ni/((b)), // e1_e3_ni
			a.m_e2_e3_ni/((b)), // e2_e3_ni
			a.m_no_e1_e2_e3_ni/((b)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns a / b
 */
public final static circle div(final I3_t a, final double b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			1.0 / (b), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0 // e2_e3_ni
		);
}
/**
 * Returns a / b
 */
public final static flatPoint div(final noni_t a, final double b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			1.0 / (b) // no_ni
		);
}
/**
 * Returns dual of mv using default metric.
 */
public final static mv dual(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (cc[5] == null) cc[5] = new double[1];
		dual_default_0_5(ac[0], cc[5]);
	}
	
	if (ac[1] != null) {
		if (cc[4] == null) cc[4] = new double[5];
		dual_default_1_4(ac[1], cc[4]);
	}
	
	if (ac[2] != null) {
		if (cc[3] == null) cc[3] = new double[10];
		dual_default_2_3(ac[2], cc[3]);
	}
	
	if (ac[3] != null) {
		if (cc[2] == null) cc[2] = new double[10];
		dual_default_3_2(ac[3], cc[2]);
	}
	
	if (ac[4] != null) {
		if (cc[1] == null) cc[1] = new double[5];
		dual_default_4_1(ac[4], cc[1]);
	}
	
	if (ac[5] != null) {
		if (cc[0] == null) cc[0] = new double[1];
		dual_default_5_0(ac[5], cc[0]);
	}
	
	return new mv(cc);
}
/**
 * Returns undual of mv using default metric.
 */
public final static mv undual(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (cc[5] == null) cc[5] = new double[1];
		undual_default_0_5(ac[0], cc[5]);
	}
	
	if (ac[1] != null) {
		if (cc[4] == null) cc[4] = new double[5];
		undual_default_1_4(ac[1], cc[4]);
	}
	
	if (ac[2] != null) {
		if (cc[3] == null) cc[3] = new double[10];
		undual_default_2_3(ac[2], cc[3]);
	}
	
	if (ac[3] != null) {
		if (cc[2] == null) cc[2] = new double[10];
		undual_default_3_2(ac[3], cc[2]);
	}
	
	if (ac[4] != null) {
		if (cc[1] == null) cc[1] = new double[5];
		undual_default_4_1(ac[4], cc[1]);
	}
	
	if (ac[5] != null) {
		if (cc[0] == null) cc[0] = new double[1];
		undual_default_5_0(ac[5], cc[0]);
	}
	
	return new mv(cc);
}
/**
 * Returns dual of double using default metric.
 */
public final static pseudoscalar dual(final double a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a // no_e1_e2_e3_ni
		);

}
/**
 * Returns undual of double using default metric.
 */
public final static pseudoscalar undual(final double a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a // no_e1_e2_e3_ni
		);

}
/**
 * Returns dual of vectorE3GA using default metric.
 */
public final static plane dual(final vectorE3GA a)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			0.0, // e1_e2_e3_ni
			a.m_e1, // no_e2_e3_ni
			-a.m_e2, // no_e1_e3_ni
			a.m_e3 // no_e1_e2_ni
		);

}
/**
 * Returns undual of vectorE3GA using default metric.
 */
public final static plane undual(final vectorE3GA a)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			0.0, // e1_e2_e3_ni
			-a.m_e1, // no_e2_e3_ni
			a.m_e2, // no_e1_e3_ni
			-a.m_e3 // no_e1_e2_ni
		);

}
/**
 * Returns dual of normalizedPoint using default metric.
 */
public final static sphere dual(final normalizedPoint a)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_ni, // e1_e2_e3_ni
			a.m_e1, // no_e2_e3_ni
			-a.m_e2, // no_e1_e3_ni
			a.m_e3, // no_e1_e2_ni
			1.0 // no_e1_e2_e3
		);

}
/**
 * Returns undual of normalizedPoint using default metric.
 */
public final static sphere undual(final normalizedPoint a)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-a.m_ni, // e1_e2_e3_ni
			-a.m_e1, // no_e2_e3_ni
			a.m_e2, // no_e1_e3_ni
			-a.m_e3, // no_e1_e2_ni
			-1.0 // no_e1_e2_e3
		);

}
/**
 * Returns dual of bivectorE3GA using default metric.
 */
public final static line dual(final bivectorE3GA a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			0.0, // e1_e2_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			-a.m_e2_e3, // e1_no_ni
			-a.m_e3_e1, // e2_no_ni
			-a.m_e1_e2 // e3_no_ni
		);

}
/**
 * Returns undual of bivectorE3GA using default metric.
 */
public final static line undual(final bivectorE3GA a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			0.0, // e1_e2_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			a.m_e2_e3, // e1_no_ni
			a.m_e3_e1, // e2_no_ni
			a.m_e1_e2 // e3_no_ni
		);

}
/**
 * Returns dual of rotorE3GA using default metric.
 */
public final static oddVersor dual(final rotorE3GA a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			a.m_e2_e3, // no_e1_ni
			a.m_e3_e1, // no_e2_ni
			0.0, // e1_e2_ni
			a.m_e1_e2, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			-a.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns undual of rotorE3GA using default metric.
 */
public final static oddVersor undual(final rotorE3GA a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-a.m_e2_e3, // no_e1_ni
			-a.m_e3_e1, // no_e2_ni
			0.0, // e1_e2_ni
			-a.m_e1_e2, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			a.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns dual of oddVersor using default metric.
 */
public final static evenVersor dual(final oddVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_no_e1_e2_e3_ni, // scalar
			-a.m_no_e2_e3, // no_e1
			a.m_no_e1_e3, // no_e2
			-a.m_no_e1_e2, // no_e3
			-a.m_no_e3_ni, // e1_e2
			-a.m_no_e1_ni, // e2_e3
			-a.m_no_e2_ni, // e3_e1
			-a.m_e2_e3_ni, // e1_ni
			a.m_e1_e3_ni, // e2_ni
			-a.m_e1_e2_ni, // e3_ni
			-a.m_e1_e2_e3, // no_ni
			a.m_ni, // e1_e2_e3_ni
			a.m_e1, // no_e2_e3_ni
			-a.m_e2, // no_e1_e3_ni
			a.m_e3, // no_e1_e2_ni
			a.m_no // no_e1_e2_e3
		);

}
/**
 * Returns undual of oddVersor using default metric.
 */
public final static evenVersor undual(final oddVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-a.m_no_e1_e2_e3_ni, // scalar
			a.m_no_e2_e3, // no_e1
			-a.m_no_e1_e3, // no_e2
			a.m_no_e1_e2, // no_e3
			a.m_no_e3_ni, // e1_e2
			a.m_no_e1_ni, // e2_e3
			a.m_no_e2_ni, // e3_e1
			a.m_e2_e3_ni, // e1_ni
			-a.m_e1_e3_ni, // e2_ni
			a.m_e1_e2_ni, // e3_ni
			a.m_e1_e2_e3, // no_ni
			-a.m_ni, // e1_e2_e3_ni
			-a.m_e1, // no_e2_e3_ni
			a.m_e2, // no_e1_e3_ni
			-a.m_e3, // no_e1_e2_ni
			-a.m_no // no_e1_e2_e3
		);

}
/**
 * Returns dual of evenVersor using default metric.
 */
public final static oddVersor dual(final evenVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			-a.m_no_e1_e2_e3, // no
			-a.m_no_e2_e3_ni, // e1
			a.m_no_e1_e3_ni, // e2
			-a.m_no_e1_e2_ni, // e3
			-a.m_e1_e2_e3_ni, // ni
			a.m_no_e3, // no_e1_e2
			-a.m_no_e2, // no_e1_e3
			a.m_no_e1, // no_e2_e3
			a.m_no_ni, // e1_e2_e3
			a.m_e2_e3, // no_e1_ni
			a.m_e3_e1, // no_e2_ni
			a.m_e3_ni, // e1_e2_ni
			a.m_e1_e2, // no_e3_ni
			-a.m_e2_ni, // e1_e3_ni
			a.m_e1_ni, // e2_e3_ni
			-a.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns undual of evenVersor using default metric.
 */
public final static oddVersor undual(final evenVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no_e1_e2_e3, // no
			a.m_no_e2_e3_ni, // e1
			-a.m_no_e1_e3_ni, // e2
			a.m_no_e1_e2_ni, // e3
			a.m_e1_e2_e3_ni, // ni
			-a.m_no_e3, // no_e1_e2
			a.m_no_e2, // no_e1_e3
			-a.m_no_e1, // no_e2_e3
			-a.m_no_ni, // e1_e2_e3
			-a.m_e2_e3, // no_e1_ni
			-a.m_e3_e1, // no_e2_ni
			-a.m_e3_ni, // e1_e2_ni
			-a.m_e1_e2, // no_e3_ni
			a.m_e2_ni, // e1_e3_ni
			-a.m_e1_ni, // e2_e3_ni
			a.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns dual of pointPair using default metric.
 */
public final static circle dual(final pointPair a)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			a.m_no_e3, // no_e1_e2
			-a.m_no_e2, // no_e1_e3
			a.m_no_e1, // no_e2_e3
			a.m_no_ni, // e1_e2_e3
			a.m_e2_e3, // no_e1_ni
			a.m_e3_e1, // no_e2_ni
			a.m_e3_ni, // e1_e2_ni
			a.m_e1_e2, // no_e3_ni
			-a.m_e2_ni, // e1_e3_ni
			a.m_e1_ni // e2_e3_ni
		);

}
/**
 * Returns undual of pointPair using default metric.
 */
public final static circle undual(final pointPair a)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			-a.m_no_e3, // no_e1_e2
			a.m_no_e2, // no_e1_e3
			-a.m_no_e1, // no_e2_e3
			-a.m_no_ni, // e1_e2_e3
			-a.m_e2_e3, // no_e1_ni
			-a.m_e3_e1, // no_e2_ni
			-a.m_e3_ni, // e1_e2_ni
			-a.m_e1_e2, // no_e3_ni
			a.m_e2_ni, // e1_e3_ni
			-a.m_e1_ni // e2_e3_ni
		);

}
/**
 * Returns dual of plane using default metric.
 */
public final static dualPlane dual(final plane a)
{
	return new dualPlane(dualPlane.coord_e1_e2_e3_ni,
			-a.m_no_e2_e3_ni, // e1
			a.m_no_e1_e3_ni, // e2
			-a.m_no_e1_e2_ni, // e3
			-a.m_e1_e2_e3_ni // ni
		);

}
/**
 * Returns undual of plane using default metric.
 */
public final static dualPlane undual(final plane a)
{
	return new dualPlane(dualPlane.coord_e1_e2_e3_ni,
			a.m_no_e2_e3_ni, // e1
			-a.m_no_e1_e3_ni, // e2
			a.m_no_e1_e2_ni, // e3
			a.m_e1_e2_e3_ni // ni
		);

}
/**
 * Returns dual of circle using euclidean metric.
 */
public final static pointPair dual(final circle a)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			-a.m_e2_e3_ni, // no_e1
			a.m_e1_e3_ni, // no_e2
			-a.m_e1_e2_ni, // no_e3
			-a.m_no_e3_ni, // e1_e2
			-a.m_no_e1_ni, // e2_e3
			-a.m_no_e2_ni, // e3_e1
			-a.m_no_e2_e3, // e1_ni
			a.m_no_e1_e3, // e2_ni
			-a.m_no_e1_e2, // e3_ni
			a.m_e1_e2_e3 // no_ni
		);

}
/**
 * Returns undual of circle using euclidean metric.
 */
public final static pointPair undual(final circle a)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			-a.m_e2_e3_ni, // no_e1
			a.m_e1_e3_ni, // no_e2
			-a.m_e1_e2_ni, // no_e3
			-a.m_no_e3_ni, // e1_e2
			-a.m_no_e1_ni, // e2_e3
			-a.m_no_e2_ni, // e3_e1
			-a.m_no_e2_e3, // e1_ni
			a.m_no_e1_e3, // e2_ni
			-a.m_no_e1_e2, // e3_ni
			a.m_e1_e2_e3 // no_ni
		);

}
/**
 * Returns dual of e1_t using default metric.
 */
public final static plane dual(final e1_t a)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			0.0, // e1_e2_e3_ni
			1.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0 // no_e1_e2_ni
		);

}
/**
 * Returns undual of I3_t using default metric.
 */
public final static noni_t undual(final I3_t a)
{
	return new noni_t(		);

}
/**
 * Returns dual of I5_t using default metric.
 */
public final static double dual(final I5_t a)
{
	return 1.0;

}
/**
 * Returns undual of I5i_t using default metric.
 */
public final static double undual(final I5i_t a)
{
	return 1.0;

}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final mv_if a, final mv_if b, final double c)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (!equals_0_0(ac[0], bc[0], c)) return false;
		}
		else if (!zeroGroup_0(ac[0], c)) return false;
	}
		else if (bc[0] != null) {
		if (!zeroGroup_0(bc[0], c)) return false;
	}
	
	if (ac[1] != null) {
		if (bc[1] != null) {
			if (!equals_1_1(ac[1], bc[1], c)) return false;
		}
		else if (!zeroGroup_1(ac[1], c)) return false;
	}
		else if (bc[1] != null) {
		if (!zeroGroup_1(bc[1], c)) return false;
	}
	
	if (ac[2] != null) {
		if (bc[2] != null) {
			if (!equals_2_2(ac[2], bc[2], c)) return false;
		}
		else if (!zeroGroup_2(ac[2], c)) return false;
	}
		else if (bc[2] != null) {
		if (!zeroGroup_2(bc[2], c)) return false;
	}
	
	if (ac[3] != null) {
		if (bc[3] != null) {
			if (!equals_3_3(ac[3], bc[3], c)) return false;
		}
		else if (!zeroGroup_3(ac[3], c)) return false;
	}
		else if (bc[3] != null) {
		if (!zeroGroup_3(bc[3], c)) return false;
	}
	
	if (ac[4] != null) {
		if (bc[4] != null) {
			if (!equals_4_4(ac[4], bc[4], c)) return false;
		}
		else if (!zeroGroup_4(ac[4], c)) return false;
	}
		else if (bc[4] != null) {
		if (!zeroGroup_4(bc[4], c)) return false;
	}
	
	if (ac[5] != null) {
		if (bc[5] != null) {
			if (!equals_5_5(ac[5], bc[5], c)) return false;
		}
		else if (!zeroGroup_5(ac[5], c)) return false;
	}
		else if (bc[5] != null) {
		if (!zeroGroup_5(bc[5], c)) return false;
	}
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final vectorE3GA a, final vectorE3GA b, final double c)
{
	double d;
	d = a.m_e1 - b.m_e1; if ((d < -c) || (d > c)) return false; /* e1 */
	d = a.m_e2 - b.m_e2; if ((d < -c) || (d > c)) return false; /* e2 */
	d = a.m_e3 - b.m_e3; if ((d < -c) || (d > c)) return false; /* e3 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final bivectorE3GA a, final bivectorE3GA b, final double c)
{
	double d;
	d = a.m_e1_e2 - b.m_e1_e2; if ((d < -c) || (d > c)) return false; /* e1^e2 */
	d = -a.m_e3_e1 - -b.m_e3_e1; if ((d < -c) || (d > c)) return false; /* e1^e3 */
	d = a.m_e2_e3 - b.m_e2_e3; if ((d < -c) || (d > c)) return false; /* e2^e3 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final rotorE3GA a, final rotorE3GA b, final double c)
{
	double d;
	d = a.m_scalar - b.m_scalar; if ((d < -c) || (d > c)) return false; /* 1 */
	d = a.m_e1_e2 - b.m_e1_e2; if ((d < -c) || (d > c)) return false; /* e1^e2 */
	d = -a.m_e3_e1 - -b.m_e3_e1; if ((d < -c) || (d > c)) return false; /* e1^e3 */
	d = a.m_e2_e3 - b.m_e2_e3; if ((d < -c) || (d > c)) return false; /* e2^e3 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final bivectorE3GA a, final rotorE3GA b, final double c)
{
	double d;
	if ((b.m_scalar < -c) || (b.m_scalar > c)) return false; /* 1 */
	d = a.m_e1_e2 - b.m_e1_e2; if ((d < -c) || (d > c)) return false; /* e1^e2 */
	d = -a.m_e3_e1 - -b.m_e3_e1; if ((d < -c) || (d > c)) return false; /* e1^e3 */
	d = a.m_e2_e3 - b.m_e2_e3; if ((d < -c) || (d > c)) return false; /* e2^e3 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final rotorE3GA a, final bivectorE3GA b, final double c)
{
	double d;
	if ((a.m_scalar < -c) || (a.m_scalar > c)) return false; /* 1 */
	d = a.m_e1_e2 - b.m_e1_e2; if ((d < -c) || (d > c)) return false; /* e1^e2 */
	d = -a.m_e3_e1 - -b.m_e3_e1; if ((d < -c) || (d > c)) return false; /* e1^e3 */
	d = a.m_e2_e3 - b.m_e2_e3; if ((d < -c) || (d > c)) return false; /* e2^e3 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final rotorE3GA a, final line b, final double c)
{
	if ((a.m_scalar < -c) || (a.m_scalar > c)) return false; /* 1 */
	if ((a.m_e1_e2 < -c) || (a.m_e1_e2 > c)) return false; /* e1^e2 */
	if ((-a.m_e3_e1 < -c) || (-a.m_e3_e1 > c)) return false; /* e1^e3 */
	if ((a.m_e2_e3 < -c) || (a.m_e2_e3 > c)) return false; /* e2^e3 */
	if ((-b.m_e1_no_ni < -c) || (-b.m_e1_no_ni > c)) return false; /* no^e1^ni */
	if ((-b.m_e2_no_ni < -c) || (-b.m_e2_no_ni > c)) return false; /* no^e2^ni */
	if ((b.m_e1_e2_ni < -c) || (b.m_e1_e2_ni > c)) return false; /* e1^e2^ni */
	if ((-b.m_e3_no_ni < -c) || (-b.m_e3_no_ni > c)) return false; /* no^e3^ni */
	if ((b.m_e1_e3_ni < -c) || (b.m_e1_e3_ni > c)) return false; /* e1^e3^ni */
	if ((b.m_e2_e3_ni < -c) || (b.m_e2_e3_ni > c)) return false; /* e2^e3^ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final evenVersor a, final plane b, final double c)
{
	double d;
	if ((a.m_scalar < -c) || (a.m_scalar > c)) return false; /* 1 */
	if ((a.m_no_e1 < -c) || (a.m_no_e1 > c)) return false; /* no^e1 */
	if ((a.m_no_e2 < -c) || (a.m_no_e2 > c)) return false; /* no^e2 */
	if ((a.m_e1_e2 < -c) || (a.m_e1_e2 > c)) return false; /* e1^e2 */
	if ((a.m_no_e3 < -c) || (a.m_no_e3 > c)) return false; /* no^e3 */
	if ((-a.m_e3_e1 < -c) || (-a.m_e3_e1 > c)) return false; /* e1^e3 */
	if ((a.m_e2_e3 < -c) || (a.m_e2_e3 > c)) return false; /* e2^e3 */
	if ((a.m_no_e1_e2_e3 < -c) || (a.m_no_e1_e2_e3 > c)) return false; /* no^e1^e2^e3 */
	if ((a.m_no_ni < -c) || (a.m_no_ni > c)) return false; /* no^ni */
	if ((a.m_e1_ni < -c) || (a.m_e1_ni > c)) return false; /* e1^ni */
	if ((a.m_e2_ni < -c) || (a.m_e2_ni > c)) return false; /* e2^ni */
	d = a.m_no_e1_e2_ni - b.m_no_e1_e2_ni; if ((d < -c) || (d > c)) return false; /* no^e1^e2^ni */
	if ((a.m_e3_ni < -c) || (a.m_e3_ni > c)) return false; /* e3^ni */
	d = a.m_no_e1_e3_ni - b.m_no_e1_e3_ni; if ((d < -c) || (d > c)) return false; /* no^e1^e3^ni */
	d = a.m_no_e2_e3_ni - b.m_no_e2_e3_ni; if ((d < -c) || (d > c)) return false; /* no^e2^e3^ni */
	d = a.m_e1_e2_e3_ni - b.m_e1_e2_e3_ni; if ((d < -c) || (d > c)) return false; /* e1^e2^e3^ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final circle a, final line b, final double c)
{
	double d;
	if ((a.m_no_e1_e2 < -c) || (a.m_no_e1_e2 > c)) return false; /* no^e1^e2 */
	if ((a.m_no_e1_e3 < -c) || (a.m_no_e1_e3 > c)) return false; /* no^e1^e3 */
	if ((a.m_no_e2_e3 < -c) || (a.m_no_e2_e3 > c)) return false; /* no^e2^e3 */
	if ((a.m_e1_e2_e3 < -c) || (a.m_e1_e2_e3 > c)) return false; /* e1^e2^e3 */
	d = a.m_no_e1_ni - -b.m_e1_no_ni; if ((d < -c) || (d > c)) return false; /* no^e1^ni */
	d = a.m_no_e2_ni - -b.m_e2_no_ni; if ((d < -c) || (d > c)) return false; /* no^e2^ni */
	d = a.m_e1_e2_ni - b.m_e1_e2_ni; if ((d < -c) || (d > c)) return false; /* e1^e2^ni */
	d = a.m_no_e3_ni - -b.m_e3_no_ni; if ((d < -c) || (d > c)) return false; /* no^e3^ni */
	d = a.m_e1_e3_ni - b.m_e1_e3_ni; if ((d < -c) || (d > c)) return false; /* e1^e3^ni */
	d = a.m_e2_e3_ni - b.m_e2_e3_ni; if ((d < -c) || (d > c)) return false; /* e2^e3^ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final plane a, final plane b, final double c)
{
	double d;
	d = a.m_no_e1_e2_ni - b.m_no_e1_e2_ni; if ((d < -c) || (d > c)) return false; /* no^e1^e2^ni */
	d = a.m_no_e1_e3_ni - b.m_no_e1_e3_ni; if ((d < -c) || (d > c)) return false; /* no^e1^e3^ni */
	d = a.m_no_e2_e3_ni - b.m_no_e2_e3_ni; if ((d < -c) || (d > c)) return false; /* no^e2^e3^ni */
	d = a.m_e1_e2_e3_ni - b.m_e1_e2_e3_ni; if ((d < -c) || (d > c)) return false; /* e1^e2^e3^ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final normalizedPoint a, final normalizedPoint b, final double c)
{
	double d;
	d = 1.0 - 1.0; if ((d < -c) || (d > c)) return false; /* no */
	d = a.m_e1 - b.m_e1; if ((d < -c) || (d > c)) return false; /* e1 */
	d = a.m_e2 - b.m_e2; if ((d < -c) || (d > c)) return false; /* e2 */
	d = a.m_e3 - b.m_e3; if ((d < -c) || (d > c)) return false; /* e3 */
	d = a.m_ni - b.m_ni; if ((d < -c) || (d > c)) return false; /* ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final e1_t a, final e1_t b, final double c)
{
	double d;
	d = 1.0 - 1.0; if ((d < -c) || (d > c)) return false; /* e1 */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final I5_t a, final I5i_t b, final double c)
{
	double d;
	d = 1.0 - -1.0; if ((d < -c) || (d > c)) return false; /* no^e1^e2^e3^ni */
	return true;
}
/**
 * Returns whether input multivectors are equal up to an epsilon c.
 */
public final static boolean equals(final noni_t a, final pointPair b, final double c)
{
	double d;
	if ((b.m_no_e1 < -c) || (b.m_no_e1 > c)) return false; /* no^e1 */
	if ((b.m_no_e2 < -c) || (b.m_no_e2 > c)) return false; /* no^e2 */
	if ((b.m_e1_e2 < -c) || (b.m_e1_e2 > c)) return false; /* e1^e2 */
	if ((b.m_no_e3 < -c) || (b.m_no_e3 > c)) return false; /* no^e3 */
	if ((-b.m_e3_e1 < -c) || (-b.m_e3_e1 > c)) return false; /* e1^e3 */
	if ((b.m_e2_e3 < -c) || (b.m_e2_e3 > c)) return false; /* e2^e3 */
	d = 1.0 - b.m_no_ni; if ((d < -c) || (d > c)) return false; /* no^ni */
	if ((b.m_e1_ni < -c) || (b.m_e1_ni > c)) return false; /* e1^ni */
	if ((b.m_e2_ni < -c) || (b.m_e2_ni > c)) return false; /* e2^ni */
	if ((b.m_e3_ni < -c) || (b.m_e3_ni > c)) return false; /* e3^ni */
	return true;
}
/**
 * Returns grade groupBitmap of  mv.
 */
public final static mv extractGrade(final mv_if a, final int groupBitmap)
{
	int gu = a.to_mv().gu() & groupBitmap;
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if ((gu & GroupBitmap.GROUP_0) != 0) {
		cc[0] = new double[1];
		copyGroup_0(ac[0], cc[0]);
	}
	
	if ((gu & GroupBitmap.GROUP_1) != 0) {
		cc[1] = new double[5];
		copyGroup_1(ac[1], cc[1]);
	}
	
	if ((gu & GroupBitmap.GROUP_2) != 0) {
		cc[2] = new double[10];
		copyGroup_2(ac[2], cc[2]);
	}
	
	if ((gu & GroupBitmap.GROUP_3) != 0) {
		cc[3] = new double[10];
		copyGroup_3(ac[3], cc[3]);
	}
	
	if ((gu & GroupBitmap.GROUP_4) != 0) {
		cc[4] = new double[5];
		copyGroup_4(ac[4], cc[4]);
	}
	
	if ((gu & GroupBitmap.GROUP_5) != 0) {
		cc[5] = new double[1];
		copyGroup_5(ac[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns grade 2 of  mv.
 */
public final static mv extractGrade2(final mv_if a)
{
	return extractGrade(a, 4);
}
/**
 * Returns grade 0 of  rotorE3GA.
 */
public final static double extractGrade0(final rotorE3GA a)
{
	return a.m_scalar;
}
/**
 * Returns grade 2 of  rotorE3GA.
 */
public final static bivectorE3GA extractGrade2(final rotorE3GA a)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);
}
/**
 * Returns grade 1 of  oddVersor.
 */
public final static dualSphere extractGrade1(final oddVersor a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			a.m_no, // no
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			a.m_ni // ni
		);
}
/**
 * Returns grade 3 of  oddVersor.
 */
public final static circle extractGrade3(final oddVersor a)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			a.m_no_e1_e2, // no_e1_e2
			a.m_no_e1_e3, // no_e1_e3
			a.m_no_e2_e3, // no_e2_e3
			a.m_e1_e2_e3, // e1_e2_e3
			a.m_no_e1_ni, // no_e1_ni
			a.m_no_e2_ni, // no_e2_ni
			a.m_e1_e2_ni, // e1_e2_ni
			a.m_no_e3_ni, // no_e3_ni
			a.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni // e2_e3_ni
		);
}
/**
 * Returns grade 4 of  oddVersor.
 */
public final static double extractGrade4(final oddVersor a)
{
	return 0.0;
}
/**
 * Returns grade 0 of  evenVersor.
 */
public final static double extractGrade0(final evenVersor a)
{
	return a.m_scalar;
}
/**
 * Returns grade 1 of  evenVersor.
 */
public final static double extractGrade1(final evenVersor a)
{
	return 0.0;
}
/**
 * Returns grade 4 of  evenVersor.
 */
public final static sphere extractGrade4(final evenVersor a)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);
}
/**
 * Returns grade 0 of  normalizedPoint.
 */
public final static double extractGrade0(final normalizedPoint a)
{
	return 0.0;
}
/**
 * Returns grade 1 of  normalizedPoint.
 */
public final static normalizedPoint extractGrade1(final normalizedPoint a)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			a.m_ni // ni
		);
}
/**
 * Returns grade 2 of  normalizedPoint.
 */
public final static double extractGrade2(final normalizedPoint a)
{
	return 0.0;
}
/**
 * Returns grade 3 of  dualSphere.
 */
public final static double extractGrade3(final dualSphere a)
{
	return 0.0;
}
/**
 * Returns grade 4 of  normalizedPoint.
 */
public final static double extractGrade4(final normalizedPoint a)
{
	return 0.0;
}
/**
 * Returns grade 5 of  normalizedPoint.
 */
public final static double extractGrade5(final normalizedPoint a)
{
	return 0.0;
}
/**
 * Returns grade 0 of  e1_t.
 */
public final static double extractGrade0(final e1_t a)
{
	return 0.0;
}
/**
 * Returns grade 1 of  e2_t.
 */
public final static e2_t extractGrade1(final e2_t a)
{
	return new e2_t(		);
}
/**
 * Returns grade 2 of  e3_t.
 */
public final static double extractGrade2(final e3_t a)
{
	return 0.0;
}
/**
 * Returns grade 3 of  no_t.
 */
public final static double extractGrade3(final no_t a)
{
	return 0.0;
}
/**
 * Returns grade 4 of  e1_t.
 */
public final static double extractGrade4(final e1_t a)
{
	return 0.0;
}
/**
 * Returns grade 0 of  I5_t.
 */
public final static double extractGrade0(final I5_t a)
{
	return 0.0;
}
/**
 * Returns grade 1 of  I5i_t.
 */
public final static double extractGrade1(final I5i_t a)
{
	return 0.0;
}
/**
 * Returns grade 2 of  I5_t.
 */
public final static double extractGrade2(final I5_t a)
{
	return 0.0;
}
/**
 * Returns grade 3 of  I5i_t.
 */
public final static double extractGrade3(final I5i_t a)
{
	return 0.0;
}
/**
 * Returns grade 4 of  I5_t.
 */
public final static double extractGrade4(final I5_t a)
{
	return 0.0;
}
/**
 * Returns geometric product of mv and mv.
 */
public final static mv gp(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_0_1_1(ac[0], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_0_2_2(ac[0], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_0_3_3(ac[0], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_0_4_4(ac[0], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_0_5_5(ac[0], bc[5], cc[5]);
		}
	}
	if (ac[1] != null) {
		if (bc[0] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_0_1(ac[1], bc[0], cc[1]);
		}
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_1_2(ac[1], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_2_1(ac[1], bc[2], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_2_3(ac[1], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_3_2(ac[1], bc[3], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_3_4(ac[1], bc[3], cc[4]);
		}
		if (bc[4] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_4_3(ac[1], bc[4], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_1_4_5(ac[1], bc[4], cc[5]);
		}
		if (bc[5] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_5_4(ac[1], bc[5], cc[4]);
		}
	}
	if (ac[2] != null) {
		if (bc[0] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_0_2(ac[2], bc[0], cc[2]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_1_1(ac[2], bc[1], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_1_3(ac[2], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_2_2(ac[2], bc[2], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_2_2_4(ac[2], bc[2], cc[4]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_3_1(ac[2], bc[3], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_3_3(ac[2], bc[3], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_2_3_5(ac[2], bc[3], cc[5]);
		}
		if (bc[4] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_4_2(ac[2], bc[4], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_2_4_4(ac[2], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_5_3(ac[2], bc[5], cc[3]);
		}
	}
	if (ac[3] != null) {
		if (bc[0] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_0_3(ac[3], bc[0], cc[3]);
		}
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_1_2(ac[3], bc[1], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_3_1_4(ac[3], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_2_1(ac[3], bc[2], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_2_3(ac[3], bc[2], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_3_2_5(ac[3], bc[2], cc[5]);
		}
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_3_2(ac[3], bc[3], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_3_3_4(ac[3], bc[3], cc[4]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_4_1(ac[3], bc[4], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_4_3(ac[3], bc[4], cc[3]);
		}
		if (bc[5] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_5_2(ac[3], bc[5], cc[2]);
		}
	}
	if (ac[4] != null) {
		if (bc[0] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_0_4(ac[4], bc[0], cc[4]);
		}
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_4_1_3(ac[4], bc[1], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_4_1_5(ac[4], bc[1], cc[5]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_4_2_2(ac[4], bc[2], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_2_4(ac[4], bc[2], cc[4]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_3_1(ac[4], bc[3], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_4_3_3(ac[4], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_4_4_2(ac[4], bc[4], cc[2]);
		}
		if (bc[5] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_5_1(ac[4], bc[5], cc[1]);
		}
	}
	if (ac[5] != null) {
		if (bc[0] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_5_0_5(ac[5], bc[0], cc[5]);
		}
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_5_1_4(ac[5], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_5_2_3(ac[5], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_5_3_2(ac[5], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_5_4_1(ac[5], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns geometric product of vectorE3GA and vectorE3GA.
 */
public final static rotorE3GA gp(final vectorE3GA a, final vectorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3), // scalar
			(a.m_e1*b.m_e2-a.m_e2*b.m_e1), // e1_e2
			(a.m_e2*b.m_e3-a.m_e3*b.m_e2), // e2_e3
			-(a.m_e1*b.m_e3-a.m_e3*b.m_e1) // e3_e1
		);

}
/**
 * Returns geometric product of rotorE3GA and vectorE3GA.
 */
public final static oddVersor gp(final rotorE3GA a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			(a.m_e1_e2*b.m_e2-a.m_e3_e1*b.m_e3+a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*b.m_e1+a.m_e2_e3*b.m_e3+a.m_scalar*b.m_e2), // e2
			(-a.m_e2_e3*b.m_e2+a.m_e3_e1*b.m_e1+a.m_scalar*b.m_e3), // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1_e2*b.m_e3+a.m_e2_e3*b.m_e1+a.m_e3_e1*b.m_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of circle and line.
 */
public final static evenVersor gp(final circle a, final line b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_no_e1_e2*b.m_e1_e2_ni+a.m_no_e1_e3*b.m_e1_e3_ni-a.m_no_e1_ni*b.m_e1_no_ni+a.m_no_e2_e3*b.m_e2_e3_ni-a.m_no_e2_ni*b.m_e2_no_ni-a.m_no_e3_ni*b.m_e3_no_ni), // scalar
			(a.m_no_e1_e2*b.m_e2_no_ni+a.m_no_e1_e3*b.m_e3_no_ni), // no_e1
			(-a.m_no_e1_e2*b.m_e1_no_ni+a.m_no_e2_e3*b.m_e3_no_ni), // no_e2
			(-a.m_no_e1_e3*b.m_e1_no_ni-a.m_no_e2_e3*b.m_e2_no_ni), // no_e3
			(a.m_no_e1_e3*b.m_e2_e3_ni-a.m_no_e1_ni*b.m_e2_no_ni-a.m_no_e2_e3*b.m_e1_e3_ni+a.m_no_e2_ni*b.m_e1_no_ni), // e1_e2
			(a.m_no_e1_e2*b.m_e1_e3_ni-a.m_no_e1_e3*b.m_e1_e2_ni-a.m_no_e2_ni*b.m_e3_no_ni+a.m_no_e3_ni*b.m_e2_no_ni), // e2_e3
			-(-a.m_no_e1_e2*b.m_e2_e3_ni-a.m_no_e1_ni*b.m_e3_no_ni+a.m_no_e2_e3*b.m_e1_e2_ni+a.m_no_e3_ni*b.m_e1_no_ni), // e3_e1
			(-a.m_e1_e2_e3*b.m_e2_e3_ni+a.m_e1_e2_ni*b.m_e2_no_ni+a.m_e1_e3_ni*b.m_e3_no_ni+a.m_no_e2_ni*b.m_e1_e2_ni+a.m_no_e3_ni*b.m_e1_e3_ni), // e1_ni
			(a.m_e1_e2_e3*b.m_e1_e3_ni-a.m_e1_e2_ni*b.m_e1_no_ni+a.m_e2_e3_ni*b.m_e3_no_ni-a.m_no_e1_ni*b.m_e1_e2_ni+a.m_no_e3_ni*b.m_e2_e3_ni), // e2_ni
			(-a.m_e1_e2_e3*b.m_e1_e2_ni-a.m_e1_e3_ni*b.m_e1_no_ni-a.m_e2_e3_ni*b.m_e2_no_ni-a.m_no_e1_ni*b.m_e1_e3_ni-a.m_no_e2_ni*b.m_e2_e3_ni), // e3_ni
			(-a.m_no_e1_e2*b.m_e1_e2_ni-a.m_no_e1_e3*b.m_e1_e3_ni-a.m_no_e2_e3*b.m_e2_e3_ni), // no_ni
			(a.m_e1_e2_ni*b.m_e3_no_ni-a.m_e1_e3_ni*b.m_e2_no_ni+a.m_e2_e3_ni*b.m_e1_no_ni-a.m_no_e1_ni*b.m_e2_e3_ni+a.m_no_e2_ni*b.m_e1_e3_ni-a.m_no_e3_ni*b.m_e1_e2_ni), // e1_e2_e3_ni
			(a.m_e1_e2_e3*b.m_e1_no_ni-a.m_no_e1_e2*b.m_e1_e3_ni+a.m_no_e1_e3*b.m_e1_e2_ni), // no_e2_e3_ni
			(-a.m_e1_e2_e3*b.m_e2_no_ni+a.m_no_e1_e2*b.m_e2_e3_ni-a.m_no_e2_e3*b.m_e1_e2_ni), // no_e1_e3_ni
			(a.m_e1_e2_e3*b.m_e3_no_ni-a.m_no_e1_e3*b.m_e2_e3_ni+a.m_no_e2_e3*b.m_e1_e3_ni), // no_e1_e2_ni
			(a.m_no_e1_e2*b.m_e3_no_ni-a.m_no_e1_e3*b.m_e2_no_ni+a.m_no_e2_e3*b.m_e1_no_ni) // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of rotorE3GA and line.
 */
public final static oddVersor gp(final rotorE3GA a, final line b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			(-a.m_e1_e2*b.m_e1_e2_ni-a.m_e2_e3*b.m_e2_e3_ni+a.m_e3_e1*b.m_e1_e3_ni), // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			(-a.m_e1_e2*b.m_e2_no_ni+a.m_e3_e1*b.m_e3_no_ni-a.m_scalar*b.m_e1_no_ni), // no_e1_ni
			(a.m_e1_e2*b.m_e1_no_ni-a.m_e2_e3*b.m_e3_no_ni-a.m_scalar*b.m_e2_no_ni), // no_e2_ni
			(a.m_e2_e3*b.m_e1_e3_ni+a.m_e3_e1*b.m_e2_e3_ni+a.m_scalar*b.m_e1_e2_ni), // e1_e2_ni
			(a.m_e2_e3*b.m_e2_no_ni-a.m_e3_e1*b.m_e1_no_ni-a.m_scalar*b.m_e3_no_ni), // no_e3_ni
			(a.m_e1_e2*b.m_e2_e3_ni-a.m_e2_e3*b.m_e1_e2_ni+a.m_scalar*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1_e2*b.m_e1_e3_ni-a.m_e3_e1*b.m_e1_e2_ni+a.m_scalar*b.m_e2_e3_ni), // e2_e3_ni
			(-a.m_e1_e2*b.m_e3_no_ni-a.m_e2_e3*b.m_e1_no_ni-a.m_e3_e1*b.m_e2_no_ni) // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of vectorE3GA and rotorE3GA.
 */
public final static oddVersor gp(final vectorE3GA a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			(a.m_e1*b.m_scalar-a.m_e2*b.m_e1_e2+a.m_e3*b.m_e3_e1), // e1
			(a.m_e1*b.m_e1_e2+a.m_e2*b.m_scalar-a.m_e3*b.m_e2_e3), // e2
			(-a.m_e1*b.m_e3_e1+a.m_e2*b.m_e2_e3+a.m_e3*b.m_scalar), // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1*b.m_e2_e3+a.m_e2*b.m_e3_e1+a.m_e3*b.m_e1_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of rotorE3GA and rotorE3GA.
 */
public final static rotorE3GA gp(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1+a.m_scalar*b.m_scalar), // scalar
			(a.m_e1_e2*b.m_scalar-a.m_e2_e3*b.m_e3_e1+a.m_e3_e1*b.m_e2_e3+a.m_scalar*b.m_e1_e2), // e1_e2
			(a.m_e1_e2*b.m_e3_e1+a.m_e2_e3*b.m_scalar-a.m_e3_e1*b.m_e1_e2+a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3-a.m_e2_e3*b.m_e1_e2-a.m_e3_e1*b.m_scalar-a.m_scalar*b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns geometric product of plane and rotorE3GA.
 */
public final static evenVersor gp(final plane a, final rotorE3GA b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			0.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			-a.m_e1_e2_e3_ni*b.m_e2_e3, // e1_ni
			-a.m_e1_e2_e3_ni*b.m_e3_e1, // e2_ni
			-a.m_e1_e2_e3_ni*b.m_e1_e2, // e3_ni
			(-a.m_no_e1_e2_ni*b.m_e1_e2+a.m_no_e1_e3_ni*b.m_e3_e1-a.m_no_e2_e3_ni*b.m_e2_e3), // no_ni
			a.m_e1_e2_e3_ni*b.m_scalar, // e1_e2_e3_ni
			(a.m_no_e1_e2_ni*b.m_e3_e1+a.m_no_e1_e3_ni*b.m_e1_e2+a.m_no_e2_e3_ni*b.m_scalar), // no_e2_e3_ni
			(a.m_no_e1_e2_ni*b.m_e2_e3+a.m_no_e1_e3_ni*b.m_scalar-a.m_no_e2_e3_ni*b.m_e1_e2), // no_e1_e3_ni
			(a.m_no_e1_e2_ni*b.m_scalar-a.m_no_e1_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*b.m_e3_e1), // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of plane and oddVersor.
 */
public final static oddVersor gp(final plane a, final oddVersor b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no_e1_e2_ni*b.m_no_e1_e2+a.m_no_e1_e3_ni*b.m_no_e1_e3+a.m_no_e2_e3_ni*b.m_no_e2_e3), // no
			(a.m_e1_e2_e3_ni*b.m_no_e2_e3-a.m_no_e1_e2_ni*b.m_no_e2_ni-a.m_no_e1_e3_ni*b.m_no_e3_ni+a.m_no_e2_e3_ni*b.m_no_e1_e2_e3_ni), // e1
			(-a.m_e1_e2_e3_ni*b.m_no_e1_e3+a.m_no_e1_e2_ni*b.m_no_e1_ni-a.m_no_e1_e3_ni*b.m_no_e1_e2_e3_ni-a.m_no_e2_e3_ni*b.m_no_e3_ni), // e2
			(a.m_e1_e2_e3_ni*b.m_no_e1_e2+a.m_no_e1_e2_ni*b.m_no_e1_e2_e3_ni+a.m_no_e1_e3_ni*b.m_no_e1_ni+a.m_no_e2_e3_ni*b.m_no_e2_ni), // e3
			(a.m_e1_e2_e3_ni*b.m_e1_e2_e3+a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3_ni-a.m_no_e1_e2_ni*b.m_e1_e2_ni-a.m_no_e1_e3_ni*b.m_e1_e3_ni-a.m_no_e2_e3_ni*b.m_e2_e3_ni), // ni
			(-a.m_no_e1_e2_ni*b.m_no+a.m_no_e1_e3_ni*b.m_no_e2_e3-a.m_no_e2_e3_ni*b.m_no_e1_e3), // no_e1_e2
			(-a.m_no_e1_e2_ni*b.m_no_e2_e3-a.m_no_e1_e3_ni*b.m_no+a.m_no_e2_e3_ni*b.m_no_e1_e2), // no_e1_e3
			(a.m_no_e1_e2_ni*b.m_no_e1_e3-a.m_no_e1_e3_ni*b.m_no_e1_e2-a.m_no_e2_e3_ni*b.m_no), // no_e2_e3
			(-a.m_e1_e2_e3_ni*b.m_no-a.m_no_e1_e2_ni*b.m_no_e3_ni+a.m_no_e1_e3_ni*b.m_no_e2_ni-a.m_no_e2_e3_ni*b.m_no_e1_ni), // e1_e2_e3
			(-a.m_e1_e2_e3_ni*b.m_no_e2_e3-a.m_no_e1_e2_ni*b.m_e2-a.m_no_e1_e3_ni*b.m_e3+a.m_no_e2_e3_ni*b.m_e1_e2_e3), // no_e1_ni
			(a.m_e1_e2_e3_ni*b.m_no_e1_e3+a.m_no_e1_e2_ni*b.m_e1-a.m_no_e1_e3_ni*b.m_e1_e2_e3-a.m_no_e2_e3_ni*b.m_e3), // no_e2_ni
			(-a.m_e1_e2_e3_ni*b.m_e3-a.m_e1_e2_e3_ni*b.m_no_e3_ni+a.m_no_e1_e2_ni*b.m_ni-a.m_no_e1_e3_ni*b.m_e2_e3_ni+a.m_no_e2_e3_ni*b.m_e1_e3_ni), // e1_e2_ni
			(-a.m_e1_e2_e3_ni*b.m_no_e1_e2+a.m_no_e1_e2_ni*b.m_e1_e2_e3+a.m_no_e1_e3_ni*b.m_e1+a.m_no_e2_e3_ni*b.m_e2), // no_e3_ni
			(a.m_e1_e2_e3_ni*b.m_e2+a.m_e1_e2_e3_ni*b.m_no_e2_ni+a.m_no_e1_e2_ni*b.m_e2_e3_ni+a.m_no_e1_e3_ni*b.m_ni-a.m_no_e2_e3_ni*b.m_e1_e2_ni), // e1_e3_ni
			(-a.m_e1_e2_e3_ni*b.m_e1-a.m_e1_e2_e3_ni*b.m_no_e1_ni-a.m_no_e1_e2_ni*b.m_e1_e3_ni+a.m_no_e1_e3_ni*b.m_e1_e2_ni+a.m_no_e2_e3_ni*b.m_ni), // e2_e3_ni
			(a.m_e1_e2_e3_ni*b.m_no-a.m_no_e1_e2_ni*b.m_e3+a.m_no_e1_e3_ni*b.m_e2-a.m_no_e2_e3_ni*b.m_e1) // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of bivectorE3GA and bivectorE3GA.
 */
public final static rotorE3GA gp(final bivectorE3GA a, final bivectorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1), // scalar
			(-a.m_e2_e3*b.m_e3_e1+a.m_e3_e1*b.m_e2_e3), // e1_e2
			(a.m_e1_e2*b.m_e3_e1-a.m_e3_e1*b.m_e1_e2), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3-a.m_e2_e3*b.m_e1_e2) // e3_e1
		);

}
/**
 * Returns geometric product of evenVersor and dualSphere.
 */
public final static oddVersor gp(final evenVersor a, final dualSphere b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no_e1*b.m_e1+a.m_no_e2*b.m_e2+a.m_no_e3*b.m_e3-a.m_no_ni*b.m_no+a.m_scalar*b.m_no), // no
			(a.m_e1_e2*b.m_e2-a.m_e1_ni*b.m_no-a.m_e3_e1*b.m_e3+a.m_no_e1*b.m_ni+a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*b.m_e1+a.m_e2_e3*b.m_e3-a.m_e2_ni*b.m_no+a.m_no_e2*b.m_ni+a.m_scalar*b.m_e2), // e2
			(-a.m_e2_e3*b.m_e2+a.m_e3_e1*b.m_e1-a.m_e3_ni*b.m_no+a.m_no_e3*b.m_ni+a.m_scalar*b.m_e3), // e3
			(-a.m_e1_ni*b.m_e1-a.m_e2_ni*b.m_e2-a.m_e3_ni*b.m_e3+a.m_no_ni*b.m_ni+a.m_scalar*b.m_ni), // ni
			(a.m_e1_e2*b.m_no+a.m_no_e1*b.m_e2+a.m_no_e1_e2_e3*b.m_e3-a.m_no_e1_e2_ni*b.m_no-a.m_no_e2*b.m_e1), // no_e1_e2
			(-a.m_e3_e1*b.m_no+a.m_no_e1*b.m_e3-a.m_no_e1_e2_e3*b.m_e2-a.m_no_e1_e3_ni*b.m_no-a.m_no_e3*b.m_e1), // no_e1_e3
			(a.m_e2_e3*b.m_no+a.m_no_e1_e2_e3*b.m_e1+a.m_no_e2*b.m_e3-a.m_no_e2_e3_ni*b.m_no-a.m_no_e3*b.m_e2), // no_e2_e3
			(a.m_e1_e2*b.m_e3-a.m_e1_e2_e3_ni*b.m_no+a.m_e2_e3*b.m_e1+a.m_e3_e1*b.m_e2+a.m_no_e1_e2_e3*b.m_ni), // e1_e2_e3
			(a.m_e1_ni*b.m_no+a.m_no_e1*b.m_ni-a.m_no_e1_e2_ni*b.m_e2-a.m_no_e1_e3_ni*b.m_e3-a.m_no_ni*b.m_e1), // no_e1_ni
			(a.m_e2_ni*b.m_no+a.m_no_e1_e2_ni*b.m_e1+a.m_no_e2*b.m_ni-a.m_no_e2_e3_ni*b.m_e3-a.m_no_ni*b.m_e2), // no_e2_ni
			(a.m_e1_e2*b.m_ni-a.m_e1_e2_e3_ni*b.m_e3-a.m_e1_ni*b.m_e2+a.m_e2_ni*b.m_e1+a.m_no_e1_e2_ni*b.m_ni), // e1_e2_ni
			(a.m_e3_ni*b.m_no+a.m_no_e1_e3_ni*b.m_e1+a.m_no_e2_e3_ni*b.m_e2+a.m_no_e3*b.m_ni-a.m_no_ni*b.m_e3), // no_e3_ni
			(a.m_e1_e2_e3_ni*b.m_e2-a.m_e1_ni*b.m_e3-a.m_e3_e1*b.m_ni+a.m_e3_ni*b.m_e1+a.m_no_e1_e3_ni*b.m_ni), // e1_e3_ni
			(-a.m_e1_e2_e3_ni*b.m_e1+a.m_e2_e3*b.m_ni-a.m_e2_ni*b.m_e3+a.m_e3_ni*b.m_e2+a.m_no_e2_e3_ni*b.m_ni), // e2_e3_ni
			(a.m_e1_e2_e3_ni*b.m_no+a.m_no_e1_e2_e3*b.m_ni-a.m_no_e1_e2_ni*b.m_e3+a.m_no_e1_e3_ni*b.m_e2-a.m_no_e2_e3_ni*b.m_e1) // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of normalizedPoint and normalizedPoint.
 */
public final static evenVersor gp(final normalizedPoint a, final normalizedPoint b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3-a.m_ni-b.m_ni), // scalar
			(-a.m_e1+b.m_e1), // no_e1
			(-a.m_e2+b.m_e2), // no_e2
			(-a.m_e3+b.m_e3), // no_e3
			(a.m_e1*b.m_e2-a.m_e2*b.m_e1), // e1_e2
			(a.m_e2*b.m_e3-a.m_e3*b.m_e2), // e2_e3
			-(a.m_e1*b.m_e3-a.m_e3*b.m_e1), // e3_e1
			(a.m_e1*b.m_ni-a.m_ni*b.m_e1), // e1_ni
			(a.m_e2*b.m_ni-a.m_ni*b.m_e2), // e2_ni
			(a.m_e3*b.m_ni-a.m_ni*b.m_e3), // e3_ni
			(-a.m_ni+b.m_ni), // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of flatPoint and oddVersor.
 */
public final static oddVersor gp(final flatPoint a, final oddVersor b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			-a.m_no_ni*b.m_no, // no
			(-a.m_e1_ni*b.m_no+a.m_e2_ni*b.m_no_e1_e2+a.m_e3_ni*b.m_no_e1_e3-a.m_no_ni*b.m_no_e1_ni), // e1
			(-a.m_e1_ni*b.m_no_e1_e2-a.m_e2_ni*b.m_no+a.m_e3_ni*b.m_no_e2_e3-a.m_no_ni*b.m_no_e2_ni), // e2
			(-a.m_e1_ni*b.m_no_e1_e3-a.m_e2_ni*b.m_no_e2_e3-a.m_e3_ni*b.m_no-a.m_no_ni*b.m_no_e3_ni), // e3
			(-a.m_e1_ni*b.m_e1-a.m_e1_ni*b.m_no_e1_ni-a.m_e2_ni*b.m_e2-a.m_e2_ni*b.m_no_e2_ni-a.m_e3_ni*b.m_e3-a.m_e3_ni*b.m_no_e3_ni+a.m_no_ni*b.m_ni), // ni
			-a.m_no_ni*b.m_no_e1_e2, // no_e1_e2
			-a.m_no_ni*b.m_no_e1_e3, // no_e1_e3
			-a.m_no_ni*b.m_no_e2_e3, // no_e2_e3
			(-a.m_e1_ni*b.m_no_e2_e3+a.m_e2_ni*b.m_no_e1_e3-a.m_e3_ni*b.m_no_e1_e2-a.m_no_ni*b.m_no_e1_e2_e3_ni), // e1_e2_e3
			(a.m_e1_ni*b.m_no-a.m_e2_ni*b.m_no_e1_e2-a.m_e3_ni*b.m_no_e1_e3-a.m_no_ni*b.m_e1), // no_e1_ni
			(a.m_e1_ni*b.m_no_e1_e2+a.m_e2_ni*b.m_no-a.m_e3_ni*b.m_no_e2_e3-a.m_no_ni*b.m_e2), // no_e2_ni
			(-a.m_e1_ni*b.m_e2-a.m_e1_ni*b.m_no_e2_ni+a.m_e2_ni*b.m_e1+a.m_e2_ni*b.m_no_e1_ni-a.m_e3_ni*b.m_e1_e2_e3-a.m_e3_ni*b.m_no_e1_e2_e3_ni+a.m_no_ni*b.m_e1_e2_ni), // e1_e2_ni
			(a.m_e1_ni*b.m_no_e1_e3+a.m_e2_ni*b.m_no_e2_e3+a.m_e3_ni*b.m_no-a.m_no_ni*b.m_e3), // no_e3_ni
			(-a.m_e1_ni*b.m_e3-a.m_e1_ni*b.m_no_e3_ni+a.m_e2_ni*b.m_e1_e2_e3+a.m_e2_ni*b.m_no_e1_e2_e3_ni+a.m_e3_ni*b.m_e1+a.m_e3_ni*b.m_no_e1_ni+a.m_no_ni*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1_ni*b.m_e1_e2_e3-a.m_e1_ni*b.m_no_e1_e2_e3_ni-a.m_e2_ni*b.m_e3-a.m_e2_ni*b.m_no_e3_ni+a.m_e3_ni*b.m_e2+a.m_e3_ni*b.m_no_e2_ni+a.m_no_ni*b.m_e2_e3_ni), // e2_e3_ni
			(a.m_e1_ni*b.m_no_e2_e3-a.m_e2_ni*b.m_no_e1_e3+a.m_e3_ni*b.m_no_e1_e2-a.m_no_ni*b.m_e1_e2_e3) // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of e1_t and e2_t.
 */
public final static bivectorE3GA gp(final e1_t a, final e2_t b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			1.0, // e1_e2
			0.0, // e2_e3
			0.0 // e3_e1
		);

}
/**
 * Returns geometric product of e3_t and ni_t.
 */
public final static freeVector gp(final e3_t a, final ni_t b)
{
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			0.0, // e1_ni
			0.0, // e2_ni
			1.0 // e3_ni
		);

}
/**
 * Returns geometric product of no_t and ni_t.
 */
public final static evenVersor gp(final no_t a, final ni_t b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-1.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			1.0, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of e3_t and I5i_t.
 */
public final static plane gp(final e3_t a, final I5i_t b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			1.0 // no_e1_e2_ni
		);

}
/**
 * Returns geometric product of mv and mv.
 */
public final static mv gp_em(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_0_0_0(ac[0], bc[0], cc[0]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_0_1_1(ac[0], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_0_2_2(ac[0], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_0_3_3(ac[0], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_0_4_4(ac[0], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_0_5_5(ac[0], bc[5], cc[5]);
		}
	}
	if (ac[1] != null) {
		if (bc[0] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_1_0_1(ac[1], bc[0], cc[1]);
		}
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_1_1_0(ac[1], bc[1], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_1_1_2(ac[1], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_1_2_1(ac[1], bc[2], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_1_2_3(ac[1], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_1_3_2(ac[1], bc[3], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_1_3_4(ac[1], bc[3], cc[4]);
		}
		if (bc[4] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_1_4_3(ac[1], bc[4], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_1_4_5(ac[1], bc[4], cc[5]);
		}
		if (bc[5] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_1_5_4(ac[1], bc[5], cc[4]);
		}
	}
	if (ac[2] != null) {
		if (bc[0] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_2_0_2(ac[2], bc[0], cc[2]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_2_1_1(ac[2], bc[1], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_2_1_3(ac[2], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_2_2_0(ac[2], bc[2], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_2_2_2(ac[2], bc[2], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_2_2_4(ac[2], bc[2], cc[4]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_2_3_1(ac[2], bc[3], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_2_3_3(ac[2], bc[3], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_2_3_5(ac[2], bc[3], cc[5]);
		}
		if (bc[4] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_2_4_2(ac[2], bc[4], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_2_4_4(ac[2], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_2_5_3(ac[2], bc[5], cc[3]);
		}
	}
	if (ac[3] != null) {
		if (bc[0] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_3_0_3(ac[3], bc[0], cc[3]);
		}
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_3_1_2(ac[3], bc[1], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_3_1_4(ac[3], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_3_2_1(ac[3], bc[2], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_3_2_3(ac[3], bc[2], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_3_2_5(ac[3], bc[2], cc[5]);
		}
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_3_3_0(ac[3], bc[3], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_3_3_2(ac[3], bc[3], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_3_3_4(ac[3], bc[3], cc[4]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_3_4_1(ac[3], bc[4], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_3_4_3(ac[3], bc[4], cc[3]);
		}
		if (bc[5] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_3_5_2(ac[3], bc[5], cc[2]);
		}
	}
	if (ac[4] != null) {
		if (bc[0] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_4_0_4(ac[4], bc[0], cc[4]);
		}
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_4_1_3(ac[4], bc[1], cc[3]);
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_4_1_5(ac[4], bc[1], cc[5]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_4_2_2(ac[4], bc[2], cc[2]);
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_4_2_4(ac[4], bc[2], cc[4]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_4_3_1(ac[4], bc[3], cc[1]);
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_4_3_3(ac[4], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_4_4_0(ac[4], bc[4], cc[0]);
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_4_4_2(ac[4], bc[4], cc[2]);
		}
		if (bc[5] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_4_5_1(ac[4], bc[5], cc[1]);
		}
	}
	if (ac[5] != null) {
		if (bc[0] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_euclidean_5_0_5(ac[5], bc[0], cc[5]);
		}
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_euclidean_5_1_4(ac[5], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_euclidean_5_2_3(ac[5], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_euclidean_5_3_2(ac[5], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_euclidean_5_4_1(ac[5], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_euclidean_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns geometric product of vectorE3GA and vectorE3GA.
 */
public final static rotorE3GA gp_em(final vectorE3GA a, final vectorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3), // scalar
			(a.m_e1*b.m_e2-a.m_e2*b.m_e1), // e1_e2
			(a.m_e2*b.m_e3-a.m_e3*b.m_e2), // e2_e3
			-(a.m_e1*b.m_e3-a.m_e3*b.m_e1) // e3_e1
		);

}
/**
 * Returns geometric product of rotorE3GA and vectorE3GA.
 */
public final static oddVersor gp_em(final rotorE3GA a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			(a.m_e1_e2*b.m_e2-a.m_e3_e1*b.m_e3+a.m_scalar*b.m_e1), // e1
			(-a.m_e1_e2*b.m_e1+a.m_e2_e3*b.m_e3+a.m_scalar*b.m_e2), // e2
			(-a.m_e2_e3*b.m_e2+a.m_e3_e1*b.m_e1+a.m_scalar*b.m_e3), // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1_e2*b.m_e3+a.m_e2_e3*b.m_e1+a.m_e3_e1*b.m_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of vectorE3GA and line.
 */
public final static evenVersor gp_em(final vectorE3GA a, final line b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			0.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			(-a.m_e2*b.m_e1_e2_ni-a.m_e3*b.m_e1_e3_ni), // e1_ni
			(a.m_e1*b.m_e1_e2_ni-a.m_e3*b.m_e2_e3_ni), // e2_ni
			(a.m_e1*b.m_e1_e3_ni+a.m_e2*b.m_e2_e3_ni), // e3_ni
			(a.m_e1*b.m_e1_no_ni+a.m_e2*b.m_e2_no_ni+a.m_e3*b.m_e3_no_ni), // no_ni
			(a.m_e1*b.m_e2_e3_ni-a.m_e2*b.m_e1_e3_ni+a.m_e3*b.m_e1_e2_ni), // e1_e2_e3_ni
			(a.m_e2*b.m_e3_no_ni-a.m_e3*b.m_e2_no_ni), // no_e2_e3_ni
			(a.m_e1*b.m_e3_no_ni-a.m_e3*b.m_e1_no_ni), // no_e1_e3_ni
			(a.m_e1*b.m_e2_no_ni-a.m_e2*b.m_e1_no_ni), // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of rotorE3GA and circle.
 */
public final static oddVersor gp_em(final rotorE3GA a, final circle b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(-a.m_e1_e2*b.m_no_e1_e2-a.m_e2_e3*b.m_no_e2_e3+a.m_e3_e1*b.m_no_e1_e3), // no
			-a.m_e2_e3*b.m_e1_e2_e3, // e1
			-a.m_e3_e1*b.m_e1_e2_e3, // e2
			-a.m_e1_e2*b.m_e1_e2_e3, // e3
			(-a.m_e1_e2*b.m_e1_e2_ni-a.m_e2_e3*b.m_e2_e3_ni+a.m_e3_e1*b.m_e1_e3_ni), // ni
			(a.m_e2_e3*b.m_no_e1_e3+a.m_e3_e1*b.m_no_e2_e3+a.m_scalar*b.m_no_e1_e2), // no_e1_e2
			(a.m_e1_e2*b.m_no_e2_e3-a.m_e2_e3*b.m_no_e1_e2+a.m_scalar*b.m_no_e1_e3), // no_e1_e3
			(-a.m_e1_e2*b.m_no_e1_e3-a.m_e3_e1*b.m_no_e1_e2+a.m_scalar*b.m_no_e2_e3), // no_e2_e3
			a.m_scalar*b.m_e1_e2_e3, // e1_e2_e3
			(a.m_e1_e2*b.m_no_e2_ni-a.m_e3_e1*b.m_no_e3_ni+a.m_scalar*b.m_no_e1_ni), // no_e1_ni
			(-a.m_e1_e2*b.m_no_e1_ni+a.m_e2_e3*b.m_no_e3_ni+a.m_scalar*b.m_no_e2_ni), // no_e2_ni
			(a.m_e2_e3*b.m_e1_e3_ni+a.m_e3_e1*b.m_e2_e3_ni+a.m_scalar*b.m_e1_e2_ni), // e1_e2_ni
			(-a.m_e2_e3*b.m_no_e2_ni+a.m_e3_e1*b.m_no_e1_ni+a.m_scalar*b.m_no_e3_ni), // no_e3_ni
			(a.m_e1_e2*b.m_e2_e3_ni-a.m_e2_e3*b.m_e1_e2_ni+a.m_scalar*b.m_e1_e3_ni), // e1_e3_ni
			(-a.m_e1_e2*b.m_e1_e3_ni-a.m_e3_e1*b.m_e1_e2_ni+a.m_scalar*b.m_e2_e3_ni), // e2_e3_ni
			(a.m_e1_e2*b.m_no_e3_ni+a.m_e2_e3*b.m_no_e1_ni+a.m_e3_e1*b.m_no_e2_ni) // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of vectorE3GA and rotorE3GA.
 */
public final static oddVersor gp_em(final vectorE3GA a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			(a.m_e1*b.m_scalar-a.m_e2*b.m_e1_e2+a.m_e3*b.m_e3_e1), // e1
			(a.m_e1*b.m_e1_e2+a.m_e2*b.m_scalar-a.m_e3*b.m_e2_e3), // e2
			(-a.m_e1*b.m_e3_e1+a.m_e2*b.m_e2_e3+a.m_e3*b.m_scalar), // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1*b.m_e2_e3+a.m_e2*b.m_e3_e1+a.m_e3*b.m_e1_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of rotorE3GA and rotorE3GA.
 */
public final static rotorE3GA gp_em(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1+a.m_scalar*b.m_scalar), // scalar
			(a.m_e1_e2*b.m_scalar-a.m_e2_e3*b.m_e3_e1+a.m_e3_e1*b.m_e2_e3+a.m_scalar*b.m_e1_e2), // e1_e2
			(a.m_e1_e2*b.m_e3_e1+a.m_e2_e3*b.m_scalar-a.m_e3_e1*b.m_e1_e2+a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3-a.m_e2_e3*b.m_e1_e2-a.m_e3_e1*b.m_scalar-a.m_scalar*b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns geometric product of plane and rotorE3GA.
 */
public final static evenVersor gp_em(final plane a, final rotorE3GA b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			0.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			-a.m_e1_e2_e3_ni*b.m_e2_e3, // e1_ni
			-a.m_e1_e2_e3_ni*b.m_e3_e1, // e2_ni
			-a.m_e1_e2_e3_ni*b.m_e1_e2, // e3_ni
			(-a.m_no_e1_e2_ni*b.m_e1_e2+a.m_no_e1_e3_ni*b.m_e3_e1-a.m_no_e2_e3_ni*b.m_e2_e3), // no_ni
			a.m_e1_e2_e3_ni*b.m_scalar, // e1_e2_e3_ni
			(a.m_no_e1_e2_ni*b.m_e3_e1+a.m_no_e1_e3_ni*b.m_e1_e2+a.m_no_e2_e3_ni*b.m_scalar), // no_e2_e3_ni
			(a.m_no_e1_e2_ni*b.m_e2_e3+a.m_no_e1_e3_ni*b.m_scalar-a.m_no_e2_e3_ni*b.m_e1_e2), // no_e1_e3_ni
			(a.m_no_e1_e2_ni*b.m_scalar-a.m_no_e1_e3_ni*b.m_e2_e3-a.m_no_e2_e3_ni*b.m_e3_e1), // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of sphere and circle.
 */
public final static oddVersor gp_em(final sphere a, final circle b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(-a.m_no_e1_e2_e3*b.m_e1_e2_e3-a.m_no_e1_e2_ni*b.m_e1_e2_ni-a.m_no_e1_e3_ni*b.m_e1_e3_ni-a.m_no_e2_e3_ni*b.m_e2_e3_ni), // no
			(-a.m_e1_e2_e3_ni*b.m_e2_e3_ni+a.m_no_e1_e2_e3*b.m_no_e2_e3+a.m_no_e1_e2_ni*b.m_no_e2_ni+a.m_no_e1_e3_ni*b.m_no_e3_ni), // e1
			(a.m_e1_e2_e3_ni*b.m_e1_e3_ni-a.m_no_e1_e2_e3*b.m_no_e1_e3-a.m_no_e1_e2_ni*b.m_no_e1_ni+a.m_no_e2_e3_ni*b.m_no_e3_ni), // e2
			(-a.m_e1_e2_e3_ni*b.m_e1_e2_ni+a.m_no_e1_e2_e3*b.m_no_e1_e2-a.m_no_e1_e3_ni*b.m_no_e1_ni-a.m_no_e2_e3_ni*b.m_no_e2_ni), // e3
			(a.m_e1_e2_e3_ni*b.m_e1_e2_e3+a.m_no_e1_e2_ni*b.m_no_e1_e2+a.m_no_e1_e3_ni*b.m_no_e1_e3+a.m_no_e2_e3_ni*b.m_no_e2_e3), // ni
			(-a.m_e1_e2_e3_ni*b.m_no_e3_ni-a.m_no_e1_e3_ni*b.m_e2_e3_ni+a.m_no_e2_e3_ni*b.m_e1_e3_ni), // no_e1_e2
			(a.m_e1_e2_e3_ni*b.m_no_e2_ni+a.m_no_e1_e2_ni*b.m_e2_e3_ni-a.m_no_e2_e3_ni*b.m_e1_e2_ni), // no_e1_e3
			(-a.m_e1_e2_e3_ni*b.m_no_e1_ni-a.m_no_e1_e2_ni*b.m_e1_e3_ni+a.m_no_e1_e3_ni*b.m_e1_e2_ni), // no_e2_e3
			(a.m_no_e1_e2_ni*b.m_no_e3_ni-a.m_no_e1_e3_ni*b.m_no_e2_ni+a.m_no_e2_e3_ni*b.m_no_e1_ni), // e1_e2_e3
			(-a.m_e1_e2_e3_ni*b.m_no_e2_e3-a.m_no_e1_e2_e3*b.m_e2_e3_ni+a.m_no_e2_e3_ni*b.m_e1_e2_e3), // no_e1_ni
			(a.m_e1_e2_e3_ni*b.m_no_e1_e3+a.m_no_e1_e2_e3*b.m_e1_e3_ni-a.m_no_e1_e3_ni*b.m_e1_e2_e3), // no_e2_ni
			(-a.m_no_e1_e2_e3*b.m_no_e3_ni+a.m_no_e1_e3_ni*b.m_no_e2_e3-a.m_no_e2_e3_ni*b.m_no_e1_e3), // e1_e2_ni
			(-a.m_e1_e2_e3_ni*b.m_no_e1_e2-a.m_no_e1_e2_e3*b.m_e1_e2_ni+a.m_no_e1_e2_ni*b.m_e1_e2_e3), // no_e3_ni
			(a.m_no_e1_e2_e3*b.m_no_e2_ni-a.m_no_e1_e2_ni*b.m_no_e2_e3+a.m_no_e2_e3_ni*b.m_no_e1_e2), // e1_e3_ni
			(-a.m_no_e1_e2_e3*b.m_no_e1_ni+a.m_no_e1_e2_ni*b.m_no_e1_e3-a.m_no_e1_e3_ni*b.m_no_e1_e2), // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns geometric product of bivectorE3GA and bivectorE3GA.
 */
public final static rotorE3GA gp_em(final bivectorE3GA a, final bivectorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1), // scalar
			(-a.m_e2_e3*b.m_e3_e1+a.m_e3_e1*b.m_e2_e3), // e1_e2
			(a.m_e1_e2*b.m_e3_e1-a.m_e3_e1*b.m_e1_e2), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3-a.m_e2_e3*b.m_e1_e2) // e3_e1
		);

}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final mv_if a, final double b)
{
	int bitmap = 0;
	double[][] ac = a.to_mv().c();
	
	if (ac[0] != null) {
		if (!zeroGroup_0(ac[0], b)) bitmap |= 1;
	}
	
	if (ac[1] != null) {
		if (!zeroGroup_1(ac[1], b)) bitmap |= 2;
	}
	
	if (ac[2] != null) {
		if (!zeroGroup_2(ac[2], b)) bitmap |= 4;
	}
	
	if (ac[3] != null) {
		if (!zeroGroup_3(ac[3], b)) bitmap |= 8;
	}
	
	if (ac[4] != null) {
		if (!zeroGroup_4(ac[4], b)) bitmap |= 16;
	}
	
	if (ac[5] != null) {
		if (!zeroGroup_5(ac[5], b)) bitmap |= 32;
	}
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final rotorE3GA a, final double b)
{
	int bitmap = 0;
	if ((a.m_scalar < -b) || (a.m_scalar > b)) bitmap |= 1;
	if ((a.m_e1_e2 < -b) || (a.m_e1_e2 > b)) bitmap |= 4;
	if ((a.m_e2_e3 < -b) || (a.m_e2_e3 > b)) bitmap |= 4;
	if ((a.m_e3_e1 < -b) || (a.m_e3_e1 > b)) bitmap |= 4;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final oddVersor a, final double b)
{
	int bitmap = 0;
	if ((a.m_no < -b) || (a.m_no > b)) bitmap |= 2;
	if ((a.m_e1 < -b) || (a.m_e1 > b)) bitmap |= 2;
	if ((a.m_e2 < -b) || (a.m_e2 > b)) bitmap |= 2;
	if ((a.m_e3 < -b) || (a.m_e3 > b)) bitmap |= 2;
	if ((a.m_ni < -b) || (a.m_ni > b)) bitmap |= 2;
	if ((a.m_no_e1_e2 < -b) || (a.m_no_e1_e2 > b)) bitmap |= 8;
	if ((a.m_no_e1_e3 < -b) || (a.m_no_e1_e3 > b)) bitmap |= 8;
	if ((a.m_no_e2_e3 < -b) || (a.m_no_e2_e3 > b)) bitmap |= 8;
	if ((a.m_e1_e2_e3 < -b) || (a.m_e1_e2_e3 > b)) bitmap |= 8;
	if ((a.m_no_e1_ni < -b) || (a.m_no_e1_ni > b)) bitmap |= 8;
	if ((a.m_no_e2_ni < -b) || (a.m_no_e2_ni > b)) bitmap |= 8;
	if ((a.m_e1_e2_ni < -b) || (a.m_e1_e2_ni > b)) bitmap |= 8;
	if ((a.m_no_e3_ni < -b) || (a.m_no_e3_ni > b)) bitmap |= 8;
	if ((a.m_e1_e3_ni < -b) || (a.m_e1_e3_ni > b)) bitmap |= 8;
	if ((a.m_e2_e3_ni < -b) || (a.m_e2_e3_ni > b)) bitmap |= 8;
	if ((a.m_no_e1_e2_e3_ni < -b) || (a.m_no_e1_e2_e3_ni > b)) bitmap |= 32;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final evenVersor a, final double b)
{
	int bitmap = 0;
	if ((a.m_scalar < -b) || (a.m_scalar > b)) bitmap |= 1;
	if ((a.m_no_e1 < -b) || (a.m_no_e1 > b)) bitmap |= 4;
	if ((a.m_no_e2 < -b) || (a.m_no_e2 > b)) bitmap |= 4;
	if ((a.m_no_e3 < -b) || (a.m_no_e3 > b)) bitmap |= 4;
	if ((a.m_e1_e2 < -b) || (a.m_e1_e2 > b)) bitmap |= 4;
	if ((a.m_e2_e3 < -b) || (a.m_e2_e3 > b)) bitmap |= 4;
	if ((a.m_e3_e1 < -b) || (a.m_e3_e1 > b)) bitmap |= 4;
	if ((a.m_e1_ni < -b) || (a.m_e1_ni > b)) bitmap |= 4;
	if ((a.m_e2_ni < -b) || (a.m_e2_ni > b)) bitmap |= 4;
	if ((a.m_e3_ni < -b) || (a.m_e3_ni > b)) bitmap |= 4;
	if ((a.m_no_ni < -b) || (a.m_no_ni > b)) bitmap |= 4;
	if ((a.m_e1_e2_e3_ni < -b) || (a.m_e1_e2_e3_ni > b)) bitmap |= 16;
	if ((a.m_no_e2_e3_ni < -b) || (a.m_no_e2_e3_ni > b)) bitmap |= 16;
	if ((a.m_no_e1_e3_ni < -b) || (a.m_no_e1_e3_ni > b)) bitmap |= 16;
	if ((a.m_no_e1_e2_ni < -b) || (a.m_no_e1_e2_ni > b)) bitmap |= 16;
	if ((a.m_no_e1_e2_e3 < -b) || (a.m_no_e1_e2_e3 > b)) bitmap |= 16;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final vectorE3GA a, final double b)
{
	int bitmap = 0;
	if ((a.m_e1 < -b) || (a.m_e1 > b)) bitmap |= 2;
	if ((a.m_e2 < -b) || (a.m_e2 > b)) bitmap |= 2;
	if ((a.m_e3 < -b) || (a.m_e3 > b)) bitmap |= 2;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final bivectorE3GA a, final double b)
{
	int bitmap = 0;
	if ((a.m_e1_e2 < -b) || (a.m_e1_e2 > b)) bitmap |= 4;
	if ((a.m_e2_e3 < -b) || (a.m_e2_e3 > b)) bitmap |= 4;
	if ((a.m_e3_e1 < -b) || (a.m_e3_e1 > b)) bitmap |= 4;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final pseudoscalar a, final double b)
{
	int bitmap = 0;
	if ((a.m_no_e1_e2_e3_ni < -b) || (a.m_no_e1_e2_e3_ni > b)) bitmap |= 32;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final circle a, final double b)
{
	int bitmap = 0;
	if ((a.m_no_e1_e2 < -b) || (a.m_no_e1_e2 > b)) bitmap |= 8;
	if ((a.m_no_e1_e3 < -b) || (a.m_no_e1_e3 > b)) bitmap |= 8;
	if ((a.m_no_e2_e3 < -b) || (a.m_no_e2_e3 > b)) bitmap |= 8;
	if ((a.m_e1_e2_e3 < -b) || (a.m_e1_e2_e3 > b)) bitmap |= 8;
	if ((a.m_no_e1_ni < -b) || (a.m_no_e1_ni > b)) bitmap |= 8;
	if ((a.m_no_e2_ni < -b) || (a.m_no_e2_ni > b)) bitmap |= 8;
	if ((a.m_e1_e2_ni < -b) || (a.m_e1_e2_ni > b)) bitmap |= 8;
	if ((a.m_no_e3_ni < -b) || (a.m_no_e3_ni > b)) bitmap |= 8;
	if ((a.m_e1_e3_ni < -b) || (a.m_e1_e3_ni > b)) bitmap |= 8;
	if ((a.m_e2_e3_ni < -b) || (a.m_e2_e3_ni > b)) bitmap |= 8;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final pointPair a, final double b)
{
	int bitmap = 0;
	if ((a.m_no_e1 < -b) || (a.m_no_e1 > b)) bitmap |= 4;
	if ((a.m_no_e2 < -b) || (a.m_no_e2 > b)) bitmap |= 4;
	if ((a.m_no_e3 < -b) || (a.m_no_e3 > b)) bitmap |= 4;
	if ((a.m_e1_e2 < -b) || (a.m_e1_e2 > b)) bitmap |= 4;
	if ((a.m_e2_e3 < -b) || (a.m_e2_e3 > b)) bitmap |= 4;
	if ((a.m_e3_e1 < -b) || (a.m_e3_e1 > b)) bitmap |= 4;
	if ((a.m_e1_ni < -b) || (a.m_e1_ni > b)) bitmap |= 4;
	if ((a.m_e2_ni < -b) || (a.m_e2_ni > b)) bitmap |= 4;
	if ((a.m_e3_ni < -b) || (a.m_e3_ni > b)) bitmap |= 4;
	if ((a.m_no_ni < -b) || (a.m_no_ni > b)) bitmap |= 4;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final e1_t a, final double b)
{
	int bitmap = 0;
	if (1.0 > b) bitmap |= 2;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final e2_t a, final double b)
{
	int bitmap = 0;
	if (1.0 > b) bitmap |= 2;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final noni_t a, final double b)
{
	int bitmap = 0;
	if (1.0 > b) bitmap |= 4;
	return bitmap;
}
/**
 * Returns a bitmap where each one-bit means that at least one coordinate of the respective grade of  a is larger than b
 */
public final static int gradeBitmap(final I5i_t a, final double b)
{
	int bitmap = 0;
	if (1.0 > b) bitmap |= 32;
	return bitmap;
}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of mv and mv.
 */
public final static mv hp(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		if (bc[0] != null) {
			cc[0] = new double[1];
			hp_0_0(ac[0], bc[0], cc[0]);
		}
	}
	
	if (ac[1] != null) {
		if (bc[1] != null) {
			cc[1] = new double[5];
			hp_1_1(ac[1], bc[1], cc[1]);
		}
	}
	
	if (ac[2] != null) {
		if (bc[2] != null) {
			cc[2] = new double[10];
			hp_2_2(ac[2], bc[2], cc[2]);
		}
	}
	
	if (ac[3] != null) {
		if (bc[3] != null) {
			cc[3] = new double[10];
			hp_3_3(ac[3], bc[3], cc[3]);
		}
	}
	
	if (ac[4] != null) {
		if (bc[4] != null) {
			cc[4] = new double[5];
			hp_4_4(ac[4], bc[4], cc[4]);
		}
	}
	
	if (ac[5] != null) {
		if (bc[5] != null) {
			cc[5] = new double[1];
			hp_5_5(ac[5], bc[5], cc[5]);
		}
	}
	return new mv(cc);
}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and vectorE3GA.
 */
public final static vectorE3GA hp(final vectorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1*b.m_e1, // e1
			a.m_e2*b.m_e2, // e2
			a.m_e3*b.m_e3 // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of bivectorE3GA and bivectorE3GA.
 */
public final static bivectorE3GA hp(final bivectorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of rotorE3GA and rotorE3GA.
 */
public final static rotorE3GA hp(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar*b.m_scalar, // scalar
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of oddVersor and oddVersor.
 */
public final static oddVersor hp(final oddVersor a, final oddVersor b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no*b.m_no, // no
			a.m_e1*b.m_e1, // e1
			a.m_e2*b.m_e2, // e2
			a.m_e3*b.m_e3, // e3
			a.m_ni*b.m_ni, // ni
			a.m_no_e1_e2*b.m_no_e1_e2, // no_e1_e2
			a.m_no_e1_e3*b.m_no_e1_e3, // no_e1_e3
			a.m_no_e2_e3*b.m_no_e2_e3, // no_e2_e3
			a.m_e1_e2_e3*b.m_e1_e2_e3, // e1_e2_e3
			a.m_no_e1_ni*b.m_no_e1_ni, // no_e1_ni
			a.m_no_e2_ni*b.m_no_e2_ni, // no_e2_ni
			a.m_e1_e2_ni*b.m_e1_e2_ni, // e1_e2_ni
			a.m_no_e3_ni*b.m_no_e3_ni, // no_e3_ni
			a.m_e1_e3_ni*b.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni*b.m_e2_e3_ni, // e2_e3_ni
			a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and oddVersor.
 */
public final static vectorE3GA hp(final vectorE3GA a, final oddVersor b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1*b.m_e1, // e1
			a.m_e2*b.m_e2, // e2
			a.m_e3*b.m_e3 // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of evenVersor and evenVersor.
 */
public final static evenVersor hp(final evenVersor a, final evenVersor b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar*b.m_scalar, // scalar
			a.m_no_e1*b.m_no_e1, // no_e1
			a.m_no_e2*b.m_no_e2, // no_e2
			a.m_no_e3*b.m_no_e3, // no_e3
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1, // e3_e1
			a.m_e1_ni*b.m_e1_ni, // e1_ni
			a.m_e2_ni*b.m_e2_ni, // e2_ni
			a.m_e3_ni*b.m_e3_ni, // e3_ni
			a.m_no_ni*b.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni*b.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni*b.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni*b.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni*b.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3*b.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of bivectorE3GA and rotorE3GA.
 */
public final static bivectorE3GA hp(final bivectorE3GA a, final rotorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of circle and evenVersor.
 */
public final static double hp(final circle a, final evenVersor b)
{
	return 0.0;

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of pseudoscalar and oddVersor.
 */
public final static pseudoscalar hp(final pseudoscalar a, final oddVersor b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a.m_no_e1_e2_e3_ni*b.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of rotorE3GA and bivectorE3GA.
 */
public final static bivectorE3GA hp(final rotorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of evenVersor and bivectorE3GA.
 */
public final static bivectorE3GA hp(final evenVersor a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2*b.m_e1_e2, // e1_e2
			a.m_e2_e3*b.m_e2_e3, // e2_e3
			a.m_e3_e1*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of sphere and plane.
 */
public final static plane hp(final sphere a, final plane b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni*b.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni*b.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni*b.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni*b.m_no_e1_e2_ni // no_e1_e2_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of normalizedPoint and e1_t.
 */
public final static vectorE3GA hp(final normalizedPoint a, final e1_t b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1, // e1
			0.0, // e2
			0.0 // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and e1_t.
 */
public final static vectorE3GA hp(final vectorE3GA a, final e1_t b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1, // e1
			0.0, // e2
			0.0 // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of I5_t and pseudoscalar.
 */
public final static pseudoscalar hp(final I5_t a, final pseudoscalar b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			b.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of pseudoscalar and I5i_t.
 */
public final static pseudoscalar hp(final pseudoscalar a, final I5i_t b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of mv and mv.
 */
public final static mv ihp(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		if (bc[0] != null) {
			cc[0] = new double[1];
			ihp_0_0(ac[0], bc[0], cc[0]);
		}
	}
	
	if (ac[1] != null) {
		if (bc[1] != null) {
			cc[1] = new double[5];
			ihp_1_1(ac[1], bc[1], cc[1]);
		}
	}
	
	if (ac[2] != null) {
		if (bc[2] != null) {
			cc[2] = new double[10];
			ihp_2_2(ac[2], bc[2], cc[2]);
		}
	}
	
	if (ac[3] != null) {
		if (bc[3] != null) {
			cc[3] = new double[10];
			ihp_3_3(ac[3], bc[3], cc[3]);
		}
	}
	
	if (ac[4] != null) {
		if (bc[4] != null) {
			cc[4] = new double[5];
			ihp_4_4(ac[4], bc[4], cc[4]);
		}
	}
	
	if (ac[5] != null) {
		if (bc[5] != null) {
			cc[5] = new double[1];
			ihp_5_5(ac[5], bc[5], cc[5]);
		}
	}
	return new mv(cc);
}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and vectorE3GA.
 */
public final static vectorE3GA ihp(final vectorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1/((b.m_e1)), // e1
			a.m_e2/((b.m_e2)), // e2
			a.m_e3/((b.m_e3)) // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of bivectorE3GA and bivectorE3GA.
 */
public final static bivectorE3GA ihp(final bivectorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)) // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of rotorE3GA and rotorE3GA.
 */
public final static rotorE3GA ihp(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar/((b.m_scalar)), // scalar
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)) // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of circle and oddVersor.
 */
public final static circle ihp(final circle a, final oddVersor b)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			a.m_no_e1_e2/((b.m_no_e1_e2)), // no_e1_e2
			a.m_no_e1_e3/((b.m_no_e1_e3)), // no_e1_e3
			a.m_no_e2_e3/((b.m_no_e2_e3)), // no_e2_e3
			a.m_e1_e2_e3/((b.m_e1_e2_e3)), // e1_e2_e3
			a.m_no_e1_ni/((b.m_no_e1_ni)), // no_e1_ni
			a.m_no_e2_ni/((b.m_no_e2_ni)), // no_e2_ni
			a.m_e1_e2_ni/((b.m_e1_e2_ni)), // e1_e2_ni
			a.m_no_e3_ni/((b.m_no_e3_ni)), // no_e3_ni
			a.m_e1_e3_ni/((b.m_e1_e3_ni)), // e1_e3_ni
			a.m_e2_e3_ni/((b.m_e2_e3_ni)) // e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and oddVersor.
 */
public final static vectorE3GA ihp(final vectorE3GA a, final oddVersor b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1/((b.m_e1)), // e1
			a.m_e2/((b.m_e2)), // e2
			a.m_e3/((b.m_e3)) // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of evenVersor and evenVersor.
 */
public final static evenVersor ihp(final evenVersor a, final evenVersor b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar/((b.m_scalar)), // scalar
			a.m_no_e1/((b.m_no_e1)), // no_e1
			a.m_no_e2/((b.m_no_e2)), // no_e2
			a.m_no_e3/((b.m_no_e3)), // no_e3
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)), // e3_e1
			a.m_e1_ni/((b.m_e1_ni)), // e1_ni
			a.m_e2_ni/((b.m_e2_ni)), // e2_ni
			a.m_e3_ni/((b.m_e3_ni)), // e3_ni
			a.m_no_ni/((b.m_no_ni)), // no_ni
			a.m_e1_e2_e3_ni/((b.m_e1_e2_e3_ni)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((b.m_no_e2_e3_ni)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((b.m_no_e1_e3_ni)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((b.m_no_e1_e2_ni)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((b.m_no_e1_e2_e3)) // no_e1_e2_e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of bivectorE3GA and rotorE3GA.
 */
public final static bivectorE3GA ihp(final bivectorE3GA a, final rotorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)) // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of plane and evenVersor.
 */
public final static plane ihp(final plane a, final evenVersor b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni/((b.m_e1_e2_e3_ni)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((b.m_no_e2_e3_ni)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((b.m_no_e1_e3_ni)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((b.m_no_e1_e2_ni)) // no_e1_e2_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of pseudoscalar and oddVersor.
 */
public final static pseudoscalar ihp(final pseudoscalar a, final oddVersor b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a.m_no_e1_e2_e3_ni/((b.m_no_e1_e2_e3_ni)) // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of rotorE3GA and bivectorE3GA.
 */
public final static bivectorE3GA ihp(final rotorE3GA a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)) // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of evenVersor and bivectorE3GA.
 */
public final static bivectorE3GA ihp(final evenVersor a, final bivectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((b.m_e1_e2)), // e1_e2
			a.m_e2_e3/((b.m_e2_e3)), // e2_e3
			-a.m_e3_e1/((-b.m_e3_e1)) // e3_e1
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of dualSphere and normalizedPoint.
 */
public final static dualSphere ihp(final dualSphere a, final normalizedPoint b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			a.m_no, // no
			a.m_e1/((b.m_e1)), // e1
			a.m_e2/((b.m_e2)), // e2
			a.m_e3/((b.m_e3)), // e3
			a.m_ni/((b.m_ni)) // ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of normalizedPoint and normalizedPoint.
 */
public final static normalizedPoint ihp(final normalizedPoint a, final normalizedPoint b)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			a.m_e1/((b.m_e1)), // e1
			a.m_e2/((b.m_e2)), // e2
			a.m_e3/((b.m_e3)), // e3
			a.m_ni/((b.m_ni)) // ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of vectorE3GA and e1_t.
 */
public final static vectorE3GA ihp(final vectorE3GA a, final e1_t b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1, // e1
			0.0, // e2
			0.0 // e3
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of I5_t and pseudoscalar.
 */
public final static pseudoscalar ihp(final I5_t a, final pseudoscalar b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			1.0 / (b.m_no_e1_e2_e3_ni) // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hadamard product (coordinate-wise multiplication) of pseudoscalar and I5i_t.
 */
public final static pseudoscalar ihp(final pseudoscalar a, final I5i_t b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns (a + 1).
 */
public final static mv increment(final mv_if a)
{
	mv _dst = new mv(a.to_mv());
	double val = _dst.get_scalar() + 1.0;
	_dst.set_scalar(val);
	return _dst;
}
/**
 * Returns (a + 1).
 */
public final static rotorE3GA increment(final bivectorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			1.0, // scalar
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);
}
/**
 * Returns (a + 1).
 */
public final static rotorE3GA increment(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(1.0+a.m_scalar), // scalar
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);
}
/**
 * Returns (a + 1).
 */
public final static evenVersor increment(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(1.0+a.m_scalar), // scalar
			a.m_no_e1, // no_e1
			a.m_no_e2, // no_e2
			a.m_no_e3, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni, // e3_ni
			a.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);
}
/**
 * Returns (a + 1).
 */
public final static evenVersor increment(final pointPair a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			1.0, // scalar
			a.m_no_e1, // no_e1
			a.m_no_e2, // no_e2
			a.m_no_e3, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni, // e3_ni
			a.m_no_ni, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}
/**
 * Returns (a + 1).
 */
public final static evenVersor increment(final noni_t a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			1.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			1.0, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}
/**
 * Returns (a - 1).
 */
public final static mv decrement(final mv_if a)
{
	mv _dst = new mv(a.to_mv());
	double val = _dst.get_scalar() - 1.0;
	_dst.set_scalar(val);
	return _dst;
}
/**
 * Returns (a - 1).
 */
public final static rotorE3GA decrement(final bivectorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			-1.0, // scalar
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);
}
/**
 * Returns (a - 1).
 */
public final static rotorE3GA decrement(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-1.0+a.m_scalar), // scalar
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);
}
/**
 * Returns (a - 1).
 */
public final static evenVersor decrement(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(-1.0+a.m_scalar), // scalar
			a.m_no_e1, // no_e1
			a.m_no_e2, // no_e2
			a.m_no_e3, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni, // e3_ni
			a.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);
}
/**
 * Returns (a - 1).
 */
public final static evenVersor decrement(final noni_t a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-1.0, // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			1.0, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}
/**
 * Returns (a - 1).
 */
public final static translator decrement(final freeVector a)
{
	return new translator(translator.coord_scalar_e1ni_e2ni_e3ni,
			-1.0, // scalar
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni // e3_ni
		);
}
/**
 * Returns scalar product of mv and mv.
 */
public final static double sp(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	cc[0] = new double[1];
	if (ac[0] != null) {
		if (bc[0] != null) {
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
	}
	if (ac[1] != null) {
		if (bc[1] != null) {
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
		}
	}
	if (ac[2] != null) {
		if (bc[2] != null) {
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
		}
	}
	if (ac[3] != null) {
		if (bc[3] != null) {
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
		}
	}
	if (ac[4] != null) {
		if (bc[4] != null) {
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
		}
	}
	if (ac[5] != null) {
		if (bc[5] != null) {
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return cc[0][0];
}
/**
 * Returns left contraction of mv and mv.
 */
public final static mv lc(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_0_1_1(ac[0], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_0_2_2(ac[0], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_0_3_3(ac[0], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_0_4_4(ac[0], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_0_5_5(ac[0], bc[5], cc[5]);
		}
	}
	if (ac[1] != null) {
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_2_1(ac[1], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_3_2(ac[1], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_4_3(ac[1], bc[4], cc[3]);
		}
		if (bc[5] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_5_4(ac[1], bc[5], cc[4]);
		}
	}
	if (ac[2] != null) {
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_3_1(ac[2], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_4_2(ac[2], bc[4], cc[2]);
		}
		if (bc[5] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_5_3(ac[2], bc[5], cc[3]);
		}
	}
	if (ac[3] != null) {
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_4_1(ac[3], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_5_2(ac[3], bc[5], cc[2]);
		}
	}
	if (ac[4] != null) {
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
		}
		if (bc[5] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_5_1(ac[4], bc[5], cc[1]);
		}
	}
	if (ac[5] != null) {
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns right contraction of mv and mv.
 */
public final static mv rc(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
	}
	if (ac[1] != null) {
		if (bc[0] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_0_1(ac[1], bc[0], cc[1]);
		}
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
		}
	}
	if (ac[2] != null) {
		if (bc[0] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_0_2(ac[2], bc[0], cc[2]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_1_1(ac[2], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
		}
	}
	if (ac[3] != null) {
		if (bc[0] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_0_3(ac[3], bc[0], cc[3]);
		}
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_1_2(ac[3], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_2_1(ac[3], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
		}
	}
	if (ac[4] != null) {
		if (bc[0] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_0_4(ac[4], bc[0], cc[4]);
		}
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_4_1_3(ac[4], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_4_2_2(ac[4], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_3_1(ac[4], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
		}
	}
	if (ac[5] != null) {
		if (bc[0] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_5_0_5(ac[5], bc[0], cc[5]);
		}
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_5_1_4(ac[5], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_5_2_3(ac[5], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_5_3_2(ac[5], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_5_4_1(ac[5], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns Hestenes inner product of mv and mv.
 */
public final static mv hip(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[1] != null) {
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_2_1(ac[1], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_3_2(ac[1], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_4_3(ac[1], bc[4], cc[3]);
		}
		if (bc[5] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_5_4(ac[1], bc[5], cc[4]);
		}
	}
	if (ac[2] != null) {
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_1_1(ac[2], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_3_1(ac[2], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_4_2(ac[2], bc[4], cc[2]);
		}
		if (bc[5] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_5_3(ac[2], bc[5], cc[3]);
		}
	}
	if (ac[3] != null) {
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_1_2(ac[3], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_2_1(ac[3], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_4_1(ac[3], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_5_2(ac[3], bc[5], cc[2]);
		}
	}
	if (ac[4] != null) {
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_4_1_3(ac[4], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_4_2_2(ac[4], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_3_1(ac[4], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
		}
		if (bc[5] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_5_1(ac[4], bc[5], cc[1]);
		}
	}
	if (ac[5] != null) {
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_5_1_4(ac[5], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_5_2_3(ac[5], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_5_3_2(ac[5], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_5_4_1(ac[5], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns Modified Hestenes inner product of mv and mv.
 */
public final static mv mhip(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_0_1_1(ac[0], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_0_2_2(ac[0], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_0_3_3(ac[0], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_0_4_4(ac[0], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_0_5_5(ac[0], bc[5], cc[5]);
		}
	}
	if (ac[1] != null) {
		if (bc[0] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_0_1(ac[1], bc[0], cc[1]);
		}
		if (bc[1] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_1_1_0(ac[1], bc[1], cc[0]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_2_1(ac[1], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_3_2(ac[1], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_4_3(ac[1], bc[4], cc[3]);
		}
		if (bc[5] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_5_4(ac[1], bc[5], cc[4]);
		}
	}
	if (ac[2] != null) {
		if (bc[0] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_0_2(ac[2], bc[0], cc[2]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_1_1(ac[2], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_2_2_0(ac[2], bc[2], cc[0]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_2_3_1(ac[2], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_4_2(ac[2], bc[4], cc[2]);
		}
		if (bc[5] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_5_3(ac[2], bc[5], cc[3]);
		}
	}
	if (ac[3] != null) {
		if (bc[0] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_0_3(ac[3], bc[0], cc[3]);
		}
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_1_2(ac[3], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_2_1(ac[3], bc[2], cc[1]);
		}
		if (bc[3] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_3_3_0(ac[3], bc[3], cc[0]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_3_4_1(ac[3], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_3_5_2(ac[3], bc[5], cc[2]);
		}
	}
	if (ac[4] != null) {
		if (bc[0] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_0_4(ac[4], bc[0], cc[4]);
		}
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_4_1_3(ac[4], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_4_2_2(ac[4], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_3_1(ac[4], bc[3], cc[1]);
		}
		if (bc[4] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_4_4_0(ac[4], bc[4], cc[0]);
		}
		if (bc[5] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_4_5_1(ac[4], bc[5], cc[1]);
		}
	}
	if (ac[5] != null) {
		if (bc[0] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_5_0_5(ac[5], bc[0], cc[5]);
		}
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_5_1_4(ac[5], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_5_2_3(ac[5], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_5_3_2(ac[5], bc[3], cc[2]);
		}
		if (bc[4] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_5_4_1(ac[5], bc[4], cc[1]);
		}
		if (bc[5] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_5_5_0(ac[5], bc[5], cc[0]);
		}
	}
	return new mv(cc);
}
/**
 * Returns scalar product of vectorE3GA and vectorE3GA.
 */
public final static double sp(final vectorE3GA a, final vectorE3GA b)
{
	return (a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3);

}
/**
 * Returns left contraction of vectorE3GA and plane.
 */
public final static line lc(final vectorE3GA a, final plane b)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			a.m_e3*b.m_e1_e2_e3_ni, // e1_e2_ni
			-a.m_e2*b.m_e1_e2_e3_ni, // e1_e3_ni
			a.m_e1*b.m_e1_e2_e3_ni, // e2_e3_ni
			-(a.m_e2*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e1_e3_ni), // e1_no_ni
			-(-a.m_e1*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e2_e3_ni), // e2_no_ni
			-(-a.m_e1*b.m_no_e1_e3_ni-a.m_e2*b.m_no_e2_e3_ni) // e3_no_ni
		);

}
/**
 * Returns right contraction of vectorE3GA and line.
 */
public final static double rc(final vectorE3GA a, final line b)
{
	return 0.0;

}
/**
 * Returns Hestenes inner product of vectorE3GA and vectorE3GA.
 */
public final static double hip(final vectorE3GA a, final vectorE3GA b)
{
	return (a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3);

}
/**
 * Returns Modified Hestenes inner product of pseudoscalar and vectorE3GA.
 */
public final static plane mhip(final pseudoscalar a, final vectorE3GA b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			0.0, // e1_e2_e3_ni
			-a.m_no_e1_e2_e3_ni*b.m_e1, // no_e2_e3_ni
			a.m_no_e1_e2_e3_ni*b.m_e2, // no_e1_e3_ni
			-a.m_no_e1_e2_e3_ni*b.m_e3 // no_e1_e2_ni
		);

}
/**
 * Returns scalar product of bivectorE3GA and oddVersor.
 */
public final static double sp(final bivectorE3GA a, final oddVersor b)
{
	return 0.0;

}
/**
 * Returns left contraction of bivectorE3GA and vectorE3GA.
 */
public final static double lc(final bivectorE3GA a, final vectorE3GA b)
{
	return 0.0;

}
/**
 * Returns right contraction of evenVersor and vectorE3GA.
 */
public final static oddVersor rc(final evenVersor a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no_e1*b.m_e1+a.m_no_e2*b.m_e2+a.m_no_e3*b.m_e3), // no
			(a.m_e1_e2*b.m_e2-a.m_e3_e1*b.m_e3), // e1
			(-a.m_e1_e2*b.m_e1+a.m_e2_e3*b.m_e3), // e2
			(-a.m_e2_e3*b.m_e2+a.m_e3_e1*b.m_e1), // e3
			(-a.m_e1_ni*b.m_e1-a.m_e2_ni*b.m_e2-a.m_e3_ni*b.m_e3), // ni
			a.m_no_e1_e2_e3*b.m_e3, // no_e1_e2
			-a.m_no_e1_e2_e3*b.m_e2, // no_e1_e3
			a.m_no_e1_e2_e3*b.m_e1, // no_e2_e3
			0.0, // e1_e2_e3
			(-a.m_no_e1_e2_ni*b.m_e2-a.m_no_e1_e3_ni*b.m_e3), // no_e1_ni
			(a.m_no_e1_e2_ni*b.m_e1-a.m_no_e2_e3_ni*b.m_e3), // no_e2_ni
			-a.m_e1_e2_e3_ni*b.m_e3, // e1_e2_ni
			(a.m_no_e1_e3_ni*b.m_e1+a.m_no_e2_e3_ni*b.m_e2), // no_e3_ni
			a.m_e1_e2_e3_ni*b.m_e2, // e1_e3_ni
			-a.m_e1_e2_e3_ni*b.m_e1, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hestenes inner product of oddVersor and vectorE3GA.
 */
public final static evenVersor hip(final oddVersor a, final vectorE3GA b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_e1*b.m_e1+a.m_e2*b.m_e2+a.m_e3*b.m_e3), // scalar
			(a.m_no_e1_e2*b.m_e2+a.m_no_e1_e3*b.m_e3), // no_e1
			(-a.m_no_e1_e2*b.m_e1+a.m_no_e2_e3*b.m_e3), // no_e2
			(-a.m_no_e1_e3*b.m_e1-a.m_no_e2_e3*b.m_e2), // no_e3
			a.m_e1_e2_e3*b.m_e3, // e1_e2
			a.m_e1_e2_e3*b.m_e1, // e2_e3
			a.m_e1_e2_e3*b.m_e2, // e3_e1
			(-a.m_e1_e2_ni*b.m_e2-a.m_e1_e3_ni*b.m_e3), // e1_ni
			(a.m_e1_e2_ni*b.m_e1-a.m_e2_e3_ni*b.m_e3), // e2_ni
			(a.m_e1_e3_ni*b.m_e1+a.m_e2_e3_ni*b.m_e2), // e3_ni
			(-a.m_no_e1_ni*b.m_e1-a.m_no_e2_ni*b.m_e2-a.m_no_e3_ni*b.m_e3), // no_ni
			0.0, // e1_e2_e3_ni
			-a.m_no_e1_e2_e3_ni*b.m_e1, // no_e2_e3_ni
			a.m_no_e1_e2_e3_ni*b.m_e2, // no_e1_e3_ni
			-a.m_no_e1_e2_e3_ni*b.m_e3, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);

}
/**
 * Returns Modified Hestenes inner product of bivectorE3GA and vectorE3GA.
 */
public final static vectorE3GA mhip(final bivectorE3GA a, final vectorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			(a.m_e1_e2*b.m_e2-a.m_e3_e1*b.m_e3), // e1
			(-a.m_e1_e2*b.m_e1+a.m_e2_e3*b.m_e3), // e2
			(-a.m_e2_e3*b.m_e2+a.m_e3_e1*b.m_e1) // e3
		);

}
/**
 * Returns scalar product of vectorE3GA and pseudoscalar.
 */
public final static double sp(final vectorE3GA a, final pseudoscalar b)
{
	return 0.0;

}
/**
 * Returns left contraction of vectorE3GA and evenVersor.
 */
public final static oddVersor lc(final vectorE3GA a, final evenVersor b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(-a.m_e1*b.m_no_e1-a.m_e2*b.m_no_e2-a.m_e3*b.m_no_e3), // no
			(-a.m_e2*b.m_e1_e2+a.m_e3*b.m_e3_e1), // e1
			(a.m_e1*b.m_e1_e2-a.m_e3*b.m_e2_e3), // e2
			(-a.m_e1*b.m_e3_e1+a.m_e2*b.m_e2_e3), // e3
			(a.m_e1*b.m_e1_ni+a.m_e2*b.m_e2_ni+a.m_e3*b.m_e3_ni), // ni
			-a.m_e3*b.m_no_e1_e2_e3, // no_e1_e2
			a.m_e2*b.m_no_e1_e2_e3, // no_e1_e3
			-a.m_e1*b.m_no_e1_e2_e3, // no_e2_e3
			0.0, // e1_e2_e3
			(a.m_e2*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e1_e3_ni), // no_e1_ni
			(-a.m_e1*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e2_e3_ni), // no_e2_ni
			a.m_e3*b.m_e1_e2_e3_ni, // e1_e2_ni
			(-a.m_e1*b.m_no_e1_e3_ni-a.m_e2*b.m_no_e2_e3_ni), // no_e3_ni
			-a.m_e2*b.m_e1_e2_e3_ni, // e1_e3_ni
			a.m_e1*b.m_e1_e2_e3_ni, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns right contraction of vectorE3GA and bivectorE3GA.
 */
public final static double rc(final vectorE3GA a, final bivectorE3GA b)
{
	return 0.0;

}
/**
 * Returns Hestenes inner product of vectorE3GA and plane.
 */
public final static line hip(final vectorE3GA a, final plane b)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			a.m_e3*b.m_e1_e2_e3_ni, // e1_e2_ni
			-a.m_e2*b.m_e1_e2_e3_ni, // e1_e3_ni
			a.m_e1*b.m_e1_e2_e3_ni, // e2_e3_ni
			-(a.m_e2*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e1_e3_ni), // e1_no_ni
			-(-a.m_e1*b.m_no_e1_e2_ni+a.m_e3*b.m_no_e2_e3_ni), // e2_no_ni
			-(-a.m_e1*b.m_no_e1_e3_ni-a.m_e2*b.m_no_e2_e3_ni) // e3_no_ni
		);

}
/**
 * Returns Modified Hestenes inner product of vectorE3GA and line.
 */
public final static flatPoint mhip(final vectorE3GA a, final line b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			(-a.m_e2*b.m_e1_e2_ni-a.m_e3*b.m_e1_e3_ni), // e1_ni
			(a.m_e1*b.m_e1_e2_ni-a.m_e3*b.m_e2_e3_ni), // e2_ni
			(a.m_e1*b.m_e1_e3_ni+a.m_e2*b.m_e2_e3_ni), // e3_ni
			(a.m_e1*b.m_e1_no_ni+a.m_e2*b.m_e2_no_ni+a.m_e3*b.m_e3_no_ni) // no_ni
		);

}
/**
 * Returns scalar product of evenVersor and rotorE3GA.
 */
public final static double sp(final evenVersor a, final rotorE3GA b)
{
	return (-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1+a.m_scalar*b.m_scalar);

}
/**
 * Returns left contraction of evenVersor and rotorE3GA.
 */
public final static rotorE3GA lc(final evenVersor a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1+a.m_scalar*b.m_scalar), // scalar
			a.m_scalar*b.m_e1_e2, // e1_e2
			a.m_scalar*b.m_e2_e3, // e2_e3
			a.m_scalar*b.m_e3_e1 // e3_e1
		);

}
/**
 * Returns right contraction of oddVersor and rotorE3GA.
 */
public final static oddVersor rc(final oddVersor a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no*b.m_scalar-a.m_no_e1_e2*b.m_e1_e2+a.m_no_e1_e3*b.m_e3_e1-a.m_no_e2_e3*b.m_e2_e3), // no
			(a.m_e1*b.m_scalar-a.m_e1_e2_e3*b.m_e2_e3), // e1
			(-a.m_e1_e2_e3*b.m_e3_e1+a.m_e2*b.m_scalar), // e2
			(-a.m_e1_e2_e3*b.m_e1_e2+a.m_e3*b.m_scalar), // e3
			(-a.m_e1_e2_ni*b.m_e1_e2+a.m_e1_e3_ni*b.m_e3_e1-a.m_e2_e3_ni*b.m_e2_e3+a.m_ni*b.m_scalar), // ni
			a.m_no_e1_e2*b.m_scalar, // no_e1_e2
			a.m_no_e1_e3*b.m_scalar, // no_e1_e3
			a.m_no_e2_e3*b.m_scalar, // no_e2_e3
			a.m_e1_e2_e3*b.m_scalar, // e1_e2_e3
			(-a.m_no_e1_e2_e3_ni*b.m_e2_e3+a.m_no_e1_ni*b.m_scalar), // no_e1_ni
			(-a.m_no_e1_e2_e3_ni*b.m_e3_e1+a.m_no_e2_ni*b.m_scalar), // no_e2_ni
			a.m_e1_e2_ni*b.m_scalar, // e1_e2_ni
			(-a.m_no_e1_e2_e3_ni*b.m_e1_e2+a.m_no_e3_ni*b.m_scalar), // no_e3_ni
			a.m_e1_e3_ni*b.m_scalar, // e1_e3_ni
			a.m_e2_e3_ni*b.m_scalar, // e2_e3_ni
			a.m_no_e1_e2_e3_ni*b.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hestenes inner product of oddVersor and rotorE3GA.
 */
public final static oddVersor hip(final oddVersor a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(-a.m_no_e1_e2*b.m_e1_e2+a.m_no_e1_e3*b.m_e3_e1-a.m_no_e2_e3*b.m_e2_e3), // no
			(-a.m_e1_e2_e3*b.m_e2_e3-a.m_e2*b.m_e1_e2+a.m_e3*b.m_e3_e1), // e1
			(a.m_e1*b.m_e1_e2-a.m_e1_e2_e3*b.m_e3_e1-a.m_e3*b.m_e2_e3), // e2
			(-a.m_e1*b.m_e3_e1-a.m_e1_e2_e3*b.m_e1_e2+a.m_e2*b.m_e2_e3), // e3
			(-a.m_e1_e2_ni*b.m_e1_e2+a.m_e1_e3_ni*b.m_e3_e1-a.m_e2_e3_ni*b.m_e2_e3), // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-a.m_no_e1_e2_e3_ni*b.m_e2_e3, // no_e1_ni
			-a.m_no_e1_e2_e3_ni*b.m_e3_e1, // no_e2_ni
			0.0, // e1_e2_ni
			-a.m_no_e1_e2_e3_ni*b.m_e1_e2, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns Modified Hestenes inner product of oddVersor and rotorE3GA.
 */
public final static oddVersor mhip(final oddVersor a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			(a.m_no*b.m_scalar-a.m_no_e1_e2*b.m_e1_e2+a.m_no_e1_e3*b.m_e3_e1-a.m_no_e2_e3*b.m_e2_e3), // no
			(a.m_e1*b.m_scalar-a.m_e1_e2_e3*b.m_e2_e3-a.m_e2*b.m_e1_e2+a.m_e3*b.m_e3_e1), // e1
			(a.m_e1*b.m_e1_e2-a.m_e1_e2_e3*b.m_e3_e1+a.m_e2*b.m_scalar-a.m_e3*b.m_e2_e3), // e2
			(-a.m_e1*b.m_e3_e1-a.m_e1_e2_e3*b.m_e1_e2+a.m_e2*b.m_e2_e3+a.m_e3*b.m_scalar), // e3
			(-a.m_e1_e2_ni*b.m_e1_e2+a.m_e1_e3_ni*b.m_e3_e1-a.m_e2_e3_ni*b.m_e2_e3+a.m_ni*b.m_scalar), // ni
			a.m_no_e1_e2*b.m_scalar, // no_e1_e2
			a.m_no_e1_e3*b.m_scalar, // no_e1_e3
			a.m_no_e2_e3*b.m_scalar, // no_e2_e3
			a.m_e1_e2_e3*b.m_scalar, // e1_e2_e3
			(-a.m_no_e1_e2_e3_ni*b.m_e2_e3+a.m_no_e1_ni*b.m_scalar), // no_e1_ni
			(-a.m_no_e1_e2_e3_ni*b.m_e3_e1+a.m_no_e2_ni*b.m_scalar), // no_e2_ni
			a.m_e1_e2_ni*b.m_scalar, // e1_e2_ni
			(-a.m_no_e1_e2_e3_ni*b.m_e1_e2+a.m_no_e3_ni*b.m_scalar), // no_e3_ni
			a.m_e1_e3_ni*b.m_scalar, // e1_e3_ni
			a.m_e2_e3_ni*b.m_scalar, // e2_e3_ni
			a.m_no_e1_e2_e3_ni*b.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns scalar product of e1_t and rotorE3GA.
 */
public final static double sp(final e1_t a, final rotorE3GA b)
{
	return 0.0;

}
/**
 * Returns left contraction of e2_t and rotorE3GA.
 */
public final static vectorE3GA lc(final e2_t a, final rotorE3GA b)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-b.m_e1_e2, // e1
			0.0, // e2
			b.m_e2_e3 // e3
		);

}
/**
 * Returns right contraction of I5_t and rotorE3GA.
 */
public final static oddVersor rc(final I5_t a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-b.m_e2_e3, // no_e1_ni
			-b.m_e3_e1, // no_e2_ni
			0.0, // e1_e2_ni
			-b.m_e1_e2, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			b.m_scalar // no_e1_e2_e3_ni
		);

}
/**
 * Returns Hestenes inner product of I5i_t and rotorE3GA.
 */
public final static line hip(final I5i_t a, final rotorE3GA b)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			0.0, // e1_e2_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			-b.m_e2_e3, // e1_no_ni
			-b.m_e3_e1, // e2_no_ni
			-b.m_e1_e2 // e3_no_ni
		);

}
/**
 * Returns Modified Hestenes inner product of no_t and rotorE3GA.
 */
public final static dualSphere mhip(final no_t a, final rotorE3GA b)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			b.m_scalar, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0 // ni
		);

}
/**
 * Returns scalar product of e1_t and e1_t.
 */
public final static double sp(final e1_t a, final e1_t b)
{
	return 1.0;

}
/**
 * Returns left contraction of e2_t and e3_t.
 */
public final static double lc(final e2_t a, final e3_t b)
{
	return 0.0;

}
/**
 * Returns right contraction of I5_t and I5i_t.
 */
public final static double rc(final I5_t a, final I5i_t b)
{
	return 1.0;

}
/**
 * Returns Hestenes inner product of I5i_t and ni_t.
 */
public final static plane hip(final I5i_t a, final ni_t b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			1.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0 // no_e1_e2_ni
		);

}
/**
 * Returns Modified Hestenes inner product of no_t and e1_t.
 */
public final static double mhip(final no_t a, final e1_t b)
{
	return 0.0;

}
/**
 * Returns scalar product of rotorE3GA and plane.
 */
public final static double sp(final rotorE3GA a, final plane b)
{
	return 0.0;

}
/**
 * Returns left contraction of rotorE3GA and line.
 */
public final static oddVersor lc(final rotorE3GA a, final line b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			(-a.m_e1_e2*b.m_e1_e2_ni-a.m_e2_e3*b.m_e2_e3_ni+a.m_e3_e1*b.m_e1_e3_ni), // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-a.m_scalar*b.m_e1_no_ni, // no_e1_ni
			-a.m_scalar*b.m_e2_no_ni, // no_e2_ni
			a.m_scalar*b.m_e1_e2_ni, // e1_e2_ni
			-a.m_scalar*b.m_e3_no_ni, // no_e3_ni
			a.m_scalar*b.m_e1_e3_ni, // e1_e3_ni
			a.m_scalar*b.m_e2_e3_ni, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns right contraction of rotorE3GA and evenVersor.
 */
public final static rotorE3GA rc(final rotorE3GA a, final evenVersor b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1+a.m_scalar*b.m_scalar), // scalar
			a.m_e1_e2*b.m_scalar, // e1_e2
			a.m_e2_e3*b.m_scalar, // e2_e3
			a.m_e3_e1*b.m_scalar // e3_e1
		);

}
/**
 * Returns Hestenes inner product of rotorE3GA and bivectorE3GA.
 */
public final static double hip(final rotorE3GA a, final bivectorE3GA b)
{
	return (-a.m_e1_e2*b.m_e1_e2-a.m_e2_e3*b.m_e2_e3-a.m_e3_e1*b.m_e3_e1);

}
/**
 * Returns Modified Hestenes inner product of rotorE3GA and pseudoscalar.
 */
public final static oddVersor mhip(final rotorE3GA a, final pseudoscalar b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			0.0, // e1_e2_e3
			-a.m_e2_e3*b.m_no_e1_e2_e3_ni, // no_e1_ni
			-a.m_e3_e1*b.m_no_e1_e2_e3_ni, // no_e2_ni
			0.0, // e1_e2_ni
			-a.m_e1_e2*b.m_no_e1_e2_e3_ni, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			a.m_scalar*b.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns norm of mv using default metric.
 */
public final static double norm(final mv_if a)
{
	double n2 = 0.0;
	double[] c = new double[1];
	double[][] ac = a.to_mv().c();
	if (ac[0] != null) { /* group 0 (grade 0) */
		c[0] = 0.0;
			gp_default_0_0_0(ac[0], ac[0], c);
		n2 += c[0];
	}
	if (ac[1] != null) { /* group 1 (grade 1) */
		c[0] = 0.0;
			gp_default_1_1_0(ac[1], ac[1], c);
		n2 += c[0];
	}
	if (ac[2] != null) { /* group 2 (grade 2) */
		c[0] = 0.0;
			gp_default_2_2_0(ac[2], ac[2], c);
		n2 -= c[0];
	}
	if (ac[3] != null) { /* group 3 (grade 3) */
		c[0] = 0.0;
			gp_default_3_3_0(ac[3], ac[3], c);
		n2 -= c[0];
	}
	if (ac[4] != null) { /* group 4 (grade 4) */
		c[0] = 0.0;
			gp_default_4_4_0(ac[4], ac[4], c);
		n2 += c[0];
	}
	if (ac[5] != null) { /* group 5 (grade 5) */
		c[0] = 0.0;
			gp_default_5_5_0(ac[5], ac[5], c);
		n2 += c[0];
	}
	return ((n2 < 0.0) ? Math.sqrt(-n2) : Math.sqrt(n2));
}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final mv a) {
	return norm(a);
}
/**
 * Returns norm of vectorE3GA using default metric.
 */
public final static double norm(final vectorE3GA a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final vectorE3GA a) {
	return norm(a);
}
/**
 * Returns norm of bivectorE3GA using default metric.
 */
public final static double norm(final bivectorE3GA a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final bivectorE3GA a) {
	return norm(a);
}
/**
 * Returns norm of line using default metric.
 */
public final static double norm(final line a)
{
	return Math.abs(Math.sqrt(Math.abs((-a.m_e1_no_ni*a.m_e1_no_ni-a.m_e2_no_ni*a.m_e2_no_ni-a.m_e3_no_ni*a.m_e3_no_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final line a) {
	return norm(a);
}
/**
 * Returns norm of plane using default metric.
 */
public final static double norm(final plane a)
{
	return Math.abs(Math.sqrt(Math.abs((-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final plane a) {
	return norm(a);
}
/**
 * Returns norm of rotorE3GA using default metric.
 */
public final static double norm(final rotorE3GA a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final rotorE3GA a) {
	return norm(a);
}
/**
 * Returns norm of oddVersor using default metric.
 */
public final static double norm(final oddVersor a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final oddVersor a) {
	return norm(a);
}
/**
 * Returns norm of evenVersor using default metric.
 */
public final static double norm(final evenVersor a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final evenVersor a) {
	return norm(a);
}
/**
 * Returns norm of normalizedPoint using default metric.
 */
public final static double norm(final normalizedPoint a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final normalizedPoint a) {
	return norm(a);
}
/**
 * Returns norm of dualSphere using default metric.
 */
public final static double norm(final dualSphere a)
{
	return Math.abs(Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no))));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final dualSphere a) {
	return norm(a);
}
/**
 * Returns norm of pseudoscalar using default metric.
 */
public final static double norm(final pseudoscalar a)
{
	return Math.abs(Math.sqrt(Math.abs(-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final pseudoscalar a) {
	return norm(a);
}
/**
 * Returns norm of e1_t using default metric.
 */
public final static double norm(final e1_t a)
{
	return Math.abs(1.0);

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final e1_t a) {
	return norm(a);
}
/**
 * Returns norm of noni_t using default metric.
 */
public final static double norm(final noni_t a)
{
	return Math.abs(-1.0);

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final noni_t a) {
	return norm(a);
}
/**
 * Returns norm of I5i_t using default metric.
 */
public final static double norm(final I5i_t a)
{
	return Math.abs(-1.0);

}
/**
 * internal conversion function
 */
public final static double norm_returns_scalar(final I5i_t a) {
	return norm(a);
}
/**
 * Returns norm2 of mv using default metric.
 */
public final static double norm2(final mv_if a)
{
	double n2 = 0.0;
	double[] c = new double[1];
	double[][] ac = a.to_mv().c();
	if (ac[0] != null) { /* group 0 (grade 0) */
		c[0] = 0.0;
			gp_default_0_0_0(ac[0], ac[0], c);
		n2 += c[0];
	}
	if (ac[1] != null) { /* group 1 (grade 1) */
		c[0] = 0.0;
			gp_default_1_1_0(ac[1], ac[1], c);
		n2 += c[0];
	}
	if (ac[2] != null) { /* group 2 (grade 2) */
		c[0] = 0.0;
			gp_default_2_2_0(ac[2], ac[2], c);
		n2 -= c[0];
	}
	if (ac[3] != null) { /* group 3 (grade 3) */
		c[0] = 0.0;
			gp_default_3_3_0(ac[3], ac[3], c);
		n2 -= c[0];
	}
	if (ac[4] != null) { /* group 4 (grade 4) */
		c[0] = 0.0;
			gp_default_4_4_0(ac[4], ac[4], c);
		n2 += c[0];
	}
	if (ac[5] != null) { /* group 5 (grade 5) */
		c[0] = 0.0;
			gp_default_5_5_0(ac[5], ac[5], c);
		n2 += c[0];
	}
	return n2;
}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final mv a) {
	return norm2(a);
}
/**
 * Returns norm2 of vectorE3GA using default metric.
 */
public final static double norm2(final vectorE3GA a)
{
	return (a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final vectorE3GA a) {
	return norm2(a);
}
/**
 * Returns norm2 of bivectorE3GA using default metric.
 */
public final static double norm2(final bivectorE3GA a)
{
	return (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final bivectorE3GA a) {
	return norm2(a);
}
/**
 * Returns norm2 of normalizedPoint using default metric.
 */
public final static double norm2(final normalizedPoint a)
{
	return (a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final normalizedPoint a) {
	return norm2(a);
}
/**
 * Returns norm2 of dualSphere using default metric.
 */
public final static double norm2(final dualSphere a)
{
	return (a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final dualSphere a) {
	return norm2(a);
}
/**
 * Returns norm2 of circle using default metric.
 */
public final static double norm2(final circle a)
{
	return (a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final circle a) {
	return norm2(a);
}
/**
 * Returns norm2 of sphere using default metric.
 */
public final static double norm2(final sphere a)
{
	return (2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final sphere a) {
	return norm2(a);
}
/**
 * Returns norm2 of rotorE3GA using default metric.
 */
public final static double norm2(final rotorE3GA a)
{
	return (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final rotorE3GA a) {
	return norm2(a);
}
/**
 * Returns norm2 of oddVersor using default metric.
 */
public final static double norm2(final oddVersor a)
{
	return (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final oddVersor a) {
	return norm2(a);
}
/**
 * Returns norm2 of evenVersor using default metric.
 */
public final static double norm2(final evenVersor a)
{
	return (a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final evenVersor a) {
	return norm2(a);
}
/**
 * Returns norm2 of pseudoscalar using default metric.
 */
public final static double norm2(final pseudoscalar a)
{
	return -a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni;

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final pseudoscalar a) {
	return norm2(a);
}
/**
 * Returns norm2 of e3_t using default metric.
 */
public final static double norm2(final e3_t a)
{
	return 1.0;

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final e3_t a) {
	return norm2(a);
}
/**
 * Returns norm2 of no_t using default metric.
 */
public final static double norm2(final no_t a)
{
	return 0.0;

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final no_t a) {
	return norm2(a);
}
/**
 * Returns norm2 of I5_t using default metric.
 */
public final static double norm2(final I5_t a)
{
	return -1.0;

}
/**
 * internal conversion function
 */
public final static double norm2_returns_scalar(final I5_t a) {
	return norm2(a);
}
/**
 * Returns norm of mv using euclidean metric.
 */
public final static double norm_em(final mv_if a)
{
	double[] c = new double[1];
	double[][] ac = a.to_mv().c();
	double n2 = 0.0;
	
	if (ac[0] != null) { /* group 0 (grade 0) */
		c[0] = 0.0;
		gp_euclidean_0_0_0(ac[0], ac[ 0], c);
		n2 += c[0];
	}
	
	if (ac[1] != null) { /* group 1 (grade 1) */
		c[0] = 0.0;
		gp_euclidean_1_1_0(ac[1], ac[ 1], c);
		n2 += c[0];
	}
	
	if (ac[2] != null) { /* group 2 (grade 2) */
		c[0] = 0.0;
		gp_euclidean_2_2_0(ac[2], ac[ 2], c);
		n2 -= c[0];
	}
	
	if (ac[3] != null) { /* group 3 (grade 3) */
		c[0] = 0.0;
		gp_euclidean_3_3_0(ac[3], ac[ 3], c);
		n2 -= c[0];
	}
	
	if (ac[4] != null) { /* group 4 (grade 4) */
		c[0] = 0.0;
		gp_euclidean_4_4_0(ac[4], ac[ 4], c);
		n2 += c[0];
	}
	
	if (ac[5] != null) { /* group 5 (grade 5) */
		c[0] = 0.0;
		gp_euclidean_5_5_0(ac[5], ac[ 5], c);
		n2 += c[0];
	}
	return Math.sqrt(n2);
}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final mv a) {
	return norm_em(a);
}
/**
 * Returns norm of vectorE3GA using euclidean metric.
 */
public final static double norm_em(final vectorE3GA a)
{
	return Math.abs(Math.sqrt((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final vectorE3GA a) {
	return norm_em(a);
}
/**
 * Returns norm of bivectorE3GA using euclidean metric.
 */
public final static double norm_em(final bivectorE3GA a)
{
	return Math.abs(Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final bivectorE3GA a) {
	return norm_em(a);
}
/**
 * Returns norm of freeVector using euclidean metric.
 */
public final static double norm_em(final freeVector a)
{
	return Math.abs(Math.sqrt((a.m_e1_ni*a.m_e1_ni+a.m_e2_ni*a.m_e2_ni+a.m_e3_ni*a.m_e3_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final freeVector a) {
	return norm_em(a);
}
/**
 * Returns norm of freeBivector using euclidean metric.
 */
public final static double norm_em(final freeBivector a)
{
	return Math.abs(Math.sqrt((a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3_e1_ni*a.m_e3_e1_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final freeBivector a) {
	return norm_em(a);
}
/**
 * Returns norm of rotorE3GA using euclidean metric.
 */
public final static double norm_em(final rotorE3GA a)
{
	return Math.abs(Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final rotorE3GA a) {
	return norm_em(a);
}
/**
 * Returns norm of oddVersor using euclidean metric.
 */
public final static double norm_em(final oddVersor a)
{
	return Math.abs(Math.sqrt((a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2*a.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3*a.m_e3+a.m_ni*a.m_ni+a.m_no*a.m_no+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final oddVersor a) {
	return norm_em(a);
}
/**
 * Returns norm of evenVersor using euclidean metric.
 */
public final static double norm_em(final evenVersor a)
{
	return Math.abs(Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+a.m_e1_ni*a.m_e1_ni+a.m_e2_e3*a.m_e2_e3+a.m_e2_ni*a.m_e2_ni+a.m_e3_e1*a.m_e3_e1+a.m_e3_ni*a.m_e3_ni+a.m_no_e1*a.m_no_e1+a.m_no_e1_e2_e3*a.m_no_e1_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2*a.m_no_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_e3*a.m_no_e3+a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar)));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final evenVersor a) {
	return norm_em(a);
}
/**
 * Returns norm of pseudoscalar using euclidean metric.
 */
public final static double norm_em(final pseudoscalar a)
{
	return Math.abs(Math.sqrt(a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni));

}
/**
 * internal conversion function
 */
public final static double norm_em_returns_scalar(final pseudoscalar a) {
	return norm_em(a);
}
/**
 * Returns norm2 of mv using euclidean metric.
 */
public final static double norm2_em(final mv_if a)
{
	double[] c = new double[1];
	double[][] ac = a.to_mv().c();
	double n2 = 0.0;
	
	if (ac[0] != null) { /* group 0 (grade 0) */
		c[0] = 0.0;
		gp_euclidean_0_0_0(ac[0], ac[ 0], c);
		n2 += c[0];
	}
	
	if (ac[1] != null) { /* group 1 (grade 1) */
		c[0] = 0.0;
		gp_euclidean_1_1_0(ac[1], ac[ 1], c);
		n2 += c[0];
	}
	
	if (ac[2] != null) { /* group 2 (grade 2) */
		c[0] = 0.0;
		gp_euclidean_2_2_0(ac[2], ac[ 2], c);
		n2 -= c[0];
	}
	
	if (ac[3] != null) { /* group 3 (grade 3) */
		c[0] = 0.0;
		gp_euclidean_3_3_0(ac[3], ac[ 3], c);
		n2 -= c[0];
	}
	
	if (ac[4] != null) { /* group 4 (grade 4) */
		c[0] = 0.0;
		gp_euclidean_4_4_0(ac[4], ac[ 4], c);
		n2 += c[0];
	}
	
	if (ac[5] != null) { /* group 5 (grade 5) */
		c[0] = 0.0;
		gp_euclidean_5_5_0(ac[5], ac[ 5], c);
		n2 += c[0];
	}
	return n2;
}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final mv a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of vectorE3GA using euclidean metric.
 */
public final static double norm2_em(final vectorE3GA a)
{
	return (a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final vectorE3GA a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of bivectorE3GA using euclidean metric.
 */
public final static double norm2_em(final bivectorE3GA a)
{
	return (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final bivectorE3GA a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of line using euclidean metric.
 */
public final static double norm2_em(final line a)
{
	return (a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e1_no_ni*a.m_e1_no_ni+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e2_no_ni*a.m_e2_no_ni+a.m_e3_no_ni*a.m_e3_no_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final line a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of plane using euclidean metric.
 */
public final static double norm2_em(final plane a)
{
	return (a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final plane a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of rotorE3GA using euclidean metric.
 */
public final static double norm2_em(final rotorE3GA a)
{
	return (a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final rotorE3GA a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of oddVersor using euclidean metric.
 */
public final static double norm2_em(final oddVersor a)
{
	return (a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2*a.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3*a.m_e3+a.m_ni*a.m_ni+a.m_no*a.m_no+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final oddVersor a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of evenVersor using euclidean metric.
 */
public final static double norm2_em(final evenVersor a)
{
	return (a.m_e1_e2*a.m_e1_e2+a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+a.m_e1_ni*a.m_e1_ni+a.m_e2_e3*a.m_e2_e3+a.m_e2_ni*a.m_e2_ni+a.m_e3_e1*a.m_e3_e1+a.m_e3_ni*a.m_e3_ni+a.m_no_e1*a.m_no_e1+a.m_no_e1_e2_e3*a.m_no_e1_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2*a.m_no_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_e3*a.m_no_e3+a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar);

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final evenVersor a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of pseudoscalar using euclidean metric.
 */
public final static double norm2_em(final pseudoscalar a)
{
	return a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni;

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final pseudoscalar a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of no_t using euclidean metric.
 */
public final static double norm2_em(final no_t a)
{
	return 1.0;

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final no_t a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of I5_t using euclidean metric.
 */
public final static double norm2_em(final I5_t a)
{
	return 1.0;

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final I5_t a) {
	return norm2_em(a);
}
/**
 * Returns norm2 of e1_t using euclidean metric.
 */
public final static double norm2_em(final e1_t a)
{
	return 1.0;

}
/**
 * internal conversion function
 */
public final static double norm2_em_returns_scalar(final e1_t a) {
	return norm2_em(a);
}
/**
 * Returns outer product of mv and mv.
 */
public final static mv op(final mv_if a, final mv_if b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = b.to_mv().c();
	double[][] cc = new double[6][];
	if (ac[0] != null) {
		if (bc[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
		}
		if (bc[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_0_1_1(ac[0], bc[1], cc[1]);
		}
		if (bc[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_0_2_2(ac[0], bc[2], cc[2]);
		}
		if (bc[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_0_3_3(ac[0], bc[3], cc[3]);
		}
		if (bc[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_0_4_4(ac[0], bc[4], cc[4]);
		}
		if (bc[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_0_5_5(ac[0], bc[5], cc[5]);
		}
	}
	if (ac[1] != null) {
		if (bc[0] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_0_1(ac[1], bc[0], cc[1]);
		}
		if (bc[1] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_1_1_2(ac[1], bc[1], cc[2]);
		}
		if (bc[2] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_1_2_3(ac[1], bc[2], cc[3]);
		}
		if (bc[3] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_1_3_4(ac[1], bc[3], cc[4]);
		}
		if (bc[4] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_1_4_5(ac[1], bc[4], cc[5]);
		}
	}
	if (ac[2] != null) {
		if (bc[0] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_0_2(ac[2], bc[0], cc[2]);
		}
		if (bc[1] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_2_1_3(ac[2], bc[1], cc[3]);
		}
		if (bc[2] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_2_2_4(ac[2], bc[2], cc[4]);
		}
		if (bc[3] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_2_3_5(ac[2], bc[3], cc[5]);
		}
	}
	if (ac[3] != null) {
		if (bc[0] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_0_3(ac[3], bc[0], cc[3]);
		}
		if (bc[1] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_3_1_4(ac[3], bc[1], cc[4]);
		}
		if (bc[2] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_3_2_5(ac[3], bc[2], cc[5]);
		}
	}
	if (ac[4] != null) {
		if (bc[0] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_0_4(ac[4], bc[0], cc[4]);
		}
		if (bc[1] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_4_1_5(ac[4], bc[1], cc[5]);
		}
	}
	if (ac[5] != null) {
		if (bc[0] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_5_0_5(ac[5], bc[0], cc[5]);
		}
	}
	return new mv(cc);
}
/**
 * Returns outer product of vectorE3GA and vectorE3GA.
 */
public final static bivectorE3GA op(final vectorE3GA a, final vectorE3GA b)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			(a.m_e1*b.m_e2-a.m_e2*b.m_e1), // e1_e2
			(a.m_e2*b.m_e3-a.m_e3*b.m_e2), // e2_e3
			-(a.m_e1*b.m_e3-a.m_e3*b.m_e1) // e3_e1
		);

}
/**
 * Returns outer product of rotorE3GA and vectorE3GA.
 */
public final static oddVersor op(final rotorE3GA a, final vectorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			a.m_scalar*b.m_e1, // e1
			a.m_scalar*b.m_e2, // e2
			a.m_scalar*b.m_e3, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1_e2*b.m_e3+a.m_e2_e3*b.m_e1+a.m_e3_e1*b.m_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns outer product of vectorE3GA and line.
 */
public final static plane op(final vectorE3GA a, final line b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			(a.m_e1*b.m_e2_e3_ni-a.m_e2*b.m_e1_e3_ni+a.m_e3*b.m_e1_e2_ni), // e1_e2_e3_ni
			(a.m_e2*b.m_e3_no_ni-a.m_e3*b.m_e2_no_ni), // no_e2_e3_ni
			(a.m_e1*b.m_e3_no_ni-a.m_e3*b.m_e1_no_ni), // no_e1_e3_ni
			(a.m_e1*b.m_e2_no_ni-a.m_e2*b.m_e1_no_ni) // no_e1_e2_ni
		);

}
/**
 * Returns outer product of rotorE3GA and circle.
 */
public final static oddVersor op(final rotorE3GA a, final circle b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0, // ni
			a.m_scalar*b.m_no_e1_e2, // no_e1_e2
			a.m_scalar*b.m_no_e1_e3, // no_e1_e3
			a.m_scalar*b.m_no_e2_e3, // no_e2_e3
			a.m_scalar*b.m_e1_e2_e3, // e1_e2_e3
			a.m_scalar*b.m_no_e1_ni, // no_e1_ni
			a.m_scalar*b.m_no_e2_ni, // no_e2_ni
			a.m_scalar*b.m_e1_e2_ni, // e1_e2_ni
			a.m_scalar*b.m_no_e3_ni, // no_e3_ni
			a.m_scalar*b.m_e1_e3_ni, // e1_e3_ni
			a.m_scalar*b.m_e2_e3_ni, // e2_e3_ni
			(a.m_e1_e2*b.m_no_e3_ni+a.m_e2_e3*b.m_no_e1_ni+a.m_e3_e1*b.m_no_e2_ni) // no_e1_e2_e3_ni
		);

}
/**
 * Returns outer product of vectorE3GA and rotorE3GA.
 */
public final static oddVersor op(final vectorE3GA a, final rotorE3GA b)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			0.0, // no
			a.m_e1*b.m_scalar, // e1
			a.m_e2*b.m_scalar, // e2
			a.m_e3*b.m_scalar, // e3
			0.0, // ni
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			(a.m_e1*b.m_e2_e3+a.m_e2*b.m_e3_e1+a.m_e3*b.m_e1_e2), // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0, // e2_e3_ni
			0.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns outer product of rotorE3GA and rotorE3GA.
 */
public final static rotorE3GA op(final rotorE3GA a, final rotorE3GA b)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar*b.m_scalar, // scalar
			(a.m_e1_e2*b.m_scalar+a.m_scalar*b.m_e1_e2), // e1_e2
			(a.m_e2_e3*b.m_scalar+a.m_scalar*b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1*b.m_scalar-a.m_scalar*b.m_e3_e1) // e3_e1
		);

}
/**
 * Returns outer product of plane and rotorE3GA.
 */
public final static plane op(final plane a, final rotorE3GA b)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni*b.m_scalar, // e1_e2_e3_ni
			a.m_no_e2_e3_ni*b.m_scalar, // no_e2_e3_ni
			a.m_no_e1_e3_ni*b.m_scalar, // no_e1_e3_ni
			a.m_no_e1_e2_ni*b.m_scalar // no_e1_e2_ni
		);

}
/**
 * Returns outer product of circle and pointPair.
 */
public final static pseudoscalar op(final circle a, final pointPair b)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			(-a.m_e1_e2_e3*b.m_no_ni+a.m_e1_e2_ni*b.m_no_e3-a.m_e1_e3_ni*b.m_no_e2+a.m_e2_e3_ni*b.m_no_e1+a.m_no_e1_e2*b.m_e3_ni-a.m_no_e1_e3*b.m_e2_ni+a.m_no_e1_ni*b.m_e2_e3+a.m_no_e2_e3*b.m_e1_ni+a.m_no_e2_ni*b.m_e3_e1+a.m_no_e3_ni*b.m_e1_e2) // no_e1_e2_e3_ni
		);

}
/**
 * Returns outer product of bivectorE3GA and bivectorE3GA.
 */
public final static double op(final bivectorE3GA a, final bivectorE3GA b)
{
	return 0.0;

}
/**
 * Returns outer product of pseudoscalar and oddVersor.
 */
public final static double op(final pseudoscalar a, final oddVersor b)
{
	return 0.0;

}
/**
 * Returns outer product of no_t and ni_t.
 */
public final static noni_t op(final no_t a, final ni_t b)
{
	return new noni_t(		);

}
/**
 * Returns outer product of ni_t and no_t.
 */
public final static flatPoint op(final ni_t a, final no_t b)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			-1.0 // no_ni
		);

}
/**
 * Returns geometric product of mv and double.
 */
public final static mv gp(final mv_if a, final double b)
{
	double[][] ac = a.to_mv().c();
	double[][] bc = new double[][]{new double[]{b}};
	double[][] cc = new double[6][];
	if (ac[0] != null) {
			if (cc[0] == null) cc[0] = new double[1];
			gp_default_0_0_0(ac[0], bc[0], cc[0]);
	}
	if (ac[1] != null) {
			if (cc[1] == null) cc[1] = new double[5];
			gp_default_1_0_1(ac[1], bc[0], cc[1]);
	}
	if (ac[2] != null) {
			if (cc[2] == null) cc[2] = new double[10];
			gp_default_2_0_2(ac[2], bc[0], cc[2]);
	}
	if (ac[3] != null) {
			if (cc[3] == null) cc[3] = new double[10];
			gp_default_3_0_3(ac[3], bc[0], cc[3]);
	}
	if (ac[4] != null) {
			if (cc[4] == null) cc[4] = new double[5];
			gp_default_4_0_4(ac[4], bc[0], cc[4]);
	}
	if (ac[5] != null) {
			if (cc[5] == null) cc[5] = new double[1];
			gp_default_5_0_5(ac[5], bc[0], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns double b * mv a + double c.
 */
public final static mv sas(final mv_if a, final double b, final double c)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyMul_0(ac[0], cc[0], b);
		cc[0][0] += c;
	}
	else if (c != 0.0) {
		cc[0] = new double[1];
	cc[0][0] = c;
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyMul_1(ac[1], cc[1], b);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyMul_2(ac[2], cc[2], b);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyMul_3(ac[3], cc[3], b);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyMul_4(ac[4], cc[4], b);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyMul_5(ac[5], cc[5], b);
	}
	return new mv(cc);
}

/**
 * Computes exponential of mv up to 12th term.
 * 
 */
public final static mv exp(final mv x) {
	return exp(x, 12);
}

/**
 * Computes exponential of mv.
 * 
 */
public final static mv exp(final mv x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		mv xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared < 0.0) {
				double a = Math.sqrt(-s_xSquared);
				return sas(x, Math.sin(a) / a, Math.cos(a));
			}
			else if (s_xSquared > 0.0) {
				double a = Math.sqrt(s_xSquared);
				return sas(x, Math.sinh(a) / a, Math.cosh(a));
			}
			else {
				return sas(x, 1.0, 1.0);
			}
		}
	}

	// else do general series eval . . .

	// result = 1 + ....	
	mv result = new mv(1.0);
	if (order == 0) return result;

	// find scale (power of 2) such that its norm is < 1
	long maxC = (long)x.largestCoordinate();
	int scale = 1;
	if (maxC > 1) scale <<= 1;
	while (maxC != 0)
	{
		maxC >>= 1;
		scale <<= 1;
	}

	// scale
	mv xScaled = gp(x, 1.0 / (double)scale); 

	// taylor series approximation
	mv xPow1 = new mv(1.0); 
	for (int i = 1; i <= order; i++) {
		mv xPow2 = gp(xPow1, xScaled);
		xPow1 = gp(xPow2, 1.0 / (double)i);
		
		result = add(result, xPow1); // result2 = result1 + xPow1
    }

	// undo scaling
	while (scale > 1)
	{
		result = gp(result, result);
		scale >>= 1;
	}
    
    return result;
} // end of exp()


/**
 * Computes exponential of pointPair up to 12th term.
 * 
 */
public final static evenVersor exp(final pointPair x) {
	return exp(x, 12);
}

/**
 * Computes exponential of pointPair.
 * 
 */
public final static evenVersor exp(final pointPair x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		evenVersor xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared < 0.0) {
				double a = Math.sqrt(-s_xSquared);
				return sas(x, Math.sin(a) / a, Math.cos(a));
			}
			else if (s_xSquared > 0.0) {
				double a = Math.sqrt(s_xSquared);
				return sas(x, Math.sinh(a) / a, Math.cosh(a));
			}
			else {
				return sas(x, 1.0, 1.0);
			}
		}
	}

	// else do general series eval . . .

	// result = 1 + ....	
	evenVersor result = new evenVersor(1.0);
	if (order == 0) return result;

	// find scale (power of 2) such that its norm is < 1
	long maxC = (long)x.largestCoordinate();
	int scale = 1;
	if (maxC > 1) scale <<= 1;
	while (maxC != 0)
	{
		maxC >>= 1;
		scale <<= 1;
	}

	// scale
	pointPair xScaled = gp(x, 1.0 / (double)scale); 

	// taylor series approximation
	evenVersor xPow1 = new evenVersor(1.0); 
	for (int i = 1; i <= order; i++) {
		evenVersor xPow2 = gp(xPow1, xScaled);
		xPow1 = gp(xPow2, 1.0 / (double)i);
		
		result = add(result, xPow1); // result2 = result1 + xPow1
    }

	// undo scaling
	while (scale > 1)
	{
		result = gp(result, result);
		scale >>= 1;
	}
    
    return result;
} // end of exp()

/**
 * exp of freeVector (uses fast special case)
 */
public final static normalizedTranslator exp(final freeVector a)
{
	return new normalizedTranslator(normalizedTranslator.coord_e1ni_e2ni_e3ni,
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni // e3_ni
		);
}
/**
 * exp of noni_t (uses fast special case)
 */
public final static evenVersor exp(final noni_t a)
{
	double _alpha = Math.sqrt(Math.abs(0.99999999999999956));

	double _mul;
	if (_alpha != 0.0) {
		_mul = Math.sinh(_alpha)/((_alpha));

	}
	else {
		_mul = 0.0;

	}
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			Math.cosh(_alpha), // scalar
			0.0, // no_e1
			0.0, // no_e2
			0.0, // no_e3
			0.0, // e1_e2
			0.0, // e2_e3
			0.0, // e3_e1
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			_mul, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}

/**
 * Computes hyperbolic cosine of mv up to 12th term.
 * 
 */
public final static mv cosh(final mv x) {
	return cosh(x, 12);
}

/**
 * Computes hyperbolic cosine of mv.
 * 
 */
public final static mv cosh(final mv x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		mv xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared > 0.0) {
				return new mv(Math.cosh(Math.sqrt(s_xSquared)));
			}
			else if (s_xSquared < 0.0) {
				return new mv(Math.cos(Math.sqrt(-s_xSquared)));
			}
			else {
				return new mv(1.0);
			}
		}
	}

	// else do general series eval . . .


	mv result = new mv(1.0);
	if (order == 0) return result;

	// taylor series approximation
	mv xPow1 = new mv(1.0);
	for (int i = 1; i <= order; i++) {
		mv xPow2 = gp(xPow1, x);
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 2) == 0) {
			result = add(result, xPow1); 
		}
    }

    return result;
} // end of cosh()
/**
 * cosh of flatPoint (uses fast special case)
 */
public final static double cosh(final flatPoint a)
{
	double _alpha = Math.sqrt(Math.abs(0.99999999999999956*a.m_no_ni*a.m_no_ni));

	return Math.cosh(_alpha);
}

/**
 * Computes hyperbolic sine of mv up to 12th term.
 * 
 */
public final static mv sinh(final mv x) {
	return sinh(x, 12);
}

/**
 * Computes hyperbolic sine of mv.
 * 
 */
public final static mv sinh(final mv x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		mv xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared < 0.0) {
				double a = Math.sqrt(-s_xSquared);
				return sas(x, Math.sin(a) / a, 0.0);
			}
			else if (s_xSquared > 0.0) {
				double a = Math.sqrt(s_xSquared);
				return sas(x, Math.sinh(a) / a, 0.0);
			}
			else {
				return x;
			}
		}
	}

	// else do general series eval . . .

	// result = A +  A^3/3! + A^5/5!
	mv result = new mv(); // result = 0
    if (order == 0) return result;
    	
	// taylor series approximation
	mv xPow1 = new mv(1.0);
	for (int i = 1; i <= order; i++) {
		mv xPow2 = gp(xPow1, x); // xPow2 = xPow1 * x
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 2) == 1) {
			result = add(result, xPow1); 
		}
	}

    return result;
} // end of sinh()
/**
 * sinh of bivectorE3GA (uses fast special case)
 */
public final static bivectorE3GA sinh(final bivectorE3GA a)
{
	double _alpha = Math.sqrt(Math.abs((-a.m_e1_e2*a.m_e1_e2-a.m_e2_e3*a.m_e2_e3-a.m_e3_e1*a.m_e3_e1)));

	double _mul;
	if (_alpha != 0.0) {
		_mul = Math.sin(_alpha)/((_alpha));

	}
	else {
		_mul = 0.0;

	}
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			_mul*a.m_e1_e2, // e1_e2
			_mul*a.m_e2_e3, // e2_e3
			_mul*a.m_e3_e1 // e3_e1
		);
}
/**
 * sinh of freeVector (uses fast special case)
 */
public final static freeVector sinh(final freeVector a)
{
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni // e3_ni
		);
}

/**
 * Computes cosine of mv up to 12th term.
 * 
 */
public final static mv cos(final mv x) {
	return cos(x, 12);
}

/**
 * Computes cosine of mv.
 * 
 */
public final static mv cos(final mv x, final int order) {
	{ // First try special cases: check if (x * x) is scalar
		mv xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared > 0.0) {
				return new mv(Math.cos(Math.sqrt(s_xSquared)));
			}
			else if (s_xSquared < 0.0) {
				return new mv(Math.cosh(Math.sqrt(-s_xSquared)));
			}
			else {
				return new mv(1.0);
			}
		}
	}

	// else do general series eval . . .


	mv result = new mv(1.0);
	if (order == 0) return result;

	// taylor series approximation
	mv xPow1 = new mv(1.0); // xPow1 = 1.0
	for (int i = 1; i <= order; i++) {
		mv xPow2 = gp(xPow1, x); // xPow2 = xPow1 * x
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 4) == 2)
		{
			result = subtract(result, xPow1); // result2 = result1 - xPow1
		}
		else if ((i % 4) == 0) 
		{
			result = add(result, xPow1); // result2 = result1 + xPow1
		}		
    }

	return result;
} // end of cos()
/**
 * cos of bivectorE3GA (uses fast special case)
 */
public final static double cos(final bivectorE3GA a)
{
	double _alpha = Math.sqrt(Math.abs((-a.m_e1_e2*a.m_e1_e2-a.m_e2_e3*a.m_e2_e3-a.m_e3_e1*a.m_e3_e1)));

	return Math.cosh(_alpha);
}

/**
 * Computes cosine of pointPair up to 12th term.
 * 
 */
public final static evenVersor cos(final pointPair x) {
	return cos(x, 12);
}

/**
 * Computes cosine of pointPair.
 * 
 */
public final static evenVersor cos(final pointPair x, final int order) {
	{ // First try special cases: check if (x * x) is scalar
		evenVersor xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared > 0.0) {
				return new evenVersor(Math.cos(Math.sqrt(s_xSquared)));
			}
			else if (s_xSquared < 0.0) {
				return new evenVersor(Math.cosh(Math.sqrt(-s_xSquared)));
			}
			else {
				return new evenVersor(1.0);
			}
		}
	}

	// else do general series eval . . .


	evenVersor result = new evenVersor(1.0);
	if (order == 0) return result;

	// taylor series approximation
	evenVersor xPow1 = new evenVersor(1.0); // xPow1 = 1.0
	for (int i = 1; i <= order; i++) {
		evenVersor xPow2 = gp(xPow1, x); // xPow2 = xPow1 * x
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 4) == 2)
		{
			result = subtract(result, xPow1); // result2 = result1 - xPow1
		}
		else if ((i % 4) == 0) 
		{
			result = add(result, xPow1); // result2 = result1 + xPow1
		}		
    }

	return result;
} // end of cos()
/**
 * cos of freeVector (uses fast special case)
 */
public final static double cos(final freeVector a)
{
	return 1.0;
}
/**
 * cos of noni_t (uses fast special case)
 */
public final static double cos(final noni_t a)
{
	double _alpha = Math.sqrt(Math.abs(0.99999999999999956));

	return Math.cos(_alpha);
}

/**
 * Computes sine of mv up to 12th term.
 * 
 */
public final static mv sin(final mv x) {
	return sin(x, 12);
}

/**
 * Computes sine of mv.
 * 
 */
public final static mv sin(final mv x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		mv xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared < 0.0) {
				double a = Math.sqrt(-s_xSquared);
				return sas(x, Math.sinh(a) / a, 0.0);
			}
			else if (s_xSquared > 0.0) {
				double a = Math.sqrt(s_xSquared);
				return sas(x, Math.sin(a) / a, 0.0);
			}
			else {
				return x;
			}
		}
	}

	// else do general series eval . . .

	// result = A -  ....	+ ... - ...
	mv result = new mv(); // result = 0;
    if (order == 0) return result;
    	
	// taylor series approximation
	mv xPow1 = new mv(1.0); // xPow1 = 1.0
	for (int i = 1; i <= order; i++) {
		mv xPow2 = gp(xPow1, x); // xPow2 = xPow1 * x
		
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 4) == 3)
		{
			result = subtract(result, xPow1); // result = result - xPow1
		}
		else if ((i % 4) == 1) 
		{
			result = add(result, xPow1); // result = result + xPow1
		}
	}

	return result;
} // end of sin()

/**
 * sin of bivectorE3GA (uses fast special case)
 */
public final static bivectorE3GA sin(final bivectorE3GA a)
{
	double _alpha = Math.sqrt(Math.abs((-a.m_e1_e2*a.m_e1_e2-a.m_e2_e3*a.m_e2_e3-a.m_e3_e1*a.m_e3_e1)));

	double _mul;
	if (_alpha != 0.0) {
		_mul = Math.sinh(_alpha)/((_alpha));

	}
	else {
		_mul = 0.0;

	}
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			_mul*a.m_e1_e2, // e1_e2
			_mul*a.m_e2_e3, // e2_e3
			_mul*a.m_e3_e1 // e3_e1
		);
}

/**
 * Computes sine of pointPair up to 12th term.
 * 
 */
public final static evenVersor sin(final pointPair x) {
	return sin(x, 12);
}

/**
 * Computes sine of pointPair.
 * 
 */
public final static evenVersor sin(final pointPair x, final int order) {
   
	{ // First try special cases: check if (x * x) is scalar
		evenVersor xSquared = gp(x, x);
		double s_xSquared = xSquared.get_scalar();
		if ((norm2_returns_scalar(xSquared) - s_xSquared * s_xSquared) < 1E-14) {
			// OK (x * x == ~scalar), so use special cases:
			if (s_xSquared < 0.0) {
				double a = Math.sqrt(-s_xSquared);
				return sas(x, Math.sinh(a) / a, 0.0);
			}
			else if (s_xSquared > 0.0) {
				double a = Math.sqrt(s_xSquared);
				return sas(x, Math.sin(a) / a, 0.0);
			}
			else {
				return _evenVersor(x);
			}
		}
	}

	// else do general series eval . . .

	// result = A -  ....	+ ... - ...
	evenVersor result = new evenVersor(); // result = 0;
    if (order == 0) return result;
    	
	// taylor series approximation
	evenVersor xPow1 = new evenVersor(1.0); // xPow1 = 1.0
	for (int i = 1; i <= order; i++) {
		evenVersor xPow2 = gp(xPow1, x); // xPow2 = xPow1 * x
		
		xPow1 = gp(xPow2, 1.0 / (double)i); // xPow1 = xScaled^i / i! 
		
		if ((i % 4) == 3)
		{
			result = subtract(result, xPow1); // result = result - xPow1
		}
		else if ((i % 4) == 1) 
		{
			result = add(result, xPow1); // result = result + xPow1
		}
	}

	return result;
} // end of sin()

/**
 * sin of freeVector (uses fast special case)
 */
public final static freeVector sin(final freeVector a)
{
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni // e3_ni
		);
}
/**
 * sin of noni_t (uses fast special case)
 */
public final static flatPoint sin(final noni_t a)
{
	double _alpha = Math.sqrt(Math.abs(0.99999999999999956));

	double _mul;
	if (_alpha != 0.0) {
		_mul = Math.sin(_alpha)/((_alpha));

	}
	else {
		_mul = 0.0;

	}
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			_mul // no_ni
		);
}
/**
 * Returns negation of mv.
 */
public final static mv negate(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		neg_0(ac[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		neg_1(ac[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		neg_2(ac[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		neg_3(ac[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		neg_4(ac[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		neg_5(ac[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns negation of vectorE3GA.
 */
public final static vectorE3GA negate(final vectorE3GA a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3 // e3
		);

}
/**
 * Returns negation of freeVector.
 */
public final static freeVector negate(final freeVector a)
{
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni // e3_ni
		);

}
/**
 * Returns negation of normalizedPoint.
 */
public final static dualSphere negate(final normalizedPoint a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-1.0, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni // ni
		);

}
/**
 * Returns negation of dualSphere.
 */
public final static dualSphere negate(final dualSphere a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-a.m_no, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni // ni
		);

}
/**
 * Returns negation of line.
 */
public final static line negate(final line a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			-a.m_e1_no_ni, // e1_no_ni
			-a.m_e2_no_ni, // e2_no_ni
			-a.m_e3_no_ni // e3_no_ni
		);

}
/**
 * Returns negation of sphere.
 */
public final static sphere negate(final sphere a)
{
	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			-a.m_no_e2_e3_ni, // no_e2_e3_ni
			-a.m_no_e1_e3_ni, // no_e1_e3_ni
			-a.m_no_e1_e2_ni, // no_e1_e2_ni
			-a.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns negation of rotorE3GA.
 */
public final static rotorE3GA negate(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			-a.m_scalar, // scalar
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns negation of oddVersor.
 */
public final static oddVersor negate(final oddVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			-a.m_no, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni, // ni
			-a.m_no_e1_e2, // no_e1_e2
			-a.m_no_e1_e3, // no_e1_e3
			-a.m_no_e2_e3, // no_e2_e3
			-a.m_e1_e2_e3, // e1_e2_e3
			-a.m_no_e1_ni, // no_e1_ni
			-a.m_no_e2_ni, // no_e2_ni
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_no_e3_ni, // no_e3_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns negation of evenVersor.
 */
public final static evenVersor negate(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			-a.m_scalar, // scalar
			-a.m_no_e1, // no_e1
			-a.m_no_e2, // no_e2
			-a.m_no_e3, // no_e3
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1, // e3_e1
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni, // e3_ni
			-a.m_no_ni, // no_ni
			-a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			-a.m_no_e2_e3_ni, // no_e2_e3_ni
			-a.m_no_e1_e3_ni, // no_e1_e3_ni
			-a.m_no_e1_e2_ni, // no_e1_e2_ni
			-a.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns negation of pseudoscalar.
 */
public final static pseudoscalar negate(final pseudoscalar a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns negation of e1_t.
 */
public final static vectorE3GA negate(final e1_t a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-1.0, // e1
			0.0, // e2
			0.0 // e3
		);

}
/**
 * Returns negation of e2_t.
 */
public final static vectorE3GA negate(final e2_t a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			0.0, // e1
			-1.0, // e2
			0.0 // e3
		);

}
/**
 * Returns negation of noni_t.
 */
public final static flatPoint negate(final noni_t a)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			-1.0 // no_ni
		);

}
/**
 * Returns negation of double.
 */
public final static double negate(final double a)
{
	return -a;

}
/**
 * Returns reverse of mv.
 */
public final static mv reverse(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyGroup_0(ac[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyGroup_1(ac[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		neg_2(ac[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		neg_3(ac[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyGroup_4(ac[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyGroup_5(ac[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns reverse of vectorE3GA.
 */
public final static vectorE3GA reverse(final vectorE3GA a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3 // e3
		);

}
/**
 * Returns reverse of bivectorE3GA.
 */
public final static bivectorE3GA reverse(final bivectorE3GA a)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns reverse of normalizedPoint.
 */
public final static normalizedPoint reverse(final normalizedPoint a)
{
	return new normalizedPoint(normalizedPoint.coord_e1_e2_e3_ni,
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			a.m_ni // ni
		);

}
/**
 * Returns reverse of dualSphere.
 */
public final static dualSphere reverse(final dualSphere a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			a.m_no, // no
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			a.m_ni // ni
		);

}
/**
 * Returns reverse of line.
 */
public final static line reverse(final line a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			-a.m_e1_no_ni, // e1_no_ni
			-a.m_e2_no_ni, // e2_no_ni
			-a.m_e3_no_ni // e3_no_ni
		);

}
/**
 * Returns reverse of circle.
 */
public final static circle reverse(final circle a)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			-a.m_no_e1_e2, // no_e1_e2
			-a.m_no_e1_e3, // no_e1_e3
			-a.m_no_e2_e3, // no_e2_e3
			-a.m_e1_e2_e3, // e1_e2_e3
			-a.m_no_e1_ni, // no_e1_ni
			-a.m_no_e2_ni, // no_e2_ni
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_no_e3_ni, // no_e3_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni // e2_e3_ni
		);

}
/**
 * Returns reverse of rotorE3GA.
 */
public final static rotorE3GA reverse(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar, // scalar
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns reverse of oddVersor.
 */
public final static oddVersor reverse(final oddVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no, // no
			a.m_e1, // e1
			a.m_e2, // e2
			a.m_e3, // e3
			a.m_ni, // ni
			-a.m_no_e1_e2, // no_e1_e2
			-a.m_no_e1_e3, // no_e1_e3
			-a.m_no_e2_e3, // no_e2_e3
			-a.m_e1_e2_e3, // e1_e2_e3
			-a.m_no_e1_ni, // no_e1_ni
			-a.m_no_e2_ni, // no_e2_ni
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_no_e3_ni, // no_e3_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns reverse of evenVersor.
 */
public final static evenVersor reverse(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar, // scalar
			-a.m_no_e1, // no_e1
			-a.m_no_e2, // no_e2
			-a.m_no_e3, // no_e3
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1, // e3_e1
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni, // e3_ni
			-a.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns reverse of pseudoscalar.
 */
public final static pseudoscalar reverse(final pseudoscalar a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns reverse of double.
 */
public final static double reverse(final double a)
{
	return a;

}
/**
 * Returns reverse of e1_t.
 */
public final static e1_t reverse(final e1_t a)
{
	return new e1_t(		);

}
/**
 * Returns reverse of e3_t.
 */
public final static e3_t reverse(final e3_t a)
{
	return new e3_t(		);

}
/**
 * Returns reverse of I5i_t.
 */
public final static pseudoscalar reverse(final I5i_t a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-1.0 // no_e1_e2_e3_ni
		);

}
/**
 * Returns Clifford conjugate of mv.
 */
public final static mv cliffordConjugate(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyGroup_0(ac[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		neg_1(ac[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		neg_2(ac[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyGroup_3(ac[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyGroup_4(ac[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		neg_5(ac[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns Clifford conjugate of vectorE3GA.
 */
public final static vectorE3GA cliffordConjugate(final vectorE3GA a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3 // e3
		);

}
/**
 * Returns Clifford conjugate of pointPair.
 */
public final static pointPair cliffordConjugate(final pointPair a)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			-a.m_no_e1, // no_e1
			-a.m_no_e2, // no_e2
			-a.m_no_e3, // no_e3
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1, // e3_e1
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni, // e3_ni
			-a.m_no_ni // no_ni
		);

}
/**
 * Returns Clifford conjugate of normalizedPoint.
 */
public final static dualSphere cliffordConjugate(final normalizedPoint a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-1.0, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni // ni
		);

}
/**
 * Returns Clifford conjugate of freeVector.
 */
public final static freeVector cliffordConjugate(final freeVector a)
{
	return new freeVector(freeVector.coord_e1ni_e2ni_e3ni,
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni // e3_ni
		);

}
/**
 * Returns Clifford conjugate of line.
 */
public final static line cliffordConjugate(final line a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			a.m_e1_e2_ni, // e1_e2_ni
			a.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni, // e2_e3_ni
			a.m_e1_no_ni, // e1_no_ni
			a.m_e2_no_ni, // e2_no_ni
			a.m_e3_no_ni // e3_no_ni
		);

}
/**
 * Returns Clifford conjugate of plane.
 */
public final static plane cliffordConjugate(final plane a)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni // no_e1_e2_ni
		);

}
/**
 * Returns Clifford conjugate of rotorE3GA.
 */
public final static rotorE3GA cliffordConjugate(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar, // scalar
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns Clifford conjugate of oddVersor.
 */
public final static oddVersor cliffordConjugate(final oddVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			-a.m_no, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni, // ni
			a.m_no_e1_e2, // no_e1_e2
			a.m_no_e1_e3, // no_e1_e3
			a.m_no_e2_e3, // no_e2_e3
			a.m_e1_e2_e3, // e1_e2_e3
			a.m_no_e1_ni, // no_e1_ni
			a.m_no_e2_ni, // no_e2_ni
			a.m_e1_e2_ni, // e1_e2_ni
			a.m_no_e3_ni, // no_e3_ni
			a.m_e1_e3_ni, // e1_e3_ni
			a.m_e2_e3_ni, // e2_e3_ni
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Clifford conjugate of evenVersor.
 */
public final static evenVersor cliffordConjugate(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar, // scalar
			-a.m_no_e1, // no_e1
			-a.m_no_e2, // no_e2
			-a.m_no_e3, // no_e3
			-a.m_e1_e2, // e1_e2
			-a.m_e2_e3, // e2_e3
			-a.m_e3_e1, // e3_e1
			-a.m_e1_ni, // e1_ni
			-a.m_e2_ni, // e2_ni
			-a.m_e3_ni, // e3_ni
			-a.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns Clifford conjugate of pseudoscalar.
 */
public final static pseudoscalar cliffordConjugate(final pseudoscalar a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns Clifford conjugate of double.
 */
public final static double cliffordConjugate(final double a)
{
	return a;

}
/**
 * Returns Clifford conjugate of e1_t.
 */
public final static vectorE3GA cliffordConjugate(final e1_t a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-1.0, // e1
			0.0, // e2
			0.0 // e3
		);

}
/**
 * Returns Clifford conjugate of noni_t.
 */
public final static flatPoint cliffordConjugate(final noni_t a)
{
	return new flatPoint(flatPoint.coord_e1ni_e2ni_e3ni_noni,
			0.0, // e1_ni
			0.0, // e2_ni
			0.0, // e3_ni
			-1.0 // no_ni
		);

}
/**
 * Returns Clifford conjugate of I3_t.
 */
public final static I3_t cliffordConjugate(final I3_t a)
{
	return new I3_t(		);

}
/**
 * Returns grade involution of mv.
 */
public final static mv gradeInvolution(final mv_if a)
{
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyGroup_0(ac[0], cc[0]);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		neg_1(ac[1], cc[1]);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyGroup_2(ac[2], cc[2]);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		neg_3(ac[3], cc[3]);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyGroup_4(ac[4], cc[4]);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		neg_5(ac[5], cc[5]);
	}
	return new mv(cc);
}
/**
 * Returns grade involution of vectorE3GA.
 */
public final static vectorE3GA gradeInvolution(final vectorE3GA a)
{
	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3 // e3
		);

}
/**
 * Returns grade involution of bivectorE3GA.
 */
public final static bivectorE3GA gradeInvolution(final bivectorE3GA a)
{
	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns grade involution of normalizedPoint.
 */
public final static dualSphere gradeInvolution(final normalizedPoint a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-1.0, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni // ni
		);

}
/**
 * Returns grade involution of dualSphere.
 */
public final static dualSphere gradeInvolution(final dualSphere a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-a.m_no, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni // ni
		);

}
/**
 * Returns grade involution of line.
 */
public final static line gradeInvolution(final line a)
{
	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			-a.m_e1_no_ni, // e1_no_ni
			-a.m_e2_no_ni, // e2_no_ni
			-a.m_e3_no_ni // e3_no_ni
		);

}
/**
 * Returns grade involution of plane.
 */
public final static plane gradeInvolution(final plane a)
{
	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni // no_e1_e2_ni
		);

}
/**
 * Returns grade involution of rotorE3GA.
 */
public final static rotorE3GA gradeInvolution(final rotorE3GA a)
{
	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar, // scalar
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1 // e3_e1
		);

}
/**
 * Returns grade involution of oddVersor.
 */
public final static oddVersor gradeInvolution(final oddVersor a)
{
	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			-a.m_no, // no
			-a.m_e1, // e1
			-a.m_e2, // e2
			-a.m_e3, // e3
			-a.m_ni, // ni
			-a.m_no_e1_e2, // no_e1_e2
			-a.m_no_e1_e3, // no_e1_e3
			-a.m_no_e2_e3, // no_e2_e3
			-a.m_e1_e2_e3, // e1_e2_e3
			-a.m_no_e1_ni, // no_e1_ni
			-a.m_no_e2_ni, // no_e2_ni
			-a.m_e1_e2_ni, // e1_e2_ni
			-a.m_no_e3_ni, // no_e3_ni
			-a.m_e1_e3_ni, // e1_e3_ni
			-a.m_e2_e3_ni, // e2_e3_ni
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns grade involution of evenVersor.
 */
public final static evenVersor gradeInvolution(final evenVersor a)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar, // scalar
			a.m_no_e1, // no_e1
			a.m_no_e2, // no_e2
			a.m_no_e3, // no_e3
			a.m_e1_e2, // e1_e2
			a.m_e2_e3, // e2_e3
			a.m_e3_e1, // e3_e1
			a.m_e1_ni, // e1_ni
			a.m_e2_ni, // e2_ni
			a.m_e3_ni, // e3_ni
			a.m_no_ni, // no_ni
			a.m_e1_e2_e3_ni, // e1_e2_e3_ni
			a.m_no_e2_e3_ni, // no_e2_e3_ni
			a.m_no_e1_e3_ni, // no_e1_e3_ni
			a.m_no_e1_e2_ni, // no_e1_e2_ni
			a.m_no_e1_e2_e3 // no_e1_e2_e3
		);

}
/**
 * Returns grade involution of pseudoscalar.
 */
public final static pseudoscalar gradeInvolution(final pseudoscalar a)
{
	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			-a.m_no_e1_e2_e3_ni // no_e1_e2_e3_ni
		);

}
/**
 * Returns grade involution of double.
 */
public final static double gradeInvolution(final double a)
{
	return a;

}
/**
 * Returns grade involution of no_t.
 */
public final static dualSphere gradeInvolution(final no_t a)
{
	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			-1.0, // no
			0.0, // e1
			0.0, // e2
			0.0, // e3
			0.0 // ni
		);

}
/**
 * Returns grade involution of ni_t.
 */
public final static dualPlane gradeInvolution(final ni_t a)
{
	return new dualPlane(dualPlane.coord_e1_e2_e3_ni,
			0.0, // e1
			0.0, // e2
			0.0, // e3
			-1.0 // ni
		);

}
/**
 * Returns grade involution of I3_t.
 */
public final static circle gradeInvolution(final I3_t a)
{
	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			0.0, // no_e1_e2
			0.0, // no_e1_e3
			0.0, // no_e2_e3
			-1.0, // e1_e2_e3
			0.0, // no_e1_ni
			0.0, // no_e2_ni
			0.0, // e1_e2_ni
			0.0, // no_e3_ni
			0.0, // e1_e3_ni
			0.0 // e2_e3_ni
		);

}
/**
 * Returns unit of mv using default metric.
 */
public final static mv unit(final mv_if a)
{
	double n = norm_returns_scalar(a.to_mv());
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyDiv_0(ac[0], cc[0], n);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyDiv_1(ac[1], cc[1], n);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyDiv_2(ac[2], cc[2], n);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyDiv_3(ac[3], cc[3], n);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyDiv_4(ac[4], cc[4], n);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyDiv_5(ac[5], cc[5], n);
	}
	return new mv(cc);
}
/**
 * Returns unit of vectorE3GA using default metric.
 */
public final static vectorE3GA unit(final vectorE3GA a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3)));

	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)) // e3
		);
}
/**
 * Returns unit of bivectorE3GA using default metric.
 */
public final static bivectorE3GA unit(final bivectorE3GA a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1)));

	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)) // e3_e1
		);
}
/**
 * Returns unit of line using default metric.
 */
public final static line unit(final line a)
{
	double _n_ = Math.sqrt(Math.abs((-a.m_e1_no_ni*a.m_e1_no_ni-a.m_e2_no_ni*a.m_e2_no_ni-a.m_e3_no_ni*a.m_e3_no_ni)));

	return new line(line.coord_e1e2ni_e1e3ni_e2e3ni_e1noni_e2noni_e3noni,
			a.m_e1_e2_ni/((_n_)), // e1_e2_ni
			a.m_e1_e3_ni/((_n_)), // e1_e3_ni
			a.m_e2_e3_ni/((_n_)), // e2_e3_ni
			a.m_e1_no_ni/((_n_)), // e1_no_ni
			a.m_e2_no_ni/((_n_)), // e2_no_ni
			a.m_e3_no_ni/((_n_)) // e3_no_ni
		);
}
/**
 * Returns unit of sphere using default metric.
 */
public final static sphere unit(final sphere a)
{
	double _n_ = Math.sqrt(Math.abs((2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni)));

	return new sphere(sphere.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_e1_e2_e3_ni/((_n_)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((_n_)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((_n_)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((_n_)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((_n_)) // no_e1_e2_e3
		);
}
/**
 * Returns unit of rotorE3GA using default metric.
 */
public final static rotorE3GA unit(final rotorE3GA a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar)));

	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar/((_n_)), // scalar
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)) // e3_e1
		);
}
/**
 * Returns unit of oddVersor using default metric.
 */
public final static oddVersor unit(final oddVersor a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+-2.0*a.m_e1_e2_ni*a.m_no_e1_e2+-2.0*a.m_e1_e3_ni*a.m_no_e1_e3+a.m_e2*a.m_e2+-2.0*a.m_e2_e3_ni*a.m_no_e2_e3+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni-a.m_no_e1_ni*a.m_no_e1_ni-a.m_no_e2_ni*a.m_no_e2_ni-a.m_no_e3_ni*a.m_no_e3_ni)));

	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no/((_n_)), // no
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)), // e3
			a.m_ni/((_n_)), // ni
			a.m_no_e1_e2/((_n_)), // no_e1_e2
			a.m_no_e1_e3/((_n_)), // no_e1_e3
			a.m_no_e2_e3/((_n_)), // no_e2_e3
			a.m_e1_e2_e3/((_n_)), // e1_e2_e3
			a.m_no_e1_ni/((_n_)), // no_e1_ni
			a.m_no_e2_ni/((_n_)), // no_e2_ni
			a.m_e1_e2_ni/((_n_)), // e1_e2_ni
			a.m_no_e3_ni/((_n_)), // no_e3_ni
			a.m_e1_e3_ni/((_n_)), // e1_e3_ni
			a.m_e2_e3_ni/((_n_)), // e2_e3_ni
			a.m_no_e1_e2_e3_ni/((_n_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns unit of evenVersor using default metric.
 */
public final static evenVersor unit(final evenVersor a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_e2_e3_ni*a.m_no_e1_e2_e3+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_e1_e2_ni*a.m_no_e1_e2_ni-a.m_no_e1_e3_ni*a.m_no_e1_e3_ni-a.m_no_e2_e3_ni*a.m_no_e2_e3_ni-a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar)));

	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar/((_n_)), // scalar
			a.m_no_e1/((_n_)), // no_e1
			a.m_no_e2/((_n_)), // no_e2
			a.m_no_e3/((_n_)), // no_e3
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)), // e3_e1
			a.m_e1_ni/((_n_)), // e1_ni
			a.m_e2_ni/((_n_)), // e2_ni
			a.m_e3_ni/((_n_)), // e3_ni
			a.m_no_ni/((_n_)), // no_ni
			a.m_e1_e2_e3_ni/((_n_)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((_n_)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((_n_)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((_n_)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((_n_)) // no_e1_e2_e3
		);
}
/**
 * Returns unit of normalizedPoint using default metric.
 */
public final static dualSphere unit(final normalizedPoint a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni)));

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			1.0 / (_n_), // no
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)), // e3
			a.m_ni/((_n_)) // ni
		);
}
/**
 * Returns unit of dualSphere using default metric.
 */
public final static dualSphere unit(final dualSphere a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3+-2.0*a.m_ni*a.m_no)));

	return new dualSphere(dualSphere.coord_no_e1_e2_e3_ni,
			a.m_no/((_n_)), // no
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)), // e3
			a.m_ni/((_n_)) // ni
		);
}
/**
 * Returns unit of pseudoscalar using default metric.
 */
public final static pseudoscalar unit(final pseudoscalar a)
{
	double _n_ = Math.sqrt(Math.abs(-a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni));

	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a.m_no_e1_e2_e3_ni/((_n_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns unit of pointPair using default metric.
 */
public final static pointPair unit(final pointPair a)
{
	double _n_ = Math.sqrt(Math.abs((a.m_e1_e2*a.m_e1_e2+2.0*a.m_e1_ni*a.m_no_e1+a.m_e2_e3*a.m_e2_e3+2.0*a.m_e2_ni*a.m_no_e2+a.m_e3_e1*a.m_e3_e1+2.0*a.m_e3_ni*a.m_no_e3-a.m_no_ni*a.m_no_ni)));

	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			a.m_no_e1/((_n_)), // no_e1
			a.m_no_e2/((_n_)), // no_e2
			a.m_no_e3/((_n_)), // no_e3
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)), // e3_e1
			a.m_e1_ni/((_n_)), // e1_ni
			a.m_e2_ni/((_n_)), // e2_ni
			a.m_e3_ni/((_n_)), // e3_ni
			a.m_no_ni/((_n_)) // no_ni
		);
}
/**
 * Returns unit of e2_t using default metric.
 */
public final static e2_t unit(final e2_t a)
{
	return new e2_t(		);
}
/**
 * Returns unit of I5_t using default metric.
 */
public final static I5_t unit(final I5_t a)
{
	return new I5_t(		);
}
/**
 * Returns unit of mv using euclidean metric.
 */
public final static mv unit_em(final mv_if a)
{
	double n = norm_em_returns_scalar(a.to_mv());
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyDiv_0(ac[0], cc[0], n);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyDiv_1(ac[1], cc[1], n);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyDiv_2(ac[2], cc[2], n);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyDiv_3(ac[3], cc[3], n);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyDiv_4(ac[4], cc[4], n);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyDiv_5(ac[5], cc[5], n);
	}
	return new mv(cc);
}
/**
 * Returns unit of vectorE3GA using euclidean metric.
 */
public final static vectorE3GA unit_em(final vectorE3GA a)
{
	double _n_ = Math.sqrt((a.m_e1*a.m_e1+a.m_e2*a.m_e2+a.m_e3*a.m_e3));

	return new vectorE3GA(vectorE3GA.coord_e1_e2_e3,
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)) // e3
		);
}
/**
 * Returns unit of bivectorE3GA using euclidean metric.
 */
public final static bivectorE3GA unit_em(final bivectorE3GA a)
{
	double _n_ = Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1));

	return new bivectorE3GA(bivectorE3GA.coord_e1e2_e2e3_e3e1,
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)) // e3_e1
		);
}
/**
 * Returns unit of circle using euclidean metric.
 */
public final static circle unit_em(final circle a)
{
	double _n_ = Math.sqrt((a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni));

	return new circle(circle.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni,
			a.m_no_e1_e2/((_n_)), // no_e1_e2
			a.m_no_e1_e3/((_n_)), // no_e1_e3
			a.m_no_e2_e3/((_n_)), // no_e2_e3
			a.m_e1_e2_e3/((_n_)), // e1_e2_e3
			a.m_no_e1_ni/((_n_)), // no_e1_ni
			a.m_no_e2_ni/((_n_)), // no_e2_ni
			a.m_e1_e2_ni/((_n_)), // e1_e2_ni
			a.m_no_e3_ni/((_n_)), // no_e3_ni
			a.m_e1_e3_ni/((_n_)), // e1_e3_ni
			a.m_e2_e3_ni/((_n_)) // e2_e3_ni
		);
}
/**
 * Returns unit of plane using euclidean metric.
 */
public final static plane unit_em(final plane a)
{
	double _n_ = Math.sqrt((a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni));

	return new plane(plane.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni,
			a.m_e1_e2_e3_ni/((_n_)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((_n_)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((_n_)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((_n_)) // no_e1_e2_ni
		);
}
/**
 * Returns unit of rotorE3GA using euclidean metric.
 */
public final static rotorE3GA unit_em(final rotorE3GA a)
{
	double _n_ = Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e2_e3*a.m_e2_e3+a.m_e3_e1*a.m_e3_e1+a.m_scalar*a.m_scalar));

	return new rotorE3GA(rotorE3GA.coord_scalar_e1e2_e2e3_e3e1,
			a.m_scalar/((_n_)), // scalar
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)) // e3_e1
		);
}
/**
 * Returns unit of oddVersor using euclidean metric.
 */
public final static oddVersor unit_em(final oddVersor a)
{
	double _n_ = Math.sqrt((a.m_e1*a.m_e1+a.m_e1_e2_e3*a.m_e1_e2_e3+a.m_e1_e2_ni*a.m_e1_e2_ni+a.m_e1_e3_ni*a.m_e1_e3_ni+a.m_e2*a.m_e2+a.m_e2_e3_ni*a.m_e2_e3_ni+a.m_e3*a.m_e3+a.m_ni*a.m_ni+a.m_no*a.m_no+a.m_no_e1_e2*a.m_no_e1_e2+a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni+a.m_no_e1_e3*a.m_no_e1_e3+a.m_no_e1_ni*a.m_no_e1_ni+a.m_no_e2_e3*a.m_no_e2_e3+a.m_no_e2_ni*a.m_no_e2_ni+a.m_no_e3_ni*a.m_no_e3_ni));

	return new oddVersor(oddVersor.coord_no_e1_e2_e3_ni_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni_noe1e2e3ni,
			a.m_no/((_n_)), // no
			a.m_e1/((_n_)), // e1
			a.m_e2/((_n_)), // e2
			a.m_e3/((_n_)), // e3
			a.m_ni/((_n_)), // ni
			a.m_no_e1_e2/((_n_)), // no_e1_e2
			a.m_no_e1_e3/((_n_)), // no_e1_e3
			a.m_no_e2_e3/((_n_)), // no_e2_e3
			a.m_e1_e2_e3/((_n_)), // e1_e2_e3
			a.m_no_e1_ni/((_n_)), // no_e1_ni
			a.m_no_e2_ni/((_n_)), // no_e2_ni
			a.m_e1_e2_ni/((_n_)), // e1_e2_ni
			a.m_no_e3_ni/((_n_)), // no_e3_ni
			a.m_e1_e3_ni/((_n_)), // e1_e3_ni
			a.m_e2_e3_ni/((_n_)), // e2_e3_ni
			a.m_no_e1_e2_e3_ni/((_n_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns unit of evenVersor using euclidean metric.
 */
public final static evenVersor unit_em(final evenVersor a)
{
	double _n_ = Math.sqrt((a.m_e1_e2*a.m_e1_e2+a.m_e1_e2_e3_ni*a.m_e1_e2_e3_ni+a.m_e1_ni*a.m_e1_ni+a.m_e2_e3*a.m_e2_e3+a.m_e2_ni*a.m_e2_ni+a.m_e3_e1*a.m_e3_e1+a.m_e3_ni*a.m_e3_ni+a.m_no_e1*a.m_no_e1+a.m_no_e1_e2_e3*a.m_no_e1_e2_e3+a.m_no_e1_e2_ni*a.m_no_e1_e2_ni+a.m_no_e1_e3_ni*a.m_no_e1_e3_ni+a.m_no_e2*a.m_no_e2+a.m_no_e2_e3_ni*a.m_no_e2_e3_ni+a.m_no_e3*a.m_no_e3+a.m_no_ni*a.m_no_ni+a.m_scalar*a.m_scalar));

	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar/((_n_)), // scalar
			a.m_no_e1/((_n_)), // no_e1
			a.m_no_e2/((_n_)), // no_e2
			a.m_no_e3/((_n_)), // no_e3
			a.m_e1_e2/((_n_)), // e1_e2
			a.m_e2_e3/((_n_)), // e2_e3
			a.m_e3_e1/((_n_)), // e3_e1
			a.m_e1_ni/((_n_)), // e1_ni
			a.m_e2_ni/((_n_)), // e2_ni
			a.m_e3_ni/((_n_)), // e3_ni
			a.m_no_ni/((_n_)), // no_ni
			a.m_e1_e2_e3_ni/((_n_)), // e1_e2_e3_ni
			a.m_no_e2_e3_ni/((_n_)), // no_e2_e3_ni
			a.m_no_e1_e3_ni/((_n_)), // no_e1_e3_ni
			a.m_no_e1_e2_ni/((_n_)), // no_e1_e2_ni
			a.m_no_e1_e2_e3/((_n_)) // no_e1_e2_e3
		);
}
/**
 * Returns unit of pseudoscalar using euclidean metric.
 */
public final static pseudoscalar unit_em(final pseudoscalar a)
{
	double _n_ = Math.sqrt(a.m_no_e1_e2_e3_ni*a.m_no_e1_e2_e3_ni);

	return new pseudoscalar(pseudoscalar.coord_noe1e2e3ni,
			a.m_no_e1_e2_e3_ni/((_n_)) // no_e1_e2_e3_ni
		);
}
/**
 * Returns evenVersor + evenVersor.
 */
public final static evenVersor add(final evenVersor a, final evenVersor b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_scalar+b.m_scalar), // scalar
			(a.m_no_e1+b.m_no_e1), // no_e1
			(a.m_no_e2+b.m_no_e2), // no_e2
			(a.m_no_e3+b.m_no_e3), // no_e3
			(a.m_e1_e2+b.m_e1_e2), // e1_e2
			(a.m_e2_e3+b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1-b.m_e3_e1), // e3_e1
			(a.m_e1_ni+b.m_e1_ni), // e1_ni
			(a.m_e2_ni+b.m_e2_ni), // e2_ni
			(a.m_e3_ni+b.m_e3_ni), // e3_ni
			(a.m_no_ni+b.m_no_ni), // no_ni
			(a.m_e1_e2_e3_ni+b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(a.m_no_e2_e3_ni+b.m_no_e2_e3_ni), // no_e2_e3_ni
			(a.m_no_e1_e3_ni+b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_no_e1_e2_ni+b.m_no_e1_e2_ni), // no_e1_e2_ni
			(a.m_no_e1_e2_e3+b.m_no_e1_e2_e3) // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of pointPair and pointPair.
 */
public final static evenVersor gp(final pointPair a, final pointPair b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e1_ni*b.m_no_e1-a.m_e2_e3*b.m_e2_e3-a.m_e2_ni*b.m_no_e2-a.m_e3_e1*b.m_e3_e1-a.m_e3_ni*b.m_no_e3-a.m_no_e1*b.m_e1_ni-a.m_no_e2*b.m_e2_ni-a.m_no_e3*b.m_e3_ni+a.m_no_ni*b.m_no_ni), // scalar
			(a.m_e1_e2*b.m_no_e2-a.m_e3_e1*b.m_no_e3+a.m_no_e1*b.m_no_ni-a.m_no_e2*b.m_e1_e2+a.m_no_e3*b.m_e3_e1-a.m_no_ni*b.m_no_e1), // no_e1
			(-a.m_e1_e2*b.m_no_e1+a.m_e2_e3*b.m_no_e3+a.m_no_e1*b.m_e1_e2+a.m_no_e2*b.m_no_ni-a.m_no_e3*b.m_e2_e3-a.m_no_ni*b.m_no_e2), // no_e2
			(-a.m_e2_e3*b.m_no_e2+a.m_e3_e1*b.m_no_e1-a.m_no_e1*b.m_e3_e1+a.m_no_e2*b.m_e2_e3+a.m_no_e3*b.m_no_ni-a.m_no_ni*b.m_no_e3), // no_e3
			(-a.m_e1_ni*b.m_no_e2-a.m_e2_e3*b.m_e3_e1+a.m_e2_ni*b.m_no_e1+a.m_e3_e1*b.m_e2_e3-a.m_no_e1*b.m_e2_ni+a.m_no_e2*b.m_e1_ni), // e1_e2
			(a.m_e1_e2*b.m_e3_e1-a.m_e2_ni*b.m_no_e3-a.m_e3_e1*b.m_e1_e2+a.m_e3_ni*b.m_no_e2-a.m_no_e2*b.m_e3_ni+a.m_no_e3*b.m_e2_ni), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3-a.m_e1_ni*b.m_no_e3-a.m_e2_e3*b.m_e1_e2+a.m_e3_ni*b.m_no_e1-a.m_no_e1*b.m_e3_ni+a.m_no_e3*b.m_e1_ni), // e3_e1
			(a.m_e1_e2*b.m_e2_ni-a.m_e1_ni*b.m_no_ni-a.m_e2_ni*b.m_e1_e2-a.m_e3_e1*b.m_e3_ni+a.m_e3_ni*b.m_e3_e1+a.m_no_ni*b.m_e1_ni), // e1_ni
			(-a.m_e1_e2*b.m_e1_ni+a.m_e1_ni*b.m_e1_e2+a.m_e2_e3*b.m_e3_ni-a.m_e2_ni*b.m_no_ni-a.m_e3_ni*b.m_e2_e3+a.m_no_ni*b.m_e2_ni), // e2_ni
			(-a.m_e1_ni*b.m_e3_e1-a.m_e2_e3*b.m_e2_ni+a.m_e2_ni*b.m_e2_e3+a.m_e3_e1*b.m_e1_ni-a.m_e3_ni*b.m_no_ni+a.m_no_ni*b.m_e3_ni), // e3_ni
			(-a.m_e1_ni*b.m_no_e1-a.m_e2_ni*b.m_no_e2-a.m_e3_ni*b.m_no_e3+a.m_no_e1*b.m_e1_ni+a.m_no_e2*b.m_e2_ni+a.m_no_e3*b.m_e3_ni), // no_ni
			(a.m_e1_e2*b.m_e3_ni+a.m_e1_ni*b.m_e2_e3+a.m_e2_e3*b.m_e1_ni+a.m_e2_ni*b.m_e3_e1+a.m_e3_e1*b.m_e2_ni+a.m_e3_ni*b.m_e1_e2), // e1_e2_e3_ni
			(a.m_e2_e3*b.m_no_ni-a.m_e2_ni*b.m_no_e3+a.m_e3_ni*b.m_no_e2+a.m_no_e2*b.m_e3_ni-a.m_no_e3*b.m_e2_ni+a.m_no_ni*b.m_e2_e3), // no_e2_e3_ni
			(-a.m_e1_ni*b.m_no_e3-a.m_e3_e1*b.m_no_ni+a.m_e3_ni*b.m_no_e1+a.m_no_e1*b.m_e3_ni-a.m_no_e3*b.m_e1_ni-a.m_no_ni*b.m_e3_e1), // no_e1_e3_ni
			(a.m_e1_e2*b.m_no_ni-a.m_e1_ni*b.m_no_e2+a.m_e2_ni*b.m_no_e1+a.m_no_e1*b.m_e2_ni-a.m_no_e2*b.m_e1_ni+a.m_no_ni*b.m_e1_e2), // no_e1_e2_ni
			(a.m_e1_e2*b.m_no_e3+a.m_e2_e3*b.m_no_e1+a.m_e3_e1*b.m_no_e2+a.m_no_e1*b.m_e2_e3+a.m_no_e2*b.m_e3_e1+a.m_no_e3*b.m_e1_e2) // no_e1_e2_e3
		);

}
/**
 * Returns evenVersor - evenVersor.
 */
public final static evenVersor subtract(final evenVersor a, final evenVersor b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(a.m_scalar-b.m_scalar), // scalar
			(a.m_no_e1-b.m_no_e1), // no_e1
			(a.m_no_e2-b.m_no_e2), // no_e2
			(a.m_no_e3-b.m_no_e3), // no_e3
			(a.m_e1_e2-b.m_e1_e2), // e1_e2
			(a.m_e2_e3-b.m_e2_e3), // e2_e3
			-(-a.m_e3_e1+b.m_e3_e1), // e3_e1
			(a.m_e1_ni-b.m_e1_ni), // e1_ni
			(a.m_e2_ni-b.m_e2_ni), // e2_ni
			(a.m_e3_ni-b.m_e3_ni), // e3_ni
			(a.m_no_ni-b.m_no_ni), // no_ni
			(a.m_e1_e2_e3_ni-b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(a.m_no_e2_e3_ni-b.m_no_e2_e3_ni), // no_e2_e3_ni
			(a.m_no_e1_e3_ni-b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_no_e1_e2_ni-b.m_no_e1_e2_ni), // no_e1_e2_ni
			(a.m_no_e1_e2_e3-b.m_no_e1_e2_e3) // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of evenVersor and pointPair.
 */
public final static evenVersor gp(final evenVersor a, final pointPair b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(-a.m_e1_e2*b.m_e1_e2-a.m_e1_ni*b.m_no_e1-a.m_e2_e3*b.m_e2_e3-a.m_e2_ni*b.m_no_e2-a.m_e3_e1*b.m_e3_e1-a.m_e3_ni*b.m_no_e3-a.m_no_e1*b.m_e1_ni-a.m_no_e2*b.m_e2_ni-a.m_no_e3*b.m_e3_ni+a.m_no_ni*b.m_no_ni), // scalar
			(a.m_e1_e2*b.m_no_e2-a.m_e3_e1*b.m_no_e3+a.m_no_e1*b.m_no_ni-a.m_no_e1_e2_e3*b.m_e2_e3-a.m_no_e1_e2_ni*b.m_no_e2-a.m_no_e1_e3_ni*b.m_no_e3-a.m_no_e2*b.m_e1_e2+a.m_no_e3*b.m_e3_e1-a.m_no_ni*b.m_no_e1+a.m_scalar*b.m_no_e1), // no_e1
			(-a.m_e1_e2*b.m_no_e1+a.m_e2_e3*b.m_no_e3+a.m_no_e1*b.m_e1_e2-a.m_no_e1_e2_e3*b.m_e3_e1+a.m_no_e1_e2_ni*b.m_no_e1+a.m_no_e2*b.m_no_ni-a.m_no_e2_e3_ni*b.m_no_e3-a.m_no_e3*b.m_e2_e3-a.m_no_ni*b.m_no_e2+a.m_scalar*b.m_no_e2), // no_e2
			(-a.m_e2_e3*b.m_no_e2+a.m_e3_e1*b.m_no_e1-a.m_no_e1*b.m_e3_e1-a.m_no_e1_e2_e3*b.m_e1_e2+a.m_no_e1_e3_ni*b.m_no_e1+a.m_no_e2*b.m_e2_e3+a.m_no_e2_e3_ni*b.m_no_e2+a.m_no_e3*b.m_no_ni-a.m_no_ni*b.m_no_e3+a.m_scalar*b.m_no_e3), // no_e3
			(-a.m_e1_e2_e3_ni*b.m_no_e3-a.m_e1_ni*b.m_no_e2-a.m_e2_e3*b.m_e3_e1+a.m_e2_ni*b.m_no_e1+a.m_e3_e1*b.m_e2_e3-a.m_no_e1*b.m_e2_ni-a.m_no_e1_e2_e3*b.m_e3_ni+a.m_no_e1_e2_ni*b.m_no_ni+a.m_no_e2*b.m_e1_ni+a.m_scalar*b.m_e1_e2), // e1_e2
			(a.m_e1_e2*b.m_e3_e1-a.m_e1_e2_e3_ni*b.m_no_e1-a.m_e2_ni*b.m_no_e3-a.m_e3_e1*b.m_e1_e2+a.m_e3_ni*b.m_no_e2-a.m_no_e1_e2_e3*b.m_e1_ni-a.m_no_e2*b.m_e3_ni+a.m_no_e2_e3_ni*b.m_no_ni+a.m_no_e3*b.m_e2_ni+a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3+a.m_e1_e2_e3_ni*b.m_no_e2-a.m_e1_ni*b.m_no_e3-a.m_e2_e3*b.m_e1_e2+a.m_e3_ni*b.m_no_e1-a.m_no_e1*b.m_e3_ni+a.m_no_e1_e2_e3*b.m_e2_ni+a.m_no_e1_e3_ni*b.m_no_ni+a.m_no_e3*b.m_e1_ni-a.m_scalar*b.m_e3_e1), // e3_e1
			(a.m_e1_e2*b.m_e2_ni-a.m_e1_e2_e3_ni*b.m_e2_e3-a.m_e1_ni*b.m_no_ni-a.m_e2_ni*b.m_e1_e2-a.m_e3_e1*b.m_e3_ni+a.m_e3_ni*b.m_e3_e1+a.m_no_e1_e2_ni*b.m_e2_ni+a.m_no_e1_e3_ni*b.m_e3_ni+a.m_no_ni*b.m_e1_ni+a.m_scalar*b.m_e1_ni), // e1_ni
			(-a.m_e1_e2*b.m_e1_ni-a.m_e1_e2_e3_ni*b.m_e3_e1+a.m_e1_ni*b.m_e1_e2+a.m_e2_e3*b.m_e3_ni-a.m_e2_ni*b.m_no_ni-a.m_e3_ni*b.m_e2_e3-a.m_no_e1_e2_ni*b.m_e1_ni+a.m_no_e2_e3_ni*b.m_e3_ni+a.m_no_ni*b.m_e2_ni+a.m_scalar*b.m_e2_ni), // e2_ni
			(-a.m_e1_e2_e3_ni*b.m_e1_e2-a.m_e1_ni*b.m_e3_e1-a.m_e2_e3*b.m_e2_ni+a.m_e2_ni*b.m_e2_e3+a.m_e3_e1*b.m_e1_ni-a.m_e3_ni*b.m_no_ni-a.m_no_e1_e3_ni*b.m_e1_ni-a.m_no_e2_e3_ni*b.m_e2_ni+a.m_no_ni*b.m_e3_ni+a.m_scalar*b.m_e3_ni), // e3_ni
			(-a.m_e1_ni*b.m_no_e1-a.m_e2_ni*b.m_no_e2-a.m_e3_ni*b.m_no_e3+a.m_no_e1*b.m_e1_ni-a.m_no_e1_e2_ni*b.m_e1_e2+a.m_no_e1_e3_ni*b.m_e3_e1+a.m_no_e2*b.m_e2_ni-a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e3*b.m_e3_ni+a.m_scalar*b.m_no_ni), // no_ni
			(a.m_e1_e2*b.m_e3_ni-a.m_e1_e2_e3_ni*b.m_no_ni+a.m_e1_ni*b.m_e2_e3+a.m_e2_e3*b.m_e1_ni+a.m_e2_ni*b.m_e3_e1+a.m_e3_e1*b.m_e2_ni+a.m_e3_ni*b.m_e1_e2+a.m_no_e1_e2_ni*b.m_e3_ni-a.m_no_e1_e3_ni*b.m_e2_ni+a.m_no_e2_e3_ni*b.m_e1_ni), // e1_e2_e3_ni
			(-a.m_e1_e2_e3_ni*b.m_no_e1+a.m_e2_e3*b.m_no_ni-a.m_e2_ni*b.m_no_e3+a.m_e3_ni*b.m_no_e2+a.m_no_e1_e2_e3*b.m_e1_ni+a.m_no_e1_e2_ni*b.m_e3_e1+a.m_no_e1_e3_ni*b.m_e1_e2+a.m_no_e2*b.m_e3_ni-a.m_no_e3*b.m_e2_ni+a.m_no_ni*b.m_e2_e3), // no_e2_e3_ni
			(a.m_e1_e2_e3_ni*b.m_no_e2-a.m_e1_ni*b.m_no_e3-a.m_e3_e1*b.m_no_ni+a.m_e3_ni*b.m_no_e1+a.m_no_e1*b.m_e3_ni-a.m_no_e1_e2_e3*b.m_e2_ni+a.m_no_e1_e2_ni*b.m_e2_e3-a.m_no_e2_e3_ni*b.m_e1_e2-a.m_no_e3*b.m_e1_ni-a.m_no_ni*b.m_e3_e1), // no_e1_e3_ni
			(a.m_e1_e2*b.m_no_ni-a.m_e1_e2_e3_ni*b.m_no_e3-a.m_e1_ni*b.m_no_e2+a.m_e2_ni*b.m_no_e1+a.m_no_e1*b.m_e2_ni+a.m_no_e1_e2_e3*b.m_e3_ni-a.m_no_e1_e3_ni*b.m_e2_e3-a.m_no_e2*b.m_e1_ni-a.m_no_e2_e3_ni*b.m_e3_e1+a.m_no_ni*b.m_e1_e2), // no_e1_e2_ni
			(a.m_e1_e2*b.m_no_e3+a.m_e2_e3*b.m_no_e1+a.m_e3_e1*b.m_no_e2+a.m_no_e1*b.m_e2_e3+a.m_no_e1_e2_e3*b.m_no_ni-a.m_no_e1_e2_ni*b.m_no_e3+a.m_no_e1_e3_ni*b.m_no_e2+a.m_no_e2*b.m_e3_e1-a.m_no_e2_e3_ni*b.m_no_e1+a.m_no_e3*b.m_e1_e2) // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of evenVersor and evenVersor.
 */
public final static evenVersor gp(final evenVersor a, final evenVersor b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			(-a.m_e1_e2*b.m_e1_e2+a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3-a.m_e1_ni*b.m_no_e1-a.m_e2_e3*b.m_e2_e3-a.m_e2_ni*b.m_no_e2-a.m_e3_e1*b.m_e3_e1-a.m_e3_ni*b.m_no_e3-a.m_no_e1*b.m_e1_ni+a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni-a.m_no_e1_e2_ni*b.m_no_e1_e2_ni-a.m_no_e1_e3_ni*b.m_no_e1_e3_ni-a.m_no_e2*b.m_e2_ni-a.m_no_e2_e3_ni*b.m_no_e2_e3_ni-a.m_no_e3*b.m_e3_ni+a.m_no_ni*b.m_no_ni+a.m_scalar*b.m_scalar), // scalar
			(a.m_e1_e2*b.m_no_e2-a.m_e2_e3*b.m_no_e1_e2_e3-a.m_e3_e1*b.m_no_e3+a.m_no_e1*b.m_no_ni+a.m_no_e1*b.m_scalar-a.m_no_e1_e2_e3*b.m_e2_e3-a.m_no_e1_e2_e3*b.m_no_e2_e3_ni-a.m_no_e1_e2_ni*b.m_no_e2-a.m_no_e1_e3_ni*b.m_no_e3-a.m_no_e2*b.m_e1_e2-a.m_no_e2*b.m_no_e1_e2_ni+a.m_no_e2_e3_ni*b.m_no_e1_e2_e3+a.m_no_e3*b.m_e3_e1-a.m_no_e3*b.m_no_e1_e3_ni-a.m_no_ni*b.m_no_e1+a.m_scalar*b.m_no_e1), // no_e1
			(-a.m_e1_e2*b.m_no_e1+a.m_e2_e3*b.m_no_e3-a.m_e3_e1*b.m_no_e1_e2_e3+a.m_no_e1*b.m_e1_e2+a.m_no_e1*b.m_no_e1_e2_ni-a.m_no_e1_e2_e3*b.m_e3_e1+a.m_no_e1_e2_e3*b.m_no_e1_e3_ni+a.m_no_e1_e2_ni*b.m_no_e1-a.m_no_e1_e3_ni*b.m_no_e1_e2_e3+a.m_no_e2*b.m_no_ni+a.m_no_e2*b.m_scalar-a.m_no_e2_e3_ni*b.m_no_e3-a.m_no_e3*b.m_e2_e3-a.m_no_e3*b.m_no_e2_e3_ni-a.m_no_ni*b.m_no_e2+a.m_scalar*b.m_no_e2), // no_e2
			(-a.m_e1_e2*b.m_no_e1_e2_e3-a.m_e2_e3*b.m_no_e2+a.m_e3_e1*b.m_no_e1-a.m_no_e1*b.m_e3_e1+a.m_no_e1*b.m_no_e1_e3_ni-a.m_no_e1_e2_e3*b.m_e1_e2-a.m_no_e1_e2_e3*b.m_no_e1_e2_ni+a.m_no_e1_e2_ni*b.m_no_e1_e2_e3+a.m_no_e1_e3_ni*b.m_no_e1+a.m_no_e2*b.m_e2_e3+a.m_no_e2*b.m_no_e2_e3_ni+a.m_no_e2_e3_ni*b.m_no_e2+a.m_no_e3*b.m_no_ni+a.m_no_e3*b.m_scalar-a.m_no_ni*b.m_no_e3+a.m_scalar*b.m_no_e3), // no_e3
			(a.m_e1_e2*b.m_scalar-a.m_e1_e2_e3_ni*b.m_no_e3-a.m_e1_ni*b.m_no_e2-a.m_e2_e3*b.m_e3_e1+a.m_e2_ni*b.m_no_e1+a.m_e3_e1*b.m_e2_e3-a.m_e3_ni*b.m_no_e1_e2_e3-a.m_no_e1*b.m_e2_ni-a.m_no_e1_e2_e3*b.m_e3_ni+a.m_no_e1_e2_ni*b.m_no_ni-a.m_no_e1_e3_ni*b.m_no_e2_e3_ni+a.m_no_e2*b.m_e1_ni+a.m_no_e2_e3_ni*b.m_no_e1_e3_ni-a.m_no_e3*b.m_e1_e2_e3_ni+a.m_no_ni*b.m_no_e1_e2_ni+a.m_scalar*b.m_e1_e2), // e1_e2
			(a.m_e1_e2*b.m_e3_e1-a.m_e1_e2_e3_ni*b.m_no_e1-a.m_e1_ni*b.m_no_e1_e2_e3+a.m_e2_e3*b.m_scalar-a.m_e2_ni*b.m_no_e3-a.m_e3_e1*b.m_e1_e2+a.m_e3_ni*b.m_no_e2-a.m_no_e1*b.m_e1_e2_e3_ni-a.m_no_e1_e2_e3*b.m_e1_ni-a.m_no_e1_e2_ni*b.m_no_e1_e3_ni+a.m_no_e1_e3_ni*b.m_no_e1_e2_ni-a.m_no_e2*b.m_e3_ni+a.m_no_e2_e3_ni*b.m_no_ni+a.m_no_e3*b.m_e2_ni+a.m_no_ni*b.m_no_e2_e3_ni+a.m_scalar*b.m_e2_e3), // e2_e3
			-(a.m_e1_e2*b.m_e2_e3+a.m_e1_e2_e3_ni*b.m_no_e2-a.m_e1_ni*b.m_no_e3-a.m_e2_e3*b.m_e1_e2+a.m_e2_ni*b.m_no_e1_e2_e3-a.m_e3_e1*b.m_scalar+a.m_e3_ni*b.m_no_e1-a.m_no_e1*b.m_e3_ni+a.m_no_e1_e2_e3*b.m_e2_ni+a.m_no_e1_e2_ni*b.m_no_e2_e3_ni+a.m_no_e1_e3_ni*b.m_no_ni+a.m_no_e2*b.m_e1_e2_e3_ni-a.m_no_e2_e3_ni*b.m_no_e1_e2_ni+a.m_no_e3*b.m_e1_ni+a.m_no_ni*b.m_no_e1_e3_ni-a.m_scalar*b.m_e3_e1), // e3_e1
			(a.m_e1_e2*b.m_e2_ni-a.m_e1_e2_e3_ni*b.m_e2_e3+a.m_e1_e2_e3_ni*b.m_no_e2_e3_ni-a.m_e1_ni*b.m_no_ni+a.m_e1_ni*b.m_scalar-a.m_e2_e3*b.m_e1_e2_e3_ni-a.m_e2_ni*b.m_e1_e2+a.m_e2_ni*b.m_no_e1_e2_ni-a.m_e3_e1*b.m_e3_ni+a.m_e3_ni*b.m_e3_e1+a.m_e3_ni*b.m_no_e1_e3_ni+a.m_no_e1_e2_ni*b.m_e2_ni+a.m_no_e1_e3_ni*b.m_e3_ni-a.m_no_e2_e3_ni*b.m_e1_e2_e3_ni+a.m_no_ni*b.m_e1_ni+a.m_scalar*b.m_e1_ni), // e1_ni
			(-a.m_e1_e2*b.m_e1_ni-a.m_e1_e2_e3_ni*b.m_e3_e1-a.m_e1_e2_e3_ni*b.m_no_e1_e3_ni+a.m_e1_ni*b.m_e1_e2-a.m_e1_ni*b.m_no_e1_e2_ni+a.m_e2_e3*b.m_e3_ni-a.m_e2_ni*b.m_no_ni+a.m_e2_ni*b.m_scalar-a.m_e3_e1*b.m_e1_e2_e3_ni-a.m_e3_ni*b.m_e2_e3+a.m_e3_ni*b.m_no_e2_e3_ni-a.m_no_e1_e2_ni*b.m_e1_ni+a.m_no_e1_e3_ni*b.m_e1_e2_e3_ni+a.m_no_e2_e3_ni*b.m_e3_ni+a.m_no_ni*b.m_e2_ni+a.m_scalar*b.m_e2_ni), // e2_ni
			(-a.m_e1_e2*b.m_e1_e2_e3_ni-a.m_e1_e2_e3_ni*b.m_e1_e2+a.m_e1_e2_e3_ni*b.m_no_e1_e2_ni-a.m_e1_ni*b.m_e3_e1-a.m_e1_ni*b.m_no_e1_e3_ni-a.m_e2_e3*b.m_e2_ni+a.m_e2_ni*b.m_e2_e3-a.m_e2_ni*b.m_no_e2_e3_ni+a.m_e3_e1*b.m_e1_ni-a.m_e3_ni*b.m_no_ni+a.m_e3_ni*b.m_scalar-a.m_no_e1_e2_ni*b.m_e1_e2_e3_ni-a.m_no_e1_e3_ni*b.m_e1_ni-a.m_no_e2_e3_ni*b.m_e2_ni+a.m_no_ni*b.m_e3_ni+a.m_scalar*b.m_e3_ni), // e3_ni
			(-a.m_e1_e2*b.m_no_e1_e2_ni+a.m_e1_e2_e3_ni*b.m_no_e1_e2_e3-a.m_e1_ni*b.m_no_e1-a.m_e2_e3*b.m_no_e2_e3_ni-a.m_e2_ni*b.m_no_e2+a.m_e3_e1*b.m_no_e1_e3_ni-a.m_e3_ni*b.m_no_e3+a.m_no_e1*b.m_e1_ni-a.m_no_e1_e2_e3*b.m_e1_e2_e3_ni-a.m_no_e1_e2_ni*b.m_e1_e2+a.m_no_e1_e3_ni*b.m_e3_e1+a.m_no_e2*b.m_e2_ni-a.m_no_e2_e3_ni*b.m_e2_e3+a.m_no_e3*b.m_e3_ni+a.m_no_ni*b.m_scalar+a.m_scalar*b.m_no_ni), // no_ni
			(a.m_e1_e2*b.m_e3_ni-a.m_e1_e2_e3_ni*b.m_no_ni+a.m_e1_e2_e3_ni*b.m_scalar+a.m_e1_ni*b.m_e2_e3-a.m_e1_ni*b.m_no_e2_e3_ni+a.m_e2_e3*b.m_e1_ni+a.m_e2_ni*b.m_e3_e1+a.m_e2_ni*b.m_no_e1_e3_ni+a.m_e3_e1*b.m_e2_ni+a.m_e3_ni*b.m_e1_e2-a.m_e3_ni*b.m_no_e1_e2_ni+a.m_no_e1_e2_ni*b.m_e3_ni-a.m_no_e1_e3_ni*b.m_e2_ni+a.m_no_e2_e3_ni*b.m_e1_ni+a.m_no_ni*b.m_e1_e2_e3_ni+a.m_scalar*b.m_e1_e2_e3_ni), // e1_e2_e3_ni
			(-a.m_e1_e2*b.m_no_e1_e3_ni-a.m_e1_e2_e3_ni*b.m_no_e1-a.m_e1_ni*b.m_no_e1_e2_e3+a.m_e2_e3*b.m_no_ni-a.m_e2_ni*b.m_no_e3-a.m_e3_e1*b.m_no_e1_e2_ni+a.m_e3_ni*b.m_no_e2+a.m_no_e1*b.m_e1_e2_e3_ni+a.m_no_e1_e2_e3*b.m_e1_ni+a.m_no_e1_e2_ni*b.m_e3_e1+a.m_no_e1_e3_ni*b.m_e1_e2+a.m_no_e2*b.m_e3_ni+a.m_no_e2_e3_ni*b.m_scalar-a.m_no_e3*b.m_e2_ni+a.m_no_ni*b.m_e2_e3+a.m_scalar*b.m_no_e2_e3_ni), // no_e2_e3_ni
			(a.m_e1_e2*b.m_no_e2_e3_ni+a.m_e1_e2_e3_ni*b.m_no_e2-a.m_e1_ni*b.m_no_e3-a.m_e2_e3*b.m_no_e1_e2_ni+a.m_e2_ni*b.m_no_e1_e2_e3-a.m_e3_e1*b.m_no_ni+a.m_e3_ni*b.m_no_e1+a.m_no_e1*b.m_e3_ni-a.m_no_e1_e2_e3*b.m_e2_ni+a.m_no_e1_e2_ni*b.m_e2_e3+a.m_no_e1_e3_ni*b.m_scalar-a.m_no_e2*b.m_e1_e2_e3_ni-a.m_no_e2_e3_ni*b.m_e1_e2-a.m_no_e3*b.m_e1_ni-a.m_no_ni*b.m_e3_e1+a.m_scalar*b.m_no_e1_e3_ni), // no_e1_e3_ni
			(a.m_e1_e2*b.m_no_ni-a.m_e1_e2_e3_ni*b.m_no_e3-a.m_e1_ni*b.m_no_e2+a.m_e2_e3*b.m_no_e1_e3_ni+a.m_e2_ni*b.m_no_e1+a.m_e3_e1*b.m_no_e2_e3_ni-a.m_e3_ni*b.m_no_e1_e2_e3+a.m_no_e1*b.m_e2_ni+a.m_no_e1_e2_e3*b.m_e3_ni+a.m_no_e1_e2_ni*b.m_scalar-a.m_no_e1_e3_ni*b.m_e2_e3-a.m_no_e2*b.m_e1_ni-a.m_no_e2_e3_ni*b.m_e3_e1+a.m_no_e3*b.m_e1_e2_e3_ni+a.m_no_ni*b.m_e1_e2+a.m_scalar*b.m_no_e1_e2_ni), // no_e1_e2_ni
			(a.m_e1_e2*b.m_no_e3+a.m_e2_e3*b.m_no_e1+a.m_e3_e1*b.m_no_e2+a.m_no_e1*b.m_e2_e3+a.m_no_e1*b.m_no_e2_e3_ni+a.m_no_e1_e2_e3*b.m_no_ni+a.m_no_e1_e2_e3*b.m_scalar-a.m_no_e1_e2_ni*b.m_no_e3+a.m_no_e1_e3_ni*b.m_no_e2+a.m_no_e2*b.m_e3_e1-a.m_no_e2*b.m_no_e1_e3_ni-a.m_no_e2_e3_ni*b.m_no_e1+a.m_no_e3*b.m_e1_e2+a.m_no_e3*b.m_no_e1_e2_ni-a.m_no_ni*b.m_no_e1_e2_e3+a.m_scalar*b.m_no_e1_e2_e3) // no_e1_e2_e3
		);

}
/**
 * Returns double b * pointPair a + double c.
 */
public final static evenVersor sas(final pointPair a, final double b, final double c)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			c, // scalar
			a.m_no_e1*b, // no_e1
			a.m_no_e2*b, // no_e2
			a.m_no_e3*b, // no_e3
			a.m_e1_e2*b, // e1_e2
			a.m_e2_e3*b, // e2_e3
			a.m_e3_e1*b, // e3_e1
			a.m_e1_ni*b, // e1_ni
			a.m_e2_ni*b, // e2_ni
			a.m_e3_ni*b, // e3_ni
			a.m_no_ni*b, // no_ni
			0.0, // e1_e2_e3_ni
			0.0, // no_e2_e3_ni
			0.0, // no_e1_e3_ni
			0.0, // no_e1_e2_ni
			0.0 // no_e1_e2_e3
		);
}
/**
 * Returns geometric product of evenVersor and double.
 */
public final static evenVersor gp(final evenVersor a, final double b)
{
	return new evenVersor(evenVersor.coord_scalar_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3,
			a.m_scalar*b, // scalar
			a.m_no_e1*b, // no_e1
			a.m_no_e2*b, // no_e2
			a.m_no_e3*b, // no_e3
			a.m_e1_e2*b, // e1_e2
			a.m_e2_e3*b, // e2_e3
			a.m_e3_e1*b, // e3_e1
			a.m_e1_ni*b, // e1_ni
			a.m_e2_ni*b, // e2_ni
			a.m_e3_ni*b, // e3_ni
			a.m_no_ni*b, // no_ni
			a.m_e1_e2_e3_ni*b, // e1_e2_e3_ni
			a.m_no_e2_e3_ni*b, // no_e2_e3_ni
			a.m_no_e1_e3_ni*b, // no_e1_e3_ni
			a.m_no_e1_e2_ni*b, // no_e1_e2_ni
			a.m_no_e1_e2_e3*b // no_e1_e2_e3
		);

}
/**
 * Returns geometric product of pointPair and double.
 */
public final static pointPair gp(final pointPair a, final double b)
{
	return new pointPair(pointPair.coord_noe1_noe2_noe3_e1e2_e2e3_e3e1_e1ni_e2ni_e3ni_noni,
			a.m_no_e1*b, // no_e1
			a.m_no_e2*b, // no_e2
			a.m_no_e3*b, // no_e3
			a.m_e1_e2*b, // e1_e2
			a.m_e2_e3*b, // e2_e3
			a.m_e3_e1*b, // e3_e1
			a.m_e1_ni*b, // e1_ni
			a.m_e2_ni*b, // e2_ni
			a.m_e3_ni*b, // e3_ni
			a.m_no_ni*b // no_ni
		);

}
/**
 * Returns versor inverse of a using default metric.
 */
public final static mv versorInverse(final mv_if a)
{
	double n2 = norm2_returns_scalar(a.to_mv());
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyDiv_0(ac[0], cc[0], n2);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyDiv_1(ac[1], cc[1], n2);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyDiv_2(ac[2], cc[2], -n2);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyDiv_3(ac[3], cc[3], -n2);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyDiv_4(ac[4], cc[4], n2);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyDiv_5(ac[5], cc[5], n2);
	}
	return new mv(cc);
}
/**
 * Returns versor inverse of a using euclidean metric.
 */
public final static mv versorInverse_em(final mv_if a)
{
	double n2 = norm2_em_returns_scalar(a.to_mv());
	double[][] ac = a.to_mv().c();
	double[][] cc = new double[6][];
	
	if (ac[0] != null) {
		cc[0] = new double[1];
		copyDiv_0(ac[0], cc[0], n2);
	}
	
	if (ac[1] != null) {
		cc[1] = new double[5];
		copyDiv_1(ac[1], cc[1], n2);
	}
	
	if (ac[2] != null) {
		cc[2] = new double[10];
		copyDiv_2(ac[2], cc[2], -n2);
	}
	
	if (ac[3] != null) {
		cc[3] = new double[10];
		copyDiv_3(ac[3], cc[3], -n2);
	}
	
	if (ac[4] != null) {
		cc[4] = new double[5];
		copyDiv_4(ac[4], cc[4], n2);
	}
	
	if (ac[5] != null) {
		cc[5] = new double[1];
		copyDiv_5(ac[5], cc[5], n2);
	}
	return new mv(cc);
}
} // end of class c3ga
