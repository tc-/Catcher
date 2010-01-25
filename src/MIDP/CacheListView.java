/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import GUI.ICacheListView;
import javax.microedition.lcdui.Graphics;
import GUI.IViewNavigator;
import System.Cache;

public class CacheListView extends CatcherCanvas implements ICacheListView {

    private Cache[] caches;
    private Cache selected;

    public Cache getSelected() {
        return selected;
    }

    public void setSelected(Cache selected) {
        this.selected = selected;
    }

    public Cache[] getCaches() {
        return caches;
    }

    public void setCaches(Cache[] caches) {
        this.caches = caches;
    }

    /**
     * constructor
     */
    public CacheListView(IViewNavigator viewNavigator, ViewResources viewResources) {
        super(viewNavigator);
        setFullScreenMode(true);

        this.viewResources = viewResources;
    }
    
    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.drawString("CacheListView",0,0,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected void keyPressed(int keyCode) {
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