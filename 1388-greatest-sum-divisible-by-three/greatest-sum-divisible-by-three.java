import java.util.Arrays;

class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[r] = max sum achievable with remainder r (mod 3)
        // initialize dp: only 0 remainder is 0; others are impossible => set to very small
        int NEG = Integer.MIN_VALUE / 2; // avoid overflow when adding
        int[] dp = new int[] {0, NEG, NEG};

        for (int num : nums) {
            int mod = num % 3;
            // snapshot of current dp before updating
            int a = dp[0], b = dp[1], c = dp[2];

            if (mod == 0) {
                // adding a mod-0 number doesn't change remainder
                dp[0] = Math.max(dp[0], a + num);
                dp[1] = Math.max(dp[1], b + num);
                dp[2] = Math.max(dp[2], c + num);
            } else if (mod == 1) {
                // map old remainders to new ones
                dp[0] = Math.max(dp[0], c + num); // c (2) + 1 -> 0
                dp[1] = Math.max(dp[1], a + num); // a (0) + 1 -> 1
                dp[2] = Math.max(dp[2], b + num); // b (1) + 1 -> 2
            } else { // mod == 2
                dp[0] = Math.max(dp[0], b + num); // b (1) + 2 -> 0
                dp[1] = Math.max(dp[1], c + num); // c (2) + 2 -> 1
                dp[2] = Math.max(dp[2], a + num); // a (0) + 2 -> 2
            }

            // also keep original dp values (no-add option) - above Math.max with dp[...] handles it
            // but careful: because dp[...] was updated in place, we needed the snapshot a,b,c
            // (we used a,b,c when updating so no further action needed)
        }

        return Math.max(0, dp[0]); // dp[0] might be negative if no valid subset other than 0; return at least 0
    }
}
