Intuition

We ask the question: for each additional row, how many more rectangles are added?

For each pair of 1s in the new row (say at new_row[i] and new_row[j]), we could create more rectangles where that pair forms the base. 
The number of new rectangles is the number of times some previous row had row[i] = row[j] = 1.

Algorithm

Let's maintain a count count[i, j], the number of times we saw row[i] = row[j] = 1. When we process a new row, for every pair 
new_row[i] = new_row[j] = 1, we add count[i, j] to the answer, then we increment count[i, j].



    Time Complexity: O(R*C^2)O where R,CR, CR,C is the number of rows and columns.

    Space Complexity: O(C^2) in additional space.

class Solution {
    public int countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int[] row : grid){
            for (int c1 = 0; c1 < row.length - 1; c1++){
                if (row[c1] == 1){
                     for (int c2 = c1 + 1; c2 < row.length; c2++){
                         if (row[c2] == 1){
                             int pos = c1 * row.length + c2;
                             int count = map.getOrDefault(pos, 0);
                             ans += count;
                             map.put(pos, count+1);
                         }
                    }
                }
            }
        }
        return ans;
    }
}
