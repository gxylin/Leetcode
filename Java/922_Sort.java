Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 

Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

Method 1:
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int odd = 1;
        int even = 0;
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++){
            if (A[i] % 2 == 0){
                res[even] = A[i];
                even += 2;
            }else{
                res[odd] = A[i];
                odd += 2;
            }
        }
        return res;
    }
}

Method 2: In-place
Find the odd index with even value, and the even index with odd value, swap them 
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int odd = 1;
        int even = 0;
        int n = A.length;
        while (odd < n && even < n){
            while (even < n && A[even] % 2 == 0){
                even += 2;
            }
            while (odd < n && A[odd] % 2 == 1){
                odd += 2;
            }
            if (odd < n && even < n){
                swap(A, odd, even);
                even += 2;
                odd += 2;
            }
        }
        return A;
    }
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
