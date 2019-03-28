// package whatever; // don't place package name!
/*
题目不难，给你一个menu，是一个map，key是菜名，value是价格，比如
"apple": 3.25,
"chicken": 4.55,
"cake":10.85,

然后给你一个budget，比如7.80.
要你给出所有菜名的combination，总价要正好符合budget，次序不重要，但不能有重复。
比如，如果budget是7.80，他就要求结果是[["apple", "chicken"]]，不能是[["apple", "chicken"],["chicken","apple"]]
比如，如果budget是6.50，他就要求结果是[["apple", "apple"]]
*/

import java.io.*;
import java.util.*;
class MyCode {
  public static List<List<String>> menu (Map<String, Double> map, double target){
    List<List<String>> res = new ArrayList<>();
    String[] names = new String[map.size()];
    int index = 0;
    for (String key : map.keySet()){
      names[index++] = key;
    }
    backtrack(map, target, res, new ArrayList<>(), names, 0);
    return res;
  }
  private static void backtrack(Map<String, Double> map, double target, List<List<String>> res, List<String> item, String[] names, int start){
    if (target <= 0){
      if (target == 0)
          res.add(new ArrayList<>(item));
      return;
    }
    for (int i = start; i < names.length; i++){
      item.add(names[i]);
      backtrack(map, target - map.get(names[i]), res, item, names, i);
      item.remove(item.size() - 1);
    }
  }
	public static void main (String[] args) {
		Map<String, Double> map = new HashMap<>();
    map.put("apple", 3.25);
    map.put("chickin", 4.55);
    map.put("cake", 10.85);
    map.put("banana", 3.25);
    List<List<String>> res = menu(map, 6.50);
    for (List<String> list : res){
      System.out.println("list:");
      for (String str : list){
        System.out.println(str);
      }
    }
	}
}
