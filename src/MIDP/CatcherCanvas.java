/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import GUI.IViewNavigator;
import System.Cache;
import System.DateUtils;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

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

    // Icon indexes
    private static final int IC_COMPASS_OFFS = 0; // 0--7: 8 directions,
                                                  // clockwise from north
    private static final int IC_GPS_OK = 8;
    private static final int IC_GPS_LIMITED = 9;
    private static final int IC_GPS_OFF = 10;
    private static final int IC_CROSSHAIR1 = 16;
    private static final int IC_CROSSHAIR2 = 17;
    private static final int IC_CACHES_OFFS = 32;

    protected ViewResources viewResources;
    protected IViewNavigator viewNavigator;
    protected Menu menu;

    // Careful when changing globalItems! menuAction() assumes a certain order!
    protected String[] globalItems = {"Settings", "Log cache", "Exit"};
    private Modal modal=null;
    protected Font sysFont;

    public CatcherCanvas(IViewNavigator viewNavigator) {
        this.viewNavigator = viewNavigator;
        menu = new Menu(globalItems);
        sysFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    }

    protected boolean screenOrientation() {
        return getHeight() > getWidth();
    }

    /**
     * View paint handler
     * @param g Graphics
     */
    abstract void paintView(Graphics g);

    protected final void paint(Graphics g) {
        g.setFont(sysFont);
        if (modal != null) {
            modal.paint(g);
        } else {
            paintView(g);
            paintStatusBar(g);
        }
    }

    /*
     * Draw statusbar with a stored graphic later, and add date and status icons
     * for gps, battery, net and cell status.
     */
    protected void paintStatusBar(Graphics g) {
        if (screenOrientation() == PORTRAIT) {
            int x = 0;
            int y = getHeight()-HEIGHT_STATUSBAR;
            int width = getWidth();
            int height = getHeight();
            g.setClip(x, y, width, height);

            g.setColor(COLOR_STATUSBAR_BG);
            g.fillRect(x, y, width, height);
            g.setColor(COLOR_OUTLINE);
            g.drawLine(0, y, width, y);
            g.drawLine(30, y, 30, height);
            g.drawLine(getWidth()-30, y, width-30, height);
            g.setColor(COLOR_TEXT);
            g.drawString("VIEW", 15, height, Graphics.HCENTER | Graphics.BOTTOM);
            g.drawString("MENU", width-15, height, Graphics.HCENTER | Graphics.BOTTOM);
            g.drawString(DateUtils.getDateTimeStr(), width>>1, height,
                    Graphics.HCENTER | Graphics.BOTTOM);
        } else {
            // fixme: Put some effort here later.
            g.setColor(COLOR_TEXT);
            g.drawString("Landscape view not implemented", 0, 40, Graphics.TOP|Graphics.LEFT);
        }
    }

    protected void paintSelectedCache(Graphics g) {
        int x = 0;
        int y = 0;
        int width = getWidth();
        int height = HEIGHT_CACHELISTITEM;

        g.setClip(x, y, width, height);

        int ht = HEIGHT_CACHELISTITEM;

        g.setColor(COLOR_CACHELIST_BG);
        g.fillRect(x, y, width, height);

        // fake data until cache store is in place
        String cacheName = "Under the bridge";
        int cacheType = Cache.CT_REGULAR;


        // GC.com currently has 9 levels, 1 to 5 in .5 increments. We represent
        // them as 0-8 internally. Others might have different scales.
        int terrain = 3;
        int difficulty = 2;
        int[] lastLogs = {0,2,0,1};
        int heading = 3; // clockwise degree from north / 45;

        g.drawImage(viewResources.getIcon(IC_CACHES_OFFS+cacheType), x, y,
                Graphics.TOP|Graphics.LEFT);
        g.drawImage(viewResources.getIcon(IC_COMPASS_OFFS+heading), width, y,
                Graphics.TOP|Graphics.RIGHT);

        // Draws a set of squares to indicate cache health based on latest logs
        for (int i=0;i<lastLogs.length;i++) {
            g.setColor(COLOR_LOG[lastLogs[i]]);
            g.fillRect(x+16, y+(i<<2), 4, 4);
        }

        g.setColor(COLOR_TEXT);
        g.drawString(cacheName, x+20, y,
                Graphics.LEFT | Graphics.TOP);
    }

    /**
     * Called by menuAction when a view menu item has been selected
     * @param menuItem: index in view menu array
     */
    abstract void menuActionView(int menuItem);

    protected final void menuAction(int menuItem) {
        if (menuItem >= globalItems.length) {
            menuActionView(menuItem-globalItems.length);
        }
        switch (menuItem) {
            case 0:
                // Settings
                System.out.println("menu Settings");
                break;
            case 1:
                // Log cache
                System.out.println("menu Log cache");
                break;
            case 2:
                // Exit
                System.out.println("menu Exit");
                break;
        }
    }

    /**
     * View keyPressed handler
     * @param keyCode
     */
    abstract void keyPressedView(int keyCode);

    /*
     * Handle events common to all views.
     * Returns false if no action was taken.
     */
    protected final void keyPressed(int keyCode) {
        if (modal != null) {
            if (modal.keyPressed(keyCode, this)) {
                if ((modal == menu) && (menu.execute())) {
                    menuAction(menu.getAction());
                }
                modal = modal.parentModal;
            }
            this.repaint();
            System.out.println("repaint");
            return;
        }
        switch (keyCode) {
            case -7:
            case KEY_POUND:
                modal = menu;
                this.repaint();
                return;
            case -6:
            case KEY_STAR:
            case KEY_NUM0: // Convenience key when developing
                viewNavigator.ShowNext();
                return;
        }
        keyPressedView(keyCode); // Let the current view handle this keypress
    }

    protected  void keyReleased(int keyCode) {
    }

    protected  void keyRepeated(int keyCode) {
    }

    protected  void pointerDragged(int x, int y) {
    }

    protected  void pointerPressed(int x, int y) {
    }

    protected  void pointerReleased(int x, int y) {
    }
}
