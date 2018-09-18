import java.util.*;
class Modulator {
     
    Modulator(){
        Divider div = new Divider();
    }
    ArrayList<Integer> mod(ArrayList<Integer> a, ArrayList<Integer> mod, int base){
        div.div(a, mod, base);
        return div.getRemainder();
    }
}