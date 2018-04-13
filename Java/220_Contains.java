Given an array of integers, find out whether there are two distinct indices i and j in the array such that 
the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between 
i and j is at most k.


This problem requires to maintain a window of size k of the previous values that can be queried for value ranges. 
The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will result
in time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to simple 
queries can be executed both of time complexity O(lg K)

To avoid overflow, cast to long

TreeMap function: floorKey(), ceilingKey(), firstKey(), lastKey(), higherKey(), lowerKey(), pollFirstEntry(), pollLastEntry()
TreeSet function: floor(), ceiling(), first(), last(), higher(), lower(), pollFirst(), pollLast()


Method 1: TreeSet
Time complexity: O(Nlogk)
Space complexity: O(k)
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++){
            if (i > k){
                set.remove((long)nums[i-k-1]);
            }
            Long max = set.floor((long) nums[i]+t);
            Long min = set.ceiling((long)nums[i]-t);
            if (max != null && nums[i] <= max || min != null && nums[i] >= min){
                return true;
            }
            set.add((long) nums[i]);
        }
        return false;
    }
}

Method 2
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    class Pair {
        int index;
        long value;
        public Pair (int index, long value){
            this.index = index;
            this.value = value;
        }
    }
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i< nums.length; i++){
            pairs[i] = new Pair(i, (long) nums[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair>(){
           public int compare (Pair a, Pair b){
               if (a.value != b.value){
                   return (int) (a.value - b.value);
               }
               return a.index - b.index;
           } 
        });

        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length && pairs[j].value - pairs[i].value <= t; j++){
                if (Math.abs(pairs[j].index - pairs[i].index) <= k){
                    return true;
                }
            }
        }
        return false;
    }
}
