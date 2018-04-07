package seedu.progresschecker.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.progresschecker.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.progresschecker.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.progresschecker.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.progresschecker.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.progresschecker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.progresschecker.logic.commands.CommandTestUtil.prepareRedoCommand;
import static seedu.progresschecker.logic.commands.CommandTestUtil.prepareUndoCommand;
import static seedu.progresschecker.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.progresschecker.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.progresschecker.testutil.TypicalPersons.getTypicalProgressChecker;

import org.junit.Test;
import seedu.progresschecker.commons.core.Messages;
import seedu.progresschecker.commons.core.index.Index;
import seedu.progresschecker.logic.CommandHistory;
import seedu.progresschecker.logic.UndoRedoStack;
import seedu.progresschecker.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.progresschecker.model.Model;
import seedu.progresschecker.model.ModelManager;
import seedu.progresschecker.model.ProgressChecker;
import seedu.progresschecker.model.UserPrefs;
import seedu.progresschecker.model.exercise.Exercise;
import seedu.progresschecker.model.exercise.QuestionIndex;
import seedu.progresschecker.model.exercise.StudentAnswer;
import seedu.progresschecker.model.person.Person;
import seedu.progresschecker.testutil.EditPersonDescriptorBuilder;
import seedu.progresschecker.testutil.ExerciseBuilder;
import seedu.progresschecker.testutil.PersonBuilder;

//@@author iNekox3
/**
 * Contains unit tests for AnswerCommand.
 */
public class AnswerCommandTest {

    private Model model = new ModelManager(getTypicalProgressChecker(), new UserPrefs());

    /*@Test
    public void execute_studentAnswerUpdate_success() throws Exception {
        Exercise editedExercise = new ExerciseBuilder().withStudentAnswer("a").build();
        AnswerCommand answerCommand = prepareCommand(new QuestionIndex("11.1.1"), new StudentAnswer("a"));

        String expectedMessage = String.format(AnswerCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, "11.1.1");

        Model expectedModel = new ModelManager(new ProgressChecker(model.getProgressChecker()), new UserPrefs());
        expectedModel.updateExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(answerCommand, model, expectedMessage, expectedModel);
    }*/

    /**
     * Returns an {@code AnswerCommand} with parameters {@code questionIndex} and {@code studentAnswer}
     */
    private AnswerCommand prepareCommand(QuestionIndex questionIndex, StudentAnswer studentAnswer) {
        AnswerCommand answerCommand = new AnswerCommand(questionIndex, studentAnswer);
        answerCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return answerCommand;
    }
}
