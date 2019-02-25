Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or 
memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than 
N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that 
an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches 
yourself.

 

Note:

    N is in the range of [1, 1000]
    Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<String> list = new ArrayList<>();
        serializePreOrder(root, list);
        return String.join(",", list);
    }

    private void serializePreOrder(Node root, List<String> list){
        if (root == null){
            return;
        }
        list.add(String.valueOf(root.val));
        list.add(String.valueOf(root.children.size()));
        for (Node node : root.children){
            serializePreOrder(node, list);
        }
    }
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0){
            return null;
        }
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return deserializePreOrder(queue);
    }
    
    private Node deserializePreOrder(Queue<String> queue){
        Node root = new Node();
        root.val = Integer.parseInt(queue.poll());
        int size = Integer.parseInt(queue.poll());
        root.children = new ArrayList<Node>(size);
        for (int i = 0; i < size; i++){
            root.children.add(deserializePreOrder(queue));
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



Better version:
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serializePreOrder(root, sb);
        return sb.toString();
    }

    private void serializePreOrder(Node root, StringBuilder sb){
        if (root == null){
          return;
        }
        sb.append(root.val + ",");
        sb.append(root.children.size(), ",");
        for (Node node : root.children){
            serializePreOrder(node, sb);
        }
    }
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        String[] strs = data.split(",");
        int[] pt = new int[1];
        pt[0] = 0;
        return deserializePreOrder(strs, pt);
    }
    
    private Node deserializePreOrder(String[] strs, int[] pt){
        int val = Integer.parseInt(strs[pt[0]]);
        pt[0]++;
        int size = Integer.parseInt(strs[pt[0]]);
        pt[0]++;
        Node root = new Node(val, new ArrayList<Node>(size));
        for (int i = 0; i < size; i++){
            root.children.add(deserializePreOrder(strs, pt));
        }
        return root;
    }
}
