import java.util.*;

class Adder { //chooses whether to use addition or subtraction for addition
    Modulator mod = new Modulator();

    List<Integer> add(List<Integer> x, List<Integer> y, int b) {
        // Copy original inputs since we are going to change it
        x = new ArrayList<>(x);
        y = new ArrayList<>(y);
        // Reverse the lists since they were originally assumed to be the other way around, with sign element at the back
        Collections.reverse(x);
        Collections.reverse(y);
        x.add(x.remove(0));
        y.add(y.remove(0));


        int x_end = x.size() - 1; //last index of x
        int y_end = y.size() - 1; //last index of y

        boolean x_pos = x.get(x_end) == 0; //check whether x is negative
        x.remove(x_end);
        boolean y_pos = y.get(y_end) == 0; //check whether y is negative
        y.remove(y_end);

        while (x.size() < y.size()) { //makes x as large as y if x is smaller
            x.add(0, 0);
        }
        while (y.size() < x.size()) { // makes y as large as x if y is smaller
            y.add(0, 0);
        }

        List<Integer> result;
        if (x_pos && y_pos) { // case 1: both x and y are positive
            result = do_add(x, y, b);
            result.add(0);
        } else if (!x_pos && !y_pos) { //case 2: both x and y are negative
            result = do_add(x, y, b);
            result.add(1);
        } else if (x_pos && !y_pos) { // case 3: x is positive and y is negative
            result = do_sub(x, y, b);
        } else {
            result = do_sub(y, x, b);    //case 4: x is negative and y is positive
        }
        while (result.size() > 2 && result.get(0) == 0) { //remove the insignificant zeroes from the result
            result.remove(0);
        }

        // Reverse back to new representation
        Collections.reverse(result);
        result.add(result.remove(0));

        return result;
    }


    List<Integer> sub(List<Integer> x, List<Integer> y, int radix) {//chooses whether to use addition or subtraction for subtraction
        // Reverse the sign of y
        int ySign = y.get(y.size() - 1);
        List<Integer> yInversed = new ArrayList<>(y);
        yInversed.set(y.size() - 1, 1 - ySign);

        // Do addition with x + (-y)
        return add(x, yInversed, radix);
    }

    List<Integer> do_add(List<Integer> x, List<Integer> y, int b) { // does addition
        int length = x.size() - 1;
        int carry = 0;
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = length; i >= 0; i--) { // adds the digits in x and y to each other, with a carry if necessary
            int digit = x.get(i) + y.get(i) + carry;
            if (digit >= b) {
                carry = 1;
                digit -= b;
            } else {
                carry = 0;
            }
            result.add(0, digit);

        }

        if (carry == 1) {
            result.add(0, 1);
        }
        return result;
    }

    List<Integer> do_sub(List<Integer> x, List<Integer> y, int b) { //does subtraction
        int length = x.size() - 1;
        int carry = 0;
        List<Integer> result = new ArrayList<>();
        boolean swap = false;

        for (int i = 0; i <= length; i++) { // checks whether swapping is necessary, depending on which number is the biggest
            if (x.get(i) > y.get(i)) {
                break;
            } else if (y.get(i) > x.get(i)) {
                swap = true;
                break;
            }
        }
        if (swap) {
            List<Integer> temp = y;
            y = x;
            x = temp;
        }
        for (int i = length; i >= 0; i--) { // subtracts the digits in x and y from each other, with a carry if necessary
            int digit = x.get(i) - y.get(i) - carry;
            if (digit < 0) {
                carry = 1;
                digit += b;
            } else {
                carry = 0;
            }
            result.add(0, digit);

        }

        if (swap) {
            result.add(1); // if you swapped, the result is negative
        } else {
            result.add(0); // no swap means that you get a positive number
        }

        return result;
    }

    /**
     * For modular addition and subtraction we assume that the input is in reduced form!
     * I.e. for input n and modulo m we assume 0 <= n < m.
     */
    List<Integer> add(List<Integer> x, List<Integer> y, List<Integer> m, int radix) {
        List<Integer> z = add(x, y, radix);

        if (BigInt.greaterOrEqual(z, m)) {
            // z >= m, do z = z - m
            z = sub(z, m, radix);
        }

        return z;
    }

    /**
     * See add(x,y,m,radix).
     */
    List<Integer> sub(List<Integer> x, List<Integer> y, List<Integer> m, int radix) {
        List<Integer> z = sub(x, y, radix);

        if (BigInt.isNegative(z)) {
            // z < 0, add m
            z = add(z, m, radix);
        }

        return z;
    }
}