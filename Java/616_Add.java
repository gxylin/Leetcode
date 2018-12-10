Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s
that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if 
two substrings wrapped by bold tags are consecutive, you need to combine them.

Example 1:

Input: 
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"

Example 2:

Input: 
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"

Note:

    The given dict won't contain duplicates, and its length won't exceed 100.
    All the strings in input have length in range [1, 1000].

Method 1: based on merge interval
Consider you have string
s = "aaabbcc"
dict = ["aaa","aab","bc"]

   you find the index of each string in dict, conver to an interval, you will get
   
   [[0, 3], [1, 4], [4, 6]]
     aaa     aab      bc
   then combine these intervals
   
   Ater merged, we got [0,6], so we know "aaabbc" needs to be surrounded by tag. 


class Solution {
    class Interval{
        int start;
        int end;
        Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
        public String toString() {
			return "[" + start + ", " + end + "]" ;
		}
    }
    public List<Interval> merge(List<Interval> intervals){
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0){
            return result;
        }
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare (Interval a, Interval b){
                if (a.start == b.start){
                    return a.end - b.end;
                }
                return a.start - b.start;
            }
        });
        Interval last = null;
        for (Interval interval : intervals){
            if (last == null || last.end < interval.start){
                result.add(interval);
                last = interval;
            }else{
                last.end = Math.max(last.end, interval.end);
            }
        }
        return result;
    }
    public String addBoldTag(String s, String[] dict) {
        List<Interval> intervals = new ArrayList<>();
        for (String str : dict){
            int index = -1;
            index = s.indexOf(str, index);
            while (index != -1){
                intervals.add(new Interval(index, index + str.length() ));
                index++;
                index = s.indexOf(str, index);
            }
        }
        System.out.println(Arrays.toString(intervals.toArray()));
        intervals = merge(intervals);
        System.out.println(Arrays.toString(intervals.toArray()));
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for (Interval interval : intervals){
            sb.append(s.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(s.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        if (prev < s.length()){
            sb.append(s.substring(prev));
        }
        return sb.toString();
    }
}
