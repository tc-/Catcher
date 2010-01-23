/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

public interface IMapProvider {

    /*
     * Get current zoom level
     */
    int getZoom();

    /*
     * Set zoom level
     */
    void setZoom(int zoom);

    /*
     * Zoom in one level. Returns false if at closest zoom level.
     */
    boolean zoomIn();

    /*
     * Zoom out one level. Return false if at outmost zoom level.
     */
    boolean zoomOut();

    /*
     * Returns a coordinate for a given pixel position in the map provided from
     * the last getMap() call.
     */
    Position XYtoPosition(int x, int y);

    /*
     * Returns {mapX, mapY} from a given position.
     * It's assumed that getMap has been called prior to a call to this function
     */
    int[] positionToXY(Position position);

    /*
     * Returns a map at current zoom level.
     */
    Object getMap(Position center, int width, int height);
}