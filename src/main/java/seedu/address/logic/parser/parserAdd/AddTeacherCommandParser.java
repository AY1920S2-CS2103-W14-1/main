package seedu.address.logic.parser.parserAdd;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.commandAdd.AddCommand;
import seedu.address.logic.commands.commandAdd.AddTeacherCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Address;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.Email;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddTeacherCommandParser extends AddCommandParser {

  /**
   * Returns true if none of the prefixes contains empty {@code Optional} values in the given {@code
   * ArgumentMultimap}.
   */
  private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
    return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
  }

  /**
   * Parses the given {@code String} of arguments in the context of the AddCommand and returns an
   * AddCommand object for execution.
   *
   * @return
   * @throws ParseException if the user input does not conform the expected format
   */
  public AddCommand parse(String args) throws ParseException {
    ArgumentMultimap argMultimap =
        ArgumentTokenizer
            .tokenize(args, PREFIX_NAME, PREFIX_TEACHERID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SALARY, PREFIX_ADDRESS,
                PREFIX_TAG);

    if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
        || !argMultimap.getPreamble().isEmpty()) {
      throw new ParseException(
          String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeacherCommand.MESSAGE_USAGE));
    }

    Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
    ID id = ParserUtil.parseID(argMultimap.getValue(PREFIX_TEACHERID).get());

    Phone phone = new Phone("Unknown");
    Email email = new Email("Unknown");
    Salary salary = new Salary("0");
    Address address = new Address("Unknown");
    if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
      phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
    }
    if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
      email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
    }
    if (arePrefixesPresent(argMultimap, PREFIX_SALARY)) {
      salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
    }
    if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
      address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
    }
    AssignedCourses assignedCourses = new AssignedCourses("");
    Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

    /*
    Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
    Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
    Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());
    Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
    */

    Teacher teacher = new Teacher(name, id, phone, email, salary, address, assignedCourses, tagList);

    return new AddTeacherCommand(teacher);
  }

}
