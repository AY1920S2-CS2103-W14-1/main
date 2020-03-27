package seedu.address.model.modelAssignment;

import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class AssignmentAddressBook extends AddressBookGeneric<Assignment> {

  public AssignmentAddressBook() {
    super();
  }

  public AssignmentAddressBook(ReadOnlyAddressBookGeneric<Assignment> toBeCopied) {
    super();
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " assignments";
    // TODO: refine later
  }
}
