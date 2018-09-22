import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Conversion between string and big integer.
 */
class Formatter {

    /**
     * Returns a string representation using given radix.
     */
    static String toString(List<Integer> n, int radix) {
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
    static List<Integer> toBigInt(String number, int radix) {
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

        return n;
    }

    /**
     * Creates a big integer from a 32-bit integer.
     */
    static List<Integer> toBigInt(int n, int radix) {
        List<Integer> result = new ArrayList<>();

        if (n == 0) {
            return Arrays.asList(0, 0);
        }

        // Determine sign and make n positive
        int sign = 0;
        if (n < 0) {
            sign = 1;
            n *= -1;
        }

        // Reduce n and fill words
        while (n != 0) {
            result.add(n % radix);
            n = n / radix;
        }

        // Add sign
        result.add(sign);
        return result;
    }
}
