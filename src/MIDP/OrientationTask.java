/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import javax.microedition.location.LocationException;
import javax.microedition.location.Orientation;


public class OrientationTask implements Runnable {

    private int interval;
    private PostionProvider provider;
    private boolean aborted;

    public OrientationTask(PostionProvider provider) {
        this.provider = provider;
        this.interval = 2000;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public PostionProvider getProvider() {
        return provider;
    }

    public void setProvider(PostionProvider provider) {
        this.provider = provider;
    }

    public void run() {

        aborted = false;

        while(!aborted) {
            try {
                Thread.sleep(interval);
                Orientation o = Orientation.getOrientation();
                provider.setOrientation(o);
            } catch (Exception ex) {
                ex.printStackTrace();
                aborted = true;
            }

        }
    }

    public void abort() {
        aborted = true;
    }

}
