import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AdderTest {

    @Test
    public void add() {
        Adder a = new Adder();
        List<Integer> r; // Stores result

        r = a.add(Base2.ZERO, Base2.ZERO, 2); // 0+0
        Assert.listEquals(Base2.ZERO, r);

        r = a.add(Base2.ZERO, Base2.ONE, 2); // 0+1
        Assert.listEquals(Base2.ONE, r);

        r = a.add(Base2.ONE, Base2.ZERO, 2); // 1+0
        Assert.listEquals(Base2.ONE, r);

        r = a.add(Base2.ONE, Base2.ONE, 2); // 1+1
        Assert.listEquals(Base2.TWO, r);

        r = a.add(Base2.ONE, Base2.TWO, 2); // 1+2
        Assert.listEquals(Base2.THREE, r);

        r = a.add(Base2.ZERO, Base2.MINUS_ONE, 2); // 0 + -1
        Assert.listEquals(Base2.MINUS_ONE, r);

        r = a.add(Base2.MINUS_ONE, Base2.MINUS_ONE, 2); // -1 + -1
        Assert.listEquals(Base2.MINUS_TWO, r);

        r = a.add(Base2.ONE, Base2.MINUS_ONE, 2); // 1 + -1
        Assert.listEquals(Base2.ZERO, r);
    }

    @Test
    public void sub() {
        Adder a = new Adder();
        List<Integer> r; // Stores result

        r = a.sub(Base2.ZERO, Base2.ZERO, 2); // 0-0
        Assert.listEquals(Base2.ZERO, r);

        r = a.sub(Base2.ZERO, Base2.ONE, 2); // 0-1
        Assert.listEquals(Base2.MINUS_ONE, r);

        r = a.sub(Base2.ONE, Base2.ZERO, 2); // 1-0
        Assert.listEquals(Base2.ONE, r);

        r = a.sub(Base2.ONE, Base2.ONE, 2); // 1-1
        Assert.listEquals(Base2.ZERO, r);

        r = a.sub(Base2.ONE, Base2.TWO, 2); // 1-2
        Assert.listEquals(Base2.MINUS_ONE, r);

        r = a.sub(Base2.ZERO, Base2.MINUS_ONE, 2); // 0 - -1
        Assert.listEquals(Base2.ONE, r);

        r = a.sub(Base2.MINUS_ONE, Base2.MINUS_ONE, 2); // -1 - -1
        Assert.listEquals(Base2.ZERO, r);

        r = a.sub(Base2.ONE, Base2.MINUS_ONE, 2); // 1 - -1
        Assert.listEquals(Base2.TWO, r);
    }
}