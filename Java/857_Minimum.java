There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

 

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.

class Solution {
    class Worker{
        int q;
        double ratio;
        public Worker(int q, double ratio){
            this.q = q;
            this.ratio = ratio;
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        //Step 1: sort by ratio
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i){
            workers[i] = new Worker(quality[i], (double) (wage[i]) / quality[i]);
        }
        Arrays.sort(workers, new Comparator<Worker>(){
            public int compare (Worker w1, Worker w2){//must be int, can't be double
                //return (int)(w1.ratio - w2.ratio); //not working
                return Double.compare(w1.ratio, w2.ratio);
            }
        });
       // Arrays.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio)); // Double.compare static function returns int
        
        
        //Step 2: maintain max priority queue for quality, use negative value and minPQ to implement max PQ
        Queue<Integer> minPQ = new PriorityQueue<>();
        double res = Double.MAX_VALUE;
        int sumQ = 0;
        for (Worker worker : workers){
            sumQ += worker.q;
            minPQ.offer(-worker.q);
            if (minPQ.size() > K){
                sumQ += minPQ.poll();
            }
            if (minPQ.size() == K){
                res = Math.min(res, sumQ * worker.ratio);
            }
        }
        return res;
    }
}
