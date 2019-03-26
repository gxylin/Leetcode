Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
Originally, the 2D matrix is all 0 which means there is only sea in the matrix. The list pair has k operator 
and each operator has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y] 
from sea to island. Return how many island are there in the matrix after each operator.

 Notice
0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island.
We only consider up/down/left/right adjacent.

Have you met this question in a real interview? 
Example
Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].

return [1,1,2,2].

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

Time complexity of Union Find with path compression: O(k + m*n) m*n -- union find inialization
Time complexity of BFS: O(k * m * n)
public class Solution {
    /*
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    class UF{
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
        public int find(int a){
            if (parent[a] == a){
                return a;
            }
            return parent[a] = find(parent[a]);
        }
        public void union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if (rootA != rootB){
                parent[rootA] = rootB;
                size[rootB] += size[rootA];
                count--;
            }
        }
        public int size(){
            return count;
        }
    }
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> result = new ArrayList<>();
        if (operators == null || operators.length == 0){
            return result;
        }
        UF uf = new UF(n*m);
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] island = new int[n][m];
        int count = 0;
        for (int i = 0; i < operators.length; i++){
            int x = operators[i].x;
            int y = operators[i].y;
            if (island[x][y] != 1){
                count++;
                island[x][y] = 1;
                for (int j = 0; j < dx.length; j++){
                    int cx = x + dx[j];
                    int cy = y + dy[j];
                    if (cx >= 0 && cx < n && cy >= 0 && cy < m && island[cx][cy] == 1){
                        int a = convert2dto1d(x, y, m);
                        int b = convert2dto1d(cx, cy, m);
                        if (uf.find(a) != uf.find(b)){
                            uf.union(a, b);
                            count--;
                        }
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
    private int convert2dto1d(int x, int y, int m){
        return x * m + y;
    }
}
