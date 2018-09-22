import java.util.*;

/**
 * Multiplication by the method of Karatsuba
 */
class Karatsuba {

    // Primary school multiplier is used for the base cases.
    private Multiplier multiplier = new Multiplier();

    private Adder adder = new Adder();

    List<Integer> karatsuba(List<Integer> x, List<Integer> y, int radix) {

        // Find sign for the answer
        int resultSign = (BigInt.isPositive(x) == BigInt.isPositive(y)) ? 0 : 1;

        // Copy big integers since we'll modify them
        List<Integer> xPositive = new ArrayList<>(x);
        List<Integer> yPositive = new ArrayList<>(y);

        // Make both integers positive for the Karatsuba algorithm
        xPositive.set(xPositive.size() - 1, 0);
        yPositive.set(yPositive.size() - 1, 0);

        // Execute Karatsuba
        List<Integer> result = karatsubaRecursively(xPositive, yPositive, radix);

        // Update sign
        result.set(result.size() - 1, resultSign);

        // Check for negative zero
        if (result.equals(Arrays.asList(0, 1))) {
            result = BigInt.ZERO;
        }

        // Return
        return result;
    }


    /**
     * Recursively runs Karatsuba, by calling itself.
     * Assumes positive integers.
     */
    private List<Integer> karatsubaRecursively(List<Integer> x, List<Integer> y, int radix) {
        // Check assumption to be sure
        if (BigInt.isNegative(x) || BigInt.isNegative(y)) {
            throw new IllegalArgumentException("only positive integers allowed");
        }

        // Base case: word size is 0 or 1
        if (x.size() <= 2 || y.size() <= 2) {
            // Do primary school multiply
            return multiplier.mul(x, y, radix);
        }

        // Split integer in half, rounded up
        int n = Math.max(x.size() - 1, y.size() - 1);
        int split = (n + 1) / 2;
        List<Integer> xHigh = mostSignificant(x, split);
        List<Integer> xLow = leastSignificant(x, split);
        List<Integer> yHigh = mostSignificant(y, split);
        List<Integer> yLow = leastSignificant(y, split);

        // Recurse
        List<Integer> z0 = karatsuba(xLow, yLow, radix);
        List<Integer> z2 = karatsuba(xHigh, yHigh, radix);
        List<Integer> z1 = karatsuba(adder.add(xLow, xHigh, radix), adder.add(yLow, yHigh, radix), radix);
        z1 = adder.sub(z1, z2, radix);
        z1 = adder.sub(z1, z0, radix);

        // Combine using shift
        List<Integer> combined = shift(z2, 2 * split);
        combined = adder.add(combined, shift(z1, split), radix);
        combined = adder.add(combined, z0, radix);

        return combined;
    }


    /**
     * Returns a new big integer with only the least significant words until (excluding) the given split index.
     * Assumes positive integers.
     */
    private List<Integer> leastSignificant(List<Integer> integer, int split) {
        // Check assumption to be sure
        if (BigInt.isNegative(integer)) {
            throw new IllegalArgumentException("only positive integers allowed");
        }

        // Find split index
        int index = Math.min(split, integer.size() - 1); // (minus one is needed to discard the sign word)

        // Create new big integer
        List<Integer> splitted = new ArrayList<>(integer.subList(0, index));

        // Make sure it has a valid format
        if (splitted.size() == 0) {
            splitted.add(0);
        }

        // Add positive sign at the end
        splitted.add(0);

        // Remove leading zeros
        BigInt.removeLeadingZeros(splitted);

        return splitted;
    }

    /**
     * Returns a new big integer with only the most significant words starting from the given index.
     * Assumes positive integers.
     */
    private List<Integer> mostSignificant(List<Integer> integer, int split) {
        // Check assumption to be sure
        if (BigInt.isNegative(integer)) {
            throw new IllegalArgumentException("only positive integers allowed");
        }

        // Find split index
        int index = Math.min(split, integer.size() - 1);

        // Create new big integer
        List<Integer> splitted = new ArrayList<>(integer.subList(index, integer.size() - 1));

        // Make sure it has a valid format
        if (splitted.size() == 0) {
            splitted.add(0);
        }

        // Add positive sign at the end
        splitted.add(0);

        // Remove leading zeros
        BigInt.removeLeadingZeros(splitted);

        return splitted;
    }

    /**
     * Add zeros to the end of the integer, i.e. do integer*radix^shift.
     */
    private List<Integer> shift(List<Integer> integer, int shift) {
        // If the integer is zero, do not shift, return zero
        if (BigInt.isZero(integer)) {
            return BigInt.ZERO; // (returning different instance for reference safety)
        }

        List<Integer> result = new ArrayList<>(integer.size() + shift);

        // Add the least significant zeros first
        result.addAll(Collections.nCopies(shift, 0));

        // Add the words on top of it (more significant)
        result.addAll(integer);

        // Copy the sign
        result.add(integer.get(integer.size() - 1));

        return result;
    }
}