In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the 
square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

 

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:

Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:

Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:

Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:

Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:

 

Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.


https://leetcode.com/problems/regions-cut-by-slashes/discuss/205674/C%2B%2B-with-picture-DFS-on-upscaled-grid/209661

Why transfer factor must be greater than 2?
 Consider ["//", "/ "]. If you only scale it 2x, it looks like this:
0 1 0 1
1 0 1 0
0 1 0 0
1 0 0 0

Notice the italicized zeroes - if you try to search for zeroes in that space, you'll end up with 3 separate islands. 
That's no good; we want it to count as one region. So, try scaling it 3x:

0 0 1 0 0 1
0 1 0 0 1 0
1 0 0 1 0 0
0 0 1 0 0 0
0 1 0 0 0 0
1 0 0 0 0 0

Now the zeroes identify as one island, not 3 - perfect!


Similar as Number of Island after transformation

class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int f = 3; //factor must be greater than 2
        int[][] matrix = new int[n*f][n*f];
        //build matrix
        for (int i = 0; i < n; i++){
            for (int j = 0 ; j < n; j++){
                int startX = i * f;
                int startY = j * f;
                if (grid[i].charAt(j) == '/'){
                    for (int k = 0; k < f; k++){
                        for (int m = 0; m < f; m++){
                            if (k + m == f - 1){
                                matrix[startX+k][startY+m] = 1;
                            }
                        }
                    }
                }else if (grid[i].charAt(j) == '\\'){
                    for (int k = 0; k < f; k++){
                        for (int m = 0; m < f; m++){
                            if (k == m){
                                matrix[startX+k][startY+m] = 1;
                            }
                        }
                    }
                }
            }
        }
        //BFS to find ZERO regions
        int res = 0;
        for (int i = 0; i < n * f; i++){
            for (int j = 0; j < n * f; j++){
                if (matrix[i][j] == 0){
                    bfs(matrix, i, j);
                    res++;
                }
            }
        }
        return res;
    }
    private void bfs(int[][] matrix, int x, int y){
        int n = matrix.length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.offer(new int[]{x, y});
        matrix[x][y] = 1;
        while (!queue.isEmpty()){
            int[] curr = queue.poll();
            for (int[] dir : dirs){
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && matrix[nx][ny] == 0){
                    queue.offer(new int[]{nx, ny});
                    matrix[nx][ny] = 1;
                }
            }
        }
    }
}
