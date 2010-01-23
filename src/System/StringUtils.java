/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package System;

/**
 * Random string utilities
 */
public class StringUtils {

    /*
     * Replace oldStr with newStr in s
     */
    public static String replace(String s, String oldStr, String newStr) {
        int count = s.indexOf(oldStr)-1;
        return s.substring(0, count) + newStr +
                s.substring(count+oldStr.length()+2);
    }
}
