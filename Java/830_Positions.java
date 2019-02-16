In a string S of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

Call a group large if it has 3 or more characters.  We would like the starting and ending positions of every large group.

The final answer should be in lexicographic order.

 

Example 1:

Input: "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
Example 2:

Input: "abc"
Output: []
Explanation: We have "a","b" and "c" but no large group.
Example 3:

Input: "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
 

Note:  1 <= S.length <= 1000

Method 1:
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        while (i < S.length()){
            i++;
            int count = 1;
            int start = i;
            while (i < S.length() && S.charAt(i-1) == S.charAt(i)){
                count++;
                i++;
            }
            if (count >= 3){
                List<Integer> item = new ArrayList<>();
                item.add(start-1);
                item.add(i-1);
                res.add(new ArrayList<Integer>(item));
            }
        }
        return res;
    }
}

Method 2: two pointers
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < S.length()){
            while (j < S.length() && S.charAt(j) == S.charAt(i)){
                j++;
            }
            if (j - i >= 3){
                res.add(Arrays.asList(i, j-1));
            }
            i = j;
        }
        return res;
    }
}

Similar as Group Binary String 
https://github.com/optimisea/Leetcode/blob/master/Java/696_Count.java

Best solution:
class Solution {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();
        int curr = 1;
        int currStart = 0;
        for (int i = 1; i < S.length(); i++){
            if (S.charAt(i) == S.charAt(i-1)){
                curr++;
            }else{
                if (curr >= 3){
                    List<Integer> list = new ArrayList<>();
                    list.add(currStart);
                    list.add(i-1);
                    res.add(list);
                }
                currStart = i;
                curr = 1;
            }
        }
        if (curr >= 3){
            List<Integer> list = new ArrayList<>();
            list.add(currStart);
            list.add(S.length()-1);
            res.add(list);
        }
        return res;
    }
}
