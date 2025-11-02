class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double sum = 0;

        // Step 1: Calculate sum of first k elements
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxSum = sum;

        // Step 2: Slide the window from index k to end
        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];  // add new, remove leftmost
            maxSum = Math.max(maxSum, sum); // track max sum window
        }

        // Step 3: Return maximum average
        return maxSum / k;
    }
}
