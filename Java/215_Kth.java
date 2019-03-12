Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct 
element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

Quick Select
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end){
            int index = partition(nums, start, end);
            if (index == k-1){
                return nums[index]; 
            }else if (index > k-1){
                end = index-1;
            }else{
                start = index+1;
            }
        }
        return nums[start];
    }
    private int partition(int[] nums, int start, int end){
        Random random = new Random();
        int index = start + random.nextInt(end - start + 1);
        int pivot = nums[index];
        swap(nums, index, start);
        int head = start;
        start++;
        while (start <= end){
            while (start <= end && nums[start] >= pivot){
                start++;
            }
            while (start <= end && nums[end] < pivot){
                end--;
            }
            if (start < end){
                swap(nums, start, end);
                start++;
                end--;
            }  
        }
        swap(nums, head, end);
        return end;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
