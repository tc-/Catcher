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
    private CompassView compassView = null;

    public void startApp() {

        mapView = new MapView(this);
        cacheView = new CacheView(this);
        compassView = new CompassView(this);

        display().setCurrent(mapView);
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
            display().setCurrent(compassView);
            compassView.activate();
        }
        else if (current == compassView)
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
        current.deactivate();
        if (current == mapView)
        {
            display().setCurrent(cacheView);
            cacheView.activate();
        }
        else if (current == cacheView)
        {
            Display.getDisplay(this).setCurrent(compassView);
            compassView.activate();
        }
        else if (current == compassView)
        {
            display().setCurrent(mapView);
            mapView.activate();
        }
    }

    private Display display()
    {
        return Display.getDisplay(this);
    }

}
