package seedu.address.model.person.exceptions;

public class DuplicateStaffException extends RuntimeException {
    public DuplicateStaffException() {
        super("Operation would result in duplicate staffs");
    }
}
