Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

Method 1:
Time complexity: O(n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < nums1.length; i++){
            if (!set.contains(nums1[i])){
                set.add(nums1[i]);
            }
        }
        for (int i = 0; i < nums2.length; i++){
            if (set.contains(nums2[i])){
                result.add(nums2[i]);
            }
        }
        int[] ans = new int[result.size()];
        int index = 0;
        for (Integer i : result){
            ans[index] = i;
            index++;
        }
        return ans;
    }
}

Method 2:
Time complexity: O(nlogn)
 public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
}

Method 3:
Time complexity: O(nlogn)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0){
            return nums1;
        }
        if (nums2.length == 0){
            return nums2;
        }
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        for (int i = 0; i < nums2.length; i++){
            if (binarySearch(nums1, nums2[i])){
                set.add(nums2[i]);
            }
        }
        int[] ans = new int[set.size()];
        int index = 0;
        for (Integer i : set){
            ans[index] = i;
            index++;
        }
        return ans;
    }
    private boolean binarySearch(int[] A, int target){
        int start = 0; 
        int end = A.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (A[mid] == target){
                return true;
            }else if (A[mid] > target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (A[start] == target || A[end] == target){
            return true;
        }
        return false;
    }
}
 
