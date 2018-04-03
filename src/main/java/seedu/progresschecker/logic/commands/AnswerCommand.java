package seedu.progresschecker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.progresschecker.model.Model.PREDICATE_SHOW_ALL_EXERCISES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.progresschecker.commons.core.Messages;
import seedu.progresschecker.commons.util.CollectionUtil;
import seedu.progresschecker.logic.commands.exceptions.CommandException;
import seedu.progresschecker.model.exercise.Exercise;
import seedu.progresschecker.model.exercise.ModelAnswer;
import seedu.progresschecker.model.exercise.Question;
import seedu.progresschecker.model.exercise.QuestionIndex;
import seedu.progresschecker.model.exercise.QuestionType;
import seedu.progresschecker.model.exercise.StudentAnswer;
import seedu.progresschecker.model.exercise.exceptions.ExerciseNotFoundException;

//@@author iNekox3
/**
 * Edits details of student answer of an exercise in the ProgressChecker.
 */
public class AnswerCommand extends UndoableCommand {
    public static final int MIN_WEEK_NUMBER = 2;
    public static final int MAX_WEEK_NUMBER = 13;
    
    public static final String COMMAND_WORD = "answer";
    public static final String COMMAND_ALIAS = "ans";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " QUESTION-INDEX" + " ANSWER";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Answer an exercise"
            + "identified by the index number shown"
            + "Existing answer will be overwritten by the input value.\n"
            + "Parameters: INDEX (must be in the format of WEEK.SECTION.QUESTION number "
            + "where WEEK range from " + MIN_WEEK_NUMBER + " to " + MAX_WEEK_NUMBER + ") "
            + "ANSWER\n"
            + "Example: " + COMMAND_WORD + " 2.1.1 "
            + "Procedural languages work at simple data structures and functions level.";

    public static final String MESSAGE_EDIT_EXERCISE_SUCCESS = "Answered Exercise: %1$s";

    private final QuestionIndex questionIndex;
    private final EditExerciseDescriptor editExerciseDescriptor;

    private Exercise exerciseToEdit;
    private Exercise editedExercise;

    /**
     * @param questionIndex of the question in the filtered exercise list to edit
     * @param editExerciseDescriptor details to edit the exericse with
     */
    public AnswerCommand(QuestionIndex questionIndex, EditExerciseDescriptor editExerciseDescriptor) {
        requireNonNull(questionIndex);
        requireNonNull(editExerciseDescriptor);

        this.questionIndex = questionIndex;
        this.editExerciseDescriptor = new EditExerciseDescriptor(editExerciseDescriptor);
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        try {
            model.updateExercise(exerciseToEdit, editedExercise);
        } catch (ExerciseNotFoundException enfe) {
            throw new AssertionError("The target exercise cannot be missing");
        }
        model.updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISES);
        return new CommandResult(String.format(MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise));
    }

    @Override
    protected void preprocessUndoableCommand() throws CommandException {
        List<Exercise> exerciseList = model.getFilteredExerciseList();

        if (!questionIndex.isValidIndex(questionIndex.toString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXERCISE_INDEX);
        }

        exerciseToEdit = exerciseList.get(questionIndex.getQuestionNumber());
        editedExercise = createEditedExercise(exerciseToEdit, editExerciseDescriptor);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Exercise createEditedExercise(Exercise exerciseToEdit, EditExerciseDescriptor editExerciseDescriptor) {
        assert exerciseToEdit != null;

        QuestionIndex updatedQuestionIndex = editExerciseDescriptor.getQuestionIndex()
                .orElse(exerciseToEdit.getQuestionIndex());
        QuestionType updatedQuestionType = editExerciseDescriptor.getQuestionType()
                .orElse(exerciseToEdit.getQuestionType());
        Question updatedQuestion = editExerciseDescriptor.getQuestion()
                .orElse(exerciseToEdit.getQuestion());
        StudentAnswer updatedStudentAnswer = editExerciseDescriptor.getStudentAnswer()
                .orElse(exerciseToEdit.getStudentAnswer());
        ModelAnswer updatedModelAnswer = editExerciseDescriptor.getModelAnswer()
                .orElse(exerciseToEdit.getModelAnswer());

        return new Exercise(updatedQuestionIndex, updatedQuestionType, updatedQuestion,
                updatedStudentAnswer, updatedModelAnswer);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        AnswerCommand e = (AnswerCommand) other;
        return questionIndex.equals(e.questionIndex)
                && editExerciseDescriptor.equals(e.editExerciseDescriptor)
                && Objects.equals(exerciseToEdit, e.exerciseToEdit);
    }

    /**
     * Stores the details to edit the exercise with. Each non-empty field value will replace the
     * corresponding field value of the exercise.
     */
    public static class EditExerciseDescriptor {
        private QuestionIndex questionIndex;
        private QuestionType questionType;
        private Question question;
        private StudentAnswer studentAnswer;
        private ModelAnswer modelAnswer;

        public EditExerciseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExerciseDescriptor(EditExerciseDescriptor toCopy) {
            setQuestionIndex(toCopy.questionIndex);
            setQuestionType(toCopy.questionType);
            setQuestion(toCopy.question);
            setStudentAnswer(toCopy.studentAnswer);
            setModelAnswer(toCopy.modelAnswer);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(this.questionIndex, this.questionType, this.question,
                    this.studentAnswer, this.modelAnswer);
        }

        public void setQuestionIndex(QuestionIndex questionIndex) {
            this.questionIndex = questionIndex;
        }

        public Optional<QuestionIndex> getQuestionIndex() {
            return Optional.ofNullable(questionIndex);
        }

        public void setQuestionType(QuestionType questionType) {
            this.questionType = questionType;
        }

        public Optional<QuestionType> getQuestionType() {
            return Optional.ofNullable(questionType);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Optional<Question> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setStudentAnswer(StudentAnswer studentAnswer) {
            this.studentAnswer = studentAnswer;
        }

        public Optional<StudentAnswer> getStudentAnswer() {
            return Optional.ofNullable(studentAnswer);
        }

        public void setModelAnswer(ModelAnswer modelAnswer) {
            this.modelAnswer = modelAnswer;
        }

        public Optional<ModelAnswer> getModelAnswer() {
            return Optional.ofNullable(modelAnswer);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExerciseDescriptor)) {
                return false;
            }

            // state check
            EditExerciseDescriptor e = (EditExerciseDescriptor) other;

            return getQuestionIndex().equals(e.getQuestionIndex())
                    && getQuestionType().equals(e.getQuestionType())
                    && getQuestion().equals(e.getQuestion())
                    && getStudentAnswer().equals(e.getStudentAnswer())
                    && getModelAnswer().equals(e.getModelAnswer());
        }
    }
}
