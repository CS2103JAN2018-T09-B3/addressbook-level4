package seedu.progresschecker.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.progresschecker.testutil.TypicalTaskArgs.DEFAULT_LIST_TITLE;

import org.junit.Test;

//@@author EdwardKSG
/**
 * Contains assertion tests for {@code AddDefaultTasksCommand}. This command is not undoable.
 * The main part of the test is commented (will not execute). justification is given in comments at the test.
 */
public class AddDefaultTasksCommandTest {

    @Test
    public void execute_commandEquals() throws Exception {
        AddDefaultTasksCommand completeTaskCommand = new AddDefaultTasksCommand(DEFAULT_LIST_TITLE);
        AddDefaultTasksCommand completeTaskCommand2 = new AddDefaultTasksCommand("random thing");

        // same object -> execution successful
        assertTrue(completeTaskCommand.equals(completeTaskCommand));

        // different object -> execution failed
        assertFalse(completeTaskCommand.equals(completeTaskCommand2));
    }

    /* Decided to remove this test because: 1. this test will add a new task list and the content of the list is
    still being updated while before the final release. Once the list data is updated by us developers, the edge
    condition and expected output for tests of complete/reset task command and view URL command will all must be
    updated which is very tedious. 2. the result of this command is easy to observe and no repetitive tests involved
    3. this test takes a long time, which slows down the process when other developers build the project.
    @Test
    public void execute_success() throws Exception {
        AddDefaultTasksCommand addDefaultTasksCommand = new AddDefaultTasksCommand(DEFAULT_LIST_TITLE);

        String expected = String.format(MESSAGE_SUCCESS, DEFAULT_LIST_TITLE);
        String actual = addDefaultTasksCommand.execute().feedbackToUser;
        assertEquals(expected, actual);
    }
    */
}
