You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.

Method 1: brute force
Time complexity: O(n^2)
Space complexity: O(1)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++){
            boolean foundKey = false;
            boolean foundGreater = false;
            for (int j = 0; j < nums2.length; j++){
                if (!foundKey && nums2[j] == nums1[i]){
                    foundKey = true;
                    continue;
                }
                if (foundKey){
                    if (nums2[j] > nums1[i]){
                        result[i] = nums2[j];
                        foundGreater = true;
                        break;
                    }
                }
            }
            if (!foundGreater){
                result[i] = -1;
            }
        }
        return result;
    }
}
Method 2: monotonic increasing stack: top element is the smallest one
https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();//monotonic stack
        for (int num : nums2){
            while(!stack.isEmpty() && stack.peek() < num){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        for (int i = 0; i < nums1.length; i++){
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
