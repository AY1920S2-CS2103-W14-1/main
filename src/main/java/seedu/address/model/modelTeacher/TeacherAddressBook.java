package seedu.address.model.modelTeacher;

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
public class TeacherAddressBook extends AddressBookGeneric<Teacher> {

  public TeacherAddressBook() {
    super();
  }

  public TeacherAddressBook(ReadOnlyAddressBookGeneric<Teacher> toBeCopied) {
    super(toBeCopied);
  }

  @Override
  public String toString() {
    return objects.asUnmodifiableObservableList().size() + " teachers";
  }

}
