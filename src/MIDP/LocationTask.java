/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;


public class LocationTask implements Runnable {

    private final LocationProvider provider;
    private final LocationListener listener;

    public LocationTask(LocationProvider provider, LocationListener listener) {
        this.provider = provider;
        this.listener = listener;
    }

    public void run() {
        provider.setLocationListener(listener, -1, -1, -1);
    }

}
