/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.Position;

/**
 * Displays a map with caches
 */
public interface IMapView extends IView {

    Position getCenter();

    int getZoom();

    void setCenter(Position center);

    void setZoom(int zoom);

    public void maptileDownloaded();

}
