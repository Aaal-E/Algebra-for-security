import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DividerTest {

    Divider divider;

    @Before
    public void setUp() throws Exception {
        divider = new Divider();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void zeroOne() {
        assertBase2(Base2.ZERO, Base2.ONE, Base2.ZERO, Base2.ONE); // 0 / 1 = 0 rem 1
    }

    @Test
    public void oneOne() {
        assertBase2(Base2.ONE, Base2.ONE, Base2.ONE, Base2.ZERO); // 1 / 1 = 1 rem 0
    }

    @Test
    public void twoOne() {
        assertBase2(Base2.TWO, Base2.ONE, Base2.TWO, Base2.ZERO); // 2 / 1 = 2 rem 0
    }

    @Test
    public void threeTwo() {
        assertBase2(Base2.THREE, Base2.TWO, Base2.ONE, Base2.ONE); // 3 / 2 = 1 rem 1
    }

    private void assertBase2(List<Integer> a, List<Integer> b, List<Integer> expectedQ, List<Integer> expectedR) {
        List<Integer> q = divider.divide(a, b, 2);
        List<Integer> r = divider.getRem();
        assertEquals(expectedQ, q);
        assertEquals(expectedR, r);
    }
}