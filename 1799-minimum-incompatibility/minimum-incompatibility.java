class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int groupSize = n / k;
        int fullMask = (1 << n) - 1;
        int INF = Integer.MAX_VALUE / 2;

        // Precompute incompatibility for valid subsets
        int[] incompat = new int[1 << n];
        Arrays.fill(incompat, -1);

        for (int mask = 0; mask <= fullMask; mask++) {
            if (Integer.bitCount(mask) != groupSize) continue;

            Set<Integer> seen = new HashSet<>();
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (!seen.add(nums[i])) {
                        valid = false;
                        break;
                    }
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            }

            if (valid) {
                incompat[mask] = max - min;
            }
        }

        // DP
        int[] dp = new int[1 << n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int mask = 0; mask <= fullMask; mask++) {
            if (dp[mask] == INF) continue;

            int remaining = (~mask) & fullMask;

            // Optimization: pick first unused index
            int first = Integer.numberOfTrailingZeros(remaining);

            for (int sub = remaining; sub > 0; sub = (sub - 1) & remaining) {
                if ((sub & (1 << first)) == 0) continue;
                if (incompat[sub] == -1) continue;

                dp[mask | sub] = Math.min(
                    dp[mask | sub],
                    dp[mask] + incompat[sub]
                );
            }
        }

        return dp[fullMask] >= INF ? -1 : dp[fullMask];
    }
}