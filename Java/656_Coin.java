 Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that
 from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed
 i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, 
it means you can’t jump to the place indexed i in the array.

Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. 
 You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using 
 minimum coins.

If there are multiple paths with the same cost, return the lexicographically smallest such path.

If it's not possible to reach the place indexed N then you need to return an empty array.

Example 1:

Input: [1,2,4,-1,2], 2
Output: [1,3,5]

Example 2:

Input: [1,2,4,-1,2], 1
Output: []

Note:

    Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where 
    Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
    A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
    Length of A is in the range of [1, 1000].
    B is in the range of [1, 100].


LIS DP + Record Path
class Solution {
    class Pair {
        int num;
        List<Integer> list;
        public Pair (int num, List<Integer> list){
            this.num = num;
            this.list = list;
        }
    }
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> res = new ArrayList<>();
        int n = A.length;
        Pair[] dp = new Pair[n+1];
        for (int i = 0; i < dp.length; i++){
            if (i == 0 || i == 1){
                dp[i] = new Pair(0, new ArrayList<>());
            }else{
                dp[i] = new Pair(Integer.MAX_VALUE, new ArrayList<>());
            }   
        }
        for (int i = 1; i <= n; i++){
            if (A[i-1] == -1){
                continue;
            }
            for (int j = 1; j <= B; j++){
                if (i > j && A[i-j-1] != -1){
                    if (dp[i-j].num + A[i-j-1] < dp[i].num){
                        int newNum = A[i-j-1] + dp[i-j].num;
                        List<Integer> newList = new ArrayList<>();
                        newList.addAll(dp[i-j].list);
                        newList.add(i-j-1);
                        dp[i] = new Pair(newNum, newList);
                    }else if (dp[i-j].num + A[i-j-1] == dp[i].num){
                        List<Integer> newList = new ArrayList<>();
                        newList.addAll(dp[i-j].list);
                        newList.add(i-j-1);
                        String s1 = "";
                        for (int k : newList){
                            s1 += k;
                        }
                        String s2 = "";
                        for (int k : dp[i].list){
                            s2 += k;
                        }
                        if (s1.compareTo(s2) < 0){
                            dp[i] = new Pair(dp[i].num, newList);
                        }
                    }
                }
            }
        }
        if (dp[n].num == Integer.MAX_VALUE){
            return new ArrayList<Integer>();
        }
        dp[n].list.add(n-1);
        for (int i = 0; i < dp[n].list.size(); i++){
            dp[n].list.set(i, dp[n].list.get(i) + 1);
        }
        
        return dp[n].list;
    }
}
