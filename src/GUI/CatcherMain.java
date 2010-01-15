/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.ICacheProvider;
import System.IMapProvider;
import System.IPlatformManager;
import System.IPositionProvider;
import System.ISettingsProvider;


/**
 * Controls the application in a platform independent manner
 */
public class CatcherMain implements IViewNavigator {

    private IPlatformManager platform;

    private IMapView mapView;
    private ICacheView cacheView;
    private ICompassView compassView;
    private ICacheListView cacheListView;

    public CatcherMain(IPlatformManager platform) {
        this.platform = platform;
    }

    /**
     * Run when application is started
     */
    public void start() {
        // Get platform implementations
        IViewManager views = platform.getViewManager();
        ISettingsProvider sett = platform.getSettingsProvider();
        ICacheProvider caches = sett.getCacheProvider();
        IPositionProvider pos = platform.getPositionProvider();
        IMapProvider maps = sett.getMapProvider();

        // Create the views
        mapView = views.getMapView(caches, pos, maps);
        cacheView = views.getCacheView();
        compassView = views.getCompassView(caches, pos);
        cacheListView = views.getCacheListView(caches, pos);

        // Restore last zoom and position
        mapView.setZoom(sett.getLastZoom());
        mapView.setCenter(sett.getLastPosition());

        // Set mapView as the initial view
        views.setCurrent(mapView);
    }

    /**
     * Run when application is paused by system
     */
    public void pause() {

    }

    /**
     * Run when the application should resume work after being paused
     */
    public void resume() {

    }

    /**
     * Run when application exits
     */
    public void stop() {

    }

    public void ShowNext()
    {
        IViewManager views = platform.getViewManager();
        IView current = views.getCurrent();
        if (current == mapView) views.setCurrent(compassView);
        else if (current == compassView) views.setCurrent(cacheView);
        else if (current == cacheView) views.setCurrent(mapView);
    }

    public void ShowPrevious()
    {
        IViewManager views = platform.getViewManager();
        IView current = views.getCurrent();
        if (current == mapView) views.setCurrent(cacheView);
        else if (current == cacheView) views.setCurrent(compassView);
        else if (current == compassView) views.setCurrent(mapView);
    }
    
}
