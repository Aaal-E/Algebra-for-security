import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Helper functions for big integers.
 */
class BigInt {

    final static List<Integer> ZERO = Arrays.asList(0, 0);
    final static List<Integer> ONE = Arrays.asList(1, 0);

    /**
     * Compares two big integers. Assumes they have the same radix.
     */
    static int compare(List<Integer> a, List<Integer> b) {
        // Check if sign differs
        if (isPositive(a) != isPositive(b)) {
            return isPositive(a) ? 1 : -1;
        }
        int signCorrection = isPositive(a) ? 1 : -1;

        // Check if array length differs
        if (a.size() != b.size()) {
            return signCorrection * (a.size() - b.size());
        }
        // Length is the same and sign is the same, compare all words starting at leading digit
        for (int i = a.size() - 2; i >= 0; i--) {
            if (!a.get(i).equals(b.get(i))) {
                return signCorrection * (a.get(i) - b.get(i));
            }
        }
        // x equals y
        return 0;
    }

    static boolean lessThan(List<Integer> a, List<Integer> b) {
        return compare(a, b) < 0;
    }

    static boolean greaterThan(List<Integer> a, List<Integer> b) {
        return compare(a, b) > 0;
    }

    static boolean greaterOrEqual(List<Integer> a, List<Integer> b) {
        return compare(a, b) >= 0;
    }

    static boolean isPositive(List<Integer> n) {
        return n.get(n.size() - 1) == 0;
    }

    static boolean isNegative(List<Integer> n) {
        return !isPositive(n);
    }

    static boolean isZero(List<Integer> n) {
        return n.equals(ZERO);
    }

    static void removeLeadingZeros(List<Integer> n) {
        while (n.size() > 2 && n.get(n.size() - 2) == 0) {
            n.remove(n.size() - 2);
        }
    }

    /**
     * Changes the sign of the integer, i.e. does n*-1.
     */
    static List<Integer> swapSign(List<Integer> n) {
        List<Integer> result = new ArrayList<>(n);
        int newSign = 1 - n.get(n.size() - 1);
        result.set(result.size() - 1, newSign);
        return result;
    }
}
