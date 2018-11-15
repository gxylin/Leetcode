Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

Method 1:
Conver to alphabet array with the size of 26
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null){
            return false;
        }
        if (s.length() == 0 && t.length() == 0){
            return true;
        }
        final int MAX_CHAR = 26;
        int[] alphabet = new int[MAX_CHAR];
        for (int i = 0; i < s.length(); i++){
            alphabet[s.charAt(i) - 'a']++; 
        }
        for (int i = 0; i < t.length(); i++){
            alphabet[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < MAX_CHAR; i++){
            if (alphabet[i] != 0){
                return false;
            }
        }
        return true;
    }
}

Method 2:
Use sort and compare
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null){
            return false;
        }
        if (s.length() == 0 && t.length() == 0){
            return true;
        }
        char[] sArray = s.toCharArray();
        Arrays.sort(sArray);
        String s_new = String.valueOf(sArray);
        
        char[] tArray = t.toCharArray();
        Arrays.sort(tArray);
        String t_new = String.valueOf(tArray);
        
        if (s_new.equals(t_new)){
            return true;
        }
        return false;
    }
}

Method 3:
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] alphabet = new int[256];
        for (int i = 0; i < s.length(); i++){
            alphabet[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++){
            alphabet[t.charAt(i)]--;
        }
        for (int i = 0; i < alphabet.length; i++){
            if (alphabet[i] != 0){
                return false;
            }
        }
        return true;
    }
}

Better version:
class Solution {
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()){
            if (!map.containsKey(c)){
                return false;
            }else{
                map.put(c, map.get(c) - 1);
            }
        }
        for (int val : map.values()){
            if (val != 0){
                return false;
            }
        }
        return true;
    }
}
