

Given two Sparse Matrix A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.
Have you met this question in a real interview?
Example

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |

Method 1: O(nmt)
public class Solution {
    /*
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int t = A[0].length;
        int m = B[0].length;
        int[][] result = new int[n][m];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                for (int k = 0; k < t; k++){
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}

Method 2: O(small percent of mnt)
public class Solution {
    /*
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int t = A[0].length;
        int m = B[0].length;
        int[][] result = new int[n][m];
        
        for (int i = 0; i < n; i++){
            for (int k = 0; k < t; k++){
                if (A[i][k] == 0){
                    continue;
                }
                for (int j = 0; j < m; j++){
                    if (B[k][j] == 0){
                        continue;
                    }
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }
}

Method 3: transfer sparse matrix to 链表 ArrayList
public class Solution {
    /*
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int t = A[0].length;
        int m = B[0].length;
        int[][] result = new int[n][m];
        //pre-process to get the sparse items
        List<List<Integer>> col = new ArrayList<>();
        List<List<Integer>> val = new ArrayList<>();
        for (int k = 0 ; k < t; k++){
            col.add(new ArrayList<Integer>());
            val.add(new ArrayList<Integer>());
            for (int j = 0; j < m; j++){
                if (B[k][j] != 0){
                    col.get(k).add(j);
                    val.get(k).add(B[k][j]);
                }
            }
        }
        
        for (int i = 0; i < n; i++){
            for (int k = 0; k < t; k++){
                if (A[i][k] != 0){
                    for (int p = 0; p < val.get(k).size(); p++){
                        int j = col.get(k).get(p);
                        int v = val.get(k).get(p);
                        result[i][j] += A[i][k] * v;
                    }
                }
            }
        }
        return result;
    }
}

