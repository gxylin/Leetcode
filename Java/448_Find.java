Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

Method 1: without extra space
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            while (i != nums[i] - 1 && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++){
            if (i != nums[i] - 1){
                result.add(i+1);
            }
        }
        return result;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < nums.length){
            if (nums[i] == i+1){
                i++;
            }else{
                swap(nums, i, nums[i]-1);
                if (nums[i] == nums[nums[i] - 1]){
                    i++;
                }
            }
        }
        for (int j = 0; j < nums.length; j++){
            if (nums[j] != j + 1){
                res.add(j+1);
            }
        }
        return res;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}


Method 2: with extra space
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] hash = new int[nums.length];
        for (int i = 0; i < nums.length; i++){
            hash[nums[i]-1]++;
        }
        for (int i = 0; i < nums.length; i++){
            if (hash[i] == 0){
                result.add(i+1);
            }
        }
        return result;
    }
}
