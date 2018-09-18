import java.util.*;

public class TestRunner {

    public static void main(String[] args) {
        ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0));
        ArrayList<Integer> two = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0));
        Adder adder = new Adder();
        ArrayList<Integer> result = adder.add(one, two, 10);
        
        System.out.println(result);
    }
}
