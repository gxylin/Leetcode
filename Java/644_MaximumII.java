Given an array consisting of n integers, find the contiguous subarray of given length k that
has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

Method 1: sliding window in check function
public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        double error = Integer.MAX_VALUE;
        while (error > 1e-6){
            double mid = (max + min) / 2.0;
            if (checkLargerThanMid(nums, mid, k)){
                min = mid;
            }else{
                max = mid;
            }
            error = max - min;
        }
        return min;
    }
    private boolean checkLargerThanMid(int[] nums, double mid, int k){
        int n = nums.length;
        double sum = 0;
        double min = 0.0;
        double prev = 0.0;
        for (int i = 0; i < k; i++){
            sum += nums[i] - mid;
        }
        if (sum >= 0){
            return true;
        }
        for (int i = k; i < n; i++){
            sum += nums[i] - mid;
            prev += nums[i-k] - mid;
            min = Math.min(min, prev);
            if (sum - min >= 0){
                return true;
            }
        }
        return false;
    }
}

Method 2: prefix sum in check function
public class Solution {
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // Write your code here
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }
        
       
        while (r - l >= 1e-6) {
            double mid = (l + r) / 2.0;

            if (check_valid(nums, mid, k)) {
                l = mid;
            }
            else {
                r = mid;
            }
        }

        return l;
    }
    
    private boolean check_valid(int nums[], double mid, int k) {
        int n = nums.length;
        double min_pre = 0;
        double[] sum = new double[n + 1];
        sum[0] = 0; 
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1] - mid;
            if (i >= k && sum[i] - min_pre >= 0) {
                return true;
            }
            if (i >= k)
                min_pre = Math.min(min_pre, sum[i - k + 1]);
        }
        return false;
    }
}





To understand the idea behind this method, let's look at the following points.

Firstly, we know that the value of the average could lie between the range (min,max)(min, max)(min,max). 
Here, minminmin and maxmaxmax refer to the minimum and the maximum values out of the given numsnumsnums array.
This is because, the average can't be lesser than the minimum value and can't be larger than the maximum value.

But, in this case, we need to find the maximum average of a subarray with atleast kkk elements. The idea in this 
method is to try to approximate(guess) the solution and to try to find if this solution really exists.

If it exists, we can continue trying to approximate the solution even to a further precise value, but choosing a 
larger number as the next approximation. But, if the initial guess is wrong, and the initial maximum average
value(guessed) isn't possible, we need to try with a smaller number as the next approximate.

Now, instead of doing the guesses randomly, we can make use of Binary Search. With minminmin and maxmaxmax 
as the initial numbers to begin with, we can find out the midmidmid of these two numbers given by 
(min+max)/2(min+max)/2(min+max)/2. Now, we need to find if a subarray with length greater than or equal 
to kkk is possible with an average sum greater than this midmidmid value.

To determine if this is possible in a single scan, let's look at an observation. Suppose, 
there exist jjj elements, a1,a2,a3...,aja_1, a_2, a_3..., a_ja​1​​,a​2​​,a​3​​...,a​j​​ in a subarray 
within numsnumsnums such that their average is greater than midmidmid. In this case, we can say that

(a1+a2+a3...+aj)/j≥mid

or

(a1+a2+a3...+aj)≥j∗mid

or

(a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid)≥0

Thus, we can see that if after subtracting the midmidmid number from the elements of a subarray with 
more than k−1k-1k−1 elements, within numsnumsnums, if the sum of elements of this reduced subarray is
greater than 0, we can achieve an average value greater than midmidmid. Thus, in this case, we need to 
set the midmidmid as the new minimum element and continue the process.

Otherwise, if this reduced sum is lesser than 0 for all subarrays with greater than or equal to kkk elements, 
we can't achieve midmidmid as the average. Thus, we need to set midmidmid as the new maximum element and 
continue the process.

In order to determine if such a subarray exists in a linear manner, we keep on adding nums[i]−midnums[i]-midnums[i]−mid 
to the sumsumsum obtained till the ithi^{th}i​th​​ element while traversing over the numsnumsnums array. 
If on traversing the first kkk elements, the sumsumsum becomes greater than or equal to 0, we can directly
determine that we can increase the average beyond midmidmid. Otherwise, we continue making additions to sumsumsum 
for elements beyond the kthk^{th}k​th​​ element, making use of the following idea.

If we know the cumulative sum upto indices iii and jjj, say sumisum_isum​i​​ and sumjsum_jsum​j​​ respectively,
we can determine the sum of the subarray between these indices(including jjj) as sumj−sumisum_j - sum_isum​j​​−sum​i​​. 
In our case, we want this difference between the cumulative sums to be greater than or equal to 0 as discusssed above.

Further, for sumisum_isum​i​​ as the cumulative sum upto the current(ithi^{th}i​th​​) index, all we need is sumj−sumi≥0
such that j−i≥k

.

To achive this, instead of checking with all possible values of sumisum_isum​i​​, we can just 
onsider the minimum cumulative sum upto the index j−kj - kj−k. This is because if the required 
condition can't be sastisfied with the minimum sumisum_isum​i​​, it can never be satisfied with a larger value.

To fulfil this, we make use of a prevprevprev variable which again stores the cumulative sums but,
its current index(for cumulative sum) lies behind the current index for sumsumsum at an offset of kkk units. 
Thus, by finding the minimum out of prevprevprev and the last minimum value, we can easily find out the required minimum sum value.

Every time after checking the possiblility with a new midmidmid value, at the end, we need to settle at some 
value as the average. But, we can observe that eventually, we'll reach a point, where we'll keep moving near 
some same value with very small changes. In order to keep our precision in control, we limit this process to
10−510^-510​−​​5 precision, by making use of errorerrorerror and continuing the process till errorerrorerror 
becomes lesser than 0.00001 .
