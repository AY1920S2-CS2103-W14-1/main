package seedu.address.logic.commands.commandEdit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STAFFS;

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
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.Staff.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing teacher in the address book.
 */
public class EditTeacherCommand extends Command {

  public static final String COMMAND_WORD = "edit-staff";

  public static final String MESSAGE_USAGE =
      COMMAND_WORD + ": Edits the details of the staff identified "
          + "by the ID number assigned in the displayed staff list. "
          + "Existing values will be overwritten by the input values.\n"
          + "Parameters: ID (must be an existing positive integer) "
          + "[" + PREFIX_NAME + "NAME] "
          + "[" + PREFIX_GENDER + "GENDER] "
          + "[" + PREFIX_PHONE + "PHONE] "
          + "[" + PREFIX_EMAIL + "EMAIL] "
          + "[" + PREFIX_SALARY + "SALARY] "
          + "[" + PREFIX_ADDRESS + "ADDRESS] "
          + "[" + PREFIX_TAG + "TAG]...\n"

          + "Example: " + COMMAND_WORD + " 16100 "
          + PREFIX_GENDER + "m "
          + PREFIX_PHONE + "91234567 "
          + PREFIX_EMAIL + "johndoe@example.com";

  public static final String MESSAGE_EDIT_STAFF_SUCCESS = "Edited Staff: %1$s";
  public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
  public static final String MESSAGE_DUPLICATE_STAFF = "This staff already exists in the address book.";

  private final ID targetID;
  private final EditTeacherDescriptor editTeacherDescriptor;

  /**
   * @param targetID                 of the staff in the filtered staff list to edit
   * @param editTeacherDescriptor details to edit the staff with
   */
  public EditTeacherCommand(ID targetID, EditTeacherDescriptor editTeacherDescriptor) {
    requireNonNull(targetID);
    requireNonNull(editTeacherDescriptor);

    this.targetID = targetID;
    this.editTeacherDescriptor = new EditTeacherDescriptor(editTeacherDescriptor);
  }

  /**
   * Creates and returns a {@code Teacher} with the details of {@code teacherToEdit} edited with
   * {@code editTeacherDescriptor}.
   */
  private static Staff createEditedTeacher(Staff teacherToEdit,
      EditTeacherDescriptor editTeacherDescriptor) {
    assert teacherToEdit != null;

    Name updatedName = editTeacherDescriptor.getName().orElse(teacherToEdit.getName());
    Gender updatedGender = editTeacherDescriptor.getGender().orElse(teacherToEdit.getGender());
    Phone updatedPhone = editTeacherDescriptor.getPhone().orElse(teacherToEdit.getPhone());
    Email updatedEmail = editTeacherDescriptor.getEmail().orElse(teacherToEdit.getEmail());
    Salary updatedSalary = editTeacherDescriptor.getSalary().orElse(teacherToEdit.getSalary());
    Address updatedAddress = editTeacherDescriptor.getAddress().orElse(teacherToEdit.getAddress());
    Set<Tag> updatedTags = editTeacherDescriptor.getTags().orElse(teacherToEdit.getTags());

    // fields that cannot edit
    ID id = teacherToEdit.getId();
    Level updatedLevel = teacherToEdit.getLevel();
    Set<ID> assignedCoursesID = teacherToEdit.getAssignedCoursesID();
    return new Staff(updatedName, id, updatedGender, updatedLevel, updatedPhone, updatedEmail, updatedSalary, updatedAddress,
            assignedCoursesID, updatedTags);
  }

  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);
    List<Staff> lastShownList = model.getFilteredStaffList();

    if (!ID.isValidId(targetID.toString())) {
      throw new CommandException(Messages.MESSAGE_INVALID_STAFF_DISPLAYED_ID);
    }

    Staff teacherToEdit = getStaff(lastShownList);
    Staff editedTeacher = createEditedTeacher(teacherToEdit, editTeacherDescriptor);

    if (!teacherToEdit.weakEquals(editedTeacher) && model.has(editedTeacher)) {
      throw new CommandException(MESSAGE_DUPLICATE_STAFF);
    }

    model.set(teacherToEdit, editedTeacher);
    model.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);
    return new CommandResult(String.format(MESSAGE_EDIT_STAFF_SUCCESS, editedTeacher));
  }

  // Find way to abstract this
  public Staff getStaff(List<Staff> lastShownList) throws CommandException {
    for (Staff staff : lastShownList) {
      if (staff.getId().equals(this.targetID)) {
        return staff;
      }
    }
    throw new CommandException("This staff ID does not exist");
  }

  @Override
  public boolean equals(Object other) {
    // short circuit if same object
    if (other == this) {
      return true;
    }

    // instanceof handles nulls
    if (!(other instanceof EditTeacherCommand)) {
      return false;
    }

    // state check
    EditTeacherCommand e = (EditTeacherCommand) other;
    return targetID.equals(e.targetID)
        && editTeacherDescriptor.equals(e.editTeacherDescriptor);
  }

  /**
   * Stores the details to edit the teacher with. Each non-empty field value will replace the
   * corresponding field value of the teacher.
   */
  public static class EditTeacherDescriptor {

    private Name name;
    private Gender gender;
    private Level level;
    private Phone phone;
    private Email email;
    private Address address;
    private Salary salary;
    private Set<Tag> tags;

    public EditTeacherDescriptor() {
    }

    /**
     * Copy constructor. A defensive copy of {@code tags} is used internally.
     */
    public EditTeacherDescriptor(EditTeacherDescriptor toCopy) {
      setName(toCopy.name);
      setGender(toCopy.gender);
      setLevel(toCopy.level);
      setPhone(toCopy.phone);
      setEmail(toCopy.email);
      setSalary(toCopy.salary);
      setAddress(toCopy.address);
      setTags(toCopy.tags);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
      return CollectionUtil.isAnyNonNull(name, phone, email, salary, address, tags);
    }

    public Optional<Name> getName() {
      return Optional.ofNullable(name);
    }

    public Optional<Gender> getGender() {
      return Optional.ofNullable(gender);
    }

    public void setGender(Gender gender) {
      this.gender = gender;
    }

    public Optional<Level> getLevel() {
      return Optional.ofNullable(level);
    }

    public void setLevel(Level level) {
      this.level = level;
    }

    public void setName(Name name) {
      this.name = name;
    }

    public Optional<Phone> getPhone() {
      return Optional.ofNullable(phone);
    }

    public void setPhone(Phone phone) {
      this.phone = phone;
    }

    public Optional<Email> getEmail() {
      return Optional.ofNullable(email);
    }

    public void setEmail(Email email) {
      this.email = email;
    }

    public Optional<Salary> getSalary() {
      return Optional.ofNullable(salary);
    }

    public void setSalary(Salary salary) {
      this.salary = salary;
    }

    public Optional<Address> getAddress() {
      return Optional.ofNullable(address);
    }

    public void setAddress(Address address) {
      this.address = address;
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
      if (!(other instanceof EditTeacherDescriptor)) {
        return false;
      }

      // state check
      EditTeacherDescriptor e = (EditTeacherDescriptor) other;

      return getName().equals(e.getName())
          && getGender().equals(e.getGender())
          && getPhone().equals(e.getPhone())
          && getEmail().equals(e.getEmail())
          && getSalary().equals(e.getSalary())
          && getAddress().equals(e.getAddress())
          && getTags().equals(e.getTags());
    }
  }
}
