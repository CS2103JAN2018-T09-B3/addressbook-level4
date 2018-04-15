package seedu.progresschecker.logic.commands;

import org.junit.Test;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.ProgressChecker;
import seedu.progresschecker.model.ReadOnlyProgressChecker;
import seedu.progresschecker.model.exercise.Exercise;
import seedu.progresschecker.model.exercise.exceptions.DuplicateExerciseException;
import seedu.progresschecker.model.person.Person;
import seedu.progresschecker.model.person.exceptions.DuplicatePersonException;
import seedu.progresschecker.testutil.PersonBuilder;

public class AnswerCommandTest {
    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        AnswerCommandTest.ModelStub modelStub = new AnswerCommandTest.ModelStubThrowingDuplicateExerciseException();
        Person validPerson = new PersonBuilder().build();

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddCommand.MESSAGE_DUPLICATE_PERSON);

        getAddCommandForExecise(validPerson, modelStub).execute();
    }

    /**
     * A Model stub that always throw a DuplicateExerciseException when trying to add a exercise.
     */
    private class ModelStubThrowingDuplicateExerciseException extends AnswerCommandTest.ModelStub {
        @Override
        public void addExercise(Exercise exercise) throws DuplicateExerciseException {
            throw new DuplicateExerciseException();
        }

        @Override
        public ReadOnlyProgressChecker getProgressChecker() {
            return new ProgressChecker();
        }
    }
}
