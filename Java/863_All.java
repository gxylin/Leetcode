We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

Method 1: convert to Graph
Time complexity: O(N)
Space complexity: O(N)
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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        buildGraph(graph, root, null);
        List<Integer> res = new ArrayList<>();
        bfs(res, target, K, graph);
        return res;
    }
    private void buildGraph(Map<Integer, List<Integer>> graph, TreeNode child, TreeNode parent){
        if (!graph.containsKey(child.val)){
            graph.put(child.val, new ArrayList<>());
        }
        List<Integer> list = graph.get(child.val);
        if (parent != null){
            list.add(parent.val);
        }
        if (child.left != null){
            list.add(child.left.val);
            buildGraph(graph, child.left, child);
        }
        if (child.right != null){
            list.add(child.right.val);
            buildGraph(graph, child.right, child);
        }   
    }
    private void bfs(List<Integer> res, TreeNode target, int K, Map<Integer, List<Integer>> graph){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> seen = new HashSet<>();
        queue.offer(target.val);
        seen.add(target.val);
        int level = 0;
        while (!queue.isEmpty() && level <= K){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                int value = queue.poll();
                if (level == K){
                    res.add(value);
                }
                for (int val : graph.get(value)){
                    if (!seen.contains(val)){
                        seen.add(val);
                        queue.add(val);
                    }
                }
            }
            level++;
        }
    }
}

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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        buildGraph(graph, root, null);
        List<Integer> res = new ArrayList<>();
        bfs(res, target, K, graph);
        return res;
    }
    private void buildGraph(Map<TreeNode, List<TreeNode>> graph, TreeNode child, TreeNode parent){
        if (child == null){
            return;
        }
        graph.put(child, new ArrayList<>());
        if (parent != null){
            graph.get(child).add(parent);
            graph.get(parent).add(child);
        }
        buildGraph(graph, child.left, child);
        buildGraph(graph, child.right, child);
    }
    private void bfs(List<Integer> res, TreeNode target, int K, Map<TreeNode, List<TreeNode>> graph){
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> seen = new HashSet<>();
        queue.offer(target);
        seen.add(target);
        int level = 0;
        while (!queue.isEmpty() && level <= K){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if (level == K){
                    res.add(node.val);
                }
                for (TreeNode n : graph.get(node)){
                    if (!seen.contains(n)){
                        seen.add(n);
                        queue.add(n);
                    }
                }
            }
            level++;
        }
    }
}

Best solution:
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        Map<TreeNode, Set<TreeNode>> graph = new HashMap<>();
        buildGraph(graph, root, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        int k = 0;
        while (!queue.isEmpty() && k <= K){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode curr = queue.poll();
                if (k == K){
                    res.add(curr.val);
                }
                for (TreeNode next : graph.get(curr)){
                    if (!visited.contains(next)){
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            k++;
        }
        return res;
    }
    private void buildGraph(Map<TreeNode, Set<TreeNode>> graph, TreeNode root, TreeNode parent){
        if (root == null){
            return;
        }
        if (!graph.containsKey(root)){
            graph.put(root, new HashSet<>());
        }
        if (parent != null && !graph.containsKey(parent)){
            graph.put(parent, new HashSet<>());
        }
        if (parent != null){
            graph.get(root).add(parent);
            graph.get(parent).add(root);
        }
        buildGraph(graph, root.left, root);
        buildGraph(graph, root.right, root);
    }
}

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143752/JAVA-Graph-+-BFS
