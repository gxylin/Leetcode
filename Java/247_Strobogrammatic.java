A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].


    
Method: Recusion

Best solution:
class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    private List<String> helper(int len, int target){
        if (step == 0){
            return Arrays.asList("");
        }
        if (step == 1){
            return Arrays.asList("1", "8", "0");
        }
        List<String> list = helper(len-2, target);
        List<String> result = new ArrayList<>();
        for (String str : list){
            if (len != target){
                result.add("0" + str + "0");
            }
            result.add("1" + str + "1");
            result.add("6" + str + "9");
            result.add("8" + str + "8");
            result.add("9" + str + "6");
        }
        return result;
    }
}

Method2 : iteration
public class Solution {
    public List<String> findStrobogrammatic(int n) {
        List<String> one = Arrays.asList("0", "1", "8"), two = Arrays.asList(""), r = two;
        if(n%2 == 1)
            r = one;
        for(int i=(n%2)+2; i<=n; i+=2){
            List<String> newList = new ArrayList<>();
            for(String str : r){
                if(i != n)
                    newList.add("0" + str + "0");
                newList.add("1" + str + "1");
                newList.add("6" + str + "9");
                newList.add("8" + str + "8");
                newList.add("9" + str + "6");
            }
            r = newList;
        }
        return r;   
    }

}
