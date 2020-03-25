package seedu.address.model.modelStaff;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.person.exceptions.DuplicateStaffException;
import seedu.address.model.person.exceptions.StaffNotFoundException;

/**
 * A list of teachers that enforces uniqueness between its elements and does not allow nulls. A
 * teacher is considered unique by comparing using {@code Teacher#isSameTeacher(Teacher)}. As such,
 * adding and updating of teachers uses Teacher#isSameTeacher(Teacher) for equality so as to ensure
 * that the teacher being added or updated is unique in terms of identity in the UniqueTeacherList.
 * However, the removal of a teacher uses Teacher#equals(Object) so as to ensure that the teacher
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Staff#isSameStaff(Staff)
 */
public class UniqueStaffList implements Iterable<Staff> {

    private final ObservableList<Staff> internalList = FXCollections.observableArrayList();
    private final ObservableList<Staff> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent teacher as the given argument.
     */
    public boolean contains(Staff toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameStaff);
    }

    /**
     * Adds a teacher to the list. The teacher must not already exist in the list.
     */
    public void add(Staff toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateStaffException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the teacher {@code target} in the list with {@code editedTeacher}. {@code target} must
     * exist in the list. The teacher identity of {@code editedTeacher} must not be the same as
     * another existing teacher in the list.
     */
    public void setStaff(Staff target, Staff editedStaff) {
        requireAllNonNull(target, editedStaff);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new StaffNotFoundException();
        }

        if (!target.isSameStaff(editedStaff) && contains(editedStaff)) {
            throw new DuplicateStaffException();
        }

        internalList.set(index, editedStaff);
    }

    /**
     * Removes the equivalent teacher from the list. The teacher must exist in the list.
     */
    public void remove(Staff toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new StaffNotFoundException();
        }
    }

    public void setStaffs(UniqueStaffList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code teachers}. {@code teachers} must not contain
     * duplicate teachers.
     */
    public void setStaffs(List<Staff> staffs) {
        requireAllNonNull(staffs);
        if (!staffsAreUnique(staffs)) {
            throw new DuplicateStaffException();
        }

        internalList.setAll(staffs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Staff> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Staff> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.modelStaff.UniqueStaffList // instanceof handles nulls
                && internalList.equals(((seedu.address.model.modelStaff.UniqueStaffList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code teachers} contains only unique teachers.
     */
    private boolean staffsAreUnique(List<Staff> teachers) {
        for (int i = 0; i < teachers.size() - 1; i++) {
            for (int j = i + 1; j < teachers.size(); j++) {
                if (teachers.get(i).isSameStaff(teachers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
