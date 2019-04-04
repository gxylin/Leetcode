On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible
for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

Method 1: BFS 9ms (Best solution)
https://leetcode.com/problems/sliding-puzzle/discuss/146652/Java-8ms-BFS-with-algorithm-explained
Consider each state in the board as a graph node, we just need to find out the min distance between start node and final target 
node "123450". Since it's a single point to single point questions, Dijkstra is not needed here. We can simply use BFS, and also 
count the level we passed. Every time we swap 0 position in the String to find the next state. Use a hashTable to store the visited 
states.
class Solution {
    public int slidingPuzzle(int[][] board) {
        int ans = 0;
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1,5}, {0, 4}, {1, 3, 5}, {2, 4}};
        String target = "123450";
        String source = "";
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                source += board[i][j];
            }
        }
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(source);
        seen.add(source);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String curr = queue.poll();
                if (curr.equals(target)){
                    return ans;
                }
                int index = curr.indexOf("0");
                for (int dir : dirs[index]){
                    String str = swap(curr, index, dir);
                    if (!seen.contains(str)){
                        queue.offer(str);
                        seen.add(str);
                    }
                }
            }
            ans++;
        }
        return -1;
    }
    
    private String swap(String curr, int i, int j){
        StringBuilder sb = new StringBuilder(curr);
        sb.setCharAt(i, curr.charAt(j));
        sb.setCharAt(j, curr.charAt(i));
        return sb.toString();
    }
}

Method 2: DFS
class Solution {
    int min = Integer.MAX_VALUE;
    public int slidingPuzzle(int[][] board) {
        String source = "";
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                source += board[i][j];
            }
        }
        String target = "123450";
        int[][] dirs = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        Map<String, Integer> map = new HashMap<>();
        dfs(target, source, dirs, 0, map);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private void dfs(String target, String source, int[][] dirs, int moves, Map<String, Integer> map){
        if (target.equals(source)){
            min = Math.min(min, moves);
            return;
        }
        if (map.containsKey(source)){
            if (moves > map.get(source)){
                return;
            }
        }
        map.put(source, moves);
        int index = source.indexOf("0");
        for (int i = 0 ; i < dirs[index].length; i++){
            String next = swap(source, dirs[index][i], index);
            dfs(target, next, dirs, moves + 1, map);
        }
    }
    private String swap (String str, int i , int j){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i, str.charAt(j));
        sb.setCharAt(j, str.charAt(i));
        return sb.toString();
    }
}


Method 3: backtracking 167ms
class Solution {
    int min = Integer.MAX_VALUE;
    public int slidingPuzzle(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int xStart = 0;
        int yStart = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(encode(board), 0);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 0){
                    xStart = i;
                    yStart = j;
                    break;
                }
            }
        }
        dfs(board, xStart, yStart, 0, map);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    private void dfs(int[][] board, int xStart, int yStart, int moves,  Map<Integer, Integer> map){
        int code = encode(board);
        if (code == 123450){
            min = Math.min(min, moves);
            return;
        }
        if (map.containsKey(code)){//way to prune duplicate moves
            if (moves > map.get(code)){
                return;
            }
        }
        map.put(code, moves);
        int m = board.length;
        int n = board[0].length;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < dx.length; i++){
            int x = xStart + dx[i];
            int y = yStart + dy[i];
            if (x >= 0 && x < m && y >= 0 && y < n){
                swap(board, x, y, xStart, yStart);
                dfs(board, x, y, moves + 1, map);
                swap(board, x, y, xStart, yStart);
            }
        }
    }
    private void swap(int[][] board, int x, int y, int xStart, int yStart){
        int temp = board[x][y];
        board[x][y] = board[xStart][yStart];
        board[xStart][yStart] = temp;
    }
    private int encode(int[][] board){
        int code = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                code = code * 10 + board[i][j];
            }
        }
        return code;
    }
}

