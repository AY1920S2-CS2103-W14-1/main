package seedu.address.model.modelFinance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.UniqueList;
import seedu.address.model.person.exceptions.DuplicateFinanceException;
import seedu.address.model.person.exceptions.FinanceNotFoundException;

/**
 * A list of finances that enforces uniqueness between its elements and does not allow nulls. A
 * finance is considered unique by comparing using {@code Finance#isSameFinance(Finance)}. As such,
 * adding and updating of finances uses Finance#isSameFinance(Finance) for equality so as to ensure
 * that the finance being added or updated is unique in terms of identity in the UniqueFinanceList.
 * However, the removal of a finance uses finance#equals(Object) so as to ensure that the finance
 * with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 */
public class UniqueFinanceList extends UniqueList<Finance> {
}
