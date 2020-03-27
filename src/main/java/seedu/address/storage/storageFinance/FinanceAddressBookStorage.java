package seedu.address.storage.storageFinance;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface FinanceAddressBookStorage {

  /**
   * Returns the file path of the data file.
   */
  Path getFinanceAddressBookFilePath();

  /**
   * Returns FinanceAddressBook data as a {@link ReadOnlyAddressBookGeneric<Finance>}. Returns {@code
   * Optional.empty()} if storage file is not found.
   *
   * @throws DataConversionException if the data in storage is not in the expected format.
   * @throws IOException             if there was any problem when reading from the storage.
   */
  Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook()
      throws DataConversionException, IOException;

  /**
   * @see #getFinanceAddressBookFilePath()
   */
  Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook(Path filePath)
      throws DataConversionException, IOException;

  /**
   * Saves the given {@link ReadOnlyAddressBookGeneric<Finance>} to the storage.
   *
   * @param financeAddressBook cannot be null.
   * @throws IOException if there was any problem writing to the file.
   */
  void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook) throws IOException;

  /**
   * @see #saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance>)
   */
  void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook, Path filePath)
      throws IOException;

}
