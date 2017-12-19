Implement a load balancer for web servers. It provide the following functionality:

Add a new server to the cluster => add(server_id).
Remove a bad server from the cluster => remove(server_id).
Pick a server in the cluster randomly with equal probability => pick().
Have you met this question in a real interview? Yes
Example
At beginning, the cluster is empty => {}.

add(1)
add(2)
add(3)
pick()
>> 1         // the return value is random, it can be either 1, 2, or 3.
pick()
>> 2
pick()
>> 1
pick()
>> 3
remove(1)
pick()
>> 2
pick()
>> 3
pick()
>> 3

思路:
• 要在o(1)的时间内插入删除,只能hash。那hash可以getRandom吗?
– 不太好做
• 什么数据结构比较好getRandom?
– 数组
• 考虑hash与数组结合起来用,hash插入一个,数组也插入一个。那么问题来
了,数组删除元素怎么办? – 与最后插入的一个元素交换
• 那怎么o(1)时间在数组中找到要删除元素(要交换)的位置? – 用hash将元素的位置记下来
算法:
• 插入:
– 数组末尾加入这个元素
– Hash这个元素存下数组中的下标
• 删除:
– 通过hash找到这个元素在数组中的位置
– 数数组中这个元素和数组的末尾元素交换,交换后删除
– Hash中删除这个元素,更新数组原末尾元素现在在数组中的位置
• Pick:
– 数组中random一个返回

public class LoadBalancer {
    int num;
    Map<Integer, Integer> map; 
    List<Integer> list;
    Random rand;
    public LoadBalancer() {
        map = new HashMap<>();
        list = new ArrayList<>();
        num = 0;
        rand = new Random();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        if (!map.containsKey(server_id)){
            list.add(server_id);
            map.put(server_id, num);
            num++;
        }
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        if (map.containsKey(server_id)){
            int idx = map.get(server_id);
            int lastItem = list.get(num - 1);
            
            map.put(lastItem, idx);
            list.set(idx, lastItem);
            
            map.remove(server_id);
            list.remove(num - 1);
            num--;
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        return list.get(rand.nextInt(num));
    }
}
