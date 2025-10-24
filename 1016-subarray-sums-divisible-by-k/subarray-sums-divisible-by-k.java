class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // remainder 0 seen once initially (empty subarray)

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;
            int remainder = prefixSum % k;

            // Ensure positive remainder (to handle negatives)
            if (remainder < 0) remainder += k;

            // If this remainder was seen before, we can form subarrays
            if (map.containsKey(remainder)) {
                count += map.get(remainder);
            }

            // Store/update remainder frequency
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}
