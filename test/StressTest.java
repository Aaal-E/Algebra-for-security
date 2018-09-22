import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Ignore
public class StressTest {

    Adder adder = new Adder();
    Multiplier multiplier = new Multiplier();
    Euclid euclid = new Euclid();
    Karatsuba karatsuba = new Karatsuba();

    @Test
    public void addition() {
        int size = 100000;
        List<Integer> large = new ArrayList<>(size + 1);
        large.addAll(Collections.nCopies(size, 1));
        large.add(0);
        List<Integer> ans = adder.add(large, large, 10);
        System.out.println(Formatter.toString(ans, 10));


    }

    @Test
    public void multiply() {
        int size = 10000;
        List<Integer> large = new ArrayList<>(size + 1);
        large.addAll(Collections.nCopies(size, 1));
        large.add(0);
        List<Integer> ans = multiplier.mul(large, large, 10);
        System.out.println(Formatter.toString(ans, 10));
    }

    @Test
    public void euclid() {
        int size = 100000;
        List<Integer> large = new ArrayList<>(size + 1);
        large.addAll(Collections.nCopies(size, 1));
        large.add(0);
        List<Integer> ans = euclid.euclid(large, large, 10);
        System.out.println(Formatter.toString(ans, 10));
    }

    @Test
    public void formatter() {
        int size = 10000000;
        List<Integer> large = new ArrayList<>(size + 1);
        large.addAll(Collections.nCopies(size, 1));
        large.add(0);
        System.out.println(Formatter.toString(large, 10));

    }

    @Test
    public void karatsuba() {
        int size = 10000;
        List<Integer> large = new ArrayList<>(size + 1);
        large.addAll(Collections.nCopies(size, 1));
        large.add(0);
        List<Integer> ans = karatsuba.karatsuba(large, large, 10);
        System.out.println(Formatter.toString(ans, 10));
    }
}
