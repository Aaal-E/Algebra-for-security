import java.util.*;
class Euclid {
    Divider divider = new Divider();
    Adder adder = new Adder();
    Multiplier multiplier = new Multiplier();
	ArrayList<Integer> a;
	ArrayList<Integer> b;
	ArrayList<Integer> aa;
    ArrayList<Integer> bb;
	ArrayList<Integer> a_;
	ArrayList<Integer> b_;
	ArrayList<Integer> q;
	ArrayList<Integer> remainder;
    ArrayList<Integer> euclid (ArrayList<Integer> x, ArrayList<Integer> y, int base){
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
            a_ = adder.sub(aa, multiplier.mul(a_,  q, base), base);
            b_ = adder.sub(bb, multiplier.mul(b_, q, base), base);
        }
        
        return x;
    }
    
    ArrayList<Integer> getA() {
        return a;    
    }
    
    ArrayList<Integer> getB() {
        return b;
    }
    
}