/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 * Resources for views.
 */
public class ViewResources {
    private Image icons16x16;

    public ViewResources() {
        loadImages();
    }

    public void loadImages() {
        try {
            icons16x16 = Image.createImage("/icons16x16.png");
        }
        catch(IOException e) {
            throw new RuntimeException("Load resource: "+e);
        }
    }

    public Image getIcon(int index) {
        // icons16x16 is expected to be 128px wide / 8 icons wide
        int x = (index & 0x07) << 4; // index % 8 * 16
        int y = (index & 0xf8) << 1; // index / 8 * 16
        Image im = Image.createImage(icons16x16, x, y, 16, 16,
                Sprite.TRANS_NONE);
        return im;
    }

}