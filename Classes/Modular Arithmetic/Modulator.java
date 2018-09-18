import java.util.*;
class Modulator {
     
    Divider div = new Divider();
    
    ArrayList<Integer> mod(ArrayList<Integer> a, ArrayList<Integer> mod, int base){
        div.divide(a, mod, base);
        return div.getRem();
    }
}