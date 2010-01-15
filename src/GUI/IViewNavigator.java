/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;


/**
 * Provides navigation through the main views
 */
public interface IViewNavigator {

    /**
     * Show next main view
     */
    void ShowNext();

    /**
     * Show previous main view
     */
    void ShowPrevious();
    
}
