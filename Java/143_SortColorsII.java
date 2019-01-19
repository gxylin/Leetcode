Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of 
the same color are adjacent, with the colors in the order 1, 2, ... k.

 Notice
You are not suppose to use the library's sort function for this problem.

k <= n

Have you met this question in a real interview? Yes
Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. 
Can you do it without using extra memory?

Rainbow sort 
O(Nlogk)
public class Solution {
    /*
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0){
            return;
        }
        sortColor(colors, 0, colors.length - 1, 1, k);
    }
    private void sortColor(int[] colors, int start, int end, int colorFrom, int colorTo){
        if (start >= end){
            return;
        }
        if (colorFrom == colorTo){
            return;
        }
        int left = start;
        int right = end;
        int colorMid = (colorFrom + colorTo) / 2;
        while (left < right){
            while (left < right && colors[left] <= colorMid){
                left++;
            }
            while (left < right && colors[right] > colorMid){
                right--;
            }
            if (left < right){
                int temp = colors[left];
                colors[left] = colors[right];
                colors[right] = temp;
                left++;
                right--;
            }
        }
        sortColor(colors, start, right, colorFrom, colorMid);
        sortColor(colors, left, end, colorMid + 1, colorTo);
    }
}


Time complexity: O(nk)
Space complexity: O(1)
https://aaronice.gitbooks.io/lintcode/high_frequency/sort_colors_ii.html
class Solution {
    public void sortColors(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        int min = 1;
        int max = k;
        while (min < max){
            while (i <= right){
                if (nums[i] == min){
                    swap(nums, i, left);
                    i++;
                    left++;
                }else if (nums[i] == max){
                    swap(nums, i, right);
                    right--;
                }else{
                    i++;
                }
            }
            i = left;
            min++;
            max--;
            
        }
        
    }
    private void swap(int[] nums, int x, int y){
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
}
