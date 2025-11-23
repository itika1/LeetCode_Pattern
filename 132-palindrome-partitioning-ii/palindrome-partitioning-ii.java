class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];

        // 1. Precompute palindrome substrings
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                    (end - start <= 2 || isPal[start + 1][end - 1])) {
                    isPal[start][end] = true;
                }
            }
        }

        // 2. dp[i] = min cuts for s[0..i]
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                dp[i] = 0;  // whole substring is palindrome
            } else {
                int minCuts = i;
                for (int j = 1; j <= i; j++) {
                    if (isPal[j][i]) {
                        minCuts = Math.min(minCuts, dp[j - 1] + 1);
                    }
                }
                dp[i] = minCuts;
            }
        }

        return dp[n - 1];
    }
}
