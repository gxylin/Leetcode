ind K-th largest element in an array.

 Notice
You can swap elements in the array

Have you met this question in a real interview? Yes
Example
In array [9,3,2,4,8], the 3rd largest element is 4.

In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

Challenge 
O(n) time, O(1) extra memory.

class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high){
            int index = partition(nums, low, high);
            if (index == k - 1){
                return nums[k - 1];
            }else if (index > k - 1){
                high = index - 1;
            }else{
                low = index + 1;
            }
        }
        return -1;
    }
    private int partition(int[] A, int start, int end){
        int head = start;
        int pivot = A[start];
        start++;
        while (start <= end){
            while (start <= end && A[start] > pivot){
                start++;
            }
            while (start <= end && A[end] < pivot){
                end--;
            }
            if (start <= end){
                swap(A, start, end);
                start++;
                end--;
            }
        }
        swap(A, head, end);
        return end;
    }
    private void swap(int[] A, int a, int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
};
