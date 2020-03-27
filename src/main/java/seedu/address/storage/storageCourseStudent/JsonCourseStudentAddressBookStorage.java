package seedu.address.storage.storageCourseStudent;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * A class to access CourseStudentAddressBook data stored as a json file on the hard disk.
 */
public class JsonCourseStudentAddressBookStorage implements CourseStudentAddressBookStorage {

  private static final Logger logger = LogsCenter.getLogger(
      JsonCourseStudentAddressBookStorage.class);

  private Path filePath;

  public JsonCourseStudentAddressBookStorage(Path filePath) {
    this.filePath = filePath;
  }

  public Path getCourseStudentAddressBookFilePath() {
    return filePath;
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<CourseStudent>> readCourseStudentAddressBook()
      throws DataConversionException {
    return readCourseStudentAddressBook(filePath);
  }

  /**
   * Similar to {@link #readCourseStudentAddressBook()}.
   *
   * @param filePath location of the data. Cannot be null.
   * @throws DataConversionException if the file is not in the correct format.
   */
  public Optional<ReadOnlyAddressBookGeneric<CourseStudent>> readCourseStudentAddressBook(Path filePath)
      throws DataConversionException {
    requireNonNull(filePath);

    Optional<JsonCourseStudentSerializableAddressBook> jsonCourseStudentAddressBook = JsonUtil.readJsonFile(
        filePath, JsonCourseStudentSerializableAddressBook.class);
    if (!jsonCourseStudentAddressBook.isPresent()) {
      return Optional.empty();
    }

    try {
      return Optional.of(jsonCourseStudentAddressBook.get().toModelType());
    } catch (IllegalValueException ive) {
      logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
      throw new DataConversionException(ive);
    }
  }

  @Override
  public void saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentsAddressBook)
      throws IOException {
    saveCourseStudentAddressBook(courseStudentsAddressBook, filePath);
  }

  /**
   * Similar to {@link #saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent>)}.
   *
   * @param filePath location of the data. Cannot be null.
   */
  public void saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentsAddressBook, Path filePath)
      throws IOException {
    System.out.println("h");
    requireNonNull(courseStudentsAddressBook);
    requireNonNull(filePath);

    FileUtil.createIfMissing(filePath);
    JsonUtil.saveJsonFile(new JsonCourseStudentSerializableAddressBook(courseStudentsAddressBook), filePath);
  }

}
