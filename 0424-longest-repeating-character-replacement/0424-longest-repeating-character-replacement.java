class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int low = 0, maxFreq = 0, res = 0;
        Map<Character, Integer> freq = new HashMap<>();

        for (int high = 0; high < n; high++) {
            char c = s.charAt(high);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, freq.get(c));

            // window invalid if replacements needed > k
            while ((high - low + 1) - maxFreq > k) {
                char leftChar = s.charAt(low);
                freq.put(leftChar, freq.get(leftChar) - 1);
                low++;
            }
            res = Math.max(res, high - low + 1);
        }
        return res;
    }
}