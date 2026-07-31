class Solution {
    public int minimumPushes(String word) {
        int[] letterFrequency = new int[26];
      
        for (int i = 0; i < word.length(); i++) {
            letterFrequency[word.charAt(i) - 'a']++;
        }
      
        Arrays.sort(letterFrequency);
      
        int totalPushes = 0;
      
        for (int i = 0; i < 26; i++) {
            // i/8 determines which "round" of key assignments we're on
            // First 8 letters (i=0-7) need 1 push, next 8 need 2 pushes, etc.
            // letterFrequency[26-i-1] accesses frequencies in descending order
            int pushesPerLetter = (i / 8) + 1;
            int frequency = letterFrequency[26 - i - 1];
            totalPushes += pushesPerLetter * frequency;
        }
      
        return totalPushes;
    }
}
