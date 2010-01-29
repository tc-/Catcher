/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.Direction;
import System.Position;

/**
 * Displays a map with caches
 */
public interface IMapView extends IView {

    Position getCenter();
    void setCenter(Position center);

    int getZoom();
    void setZoom(int zoom);

    Direction getDirection();
    public void setDirection(Direction newDirection);

    public void maptileDownloaded();

}
