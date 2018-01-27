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
