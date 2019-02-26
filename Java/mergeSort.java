Merge Sort Template

493. Reverse Pairs
https://github.com/optimisea/Leetcode/blob/master/Java/493_Reverse.java

315. Count of Smaller Numbers After Self
https://github.com/optimisea/Leetcode/blob/master/Java/315_Count.java



Template
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
