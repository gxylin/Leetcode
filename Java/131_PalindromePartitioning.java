Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]

Method 1: DFS
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0){
            return result;
        }
        List<String> item = new ArrayList<>();
        dfs(result, item, s, 0);
        return result;
    }
    private void dfs(List<List<String>> result, List<String> item, String s, int start){
        if (start == s.length()){
            result.add(new ArrayList<String>(item));
            return;
        }
        for (int i = start; i < s.length(); i++){
            String str = s.substring(start, i+1);
            if (isValid(str)){
                item.add(str);
                dfs(result, item, s, i + 1);
                item.remove(item.size() - 1);
            }
        }
    }
    private boolean isValid(String s){
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


Method 2: Recursion + memo
class Solution {
    Map<String, List<List<String>>> map = new HashMap<>();
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0){
            return res;
        }
        if (map.containsKey(s)){
            return map.get(s);
        }
        for (int i = 1; i <= s.length(); i++){
            String sub = s.substring(0, i);
            if (isValid(sub)){
                List<List<String>> temp = partition(s.substring(i));
                if (temp.size() == 0){
                    List<String> list = new ArrayList<>();
                    list.add(sub);
                    res.add(list);
                }else{
                    for (List<String> item: temp){
                        List<String> list = new ArrayList<>();
                        list.add(sub);
                        list.addAll(item);
                        res.add(list);
                    }
                }
                
            }
        }
        map.put(s, res);
        return res;
    }
    private boolean isValid(String s){
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
