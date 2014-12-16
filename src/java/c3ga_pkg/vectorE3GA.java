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
 * This class can hold a specialized multivector of type vectorE3GA.
 * 
 * The coordinates are stored in type double.
 * 
 * The variable non-zero coordinates are:
 *   - coordinate e1  (array index: E1 = 0)
 *   - coordinate e2  (array index: E2 = 1)
 *   - coordinate e3  (array index: E3 = 2)
 * 
 * The type has no constant coordinates.
 * 
 * 
 */
public class vectorE3GA  implements  mv_if
{ 
	/**
	 * The e1 coordinate.
	 */
	protected double m_e1;
	/**
	 * The e2 coordinate.
	 */
	protected double m_e2;
	/**
	 * The e3 coordinate.
	 */
	protected double m_e3;
	/**
	 * Array indices of vectorE3GA coordinates.
	 */

	/**
	 * index of coordinate for e1 in vectorE3GA
	 */
	public static final int E1 = 0;

	/**
	 * index of coordinate for e2 in vectorE3GA
	 */
	public static final int E2 = 1;

	/**
	 * index of coordinate for e3 in vectorE3GA
	 */
	public static final int E3 = 2;

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord_e1_e2_e3
	};
	public static final CoordinateOrder coord_e1_e2_e3 = CoordinateOrder.coord_e1_e2_e3;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new vectorE3GA with variable coordinates set to 0. */
	public vectorE3GA() {set();}

    /** Copy constructor. */
	public vectorE3GA(final vectorE3GA A) {set(A);}



	/** Constructs a new vectorE3GA from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public vectorE3GA(final mv A /*, final int filler */) {set(A);}

	/** Constructs a new vectorE3GA. Coordinate values come from 'A'. */
	public vectorE3GA(final CoordinateOrder co, final double[] A) {set(co, A);}
	
	/** Constructs a new vectorE3GA with each coordinate specified. */
	public vectorE3GA(final CoordinateOrder co,  final double e1, final double e2, final double e3) {
		set(co, e1, e2, e3);
	}

public final void set()
{
	m_e1 = m_e2 = m_e3 = 0.0;

}

public final void set(final double scalarVal)
{
	m_e1 = m_e2 = m_e3 = 0.0;

}

public final void set(final CoordinateOrder co, final double _e1, final double _e2, final double _e3)
{
	m_e1 = _e1;
	m_e2 = _e2;
	m_e3 = _e3;

}

public final void set(final CoordinateOrder co, final double[] A)
{
	m_e1 = A[0];
	m_e2 = A[1];
	m_e3 = A[2];

}

public final void set(final vectorE3GA a)
{
	m_e1 = a.m_e1;
	m_e2 = a.m_e2;
	m_e3 = a.m_e3;

}
	public final void set(final mv src) {
		if (src.c()[1] != null) {
			double[] ptr = src.c()[1];
			m_e1 = ptr[1];
			m_e2 = ptr[2];
			m_e3 = ptr[3];
		}
		else {
			m_e1 = 0.0;
			m_e2 = 0.0;
			m_e3 = 0.0;
		}
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = Math.abs(m_e1);
		if (Math.abs(m_e2) > maxValue) { maxValue = Math.abs(m_e2); }
		if (Math.abs(m_e3) > maxValue) { maxValue = Math.abs(m_e3); }
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = Math.abs(m_e1);
		bm = 0;
		if (Math.abs(m_e2) > maxValue) { maxValue = Math.abs(m_e2); bm = 4; }
		if (Math.abs(m_e3) > maxValue) { maxValue = Math.abs(m_e3); bm = 8; }
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
	 * Returns the e1 coordinate.
	 */
	public final double get_e1() { return m_e1;}
	/**
	 * Sets the e1 coordinate.
	 */
	public final void set_e1(double e1) { m_e1 = e1;}
	/**
	 * Returns the e2 coordinate.
	 */
	public final double get_e2() { return m_e2;}
	/**
	 * Sets the e2 coordinate.
	 */
	public final void set_e2(double e2) { m_e2 = e2;}
	/**
	 * Returns the e3 coordinate.
	 */
	public final double get_e3() { return m_e3;}
	/**
	 * Sets the e3 coordinate.
	 */
	public final void set_e3(double e3) { m_e3 = e3;}
	/**
	 * Returns the scalar coordinate (which is always 0).
	 */
	public final double get_scalar() { return 0.0;}
} // end of class vectorE3GA
