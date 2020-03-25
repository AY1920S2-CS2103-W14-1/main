package seedu.address.model.modelStaff;


import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyStaffAddressBook {

    /**
     * Returns an unmodifiable view of the teachers list. This list will not contain any duplicate
     * teachers.
     */
    ObservableList<Staff> getStaffList();

}