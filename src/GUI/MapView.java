/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        switch(keyCode) {
            case GAME_A: this.msg="game_a";break;
            case GAME_B: this.msg="game_b";break;
            case GAME_C: this.msg="game_c";break;
            case GAME_D: this.msg="game_d";break;
            case UP: this.msg="up";break;
            case DOWN: this.msg="down";break;
            case LEFT:
            case -3:
                this.msg="left";
                showPreviousView.executeHandler(this, null);
                break;
            case RIGHT:
            case -4:
                this.msg="right";
                showNextView.executeHandler(this, null);
                break;
            case FIRE: this.msg="fire";break;
            case KEY_NUM0: this.msg="0";break;
            case KEY_NUM1: this.msg="1";break;
            case KEY_NUM2: this.msg="2";break;
            case KEY_NUM3: this.msg="3";break;
            case KEY_NUM4: this.msg="4";break;
            case KEY_NUM5: this.msg="5";break;
            case KEY_NUM6: this.msg="6";break;
            case KEY_NUM7: this.msg="7";break;
            case KEY_NUM8: this.msg="8";break;
            case KEY_NUM9: this.msg="9";break;
            default: this.msg = "Unknown: "+String.valueOf(keyCode);
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
        msg = "Command"+command.getLabel();
        repaint();
    }

}