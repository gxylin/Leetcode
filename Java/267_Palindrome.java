Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list 
if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]

Example 2:

Input: "abc"
Output: []

Similar to permutation II to avoid duplication.
Backtracking for StringBuilder class

Basically, the idea is to perform permutation on half of the palindromic string and then form the full palindromic result.

class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        // step 1. build character count map and count odds
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int singelCount = 0;
        for (char ch : map.keySet()){
            if (map.get(ch) % 2 == 1){
                singelCount++;
            }
        }
        if (singelCount > 1){
            return result;
        }
        
        // step 2. add half count of each character to list
        List<Character> list = new ArrayList<>();
        String mid = "";
        for (char key : map.keySet()){
            int val = map.get(key);
            if (val % 2 == 1){
                mid += key;
            }
            for (int i = 0; i < val / 2; i++){
                list.add(key);
            }
        }
        // step 3. generate all the permutations
        dfs(result, list, mid, new boolean[list.size()], new StringBuilder());
        return result;
    }
    private void dfs(List<String> result, List<Character> list, String mid, boolean[] used, StringBuilder sb){
        if (sb.length() == list.size()){
            result.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        for (int i = 0; i < list.size(); i++){
            if (i > 0 && list.get(i) == list.get(i-1) && !used[i-1]){
                continue;
            }
            if (!used[i]){
                used[i] = true;
                sb.append(list.get(i));
                dfs(result, list, mid, used, sb);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
