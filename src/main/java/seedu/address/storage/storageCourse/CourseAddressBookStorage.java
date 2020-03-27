package seedu.address.storage.storageCourse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface CourseAddressBookStorage {

  /**
   * Returns the file path of the data file.
   */
  Path getCourseAddressBookFilePath();

  /**
   * Returns CourseAddressBook data as a {@linkReadOnlyAddressBookGeneric<Course>}. Returns {@code
   * Optional.empty()} if storage file is not found.
   *
   * @throws DataConversionException if the data in storage is not in the expected format.
   * @throws IOException             if there was any problem when reading from the storage.
   */
  Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook()
      throws DataConversionException, IOException;

  /**
   * @see #getCourseAddressBookFilePath()
   */
  Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook(Path filePath)
      throws DataConversionException, IOException;

  /**
   * Saves the given {@linkReadOnlyAddressBookGeneric<Course>} to the storage.
   *
   * @param courseAddressBook cannot be null.
   * @throws IOException if there was any problem writing to the file.
   */
  void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook) throws IOException;

  /**
   * @see #saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course>)
   */
  void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook, Path filePath)
      throws IOException;

}
