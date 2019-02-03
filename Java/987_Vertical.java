Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

Method 1: based on vertical order traversal
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> indexQ = new LinkedList<>();
        nodeQ.offer(root);
        indexQ.offer(0);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (!nodeQ.isEmpty()){
            int size = nodeQ.size();
            Map<Integer, Integer> collision = new HashMap<>(); //not hashset because more than two points could collision 
            for (int i = 0; i < size; i++){
                TreeNode node = nodeQ.poll();
                int index = indexQ.poll();
                
                if (!map.containsKey(index)){
                    map.put(index, new ArrayList<>());
                }
                
                if (!collision.containsKey(index)){
                    map.get(index).add(node.val);
                    collision.put(index, 1);
                }else{
                    int points = collision.get(index);
                    List<Integer> list = map.get(index);
                    Stack<Integer> stack = new Stack<>();
                    while (points > 0){
                        int val = list.get(list.size() - 1);
                        if (node.val >= val){
                            list.add(node.val);
                            while (!stack.isEmpty()){
                                list.add(stack.pop());
                            }
                            break;
                        }else{
                            stack.push(list.remove(list.size() - 1));
                        }
                        points--;
                    }
                    if (points == 0){
                        list.add(node.val);
                        while (!stack.isEmpty()){
                            list.add(stack.pop());
                        }
                    }
                    collision.put(index, collision.get(index) + 1);
                }

                min = Math.min(min, index);
                max = Math.max(max, index);

                if (node.left != null){
                    nodeQ.offer(node.left);
                    indexQ.offer(index-1);
                }
                if (node.right != null){
                    nodeQ.offer(node.right);
                    indexQ.offer(index+1);
                }
            }
        }
        for (int i = min; i<= max; i++){
            res.add(map.get(i));
        }
        return res;
    }
}

Method 2: DFS Better solution
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> mapX = new TreeMap<>();
        dfs(root, mapX, 0, 0);
        for (int x : mapX.keySet()){
            List<Integer> list = new ArrayList<>();
            TreeMap<Integer, TreeSet<Integer>> mapY = mapX.get(x);
            for (int y : mapY.keySet()){
                TreeSet<Integer> set = mapY.get(y);
                for (int val : set){
                    list.add(val);
                }
            }
            res.add(list);
        }
        return res;
        
    }
    private void dfs (TreeNode root, TreeMap<Integer, TreeMap<Integer, TreeSet<Integer>>> mapX, int x, int y){
        if (root == null){
            return;
        }
        if (!mapX.containsKey(x)){
            mapX.put(x, new TreeMap<>());
        }
        TreeMap<Integer, TreeSet<Integer>> mapY = mapX.get(x);
        if (!mapY.containsKey(y)){
            mapY.put(y, new TreeSet<>());
        }
        TreeSet<Integer> set = mapY.get(y);
        set.add(root.val);
        dfs(root.left, mapX, x-1, y+1);
        dfs(root.right, mapX, x+1, y+1);
    }
}
