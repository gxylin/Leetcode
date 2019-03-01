We are stacking blocks to form a pyramid. Each block has a color which is a one letter string, like `'Z'`.

For every block of color `C` we place not in the bottom row, we are placing it on top of a left block of color `A` and right block of color `B`. We are allowed to place the block there only if `(A, B, C)` is an allowed triple.

We start with a bottom row of bottom, represented as a single string. We also start with a list of allowed triples allowed. Each allowed triple is represented as a string of length 3.

Return true if we can build the pyramid all the way to the top, otherwise false.

Example 1:
Input: bottom = "XYZ", allowed = ["XYD", "YZE", "DEA", "FFF"]
Output: true
Explanation:
We can stack the pyramid like this:
    A
   / \
  D   E
 / \ / \
X   Y   Z

This works because ('X', 'Y', 'D'), ('Y', 'Z', 'E'), and ('D', 'E', 'A') are allowed triples.
Example 2:
Input: bottom = "XXYX", allowed = ["XXX", "XXY", "XYX", "XYY", "YXZ"]
Output: false
Explanation:
We can't stack the pyramid to the top.
Note that there could be allowed triples (A, B, C) and (A, B, D) with C != D.
Note:
bottom will be a string with length in range [2, 8].
allowed will have length in range [0, 200].
Letters in all strings will be chosen from the set {'A', 'B', 'C', 'D', 'E', 'F', 'G'}.

Method: DFS + backtracking
class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : allowed){
            String key = str.substring(0,2);
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str.substring(2));
        }
        return dfs(bottom, map);
    }
    private boolean dfs(String bottom, Map<String, List<String>> map){
        if (bottom.length() == 1){
            return true;
        }
        for (int i = 0; i < bottom.length() - 1; i++){
            String prefix = bottom.substring(i, i+2);
            if (!map.containsKey(prefix)){
                return false;
            }
        }
        List<String> list = new ArrayList<>();
        getList(bottom, map, list, 0, new StringBuilder());
        for (String str : list){
            if (dfs(str, map)){
                return true;
            }
        }
        return false;
    }
    private void getList(String bottom, Map<String, List<String>> map, List<String> list, int start, StringBuilder sb){
        if (start == bottom.length() - 1){
            list.add(sb.toString());
            return;
        }
        List<String> temp = map.get(bottom.substring(start, start+2));
        for (String s : temp){
            sb.append(s);
            getList(bottom, map, list, start + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}


Better version:
class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String s : allowed){
            String key = s.substring(0, 2);
            String val = s.substring(2);
            if (!map.containsKey(key)){
                map.put(key, new HashSet<>());
            }
            map.get(key).add(val);
        }
        
        return dfs(bottom, map);
    }
    private boolean dfs(String target,  Map<String, Set<String>> map){
        if (target.length() == 1){
            return true;
        }
        for (int i = 0; i < target.length() - 1; i++){
            String sub = target.substring(i, i+2);
            if (!map.containsKey(sub)){
                return false;
            } 
        }
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        backtrack(list, sb, map, target, 0);
        for (String str : list){
            if (dfs(str, map)){
                return true;
            }
        }
        return false;
    }
    private void backtrack(List<String> list, StringBuilder sb, Map<String, Set<String>> map, String target, int start){
        if (sb.length() == target.length() - 1){
            list.add(sb.toString());
            return;
        }
        Set<String> set = map.get(target.substring(start, start+2));
        for (String key : set){
            sb.append(key);
            backtrack(list, sb, map, target, start+1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
