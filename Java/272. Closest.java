Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?


Method 1:
O(n)
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> result = new LinkedList<>();
        inOrder(result, root, target, k);
        return result;
    }
    private void inOrder(LinkedList<Integer> result, TreeNode root, double target, int k){
        if (root == null){
            return;
        }
        inOrder(result, root.left, target, k);
        if (result.size() == k){
            if (Math.abs(result.peekFirst() - target) <= Math.abs(root.val - target)){
                return;
            }else{
                result.pollFirst();
            }
        }
        result.offerLast(root.val);
        inOrder(result, root.right, target, k);
    }
}
