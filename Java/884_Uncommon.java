We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)

A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.

Return a list of all uncommon words. 

You may return the list in any order.

 

Example 1:

Input: A = "this apple is sweet", B = "this apple is sour"
Output: ["sweet","sour"]
Example 2:

Input: A = "apple apple", B = "banana"
Output: ["banana"]
 

Note:

0 <= A.length <= 200
0 <= B.length <= 200
A and B both contain only spaces and lowercase letters.

Similar as 819. Most Common Word
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        String C = A + " " + B;
        List<String> list = new ArrayList<>();
        
        String[] strs = C.split("\\s+");
        Map<String, Integer> map = new HashMap<>();
        for (String str : strs){
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (String str : map.keySet()){
            if (map.get(str) == 1){
               list.add(str);
            }
        }
        
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
}

class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        List<String> list = new ArrayList<>();
        getSet(A, B, list);
        getSet(B, A, list);
        String[] res = new String[list.size()];
        for (int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }
    private void getSet(String A, String B, List<String> list){
        String[] strA = A.split("\\s+");
        String[] strB = B.split("\\s+");
        Set<String> set = new HashSet<>();
        for (String str : strA){
            set.add(str);
        }
        for (String str : strB){
            if (!set.contains(str)){
                list.add(str);
            }
        }
    }
}
