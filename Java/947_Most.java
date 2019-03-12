On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?

 

Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0
 

Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000

Method: Uion Find

class Solution {
    class UF {
        int[] parent;
        int[] size;
        int count;
        public UF(int N){
            parent = new int[N];
            size = new int[N];
            count = N;
            for (int i = 0; i < N; i++){
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x){
            if (x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]); // path compression
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY){
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                count--;
            }
        }
        public int getSize(){
            return count;
        }
    }
    public int removeStones(int[][] stones) {
        int N = stones.length;
        UF uf = new UF(N);
        for(int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                int[] a = stones[i];
                int[] b = stones[j];
                if (a[0] == b[0] || a[1] == b[1]){
                    uf.union(i, j);
                }
            }
        }
        return N - uf.getSize();
    }
}
