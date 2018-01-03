Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0){
            return "";
        }
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++){
            strs[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String a, String b){
                String ab = a + b;
                String ba = b + a;
                return ba.compareTo(ab);
            }
        });
        if (strs[0].equals("0")){
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < strs.length; i++){
            ans.append(strs[i]);
        }
        return ans.toString();
        
    }
}
