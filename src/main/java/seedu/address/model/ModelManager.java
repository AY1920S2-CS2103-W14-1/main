package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;
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

  private final UserPrefs userPrefs;
  private final FilteredList<Person> filteredPersons;
  private final FilteredList<Teacher> filteredTeachers;
  private final FilteredList<Student> filteredStudents;
  private final FilteredList<Finance> filteredFinances;
  private final FilteredList<Course> filteredCourses;
  private final FilteredList<Assignment> filteredAssignments;

  /**
   * Initializes a ModelManager with the given addressBook and userPrefs.
   */
  public ModelManager(ReadOnlyAddressBook addressBook,
                      ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook, ReadOnlyAddressBookGeneric<Student> studentAddressBook,
                      ReadOnlyAddressBookGeneric<Finance> financeAddressBook, ReadOnlyAddressBookGeneric<Course> courseAddressBook,
                      ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook,
                      ReadOnlyUserPrefs userPrefs) {
    super();
    requireAllNonNull(teacherAddressBook, studentAddressBook, financeAddressBook, courseAddressBook,
             assignmentAddressBook, userPrefs);

    logger.fine("Initializing with address book: " + studentAddressBook
        + "Initializing with  teacher address book: " + teacherAddressBook + " and user prefs "
        + userPrefs);

    this.addressBook = new AddressBook(addressBook);
    this.teacherAddressBook = new TeacherAddressBook(teacherAddressBook);
    this.studentAddressBook = new StudentAddressBook(studentAddressBook);
    this.financeAddressBook = new FinanceAddressBook(financeAddressBook);
    this.courseAddressBook = new CourseAddressBook(courseAddressBook);
    this.assignmentAddressBook = new AssignmentAddressBook(assignmentAddressBook);

    this.userPrefs = new UserPrefs(userPrefs);
    filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    filteredTeachers = new FilteredList<>(this.teacherAddressBook.getList());
    filteredStudents = new FilteredList<>(this.studentAddressBook.getList());
    filteredFinances = new FilteredList<>(this.financeAddressBook.getList());
    filteredCourses = new FilteredList<>(this.courseAddressBook.getList());
    filteredAssignments = new FilteredList<>(this.assignmentAddressBook.getList());

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
            new AssignmentAddressBook(),
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

  @Override
  public ReadOnlyAddressBook getAddressBook() {
    return addressBook;
  }

  //=========== AddressBook ================================================================================
  ///
  @Override
  public void setAddressBook(ReadOnlyAddressBook addressBook) {
    this.addressBook.resetData(addressBook);
  }

  @Override
  public boolean hasPerson(Person person) {
    requireNonNull(person);
    return addressBook.hasPerson(person);
  }

  @Override
  public void deletePerson(Person target) {
    addressBook.removePerson(target);
  }

  @Override
  public void addPerson(Person person) {
    addressBook.addPerson(person);
    updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
  }

  @Override
  public void setPerson(Person target, Person editedPerson) {
    requireAllNonNull(target, editedPerson);

    addressBook.setPerson(target, editedPerson);
  }

  ///
  @Override
  public ReadOnlyAddressBookGeneric<Teacher> getTeacherAddressBook() {
    return teacherAddressBook;
  }


  @Override
  public void setTeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> teacherAddressBook) {
    this.teacherAddressBook.resetData(teacherAddressBook);
  }

  @Override
  public boolean hasTeacher(Teacher teacher) {
    requireNonNull(teacher);
    return teacherAddressBook.has(teacher);
  }

  @Override
  public void deleteTeacher(Teacher target) {
    teacherAddressBook.remove(target);
  }

  @Override
  public void addTeacher(Teacher teacher) {
    teacherAddressBook.add(teacher);
    updateFilteredTeacherList(PREDICATE_SHOW_ALL_TEACHERS);
  }
  @Override
  public void setTeacher(Teacher target, Teacher editedTeacher) {
    requireAllNonNull(target, editedTeacher);

    teacherAddressBook.set(target, editedTeacher);
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

  @Override
  public boolean hasStudent(Student student) {
    requireNonNull(student);
    return studentAddressBook.has(student);
  }

  @Override
  public void deleteStudent(Student target) {
    studentAddressBook.remove(target);
  }

  @Override
  public void addStudent(Student student) {
    studentAddressBook.add(student);
    updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
  }

  @Override
  public void setStudent(Student target, Student editedStudent) {
    requireAllNonNull(target, editedStudent);

    studentAddressBook.set(target, editedStudent);
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

  @Override
  public boolean hasFinance(Finance finance) {
    requireNonNull(finance);
    return financeAddressBook.has(finance);
  }

  @Override
  public void deleteFinance(Finance target) {
    financeAddressBook.remove(target);
  }

  @Override
  public void addFinance(Finance finance) {
    financeAddressBook.add(finance);
    updateFilteredFinanceList(PREDICATE_SHOW_ALL_FINANCES);
  }

  @Override
  public void setFinance(Finance target, Finance editedFinance) {
    requireAllNonNull(target, editedFinance);

    financeAddressBook.set(target, editedFinance);
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

  @Override
  public boolean hasCourse(Course course) {
    requireNonNull(course);
    return courseAddressBook.has(course);
  }

  @Override
  public void deleteCourse(Course target) {
    courseAddressBook.remove(target);
  }

  @Override
  public void addCourse(Course course) {
    courseAddressBook.add(course);
    updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
  }

  @Override
  public void setCourse(Course target, Course editedCourse) {
    requireAllNonNull(target, editedCourse);
    courseAddressBook.set(target, editedCourse);
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


  //TODO
  @Override
  public boolean hasAssignment(Assignment assignment) {
    return false;
  }

  @Override
  public void deleteAssignment(Assignment assignment) {

  }

  @Override
  public void addAssignment(Assignment assignment) {
    requireNonNull(assignment);
    assignmentAddressBook.add(assignment);
    updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENTS);
  }

  @Override
  public void setAssignment(Assignment target, Assignment editedAssignment) {

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

    // state check
    ModelManager other = (ModelManager) obj;
    return userPrefs.equals(other.userPrefs)
        && teacherAddressBook.equals(other.teacherAddressBook)
        && studentAddressBook.equals(other.studentAddressBook)
        && courseAddressBook.equals(other.courseAddressBook)
        && financeAddressBook.equals(other.financeAddressBook)
        && assignmentAddressBook.equals(other.assignmentAddressBook)
        && filteredTeachers.equals(other.filteredTeachers)
        && filteredStudents.equals(other.filteredStudents)
        && filteredCourses.equals(other.filteredCourses)
        && filteredFinances.equals(other.filteredFinances)
        && filteredAssignments.equals(other.filteredAssignments);

  }

  private class NameIdTuple implements Comparable<NameIdTuple>{
    private String name;
    private String id;
    NameIdTuple(String name, String id){
      this.name = name;
      this.id = id;
    }

    public String getName(){
      return this.name;
    }

    public String getId(){
      return this.id;
    }

    @Override
    public int compareTo(NameIdTuple o) {
      return Integer.parseInt(id) - (Integer.parseInt(o.getId()));
    }

    @Override
    public String toString(){
      return getName() + "(" + getId() + ")";
    }
  }
}

