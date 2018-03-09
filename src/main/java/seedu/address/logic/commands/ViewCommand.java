package seedu.address.logic.commands;

/**
 * View the web view of outcomes of a particular week.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            // TODO: change description and parameter range when appropriate
            + ": Toggle view to display outcomes in the specified week.\n"
            + "Parameters: INDEX (must be a positive integer ranging from 1 to 13)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Viewing week %1$s";
}
