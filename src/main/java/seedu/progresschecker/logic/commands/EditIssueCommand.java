package seedu.progresschecker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_BODY;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_LABEL;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_MILESTONE;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.progresschecker.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.commons.util.CollectionUtil;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.issues.Assignees;
import seedu.progresschecker.model.issues.Body;
import seedu.progresschecker.model.issues.Issue;
import seedu.progresschecker.model.issues.Labels;
import seedu.progresschecker.model.issues.Milestone;
import seedu.progresschecker.model.issues.Title;

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

    /**
     * Creates and returns a {@code Issue} with the details of {@code issueToEdit}
     * edited with {@code editIssueDescriptor}.
     */
    private static Issue createEditedIssue(Issue issueToEdit,
                                           EditIssueCommand.EditIssueDescriptor editIssueDescriptor) {
        assert issueToEdit != null;

        Title updatedTitle = editIssueDescriptor.getTitle().orElse(issueToEdit.getTitle());
        List<Assignees> updatedAssignees = editIssueDescriptor.getAssignees().orElse(issueToEdit.getAssignees());
        Milestone updatedMilestone = editIssueDescriptor.getMilestone().orElse(issueToEdit.getMilestone());
        Body updatedBody = editIssueDescriptor.getBody().orElse(issueToEdit.getBody());
        List<Labels> updatedLabels = editIssueDescriptor.getLabels().orElse(issueToEdit.getLabelsList());

        List<Assignees> updatedAssigneesList = new ArrayList<>(updatedAssignees);
        List<Labels> updatedLabelsList = new ArrayList<>(updatedLabels);

        return new Issue(updatedTitle, updatedAssigneesList, updatedMilestone, updatedBody, updatedLabelsList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditIssueCommand)) {
            return false;
        }

        // state check
        EditIssueCommand e = (EditIssueCommand) other;
        return index.equals(e.index)
                && editIssueDescriptor.equals(e.editIssueDescriptor)
                && Objects.equals(issueToEdit, e.issueToEdit);
    }

    /**
     * Stores the details to edit the issue with. Each non-empty field value will replace the
     * corresponding field value of the Issue.
     */
    public static class EditIssueDescriptor {
        private Title title;
        private List<Assignees> assignees;
        private Milestone milestone;
        private Body body;
        private List<Labels> labels;

        public EditIssueDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code labels} is used internally.
         */
        public EditIssueDescriptor(EditIssueCommand.EditIssueDescriptor toCopy) {
            setTitle(toCopy.title);
            setAssignees(toCopy.assignees);
            setMilestone(toCopy.milestone);
            setBody(toCopy.body);
            setLabels(toCopy.labels);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.title, this.assignees, this.milestone, this.body,
                    this.labels);
        }

        public void setTitle(Title title) {
            this.title = title;
        }

        public Optional<Title> getTitle() {
            return Optional.ofNullable(title);
        }

        /**
         * Sets {@code assignees} to this object's {@code assignees}.
         * A defensive copy of {@code assignees} is used internally.
         */
        public void setAssignees(List<Assignees> assignees) {
            this.assignees = (assignees != null) ? new ArrayList<>(assignees) : null;
        }

        /**
         * Returns an unmodifiable assignees set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code labels} is null.
         */
        public Optional<List<Assignees>> getAssignees() {
            return (assignees != null) ? Optional.of(Collections.unmodifiableList(assignees)) : Optional.empty();
        }

        public void setMilestone(Milestone milestone) {
            this.milestone = milestone;
        }

        public Optional<Milestone> getMilestone() {
            return Optional.ofNullable(milestone);
        }

        public void setBody(Body body) {
            this.body = body;
        }

        public Optional<Body> getBody() {
            return Optional.ofNullable(body);
        }

        /**
         * Sets {@code labels} to this object's {@code labels}.
         * A defensive copy of {@code labels} is used internally.
         */
        public void setLabels(List<Labels> labels) {
            this.labels = (labels != null) ? new ArrayList<>(labels) : null;
        }

        /**
         * Returns an unmodifiable labels set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code labels} is null.
         */
        public Optional<List<Labels>> getLabels() {
            return (labels != null) ? Optional.of(Collections.unmodifiableList(labels)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditIssueCommand.EditIssueDescriptor e = (EditIssueCommand.EditIssueDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getAssignees().equals(e.getAssignees())
                    && getMilestone().equals(e.getMilestone())
                    && getBody().equals(e.getBody())
                    && getLabels().equals(e.getLabels());
        }
    }
}
