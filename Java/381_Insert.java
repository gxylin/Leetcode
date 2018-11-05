class RandomizedCollection {
    Random random;
    List<Integer> list;
    Map<Integer, Set<Integer>> map;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        random = new Random();
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)){
            map.get(val).add(list.size());
            list.add(val);
            return false;
        }
        map.put(val, new HashSet<>());
        map.get(val).add(list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0){
            
            return false;
        }
        Set<Integer> set = map.get(val);
        int index = set.iterator().next();
        int lastIndex = list.size() - 1;
        int lastNum = list.get(lastIndex);
        list.set(index, lastNum);
        if (val != lastNum){//note that this is very important as it may remove more if not checking
            map.get(lastNum).add(index);
            map.get(val).remove(index);
        }
        
        map.get(lastNum).remove(lastIndex);
        list.remove(lastIndex);

        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
