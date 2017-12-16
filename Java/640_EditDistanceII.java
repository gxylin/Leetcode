Given two strings S and T, determine if they are both one edit distance apart.

Have you met this question in a real interview? Yes
Example
Given s = "aDb", t = "adb"
return true

public class Solution {
    /*
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null){
            return false;
        }
        if (s.length() > t.length()){
            String temp = s;
            s = t;
            t = temp;
        }
        int slen = s.length();
        int tlen = t.length();
        int diff = tlen - slen;
        if (diff > 1){
            return false;
        }
        if (diff == 0){
            int count = 0;
            for (int i = 0; i < slen; i++){
                if (s.charAt(i) != t.charAt(i)){
                    count++;
                }
            }
            return (count == 1);
        }
        if (diff == 1){
            for (int i = 0; i < slen; i++){
                if (s.charAt(i) != t.charAt(i)){
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
            return true;
        }
        return true;
    }
}
