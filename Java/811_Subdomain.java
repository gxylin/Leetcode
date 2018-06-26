A website domain like "discuss.leetcode.com" consists of various subdomains. At the top level, we have "com", at the next level, we have "leetcode.com", and at the lowest level, "discuss.leetcode.com". When we visit a domain like "discuss.leetcode.com", we will also visit the parent domains "leetcode.com" and "com" implicitly.

Now, call a "count-paired domain" to be a count (representing the number of visits this domain received), followed by a space, followed by the address. An example of a count-paired domain might be "9001 discuss.leetcode.com".

We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, (in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Example 1:
Input: 
["9001 discuss.leetcode.com"]
Output: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
Explanation: 
We only have one website domain: "discuss.leetcode.com". As discussed above, the subdomain "leetcode.com" and "com" will also be visited. So they will all be visited 9001 times.

Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: 
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.

Notes:

The length of cpdomains will not exceed 100. 
The length of each domain name will not exceed 100.
Each address will have either 1 or 2 "." characters.
The input count in any count-paired domain will not exceed 10000.
The answer output can be returned in any order.

Method 1:
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str : cpdomains){
            String[] arr = str.split("\\s+");
            String subdomain = arr[1];
            int num = Integer.parseInt(arr[0]);
            map.put(subdomain, map.getOrDefault(subdomain, 0) + num);
            int start = subdomain.lastIndexOf(".");
            while (start != -1){
                String sub = subdomain.substring(start+1);
                map.put(sub, map.getOrDefault(sub, 0) + num);
                start = subdomain.lastIndexOf(".", start-1);
            }
        }
        for (String key : map.keySet()){
            String str = map.get(key) + " " + key;
            res.add(str);
        }
        return res;
    }
}

Method 2:
note to split ".", must use str.split("\\.");
there are 12 characters with special meanings: the backslash \, the caret ^, the dollar sign $, the period or dot ., 
the vertical bar or pipe symbol |, the question mark ?, the asterisk or star *, the plus sign +, the opening parenthesis (, 
the closing parenthesis ), and the opening square bracket [, the opening curly brace {, These special characters are often 
called "metacharacters".
https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str : cpdomains){
            String[] arr = str.split("\\s+");
            String subdomain = arr[1];
            int num = Integer.parseInt(arr[0]);
            String[] frags = subdomain.split("\\."); // can't use "."
            String curr = "";
            for (int i = frags.length - 1; i >= 0; i--){
                curr = frags[i] + (i == frags.length - 1 ? "" : ".") + curr;
                map.put(curr, map.getOrDefault(curr, 0) + num);
            }
        }
        for (String key : map.keySet()){
            String str = map.get(key) + " " + key;
            res.add(str);
        }
        return res;
    }
}
