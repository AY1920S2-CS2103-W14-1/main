package seedu.address.logic.commands.commandAdd;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.modelFinance.Finance;

/**
 * Adds a finance to the address book.
 */
public class AddFinanceCommand extends AddCommand {

  public static final String COMMAND_WORD = "add-finance";

  public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a finance to the address book. "
      + "Parameters: "
      + PREFIX_NAME + "NAME "
      + PREFIX_DATE + "DATE "
      + PREFIX_AMOUNT + "AMOUNT "
      + "[" + PREFIX_TAG + "TAG]...\n"
      + "Example: " + COMMAND_WORD + " "
      + PREFIX_NAME + "Paid NTU "
      + PREFIX_DATE + "2020-12-09 "
      + PREFIX_AMOUNT + "1200 "
      + PREFIX_TAG + "Partnership "
      + PREFIX_TAG + "Monthly ";

  public static final String MESSAGE_SUCCESS = "New finance added: %1$s";
  public static final String MESSAGE_DUPLICATE_FINANCE = "This finance already exists in the address book";

  private final Finance toAdd;

  /**
   * Creates an AddCommand to add the specified {@code Finance}
   */
  public AddFinanceCommand(Finance finance) {
    requireNonNull(finance);
    toAdd = finance;
  }

  @Override
  public CommandResult execute(Model model) throws CommandException {
    requireNonNull(model);

    if (model.hasFinance(toAdd)) {
      throw new CommandException(MESSAGE_DUPLICATE_FINANCE);
    }

    model.addFinance(toAdd);
    return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
  }

  @Override
  public boolean equals(Object other) {
    return other == this // short circuit if same object
        || (other instanceof AddFinanceCommand // instanceof handles nulls
        && toAdd.equals(((AddFinanceCommand) other).toAdd));
  }
}
