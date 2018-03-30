package seedu.progresschecker.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.progresschecker.commons.core.LogsCenter;
import seedu.progresschecker.commons.exceptions.DataConversionException;
import seedu.progresschecker.commons.exceptions.IllegalValueException;
import seedu.progresschecker.commons.util.FileUtil;
import seedu.progresschecker.model.ReadOnlyProgressChecker;

/**
 * A class to access ProgressChecker data stored as an xml file on the hard disk.
 */
public class XmlProgressCheckerStorage implements ProgressCheckerStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlProgressCheckerStorage.class);

    private String filePath;
    private String filePathExercises;

    public XmlProgressCheckerStorage(String filePath, String filePathExercises) {
        this.filePath = filePath;
        this.filePathExercises = filePathExercises;
    }

    public String getProgressCheckerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProgressChecker> readProgressChecker() throws DataConversionException, IOException {
        return readProgressChecker(filePath);
    }

    /**
     * Similar to {@link #readProgressChecker()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProgressChecker> readProgressChecker(String filePath) throws DataConversionException,
                                                                                 FileNotFoundException {
        requireNonNull(filePath);

        File progressCheckerFile = new File(filePath);

        if (!progressCheckerFile.exists()) {
            logger.info("ProgressChecker file "  + progressCheckerFile + " not found");
            return Optional.empty();
        }

        XmlSerializableProgressChecker xmlProgressChecker = XmlFileStorage.loadDataFromSaveFile(new File(filePath));
        try {
            return Optional.of(xmlProgressChecker.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + progressCheckerFile + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProgressChecker(ReadOnlyProgressChecker progressChecker) throws IOException {
        saveProgressChecker(progressChecker, filePath);
    }

    /**
     * Similar to {@link #saveProgressChecker(ReadOnlyProgressChecker)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveProgressChecker(ReadOnlyProgressChecker progressChecker, String filePath) throws IOException {
        requireNonNull(progressChecker);
        requireNonNull(filePath);

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveDataToFile(file, new XmlSerializableProgressChecker(progressChecker));
    }

    //@@author iNekox3
    public String getExercisesFilePath() {
        return filePathExercises;
    }

    public void setExercisesFilePath(String filePath) {
        this.filePathExercises = filePath;
    }

    @Override
    public Optional<ReadOnlyProgressChecker> readExercises(int index) throws DataConversionException, IOException {
        return readExercises(filePath, index);
    }

    /**
     * Similar to {@link #readProgressChecker()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProgressChecker> readExercises(String filePath, int index) throws DataConversionException,
            FileNotFoundException {
        requireNonNull(filePath);

        File exercisesFile = new File(filePath);

        if (!exercisesFile.exists()) {
            logger.info("Exercises file "  + exercisesFile + " not found");
            return Optional.empty();
        }

        XmlSerializableProgressChecker xmlProgressChecker = XmlFileStorage.loadDataFromSaveFile(new File(filePath));
        try {
            return Optional.of(xmlProgressChecker.toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + exercisesFile + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

}
