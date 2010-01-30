/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.Direction;
import System.PositionListener;
import System.IPositionProvider;
import System.Position;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;
import javax.microedition.location.Orientation;
import javax.microedition.location.QualifiedCoordinates;


public class PostionProvider implements IPositionProvider, LocationListener {
    
    private PositionListener listener;
    private boolean enabled;
    private LocationProvider provider;
    private final boolean orientationSupport;
    private Position lastPosition;
    private int lastPositionAccuracy;
    private Direction lastDirection;
    private Thread orientationThread;
    private Thread locationThread;

    public PostionProvider() {
        orientationSupport = hasOrientationSupport();
        lastPosition = new Position(57.722, 11.987);
        lastPositionAccuracy = 3;
        lastDirection = new Direction(0.0);
    }

    public Position getLastPosition() {
        System.out.println("getLastPosition");
        return lastPosition;
    }

    public int getLastAccuracy() {
        return lastPositionAccuracy;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public int getLocationStatus() {
        return IPositionProvider.LOC_STAT_ACTIVE;
    }

    public int getDirectionStatus() {
        if (!orientationSupport) return IPositionProvider.DIR_STAT_NOT_PRESSENT;
        else return IPositionProvider.DIR_STAT_ACTIVE;
    }

    public void setPositionListener(PositionListener listener) {
        this.listener= listener;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) return;

        if (enabled) enable();
        else disable();
    }

    public boolean getEnabled() {
        return enabled;
    }

    private void enable() {

        if (enabled) return;

        // Try to get a provider
        if (provider == null) {
            Criteria criteria = new Criteria();
            criteria.setVerticalAccuracy(25);
            criteria.setHorizontalAccuracy(25);
            criteria.setCostAllowed(true);
            criteria.setSpeedAndCourseRequired(true);
            criteria.setAltitudeRequired(false);
            criteria.setAddressInfoRequired(false);
            criteria.setPreferredResponseTime(Criteria.NO_REQUIREMENT);
            criteria.setPreferredPowerConsumption(Criteria.NO_REQUIREMENT);

            try {
                provider = LocationProvider.getInstance(criteria);
            } catch (LocationException ex) {
                ex.printStackTrace();
            }
        }

        // If we got a provider, start the location thread
        if (provider != null) {
            locationThread = new Thread(new LocationTask(provider, this));
            locationThread.start();
        }

        // Start the orientation thread if orientation is supported
        if (orientationSupport) {

            if (orientationThread != null) {
                orientationThread.interrupt();
            }
            orientationThread = new Thread(new OrientationTask(this));
            orientationThread.start();
            
        }

        enabled = true;
    }

    private void disable() {

        if (!enabled) return;

        if (locationThread != null) {
            locationThread.interrupt();
            locationThread = null;
        }

        if (orientationThread != null) {
            orientationThread.interrupt();
            orientationThread = null;
        }

        if (provider != null) {
            provider.setLocationListener(null, -1, -1, -1);
        }
        
        enabled = false;
    }

    private boolean hasOrientationSupport() {
        try
        {
            Orientation.getOrientation();
            return true;
        }
        catch (LocationException e)
        {
            return false;
        }
    }

    public void locationUpdated(LocationProvider locationProvider, Location location) {
        if (!location.isValid()) return;

        QualifiedCoordinates coord = location.getQualifiedCoordinates();
        lastPosition = new Position(coord.getLatitude(), coord.getLongitude());
        lastPositionAccuracy = (int)Math.max(coord.getHorizontalAccuracy(), coord.getVerticalAccuracy()); // TODO examine a better way to do it

        if (listener != null) listener.PositionUpdated(this, lastPosition, lastPositionAccuracy);
    }

    public void providerStateChanged(LocationProvider locationProvider, int state) {
        if (listener != null) listener.PositionStatusChanged(this, IPositionProvider.LOC_STAT_ACTIVE);
    }

    public void setOrientation(Orientation o) {
        if (o == null) return;
        //TODO convert between geographic and true north orientations, do this in Direction?
        lastDirection = new Direction(o.getCompassAzimuth());
        if (listener != null) listener.DirectionUpdated(this, lastDirection);
    }

}
