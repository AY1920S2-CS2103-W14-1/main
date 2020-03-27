package seedu.address.logic;

import java.nio.file.Path;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Person;
import seedu.address.ui.SummaryPanel;

/**
 * API of the Logic component
 */
public interface Logic {

  /**
   * Executes the command and returns the result.
   *
   * @param commandText The command as entered by the user.
   * @return the result of the command execution.
   * @throws CommandException If an error occurs during command execution.
   * @throws ParseException   If an error occurs during parsing.
   */
  CommandResult execute(String commandText) throws CommandException, ParseException;
  ///

  /**
   * Sets the summary panel of logic
   * @param summaryPanel the summary panel
   */
  public void setSummaryPanel(SummaryPanel summaryPanel);

  /**
   * Returns the AddressBook.
   *
   * @see seedu.address.model.Model#getAddressBook()
   */
  ReadOnlyAddressBook getAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of persons
   */
  ObservableList<Person> getFilteredPersonList();

  /**
   * Returns the user prefs' address book file path.
   */
  Path getAddressBookFilePath();

  /**
   * Returns the user prefs' GUI settings.
   */

  ///

  /**
   * Returns the TeacherAddressBook.
   *
   * @see seedu.address.model.Model#getTeacherAddressBook()
   */
  ReadOnlyAddressBookGeneric<Teacher> getTeacherAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of teachers
   */
  ObservableList<Teacher> getFilteredTeacherList();

  /**
   * Returns the user prefs' teacher address book file path.
   */
  Path getTeacherAddressBookFilePath();

  ///

  /**
   * Returns the StudentddressBook.
   *
   * @see seedu.address.model.Model#getStudentAddressBook()
   */
  ReadOnlyAddressBookGeneric<Student> getStudentAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of students
   */
  ObservableList<Student> getFilteredStudentList();

  /**
   * Returns the user prefs' teacher address book file path.
   */
  Path getStudentAddressBookFilePath();

  ///

  /**
   * Returns the FinanceAddressBook.
   *
   * @see seedu.address.model.Model#getFinanceAddressBook()
   */
  ReadOnlyAddressBookGeneric<Finance> getFinanceAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of finances
   */
  ObservableList<Finance> getFilteredFinanceList();

  /**
   * Returns the user prefs' teacher address book file path.
   */
  Path getFinanceAddressBookFilePath();

  ///

  /**
   * Returns the CourseAddressBook.
   *
   * @see seedu.address.model.Model#getCourseAddressBook()
   */
 ReadOnlyAddressBookGeneric<Course> getCourseAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of courses
   */
  ObservableList<Course> getFilteredCourseList();

  /**
   * Returns the user prefs' course address book file path.
   */
  Path getCourseAddressBookFilePath();

  //TODO
  /**
   * Returns the AssignmentAddressBook.
   *
   * @see seedu.address.model.Model#getCourseAddressBook()
   */
  ReadOnlyAddressBookGeneric<Assignment> getAssignmentAddressBook();

  /**
   * Returns an unmodifiable view of the filtered list of courses
   */
  ObservableList<Assignment> getFilteredAssignmentList();

  /**
   * Returns the user prefs' course address book file path.
   */
  Path getAssignmentAddressBookFilePath();

  /**
   * Returns the user prefs' GUI settings.
   */
  GuiSettings getGuiSettings();

  /**
   * Set the user prefs' GUI settings.
   */
  void setGuiSettings(GuiSettings guiSettings);
}
