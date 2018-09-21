import java.util.*;

class Divider {

    private List<Integer> q;
    private List<Integer> r;
    private int countAdd;
    private int countMul;

    /**
     * Calculate division with remainder for x divided by y. Returns the quotient, use getRem() to get the remainder.
     * Based on Benne de Weger Algorithmic Number Theory Algorithm 1.4.
     * Assumes |a|>|b|
     */
    public List<Integer> divide(List<Integer> a, List<Integer> b, int base) {
        // Reset variables
        q = null;
        r = null;
        countAdd = 0;
        countMul = 0;

        // Check for leading zeros to be sure
        if (hasLeadingZeros(a) || hasLeadingZeros(b)) {
            throw new IllegalArgumentException("leading zeros detected");
        }

        // Take absolute value
        List<Integer> aAbs = new ArrayList<>(a);
        List<Integer> bAbs = new ArrayList<>(b);
        aAbs.set(aAbs.size() - 1, 0);
        bAbs.set(bAbs.size() - 1, 0);

        // Division of nonnegative integers
        divisionNonnegative(aAbs, bAbs, base);

        /*


        // Split number and sign
        // xUnsigned/yUnsigned is the original number with the sign element removed
        // At index 0 is the least significant word
        List<Integer> aUnsigned = new ArrayList<>(a);
        List<Integer> bUnsigned = new ArrayList<>(b);

        // If x < y, we have quotient = 0 and remainder = x
        if (lessThanUnsigned(a, b)) {
            r = a;
            // Return positive 0
            return Arrays.asList(0, 0);
        }

        int k = aUnsigned.size();
        int l = bUnsigned.size();

        // The quotient will have at most m digits in current base
        int m = k - l + 1;

        // Initialize quotient to an array of length m
        List<Integer> q = new ArrayList<>(m);
        // Fill with zeroes
        q.addAll(Collections.nCopies(m, 0));

        // Fill remainder with a and add leading zero
        r = new ArrayList<>(aUnsigned);
        r.add(0);

        for (int i = k - l; i >= 0; i--) {

            int lhs = r.get(i + l) * base + r.get(i + l - 1);
            int rhs = b.get(l - 1);
            q.set(i, lhs / rhs);

            if (q.get(i) >= base) {
                // Overflow
                q.set(i, base - 1);
            }

            int carry = 0;

            for (int j = 0; j <= l - 1; j++) {
                int tmp = r.get(i + j) - q.get(i) * b.get(j) + carry;

                // Qoutient
                carry = tmp / base;
                // Remainder
                r.set(i + j, tmp % base);
            }
            r.set(i + l, r.get(i + l) + carry);

            while (r.get(i + l) < 0) {
                carry = 0;
                for (int j = 0; j <= l - 1; j++) {
                    int tmp = r.get(i + j) + b.get(i) + carry;

                    // Quotient
                    carry = tmp / base;
                    // Remainder
                    r.set(i + j, tmp % base);
                }
                r.set(i + l, r.get(i + l) + carry);
                q.set(i, q.get(i) - 1);
            }
        }

        q = q.subList(0, k - l + 1);
        r = r.subList(0, l);

        */

        // Find signs
        boolean aPositive = a.remove(a.size() - 1) == 0;
        boolean bPositive = b.remove(b.size() - 1) == 0;


        // Fix sign of quotient
        if (aPositive == bPositive) {
            // Both numbers have the same sign, quotient is positive
            q.add(0);
        } else {
            // Sign differs, quotient is negative
            q.add(1);
        }

        // Fix sign of remainder
        if (aPositive) {
            // a is positive, remainder is positive
            r.add(0);
        } else {
            // a is negative, remainder is negative
            r.add(1);
        }

        // Correct for negative 0
        if (q.equals(Arrays.asList(0, 1))) {
            q = Arrays.asList(0, 0);
        }
        if (r.equals(Arrays.asList(0, 1))) {
            r = Arrays.asList(0, 0);
        }

        return q;
    }

    public List<Integer> getRem() {
        return r;
    }

    /**
     * Does division on nonnegative integers.
     */
    private void divisionNonnegative(List<Integer> x, List<Integer> y, int b) {
        // Checks to be sure of the assumptions
        if (x.get(x.size() - 1) == 1 || y.get(y.size() - 1) == 1) {
            throw new IllegalArgumentException("negative integer");
        }
        if (hasLeadingZeros(x) || hasLeadingZeros(y)) {
            throw new IllegalArgumentException("integer has leading zeros");
        }


    }

    /**
     * Compares two integers and returns x < y. Assumes a list without a sign element. Assumes no leading zeros.
     */
    private boolean lessThanUnsigned(List<Integer> x, List<Integer> y) {
        if (x.size() != y.size()) {
            // Length differs
            return x.size() < y.size();
        }
        // Length is the same, compare all words
        for (int i = x.size() - 1; i >= 0; i--) {
            if (!x.get(i).equals(y.get(i))) {
                return x.get(i) < y.get(i);
            }
        }
        // x equals y
        return false;
    }

    /**
     * Returns if the signed big integer has leading zeros.
     */
    private boolean hasLeadingZeros(List<Integer> n) {
        // Check if second to last is zero
        return n.size() > 2 && n.get(n.size() - 2) == 0;
    }
}