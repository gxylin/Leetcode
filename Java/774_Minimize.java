On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.

Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.

Return the smallest possible value of D.

Example:

Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
Output: 0.500000

Note:

    stations.length will be an integer in range [10, 2000].
    stations[i] will be an integer in range [0, 10^8].
    K will be an integer in range [1, 10^6].
    Answers within 10^-6 of the true value will be accepted as correct.


Binary Search : O(NLogM) N -- stations.length; M --stations[N-1] - stations[0]

if we are to have mid as the min max distance between any two buildings, this count is how many more gas stations that we can 
add to the array and we can compute this count by trying to inserting new gas stations between the 2 adjacent houses.
For any 2 adjacent gas stations, we divide their original distance by the min max distance (mid) we could have in the array, 
to try to insert as many gas stations as we can into any 2 adjacent gas stations.

this step is computing the number of houses we can insert into this original array.

class Solution {
    public double minmaxGasDist(int[] stations, int K) {
        int N = stations.length;
        double low = 0;
        double high = stations[N-1] - stations[0];
        while (low + 1e-6 < high){
            double mid = (low + high) / 2.0;
            if (possible(mid, stations, K)){
                high = mid;
            }else{
                low = mid;
            }
        }
        return low;
    }
    private boolean possible(double mid, int[] stations, int K){
        int used = 0;
        for (int i = 0; i < stations.length - 1; i++){
            used += (int) (stations[i+1] - stations[i]) / mid;
        }
        return used <= K;
    }
}

Intuition

Let's ask possible(D): with K (or less) gas stations, can we make every adjacent distance between gas stations at most D? This function is monotone, so we can apply a binary search to find D*D^{\text{*}}D​*​​.

Algorithm

More specifically, there exists some D* (the answer) for which possible(d) = False when d < D* and possible(d) = True when d > D*. 
Binary searching a monotone function is a typical technique, so let's focus on the function possible(D).

When we have some interval like X = stations[i+1] - stations[i], we'll need to use ⌊XD⌋\lfloor \frac{X}{D} \rfloor⌊​D​​X​​⌋ 
gas stations to ensure every subinterval has size less than D. This is independent of other intervals, so in total we'll 
need to use ∑i⌊XiD⌋\sum_i \lfloor \frac{X_i}{D} \rfloor∑​i​​⌊​D​​X​i​​​​⌋
gas stations. If this is at most K, then it is possible to make every adjacent distance between gas stations at most D
