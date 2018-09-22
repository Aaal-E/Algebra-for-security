import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EuclidTest {

    int radix;

    @Test
    public void a() {
        Euclid euclid = new Euclid();
        radix = 16;
        List<Integer> x = bigint("58");
        List<Integer> y= bigint("c7e");
        List<Integer> d = euclid.euclid(x, y, radix);
        List<Integer> a = euclid.getX();
        List<Integer> b = euclid.getY();
        System.out.printf("d=%s, a=%s, b=%s\n", str(d), str(a), str(b));
        assertEquals("2", str(d));
        assertEquals("-6d", str(a));
        assertEquals("3", str(b));

    }


    private List<Integer> bigint(String s) {
        return Formatter.toBigInt(s, radix);
    }

    private String str(List<Integer> bigInt) {
        return Formatter.toString(bigInt, radix);
    }
}