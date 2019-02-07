Given a sorted array consisting of only integers where every element appears twice except for one element which appears once.
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.

Method 1:
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]){
                return nums[mid];
            }
            if (isEven(start, end)){
                if (nums[mid] == nums[mid-1]){
                    end = mid;
                }else if (nums[mid] == nums[mid+1]){
                    start = mid;
                }
            }else{
                if (nums[mid] == nums[mid-1]){
                    start = mid + 1;
                }else if (nums[mid] == nums[mid+1]){
                    end = mid - 1;
                }
            }
        }
        return nums[start];
    }
    private boolean isEven (int start, int end){
        int len = end - start + 1;
        return ((len - 1) /2) % 2 == 0;
    }
}

Method 2:
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] != nums[mid+1] && nums[mid] != nums[mid-1]){
                return nums[mid];
            }
            if (mid % 2 == 0){
                if (nums[mid] == nums[mid-1]){
                    end = mid;
                }else if (nums[mid] == nums[mid+1]){
                    start = mid;
                }
            }else{
                if (nums[mid] == nums[mid-1]){
                    start = mid + 1;
                }else if (nums[mid] == nums[mid+1]){
                    end = mid - 1;
                }
            }
        }
        return nums[start];
    }
}

Better solution:
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end){
            int mid = start + (end - start) / 2;
            int left = nums[mid-1];
            int right = nums[mid+1];
            if (nums[mid] != left && nums[mid] != right){
                return nums[mid];
            }
            if (mid % 2 == 0){
                if (nums[mid] == nums[mid-1]){
                    end = mid -1;
                }else if (nums[mid] == nums[mid+1]){
                    start = mid + 1;
                }
            }else{
                if (nums[mid] == nums[mid-1]){
                    start = mid + 1;
                }else if (nums[mid] == nums[mid+1]){
                    end = mid - 1;
                }
            }
        }
        return nums[start];
    }
}


Method 3
https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
