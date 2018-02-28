Numbers keep coming, return the median of numbers at every time a new number added.

Have you met this question in a real interview? 
Clarification
What's the definition of Median?
- Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, 
the median is A[(n - 1) / 2]. For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.

Example
For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].

For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].

For numbers coming list: [2, 20, 100], return [2, 2, 20].

Challenge 
Total run time in O(nlogn).

public class Solution {
    /*
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        if (nums == null || nums.length == 0){
            return null;
        }
        Queue<Integer> minHeap = new PriorityQueue<Integer>();
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(1, new Comparator<Integer>(){
           public int compare(Integer a, Integer b){
               return b - a;
           } 
        });
        
        int[] result = new int[nums.length];
        result[0] = nums[0];
        maxHeap.offer(nums[0]);
        
        for (int i = 1; i < nums.length; i++){
            int prevMedian = maxHeap.peek();
            if (nums[i] > prevMedian){
                minHeap.offer(nums[i]);
            }else{
                maxHeap.offer(nums[i]);
            }
            if (maxHeap.size() > minHeap.size() + 1){
                minHeap.offer(maxHeap.poll());
            }
            if (maxHeap.size() < minHeap.size()){
                maxHeap.offer(minHeap.poll());
            }
            result[i] = maxHeap.peek();
        }
        return result;
    }
}
