package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelProgress.Progress;
import seedu.address.model.modelProgress.ProgressAddressBook;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;
import seedu.address.model.person.ID;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

  private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

  private final AddressBook addressBook;
  private final TeacherAddressBook teacherAddressBook;
  private final StudentAddressBook studentAddressBook;
  private final FinanceAddressBook financeAddressBook;
  private final CourseAddressBook courseAddressBook;
  private final AssignmentAddressBook assignmentAddressBook;
  private final ProgressAddressBook progressAddressBook;

  private final UserPrefs userPrefs;
  private final FilteredList<Person> filteredPersons;
  private final FilteredList<Teacher> filteredTeachers;
  private final FilteredList<Student> filteredStudents;
  private final FilteredList<Finance> filteredFinances;
  private final FilteredList<Course> filteredCourses;
  private final FilteredList<Assignment> filteredAssignments;
  private final FilteredList<Progress> filteredProgresses;


  /**
   * Initializes a ModelManager with the given addressBook and userPrefs.
   */
  public ModelManager(ReadOnlyAddressBook addressBook,
                      ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook, ReadOnlyAddressBookGeneric<Student> studentAddressBook,
                      ReadOnlyAddressBookGeneric<Finance> financeAddressBook, ReadOnlyAddressBookGeneric<Course> courseAddressBook,
                      ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook, ReadOnlyAddressBookGeneric<Progress> progressAssignmentBook,
                      ReadOnlyUserPrefs userPrefs) {
    super();
    requireAllNonNull(teacherAddressBook, studentAddressBook, financeAddressBook, courseAddressBook,
            assignmentAddressBook, progressAssignmentBook, userPrefs);

    logger.info("Model Manager check:" + assignmentAddressBook.toString());


    logger.info("Model Manager check:" + assignmentAddressBook.toString());


    logger.fine("Initializing with address book: " + studentAddressBook
            + "Initializing with teacher address book: " + teacherAddressBook
            + "Initializing with address address book: " + assignmentAddressBook
            + " and user prefs " + userPrefs);

    this.addressBook = new AddressBook(addressBook);
    this.teacherAddressBook = new TeacherAddressBook(teacherAddressBook);
    this.studentAddressBook = new StudentAddressBook(studentAddressBook);
    this.financeAddressBook = new FinanceAddressBook(financeAddressBook);
    this.courseAddressBook = new CourseAddressBook(courseAddressBook);
    this.assignmentAddressBook = new AssignmentAddressBook(assignmentAddressBook);
    this.progressAddressBook = new ProgressAddressBook(progressAssignmentBook);

    this.userPrefs = new UserPrefs(userPrefs);
    filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    filteredTeachers = new FilteredList<>(this.teacherAddressBook.getList());
    filteredStudents = new FilteredList<>(this.studentAddressBook.getList());
    filteredFinances = new FilteredList<>(this.financeAddressBook.getList());
    filteredCourses = new FilteredList<>(this.courseAddressBook.getList());
    filteredAssignments = new FilteredList<>(this.assignmentAddressBook.getList());
    filteredProgresses = new FilteredList<>(this.progressAddressBook.getList());

    for (Course course : filteredCourses) {
      course.processAssignedStudents(filteredStudents);
      course.processAssignedTeacher(filteredTeachers);
    }

    for (Student student : filteredStudents) {
      student.processAssignedCourses(filteredCourses);
    }

    for (Teacher teacher : filteredTeachers) {
      teacher.processAssignedCourses(filteredCourses);
    }


  }

  public ModelManager() {
    this(new AddressBook(), new TeacherAddressBook(), new StudentAddressBook(),
            new FinanceAddressBook(), new CourseAddressBook(),
            new AssignmentAddressBook(), new ProgressAddressBook(),
            new UserPrefs());
  }

  //=========== UserPrefs ==================================================================================

  @Override
  public ReadOnlyUserPrefs getUserPrefs() {
    return userPrefs;
  }

  @Override
  public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
    requireNonNull(userPrefs);
    this.userPrefs.resetData(userPrefs);
  }

  @Override
  public GuiSettings getGuiSettings() {
    return userPrefs.getGuiSettings();
  }

  @Override
  public void setGuiSettings(GuiSettings guiSettings) {
    requireNonNull(guiSettings);
    userPrefs.setGuiSettings(guiSettings);
  }

  @Override
  public Path getAddressBookFilePath() {
    return userPrefs.getAddressBookFilePath();
  }

  @Override
  public void setAddressBookFilePath(Path addressBookFilePath) {
    requireNonNull(addressBookFilePath);
    userPrefs.setAddressBookFilePath(addressBookFilePath);
  }

  @Override
  public Path getTeacherAddressBookFilePath() {
    return userPrefs.getTeacherAddressBookFilePath();
  }

  @Override
  public void setTeacherAddressBookFilePath(Path teacherAddressBookFilePath) {
    requireNonNull(teacherAddressBookFilePath);
    userPrefs.setTeacherAddressBookFilePath(teacherAddressBookFilePath);
  }

  @Override
  public Path getStudentAddressBookFilePath() {
    return userPrefs.getStudentAddressBookFilePath();
  }

  @Override
  public void setStudentAddressBookFilePath(Path studentAddressBookFilePath) {
    requireNonNull(studentAddressBookFilePath);
    userPrefs.setStudentAddressBookFilePath(studentAddressBookFilePath);
  }

  @Override
  public Path getFinanceAddressBookFilePath() {
    return userPrefs.getFinanceAddressBookFilePath();
  }

  @Override
  public void setFinanceAddressBookFilePath(Path financeAddressBookFilePath) {
    requireNonNull(financeAddressBookFilePath);
    userPrefs.setFinanceAddressBookFilePath(financeAddressBookFilePath);
  }

  @Override
  public Path getCourseAddressBookFilePath() {
    return userPrefs.getCourseAddressBookFilePath();
  }

  @Override
  public void setCourseAddressBookFilePath(Path courseAddressBookFilePath) {
    requireNonNull(courseAddressBookFilePath);
    userPrefs.setCourseAddressBookFilePath(courseAddressBookFilePath);
  }

  @Override
  public Path getAssignmentAddressBookFilePath() {
    return userPrefs.getAssignmentAddressBookFilePath();
  }

  @Override
  public void setAssignmentAddressBookFilePath(Path assignmentAddressBookFilePath) {
    requireNonNull(assignmentAddressBookFilePath);
    userPrefs.setAssignmentAddressBookFilePath(assignmentAddressBookFilePath);
  }

  /**
   * Returns the user prefs' assignment address book file path.
   */
  @Override
  public Path getProgressAddressBookFilePath() {
    return userPrefs.getProgressAddressBookFilePath();
  }

  /**
   * Sets the user prefs' address book file path.
   *
   * @param progressAddressBookFilePath
   */
  @Override
  public void setProgressAddressBookFilePath(Path progressAddressBookFilePath) {
    requireNonNull(progressAddressBookFilePath);
    userPrefs.setProgressAddressBookFilePath(progressAddressBookFilePath);
  }

  @Override
  public ReadOnlyAddressBook getAddressBook() {
    return addressBook;
  }

  //=========== AddressBook ================================================================================
  ///


  // ================================== FACTORY HELPERS =================================================
  private List<Object> getEntityFactory(ModelObject obj) throws CommandException {
    if (obj instanceof Teacher) {
      return Arrays.asList(
              this.teacherAddressBook,
              PREDICATE_SHOW_ALL_TEACHERS,
              filteredTeachers);
    } else if (obj instanceof Student) {
      return Arrays.asList(
              this.studentAddressBook,
              PREDICATE_SHOW_ALL_STUDENTS,
              filteredStudents);
    } else if (obj instanceof Finance) {
      return Arrays.asList(
              this.financeAddressBook,
              PREDICATE_SHOW_ALL_FINANCES,
              filteredFinances);
    } else if (obj instanceof Course) {
      return Arrays.asList(
              this.courseAddressBook,
              PREDICATE_SHOW_ALL_COURSES,
              filteredCourses);
    } else if (obj instanceof Assignment) {
      return Arrays.asList(
              this.assignmentAddressBook,
              PREDICATE_SHOW_ALL_ASSIGNMENTS,
              filteredAssignments);
    }
    throw new CommandException("This command is accessing non-existent entity or entity not extending from ModelObject");
  }

  private AddressBookGeneric getAddressBook(ModelObject obj) throws CommandException {
    return (AddressBookGeneric)getEntityFactory(obj).get(0);
  }

  private Predicate getPredicateAll(ModelObject obj) throws CommandException {
    return (Predicate)getEntityFactory(obj).get(1);
  }

  private FilteredList getFilterList(ModelObject obj) throws CommandException {
    return (FilteredList)getEntityFactory(obj).get(2);
  }
  // ======================================================================================================


  // =================================== CRUD METHODS =====================================================
  @Override
  public boolean has(ModelObject obj) throws CommandException {
    requireNonNull(obj);
    return getAddressBook(obj).has(obj);
  }

  @Override
  public void delete(ModelObject obj) throws CommandException {
    getAddressBook(obj).remove(obj);
    getFilterList(obj).setPredicate(getPredicateAll(obj));
  }

  @Override
  public void add(ModelObject obj) throws CommandException {
    getAddressBook(obj).add(obj);
    getFilterList(obj).setPredicate(getPredicateAll(obj));
  }

  @Override
  public void set(ModelObject target, ModelObject editedTarget) throws CommandException {
    requireAllNonNull(target, editedTarget);
    getAddressBook(target).set(target, editedTarget);
  }

  // =========================== CRUD METHODS DONE VIA ID =====================================================

  @Override
  public boolean hasStudent(ID studentID) {
    return studentAddressBook.has(studentID);
  }

  @Override
  public Student getStudent(ID studentID) {
    return studentAddressBook.get(studentID);
  }

  @Override
  public boolean hasCourse(ID courseID) {
    return courseAddressBook.has(courseID);
  }

  @Override
  public Course getCourse(ID courseID) {
    return courseAddressBook.get(courseID);
  }

  @Override
  public boolean hasAssignment(ID assignmentID) {
    return false;
  }

  @Override
  public Assignment getAssignment(ID assignmentID) {
    return null;
  }
  // =====================================================================================================


  ///
  @Override
  public ReadOnlyAddressBookGeneric<Teacher> getTeacherAddressBook() {
    return teacherAddressBook;
  }


  @Override
  public void setTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook) {
    this.teacherAddressBook.resetData(teacherAddressBook);
  }

  ///
  @Override
  public ReadOnlyAddressBookGeneric<Student> getStudentAddressBook() {
    return studentAddressBook;
  }


  @Override
  public void setStudentAddressBook(ReadOnlyAddressBookGeneric<Student> studentAddressBook) {
    this.studentAddressBook.resetData(studentAddressBook);
  }

  ///
  @Override
  public ReadOnlyAddressBookGeneric<Finance> getFinanceAddressBook() {
    return financeAddressBook;
  }


  @Override
  public void setFinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> financeAddressBook) {
    this.financeAddressBook.resetData(financeAddressBook);
  }

  ///
  @Override
  public ReadOnlyAddressBookGeneric<Course> getCourseAddressBook() {
    return courseAddressBook;
  }


  @Override
  public void setCourseAddressBook(ReadOnlyAddressBookGeneric<Course> courseAddressBook) {
    this.courseAddressBook.resetData(courseAddressBook);
  }

  ///
  @Override
  public ReadOnlyAddressBookGeneric<Assignment> getAssignmentAddressBook() {
    return assignmentAddressBook;
  }

  @Override
  public void setAssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook) {
    this.assignmentAddressBook.resetData(assignmentAddressBook);
  }

  @Override
  public void setProgressAddressBook(ReadOnlyAddressBookGeneric<Progress> progressAddressBook) {
    this.progressAddressBook.resetData(progressAddressBook);
  }

  @Override
  public ReadOnlyAddressBookGeneric<Progress> getProgressAddressBook() {
    return progressAddressBook;
  }


  //=========== Filtered List Accessors =============================================================

  /**
   * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
   * {@code versionedAddressBook}
   */
  @Override
  public ObservableList<Person> getFilteredPersonList() {
    return filteredPersons;
  }

  @Override
  public void updateFilteredPersonList(Predicate<Person> predicate) {
    requireNonNull(predicate);
    filteredPersons.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the list of {@code Teacher} backed by the internal list of
   * {@code versionedTeacherAddressBook}
   */
  @Override
  public ObservableList<Teacher> getFilteredTeacherList() {
    return filteredTeachers;
  }

  @Override
  public void updateFilteredTeacherList(Predicate<Teacher> predicate) {
    requireNonNull(predicate);
    filteredTeachers.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
   * {@code versionedStudentAddressBook}
   */
  @Override
  public ObservableList<Student> getFilteredStudentList() {
    return filteredStudents;
  }

  @Override
  public void updateFilteredStudentList(Predicate<Student> predicate) {
    requireNonNull(predicate);
    filteredStudents.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the list of {@code Finance} backed by the internal list of
   * {@code versionedFinanceAddressBook}
   */
  @Override
  public ObservableList<Finance> getFilteredFinanceList() {
    return filteredFinances;
  }

  @Override
  public void updateFilteredFinanceList(Predicate<Finance> predicate) {
    requireNonNull(predicate);
    filteredFinances.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the list of {@code Course} backed by the internal list of
   * {@code versionedCourseAddressBook}
   */
  @Override
  public ObservableList<Course> getFilteredCourseList() {
    return filteredCourses;
  }

  @Override
  public void updateFilteredCourseList(Predicate<Course> predicate) {
    requireNonNull(predicate);
    filteredCourses.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the list of {@code Assignment} backed by the internal list of
   * {@code versionedAssignmentAddressBook}
   */
  @Override
  public ObservableList<Assignment> getFilteredAssignmentList() {
    return filteredAssignments;
  }

  @Override
  public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
    requireNonNull(predicate);
    filteredAssignments.setPredicate(predicate);
  }

  /**
   * Returns an unmodifiable view of the filtered finance list
   */
  @Override
  public ObservableList<Progress> getFilteredProgressList() {
    return filteredProgresses;
  }

  /**
   * Updates the filter of the filtered assignment list to filter by the given {@code predicate}.
   *
   * @param predicate
   * @throws NullPointerException if {@code predicate} is null.
   */
  @Override
  public void updateFilteredProgressList(Predicate<Progress> predicate) {
    requireNonNull(predicate);
    filteredProgresses.setPredicate(predicate);
  }

  // ========================== For Assigning of X to Y =========================

  public void assignStudentToCourse(ID studentID, ID courseID) {
    // if student exists
    // if course exists
    // if student not already assigned to the course
    // if course doesn't already have the student
  }

  @Override
  public boolean equals(Object obj) {
    // short circuit if same object
    if (obj == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(obj instanceof ModelManager)) {
      return false;
    }

    //
    ModelManager other = (ModelManager) obj;
    return userPrefs.equals(other.userPrefs)
            && teacherAddressBook.equals(other.teacherAddressBook)
            && studentAddressBook.equals(other.studentAddressBook)
            && courseAddressBook.equals(other.courseAddressBook)
            && financeAddressBook.equals(other.financeAddressBook)
            && assignmentAddressBook.equals(other.assignmentAddressBook)
            && progressAddressBook.equals(other.progressAddressBook)
            && filteredTeachers.equals(other.filteredTeachers)
            && filteredStudents.equals(other.filteredStudents)
            && filteredCourses.equals(other.filteredCourses)
            && filteredFinances.equals(other.filteredFinances)
            && filteredAssignments.equals(other.filteredAssignments)
            && filteredProgresses.equals(other.filteredProgresses);


  }
}
