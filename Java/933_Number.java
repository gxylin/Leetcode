Write a class RecentCounter to count recent requests.

It has only one method: ping(int t), where t represents some time in milliseconds.

Return the number of pings that have been made from 3000 milliseconds ago until now.

Any ping with time in [t - 3000, t] will count, including the current ping.

It is guaranteed that every call to ping uses a strictly larger value of t than before.

 

Example 1:

Input: inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]
Output: [null,1,2,3,3]
 

Note:

Each test case will have at most 10000 calls to ping.
Each test case will call ping with strictly increasing values of t.
Each call to ping will have 1 <= t <= 10^9.

Method 1: TreeMap
Time complexity: O(logn)
class RecentCounter {
    TreeMap<Integer, Integer> map;
    public RecentCounter() {
        map = new TreeMap<>();
    }
    
    public int ping(int t) {
        Integer i = map.lowerKey(t);
        if (i == null){
            map.put(t, 1);
            return 1;
        }
        map.put(t, map.get(i) + 1);
        int start = 0;
        if (map.containsKey(t-3000)){
            start = map.get(t-3000);
            return map.get(t) - start + 1;
        }
        Integer j = map.lowerKey(t-3000);
        if (j == null){
            return map.get(t);
        }
        return map.get(t) - map.get(j);
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
