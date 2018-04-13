Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:
Input: [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Example 2:
Input: [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]

Method 1:
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; j++){
            while (j+1 < nums.length && nums[j] + 1 == nums[j+1]){
                j++;
            }
            if (i == j){
                result.add(nums[i] + "");
            }else{
                result.add(nums[i] + "->" + nums[j]);
            }
            i = j + 1;
        }
        return result;
    }
}

Method 2:
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        int len = nums.length;
        if (len == 1){
            result.add(String.valueOf(nums[0]));
            return result;
        }
        int start = 0;
        int end = 0;
        while (end < len){
            StringBuilder sb = new StringBuilder();
            while (end + 1 < len){
                if (nums[end] + 1 != nums[end+1]){
                    if (end == start){
                        sb.append(nums[start]);
                    }else{
                        sb.append(nums[start] + "->" + nums[end]);
                    }
                    break;
                }
                end++;
            }
            if (end + 1 == len){
                if (nums[end] != nums[end-1] + 1){
                    sb.append(nums[end]);
                }else{
                    sb.append(nums[start] + "->" + nums[end]);
                }
            }
            result.add(sb.toString());
            end++;
            start = end;
        }
        return result;
    }
}

