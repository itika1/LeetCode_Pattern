class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort to avoid duplicates
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {

        // If target is met -> add to result
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // If out of bounds or target negative -> return
        if (target < 0) return;

        // Iterate all available options
        for (int i = index; i < candidates.length; i++) {

            // Skip duplicates (only allow first occurrence in branch)
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            // Choose current number
            current.add(candidates[i]);

            // Move to next index (since reuse is NOT allowed)
            backtrack(candidates, target - candidates[i], i + 1, current, result);

            // Undo choice (backtrack)
            current.remove(current.size() - 1);
        }
    }
}
