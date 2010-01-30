/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 *
 * Interface for modal gui elements, such as menus and dialogs
 */
public abstract interface Modal {
    Modal parentModal = null;

    /**
     * A paint handler for this object
     */
    abstract void paint(Graphics g);

    /**
     * A keyPressed handler for this object
     * @return true if modal closed
     */
    abstract boolean keyPressed(int keyCode, Canvas canvas);
}
