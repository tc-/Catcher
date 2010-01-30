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
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class CacheView extends CatcherCanvas implements ICacheView {

    private Cache cache;
    private int fontFace = Font.FACE_SYSTEM;

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

        g.setColor(0);
        int y = 0;
        Font font;
        font = Font.getFont(fontFace, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_BOLD, Font.SIZE_SMALL);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_BOLD, Font.SIZE_LARGE);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_ITALIC, Font.SIZE_SMALL);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
        font = Font.getFont(fontFace, Font.STYLE_ITALIC, Font.SIZE_LARGE);
        g.setFont(font);
        g.drawString(String.valueOf(font.getHeight())+" Testing 123.!&/(#%", 0, y, Graphics.TOP|Graphics.LEFT);
        y += font.getHeight();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressedView(int keyCode) {
        switch (keyCode) {
            case KEY_NUM1:
                fontFace = Font.FACE_MONOSPACE;
                break;
            case KEY_NUM2:
                fontFace = Font.FACE_PROPORTIONAL;
                break;
        }
        this.repaint();
    }

    public void activate() {
        
    }

    public void deactivate() {
        
    }
}