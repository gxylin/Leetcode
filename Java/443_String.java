Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000.

Method 1: 
Space complexity: O(1)
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0){
            return 0;
        }
        int ans = 0;
        char prev = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++){
            if (chars[i] == prev){
                count++;
            }else{
                chars[ans++] = prev;
                if (count > 1){
                    int tmp = count;
                    int n = 1;
                    while (tmp >= 10){
                        n *= 10;
                        tmp /= 10;
                    }
                    while (count > 0){
                        int digit = count / n;
                        chars[ans++] = (char)(digit + '0');
                        count %= n;
                        n /= 10;
                    }
                }
                count = 1;
            }
            prev = chars[i];
        }
        if (count > 1){
            chars[ans++] = prev;
            if (count > 1){
                int tmp = count;
                int n = 1;
                while (tmp >= 10){
                    n *= 10;
                    tmp /= 10;
                }
                while (n > 0){
                    int digit = count / n;
                    chars[ans++] = (char)(digit + '0');
                    count %= n;
                    n /= 10;
                }
            }
            return ans;
        }
        chars[ans++] = prev;
        return ans;
    }
}

Method 2: better
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0){
            return 0;
        }
        int ans = 0;
        int i = 0;
        while (i < chars.length){
            char curr = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == curr){
                count++;
                i++;
            }
            chars[ans++] = curr;
            if (count > 1){
                int tmp = count;
                int n = 1;
                while (tmp >= 10){
                    n *= 10;
                    tmp /= 10;
                }
                while (n > 0){
                    int digit = count / n;
                    chars[ans++] = (char)(digit + '0');
                    count %= n;
                    n /= 10;
                }
            }
        }
        return ans;
    }
}

Method 3: Best use Integer.toString(count).toCharArray()
class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0){
            return 0;
        }
        int ans = 0;
        int i = 0;
        while (i < chars.length){
            char curr = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == curr){
                count++;
                i++;
            }
            chars[ans++] = curr;
            if (count > 1){
                for (char c : Integer.toString(count).toCharArray()){
                    chars[ans++] = c;
                }
            }
        }
        return ans;
    }
}


Method 4: 
class Solution {
    public int compress(char[] chars) {
        boolean newchar = true;
        int index = 0;
        int i = 0;
        while (i < chars.length){
            if (newchar){
                chars[index] = chars[i];
                if (i < chars.length - 1 && chars[i+1] == chars[i]){
                    newchar = false;
                }
                index++;
                i++;
            }else{
                int count = 1;
                while(i < chars.length && chars[i] == chars[i-1]){
                    count++;
                    i++;
                }
                String str = String.valueOf(count);
                for (int j = 0; j < str.length(); j++){
                    chars[index++] = str.charAt(j);
                }
                newchar = true;
            }
        }
        return index;
    }
}



Best solution: in place
class Solution {
    public int compress(char[] chars) {
        int index = 0;
        int i = 0;
        while (i < chars.length){
            char curr = chars[i];
            int count = 0;
            while (i < chars.length && chars[i] == curr){
                count++;
                i++;
            }
            chars[index++] = curr;
            if (count > 1){
                String str = String.valueOf(count);
                for (char c : str.toCharArray()){
                    chars[index++] = c;
                }
            }
        }
        return index;
    }
}
