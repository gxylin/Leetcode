On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally
adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time.
 Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].

Method 1: BFS + priority queue
Time complexity: O(N^2 * logN) We may expand O(N^2) nodes, 
and each one requires O(logN)time to perform the heap operations.
Space complexity: O(N^2)

class Solution {
    class Pair {
        int x;
        int y;
        int height;
        public Pair (int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public int swimInWater(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.height - p2.height;
            }
        });
        int max = 0;
        Set<Integer> visited = new HashSet<>();
        pq.offer(new Pair(0, 0, grid[0][0]));
        visited.add(0);
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!pq.isEmpty()){
            Pair p = pq.poll();
            max = Math.max(max, p.height);
            if (p.x * n + p.y == (m-1) * n + n - 1){
                return max;
            }
            for (int i = 0; i < dx.length; i++){
                int x = p.x + dx[i];
                int y = p.y + dy[i];
                if (0 <= x && x < m && 0 <= y && y < n && !visited.contains(x * n + y)){
                    pq.offer(new Pair(x, y, grid[x][y]));
                    visited.add(x * n + y);
                }
            }
        }
        throw null;
    }
}

class Solution {
    class Pair {
        int x;
        int y;
        int height;
        public Pair (int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        Queue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.height - p2.height;
            }
        });
        
        pq.offer(new Pair(0, 0, grid[0][0]));
        boolean[][] inQueue = new boolean[N][N];
        inQueue[0][0] = true;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int max = 0;
        while (!pq.isEmpty()){
            Pair p = pq.poll();
            max = Math.max(max, p.height);
            if (p.x == N-1 && p.y == N-1 && max >= p.height){
                return max;
            }
            for (int[] dir : dirs){
                int nx = p.x + dir[0];
                int ny = p.y + dir[1];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !inQueue[nx][ny]){
                    pq.offer(new Pair(nx, ny, grid[nx][ny]));
                    inQueue[nx][ny] = true;
                }
            }
        }
        return max;
    }
}
Method 2: Binary Search + DFS
Intuition and Algorithm

Whether the swim is possible is a monotone function with respect to time, so we can binary search this function for the correct time: 
the smallest T for which the swim is possible.

Say we guess that the correct time is T. To check whether it is possible, we perform a simple depth-first search where we can only 
walk in squares that are at most T.



    Time Complexity: O(N2logN) Our depth-first search during a call to possible is O(N2) and we make up to O(logN) of them.

    Space Complexity: O(N2), the maximum size of the stack.
class Solution {
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        Stack<Integer> stack = new Stack();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N-1 && c == N-1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N
                        && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }
}
