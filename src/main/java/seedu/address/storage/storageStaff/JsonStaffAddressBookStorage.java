package seedu.address.storage.storageStaff;

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
import seedu.address.model.modelStaff.Staff;
import seedu.address.storage.storageStaff.JsonStaffSerializableAddressBook;
import seedu.address.storage.storageStaff.StaffAddressBookStorage;

/**
 * A class to access TeacherAddressBook data stored as a json file on the hard disk.
 */
public class JsonStaffAddressBookStorage implements StaffAddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(seedu.address.storage.storageStaff.JsonStaffAddressBookStorage.class);

    private Path filePath;

    public JsonStaffAddressBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStaffAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook()
            throws DataConversionException {
        return readStaffAddressBook(filePath);
    }

    /**
     * Similar to {@link #readStaffAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonStaffSerializableAddressBook> jsonStaffAddressBook = JsonUtil.readJsonFile(
                filePath, JsonStaffSerializableAddressBook.class);
        if (!jsonStaffAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStaffAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook)
            throws IOException {
        saveStaffAddressBook(staffAddressBook, filePath);
    }

    /**
     * Similar to {@link #saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff>)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook, Path filePath)
            throws IOException {
        System.out.println("saving staff");
        requireNonNull(staffAddressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonStaffSerializableAddressBook(staffAddressBook), filePath);
    }

}
