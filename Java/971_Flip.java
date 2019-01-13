Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].

 

Example 1:



Input: root = [1,2], voyage = [2,1]
Output: [-1]
Example 2:



Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
Example 3:



Input: root = [1,2,3], voyage = [1,2,3]
Output: []
 

Note:

1 <= N <= 100

https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/discuss/214216/JavaC++Python-DFS-Solution

Method 1: dfs with global value
class Solution {
    int i;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        return canMatch(root, voyage, res) ? res : Arrays.asList(-1);
    }
    private boolean canMatch(TreeNode root, int[] voyage, List<Integer> res){
        if (root == null){
            return true;
        }
        if (root.val != voyage[i]){
            return false;
        }
        i++;
        if (root.left != null && root.left.val != voyage[i]){
            res.add(root.val);
            return canMatch(root.right, voyage, res) && canMatch(root.left, voyage, res);
        }
         return canMatch(root.left, voyage, res) && canMatch(root.right, voyage, res);
    }
}


class Solution {
    int i;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        return canMatch(root, voyage, res) ? res : Arrays.asList(-1);
    }
    private boolean canMatch(TreeNode root, int[] voyage, List<Integer> res){
        if (root == null){
            return true;
        }
        if (root.val != voyage[i]){
            return false;
        }
        i++;
        if (root.left != null && root.left.val != voyage[i]){
            if (root.right != null && root.right.val != voyage[i]){
                return false;
            }
            res.add(root.val);
            return canMatch(root.right, voyage, res) && canMatch(root.left, voyage, res);
        }
        return canMatch(root.left, voyage, res) && canMatch(root.right, voyage, res);
    }
}


Method 2: dfs without global val

class Solution {
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        int[] p = new int[1];
        return canMatch(root, voyage, res, p) ? res : Arrays.asList(-1);
    }
    private boolean canMatch(TreeNode root, int[] voyage, List<Integer> res, int [] pointer){
        if (root == null){
            return true;
        }
        if (root.val != voyage[pointer[0]]){
            return false;
        }
        pointer[0]++;
        if (root.left != null && root.left.val != voyage[pointer[0]]){
            res.add(root.val);
            return canMatch(root.right, voyage, res, pointer) && canMatch(root.left, voyage, res, pointer);
        }
        return canMatch(root.left, voyage, res, pointer) && canMatch(root.right, voyage, res, pointer);
    }
}
