Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

 

Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 

Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000

Method 1: Brute Force (TLE)
Time complexity: O(N^2)
Space complexity: O(1)
class Solution {
    public int sumSubarrayMins(int[] A) {
        int mod = (int)1e9 + 7;
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n; i++){
            int min = A[i];
            for (int j = i; j < n; j++){
                min = Math.min(min, A[j]);
                res = (res + min) % mod;
            }
        }
        return res;
    }
}

Method 2: DP + Monotonic stack (store index but value in ascending)
Time complexity: O(N)
Space complexity: O(N)
https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170769/Java-O(n)-monotone-stack-with-DP

class Solution {
    public int sumSubarrayMins(int[] A) {
        int mod = (int)1e9 + 7;
        int n = A.length;
        int res = 0;
        Stack<Integer> stack = new Stack<>();//monotonic stack and  store index, but the value of index in ascending
        int[] dp = new int[n+1]; //dp[i] indicates the sum of subarray minimum ending at i-1
        stack.push(0); //dummy index is set to 0
        for (int i = 1; i <= n; i++){
            while (stack.peek() != 0 && A[stack.peek()-1] >= A[i-1]){
                stack.pop();
            }
            dp[i] = (dp[stack.peek()] + (i - stack.peek()) * A[i-1]) % mod;
            stack.push(i);//stack stores the index + 1
            res = (res + dp[i]) % mod;
        }
        return res;
    }
}


Monotonic Stack Template
https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

Method 3: Monotonic Stack
Best solution
class Solution {
    public int sumSubarrayMins(int[] A) {
        int mod = (int)1e9 + 7;
        int n = A.length;
        // left is for the distance to previous less element
        // right is for the distance to next less element
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i < A.length; i++) {
            left[i] = i + 1;
            right[i] = n - i;
        }
        Stack<Integer> stack = new Stack<>();
        // previous less element
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && A[stack.peek()] > A[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
         //next less element
        stack = new Stack<>();
        for (int i = 0; i < n; i++){
            while (!stack.isEmpty() && A[stack.peek()] > A[i]){
                int index = stack.pop();
                right[index] = i - index;
            }
            stack.push(i);
        }
        int res = 0;
        for (int i = 0; i < n; i++){
            res = (res + A[i] * left[i] * right[i]) % mod;
        }
        return res;
    }
}
