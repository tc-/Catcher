/*
 * Catcher, MapView.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public class Cache {
    private static final int LOG_FOUND = 0;
    private static final int LOG_DNF = 1; // Did Not find
    private static final int LOG_NOTE = 2;
    private static final int LOG_PUBLISHED = 3;
    private static final int LOG_MAINTENANCE = 4;
    private static final int LOG_ARCHIVED = 5;
    private static final int LOG_DISABLED = 6;

    // Cache types (these might change. These are the currently supported on
    // geocaching.com. opencaching has additional types, and more are added.)
    public static final int CT_REGULAR=0;
    public static final int CT_MYSTERY=1;// aka unknown
    public static final int CT_UNKNOWN=1;
    public static final int CT_MULTI=2;
    public static final int CT_LETTERBOX=3;
    public static final int CT_VIRTUAL=4;
    public static final int CT_WHEREIGO=5;
    public static final int CT_EVENT=6;
    public static final int CT_WEBCAM=7;
    public static final int CT_EARTH=8;
    public static final int CT_CITO=9; // aka "Cache In Trash Out"
    public static final int CT_MEGAEVENT=10;

    public Position position;

    public String name;
    public String description;
    
}
