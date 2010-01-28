/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class Menu {

    private boolean opened=false;
    private int index;
    private int nofItems;
    private String[] globalItems;
    private String[] viewItems={};

    // Takes an array of strings
    Menu(String[] globalItems) {
        this.globalItems = globalItems;
        nofItems = globalItems.length;
        index = 0;
    }

    public void viewItems(String[] viewItems) {
        this.viewItems = viewItems;
        nofItems = globalItems.length+viewItems.length;
        index = 0;
    }

    public void clearViewItems() {
        String[] empty = {};
        viewItems = empty;
        nofItems = globalItems.length;
        index = 0;
    }

    public void paint(Graphics g) {
        if (!opened) return;

        Font font = Font.getDefaultFont();
        int lineHeight = font.getHeight();
        int w, width=0;
        for (int i=0;i<viewItems.length;i++) {
            w = font.stringWidth(viewItems[i]);
            width = (w>width? w:width);
        }
        for (int i=0;i<globalItems.length;i++) {
            w = font.stringWidth(globalItems[i]);
            width = (w>width? w:width);
        }
        width += 4;
        int height = lineHeight*nofItems+4;
        int x = 20;
        int y = 20;
        g.setClip(x, y, width, height);
        g.setColor(CatcherCanvas.COLOR_STATUSBAR_BG);
        g.fillRect(x, y, width, height);

        // Draw selected items background
        g.setColor(CatcherCanvas.COLOR_BACKGROUND);
        g.fillRect(x+2, y+2+lineHeight*index, width-4, lineHeight);
        g.setColor(CatcherCanvas.COLOR_OUTLINE);
        g.drawRect(x, y, width-1, height-1);
        
        // Draw each items text
        g.setColor(CatcherCanvas.COLOR_TEXT);
        for (int i=0;i<nofItems;i++) {
            if (i < viewItems.length) {
                g.drawString(viewItems[i], 
                        x+2, y+2+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            } else {
                g.drawString(globalItems[i-viewItems.length],
                        x+2, y+2+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            }
        }
    }

    // True if menu is displaying
    public boolean opened() {
        return opened;
    }

    // Open menu
    public void open() {
        System.out.println("Menu.open");
        opened = true;
        index = 0;
    }

    // Close menu
    public void close() {
        System.out.println("Menu.close");
        opened = false;
    }

    // Move to previous item in list
    public void up() {
        System.out.println("Menu.up");
        index = (index<=0? nofItems-1 : --index);
    }

    // Move to next item in menu
    public void down() {
        System.out.println("Menu.down");
        index = (index>=nofItems-1? 0: ++index);
    }

    public int select() {
        close();
        String sel = "";
        if (index < viewItems.length) {
            sel = viewItems[index];
        } else {
            sel = globalItems[index-viewItems.length];
        }
        System.out.println("Menu.select: "+sel);

        return index-viewItems.length; // viewItems are negative
    }
}
