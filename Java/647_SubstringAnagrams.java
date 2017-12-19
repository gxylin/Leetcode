Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.

The order of output does not matter.

Have you met this question in a real interview? Yes
Example
Given s = "cbaebabacd" p = "abc"

return [0, 6]

The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Method 1: 
Time complexity: O(n)
public class Solution {
    /*
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }
        int[] count = new int[256];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        for (int i = 0; i < p.length(); i++){
            count[sc[i]]++;
            count[pc[i]]--;
        }
        int absSum = 0;
        for (int item : count){
            absSum += Math.abs(item);
        }
        if (absSum == 0){
            result.add(0);
        }
        for (int i = p.length(); i < s.length(); i++){
            absSum = absSum - Math.abs(count[sc[i]]) - 
                            Math.abs(count[sc[i-p.length()]]); //remove the absolute sum
            count[sc[i]]++;
            count[sc[i-p.length()]]--;
            absSum = absSum + Math.abs(count[sc[i]]) + 
                            Math.abs(count[sc[i-p.length()]]);  //add the updated absolute sum
            if (absSum == 0){
                result.add(i-p.length()+1);
            }                
        }
        return result;
    }
}

Method 2:
Time complexity: O(n * 256)
public class Solution {
    /*
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()){
            return result;
        }
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        int[] countSc = new int[256];
        int[] countPc = new int[256];
        for (int i = 0; i < p.length(); i++){
            countSc[sc[i]]++;
            countPc[pc[i]]++;
        }
        if (Arrays.equals(countSc, countPc)){   //compare two arrays take 256
            result.add(0);
        }
        for (int i = p.length(); i < s.length(); i++){
            countSc[sc[i]]++;
            countSc[sc[i-p.length()]]--;
            if (Arrays.equals(countSc, countPc)){
                result.add(i-p.length()+1);
            }
        }
        return result;
    }
}
