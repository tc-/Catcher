/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import GUI.IView;
import System.Cache;

/**
 *
 * @author tc
 */
public interface ICacheListView extends IView {

    Cache[] getCaches();

    Cache getSelected();

    void setCaches(Cache[] caches);

    void setSelected(Cache selected);

}
