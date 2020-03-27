package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.storage.storageAssignments.AssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.CourseAddressBookStorage;
import seedu.address.storage.storageFinance.FinanceAddressBookStorage;
import seedu.address.storage.storageStaff.StaffAddressBookStorage;
import seedu.address.storage.storageStudent.StudentAddressBookStorage;
import seedu.address.storage.storageTeacher.TeacherAddressBookStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

  private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
  private AddressBookStorage addressBookStorage;
  private TeacherAddressBookStorage teacherAddressBookStorage;
  private StaffAddressBookStorage staffAddressBookStorage;
  private StudentAddressBookStorage studentAddressBookStorage;
  private FinanceAddressBookStorage financeAddressBookStorage;
  private CourseAddressBookStorage courseAddressBookStorage;
  private AssignmentAddressBookStorage assignmentAddressBookStorage;

  private UserPrefsStorage userPrefsStorage;


  public StorageManager(AddressBookStorage addressBookStorage,
      TeacherAddressBookStorage teacherAddressBookStorage,
      StaffAddressBookStorage staffAddressBookStorage,
      StudentAddressBookStorage studentAddressBookStorage,
      FinanceAddressBookStorage financeAddressBookStorage,
      CourseAddressBookStorage courseAddressBookStorage,
      AssignmentAddressBookStorage assignmentAddressBookStorage,
                        UserPrefsStorage userPrefsStorage) {

    super();
    this.addressBookStorage = addressBookStorage;
    this.teacherAddressBookStorage = teacherAddressBookStorage;
    this.staffAddressBookStorage = staffAddressBookStorage;
    this.studentAddressBookStorage = studentAddressBookStorage;
    this.financeAddressBookStorage = financeAddressBookStorage;
    this.courseAddressBookStorage = courseAddressBookStorage;
    this.assignmentAddressBookStorage = assignmentAddressBookStorage;
    this.userPrefsStorage = userPrefsStorage;
  }

  // ================ UserPrefs methods ==============================

  @Override
  public Path getUserPrefsFilePath() {
    return userPrefsStorage.getUserPrefsFilePath();
  }

  @Override
  public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
    return userPrefsStorage.readUserPrefs();
  }

  @Override
  public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
    userPrefsStorage.saveUserPrefs(userPrefs);
  }

  // ================ AddressBook methods ==============================

  @Override
  public Path getAddressBookFilePath() {
    return addressBookStorage.getAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBook> readAddressBook()
      throws DataConversionException, IOException {
    return readAddressBook(addressBookStorage.getAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath)
      throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return addressBookStorage.readAddressBook(filePath);
  }

  @Override
  public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
    saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
  }

  @Override
  public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    addressBookStorage.saveAddressBook(addressBook, filePath);
  }

  // ================ TeacherAddressBook methods ==============================

  @Override
  public Path getTeacherAddressBookFilePath() {
    return teacherAddressBookStorage.getTeacherAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Teacher>> readTeacherAddressBook()
      throws DataConversionException, IOException {
    return readTeacherAddressBook(teacherAddressBookStorage.getTeacherAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Teacher>> readTeacherAddressBook(Path filePath)
      throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return teacherAddressBookStorage.readTeacherAddressBook(filePath);
  }

  @Override
  public void saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook)
      throws IOException {
    saveTeacherAddressBook(teacherAddressBook,
        teacherAddressBookStorage.getTeacherAddressBookFilePath());
  }

  @Override
  public void saveTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook, Path filePath)
      throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    teacherAddressBookStorage.saveTeacherAddressBook(teacherAddressBook, filePath);
  }

  // ================ StaffAddressBook methods ==============================

  @Override
  public Path getStaffAddressBookFilePath() {
    return staffAddressBookStorage.getStaffAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook()
          throws DataConversionException, IOException {
    return readStaffAddressBook(staffAddressBookStorage.getStaffAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Staff>> readStaffAddressBook(Path filePath)
          throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return staffAddressBookStorage.readStaffAddressBook(filePath);
  }

  @Override
  public void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook)
          throws IOException {
    saveStaffAddressBook(staffAddressBook,
            staffAddressBookStorage.getStaffAddressBookFilePath());
  }

  @Override
  public void saveStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook, Path filePath)
          throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    staffAddressBookStorage.saveStaffAddressBook(staffAddressBook, filePath);
  }

  // ================ StudentAddressBook methods ==============================

  @Override
  public Path getStudentAddressBookFilePath() {
    return studentAddressBookStorage.getStudentAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Student>> readStudentAddressBook()
      throws DataConversionException, IOException {
    return readStudentAddressBook(studentAddressBookStorage.getStudentAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Student>> readStudentAddressBook(Path filePath)
      throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return studentAddressBookStorage.readStudentAddressBook(filePath);
  }

  @Override
  public void saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook)
      throws IOException {
    saveStudentAddressBook(studentAddressBook,
        studentAddressBookStorage.getStudentAddressBookFilePath());
  }

  @Override
  public void saveStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook, Path filePath)
      throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    studentAddressBookStorage.saveStudentAddressBook(studentAddressBook, filePath);
  }

  // ================ FinanceAddressBook methods ==============================

  @Override
  public Path getFinanceAddressBookFilePath() {
    return financeAddressBookStorage.getFinanceAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook()
      throws DataConversionException, IOException {
    return readFinanceAddressBook(financeAddressBookStorage.getFinanceAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Finance>> readFinanceAddressBook(Path filePath)
      throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return financeAddressBookStorage.readFinanceAddressBook(filePath);
  }

  @Override
  public void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook)
      throws IOException {
    saveFinanceAddressBook(financeAddressBook,
        financeAddressBookStorage.getFinanceAddressBookFilePath());
  }

  @Override
  public void saveFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook, Path filePath)
      throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    financeAddressBookStorage.saveFinanceAddressBook(financeAddressBook, filePath);
  }

  // ================ CourseAddressBook methods ==============================

  @Override
  public Path getCourseAddressBookFilePath() {
    return courseAddressBookStorage.getCourseAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook()
      throws DataConversionException, IOException {
    return readCourseAddressBook(courseAddressBookStorage.getCourseAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Course>> readCourseAddressBook(Path filePath)
      throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return courseAddressBookStorage.readCourseAddressBook(filePath);
  }

  @Override
  public void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook)
      throws IOException {
    saveCourseAddressBook(courseAddressBook,
        courseAddressBookStorage.getCourseAddressBookFilePath());
  }

  @Override
  public void saveCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook, Path filePath)
      throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    courseAddressBookStorage.saveCourseAddressBook(courseAddressBook, filePath);
  }

  // ================ AssignmentAddressBook methods ==============================

  @Override
  public Path getAssignmentAddressBookFilePath() {
    return assignmentAddressBookStorage.getAssignmentAddressBookFilePath();
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook()
          throws DataConversionException, IOException {
    return readAssignmentAddressBook(assignmentAddressBookStorage.getAssignmentAddressBookFilePath());
  }

  @Override
  public Optional<ReadOnlyAddressBookGeneric<Assignment>> readAssignmentAddressBook(Path filePath)
          throws DataConversionException, IOException {
    logger.fine("Attempting to read data from file: " + filePath);
    return assignmentAddressBookStorage.readAssignmentAddressBook(filePath);
  }

  @Override
  public void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook)
          throws IOException {
    saveAssignmentAddressBook(assignmentAddressBook,
            assignmentAddressBookStorage.getAssignmentAddressBookFilePath());
  }

  @Override
  public void saveAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook, Path filePath)
          throws IOException {
    logger.fine("Attempting to write to data file: " + filePath);
    assignmentAddressBookStorage.saveAssignmentAddressBook(assignmentAddressBook, filePath);
  }
}
