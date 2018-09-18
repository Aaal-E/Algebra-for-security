import java.util.*;
class Inverser {
    
    Inverser(){
        Euclid euclid = new Euclid();
    }
    
    ArrayList<Integer> invert (ArrayList<Integer> a, ArrayList<Integer> m, int base){
        euclid.euclid(a, m, base);
        return euclid.getA();
    }
}