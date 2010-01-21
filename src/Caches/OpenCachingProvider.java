/*
 * Catcher
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package Caches;

import System.Cache;
import System.CacheFilter;
import System.ICacheProvider;
import System.Position;


public class OpenCachingProvider implements ICacheProvider {

    public OpenCachingProvider()
    {
        
    }

    public Cache[] findNear(Position position, int radiusKiloMeters, CacheFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Cache[] findText(String text, boolean searchCacheNames, boolean searchDescriptions, boolean searchLogs, CacheFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Cache[] findByOwner(String ownerID, CacheFilter filter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
