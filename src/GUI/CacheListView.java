/*
 * Catcher, CacheListView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class CacheListView extends Canvas implements IView {

    private IViewNavigator viewNavigator;

    /**
     * constructor
     */
    public CacheListView(IViewNavigator viewNavigator) {
        setFullScreenMode(true);
        this.viewNavigator = viewNavigator;
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
    protected  void keyPressed(int keyCode) {
        switch(getGameAction(keyCode)) {
            case LEFT:
                viewNavigator.ShowPrevious(this);
                break;
            case RIGHT:
                viewNavigator.ShowNext(this);
                break;
        }
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