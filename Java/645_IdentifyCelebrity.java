Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

 Notice

There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

Have you met this question in a real interview? Yes
Example
Given n = 2

2 // next n * (n - 1) lines 
0 knows 1
1 does not know 0
return 1 // 1 is celebrity

 小技巧总结:
• 降时间复杂度 - > 找冗余
• 思维上双向:true时候,false的时候?
• 我们询问一次的时候只利用答案为true的情况,如果为false呢?
• 一次询问knows(a, b): true a认识 b a一定不是名人
￼•
• 所以一次询问就可以排除一个人,n-1询问后剩下一个人,再对这个做
false a不认识b b一定不是名人 个名人检验就能确定他是否为名人
• 所以实现上就是从左到右扫一遍,每次都是保留下的人和新的人做一次 询问,最开始保留的人设为第1个人

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    /**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
       if (n <= 0){
           return -1;
       }
       int ans = 0;
       for (int i = 0; i < n; i++){
           if (knows(ans, i)){
               ans = i;
           }
       }
       for (int i = 0; i < n; i++){
           if (ans != i && knows(ans, i)){
               return -1;
           }
           if (ans != i && !knows(i, ans)){
               return -1;
           }
       }
       return ans;
    }
}
