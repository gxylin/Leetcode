Given scores of N athletes, find their relative ranks and the people with the top three highest scores, 
who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.

Method 1: 
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        Arrays.sort(nums);
        int n = nums.length;
        String[] result = new String[n];
        for (int i = 0; i < n; i++){
            int index = map.get(nums[i]);
            if (i == n - 1){
                result[index] = "Gold Medal";
            }else if (i == n - 2){
                result[index] = "Silver Medal";
            }else if (i == n - 3){
                result[index] = "Bronze Medal";
            }else{
                result[index] = Integer.toString(n-i);
            }
        }
        return result;
    }
}
