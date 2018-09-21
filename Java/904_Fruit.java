In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length

Method 1: Two Points
Time complexity: O(N)
Space complexity: O(1)
class Solution {
    public int totalFruit(int[] tree) {
        int N = tree.length;
        int type1 = -1;
        int type2 = -1;
        int start1 = 0;
        int start2 = 0;
        int res = -1;
        int i = 0;
        while (i < N){
            if (tree[i] != type1 && tree[i] != type2){
                if (type1 == -1){
                    type1 = tree[i];
                    start1 = i;
                }else if (type2 == -1){
                    type2 = tree[i];
                    start2 = i;
                }else{
                    res = Math.max(res, i - start1);
                    type1 = type2;
                    start1 = start2;
                    i = start2; 
                    type2 = -1;
                }
            }
            i++;
        }
        return Math.max(res, i - start1); 
    }
}

Method 2: Longest Subarray With 2 Elements: Find out the longest length of subarrays with at most 2 different numbers
Sliding Window
Time complexity: O(N)
Space complexity: O(1)
class Solution {
    public int totalFruit(int[] tree) {
        int res = 0;
        int a = -1;
        int b = -1;
        int count_b = 0; // last type
        int curr = 0; // current longest length 
        for (int c : tree){
            if (c == a || c == b){
                curr++;
            }
            if (c == b){
                count_b++;
            }
            if (c == a){
                count_b = 1;
                a = b;
                b = c;
            }
            if (c != a && c != b){
                curr = count_b + 1;
                count_b = 1;
                a = b;
                b = c;
            }
            res = Math.max(res, curr);
        }
        return res;
    }
}

Method 3: Slidnig window with hashMap
class Solution {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> map = new HashMap<>();
        int N = tree.length;
        int res = 0;
        int start = 0;
        int end = 0;
        while (end < N){
            map.put(tree[end], map.getOrDefault(tree[end], 0) + 1);
            while (map.size() > 2){
                map.put(tree[start], map.get(tree[start]) - 1);
                if (map.get(tree[start]) == 0){
                    map.remove(tree[start]);
                }
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
