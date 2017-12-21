Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.

Method 1:
class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root.val > target){
            if (root.left == null){
                return root.val;
            }else{
               int leftclosestValue = closestValue(root.left, target);
               if (Math.abs(leftclosestValue - target) < Math.abs(root.val - target)){
                    return leftclosestValue;
                }else{
                    return root.val;
                } 
            }
        }else if (root.val < target){
            if (root.right == null){
                return root.val;
            }else{
               int rightclosestValue = closestValue(root.right, target);
               if (Math.abs(root.val - target) > Math.abs(rightclosestValue - target)){
                    return rightclosestValue;
                }else{
                    return root.val;
                } 
            }
        }
        return root.val;
    }
}

Method 2:
class Solution {
    public int closestValue(TreeNode root, double target) {
        TreeNode next = target > root.val ? root.right : root.left;
        if (next == null){
            return root.val;
        }
        int nextClosesetValue = closestValue(next, target);
        return Math.abs(nextClosesetValue - target) < Math.abs(root.val - target) ? nextClosesetValue : root.val;
    }
}

Method 3:
class Solution {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null){
            if (Math.abs(root.val - target) < Math.abs(closest - target)){
                closest = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
}
