package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's teacherid number in the address book. Guarantees: immutable; is valid as
 * declared in {@link #isValidTeacherid(String)}
 */
public class Teacherid {


  public static final String MESSAGE_CONSTRAINTS =
      "Teacherid numbers should only contain numbers, and it should be at least 1 digits long";
  public static final String VALIDATION_REGEX = "\\d{1,}";
  public final String value;

  /**
   * Constructs a {@code Teacherid}.
   *
   * @param teacherid A valid teacherid number.
   */
  public Teacherid(String teacherid) {
    requireNonNull(teacherid);
    checkArgument(isValidTeacherid(teacherid), MESSAGE_CONSTRAINTS);
    value = teacherid;
  }

  /**
   * Returns true if a given string is a valid teacherid number.
   */
  public static boolean isValidTeacherid(String test) {
    return test.matches(VALIDATION_REGEX);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof Teacherid // instanceof handles nulls
        && value.equals(((Teacherid) other).value)); // state check
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

}
