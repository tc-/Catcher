/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import GUI.CatcherMain;
import GUI.ICompassView;
import GUI.IView;
import GUI.IViewManager;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;


public class CatcherMIDP extends MIDlet implements IViewManager {

    private IView current = null;
    private CatcherMain main = null;

    public void startApp() {
        if (main == null) {
            main = new CatcherMain(this);
            main.start();
        }
        else
        {
            main.resume();
        }
    }

    public void pauseApp() {
        main.pause();
    }

    public void destroyApp(boolean unconditional) {
        main.stop();
    }

    private Display display()
    {
        return Display.getDisplay(this);
    }

    public void setCurrent(IView view) {
        if (current != null) current.deactivate();

        display().setCurrent((Displayable)view);
        current = view;

        view.activate();
    }

    public IView getCurrent() {
        return current;
    }

    public IView getCacheView() {
        return new CacheView(main);
    }

    public IView getCacheListView() {
        return null;
    }

    public ICompassView getCompassView() {
        return new CompassView(main);
    }

    public IView getMapView() {
        return new MapView(main);
    }

}
