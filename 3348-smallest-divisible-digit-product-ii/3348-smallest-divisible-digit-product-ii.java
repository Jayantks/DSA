import java.util.*;

class Solution {
    // digit -> {prime: exponent}
    private static final Map<Integer, Map<Integer,Integer>> DIGIT_FACTORS = new HashMap<>();
    static {
        DIGIT_FACTORS.put(0, Map.of());
        DIGIT_FACTORS.put(1, Map.of());
        DIGIT_FACTORS.put(2, Map.of(2,1));
        DIGIT_FACTORS.put(3, Map.of(3,1));
        DIGIT_FACTORS.put(4, Map.of(2,2));
        DIGIT_FACTORS.put(5, Map.of(5,1));
        DIGIT_FACTORS.put(6, Map.of(2,1,3,1));
        DIGIT_FACTORS.put(7, Map.of(7,1));
        DIGIT_FACTORS.put(8, Map.of(2,3));
        DIGIT_FACTORS.put(9, Map.of(3,2));
    }

    public String smallestNumber(String num, long t) {
        // 1. Factor t into primes 2,3,5,7
        Map<Integer,Integer> primeCount = new HashMap<>();
        primeCount.put(2,0); primeCount.put(3,0); primeCount.put(5,0); primeCount.put(7,0);
        for (int p : new int[]{2,3,5,7}) {
            while (t % p == 0) {
                t /= p;
                primeCount.merge(p, 1, Integer::sum);
            }
        }
        if (t != 1) return "-1";

        Map<Integer,Integer> factorCount = getFactorCount(primeCount);
        if (sumValues(factorCount) > num.length()) {
            return construct(factorCount);
        }

        Map<Integer,Integer> prefix = primeCountFromString(num);
        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = num.length();
            if (isSubset(primeCount, prefix)) {
                return num;
            }
        }

        int n = num.length();
        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            prefix = subtract(prefix, DIGIT_FACTORS.get(d)); // now covers num[0..i-1]
            int spaceAfter = n - 1 - i;
            if (i > firstZero) continue;

            for (int bigger = d + 1; bigger <= 9; bigger++) {
                Map<Integer,Integer> remaining = subtract(
                        subtract(primeCount, prefix), DIGIT_FACTORS.get(bigger));
                Map<Integer,Integer> factorsAfter = getFactorCount(remaining);
                int need = sumValues(factorsAfter);
                if (need <= spaceAfter) {
                    int fillOnes = spaceAfter - need;
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append(bigger);
                    for (int k = 0; k < fillOnes; k++) sb.append('1');
                    sb.append(construct(factorsAfter));
                    return sb.toString();
                }
            }
        }

        // Need to extend length by 1
        Map<Integer,Integer> factorsExt = getFactorCount(primeCount);
        int ones = n + 1 - sumValues(factorsExt);
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < ones; k++) sb.append('1');
        sb.append(construct(factorsExt));
        return sb.toString();
    }

    private Map<Integer,Integer> primeCountFromString(String s) {
        Map<Integer,Integer> count = new HashMap<>();
        count.put(2,0); count.put(3,0); count.put(5,0); count.put(7,0);
        for (char ch : s.toCharArray()) {
            int d = ch - '0';
            for (Map.Entry<Integer,Integer> e : DIGIT_FACTORS.get(d).entrySet()) {
                count.merge(e.getKey(), e.getValue(), Integer::sum);
            }
        }
        return count;
    }

    private Map<Integer,Integer> subtract(Map<Integer,Integer> a, Map<Integer,Integer> b) {
        Map<Integer,Integer> res = new HashMap<>(a);
        for (Map.Entry<Integer,Integer> e : b.entrySet()) {
            int cur = res.getOrDefault(e.getKey(), 0);
            res.put(e.getKey(), Math.max(0, cur - e.getValue()));
        }
        return res;
    }

    private int sumValues(Map<Integer,Integer> d) {
        int sum = 0;
        for (int v : d.values()) sum += v;
        return sum;
    }

    private boolean isSubset(Map<Integer,Integer> a, Map<Integer,Integer> b) {
        for (Map.Entry<Integer,Integer> e : a.entrySet()) {
            if (b.getOrDefault(e.getKey(), 0) < e.getValue()) return false;
        }
        return true;
    }

    // Pack prime counts into the minimal number of digits (2..9)
    private Map<Integer,Integer> getFactorCount(Map<Integer,Integer> count) {
        int c2 = count.getOrDefault(2, 0);
        int c3 = count.getOrDefault(3, 0);

        int count8 = c2 / 3;
        int rem2 = c2 % 3;
        int count9 = c3 / 2;
        int count3 = c3 % 2;
        int count4 = rem2 / 2;
        int count2 = rem2 % 2;
        int count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0; count3 = 0; count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1; count6 = 1; count3 = 0; count4 = 0;
        }

        Map<Integer,Integer> res = new HashMap<>();
        res.put(2, count2);
        res.put(3, count3);
        res.put(4, count4);
        res.put(5, count.getOrDefault(5, 0));
        res.put(6, count6);
        res.put(7, count.getOrDefault(7, 0));
        res.put(8, count8);
        res.put(9, count9);
        return res;
    }

    // Build the digit string in ascending order (2..9) — smallest arrangement
    private String construct(Map<Integer,Integer> factors) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 2; digit <= 9; digit++) {
            int cnt = factors.getOrDefault(digit, 0);
            for (int k = 0; k < cnt; k++) sb.append((char) ('0' + digit));
        }
        return sb.toString();
    }
}