There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.

Backtracking
class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) Math.pow(k, n);
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < n; i++){
            sb.append("0");
        }
        seen.add(sb.toString());
        dfs(sb, seen, total, k, n);
        return sb.toString();
    }
    private boolean dfs(StringBuilder sb, Set<String> seen, int total, int k, int n){
        if (seen.size() == total){
            return true;
        }
        String prev = sb.substring(sb.length() - n + 1);
        for (int i = 0; i < k; i++){
            String next = prev + i;
            if (!seen.contains(next)){
                seen.add(next);
                sb.append(i);
                if (dfs(sb,seen, total, k, n)){
                    return true;
                }else{
                    sb.deleteCharAt(sb.length() - 1);
                    seen.remove(next);
                }
            }
        }
        return false;
    }
}
