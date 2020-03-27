package seedu.address.storage.storageCourse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.person.Amount;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.AssignedStudents;
import seedu.address.model.person.AssignedTeacher;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedCourse {

  public static final String MISSING_FIELD_MESSAGE_FORMAT = "Courses's %s field is missing!";

  private final String name;
  private final String courseID;
  private final String amount;
  private final String assignedTeacher;
  private final String assignedStudents;
  private final List<JsonCourseAdaptedTag> tagged = new ArrayList<>();

  /**
   * Constructs a {@code JsonAdaptedPerson} with the given person details.
   */
  @JsonCreator
  public JsonAdaptedCourse(@JsonProperty("name") String name,
      @JsonProperty("courseID") String courseID,
      @JsonProperty("amount") String amount,
      @JsonProperty("assignedTeacher") String assignedTeacher,
      @JsonProperty("assignedStudents") String assignedStudents,
      @JsonProperty("tagged") List<JsonCourseAdaptedTag> tagged) {
    this.name = name;
    this.courseID = courseID;
    this.amount = amount;
    this.assignedTeacher = assignedTeacher;
    this.assignedStudents = assignedStudents;
    if (tagged != null) {
      this.tagged.addAll(tagged);
    }
  }

  /**
   * Converts a given {@code Assignment} into this class for Jackson use.
   */
  public JsonAdaptedCourse(Course source) {
    name = source.getName().fullName;
    courseID = source.getId().value;
    amount = source.getAmount().value;
    assignedTeacher = source.getAssignedTeacher().toString();
    assignedStudents = source.getAssignedStudents().toString();
    tagged.addAll(source.getTags().stream()
        .map(JsonCourseAdaptedTag::new)
        .collect(Collectors.toList()));
  }

  /**
   * Converts this Jackson-friendly adapted Assignment object into the model's {@code Assignment} object.
   *
   * @throws IllegalValueException if there were any data constraints violated in the adapted
   *                               Assignment.
   */
  public Course toModelType() throws IllegalValueException {
    final List<Tag> CourseTags = new ArrayList<>();
    for (JsonCourseAdaptedTag tag : tagged) {
      CourseTags.add(tag.toModelType());
    }

    if (name == null) {
      throw new IllegalValueException(
          String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
    }
    if (!Name.isValidName(name)) {
      throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
    }
    final Name modelName = new Name(name);

    if (courseID == null) {
      throw new IllegalValueException(
          String.format(MISSING_FIELD_MESSAGE_FORMAT, ID.class.getSimpleName()));
    }
    if (!ID.isValidId(courseID)) {
      throw new IllegalValueException(ID.MESSAGE_CONSTRAINTS);
    }
    final ID modelId = new ID(courseID);

    if (amount == null) {
      throw new IllegalValueException(
          String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
    }
    if (!Amount.isValidAmount(amount)) {
      throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
    }
    final Amount modelAmount = new Amount(amount);

    if (assignedTeacher == null) {
      throw new IllegalValueException(
          String.format(MISSING_FIELD_MESSAGE_FORMAT, AssignedTeacher.class.getSimpleName()));
    }
    if (!AssignedTeacher.isValidAssignedTeacher(assignedTeacher)) {
      throw new IllegalValueException(AssignedTeacher.MESSAGE_CONSTRAINTS);
    }
    final AssignedTeacher modelAssignedTeacher = new AssignedTeacher(assignedTeacher);

    if (assignedStudents == null) {
      throw new IllegalValueException(
          String.format(MISSING_FIELD_MESSAGE_FORMAT, AssignedStudents.class.getSimpleName()));
    }
    if (!AssignedStudents.isValidAssignedStudents(assignedStudents)) {
      throw new IllegalValueException(AssignedStudents.MESSAGE_CONSTRAINTS);
    }
    final AssignedStudents modelAssignedStudents = new AssignedStudents(assignedStudents);

    final Set<Tag> modelTags = new HashSet<>(CourseTags);
    return new Course(modelName, modelId, modelAmount, modelAssignedTeacher, modelAssignedStudents, modelTags);
  }

}
