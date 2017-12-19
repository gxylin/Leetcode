Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Have you met this question in a real interview? Yes
Clarification
Your algorithm should run in O(n) complexity.

Example
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

• 我们一个个看 如果100在答案中 最长能有多长?最长是1 因为101 99
不在数组中(怎么确定? hash), 如果4 在答案中 最长能有多长?
• 所以一种简单的方法是对每个数字,向左向右搜一下,看最长能有多长
• 还有一个发现就是,如果4 向左向右搜到了1 2 3 那么1 2 3这三个数字 就不用向左向右搜了(发现冗余)。
• 时间复杂度o(n)

 Hash 小技巧总结:
• Hash可以在O(1)的时间内确定一个元素是否存在,利用这个可以降低
时间复杂度
• 计算时间复杂度的方法,“每个元素只会被访问一遍”这句话所代表的 方法很常用

public class Solution {
    /*
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] num) {
        if (num.length == 0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (Integer item : num){
            set.add(item);
        }
        int ans = 0;
        for (Integer item : num){
            if (set.contains(item)){
                set.remove(item);
            }
            int up = item + 1;
            int down = item - 1;
            while (set.contains(up)){
                set.remove(up);
                up++;
            }
            while (set.contains(down)){
                set.remove(down);
                down--;
            }
            ans = Math.max(ans, up - down - 1);
        }
        return ans;
    }
}
