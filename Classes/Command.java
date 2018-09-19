/**
 * Represents one line from input.
 */
public class Command {
    public final String command;
    public final String argument;

    public Command(String command, String argument) {
        this.command = command;
        this.argument = argument;
    }
}