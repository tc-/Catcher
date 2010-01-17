/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import GUI.*;
import System.Direction;
import System.MathUtils;
import System.Position;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;


public class CompassView extends Canvas implements ICompassView {

    private IViewNavigator viewNavigator;

    private Position myPosition;
    private Direction myDirection;
    private Position targetPosition;

    public Direction getMyDirection() {
        return myDirection;
    }

    public Position getMyPosition() {
        return myPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setMyDirection(Direction myDirection) {
        this.myDirection = myDirection;
    }

    public void setMyPosition(Position myPosition) {
        this.myPosition = myPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }


    /**
     * constructor
     */
    public CompassView(IViewNavigator viewNavigator) {
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
        g.drawString("CompassView",0,0,Graphics.TOP|Graphics.LEFT);
        
        // Draw compass
        int bearing = 57;
        int maxX = getWidth();
        int maxY = getHeight();
        int compassDia = (maxX<maxY ? maxX:maxY);
        g.drawArc(5, 5, compassDia-10, compassDia-10, 0, 360);
        g.drawArc(15, 15, compassDia-30, compassDia-30, 0, 360);

        int x1,x2,y1,y2;
        int xOffs = compassDia / 2;
        int yOffs = xOffs;

        int j;
        for (int i=0;i<360;i+=10) {
          j = i+bearing;
          j = (j < 360? j : j-360);
          x1 = xOffs+MathUtils.iSin(j, 105);
          y1 = yOffs+MathUtils.iCos(j, 105);
          x2 = xOffs+MathUtils.iSin(j, 115);
          y2 = yOffs+MathUtils.iCos(j, 115);
          g.drawLine(x1, y1, x2, y2);
        }
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {
        switch(getGameAction(keyCode)) {
            case LEFT:
                viewNavigator.ShowPrevious();
                break;
            case RIGHT:
                viewNavigator.ShowNext();
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
