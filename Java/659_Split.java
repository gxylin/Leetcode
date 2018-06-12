Intuition

Call a chain a sequence of 3 or more consecutive numbers.

Considering numbers x from left to right, if x can be added to a current chain, it's at least as good to add x to that chain first, rather than to start a new chain.

Why? If we started with numbers x and greater from the beginning, the shorter chains starting from x could be concatenated with the chains ending before x, possibly helping us if there was a "chain" from x that was only length 1 or 2.

Algorithm

Say we have a count of each number, and let tails[x] be the number of chains ending right before x.

Now let's process each number. If there's a chain ending before x, then add it to that chain. Otherwise, if we can start a new chain, do so.

It's worth noting that our solution can be amended to take only O(1)O(1)O(1) additional space, since we could do our counts similar to Approach #1, and we only need to know the last 3 counts at a time.

1. We iterate through the array once to get the frequency of all the elements in the array
2. We iterate through the array once more and for each element we either see if it can be appended to a previously constructed consecutive sequence or if it can be the start of a new consecutive sequence. If neither are true, then we return false.

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
