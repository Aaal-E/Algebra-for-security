/*
	handles the in and output to and from files, as well as the conversion from our storage format to a printable format
*/


import java.util.ArrayList;
import java.util.List;

public class Formatter {


    //converts the array to a binary String
    public static String toString(ArrayList<Integer> number) {
        String result = "";
        if (number.get(number.size()) == 1) result = result + "-";
        number.remove(number.size());
        for (int i = number.size(); i > 0; i--) {
            result = result + number.get(i);
        }
        return result;
    }

    //converts the array to a String of base b
    String toString(ArrayList<Integer> number, int base) {
        String result = "";
        if (number.get(number.size()) == 1) result = result + "-";
        for (int i = number.size(); i > 0; i--) {
            result = result + Integer.toString(number.get(i), base);
        }
        return result;
    }


    /**
     * Convert String to BigInteger
     */
    public static List<Integer> store(String str, int base) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int neg = 0;
        if (str.substring(0, 1) == "-") {
            str = str.substring(1, str.length());
            neg = 1;
        }

        for (int i = str.length(); i > 0; i--) {
            result.add(Integer.parseInt(str.substring(i, i + 1), base));
        }
        result.add(neg);
        return result;
    }
}
