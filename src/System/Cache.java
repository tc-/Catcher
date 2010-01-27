/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public class Cache {
    public static final int LOG_FOUND = 0;
    public static final int LOG_DNF = 1; // Did Not find
    public static final int LOG_NOTE = 2;
    public static final int LOG_PUBLISHED = 3;
    public static final int LOG_MAINTENANCE = 4;
    public static final int LOG_ARCHIVED = 5;
    public static final int LOG_DISABLED = 6;

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

    public String hint;         // decoded
    public String name;
    public String description;  // For sources with short and longdesc this is
                                // both of them concatenated
    public String lastLogs; // last logs concatenated, decoded
    public int[] lastLogsType; // latest first
    public int difficulty;
    public int terrain;
    public int type;

    // Decode rot13 enciphered text, maybe we should move this to StringUtils?!
    public String decodeHint(String s) {
        String res="";
        boolean decrypt=true;

        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c == '[') { decrypt = false; }
            else if (c == ']') { decrypt = true; }
            if (decrypt) {
                if (c >= 'a' && c <= 'm') { c += 13; }
                else if (c >= 'n' && c <= 'z') { c -= 13; }
                else if (c >= 'A' && c <= 'M') { c += 13; }
                else if (c >= 'N' && c <= 'Z') { c -= 13; }
            }
            res += c;
        }
        return res;
    }
}
