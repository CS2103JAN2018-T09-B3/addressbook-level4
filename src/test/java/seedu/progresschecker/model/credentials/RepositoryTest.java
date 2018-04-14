package seedu.progresschecker.model.credentials;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import seedu.progresschecker.testutil.Assert;

public class RepositoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Repository(null));
    }

    @Test
    public void constructor_invalidRepository_throwsIllegalArgumentException() {
        String invalidRepo = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Repository(invalidRepo));
    }

    @Test
    public void isValidRepository() {
        // null name
        Assert.assertThrows(NullPointerException.class, () -> Repository.isValidRepository(null));

        // invalid name
        assertFalse(Repository.isValidRepository("")); // empty string
        assertFalse(Repository.isValidRepository(" ")); // spaces only
        assertFalse(Repository.isValidRepository("^")); // only non-alphanumeric characters
        assertFalse(Repository.isValidRepository("peter jack")); // alphabets only with spaces
        assertFalse(Repository.isValidRepository("peter the 2nd")); // alphanumeric characters with spaces


        // valid name
        assertTrue(Repository.isValidRepository("12345")); // numbers only
        assertTrue(Repository.isValidRepository("github/repo-4")); // with capital letters
        assertTrue(Repository.isValidRepository("peter*")); // contains non-alphanumeric characters

    }
}
