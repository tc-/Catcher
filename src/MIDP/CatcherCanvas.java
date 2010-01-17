/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package MIDP;

import System.Cache;
import System.DateUtils;
import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;



public abstract class CatcherCanvas extends Canvas {

    // Screen orientation
    protected static final boolean PORTRAIT = true;
    protected static final boolean LANDSCAPE = false;

    protected static final int HEIGHT_STATUSBAR = 16;
    protected static final int HEIGHT_CACHELISTITEM = 16;

    protected static final int COLOR_OUTLINE = 0x000000;
    protected static final int COLOR_BACKGROUND = 0xffffff;
    protected static final int COLOR_TEXT = 0x000000;
    protected static final int COLOR_STATUSBAR_BG = 0xddddff;
    protected static final int COLOR_CACHELIST_BG = 0xddddff;
    protected static final int COLOR_COMPASS_BG = 0xffffff;

    protected static final int[] COLOR_LOG = {
        0X00FF00,// LOG_FOUND
        0XFFFF77,// LOG_DNF     Did Not find
        0x7777ff,// LOG_NOTE
        0x00aa00,// LOG_PUBLISHED
        0xffff00,// LOG_MAINTENANCE
        0x000000,// LOG_ARCHIVED
        0xff0000 // LOG_DISABLED
    };

    protected boolean screenOrientation() {
        return getHeight() > getWidth();
    }

    /*
     * Draw statusbar with a stored graphic later, and add date and status icons
     * for gps, battery, net and cell status.
     */
    protected void paintStatusBar(Graphics g) {
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
            g.drawString(DateUtils.getDateTimeStr(), x2>>1, y2,
                    Graphics.HCENTER | Graphics.BOTTOM);
        } else {
            // Put some effort here later.
        }
    }

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

    protected void paintSelectedCache(Graphics g) {
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
        int cacheType = Cache.CT_REGULAR;


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


}
