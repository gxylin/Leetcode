You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents 
the tree's height.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with 
lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. 
If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:
Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
Example 2:
Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
Example 3:
Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
Hint: size of the given matrix will not exceed 50x50.


class Solution {
    class Pair{
        int x;
        int y;
        int height;
        public Pair(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0){
            return -1;
        }
        int ans = 0;
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.height - p2.height;
            }
        });
        int m = forest.size();
        int n = forest.get(0).size();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (forest.get(i).get(j) > 1){
                    pq.offer(new Pair(i, j, forest.get(i).get(j)));
                }
            }
        }
        int txPrev = 0;
        int tyPrev = 0;
        int step = 0;
        
        while (!pq.isEmpty()){
            Pair pair = pq.poll();
            int tx = pair.x;
            int ty = pair.y;
            if (tx == txPrev && ty == tyPrev){
                step = 0;
            }else{
                step = bfs(tx, ty, m, n, txPrev, tyPrev, forest);
            }
            
            if (step == -1){
                return -1;
            }
            ans += step;
            txPrev = tx;
            tyPrev = ty;
        }
        return ans;
    }
    private int bfs(int tx, int ty, int m, int n, int txPrev, int tyPrev, List<List<Integer>> forest){
        int step = 0;
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited = new boolean[m][n];
        visited[txPrev][tyPrev] = true;
        qx.offer(txPrev);
        qy.offer(tyPrev);
        
        while (!qx.isEmpty()){
            int size = qx.size();
            step++;
            for (int k = 0; k < size; k++){
                int cx = qx.poll();
                int cy = qy.poll();
                for (int i = 0; i < dx.length; i++){
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && forest.get(nx).get(ny) != 0){
                        if (nx == tx && ny == ty){
                            return step;
                        }
                        if (!visited[nx][ny]){
                            qx.offer(nx);
                            qy.offer(ny);
                            visited[nx][ny] = true;
                        }
                    }
                }
            } 
        }
        return -1;
    }
}
