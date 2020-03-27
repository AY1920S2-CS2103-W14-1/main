package seedu.address.model.modelCourse;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.person.Address;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class CourseAddressBook extends AddressBookGeneric<Course> {

  public CourseAddressBook() {
    super();
  }

  public CourseAddressBook(ReadOnlyAddressBookGeneric<Course> toBeCopied) {
    super(toBeCopied);
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " courses";
  }

}
