package seedu.progresschecker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_MILESTONE;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.issues.Issue;

/**
 * Edits the details of an existing issue on Github.
 */
public class EditIssueCommand extends Command {
    public static final String COMMAND_WORD = "editIssue";
    public static final String COMMAND_ALIAS = "edI";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " " + "INDEX "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_ASSIGNEES + "ASSIGNEES] "
            + "[" + PREFIX_MILESTONE + "MILESTONE] "
            + "[" + PREFIX_BODY + "BODY] "
            + "[" + PREFIX_LABEL + "LABEL]...";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of an existing issue "
            + "by the index number used in the issue listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_ASSIGNEES + "ASSIGNEES] "
            + "[" + PREFIX_MILESTONE + "MILESTONE] "
            + "[" + PREFIX_BODY + "BODY] "
            + "[" + PREFIX_LABEL + "LABEL]..."
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 5 "
            + PREFIX_TITLE + "Make a new attribute "
            + PREFIX_MILESTONE + "v1.3";

    public static final String MESSAGE_EDIT_ISSUE_SUCCESS = "Issue #1$s was successfully edited.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditIssueCommand.EditIssueDescriptor editIssueDescriptor;

    private Issue issueToEdit;
    private Issue editedIssue;

    /**
     * @param index of the issue on github that is to be edited
     * @param editIssueDescriptor details to edit the issue with
     */
    public EditIssueCommand(Index index, EditIssueCommand.EditIssueDescriptor editIssueDescriptor) {
        requireNonNull(index);
        requireNonNull(editIssueDescriptor);

        this.index = index;
        this.editIssueDescriptor = new EditIssueCommand.EditIssueDescriptor(editIssueDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        return new CommandResult("CHECK");
    }
}
