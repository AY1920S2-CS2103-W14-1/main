package seedu.address.logic.parser.parserAdd;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.commandAdd.AddCommand;
import seedu.address.logic.commands.commandAdd.AddCourseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.person.Amount;
import seedu.address.model.person.AssignedStudents;
import seedu.address.model.person.AssignedTeacher;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCourseCommandParser extends AddCommandParser {

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
        ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COURSEID, PREFIX_AMOUNT, PREFIX_TAG);

    if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_COURSEID, PREFIX_AMOUNT)
        || !argMultimap.getPreamble().isEmpty()) {
      throw new ParseException(
          String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCourseCommand.MESSAGE_USAGE));
    }

    Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
    ID courseID = ParserUtil.parseID(argMultimap.getValue(PREFIX_COURSEID).get());
    Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
    AssignedTeacher assignedTeacher = new AssignedTeacher("");
    AssignedStudents assignedStudents = new AssignedStudents("");
    Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

    Course course = new Course(name, courseID, amount, assignedTeacher, assignedStudents,  tagList);

    return new AddCourseCommand(course);
  }

}
