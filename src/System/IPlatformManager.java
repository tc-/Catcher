/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

import GUI.IViewManager;


/**
 * Provides the application with all the device dependent interfaces
 */
public interface IPlatformManager {

    /**
     * Get IImageLoader to use in the applcation
     * @return imageloader to use
     */
    IImageLoader getImageLoader();

    /**
     * Get IPositionProvider to use in the applcation
     * @return position provider to use
     */
    IPositionProvider getPositionProvider();

    /**
     * Get ISettingsProvider to use in the applcation
     * @return settings provider to use
     */
    ISettingsProvider getSettingsProvider();

    /**
     * Get IViewManager to use in the applcation
     * @return view manager to use
     */
    IViewManager getViewManager();
    
}
