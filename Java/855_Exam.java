In an exam room, there are N seats in a single row, numbered 0, 1, 2, ..., N-1.

When a student enters the room, they must sit in the seat that maximizes the distance to the closest person.  If there are multiple such seats, they sit in the seat with the lowest number.  (Also, if no one is in the room, then the student sits at seat number 0.)

Return a class ExamRoom(int N) that exposes two functions: ExamRoom.seat() returning an int representing what seat the student sat in, and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room.  It is guaranteed that any calls to ExamRoom.leave(p) have a student sitting in seat p.

 

Example 1:

Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
Output: [null,0,9,4,2,null,5]
Explanation:
ExamRoom(10) -> null
seat() -> 0, no one is in the room, then the student sits at seat number 0.
seat() -> 9, the student sits at the last seat number 9.
seat() -> 4, the student sits at the last seat number 4.
seat() -> 2, the student sits at the last seat number 2.
leave(4) -> null
seat() -> 5, the student​​​​​​​ sits at the last seat number 5.
​​​​​​​

Note:

1 <= N <= 10^9
ExamRoom.seat() and ExamRoom.leave() will be called at most 10^4 times across all test cases.
Calls to ExamRoom.leave(p) are guaranteed to have a student currently sitting in seat number p.

class ExamRoom {
    private List<Integer> list;
    private int n;
    public ExamRoom(int N) {
        list = new ArrayList<>();
        n = N;
    }
    
    public int seat() {
        if (list.isEmpty()){
            list.add(0);
            return 0;
        }
        int max = Math.max(list.get(0), n - 1 - list.get(list.size() - 1)); // to handle the corner case: before the first and after the last
        for (int i = 0; i < list.size() - 1 ; i++){
            max = Math.max(max, (list.get(i+1) - list.get(i)) / 2);
        }
        if (list.get(0) == max){
            list.add(0, 0);
            return 0;
        }
        for (int i = 0; i < list.size() - 1; i++){
            if ((list.get(i+1) - list.get(i)) / 2 == max){
                list.add(i+1, list.get(i) + max);
                return list.get(i+1);
            }
        }
        list.add(n-1);
        return n-1;
    }
    
    public void leave(int p) {
        list.remove(new Integer(p));//remove Object, not index if we want to remove index, we need use loop
    }
    /*
    for (int i = 0; i < list.size(); i++){
            if (list.get(i) == p){
                list.remove(i);
            }
     }
     */
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
