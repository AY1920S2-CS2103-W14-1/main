package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelProgress.Progress;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStudent.Student;
import seedu.address.storage.storageAssignments.AssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.CourseAddressBookStorage;
import seedu.address.storage.storageFinance.FinanceAddressBookStorage;
import seedu.address.storage.storageProgress.ProgressAddressBookStorage;
import seedu.address.storage.storageStaff.StaffAddressBookStorage;
import seedu.address.storage.storageStudent.StudentAddressBookStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, StaffAddressBookStorage,
        StudentAddressBookStorage,
        FinanceAddressBookStorage, CourseAddressBookStorage, AssignmentAddressBookStorage,
        ProgressAddressBookStorage,
        UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    ///
    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;


    ///
    @Override
    Path getStaffAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook) throws IOException;

    ///
    @Override
    Path getStudentAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Student>> readStudentAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook) throws IOException;

    ///
    @Override
    Path getFinanceAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook) throws IOException;

    ///
    @Override
    Path getCourseAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook) throws IOException;

    ///
    @Override
    Path getAssignmentAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook) throws IOException;

    ///
    @Override
    Path getProgressAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBookGeneric<Progress>> readProgressAddressBook()
            throws DataConversionException, IOException;

    @Override
    void saveProgressAddressBook(ReadOnlyAddressBookGeneric<Progress> progressAddressBook) throws IOException;


}
