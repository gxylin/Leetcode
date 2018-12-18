Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]

Method 1:
Time complexity: O(n^2)
Space complexity: O(n)
class Solution {
    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < n; i++){
            int start = (int) (charArray[i] - '0');
            int maxIndex = i;
            int max = start;
            for (int j = i+1; j < n; j++){
                int digit = (int) (charArray[j] - '0');
                if (digit >= max){
                    max = digit;
                    maxIndex = j;
                }
            }
            if (max > start){
                char temp = charArray[i];
                charArray[i] = charArray[maxIndex];
                charArray[maxIndex] = temp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(charArray));
    }
}

class Solution {
    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        char[] charArray = str.toCharArray();
        int maxIndex = n - 1;
        int minIndex = 0;
        boolean foundMax = false;
        boolean foundMin = false;
        for (int i = 0; i < n; i++){
            int max = 0;
            for (int j = n - 1; j >= i; j--){
                int digitMax = (int) (charArray[j] - '0');
                 System.out.println("digitMax:" + i + digitMax);
                System.out.println("max:" + i + max);
                if (digitMax > max){
                    max = digitMax;
                    maxIndex = j;
                    foundMax = true;
                }
            }
            for (int j = i; j < maxIndex; j++){
                int digitMin = (int) (charArray[j] - '0');
                if (digitMin < max){
                    minIndex = j;
                    foundMin = true;
                    break;
                }
            }
            if (foundMax && foundMin){
                char temp = charArray[maxIndex];
                charArray[maxIndex] = charArray[minIndex];
                charArray[minIndex] = temp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(charArray));
    }
}

Method 2: 
Time complexity: O(n)
Space complexity: O(n)
Intuition

At each digit of the input number in order, if there is a larger digit that occurs later, we know that the best swap must occur with 
the digit we are currently considering.

Algorithm

We will compute last[d] = i\text{last[d] = i}last[d] = i, the index i\text{i}i of the last occurrence of digit d\text{d}d 
(if it exists).

Afterwards, when scanning the number from left to right, if there is a larger digit in the future, we will swap it with the largest 
such digit; if there are multiple such digits, we will swap it with the one that occurs the latest.

class Solution {
    public int maximumSwap(int num) {
        char[] charArray = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        for (int i = 0; i < charArray.length; i++){
            last[charArray[i] - '0'] = i;
        }
        for (int i = 0; i < charArray.length; i++){
            for (int d = 9; d > (charArray[i] - '0'); d--){
                if (last[d] > i){
                    char temp = charArray[i];
                    charArray[i] = charArray[last[d]];
                    charArray[last[d]] = temp;
                    return Integer.valueOf(new String(charArray));
                }
            }
        }
        return num;
    }
}


Method 3: Best solution
Time complexity: O(n)
 https://leetcode.com/problems/maximum-swap/discuss/107102/Simple-AC-O(n)-java-solution-with-ex
class Solution {
    public int maximumSwap(int num) {
        //step 1: find the cutoff index which does not meet the reverse array and cut array into two parts
        char[] charArr = Integer.toString(num).toCharArray();
        int n = charArr.length;
        int index = -1;
        for (int i = 0; i < n-1; i++){
            if (charArr[i] < charArr[i+1]){
                index = i+1;
                break;
            }
        }
        if (index == -1){//already the largest
            return num;
        }
        //step 2: find the LAST (i.e. 1993)largest value in the 2nd part
        int max = -1;
        int index2 = -1;
        for (int i = index; i < n; i++){
            int val = (int)(charArr[i] - '0');
            if (val >= max){
                max = val;
                index2 = i;
            }
        }
        //step 3: find the largest value in the 1st part which is slightly less than the max, and swap them
        for (int i = 0; i < index; i++){
            int val = (int)(charArr[i] - '0');
            if (val < max){
                char temp = charArr[index2];
                charArr[index2] = charArr[i];
                charArr[i] = temp;
                break;
            }
        }
        return Integer.parseInt(new String(charArr));
    }
}
