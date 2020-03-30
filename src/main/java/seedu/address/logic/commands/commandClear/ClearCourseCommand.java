package seedu.address.logic.commands.commandClear;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.modelCourse.CourseAddressBook;

/**
 * Clears the address book.
 */
public class ClearCourseCommand extends ClearCommand {

  public static final String COMMAND_WORD = "clear-courses";
  public static final String MESSAGE_SUCCESS = "Database of Courses has been cleared!";

  @Override
  public CommandResult execute(Model model) {
    requireNonNull(model);
    model.setCourseAddressBook(new CourseAddressBook());
    return new CommandResult(MESSAGE_SUCCESS);
  }
}