Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

Method 1:
Time complexity: O(words1.length) * O(pairs.length) * O(word string length for comparison)
Space complexity: O(1)
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
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
        for (int i = 0; i < words1.length; i++){
            if (words1[i].equals(words2[i])){
                continue;
            }
            boolean found = false;
            for (int j = 0; j < pairs.length; j++){
                if (pairs[j][0].equals(words1[i]) && pairs[j][1].equals(words2[i]) || pairs[j][0].equals(words2[i]) && pairs[j][1].equals(words1[i])){
                    found = true;
                    break;
                }
            }
            if (!found){
                return false;
            }
        }
        return true;
    }
}

Method 2:
Time complexity: O(words1.length) * O(word string length for comparison)
Space complexity: O(pairs.length)
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
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
            if (map.containsKey(words1[i])){
                if (map.get(words1[i]).contains(words2[i])){
                    continue;
                }else{
                    return false;
                }
            }
            // if (map.containsKey(words2[i])){
            //     if (map.get(words2[i]).contains(words1[i])){
            //         continue;
            //     }else{
            //         return false;
            //     }
            // }
            return false;
        }
        return true;
    }
}

class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
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
            if (!map.get(words1[i]).contains(words2[i])){
                return false;
            }
        }
        return true;
    }
}
