/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package Maps;

import System.IImageLoader;
import System.Position;
import System.IMapProvider;
import System.MathUtil;

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
    private IImageLoader imageLoader = null;

    private static final int NOF_CACHED_TILES = 16;

    // We could set this to 0 and see the entire world, but what use is it?
    private static int ZOOM_MIN = 6;

    // Depending on map source, this value varies. 14 would be safe for most
    // maps.
    private static final int ZOOM_MAX = 20;

    private int zoom;

    // It is assumed these will not be used before a call to getMap()
    // they are used in pixel to coord conversions.
    private Position mapCenter;
    private int mapWidth;
    private int mapHeight;
    private int[] mapTileX;
    private int[] mapTileY;

    private String mapSource;
    private String mapID;

    public void setMapSource(String source, String id) {
        mapSource = source;
        mapID = id;
    }

    public MercatorMap(IImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

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
        // offset in map to center tile
        int ctx = (mapWidth >> 1)-mapTileX[1];
        int cty = (mapHeight >> 1)-mapTileY[1];

        /* case:
         * ctx = -20
         * x = 270
         * x-ctx = 290
         * 290/256=1
         */
        int xPos = (mapTileX[0]<<3)+(x-ctx);
        int yPos = (mapTileY[0]<<3)+(y-cty);
        return new Position(yToLat(yPos), xToLon(xPos));
    }

    /*
     * fixme: Prioritize tile loading order, load from center
     * 
     * This could be done by calculating each tile's center position and
     * ordering by distance to map center. Optimize by offsetting map center
     * position by a half tile instead.
     */
    public Object getMap(Position center, int width, int height) {
        mapCenter = center;
        mapWidth = width;
        mapHeight = height;

        // Get center tile position and calculate the rest from there.
        mapTileX = tileX(center.getLon());
        mapTileY = tileY(center.getLat());

        // offset in map to center tile
        int ctx = (width >> 1)-mapTileX[1];
        int cty = (height >> 1)-mapTileY[1];

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
        int tilesLeft = (ctx+255) >> 3;
        int tilesAbove = (cty+255) >> 3;

        int tilesRight = (width-1-ctx) >> 3;
        int tilesBelow = (height-1-cty) >> 3;

        int nofTilesX = tilesLeft+tilesRight+1;
        int nofTilesY = tilesAbove+tilesBelow+1;

        // offset of topleftmost tile.
        int firstX = ctx-(tilesLeft<<3);
        int firstY = cty-(tilesAbove<<3);
        int firstTileX = mapTileX[0]-tilesLeft;
        int firstTileY = mapTileY[0]-tilesAbove;

        Object map = imageLoader.createImage(width, height);
        Object imTile = null;
        for (int y=0; y<nofTilesY; y++) {
            for (int x=0; x<nofTilesX; x++) {
                imTile = getTile(firstTileX+x, firstTileY+y, getZoom());
                map = imageLoader.drawImage(map, imTile, firstX+(x<<3),
                        firstY+(y<<3));
            }
        }
        return map;
    }

    private String getTileURL(int tileX, int tileY, int tileZ) {
        
        return null;
    }

    private String getTilePath(int tileX, int tileY, int tileZ) {
        return null;
    }

    private Object getTile(int tileX, int tileY, int tileZ) {
        // Try local storage
        String path = getTilePath(tileX, tileY, tileZ);
        Object imTile = imageLoader.localLoad(path, NOF_CACHED_TILES);
        if (imTile != null) { return imTile; }

        // Get from http
        String url = getTileURL(tileX, tileY, tileZ);
        imTile = imageLoader.httpLoad(url, path, NOF_CACHED_TILES);

        /*
    Object httpLoad(String url, String localCachePath);
    Object localLoad(String path);

    Object createImage(int width, int height);
    Object drawImage(Object canvas, Object image, int xPos, int yPos);


        return img;
        */
        return null; // No image for you
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

    /*
     * Returns longitude for given tile.tileoffs X value
     * x is a fixed point int n.8 (8 bits fraction)
     *
     * fixme: optimize
     */
    private double xToLon(int x) {
        double dx = x/256;
        return dx / (1 << zoom) * 360 - 180;
    }

    /*
     * Returns latitude for given tile+tileoffs Y value
     * y is a fixed point int n.8 (8 bits fraction)
     * 
     * fixme: optimize
     */
    private double yToLat(int y) {
        double dy = y/256;
        double n = Math.PI - 2 * Math.PI * dy / (1 << zoom);
        return 180 / Math.PI * MathUtil.atan(0.5 * (MathUtil.exp(n) -
                MathUtil.exp(-n)));
    }
}
