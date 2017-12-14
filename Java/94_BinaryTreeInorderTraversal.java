Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        TreeNode curt = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (curt != null || !stack.isEmpty()){
            while (curt != null){
                stack.push(curt);
                curt = curt.left;
            }
            curt = stack.pop();
            result.add(curt.val);
            curt = curt.right;
        }
        return result;
    }
}

