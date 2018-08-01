Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.

 

Example 1:

Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4

Example 2:

Input: array = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

 

Note:

    You may assume that all elements in the array are unique.
    The value of each element in the array will be in the range [-9999, 9999].


Method 1:
class Solution {
    public int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 20000;
        while (start <= end){
            int mid = start + (end - start) / 2;
            int val = reader.get(mid);
            if (val == 2147483647){
                end = mid -1 ;
            }else if (val == target){
                return mid;
            }else if (val < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}

class Solution {
    public int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 20000;
        while (start <= end){
            int mid = start + (end - start) / 2;
            int val = reader.get(mid);
            if (val == target){
                return mid;
            }else if (val < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}

Method 2:
class Solution {
    public int search(ArrayReader reader, int target) {
        int end = 1;
        while (reader.get(end) < target){
            end <<= 1;
        }
        int start = end >> 1;
        while (start <= end){
            int mid = start + (end - start) / 2;
            int val = reader.get(mid);
            if (val == target){
                return mid;
            }else if (val < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
}
