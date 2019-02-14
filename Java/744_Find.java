Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.

Method: Binary Search
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        while (left + 1 < right){
            int mid = left + (right - left) / 2;
            if (letters[mid] == target){
                left = mid;
            }else if (letters[mid] < target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if (target < letters[left]){
            return letters[left];
        }else if (target < letters[right]){
            return letters[right];
        }
        return right + 1 < letters.length ? letters[right+1] : letters[0];
    }
}


The pattern is 0001111
    binary search to find the first 1
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end){
            int mid = start + (end - start)/ 2;
            char c = letters[mid];
            if (c == target){
                start = mid;
            }else if (c > target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if (letters[start] > target){
            return letters[start];
        }
        if (letters[end] > target){
            return letters[end];
        }
        return letters[0];
    }
}

