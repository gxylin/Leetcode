In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.  

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.

 

Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation: 
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.
 

Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].

class TopVotedCandidate {
    int[] times;
    Map<Integer, Integer> map;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        map = new HashMap<>();
        int[] hash = new int[persons.length+1];
        int cand = 0;
        for (int i = 0; i < times.length; i++){
            int curr = persons[i];
            hash[curr]++;
            if (hash[curr] >= hash[cand]){
                cand = curr;
            }
            map.put(times[i],  cand);
        }
    }
    
    public int q(int t) {//binary search
        int start = 0;
        int end = times.length - 1;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (times[mid] == t){
                return map.get(times[mid]);
            }else if (times[mid] > t){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (times[end] <= t){
            return map.get(times[end]);
        }
        return map.get(times[start]);
    }
}
 
class TopVotedCandidate {
    int[] times;
    Map<Integer, Integer> map;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        map = new HashMap<>();
        int[] hash = new int[persons.length+1];
        int cand = 0;
        for (int i = 0; i < times.length; i++){
            int curr = persons[i];
            hash[curr]++;
            if (hash[curr] >= hash[cand]){
                cand = curr;
            }
            map.put(times[i],  cand);
        }
    }
    
    public int q(int t) {//binary search
        int i = Arrays.binarySearch(times, t);
        return i < 0 ? map.get(times[-i-2]) : map.get(times[i]);
    }
}
