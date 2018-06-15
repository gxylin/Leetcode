Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.

Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
Example 2:
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
Note:

s.length will be between 1 and 50,000.
s will only consist of "0" or "1" characters.

Method 1: Brute Force
Time complexity: worst O(n^2) because it has backward
Space complexity: O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int start = 0;
        int end = 0;
        int i = 0;
        int ans = 0;
        while (i < s.length()){
            int startCount = 0;
            int endCount = 0;
            while (i < s.length() && s.charAt(i) == s.charAt(start)){
                i++;
                startCount++;
            }
            end = i;
            while (i < s.length() && s.charAt(i) == s.charAt(end)){
                i++;
                endCount++;
                if (endCount <= startCount){
                    ans++;
                }
            }
            start = end;
            i = end;
        }
        return ans;
    }
}

Method 2: Group By Character
Time complexity: O(n)
Space complexity: O(n)
class Solution {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] group = new int[s.length()];
        int t = 0;
        group[0] = 1;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == s.charAt(i-1)){
                group[t]++;
            }else{
                group[++t] = 1;
            }
        }
        int ans = 0;
        for (int i = 1; i < group.length; i++){
            ans += Math.min(group[i-1], group[i]);
        }
        return ans;
    }
}

Method 3: Best solution, based on method 2, just remember to add the last item Math.min(prev, curr) because it is out of loop, the
calculate is not triggered.

Time complexity: O(n)
Space complexity: O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        int prev = 0;
        int curr = 1;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == s.charAt(i-1)){
                curr++;
            }else{
                ans += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            }
        }
        return ans + Math.min(prev, curr);
    }
}

