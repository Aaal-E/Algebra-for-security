import java.util.*;

class Modulator {

    List<Integer> mod(List<Integer> a, List<Integer> mod, int base) {
        Divider divider = new Divider();
        divider.divide(a, mod, base);
        return divider.getRem();
    }
}