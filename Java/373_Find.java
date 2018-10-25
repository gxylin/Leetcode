You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]

Method 1: Brute force
Time complexity: O(mn)
Space complexity: O(mn)
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        int[][] nums = new int[m*n][2];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                nums[i*n+j][0] = nums1[i];
                nums[i*n+j][1] = nums2[j];
            }
        }
        Arrays.sort(nums, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] + a[1] - (b[0] + b[1]);
            }
        });
        for(int i = 0; i < k && i < m*n; i++){
            result.add(nums[i]);
        }
        return result;
    }
}

Method 2: PQ with using the given information of sorted array
Time complexity: O(k * logm)
Space complexity: O(k)

class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
         List<int[]> res = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return res;
        }
        Queue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare (int[] a, int[] b){
                return (a[0] + a[1]) - (b[0] + b[1]);
            }
        });
        for (int i = 0; i < nums1.length; i++){
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (!pq.isEmpty() && k > 0){
            int[] curr = pq.poll();
            res.add(new int[]{curr[0], curr[1]});
            int nextIdx = curr[2] + 1;
            if (nextIdx < nums2.length){
                pq.offer(new int[]{curr[0], nums2[nextIdx], nextIdx});
            }
            k--;
        }
        return res;
    }
}

Method 3: Best solution
Time complexity: O(klogk)
Space complexity: O(k)
class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0){
            return result;
        }
        Queue<int[]> minIndexHeap = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return nums1[a[0]] + nums2[a[1]] - (nums1[b[0]] + nums2[b[1]]);
            }
        });
        minIndexHeap.offer(new int[]{0, 0});
        while (!minIndexHeap.isEmpty() && k > 0){
            int[] curIndex = minIndexHeap.poll();
            result.add(new  int[]{nums1[curIndex[0]], nums2[curIndex[1]]});
            if (curIndex[1] != nums2.length - 1){
                minIndexHeap.offer(new int[]{curIndex[0], curIndex[1] + 1});
            }
            if (curIndex[0] != nums1.length - 1 && curIndex[1] == 0){
                minIndexHeap.offer(new int[]{curIndex[0] + 1, 0});
            }
            k--;
        }
        return result;
    }
}

