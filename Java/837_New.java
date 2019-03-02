Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
Note:

0 <= K <= N <= 10000
1 <= W <= 10000
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
The judging time limit has been reduced for this question.

Method: DP + Sliding window
https://leetcode.com/problems/new-21-game/discuss/132334/One-Pass-DP-O(N)
 When the game ends, the point is between K and K-1+W
    What is the probability that the the point is less than N?
    - If N is greater than K-1+W, probability is 1
    - If N is less than K, probability is 0
    
    If W == 3 and we want to find the probability to get a 5
    - You can get a card with value 1, 2, or 3 with equal probability (1/3)
    - If you had a 4 and you get a 1: prob(4) * (1/3)
    - If you had a 3 and you get a 2: prob(3) * (1/3)
    - If you had a 2 and you get a 3: prob(2) * (1/3)
    - If you had a 1, you can never reach a 5 in the next draw
    - prob(5) = prob(4) / 3 + prob(3) / 3 + prob(2) /3
    
    To generalize:
    The probability to get point K is
    p(K) = p(K-1) / W + p(K-2) / W + p(K-3) / W + ... p(K-W) / W
    
    let wsum = p(K-1) + p(K-2) + ... + p(K-W)
    p(K) = wsum / W
    
class Solution {
    public double new21Game(int N, int K, int W) {
        if (K == 0){
            return 1.0;
        }
        double res = 0.0;
        double[] dp = new double[N+1];//the probability to get i point
        dp[0] = 1.0;
        double Wsum = 1.0;
        for (int i = 1; i <= N; i++){
            dp[i] = Wsum / W;
            if (i < K){
                Wsum += dp[i];
            }else{//in this case, no card draw
                res += dp[i];
            }
            if (i >= W){
                Wsum -= dp[i-W];
            }
        }
        return res;
    }
}
