In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running 
total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a
total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, 
assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.

class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (sum < desiredTotal){
            return false;
        }
        if (desiredTotal <= 0){
            return true;
        }
        boolean[] used = new boolean[maxChoosableInteger+1];
        Map<Integer, Boolean> map = new HashMap<>();
        return dfs(map, used, desiredTotal);//return if the first play can win
    }
    private boolean dfs(Map<Integer, Boolean> map, boolean[] used, int desiredTotal){
        int key = format(used);
        if (map.containsKey(key)){
            return map.get(key);
        }
        if (desiredTotal <= 0){
            return false;
        }
        for (int i = 1; i < used.length; i++){
            if (!used[i]){
                used[i] = true;
                if (!dfs(map, used, desiredTotal - i)){//if the second player can't win
                    map.put(key, true);
                    used[i] = false; // need backtrack
                    return true;
                }
                used[i] = false;
            }
        }
        map.put(key, false);
        return false;
    }
    private int format(boolean[] used){
        int num = 0;
        for (int i = 0; i < used.length; i++){
            num <<= 1;
            if (used[i]){
                num |= 1;
            }
        }
        return num;
    }
}


Backtrack + meomo
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (sum < desiredTotal){
            return false;
        }
        if (desiredTotal <= 0){
            return true;
        }
        if (maxChoosableInteger >= desiredTotal){
            return true;
        }
        Map<String, Boolean> map = new HashMap<>();
        boolean[] visited = new boolean[maxChoosableInteger+1];
        int[] total = new int[1];
        return firstWin(maxChoosableInteger, visited, desiredTotal, total, map);    
    }
    private boolean firstWin(int max, boolean[] visited, int target, int[] total, Map<String, Boolean> map){
        String key = format(visited);
        if (map.containsKey(key)){
            return map.get(key);
        }
        if (total[0] >= target){
            return false;
        }
        for (int i = 1; i <= max; i++){
            if (visited[i]){
                continue;
            }
            visited[i] = true;
            total[0] += i;
            if (!firstWin(max, visited, target, total, map)){
                map.put(key, true);
                visited[i] = false; // need backtrack
                total[0] -= i; // need backtrack
                return true;
            }
            total[0] -= i;
            visited[i] = false;
        }
        map.put(key, false);
        return false;
    }
    private String format(boolean[] visited){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < visited.length; i++){
            if (visited[i]){
                sb.append(1);
            }else{
                sb.append(0);
            }
        }
        return sb.toString();
    }
}
