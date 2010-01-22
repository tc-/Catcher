/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.IImageLoader;


public class MIDPImageLoader implements IImageLoader {

    public Object httpLoad(String url, String localCachePath, int minimumMemoryCache) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object localLoad(String path, int minimumMemoryCache) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object createImage(int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object drawImage(Object canvas, Object image, int xPos, int yPos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
