 Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.



class Solution {
    public int trap(int[] height) {
        if (height == null || height.length < 3){
            return 0;
        }
        int ans = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r && height[l] < height[l+1]){
            l++;
        }
        while (l < r && height[r] < height[r-1]){
            r--;
        }
        while (l < r){
            int left = height[l];
            int right = height[r];
            if (left <= right){
                while (left > height[++l]){
                    ans += left - height[l];
                }
            }else{
                while (right > height[--r]){
                    ans += right - height[r];
                }
            }
        }
        return ans;
    }
}

Lintcode: 363
public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        if (heights == null || heights.length <= 1){
            return 0;
        }
        int left = 0;
        int right = heights.length - 1;
        int ans = 0;
        int leftHeight = heights[left];
        int rightHeight = heights[right];
        while (left < right){
            if (leftHeight < rightHeight){
                left++;
                if (leftHeight > heights[left]){
                    ans += leftHeight - heights[left];
                }else{
                    leftHeight = heights[left];
                }
            }else{
                right--;
                if (rightHeight > heights[right]){
                    ans += rightHeight - heights[right];
                }else{
                    rightHeight = heights[right];
                }
            }
        }
        return ans;
    }
}
