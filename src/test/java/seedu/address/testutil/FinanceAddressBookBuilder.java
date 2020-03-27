package seedu.address.testutil;

import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class FinanceAddressBookBuilder {

    private FinanceAddressBook financeAddressBook;

    public FinanceAddressBookBuilder() {
        financeAddressBook = new FinanceAddressBook();
    }

    public FinanceAddressBookBuilder(FinanceAddressBook financeAddressBook) {
        this.financeAddressBook = financeAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public FinanceAddressBookBuilder withFinance(Finance finance) {
        financeAddressBook.add(finance);
        return this;
    }

    public FinanceAddressBook build() {
        return financeAddressBook;
    }
}
