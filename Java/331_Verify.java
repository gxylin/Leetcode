One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false


when you see two consecutive “#” characters on stack, pop both of them and replace the topmost element on the stack with “#”.
The basic intuition was to collapse the entire tree into the root node.
Method 1: stack
class Solution {
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] strs = preorder.split(",");
        for (String str : strs){
            while (str.equals("#") && !stack.isEmpty() && stack.peek().equals("#")){
                stack.pop();
                if (stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
            stack.push(str);
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }
}

Method 2: Best solution
https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/discuss/78551/7-lines-Easy-Java-Solution
public boolean isValidSerialization(String preorder) {
    String[] nodes = preorder.split(",");
    int diff = 1;
    for (String node: nodes) {
        if (--diff < 0) return false;
        if (!node.equals("#")) diff += 2;
    }
    return diff == 0;
}

Method 3:
If we treat null's as leaves, then the binary tree will always be full. A full binary tree has a good property 
that # of leaves = # of nonleaves + 1. Since we are given a pre-order serialization, we just need to find the 
shortest prefix of the serialization sequence satisfying the property above. If such prefix does not exist, 
then the serialization is definitely invalid; otherwise, the serialization is valid if and only if the
prefix is the entire sequence.

class Solution {
    public boolean isValidSerialization(String preorder) {
        int leaves = 0;
        int nonLeaves = 0;
        String[] strs = preorder.split(",");
        for (String str : strs){
            if (leaves == nonLeaves + 1){
                return false;
            }
            if (str.equals("#")){
                leaves++;
            }else{
                nonLeaves++;
            }
        }
        return leaves == nonLeaves + 1;
    }
}
