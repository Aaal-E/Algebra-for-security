import java.util.List;

class Inverser {

    Euclid euclid = new Euclid();
    List<Integer> temp;

    List<Integer> invert(List<Integer> a, List<Integer> m, int base) {
        if ((temp = euclid.euclid(a, m, base)).size() == 2 && temp.get(0) == 1)
            return euclid.getA();
        else return null;
    }
}