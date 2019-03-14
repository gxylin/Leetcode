A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:

For 1-byte character, the first bit is a 0, followed by its unicode code.
For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
This is how the UTF-8 encoding would work:

   Char. number range  |        UTF-8 octet sequence
      (hexadecimal)    |              (binary)
   --------------------+---------------------------------------------
   0000 0000-0000 007F | 0xxxxxxx
   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Given an array of integers representing the data, return whether it is a valid utf-8 encoding.

Note:
The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. This means 
each integer represents only 1 byte of data.

Example 1:

data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.

Return true.
It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
Example 2:

data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.

Return false.
The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
The next byte is a continuation byte which starts with 10 and that's correct.
But the second continuation byte does not start with 10, so it is invalid.

https://leetcode.com/problems/utf-8-validation/discuss/87485/O(n)-JAVA-solution-with-detailed-explaination

* Method: 
     * Start from index 0, determine each byte's type and check its validity.
     *
     * There are five kinds of valid byte type: 0**, 10**, 110**,1110** and 11110**
     * Give them type numbers, 0, 1, 2, 3, 4 which are the index of the first 0 from left. 
     * So, the index of the first 0 determines the byte type.
     *
     * if a byte belongs to one of them:
        1 : if it is type 0, continue
        2 : if it is type 2 or 3 or 4, check whether the following 1, 2, and 3 byte(s) are of type 1 or not
                if not, return false;
     * else if a byte is type 1 or not of valid type, return false
        
 Type 0:  0xxxxxxx
 Type 1:  10xxxxxx
 Type 2:  110xxxxx
 Type 3:  1110xxxx
 Type 4:  11110xxx
 
Palantir and FB phone interview Question
class Solution {
    private int[] mask = {128, 64, 32, 16, 8};
    public boolean validUtf8(int[] data) {
        int len = data.length;
        for (int i = 0; i < len; i++){
            int num = data[i];
            int type = getType(num);
            if (type == 0){
                continue;
            }else if (type > 1 && type + i <= len){
                while (type > 1){
                    i++;
                    int next = getType(data[i]);
                    if (next != 1){
                        return false;
                    }
                    type--;
                }
            }else{
                return false;
            }
        }
        return true;
    }
    private int getType(int num){
        for (int i = 0; i < mask.length; i++){
            if ((mask[i] & num) == 0){
                return i;
            }
        }
        return -1;
    }
}


Palantir Phone interview: when input is string instead of int[] data
class Solution {
    private int[] mask = {128, 64, 32, 16, 8};
    public boolean validUtf8(String str) {
        if (str == null || str.length() == 0){
            return false;
        }
        if (str.length() % 8 != 0){
            return false;
        }
        int len = str.length() / 8;
        int[] data = new int[len];
        int start = 0;
        for (int i = 0; i < n; i++){
            data[i] = Integer.parseInt(str.substring(start, start+8));
            start += 8;
        }
        
        //below is the same as the original one
        for (int i = 0; i < len; i++){
            int num = data[i];
            int type = getType(num);
            if (type == 0){
                continue;
            }else if (type > 1 && type + i <= len){
                while (type > 1){
                    i++;
                    int next = getType(data[i]);
                    if (next != 1){
                        return false;
                    }
                    type--;
                }
            }else{
                return false;
            }
        }
        return true;
    }
    private int getType(int num){
        for (int i = 0; i < mask.length; i++){
            if ((mask[i] & num) == 0){
                return i;
            }
        }
        return -1;
    }
}
