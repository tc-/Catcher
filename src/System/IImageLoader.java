/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


/**
 * Loads and composes images
 */
public interface IImageLoader {

    Object httpLoad(String url, String localCachePath, int minimumMemoryCache);
    Object localLoad(String path, int minimumMemoryCache);

    Object createImage(int width, int height);
    Object drawImage(Object canvas, Object image, int xPos, int yPos);
    
}
