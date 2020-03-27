package seedu.address.model.modelCourseStudent;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class CourseStudentAddressBook extends AddressBookGeneric<CourseStudent> {

  public CourseStudentAddressBook() {
     super();
  }

  /**
   * Creates an AddressBook using the Persons in the {@code toBeCopied}
   */
  public CourseStudentAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> toBeCopied) {
    super(toBeCopied);
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " courseStudents";
    // TODO: refine later
  }
}
