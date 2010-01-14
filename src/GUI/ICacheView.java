/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import System.Cache;

/**
 *
 * @author tc
 */
public interface ICacheView extends IView {

    Cache getCache();

    void setCache(Cache cache);

}
