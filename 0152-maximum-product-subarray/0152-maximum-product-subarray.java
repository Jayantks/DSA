class Solution {
    public int maxProduct(int[] nums) {
        int maxEnding = nums[0];
        int minEnding = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int v1 = maxEnding * nums[i];
            int v2 = minEnding * nums[i];
            int v3 = nums[i];
            maxEnding = Math.max(v1, Math.max(v2, v3));
            minEnding = Math.min(v1, Math.min(v2, v3));
            ans = Math.max(ans, maxEnding);
        }
        return ans;
    }
}