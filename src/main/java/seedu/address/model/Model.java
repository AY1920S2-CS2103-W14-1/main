package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {

  /**
   * {@code Predicate} that always evaluate to true
   */
  Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
  Predicate<Teacher> PREDICATE_SHOW_ALL_TEACHERS = unused -> true;
  Predicate<Staff> PREDICATE_SHOW_ALL_STAFFS = unused -> true;
  Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;
  Predicate<Finance> PREDICATE_SHOW_ALL_FINANCES = unused -> true;
  Predicate<Course> PREDICATE_SHOW_ALL_COURSES = unused -> true;
  Predicate<Assignment> PREDICATE_SHOW_ALL_ASSIGNMENTS = unused -> true;

  /**
   * Returns the user prefs.
   */
  ReadOnlyUserPrefs getUserPrefs();

  /**
   * Replaces user prefs data with the data in {@code userPrefs}.
   */
  void setUserPrefs(ReadOnlyUserPrefs userPrefs);

  /**
   * Returns the user prefs' GUI settings.
   */
  GuiSettings getGuiSettings();

  /**
   * Sets the user prefs' GUI settings.
   */
  void setGuiSettings(GuiSettings guiSettings);

  /**
   * Returns the user prefs' address book file path.
   */
  Path getAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setAddressBookFilePath(Path addressBookFilePath);

  /**
   * Returns the AddressBook
   */
  ReadOnlyAddressBook getAddressBook();

  /**
   * Replaces address book data with the data in {@code addressBook}.
   */
  void setAddressBook(ReadOnlyAddressBook addressBook);

  /**
   * Returns true if a person with the same identity as {@code person} exists in the address book.
   */
  boolean hasPerson(Person person);

  /**
   * Deletes the given person. The person must exist in the address book.
   */
  void deletePerson(Person target);

  /**
   * Adds the given person. {@code person} must not already exist in the address book.
   */
  void addPerson(Person person);

  /**
   * Replaces the given person {@code target} with {@code editedPerson}. {@code target} must exist
   * in the address book. The person identity of {@code editedPerson} must not be the same as
   * another existing person in the address book.
   */
  void setPerson(Person target, Person editedPerson);

  /**
   * Returns an unmodifiable view of the filtered person list
   */
  ObservableList<Person> getFilteredPersonList();

  /**
   * Updates the filter of the filtered person list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredPersonList(Predicate<Person> predicate);

  ///

  /**
   * Returns the user prefs' address book file path.
   */
  Path getTeacherAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setTeacherAddressBookFilePath(Path teacherAddressBookFilePath);

  /**
   * Returns the teacherAddressBook
   */
  ReadOnlyAddressBookGeneric<Teacher> getTeacherAddressBook();

  /**
   * Replaces teacher address book data with the data in {@code teacerAddressBook}.
   */
  void setTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook);

  /**
   * Returns true if a teacher with the same identity as {@code teacher} exists in the address
   * book.
   */
  boolean hasTeacher(Teacher teacher);

  /**
   * Deletes the given teacher. The teacher must exist in the address book.
   */
  void deleteTeacher(Teacher target);

  /**
   * Adds the given teacher. {@code teacher} must not already exist in the address book.
   */
  void addTeacher(Teacher teacher);

  /**
   * Replaces the given teacher {@code target} with {@code editedTeacher}. {@code target} must exist
   * in the address book. The teacher identity of {@code editedTeacher} must not be the same as
   * another existing teacher in the address book.
   */
  void setTeacher(Teacher target, Teacher editedTeacher);

  /**
   * Returns an unmodifiable view of the filtered teacher list
   */
  ObservableList<Teacher> getFilteredTeacherList();

  /**
   * Updates the filter of the filtered teacher list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredTeacherList(Predicate<Teacher> predicate);

  // For Staff

    /**
     * Returns the user prefs' address book file path.
     */
    Path getStaffAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setStaffAddressBookFilePath(Path teacherAddressBookFilePath);

    /**
     * Returns the teacherAddressBook
     */
    ReadOnlyAddressBookGeneric<Staff> getStaffAddressBook();

    /**
     * Replaces teacher address book data with the data in {@code teacerAddressBook}.
     */
    void setStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook);

    /**
     * Returns true if a teacher with the same identity as {@code teacher} exists in the address
     * book.
     */
    boolean hasStaff(Staff staff);

    /**
     * Deletes the given teacher. The teacher must exist in the address book.
     */
    void deleteStaff(Staff staff);

    /**
     * Adds the given staff. {@code staff} must not already exist in the address book.
     */
    void addStaff(Staff staff);

    /**
     * Replaces the given staff {@code target} with {@code editedStaff}. {@code target} must exist
     * in the address book. The teacher identity of {@code editedStaff} must not be the same as
     * another existing staff in the address book.
     */
    void setStaff(Staff target, Staff editedStaff);

    /**
     * Returns an unmodifiable view of the filtered staff list
     */
    ObservableList<Staff> getFilteredStaffList();

    /**
     * Updates the filter of the filtered teacher list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStaffList(Predicate<Staff> predicate);

    // Student

  /**
   * Returns the user prefs' address book file path.
   */
  Path getStudentAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setStudentAddressBookFilePath(Path studentAddressBookFilePath);

  /**
   * Returns the studentAddressBook
   */
  ReadOnlyAddressBookGeneric<Student> getStudentAddressBook();

  /**
   * Replaces student address book data with the data in {@code teacherAddressBook}.
   */
  void setStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook);

  /**
   * Returns true if a student with the same identity as {@code student} exists in the address
   * book.
   */
  boolean hasStudent(Student student);

  /**
   * Deletes the given student. The student must exist in the address book.
   */
  void deleteStudent(Student target);

  /**
   * Adds the given student. {@code student} must not already exist in the address book.
   */
  void addStudent(Student student);

  /**
   * Replaces the given student {@code target} with {@code editedStudent}. {@code target} must exist
   * in the address book. The student identity of {@code editedStudent} must not be the same as
   * another existing student in the address book.
   */
  void setStudent(Student target, Student editedStudent);

  /**
   * Returns an unmodifiable view of the filtered student list
   */
  ObservableList<Student> getFilteredStudentList();

  /**
   * Updates the filter of the filtered student list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredStudentList(Predicate<Student> predicate);

  ///

  /**
   * Returns the user prefs' address book file path.
   */
  Path getCourseAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setCourseAddressBookFilePath(Path courseAddressBookFilePath);

  /**
   * Returns the courseAddressBook
   */
 ReadOnlyAddressBookGeneric<Course> getCourseAddressBook();

  /**
   * Replaces course address book data with the data in {@code teacerAddressBook}.
   */
  void setCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook);

  /**
   * Returns true if a course with the same identity as {@code course} exists in the address book.
   */
  boolean hasCourse(Course course);

  /**
   * Deletes the given course. The course must exist in the address book.
   */
  void deleteCourse(Course target);

  /**
   * Adds the given course. {@code course} must not already exist in the address book.
   */
  void addCourse(Course course);

  /**
   * Replaces the given course {@code target} with {@code editedCourse}. {@code target} must exist
   * in the address book. The course identity of {@code editedCourse} must not be the same as
   * another existing course in the address book.
   */
  void setCourse(Course target, Course editedCourse);

  /**
   * Returns an unmodifiable view of the filtered course list
   */
  ObservableList<Course> getFilteredCourseList();

  /**
   * Updates the filter of the filtered course list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredCourseList(Predicate<Course> predicate);

  ///

  /**
   * Returns the user prefs' address book file path.
   */
  Path getFinanceAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setFinanceAddressBookFilePath(Path financeAddressBookFilePath);

  /**
   * Returns the financeAddressBook
   */
  ReadOnlyAddressBookGeneric<Finance> getFinanceAddressBook();

  /**
   * Replaces finance address book data with the data in {@code teacerAddressBook}.
   */
  void setFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook);

  /**
   * Returns true if a finance with the same identity as {@code finance} exists in the address
   * book.
   */
  boolean hasFinance(Finance finance);

  /**
   * Deletes the given finance. The finance must exist in the address book.
   */
  void deleteFinance(Finance target);

  /**
   * Adds the given finance. {@code finance} must not already exist in the address book.
   */
  void addFinance(Finance finance);

  /**
   * Replaces the given finance {@code target} with {@code editedFinance}. {@code target} must exist
   * in the address book. The finance identity of {@code editedFinance} must not be the same as
   * another existing finance in the address book.
   */
  void setFinance(Finance target, Finance editedFinance);

  /**
   * Returns an unmodifiable view of the filtered finance list
   */
  ObservableList<Finance> getFilteredFinanceList();

  /**
   * Updates the filter of the filtered finance list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredFinanceList(Predicate<Finance> predicate);

  ///

  /**
   * Returns the user prefs' assignment address book file path.
   */
  Path getAssignmentAddressBookFilePath();

  /**
   * Sets the user prefs' address book file path.
   */
  void setAssignmentAddressBookFilePath(Path assignmentAddressBookFilePath);

  /**
   * Returns the assignmentAddressBook
   */
  ReadOnlyAddressBookGeneric<Assignment> getAssignmentAddressBook();

  /**
   * Replaces assignment address book data with the data in {@code teacerAddressBook}.
   */
  void setAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook);

  /**
   * Returns true if a assignment with the same identity as {@code assignment} exists in the address
   * book.
   */
  boolean hasAssignment(Assignment assignment);

  /**
   * Deletes the given finance. The finance must exist in the address book.
   */
  void deleteAssignment(Assignment assignment);

  /**
   * Adds the given assignment. {@code assignment} must not already exist in the address book.
   */
  void addAssignment(Assignment assignment);

  /**
   * Replaces the given assignment {@code target} with {@code editedAssignment}. {@code target} must exist
   * in the address book. The assignment identity of {@code editedAssignment} must not be the same as
   * another existing finance in the address book.
   */
  void setAssignment(Assignment target, Assignment editedAssignment);

  /**
   * Returns an unmodifiable view of the filtered finance list
   */
  ObservableList<Assignment> getFilteredAssignmentList();

  /**
   * Updates the filter of the filtered assignment list to filter by the given {@code predicate}.
   *
   * @throws NullPointerException if {@code predicate} is null.
   */
  void updateFilteredAssignmentList(Predicate<Assignment> predicate);

}
