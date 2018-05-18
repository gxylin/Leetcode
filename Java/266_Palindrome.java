Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false

Example 2:

Input: "aab"
Output: true

Example 3:

Input: "carerac"
Output: true

Method 1:
class Solution {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int singleCount = 0;
        for (char key : map.keySet()){
            if (map.get(key) % 2 == 1){
                singleCount++;
            }
            if (singleCount > 1){
                return false;
            }
        }
        return true;
    }
}

Method 2:
class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (!set.contains(c)){
                set.add(c);
            }else{
                set.remove(c);
            }
        }
        return set.size() <= 1;
    }
}
