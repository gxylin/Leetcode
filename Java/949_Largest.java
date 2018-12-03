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

class Solution {
    int max = Integer.MIN_VALUE;
    public String largestTimeFromDigits(int[] A) {
        if (A == null || A.length == 0){
            return "";
        }
        boolean[] visited = new boolean[4];
        List<List<Integer>> resList = new ArrayList<>();
        backtrack(resList, A, new ArrayList<Integer>(), visited);
        if (max == Integer.MIN_VALUE){
            return "";
        }
        List<Integer> res = resList.get(resList.size() - 1);
        return res.get(0) + String.valueOf(res.get(1)) + ":" + String.valueOf(res.get(2)) + res.get(3);
    }
    private void backtrack(List<List<Integer>> res, int[] A, List<Integer> item, boolean[] visited){
        if (item.size() == A.length){
            int cand = item.get(0) * 1000 + item.get(1) * 100 + item.get(2) * 10 + item.get(3);
            if (item.get(0) * 10 + item.get(1) < 24 && item.get(2) * 10 + item.get(3) < 60 && cand > max){
                max = cand;
                res.add(new ArrayList<>(item));
            }
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (!visited[i]){
                visited[i] = true;
                item.add(A[i]);
                backtrack(res, A, item, visited);
                visited[i] = false;
                item.remove(item.size() - 1);
            }
        }
    }
}


Method 3: Permutation code
class Solution {
    
    public String largestTimeFromDigits(int[] A) {
        if (A == null || A.length == 0){
            return "";
        }
        boolean[] visited = new boolean[4];
        List<List<Integer>> resList = new ArrayList<>();
        permutation(resList, A, new ArrayList<Integer>(), visited);
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < resList.size(); i++){
            List<Integer> list = resList.get(i);
            int num = convert(list);
            if (num > max){
                max = num;
                index = i;
            }
        }
        if (index == -1 || max == Integer.MIN_VALUE){
            return "";
        }
        List<Integer> res = resList.get(index);
        return res.get(0) + String.valueOf(res.get(1)) + ":" + String.valueOf(res.get(2)) + res.get(3);
    }
    private void permutation(List<List<Integer>> res, int[] A, List<Integer> item, boolean[] visited){
        if (item.size() == A.length){
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (!visited[i]){
                visited[i] = true;
                item.add(A[i]);
                permutation(res, A, item, visited);
                visited[i] = false;
                item.remove(item.size() - 1);
            }
        }
    }
    private int convert(List<Integer> list){
        if (list.get(0) * 10 + list.get(1) < 24 && list.get(2) * 10 + list.get(3) < 60){
            return list.get(0) * 1000 + list.get(1) * 100 + list.get(2) * 10 + list.get(3);
        }
        return Integer.MIN_VALUE;
    }
}


Another solution Similar to Leetcode 681 Next Closest Time
class Solution {
    
    public String largestTimeFromDigits(int[] A) {
        if (A == null || A.length == 0){
            return "";
        }
        boolean[] visited = new boolean[4];
        List<List<Integer>> resList = new ArrayList<>();
        permutation(resList, A, new ArrayList<Integer>(), visited, 0);
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < resList.size(); i++){
            List<Integer> list = resList.get(i);
            int num = convert(list);
            if (num > max){
                max = num;
                index = i;
            }
        }
        if (index == -1 || max == Integer.MIN_VALUE){
            return "";
        }
        List<Integer> res = resList.get(index);
        return res.get(0) + String.valueOf(res.get(1)) + ":" + String.valueOf(res.get(2)) + res.get(3);
    }
    private void permutation(List<List<Integer>> res, int[] A, List<Integer> item, boolean[] visited, int pos){
        if (item.size() == A.length){
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < A.length; i++){
            if (pos == 0 && A[i] > 2){
                continue;
            }
            if (pos == 1 && item.get(0) * 10 + A[i] > 23){
                continue;
            }
            if (pos == 2 && A[i] > 5){
                continue;
            }
            if (pos == 3 && item.get(2) * 10 + A[i] > 59){
                continue;
            }
            if (!visited[i]){
                visited[i] = true;
                item.add(A[i]);
                permutation(res, A, item, visited, pos + 1);
                visited[i] = false;
                item.remove(item.size() - 1);
            }
        }
    }
    private int convert(List<Integer> list){
   //     if (list.get(0) * 10 + list.get(1) < 24 && list.get(2) * 10 + list.get(3) < 60){
            return list.get(0) * 1000 + list.get(1) * 100 + list.get(2) * 10 + list.get(3);
   //     }
   //     return Integer.MIN_VALUE;
    }
}
