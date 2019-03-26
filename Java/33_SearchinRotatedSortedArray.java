Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.


Method 1:

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int start = 0; 
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end -start) / 2;
            if (nums[mid] == target){
                return mid;
            }
            if (nums[mid] > nums[start]){
                if (nums[start] <= target && target < nums[mid]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else{
                if (nums[mid] < target && target <= nums[end]){
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        if (nums[start] == target){
                return start;
        }else if (nums[end] == target){
                return end;
        }else{
                return -1;
        }
    }
}

Take this:
Best solution:
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > nums[end]){
                if (nums[mid] > target && target > nums[end]){
                    end = mid;
                }else{
                    start = mid;
                }
            }else if (nums[mid] <= nums[end]){
                if (nums[mid] < target && target <= nums[end]){ // note that must use <= , not <
                    start = mid;
                }else{
                    end = mid;
                }
            }
        }
        if (nums[start] == target){
            return start;
        }else if (nums[end] == target){
            return end;
        }
        return -1;
    }
}


Method 2:
first find minimum using binary search instead of scanning
then check where the target is and use binary search

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int minIndex = findMiniumum(nums);
       
        if (target == nums[start]){
            return start;
        }else if (target > nums[start]){
            start = 0;
            
            if (minIndex == 0){
                end = nums.length - 1;
            }else{
                end = minIndex - 1;  
            }
            
            while (start + 1 < end){
                int mid = start + (end - start) / 2;
                if (nums[mid] == target){
                    return mid;
                }else if (nums[mid] < target){
                    start = mid;
                }else{
                    end = mid;
                }
            }
            if (nums[start] == target){
                return start;
            }
            if (nums[end] == target){
                return end;
            }
        }else{
            start = minIndex;
            end = nums.length - 1;
            while (start + 1 < end){
                int mid = start + (end - start) / 2;
                if (nums[mid] == target){
                    return mid;
                }else if (nums[mid] < target){
                    start = mid;
                }else{
                    end = mid;
                }
            }
            if (nums[start] == target){
                return start;
            }
            if (nums[end] == target){
                return end;
            }
        }
        return -1;
    }
    private static int findMiniumum(int[] nums){
        int start = 0;
        int end = nums.length - 1;
        int target = nums[end];
        
        while (start + 1 < end){
            int mid = start + (end - start) /2;
            if (nums[mid] == target){
                end = mid;
            }else if (nums[mid] > target){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (nums[start] <= target){
            return start;
        }
        return end;        
    }
}


class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int last = nums[end];
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] == target){
                return mid;
            }
            int last = nums[end];
            if (target > last){
                if (nums[mid] < target && nums[mid] > last){
                    start = mid;
                }else{
                    end = mid;
                }
            }else{
                if (nums[mid] > target && nums[mid] < last){
                    end = mid;
                }else{
                    start = mid;
                }
            }
        }
        if (nums[start] == target){
            return start;
        }else if (nums[end] == target){
            return end;
        }
        return -1;
    }
}
