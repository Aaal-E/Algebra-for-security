import java.util.*;

class Divider {

    private static final Logger LOG = new Logger("divider");

    private List<Integer> q;
    private List<Integer> r;
    private int countAdd;
    private int countMul;

    private Adder adder = new Adder();
    private Multiplier multiplier = new Multiplier();

    /**
     * Calculate division with remainder for x divided by y. Returns the quotient, use getRem() to get the remainder.
     * Based on Benne de Weger Algorithmic Number Theory Algorithm 1.4.
     */
    public List<Integer> divide(List<Integer> a, List<Integer> b, int radix) {
        // Reset variables
        q = null;
        r = null;
        countAdd = 0;
        countMul = 0;

        // Copy since we are modifying the values
        List<Integer> aAbs = new ArrayList<>(a);
        List<Integer> bAbs = new ArrayList<>(b);

        // Take absolute value
        aAbs.set(aAbs.size() - 1, 0);
        bAbs.set(bAbs.size() - 1, 0);

        // The algorithm (Algorithm 1.4) works for |a|>=|b|, for |a|<|b| the result is q=0, r=a
        if (BigInt.lessThan(aAbs, bAbs)) {
            q = BigInt.ZERO;
            r = new ArrayList<>(a); // Make a copy for safety of reference
            return q;
        }

        // Division of nonnegative integers
        divisionNonnegative(aAbs, bAbs, radix);

        // Fix sign of quotient
        if (BigInt.isPositive(a) != BigInt.isPositive(b)) {
            // Sign differs, quotient should become negative
            q.set(q.size() - 1, 1);
        }

        // Fix sign of remainder
        if (BigInt.isNegative(a)) {
            // a is negative, remainder should become negative
            r.set(r.size() - 1, 1);
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
     * Does division on nonnegative integers. Modifies the input integers.
     */
    private void divisionNonnegative(List<Integer> x, List<Integer> y, int b) {

        LOG.infof("divisionNonnegative(x = %s, y = %s, b = %s)", x, y, b);

        // Checks to be sure of the assumptions
        if (BigInt.isNegative(x) || BigInt.isNegative(y)) {
            throw new IllegalArgumentException("negative integer");
        }

        int m = x.size() - 1; // (-1 because of the sign element)
        int n = y.size() - 1;

        if (n == 0 || y.equals(BigInt.ZERO)) {
            throw new UnsupportedOperationException("special case");
        }

        int k = m - n + 1;
        r = x;
        q = new ArrayList<>(k + 1); // (+1 for the sign element)
        q.addAll(Collections.nCopies(k + 1, 0)); // Fill with zeroes

        for (int i = k - 1; i >= 0; i--) {
            // Create b^i
            List<Integer> bi = new ArrayList<>(i + 1);
            bi.addAll(Collections.nCopies(i, 0)); // Add trailing zeros
            bi.add(1); // Add leading one
            bi.add(0); // Add positive sign
            // b^i * y
            List<Integer> biy = multiplier.mul(bi, y, b);

            // r / (b^i*y)
            List<Integer> qi = approximateDivision(r, biy, b);

            // Reduce remainder by q*b^i*y
            List<Integer> toReduce = multiplier.mul(qi, biy, b);
            r = adder.sub(r, toReduce, b);

            LOG.finerf("q_i = %s, r = %s", qi, r);

            int adjustments = 0;

            // Adjust q_i and r if r <= 0
            while (BigInt.isNegative(r)) {
                r = adder.add(r, biy, b);
                qi = adder.sub(qi, BigInt.ONE, b);
                adjustments++;

                LOG.finerf("after adjustment q_i = %s, r = %s", qi, r);
            }

            // Adjust q and r if r is greater than the denominator (b^i * y)
            while (BigInt.greaterThan(r, biy)) {
                r = adder.sub(r, biy, b);
                qi = adder.add(qi, BigInt.ONE, b);
                adjustments++;

                LOG.finerf("after adjustment q_i = %s, r = %s", qi, r);
            }


            LOG.finef("adjustments of the q_i approximation: %s", adjustments);

            // Check if qi has only one word
            if (qi.size() > 2) {
                throw new IllegalStateException("qi should have only one word");
            }

            // Store qi in q
            q.set(i, qi.get(0));

            LOG.finef("q = %s, r = %s", q, r);
        }

        // Remove leading zeros
        BigInt.removeLeadingZeros(q);
    }

    /**
     * Does approximate division for x / y, assuming that |x| <= |y| + 1.
     * Also assumes nonnegative integers and y != 0.
     */
    private List<Integer> approximateDivision(List<Integer> x, List<Integer> y, int b) {

        // Copy big integers since we will modify them
        x = new ArrayList<>(x);
        y = new ArrayList<>(y);

        int leadingIndex = y.size() - 2;
        int approxThreshold = b / 2;

        // Increase denominator as to be able to provide a good approximation
        while (y.get(leadingIndex) < approxThreshold) {
            x = adder.add(x, x, b);
            y = adder.add(y, y, b);
        }

        // Check if numerator is smaller than denominator, i.e. result is 0
        if (x.size() < y.size()) {
            return BigInt.ZERO;
        }

        // Get leading word(s) of numerator in a 32 bit integer
        int leadingNumerator = 0;
        for (int i = leadingIndex; i < x.size() - 1; i++) {
            int pow = i - leadingIndex;
            int elem = x.get(i);
            leadingNumerator += Math.pow(b, pow) * elem;
        }

        // Get leading word of denominator
        int leadingDenominator = y.get(leadingIndex);

        // Do elementary floor division
        int result = leadingNumerator / leadingDenominator;
        countMul += 1;

        LOG.finef("approximateDivision(x = %s, y = %s, b = %s) = %s", x, y, b, result);

        return Formatter.toBigInt(result, b);
    }

}