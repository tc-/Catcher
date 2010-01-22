/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public interface ICacheProvider {

    Cache[] findNear(Position position, int radiusKiloMeters, CacheFilter filter);
    Cache[] findText(String text, boolean searchCacheNames, boolean searchDescriptions, boolean searchLogs, CacheFilter filter);
    Cache[] findByOwner(String ownerID, CacheFilter filter);

}
