Given a string and a string dictionary, find the longest string in the dictionary that can be formed by
deleting some characters of the given string. If there are more than one possible results, return the longest
word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

Method 1: sorting
Time complexity: O(x*n*logn + n*x) Here nn refers to the number of strings in list dd and xx refers to average string length.
class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>(){
            public int compare (String a, String b){
                if (a.length() == b.length()){
                    return a.compareTo(b);
                }
                return b.length() - a.length();
            }
        });
        for (String str : d){
            int i = 0; // str string
            int j = 0; // s string
            while (i < str.length() && j < s.length()){
                while (j < s.length() && str.charAt(i) != s.charAt(j)){
                    j++;
                }
                if (j == s.length() && i < str.length()){
                    break;
                }
                i++;
                j++;
            }
            if (i == str.length()){
                return str;
            }
        }
        return "";
    }
}

Method 2:
class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>(){
            public int compare (String a, String b){
                if (a.length() == b.length()){
                    return a.compareTo(b);
                }
                return b.length() - a.length();
            }
        });
        for (String str : d){
            int i = 0; // str string
            int j = 0; // s string
            while (i < str.length() && j < s.length()){
                if (str.charAt(i) == s.charAt(j)){
                    i++;
                }
                j++;
            }
            if (i == str.length()){
                return str;
            }
        }
        return "";
    }
}

Method 3: without sorting
Time complexity: O(n*x) Here nn refers to the number of strings in list dd and xx refers to average string length.
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String ans = "";
        for (String str : d){
            int i = 0; // str string
            int j = 0; // s string
            while (i < str.length() && j < s.length()){
                if (str.charAt(i) == s.charAt(j)){
                    i++;
                }
                j++;
            }
            if (i == str.length() && str.length() >= ans.length()){
                if (str.length() > ans.length() || str.compareTo(ans) < 0){
                    ans = str;
                }
            }
        }
        return ans;
    }
}
