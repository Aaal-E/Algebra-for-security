import java.util.*;

class Euclid {

    private List<Integer> a;
    private List<Integer> b;

    List<Integer> euclid(List<Integer> x, List<Integer> y, int base) {
        Divider divider = new Divider();
        Adder adder = new Adder();
        Multiplier multiplier = new Multiplier();

        a = new ArrayList<>();
        b = new ArrayList<>();
        List<Integer> aa;
        List<Integer> bb;
        List<Integer> a_ = new ArrayList<>();
        List<Integer> b_ = new ArrayList<>();
        List<Integer> q;
        List<Integer> remainder;

        a.add(1);
        a.add(0);
        a_.add(0);
        a_.add(0);
        b.add(0);
        b.add(0);
        b_.add(1);
        b_.add(0);

        while (y.size() != 2 || y.get(0) != 0) {
            q = divider.divide(x, y, base);
            remainder = divider.getRem();
            aa = a;
            bb = b;
            a = a_;
            b = b_;
            x = y;
            y = remainder;
            a_ = adder.sub(aa, multiplier.mul(a_, q, base), base);
            b_ = adder.sub(bb, multiplier.mul(b_, q, base), base);
        }

        return x;
    }

    List<Integer> getA() {
        return a;
    }

    List<Integer> getB() {
        return b;
    }

}