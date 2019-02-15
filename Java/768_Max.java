This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.

Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 2000].
arr[i] will be an integer in range [0, 10**8].

https://leetcode.com/problems/max-chunks-to-make-sorted-ii/discuss/113466/simple-java-solution-with-explanation
Method 1: 
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        int ans = 0;
        int[] maxArr = new int[arr.length];
        maxArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            maxArr[i] = Math.max(maxArr[i-1], arr[i]);
        }
        int upperLimit = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--){
            if (maxArr[i] == sorted[i]){
                if (sorted[i] <= upperLimit){
                    ans++;
                    upperLimit = arr[i];
                }
            }
        }
        return ans;
    }
}


Method 2: Best solution
Time complexity: O(n)
Space complexity: O(n)
https://leetcode.com/problems/max-chunks-to-make-sorted-ii/discuss/113462/Java-solution-left-max-and-right-min.
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[] leftMax= new int[n+1];
        int[] rightMin = new int[n+1];
        leftMax[0] = 0;
        rightMin[n] = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++){
            leftMax[i] = Math.max(leftMax[i-1], arr[i-1]);
        }
        for (int i = n-1; i >= 0; i--){
            rightMin[i] = Math.min(rightMin[i+1], arr[i]);
        }
        int count = 0;
        for (int i = 0; i < n; i++){
            if (leftMax[i+1] <= rightMin[i+1]){
                count++;
            }
        }
        return count;
    }
}


