Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all 
unique triplets in the array which gives the sum of zero.

 Notice
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)

The solution set must not contain duplicate triplets.

Have you met this question in a real interview? Yes
Example
For example, given array S = {-1 0 1 2 -1 -4}, A solution set is:

(-1, 0, 1)
(-1, -1, 2)

public class Solution {
    /*
     * @param numbers: Give an array numbers of n integer
     * @return: Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        
        Arrays.sort(numbers);
        
        for(int i = 0; i < numbers.length - 2; i++){
            if (i > 0 && numbers[i] == numbers[i - 1]){
                continue;
            }
            int target = -numbers[i];
            int start = i + 1;
            int end = numbers.length - 1;
            twoSum(result, numbers, target, start, end);
        }
        return result;
    }
    private void twoSum(List<List<Integer>> result, 
                        int[] numbers, int target, int start, int end){
        while (start < end){
            List<Integer> set = new ArrayList<>();
            if (numbers[start] + numbers[end] == target){
                set.add(-target);
                set.add(numbers[start]);
                set.add(numbers[end]);
                result.add(set);
                start++;
                end--;
                while (start < end && numbers[start] == numbers[start - 1]){
                    start++;
                }
                while (start < end && numbers[end] == numbers[end + 1]){
                    end--;
                }
            }else if (numbers[start] + numbers[end] > target){
                end--;
            }else{
                start++;
            }
        }
    }
}
