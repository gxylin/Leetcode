Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.

Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].

 

Example 1:

Input: [1,17,8]
Output: 2
Explanation: 
[1,8,17] and [17,8,1] are the valid permutations.
Example 2:

Input: [2,2,2]
Output: 1
 

Note:

1 <= A.length <= 12
0 <= A[i] <= 1e9

class Solution {
    public int numSquarefulPerms(int[] A) {
        List<List<Long>> res = new ArrayList<>();
        long[] B = new long[A.length];
        for (int i = 0; i < A.length; i++){
            B[i] = (long)A[i];
        }
        boolean[] visited = new boolean[A.length];
        Arrays.sort(B);
        backtrack(res, new ArrayList<>(), B, visited);
        return res.size();
    }
    private void backtrack(List<List<Long>> res, List<Long> item, long[] A, boolean[] visited){
        if (item.size() == A.length){
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (i > 0 && A[i] == A[i-1] && !visited[i-1]){
                continue;
            }
            if (visited[i]){
                continue;
            }
            if (item.size() == 0){
                visited[i] = true;
                item.add(A[i]);
                backtrack(res, item, A, visited);
                item.remove(item.size() - 1);
                visited[i] = false;
            }else{
                long prev = item.get(item.size() - 1);
                long cand = A[i]  + prev ;
                if (isPerfectSquare(cand)){
                    visited[i] = true;
                    item.add(A[i]);
                    backtrack(res, item, A, visited);
                    item.remove(item.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
    private boolean isPerfectSquare(long num) {
        long start = 0;
        long end = num;
        long target = num;
        while (start <= end){
            long mid = start + (end - start) / 2;
            if (mid * mid == target){
                return true;
            }else if (mid * mid < target){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        
        return false;
    }
}

Shorter version isPerfectSquare
private boolean isPerfectSquare(long num) {
        long sqrt = (long)Math.sqrt(num);
        return sqrt * sqrt == num;
}

Method 2: use global variable
class Solution {
    int count = 0;
    public int numSquarefulPerms(int[] A) {
        boolean[] visited = new boolean[A.length];
        Arrays.sort(A);
        backtrack(0, A, -1, visited);
        return count;
    }
    private void backtrack(int total, int[] A, int lastNum, boolean[] visited){
        if (total == A.length){
            count++;
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (i > 0 && A[i] == A[i-1] && !visited[i-1]){
                continue;
            }
            if (visited[i]){
                continue;
            }
            if (lastNum == -1){
                visited[i] = true;
                backtrack(total+1, A, A[i], visited);
                visited[i] = false;
            }else{
                int cand = A[i]  + lastNum ;
                if (isPerfectSquare(cand)){
                    visited[i] = true;
                    backtrack(total+1, A, A[i], visited);
                    visited[i] = false;
                }
            }
        }
    }
    private boolean isPerfectSquare(int num) {
        int sqrt = (int)Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
