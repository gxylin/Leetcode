Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.


class Solution {
    int res = 0;
    public int distributeCoins(TreeNode root) {
        coinGivenToParent(root);
        return res;
    }
    private int coinGivenToParent(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = coinGivenToParent(root.left);
        int right = coinGivenToParent(root.right);
        res += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    }
}


class Solution {
    public int distributeCoins(TreeNode root) {
        int[] d = new int[1];
        coinGivenToParent(root,d );
        return d[0];
    }
    private int coinGivenToParent(TreeNode root, int[] d){
        if (root == null){
            return 0;
        }
        int left = coinGivenToParent(root.left, d);
        int right = coinGivenToParent(root.right, d);
        d[0] += Math.abs(left) + Math.abs(right);
        return left + right + root.val - 1;
    }
}
