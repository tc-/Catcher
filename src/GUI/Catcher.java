/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.IEventHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class Catcher extends MIDlet {

    private MapView mapView = null;
    private CacheView cacheView = null;
    private CompassView compassView = null;
//    private CacheListView cacheListView = null;

    public void startApp() {

        IEventHandler nextHandler = new IEventHandler()
        {
            public void executeHandler(Object sender, Object tag) {
                ShowNext((Canvas)sender);
            }
        };

        IEventHandler prevHandler = new IEventHandler()
        {
            public void executeHandler(Object sender, Object tag) {
                ShowPrevious((Canvas)sender);
            }
        };

        mapView = new MapView(prevHandler, nextHandler);
        cacheView = new CacheView(prevHandler, nextHandler);
        compassView = new CompassView(prevHandler, nextHandler);
//      cacheListView = new CacheListView(prevHandler, nextHandler);

        Display.getDisplay(this).setCurrent(mapView);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void ShowNext(Canvas current)
    {
        if (current == mapView)
        {
            Display.getDisplay(this).setCurrent(compassView);
        }
        else if (current == compassView)
        {
            Display.getDisplay(this).setCurrent(cacheView);
        }
        else if (current == cacheView)
        {
            Display.getDisplay(this).setCurrent(mapView);
        }
    }

    public void ShowPrevious(Canvas current)
    {
        if (current == mapView)
        {
            Display.getDisplay(this).setCurrent(cacheView);
        }
        else if (current == cacheView)
        {
            Display.getDisplay(this).setCurrent(compassView);
        }
        else if (current == compassView)
        {
            Display.getDisplay(this).setCurrent(mapView);
        }
    }
}
