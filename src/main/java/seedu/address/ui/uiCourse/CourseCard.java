package seedu.address.ui.uiCourse;

import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.modelCourse.Course;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class CourseCard extends UiPart<Region> {

  private static final String FXML = "CourseListCard.fxml";

  /**
   * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a
   * consequence, UI elements' variable names cannot be set to such keywords or an exception will be
   * thrown by JavaFX during runtime.
   *
   * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook
   * level 4</a>
   */

  public final Course course;

  @FXML
  private HBox cardPane;
  @FXML
  private Label name;
  @FXML
  private Label teacherID;
  @FXML
  private Label courseID;
  @FXML
  private Label assignedTeacher;
  @FXML
  private Label assignedStudents;
  @FXML
  private Label amount;
  @FXML
  private FlowPane tags;

  public CourseCard(Course course, int displayedIndex) {
    super(FXML);
    this.course = course;
    name.setText(course.getName().fullName);
    teacherID.setText(course.getId().value);
    courseID.setText(course.getId().value);
    amount.setText(course.getAmount().value);
    assignedTeacher.setText(course.getAssignedTeacherWithName());
    assignedStudents.setText(course.getAssignedStudentsWithNames());

    course.getTags().stream()
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
    if (!(other instanceof CourseCard)) {
      return false;
    }

    // state check
    CourseCard card = (CourseCard) other;
    return courseID.getText().equals(card.courseID.getText())
        && amount.getText().equals(card.amount.getText())
        && course.equals(card.course);
  }
}
