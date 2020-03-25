package seedu.address.logic.commands.commandAdd;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.modelStaff.Staff;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddStaffCommand extends AddCommand {

    public static final String COMMAND_WORD = "add-staff";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a staff to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_SALARY + "SALARY "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Bob Ross "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "bob.ross@gmail.com "
            + PREFIX_SALARY + "1000 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "LovesArt "
            + PREFIX_TAG + "Friendly";

    public static final String MESSAGE_SUCCESS = "New staff added: %1$s";
    public static final String MESSAGE_DUPLICATE_STAFF = "This staff already exists in the address book";

    private final Staff toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Teacher}
     */
    public AddStaffCommand(Staff staff) {
        requireNonNull(staff);
        toAdd = staff;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStaff(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STAFF);
        }

        model.addStaff(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddStaffCommand // instanceof handles nulls
                && toAdd.equals(((AddStaffCommand) other).toAdd));
    }
}