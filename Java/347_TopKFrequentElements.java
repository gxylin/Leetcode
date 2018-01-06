Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Method 1:
Time complexity: O(nlogn)
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
Time complexity: O(nlogn)
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
        Queue<Freq> maxHeap = new PriorityQueue<Freq>(k, new Comparator<Freq>(){
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
