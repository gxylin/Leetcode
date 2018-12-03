
Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, 
and all nodes in the last level are as far left as possible. It can have between 1 and 2h
nodes inclusive at the last level h.




https://leetcode.com/problems/count-complete-tree-nodes/discuss/61948/Accepted-Easy-Understand-Java-Solution/122365?page=1
<< and >> are bit manipulate operators, it means to move the 32 bit integer to left or to right, which essentially 
does what pow(2,n) does. When the exponential base is 2, use bit moving operations are fast.
1 << leftDepth == 2 ^ leftDepth, leftDepth << 1 == leftDepth * 2

https://leetcode.com/problems/count-complete-tree-nodes/discuss/61967/A-very-clear-recursive-solution-isn't-it

Method 1: 
Time complexity: O(logN * logN or depth * depth)

class Solution {
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        int leftDepth = getLeftDepth(root.left);
        int rightDepth = getLeftDepth(root.right);
        if (leftDepth == rightDepth){//left subtree is full, right subtree may be or may not be
            return (1 << leftDepth) - 1 + countNodes(root.right) + 1;
        }else{//right subtree is full, left subtree is not full
            return (1 << rightDepth) - 1 + countNodes(root.left) + 1;
        }
    }
    private int getLeftDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + getLeftDepth(root.left);
    }
}


Method 2: TLE
Time complexity: N or number of node
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null){
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
