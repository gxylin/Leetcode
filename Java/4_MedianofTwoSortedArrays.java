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

Best solution:  O(log(m + n)), O(log(k))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m+n) % 2  == 0){
            return (findKth(nums1, 0, nums2, 0, (m+n)/2) + findKth(nums1, 0, nums2, 0, (m+n)/2 + 1)) / 2.0;
        }
        return (double)findKth(nums1, 0, nums2, 0, (m+n)/2 + 1);
    }
    private int findKth(int[] nums1, int start1, int[] nums2, int start2, int k){
        if (start1 >= nums1.length){
            return nums2[start2 + k - 1];
        }
        if (start2 >= nums2.length){
            return nums1[start1 + k - 1];
        }
        if (k == 1){
            return Math.min(nums1[start1], nums2[start2]);
        }
        int mid1 = start1 + k/2 - 1 < nums1.length ? nums1[start1 + k/2 - 1] : Integer.MAX_VALUE;
        int mid2 = start2 + k/2 - 1 < nums2.length ? nums2[start2 + k/2 - 1] : Integer.MAX_VALUE;
        if (mid1 < mid2){//median must be within the 2nd half of nums1 and 1st half of nums2
            return findKth(nums1, start1 + k/2, nums2, start2, k - k/2);
        }else{
            return findKth(nums1, start1, nums2, start2 + k/2, k - k /2);
        }
    }
}

Method 1: O(log(m + n)), O(log(k))
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

Method 2: Time complexity O(k)
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int len = A.length + B.length;
        if (len % 2 == 0){
            return (findKth(A, B, len /2) + findKth(A, B, len / 2 + 1))/2.0;
        }else{
            return findKth(A, B, len / 2 + 1);
        }
    }
    private int findKth(int[] A, int[] B, int k){
        int i = 0, j = 0;
        int index = 0;
        while (i < A.length && j < B.length){
            if (A[i] < B[j]){
                index++;
                if (index == k){
                    return A[i];
                }else{
                    i++;
                }
            }else{
                index++;
                if (index == k){
                    return B[j];
                }else{
                    j++;
                }
            }
        }
        if (i < A.length){
            return A[i + (k - index - 1)];
        }else{
            return B[j + (k - index - 1)];
        }
    }
}
