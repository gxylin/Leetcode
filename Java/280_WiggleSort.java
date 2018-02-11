 Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4]. 

Method 1:
Time complexity: O(nlogn)
Space complexity : O(1)O(1)O(1). Space depends on the sorting implementation which, usually, 
costs O(1)O(1)O(1) auxiliary space if heapsort is used.
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2){
            swap(nums, i, i+1);
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

Method 2:
As we iterate through the array, we compare the current element to its next element 
and if the order is incorrect, we swap them.
Time complexity : O(n)O(n)O(n). In the worst case we swap at most n/2 times. An example input is [2,1,3,1,4,1].
Space complexity : O(1)

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0){
            return;
        }
        boolean less = true;
        for (int i = 0; i < nums.length - 1; i++){
            if (less){
                if (nums[i] > nums[i+1]){
                    swap(nums, i, i+1);
                }
            }else{
                if (nums[i] < nums[i+1]){
                    swap(nums, i, i+1);
                }
            }
            less = !less;
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
