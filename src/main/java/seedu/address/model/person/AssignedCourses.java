package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Course in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidAssignedCourses (String)}
 */
public class AssignedCourses {

  public static final String MESSAGE_CONSTRAINTS =
      "Assigned Courses should be numbers seperated by commas";

  /*
   * Must be numbers seperated by commas
   */
  public static final String VALIDATION_REGEX = "[0-9]+(,[0-9]+)*+";

  public final String value;

  /**
   * Constructs a {@code Course}.
   *
   * @param assignedCourses A valid course.
   */
  public AssignedCourses(String assignedCourses) {
    requireNonNull(assignedCourses);
    checkArgument(isValidAssignedCourses(assignedCourses), MESSAGE_CONSTRAINTS);
    value = assignedCourses;
  }

  /**
   * Returns true if a given string is a valid course.
   */
  public static boolean isValidAssignedCourses(String test) {
    return test.matches(VALIDATION_REGEX) || test.equals("");
  }


  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof AssignedCourses // instanceof handles nulls
        && value.equals(((AssignedCourses) other).value)); // state check
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

}
