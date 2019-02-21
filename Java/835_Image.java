Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)

We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.

(Note also that a translation does not include any kind of rotation.)

What is the largest possible overlap?

Example 1:

Input: A = [[1,1,0],
            [0,1,0],
            [0,1,0]]
       B = [[0,0,0],
            [0,1,1],
            [0,0,1]]
Output: 3
Explanation: We slide A to right by 1 unit and down by 1 unit.
Notes: 

1 <= A.length = A[0].length = B.length = B[0].length <= 30
0 <= A[i][j], B[i][j] <= 1

Method:
Time complexity: O(N^2)
Spapce complexity: O(N)

https://leetcode.com/problems/image-overlap/discuss/130623/C++JavaPython-Straight-Forward

note that in order to avoid collison, the factor must be greater than 2*n - 1.
avoid case :[0,1], [1, 1]
             [1, 1], [1, 0]
          
class Solution {
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        int factor = 2 * n;
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (A[i][j] == 1){
                    listA.add(i * factor + j);
                }
                if (B[i][j] == 1){
                    listB.add(i * factor + j);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : listA){
            for (int j : listB){
                map.put(i-j, map.getOrDefault(i-j, 0) + 1);
            }
        }
        int max = 0;
        for (int value : map.values()){
            max = Math.max(max, value);
        }
        return max;
    }
}
