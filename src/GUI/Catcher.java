/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * @author tc
 */
public class Catcher extends MIDlet {
    public void startApp() {
        Display.getDisplay(this).setCurrent(new MapView());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
