Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output:  [1,2,4,7,5,3,6,8,9]
Explanation:


class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0){
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m*n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (!map.containsKey(i+j)){
                    map.put(i+j, new ArrayList<Integer>());
                }
                map.get(i+j).add(matrix[i][j]);
            }
        }
        int index = 0;
        for (int key : map.keySet()){
            List<Integer> temp = map.get(key);
            int size = temp.size();
            if (key % 2 == 0){
                for (int i = size - 1; i >= 0; i--){
                    res[index++] = map.get(key).get(i);
                }
            }else{
                for (int i = 0; i < size; i++){
                    res[index++] = map.get(key).get(i);
                }
            }
        }
        return res;
    }
}
