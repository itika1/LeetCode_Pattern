class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;  // Step 1
        int maxProfit = 0;                 // Step 2

        for (int price : prices) {         // Step 3
            if (price < minPrice) {        // Step 4
                minPrice = price;          // Step 5
            } else if (price - minPrice > maxProfit) {  // Step 6
                maxProfit = price - minPrice;           // Step 7
            }
        }
        return maxProfit;                   // Step 8
    }
}
