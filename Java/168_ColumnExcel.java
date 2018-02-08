Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    
 Method 1:
 class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int weight = 26;
        while (n > 0){
            sb.append((char)((n - 1) % weight + 'A'));
            n = (n - 1) / weight;
        }
        return sb.reverse().toString();
    }
}

Method 2:
class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        int weight = 26;
        while (n > 0){
            n--;
            sb.insert(0, (char)(n % weight + 'A'));
            n = n / weight;
        }
        return sb.toString();
    }
}
