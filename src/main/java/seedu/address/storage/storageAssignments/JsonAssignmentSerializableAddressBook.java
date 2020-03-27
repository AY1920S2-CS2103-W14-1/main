package seedu.address.storage.storageAssignments;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "assignmentaddressbook")
class JsonAssignmentSerializableAddressBook {

  public static final String MESSAGE_DUPLICATE_COURSE = "Assignment list contains duplicate assignment(s).";

  private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();

  /**
   * Constructs a {@code JsonSerializableAddressBook} with the given courses.
   */
  @JsonCreator
  public JsonAssignmentSerializableAddressBook(
      @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments) {
    this.assignments.addAll(assignments);
  }

  /**
   * Converts a given {@code ReadOnlyAssignmentsAddressBook} into this class for Jackson use.
   *
   * @param source future changes to this will not affect the created {@code
   *               JsonAssignmentSerializableAddressBook}.
   */
  public JsonAssignmentSerializableAddressBook(ReadOnlyAddressBookGeneric<Assignment> source) {
    assignments.addAll(source.getList().stream().map(
        JsonAdaptedAssignment::new).collect(Collectors.toList()));
  }

  /**
   * Converts this address book into the model's {@code AddressBook} object.
   *
   * @throws IllegalValueException if there were any data constraints violated.
   */
  public AssignmentAddressBook toModelType() throws IllegalValueException {
    AssignmentAddressBook assignmentAddressBook = new AssignmentAddressBook();
    for (JsonAdaptedAssignment jsonAdaptedAssignment : assignments) {
      Assignment assignment = jsonAdaptedAssignment.toModelType();
      if (assignmentAddressBook.has(assignment)) {
        throw new IllegalValueException(MESSAGE_DUPLICATE_COURSE);
      }
      assignmentAddressBook.add(assignment);
    }
    return assignmentAddressBook;
  }

}
