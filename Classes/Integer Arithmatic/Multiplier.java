import java.util.*;

class Multiplier {

    private long countadd = 0;
    private long countmul = 0;
    Modulator mod = new Modulator();

    List<Integer> mul(List<Integer> x, List<Integer> y, int b) {
        // Copy x and y since we'll modify them
        x = new ArrayList<>(x);
        y = new ArrayList<>(y);


        int m = x.size();
        int n = y.size();
        int k; //size of the result
        int carry, t; //carry and dummy variable t
        boolean negative = false;

        List<Integer> z = new ArrayList<>();

        countadd = 0;
        countmul = 0;

        if (x.get(m - 1) == 1 || y.get(n - 1) == 1)
            negative = true;
        if (x.get(m - 1) == 1 && y.get(n - 1) == 1)
            negative = false;
        x.remove(m - 1);
        y.remove(n - 1);
        x.add(0);
        y.add(0);

        z.clear();
//        for (int i = n; i < m + n - 1; i++)
        for (int i = 0; i < m + n; i++)
            z.add(0); //reset the resulting array z = xy.

        for (int i = 0; i < m; i++) {
            carry = 0; //reset the carry
            for (int j = 0; j < n; j++) {

                t = z.get(i + j) + x.get(i) * y.get(j) + carry; //do the multiplication with carry

                carry = Math.floorDiv(t, b); //update the carry

                z.set(i + j, t - carry * b);    //t - carry * b = t mod b = t % b.
            }
            z.set(i + n, carry); //store the new carry
        }

//        if (z.get(m + n - 1) == 0)
//            k = m + n - 2;           //update the size of the result of the multiplication
//        else
//            k = m + n - 1;

        if (negative)
            z.add(1);   //if one of the nrs is negative, the result is negative.
        else
            z.add(0);    //if both pos or negative, the result is positive.

        countadd = m + m * (n * 7) + 6;  //first for + second for (==) + if -Is this correct?
        countmul = m * n * 3;        // is this correct?

        // Remove leading zeros
        BigInt.removeLeadingZeros(z);

        return z; // [z]_b in the usual form sum_{0}^{k} ( z_i* b^{i)

    }

    long getCountAdd() {
        return countadd;
    }

    long getCountMul() {
        return countmul;
    }


    List<Integer> mul(List<Integer> x, List<Integer> y, List<Integer> m, int b) {
        x = mod.mod(x, m, b);
        y = mod.mod(y, m, b);
        List<Integer> ans = mod.mod(mul(x, y, b), m, b);

        BigInt.removeLeadingZeros(ans);
        return ans;
    }
}

		
