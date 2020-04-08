package seedu.address.model.modelGeneric;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.NotFoundException;
import seedu.address.model.person.ID;

/**
 * A generic list that enforces uniqueness between its elements and does not allow nulls. A
 * course is considered unique by comparing using {@code Assignment#isSameCourse(Assignment)}. As such,
 * adding and updating of courses uses Assignment#isSameCourse(Assignment) for equality so as to ensure that
 * the course being added or updated is unique in terms of identity in the UniqueAssignmentList.
 * However, the removal of a course uses course#equals(Object) so as to ensure that the course with
 * exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueList<K extends ModelObject> implements Iterable<K> {

    private final ObservableList<K> internalList = FXCollections.observableArrayList();
    private final ObservableList<K> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(K toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::weakEquals);
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * The element should already be unique in unique list.
     */
    public Index getIndex(K obj) {
        requireAllNonNull(obj);
        return Index.fromZeroBased(internalList.indexOf(obj));
    }

    /**
     * Adds an object to the list.
     * The object must not already exist in the list.
     */
    public void add(K toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw toAdd.getDuplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Adds an object to the list at the exact index.
     * The Object must not already exist in the list.
     */
    public void addAtIndex(K toAdd, Integer idx) {
        requireAllNonNull(toAdd, idx);
        if (contains(toAdd)) {
            throw toAdd.getDuplicateException();
        }
        internalList.add(idx, toAdd);
    }

    /**
     * Replaces the object {@code target} in the list with {@code editedObject}. {@code target} must
     * exist in the list. The Assignment identity of {@code editedObject} must not be the same as another
     * existing object in the list.
     */
    public void set(K target, K edited) {
        requireAllNonNull(target, edited);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw target.getNotFoundException();
        }

        if (!target.weakEquals(edited) && contains(edited)) {
            throw target.getDuplicateException();
        }

        internalList.set(index, edited);
    }

    /**
     * Removes the equivalent course from the list. The course must exist in the list.
     */
    public void remove(K toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw toRemove.getNotFoundException();
        }
    }

    public void setList(UniqueList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code courses}. {@code courses} must not contain
     * duplicate courses.
     */
    public void set(List<K> objects) {
        requireAllNonNull(objects);
        if (!objectsAreUnique(objects)) {
            // guarantee to have at least one object
            throw objects.get(0).getDuplicateException();
        }

        internalList.setAll(objects);
    }

    // Operations by ID
    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(ID toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(x -> x.getId().equals(toCheck));
    }

    /**
     * Returns object if the ID matches.
     */
    public K get(ID toCheck) {
        requireNonNull(toCheck);
        return internalList.stream()
                .filter(x -> x.getId().equals(toCheck))
                .findFirst()
                .get();
    }

    // allow removal by IDs
    public K remove(ID toRemove) {
        requireNonNull(toRemove);
        boolean containsID = contains(toRemove);
        if (!containsID) {
            throw new NotFoundException("There's nothing with this ID to remove");
        } else {
            K toBeRemoved = get(toRemove);
            remove(toBeRemoved);
            return toBeRemoved;
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<K> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<K> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueList
                // instanceof handles nulls
                && internalList.equals(((UniqueList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code courses} contains only unique courses.
     */
    private boolean objectsAreUnique(List<K> objects) {
        for (int i = 0; i < objects.size() - 1; i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                if (objects.get(i).weakEquals(objects.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
