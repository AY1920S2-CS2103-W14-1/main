package seedu.address.storage.storageFinance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "financeaddressbook")
class JsonFinanceSerializableAddressBook {

  public static final String MESSAGE_DUPLICATE_FINANCE = "Finances list contains duplicate finance(s).";

  private final List<JsonAdaptedFinance> finances = new ArrayList<>();

  /**
   * Constructs a {@code JsonSerializableAddressBook} with the given finances.
   */
  @JsonCreator
  public JsonFinanceSerializableAddressBook(
      @JsonProperty("finances") List<JsonAdaptedFinance> finances) {
    this.finances.addAll(finances);
  }

  /**
   * Converts a given {@code ReadOnlyAddressBookGeneric<Finance>} into this class for Jackson use.
   *
   * @param source future changes to this will not affect the created {@code
   *               JsonFinanceSerializableAddressBook}.
   */
  public JsonFinanceSerializableAddressBook(ReadOnlyAddressBookGeneric<Finance> source) {
    finances.addAll(source.getList().stream().map(
        JsonAdaptedFinance::new).collect(Collectors.toList()));
  }

  /**
   * Converts this address book into the model's {@code AddressBook} object.
   *
   * @throws IllegalValueException if there were any data constraints violated.
   */
  public FinanceAddressBook toModelType() throws IllegalValueException {
    FinanceAddressBook financeAddressBook = new FinanceAddressBook();
    for (JsonAdaptedFinance jsonAdaptedFinance : finances) {
      Finance finance = jsonAdaptedFinance.toModelType();
      if (financeAddressBook.has(finance)) {
        throw new IllegalValueException(MESSAGE_DUPLICATE_FINANCE);
      }
      financeAddressBook.add(finance);
    }
    return financeAddressBook;
  }

}
