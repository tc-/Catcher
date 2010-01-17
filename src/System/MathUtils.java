/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

public class MathUtils {

    private static int[] sincos_lut = {
        0, 571, 1143, 1714, 2285, 2855, 3425, 3993, 4560, 5126,
        5690, 6252, 6812, 7371, 7927, 8480, 9032, 9580, 10125, 10668, 11207,
        11743, 12275, 12803, 13327, 13848, 14364, 14876, 15383, 15886, 16383,
        16876, 17364, 17846, 18323, 18794, 19260, 19720, 20173, 20621, 21062,
        21497, 21926, 22347, 22762, 23170, 23571, 23964, 24351, 24730, 25101,
        25465, 25821, 26169, 26509, 26841, 27165, 27481, 27788, 28087, 28377,
        28659, 28932, 29196, 29451, 29697, 29935, 30163, 30381, 30591, 30791,
        30982, 31164, 31336, 31498, 31651, 31794, 31928, 32051, 32165, 32270,
        32364, 32449, 32523, 32588, 32643, 32688, 32723, 32748, 32763, 32768
    };


    public static int iSin(int d, int multi) {
        // range checking: if ((d < 0) || (d > 359)) { return 0; }
        int i;
        if (d < 180) {
            i = (d<90? sincos_lut[d] : sincos_lut[90-(d-90)]);
        } else {
            i = (d<270? -sincos_lut[d-180] : -sincos_lut[90-(d-270)]);
        }
        return (i*multi) >> 15;
    }

    public static int iCos(int d, int multi) {
        // range checking: if ((d < 0) || (d > 359)) {return 0; }
        int i;
        if (d < 180) {
            i = (d<90? sincos_lut[90-d] : -sincos_lut[d-90]);
        } else {
            i = (d<270? -sincos_lut[270-d] : sincos_lut[d-270]);
        }
        return (i*multi) >> 15;
    }

}
