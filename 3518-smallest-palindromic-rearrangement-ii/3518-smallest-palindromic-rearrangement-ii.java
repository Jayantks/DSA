class Solution {

    static final long LIMIT = 10_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] cnt = new int[26], half = new int[26];
        char mid = 0;

        for (char c : s.toCharArray()) cnt[c - 'a']++;

        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
            half[i] = cnt[i] / 2;
        }

        if (odd > 1 || k > count(half)) return "";

        StringBuilder left = new StringBuilder();

        for (int len = s.length() / 2; len > 0; len--) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;

                half[i]--;
                long ways = count(half);

                if (ways >= k) {
                    left.append((char) ('a' + i));
                    break;
                }

                k -= ways;
                half[i]++;
            }
        }

        return left.toString() + (mid == 0 ? "" : mid)
                + left.reverse().toString();
    }

    private long count(int[] cnt) {
        int total = 0;
        for (int x : cnt) total += x;

        long ans = 1;

        for (int c : cnt) {
            if (c == 0) continue;
            ans *= C(total, c);
            if (ans >= LIMIT) return LIMIT;
            total -= c;
        }

        return ans;
    }

    private long C(int n, int r) {
        if (r == 0 || r == n) return 1;

        r = Math.min(r, n - r);
        long ans = 1;

        for (int i = 1; i <= r; i++) {
            ans = ans * (n - r + i) / i;
            if (ans >= LIMIT) return LIMIT;
        }

        return ans;
    }
}