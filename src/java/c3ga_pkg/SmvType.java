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
public enum SmvType {
	C3GA_MV(0, "mv"),
	C3GA_DOUBLE(1, "double"),
	C3GA_NO_T(2, "no_t"),
	C3GA_E1_T(3, "e1_t"),
	C3GA_E2_T(4, "e2_t"),
	C3GA_E3_T(5, "e3_t"),
	C3GA_NI_T(6, "ni_t"),
	C3GA_NONI_T(7, "noni_t"),
	C3GA_I3_T(8, "I3_t"),
	C3GA_I5_T(9, "I5_t"),
	C3GA_I5I_T(10, "I5i_t"),
	C3GA_VECTORE3GA(11, "vectorE3GA"),
	C3GA_BIVECTORE3GA(12, "bivectorE3GA"),
	C3GA_ROTORE3GA(13, "rotorE3GA"),
	C3GA_NORMALIZEDPOINT(14, "normalizedPoint"),
	C3GA_DUALSPHERE(15, "dualSphere"),
	C3GA_DUALPLANE(16, "dualPlane"),
	C3GA_FREEVECTOR(17, "freeVector"),
	C3GA_FREEBIVECTOR(18, "freeBivector"),
	C3GA_FLATPOINT(19, "flatPoint"),
	C3GA_POINTPAIR(20, "pointPair"),
	C3GA_LINE(21, "line"),
	C3GA_CIRCLE(22, "circle"),
	C3GA_SPHERE(23, "sphere"),
	C3GA_PLANE(24, "plane"),
	C3GA_PSEUDOSCALAR(25, "pseudoscalar"),
	C3GA_NORMALIZEDTRANSLATOR(26, "normalizedTranslator"),
	C3GA_TRANSLATOR(27, "translator"),
	C3GA_RBM(28, "RBM"),
	C3GA_EVENVERSOR(29, "evenVersor"),
	C3GA_ODDVERSOR(30, "oddVersor"),
	C3GA_NONE(-1, "none");

	private final int id;
    private final String label;

    SmvType(final int id, final String label) {
        this.id = id;
        this.label = label;
    }
    
    public final int getId() {
        return id;
    }
 
    public final String toString() {
        return label;
    }
}
