import java.util.*;

public class TestRunner {

    public static void main(String[] args) {
        TestRunner r = new TestRunner();
        r.testOne();
        r.testTwo();
    }

    public void testOne() {
        ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0));
        ArrayList<Integer> two = new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0));
        Adder adder = new Adder();
        ArrayList<Integer> result = adder.add(one, two, 10);

        System.out.println(result);
        String res = IO.toString(result, 10);
        if (!res.equals("1111222222222222222222")) {
            throw new RuntimeException("WRONG TESTCASE");
        }

    }

    public void testTwo() {
        ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(1, 0));
        ArrayList<Integer> two = new ArrayList<Integer>(Arrays.asList(1, 0));
        Adder adder = new Adder();
        ArrayList<Integer> result = adder.add(one, two, 2);

        System.out.println(result);
        String res = IO.toString(result, 2);
        System.out.println(res);
//        if (!res.equals("2222222222222222221111")) {
//            throw new RuntimeException("WRONG TESTCASE");
//        }

    }

    public void testThree() {
        ArrayList<Integer> one = new ArrayList<Integer>(Arrays.asList(1, 0));
        ArrayList<Integer> two = new ArrayList<Integer>(Arrays.asList(1, 0));
        Adder adder = new Adder();
        ArrayList<Integer> result = adder.add(one, two, 2);

        System.out.println(result);
        String res = IO.toString(result, 2);
        System.out.println(res);
//        if (!res.equals("2222222222222222221111")) {
//            throw new RuntimeException("WRONG TESTCASE");
//        }

    }

}
