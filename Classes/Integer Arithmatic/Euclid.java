import java.util.*;

class Euclid {

    private List<Integer> x;
    private List<Integer> y;

    /**
     * Input: a, b element of Z
     * Output: d, x, y elements of Z with d = gcd(a,b) = xa + yb
     * Algorithm based on Benne de Weger Algorithmic Number Theory Algorithm 2.2
     *
     * @return d
     */
    List<Integer> euclid(List<Integer> a, List<Integer> b, int base) {
        Divider divider = new Divider();
        Adder adder = new Adder();
        Multiplier multiplier = new Multiplier();

        // Copy so we can modify
        List<Integer> aPrime = new ArrayList<>(a);
        List<Integer> bPrime = new ArrayList<>(b);

        // Take the absolute value
        aPrime.set(aPrime.size() - 1, 0);
        bPrime.set(bPrime.size() - 1, 0);

        // Initialize x1, x2, y1, y2
        List<Integer> x1 = Arrays.asList(1, 0);
        List<Integer> x2 = Arrays.asList(0, 0);
        List<Integer> y1 = Arrays.asList(0, 0);
        List<Integer> y2 = Arrays.asList(1, 0);

        // While bPrime > 0 (i.e. bPrime is positive and not zero)
        while (bPrime.get(bPrime.size() - 1) == 0 && (bPrime.size() > 2 || bPrime.get(0) != 0)) {
            // a' / b'
            List<Integer> q = divider.divide(aPrime, bPrime, base);
            List<Integer> r = divider.getRem();

            aPrime = bPrime;
            bPrime = r;

            List<Integer> x3 = adder.sub(x1, multiplier.mul(q, x2, base), base);
            List<Integer> y3 = adder.sub(y1, multiplier.mul(q, y2, base), base);
            x1 = x2;
            y1 = y2;
            x2 = x3;
            y2 = y3;

        }

        List<Integer> d = aPrime;
        x = x1;
        y = y1;

        // Copy the sign of original numbers
        x.set(x.size() - 1, a.get(a.size() - 1));
        y.set(y.size() - 1, b.get(b.size() - 1));

        return d;
    }

    List<Integer> getX() {
        return x;
    }

    List<Integer> getY() {
        return y;
    }

}