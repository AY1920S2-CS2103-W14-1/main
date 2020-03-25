package seedu.address.model.modelStaff;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelTeacher.ReadOnlyTeacherAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.UniqueTeacherList;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class StaffAddressBook implements ReadOnlyStaffAddressBook {

    private final UniqueStaffList staffs;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        staffs = new UniqueStaffList();
    }

    public StaffAddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public StaffAddressBook(ReadOnlyStaffAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the staff list with {@code staffs}. {@code staffs} must not
     * contain duplicate staffs.
     */
    public void setStaffs(List<Staff> staffs) {
        this.staffs.setStaffs(staffs);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyStaffAddressBook newData) {
        requireNonNull(newData);

        setStaffs(newData.getStaffList());
    }

    //// teacher-level operations

    /**
     * Returns true if a teacher with the same identity as {@code teacher} exists in the address
     * book.
     */
    public boolean hasStaffs(Staff staff) {
        requireNonNull(staff);
        return staffs.contains(staff);
    }

    /**
     * Adds a staff to the address book. The staff must not already exist in the address book.
     */
    public void addStaff(Staff s) {
        staffs.add(s);
    }

    /**
     * Replaces the given staff {@code target} in the list with {@code editedStaff}. {@code
     * target} must exist in the address book. The staff identity of {@code editedStaff} must not
     * be the same as another existing staff in the address book.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireNonNull(editedStaff);

        staffs.setStaff(target, editedStaff);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void removeStaff(Staff key) {
        staffs.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return staffs.asUnmodifiableObservableList().size() + " teachers";
        // TODO: refine later
    }

    @Override
    public ObservableList<Staff> getStaffList() {
        return staffs.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.modelStaff.StaffAddressBook // instanceof handles nulls
                && staffs.equals(((seedu.address.model.modelStaff.StaffAddressBook) other).staffs));
    }

    @Override
    public int hashCode() {
        return staffs.hashCode();
    }
}
