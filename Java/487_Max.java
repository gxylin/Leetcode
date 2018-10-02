 Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:

Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.

Note:

    The input array will only contain 0 and 1.
    The length of input array is a positive integer and will not exceed 10,000

Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently? 

Method 1:
Use variable index to record the position of zero, and initiate the index to -1. Once we find a zero, refresh the count and store the new index.

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        int index = -1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == 1){
                count++;
            }else{
                count = i - index;
                index = i;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}

Method 2 (better solution): sliding window to cover follow up
keep a window [l, h] that contains at most k zero
Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int zeros = 0;
        int k = 1;//flip most 
        int low = 0;
        int high = 0;
        while (high < nums.length){
            if (nums[high] == 0){
                zeros++;
            }
            while (zeros > k){
                if (nums[low] == 0){
                    zeros--;
                }
                low++;
            }
            max = Math.max(max, high - low + 1);
            high++;
        }
        return max;
    }
}
Now let's deal with follow-up, we need to store up to k indexes of zero within the window [l, h] so that we know where to move l next when the window contains more than k zero. If the input stream is infinite, then the output could be extremely large because there could be super long consecutive ones. In that case we can use BigInteger for all indexes. For simplicity, here we will use int
Time: O(n) Space: O(k)
Follow up
public int findMaxConsecutiveOnes(int[] nums) {                 
        int max = 0, k = 1; // flip at most k zero
        Queue<Integer> zeroIndex = new LinkedList<>(); 
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0)
                zeroIndex.offer(h);
            if (zeroIndex.size() > k)                                   
                l = zeroIndex.poll() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;                     
    }
    
    Note that setting k = 0 will give a solution to the earlier version Max Consecutive Ones

For k = 1 we can apply the same idea to simplify the solution. Here q stores the index of zero within the window [l, h] so its role is similar to Queue in the above solution

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, q = -1;
        for (int l = 0, h = 0; h < nums.length; h++) {
            if (nums[h] == 0) {
                l = q + 1;
                q = h;
            }
            max = Math.max(max, h - l + 1);
        }                                                               
        return max;             
    }
