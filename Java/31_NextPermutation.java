Solution:
Step1, from right to left, find the first number which not increase in a ascending order. In this case which is 3.
Step2, here we can have two situations:

We cannot find the number, all the numbers increasing in a ascending order. This means this permutation is the 
last permutation, we need to rotate back to the first permutation. So we reverse the whole array, for example, 
6,5,4,3,2,1 we turn it to 1,2,3,4,5,6.

We can find the number, then the next step, we will start from right most to leftward, try to find the first 
number which is larger than 3, in this case it is 4.
Then we swap 3 and 4, the list turn to 2,4,6,5,3,1.
Last, we reverse numbers on the right of 4, we finally get 2,4,1,3,5,6.

Time complexity is: O(3*n)=O(n).

class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        //step 1: find the first number that breaks ascending order from right most
        int left = 0;
        int i;
        boolean lowest = true;
        for (i = len - 2; i >= 0; i--){
            if (nums[i] < nums[i+1]){
                left = i;
                lowest = false;
                break;
            }
        }
        if (lowest){
            reverse(nums, 0, len - 1);
            return;
        }
        //step 2: find the smallest number that is greater than the number in step 1
        int right = left;
        int min = Integer.MAX_VALUE;
        for (i = left + 1; i < len; i++){
            if (nums[i] > nums[left] && nums[i] <= min){ // need use to "=" to ensure the smallest value goes to the right most
                right = i;
                min = nums[i];
            }
        }
        swap(nums, left, right);
        //step 3: reverse nums starting from the index of the number in step 1
        reverse(nums, left + 1, len - 1);
    }
    private void reverse(int[] A, int start, int end){
        while (start < end){
            swap(A, start, end);
            start++;
            end--;
        }
    }
    private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}


Better version:
class Solution {
    public void nextPermutation(int[] nums) {
        //three steps:
        //step 1: find the first non-ascending element from right to left
        if (nums == null || nums.length == 0){
            return;
        }
        int n = nums.length;
        int index = n;
        for (int i = n - 1; i > 0; i--){
            if (nums[i-1] < nums[i]){
                index = i-1;
                break;
            }
        }
        if (index == n){//reverse the nums
            reverse(nums, 0, n-1);
            return;
        }
        int pivot = nums[index];
        //step 2: find the first element that is greater than pivot from right to left
        for (int i = n - 1; i > index; i--){
            if (nums[i] > pivot){
                swap(nums, index, i);
                break;
            }
        }
        //step 3: reverse the elements from index + 1 to right
        reverse(nums, index+1, n-1);
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
    private void swap(int[] nums, int start, int end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
