A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02

Method 1: permutation
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] weightHrs = {8, 4, 2, 1};
        int[] weightMins = {32, 16, 8, 4, 2, 1};
        for (int i = 0; i <= num; i++){
            List<Integer> hours = generateDigits(weightHrs, i);
            List<Integer> mins = generateDigits(weightMins, num - i);
            for (int hour : hours){
                if (hour >= 12){
                    continue;
                }
                for (int min : mins){
                    if (min >= 60){
                        continue;
                    }
                    res.add(hour + ":" + (min >= 10 ? min : "0" + min));
                }
            }
        }
        return res;
    }
    private List<Integer> generateDigits(int[] weights, int count){
        List<Integer> res = new ArrayList<>();
        dfs(res, weights, count, 0, 0);
        return res;
    }
    private void dfs(List<Integer> res, int[] weights, int count, int index, int sum){
        if (count == 0){
            res.add(sum);
            return;
        }
        for (int i = index; i < weights.length; i++){
            dfs(res, weights, count - 1, i + 1, sum + weights[i]);
        }
    }
}

Method 2: Best solution
recursion
class Solution {
    public final int[] weights = {1, 2, 4, 8, 32, 16, 8, 4, 2, 1};//order does not matter as long as the first 4 are hours
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        dfs(res, num, 0, 0, 0);
        return res;
    }
    private void dfs(List<String> res, int num, int index, int hour, int min){
        if (hour >= 12 || min >= 60){
            return;
        }
        if (num == 0){
            res.add(hour + ":" + (min >= 10 ? min : "0" + min));
            return;
        }
        for (int i = index; i < weights.length; i++){
            if (i < 4){
                dfs(res, num - 1, i + 1, hour + weights[i], min);
            }else{
                dfs(res, num - 1, i + 1, hour, min + weights[i]);
            }
        }
    }
}

class Solution {
    public final int[] weights = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        dfs(res, num, 0, 0, 0);
        return res;
    }
    private void dfs(List<String> res, int num, int index, int hour, int minute){
        if (num == 0){
            res.add(hour + ":" + (minute < 10 ?  "0" + minute : minute));
        }
        for (int i = index; i < weights.length; i++){
            if (i < 4){
                if (hour + weights[i] >= 12){
                    continue;
                }
                dfs(res, num - 1, i + 1, hour + weights[i], minute);
            }else{
                if (minute + weights[i] >= 60){
                    continue;
                }
                dfs(res, num - 1, i + 1, hour, minute + weights[i]);
            }
        }
    }
}

backtracking:
class Solution {
    public final int[] weights = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        dfs(res, num, 0, 0, 0);
        return res;
    }
    private void dfs(List<String> res, int num, int index, int hour, int min){
        if (hour >= 12 || min >= 60){
            return;
        }
        if (num == 0){
            res.add(hour + ":" + (min >= 10 ? min : "0" + min));
            return;
        }
        for (int i = index; i < weights.length; i++){
            if (i < 4){
                hour += weights[i];
                dfs(res, num - 1, i + 1, hour, min);
                hour -= weights[i];
            }else{
                min += weights[i];
                dfs(res, num - 1, i + 1, hour, min);
                min -= weights[i];
            }
        }
    }
}
