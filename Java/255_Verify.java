Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?


https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/discuss/68142/Java-O(n)-and-O(1)-extra-space
Method 1: monotonic stack
time complexity: O(n)
space complexity: O(n)
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> monotonicStack = new Stack<>();
        int low = Integer.MIN_VALUE;
        for (int p: preorder){
            if (p < low){
                return false;
            }
            while (!monotonicStack.isEmpty() && p > monotonicStack.peek()){
                low = monotonicStack.pop();
            }
            monotonicStack.push(p);
        }
        return true;
    }
}

Method 2: use array as monotonic stack
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        int i = -1;
        int low = Integer.MIN_VALUE;
        for (int p: preorder){
            if (p < low){
                return false;
            }
            while (i >= 0 && p > preorder[i]){
                low = preorder[i--];
            }
           preorder[++i] = p;
        }
        return true;
    }
}


Method 3:
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1);    
    }
    private boolean verify(int[] nums, int start, int end){
        if (start >= end){
            return true;
        }
        int root = nums[start];
        int indexOfFirstGreater = -1;
        for (int i = start + 1; i <= end; i++){
            if (indexOfFirstGreater == -1 && nums[i] > root){
                indexOfFirstGreater = i;
            }
            if (indexOfFirstGreater != -1 && nums[i] < root){
                return false;
            }
        }
        if (indexOfFirstGreater == -1){
            return verify(nums, start+1, end);
        }
        return verify(nums, start+1, indexOfFirstGreater-1) && verify(nums, indexOfFirstGreater, end);
    }
}
