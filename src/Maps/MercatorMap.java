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
 *
 * Known bugs:
 * Doesn't handle wrapping around longitude -180/179
 */
public class MercatorMap implements IMapProvider {

    // We could set this to 0 and see the entire world, but what use is it?
    private static int ZOOM_MIN = 6;
    // Depending on map source, this value varies. 14 would be safe for most
    // maps.
    private static final int ZOOM_MAX = 20;

    private int zoom;

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = (zoom<=ZOOM_MAX ? (zoom>=ZOOM_MIN?zoom:ZOOM_MIN):ZOOM_MAX);
    }

    public boolean zoomIn() {
        if (zoom <= ZOOM_MAX) {
            ++zoom;
            return true;
        }
        return false;
    }

    public boolean zoomOut() {
        if (zoom >= ZOOM_MIN) {
            --zoom;
            return true;
        } else {
            return false;
        }
    }

    public Position XYtoPosition(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * fixme: Prioritize tile loading order, load from center
     * 
     * This could be done by calculating each tile's center position and
     * ordering by distance to map center. Optimize by offsetting map center
     * position by a half tile instead.
     */
    public Object getMap(Position center, int width, int height) {
        // Get center tile position and calculate the rest from there.
        int[] tileX = tileX(center.getLon());
        int[] tileY = tileY(center.getLat());

        int tx1 = tileX[0]-tileX[1];
        int ty1 = tileY[0]-tileY[1];

        /*
         * Find tile arrangement
         *
         * case1 tx1=57 width=240
         * 57+255 >> 3 = 312 >> 3 = 1
         * 240-57 >> 3 = 183 >> 3 = 0
         * case2 tx1=-3
         * -3+255 >> 3 = 252 >> 3 = 0
         * 240-3 >> 3 = 237 >> 3 = 0
         * case3 tx1=0 width=256
         * 0+255 >> 3 = 0
         * 255-0 >> 3 = 0
         * case4 tx1=1 width=256
         * 1+255 >> 3 = 1
         * 255-1 >> 3 = 0
         * case5 tx1=-1 width=256
         * -1+255 >> 3 = 0
         * 255--1 >> 3 = 1
        */
        int tilesLeft = (tx1+255) >> 3;
        int tilesAbove = (ty1+255) >> 3;

        int tilesRight = (width-1-tx1) >> 3;
        int tilesBelow = (height-1-ty1) >> 3;

        int nofTilesX = tilesLeft+tilesRight+1;
        int nofTilesY = tilesAbove+tilesBelow+1;

        int[] topLeftTile = {tileX[0]-tilesLeft, tileY[0]-tilesAbove};

        // offset of topleftmost tile.
        int firstX = tx1-(tilesLeft<<3);
        int firstY = ty1-(tilesAbove<<3);
        int firstTileX = tileX[0]-tilesLeft;
        int firstTileY = tileY[0]-tilesAbove;

        for (int y=0; y<nofTilesY; y++) {
            for (int x=0; x<nofTilesX; x++) {
                Object imTile = getTile(firstTileX+x, firstTileY+y, getZoom());
                paintTile(imTile, firstX+(x<<3), firstY+(y<<3));

            }
        }
        return null;
    }

    private void paintTile(Object tileImage, int xOffs, int yOffs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Object getTile(int tileX, int tileY, int tileZ) {
        /*
         * we need to implement a IImageLoader for map tiles!
         *
        Object t1 = imgldr.loadImage(tile1);
        Object t2 = imgldr.loadImage(tile1);
        Object img = imgldr.createImage(width, height);
        imgldr.drawImage(img, t1, x1, y1);
        imgldr.drawImage(img, t2, x2, y2);

        return img;
        */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
     * Returns { tile, offset in tile }
     */
    private int[] tileX(double lon) {
        int tileX = (int)((lon + 180) / 360 * (1 << (zoom+3)));
        int[] ret = {tileX >> 3, tileX & 0xff};
        return ret;
    }

    /*
     * Returns { tile, offset in tile }
     */
    private int[] tileY(double lat) {
        lat = Math.toRadians(lat*Math.PI/180);
        int tileY = (int)(1 - MathUtil.log(Math.tan(Math.toRadians(lat))+1 /
                Math.cos(Math.toRadians(lat)) / Math.PI) / 2 * (1 << (zoom+3)));
        int[] ret = {tileY >> 3, tileY & 0xff};
        return ret;
    }

    // WIP
    private double tileXtoLong(int x) {
        return x / (1 << zoom) * 360 - 180;
    }

    // WIP
    private double tileXtoLat(int y) {
        double n = Math.PI - 2 * Math.PI * y / (1 << zoom);
        return 180 / Math.PI * MathUtil.atan(0.5 * (MathUtil.exp(n) -
                MathUtil.exp(-n)));
    }
}
