Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
Note: 1 <= N <= 10 ^ 9.

https://leetcode.com/problems/consecutive-numbers-sum/discuss/129015/5-lines-C++-solution-with-detailed-mathematical-explanation.
Time complexity: O(sqrt(n))
class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 1;
        for (int k = 2; k * k < 2*N; k++){
            if ((N - (k*(k-1)/2))%k == 0){
                count++;
            }
        }
        return count;
    }
}


https://leetcode.com/problems/consecutive-numbers-sum/discuss/209317/topic
class Solution {
    public int consecutiveNumbersSum(int N) {
        int count = 0;
        int m = 1; //number of items that can consecutively sum up to N
        while (true){
            int mx = N - m*(m-1)/2;
            if (mx <= 0){
                break;
            }else if (mx % m == 0){
                count++;
            }
            m++;
        }
        return count;
    }
}
