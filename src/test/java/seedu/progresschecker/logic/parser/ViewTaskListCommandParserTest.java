package seedu.progresschecker.logic.parser;

import static seedu.progresschecker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.progresschecker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.progresschecker.logic.parser.ParserUtil.MESSAGE_INVALID_TASK_FILTER;
import static seedu.progresschecker.testutil.TypicalTaskArgs.COMPULSORY;
import static seedu.progresschecker.testutil.TypicalTaskArgs.COM_INT;
import static seedu.progresschecker.testutil.TypicalTaskArgs.FIRST_WEEK;
import static seedu.progresschecker.testutil.TypicalTaskArgs.FIRST_WEEK_INT;
import static seedu.progresschecker.testutil.TypicalTaskArgs.INVALID_CHARSET;
import static seedu.progresschecker.testutil.TypicalTaskArgs.INVALID_NEGATIVE;
import static seedu.progresschecker.testutil.TypicalTaskArgs.INVALID_ZERO;
import static seedu.progresschecker.testutil.TypicalTaskArgs.SUBMISSION;
import static seedu.progresschecker.testutil.TypicalTaskArgs.SUB_INT;

import org.junit.Test;

import seedu.progresschecker.logic.commands.ViewTaskListCommand;

//@@author EdwardKSG
public class ViewTaskListCommandParserTest {

    private ViewTaskListCommandParser parser = new ViewTaskListCommandParser();

    @Test
    public void parse_validArgs1_returnsViewTaskListCommand() {
        assertParseSuccess(parser, FIRST_WEEK, new ViewTaskListCommand(FIRST_WEEK_INT));
    }

    @Test
    public void parse_validArgs2_returnsViewTaskListCommand() {
        assertParseSuccess(parser, COMPULSORY, new ViewTaskListCommand(COM_INT));
    }

    @Test
    public void parse_validArgs3_returnsViewTaskListCommand() {
        assertParseSuccess(parser, SUBMISSION, new ViewTaskListCommand(SUB_INT));
    }

    @Test
    public void parse_invalidArgs1_throwsParseException() {
        assertParseFailure(parser, INVALID_NEGATIVE, String.format(MESSAGE_INVALID_TASK_FILTER,
                ViewTaskListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs2_throwsParseException() {
        assertParseFailure(parser, INVALID_ZERO, String.format(MESSAGE_INVALID_TASK_FILTER,
                ViewTaskListCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidArgs3_throwsParseException() {
        assertParseFailure(parser, INVALID_CHARSET, String.format(MESSAGE_INVALID_TASK_FILTER,
                ViewTaskListCommand.MESSAGE_USAGE));
    }
}
