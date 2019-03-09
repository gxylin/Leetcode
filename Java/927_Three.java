Given an array A of 0s and 1s, divide the array into 3 non-empty parts such that all of these parts represent the same binary value.

If it is possible, return any [i, j] with i+1 < j, such that:

A[0], A[1], ..., A[i] is the first part;
A[i+1], A[i+2], ..., A[j-1] is the second part, and
A[j], A[j+1], ..., A[A.length - 1] is the third part.
All three parts have equal binary value.
If it is not possible, return [-1, -1].

Note that the entire part is used when considering what binary value it represents.  For example, [1,1,0] represents 6 in decimal, 
not 3.  Also, leading zeros are allowed, so [0,1,1] and [1,1] represent the same value.

 

Example 1:

Input: [1,0,1,0,1]
Output: [0,3]
Example 2:

Input: [1,1,0,1,1]
Output: [-1,-1]
 

Note:

3 <= A.length <= 30000
A[i] == 0 or A[i] == 1
 
 
 class Solution {
    public int[] threeEqualParts(int[] A) {
        int n = A.length;
        int ones = 0;
        for (int i : A){
            if (i == 1){
                ones++;
            }
        }
        if (ones % 3 != 0){
            return new int[]{-1, -1};
        }
        //corner case: all zeros
        if (ones == 0){
            return new int[]{0, n-1};
        }
        int targetOnes = ones / 3;
        //construct the target string from right most
        StringBuilder sb = new StringBuilder();
        int j = n - 1;
        int rightOnes = 0;
        while (j >= 0 && rightOnes < targetOnes){
            sb.append(A[j]);
            if (A[j] == 1){
                rightOnes++;
            }
            j--;
        }
        String target = sb.reverse().toString();
        //skip leading zeros at left
        int i = 0;
        while (i < n && A[i] == 0){
            i++;
        }
        //match target string at left
        int k = 0;
        while (k < target.length()){
            if (A[i+k] == target.charAt(k) - '0'){
                k++;
            }else{
                return new int[]{-1, -1};
            }
        }
        int left = i + k - 1;
        
        //skip leading zeros at middle
        i = i + k;
        while (i < n && A[i] == 0){
            i++;
        }
        //match target string at middle
        k = 0;
        while (k < target.length()){
            if (A[i+k] == target.charAt(k) - '0'){
                k++;
            }else{
                return new int[]{-1, -1};
            }
        }
        return new int[]{left, i + k};
    }
}
