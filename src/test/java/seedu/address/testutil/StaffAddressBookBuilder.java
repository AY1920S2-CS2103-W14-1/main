package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class StaffAddressBookBuilder {

    private StaffAddressBook staffAddressBook;

    public StaffAddressBookBuilder() {
        staffAddressBook = new StaffAddressBook();
    }

    public StaffAddressBookBuilder(StaffAddressBook staffAddressBook) {
        this.staffAddressBook = staffAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public StaffAddressBookBuilder withStaff(Staff staff) {
        staffAddressBook.add(staff);
        return this;
    }

    public StaffAddressBook build() {
        return staffAddressBook;
    }
}
