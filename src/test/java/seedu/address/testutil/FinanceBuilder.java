package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.person.Amount;
import seedu.address.model.person.Date;
import seedu.address.model.person.FinanceType;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Finance objects.
 */
public class FinanceBuilder {

    public static final String DEFAULT_NAME = "AdHoc";
    public static final String DEFAULT_FINANCETYPE = "Misc";
    public static final String DEFAULT_DATE = "2020-03-20";
    public static final String DEFAULT_AMOUNT = "102";

    private Name name;
    private FinanceType financeType;
    private Date date;
    private Amount amount;
    private Set<Tag> tags;

    public FinanceBuilder() {
        name = new Name(DEFAULT_NAME);
        financeType = new FinanceType(DEFAULT_FINANCETYPE);
        date = new Date(DEFAULT_DATE);
        amount = new Amount(DEFAULT_AMOUNT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the FinanceBuilder with the data of {@code financeToCopy}.
     */
    public FinanceBuilder(Finance financeToCopy) {
        name = financeToCopy.getName();
        financeType = financeToCopy.getFinanceType();
        date = financeToCopy.getDate();
        amount = financeToCopy.getAmount();
        tags = new HashSet<>(financeToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Finance} that we are building.
     */
    public FinanceBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Finance} that we are building.
     */
    public FinanceBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code Finance} that we are building.
     */
    public FinanceBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code financeType} of the {@code Finance} that we are building.
     */
    public FinanceBuilder withFinanceType(String financeType) {
        this.financeType = new FinanceType(financeType);
        return this;
    }

    public Finance build() {
        return new Finance(name, financeType, date, amount, tags);
    }

}
