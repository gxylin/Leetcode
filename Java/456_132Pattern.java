Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

Method 1: Better Brute Force
Time complexity: O(n^2)
Space complexity: O(1)
class Solution {
    public boolean find132pattern(int[] nums) {
        int min_i = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length-1; j++){
            min_i = Math.min(min_i, nums[j]);
            for (int k = j + 1; k < nums.length; k++){
                if (nums[k] > min_i && nums[k] < nums[j]){
                    return true;
                }
            }
        }
        return false;
    }
}

https://leetcode.com/articles/132-pattern/
Method 2: Monotonic Decreasing Stack
To keep a track of these potential nums[k]nums[k] values for a particular nums[i],nums[j]nums[i],nums[j] considered currently, 
we maintain a stackstack on which these potential nums[k]nums[k]'s satisfying the 132 criteria lie in a descending
order(minimum element on the top).We need not sort these elements on the stackstack,
but they'll be sorted automatically as we'll discuss along with the process.

we keep on popping the elements from the top of the stackstack till we find an element, stack[top]stack[top] 
such that, stack[top] > min[j]stack[top]>min[j](or stack[top] > nums[i]stack[top]>nums[i]).

In summary:
Step 1: use min array to store the potential nums[i]
Step 2: use montonic array to store the potential nums[k]
Step 3: loop j backward to find nums[i] < nums[k] < nums[j]

class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3){
            return false;
        }
        int[] minArray = new int[nums.length];
        minArray[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            minArray[i] = Math.min(minArray[i-1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for (int j = nums.length - 1; j >= 0; j--){
            if (nums[j] > minArray[j]){
                while (!stack.isEmpty() && stack.peek() <= minArray[j]){//invariant: all the stack values are greater than minArray[j]
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[j]){
                    return true;
                }
                stack.push(nums[j]);
            }
        }
        return false;
    }
}
