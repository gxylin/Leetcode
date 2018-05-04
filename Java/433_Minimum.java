A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3

The same as word ladder
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0){
            return -1;
        }
        if (start.equals(end)){
            return 0;
        }
        Set<String> bankSet = new HashSet<>();
        for (String str : bank){
            bankSet.add(str);
        }
        if (!bankSet.contains(end)){
            return -1;
        }
        int level = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(start);
        set.add(start);
        char[] genes = {'A', 'C', 'G', 'T'};
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++){
                String str = queue.poll();
                for (int j = 0; j < str.length(); j++){
                    char[] strChar = str.toCharArray();
                    for (char ch : genes){
                        if (str.charAt(j) != ch){
                            strChar[j] = ch;
                            String s = new String(strChar);
                            if (end.equals(s)){
                                return level;
                            }
                            if (!set.contains(s) && bankSet.contains(s)){
                                queue.offer(s);
                                set.add(s);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }
}
