/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;


/**
 * Interface implemented by all views
 */
public interface IView {

    /**
     * Run when view is displayed
     */
    void activate();

    /**
     * Run when view is hidden
     */
    void deactivate();

}
