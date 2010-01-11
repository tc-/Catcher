/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import System.IEventHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class Catcher extends MIDlet {


    private MapView mapView = null;
    private CacheListView cacheListView = null;

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
        cacheListView = new CacheListView(prevHandler, nextHandler);

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
            Display.getDisplay(this).setCurrent(cacheListView);
        }
        else if (current == cacheListView)
        {
            Display.getDisplay(this).setCurrent(mapView);
        }
    }

    public void ShowPrevious(Canvas current)
    {
        if (current == mapView)
        {
            Display.getDisplay(this).setCurrent(cacheListView);
        }
        else if (current == cacheListView)
        {
            Display.getDisplay(this).setCurrent(mapView);
        }
    }
}
