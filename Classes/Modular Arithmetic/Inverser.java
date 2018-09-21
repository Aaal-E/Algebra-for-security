import java.util.List;

class Inverser {

    List<Integer> invert(List<Integer> a, List<Integer> m, int base) {
        Euclid euclid = new Euclid();
        List<Integer> temp;

        temp = euclid.euclid(a, m, base);
        if (temp.size() == 2 && temp.get(0) == 1) {
            return euclid.getX();
        }
        return null;
    }
}