package seedu.address.storage.storageAssignments;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.address.model.modelAssignment.AssignmentAddressBook}.
 */
public interface AssignmentAddressBookStorage {

  /**
   * Returns the file path of the data file.
   */
  Path getAssignmentAddressBookFilePath();

  /**
   * Returns AssignmentAddressBook data as a {@link ReadOnlyAddressBookGeneric<Assignment>}. Returns {@code
   * Optional.empty()} if storage file is not found.
   *
   * @throws DataConversionException if the data in storage is not in the expected format.
   * @throws IOException             if there was any problem when reading from the storage.
   */
  Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook()
      throws DataConversionException, IOException;

  /**
   * @see #getAssignmentAddressBookFilePath()
   */
  Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook(Path filePath)
      throws DataConversionException, IOException;

  /**
   * Saves the given {@link ReadOnlyAddressBookGeneric<Assignment>} to the storage.
   *
   * @param assignmentAddressBook cannot be null.
   * @throws IOException if there was any problem writing to the file.
   */
  void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook) throws IOException;

  /**
   * @see #saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment>)
   */
  void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook, Path filePath)
      throws IOException;

}
