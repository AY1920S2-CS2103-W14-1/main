package seedu.address.model.modelStudent;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class StudentAddressBook extends AddressBookGeneric<Student> {
  public StudentAddressBook() {
    super();
  }

  /**
   * Creates an AddressBook using the Persons in the {@code toBeCopied}
   */
  public StudentAddressBook(ReadOnlyAddressBookGeneric<Student> toBeCopied) {
    super(toBeCopied);
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " teachers";
    // TODO: refine later
  }
}
