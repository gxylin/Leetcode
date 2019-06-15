In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.

We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.

Return true if and only if it is possible to reach the target square through a sequence of moves.

 

Example 1:

Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
Output: false
Explanation: 
The target square is inaccessible starting from the source square, because we can't walk outside the grid.
Example 2:

Input: blocked = [], source = [0,0], target = [999999,999999]
Output: true
Explanation: 
Because there are no blocked cells, it's possible to reach the target square.
 

Note:

0 <= blocked.length <= 200
blocked[i].length == 2
0 <= blocked[i][j] < 10^6
source.length == target.length == 2
0 <= source[i][j], target[i][j] < 10^6
source != target


https://leetcode.com/problems/escape-a-large-maze/discuss/282870/python-solution-with-picture-show-my-thoughts

need optimization to limit the bfs steps
class Solution {
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return helper(blocked, source, target) && helper(blocked, target, source);
    }
    private boolean helper(int[][] blocked, int[] source, int[] target){
        if (blocked.length == 0){
            return true;
        }
        int maxStep = blocked.length;
        Set<String> set = new HashSet<>();
        for (int[] block : blocked){
            set.add(block[0] + ":" + block[1]);
        }
        Queue<int[]> queue = new LinkedList<>();
        int N = (int)Math.pow(10, 6);
        Set<String> seen = new HashSet<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{source[0], source[1]});
        seen.add(source[0] + ":" + source[1]);
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int[] curr = queue.poll();
                if (curr[0] == target[0] && curr[1] == target[1]){
                    return true;
                }
                for (int[] dir : dirs){
                    int nx = curr[0] + dir[0];
                    int ny = curr[1] + dir[1];
                    String key = nx + ":" + ny;
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && !seen.contains(key) && !set.contains(key)){
                        queue.offer(new int[]{nx, ny});
                        seen.add(key);
                    }
                }
            }
            step++;
            if (step >= maxStep){
                return true;
            }
        }
        return false;
    }
}
