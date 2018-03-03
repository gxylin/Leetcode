Given n books and the ith book has A[i] pages. You are given k people to copy the n books.

n books list in a row and each person can claim a continous range of the n books. 
For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 
2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. 
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Have you met this question in a real interview? 
Example
Given array A = [3,2,4], k = 2.

Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. )

    
Method: Binary Search
O(n log m) where n is the number of books and m is the sum of the pages
public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0){
            return 0;
        }
        int total = 0;
        int max = 0;
        for (int i = 0; i < pages.length; i++){
            total += pages[i];
            if (max < pages[i]) {
                max = pages[i];
            }
        }
        int start = max;
        int end = total;
        while (start + 1 < end){
            int mid = start + (end - start) / 2;
            if (countCopies(pages, mid) > k){
                start = mid;
            }else{
                end = mid;
            }
        }
        if (countCopies(pages, start) <= k){
            return start;
        }
        return end;
    }
    private int countCopies(int[] pages, int time){
        int copies = 1;
        int sum = 0;
        for (int i = 0; i < pages.length; i++){
            if (sum + pages[i] > time){
                copies++;
                sum = 0;
            }
            sum += pages[i];
        }
        return copies;
    }
}
