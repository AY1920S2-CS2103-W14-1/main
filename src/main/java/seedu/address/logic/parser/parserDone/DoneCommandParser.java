package seedu.address.logic.parser.parserDone;

import seedu.address.logic.commands.commandAdd.AddAssignmentCommand;
import seedu.address.logic.commands.commandAssign.AssignCommandBase;
import seedu.address.logic.commands.commandAssign.AssignCommandFactory;
import seedu.address.logic.commands.commandAssign.AssignDescriptor;
import seedu.address.logic.commands.commandDone.DoneCommandBase;
import seedu.address.logic.commands.commandDone.DoneCommandFactory;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class DoneCommandParser implements Parser<DoneCommandBase> {

    public static final String MESSAGE_USAGE =
        "done command must have an AssignmentID and a studentID"
        + "\nParameters: "
        + "\n" + PREFIX_STUDENTID + "STUDENTID"
        + " " + PREFIX_ASSIGNMENTID + "ASSIGNMENTID"
        + "\n" + "Example: "  + "done "
        + PREFIX_STUDENTID + "101 "
        + PREFIX_ASSIGNMENTID + "359 ";

    /**
     * Parses the given arguments into context of AssignCommand (actually a class that inherits
     * from AssignCommand, decided by AssignCommandFactory)
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommandBase parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID, PREFIX_ASSIGNMENTID);

        AssignDescriptor assignDescriptor = new AssignDescriptor();

        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            assignDescriptor.setAssignEntity(PREFIX_STUDENTID, ParserUtil.parseID(argMultimap.getValue(PREFIX_STUDENTID).get()));
        }

        if (argMultimap.getValue(PREFIX_ASSIGNMENTID).isPresent()) {
            assignDescriptor.setAssignEntity(PREFIX_ASSIGNMENTID, ParserUtil.parseID(argMultimap.getValue(PREFIX_ASSIGNMENTID).get()));
        }

        if(assignDescriptor.getAllAssignKeys().size() != 2) {
            throw new ParseException(
                    String.format(MESSAGE_USAGE, "")
            );
        }

        return DoneCommandFactory.getCommand(assignDescriptor);
    }
}
