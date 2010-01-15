/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

/**
 * Provides user/application settings to the rest of the application
 */
public interface ISettingsProvider {

    /**
     * Get all avaiable IMapProviders that can be used
     * @return array of IMapProviders
     */
    IMapProvider[] getAvailableMapProviders();
    
    /**
     * Get currently selected IMapProvider
     * @return Currently selected IMapProvider
     */
    IMapProvider getMapProvider();

    /**
     * Set currently selected IMapProvider
     * @param maps IMapProvider to use
     */
    void setMapProvider(IMapProvider maps);

    /**
     * Get all available ICacheProviders that can be used
     * @return array of ICacheProviders
     */
    ICacheProvider[] getAvailableCacheProviders();

    /**
     * Get currently selected ICacheProvider
     * @return Currently selected ICacheProvider
     */
    ICacheProvider getCacheProvider();
    
    /**
     * Set currently selected ICacheProvider
     * @param caches ICacheProvider to use
     */
    void setCacheProvider(ICacheProvider caches);

    /**
     * Get the last used zoom-level
     * @return zoom-level
     */
    int getLastZoom();

    /**
     * Set last used zoom-level
     * @param zoom new zoom-level
     */
    void setLastZoom(int zoom);

    /**
     * Get the last known position of the User since last time GPS was working
     * @return last known position
     */
    Position getLastPosition();

    /**
     * Set last known position
     * @param position new position
     */
    void setLastPosition(Position position);

}
