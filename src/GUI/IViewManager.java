/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;


public interface IViewManager {

    void setCurrent(IView view);
    IView getCurrent();

    ICacheView getCacheView();
    ICacheListView getCacheListView();
    ICompassView getCompassView();
    IMapView getMapView();

}
