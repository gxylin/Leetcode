Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?

Method 1:
Time complexity: O(k^2)
Space complexity: O(1)
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0){
            return result;
        }
        for (int i = 0; i <= rowIndex; i++){
            result.add(1);
            for (int j = i - 1; j > 0; j--){
                result.set(j, result.get(j) + result.get(j-1));
            }
        }
        return result;
    }
}

Method 2:
Time complexity: O(k^2)
Space complexity: O(2*k);
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        int[][] f = new int[2][rowIndex+1];
        f[0][0] = 1;
        for (int i = 1; i <= rowIndex ; i++){
            for (int j = 0; j <= rowIndex; j++){
                if (j == 0 || j == rowIndex){
                    f[i%2][j] = 1;
                }else{
                    f[i%2][j] = f[(i-1)%2][j-1] + f[(i-1)%2][j];
                }
                
            }
        }
        for (int i = 0; i <= rowIndex; i++){
            result.add(f[rowIndex%2][i]);
        }
        return result;
    }
}
