/*
	stores big numbers as a boolean arraylist
	maybe add a method to convert to string (in base b)
*/

import java.util.*;

/**
 * Stores a big integer, immutable.
 * Representation for array of length n: trailing digit at index 0, leading digit at n-2, sign at n-1.
 * Sign is 0 for positive, 1 for negative.
 */
class BigInteger implements Comparable<BigInteger> {

    public static final BigInteger ZERO = new BigInteger("0", Character.MAX_RADIX);

    public final int radix;
    private final List<Integer> integer;

    /**
     * Creates the big integer from a string.
     */
    public BigInteger(String number, int radix) {
        this.radix = radix;
        this.integer = new ArrayList<>();

        int neg = 0;
        if (number.substring(0, 1).equals("-")) {
            number = number.substring(1);
            neg = 1;
        }

        for (int i = number.length() - 1; i >= 0; i--) {
            integer.add(Integer.parseInt(number.substring(i, i + 1), radix));
        }
        integer.add(neg);

        removeLeadingZeros();
    }

    public BigInteger(List<Integer> n, int radix) {
        if (n.size() < 2) {
            throw new IllegalArgumentException("incorrect big integer representation");
        }
        // Check for negative 0
        if (n.equals(Arrays.asList(0, 1))) {
            throw new IllegalArgumentException("negative 0 not allowed");
        }
        this.radix = radix;
        this.integer = new ArrayList<>(n);

        removeLeadingZeros();
    }

    /**
     * Returns the integer array, immutable.
     */
    public List<Integer> getInteger() {
        return Collections.unmodifiableList(integer);
    }

    /**
     * Returns the integer array, immutable.
     */
    public ArrayList<Integer> getIntegerLegacy() {
        return new ArrayList<>(integer);
    }

    /**
     * Returns whether the number is positive.
     */
    public boolean isPositive() {
        return integer.get(integer.size() - 1) == 0;
    }

    /**
     * Returns whether the number is negative.
     */
    public boolean isNegative() {
        return !this.isPositive();
    }


    /**
     * Returns a string representation using the original radix.
     */
    @Override
    public String toString() {
        return toString(radix);
    }

    /**
     * Returns a string representation using given radix.
     */
    public String toString(int radix) {
        StringBuilder result = new StringBuilder();
        if (isNegative()) {
            result.append('-');
        }
        for (int i = integer.size() - 2; i >= 0; i--) {
            // Print the numbers
            int number = integer.get(i);
            result.append(Integer.toString(number, radix));
        }
        return result.toString();
    }

    private void removeLeadingZeros() {
        while (integer.size() > 2 && integer.get(integer.size() - 2) == 0) {
            integer.remove(integer.size() - 2);
        }
    }

    @Override
    public int compareTo(BigInteger o) {
        // Check if sign differs
        if (isPositive() != o.isPositive()) {
            return isPositive() ? 1 : -1;
        }
        int signCorrection = isPositive() ? 1 : -1;

        // Check if array length differs
        if (integer.size() != o.integer.size()) {
            return signCorrection * (integer.size() - o.integer.size());
        }
        // Length is the same and sign is the same, compare all words starting at leading digit
        for (int i = integer.size() - 2; i >= 0; i--) {
            if (!integer.get(i).equals(o.integer.get(i))) {
                return signCorrection * (integer.get(i) - o.integer.get(i));
            }
        }
        // x equals y
        return 0;
    }
}