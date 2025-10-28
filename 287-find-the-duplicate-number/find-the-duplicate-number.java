class Solution {
    public int findDuplicate(int[] nums) {
        int left = 1;
        int right = nums.length - 1;
        int duplicate = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Count numbers <= mid
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            
            // If count is greater than mid, duplicate is in [left, mid]
            if (count > mid) {
                duplicate = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return duplicate;
    }
}
