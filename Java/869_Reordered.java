Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
 

Note:

1 <= N <= 10^9

Method 1: Brute Force
Time Complexity: O((logN)!∗logN). Note that logN is the number of digits in the binary representation of N.
For each of (logN)! permutations of the digits of N, we need to check that it is a power of 2 in O(logN) time.

Space Complexity: O(logN), the space used by A

class Solution {
    public boolean reorderedPowerOf2(int N) {
        String s = Integer.toString(N);
        int[] A = new int[s.length()];
        for (int i = 0; i < A.length; i++){
            A[i] = s.charAt(i) - '0';
        }
        return permutation(A, 0);
    }
    private boolean permutation(int[] A, int start){
        if (start == A.length){
            return isPowerOfTwo(A);
        }
        for (int i = start; i < A.length; i++){
            swap(A, start, i);
            if (permutation(A, start+1)){
                return true;
            }
            swap(A, start, i);
        }
        return false;
    }
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private boolean isPowerOfTwo(int[] A){
        if (A[0] == 0){
            return false;
        }
        int num = 0;
        for (int i = 0; i < A.length; i++){
            num = 10 * num + A[i];
        }
        int count = 0;
        while (num > 0){
            if ((num & 1) == 1){
                count++;
            }
            num >>= 1;
        }
        return count == 1;
    }
}

Method 2:
Note that the range of integer is between -2^31 and 2^31, which is 
Time Complexity: O(logn * logn) There are logN different candidate powers of 2, and each comparison hasO(logN) time complexity.
Space Complexity: O(logN). 

class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] hash = count(N);
        for (int i = 0; i < 31; i++){
            int[] arr = count((1 << i));
            if (Arrays.equals(arr, hash)){
                return true;
            }
        }
        return false;
    }
    private int[] count(int N){
        int[] res = new int[10];
        while (N > 0){
            res[N % 10]++;
            N /= 10;
        }
        return res;
    }
}
