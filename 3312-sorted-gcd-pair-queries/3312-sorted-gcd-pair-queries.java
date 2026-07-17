class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];
        for (int x : nums) freq[x]++;

        long[] exact = new long[mx + 1];

        // exact[g] = pairs with gcd exactly g
        for (int g = mx; g >= 1; g--) {
            long cnt = 0;
            for (int j = g; j <= mx; j += g)
                cnt += freq[j];

            long pairs = cnt * (cnt - 1) / 2;

            for (int j = g * 2; j <= mx; j += g)
                pairs -= exact[j];

            exact[g] = pairs;
        }

        // prefix counts
        long[] pref = new long[mx + 1];
        for (int i = 1; i <= mx; i++)
            pref[i] = pref[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1;   // 1-based order statistic

            int l = 1, r = mx;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (pref[mid] >= k)
                    r = mid;
                else
                    l = mid + 1;
            }
            ans[i] = l;
        }

        return ans;
    }
}