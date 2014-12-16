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
 * This class can hold a specialized multivector of type translator.
 * 
 * The coordinates are stored in type double.
 * 
 * The variable non-zero coordinates are:
 *   - coordinate 1  (array index: SCALAR = 0)
 *   - coordinate e1^ni  (array index: E1_NI = 1)
 *   - coordinate e2^ni  (array index: E2_NI = 2)
 *   - coordinate e3^ni  (array index: E3_NI = 3)
 * 
 * The type has no constant coordinates.
 * 
 * 
 */
public class translator  implements  mv_if
{ 
	/**
	 * The 1 coordinate.
	 */
	protected double m_scalar;
	/**
	 * The e1^ni coordinate.
	 */
	protected double m_e1_ni;
	/**
	 * The e2^ni coordinate.
	 */
	protected double m_e2_ni;
	/**
	 * The e3^ni coordinate.
	 */
	protected double m_e3_ni;
	/**
	 * Array indices of translator coordinates.
	 */

	/**
	 * index of coordinate for 1 in translator
	 */
	public static final int SCALAR = 0;

	/**
	 * index of coordinate for e1^ni in translator
	 */
	public static final int E1_NI = 1;

	/**
	 * index of coordinate for e2^ni in translator
	 */
	public static final int E2_NI = 2;

	/**
	 * index of coordinate for e3^ni in translator
	 */
	public static final int E3_NI = 3;

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord_scalar_e1ni_e2ni_e3ni
	};
	public static final CoordinateOrder coord_scalar_e1ni_e2ni_e3ni = CoordinateOrder.coord_scalar_e1ni_e2ni_e3ni;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new translator with variable coordinates set to 0. */
	public translator() {set();}

    /** Copy constructor. */
	public translator(final translator A) {set(A);}


	/** Constructs a new translator with scalar value 'scalar'. */
	public translator(final double scalar) {set(scalar);}

	/** Constructs a new translator from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public translator(final mv A /*, final int filler */) {set(A);}

	/** Constructs a new translator. Coordinate values come from 'A'. */
	public translator(final CoordinateOrder co, final double[] A) {set(co, A);}
	
	/** Constructs a new translator with each coordinate specified. */
	public translator(final CoordinateOrder co,  final double scalar, final double e1_ni, final double e2_ni, final double e3_ni) {
		set(co, scalar, e1_ni, e2_ni, e3_ni);
	}

public final void set()
{
	m_scalar = m_e1_ni = m_e2_ni = m_e3_ni = 0.0;

}

public final void set(final double scalarVal)
{
	m_scalar = scalarVal;
	m_e1_ni = m_e2_ni = m_e3_ni = 0.0;

}

public final void set(final CoordinateOrder co, final double _scalar, final double _e1_ni, final double _e2_ni, final double _e3_ni)
{
	m_scalar = _scalar;
	m_e1_ni = _e1_ni;
	m_e2_ni = _e2_ni;
	m_e3_ni = _e3_ni;

}

public final void set(final CoordinateOrder co, final double[] A)
{
	m_scalar = A[0];
	m_e1_ni = A[1];
	m_e2_ni = A[2];
	m_e3_ni = A[3];

}

public final void set(final translator a)
{
	m_scalar = a.m_scalar;
	m_e1_ni = a.m_e1_ni;
	m_e2_ni = a.m_e2_ni;
	m_e3_ni = a.m_e3_ni;

}
	public final void set(final mv src) {
		if (src.c()[0] != null) {
			double[] ptr = src.c()[0];
			m_scalar = ptr[0];
		}
		else {
			m_scalar = 0.0;
		}
		if (src.c()[2] != null) {
			double[] ptr = src.c()[2];
			m_e1_ni = ptr[7];
			m_e2_ni = ptr[8];
			m_e3_ni = ptr[9];
		}
		else {
			m_e1_ni = 0.0;
			m_e2_ni = 0.0;
			m_e3_ni = 0.0;
		}
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = Math.abs(m_scalar);
		if (Math.abs(m_e1_ni) > maxValue) { maxValue = Math.abs(m_e1_ni); }
		if (Math.abs(m_e2_ni) > maxValue) { maxValue = Math.abs(m_e2_ni); }
		if (Math.abs(m_e3_ni) > maxValue) { maxValue = Math.abs(m_e3_ni); }
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = Math.abs(m_scalar);
		bm = 0;
		if (Math.abs(m_e1_ni) > maxValue) { maxValue = Math.abs(m_e1_ni); bm = 18; }
		if (Math.abs(m_e2_ni) > maxValue) { maxValue = Math.abs(m_e2_ni); bm = 20; }
		if (Math.abs(m_e3_ni) > maxValue) { maxValue = Math.abs(m_e3_ni); bm = 24; }
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
	 * Returns the 1 coordinate.
	 */
	public final double get_scalar() { return m_scalar;}
	/**
	 * Sets the 1 coordinate.
	 */
	public final void set_scalar(double scalar) { m_scalar = scalar;}
	/**
	 * Returns the e1^ni coordinate.
	 */
	public final double get_e1_ni() { return m_e1_ni;}
	/**
	 * Sets the e1^ni coordinate.
	 */
	public final void set_e1_ni(double e1_ni) { m_e1_ni = e1_ni;}
	/**
	 * Returns the e2^ni coordinate.
	 */
	public final double get_e2_ni() { return m_e2_ni;}
	/**
	 * Sets the e2^ni coordinate.
	 */
	public final void set_e2_ni(double e2_ni) { m_e2_ni = e2_ni;}
	/**
	 * Returns the e3^ni coordinate.
	 */
	public final double get_e3_ni() { return m_e3_ni;}
	/**
	 * Sets the e3^ni coordinate.
	 */
	public final void set_e3_ni(double e3_ni) { m_e3_ni = e3_ni;}
} // end of class translator
