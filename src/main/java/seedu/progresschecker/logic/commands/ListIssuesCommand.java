package seedu.progresschecker.logic.commands;

import seedu.progresschecker.commons.exceptions.IllegalValueException;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in ProgressChecker whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class ListIssuesCommand extends Command {

    public static final String COMMAND_WORD = "listissue";
    public static final String COMMAND_ALIAS = "lis";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " STATE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all the issues "
            + "of the specified state with the respective github issue index.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " CLOSE";
    private static final String MESSAGE_INVALID_STATE = "Please enter correct issue state";
    
    private static String state;

    public ListIssuesCommand(String state) {
        this.state = state;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            model.listIssues(state);
            return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
        } catch (IllegalValueException ie) {
            throw new CommandException(MESSAGE_INVALID_STATE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListIssuesCommand // instanceof handles nulls
                && this.state.equals(((ListIssuesCommand) other).state)); // state check
    }
}