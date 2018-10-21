A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

 

Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.
 

Note:

1 <= S.length <= 20000
S only consists of '0' and '1' characters.


Time complexity: O(N)
Space complexity: O(N)
This is DP partition problem
The key idea to find the cut point where the left of the point will be 0 and the right of the point will be 1. So we can use DP to find the point which yields the minimum flip. This is a top down DP solution

cut pointt:   0   1    2   3    4    5   6   7
array:            1   0    0   1    1    0   1 
Note that if the length of the array is n, then there will be n+1 cut point. So dp length will be n+1

Step 1: initialization. the first cut point is at index 0. All the elements need to be 1. So the flip number should be equal to the number of 0

Step 2: Iteration. if the element is '1', it needs to be changed to '0', and the right side of the cut point are no changes. So dp[i] = dp[i-1] + 1; If the element is '0', it does not to be changed, however, this element has to be changed to '1' in previous cut point dp[i-1] because it is '0'. So compared to previouis step dp[i-1], The number of flip is reduced by 1. Hence, dp[i] = dp[i-1] - 1;

Step 3: Top down DP to find minimum

```
class Solution {
    public int minFlipsMonoIncr(String S) {
        int n = S.length();
        int[] dp = new int[n+1];
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < n; i++){
            if (S.charAt(i) == '0'){
                zeros++;
            }else{
                ones++;
            }
        }
        dp[0] = zeros; //all '0' need to be '1'
        for (int i = 1; i <= n; i++){
            if (S.charAt(i-1) == '1'){
                dp[i] = dp[i-1] + 1;
            }else{
                dp[i] = dp[i-1] - 1;
            }
        }
        int min = n;
        for (int i = 0; i <= n; i++){
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
```
