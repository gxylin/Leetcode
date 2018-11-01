Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Method 1:
Time complexity: O(nlogk)
class Solution {
    class Freq{
        int val;
        int freq;
        public Freq(int val, int freq){
            this.val = val;
            this.freq = freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        Queue<Freq> minHeap = new PriorityQueue<Freq>(k, new Comparator<Freq>(){
            public int compare(Freq a, Freq b){
                return a.freq - b.freq;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (minHeap.size() < k){
                minHeap.offer(new Freq(entry.getKey(), entry.getValue()));
            }else{
                if (entry.getValue() > minHeap.peek().freq){
                    minHeap.poll();
                    minHeap.offer(new Freq(entry.getKey(), entry.getValue()));
                }
            }
        }
        while (!minHeap.isEmpty()){
            result.add(minHeap.poll().val);
        }
        return result;
    }
}

Method 2:
Time complexity: O(nlogk)
class Solution {
    class Freq{
        int val;
        int freq;
        public Freq(int val, int freq){
            this.val = val;
            this.freq = freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            return result;
        }
        Queue<Freq> maxHeap = new PriorityQueue<Freq>(nums.length, new Comparator<Freq>(){
            public int compare(Freq a, Freq b){
                return b.freq - a.freq;
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            maxHeap.offer(new Freq(entry.getKey(), entry.getValue()));
        }
        for (int i = 0; i < k; i++){
            result.add(maxHeap.poll().val);
        }
        return result;
    }
}

class Solution {
    class Pair{
        String str;
        int freq;
        public Pair(String str, int freq){
            this.str = str;
            this.freq = freq;
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Queue<Pair> maxQueue = new PriorityQueue<Pair>(new Comparator<Pair>(){
           public int compare(Pair a, Pair b){
               if (a.freq != b.freq){
                   return b.freq - a.freq;
               }
               return a.str.compareTo(b.str);
           } 
        });
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (String s : map.keySet()){
            maxQueue.offer(new Pair(s, map.get(s)));
        }
        for (int i = 0; i < k; i++){
            result.add(maxQueue.poll().str);
        }
        return result;
    }
}




Better:
Time complexity: O(nlogk)
class Solution {
    class Pair {
        int val;
        int freq;
        public Pair (int val, int freq){
            this.val = val;
            this.freq = freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p2.freq - p1.freq;
            }
        });
        for (int key : map.keySet()){
            pq.offer(new Pair(key, map.get(key)));
        }
        int i = 0;
        while (!pq.isEmpty() && i < k){
            Pair p = pq.poll();
            res.add(p.val);
            i++;
        }
        return res;
    }
}

Best:
Time complexity: O(klogk)
class Solution {
    class Pair {
        int val;
        int freq;
        public Pair (int val, int freq){
            this.val = val;
            this.freq = freq;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Queue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.freq - p2.freq;
            }
        });
        for (int key : map.keySet()){
            if (pq.size() < k){
                pq.offer(new Pair(key, map.get(key)));
            }else{
                if (pq.peek().freq < map.get(key)){
                    pq.poll();
                    pq.offer(new Pair(key, map.get(key)));
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        while (!pq.isEmpty()){
            stack.push(pq.poll().val);
        }
        while (!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;
    }
}
