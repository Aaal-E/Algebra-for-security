import java.util.*;
class Modulator {
     
    Divider div = new Divider();
    
    List<Integer> mod(List<Integer> a, List<Integer> mod, int base){
        div.divide(a, mod, base);
        return div.getRem();
    }
}