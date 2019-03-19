You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the 
number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret 
number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong 
position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

For example:

Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".

Please note that both secret number and friend's guess may contain duplicate digits, for example:

Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

class Solution {
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < secret.length(); i++){
            char gchar = guess.charAt(i);
            char schar = secret.charAt(i);
            if (gchar == schar){
                A++;
            }else{
                map.put(schar, map.getOrDefault(schar, 0) + 1);
            }
        }
        for (int i = 0; i < guess.length(); i++){
            char gchar = guess.charAt(i);
            char schar = secret.charAt(i);
            if (gchar != schar && map.containsKey(gchar) && map.get(gchar) != 0){
                B++;
                map.put(gchar, map.get(gchar) - 1);
            }
        }
        return A + "A" + B + "B";
    }
}

https://leetcode.com/problems/bulls-and-cows/discuss/74621/One-pass-Java-solution
class Solution {
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        int[] hash = new int[10];
        for (int i = 0; i < secret.length(); i++){
            char gchar = guess.charAt(i);
            char schar = secret.charAt(i);
            if (gchar == schar){
                A++;
            }else{
                if (hash[schar -'0']++ < 0){
                    B++;
                }
                if (hash[gchar - '0']-- > 0){
                    B++;
                }
            }
        }
        return A + "A" + B + "B";
    }
}

class Solution {
    public String getHint(String secret, String guess) {
        int A = 0;
        int B = 0;
        int len = secret.length();
        boolean[] bull = new boolean[len];
        for (int i = 0; i < len; i++){
            if (secret.charAt(i) == guess.charAt(i)){
                A++;
                bull[i] = true;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++){
            if (!bull[i]){
                char c = secret.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
        }
        for (int i = 0; i < len; i++){
            if (!bull[i]){
                char c = guess.charAt(i);
                if (map.containsKey(c) && map.get(c) > 0){
                    map.put(c, map.get(c) - 1);
                    B++;
                }
            }
        }
        return A + "A" + B + "B";
    }
}
