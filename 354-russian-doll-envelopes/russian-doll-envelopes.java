class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;

        // Step 1: Sort
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // height DESC if width same
            }
            return a[0] - b[0]; // width ASC
        });

        // Step 2: LIS on heights
        int[] dp = new int[envelopes.length];
        int len = 0;

        for (int[] env : envelopes) {
            int height = env[1];

            int idx = Arrays.binarySearch(dp, 0, len, height);
            if (idx < 0) idx = -(idx + 1);

            dp[idx] = height;

            if (idx == len) len++;
        }

        return len;
    }
}