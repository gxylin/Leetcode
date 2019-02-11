Based on last question I, but transitivity is allowed.
Note that the similarity relation is transitive. For example, if "great" and "good" are similar, 
and "fine" and "good" are similar, then "great" and "fine" are similar. 

Method 1: DFS
Time complexity: Time complexity: O(words1.length) * O(pairs.length) * O(word string length for comparison)
Space complexity: O(pairs.length)
class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length){
            return false;
        }
        if (pairs.length == 0){
            for (int i = 0; i < words1.length; i++){
                if (!words1[i].equals(words2[i])){
                    return false;
                }
            }
        }
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < pairs.length; i++){
            if (!map.containsKey(pairs[i][0])){
                map.put(pairs[i][0], new HashSet<String>());
            }
            if (!map.containsKey(pairs[i][1])){
                map.put(pairs[i][1], new HashSet<String>());
            }
            map.get(pairs[i][0]).add(pairs[i][1]);
            map.get(pairs[i][1]).add(pairs[i][0]);
        }
        for (int i = 0; i < words1.length; i++){
            if (words1[i].equals(words2[i])){
                continue;
            }
            if (!map.containsKey(words1[i])){
                return false;
            }
            if (!dfs(map, words1[i], words2[i], new HashSet<String>())){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(Map<String, Set<String>> map, String source, String target, HashSet<String> visited){
        if (map.get(source).contains(target)){
            return true;
        }
        visited.add(source);
        for (String str : map.get(source)){
            if (!visited.contains(str) && dfs(map, str, target, visited)){
                return true;
            }
        }
        visited.remove(source);
        return false;
    }
}

Method 2: Union Find
Time complexity: Time complexity: O(words1.length) * O(word string length for comparison)
Space complexity: O(pairs.length)
Time complexity with path compression: Find: O(1), Union: O(1)

class Solution {
    class UF{
        int[] parent;
        public UF(int n){
            parent = new int[n];
            for (int i = 0; i < n; i++){
                parent[i] = i;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }
        public void union(int x, int y){
            int a = find(x);
            int b = find(y);
            if (a != b){
                parent[a] = b;
            }
        }
    }
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length){
            return false;
        }
        if (pairs.length == 0){
            for (int i = 0; i < words1.length; i++){
                if (!words1[i].equals(words2[i])){
                    return false;
                }
            }
        }
        Map<String, Integer> map = new HashMap<>();
        UF uf = new UF(2 * pairs.length);
        int count = 0;
        for (int i = 0; i < pairs.length; i++){
            if (!map.containsKey(pairs[i][0])){
                map.put(pairs[i][0], count++);
            }
            if (!map.containsKey(pairs[i][1])){
                map.put(pairs[i][1], count++);
            }
            uf.union(map.get(pairs[i][0]), map.get(pairs[i][1]));
        }
        for (int i = 0; i < words1.length; i++){
            if (words1[i].equals(words2[i])){
                continue;
            }
            if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]) || uf.find(map.get(words1[i])) != uf.find(map.get(words2[i]))){
                return false;
            }
        }
        return true;
    }
    
}
