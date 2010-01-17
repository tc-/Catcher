/*
 * Catcher
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.Cache;

/**
 * Show a list of caches
 */
public interface ICacheListView extends IView {

    Cache[] getCaches();

    Cache getSelected();

    void setCaches(Cache[] caches);

    void setSelected(Cache selected);

}
