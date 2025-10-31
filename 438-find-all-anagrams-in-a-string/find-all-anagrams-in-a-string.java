import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        int pLen = p.length();

        // Step 1: Count frequency of p
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // Step 2: Initialize sliding window
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++; // add current char to window

            // Shrink the window if it's larger than pLen
            if (i >= pLen) {
                sCount[s.charAt(i - pLen) - 'a']--; // remove leftmost char
            }

            // Step 3: Compare frequency arrays
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - pLen + 1); // store start index
            }
        }

        return result;
    }
}
