Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?
Related problem: Reverse Words in a String II

Method 1:
Space complexity: O(n)
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return;
        }
        int n = nums.length;
        k = k % n;
        int[] temp = new int[2*n];
        for (int i = 0; i < n; i++){
            temp[i] = nums[i];
            temp[i + n] = nums[i];
        }
        int i = 0;
        int index = n - k;
        while (i < n){
            nums[i] = temp[index];
            i++;
            index++;
        }
    }
}

Method 2: best solution
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0){
            return;
        }
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }
    private void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
