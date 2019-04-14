Given the root of a binary tree, find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.

(A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)

 

Example 1:



Input: [8,3,10,1,6,null,14,null,null,4,7,13]
Output: 7
Explanation: 
We have various ancestor-node differences, some of which are given below :
|8 - 3| = 5
|3 - 7| = 4
|8 - 1| = 7
|10 - 13| = 3
Among all possible differences, the maximum value of 7 is obtained by |8 - 1| = 7.
 

Note:

The number of nodes in the tree is between 2 and 5000.
Each node will have value between 0 and 100000.

Method 1: Best Top down
class Solution {
    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }
    private int dfs(TreeNode root, int min, int max){
        if (root == null){
            return 0;
        }
        int res = Math.max(Math.abs(root.val-min), Math.abs(root.val - max));
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        int left = dfs(root.left, min, max);
        int right = dfs(root.right, min, max);
        return Math.max(res, Math.max(left, right));
    }
}

Method 2: Bottom Up
class Solution {
    class Node{
        int min;
        int max;
        public Node(int max, int min){
            this.min = min;
            this.max = max;
        }
    }
    int res = Integer.MIN_VALUE;
    public int maxAncestorDiff(TreeNode root) {
        if (root == null){
            return 0;
        }
        dfs(root);
        return res;
    }
    private Node dfs(TreeNode root){
        if (root == null){
            return new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Node left = dfs(root.left);
        Node right = dfs(root.right);
        int diff = Integer.MIN_VALUE;
        
        if (left.max == Integer.MIN_VALUE && right.max == Integer.MIN_VALUE){
            return new Node(root.val, root.val);
        }
        
        if (left.max != Integer.MIN_VALUE && left.min != Integer.MAX_VALUE){
            diff = Math.max(diff, Math.max(Math.abs(root.val - left.max), Math.abs(root.val - left.min)));
        }
        
        if (right.max != Integer.MIN_VALUE && right.min != Integer.MAX_VALUE){
            diff = Math.max(diff, Math.max(Math.abs(root.val - right.max), Math.abs(root.val - right.min)));
        }
        res = Math.max(res, diff);
        int currMax = Math.max(root.val, Math.max(left.max, right.max));
        int currMin = Math.min(root.val, Math.min(left.min, right.min));
        return new Node(currMax, currMin);
    }
}
