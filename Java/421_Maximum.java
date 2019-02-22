Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

Method 1: Trie
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/130427/()-Beats-92
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91059/Java-O(n)-solution-using-Trie


In order to get O(n), we have to find way to ensure "search" is O(1) which is what Trie does.
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    class TrieNode {
        private TrieNode[] children;
        public TrieNode (){
            children = new TrieNode[2]; //TrieNode[0] contains 0; TrieNode[1] contains 1
        }
    }
    public int findMaximumXOR(int[] nums) {
        //build Trie
        TrieNode root = new TrieNode();
        for (int i : nums){
            TrieNode node = root;
            for (int j = 31; j >= 0; j--){
                int bit = (i >> j) & 1;
                if (node.children[bit] == null){
                    node.children[bit] = new TrieNode();
                }
                node = node.children[bit];
            }
        }
        //find the max of each number and get the global max
        int max = Integer.MIN_VALUE;
        for (int i : nums){
            TrieNode node = root;
            int localMax = 0;
            for (int j = 31; j >= 0; j--){
                int bit = (i >> j) & 1;
                if (node.children[bit ^ 1] != null){
                    localMax |= (1 << j);
                    node = node.children[bit^1];
                }else{
                    node = node.children[bit];
                }
            }
            max = Math.max(max, localMax);
        }
        return max;
    }
}

Method 2:
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap


