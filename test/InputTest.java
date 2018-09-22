import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class InputTest {
    Adder adder;
    Multiplier multiplier;
    Karatsuba karatsuba;
    Modulator modulator;
    Euclid euclid;
    Inverser inverser;

    List<Integer> x;
    List<Integer> y;
    List<Integer> m;
    List<Integer> d;
    List<Integer> a;
    List<Integer> b;
    List<Integer> ans;
    String ansStr;
    int radix;

    @Before
    public void setUp() {
        adder = new Adder();
        multiplier = new Multiplier();
        karatsuba = new Karatsuba();
        modulator = new Modulator();
        euclid = new Euclid();
        inverser = new Inverser();
    }

    @Test
    public void a() {
        radix = 2;
        x = bigint("1100110001011011110011101011100100000001010101111101001111010101111100110110001100010100001011111010");
        y = bigint("1001011010110100010111100101110011001010001010001001010100111001010011000101001110000101000001010111");
        ansStr = str(adder.add(x, y, radix));
        assertEquals("10110001100010000001011010001010111001011100000000110100100001111001111111011011010011001001101010001", ansStr);
    }

    @Test
    public void b() {
        radix = 2;
        x = bigint("1111001001111000100100000101010011011111111101100110100101110101101100100110001010011101011010001011");
        y = bigint("1001011010100110111010101000101110010010111011100100111101010001011000110110100111011000110001011111");
        ansStr = str(adder.sub(x, y, radix));
        assertEquals("101101111010001101001011100100101001101000010000001101000100100010011101111100011000100101000101100", ansStr);
    }

    @Test
    public void c() {
        radix = 2;
        x = bigint("1111001110011011011101001100010101111000011010100100001101011101111101101110010001111010001010110011");
        y = bigint("1110110000001110100000101100010100101010110001101100011011101000101001001001000100001111010100100110");
        ansStr = str(multiplier.mul(x, y, radix));
        assertEquals("11100000101000010001111010010110111111100111000001010111101100100100001100000010011011111001001001111100000010010100100011101011001011000101101001100010110001000001001000100011100001110111010110010010", ansStr);
    }

    @Test
    public void dKaratsuba() {
        radix = 2;
        x = bigint("1111001110011011011101001100010101111000011010100100001101011101111101101110010001111010001010110011");
        y = bigint("1110110000001110100000101100010100101010110001101100011011101000101001001001000100001111010100100110");
        ansStr = str(karatsuba.karatsuba(x, y, radix));
        assertEquals("11100000101000010001111010010110111111100111000001010111101100100100001100000010011011111001001001111100000010010100100011101011001011000101101001100010110001000001001000100011100001110111010110010010", ansStr);
    }

    @Test
    public void e() {
        radix = 3;
        x = bigint("1021002222220100111012120000201102021221");
        y = bigint("1012200010211212111211101000010010220000");
        ansStr = str(adder.add(x, y, radix));
        assertEquals("2110210010202020000000221000211120011221", ansStr);
    }

    @Test
    public void f() {
        radix = 3;
        x = bigint("2021112002201010101201001221200211210001");
        y = bigint("1211222222202101122110112222212210012122");
        ans = adder.sub(x, y, radix);
        assertEquals("102112002221201202020111221211001120102", str(ans));
    }

    @Test
    public void g() {
        radix = 3;
        x = bigint("2010220211120222202210012012101022110011");
        y = bigint("-2102021102022210001211210202210112011002");
        ans = multiplier.mul(x, y, radix);
        assertEquals("-12012000201010022100110110121211221122212001010101210112211010012201102111111022", str(ans));
    }

    @Test
    public void hKaratsuba() {
        radix = 3;
        x = bigint("2010220211120222202210012012101022110011");
        y = bigint("-2102021102022210001211210202210112011002");
        ans = karatsuba.karatsuba(x, y, radix);
        assertEquals("-12012000201010022100110110121211221122212001010101210112211010012201102111111022", str(ans));
    }

    @Test
    public void i() {
        radix = 16;
        x = bigint("df9e76d113895821c567");
        y = bigint("6a20b188675ab39e17a2");
        ans = adder.add(x, y, radix);
        assertEquals("149bf28597ae40bbfdd09", str(ans));
    }

    @Test
    public void j() {
        radix = 16;
        x = bigint("36d2b5154ab14bfabbf2");
        y = bigint("f9d1495cfafe396ae4b1");
        ans = adder.sub(x, y, radix);
        assertEquals("-c2fe9447b04ced7028bf", str(ans));
    }

    @Test
    public void kMultiply() {
        radix = 16;
        x = bigint("-eed50d6aa53e51691add");
        y = bigint("-f9027b863f654daae6a8");
        ans = multiplier.mul(x, y, radix);
        assertEquals("e84f8af471ab1bb45d20f1a95313171b2ade2f08", str(ans));
    }

    @Test
    public void lKaratsuba() {
        radix = 16;
        x = bigint("-eed50d6aa53e51691add");
        y = bigint("-f9027b863f654daae6a8");
        ans = karatsuba.karatsuba(x, y, radix);
        assertEquals("e84f8af471ab1bb45d20f1a95313171b2ade2f08", str(ans));
    }

    @Test
    public void mReduce() {
        radix = 16;
        x = bigint("ffbd238907b7d47c8f011379ad54173b9502beb1");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = modulator.mod(x, m, radix);
        assertEquals("c0808380322a6abc359a", str(ans));
    }

    @Test
    public void nAddWithModulo() {
        radix = 16;
        x = bigint("54311bd480c5d7f89db4");
        y = bigint("96389ae5100438574eaf");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = adder.add(x, y, m, radix);
        assertEquals("227e2c27951d0333cd60", str(ans));
    }

    @Test
    public void oSubtractModulo() {
        radix = 16;
        x = bigint("62f73b5b5c02ab69e6f5");
        y = bigint("7a3e5237d2111e1d46fd");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = adder.sub(x, y, m, radix);
        assertEquals("b0a473b5859e9a68befb", str(ans));
    }

    @Test
    public void pMultiplyModulo() {
        radix = 16;
        x = bigint("44105f31659258bdf082");
        y = bigint("86347b5906a96ca11cc2");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = multiplier.mul(x, y, m, radix);
        assertEquals("9c1b2bab5c0ca148e260", str(ans));
    }

    @Test
    public void qEuclid() {
        radix = 16;
        x = bigint("5896363941d32eccd5c");
        y = bigint("c7eb8a91fbad0d1c1f03");
        d = euclid.euclid(x, y, radix);
        a = euclid.getX();
        b = euclid.getY();
        System.out.printf("d=%s, a=%s, b=%s\n", str(d), str(a), str(b));
        assertEquals("1", str(d));
        assertEquals("96998fcd4268440ce6a5", str(a)); // 15DAB57F3EBF751CE43
        assertEquals("-42bb80ba0313b9aff19", str(b)); // -3151FAC4B944C90F385E
    }

    @Test
    public void rInvert() {
        radix = 16;
        x = bigint("5896363941d32eccd5c");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = inverser.invert(x, m, radix);
        assertEquals("96998fcd4268440ce6a5", str(ans));
    }

    @Test
    public void sInvert() {
        radix = 16;
        x = bigint("b99ab2815ee4b4a5f842");
        m = bigint("c7eb8a91fbad0d1c1f03");
        ans = inverser.invert(x, m, radix);
        assertNull(ans);
    }


    private List<Integer> bigint(String s) {
        return Formatter.toBigInt(s, radix);
    }

    private String str(List<Integer> bigInt) {
        return Formatter.toString(bigInt, radix);
    }
}
