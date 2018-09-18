import java.util.*;
class Inverser {
    
    Inverser(){
        Euclid euclid = new Euclid();
    }
    
    ArrayList<Integer> invert (ArrayList<Integer> a, ArrayList<Integer> m, int base){
        if (euclid.euclid(a, m, base) == 1)
        return euclid.getA();
        else return null;
    }
}