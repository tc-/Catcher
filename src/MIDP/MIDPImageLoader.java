/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.IImageLoader;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.lcdui.Image;


public class MIDPImageLoader implements IImageLoader {

    public Object httpLoad(String url, String localCachePath, int minimumMemoryCache) {
        //Test image: http://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png
        // Check if Image has been cached already
        Object ret = localLoad(localCachePath, minimumMemoryCache);
        
        // Check if Image was cached
        if (ret == null) {
            try {
                InputStream data = Connector.openDataInputStream(url);
                ret = Image.createImage(data); // createImage(InputStream) is MIDP 2.0
            } catch (IOException ex) {
                ex.printStackTrace();
                ret = null;
            }
        }

        return ret;
    }

    public Object localLoad(String path, int minimumMemoryCache) {
        return null;
    }

    public Object createImage(int width, int height) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object drawImage(Object canvas, Object image, int xPos, int yPos) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void StoreInCache()
    {

    }

}
