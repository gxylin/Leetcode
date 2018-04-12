All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for 
example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++){
            String str = s.substring(i, i+10);
            if (!set.add(str)){
                result.add(str);
            }
        }
        return new ArrayList<>(result);
    }
}
