import java.util.List;
import static org.junit.Assert.*;

public class Assert {
    public static void listEquals(List expected, List result) {
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
