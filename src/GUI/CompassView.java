/*
 * Catcher, CompassView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;


/**
 * @author richie
 */
public class CompassView extends Canvas implements IView {

    private IViewNavigator viewNavigator;

    /**
     * constructor
     */
    public CompassView(IViewNavigator viewNavigator) {
        setFullScreenMode(true);
        this.viewNavigator = viewNavigator;
    } 

    private int[] sincos_lut = {0, 571, 1143, 1714, 2285, 2855, 3425, 3993, 4560, 5126, 
        5690, 6252, 6812, 7371, 7927, 8480, 9032, 9580, 10125, 10668, 11207, 
        11743, 12275, 12803, 13327, 13848, 14364, 14876, 15383, 15886, 16383, 
        16876, 17364, 17846, 18323, 18794, 19260, 19720, 20173, 20621, 21062, 
        21497, 21926, 22347, 22762, 23170, 23571, 23964, 24351, 24730, 25101, 
        25465, 25821, 26169, 26509, 26841, 27165, 27481, 27788, 28087, 28377, 
        28659, 28932, 29196, 29451, 29697, 29935, 30163, 30381, 30591, 30791, 
        30982, 31164, 31336, 31498, 31651, 31794, 31928, 32051, 32165, 32270, 
        32364, 32449, 32523, 32588, 32643, 32688, 32723, 32748, 32763, 32768};


    private int iSin(int d, int multi) {
        // range checking: if ((d < 0) || (d > 359)) { return 0; }
        int i;
        if (d < 180) {
            i = (d<90? sincos_lut[d] : sincos_lut[90-(d-90)]);
        } else {
            i = (d<270? -sincos_lut[d-180] : -sincos_lut[90-(d-270)]);
        }
        return (i*multi) >> 15;
    }

    private int iCos(int d, int multi) {
        // range checking: if ((d < 0) || (d > 359)) {return 0; }
        int i;
        if (d < 180) {
            i = (d<90? sincos_lut[90-d] : -sincos_lut[d-90]);
        } else {
            i = (d<270? -sincos_lut[270-d] : sincos_lut[d-270]);
        }
        return (i*multi) >> 15;
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
          x1 = xOffs+iSin(j, 105);
          y1 = yOffs+iCos(j, 105);
          x2 = xOffs+iSin(j, 115);
          y2 = yOffs+iCos(j, 115);
          g.drawLine(x1, y1, x2, y2);
        }
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
