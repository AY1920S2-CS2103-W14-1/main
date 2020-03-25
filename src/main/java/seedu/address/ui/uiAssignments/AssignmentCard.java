package seedu.address.ui.uiAssignments;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.ui.UiPart;

import java.util.Comparator;

/**
 * An UI component that displays information of a {@code Assignment}.
 */
public class AssignmentCard extends UiPart<Region> {

  private static final String FXML = "AssignmentListCard.fxml";

  /**
   * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a
   * consequence, UI elements' variable names cannot be set to such keywords or an exception will be
   * thrown by JavaFX during runtime.
   *
   * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook
   * level 4</a>
   */

  public final Assignment assignment;

  @FXML
  private HBox cardPane;
  @FXML
  private Label id;
  @FXML
  private Label assignmentID;
  @FXML
  private Label assignmentName;
  @FXML
  private Label assignmentDeadline;

  public AssignmentCard(Assignment assignment, int displayedIndex) {
    super(FXML);
    this.assignment = assignment;
    id.setText(displayedIndex + ". ");
    assignmentID.setText("Assignment ID: " + assignment.getId().toString());
    assignmentName.setText(assignment.getName().fullName);
    assignmentDeadline.setText("Deadline: " + assignment.getDeadline().toString());
  }

  @Override
  public boolean equals(Object other) {
    // short circuit if same object
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof AssignmentCard)) {
      return false;
    }

    // state check
    AssignmentCard card = (AssignmentCard) other;
    return id.getText().equals(card.id.getText())
        && assignment.equals(card.assignment);
  }
}
