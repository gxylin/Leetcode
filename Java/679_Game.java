You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

Backtracking within backtracking
https://leetcode.com/problems/24-game/discuss/107673/JAVA-Easy-to-understand.-Backtracking.


class Solution {
    private final double eps = 0.000001;
    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums){
            list.add((double)num);
        }
        return judgePoint24(list);
    }
    private boolean judgePoint24(List<Double> list){
        if (list.size() == 1){
            return Math.abs(list.get(0) - 24.0) < eps;
        }
        for (int i = 0; i < list.size() - 1; i++){
            for (int j = i + 1; j < list.size(); j++){
                double op1 = list.get(i);
                double op2 = list.get(j);
                List<Double> calList = new ArrayList<>();
                calList.addAll(Arrays.asList(op1+op2, op1-op2, op2-op1, op1*op2));
                if (Math.abs(op1) > eps){
                    calList.add(op2/op1);//it is double division/real division
                }
                if (Math.abs(op2) > eps){
                    calList.add(op1/op2);
                }
                list.remove(j);//can't remove i first, must remove j first
                list.remove(i);
                for (double d : calList){
                    list.add(d);
                    if (judgePoint24(list)){
                        return true;
                    }
                    list.remove(list.size() - 1);
                }
                list.add(i, op1);//can't add j first, must add i first
                list.add(j, op2);
            }
        }
        return false;
    }
}
