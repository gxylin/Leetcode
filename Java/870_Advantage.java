Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9


Method 1: TreeMap
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int[] res = new int[A.length];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : A){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < B.length; i++){
            Integer key = map.higherKey(B[i]);
            if (key == null){
                key = map.firstKey();
            }
            res[i] = key;
            map.put(key, map.get(key) - 1);
            if (map.get(key) == 0){
                map.remove(key);
            }
        }
        return res;
    }
}

Method 2: PQ
Time complexity: O(nlogn)
Space complexity: O(n)
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int n = A.length;
        int[] res = new int[n];
        Arrays.sort(A);
        Queue<int[]> maxQ = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare (int[] p1, int[] p2){
                return p2[0] - p1[0];
            }
        });
        for (int i = 0; i < n; i++){
            maxQ.offer(new int[]{B[i], i});
        }
        int low = 0;
        int high = n - 1;
        while (!maxQ.isEmpty()){
            int[] p = maxQ.poll();
            int val = p[0];
            int index = p[1];
            if (A[high] > val){
                res[index] = A[high];
                high--;
            }else{
                res[index] = A[low];
                low++;
            }
        }
        return res;
    }
}
