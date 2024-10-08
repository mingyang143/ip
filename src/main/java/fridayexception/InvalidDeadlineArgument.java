package fridayexception;

/**
 * Represents an exception where the deadline argument is invalid.
 */
public class InvalidDeadlineArgument extends FridayException {

    /**
     * Constructs an InvalidDeadlineArgument exception.
     */
    public InvalidDeadlineArgument() {
        super("The description of a deadline and date to be done by cannot be empty. " + "\n"
                + "     Please enter a valid deadline command: deadline <description> /by <date>.");
    }
}
