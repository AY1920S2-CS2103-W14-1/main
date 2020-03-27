package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;

/**
 * A utility class containing a list of {@code Finance} objects to be used in tests.
 */
public class TypicalFinance {

    public static final Finance FINANCE_ALICE = new FinanceBuilder().withName("Alice Pauline")
            .withAmount("999")
            .withTags("friends").build();
    public static final Finance FINANCE_BENSON = new FinanceBuilder().withName("Benson Meier")
            .withAmount("999")
            .withTags("owesMoney", "friends").build();
    public static final Finance CARL = new FinanceBuilder().withName("Carl Kurz").withAmount("999").build();
    public static final Finance DANIEL = new FinanceBuilder().withName("Daniel Meier").withAmount("999")
            .withTags("friends").build();
    public static final Finance ELLE = new FinanceBuilder().withName("Elle Meyer").withAmount("999").build();
    public static final Finance FIONA = new FinanceBuilder().withName("Fiona Kunz").withAmount("999").build();
    public static final Finance GEORGE = new FinanceBuilder().withName("George Best").withAmount("999").build();

    // Manually added
    public static final Finance HOON = new FinanceBuilder().withName("Hoon Meier").withAmount("999").build();
    public static final Finance IDA = new FinanceBuilder().withName("Ida Mueller").withAmount("999").build();

    // Manually added - Finance's details found in {@code CommandTestUtil}
    public static final Finance AMY = new FinanceBuilder().withName(VALID_NAME_AMY).withAmount(VALID_AMOUNT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Finance BOB = new FinanceBuilder().withName(VALID_NAME_BOB).withAmount(VALID_AMOUNT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalFinance() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical finances.
     */
    public static FinanceAddressBook getTypicalFinanceAddressBook() {
        FinanceAddressBook ab = new FinanceAddressBook();
        for (Finance finance : getTypicalFinances()) {
            ab.add(finance);
        }
        return ab;
    }

    public static List<Finance> getTypicalFinances() {
        return new ArrayList<>(Arrays.asList(FINANCE_ALICE, FINANCE_BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
