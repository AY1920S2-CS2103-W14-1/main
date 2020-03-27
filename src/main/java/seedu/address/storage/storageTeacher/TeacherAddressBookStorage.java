package seedu.address.storage.storageTeacher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelTeacher.Teacher;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface TeacherAddressBookStorage {

  /**
   * Returns the file path of the data file.
   */
  Path getTeacherAddressBookFilePath();

  /**
   * Returns TeacherAddressBook data as a {@link ReadOnlyAddressBookGeneric<Teacher>}. Returns {@code
   * Optional.empty()} if storage file is not found.
   *
   * @throws DataConversionException if the data in storage is not in the expected format.
   * @throws IOException             if there was any problem when reading from the storage.
   */
  Optional<ReadOnlyAddressBookGeneric<Teacher>> readTeacherAddressBook()
      throws DataConversionException, IOException;

  /**
   * @see #getTeacherAddressBookFilePath()
   */
  Optional<ReadOnlyAddressBookGeneric<Teacher>> readTeacherAddressBook(Path filePath)
      throws DataConversionException, IOException;

  /**
   * Saves the given {@link ReadOnlyAddressBookGeneric<Teacher>} to the storage.
   *
   * @param teacherAddressBook cannot be null.
   * @throws IOException if there was any problem writing to the file.
   */
  void saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook) throws IOException;

  /**
   * @see #saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher>)
   */
  void saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook, Path filePath)
      throws IOException;

}
