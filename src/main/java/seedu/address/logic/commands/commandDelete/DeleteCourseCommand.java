package seedu.address.logic.commands.commandDelete;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.Constants;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandAdd.AddCourseCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelObjectTags.ID;

/**
 * Deletes a course identified using it's displayed index.
 */
public class DeleteCourseCommand extends DeleteCommand {

  public static final String COMMAND_WORD = "delete-course";

  public static final String MESSAGE_USAGE = COMMAND_WORD
      + ": Deletes the course identified by the existing ID number used in the displayed course list.\n"
      + "Parameters: ID (must be an existing positive integer)\n"
      + "Example: " + COMMAND_WORD + " 16100";

  public static final String MESSAGE_DELETE_COURSE_SUCCESS = "Deleted Course: %1$s";

  private Index targetIndex;
  private ID targetID;
  private Course toDelete;

  public DeleteCourseCommand(ID targetID) {
    this.targetID = targetID;
  }

  public DeleteCourseCommand(Course toDelete) {
    this.toDelete = toDelete;
  }

  @Override
  protected void preprocessUndoableCommand(Model model) throws CommandException {
    if (this.toDelete == null) {
      if (!ID.isValidId(targetID.toString())) {
        throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_ID);
      }
      if (!model.has(targetID, Constants.ENTITY_TYPE.COURSE)) {
        throw new CommandException(Messages.MESSAGE_NOTFOUND_COURSE_DISPLAYED_ID);
      }
      this.toDelete = (Course) model.get(targetID, Constants.ENTITY_TYPE.COURSE);
    }
    if (this.targetID == null) {
      this.targetID = toDelete.getId();
    }
    if (this.targetIndex == null) {
      this.targetIndex = model.getIndex(this.toDelete);
    }
  }

  @Override
  protected void generateOppositeCommand() throws CommandException {
    oppositeCommand = new AddCourseCommand(toDelete.clone(), targetIndex.getZeroBased());
  }

  @Override
  public CommandResult executeUndoableCommand(Model model) throws CommandException {
    requireNonNull(model);
    model.delete(this.toDelete);
    return new CommandResult(String.format(MESSAGE_DELETE_COURSE_SUCCESS, this.toDelete));
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof DeleteCourseCommand // instanceof handles nulls
        && targetIndex.equals(((DeleteCourseCommand) other).targetIndex)); // state check
  }
}
