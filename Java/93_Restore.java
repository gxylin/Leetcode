Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)


class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12){
            return result;
        }
        dfs(result, s, "", 0, 4);
        return result;
    }
    private void dfs(List<String> result, String s, String item, int start, int k){
        if (k == 0 && start == s.length()){
            result.add(item);
            return;
        }
        for (int i = start; i < s.length() && i - start < 3 ; i++){
            String str = s.substring(start, i+1);
            if (str.length() > 1 && str.startsWith("0")){
                break;
            }
            int num = Integer.parseInt(str);
            if (num <= 255 && num >= 0){
                dfs(result, s, start == 0 ? str : item + "." + str, i + 1, k-1);
            }
        }
    }
}

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 12){
            return res;
        }
        dfs(res, "", s, 0, 4);
        return res;
    }
    private void dfs(List<String> res, String item, String s, int start, int k){
        if (k == 0 && start == s.length()){
            res.add(item);
            return;
        }
        for (int i = start; i < s.length() && i - start < 3; i++){
            String str = s.substring(start, i+1);
            if (str.length() > 1 && str.charAt(0) == '0'){
                break;
            }
            int num = Integer.parseInt(str);
            if (num > 255){
                break;
            }
            dfs(res, start == 0 ? str : item + "." + str, s, i+1, k-1); 
        }
    }
}

https://leetcode.com/problems/restore-ip-addresses/discuss/30944/Very-simple-DFS-solution
