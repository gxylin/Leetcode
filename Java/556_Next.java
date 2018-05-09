Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same
digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, 
you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1

The same as next permutation
class Solution {
    public int nextGreaterElement(int n) {
        //step 1: find the index of the first digit is smaller than previous one from rightmost digit
        String s = Integer.toString(n);
        char[] charArray = s.toCharArray();
        int index1 = 0;
        int i;
        for (i = s.length() - 2; i >= 0; i--){
            if (charArray[i] < charArray[i+1]){
                index1 = i;
                break;
            }
        }
        if (i < 0){
            return -1;
        }
        //step 2: find the first digit that is greater than the digit from the rightmost digit
        int index2 = 0;
        for (i = s.length() - 1; i >= 0; i--){
            if (charArray[i] > s.charAt(index1)){
                index2 = i;
                break;
            }
        }
        //step 3: swap index1 and index2
        char temp = charArray[index1];
        charArray[index1] = charArray[index2];
        charArray[index2] = temp;
        //step 4: reverse the order from index1 + 1 to rightmost
        int start = index1 + 1;
        int end = s.length() - 1;
        while (start < end){
            temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
        long ans =  Long.parseLong(String.valueOf(charArray));
        if (ans > Integer.MAX_VALUE){
            return -1;
        }
        return (int) ans;
    }
}
