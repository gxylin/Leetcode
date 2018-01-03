A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.

class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] f = new int[s.length() + 1];
        f[0] = 1;
        if (s.charAt(0) == '0'){
            f[1] = 0;
        }else{
            f[1] = 1;
        }
        for (int i = 2; i <= s.length(); i++){
            char s1 = s.charAt(i-1);
            char s2 = s.charAt(i-2);
            if (s1 != '0' ){
                f[i] += f[i-1];
            }
            int v = s1 -'0'  + (s2 - '0') *10; // convert char to integer
            if (10 <= v && v <= 26){
                f[i] += f[i-2];
            }
        }
        return f[s.length()];
    }
}
