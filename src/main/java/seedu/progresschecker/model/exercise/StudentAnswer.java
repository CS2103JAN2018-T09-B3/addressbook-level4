package seedu.progresschecker.model.exercise;

import static java.util.Objects.requireNonNull;

//@@author iNekox3
/**
 * Represents an Exercise's student answer in the ProgressChecker.
 */
public class StudentAnswer {

    public final String answer;

    /**
     * Constructs a {@code StudentAnswer}.
     *
     * @param answer An answer of any word and character.
     */
    public StudentAnswer(String answer) {
        requireNonNull(answer);
        this.answer = answer;
    }

    @Override
    public String toString() {
        return answer;
    }
}
