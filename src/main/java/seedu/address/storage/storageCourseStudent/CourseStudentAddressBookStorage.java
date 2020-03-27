package seedu.address.storage.storageCourseStudent;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Represents a storage for {@link seedu.address.model.modelCourseStudent.CourseStudentAddressBook}.
 */
public interface CourseStudentAddressBookStorage {

  /**
   * Returns the file path of the data file.
   */
  Path getCourseStudentAddressBookFilePath();

  /**
   * Returns CourseStudentAddressBook data as a {@link ReadOnlyAddressBookGeneric<CourseStudent>}. Returns {@code
   * Optional.empty()} if storage file is not found.
   *
   * @throws DataConversionException if the data in storage is not in the expected format.
   * @throws IOException             if there was any problem when reading from the storage.
   */
  Optional<ReadOnlyAddressBookGeneric<CourseStudent>> readCourseStudentAddressBook()
      throws DataConversionException, IOException;

  /**
   * @see #getCourseStudentAddressBookFilePath()
   */
  Optional<ReadOnlyAddressBookGeneric<CourseStudent>> readCourseStudentAddressBook(Path filePath)
      throws DataConversionException, IOException;

  /**
   * Saves the given {@link ReadOnlyAddressBookGeneric<CourseStudent>} to the storage.
   *
   * @param courseStudentAddressBook cannot be null.
   * @throws IOException if there was any problem writing to the file.
   */
  void saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook) throws IOException;

  /**
   * @see #saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent>)
   */
  void saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook, Path filePath)
      throws IOException;

}
