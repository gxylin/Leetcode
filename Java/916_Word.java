We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a
subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a. 

Return a list of all universal words in A.  You can return the words in any order.

 

Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]
Example 3:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]
Example 4:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
Example 5:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]
 

Note:

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].

Method 1: Brute Force TLE
Time complexity: O(A*B)
Space complexity: O(1)
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        for (String a : A){
            boolean isUniversal = true;
            int[] hash = new int[26];
            for (char c : a.toCharArray()){
                hash[c - 'a']++;
            }
            for (String b : B){
                int[] temp = new int[26];
                for (int i = 0; i < 26; i++){
                    temp[i] = hash[i];
                }
                for (char c : b.toCharArray()){
                    temp[c - 'a']--;
                    if (temp[c - 'a'] < 0){
                        isUniversal = false;
                        break;
                    }
                }
                if (!isUniversal){
                    break;
                }
            }
            if (isUniversal){
                res.add(a);
            }
        }
        return res;
    }
}

Similar as Leetcode 1002 Find Common Character
https://github.com/optimisea/Leetcode/blob/master/Java/1002_Find.java

Method 2: Best solution
Time complexity: O(A + B)
Space complexity: O(1)
class Solution {
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        int[] hash = new int[26];
        for (String b : B){
            int[] temp = new int[26];
            for (char c : b.toCharArray()){
                temp[c - 'a']++; 
            }
            for (int i = 0; i < 26; i++){
                hash[i] = Math.max(hash[i], temp[i]); // here is the trick, get the max number of each character
            }
        }
        for (String a : A){
            boolean isUniversal = true;
            int[] temp = new int[26];
            for (char c : a.toCharArray()){
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++){
                if (temp[i] < hash[i]){
                    isUniversal = false;
                    break;
                }
            }
            if (isUniversal){
                res.add(a);
            }
        }
        return res;
    }
}
