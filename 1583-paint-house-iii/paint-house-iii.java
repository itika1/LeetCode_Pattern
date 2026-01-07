class Solution {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int INF = 1_000_000_000;

        // dp[i][c][t]: min cost to paint up to house i,
        // with color c at i, and t neighborhoods
        int[][][] dp = new int[m][n + 1][target + 1];

        // initialize
        for (int i = 0; i < m; i++) {
            for (int c = 1; c <= n; c++) {
                Arrays.fill(dp[i][c], INF);
            }
        }

        // first house
        for (int c = 1; c <= n; c++) {
            if (houses[0] == 0 || houses[0] == c) {
                dp[0][c][1] = (houses[0] == 0) ? cost[0][c - 1] : 0;
            }
        }

        // fill DP
        for (int i = 1; i < m; i++) {
            for (int c = 1; c <= n; c++) {

                if (houses[i] != 0 && houses[i] != c) continue;

                int paintCost = (houses[i] == 0) ? cost[i][c - 1] : 0;

                for (int t = 1; t <= target; t++) {

                    // same color → no new neighborhood
                    dp[i][c][t] = Math.min(
                        dp[i][c][t],
                        dp[i - 1][c][t] + paintCost
                    );

                    // different color → new neighborhood
                    for (int pc = 1; pc <= n; pc++) {
                        if (pc == c) continue;
                        dp[i][c][t] = Math.min(
                            dp[i][c][t],
                            dp[i - 1][pc][t - 1] + paintCost
                        );
                    }
                }
            }
        }

        int ans = INF;
        for (int c = 1; c <= n; c++) {
            ans = Math.min(ans, dp[m - 1][c][target]);
        }

        return ans == INF ? -1 : ans;
    }
}
