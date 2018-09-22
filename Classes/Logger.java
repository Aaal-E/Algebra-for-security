import java.util.List;

class Logger {

    /**
     * Set log level here.
     */
    private final static int LEVEL = 9; // 0 (nothing) <= LEVEL <= 9 (everything)

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    void infof(String msg, Object... args) {
        if (LEVEL < 5) return;
        writef(msg, args);
    }

    void finef(String msg, Object... args) {
        if (LEVEL < 6) return;
        writef(msg, args);
    }

    void finerf(String msg, Object... args) {
        if (LEVEL < 7) return;
        writef(msg, args);
    }

    private void writef(String msg, Object... args) {
        // Convert List<Integer> to string form
        for (int i = 0; i < args.length; i++) {
            if (isBigInt(args[i])) {
                args[i] = Formatter.toString((List<Integer>) args[i], Character.MAX_RADIX);
            }
        }
        write(String.format(msg, args));
    }

    private void write(String msg) {
        System.out.printf("[%s] %s\n", name, msg);
    }

    /**
     * Checks if given object is of type List<Integer>
     */
    private boolean isBigInt(Object o) {
        if (!(o instanceof List)) {
            return false;
        }
        for (Object ob : (List) o) {
            if (!(ob instanceof Integer)) {
                return false;
            }
        }
        return true;
    }
}
