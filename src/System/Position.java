/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package System;

public class Position {
    // Mean radius of earth in meters
    private final static double R_EARTH_METERS = 6371000.0;
    private final static double RADIANSPERDEGREE = 0.017453293;

    private double lat;
    private double lon;

    public Position(double latitude, double longitude) {
        this.lat = latitude;
        this.lon = longitude;
    }

    public void setLatLon(double latitude, double longitude) {
        this.lat = latitude;
        this.lon = longitude;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    /*
     * Estimated distance between this coord and another coord in km.
     * Reference:
     * http://openmap.bbn.com/mailArchives/openmap-users/2002-03/0665.html
     */
    public int distanceTo(Position p) {
        // all values are of type "double"; lat1,lon1,lat2 and lon2 are the
        // coordinates we are using in the calculation, in RADIANS.
        // You will have to convert from degrees to radians using the
        // conversion factor, RADIANSPERDEGREE
        double lat1 = getLat() * RADIANSPERDEGREE;
        double lon1 = getLon() * RADIANSPERDEGREE;
        double lat2 = p.getLat() * RADIANSPERDEGREE;
        double lon2 = p.getLon() * RADIANSPERDEGREE;

        double dLon = lon2 - lon1; // difference in longitude
        double dLat = lat2 - lat1; // difference in latitude

        double sinLat = Math.sin(dLat/2.0);
        double sinLon = Math.sin(dLon/2.0);
        double a = (sinLat*sinLat)+(Math.cos(lat1) * Math.cos(lat2) *
                sinLon * sinLon);

        double c = 2.0 * MathUtil.asin(Math.sqrt(a));

        return (int)(R_EARTH_METERS * c); // Final distance is in kilometers.
    }
}
