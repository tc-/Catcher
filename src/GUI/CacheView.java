/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import System.IEventHandler;
import javax.microedition.lcdui.*;

/**
 * @author tc
 */
public class CacheView extends Canvas {

    private IEventHandler showNextView;
    private IEventHandler showPreviousView;

    /**
     * constructor
     */
    public CacheView(IEventHandler showPreviousView, IEventHandler showNextView) {
        setFullScreenMode(true);
        this.showPreviousView = showPreviousView;
        this.showNextView = showNextView;
    }
    
    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(0, 0, 0);
        g.drawString("CacheView",0,0,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {
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

}