/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;

public class Menu implements Modal {

    private int index;
    private int nofItems;
    private String[] globalItems;
    private String[] viewItems={};
    private boolean execute = false;

    Menu(String[] globalItems) {
        this.globalItems = globalItems;
        nofItems = globalItems.length;
        index = 0;
    }

    public boolean execute() {
        return execute;
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
        Font font = g.getFont();
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
        width += 8;
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
                        x+4, y+2+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            } else {
                g.drawString(globalItems[i-viewItems.length],
                        x+4, y+2+lineHeight*i, Graphics.TOP|Graphics.LEFT);
            }
        }
    }

    // Move to previous item in list
    public void up() {
        index = (index<=0? nofItems-1 : --index);
    }

    // Move to next item in menu
    public void down() {
        index = (index>=nofItems-1? 0: ++index);
    }

    public int getAction() {
        String sel = "";
        if (index < viewItems.length) {
            sel = viewItems[index];
        } else {
            sel = globalItems[index-viewItems.length];
        }
        System.out.println("Menu.select: "+sel);

        // We need to put global index first as it's not aware of viewItems
        int ret;
        if (index < viewItems.length) {
            ret = index+globalItems.length;
        } else {
            ret = index-viewItems.length;
        }

        reset();
        return ret;
    }

    public boolean keyPressed(int keyCode, Canvas canvas) {
        switch (keyCode) {
            /* fixme: -7 is the right soft key on nokia midp2 devices, other
             * phones may have different values
             * Meanwhile the menu can be accessed with the '#' key */
            case -7:
            case Canvas.KEY_POUND:
                // Escape menu
                index = 0; // Reset index
                return true;
        }
        switch (canvas.getGameAction(keyCode)) {
            case Canvas.UP:
                // Move one item up in list
                up();
                break;
            case Canvas.DOWN:
                // Move one item down in list
                down();
                break;
            case Canvas.FIRE:
                // Execute selected items action
                execute = true;
                return true;
        }
        return false;
    }

    private void reset() {
        execute = false;
        index = 0;
    }

}
