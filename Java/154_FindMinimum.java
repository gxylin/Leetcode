Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.

Method: similar concept as 338 search target in rotated array II
worset case: O(n)
average case: O(logn)

class Solution {
    public int findMin(int[] nums) {
        int target = nums[nums.length - 1];
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start)/2;
            if (nums[mid] == nums[start]){//this is the only difference compared to search minimium I due to duplicates
                start++;
            }else if (nums[mid] <= target){
                end = mid;
            }else if (nums[mid] > target){
                start = mid;
            }
        }
        if (nums[start] < nums[end]){
            return nums[start];
        }
        return nums[end];
    }
}

class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == nums[start]){//when eqaul, we don't know which direction to do binary
                start++;
            }else if (nums[mid] == nums[end]){//when eqaul, we don't know which direction to do binary
                end--;
            }else if (nums[mid] <= target){
                end = mid;
            }else{
                start = mid;
            }
        }
        return Math.min(nums[start], nums[end]);
    }
}
