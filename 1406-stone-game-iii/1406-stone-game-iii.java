class Solution {
    int n;
    int[] stoneValue;
    Integer[] memo;

    public String stoneGameIII(int[] stoneValue) {
        this.stoneValue = stoneValue;
        this.n = stoneValue.length;
        this.memo = new Integer[n];

        int result = solve(0);

        if (result > 0) return "Alice";
        else if (result < 0) return "Bob";
        else return "Tie";
    }

    private int solve(int i) {
        if (i >= n) return 0;
        if (memo[i] != null) return memo[i];

        int curr = 0;
        int ans = Integer.MIN_VALUE;

        for (int k = 0; k < 3; k++) {
            if (i + k == n) break;

            curr += stoneValue[i + k];
            ans = Math.max(ans, curr - solve(i + k + 1));
        }

        memo[i] = ans;
        return ans;
    }
}