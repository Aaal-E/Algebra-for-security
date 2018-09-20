import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IO {

    private final BufferedReader reader;
    private final PrintWriter writer;

    public IO(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader);
        this.writer = new PrintWriter(writer);
    }

    public String nextInput() {
        try {
            String result = "";
            int intValueOfChar;
            while ((intValueOfChar = reader.read()) != -1) { //stop at end of file
                writer.print((char) intValueOfChar);
                String temp;
                if (intValueOfChar == '#') {
                    temp = reader.readLine(); //ignore lines after #
                    writer.println(temp);
                } else if (intValueOfChar == ' ') {
                    if (result != "")
                        return result; //return the end of a word when encountering a space, but only if there is a word read
                } else {
                    result += (char) intValueOfChar; //add the read character to the resulting word
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Returns the next command or null if end of file is reached.
     */
    public Command next() throws IOException {
        String line;

        // Advance until next command line
        while ((line = reader.readLine()) != null && (line.startsWith("#") || line.isEmpty())) {
            // Copy all non-command lines
            writer.println(line);
        }

        if (line == null) {
            return null;
        }

        // Extract command + argument
        String command = line.substring(1, line.indexOf(']'));
        String argument = line.substring(line.indexOf(']') + 1).trim();

        // Copy command line if command is not one of the answer commands
        List<String> noCopy = Arrays.asList("answer", "count-add", "count-mul", "answ-a", "answ-b", "answ-d");
        if (!noCopy.contains(command)) {
            writer.println(line);
        } else {
            // Make space for the answer
            writer.printf("[%s] ", command);
        }

        return new Command(command, argument);
    }

    //print a line using the printer
    public void print(String str) {
        writer.println(str);
    }

    //print a number in a given base
    void print(ArrayList<Integer> number, int base) {
        print(toString(number, base));
    }


    //print a number in a given base with added prefix
    void print(String str, ArrayList<Integer> number, int base) {
        str = str + toString(number, base);
        print(str);
    }

    void print(long l) {
        print(Long.toString(l));
    }

    /**
     * Returns a string representation using given radix.
     */
    public static String toString(ArrayList<Integer> n, int radix) {
        BigInteger bigint = new BigInteger(n, radix);
        StringBuilder result = new StringBuilder();
        if (bigint.isNegative()) {
            result.append('-');
        }
        for (int i = bigint.getIntegerLegacy().size() - 2; i >= 0; i--) {
            result.append(Integer.toString(bigint.getIntegerLegacy().get(i), radix));
        }
        return result.toString();
    }

}
