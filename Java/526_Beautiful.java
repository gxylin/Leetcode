Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

Method: Backtracking
class Solution {
    public int countArrangement(int N) {
        List<List<Integer>> res = new ArrayList<>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = i+1; 
        }
        backtrack(res, new ArrayList<Integer>(), arr, 1);
        return res.size();
    }
    private void backtrack(List<List<Integer>> res, List<Integer> item, int[] arr, int pos){
        if (pos == arr.length + 1){
            if (item.size() == arr.length){
                res.add(new ArrayList<Integer>(item));
            }
            return;
        }
        for (int i = 0; i < arr.length; i++){
            if (item.contains(arr[i])){
                continue;
            }
            if (arr[i] % pos == 0 || pos % arr[i] == 0){
                item.add(arr[i]);
                backtrack(res, item, arr, pos+1);
                item.remove(item.size() - 1);
            }
        }
    }
}

Backtracking again

class Solution {
    int count = 0;
    public int countArrangement(int N) {
        backtrack(N, 1, new boolean[N+1]);
        return count;
    }
    private void backtrack(int N, int pos, boolean[] visited){
        if (pos == N + 1){
            count++;
            return;
        }
        for (int i = 1; i <= N; i++){
            if (!visited[i] && (i % pos == 0 || pos % i == 0)){
                visited[i] = true;
                backtrack(N, pos+1, visited);
                visited[i] = false;
            }
        }
    }
}
