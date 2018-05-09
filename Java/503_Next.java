Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element. The Next Greater Number of a number x is the first 
greater number to its traversing-order next in the array, which means you could search circularly to 
find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.

Method 1: similar as next grater element I, use stack, two different points
1. Use stack to store index as key instead of value due to duplicates allowed
2. Repeat array to implement cirular array

Time complexity: O(2*n)
Space complexity: O(2*n)
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();//store index and result
        Stack<Integer> stack = new Stack<>();
        for (int k = 0; k < 2; k++){
            for (int i = 0; i < nums.length; i++){
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                    map.put(stack.pop(), nums[i]);
                }
                stack.push(i);
            }
        }
        for (int i = 0; i < nums.length; i++){
            result[i] = map.getOrDefault(i, -1);
        }
        return result;
    }
}

Method 2: stack only, no map
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        for (int k = 0; k < 2; k++){
            for (int i = 0; i < nums.length; i++){
                while (!stack.isEmpty() && nums[stack.peek()] < nums[i]){
                    result[stack.pop()] = nums[i];
                }
                stack.push(i);
            }
        }
        return result;
    }
}

Method 3: use rolling array + stack
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < 2 * n; i++){
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]){
                result[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return result;
    }
}
