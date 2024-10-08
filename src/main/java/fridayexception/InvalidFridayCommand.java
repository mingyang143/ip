package fridayexception;

/**
 * Represents an exception where the command is invalid.
 */
public class InvalidFridayCommand extends FridayException {

    /**
     * Constructs an InvalidFridayCommand exception.
     *
     * @param command The invalid command.
     */
    public InvalidFridayCommand(String command) {
        super("I'm sorry, but this command is invalid: " + command + "\n"
                + "     Please enter a valid command: list, todo, deadline, "
                + "event, mark, unmark, delete, search, find, sort or bye.");
    }
}
