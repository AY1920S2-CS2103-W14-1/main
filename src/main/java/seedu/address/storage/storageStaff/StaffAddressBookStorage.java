package seedu.address.storage.storageStaff;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelStaff.ReadOnlyStaffAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface StaffAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStaffAddressBookFilePath();

    /**
     * Returns StaffAddressBook data as a {@link ReadOnlyStaffAddressBook}. Returns {@code
     * Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStaffAddressBook> readStaffAddressBook()
            throws DataConversionException, IOException;

    /**
     * @see #getStaffAddressBookFilePath()
     */
    Optional<ReadOnlyStaffAddressBook> readStaffAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStaffAddressBook} to the storage.
     *
     * @param staffAddressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStaffAddressBook(ReadOnlyStaffAddressBook staffAddressBook) throws IOException;

    /**
     * @see #saveStaffAddressBook(ReadOnlyStaffAddressBook)
     */
    void saveStaffAddressBook(ReadOnlyStaffAddressBook staffAddressBook, Path filePath)
            throws IOException;

}