Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */


public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return node;
        }
        
        //1. node -> all nodes
        ArrayList<UndirectedGraphNode> nodes = getAllNodes(node);
        
        //2. nodes -> new nodes
        Map<UndirectedGraphNode, UndirectedGraphNode> mapping = new HashMap<>();
        
        for (UndirectedGraphNode n : nodes){
            mapping.put(n, new UndirectedGraphNode(n.label));
        }
        
        //3. edges -> new edges
        for (UndirectedGraphNode n: nodes){
            UndirectedGraphNode newNode = mapping.get(n);
            for(UndirectedGraphNode bor : n.neighbors){
                UndirectedGraphNode newbor = mapping.get(bor);
                newNode.neighbors.add(newbor);
            }
        }
        return mapping.get(node);
    }
    
    private ArrayList<UndirectedGraphNode> getAllNodes(UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        //Set<UndirectedGraphNode> hash = new HashSet<>();
        ArrayList<UndirectedGraphNode> hash = new ArrayList<>();
        queue.add(node);
        hash.add(node);
        
        while (!queue.isEmpty()){
            UndirectedGraphNode head = queue.poll();
            for (UndirectedGraphNode n : head.neighbors){
                if (!hash.contains(n)){
                    hash.add(n);
                    queue.add(n);
                }
            }
        }
        
        //return new ArrayList<UndirectedGraphNode>(hash);
        return hash;
    }
}


Better version: BFS
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new UndirectedGraphNode(node.label));
        while (!queue.isEmpty()){
            UndirectedGraphNode curr = queue.poll();
            for (UndirectedGraphNode nei : curr.neighbors){
                if (!map.containsKey(nei)){
                    map.put(nei, new UndirectedGraphNode(nei.label));
                    queue.offer(nei);//only new node will go on for BFS
                }
                map.get(curr).neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }
}

Better version: DFS
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }
        UndirectedGraphNode curr = new UndirectedGraphNode(node.label);
        map.put(node, curr);
        for (UndirectedGraphNode nei : node.neighbors){
            if (!map.containsKey(nei)){
                cloneGraph(nei);
            }
            curr.neighbors.add(map.get(nei));
        }
        return curr;
    }
}


Better DFS
public class Solution {   
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null){
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        map.put(node, root);
        dfs(node, map);
        return root;
    }
    private void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map){
        if (node == null){
            return;
        }
        for (UndirectedGraphNode nei : node.neighbors){
            if (!map.containsKey(nei)){
                UndirectedGraphNode newNode = new UndirectedGraphNode(nei.label);
                map.put(nei, newNode);
                map.get(node).neighbors.add(newNode);
                dfs(nei, map);//only new node will go on for dfs
            }else{
                map.get(node).neighbors.add(map.get(nei));
            }
            
        }
    }
}
