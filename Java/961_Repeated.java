In a array A of size 2N, there are N+1 unique elements, and exactly one of these elements is repeated N times.

Return the element repeated N times.

 

Example 1:

Input: [1,2,3,3]
Output: 3
Example 2:

Input: [2,1,2,5,3,2]
Output: 2
Example 3:

Input: [5,1,5,2,5,3,5,4]
Output: 5
 

Note:

4 <= A.length <= 10000
0 <= A[i] < 10000
A.length is even

Method 1:
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    public int repeatedNTimes(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++){
            if (!set.add(A[i])){
                return A[i];
            }
        }
        return -1;
    }
}

Method 2:
Time complexity: O(N)
Space complexity: O(1)
https://leetcode.com/problems/n-repeated-element-in-size-2n-array/discuss/208317/C++-2-lines-O(4)-or-O-(1)


The intuition here is that the repeated numbers have to appear either next to each other (A[i] and A[i + 1]), or
alternated (A[i] and A[i + 2]).

The only exception is sequences like [2, 1, 3, 2]. In this case, the result is the last number, so we just return it in the end.

class Solution {
    public int repeatedNTimes(int[] A) {
        int n = A.length;
        if (A[0] == A[n-1]){
            return A[0];
        }
        for (int i = 0; i < n-2; i++){
            if (A[i] == A[i+1] || A[i] == A[i+2]){
                return A[i];
            }
        }
        return A[n-1];
    }
}

