package seedu.address.storage.storageCourseStudent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelCourseStudent.CourseStudentAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "courseStudentaddressbook")
class JsonCourseStudentSerializableAddressBook {

  public static final String MESSAGE_DUPLICATE_COURSE = "CourseStudent list contains duplicate courseStudent(s).";

  private final List<JsonAdaptedCourseStudent> courseStudents = new ArrayList<>();

  /**
   * Constructs a {@code JsonSerializableAddressBook} with the given courses.
   */
  @JsonCreator
  public JsonCourseStudentSerializableAddressBook(
      @JsonProperty("courseStudents") List<JsonAdaptedCourseStudent> courseStudents) {
    this.courseStudents.addAll(courseStudents);
  }

  /**
   * Converts a given {@code ReadOnlyCourseStudentsAddressBook} into this class for Jackson use.
   *
   * @param source future changes to this will not affect the created {@code
   *               JsonCourseStudentSerializableAddressBook}.
   */
  public JsonCourseStudentSerializableAddressBook(ReadOnlyAddressBookGeneric<CourseStudent> source) {
    courseStudents.addAll(source.getList().stream().map(
        JsonAdaptedCourseStudent::new).collect(Collectors.toList()));
  }

  /**
   * Converts this address book into the model's {@code AddressBook} object.
   *
   * @throws IllegalValueException if there were any data constraints violated.
   */
  public CourseStudentAddressBook toModelType() throws IllegalValueException {
    CourseStudentAddressBook courseStudentAddressBook = new CourseStudentAddressBook();
    for (JsonAdaptedCourseStudent jsonAdaptedCourseStudent : courseStudents) {
      CourseStudent courseStudent = jsonAdaptedCourseStudent.toModelType();
      if (courseStudentAddressBook.has(courseStudent)) {
        throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
      }
      courseStudentAddressBook.add(courseStudent);
    }
    return courseStudentAddressBook;
  }

}
