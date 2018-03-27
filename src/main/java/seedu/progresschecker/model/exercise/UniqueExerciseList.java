package seedu.progresschecker.model.exercise;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.progresschecker.commons.util.CollectionUtil;

//@@author iNekox3
/**
 * A list of exercises that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Exercise#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueExerciseList {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exercise> asObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }
}
