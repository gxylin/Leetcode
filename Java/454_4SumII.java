Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] 
is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:
Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0

Method 1:
Time complexity: O(n^2)
Space complexity: O(n^2)
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int ans = 0; 
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < C.length; i++){
            for (int j = 0; j < D.length; j++){
                map.put(C[i] + D[j], map.getOrDefault(C[i] + D[j], 0) + 1);
            }
        }
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B.length; j++){
                int target = -(A[i] + B[j]);
                if (map.containsKey(target)){
                    ans += map.get(target);
                }
            }
        }
        return ans;
    }
}


Method 2:
Time complexity: O(n^3) TLE
Space complexity: O(n)
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int ans = 0; 
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < D.length; i++){
            map.put(D[i], map.getOrDefault(D[i], 0) + 1);
        }
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B.length; j++){
                for (int k = 0; k < C.length; k++){
                    int target = -(A[i] + B[j] + C[k]);
                    if (map.containsKey(target)){
                        ans += map.get(target);
                    }
                }
            }
        }
        return ans;
    }
}

Method 3: 
Time complexity: O(n^3longn) TLE
Space complexity: O(1)
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int ans = 0; 
        Arrays.sort(D);
        for (int i = 0; i < A.length; i++){
            for (int j = 0; j < B.length; j++){
                for (int k = 0; k < C.length; k++){
                    int target = -(A[i] + B[j] + C[k]);
                    int len = binarySearch(D, target);
                    if(len != -1){
                        ans += len;
                    }
                }
            }
        }
        return ans;
    }
    private int binarySearch(int[] A, int target){
        int first = 0;
        int last = 0;
        
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] >= target){
                end = mid;
            }else if (A[mid] < target){
                start = mid;
            }
        }
        if (A[start] == target){
            first = start;
        }else if (A[end] == target){
            first = end;
        }else{
            return -1;
        }
        
        start = 0;
        end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] <= target){
                start = mid;
            }else if (A[mid] > target){
                end = mid;
            }
        }
        if (A[end] == target){
            last = end;
        }else if (A[start] == target){
            last = start;
        }else{
            return -1;
        }
        return last - first + 1;
    }
}
