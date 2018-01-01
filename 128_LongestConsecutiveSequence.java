Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++){
            if (!set.contains(nums[i])){
                set.add(nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++){
            int up = nums[i] + 1;
            while (set.contains(up)){
                set.remove(up);
                up++;
            }
            int down = nums[i] - 1;
            while (set.contains(down)){
                set.remove(down);
                down--;
            }
            ans = Math.max(ans, up - down - 1);
        }
        return ans;
    }
}
