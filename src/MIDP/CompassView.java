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
import javax.microedition.lcdui.Graphics;

public class CompassView extends CatcherCanvas implements ICompassView {

    private Position myPosition;
    private Direction myDirection;
    private Position targetPosition;

    // Careful when changing globalItems! menuAction() assumes a certain order!
    private String[] viewItems = {"Radar", "Tracks", "Guide"};

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
    public CompassView(IViewNavigator viewNavigator,
            ViewResources viewResources) {
        super(viewNavigator);

        setFullScreenMode(true);
        this.viewResources = viewResources;
    }

    /*
     * Draws a circular compass
     * It's assumed that bearing and target are 0>n>360
     */
    private void paintCompass(Graphics g, int bearing, int target, int x,
            int y, int width, int height) {

        g.setClip(x, y, width, height);
        g.setColor(0);

        int dia = (width < height? width : height);
        int radius = dia >> 1;
        int cx = x+radius;
        int cy = y+radius;

        g.drawArc(x, y, dia-1, dia-1, 0, 360);

        int angle = bearing;
        int tx1 = MathUtils.iCos(angle, radius);
        int tx2 = MathUtils.iCos(angle, radius-10);
        int tx3 = MathUtils.iCos(angle, radius-20);

        int ty1 = MathUtils.iSin(angle, radius);
        int ty2 = MathUtils.iSin(angle, radius-10);
        int ty3 = MathUtils.iSin(angle, radius-20);

        g.setColor(0xff0000);
        g.drawLine(cx+ty1, cy-tx1, cx+ty2, cy-tx2);
        g.drawString("N", cx+ty3, cy-tx3, Graphics.BASELINE|Graphics.HCENTER);

        g.setColor(0x000000);
        g.drawLine(cx+tx1, cy+ty1, cx+tx2, cy+ty2);
        g.drawString("E", cx+tx3, cy+ty3, Graphics.BASELINE|Graphics.HCENTER);
        g.drawLine(cx-ty1, cy+tx1, cx-ty2, cy+tx2);
        g.drawString("S", cx-ty3, cy+tx3, Graphics.BASELINE|Graphics.HCENTER);
        g.drawLine(cx-tx1, cy-ty1, cx-tx2, cy-ty2);
        g.drawString("W", cx-tx3, cy-ty3, Graphics.BASELINE|Graphics.HCENTER);

        angle += 45;
        if (angle >= 360) { angle -=360; }
        tx1 = MathUtils.iCos(angle, radius);
        tx2 = MathUtils.iCos(angle, radius-10);
        tx3 = MathUtils.iCos(angle, radius-20);

        ty1 = MathUtils.iSin(angle, radius);
        ty2 = MathUtils.iSin(angle, radius-10);
        ty3 = MathUtils.iSin(angle, radius-20);

        g.setColor(0x3f3f3f);
        g.drawLine(cx+ty1, cy-tx1, cx+ty2, cy-tx2);
        g.drawString("NE", cx+ty3, cy-tx3, Graphics.BASELINE|Graphics.HCENTER);
        g.drawLine(cx+tx1, cy+ty1, cx+tx2, cy+ty2);
        g.drawString("SE", cx+tx3, cy+ty3, Graphics.BASELINE|Graphics.HCENTER);
        g.drawLine(cx-ty1, cy+tx1, cx-ty2, cy+tx2);
        g.drawString("SW", cx-ty3, cy+tx3, Graphics.BASELINE|Graphics.HCENTER);
        g.drawLine(cx-tx1, cy-ty1, cx-tx2, cy-ty2);
        g.drawString("NW", cx-tx3, cy-ty3, Graphics.BASELINE|Graphics.HCENTER);

        // Draw target direction
        target = bearing+target;
        if (target >= 360) { target -= 360; }
        if (target < 0) { target += 360; }

        tx1 = MathUtils.iCos(target, radius-25);
        ty1 = MathUtils.iSin(target, radius-25);

        g.setColor(0xff00ff);
        g.drawLine(cx, cy, cx+ty1, cy-tx1);
    }

    public void paintView(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight()-HEIGHT_STATUSBAR);
        
        paintCompass(g, 5, 355, 30, 30, getWidth()-60, getHeight()-60);
    }
    
    /**
     * Called when a menu item has been selected
     * @param menuItem: index in view menu array
     */
    void menuActionView(int menuItem) {
        System.out.println("menuActionItem: "+String.valueOf(menuItem));
    }

    protected  void keyPressedView(int keyCode) {
    }
    
    public void activate() {
        menu.viewItems(viewItems);
    }

    public void deactivate() {
        menu.clearViewItems();
    }
}
