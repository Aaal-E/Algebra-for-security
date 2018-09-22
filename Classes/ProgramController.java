import java.io.*;
import java.util.*;

public class ProgramController {

    // Variables used in computations
    int radix;
    String commandtype;
    List<Integer> x;
    List<Integer> y;
    List<Integer> m;
    List<Integer> result;
    List<Integer> a;
    List<Integer> b;
    long countadd;
    long countmul;

    // Calculators
    Adder adder = new Adder();
    Multiplier multiplier = new Multiplier();
    Modulator modulator = new Modulator();
    Inverser inverser = new Inverser();
    Euclid euclid = new Euclid();
    Karatsuba karatsuba = new Karatsuba();

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

                // Assume the start of a new computation, clear variables
                clearFields();
                break;

            case "add":
            case "subtract":
            case "multiply":
            case "karatsuba":
            case "reduce":
            case "inverse":
            case "euclid":
                // Assume the start of a new computation, clear variables (excludes radix)
                clearFields();

                commandtype = cmd.command;
                break;

            case "x":
                x = Formatter.toBigInt(cmd.argument, radix);
                break;

            case "y":
                y = Formatter.toBigInt(cmd.argument, radix);
                break;

            case "m":
                m = Formatter.toBigInt(cmd.argument, radix);
                break;


            case "answer":  //process the commands previously read, and output the output
                switch (commandtype) {
                    case "add":
                        if (m != null) {
                            io.print(adder.add(x, y, m, radix), radix);
                        } else {
                            io.print(adder.add(x, y, radix), radix);
                        }
                        break;

                    case "subtract":
                        if (m != null) {
                            io.print(adder.sub(x, y, m, radix), radix);
                        } else {
                            io.print(adder.sub(x, y, radix), radix);
                        }
                        break;

                    case "multiply":
                        if (m != null) {
                            io.print(multiplier.mul(x, y, m, radix), radix);
                            countadd = multiplier.getCountAdd();
                            countmul = multiplier.getCountMul();
                        } else {
                            io.print(multiplier.mul(x, y, radix), radix);
                            countadd = multiplier.getCountAdd();
                            countmul = multiplier.getCountMul();
                        }
                        break;

                    case "karatsuba":
                        io.print(karatsuba.karatsuba(x, y, radix), radix);
                        countadd = karatsuba.getCountAdd();
                        countmul = karatsuba.getCountMul();
                        break;

                    case "reduce":
                        io.print(modulator.mod(x, m, radix), radix);
                        break;

                    case "inverse":
                        result = inverser.invert(x, m, radix);
                        if (result != null) {
                            io.print(result, radix);
                        } else {
                            io.print("ERROR");
                        }
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
                // It assumes that the command answ-d has been called before
                // (i.e. [answ-d] should be above [answ-b]/[answ-a] in input.txt
                io.print(a, radix);
                break;

            case "answ-b":
                // It assumes that the command answ-d has been called before
                // (i.e. [answ-d] should be above [answ-b]/[answ-a] in input.txt
                io.print(b, radix);
                break;

            case "answ-d":
                if (commandtype.equals("euclid")) {
                    io.print(euclid.euclid(x, y, radix), radix);
                    a = euclid.getX();
                    b = euclid.getY();
                }
                break;
        }
    }

    /**
     * Clears variables used in computations. Excludes radix.
     */
    private void clearFields() {
        commandtype = null;
        x = null;
        y = null;
        m = null;
        a = null;
        b = null;
        result = null;
        countadd = 0;
        countmul = 0;
    }

    public static void main(String[] args) throws IOException {
        if (args.length >= 2) {
            Logger.setLevel(9);
        }
        new ProgramController().run();
    }
}

