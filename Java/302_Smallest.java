An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e.,
there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black
pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:

Input:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2

Output: 6


Method 1:
Time complexity: O(mn)
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int[] rowsum = new int[m];
        int[] colsum = new int[n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                rowsum[i] += (int)(image[i][j] - '0');
            }
        }
        for (int j = 0; j < n; j++){
            for (int i = 0; i < m; i++){
                colsum[j] += (int)(image[i][j] - '0');
            }
        }
        int top = 0;
        for (int i = 0; i < m; i++){
            if (rowsum[i] > 0){
                top = i;
                break;
            }
        }
        int bottom = 0;
        for (int i = m - 1; i >= 0; i--){
            if (rowsum[i] > 0){
                bottom = i;
                break;
            }
        }
        int left = 0;
        for (int j = 0; j < n; j++){
            if (colsum[j] > 0){
                left = j;
                break;
            }
        }
        int right = 0;
        for (int j = n - 1; j >= 0; j--){
            if (colsum[j] > 0){
                right = j;
                break;
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }
}

Method 2: binary search
Time complexity: O(mlogn or nlogm)
class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        int left = searchFirst(image, 0, y, true);
        int right = searchLast(image, y, n - 1, true);
        int top = searchFirst(image, 0, x, false);
        int bottom = searchLast(image, x, m - 1, false);
        return (right - left + 1) * (bottom - top + 1);
    }
    private int searchFirst(char[][] image, int start, int end, boolean checkVertical){
        while (start + 1 < end){
            int mid = (start + end) / 2;
            if (hasBlack(image, mid, checkVertical)){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (hasBlack(image, start, checkVertical)){
            return start;
        }
        return end;
    }
    private int searchLast(char[][] image, int start, int end, boolean checkVertical){
        while (start + 1 < end){
            int mid = (start + end) / 2;
            if (hasBlack(image, mid, checkVertical)){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (hasBlack(image, end, checkVertical)){
            return end;
        }
        return start;
    }
    private boolean hasBlack(char[][] image, int ind, boolean checkVertical){
        int m = image.length;
        int n = image[0].length;
        int sum = 0;
        if (checkVertical){
            for (int i = 0; i < m; i++){
                sum += (int)(image[i][ind] - '0');
            }
        }else{
            for (int j = 0; j < n; j++){
                sum += (int)(image[ind][j] - '0');
            }
        }
        return sum > 0;
    }
}
