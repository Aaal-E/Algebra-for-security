/*
	stores big numbers as a boolean arraylist
	maybe add a method to convert to string (in base b)
*/

import java.util.*;

/**
 * Stores a big integer, immutable.
 */
class BigInteger {

    public final int radix;
    private final List<Integer> integer = new ArrayList<>();

    /**
     * Creates the big integer from a string.
     */
    public BigInteger(String number, int radix) {
        this.radix = radix;

        int neg = 0;
        if (number.substring(0, 1).equals("-")) {
            number = number.substring(1);
            neg = 1;
        }

        for (int i = number.length() - 1; i >= 0; i--) {
            integer.add(Integer.parseInt(number.substring(i, i + 1), radix));
        }
        integer.add(neg);
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
            result.append(Integer.toString(integer.get(i), radix));
        }
        return result.toString();
    }
}