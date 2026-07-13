class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int expectedSum = (1 + n) * n / 2;
        Set<Integer> uniqueNumbers = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();
        int uniqueSum = 0;
        for (int num : nums) {
            if (set.add(num)) { // add returns false if it was already present
                uniqueSum += num;
            }
        }
        int s = Arrays.stream(nums).sum();
        int duplicate = s - uniqueSum;
        int missing = expectedSum - uniqueSum;
        return new int[] { duplicate, missing };
    }
}