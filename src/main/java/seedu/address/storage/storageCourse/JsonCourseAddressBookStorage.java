package seedu.address.storage.storageCourse;

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
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * A class to access AssignmentAddressBook data stored as a json file on the hard disk.
 */
public class JsonCourseAddressBookStorage implements CourseAddressBookStorage {

  private static final Logger logger = LogsCenter.getLogger(
      JsonCourseAddressBookStorage.class);

  private Path filePath;

  public JsonCourseAddressBookStorage(Path filePath) {
    this.filePath = filePath;
  }

  public Path getCourseAddressBookFilePath() {
    return filePath;
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook()
      throws DataConversionException {
    return readCourseAddressBook(filePath);
  }

  /**
   * Similar to {@link #readCourseAddressBook()}.
   *
   * @param filePath location of the data. Cannot be null.
   * @throws DataConversionException if the file is not in the correct format.
   */
  public Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook(Path filePath)
      throws DataConversionException {
    requireNonNull(filePath);

    Optional<JsonCourseSerializableAddressBook> jsonCourseAddressBook = JsonUtil.readJsonFile(
        filePath, JsonCourseSerializableAddressBook.class);
    if (!jsonCourseAddressBook.isPresent()) {
      return Optional.empty();
    }

    try {
      return Optional.of(jsonCourseAddressBook.get().toModelType());
    } catch (IllegalValueException ive) {
      logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
      throw new DataConversionException(ive);
    }
  }

  @Override
  public void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> coursesAddressBook)
      throws IOException {
    saveCourseAddressBook(coursesAddressBook, filePath);
  }

  /**
   * Similar to {@link #saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course>)}.
   *
   * @param filePath location of the data. Cannot be null.
   */
  public void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> coursesAddressBook, Path filePath)
      throws IOException {
    System.out.println("h");
    requireNonNull(coursesAddressBook);
    requireNonNull(filePath);

    FileUtil.createIfMissing(filePath);
    JsonUtil.saveJsonFile(new JsonCourseSerializableAddressBook(coursesAddressBook), filePath);
  }

}
