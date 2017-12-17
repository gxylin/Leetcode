Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Please implement encode and decode

Have you met this question in a real interview? Yes
Example
Given strs = ["lint","code","love","you"]
string encoded_string = encode(strs)

return ["lint","code","love","you"] when you call decode(encoded_string)

Method 1: length code
public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        String ans = "";
        for (String str : strs){
            ans += str.length() + ":" + str;
        }
        return ans;
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> result = new ArrayList<String>();
        int i = 0; 
        int numPtr = 0;
        while (i < str.length()){
           if (str.charAt(i) == ':'){
               String num = str.substring(numPtr, i);
               int len = Integer.parseInt(num);
               String temp = str.substring(i+1, i + 1 + len);
               result.add(temp);
               i += len + 1;
               numPtr = i;
           }else{
               i++;
           }
        }
        return result;
    }
}


Method 2: length code
public class Solution {
    /**
     * @param strs a list of strings
     * @return encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        // Write your code here
        StringBuilder sb = new StringBuilder();
        StringBuilder number = new StringBuilder();
        for (String s: strs){
            number.append("/").append(s.length());
            sb.append(s);
        }
        
        return sb.toString() + "+" + number.toString();
        
    }

    /**
     * @param str a string
     * @return dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        // Write your code here
        
        // have string like this 
        // lintcodelikeyou + /4/4/4/3
        
        int index = str.lastIndexOf("+");
        String prefix = str.substring(0, index);
        String postfix = str.substring(index+1);
        List<String> res = new ArrayList<>();
        int pointer = 0;
        String[] number = postfix.split("/");
  /*      for (String num: number){
            if (num.equals("")) continue;  //note the first num is empty string
            int temp = Integer.parseInt(num);
            res.add(prefix.substring(pointer, pointer + temp));
            pointer += temp;
        }*/
        for (int i = 1; i < number.length; i++){
            int temp = Integer.parseInt(number[i]);
            res.add(prefix.substring(pointer, pointer + temp));
            pointer += temp;
        }
        return res;
    }
    
    
}


Method 3: translation code
public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        String ans = "";
        for (String str : strs){
            for (char c : str.toCharArray()){
                if (c == ':'){
                    ans += "::";
                }else{
                    ans += c;
                }
            }
            ans += ":;";
        }
        return ans;
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        String item = "";
        char[] strChar = str.toCharArray();
        int i = 0;
        while (i < str.length()){
            if (strChar[i] == ':'){
                if (strChar[i+1] == ';'){
                    result.add(item);
                    item = "";
                    i += 2;
                }else{
                    item += strChar[i+1];
                    i += 2;
                }
            }else{
               item += strChar[i];
               i++;
            }
        }
        return result;
    }
}
