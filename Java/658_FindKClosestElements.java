Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

Method 1:
Time complexity: O(nlogn)
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        int[] resultArr = new int[k];
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
           public int compare(Integer a, Integer b){
               if (Math.abs(b-x) != Math.abs(a-x) ){
                   return Math.abs(b-x) - Math.abs(a-x);
               }
               return b-a;
           } 
        });
        for (int i = 0; i < arr.length; i++){
            maxHeap.offer(arr[i]);
            if (maxHeap.size() > k){
                maxHeap.poll();
            }
        }
        int index = 0;
        while (!maxHeap.isEmpty()){
            resultArr[index] = maxHeap.poll();
            index++;
        }
        Arrays.sort(resultArr);
        for (int i = 0; i < k; i++){
            result.add(resultArr[i]);
        }
        return result;
    }
}
