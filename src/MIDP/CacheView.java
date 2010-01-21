/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import GUI.ICacheView;
import GUI.IViewNavigator;
import System.Cache;
import javax.microedition.lcdui.Graphics;

public class CacheView extends CatcherCanvas implements ICacheView {

    private Cache cache;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * constructor
     */
    public CacheView(IViewNavigator viewNavigator, ViewResources viewResources) {
        setFullScreenMode(true);
        this.viewNavigator = viewNavigator;
        this.viewResources = viewResources;
    }
    
    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight()-HEIGHT_STATUSBAR);

        paintStatusBar(g);
        paintSelectedCache(g);

        g.setColor(0, 0, 0);
        g.drawString("CacheView",0,40,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {
        if (globalKeyPressed(keyCode)) { return; }
        // Local events goes here
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode) {
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode) {
    }
    
    /**
     * Called when the pointer is dragged.
     */
    protected  void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y) {
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y) {
    }

    public void activate() {
        
    }

    public void deactivate() {
        
    }
}