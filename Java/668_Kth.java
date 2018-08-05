Nearly every one have used the Multiplication Table. But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k, you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 
Explanation: 
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]

Method 1: Binary Search
Time complexity: O(m*log(m*n)
Space complexity: O(1)
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1;
        int high = m * n;
        while (low <= high){
            int mid = low + (high - low)/ 2;
            int count = getLessEqual(m, n, mid);
            if (count < k){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
    private int getLessEqual(int m, int n, int val){
        int res = 0;
        for (int i = 1; i <= m; i++){
            res += Math.min(val / i, n);
        }
        return res;
    }
    
}

class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1;
        int high = m * n;
        while (low <= high){
            int mid = low + (high - low)/ 2;
            int count = getLessEqual(m, n, mid);
            if (count < k){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return low;
    }
    private int getLessEqual(int m, int n, int val){
        int res = 0;
        for (int i = 1; i <= m; i++){
            int low = 1;
            int high = n;
            while (low <= high){
                int mid = low + (high - low) / 2;
                if (mid * i <= val){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
            res += low - 1;
        }
        return res;
    }
}

Method 2: Heap TLE
Time complexity: O(k*m*logm)
Space complexity: O(m)
class Solution {
    class Pair {
        int x;
        int y;
        public Pair (int x, int y){
            this.x = x;
            this. y = y;
        }
    }
    public int findKthNumber(int m, int n, int k) {
        Queue<Pair> minPQ = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare (Pair p1, Pair p2){
                return p1.x * p1.y - p2.x * p2.y;
            }
        });
        for (int i = 1; i <= m; i++){
            minPQ.offer(new Pair(i, 1));
        }
        for (int i = 0; i < k - 1; i++){
            Pair p = minPQ.poll();
            if (p.y < n){
                minPQ.offer(new Pair(p.x, p.y + 1));
            }
        }
        Pair res = minPQ.poll();
        return res.x * res.y;
    }
}
