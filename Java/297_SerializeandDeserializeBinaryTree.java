Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored
in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or 
another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your 
serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized 
to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need 
to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms 
should be stateless.

Method 1:
Level order traversal:
the code string will be 1,2,3,null,null,4,5,null,null,null,null
Note that: linkelist/arraylist can add null

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        if (root == null){
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node != null){
                ans.append(String.valueOf(node.val) + ",");
                queue.offer(node.left);
                queue.offer(node.right);
            }else{
                ans.append("null,");
            }
        }
        return ans.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")){
            return null;
        }
        String[] strs = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        queue.offer(root);
        for (int i = 1; i < strs.length; i++){
            TreeNode parent = queue.poll();
            if (!strs[i].equals("null")){
                int num = Integer.parseInt(strs[i]);
                TreeNode left = new TreeNode(num);
                parent.left = left;
                queue.offer(left);
            }
            if (!strs[++i].equals("null")){
                int num = Integer.parseInt(strs[i]);
                TreeNode right = new TreeNode(num);
                parent.right = right;
                queue.offer(right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

Method 2:
preorder traversal
the order string will be 1,2,null,null,3,4,null,null,5,null,null
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return "";
        }
        StringBuilder ans = new StringBuilder();
        dfsSe(root, ans);
        return ans.toString();
    }
    private void dfsSe(TreeNode root, StringBuilder ans){
        if (root == null){
            ans.append("null,");
            return;
        }
        ans.append(root.val + ",");
        dfsSe(root.left, ans);
        dfsSe(root.right, ans);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("")){
            return null;
        }
        String[] strs = data.split(",");
        int[] d = new int[1];
        return dfsDe(strs, d);
    }
    private TreeNode dfsDe(String[] strs, int[] d){
        if (strs[d[0]].equals("null")){
            d[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strs[d[0]]));
        d[0]++;
        root.left = dfsDe(strs, d);
        root.right = dfsDe(strs, d);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



Method 3:
Best solution: preOrder with queue
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializePreOrder(root, sb);
        return sb.toString();
    }

    private void serializePreOrder(TreeNode root, StringBuilder sb){
        if (root == null){
            sb.append("null,");
            return;
        }
        sb.append(root.val + ",");
        serializePreOrder(root.left, sb);
        serializePreOrder(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0){
            return null;
        }
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return deserializePreOrder(queue);
    }
    
    private TreeNode deserializePreOrder(Queue<String> queue){
        String str = queue.poll();
        if (str.equals("null")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = deserializePreOrder(queue);
        root.right = deserializePreOrder(queue);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



Best version: preorder
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder res = new StringBuilder();
        preOrderSerial(root, res);
        return res.toString();
    }

    private void preOrderSerial(TreeNode root, StringBuilder res){
        if (root == null){
            res.append("null,");
            return;
        }
        res.append(root.val + ",");
        preOrderSerial(root.left, res);
        preOrderSerial(root.right, res);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        int[] pt = new int[1];
        pt[0] = 0;
        return preOrderDeserial(strs, pt);
    }
    
    private TreeNode preOrderDeserial(String[] strs, int[] pt){
        String str = strs[pt[0]];
        pt[0]++;
        if (str.equals("null")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = preOrderDeserial(strs, pt);
        root.right = preOrderDeserial(strs, pt);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
