Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".

class Solution {
    public String reverseVowels(String s) {
        char[] charArray= s.toCharArray();
        int start = 0;
        int end = charArray.length - 1;
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        while (start < end){
            while (start < end && !set.contains(charArray[start])){
                start++;
            }
            while (start < end && !set.contains(charArray[end])){
                end--;
            }
            
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;  
        }
        return String.valueOf(charArray);
    }
}
