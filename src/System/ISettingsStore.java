/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public interface ISettingsStore {

    String getSetting(String key, String defaultValue);
    void setSetting(String key, String value);

}
