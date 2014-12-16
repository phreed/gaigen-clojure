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
 * This class can hold a specialized multivector of type I5i_t.
 * 
 * The coordinates are stored in type double.
 * 
 * The type is constant.
 * 
 * The constant non-zero coordinates are:
 *   - no^e1^e2^e3^ni = -1
 * 
 * 
 */
public class I5i_t  implements  mv_if
{ 
	/**
	 * Array indices of I5i_t coordinates.
	 */

	/**
	 * The order of coordinates (this is the type of the first argument of coordinate-handling functions.
	 */
	private enum CoordinateOrder {
		coord
	};
	public static final CoordinateOrder coord = CoordinateOrder.coord;

	public final mv to_mv() {
		return new mv(this);
	}

    /** Constructs a new I5i_t with variable coordinates set to 0. */
	public I5i_t() {set();}

    /** Copy constructor. */
	public I5i_t(final I5i_t A) {set(A);}



	/** Constructs a new I5i_t from mv.
	 *  @param A The value to copy. Coordinates that cannot be represented
	 *  are silently dropped.
	 */
	public I5i_t(final mv A /*, final int filler */) {set(A);}


public final void set()
{

}

public final void set(final double scalarVal)
{

}

public final void set(final CoordinateOrder co)
{

}

public final void set(final CoordinateOrder co, final double[] A)
{

}

public final void set(final I5i_t a)
{

}
	public final void set(final mv src) {
	}

	/**
	 * Returns the absolute largest coordinate.
	 */
	public final double largestCoordinate() {
		double maxValue = 1.0;
		return maxValue;
	}
	/**
	 * Returns the absolute largest coordinate,
	 * and the corresponding basis blade bitmap.
	 */
	public final double[] largestBasisBlade()  {
		int bm;
		double maxValue = 1.0;
		bm = 31;
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
	 * Returns the no^e1^e2^e3^ni coordinate.
	 */
	public final double get_no_e1_e2_e3_ni() { return -1.0;}
	/**
	 * Returns the scalar coordinate (which is always 0).
	 */
	public final double get_scalar() { return 0.0;}
} // end of class I5i_t
