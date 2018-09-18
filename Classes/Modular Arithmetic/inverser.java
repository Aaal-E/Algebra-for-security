import java.util.*;
class Inverser {
    
    Euclid euclid = new Euclid();
    ArrayList<Integer> temp;
    ArrayList<Integer> invert (ArrayList<Integer> a, ArrayList<Integer> m, int base){
        if ((temp = euclid.euclid(a, m, base)).size() == 2 && temp.get(0) == 1)
        return euclid.getA();
        else return null;
    }
}