Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

 1
  \
   2
    \
     3
      \
       4
        \
         5
          \
           6
            \
             7
              \
               8
                \
                 9  
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.

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
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        TreeNode newRoot = new TreeNode(list.get(0));
        TreeNode node = newRoot;
        for (int i = 1; i < list.size(); ++i){
            node.right = new TreeNode(list.get(i));
            node = node.right;
        }
        return newRoot;
    }
    private void inOrder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}

Best solution:

 
class Solution {
    public TreeNode increasingBST(TreeNode root) {
        return dfs(root, null);
    }
    private TreeNode dfs(TreeNode root, TreeNode tail){
        if (root == null){
            return tail;
        }
        TreeNode res = dfs(root.left, root);
        root.left = null;
        root.right = dfs(root.right, tail);
        return res;
    }
}

Iteration: based on inorder traversal template

class Solution {
    public TreeNode increasingBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode newRoot = null;
        TreeNode prev = null;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            TreeNode curr = stack.pop();
            if (prev == null){
                newRoot = curr;
            }else{
                prev.right = curr;
            }
            curr.left = null;
            prev = curr;
            node = curr.right;
        }
        return newRoot;
    }
}
