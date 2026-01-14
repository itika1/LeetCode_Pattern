class Solution {
    public long minimumTime(int[] time, int totalTrips) {
        long low = 1;
        long minTime = Long.MAX_VALUE;

        for (int t : time) {
            minTime = Math.min(minTime, t);
        }

        long high = minTime * totalTrips;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canComplete(time, mid, totalTrips)) {
                ans = mid;          // possible answer
                high = mid - 1;     // try smaller time
            } else {
                low = mid + 1;      // need more time
            }
        }

        return ans;
    }

    private boolean canComplete(int[] time, long currTime, int totalTrips) {
        long trips = 0;

        for (int t : time) {
            trips += currTime / t;
            if (trips >= totalTrips) return true;
        }

        return false;
    }
}
