Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.


Use heap index:
Regardless whether these nodes exist:

Always make the id of left child as parent_id * 2;
Always make the id of right child as parent_id * 2 + 1;

change the val of node to be the index to save space. The value is useless. All we need is just the index.

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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        int max = 0;
        Deque<TreeNode> deque = new LinkedList<>(); //deque
        root.val = 0;
        deque.offer(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            max = Math.max(max, deque.peekLast().val - deque.peekFirst().val + 1);
            for (int i = 0; i < size; i++){
                TreeNode node = deque.poll();
                if (node.left != null){
                    node.left.val = node.val * 2;
                    deque.offer(node.left);
                }
                if (node.right != null){
                    node.right.val = node.val * 2 + 1;
                    deque.offer(node.right);
                }
            }
        }
        return max;
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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> count = new LinkedList<>();
        int max = 1;
        queue.offer(root);
        count.offer(1);
        while (!queue.isEmpty()){
            int size = queue.size();
            int left = 0;
            int right = 0;
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                Integer index = count.poll();
                if (i == 0){
                    left = index;
                }
                if (i == size - 1){
                    right = index;
                }
                if (node.left != null){
                    queue.offer(node.left);
                    count.offer(index*2);
                }
                if (node.right != null){
                    queue.offer(node.right);
                    count.offer(index*2+1);
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
