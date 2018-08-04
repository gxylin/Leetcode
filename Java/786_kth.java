A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]
Note:

A will have length between 2 and 2000.
Each A[i] will be between 1 and 30000.
K will be between 1 and A.length * (A.length - 1) / 2.

https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378

Method 1:
Time complexity: O(klogn)
Space complexity: O(n)
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length;
        Queue<int[]> minPQ = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare (int[] p1, int[] p2){
                return A[p1[0]] * A[p2[1]] - A[p2[0]] * A[p1[1]];
            }
        });
        for (int i = 0; i < n - 1; i++){
            minPQ.offer(new int[]{i, n-1});
        }
        for (int i = 0; i < K-1; i++){
            int[] p = minPQ.poll();
            if (A[p[1] - 1] > A[p[0]]){
                minPQ.offer(new int[]{p[0], p[1] - 1});
            }
        }
        int[] res = minPQ.poll();
        return new int[]{A[res[0]], A[res[1]]};
    }
}

Method 2:
Time complexity: O(nLogmaxProd)
Space complexity: O(1)
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int prod = 1;
        for (int i : A){
            prod *= i;
        }
        System.out.println(prod);
        int[] res = new int[2];
        int low = prod/A[A.length-1];
        int high = prod;
        while (low <= high){
            int mid = low + (high - low) / 2;
            int count = getLessEqual(A, mid, prod);
            System.out.println(count);
            System.out.println("low = " + low);
            System.out.println("high = " + high);
            if (count <= K-1){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        System.out.println("k th smallest = " + low);
        for (int j = 0; j < A.length; j++){
            if ((A[j] * low) % prod == 0){
                res[0] = (A[j] * low) / prod;
                res[1] = A[j];
              //  break;
            } 
        }
        return res;
    }
    private int getLessEqual(int[] A, int val, int prod){
       int res = 0;
        int n = A.length;
        for (int i = 0; i < A.length; i++){
            int j = n-1;
            while (j >= i+1 && prod * A[i] <= A[j] * val){
                j--;
            }
            res += n-j-1;
        }
        return res;
    }
}
