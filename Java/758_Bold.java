 Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.

The returned string should use the least number of tags possible, and of course the tags should form a valid combination.

For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.

Note:

    words has length in range [0, 50].
    words[i] has length in range [1, 10].
    S has length in range [0, 500].
    All characters in words[i] and S are lowercase letters.


Method 1: the same as 616. Add Bold Tag in a String
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
    public String boldWords(String[] words, String S) {
        List<Interval> intervals = new ArrayList<>();
        for (String str : words){
            int index = -1;
            index = S.indexOf(str, index);
            while (index != -1){
                intervals.add(new Interval(index, index + str.length()));
                index++;
                index = S.indexOf(str, index);
            }
        }
        intervals = merge(intervals);
        StringBuilder sb = new StringBuilder();
        int prev = 0;
        for (Interval interval : intervals){
            sb.append(S.substring(prev, interval.start));
            sb.append("<b>");
            sb.append(S.substring(interval.start, interval.end));
            sb.append("</b>");
            prev = interval.end;
        }
        if (prev < S.length()){
            sb.append(S.substring(prev));
        }
        return sb.toString();
    }
}
