A zero-indexed array A of length N contains all integers from 0 to N-1. Find and return the longest length of set S, 
where S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } subjected to the rule below.

Suppose the first element in S starts with the selection of element A[i] of index = i, the next element in S should be A[A[i]],
and then A[A[A[i]]]… By that analogy, we stop adding right before a duplicate element occurs in S.

Example 1:
Input: A = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.

One of the longest S[K]:
S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
Note:
N is an integer within the range [1, 20,000].
The elements of A are all distinct.
Each element of A is an integer within the range [0, N-1].

Method 1: Brute force (TLE)
class Solution {
    public int arrayNesting(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            while (!set.contains(nums[i])){
                set.add(nums[i]);
                i = nums[i];
            }
            max = Math.max(max, set.size());
            set.clear();
        }
        
        return max;
    }
}

class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++){
            Set<Integer> set = new HashSet<>();
            int index = i;
            int count = 0;
            while (!set.contains(nums[index])){
                set.add(nums[index]);
                count++;
                index = nums[index];
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

class Solution {
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++){
            boolean[] visited = new boolean[n];
            int index = i;
            int count = 0;
            while (!visited[nums[index]]){
                visited[nums[index]] = true;
                count++;
                index = nums[index];
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

Method 2: 
class Solution {
    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] < 0){
                continue;
            }
            int count = 1;
            int val = nums[i];
            while (Math.abs(val) != i){
                count++;
                val = nums[Math.abs(val)];
                nums[Math.abs(val)] *= -1;
            }
            max = Math.max(max, count);
        }
        
        return max;
    }
}
