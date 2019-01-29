 Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal 
  sum of values after removing exactly one edge on the original tree.

Example 1:

Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15

Method 1: DFS: HashMap
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
    public boolean checkEqualTree(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = dfs(root, map);
        if (sum == 0){
            return map.get(sum) > 1;
        }
        if (sum % 2 != 0){
            return false;
        }
        return map.containsKey(sum / 2);
    }
    private int dfs(TreeNode root, Map<Integer, Integer> map){
        if (root == null){
            return 0;
        }
        int curSum = dfs(root.left, map) + dfs(root.right, map) + root.val;
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        return curSum;
    }
}

Method 2: Iteration Stack
class Solution {
    Stack<Integer> seen;
    public boolean checkEqualTree(TreeNode root) {
        seen = new Stack();
        int total = sum(root);
        seen.pop();
        if (total % 2 == 0)
            for (int s: seen)
                if (s == total / 2)
                    return true;
        return false;
    }

    public int sum(TreeNode node) {
        if (node == null) return 0;
        seen.push(sum(node.left) + sum(node.right) + node.val);
        return seen.peek();
    }
}

Method 3: DFS( can't pass some cases as below)
 0
 / \
-1  1

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
    boolean isHalf = false;
    public boolean checkEqualTree(TreeNode root) {
        if (root == null){
            return false;
        }
        int total = getTotal(root);
        checkSum(root, total);
        return isHalf;
    }
    private int getTotal(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = getTotal(root.left);
        int right = getTotal(root.right);
        return left + right + root.val;
    }
    private int checkSum(TreeNode root,int total){
        if (root == null || isHalf){
            return 0;
        }
        int left = checkSum(root.left, total);
        int right = checkSum(root.right, total);
        int sum = left + right + root.val;
        if (sum * 2 == total){
            isHalf = true;
            return 0;
        }
        return sum;
        
    }
}
