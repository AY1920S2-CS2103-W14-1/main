package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelAssignment.ReadOnlyAssignmentAddressBook;
import seedu.address.model.modelCourse.ReadOnlyCourseAddressBook;
import seedu.address.model.modelCourseStudent.ReadOnlyCourseStudentAddressBook;
import seedu.address.model.modelFinance.ReadOnlyFinanceAddressBook;
import seedu.address.model.modelStaff.ReadOnlyStaffAddressBook;
import seedu.address.model.modelStudent.ReadOnlyStudentAddressBook;
import seedu.address.model.modelTeacher.ReadOnlyTeacherAddressBook;
import seedu.address.storage.storageAssignments.AssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.CourseAddressBookStorage;
import seedu.address.storage.storageCourseStudent.CourseStudentAddressBookStorage;
import seedu.address.storage.storageFinance.FinanceAddressBookStorage;
import seedu.address.storage.storageStudent.StudentAddressBookStorage;
import seedu.address.storage.storageTeacher.TeacherAddressBookStorage;
import seedu.address.storage.storageStaff.StaffAddressBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TeacherAddressBookStorage,
    StaffAddressBookStorage,
    StudentAddressBookStorage,
    FinanceAddressBookStorage, CourseAddressBookStorage, AssignmentAddressBookStorage,
    CourseStudentAddressBookStorage,
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
  Path getTeacherAddressBookFilePath();

  @Override
  Optional<ReadOnlyTeacherAddressBook> readTeacherAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveTeacherAddressBook(ReadOnlyTeacherAddressBook teacherAddressBook) throws IOException;

  ///
  @Override
  Path getStaffAddressBookFilePath();

  @Override
  Optional<ReadOnlyStaffAddressBook> readStaffAddressBook()
          throws DataConversionException, IOException;

  @Override
  void saveStaffAddressBook(ReadOnlyStaffAddressBook staffAddressBook) throws IOException;

  ///
  @Override
  Path getStudentAddressBookFilePath();

  @Override
  Optional<ReadOnlyStudentAddressBook> readStudentAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveStudentAddressBook(ReadOnlyStudentAddressBook studentAddressBook) throws IOException;

  ///
  @Override
  Path getFinanceAddressBookFilePath();

  @Override
  Optional<ReadOnlyFinanceAddressBook> readFinanceAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveFinanceAddressBook(ReadOnlyFinanceAddressBook financeAddressBook) throws IOException;

  ///
  @Override
  Path getCourseAddressBookFilePath();

  @Override
  Optional<ReadOnlyCourseAddressBook> readCourseAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveCourseAddressBook(ReadOnlyCourseAddressBook courseAddressBook) throws IOException;

  ///
  @Override
  Path getAssignmentAddressBookFilePath();

  @Override
  Optional<ReadOnlyAssignmentAddressBook> readAssignmentAddressBook()
          throws DataConversionException, IOException;

  @Override
  void saveAssignmentAddressBook(ReadOnlyAssignmentAddressBook assignmentAddressBook) throws IOException;

  ///
  @Override
  Path getCourseStudentAddressBookFilePath();

  @Override
  Optional<ReadOnlyCourseStudentAddressBook> readCourseStudentAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveCourseStudentAddressBook(ReadOnlyCourseStudentAddressBook courseStudentAddressBook) throws IOException;

}
