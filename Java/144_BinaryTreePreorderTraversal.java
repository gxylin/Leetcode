Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree [1,null,2,3],
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?

Method 1: iteration 
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
        return result;
    }
}

Method 2: 
Recursion(Traverse)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        preorder(root, result);
        return result;
    }
    private void preorder (TreeNode root, ArrayList<Integer> result){
        if (root == null){
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right,result);
    }
}

Method 3:
Recursion(divide & conquer)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null){
            return result;
        }
        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);
        
        result.add(root.val);
        result.addAll(left);
        result.addAll(right);
        return result;
    }
}


Good practice for iteration template: 
refer to https://github.com/optimisea/Leetcode/blob/master/Java/114_FlattenBinaryTree.java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if (curr.right != null){
                stack.push(curr.right);
            }
            if (curr.left != null){
                stack.push(curr.left);
            }
            if (!stack.isEmpty()){
                curr.right = stack.peek();
                curr.left = null;
            }
        }
    }
}
