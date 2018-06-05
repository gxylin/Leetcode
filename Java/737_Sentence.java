Based on last question I, but transitivity is allowed.
Note that the similarity relation is transitive. For example, if "great" and "good" are similar, 
and "fine" and "good" are similar, then "great" and "fine" are similar. 


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
        return false;
    }
}
