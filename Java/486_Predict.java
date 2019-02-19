Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

    
    Compare to 464, this one has to use dp (recursion + memo) because number is chosen following some order
https://leetcode.com/problems/predict-the-winner/discuss/96838/Java-'1-Line'-Recursive-Solution-O(n2)-Time-and-O(n)-Space
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] cache = new int[n][n];
        for (int i = 0; i < n; i++){
            Arrays.fill(cache[i], -1);
        }
        return maxRelativeScore(nums, 0, n-1, cache) >= 0;
    }
    private int maxRelativeScore(int[] nums, int start, int end, int[][] cache){
        if (cache[start][end] != -1){
            return cache[start][end];
        }
        if (start == end){
            return nums[start];
        }
        int chooseFront = nums[start] - maxRelativeScore(nums, start+1, end, cache);
        int chooseEnd = nums[end] - maxRelativeScore(nums, start, end-1, cache);
        cache[start][end] = Math.max(chooseFront, chooseEnd);
        return cache[start][end];
    }
}

Method 1:
Time complexity: O(2^n)
Space complexity: O(n)
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }
    public int winner(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + winner(nums, s + 1, e, -turn);
        int b = turn * nums[e] + winner(nums, s, e - 1, -turn);
        return turn * Math.max(turn * a, turn * b);
    }
}

Method 2:
Time complexity: O(n^2)
Space complexity: O(n^2)
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        Integer[][] memo = new Integer[nums.length][nums.length];
        return winner(nums, 0, nums.length - 1, memo) >= 0;
    }
    public int winner(int[] nums, int s, int e, Integer[][] memo) {
        if (s == e)
            return nums[s];
        if (memo[s][e] != null)
            return memo[s][e];
        int a = nums[s] - winner(nums, s + 1, e, memo);
        int b = nums[e] - winner(nums, s, e - 1, memo);
        memo[s][e] = Math.max(a, b);
        return memo[s][e];
    }
}

Method 3:
Time complexity: O(n^2)
Space complexity: O(n^2)
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] >= 0;
    }
}

Method 4:
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--){
            for (int j = i + 1; j < n; j++){
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j-1]);
            }
        }
        return dp[n-1] >= 0;
    }
}

The idea behind the recursive approach is simple. The two players Player 1 and Player 2 will be taking turns alternately.
For the Player 1 to be the winner, we need scorePlayer_1≥scorePlayer_2. Or in other terms, scorePlayer_1−scorePlayer_2≥0

.

Thus, for the turn of Player 1, we can add its score obtained to the total score and for Player 2's turn, we can substract its 
score from the total score. At the end, we can check if the total score is greater than or equal to zero(equal score of both players), 
to predict that Player 1 will be the winner.

Thus, by making use of a recursive function winner(nums,s,e,turn) which predicts the winner for the numsnumsnums array as the 
score array with the elements in the range of indices [s,e][s,e][s,e] currently being considered, given a particular player's turn, 
indicated by turn=1turn=1turn=1 being Player 1's turn and turn=−1turn=-1turn=−1 being the Player 2's turn, we can predict the winner of
the given problem by making the function call winner(nums,0,n-1,1). Here, nnn refers to the length of numsnumsnums array.

In every turn, we can either pick up the first(nums[s]nums[s]nums[s]) or the last(nums[e]nums[e]nums[e]) element of the current
subarray. Since both the players are assumed to be playing smartly and making the best move at every step, both will tend to maximize 
their scores. Thus, we can make use of the same function winner to determine the maximum score possible for any of the players.

Now, at every step of the recursive process, we determine the maximum score possible for the current player. It will be the maximum 
one possible out of the scores obtained by picking the first or the last element of the current subarray.

To obtain the score possible from the remaining subarray, we can again make use of the same winner function and add the score 
corresponding to the point picked in the current function call. But, we need to take care of whether to add or subtract this score 
to the total score available. If it is Player 1's turn, we add the current number's score to the total score, otherwise, we need to 
subtract the same.

Thus, at every step, we need update the search space appropriately based on the element chosen and also invert the turnturnturn's 
value to indicate the turn change among the players and either add or subtract the current player's score from the total score available to determine the end result.

Further, note that the value returned at every step is given by turn∗max(turn∗a,turn∗b)turn *\text{max}(turn * a, turn * b)
turn∗max(turn∗a,turn∗b). This is equivalent to the statement max(a,b)max(a,b)max(a,b) for Player 1's turn and min(a,b)min(a,b)min(a,b) 
for Player 2's turn.

This is done because, looking from Player 1's perspective, for any move made by Player 1, it tends to leave the remaining subarray 
in a situation which minimizes the best score possible for Player 2, even if it plays in the best possible manner. But, when the turn
passes to Player 1 again, for Player 1 to win, the remaining subarray should be left in a state such that the score obtained from this
subarrray is maximum(for Player 1).

This is a general criteria for any arbitrary two player game and is commonly known as the Min-Max algorithm.

The following image shows how the scores are passed to determine the end result for a simple example.
