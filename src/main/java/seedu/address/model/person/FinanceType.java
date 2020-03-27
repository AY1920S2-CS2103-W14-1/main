package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's financeType in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidFinanceType(String)}
 */
public class FinanceType {

  public static final String MESSAGE_CONSTRAINTS =
      "FinanceTypes should only contain alphanumeric characters and spaces, and it should not be blank";

  /*
   * The first character of the address must not be a whitespace,
   * otherwise " " (a blank string) becomes a valid input.
   */
  public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

  public final String fullFinanceType;

  /**
   * Constructs a {@code FinanceType}.
   *
   * @param financeType A valid financeType.
   */
  public FinanceType(String financeType) {
    requireNonNull(financeType);
    checkArgument(isValidFinanceType(financeType), MESSAGE_CONSTRAINTS);
    fullFinanceType = financeType;
  }

  /**
   * Returns true if a given string is a valid financeType.
   */
  public static boolean isValidFinanceType(String test) {
    return test.matches(VALIDATION_REGEX);
  }


  @Override
  public String toString() {
    return fullFinanceType;
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof FinanceType // instanceof handles nulls
        && fullFinanceType.equals(((FinanceType) other).fullFinanceType)); // state check
  }

  @Override
  public int hashCode() {
    return fullFinanceType.hashCode();
  }

}
