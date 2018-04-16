Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.



class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String>  map = new HashMap<>();
        String[] strArray = str.split(" ");
        if (pattern.length() != strArray.length){
            return false;
        }
        for (int i = 0; i < pattern.length(); i++){
            char c = pattern.charAt(i);
            if (!map.containsKey(c)){
                map.put(c, strArray[i]);
            }else{
                if (!map.get(c).equals(strArray[i])){
                    return false;
                }
            }
        }
        Map<String, Character>  map1 = new HashMap<>();
        for (int i = 0; i < strArray.length; i++){
            char c = pattern.charAt(i);
            if (!map1.containsKey(strArray[i])){
                map1.put(strArray[i], c);
            }else{
                if (map1.get(strArray[i]) != c){
                    return false;
                }
            }
        }
        return true;
    }
}


https://leetcode.com/problems/word-pattern/discuss/73402/8-lines-simple-Java
previousValue =map(key, value) return the stored previous value

class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map map = new HashMap();
        String[] strArray = str.split(" ");
        if (pattern.length() != strArray.length){
            return false;
        }
        for (Integer i = 0; i < pattern.length(); i++){ //notice here we must use Integer instead of int
        // i being an Integer object, which allows to compare with just != because 
        // there’s no autoboxing-same-value-to-different-objects-problem anymore.
            char c = pattern.charAt(i);
            if (map.put(c, i) != map.put(strArray[i], i)){
                return false;
            }
        }
        return true;
    }
}
