package seedu.address.storage.storageStaff;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStaff.Staff;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface StaffAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStaffAddressBookFilePath();

    /**
     * Returns StaffAddressBook data as a {@link ReadOnlyAddressBookGeneric<Staff>}. Returns {@code
     * Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook()
            throws DataConversionException, IOException;

    /**
     * @see #getStaffAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBookGeneric<Staff>} to the storage.
     *
     * @param staffAddressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook) throws IOException;

    /**
     * @see #saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff>)
     */
    void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook, Path filePath)
            throws IOException;

}