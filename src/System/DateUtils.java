/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package System;

import java.util.Calendar;

public class DateUtils {
    // Extend this to (optionally) take date as param.
    public static String getDateStr() {
        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);
        int m = cal.get(Calendar.MONTH)+1; // January = 0
        int d = cal.get(Calendar.DAY_OF_MONTH);
        String str = y+"-"+(m<10?"0":"")+m+"-"+(d<10?"0":"")+d;
        return str;
    }

    // Extend this to (optionally) take date as param.
    public static String getTimeStr() {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY); // 24-hour hour
        int m = cal.get(Calendar.MINUTE);
        String str = (h<10?"0":"")+h+":"+(m<10?"0":"")+m;
        return str;
    }

    // Extend this to (optionally) take date as param.
    public static String getDateTimeStr() {
        return getDateStr()+" / "+getTimeStr();
    }


}
