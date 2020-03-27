package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.modelFinance.Finance;

/**
 * Finds and lists all finances in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CalculateEarningsFinanceCommand extends Command {

  public static final String COMMAND_WORD = "earnings";

  @Override
  public CommandResult execute(Model model) {
    requireNonNull(model);
    ObservableList<Finance> filteredFinances = model.getFilteredFinanceList();
    float totalAmount = 0;

    for (Finance finance : filteredFinances){
      String amount = finance.getAmount().toString();
      if (!amount.substring(0, 1).equals("-")){
        //Add earnings
        amount = amount.trim();
        totalAmount += Float.parseFloat(amount);
      }

      //Ignore expenses
    }

    return new CommandResult("Total earnings: " + totalAmount);
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof CalculateEarningsFinanceCommand); // instanceof handles nulls
  }
}
