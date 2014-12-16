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
 * This class can hold a specialized multivector of type bivectorE3GA.
 * 
 * The coordinates are stored in type double.
 * 
 * The variable non-zero coordinates are:
 *   - coordinate e1^e2  (array index: E1_E2 = 0)
 *   - coordinate e2^e3  (array index: E2_E3 = 1)
 *   - coordinate -1*e1^e3  (array index: E3_E1 = 2)
 * 
 * The type has no constant coordinates.
 * 
 * 
 */
public class bivectorE3GA  implements  mv_if
{ 
	/**
	 * The e1^e2 coordinate.
	 */
	protected double m_e1_e2;
	/**
	 * The e2^e3 coordinate.
	 */
	protected double m_e2_e3;
	/**
	 * The -1*e1^e3 coordinate.
	 */
	protected double m_e3_e1;
	/**
	 * Array indices of bivectorE3GA coordinates.
	 */

	/**
	 * index of coordinate for e1^e2 in bivectorE3GA
	 */
	public static final int E1_E2 = 0;

	/**
	 * index of coordinate for e2^e3 in bivectorE3GA
	 */
	public static final int E2_E3 = 1;

	/**
	 * index of coordinate for -1*e1^e3 in bivectorE3GA
	 */
	public static final int E3_E1 = 2;

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord_e1e2_e2e3_e3e1
	};
	public static final CoordinateOrder coord_e1e2_e2e3_e3e1 = CoordinateOrder.coord_e1e2_e2e3_e3e1;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new bivectorE3GA with variable coordinates set to 0. */
	public bivectorE3GA() {set();}

    /** Copy constructor. */
	public bivectorE3GA(final bivectorE3GA A) {set(A);}



	/** Constructs a new bivectorE3GA from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public bivectorE3GA(final mv A /*, final int filler */) {set(A);}

	/** Constructs a new bivectorE3GA. Coordinate values come from 'A'. */
	public bivectorE3GA(final CoordinateOrder co, final double[] A) {set(co, A);}
	
	/** Constructs a new bivectorE3GA with each coordinate specified. */
	public bivectorE3GA(final CoordinateOrder co,  final double e1_e2, final double e2_e3, final double e3_e1) {
		set(co, e1_e2, e2_e3, e3_e1);
	}

public final void set()
{
	m_e1_e2 = m_e2_e3 = m_e3_e1 = 0.0;

}

public final void set(final double scalarVal)
{
	m_e1_e2 = m_e2_e3 = m_e3_e1 = 0.0;

}

public final void set(final CoordinateOrder co, final double _e1_e2, final double _e2_e3, final double _e3_e1)
{
	m_e1_e2 = _e1_e2;
	m_e2_e3 = _e2_e3;
	m_e3_e1 = _e3_e1;

}

public final void set(final CoordinateOrder co, final double[] A)
{
	m_e1_e2 = A[0];
	m_e2_e3 = A[1];
	m_e3_e1 = A[2];

}

public final void set(final bivectorE3GA a)
{
	m_e1_e2 = a.m_e1_e2;
	m_e2_e3 = a.m_e2_e3;
	m_e3_e1 = a.m_e3_e1;

}
	public final void set(final mv src) {
		if (src.c()[2] != null) {
			double[] ptr = src.c()[2];
			m_e1_e2 = ptr[2];
			m_e2_e3 = ptr[5];
			m_e3_e1 = -ptr[4];
		}
		else {
			m_e1_e2 = 0.0;
			m_e2_e3 = 0.0;
			m_e3_e1 = 0.0;
		}
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = Math.abs(m_e1_e2);
		if (Math.abs(m_e2_e3) > maxValue) { maxValue = Math.abs(m_e2_e3); }
		if (Math.abs(m_e3_e1) > maxValue) { maxValue = Math.abs(m_e3_e1); }
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = Math.abs(m_e1_e2);
		bm = 0;
		if (Math.abs(m_e2_e3) > maxValue) { maxValue = Math.abs(m_e2_e3); bm = 12; }
		if (Math.abs(m_e3_e1) > maxValue) { maxValue = Math.abs(m_e3_e1); bm = 10; }
		return new double[]{maxValue, (double)bm};
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
	/**
	 * Returns the e1^e2 coordinate.
	 */
	public final double get_e1_e2() { return m_e1_e2;}
	/**
	 * Sets the e1^e2 coordinate.
	 */
	public final void set_e1_e2(double e1_e2) { m_e1_e2 = e1_e2;}
	/**
	 * Returns the e2^e3 coordinate.
	 */
	public final double get_e2_e3() { return m_e2_e3;}
	/**
	 * Sets the e2^e3 coordinate.
	 */
	public final void set_e2_e3(double e2_e3) { m_e2_e3 = e2_e3;}
	/**
	 * Returns the -1*e1^e3 coordinate.
	 */
	public final double get_e3_e1() { return m_e3_e1;}
	/**
	 * Sets the -1*e1^e3 coordinate.
	 */
	public final void set_e3_e1(double e3_e1) { m_e3_e1 = e3_e1;}
	/**
	 * Returns the scalar coordinate (which is always 0).
	 */
	public final double get_scalar() { return 0.0;}
} // end of class bivectorE3GA
