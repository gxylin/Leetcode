Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.

Example:

Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3

Hint: The number of elements in the given matrix will not exceed 10,000. 

class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0){
            return 0;
        }
        int m = M.length;
        int n = M[0].length;
        int max = 0;
        //horizontal
        for (int i = 0; i < m; i++){
            int count = 0;
            for (int j = 0; j < n; j++){
                if (M[i][j] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //vertical
        for (int i = 0; i < n; i++){
            int count = 0;
            for (int j = 0; j < m; j++){
                if (M[j][i] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //upper diagonal
        for (int j = 0; j < n; j++){
            int count = 0;
            for (int x = 0, y = j; x < m && y < n; x++, y++){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //lower diagonal
        for (int i = 0; i < m; i++){
            int count = 0;
            for (int x = i, y = 0; x < m && y < n; x++, y++){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //upper anti-diagonal
         for (int j = 0; j < n; j++){
            int count = 0;
            for (int x = 0, y = n-1-j; x < m && y >= 0; x++, y--){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //lower anti-diagonal
        for (int i = 0; i < m; i++){
            int count = 0;
            for (int x = i,  y = n-1; x < m && y >= 0; x++, y--){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        return max;
    }
}


class Solution {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0){
            return 0;
        }
        int m = M.length;
        int n = M[0].length;
        int max = 0;
        //horizontal
        for (int i = 0; i < m; i++){
            int count = 0;
            for (int j = 0; j < n; j++){
                if (M[i][j] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //vertical
        for (int i = 0; i < n; i++){
            int count = 0;
            for (int j = 0; j < m; j++){
                if (M[j][i] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //upper diagonal
        for (int j = 0; j < n; j++){
            int count = 0;
            for (int x = 0, y = j; x < m && y < n; x++, y++){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //lower diagonal
        for (int i = 0; i < m; i++){
            int count = 0;
            for (int x = i, y = 0; x < m && y < n; x++, y++){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //upper anti-diagonal
         for (int j = n-1; j >= 0 ; j--){
            int count = 0;
            for (int x = 0, y = j; x < m && y >= 0; x++, y--){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        //lower anti-diagonal
        for (int i = m-1; i >= 0; i--){
            int count = 0;
            for (int x = i, y = n-1; x < m && y >= 0; x++, y--){
                if (M[x][y] == 1){
                    count++;
                    max = Math.max(max, count);
                }else{
                    
                    count = 0;
                }
            }
        }
        return max;
    }
}
