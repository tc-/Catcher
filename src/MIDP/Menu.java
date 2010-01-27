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
    String[] globalItems;
    String[] viewItems={};

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

    public void paint(Graphics g) {
        if (!opened) return;
        System.out.println("Menu.paint");


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
        int height = lineHeight*nofItems;
        int x = 20;
        int y = 20;
        g.setColor(0xaaaaff);
        g.fillRect(x, y, width, height);

        // Draw selected items background
        g.setColor(0xffffff);
        g.fillRect(x, y+lineHeight*index, width, lineHeight);

        // Draw each items text
        g.setColor(0xff0000);
        for (int i=0;i<nofItems;i++) {
            if (i < viewItems.length) {
                g.drawString(viewItems[i], 
                        x, y+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            } else {
                g.drawString(globalItems[i+viewItems.length], 
                        x, y+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            }
        }
    }

    // True if menu is displaying
    public boolean opened() {
        System.out.println("Menu.opened = "+String.valueOf(opened));
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

    public void select() {
        close();
        String sel = "";
        if (index < viewItems.length) {
            sel = viewItems[index];
        } else {
            sel = globalItems[index+viewItems.length];
        }
        System.out.println("Menu.select: "+sel);
    }
}
