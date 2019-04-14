Given an array A of integers, return the length of the longest arithmetic subsequence in A.

Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).

 

Example 1:

Input: [3,6,9,12]
Output: 4
Explanation: 
The whole array is an arithmetic sequence with steps of length = 3.
Example 2:

Input: [9,4,7,2,10]
Output: 3
Explanation: 
The longest arithmetic subsequence is [4,7,10].
Example 3:

Input: [20,1,15,3,10,5,8]
Output: 4
Explanation: 
The longest arithmetic subsequence is [20,15,10,5].
 

Note:

2 <= A.length <= 2000
0 <= A[i] <= 10000


Similar as Leetcode 446
https://github.com/optimisea/Leetcode/blob/master/Java/446_Arithmetic.java

class Solution {
    public int longestArithSeqLength(int[] A) {
        int res = 0;
        int n = A.length;
        Map<Integer, Integer>[] maps = new Map[A.length];
        for (int i = 0; i < n; i++){
            maps[i] = new HashMap<>();
            for (int j = 0; j < i; j++){
                long diff = (long)A[i] - A[j];
                if (diff >= Integer.MAX_VALUE || diff <= Integer.MIN_VALUE){
                    continue;
                }
                int d = (int)diff;
                int c1 = maps[j].getOrDefault(d, 1);//the count at ending with index j and difference of d
             //   int c2 = maps[i].getOrDefault(d, 0);// the count at ending with index i and difference between A[i] and A[j] is d
                res = Math.max(res, c1 + 1);
                maps[i].put(d, c1 + 1);
            }
        }
        return res;
    }
}
