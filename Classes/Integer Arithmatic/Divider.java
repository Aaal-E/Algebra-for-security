import java.util.*;

class Divider {

    ArrayList<Integer> r = new ArrayList<Integer>(); //initialize the remainder

    ArrayList<Integer> divide(ArrayList<Integer> x, ArrayList<Integer> y, int b) {
        int m = x.size(); //k
        int n = y.size(); //l

        ArrayList<Integer> q = new ArrayList<Integer>();
        int k = r.size();  //q has m digits, here = k
        int carry, t;

        for (int i = 0; i < m; i++)
            r.set(i, x.get(i));

        r.set(m, 0);

        for (int i = m - n; i >= 0; i--) {
            q.set(i, Math.floorDiv(r.get(i + n) * b + r.get(i + n - 1), y.get(n - 1)));

            if (q.get(i) >= b)
                q.set(i, b - 1); //if it overflows

            carry = 0;

            for (int j = 0; j < n; j++) {
                t = r.get(i + j) - q.get(i) * y.get(j) + carry;

                carry = Math.floorDiv(t, b);

                r.set(i + j, b);
            }
            r.set(i + n, r.get(i + n) + carry);

            while (r.get(i + n) < 0) {
                carry = 0;
                for (int j = 0; j < n; j++) {
                    t = r.get(i + j) + y.get(j) + carry;

                    carry = Math.floorDiv(t, b);

                    r.set(i + j, t - carry * b); // second argument = t % b.
                }
                r.set(i + n, r.get(i + n) + carry);

                q.set(i, q.get(i) - 1);
            }
        }

        if (x.get(m) == 1 || y.get(n) == 1)
            q.set(m - n + 1, 1);
        else
            q.set(m - n + 1, 0);

        r.set(n, 0); //r is always positive, right?

        return q;
    }

    ArrayList<Integer> getRem() {

        return r;
    }
}