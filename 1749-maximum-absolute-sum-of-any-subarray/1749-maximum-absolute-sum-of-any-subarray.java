class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxEnding = 0, minEnding = 0;
        int ans = 0;

        for (int num : nums) {
            maxEnding = Math.max(num, maxEnding + num);
            minEnding = Math.min(num, minEnding + num);

            ans = Math.max(ans, Math.max(maxEnding, -minEnding));
        }

        return ans;
    }
}