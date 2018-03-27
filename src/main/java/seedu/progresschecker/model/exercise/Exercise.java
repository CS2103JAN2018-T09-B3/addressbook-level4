package seedu.progresschecker.model.exercise;

import static java.util.Objects.requireNonNull;

//@@author iNekox3
/**
 * Represents an Exercise in the ProgressChecker.
 */
public class Exercise {

    public final int questionIndex;
    public final String questionType;
    public final String question;
    public final String studentAnswer;
    public final String modelAnswer;

    /**
     * Constructs an {@code Exercise}.
     *
     * @param questionIndex A valid question index.
     * @param questionType A valid question type.
     * @param question A string of any characters.
     * @param modelAnswer A string o any characters.
     */
    public Tag(int questionIndex, String questionType, String question, String modelAnswer) {
        requireNonNull(questionIndex);
        requireNonNull(questionType);
        requireNonNull(question);
        
        this.questionIndex = questionIndex;
        this.questionType = questionType;
        this.question = question;
        this.studentAnswer = "";
        this.modelAnswer = modelAnswer;
    }

    /**
     * Format exercise as text for viewing.
     */
    public String toString() {
        return "Q" + questionIndex + " " + question + "\n\n"
                + "Your Answer: " + studentAnswer + "\n\n"
                + "Suggested Answer: " + modelAnswer;
    }
}
