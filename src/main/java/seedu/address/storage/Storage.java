package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.storage.storageAssignments.AssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.CourseAddressBookStorage;
import seedu.address.storage.storageCourseStudent.CourseStudentAddressBookStorage;
import seedu.address.storage.storageFinance.FinanceAddressBookStorage;
import seedu.address.storage.storageStudent.StudentAddressBookStorage;
import seedu.address.storage.storageTeacher.TeacherAddressBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, TeacherAddressBookStorage,
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
  Optional<ReadOnlyAddressBookGeneric<Teacher>> readTeacherAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook) throws IOException;

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
  Path getCourseStudentAddressBookFilePath();

  @Override
  Optional<ReadOnlyAddressBookGeneric<CourseStudent>> readCourseStudentAddressBook()
      throws DataConversionException, IOException;

  @Override
  void saveCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook) throws IOException;

}
