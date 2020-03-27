package seedu.address.commons.exceptions;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String entityName) {
        super("Operation would result in duplicate " + entityName);
    }
}
