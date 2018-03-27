package seedu.progresschecker.model.exercise;

import static java.util.Objects.requireNonNull;

//@@author iNekox3
/**
 * Represents an Exercise's question in the ProgressChecker.
 */
public class Question {

    public final String question;

    /**
     * Constructs a {@code Question}.
     *
     * @param question A valid question.
     */
    public Question(String question) {
        requireNonNull(question);
        this.question = question;
    }

    @Override
    public String toString() {
        return question;
    }
}
