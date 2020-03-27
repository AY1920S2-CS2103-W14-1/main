package seedu.address.storage.storageAssignments;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access AssignmentAddressBook data stored as a json file on the hard disk.
 */
public class JsonAssignmentAddressBookStorage implements AssignmentAddressBookStorage {

  private static final Logger logger = LogsCenter.getLogger(
      JsonAssignmentAddressBookStorage.class);

  private Path filePath;

  public JsonAssignmentAddressBookStorage(Path filePath) {
    this.filePath = filePath;
  }

  public Path getAssignmentAddressBookFilePath() {
    return filePath;
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook()
      throws DataConversionException {
    return readAssignmentAddressBook(filePath);
  }

  /**
   * Similar to {@link #readAssignmentAddressBook()}.
   *
   * @param filePath location of the data. Cannot be null.
   * @throws DataConversionException if the file is not in the correct format.
   */
  public Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook(Path filePath)
      throws DataConversionException {
    requireNonNull(filePath);

    Optional<JsonAssignmentSerializableAddressBook> jsonAssignmentAddressBook = JsonUtil.readJsonFile(
        filePath, JsonAssignmentSerializableAddressBook.class);
    if (!jsonAssignmentAddressBook.isPresent()) {
      return Optional.empty();
    }

    try {
      return Optional.of(jsonAssignmentAddressBook.get().toModelType());
    } catch (IllegalValueException ive) {
      logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
      throw new DataConversionException(ive);
    }
  }

  @Override
  public void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentsAddressBook)
      throws IOException {
    saveAssignmentAddressBook(assignmentsAddressBook, filePath);
  }

  /**
   * Similar to {@link #saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment>)}.
   *
   * @param filePath location of the data. Cannot be null.
   */
  public void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentsAddressBook, Path filePath)
      throws IOException {
    requireNonNull(assignmentsAddressBook);
    requireNonNull(filePath);

    FileUtil.createIfMissing(filePath);
    JsonUtil.saveJsonFile(new JsonAssignmentSerializableAddressBook(assignmentsAddressBook), filePath);
  }

}
