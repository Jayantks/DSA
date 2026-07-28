class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] counts = new int[26];
        char[] arr = s.toCharArray();

        for (int i = 0; i < n / 2; i++) {
            counts[arr[i] - 'a']++;
        }

        int idx = 0;

        for (int i = 0; i < 26; i++) {
            while (counts[i] > 0) {
                arr[idx++] = (char) (i + 'a');
                counts[i]--;
            }
        }

        for (int i = 0; i < n / 2; i++) {
            arr[n - 1 - i] = arr[i];
        }

        return new String(arr);
    }
}