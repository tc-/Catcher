/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;


public class CatcherMain implements IViewNavigator {

    private IViewManager views;

    private IMapView mapView;
    private ICacheView cacheView;
    private ICompassView compassView;
    private ICacheListView cacheListView;

    public CatcherMain(IViewManager views) {
        this.views = views;
    }

    public void start() {
        mapView = views.getMapView();
        cacheView = views.getCacheView();
        compassView = views.getCompassView();
        cacheListView = views.getCacheListView();
        
        views.setCurrent(mapView);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void stop() {

    }

    public void ShowNext()
    {
        IView current = views.getCurrent();
        if (current == mapView) views.setCurrent(compassView);
        else if (current == compassView) views.setCurrent(cacheView);
        else if (current == cacheView) views.setCurrent(mapView);
    }

    public void ShowPrevious()
    {
        IView current = views.getCurrent();
        if (current == mapView) views.setCurrent(cacheView);
        else if (current == cacheView) views.setCurrent(compassView);
        else if (current == compassView) views.setCurrent(mapView);
    }
    
}
