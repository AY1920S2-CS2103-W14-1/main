package seedu.address.model.modelStaff;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelTeacher.Teacher;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class StaffAddressBook extends AddressBookGeneric<Staff> {

    public StaffAddressBook() {
        super();
    }

    public StaffAddressBook(ReadOnlyAddressBookGeneric<Staff> toBeCopied) {
        super(toBeCopied);
    }

    @Override
    public String toString() {
        return objects.asUnmodifiableObservableList().size() + " staffs";
    }

}