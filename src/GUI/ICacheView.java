/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.Cache;

/**
 * Displays information about a single Cache
 */
public interface ICacheView extends IView {

    /**
     * Get the currently selected Cache
     * @return current Cache
     */
    Cache getCache();

    /**
     * Set currently selected Cache
     * @param cache new Cache
     */
    void setCache(Cache cache);

}
