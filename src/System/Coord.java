/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 * 
 * References:
 * http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
 */
package System;

import GUI.IMapView;

/*
 * Holds a coordinate and translates to/from screen coords
 */
public class Coord {
    private double lat;
    private double lon;
    private IMapView map;

    public void Coord(IMapView map, double lat, double lon) {
        this.map = map;
        this.lat = lat;
        this.lon = lon;
    }

    private double tileX(double lon, int z) {
        return (int)((this.lon + 180) / 360 * (1 << z));
    }

    public int tileY(double lat, int z) {
        lat = Math.toRadians(lat*Math.PI/180);
        return (int)(1 - MathUtil.log(Math.tan(Math.toRadians(lat))+1 /
                Math.cos(Math.toRadians(lat)) / Math.PI) / 2 * (1 << z));
    }

    public int mapX() {
        /*
         * Either we need to get the tile below this coords screen pos,
         * or we need to get the coord of screen top-left corner
         */
        return 0;
    }

    public int mapY() {
        return 0;
    }

    public double tilex2long(int x, int z) {
        return x / (1 << z) * 360 - 180;
    }

    public double tiley2lat(int y, int z) {
        double n = Math.PI - 2 * Math.PI * y / (1 << z);
        return 180 / Math.PI * MathUtil.atan(0.5 * (MathUtil.exp(n) - MathUtil.exp(-n)));
    }
}
