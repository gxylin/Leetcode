Intuition

Call a chain a sequence of 3 or more consecutive numbers.

Considering numbers x from left to right, if x can be added to a current chain, it's at least as good to add x to that chain first, 
    rather than to start a new chain.

Why? If we started with numbers x and greater from the beginning, the shorter chains starting from x could be concatenated with the 
chains ending before x, possibly helping us if there was a "chain" from x that was only length 1 or 2.

Algorithm

Say we have a count of each number, and let tails[x] be the number of chains ending right before x.

Now let's process each number. If there's a chain ending before x, then add it to that chain. Otherwise, if we can start a new chain,
do so.

It's worth noting that our solution can be amended to take only O(1)O(1)O(1) additional space, since we could do our counts similar
    to Approach #1, and we only need to know the last 3 counts at a time.

    
    
1. We iterate through the array once to get the frequency of all the elements in the array
2. We iterate through the array once more and for each element we either see if it can be appended to a previously constructed
consecutive sequence or if it can be the start of a new consecutive sequence. If neither are true, then we return false.

    
Key Note:
appending to existing array has higher priority than creating one array

Greedy
public boolean isPossible(int[] nums) {
    Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
    for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
    for (int i : nums) {
        if (freq.get(i) == 0) continue;
        else if (appendfreq.getOrDefault(i,0) > 0) {
            appendfreq.put(i, appendfreq.get(i) - 1);
            appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
        }   
        else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
            freq.put(i+1, freq.get(i+1) - 1);
            freq.put(i+2, freq.get(i+2) - 1);
            appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
        }
        else return false;
        freq.put(i, freq.get(i) - 1);
    }
    return true;
}

class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> append = new HashMap<>();
        for (int num : nums){
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < nums.length; i++){
            if (freq.get(nums[i]) == 0){
                continue;
            }
            if (append.getOrDefault(nums[i], 0) > 0){
                append.put(nums[i], append.get(nums[i]) - 1);
                append.put(nums[i] + 1, append.getOrDefault(nums[i] + 1, 0) + 1);
            }else if (freq.getOrDefault(nums[i] + 1, 0) > 0 && freq.getOrDefault(nums[i] + 2, 0) > 0){
                freq.put(nums[i] + 1, freq.get(nums[i] + 1) - 1);
                freq.put(nums[i] + 2, freq.get(nums[i] + 2) - 1);
                append.put(nums[i] + 3, append.getOrDefault(nums[i] + 3, 0) + 1);
            }else{
                return false;
            }
            freq.put(nums[i], freq.get(nums[i]) - 1);
        }
        return true;
    }
}

思路：
用freq map先过一遍存频率，再建一个map存我们能用到的tail number。再过第二遍的时候，若freq==0 continue；若能接上前面的顺子，就接；不能则新开一个顺子（记住新开时候直接要把连着的两个数字剔除，因为要保证长度为三）；都不行则为false。记住最后别忘了更新当前频率

对于每一个element，我们有两种选择
1.	把它加入之前构造好的顺子中
2.	用它新开一个顺子
此处用贪心策略，如果1能满足总是先满足1，因为新开顺子可能失败，即使新开顺子成功，当1能满足的时候，将新开顺子加入之前的顺子也能成功，所以能够选择策略1的时候没必要冒风险选择策略2

目标是用策略1或者2消耗掉所有的元素

如果两个策略都无法选择，直接返回false

用另一个map记录已经构造好的顺子中现在需要哪些尾巴，来实现将当前元素加入构造好的顺子中

