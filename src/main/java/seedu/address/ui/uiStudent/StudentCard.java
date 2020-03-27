package seedu.address.ui.uiStudent;

import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.modelStudent.Student;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

  private static final String FXML = "StudentListCard.fxml";

  /**
   * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a
   * consequence, UI elements' variable names cannot be set to such keywords or an exception will be
   * thrown by JavaFX during runtime.
   *
   * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook
   * level 4</a>
   */

  public final Student student;

  @FXML
  private HBox cardPane;
  @FXML
  private Label name;
  @FXML
  private Label studentID;
  @FXML
  private Label assignedCourses;
  @FXML
  private FlowPane tags;

  public StudentCard(Student student, int displayedIndex) {
    super(FXML);
    this.student = student;
    studentID.setText(student.getID().value);
    name.setText(student.getName().fullName);
    assignedCourses.setText(student.getAssignedCoursesWithNames());
    student.getTags().stream()
        .sorted(Comparator.comparing(tag -> tag.tagName))
        .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
  }

  @Override
  public boolean equals(Object other) {
    // short circuit if same object
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof StudentCard)) {
      return false;
    }

    // state check
    StudentCard card = (StudentCard) other;
    return studentID.getText().equals(card.studentID.getText())
        && student.equals(card.student);
  }
}
