package seedu.address.testutil;

import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;

/**
 * A utility class to help with building AssignmentAddressBook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AssignmentBookBuilder().withPerson("John", "Doe").build();}
 */

public class AssignmentBookBuilder {
    private AssignmentAddressBook assignmentAddressBook;

    public AssignmentBookBuilder() {
        assignmentAddressBook = new AssignmentAddressBook();
    }

    public AssignmentBookBuilder(AssignmentAddressBook addressBook) {
        this.assignmentAddressBook = addressBook;
    }

    /**
     * Adds a new {@code Assignment} to the {@code AssignmentAddressBook} that we are building.
     */
    public AssignmentBookBuilder withAssignment(Assignment assignment) {
        assignmentAddressBook.add(assignment);
        return this;
    }

    public AssignmentAddressBook build() {
        return assignmentAddressBook;
    }

}
