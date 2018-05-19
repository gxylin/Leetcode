Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6] 

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,3,2,4,5,6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you,
replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].

Method 1: can be extented to k vector
public class ZigzagIterator {
    Queue<Iterator> queue;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<Iterator>();
        if (!v1.isEmpty()){
            queue.offer(v1.iterator());
        }
        if (!v2.isEmpty()){
            queue.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator ite = queue.poll();
        int next = (int) ite.next();
        if (ite.hasNext()){
            queue.offer(ite);
        }
        return next;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

https://www.geeksforgeeks.org/how-to-use-iterator-in-java/
 Iterator iterator = list.iterator();
 while (iterator.hasNext())
       System.out.print(iterator.next()+ " ");


/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
 
 Method 2:
public class ZigzagIterator {
    private int next;
    private int p1;
    private int p2;
    private boolean p1Curr;
    private List<Integer> l1;
    private List<Integer> l2;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        l1 = new ArrayList<>();
        l2 = new ArrayList<>();
        p1 = 0;
        p2 = 0;
        next = 0;
        p1Curr = true;
        if (v1.isEmpty()){
            p1Curr = false;
        }
        for (int i = 0; i < v1.size(); i++){
            l1.add(v1.get(i));
        }
        for (int i = 0; i < v2.size(); i++){
            l2.add(v2.get(i));
        }
    }

    public int next() {
        return next;
    }

    public boolean hasNext() {
        if (p1 + p2 < l1.size() + l2.size()){
            if (p1 < l1.size() && p1Curr){
                next = l1.get(p1);
                p1++;
                if (p2 < l2.size()){
                    p1Curr = false;
                }
            }else if (p2 < l2.size() && !p1Curr){
                next = l2.get(p2);
                p2++;
                if (p1 < l1.size()){
                    p1Curr = true;
                }
            }
            return true;
        }
        return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
