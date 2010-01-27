/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import Utils.Task;
import com.sun.kvem.jsr082.impl.PermissionsHandler;
import com.sun.mmedia.PermissionAccessor;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.OutputConnection;
import javax.microedition.io.file.FileConnection;
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
            image = Image.createImage(data); // createImage(InputStream) is MIDP 2.0
            data.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            image = null;
        }
    }

    public Image getImage() {
        return image;
    }

    public String getLocalCachePath() {
        return localCachePath;
    }

    public String getTempCachePath() {
        return tempCachePath;
    }

    public String getUrl() {
        return url;
    }

}
