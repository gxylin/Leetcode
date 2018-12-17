Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note:

The tree will have between 1 and 100 nodes.

Method 1: recursion:
O(logn * logn * logn * logn * logn)
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null){
            return true;
        }
        boolean left = isCompleteTree(root.left);
        boolean right = isCompleteTree(root.right);
        if (!left || !right){
            return false;
        }
        int leftRight = getRightDepth(root.left);
        int rightLeft = getleftDepth(root.right);
        if (leftRight < rightLeft){
            return false;
        }
        int leftLeft = getleftDepth(root.left);
        int rightRight = getRightDepth(root.right);
        if (leftLeft > rightRight + 1){
            return false;
        }
        return true;
    }
    private int getleftDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + getleftDepth(root.left);
    }
    private int getRightDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + getRightDepth(root.right);
    }
}

Method 2: BFS level order
O(n)
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.peek() != null){
            TreeNode node = queue.poll();
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node != null){
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEnd = false;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node == null){
                isEnd = true;
            }else{
                if (isEnd){
                    return false;
                }
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}
