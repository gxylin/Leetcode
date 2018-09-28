Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]


Single pointer
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        int next = lower; 
        for (int i = 0; i < nums.length; i++){
            if (next < nums[i]){
                res.add(getRange(next, nums[i] - 1));
                next = nums[i] + 1;
            }else if (next == nums[i]){
                next++;
            }
            // handle the case that [Integer.MAX_VALUE], lower = 0, hi = Integer.MAX_VALUE
            if (next == Integer.MIN_VALUE){
                return res;
            }
        }
        if (next <= upper){
            res.add(getRange(next, upper));
        }
        return res;
    }
    private String getRange(int n1, int n2){
        if (n1 == n2){
            return String.valueOf(n1);
        }
        return String.format("%d->%d", n1, n2);
    }
}
