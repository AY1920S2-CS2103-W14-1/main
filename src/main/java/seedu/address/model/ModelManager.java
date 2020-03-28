package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
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
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelCourseStudent.CourseStudentAddressBook;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

  private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

  private final AddressBook addressBook;
  private final StaffAddressBook staffAddressBook;
  private final StudentAddressBook studentAddressBook;
  private final FinanceAddressBook financeAddressBook;
  private final CourseAddressBook courseAddressBook;
  private final AssignmentAddressBook assignmentAddressBook;
  private final CourseStudentAddressBook courseStudentAddressBook;

  private final UserPrefs userPrefs;
  private final FilteredList<Person> filteredPersons;
  private final FilteredList<Staff> filteredStaffs;
  private final FilteredList<Student> filteredStudents;
  private final FilteredList<Finance> filteredFinances;
  private final FilteredList<Course> filteredCourses;
  private final FilteredList<Assignment> filteredAssignments;
  private final FilteredList<CourseStudent> filteredCourseStudents;

  /**
   * Initializes a ModelManager with the given addressBook and userPrefs.
   */
  public ModelManager(ReadOnlyAddressBook addressBook,
                      ReadOnlyAddressBookGeneric<Staff> staffAddressBook, ReadOnlyAddressBookGeneric<Student> studentAddressBook,
                      ReadOnlyAddressBookGeneric<Finance> financeAddressBook, ReadOnlyAddressBookGeneric<Course> courseAddressBook,
                      ReadOnlyAddressBookGeneric<Assignment> assignmentAddressBook, ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook,
                      ReadOnlyUserPrefs userPrefs) {
    super();
    requireAllNonNull(staffAddressBook, studentAddressBook, financeAddressBook, courseAddressBook,
            courseStudentAddressBook, assignmentAddressBook, userPrefs);

    logger.fine("Initializing with address book: " + studentAddressBook
        + "Initializing with  staff address book: " + staffAddressBook + " and user prefs "
        + userPrefs);

    this.addressBook = new AddressBook(addressBook);
    this.staffAddressBook = new StaffAddressBook(staffAddressBook);
    this.studentAddressBook = new StudentAddressBook(studentAddressBook);
    this.financeAddressBook = new FinanceAddressBook(financeAddressBook);
    this.courseAddressBook = new CourseAddressBook(courseAddressBook);
    this.assignmentAddressBook = new AssignmentAddressBook(assignmentAddressBook);
    this.courseStudentAddressBook = new CourseStudentAddressBook(courseStudentAddressBook);

    this.userPrefs = new UserPrefs(userPrefs);
    filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
    filteredStaffs = new FilteredList<>(this.staffAddressBook.getList());
    filteredStudents = new FilteredList<>(this.studentAddressBook.getList());
    filteredFinances = new FilteredList<>(this.financeAddressBook.getList());
    filteredCourses = new FilteredList<>(this.courseAddressBook.getList());
    filteredAssignments = new FilteredList<>(this.assignmentAddressBook.getList());
    filteredCourseStudents = new FilteredList<>(this.courseStudentAddressBook.getList());

    updateCourseStudents();

  }

  public ModelManager() {
    this(new AddressBook(), new StaffAddressBook(), new StudentAddressBook(),
        new FinanceAddressBook(), new CourseAddressBook(),
            new AssignmentAddressBook(), new CourseStudentAddressBook(),
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
  public Path getStaffAddressBookFilePath() {
    return userPrefs.getStaffAddressBookFilePath();
  }

  @Override
  public void setStaffAddressBookFilePath(Path staffAddressBookFilePath) {
    requireNonNull(staffAddressBookFilePath);
    userPrefs.setStaffAddressBookFilePath(staffAddressBookFilePath);
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
  public Path getCourseStudentAddressBookFilePath() {
    return userPrefs.getAssignmentAddressBookFilePath();
  }

  @Override
  public void setCourseStudentAddressBookFilePath(Path courseStudentAddressBookFilePath) {
    requireNonNull(courseStudentAddressBookFilePath);
    userPrefs.setAssignmentAddressBookFilePath(courseStudentAddressBookFilePath);
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
  public ReadOnlyAddressBookGeneric<Staff> getStaffAddressBook() {
    return staffAddressBook;
  }


  @Override
  public void setStaffAddressBook(ReadOnlyAddressBookGeneric<Staff> staffAddressBook) {
    this.staffAddressBook.resetData(staffAddressBook);
  }

  @Override
  public boolean hasStaff(Staff staff) {
    requireNonNull(staff);
    return staffAddressBook.has(staff);
  }

  @Override
  public void deleteStaff(Staff target) {
    staffAddressBook.remove(target);
  }

  @Override
  public void addStaff(Staff staff) {
    staffAddressBook.add(staff);
    updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
  }
  @Override
  public void setStaff(Staff target, Staff editedStaff) {
    requireAllNonNull(target, editedStaff);

    staffAddressBook.set(target, editedStaff);
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
    updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
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

  ///
  @Override
  public ReadOnlyAddressBookGeneric<CourseStudent> getCourseStudentAddressBook() {
    return courseStudentAddressBook;
  }


  @Override
  public void setCourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> courseStudentAddressBook) {
    this.courseStudentAddressBook.resetData(courseStudentAddressBook);
  }

  @Override
  public boolean hasCourseStudent(CourseStudent courseStudent) {
    requireNonNull(courseStudent);
    return courseStudentAddressBook.has(courseStudent);
  }

  @Override
  public void deleteCourseStudent(CourseStudent target) {
    courseStudentAddressBook.remove(target);
  }

  @Override
  public void addCourseStudent(CourseStudent courseStudent) {
    courseStudentAddressBook.add(courseStudent);

    updateCourseStudents();

    updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
    updateFilteredCourseStudentList(PREDICATE_SHOW_ALL_COURSESTUDENTS);
  }
  @Override
  public void setCourseStudent(CourseStudent target, CourseStudent editedCourseStudent) {
    requireAllNonNull(target, editedCourseStudent);

    courseStudentAddressBook.set(target, editedCourseStudent);
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
   * Returns an unmodifiable view of the list of {@code Staff} backed by the internal list of
   * {@code versionedStaffAddressBook}
   */
  @Override
  public ObservableList<Staff> getFilteredStaffList() {
    return filteredStaffs;
  }

  @Override
  public void updateFilteredStaffList(Predicate<Staff> predicate) {
    requireNonNull(predicate);
    filteredStaffs.setPredicate(predicate);
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
   * Returns an unmodifiable view of the list of {@code CourseStudent} backed by the internal list of
   * {@code versionedCourseStudentAddressBook}
   */
  @Override
  public ObservableList<CourseStudent> getFilteredCourseStudentList() {
    return filteredCourseStudents;
  }

  @Override
  public void updateFilteredCourseStudentList(Predicate<CourseStudent> predicate) {
    requireNonNull(predicate);
    filteredCourseStudents.setPredicate(predicate);
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

  public void updateCourseStudents(){
    //Updates list of students for each course
    for (Course course : filteredCourses){
      String courseString = course.getId().toString();
      ArrayList<NameIdTuple> assignedStudents = new ArrayList<>();
      for (CourseStudent curCourseStudent : filteredCourseStudents){
        if (curCourseStudent.getCourseid().toString().equals(courseString)){
          String student = curCourseStudent.getStudentid().toString();
          for (Student curStudent : filteredStudents){
            if (curStudent.getID().toString().equals(student)){
              NameIdTuple t = new NameIdTuple(curStudent.getName().toString(), curCourseStudent.getStudentid().toString());
              assignedStudents.add(t);
            }
          }
        }
      }

      Collections.sort(assignedStudents);
      if (assignedStudents.size() == 0) {
        course.setAssignedStudents("None");
      }
      else {
        course.setAssignedStudents(assignedStudents.toString());
      }
    }

    //Updates list of courses for each student
    for (Student student : filteredStudents){
      String studentString = student.getID().toString();
      ArrayList<NameIdTuple> assignedCourses = new ArrayList<>();
      for (CourseStudent curCourseStudent : filteredCourseStudents){
        if (curCourseStudent.getStudentid().toString().equals(studentString)){
          String course = curCourseStudent.getCourseid().toString();
          for (Course curCourse : filteredCourses){
            if (curCourse.getId().toString().equals(course)){
              NameIdTuple t = new NameIdTuple(curCourse.getName().toString(), curCourseStudent.getCourseid().toString());
              assignedCourses.add(t);
            }
          }
        }
      }

      Collections.sort(assignedCourses);
      if (assignedCourses.size() == 0) {
        student.setAssignedCourses("None");
      }
      else {
        student.setAssignedCourses(assignedCourses.toString());
      }
    }
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
        && staffAddressBook.equals(other.staffAddressBook)
        && studentAddressBook.equals(other.studentAddressBook)
        && courseAddressBook.equals(other.courseAddressBook)
        && financeAddressBook.equals(other.financeAddressBook)
        && assignmentAddressBook.equals(other.assignmentAddressBook)
        && courseStudentAddressBook.equals(other.courseStudentAddressBook)
        && filteredStaffs.equals(other.filteredStaffs)
        && filteredStudents.equals(other.filteredStudents)
        && filteredCourses.equals(other.filteredCourses)
        && filteredFinances.equals(other.filteredFinances)
        && filteredAssignments.equals(other.filteredAssignments)
        && filteredCourseStudents.equals(other.filteredCourseStudents);

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

