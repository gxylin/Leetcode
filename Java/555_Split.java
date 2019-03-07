Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose to reverse 
it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the loop, which will 
make the looped string into a regular one.

Specifically, to find the lexicographically biggest string, you need to experience two phases:

Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as given.
Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from the
character at the cutpoint.

And your job is to find the lexicographically biggest one among all the possible regular strings.

Example:

Input: "abc", "xyz"
Output: "zyxcba"
Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-", 
where '-' represents the looped status. 
The answer string came from the fourth looped one, 
where you could cut from the middle character 'a' and get "zyxcba".

Note:

    The input strings will only contain lowercase letters.
    The total length of all the strings will not over 1,000.


Key idea:
1. find the bigger one of each str: normal or reverse
2. try every possibiity to for each str

Brute force:
Time complexity: O(n^2), n is the sum length of all lengths of strs
class Solution {
    String res = "";
    public String splitLoopedString(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        int n = strs.length;
        //step 1: find the bigger one for each str
        for (int i = 0; i < n; i++){
            String strR = reverse(strs[i]);
            if (strR.compareTo(strs[i]) > 0){
                strs[i] = strR;
            }
        }
        //step 2: check normal or reverse for each str
        for (int i = 0; i < n; i++){
            solve(strs, i, true);
            solve(strs, i, false);
        }
        return res;
    }
    private String reverse(String str){
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--){
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
    private void solve(String[] strs, int i, boolean rev){
        String str = rev ? reverse(strs[i]) : strs[i];
        StringBuilder sb1 = new StringBuilder();
        for (int j = 0; j < i; j++){
            sb1.append(strs[j]);
        }
        StringBuilder sb2 = new StringBuilder();
        for (int j = i + 1; j < strs.length; j++){
            sb2.append(strs[j]);
        }
        for (int k = 0; k < str.length(); k++){
            StringBuilder sb = new StringBuilder();
            sb.append(str.substring(k));
            sb.append(sb2);
            sb.append(sb1);
            sb.append(str.substring(0, k));
            String newStr = sb.toString();
            if (newStr.compareTo(res) > 0){
                res = newStr;
            }
        }
    }
}
