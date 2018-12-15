Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?

Method 1:
Time complexity: O(n)
class Solution {
    int ans = 0;
    public int kthSmallest(TreeNode root, int k) {
        int[] count = new int[1];
        count[0] = k;
        inorder(root, count);
        return ans;
    }
    private void inorder(TreeNode root, int[] count){
        if (root == null){
            return;
        }
        inorder(root.left, count);
        count[0]--;
        if (count[0] == 0){
            ans = root.val;
            return;
        }
        inorder(root.right, count);
    }
}

Method 2:
Time complexity: O(n)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int[] result = new int[2];
        result[0] = k;
        inorder(root, result);
        return result[1];
    }
    private void inorder(TreeNode root, int[] result){
        if (root == null){
            return;
        }
        inorder(root.left, result);
        result[0]--;
        if (result[0] == 0){
            result[1] = root.val;
            return;
        }
        inorder(root.right, result);
    }
}

Method 3:
Time complexity: O(nlogn)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = countNode(root.left);
        if (k <= count){
            return kthSmallest(root.left, k);
        }else if (k > count + 1){
            return kthSmallest(root.right, k - count - 1);
        }
        return root.val;
    }
    private int countNode(TreeNode root){
        if (root == null){
            return 0;
        }
        return 1 + countNode(root.left) + countNode(root.right);
    }
}


Method 4:
https://leetcode.com/problems/validate-binary-search-tree/discuss/32112/Learn-one-iterative-inorder-traversal-apply-it-to-multiple-tree-questions-(Java-Solution)
Iteration:
O(n)
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                stack.push(node);
                node = node.left;
            }
            TreeNode curr = stack.pop();
            k--;
            if (k == 0){
                return curr.val;
            }
            node = curr.right;
        }
        return -1;
    }
}
