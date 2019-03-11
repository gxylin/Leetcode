Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.

Return the least number of moves to make every value in A unique.

 

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 

Note:

0 <= A.length <= 40000
0 <= A[i] < 40000

Method:
Sort the array.
Compared with previous number,
the current number need to be at least prev + 1.
Time Complexity: O(NlogN)

class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0;
        for (int i = 0; i < A.length - 1; i++){
            int val = Math.max(A[i] + 1, A[i+1]);
            res += val - A[i+1];
            A[i+1] = val;
        }
        return res;
    }
}
class Solution {
    public int minIncrementForUnique(int[] A) {
        if (A.length <= 1){
            return 0;
        }
        Arrays.sort(A);
        int res = 0;
        int max = A[0]; // maintain max value before the curr
        for (int i = 1; i < A.length; i++){
            max = Math.max(max+1, A[i]);
            res += max - A[i];
            A[i] = max;
        }
        return res;
    }
}

Better version:
class Solution {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0;
        int need = 0;
        for (int a : A){
            res += Math.max(need - a, 0);
            need = Math.max(need, a) + 1;
        }
        return res;
    }
}

