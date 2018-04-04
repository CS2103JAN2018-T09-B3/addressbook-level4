package seedu.progresschecker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.progresschecker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.progresschecker.commons.exceptions.IllegalValueException;
import seedu.progresschecker.logic.commands.AnswerCommand;
import seedu.progresschecker.logic.parser.exceptions.ParseException;
import seedu.progresschecker.model.exercise.QuestionIndex;
import seedu.progresschecker.model.exercise.StudentAnswer;

/**
 * Parses input arguments and creates a new AnswerCommand object
 */
public class AnswerCommandParser implements Parser<AnswerCommand> {

    private static final int QUESTION_INDEX_INDEX = 0;
    private static final int ANSWER_INDEX = 1;
    
    /**
     * Parses the given {@code String} of arguments in the context of the AnswerCommand
     * and returns an AnswerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] content = args.trim().split(" ", 2);

        QuestionIndex questionIndex;
        
        try {
            questionIndex = ParserUtil.parseQuestionIndex(content[QUESTION_INDEX_INDEX]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AnswerCommand.MESSAGE_USAGE));
        }

        StudentAnswer studentAnswer = ParserUtil.parseStudentAnswer(content[ANSWER_INDEX]);

        return new AnswerCommand(questionIndex, studentAnswer);
    }

}
