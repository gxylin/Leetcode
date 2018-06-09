Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 Method 1: HashMap
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        for (int key : map.keySet()){
            if (map.containsKey(k - key)){
                if (key != k - key || map.get(key) >= 2){
                    return true;
                }
            }
        }
        return false;
    }
    private void dfs(TreeNode root, Map<Integer, Integer> map){
        if (root == null){
            return;
        }
        map.put(root.val, map.getOrDefault(map.get(root.val), 0) + 1);
        dfs(root.left, map);
        dfs(root.right, map);
    }
}

Method 2: HashSet
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    private boolean dfs(TreeNode root, Set<Integer> set, int k){
        if (root == null){
            return false;
        }
        if (set.contains(k - root.val)){
            return true;
        }
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}

Method 3: Two points due to BST
Time complexity: O(n)
Space complexity: O(n)
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
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right){
            if (list.get(left) + list.get(right) == k){
                return true;
            }else if (list.get(left) + list.get(right) < k){
                left++;
            }else{
                right--;
            }
        }
        return false;
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





