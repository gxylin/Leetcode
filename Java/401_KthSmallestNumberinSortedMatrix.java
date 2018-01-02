Find the kth smallest number in at row and column sorted matrix.

Have you met this question in a real interview? Yes
Example
Given k = 4 and a matrix:

[
  [1 ,5 ,7],
  [3 ,7 ,8],
  [4 ,8 ,9],
]
return 5


    
public class Solution {
    /*
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    private class Pair{
        private int x;
        private int y;
        private int val;
        private Pair(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        Queue<Pair> minHeap = new PriorityQueue<Pair>(k, new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.val - b.val;
            }
        });
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
       
        minHeap.offer(new Pair(0, 0, matrix[0][0]));
        
        for (int i = 1; i < k; i++){
            Pair cur = minHeap.poll();
            for (int j = 0; j < 2; j++){
                int x = cur.x + dx[j];
                int y = cur.y + dy[j];
                if (x < m && y < n && !visited[x][y]){
                    minHeap.offer(new Pair(x, y, matrix[x][y]));
                    visited[x][y] = true;
                }
            }
        }
        return minHeap.poll().val;
    }
}
