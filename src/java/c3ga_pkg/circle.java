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
 * This class can hold a specialized multivector of type circle.
 * 
 * The coordinates are stored in type double.
 * 
 * The variable non-zero coordinates are:
 *   - coordinate no^e1^e2  (array index: NO_E1_E2 = 0)
 *   - coordinate no^e1^e3  (array index: NO_E1_E3 = 1)
 *   - coordinate no^e2^e3  (array index: NO_E2_E3 = 2)
 *   - coordinate e1^e2^e3  (array index: E1_E2_E3 = 3)
 *   - coordinate no^e1^ni  (array index: NO_E1_NI = 4)
 *   - coordinate no^e2^ni  (array index: NO_E2_NI = 5)
 *   - coordinate e1^e2^ni  (array index: E1_E2_NI = 6)
 *   - coordinate no^e3^ni  (array index: NO_E3_NI = 7)
 *   - coordinate e1^e3^ni  (array index: E1_E3_NI = 8)
 *   - coordinate e2^e3^ni  (array index: E2_E3_NI = 9)
 * 
 * The type has no constant coordinates.
 * 
 * 
 */
public class circle  implements  mv_if
{ 
	/**
	 * The no^e1^e2 coordinate.
	 */
	protected double m_no_e1_e2;
	/**
	 * The no^e1^e3 coordinate.
	 */
	protected double m_no_e1_e3;
	/**
	 * The no^e2^e3 coordinate.
	 */
	protected double m_no_e2_e3;
	/**
	 * The e1^e2^e3 coordinate.
	 */
	protected double m_e1_e2_e3;
	/**
	 * The no^e1^ni coordinate.
	 */
	protected double m_no_e1_ni;
	/**
	 * The no^e2^ni coordinate.
	 */
	protected double m_no_e2_ni;
	/**
	 * The e1^e2^ni coordinate.
	 */
	protected double m_e1_e2_ni;
	/**
	 * The no^e3^ni coordinate.
	 */
	protected double m_no_e3_ni;
	/**
	 * The e1^e3^ni coordinate.
	 */
	protected double m_e1_e3_ni;
	/**
	 * The e2^e3^ni coordinate.
	 */
	protected double m_e2_e3_ni;
	/**
	 * Array indices of circle coordinates.
	 */

	/**
	 * index of coordinate for no^e1^e2 in circle
	 */
	public static final int NO_E1_E2 = 0;

	/**
	 * index of coordinate for no^e1^e3 in circle
	 */
	public static final int NO_E1_E3 = 1;

	/**
	 * index of coordinate for no^e2^e3 in circle
	 */
	public static final int NO_E2_E3 = 2;

	/**
	 * index of coordinate for e1^e2^e3 in circle
	 */
	public static final int E1_E2_E3 = 3;

	/**
	 * index of coordinate for no^e1^ni in circle
	 */
	public static final int NO_E1_NI = 4;

	/**
	 * index of coordinate for no^e2^ni in circle
	 */
	public static final int NO_E2_NI = 5;

	/**
	 * index of coordinate for e1^e2^ni in circle
	 */
	public static final int E1_E2_NI = 6;

	/**
	 * index of coordinate for no^e3^ni in circle
	 */
	public static final int NO_E3_NI = 7;

	/**
	 * index of coordinate for e1^e3^ni in circle
	 */
	public static final int E1_E3_NI = 8;

	/**
	 * index of coordinate for e2^e3^ni in circle
	 */
	public static final int E2_E3_NI = 9;

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni
	};
	public static final CoordinateOrder coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni = CoordinateOrder.coord_noe1e2_noe1e3_noe2e3_e1e2e3_noe1ni_noe2ni_e1e2ni_noe3ni_e1e3ni_e2e3ni;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new circle with variable coordinates set to 0. */
	public circle() {set();}

    /** Copy constructor. */
	public circle(final circle A) {set(A);}



	/** Constructs a new circle from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public circle(final mv A /*, final int filler */) {set(A);}

	/** Constructs a new circle. Coordinate values come from 'A'. */
	public circle(final CoordinateOrder co, final double[] A) {set(co, A);}
	
	/** Constructs a new circle with each coordinate specified. */
	public circle(final CoordinateOrder co,  final double no_e1_e2, final double no_e1_e3, final double no_e2_e3, final double e1_e2_e3, final double no_e1_ni, final double no_e2_ni, final double e1_e2_ni, final double no_e3_ni, final double e1_e3_ni, final double e2_e3_ni) {
		set(co, no_e1_e2, no_e1_e3, no_e2_e3, e1_e2_e3, no_e1_ni, no_e2_ni, e1_e2_ni, no_e3_ni, e1_e3_ni, e2_e3_ni);
	}

public final void set()
{
	m_no_e1_e2 = m_no_e1_e3 = m_no_e2_e3 = m_e1_e2_e3 = m_no_e1_ni = m_no_e2_ni = m_e1_e2_ni = m_no_e3_ni = m_e1_e3_ni = m_e2_e3_ni = 0.0;

}

public final void set(final double scalarVal)
{
	m_no_e1_e2 = m_no_e1_e3 = m_no_e2_e3 = m_e1_e2_e3 = m_no_e1_ni = m_no_e2_ni = m_e1_e2_ni = m_no_e3_ni = m_e1_e3_ni = m_e2_e3_ni = 0.0;

}

public final void set(final CoordinateOrder co, final double _no_e1_e2, final double _no_e1_e3, final double _no_e2_e3, final double _e1_e2_e3, final double _no_e1_ni, final double _no_e2_ni, final double _e1_e2_ni, final double _no_e3_ni, final double _e1_e3_ni, final double _e2_e3_ni)
{
	m_no_e1_e2 = _no_e1_e2;
	m_no_e1_e3 = _no_e1_e3;
	m_no_e2_e3 = _no_e2_e3;
	m_e1_e2_e3 = _e1_e2_e3;
	m_no_e1_ni = _no_e1_ni;
	m_no_e2_ni = _no_e2_ni;
	m_e1_e2_ni = _e1_e2_ni;
	m_no_e3_ni = _no_e3_ni;
	m_e1_e3_ni = _e1_e3_ni;
	m_e2_e3_ni = _e2_e3_ni;

}

public final void set(final CoordinateOrder co, final double[] A)
{
	m_no_e1_e2 = A[0];
	m_no_e1_e3 = A[1];
	m_no_e2_e3 = A[2];
	m_e1_e2_e3 = A[3];
	m_no_e1_ni = A[4];
	m_no_e2_ni = A[5];
	m_e1_e2_ni = A[6];
	m_no_e3_ni = A[7];
	m_e1_e3_ni = A[8];
	m_e2_e3_ni = A[9];

}

public final void set(final circle a)
{
	m_no_e1_e2 = a.m_no_e1_e2;
	m_no_e1_e3 = a.m_no_e1_e3;
	m_no_e2_e3 = a.m_no_e2_e3;
	m_e1_e2_e3 = a.m_e1_e2_e3;
	m_no_e1_ni = a.m_no_e1_ni;
	m_no_e2_ni = a.m_no_e2_ni;
	m_e1_e2_ni = a.m_e1_e2_ni;
	m_no_e3_ni = a.m_no_e3_ni;
	m_e1_e3_ni = a.m_e1_e3_ni;
	m_e2_e3_ni = a.m_e2_e3_ni;

}
	public final void set(final mv src) {
		if (src.c()[3] != null) {
			double[] ptr = src.c()[3];
			m_no_e1_e2 = ptr[0];
			m_no_e1_e3 = ptr[1];
			m_no_e2_e3 = ptr[2];
			m_e1_e2_e3 = ptr[3];
			m_no_e1_ni = ptr[4];
			m_no_e2_ni = ptr[5];
			m_e1_e2_ni = ptr[6];
			m_no_e3_ni = ptr[7];
			m_e1_e3_ni = ptr[8];
			m_e2_e3_ni = ptr[9];
		}
		else {
			m_no_e1_e2 = 0.0;
			m_no_e1_e3 = 0.0;
			m_no_e2_e3 = 0.0;
			m_e1_e2_e3 = 0.0;
			m_no_e1_ni = 0.0;
			m_no_e2_ni = 0.0;
			m_e1_e2_ni = 0.0;
			m_no_e3_ni = 0.0;
			m_e1_e3_ni = 0.0;
			m_e2_e3_ni = 0.0;
		}
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = Math.abs(m_no_e1_e2);
		if (Math.abs(m_no_e1_e3) > maxValue) { maxValue = Math.abs(m_no_e1_e3); }
		if (Math.abs(m_no_e2_e3) > maxValue) { maxValue = Math.abs(m_no_e2_e3); }
		if (Math.abs(m_e1_e2_e3) > maxValue) { maxValue = Math.abs(m_e1_e2_e3); }
		if (Math.abs(m_no_e1_ni) > maxValue) { maxValue = Math.abs(m_no_e1_ni); }
		if (Math.abs(m_no_e2_ni) > maxValue) { maxValue = Math.abs(m_no_e2_ni); }
		if (Math.abs(m_e1_e2_ni) > maxValue) { maxValue = Math.abs(m_e1_e2_ni); }
		if (Math.abs(m_no_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e3_ni); }
		if (Math.abs(m_e1_e3_ni) > maxValue) { maxValue = Math.abs(m_e1_e3_ni); }
		if (Math.abs(m_e2_e3_ni) > maxValue) { maxValue = Math.abs(m_e2_e3_ni); }
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = Math.abs(m_no_e1_e2);
		bm = 0;
		if (Math.abs(m_no_e1_e3) > maxValue) { maxValue = Math.abs(m_no_e1_e3); bm = 11; }
		if (Math.abs(m_no_e2_e3) > maxValue) { maxValue = Math.abs(m_no_e2_e3); bm = 13; }
		if (Math.abs(m_e1_e2_e3) > maxValue) { maxValue = Math.abs(m_e1_e2_e3); bm = 14; }
		if (Math.abs(m_no_e1_ni) > maxValue) { maxValue = Math.abs(m_no_e1_ni); bm = 19; }
		if (Math.abs(m_no_e2_ni) > maxValue) { maxValue = Math.abs(m_no_e2_ni); bm = 21; }
		if (Math.abs(m_e1_e2_ni) > maxValue) { maxValue = Math.abs(m_e1_e2_ni); bm = 22; }
		if (Math.abs(m_no_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e3_ni); bm = 25; }
		if (Math.abs(m_e1_e3_ni) > maxValue) { maxValue = Math.abs(m_e1_e3_ni); bm = 26; }
		if (Math.abs(m_e2_e3_ni) > maxValue) { maxValue = Math.abs(m_e2_e3_ni); bm = 28; }
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
	 * Returns the no^e1^e2 coordinate.
	 */
	public final double get_no_e1_e2() { return m_no_e1_e2;}
	/**
	 * Sets the no^e1^e2 coordinate.
	 */
	public final void set_no_e1_e2(double no_e1_e2) { m_no_e1_e2 = no_e1_e2;}
	/**
	 * Returns the no^e1^e3 coordinate.
	 */
	public final double get_no_e1_e3() { return m_no_e1_e3;}
	/**
	 * Sets the no^e1^e3 coordinate.
	 */
	public final void set_no_e1_e3(double no_e1_e3) { m_no_e1_e3 = no_e1_e3;}
	/**
	 * Returns the no^e2^e3 coordinate.
	 */
	public final double get_no_e2_e3() { return m_no_e2_e3;}
	/**
	 * Sets the no^e2^e3 coordinate.
	 */
	public final void set_no_e2_e3(double no_e2_e3) { m_no_e2_e3 = no_e2_e3;}
	/**
	 * Returns the e1^e2^e3 coordinate.
	 */
	public final double get_e1_e2_e3() { return m_e1_e2_e3;}
	/**
	 * Sets the e1^e2^e3 coordinate.
	 */
	public final void set_e1_e2_e3(double e1_e2_e3) { m_e1_e2_e3 = e1_e2_e3;}
	/**
	 * Returns the no^e1^ni coordinate.
	 */
	public final double get_no_e1_ni() { return m_no_e1_ni;}
	/**
	 * Sets the no^e1^ni coordinate.
	 */
	public final void set_no_e1_ni(double no_e1_ni) { m_no_e1_ni = no_e1_ni;}
	/**
	 * Returns the no^e2^ni coordinate.
	 */
	public final double get_no_e2_ni() { return m_no_e2_ni;}
	/**
	 * Sets the no^e2^ni coordinate.
	 */
	public final void set_no_e2_ni(double no_e2_ni) { m_no_e2_ni = no_e2_ni;}
	/**
	 * Returns the e1^e2^ni coordinate.
	 */
	public final double get_e1_e2_ni() { return m_e1_e2_ni;}
	/**
	 * Sets the e1^e2^ni coordinate.
	 */
	public final void set_e1_e2_ni(double e1_e2_ni) { m_e1_e2_ni = e1_e2_ni;}
	/**
	 * Returns the no^e3^ni coordinate.
	 */
	public final double get_no_e3_ni() { return m_no_e3_ni;}
	/**
	 * Sets the no^e3^ni coordinate.
	 */
	public final void set_no_e3_ni(double no_e3_ni) { m_no_e3_ni = no_e3_ni;}
	/**
	 * Returns the e1^e3^ni coordinate.
	 */
	public final double get_e1_e3_ni() { return m_e1_e3_ni;}
	/**
	 * Sets the e1^e3^ni coordinate.
	 */
	public final void set_e1_e3_ni(double e1_e3_ni) { m_e1_e3_ni = e1_e3_ni;}
	/**
	 * Returns the e2^e3^ni coordinate.
	 */
	public final double get_e2_e3_ni() { return m_e2_e3_ni;}
	/**
	 * Sets the e2^e3^ni coordinate.
	 */
	public final void set_e2_e3_ni(double e2_e3_ni) { m_e2_e3_ni = e2_e3_ni;}
	/**
	 * Returns the scalar coordinate (which is always 0).
	 */
	public final double get_scalar() { return 0.0;}
} // end of class circle
