class Solution {
    public boolean fun(int[] have, int[] need) {
        for (int i = 0; i < 256; i++) {
            if (have[i] < need[i])
                return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] have = new int[256];
        int[] need = new int[256];
        int i;
        if (n < m)
            return "";
        for (i = 0; i < m; i++)
            need[t.charAt(i)]++;
        int low = 0, high = 0;
        int res = Integer.MAX_VALUE;
        int start = -1;
        for (high = 0; high < n; high++) {
            have[s.charAt(high)]++;
            while (fun(have, need)) // jab tk sahi hai
            {
                int len = high - low + 1;
                if (res > len) {
                    res = len;
                    start = low;
                }
                have[s.charAt(low)]--;
                low++;
            }
        }
        if (res == Integer.MAX_VALUE)
            return "";
        return s.substring(start, start + res);
    }
}