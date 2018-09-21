We have an array A of non-negative integers.

For every (contiguous) subarray B = [A[i], A[i+1], ..., A[j]] (with i <= j), we take the bitwise OR of all the elements in B, obtaining a result A[i] | A[i+1] | ... | A[j].

Return the number of possible results.  (Results that occur more than once are only counted once in the final answer.)

 

Example 1:

Input: [0]
Output: 1
Explanation: 
There is only one possible result: 0.
Example 2:

Input: [1,1,2]
Output: 3
Explanation: 
The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1, 1, 2].
These yield the results 1, 1, 2, 1, 3, 3.
There are 3 unique values, so the answer is 3.
Example 3:

Input: [1,2,4]
Output: 6
Explanation: 
The possible results are 1, 2, 3, 4, 6, and 7.
 

Note:

1 <= A.length <= 50000
0 <= A[i] <= 10^9

Method 1: Brute Force (TLE)
Time complexity: O(N^3)
Space complexity: O(N)
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++){
            for (int j = i; j < A.length; j++){
                int res = 0;
                int k = i;
                while (k <= j){
                    res |= A[k];
                    k++;
                }
                set.add(res);
            }
        }
        return set.size();
    }
}

Method 2: Brute Force (TLE)
Time complexity: O(N^2)
Space complexity: O(N)
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < A.length; i++){
            int res = A[i];
            for (int j = i; j < A.length; j++){
                res |= A[j];
                set.add(res);
            }
        }
        return set.size();
    }
}


Method 3: 
Time complexity: O(30*N)
https://leetcode.com/problems/bitwise-ors-of-subarrays/discuss/165881/C++JavaPython-O(30N)
Assume B[i][j] = A[i] | A[i+1] | ... | A[j]
Hash set cur stores all wise B[0][i], B[1][i], B[2][i], B[i][i].

When we handle the A[i+1], we want to update cur
So we need operate bitwise OR on all elements in cur.
Also we need to add A[i+1] to cur.

In each turn, we add all elements in cur to res.

class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> curr = new HashSet<>();
        for (int i : A){
            Set<Integer> next = new HashSet<>(); //store all the res for B[0][i] .... B[i][i]
            next.add(i);
            for (int j : curr){
                next.add(i|j);
            }
            res.addAll(next);
            curr = next;
        }
        return res.size();
    }
}
