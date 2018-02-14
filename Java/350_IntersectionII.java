Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

Note:
Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you 
cannot load all elements into the memory at once?

External Sort:http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L17-ExternalSortEX2.htm
 From a data engineer’s perspective, basically there are three ideas to solve the question:

1. Store the two strings in distributed system(whether self designed or not), then 
using MapReduce technique to solve the problem;

2. Processing the Strings by chunk, which fits the memory, then deal with each chunk of data at a time;

3. Processing the Strings by streaming, then check.


Method 1:
Time complexity: O(nlogn)
Space complexity: O(1)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]){
                i++;
            }else if (nums1[i] > nums2[j]){
                j++;
            }else{
                result.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] ans = new int[result.size()];
        int index = 0;
        for (Integer n : result){
            ans[index++] = n;
        }
        return ans;
    }
}

Method 2: 
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++){
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++){
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i]) - 1);
            }
        }
        int[] ans = new int[result.size()];
        int index = 0;
        for (Integer n : result){
            ans[index++] = n;
        }
        return ans;
    }
}
