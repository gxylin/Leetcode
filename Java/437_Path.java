You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards 
(traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

Method 1:
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        int self = pathSumIncludeRoot(root, sum); // must include root in the path
        int left = pathSum(root.left, sum); //must not include root in the path, may include root.left or not 
        int right = pathSum(root.right, sum); //must not include root in the path, may include root.left or not 
        return self + left + right;
    }
    private int pathSumIncludeRoot(TreeNode root, int sum){
        if (root == null){
            return 0;
        }
        int count = 0;
        if (sum == root.val){
            count++;
        }
        return count + pathSumIncludeRoot(root.left, sum - root.val) + pathSumIncludeRoot(root.right, sum - root.val);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        return pathSumIncludeRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int pathSumIncludeRoot(TreeNode root, int sum){
        if (root == null){
            return 0;
        }
        int count = 0;
        if (sum == root.val){
            count++;
        }
        return count + pathSumIncludeRoot(root.left, sum - root.val) + pathSumIncludeRoot(root.right, sum - root.val);
    }
}
