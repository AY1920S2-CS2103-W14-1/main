package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

  private GuiSettings guiSettings = new GuiSettings();
  private Path addressBookFilePath = Paths.get("data", "addressbook.json");
  private Path teacherAddressBookFilePath = Paths.get("data", "teacherAddressbook.json");
  private Path staffAddressBookFilePath = Paths.get("data", "staffAddressbook.json");
  private Path studentAddressBookFilePath = Paths.get("data", "studentAddressbook.json");
  private Path courseAddressBookFilePath = Paths.get("data", "courseAddressbook.json");
  private Path financeAddressBookFilePath = Paths.get("data", "financeAddressbook.json");
  private Path assignmentAddressBookFilePath = Paths.get("data", "assignmentAddressbook.json");
  private Path courseStudentAddressBookFilePath = Paths.get("data", "courseStudentAddressbook.json");

  /**
   * Creates a {@code UserPrefs} with default values.
   */
  public UserPrefs() {
  }

  /**
   * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
   */
  public UserPrefs(ReadOnlyUserPrefs userPrefs) {
    this();
    resetData(userPrefs);
  }

  /**
   * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
   */
  public void resetData(ReadOnlyUserPrefs newUserPrefs) {
    requireNonNull(newUserPrefs);
    setGuiSettings(newUserPrefs.getGuiSettings());
    setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
  }

  public GuiSettings getGuiSettings() {
    return guiSettings;
  }

  public void setGuiSettings(GuiSettings guiSettings) {
    requireNonNull(guiSettings);
    this.guiSettings = guiSettings;
  }

  public Path getAddressBookFilePath() {
    return addressBookFilePath;
  }

  public void setAddressBookFilePath(Path addressBookFilePath) {
    requireNonNull(addressBookFilePath);
    this.addressBookFilePath = addressBookFilePath;
  }

  public Path getTeacherAddressBookFilePath() {
    return teacherAddressBookFilePath;
  }

  public void setTeacherAddressBookFilePath(Path teacherAddressBookFilePath) {
    requireNonNull(teacherAddressBookFilePath);
    this.teacherAddressBookFilePath = teacherAddressBookFilePath;
  }

  public Path getStaffAddressBookFilePath() {
    return staffAddressBookFilePath;
  }

  public void setStaffAddressBookFilePath(Path staffAddressBookFilePath) {
    requireNonNull(staffAddressBookFilePath);
    this.staffAddressBookFilePath = staffAddressBookFilePath;
  }

  public Path getStudentAddressBookFilePath() {
    return studentAddressBookFilePath;
  }

  public void setStudentAddressBookFilePath(Path studentAddressBookFilePath) {
    requireNonNull(studentAddressBookFilePath);
    this.studentAddressBookFilePath = studentAddressBookFilePath;
  }

  public Path getCourseAddressBookFilePath() {
    return courseAddressBookFilePath;
  }

  public void setCourseAddressBookFilePath(Path courseAddressBookFilePath) {
    requireNonNull(courseAddressBookFilePath);
    this.courseAddressBookFilePath = courseAddressBookFilePath;
  }

  public Path getFinanceAddressBookFilePath() {
    return financeAddressBookFilePath;
  }

  public void setFinanceAddressBookFilePath(Path financeAddressBookFilePath) {
    requireNonNull(financeAddressBookFilePath);
    this.financeAddressBookFilePath = financeAddressBookFilePath;
  }

  public Path getAssignmentAddressBookFilePath() {
    return assignmentAddressBookFilePath;
  }

  public void setAssignmentAddressBookFilePath(Path assignmentAddressBookFilePath) {
    requireNonNull(assignmentAddressBookFilePath);
    this.assignmentAddressBookFilePath = assignmentAddressBookFilePath;
  }

  public Path getCourseStudentAddressBookFilePath() {
    return courseStudentAddressBookFilePath;
  }

  public void setCourseStudentAddressBookFilePath(Path courseStudentAddressBookFilePath) {
    requireNonNull(courseStudentAddressBookFilePath);
    this.courseAddressBookFilePath = courseStudentAddressBookFilePath;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (!(other instanceof UserPrefs)) { //this handles null as well.
      return false;
    }

    UserPrefs o = (UserPrefs) other;

    return guiSettings.equals(o.guiSettings)
        && addressBookFilePath.equals(o.addressBookFilePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(guiSettings, addressBookFilePath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Gui Settings : " + guiSettings);
    sb.append("\nLocal data file location : " + addressBookFilePath);
    return sb.toString();
  }

}
