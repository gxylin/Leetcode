Given an array of 4 digits, return the largest 24 hour time that can be made.

The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.

Return the answer as a string of length 5.  If no valid time can be made, return an empty string.

 

Example 1:

Input: [1,2,3,4]
Output: "23:41"
Example 2:

Input: [5,5,5,5]
Output: ""
 

Note:

A.length == 4
0 <= A[i] <= 9


Method 1: backtracking

class Solution {
    int max = Integer.MIN_VALUE;
    List<Integer> result = new ArrayList<>();
    public String largestTimeFromDigits(int[] A) {
        if (A == null || A.length == 0){
            return "";
        }
        boolean[] visited = new boolean[4];
        List<Integer> res = new ArrayList<>();
        dfs(A, res, visited);
        if (max == Integer.MIN_VALUE){
            return "";
        }
        return String.valueOf(result.get(0)) + String.valueOf(result.get(1)) + ":" + String.valueOf(result.get(2)) + String.valueOf(result.get(3));
    }
    private void dfs(int[] A, List<Integer> res, boolean[] visited){
        if (res.size() == A.length){
            if (res.get(0) * 10 + res.get(1) <= 23 && res.get(2) * 10 + res.get(3) <= 59 && 
               res.get(0) * 1000 + res.get(1) * 100 + res.get(2) * 10 + res.get(3) > max){
                max = Math.max(max, res.get(0) * 1000 + res.get(1) * 100 + res.get(2) * 10 + res.get(3));
                result = new ArrayList<>(res);
            }
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (!visited[i]){
                visited[i] = true;
                res.add(A[i]);
                dfs(A, res, visited);
                visited[i] = false;
                res.remove(res.size() - 1);
            }
        }
    }
}
