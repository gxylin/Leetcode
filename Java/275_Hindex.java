
Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?


The idea is to search for the first index from the sorted array so that :

citations[index] >= length(citations) - index. 

And return (length - index) as the result.

class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0){
            return 0;
        }
        int start = 0;
        int end = citations.length - 1;
        int len = citations.length;
        int target = 0;
        while (start <= end){
            int mid = start + (end - start)/2;
            target = len - mid;
            if (citations[mid] >= target){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return len - start;
    }
}
