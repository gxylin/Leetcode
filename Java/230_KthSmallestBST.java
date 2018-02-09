Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
How would you optimize the kthSmallest routine?

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
