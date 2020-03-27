package seedu.address.commons.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName) {
        super(entityName + " not found");
    }
}
