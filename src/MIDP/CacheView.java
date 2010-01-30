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
import javax.microedition.lcdui.Image;

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
        super(viewNavigator);
        
        setFullScreenMode(true);
        this.viewResources = viewResources;
    }
    
    /**
     * paint
     */
    public void paintView(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight()-HEIGHT_STATUSBAR);

        g.setColor(0, 0, 0);
        g.drawString("CacheView",0,40,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressedView(int keyCode) {
    }

    public void activate() {
        
    }

    public void deactivate() {
        
    }
}