package seedu.address.storage.storageTeacher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "teacheraddressbook")
class JsonTeacherSerializableAddressBook {

  public static final String MESSAGE_DUPLICATE_TEACHER = "Teachers list contains duplicate teacher(s).";

  private final List<JsonAdaptedTeacher> teachers = new ArrayList<>();

  /**
   * Constructs a {@code JsonSerializableAddressBook} with the given teachers.
   */
  @JsonCreator
  public JsonTeacherSerializableAddressBook(
      @JsonProperty("teachers") List<JsonAdaptedTeacher> teachers) {
    this.teachers.addAll(teachers);
  }

  /**
   * Converts a given {@code ReadOnlyAddressBookGeneric<Teacher>} into this class for Jackson use.
   *
   * @param source future changes to this will not affect the created {@code
   *               JsonTeacherSerializableAddressBook}.
   */
  public JsonTeacherSerializableAddressBook(ReadOnlyAddressBookGeneric<Teacher> source) {
    teachers.addAll(
        source.getList().stream().map(JsonAdaptedTeacher::new).collect(Collectors.toList()));
  }

  /**
   * Converts this address book into the model's {@code AddressBook} object.
   *
   * @throws IllegalValueException if there were any data constraints violated.
   */
  public TeacherAddressBook toModelType() throws IllegalValueException {
    TeacherAddressBook teacherAddressBook = new TeacherAddressBook();
    for (JsonAdaptedTeacher jsonAdaptedTeacher : teachers) {
      Teacher teacher = jsonAdaptedTeacher.toModelType();
      if (teacherAddressBook.has(teacher)) {
        throw new IllegalValueException(MESSAGE_DUPLICATE_TEACHER);
      }
      teacherAddressBook.add(teacher);
    }
    return teacherAddressBook;
  }

}
