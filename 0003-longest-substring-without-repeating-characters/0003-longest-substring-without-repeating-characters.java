class Solution {
    public int lengthOfLongestSubstring(String s) {
       int n = s.length();
       int left = 0, maxLen = 0;

       Map < Character , Integer > lastIndex = new HashMap<>();

       for ( int high = 0 ; high < n ; high++){
        char c = s.charAt(high);
        if (lastIndex.containsKey(c)){
            left = Math.max(left , lastIndex.get(c)+1); 
        }
        lastIndex.put(c,high);
        maxLen = Math.max(maxLen , high - left + 1 );
       } 
       return maxLen;
    }
}