package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

  public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

  /**
   * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
   * whitespaces will be trimmed.
   *
   * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
   */
  public static Index parseIndex(String oneBasedIndex) throws ParseException {
    String trimmedIndex = oneBasedIndex.trim();
    if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
      throw new ParseException(MESSAGE_INVALID_INDEX);
    }
    return Index.fromOneBased(Integer.parseInt(trimmedIndex));
  }

  /**
   * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code name} is invalid.
   */
  public static Name parseName(String name) throws ParseException {
    requireNonNull(name);
    String trimmedName = name.trim();
    if (!Name.isValidName(trimmedName)) {
      throw new ParseException(Name.MESSAGE_CONSTRAINTS);
    }
    return new Name(trimmedName);
  }


  /**
   * Parses a {@code String courseID} into a {@code ID}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code courseID} is invalid.
   */
  public static ID parseID(String StringID) throws ParseException {
    requireNonNull(StringID);
    String trimmedID = StringID.trim();
    if (!ID.isValidId(trimmedID)) {
      throw new ParseException(ID.MESSAGE_CONSTRAINTS);
    }
    return new ID(trimmedID);
  }

  /**
   * Parses a {@code String assignmentID} into a {@code ID}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code assignmentID} is invalid.
   */
  public static ID parseAssignmentID(String assignmentID) throws ParseException {
    requireNonNull(assignmentID);
    String trimmedAssignmentID = assignmentID.trim();
    if (!ID.isValidId(trimmedAssignmentID)) {
      throw new ParseException(ID.MESSAGE_CONSTRAINTS);
    }
    return new ID(trimmedAssignmentID);
  }

  /**
   * Parses a {@code String courseid} into a {@code Courseid}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code courseid} is invalid.
   */
  public static Courseid parseCourseid(String courseid) throws ParseException {
    requireNonNull(courseid);
    String trimmedCourseid = courseid.trim();
    if (!Courseid.isValidCourseid(courseid)) {
      throw new ParseException(Courseid.MESSAGE_CONSTRAINTS);
    }
    return new Courseid(courseid);
  }

  /**
   * Parses a {@code String studentid} into a {@code Studentid}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code studentid} is invalid.
   */
  public static Studentid parseStudentid(String studentid) throws ParseException {
    requireNonNull(studentid);
    String trimmedStudentid = studentid.trim();
    if (!Studentid.isValidStudentid(studentid)) {
      throw new ParseException(Studentid.MESSAGE_CONSTRAINTS);
    }
    return new Studentid(studentid);
  }

  /**
   * Parses a {@code String teacherid} into a {@code Teacherid}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code teacherid} is invalid.
   */
  public static Teacherid parseTeacherid(String teacherid) throws ParseException {
    requireNonNull(teacherid);
    String trimmedTeacherid = teacherid.trim();
    if (!Teacherid.isValidTeacherid(teacherid)) {
      throw new ParseException(Teacherid.MESSAGE_CONSTRAINTS);
    }
    return new Teacherid(teacherid);
  }


  /**
   * Parses a {@code String amount} into a {@code Amount}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code amount} is invalid.
   */
  public static Amount parseAmount(String amount) throws ParseException {
    requireNonNull(amount);
    String trimmedAmount = amount.trim();
    if (!Amount.isValidAmount(trimmedAmount)) {
      throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
    }
    return new Amount(trimmedAmount);
  }

  /**
   * Parses a {@code String assignedTeacher} into a {@code AssignedTeacher}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code assignedTeacher} is invalid.
   */
  public static AssignedTeacher parseAssignedTeacher(String assignedTeacher) throws ParseException {
    requireNonNull(assignedTeacher);
    String trimmedAssignedTeacher = assignedTeacher.trim();
    if (!AssignedTeacher.isValidAssignedTeacher(assignedTeacher)) {
      throw new ParseException(AssignedTeacher.MESSAGE_CONSTRAINTS);
    }
    return new AssignedTeacher(assignedTeacher);
  }

  /**
   * Parses a {@code String phone} into a {@code Phone}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code phone} is invalid.
   */
  public static Phone parsePhone(String phone) throws ParseException {
    requireNonNull(phone);
    String trimmedPhone = phone.trim();
    if (!Phone.isValidPhone(trimmedPhone)) {
      throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
    }
    return new Phone(trimmedPhone);
  }

  /**
   * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces will
   * be trimmed.
   *
   * @throws ParseException if the given {@code address} is invalid.
   */
  public static Address parseAddress(String address) throws ParseException {
    requireNonNull(address);
    String trimmedAddress = address.trim();
    if (!Address.isValidAddress(trimmedAddress)) {
      throw new ParseException(Address.MESSAGE_CONSTRAINTS);
    }
    return new Address(trimmedAddress);
  }

  /**
   * Parses a {@code String email} into an {@code Email}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code email} is invalid.
   */
  public static Email parseEmail(String email) throws ParseException {
    requireNonNull(email);
    String trimmedEmail = email.trim();
    if (!Email.isValidEmail(trimmedEmail)) {
      throw new ParseException(Email.MESSAGE_CONSTRAINTS);
    }
    return new Email(trimmedEmail);
  }

  /**
   * Parses a {@code String Salary} into an {@code Salary}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code salary} is invalid.
   */
  public static Salary parseSalary(String salary) throws ParseException {
    requireNonNull(salary);
    String trimmedSalary = salary.trim();
    if (!Salary.isValidSalary(trimmedSalary)) {
      throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
    }
    return new Salary(trimmedSalary);
  }

  /**
   * Parses a {@code String deadline} into a {@code Deadline}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code deadline} is invalid.
   */
  public static Deadline parseDeadline(String deadline) throws ParseException {
    requireNonNull(deadline);
    String trimmedDeadline = deadline.trim();
    if (!Deadline.isValidDeadline(trimmedDeadline)) {
      throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
    }
    return new Deadline(trimmedDeadline);
  }

  /**
   * Parses a {@code String date} into a {@code Date}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code date} is invalid.
   */
  public static Date parseDate(String date) throws ParseException {
    requireNonNull(date);
    String trimmedDate = date.trim();
    if (!Date.isValidDate(trimmedDate)) {
      throw new ParseException(Date.MESSAGE_CONSTRAINTS);
    }
    return new Date(trimmedDate);
  }

  /**
   * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be
   * trimmed.
   *
   * @throws ParseException if the given {@code tag} is invalid.
   */
  public static Tag parseTag(String tag) throws ParseException {
    requireNonNull(tag);
    String trimmedTag = tag.trim();
    if (!Tag.isValidTagName(trimmedTag)) {
      throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
    }
    return new Tag(trimmedTag);
  }

  /**
   * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
   */
  public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
    requireNonNull(tags);
    final Set<Tag> tagSet = new HashSet<>();
    for (String tagName : tags) {
      tagSet.add(parseTag(tagName));
    }
    return tagSet;
  }
}
