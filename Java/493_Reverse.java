Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.


Merge sort idea
The similar as Count of Smaller number after self
class Solution {
    int count = 0;
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, temp, 0, nums.length - 1);
        return count;
    }
    private void mergeSort(int[] nums, int[] temp, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(nums, temp, start, mid);
        mergeSort(nums, temp, mid + 1, end);
        merge(nums, temp, start, mid, end);
    }
    private void merge(int[] nums, int[] temp, int start, int mid, int end){
        int left = start;
        int right = mid + 1;
        int index = start;
        
        //this is count which is the only part different from merge sort
        int subCount = 0;
        int l = left;
        int r = right;
        while (l <= mid){
            if (r > end || (long) nums[l] <= 2 * (long) nums[r]){
                count += subCount;
                l++;
            }else{
                subCount++;
                r++;
            }
        }
        
        ///////////////////////
        while (left <= mid && right <= end){
            if (nums[left] < nums[right]){ 
                temp[index++] = nums[left++];
            }else {
                temp[index++] = nums[right++];
            }
        }
        while (left <= mid){
            temp[index++] = nums[left++];
        }
        while (right <= end){
            temp[index++] = nums[right++];
        }
        for (int i = start; i <= end; i++){
            nums[i] = temp[i];
        }
    }
}
