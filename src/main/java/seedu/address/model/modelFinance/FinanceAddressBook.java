package seedu.address.model.modelFinance;

import static java.util.Objects.requireNonNull;

import java.util.List;
import javafx.collections.ObservableList;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelGeneric.AddressBookGeneric;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * Wraps all data at the address-book level Duplicates are not allowed (by .isSamePerson
 * comparison)
 */
public class FinanceAddressBook extends AddressBookGeneric<Finance> {

  public FinanceAddressBook() {
    super();
  }

  public FinanceAddressBook(ReadOnlyAddressBookGeneric<Finance> toBeCopied) {
    super(toBeCopied);
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " finance";
  }
}
