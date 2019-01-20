A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.

 

Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1
 

Note:

1 <= A.length <= 40000
0 <= A[i] <= 10^9

Method 1:
Time complexity: O(N)
Space complexity: O(N)

class Solution {
    public int maxTurbulenceSize(int[] A) {
        int n = A.length;
        if (n == 1){
            return 1;
        }
        int[] dp = new int[n];
        dp[1] = A[0] != A[1] ? 2 : 1;
        int max = 1;
        for (int i = 2; i < n; i++){
            if (A[i-2] > A[i-1] && A[i-1] < A[i] || A[i-2] < A[i-1] && A[i-1] > A[i]){
                dp[i] = dp[i-1] + 1;
            }else{
                if (A[i] != A[i-1]){
                    dp[i] = 2;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}


https://leetcode.com/problems/longest-turbulent-subarray/discuss/221935/Java-O(N)-time-O(1)-space
https://leetcode.com/problems/longest-turbulent-subarray/discuss/221980/Simplest-O(n)-Java-solution

Method 2:
Time complexity: O(N)
Space complexity: O(1)
class Solution {
    public int maxTurbulenceSize(int[] A) {
        int n = A.length;
        if (n == 1){
            return 1;
        }
        int curr = 2;
        int max = 1;
        for (int i = 2; i < n; i++){
            if (A[i-2] > A[i-1] && A[i-1] < A[i] || A[i-2] < A[i-1] && A[i-1] > A[i]){
                curr += 1;
            }else{
                if (A[i] != A[i-1]){
                    curr = 2;
                }
            }
            max = Math.max(max, curr);
        }
        return max;
    }
}
