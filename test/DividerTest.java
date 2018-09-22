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
        assertBase2(Base2.ZERO, Base2.ONE, Base2.ZERO, Base2.ZERO); // 0 / 1 = 0 rem 0
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

    // -1 / 1 = -1 rem 0
    @Test
    public void e() {
        assertBase2(Base2.MINUS_ONE, Base2.ONE, Base2.MINUS_ONE, Base2.ZERO);
    }

    // 1 / -1 = -1 rem 0 -> 1 = -1 * -1 + 0
    @Test
    public void f() {
        assertBase2(Base2.ONE, Base2.MINUS_ONE, Base2.MINUS_ONE, Base2.ZERO);
    }

    // -1 / -1 = 1 rem 0
    @Test
    public void g() {
        assertBase2(Base2.MINUS_ONE, Base2.MINUS_ONE, Base2.ONE, Base2.ZERO);
    }

    @Test
    public void h() {
        List<Integer> a = Formatter.toBigInt("5896363941d32eccd5c", 16);
        List<Integer> b = Formatter.toBigInt("c7eb8a91fbad0d1c1f03", 16);
        List<Integer> q = divider.divide(a, b, 16);
        List<Integer> r = divider.getRem();
        assertEquals(BigInt.ZERO, q);
        assertEquals(Formatter.toBigInt("5896363941d32eccd5c", 16), r);
    }

    private void assertBase2(List<Integer> a, List<Integer> b, List<Integer> expectedQ, List<Integer> expectedR) {
        List<Integer> q = divider.divide(a, b, 2);
        List<Integer> r = divider.getRem();
        assertEquals(expectedQ, q);
        assertEquals(expectedR, r);
    }
}