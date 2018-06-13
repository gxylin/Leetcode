Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:

Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.

Note:

    The range of width and height of the input 2D array is [1,500].


class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture == null || picture.length == 0){
            return 0;
        }
        int count = 0;
        int m = picture.length;
        int n = picture[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (picture[i][j] == 'B'){
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (picture[i][j] == 'B' && rows[i] == 1 && cols[j] == 1){
                    count++;
                }
            }
        }
        return count;
    }
}
