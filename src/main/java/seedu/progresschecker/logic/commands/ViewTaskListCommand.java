package seedu.progresschecker.logic.commands;

import seedu.progresschecker.commons.core.EventsCenter;
import seedu.progresschecker.commons.events.ui.LoadTaskEvent;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.task.MyTaskList;

/**
 * View the web view of a particular TaskList (with the name provided).
 */
public class ViewTaskListCommand extends Command {

    public static final String COMMAND_WORD = "viewtask";
    public static final String COMMAND_ALIAS = "vt";
    public static final String COMMAND_FORMAT = COMMAND_WORD + "TASKLIST-TITLE";
    public static final String MESSAGE_TITLE_CONSTRAINTS = "The title of a task list should not exceed "
            + "49 characters (as specified by Google Task.";
    public static final int MAX_TITLE_LENGTH = 49;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            // TODO: change description and parameter range when appropriate
            + ": Toggle view to display the task list with the given name.\n"
            + "Parameters: TASKLIST-TITLE (max "
            + MAX_TITLE_LENGTH
            + " characters)\n"
            + "Example: " + COMMAND_WORD + " CS2103 LOs";

    public static final String MESSAGE_SUCCESS = "Viewing task list: %1$s";

    private final String listName;

    public ViewTaskListCommand(String name) {
        this.listName = name;
    }

    @Override
    public CommandResult execute() throws CommandException {
        String url = MyTaskList.searchTaskList(listName);
        EventsCenter.getInstance().post(new LoadTaskEvent(url));
        return new CommandResult(String.format(MESSAGE_SUCCESS, listName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewTaskListCommand // instanceof handles nulls
                && this.listName.equals(((ViewTaskListCommand) other).listName)); // state check
    }
}
