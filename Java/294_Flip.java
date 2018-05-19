You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move
and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

Example:

Input: s = "++++"
Output: true 
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

Method 1: Inspired by Flip Game I
Time complexity: O(N^N)
T(N) = (N-2) * T(N-2) = (N-2) * (N-4) * T(N-4) ... = (N-2) * (N-4) * (N-6) * ... ~ O(N!!)
At first glance, backtracking seems to be the only feasible solution to this problem. We can basically try every possible
move for the first player (Let's call him 1P from now on), and recursively check if the second player 2P has any chance to win. 
If 2P is guaranteed to lose, then we know the current move 1P takes must be the winning move. The naive implementation is actually 
very simple:

Time: 349 ms
class Solution {
    public boolean canWin(String s) {
        List<String> result = new ArrayList<>();
        // generate all possible sequence after every attempt, inspired by Flip game I
        for (int i = 0; i < s.length() - 1; i++){
            StringBuilder sb = new StringBuilder();
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                sb.append(s.substring(0, i));
                sb.append("--");
                sb.append(s.substring(i+2));
                result.add(sb.toString());
            }
        }
        for (String str : result){
            if (!canWin(str)){ // if there is any one way the next player can't win, take it and you'll win
                return true;
            }
        }
        return false;
    }
}

Method 2: with memorization
time: 34ms
class Solution {
    Map<String, Boolean> map = new HashMap<>();
    public boolean canWin(String s) {
        List<String> result = new ArrayList<>();
        // generate all possible sequence after every attempt, inspired by Flip game I
        for (int i = 0; i < s.length() - 1; i++){
            StringBuilder sb = new StringBuilder();
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+'){
                sb.append(s.substring(0, i));
                sb.append("--");
                sb.append(s.substring(i+2));
                String temp = sb.toString();
                result.add(temp);
                if (map.containsKey(temp)){
                    return true;
                }
            }
        }
        for (String str : result){
            if (!canWin(str)){ // if there is any one way the next player can't win, take it and you'll win
                map.put(str, true);
                return true;
            }
        }
        return false;
    }
}
