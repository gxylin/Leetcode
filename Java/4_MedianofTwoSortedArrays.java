There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length + nums2.length;
        if (n % 2 == 1){
            return findKth(nums1, 0, nums2, 0, n/2 + 1);
        }else{
            return (findKth(nums1, 0, nums2, 0, n/2) + findKth(nums1, 0, nums2, 0, n/2 + 1))/2.0;
        }
    }
    private double findKth(int[] A, int startA, int[] B, int startB, int k){
        if (startA >= A.length){
            return B[startB + k - 1];
        }
        if (startB >= B.length){
            return A[startA + k - 1];
        }
        if (k == 1){
            return Math.min(A[startA], B[startB]);
        }
        int Amid = startA + k/2 - 1 < A.length ? A[startA + k/2 - 1] : Integer.MAX_VALUE;
        int Bmid = startB + k/2 - 1 < B.length ? B[startB + k/2 - 1] : Integer.MAX_VALUE;

        if (Amid < Bmid){
            return findKth(A, startA + k/2 , B, startB, k - k/2);
        }else{
            return findKth(A, startA, B, startB + k/2, k - k/2);
        }
        
    }
}
