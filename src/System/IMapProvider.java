/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

public interface IMapProvider {
    /*
     * Zoom in one level if possible, otherwise returns max zoom.
     */
    int zoomIn(int zoom);

    /*
     * Zoom out one level if possible, otherwise returns min zoom.
     */
    int zoomOut(int zoom);

    /*
     * Returns a coordinate for a given pixel position in the map provided from
     * the last getMap() call.
     */
    Position XYtoPosition(int x, int y, Position center, int mapWidth,
            int mapHeight, int zoom);

    /*
     * Returns {mapX, mapY} from a given position.
     * It's assumed that getMap has been called prior to a call to this function
     */
    int[] positionToXY(Position position, Position center, int mapWidth,
            int mapHeight, int zoom);

    /*
     * Returns a map at current zoom level.
     */
    Object getMap(Position center, int width, int height, int zoom);
}