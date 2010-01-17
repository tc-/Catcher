/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import GUI.IMapView;
import GUI.IViewNavigator;
import System.Position;
import java.util.Calendar;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
//import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.game.Sprite;
import java.io.IOException;


public class MapView extends Canvas implements IMapView {

    private String msg = "Greetings Catcher";

    private IViewNavigator viewNavigator;

    private Position center;

    private int zoom;

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
     * constructor
     */
    public MapView(IViewNavigator viewNavigator) {
        setFullScreenMode(true);
        this.viewNavigator = viewNavigator;
    } 
    
    private static final int COLOR_OUTLINE = 0x000000;
    private static final int COLOR_BACKGROUND = 0xffffff;
    private static final int COLOR_TEXT = 0x000000;
    private static final int COLOR_STATUSBAR_BG = 0xddddff;
    private static final int COLOR_CACHELIST_BG = 0xddddff;
    private static final int COLOR_COMPASS_BG = 0xffffff;

    private static final int[] COLOR_LOG = {
        0X00FF00,// LOG_FOUND
        0XFFFF77,// LOG_DNF     Did Not find
        0x7777ff,// LOG_NOTE
        0x00aa00,// LOG_PUBLISHED
        0xffff00,// LOG_MAINTENANCE
        0x000000,// LOG_ARCHIVED
        0xff0000 // LOG_DISABLED
    };

    private static final int LOG_FOUND = 0;
    private static final int LOG_DNF = 1; // Did Not find
    private static final int LOG_NOTE = 2;
    private static final int LOG_PUBLISHED = 3;
    private static final int LOG_MAINTENANCE = 4;
    private static final int LOG_ARCHIVED = 5;
    private static final int LOG_DISABLED = 6;

    private static final int HEIGHT_STATUSBAR = 16;
    private static final int HEIGHT_CACHELISTITEM = 16;

    // Cache types (these might change. These are the currently supported on
    // geocaching.com. opencaching has additional types, and more are added.)
    private static final int CT_REGULAR=0;
    private static final int CT_MYSTERY=1;// aka unknown
    private static final int CT_UNKNOWN=1;
    private static final int CT_MULTI=2;
    private static final int CT_LETTERBOX=3;
    private static final int CT_VIRTUAL=4;
    private static final int CT_WHEREIGO=5;
    private static final int CT_EVENT=6;
    private static final int CT_WEBCAM=7;
    private static final int CT_EARTH=8;
    private static final int CT_CITO=9; // aka "Cache In Trash Out"
    private static final int CT_MEGAEVENT=10;


    // Screen orientation
    private static final boolean PORTRAIT = true;
    private static final boolean LANDSCAPE = false;

    private Image icons16x16;

    // fixme: run this on init and remove this lousy comment
    private void loadImages() {
       try {
           icons16x16 = Image.createImage("/icons16x16.png");
       }
       catch(IOException e) {
           throw new RuntimeException("Load resource: "+e);
       }
    }

    // Icon indexes
    private static final int IC_COMPASS_OFFS = 0; // 0--7: 8 directions,
                                                  // clockwise from north
    private static final int IC_GPS_OK = 8;
    private static final int IC_GPS_LIMITED = 9;
    private static final int IC_GPS_OFF = 10;
    private static final int IC_CROSSHAIR1 = 16;
    private static final int IC_CROSSHAIR2 = 17;
    private static final int IC_CACHES_OFFS = 32;

    private Image getIcon(int index) {
        // icons16x16 is expected to be 128px wide / 8 icons wide
        int x = (index & 0x07) << 4; // index % 8 * 16
        int y = (index & 0xf8) << 1; // index / 8 * 16
        Image im = Image.createImage(icons16x16, x, y, 16, 16,
                Sprite.TRANS_NONE);
        return im;
    }

    private boolean screenOrientation() {
        return getHeight() > getWidth();
    }

    // Extend this to (optionally) take date as param.
    private String getDateStr() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH)+1; // January = 0
        int d = cal.get(Calendar.DAY_OF_MONTH);
        String str = y+"-"+(m<10?"0":"")+m+"-"+(d<10?"0":"")+d;
        return str;
    }

    // Extend this to (optionally) take date as param.
    private String getTimeStr() {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY); // 24-hour hour
        int m = cal.get(Calendar.MINUTE);
        String str = (h<10?"0":"")+h+":"+(m<10?"0":"")+m;
        return str;
    }

    // Extend this to (optionally) take date as param.
    private String getDateTimeStr() {
        return getDateStr()+" / "+getTimeStr();
    }

    /*
     * Draw statusbar with a stored graphic later, and add date and status icons
     * for gps, battery, net and cell status.
     */
    private void paintStatusBar(Graphics g) {
        if (screenOrientation() == PORTRAIT) {
            int x1 = 0;
            int y1 = getHeight()-HEIGHT_STATUSBAR;
            int x2 = getWidth();
            int y2 = getHeight();

            g.setColor(COLOR_STATUSBAR_BG);
            g.fillRect(x1, y1, x2, y2);
            g.setColor(COLOR_OUTLINE);
            g.drawLine(0, y1, x2, y1);
            g.drawLine(30, y1, 30, y2);
            g.drawLine(getWidth()-30, y1, x2-30, y2);
            g.setColor(COLOR_TEXT);
            g.drawString("VIEW", 15, y2, Graphics.HCENTER | Graphics.BOTTOM);
            g.drawString("MENU", x2-15, y2, Graphics.HCENTER | Graphics.BOTTOM);
            g.drawString(getDateTimeStr(), x2>>1, y2,
                    Graphics.HCENTER | Graphics.BOTTOM);
        } else {
            // Put some effort here later.
        }
    }

    private void paintSelectedCache(Graphics g) {
        int x1 = 0;
        int y1 = 0;
        int x2 = getWidth();
        int y2 = HEIGHT_CACHELISTITEM;
        int ht = HEIGHT_CACHELISTITEM;

        g.setColor(COLOR_CACHELIST_BG);
        g.fillRect(x1, y1, x2, y2);

        // Tiny compass
        g.setColor(COLOR_COMPASS_BG);
        g.fillArc(x2-ht, y1, ht-1, ht-1, 0, 360); // off by one in wth/ht
        g.setColor(COLOR_OUTLINE);
        g.drawArc(x2-ht, y1, ht-1, ht-1, 0, 360);

        // fake data until cache store is in place
        String cacheName = "Under the bridge";
        int cacheType = CT_REGULAR;


        // GC.com currently has 9 levels, 1 to 5 in .5 increments. We represent
        // them as 0-8 internally. Others might have different scales.
        int terrain = 3;
        int difficulty = 2;
        int[] lastLogs = {0,2,0,1};

        g.drawImage(getIcon(IC_CACHES_OFFS+cacheType), x1, y1, 1);

        // Draws a set of squares to indicate cache health based on latest logs
        for (int i=0;i<lastLogs.length;i++) {
            g.setColor(COLOR_LOG[lastLogs[i]]);
            g.fillRect(x1+16, y1+(i<<2), 4, 4);
        }

        g.setColor(COLOR_TEXT);
        g.drawString(cacheName, x1+20, y1,
                Graphics.LEFT | Graphics.TOP);
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight()-HEIGHT_STATUSBAR);

        paintStatusBar(g);
        paintSelectedCache(g);


        g.setColor(0, 0, 0);
        g.drawString("MapView",0,40,Graphics.TOP|Graphics.LEFT);
        g.drawString(this.msg,0,60,Graphics.TOP|Graphics.LEFT);
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
