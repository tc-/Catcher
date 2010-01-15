/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.ICacheProvider;
import System.IMapProvider;
import System.IPositionProvider;

/**
 * Provides the rest of the application with views and possibility to display them
 */
public interface IViewManager {

    /**
     * Set the currently displayed view
     * @param view view to display
     */
    void setCurrent(IView view);

    /**
     * Get currently displayed view
     * @return current view or null if none is set yet
     */
    IView getCurrent();

    /**
     * Creates and returns the cache view
     * @return cache view
     */
    ICacheView getCacheView();

    /**
     * Creates and returns the cache list view
     * @param cacheProvider ICacheProvider to get the caches to display from
     * @param positionProvider IPositionProvider to get current position and orientation from
     * @return cache list view
     */
    ICacheListView getCacheListView(ICacheProvider cacheProvider, IPositionProvider positionProvider);

    /**
     * Creates and returns the compass view
     * @param cacheProvider ICacheProvider to get the caches to display from
     * @param positionProvider IPositionProvider to get current position and orientation from
     * @return compass view
     */
    ICompassView getCompassView(ICacheProvider cacheProvider, IPositionProvider positionProvider);

    /**
     * Creates and returns the map view
     * @param cacheProvider ICacheProvider to get the caches to display from
     * @param positionProvider IPositionProvider to get current position and orientation from
     * @param mapProvider IMapProvider to receive maps from
     * @return map view
     */
    IMapView getMapView(ICacheProvider cacheProvider, IPositionProvider positionProvider, IMapProvider mapProvider);

}
