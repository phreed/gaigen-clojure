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
 * This class can hold a specialized multivector of type sphere.
 * 
 * The coordinates are stored in type double.
 * 
 * The variable non-zero coordinates are:
 *   - coordinate e1^e2^e3^ni  (array index: E1_E2_E3_NI = 0)
 *   - coordinate no^e2^e3^ni  (array index: NO_E2_E3_NI = 1)
 *   - coordinate no^e1^e3^ni  (array index: NO_E1_E3_NI = 2)
 *   - coordinate no^e1^e2^ni  (array index: NO_E1_E2_NI = 3)
 *   - coordinate no^e1^e2^e3  (array index: NO_E1_E2_E3 = 4)
 * 
 * The type has no constant coordinates.
 * 
 * 
 */
public class sphere  implements  mv_if
{ 
	/**
	 * The e1^e2^e3^ni coordinate.
	 */
	protected double m_e1_e2_e3_ni;
	/**
	 * The no^e2^e3^ni coordinate.
	 */
	protected double m_no_e2_e3_ni;
	/**
	 * The no^e1^e3^ni coordinate.
	 */
	protected double m_no_e1_e3_ni;
	/**
	 * The no^e1^e2^ni coordinate.
	 */
	protected double m_no_e1_e2_ni;
	/**
	 * The no^e1^e2^e3 coordinate.
	 */
	protected double m_no_e1_e2_e3;
	/**
	 * Array indices of sphere coordinates.
	 */

	/**
	 * index of coordinate for e1^e2^e3^ni in sphere
	 */
	public static final int E1_E2_E3_NI = 0;

	/**
	 * index of coordinate for no^e2^e3^ni in sphere
	 */
	public static final int NO_E2_E3_NI = 1;

	/**
	 * index of coordinate for no^e1^e3^ni in sphere
	 */
	public static final int NO_E1_E3_NI = 2;

	/**
	 * index of coordinate for no^e1^e2^ni in sphere
	 */
	public static final int NO_E1_E2_NI = 3;

	/**
	 * index of coordinate for no^e1^e2^e3 in sphere
	 */
	public static final int NO_E1_E2_E3 = 4;

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3
	};
	public static final CoordinateOrder coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3 = CoordinateOrder.coord_e1e2e3ni_noe2e3ni_noe1e3ni_noe1e2ni_noe1e2e3;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new sphere with variable coordinates set to 0. */
	public sphere() {set();}

    /** Copy constructor. */
	public sphere(final sphere A) {set(A);}



	/** Constructs a new sphere from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public sphere(final mv A /*, final int filler */) {set(A);}

	/** Constructs a new sphere. Coordinate values come from 'A'. */
	public sphere(final CoordinateOrder co, final double[] A) {set(co, A);}
	
	/** Constructs a new sphere with each coordinate specified. */
	public sphere(final CoordinateOrder co,  final double e1_e2_e3_ni, final double no_e2_e3_ni, final double no_e1_e3_ni, final double no_e1_e2_ni, final double no_e1_e2_e3) {
		set(co, e1_e2_e3_ni, no_e2_e3_ni, no_e1_e3_ni, no_e1_e2_ni, no_e1_e2_e3);
	}

public final void set()
{
	m_e1_e2_e3_ni = m_no_e2_e3_ni = m_no_e1_e3_ni = m_no_e1_e2_ni = m_no_e1_e2_e3 = 0.0;

}

public final void set(final double scalarVal)
{
	m_e1_e2_e3_ni = m_no_e2_e3_ni = m_no_e1_e3_ni = m_no_e1_e2_ni = m_no_e1_e2_e3 = 0.0;

}

public final void set(final CoordinateOrder co, final double _e1_e2_e3_ni, final double _no_e2_e3_ni, final double _no_e1_e3_ni, final double _no_e1_e2_ni, final double _no_e1_e2_e3)
{
	m_e1_e2_e3_ni = _e1_e2_e3_ni;
	m_no_e2_e3_ni = _no_e2_e3_ni;
	m_no_e1_e3_ni = _no_e1_e3_ni;
	m_no_e1_e2_ni = _no_e1_e2_ni;
	m_no_e1_e2_e3 = _no_e1_e2_e3;

}

public final void set(final CoordinateOrder co, final double[] A)
{
	m_e1_e2_e3_ni = A[0];
	m_no_e2_e3_ni = A[1];
	m_no_e1_e3_ni = A[2];
	m_no_e1_e2_ni = A[3];
	m_no_e1_e2_e3 = A[4];

}

public final void set(final sphere a)
{
	m_e1_e2_e3_ni = a.m_e1_e2_e3_ni;
	m_no_e2_e3_ni = a.m_no_e2_e3_ni;
	m_no_e1_e3_ni = a.m_no_e1_e3_ni;
	m_no_e1_e2_ni = a.m_no_e1_e2_ni;
	m_no_e1_e2_e3 = a.m_no_e1_e2_e3;

}
	public final void set(final mv src) {
		if (src.c()[4] != null) {
			double[] ptr = src.c()[4];
			m_e1_e2_e3_ni = ptr[4];
			m_no_e2_e3_ni = ptr[3];
			m_no_e1_e3_ni = ptr[2];
			m_no_e1_e2_ni = ptr[1];
			m_no_e1_e2_e3 = ptr[0];
		}
		else {
			m_e1_e2_e3_ni = 0.0;
			m_no_e2_e3_ni = 0.0;
			m_no_e1_e3_ni = 0.0;
			m_no_e1_e2_ni = 0.0;
			m_no_e1_e2_e3 = 0.0;
		}
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = Math.abs(m_e1_e2_e3_ni);
		if (Math.abs(m_no_e2_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e2_e3_ni); }
		if (Math.abs(m_no_e1_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e1_e3_ni); }
		if (Math.abs(m_no_e1_e2_ni) > maxValue) { maxValue = Math.abs(m_no_e1_e2_ni); }
		if (Math.abs(m_no_e1_e2_e3) > maxValue) { maxValue = Math.abs(m_no_e1_e2_e3); }
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = Math.abs(m_e1_e2_e3_ni);
		bm = 0;
		if (Math.abs(m_no_e2_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e2_e3_ni); bm = 29; }
		if (Math.abs(m_no_e1_e3_ni) > maxValue) { maxValue = Math.abs(m_no_e1_e3_ni); bm = 27; }
		if (Math.abs(m_no_e1_e2_ni) > maxValue) { maxValue = Math.abs(m_no_e1_e2_ni); bm = 23; }
		if (Math.abs(m_no_e1_e2_e3) > maxValue) { maxValue = Math.abs(m_no_e1_e2_e3); bm = 15; }
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
	 * Returns the e1^e2^e3^ni coordinate.
	 */
	public final double get_e1_e2_e3_ni() { return m_e1_e2_e3_ni;}
	/**
	 * Sets the e1^e2^e3^ni coordinate.
	 */
	public final void set_e1_e2_e3_ni(double e1_e2_e3_ni) { m_e1_e2_e3_ni = e1_e2_e3_ni;}
	/**
	 * Returns the no^e2^e3^ni coordinate.
	 */
	public final double get_no_e2_e3_ni() { return m_no_e2_e3_ni;}
	/**
	 * Sets the no^e2^e3^ni coordinate.
	 */
	public final void set_no_e2_e3_ni(double no_e2_e3_ni) { m_no_e2_e3_ni = no_e2_e3_ni;}
	/**
	 * Returns the no^e1^e3^ni coordinate.
	 */
	public final double get_no_e1_e3_ni() { return m_no_e1_e3_ni;}
	/**
	 * Sets the no^e1^e3^ni coordinate.
	 */
	public final void set_no_e1_e3_ni(double no_e1_e3_ni) { m_no_e1_e3_ni = no_e1_e3_ni;}
	/**
	 * Returns the no^e1^e2^ni coordinate.
	 */
	public final double get_no_e1_e2_ni() { return m_no_e1_e2_ni;}
	/**
	 * Sets the no^e1^e2^ni coordinate.
	 */
	public final void set_no_e1_e2_ni(double no_e1_e2_ni) { m_no_e1_e2_ni = no_e1_e2_ni;}
	/**
	 * Returns the no^e1^e2^e3 coordinate.
	 */
	public final double get_no_e1_e2_e3() { return m_no_e1_e2_e3;}
	/**
	 * Sets the no^e1^e2^e3 coordinate.
	 */
	public final void set_no_e1_e2_e3(double no_e1_e2_e3) { m_no_e1_e2_e3 = no_e1_e2_e3;}
	/**
	 * Returns the scalar coordinate (which is always 0).
	 */
	public final double get_scalar() { return 0.0;}
} // end of class sphere
