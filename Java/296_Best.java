A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.

Method 1: 
Time complexity: O(mnk) k is the number of 1 in grid 
Space complexity: O(k)
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    list.add(temp);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                int d = dist(i, j, list);
                min = Math.min(min, d);
            }
        }
        return min;
    }
    private int dist(int i, int j, List<int[]> list){
        int sum = 0;
        for (int[] point : list){
            int x = point[0];
            int y = point[1];
            sum += Math.abs(x - i) + Math.abs(y - j);
        }
        return sum;
    }
}

Method 2: Sort
Note that the best meeting point must locate somewhere between the left-most and right-most point.
he median must be the optimal meeting point, not mean
Case #4: 1-1-0-0-1

To see why this is so, let us look at case #4 above and choose the median x=1x = 1x=1 as our initial meeting point.
Assume that the total distance traveled is d. Note that we have equal number of points distributed to its left and to its right. 
Now let us move one step to its right where x=2x = 2x=2 and notice how the distance changes accordingly.

Since there are two points to the left of x=2x = 2x=2, we add 2∗(+1)2 * (+1)2∗(+1) to d. And d is offset by –1 since 
there is one point to the right. This means the distance had overall increased by 1.

Time complexity: O(mnlog(mn))
Space complexity: O(mn)


class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        int row = rows.get(rows.size()/2); //no need to sort rows since it is already sorted
        Collections.sort(cols);
        int col = cols.get(cols.size()/2);
        return dist(rows, row) + dist(cols, col);
    }
    private int dist(List<Integer> list, int origin){
        int sum = 0;
        for (int p : list){
            sum += Math.abs(p - origin);
        }
        return sum;
    }
}

Method 3:replace sorting with directly collect data
Time complexity: O(mn)
Space complexity: O(mn)
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        int row = rows.get(rows.size()/2); //no need to sort rows since it is already sorted
        int col = cols.get(cols.size()/2);
        return dist(rows, row) + dist(cols, col);
    }
    private int dist(List<Integer> list, int origin){
        int sum = 0;
        for (int p : list){
            sum += Math.abs(p - origin);
        }
        return sum;
    }
    private List<Integer> collectRows(int[][] grid){
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    rows.add(i);
                }
            }
        }
        return rows;
    }
    private List<Integer> collectCols(int[][] grid){
        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < grid[0].length; j++){
            for (int i = 0; i < grid.length; i++){
                if (grid[i][j] == 1){
                    cols.add(j);
                }
            }
        }
        return cols;
    }
}

Method 4: calculate distance without knowing the median point
Time complexity: O(mn)
Space compleixty: O(mn)
class Solution {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return dist(rows) + dist(cols);
    }
    private int dist(List<Integer> list){
        int sum = 0;
        int i = 0;
        int j = list.size() - 1;
        while (i < j){
            sum += list.get(j) - list.get(i);
            i++;
            j--;
        }
        return sum;
    }
    private List<Integer> collectRows(int[][] grid){
        List<Integer> rows = new ArrayList<>();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == 1){
                    rows.add(i);
                }
            }
        }
        return rows;
    }
    private List<Integer> collectCols(int[][] grid){
        List<Integer> cols = new ArrayList<>();
        for (int j = 0; j < grid[0].length; j++){
            for (int i = 0; i < grid.length; i++){
                if (grid[i][j] == 1){
                    cols.add(j);
                }
            }
        }
        return cols;
    }
}
