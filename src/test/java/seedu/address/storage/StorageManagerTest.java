package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.storage.storageAssignments.JsonAssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.JsonCourseAddressBookStorage;
import seedu.address.storage.storageFinance.JsonFinanceAddressBookStorage;
import seedu.address.storage.storageStaff.JsonStaffAddressBookStorage;
import seedu.address.storage.storageStudent.JsonStudentAddressBookStorage;
import seedu.address.storage.storageTeacher.JsonTeacherAddressBookStorage;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(getTempFilePath("ab"));
        JsonTeacherAddressBookStorage teacherAddressBookStorage =
            new JsonTeacherAddressBookStorage(getTempFilePath("tab"));
        JsonStaffAddressBookStorage staffAddressBookStorage =
                new JsonStaffAddressBookStorage(getTempFilePath("stab"));
        JsonStudentAddressBookStorage studentAddressBookStorage =
            new JsonStudentAddressBookStorage(getTempFilePath("sab"));
        JsonFinanceAddressBookStorage financeAddressBookStorage =
            new JsonFinanceAddressBookStorage(getTempFilePath("fab"));
        JsonCourseAddressBookStorage courseAddressBookStorage =
            new JsonCourseAddressBookStorage(getTempFilePath("cab"));
        JsonAssignmentAddressBookStorage assignmentAddressBookStorage =
                new JsonAssignmentAddressBookStorage(getTempFilePath("zab"));


        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, teacherAddressBookStorage, staffAddressBookStorage,
            studentAddressBookStorage, financeAddressBookStorage, courseAddressBookStorage, assignmentAddressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        AddressBook original = getTypicalAddressBook();
        storageManager.saveAddressBook(original);
        ReadOnlyAddressBook retrieved = storageManager.readAddressBook().get();
        assertEquals(original, new AddressBook(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getAddressBookFilePath());
    }

}
