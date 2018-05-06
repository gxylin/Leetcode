Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between
any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() < 2){
            return 0;
        }
        Collections.sort(timePoints, new Comparator<String>(){
            public int compare (String a, String b){
                String[] aTime = a.split(":");
                String[] bTime = b.split(":");
                if (Integer.parseInt(aTime[0]) == Integer.parseInt(bTime[0])){
                    return Integer.parseInt(aTime[1]) - Integer.parseInt(bTime[1]);
                }
                return Integer.parseInt(aTime[0]) - Integer.parseInt(bTime[0]);
            }
        });
        timePoints.add(timePoints.get(0));
        int max = 24 * 60 ;
        int diff = max;
        for (int i = 0; i < timePoints.size() - 1; i++){
            String[] a = timePoints.get(i+1).split(":");
            String[] b = timePoints.get(i).split(":");
            int aVal = Integer.parseInt(a[0]) * 60 + Integer.parseInt(a[1]);
            int bVal = Integer.parseInt(b[0]) * 60 + Integer.parseInt(b[1]);
            int candid =  Math.abs(aVal - bVal);
            if (candid > max / 2){
                candid = max - candid;
            }
            diff = Math.min(diff, candid);
        }
        return diff;
    }
}
