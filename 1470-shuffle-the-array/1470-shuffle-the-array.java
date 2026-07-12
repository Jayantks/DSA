class Solution {
    public int[] shuffle(int[] nums, int n) {
        int i = 0;
        int j = n;
        int[] res = new int[nums.length];
        int k = 0;
        while (i < n && j < 2 * n) {
            res[k++] = nums[i];
            i++;
            res[k++] = nums[j];
            j++;
        }
        return res ;
    }
}