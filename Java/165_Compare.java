Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Example 1:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Example 2:

Input: version1 = "1.0.1", version2 = "1"
Output: 1
Example 3:

Input: version1 = "7.5.2.4", version2 = "7.5.3"
Output: -1

Method 1: Two points
class Solution {
    public int compareVersion(String version1, String version2) {
        int p1 = 0;
        int p2 = 0;
        while (p1 < version1.length() && p2 < version2.length()){
            int v1 = 0;
            while (p1 < version1.length() && version1.charAt(p1) != '.'){
                v1 = v1 * 10 + (version1.charAt(p1) - '0');
                p1++;
            }
            int v2 = 0;
            while (p2 < version2.length() && version2.charAt(p2) != '.'){
                v2 = v2 * 10 + (version2.charAt(p2) - '0');
                p2++;
            }
            if (v1 < v2){
                return -1;
            }else if (v1 > v2){
                return 1;
            }else if (p1 == version1.length() && p2 == version2.length()){
                return 0;
            }
            p1++;
            p2++;
        }
        while (p1 < version1.length() && (version1.charAt(p1) == '0' || version1.charAt(p1) == '.')){
            p1++;
        }
        if (p1 < version1.length()){
            return 1;
        }
        while (p2 < version2.length() && (version2.charAt(p2) == '0' || version2.charAt(p2) == '.')){
            p2++;
        }
        if (p2 < version2.length()){
            return -1;
        }
        return 0;
    }
}

Method 2: Use split
Note that
String a = "9";
String[] arr = a.split("\\.");
System.out.println(arr.length); \\output: 1
System.out.println(arr[0]); \\output: 9

String a = "10.";
String[] arr = a.split("\\.");
System.out.println(arr.length);  \\output: 1
System.out.println(arr[0]); \\output: 10

String a = ".10";
String[] arr = a.split("\\.");
System.out.println(arr.length); \\output: 2
System.out.println(arr[0]);   \\output: ""
System.out.println(arr[1]);   \\output: 10
    
    
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\."); 
        String[] s2 = version2.split("\\.");
        int len = Math.max(s1.length, s2.length);
        for (int i = 0; i < len; i++){
            int v1 = i < s1.length ? Integer.parseInt(s1[i]) : 0;
            int v2 = i < s2.length ? Integer.parseInt(s2[i]) : 0;
            if (v1 < v2){
                return -1;
            }else if (v1 > v2){
                return 1;
            }
        }
        return 0;
    }
}

Note that "." won't work because . in a regular expression means "any character". So we have to use str.split("\\.");
    
To split white space, use str.split"\\s+")
