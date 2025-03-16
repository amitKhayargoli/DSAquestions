package remainingQuestions;

import java.util.ArrayList;
import java.util.List;

class SubsetSum {
    static List<List<Integer>> result = new ArrayList<>();
    static List<Integer> current = new ArrayList<>();

    public static void findSubsets(int[] nums, int target) {

        backtrack(nums, target, 0);

    }

    private static void backtrack(int[] nums, int target, int start) {
        if (target == 0) { // Base case: if the target sum is 0, add current subset to result
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            // Skip if the current number exceeds the target
            if (nums[i] > target) {
                continue;
            }

            // Include nums[i] in the current subset
            current.add(nums[i]);
            // Recurse with the reduced target and next index
            backtrack(nums, target - nums[i], i + 1);
            // Backtrack: remove the last element added
            current.remove(current.size() - 1);
        }
    }
}
