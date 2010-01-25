/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import GUI.CatcherMain;
import GUI.IMapView;
import System.ICacheProvider;
import System.IMapProvider;
import System.IPositionProvider;
import System.Position;
import System.IImageLoader;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MapView extends CatcherCanvas implements IMapView {

    private Position center;

    private int zoom;

    private String msg = "Greetings Catcher";

    private IMapProvider mapProvider = null;
    //private IImageLoader imageLoader = null;

    /**
     * constructor
     */
    public MapView(CatcherMain main, ViewResources viewResources, ICacheProvider cacheProvider, IPositionProvider positionProvider,
            IMapProvider mapProvider, IImageLoader imageLoader) {

        super(main);
        
        setFullScreenMode(true);
        this.viewResources = viewResources;
        
        //this.imageLoader = imageLoader;
        this.mapProvider = mapProvider;
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight()-HEIGHT_STATUSBAR);

        paintStatusBar(g);
        paintSelectedCache(g);

        // this is a temporary Position var until location is implemented
        Position pos = new Position(47.1, 14.8);
        g.drawImage((Image)mapProvider.getMap(pos, getWidth(), 200, zoom), 0, 30,
                Graphics.TOP|Graphics.LEFT);

        g.setColor(0, 0, 0);
        g.drawString("MapView",0,40,Graphics.TOP|Graphics.LEFT);
        g.drawString(this.msg,0,60,Graphics.TOP|Graphics.LEFT);
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {
        if (globalKeyPressed(keyCode)) { return; }
        switch(getGameAction(keyCode)) {
            case GAME_A: msg="game_a";break;
            case GAME_B: msg="game_b";break;
            case GAME_C: msg="game_c";break;
            case GAME_D: msg="game_d";break;
            case UP: msg="game_up";break;
            case DOWN: msg="game_down";break;
            case LEFT:
                msg="game_left";
                viewNavigator.ShowPrevious();
                break;
            case RIGHT:
                msg="game_right";
                viewNavigator.ShowNext();
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
                    default: msg = "Unknown ("+String.valueOf(keyCode)
                            +") KeyName: "+getKeyName(keyCode);
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

    public void activate() {
        
    }

    public void deactivate() {

    }

}
