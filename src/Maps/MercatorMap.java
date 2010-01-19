/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package Maps;

import System.IMapProvider;
import System.MathUtil;
import System.Position;

/**
 * Spherical mercator (Google style) tiled map with 256x256px tiles.
 *
 * References:
 * http://wiki.openstreetmap.org/wiki/Slippy_map_tilenames
 */
public class MercatorMap implements IMapProvider {

    private double tileX(double lon, int z) {
        return (int)((lon + 180) / 360 * (1 << z));
    }

    public int tileY(double lat, int z) {
        lat = Math.toRadians(lat*Math.PI/180);
        return (int)(1 - MathUtil.log(Math.tan(Math.toRadians(lat))+1 /
                Math.cos(Math.toRadians(lat)) / Math.PI) / 2 * (1 << z));
    }

    public double tilex2long(int x, int z) {
        return x / (1 << z) * 360 - 180;
    }

    public double tiley2lat(int y, int z) {
        double n = Math.PI - 2 * Math.PI * y / (1 << z);
        return 180 / Math.PI * MathUtil.atan(0.5 * (MathUtil.exp(n) -
                MathUtil.exp(-n)));
    }

    public Position pixelXYtoPosition(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getMap(Position center, int width, int height) {
 /*  calculate which tiles are visible, preferrably ordered from center
  and out in order of visibility

  Object t1 = imgldr.loadImage(tile1);
        Object t2 = imgldr.loadImage(tile1);
        Object img = imgldr.createImage(width, height);
        imgldr.drawImage(img, t1, x1, y1);
        imgldr.drawImage(img, t2, x2, y2);

        return img;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int lonToX(double lon) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int latToY(double lat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
