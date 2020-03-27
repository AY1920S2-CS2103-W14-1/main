package seedu.address.storage.storageFinance;

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
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * A class to access FinanceAddressBook data stored as a json file on the hard disk.
 */
public class JsonFinanceAddressBookStorage implements FinanceAddressBookStorage {

  private static final Logger logger = LogsCenter.getLogger(
      JsonFinanceAddressBookStorage.class);

  private Path filePath;

  public JsonFinanceAddressBookStorage(Path filePath) {
    this.filePath = filePath;
  }

  public Path getFinanceAddressBookFilePath() {
    return filePath;
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook()
      throws DataConversionException {
    return readFinanceAddressBook(filePath);
  }

  /**
   * Similar to {@link #readFinanceAddressBook()}.
   *
   * @param filePath location of the data. Cannot be null.
   * @throws DataConversionException if the file is not in the correct format.
   */
  public Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook(Path filePath)
      throws DataConversionException {
    requireNonNull(filePath);

    Optional<JsonFinanceSerializableAddressBook> jsonFinanceAddressBook = JsonUtil.readJsonFile(
        filePath, JsonFinanceSerializableAddressBook.class);
    if (!jsonFinanceAddressBook.isPresent()) {
      return Optional.empty();
    }

    try {
      return Optional.of(jsonFinanceAddressBook.get().toModelType());
    } catch (IllegalValueException ive) {
      logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
      throw new DataConversionException(ive);
    }
  }

  @Override
  public void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook)
      throws IOException {
    saveFinanceAddressBook(financeAddressBook, filePath);
  }

  /**
   * Similar to {@link #saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance>)}.
   *
   * @param filePath location of the data. Cannot be null.
   */
  public void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook, Path filePath)
      throws IOException {
    requireNonNull(financeAddressBook);
    requireNonNull(filePath);

    FileUtil.createIfMissing(filePath);
    JsonUtil.saveJsonFile(new JsonFinanceSerializableAddressBook(financeAddressBook), filePath);
  }

}
