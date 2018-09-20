import java.io.*;
import java.util.*;

public class ProgramController {

    String command;
    int radix = 2;
    String commandtype = "";
    ArrayList<Integer> x = new ArrayList<>();
    ArrayList<Integer> y = new ArrayList<>();
    ArrayList<Integer> m = new ArrayList<>();
    long countadd;
    long countmul;
    Adder adder = new Adder();
    ArrayList<Integer> a = new ArrayList<>();
    ArrayList<Integer> b = new ArrayList<>();
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
        try {
            Command cmd;
            while ((cmd = io.next()) != null) {
                process(io, cmd);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        reader.close();
        writer.close();
    }

    private void process(IO io, Command cmd) {
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
                x = new BigInteger(cmd.argument, radix).getIntegerLegacy();
                System.out.printf("x: %s\n", x);
                break;

            case "y":
                y = new BigInteger(cmd.argument, radix).getIntegerLegacy();
                System.out.printf("y: %s\n", y);
                break;

            case "m":
                m = new BigInteger(cmd.argument, radix).getIntegerLegacy();
                System.out.printf("m: %s\n", m);
                break;


            case "answer":  //process the commands previously read, and output the output
                switch (commandtype) {
                    case "add":
                        if (m.size() > 0) io.print(adder.add(x, y, m, radix), radix);
                        else io.print(adder.add(x, y, radix), radix);
                        m.clear();
                        break;

                    case "subtract":
                        if (m.size() > 0) {
                            io.print(adder.sub(x, y, m, radix), radix);
                        } else {
                            io.print(adder.sub(x, y, radix), radix);
                        }
                        m.clear();
                        break;

                    case "multiply":
                        if (m.size() > 0) {
                            io.print(multiplier.mul(x, y, radix), radix);
                            countadd = multiplier.getCountAdd();
                            countmul = multiplier.getCountMul();
                        } else {
                            io.print(multiplier.mul(x, y, m, radix), radix);
                            countadd = multiplier.getCountAdd();
                            countmul = multiplier.getCountMul();
                        }
                        m.clear();
                        break;

                    case "reduce":
                        io.print(modulator.mod(x, m, radix), radix);
                        m.clear();
                        break;

                    case "inverse":
                        result = inverser.invert(x, m, radix);
                        if (result != null) io.print(result, radix);
                        else io.print("ERROR");
                        break;
                }
                break;

            case "count-add":
                io.print(countadd);
                break;

            case "count-mul":
                io.print(countmul);
                break;

            case "answ-a":
                io.print(a, radix);
                break;

            case "answ-b":
                io.print(b, radix);
                break;

            case "answ-d":
                if (commandtype.equals("euclid")) {
                    io.print(euclid.euclid(x, y, radix), radix);
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

