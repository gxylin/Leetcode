Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.

 

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
 

Note:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length

Method 1:
Time complexity: O(NlogN + NWlogW) N is the number of cards
Space complexity: O(N) number of cardsd
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        int n = hand.length;
        if (n % W != 0){
            return false;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : hand){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int key : map.keySet()){
            while (map.get(key) > 0){
                for (int i = 0; i < W; i++){
                    int index = key + i;
                    if (!map.containsKey(index) || map.get(index) <= 0){
                        return false;
                    }
                    map.put(index, map.get(index) - 1);
                }
            }
        }
        return true;
    }
}

Method 2:
Time complexity: O(NlogN + MWLogW) M is number of different cards
class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        int n = hand.length;
        if (n % W != 0){
            return false;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i : hand){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int key : map.keySet()){
            if (map.get(key) > 0){ // remove one loop
                for (int i = W - 1; i >= 0; i--){
                    int index = key + i;
                    if (!map.containsKey(index) || map.get(index) < map.get(key)){
                        return false;
                    }
                    map.put(index, map.get(index) - map.get(key));
                }
            }
        }
        return true;
    }
}

