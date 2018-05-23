Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]

Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]


Method 1:
Time complexity: O(nk)
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int k = updates.length;
        int[] result = new int[length];
        for (int i = 0; i < k; i++){
            for (int j = updates[i][0] ; j <= updates[i][1]; j++){
                result[j] += updates[i][2];
            }
        }
        return result;
    }
}

Method 2: Range Caching
Time complexity: O(n+k)
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int k = updates.length;
        int[] result = new int[length];
        for (int i = 0; i < k; i++){
            int start = updates[i][0];
            int end = updates[i][1];
            result[start] += updates[i][2];
            if (end < length - 1){
                result[end+1] -= updates[i][2];
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++){
            sum += result[i];
            result[i] = sum;
        }
        return result;
    }
}
Intuition

    There is only one read query on the entire range, and it occurs at the end of all update queries. Additionally, the order of processing update queries is irrelevant.

    Cumulative sums or partial_sum operations apply the effects of past elements to the future elements in the sequence.

Algorithm

The algorithm makes use of the above intuition to simply store changes at the borders of the update ranges (instead of processing the entire range). Finally a single post processing operation is carried out over the entire output array.

The two steps that are required are as follows:

    For each update query (start,end,val)(start, end, val)(start,end,val) on the array arrarrarr, we need to do only two operations:
        Update startstartstart boundary of the range:

    arrstart=arrstart+val arr_{start} = arr_{start} + val arr​start​​=arr​start​​+val
        Update just beyond the endendend boundary of the range:

    arrend+1=arrend+1−val arr_{end+1} = arr_{end+1} - val arr​end+1​​=arr​end+1​​−val

    Final Transformation. The cumulative sum of the entire array is taken (0 - based indexing)

    arri=arri+arri−1∀i∈[1,n) arr_i = arr_i + arr_{i-1} \quad \forall \quad i \in [1, n) arr​i​​=arr​i​​+arr​i−1​​∀i∈[1,n)
For each update query (start,end,val)(start, end, val)(start,end,val) on the array arrarrarr, the goal is to achieve the result:

arri=arri+val∀i∈[start,end] arr_i = arr_i + val \quad \forall \quad i \in [start, end] arr​i​​=arr​i​​+val∀i∈[start,end]

Applying the final transformation, ensures two things:

    It carries over the +val+val+val increment over to every element arri∀i≥start arr_i \; \forall \; i \ge start arr​i​​∀i≥start.
    It carries over the −val-val−val increment (equivalently, a +val+val+val decrement) over to every element arrj∀j>end arr_j \; \forall \; j \gt end arr​j​​∀j>end.

The net result is that:

arriarrj=arri+val=arrj+val−val=arrj∀i∈[start,end]∀i∈(end,length)

which meets our end goal. It is easy to see that the updates over a range did not carry over beyond it due to the compensating effect of the −val-val−val increment over the +val+val+val increment.

It is good to note that this works for multiple update queries because the particular binary operations here (namely addition and subtraction):

    are closed over the entire domain of Integers. (A counter example is division which is not closed over all Integers).

    are complementary operations. (As a counter example multiplication and division are not always complimentary due to possible loss of precision when dividing Integers).
