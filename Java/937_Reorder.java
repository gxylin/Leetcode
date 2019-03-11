You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word 
after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically 
ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 

Note:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.


class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, new Comparator<String>(){
            public int compare (String log1, String log2){
                String[] s1 = log1.split(" ");
                String[] s2 = log2.split(" ");
                boolean s1Digit = Character.isDigit(s1[1].charAt(0));
                boolean s2Digit = Character.isDigit(s2[1].charAt(0));
                                                    
                if (s1Digit && s2Digit){
                    return 0;
                }else if (s1Digit && !s2Digit){
                    return 1;
                }else if (!s1Digit && s2Digit){
                    return -1;
                }else{
                    int index1 = log1.indexOf(" ");
                    String str1 = log1.substring(index1 + 1);
                    int index2 = log2.indexOf(" ");
                    String str2 = log2.substring(index2+ 1);
                    if (str1.equals(str2)){
                        return log1.substring(0, index1).compareTo(log2.substring(0, index2));
                    }
                    return str1.compareTo(str2);
                }
            }
        });
        return logs;
    }
}
