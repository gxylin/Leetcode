Given an array A, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 

Example 1:

Input: [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Note:

2 <= A.length <= 30000
0 <= A[i] <= 10^6
It is guaranteed there is at least one way to partition A as described.

Method 1: Brute Force
Time complexity: O(n^2)
Space complexity: O(1)
class Solution {
    public int partitionDisjoint(int[] A) {
        int n = A.length;
        for (int i = 1; i < n; i++){
            int leftMax = Integer.MIN_VALUE;
            int rightMin = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++){
                leftMax = Math.max(leftMax, A[j]);
            }
            for (int j = i; j < n; j++){
                rightMin = Math.min(rightMin, A[j]);
            }
            if (leftMax <= rightMin){
                return i;
            }
        }
        return -1;
    }
}

Method 2:  Best
https://leetcode.com/problems/partition-array-into-disjoint-intervals/discuss/175945/Java-one-pass-7-lines
suppose the original left subarray is from 0 to cand, the max value of that is localMax. If it is a valid partition, 
every value from partitionIdx + 1 to end should be >= localMax. But if we find a value in the right part, a[i], 
is smaller than localMax, which means the partition is not correct and we have to incorporate a[i] to form the left subarray. 
So the partitionIdx is set to be i, and we have to recalculate the max value of the new left subarray.(recorded in max)

Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int partitionDisjoint(int[] A) {
        int leftMax = A[0];
        int cand = 0;
        int max = leftMax;
        for (int i = 1; i < A.length; i++){
            if (leftMax > A[i]){
                leftMax = max;
                cand = i;
            }else{
                max = Math.max(max, A[i]);
            }
        }
        return cand + 1;
    }
}

Method 3: 
Time complexity: O(n)
Space complexity: O(n)
https://leetcode.com/problems/partition-array-into-disjoint-intervals/discuss/175849/C++JavaPython-Straight-Forward
Use minFromBack to store min value from i to end and then check from front
class Solution {
    public int partitionDisjoint(int[] A) {
        int n = A.length;
        int[] minFromBack = new int[n];
        minFromBack[n-1] = A[n-1];
        for (int i = n - 2; i > 0; i--){
            minFromBack[i] = Math.min(minFromBack[i+1], A[i]);
        }
        int leftMax = 0;
        for (int i = 1; i < n; i++){
            leftMax = Math.max(leftMax, A[i-1]);
            if (leftMax <= minFromBack[i]){
                return i;
            }
        }
        return -1;
    }
}
