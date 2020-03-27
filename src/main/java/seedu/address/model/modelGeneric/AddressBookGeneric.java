package seedu.address.model.modelGeneric;

import javafx.collections.ObservableList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AddressBookGeneric<K extends ModelObject> implements ReadOnlyAddressBookGeneric<K> {

    protected final UniqueList<K> objects;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */ {
        objects = new UniqueList<K>();
    }

    public AddressBookGeneric() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBookGeneric(ReadOnlyAddressBookGeneric<K> toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the course list with {@code courses}. {@code courses} must not contain
     * duplicate courses.
     */
    public void set(List<K> objects) {
        this.objects.set(objects);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBookGeneric<K> newData) {
        requireNonNull(newData);

        set(newData.getList());
    }

    //// course-level operations

    /**
     * Returns true if a course with the same identity as {@code course} exists in the address book.
     */
    public boolean has(K object) {
        requireNonNull(object);
        return objects.contains(object);
    }

    /**
     * Adds a course to the address book. The course must not already exist in the address book.
     */
    public void add(K object) {
        objects.add(object);
    }

    /**
     * Replaces the given course {@code target} in the list with {@code editedCourse}. {@code target}
     * must exist in the address book. The course identity of {@code editedCourse} must not be the
     * same as another existing course in the address book.
     */
    public void set(K target, K edited) {
        requireNonNull(edited);

        objects.set(target, edited);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}. {@code key} must exist in the address book.
     */
    public void remove(K key) {
        objects.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return objects.asUnmodifiableObservableList().size() + " objects";
        // TODO: refine later
    }

    @Override
    public ObservableList<K> getList() {
        return objects.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBookGeneric
                // instanceof handles nulls
                && objects.equals(((AddressBookGeneric<K>) other).objects));
    }

    @Override
    public int hashCode() {
        return objects.hashCode();
    }
}
