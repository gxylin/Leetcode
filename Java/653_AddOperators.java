Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Have you met this question in a real interview? Yes
Example
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

思路:
• 简化版,只有加减没有乘号怎么做? – 枚举数字后再枚举符号
– sum作为中间状态记录下前面的和


• 只有加减的时候比较好处理,那么怎么处理乘号呢?
– 再记录一个状态,最后的连乘的那个数lastFactor
• 34-56*23*74 90 枚举完34562374时,lastFactor = 56 * 23 * 74 • 34-562-374 90 枚举完34562374时,lastFactor = -374
– 乘号时:
• sum更新方法:sum = sum – lastFactor + lastFactor * 当前枚举的数
• lastFactor更新方法:lastFactor = lastFactor * 当前枚举的数
– 加号/减号时:
• sum更新方法:sum = sum +/- 当前枚举的数
• lastFactor更新方法:lastFactor = 当前枚举的数(加号时为正,减号时为负)

• 有哪些情况需要特殊判断?
– 第一个数字前不能有符号 (+3456 -23 -74 +90 错误) – 数字不能有前导0 (2543+034 错误)

public class Solution {
    /*
     * @param num: a string contains only digits 0-9
     * @param target: An integer
     * @return: return all possibilities
     */
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        dfs(result, "", num, target, 0, 0, 0);
        return result;
    }
    private void dfs(List<String> result, String path, String num, int target, int start, long sum, long lastFactor){
        if (start == num.length()){
            if (sum == target){
                result.add(path);
            }
            return;
        }
        for (int i = start; i < num.length(); i++){
            String str = num.substring(start, i + 1);
            long cur = Long.parseLong(str);
            if (start == 0){
               dfs(result, str, num, target, i + 1, cur, cur); 
            }else{
               dfs(result, path + "*" + str, num, target, i + 1, sum - lastFactor + lastFactor * cur, lastFactor * cur);
                dfs(result, path + "+" + str, num, target, i+1, sum + cur, cur);
                dfs(result, path + "-" + str, num, target, i+1, sum - cur, -cur);
            }
             if (num.charAt(start) == '0'){
                break;
            }
        }
    }
}
