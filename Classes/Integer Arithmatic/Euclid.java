import java.util.*;

class Euclid {
    Divider divider = new Divider();
    Adder adder = new Adder();
    Multiplier multiplier = new Multiplier();
    List<Integer> a;
    List<Integer> b;
    List<Integer> aa;
    List<Integer> bb;
    List<Integer> a_;
    List<Integer> b_;
    List<Integer> q;
    List<Integer> remainder;

    List<Integer> euclid(List<Integer> x, List<Integer> y, int base) {
        a.clear();
        b.clear();
        a_.clear();
        b_.clear();
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