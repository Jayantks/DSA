class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        int count = 0;

        for (int i = 0; i < intervals.length; i = i + 1) {
            int c = intervals[i][0],
                d = intervals[i][1];

            boolean isCovered = false;
            for (int j = 0; j < intervals.length; j = j + 1) {
                // i interval -> koi i ko kha sakta hai ya nahi
                int a = intervals[j][0],
                    b = intervals[j][1];

                if (i != j && (a <= c && b >= d)) {
                    isCovered = true;
                    break;
                }
            }
            if (!isCovered) {
                count = count + 1;
            }
        }
        return count;
    }
}