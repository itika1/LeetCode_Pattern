class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {

        int n = customers.length;

        // 1. Base satisfied customers
        int base = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                base += customers[i];
            }
        }

        // 2. Sliding window to find max extra satisfied
        int extra = 0;
        int maxExtra = 0;

        for (int i = 0; i < n; i++) {
            // Add customers if owner is grumpy here
            if (grumpy[i] == 1) {
                extra += customers[i];
            }

            // Maintain window size
            if (i >= minutes && grumpy[i - minutes] == 1) {
                extra -= customers[i - minutes];
            }

            maxExtra = Math.max(maxExtra, extra);
        }

        // 3. Total = base + best window extra
        return base + maxExtra;
    }
}
