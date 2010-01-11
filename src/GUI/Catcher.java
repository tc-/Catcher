/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import System.IEventHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;


public class Catcher extends MIDlet {
    public void startApp() {

        IEventHandler nextHandler = new IEventHandler()
        {
            public void executeHandler(Object sender) {
                ShowNext((Canvas)sender);
            }
        };

        IEventHandler prevHandler = new IEventHandler()
        {
            public void executeHandler(Object sender) {
                ShowNext((Canvas)sender);
            }
        };

        Display.getDisplay(this).setCurrent(new MapView(nextHandler, prevHandler));
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void ShowNext(Canvas current)
    {

    }
}
