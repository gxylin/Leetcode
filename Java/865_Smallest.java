Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.

 

Example 1:

Input: [3,5,1,6,2,0,8,null,null,7,4]
Output: [2,7,4]
Explanation:



We return the node with value 2, colored in yellow in the diagram.
The nodes colored in blue are the deepest nodes of the tree.
The input "[3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]" is a serialization of the given tree.
The output "[2, 7, 4]" is a serialization of the subtree rooted at the node with value 2.
Both the input and output have TreeNode type.
 

Note:

The number of nodes in the tree will be between 1 and 500.
The values of each node are unique.


Method 1: preOrder + postOrder
Two pass:
Time complexity: O(N)
Space complexity: O(N)
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(null, -1);
        preOrder(root, null, map);
        int max = 0;
        for (int depth : map.values()){
            max = Math.max(max, depth);
        }
        return postOrder(root, max, map);
    }
    //label node  with depth, increase order so use preOrder
    private void preOrder(TreeNode root, TreeNode parent, Map<TreeNode, Integer> map){
        if (root == null){
            return;
        }
        map.put(root, map.get(parent) + 1);
        preOrder(root.left, root, map);
        preOrder(root.right, root, map);
    }
    //collect the deepest node
    private TreeNode postOrder(TreeNode root, int max,  Map<TreeNode, Integer> map){
        if (root == null || map.get(root) == max){
            return root;
        }
        TreeNode left = postOrder(root.left, max, map);
        TreeNode right = postOrder(root.right, max, map);
        if (left != null && right != null){
            return root;
        }
        if (left != null){
            return left;
        }
        if (right != null){
            return right;
        }
        return null;
    }
}

O(N)
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        map.put(null, 0);
        dfs(root, null, map);
        int max = 0;
        for (int d : map.values()){
            max = Math.max(d, max);
        }
        return lowestCommonDepth(root, max, map);
    }
    private void dfs(TreeNode root, TreeNode parent, Map<TreeNode, Integer> map){
        if (root == null){
            return;
        }
        map.put(root, map.get(parent) + 1);
        dfs(root.left, root, map);
        dfs(root.right, root, map);
    }
    private TreeNode lowestCommonDepth(TreeNode root, int max, Map<TreeNode, Integer> map){
        if (root == null || map.get(root) == max){
            return root;
        }
        TreeNode left = lowestCommonDepth(root.left, max, map);
        TreeNode right = lowestCommonDepth(root.right, max, map);
        if (left != null && right != null){
            return root;
        }
        if (left != null && right == null){
            return left;
        }
        if (left == null && right != null){
            return right;
        }
        return null;
    }
}


Method 2: 
Time complexity: O(N^2)
 class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null){
            return root;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth == rightDepth){
            return root;
        }
        if (leftDepth > rightDepth){
            return subtreeWithAllDeepest(root.left);
        }
        return subtreeWithAllDeepest(root.right);
    }
    private int getDepth(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}

