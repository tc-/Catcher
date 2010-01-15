/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import GUI.CatcherMain;
import GUI.ICacheListView;
import GUI.ICacheView;
import GUI.ICompassView;
import GUI.IMapView;
import GUI.IView;
import GUI.IViewManager;
import System.ICacheProvider;
import System.IImageLoader;
import System.IMapProvider;
import System.IPlatformManager;
import System.IPositionProvider;
import System.ISettingsProvider;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;


public class CatcherMIDP extends MIDlet implements IViewManager, IPlatformManager {

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

    public ICacheView getCacheView() {
        return new CacheView(main);
    }

    public ICacheListView getCacheListView(ICacheProvider cacheProvider, IPositionProvider positionProvider) {
        return new CacheListView(main);
    }

    public ICompassView getCompassView(ICacheProvider cacheProvider, IPositionProvider positionProvider) {
        return new CompassView(main);
    }

    public IMapView getMapView(ICacheProvider cacheProvider, IPositionProvider positionProvider, IMapProvider mapProvider) {
        return new MapView(main);
    }

    public IImageLoader getImageLoader() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IPositionProvider getPositionProvider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ISettingsProvider getSettingsProvider() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IViewManager getViewManager() {
        return this;
    }

}
