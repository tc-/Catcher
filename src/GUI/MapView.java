/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.IEventHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Displayable;


public class MapView extends Canvas implements CommandListener {

    private String msg = "Greetings Catcher";

    private IEventHandler showNextView;
    private IEventHandler showPreviousView;

    /**
     * constructor
     */
    public MapView(IEventHandler showPreviousView, IEventHandler showNextView) {
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
        g.drawString("MapView",0,0,Graphics.TOP|Graphics.LEFT);
        g.drawString(this.msg,0,20,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {
        switch(getGameAction(keyCode)) {
            case GAME_A: msg="game_a";break;
            case GAME_B: msg="game_b";break;
            case GAME_C: msg="game_c";break;
            case GAME_D: msg="game_d";break;
            case UP: msg="game_up";break;
            case DOWN: msg="game_down";break;
            case LEFT:
                msg="game_left";
                showPreviousView.executeHandler(this, null);
                break;
            case RIGHT:
                msg="game_right";
                showNextView.executeHandler(this, null);
                break;
            case FIRE: msg="game_fire";break;
            default:
                switch(keyCode) {
                    case KEY_NUM0: msg="0";break;
                    case KEY_NUM1: msg="1";break;
                    case KEY_NUM2: msg="2";break;
                    case KEY_NUM3: msg="3";break;
                    case KEY_NUM4: msg="4";break;
                    case KEY_NUM5: msg="5";break;
                    case KEY_NUM6: msg="6";break;
                    case KEY_NUM7: msg="7";break;
                    case KEY_NUM8: msg="8";break;
                    case KEY_NUM9: msg="9";break;
                    default: msg = "Unknown: "+String.valueOf(keyCode);
                }
        }
        this.repaint();
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
    
    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

}