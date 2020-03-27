package seedu.address.logic.commands.commandEdit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.person.Amount;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing course in the address book.
 */
public class EditCourseCommand extends Command {

  public static final String COMMAND_WORD = "edit-course";

  public static final String MESSAGE_USAGE =
      COMMAND_WORD + ": Edits the details of the course identified "
          + "by the index number used in the displayed course list. "
          + "Existing values will be overwritten by the input values.\n"
          + "Parameters: INDEX (must be a positive integer) "
          + "[" + PREFIX_NAME + "NAME] "
          + "[" + PREFIX_COURSEID + "COURSEID] "
          + "[" + PREFIX_AMOUNT + "AMOUNT] "
          + "[" + PREFIX_TAG + "TAG]...\n"
          + "Example: " + COMMAND_WORD + " 1 "
          + PREFIX_NAME + "Java 101 "
          + PREFIX_COURSEID + "464 "
          + PREFIX_AMOUNT + "1000 ";

  public static final String MESSAGE_EDIT_COURSE_SUCCESS = "Edited Assignment: %1$s";
  public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
  public static final String MESSAGE_DUPLICATE_COURSE = "This course already exists in the address book.";

  private final Index index;
  private final EditCourseDescriptor editCourseDescriptor;

  /**
   * @param index                of the course in the filtered course list to edit
   * @param editCourseDescriptor details to edit the course with
   */
  public EditCourseCommand(Index index, EditCourseDescriptor editCourseDescriptor) {
    requireNonNull(index);
    requireNonNull(editCourseDescriptor);

    this.index = index;
    this.editCourseDescriptor = new EditCourseDescriptor(editCourseDescriptor);
  }

  /**
   * Creates and returns a {@code Assignment} with the details of {@code courseToEdit} edited with
   * {@code editCourseDescriptor}.
   */
  private static Course createEditedCourse(Course courseToEdit,
      EditCourseDescriptor editCourseDescriptor) {
    assert courseToEdit != null;

    Name updatedName = editCourseDescriptor.getName().orElse(courseToEdit.getName());
    ID updatedCourseID = editCourseDescriptor.getID().orElse(courseToEdit.getId());
    Amount updatedAmount = editCourseDescriptor.getAmount().orElse(courseToEdit.getAmount());
    Set<Tag> updatedTags = editCourseDescriptor.getTags().orElse(courseToEdit.getTags());

    return new Course(updatedName, updatedCourseID, updatedAmount, courseToEdit.getAssignedTeacher(),
        courseToEdit.getAssignedStudents(), updatedTags);
  }

  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);
    List<Course> lastShownList = model.getFilteredCourseList();

    if (index.getZeroBased() >= lastShownList.size()) {
      throw new CommandException(Messages.MESSAGE_INVALID_COURSE_DISPLAYED_INDEX);
    }

    Course courseToEdit = lastShownList.get(index.getZeroBased());
    Course editedCourse = createEditedCourse(courseToEdit, editCourseDescriptor);

    if (!courseToEdit.weakEquals(editedCourse) && model.hasCourse(editedCourse)) {
      throw new CommandException(MESSAGE_DUPLICATE_COURSE);
    }

    model.setCourse(courseToEdit, editedCourse);
    model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
    return new CommandResult(String.format(MESSAGE_EDIT_COURSE_SUCCESS, editedCourse));
  }

  @Override
  public boolean equals(Object other) {
    // short circuit if same object
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof EditCourseCommand)) {
      return false;
    }

    // state check
    EditCourseCommand e = (EditCourseCommand) other;
    return index.equals(e.index)
        && editCourseDescriptor.equals(e.editCourseDescriptor);
  }

  /**
   * Stores the details to edit the course with. Each non-empty field value will replace the
   * corresponding field value of the course.
   */
  public static class EditCourseDescriptor {

    private Name name;
    private ID courseID;
    private Amount amount;
    private Set<Tag> tags;

    public EditCourseDescriptor() {
    }

    /**
     * Copy constructor. A defensive copy of {@code tags} is used internally.
     */
    public EditCourseDescriptor(EditCourseDescriptor toCopy) {
      setName(toCopy.name);
      setID(toCopy.courseID);
      setAmount(toCopy.amount);
      setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
      return CollectionUtil.isAnyNonNull(name, courseID, amount, tags);
    }

    public Optional<Name> getName() {
      return Optional.ofNullable(name);
    }

    public void setName(Name name) {
      this.name = name;
    }

    public Optional<ID> getID() {
      return Optional.ofNullable(courseID);
    }

    public void setID(ID courseID) {
      this.courseID = courseID;
    }

    public Optional<Amount> getAmount() {
      return Optional.ofNullable(amount);
    }

    public void setAmount(Amount amount) {
      this.amount = amount;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if
     * modification is attempted. Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
      return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    /**
     * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used
     * internally.
     */
    public void setTags(Set<Tag> tags) {
      this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    @Override
    public boolean equals(Object other) {
      // short circuit if same object
      if (other == this) {
        return true;
      }

      // instanceof handles nulls
      if (!(other instanceof EditCourseDescriptor)) {
        return false;
      }

      // state check
      EditCourseDescriptor e = (EditCourseDescriptor) other;

      return getName().equals(e.getName())
          && getID().equals(e.getID())
          && getAmount().equals(e.getAmount())
          && getTags().equals(e.getTags());
    }
  }
}
