package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.personTagValidations.DateValidator;

/**
 * Represents an Assignment's date in the address book. Guarantees: immutable; is valid as declared in
 */


public class Date {

    public final String value;

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format YYYY-MM-DD, and it should not be blank";

    public static boolean isValidDate(String test) {
        return DateValidator.validateDate(test);
    }

    /**
     * Constructs a {@code Date}.
     *
     * @param date a valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
