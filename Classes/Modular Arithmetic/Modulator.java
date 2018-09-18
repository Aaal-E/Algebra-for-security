import java.util.*;
class Modulator {
     
    Modulator(){
        Divider div = new Divider();
    }
    ArrayList<Integer> mod(ArrayList<Integer> a, int base){
        div.div(a, base);
        return div.getRemainder();
    }
}