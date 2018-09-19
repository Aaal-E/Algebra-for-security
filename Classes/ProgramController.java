import java.io.*;
import java.util.*;

public class ProgramController {

    String command;
    int radix = 2;
    String commandtype = "";
    BigInteger x;
    BigInteger y;
    BigInteger m;
    int countadd;
    int countmul;
    Adder adder = new Adder();
    ArrayList<Integer> a;
    ArrayList<Integer> b;
    Multiplier multiplier = new Multiplier();
    Modulator modulator = new Modulator();
    ArrayList<Integer> result;
    Inverser inverser = new Inverser();
    Euclid euclid = new Euclid();

    private void run() throws IOException {
        Reader reader = new FileReader("input.txt");
        Writer writer = new FileWriter("output.txt");
        IO io = new IO(reader, writer);

        // Keep reading inputs until the file ends
        Command cmd;
        while ((cmd = io.next()) != null) {
            process(cmd);
        }

        reader.close();
        writer.close();
    }

    private void process(Command cmd) {
        switch (cmd.command) {
            case "radix":
                radix = Integer.parseInt(cmd.argument);
                System.out.printf("radix: %s\n", radix);
                break;

            case "add":
            case "subtract":
            case "multiply":
            case "karatsuba":
            case "reduce":
            case "inverse":
            case "euclid":
                commandtype = cmd.command;
                System.out.printf("commandtype: %s\n", commandtype);
                break;

            case "x":
                x = new BigInteger(cmd.argument, radix);
                System.out.printf("x: %s\n", x);
                break;

            case "y":
                y = new BigInteger(cmd.argument, radix);
                System.out.printf("y: %s\n", y);
                break;

            case "m":
                m = new BigInteger(cmd.argument, radix);
                System.out.printf("m: %s\n", m);
                break;


            case "answer":  //process the commands previously read, and output the output
                switch (commandtype) {
                    case "add":
//                        if (m.size() > 0) formatter.print(adder.add(x, y, radix), radix);
//                        else formatter.print(adder.add(x, y, m, radix), radix);
//                        m.clear();

                    case "subtract":
//                        if (m.size() > 0) formatter.print(adder.sub(x, y, radix), radix);
//                        else ;//formatter.print(adder.sub(x,y,m,base), base);
//                        m.clear();
                        break;

                    case "multiply":
//                        if (m.size() > 0) {
//                            formatter.print(multiplier.mul(x, y, radix), radix);
//                            countadd = multiplier.getCountAdd();
//                            countmul = multiplier.getCountMul();
//                        } else {
//                            formatter.print(multiplier.mul(x, y, m, radix), radix);
//                            countadd = multiplier.getCountAdd();
//                            countmul = multiplier.getCountMul();
//                        }
//                        m.clear();
                        break;

                    case "reduce":
//                        formatter.print(modulator.mod(x, m, radix));
//                        m.clear();
                        break;

                    case "inverse":
//                        result = inverser.invert(x, m, radix);
//                        if (result != null) formatter.print(result);
//                        else formatter.print("ERROR");
                        break;
                }
                break;

            case "count-add":
                //formatter.print(countadd);
                break;

            case "count-mul":
                //formatter.print(countmul);
                break;

            case "answ-a":
                //formatter.print(a);
                break;

            case "answ-b":
                //formatter.print(b);
                break;

            case "answ-d":
                if (commandtype.equals("euclid")) {
                    //formatter.print(euclid.euclid(x, y, radix), radix);
                    a = euclid.getA();
                    b = euclid.getB();
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        new ProgramController().run();
    }
}

