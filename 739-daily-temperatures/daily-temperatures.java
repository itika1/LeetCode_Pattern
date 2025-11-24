class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // stores indexes

        for (int i = 0; i < n; i++) {
            // while there is a previous day with smaller temp
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;   // calculate days waited
            }

            // push today's index
            stack.push(i);
        }

        return result;
    }
}
