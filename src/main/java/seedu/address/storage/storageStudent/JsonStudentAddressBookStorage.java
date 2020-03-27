package seedu.address.storage.storageStudent;

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
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStudent.Student;

/**
 * A class to access StudentAddressBook data stored as a json file on the hard disk.
 */
public class JsonStudentAddressBookStorage implements StudentAddressBookStorage {

  private static final Logger logger = LogsCenter.getLogger(
      JsonStudentAddressBookStorage.class);

  private Path filePath;

  public JsonStudentAddressBookStorage(Path filePath) {
    this.filePath = filePath;
  }

  public Path getStudentAddressBookFilePath() {
    return filePath;
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Student>> readStudentAddressBook()
      throws DataConversionException {
    return readStudentAddressBook(filePath);
  }

  /**
   * Similar to {@link #readStudentAddressBook()}.
   *
   * @param filePath location of the data. Cannot be null.
   * @throws DataConversionException if the file is not in the correct format.
   */
  public Optional<ReadOnlyAddressBookGeneric<Student>> readStudentAddressBook(Path filePath)
      throws DataConversionException {
    requireNonNull(filePath);

    Optional<JsonStudentSerializableAddressBook> jsonStudentAddressBook = JsonUtil.readJsonFile(
        filePath, JsonStudentSerializableAddressBook.class);
    if (!jsonStudentAddressBook.isPresent()) {
      return Optional.empty();
    }

    try {
      return Optional.of(jsonStudentAddressBook.get().toModelType());
    } catch (IllegalValueException ive) {
      logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
      throw new DataConversionException(ive);
    }
  }

  @Override
  public void saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook)
      throws IOException {
    saveStudentAddressBook(studentAddressBook, filePath);
  }

  /**
   * Similar to {@link #saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student>)}.
   *
   * @param filePath location of the data. Cannot be null.
   */
  public void saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook, Path filePath)
      throws IOException {
    System.out.println("saving student");
    requireNonNull(studentAddressBook);
    requireNonNull(filePath);

    FileUtil.createIfMissing(filePath);
    JsonUtil.saveJsonFile(new JsonStudentSerializableAddressBook(studentAddressBook), filePath);
  }

}
