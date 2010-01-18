/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

public interface IMapProvider {

    /*
     * Returns a coordinate for a given pixel position in the view
     */
    Position pixelXYtoPosition(int x, int y);

    Object getMap(Position center, int width, int height);

    int lonToX(double lon);
    int latToY(double lat);
}