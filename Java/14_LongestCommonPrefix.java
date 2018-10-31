Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++){
            while (strs[i].indexOf(pre) != 0){
                pre = pre.substring(0, pre.length() - 1);
            }
        }
        return pre;
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Boolean found = false;
        for (int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++){
                if (i == strs[j].length() || c != strs[j].charAt(i)){
                    found = true;
                    break;
                }
            }
            if (!found){
                sb.append(c);
            }else{
                break;
            }   
        }
        return sb.toString();
    }
}

Better: after sorting, only compare first and last
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        for (int i = 0; i < first.length(); i++){
            if (first.charAt(i) != last.charAt(i)){
                break;
            }else{
                sb.append(first.charAt(i));
            }
        }
        return sb.toString();
    }
}
