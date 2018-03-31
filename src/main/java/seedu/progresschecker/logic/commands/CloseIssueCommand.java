package seedu.progresschecker.logic.commands;

import java.io.IOException;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.logic.commands.exceptions.CommandException;

/**
 * Close an issue on github
 */
public class CloseIssueCommand extends Command {

    public static final String COMMAND_WORD = "-Issue";
    public static final String COMMAND_ALIAS = "ci";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " ISSUE-INDEX";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Parameters: ISSUE_INDEX (must be a positive valid index number)"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_SUCCESS = "Issue %1$s closed successfully";

    private final String repoName = new String("AdityaA1998/samplerepo-pr-practice");
    private final String userLogin = new String("anminkang");
    private final String userAuthentication = new String("aditya2018");

    private final Index targetIndex;

    public CloseIssueCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() throws CommandException {
        try {
            GitHub github = GitHub.connectUsingPassword(userLogin, userAuthentication);
            GHRepository repository = github.getRepository(repoName);
            GHIssue issue = repository.getIssue(targetIndex.getOneBased());
            issue.close();
        } catch (IOException ie) {
            throw new CommandException("NOT CLOSED");
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, targetIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CloseIssueCommand // instanceof handles nulls
                && this.targetIndex.equals(((CloseIssueCommand) other).targetIndex)); // state check
    }
}
