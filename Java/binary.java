https://leetcode.com/articles/introduction-to-binary-search/

Template for binary search and question similar as 719
https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm

Binary Search related question

786. K-th Smallest Prime Fraction

774	Minimize Max Distance to Gas Station

719. Find K-th Smallest Pair Distance

668. Kth Smallest Number in Multiplication Table

644. Maximum Average Subarray II

378. Kth Smallest Element in a Sorted Matrix

35. Search Insert Position

     Must see https://github.com/optimisea/Leetcode/blob/master/Java/35_Search.java

Template to find the first smallest one:

public int binarySearch(int[] nums, int target) {
     int n = nums.length;
     int low = 0;
     int high = n - 1;
     while (low <= high){
         int mid = low + (high - low) / 2;
         if (nums[mid] <= target){
            low = mid + 1;
         else{
            high = mid - 1;
         }
     }
     return low;
}

     35. Search Insert Position

     Must see https://github.com/optimisea/Leetcode/blob/master/Java/35_Search.java
     
Note that for this template, in most cases target should be between low (inclusive) and high (inclusive) 
     But there are two corner cases
     case 1: target less than low, e.g. [1,2,3] target = 0 == > low will be 1, high will be 2
     case 2: target greater than high, e.g. [1,2,3] target = 4 == > low will be 2, high will be 3
          
public int binarySearch(int[] nums, int target) {
     int n = nums.length;
     int low = 0;
     int high = n - 1;
     while (low  + 1 < high){
         int mid = low + (high - low) / 2;
         if (nums[mid] <= target){
            low = mid;
         else{
            high = mid;
         }
     }
     if (nums[low] == target){
        return low;
     }
     return high;
}



Question 719:
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int low = 0;
        int high = nums[n-1] - nums[0];
        while (low <= high){
            int mid = low + (high - low) / 2;
            int count = getLessEqual(nums, mid);
            if (count <= k - 1){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
    private int getLessEqual(int[] nums, int val){
        int res = 0;
        for (int i = 0; i < nums.length; i++){
            int j = i + 1;
            while (j < nums.length && nums[j] - nums[i] <= val){
                j++;
            }
            res += j - i - 1;
        }
        return res;
    }
}

Question 378
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = matrix[0][0];
        int high = matrix[m-1][n-1];
        while (low + 1 < high){
            int mid = low + (high - low) / 2;
            int count = getLessEqual(matrix, mid);
            if (count <= k - 1){
                low = mid;
            }else{
                high = mid;
            }
        }
        if (getLessEqual(matrix, low) <= k - 1){
            return high;
        }
        return low;
    }
    private int getLessEqual(int[][] matrix, int val){
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n){
            if (matrix[i][j] > val){
                i--;
            }else{
                res += i + 1;
                j++;
            }
        }
        return res;
    }
}
