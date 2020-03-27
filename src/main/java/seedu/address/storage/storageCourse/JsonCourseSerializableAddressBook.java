package seedu.address.storage.storageCourse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "courseaddressbook")
class JsonCourseSerializableAddressBook {

  public static final String MESSAGE_DUPLICATE_COURSE = "Courses list contains duplicate course(s).";

  private final List<JsonAdaptedCourse> courses = new ArrayList<>();

  /**
   * Constructs a {@code JsonSerializableAddressBook} with the given courses.
   */
  @JsonCreator
  public JsonCourseSerializableAddressBook(
      @JsonProperty("courses") List<JsonAdaptedCourse> courses) {
    this.courses.addAll(courses);
  }

  /**
   * Converts a given {@codeReadOnlyAddressBookGeneric<Course>} into this class for Jackson use.
   *
   * @param source future changes to this will not affect the created {@code
   *               JsonCourseSerializableAddressBook}.
   */
  public JsonCourseSerializableAddressBook(ReadOnlyAddressBookGeneric<Course> source) {
    courses.addAll(source.getList().stream().map(
        JsonAdaptedCourse::new).collect(Collectors.toList()));
  }

  /**
   * Converts this address book into the model's {@code AddressBook} object.
   *
   * @throws IllegalValueException if there were any data constraints violated.
   */
  public CourseAddressBook toModelType() throws IllegalValueException {
    CourseAddressBook courseAddressBook = new CourseAddressBook();
    for (JsonAdaptedCourse jsonAdaptedCourse : courses) {
      Course course = jsonAdaptedCourse.toModelType();
      if (courseAddressBook.has(course)) {
        throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
      }
      courseAddressBook.add(course);
    }
    return courseAddressBook;
  }

}
