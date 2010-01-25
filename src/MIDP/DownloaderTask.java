/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import Utils.Task;
import java.io.DataOutputStream;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.OutputConnection;
import javax.microedition.lcdui.Image;

/**
 *
 */
public class DownloaderTask extends Task {

    private final String url;
    private final String localCachePath;
    private final String tempCachePath;

    private Image image;

    public DownloaderTask(String url, String localCachePath, String tempCachePath) {
        this.url = url;
        this.localCachePath = localCachePath;
        this.tempCachePath = tempCachePath;
    }

    public void task() {
        try {
            InputStream data = Connector.openDataInputStream(url);
            OutputConnection tempFile = (OutputConnection)Connector.open(tempCachePath, Connector.WRITE);
            DataOutputStream outStream = tempFile.openDataOutputStream();

            byte[] buf = new byte[1024];
            int len;
            while ((len = data.read(buf)) > 0){
                outStream.write(buf, 0, len);
            }
            
            image = Image.createImage(data); // createImage(InputStream) is MIDP 2.0
        } catch (Exception ex) {
            ex.printStackTrace();
            image = null;
        }
    }

    public Image getImage() {
        return image;
    }

}
