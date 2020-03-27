package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's assignedTeacher in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidAssignedTeacher(String)}
 */
public class AssignedTeacher {

  public static final String MESSAGE_CONSTRAINTS =
      "AssignedTeachers should only contain alphanumeric characters and spaces, and it should not be blank";

  /*
   * The first character of the address must not be a whitespace,
   * otherwise " " (a blank string) becomes a valid input.
   */
  public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

  public final String fullAssignedTeacher;

  /**
   * Constructs a {@code AssignedTeacher}.
   *
   * @param assignedTeacher A valid assignedTeacher.
   */
  public AssignedTeacher(String assignedTeacher) {
    requireNonNull(assignedTeacher);
    checkArgument(isValidAssignedTeacher(assignedTeacher), MESSAGE_CONSTRAINTS);
    fullAssignedTeacher = assignedTeacher;
  }

  /**
   * Returns true if a given string is a valid assignedTeacher.
   */
  public static boolean isValidAssignedTeacher(String test) {
    return test.matches(VALIDATION_REGEX) || test.equals("");
  }


  @Override
  public String toString() {
    return fullAssignedTeacher;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof AssignedTeacher // instanceof handles nulls
        && fullAssignedTeacher.equals(((AssignedTeacher) other).fullAssignedTeacher)); // state check
  }

  @Override
  public int hashCode() {
    return fullAssignedTeacher.hashCode();
  }

}
