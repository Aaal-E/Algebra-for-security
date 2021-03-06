import java.util.Arrays;
import java.util.List;

class Logger {

    /**
     * Set log level here.
     */
    private static int level = 0; // 0 (nothing) <= LEVEL <= 9 (everything)

    /**
     * Ignore certain loggers.
     */
    private final static String[] TO_IGNORE = {};

    private final String name;

    public Logger(String name) {
        this.name = name;
    }

    void infof(String msg, Object... args) {
        if (level < 5) return;
        writef(msg, args);
    }

    void finef(String msg, Object... args) {
        if (level < 6) return;
        writef(msg, args);
    }

    void finerf(String msg, Object... args) {
        if (level < 7) return;
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
        if (Arrays.asList(TO_IGNORE).contains(name)) {
            return;
        }
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

    static void setLevel(int level) {
        Logger.level = level;
    }
}
