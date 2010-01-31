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
        int count = s.indexOf(oldStr);
        if (count < 0) return null;
        return s.substring(0, count) + newStr +
                s.substring(count+oldStr.length());
    }

    /**
     * Decode rot13 enciphered text. Text in []-brackets are considered decoded
     * @return decoded string.
     */
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
