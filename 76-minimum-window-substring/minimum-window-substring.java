class Solution {
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";

        // Step 1: Store frequency of chars in t
        int[] target = new int[128];  // ASCII array
        for (char ch : t.toCharArray()) {
            target[ch]++;
        }

        int left = 0, right = 0, count = t.length();
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;

        // Step 2: Slide the window
        while (right < s.length()) {
            char rChar = s.charAt(right);

            // If this char is needed, decrement the count
            if (target[rChar] > 0) {
                count--;
            }
            target[rChar]--; // Add this char to window
            right++;

            // Step 3: When we have all required chars, try to minimize window
            while (count == 0) {
                // Check if this is the minimum window so far
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }

                char lChar = s.charAt(left);
                target[lChar]++; // Remove this char from window

                // If a required char is removed, increase count
                if (target[lChar] > 0) {
                    count++;
                }
                left++;
            }
        }

        // Step 4: Return the result substring
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
    }
}
