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
