class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> current, List<List<Integer>> result) {

        // If target becomes zero -> valid combination formed
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // If target goes negative OR no more candidates left
        if (target < 0 || index >= candidates.length)
            return;

        // -------------------------------------------
        // OPTION 1: TAKE current candidate
        // -------------------------------------------
        current.add(candidates[index]);                  // choose
        backtrack(candidates, target - candidates[index], index, current, result);
        current.remove(current.size() - 1);              // backtrack

        // -------------------------------------------
        // OPTION 2: SKIP current candidate
        // -------------------------------------------
        backtrack(candidates, target, index + 1, current, result);
    }
}
