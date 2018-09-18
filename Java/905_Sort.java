Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.

You may return any answer array that satisfies this condition.

 

Example 1:

Input: [3,1,2,4]
Output: [2,4,3,1]
The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 

Note:

1 <= A.length <= 5000
0 <= A[i] <= 5000

Method 1:
Time complexity: O(N)
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int N = A.length;
        int[] res = new int[N];
        int l = 0;
        int r = A.length - 1;
        for (int i = 0; i < A.length; i++){
            if (A[i] % 2 == 0){
                res[l++] = A[i];
            }else{
                res[r--] = A[i];
            }
        }
        return res;
    }
}

Method 2:
In place swap: O(N)
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int l = 0;
        int r = A.length - 1;
        while (l < r){
            if (A[l] % 2 == 0){
                l++;
            }else{
                int temp = A[l];
                A[l] = A[r];
                A[r] = temp;
                r--;
            }
        }
        return A;
    }
}
