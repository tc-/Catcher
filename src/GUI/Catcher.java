/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class Catcher extends MIDlet implements IViewNavigator {

    private MapView mapView = null;
    private CacheView cacheView = null;
    private CacheListView cacheListView = null;

    public void startApp() {

        mapView = new MapView(this);
        cacheView = new CacheView(this);
        cacheListView = new CacheListView(this);

        display().setCurrent(cacheListView);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void ShowNext(IView current)
    {
        current.deactivate();

        if (current == mapView)
        {
            display().setCurrent(cacheListView);
            cacheListView.activate();
        }
        else if (current == cacheListView)
        {
            display().setCurrent(cacheView);
            cacheView.activate();
        }
        else if (current == cacheView)
        {
            display().setCurrent(mapView);
            mapView.activate();
        }
    }

    public void ShowPrevious(IView current)
    {
        if (current == mapView)
        {
            display().setCurrent(cacheView);
        }
        else if (current == cacheView)
        {
            display().setCurrent(cacheListView);
        }
        else if (current == cacheListView)
        {
            display().setCurrent(mapView);
        }
    }

    private Display display()
    {
        return Display.getDisplay(this);
    }

}
