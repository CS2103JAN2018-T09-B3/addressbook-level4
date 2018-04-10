package seedu.progresschecker.logic.commands;

import seedu.progresschecker.commons.core.EventsCenter;
import seedu.progresschecker.commons.events.ui.TabLoadChangedEvent;

//@@author iNekox3
/**
 * Change view of the tab pane in main window based on categories.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String COMMAND_ALIAS = "v";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " TYPE";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Change the tab view to profiles, tasks or exercises.\n"
            + "Parameters: TYPE (must be either 'profile', 'task', or 'exercise')\n"
            + "INDEX (if TYPE is exercise and must be within 2 and 12 (boundary numbers inclusive)\n"
            + "Example: " + COMMAND_WORD + " exercise\n"
            + COMMAND_WORD + "exercise 5";

    public static final String MESSAGE_SUCCESS_TAB = "Viewing tab %1$s";
    public static final String MESSAGE_SUCCESS_WEEK = "Viewing week %1$s's exercises";

    public static final int MIN_WEEK_NUMBER = 2;
    public static final int MAX_WEEK_NUMBER = 11;

    private final String type;
    private final int weekNumber;
    private final boolean isWeekToggle;

    public ViewCommand(String type, int weekNumber, boolean isWeekToggle) {
        this.type = type;
        this.weekNumber = weekNumber;
        this.isWeekToggle = isWeekToggle;
    }

    @Override
    public CommandResult execute() {
        if (!isWeekToggle) {
            EventsCenter.getInstance().post(new TabLoadChangedEvent(type));
            return new CommandResult(String.format(MESSAGE_SUCCESS_TAB, type));
        }
        else {
            model.updateFilteredExerciseList(exercise -> exercise.getQuestionIndex().getWeekNumber() == weekNumber);
            EventsCenter.getInstance().post(new TabLoadChangedEvent(type));
            return new CommandResult(String.format(MESSAGE_SUCCESS_WEEK, weekNumber));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && this.type.equals(((ViewCommand) other).type)) // state check
                && this.weekNumber == ((ViewCommand) other).weekNumber
                && this.isWeekToggle == ((ViewCommand) other).isWeekToggle;
    }
}
