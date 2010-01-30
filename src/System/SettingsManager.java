/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;

import Caches.OpenCachingProvider;
import Maps.MercatorMap;

/**
 * Provides user/application settings to the rest of the application
 */
public class SettingsManager {

    private ISettingsStore store;
    private IPlatformManager platform;

    public SettingsManager(IPlatformManager platform)
    {
        this.platform = platform;
        this.store = platform.getSettingsStore();
    }

    /**
     * Get all avaiable IMapProviders that can be used
     * @return array of IMapProviders
     */
    public IMapProvider[] getAvailableMapProviders() {
        return null;
    }
    
    /**
     * Get currently selected IMapProvider
     * @return Currently selected IMapProvider
     */
    public IMapProvider getMapProvider() {
        return new MercatorMap(platform.getImageLoader());
    }

    /**
     * Set currently selected IMapProvider
     * @param maps IMapProvider to use
     */
    public void setMapProvider(IMapProvider maps) {

    }

    /**
     * Get all available ICacheProviders that can be used
     * @return array of ICacheProviders
     */
    public ICacheProvider[] getAvailableCacheProviders() {
        return null;
    }

    /**
     * Get currently selected ICacheProvider
     * @return Currently selected ICacheProvider
     */
    public ICacheProvider getCacheProvider() {
        return new OpenCachingProvider();
    }
    
    /**
     * Set currently selected ICacheProvider
     * @param caches ICacheProvider to use
     */
    public void setCacheProvider(ICacheProvider caches) {

    }

    /**
     * Get the last used zoom-level
     * @return zoom-level
     */
    public int getLastZoom() {
        return Integer.parseInt(store.getSetting("Zoom", "10"));
    }

    /**
     * Set last used zoom-level
     * @param zoom new zoom-level
     */
    public void setLastZoom(int zoom) {
        store.setSetting("Zoom", Integer.toString(zoom));
    }

    /**
     * Get the last known position of the User since last time GPS was working
     * @return last known position
     */
    public Position getLastPosition() {
        return null;
    }

    /**
     * Set last known position
     * @param position new position
     */
    public void setLastPosition(Position position) {

    }

}
