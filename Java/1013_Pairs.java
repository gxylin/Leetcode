In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of 
indices i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Note:

1 <= time.length <= 60000
1 <= time[i] <= 500


Best solution:
Similar as Leetcode 1: Two Sum; Target is 60, one number is t%60, the other is (60 - t%60)%60

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int t : time){
            int key = (60 - t%60)%60;// the second %60 to for the corner case, e.g., 60, 60, 60
            if (map.containsKey(key)){
                res += map.get(key);
            }
            map.put(t%60, map.getOrDefault(t%60, 0) + 1);
        }
        return res;
    }
}


Method 1:
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int t : time){
            map.put(t%60, map.getOrDefault(t%60, 0) + 1);
        }
        for (int key : map.keySet()){
            int cand = 60 - key;
            if (map.containsKey(cand)){
                if (key != cand){
                    count += map.get(key) * map.get(cand);
                }else{
                    int n = map.get(key);
                    count += n * (n-1);
                }
            }else if (key == 0){
                int n = map.get(key);
                count += n * (n-1);
            }
        }
        return count/2;
    }
}

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int t : time){
            map.put(t%60, map.getOrDefault(t%60, 0) + 1);
        }
        for (int key : map.keySet()){
            int cand = (60 - key) % 60;
            if (map.containsKey(cand)){
                if (key != cand){
                    count += map.get(key) * map.get(cand);
                }else{
                    int n = map.get(key);
                    count += n * (n-1);
                }
            }
        }
        return count/2;
    }
}
