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
Answer: binary search O(h)


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
        Queue<Integer> result = new LinkedList<>();
        inOrder(result, root, target, k);
        List<Integer> res = new ArrayList<>();
        for (int : result){
          res.add(int);
        }
        return res;
    }
    private void inOrder(LinkedList<Integer> result, TreeNode root, double target, int k){
        if (root == null){
            return;
        }
        inOrder(result, root.left, target, k);
        if (result.size() == k){
            if (Math.abs(result.peek() - target) <= Math.abs(root.val - target)){
                return;
            }else{
                result.poll();
            }
        }
        result.offer(root.val);
        inOrder(result, root.right, target, k);
    }
}

Method 2: BST inorder traversal 

public List<Integer> closestKValues(TreeNode root, double target, int k){
    List<Integer> res = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;
    Queue<Integer> queue = new LinkedList<>();
    while (node != null || !stack.isEmpty()){
        while (node != null){
            stack.push(node);
            node = node.left;
        }
        TreeNode curr = stack.pop();
        if (queue.size() < k){
            queue.offer(curr.val);
        }else{
            if (Math.abs(queue.peek().val - target) > Math.abs(curr.val - target)){
                queue.poll();
                queue.offer(curr.val);
            }else{
                break;
            }
        }
        node = curr.right;
    }
     for (int : queue){
          res.add(int);
     }
     return res;
}
