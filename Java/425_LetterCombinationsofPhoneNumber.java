iven a digit string excluded 01, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Cellphone

 Notice
Although the above answer is in lexicographical order, your answer could be in any order you want.

Have you met this question in a real interview? Yes
Example
Given "23"

Return ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

思路:
• 基础枚举型DFS
– 输入数字串长度为n,做n个阶段的选择,DFS n层 – 每一阶段枚举该位的数字对应的一个字母
– 直到所有情况都枚举完

• 怎样写DFS比较好想,比较快?
￼– 先写扩展,再写退出情况
最简单、不易出错:全部放在DFS函数的参数中
中间不改变的东西:可以放在全局变量中

public class Solution {
    /*
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0){
            return result;
        }
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(result, 0, "", digits, phone);
        return result;
    }
    private void dfs(List<String> result, int x, String str, String digits, String[] phone){
        if (x == digits.length()){
            result.add(str);
            return;
        }
        int num = digits.charAt(x) - '0'; 
        String s = phone[num];
        for (int i = 0; i < s.length() ; i++){
            dfs(result, x + 1, str + s.charAt(i), digits, phone); 
            // no need to remove the last char because string uses deep copy by default
        }
    }
}
