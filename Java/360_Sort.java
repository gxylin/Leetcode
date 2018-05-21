 Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example:

nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

Result: [3, 9, 15, 33]

nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

Result: [-23, -5, 1, 7]


class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0;
        int right = n - 1;
        int index = a >= 0 ? n - 1: 0;
        while (left <= right){
            int leftResult = calculate(nums[left], a, b, c);
            int rightResult = calculate(nums[right], a, b, c);
            if (a >= 0){
                if (leftResult >= rightResult){
                    result[index--] = leftResult;
                    left++;
                }else{
                    result[index--] = rightResult;
                    right--;
                }
            }else{
                if (leftResult >= rightResult){
                    result[index++] = rightResult;
                    right--;
                }else{
                    result[index++] = leftResult;
                    left++;
                }
            }          
        }
        return result;
        
    }
    private int calculate(int num, int a, int b, int c){
        return a*num*num + b*num + c;
    }
}
