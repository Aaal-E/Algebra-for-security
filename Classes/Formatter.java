import java.util.ArrayList;
import java.util.List;

/**
 * Conversion between string and big integer.
 */
public class Formatter {

    /**
     * Returns a string representation using given radix.
     */
    public static String toString(List<Integer> n, int radix) {
        // Remove leading zeros to be sure
        BigInt.removeLeadingZeros(n);

        StringBuilder result = new StringBuilder();
        if (BigInt.isNegative(n)) {
            result.append('-');
        }
        for (int i = n.size() - 2; i >= 0; i--) {
            // Print the numbers
            int number = n.get(i);
            result.append(Integer.toString(number, radix));
        }
        return result.toString();
    }

    /**
     * Creates a big integer from a string.
     */
    public static List<Integer> toBigInt(String number, int radix) {
        List<Integer> n = new ArrayList<>();

        // Check sign
        int neg = 0;
        if (number.substring(0, 1).equals("-")) {
            number = number.substring(1);
            neg = 1;
        }

        // Parse words
        for (int i = number.length() - 1; i >= 0; i--) {
            n.add(Integer.parseInt(number.substring(i, i + 1), radix));
        }
        // Add sign
        n.add(neg);

        // Remove leading zeros to be sure
        BigInt.removeLeadingZeros(n);

        return n;
    }
}
