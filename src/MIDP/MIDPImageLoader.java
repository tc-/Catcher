/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.IImageLoader;
import Utils.TaskRunner;


public class MIDPImageLoader implements IImageLoader {

    private TaskRunner runner;

    public MIDPImageLoader() {
        runner = new TaskRunner("Downloader", 4);
        runner.start();
    }

    public Object httpLoad(String url, String localCachePath, int minimumMemoryCache) {
        //Test image: http://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png
        // Check if Image has been cached already
        Object ret = localLoad(localCachePath, minimumMemoryCache);
        
        // Check if Image was cached
        if (ret == null) {
            localCachePath = "file:///Catcher/test.png";
           DownloaderTask task = new DownloaderTask(url, localCachePath, localCachePath);
           runner.addTask(task);
        }

        return ret;
    }

    public Object localLoad(String path, int minimumMemoryCache) {
        return null;
    }

    public Object createImage(int width, int height) {
        return null;
    }

    public Object drawImage(Object canvas, Object image, int xPos, int yPos) {
        return null;
    }

}
