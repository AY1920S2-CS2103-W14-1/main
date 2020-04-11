package seedu.address.model.modelStudent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import seedu.address.commons.core.UuidManager;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.Gender;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a Teacher in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Student extends ModelObject {

  // Identity fields
  private final Name name;
  private final ID id;
  private final Gender gender;
  private Set<ID> assignedCoursesID = new HashSet<>();
  private final Set<Tag> tags = new HashSet<>();

  /**
   * Every field must be present and not null.
   */
  public Student(Name name, Gender gender,  Set<Tag> tags) throws ParseException {
    requireAllNonNull(name, tags);
    this.name = name;
    this.id = UuidManager.assignNewUUID(this);
    this.gender = gender;
    this.tags.addAll(tags);
  }

  public Student(Name name, ID id, Gender gender, Set<Tag> tags) {
    requireAllNonNull(name, id, tags);
    this.name = name;
    this.id = id;
    this.gender = gender;
    this.tags.addAll(tags);
  }

  public Student(Name name, ID id, Gender gender, Set<ID> assignedCoursesID, Set<Tag> tags) {
    requireAllNonNull(name, id, tags);
    this.name = name;
    this.id = id;
    this.gender = gender;
    this.assignedCoursesID.addAll(assignedCoursesID);
    this.tags.addAll(tags);
  }

  public Student clone() {
    Student cloned = new Student(name, id, gender, assignedCoursesID, tags);
    return cloned;
  }

  public Name getName() {
    return name;
  }

  public ID getId() {
    return id;
  }

  /**
   * Get Gender of a staff
   */
  public Gender getGender() {
    return gender;
  }

  public boolean containsCourse(ID courseID) {
    if (this.assignedCoursesID.contains(courseID)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns an immutable ID set, which throws {@code UnsupportedOperationException} if
   * modification is attempted.
   */
  public Set<ID> getAssignedCoursesID() {
    return Collections.unmodifiableSet(assignedCoursesID);
  }

  /**
   * Get List of String of the ID
   * @return Array of String
   */
  public List<String> getAssignedCoursesIDString() {
    List<String> IDList = new ArrayList<>();
    for (ID id : assignedCoursesID) {
      IDList.add(id.toString());
    }
    return IDList;
  }

  /**
   * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
   * modification is attempted.
   */
  public Set<Tag> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  public void addCourse(ID courseid) {
    this.assignedCoursesID.add(courseid);
  }

  public void removeCourse(ID courseid) {
    this.assignedCoursesID.remove(courseid);
  }

  /**
   * Returns true if both students of the same name have at least one other identity field that is
   * the same. This defines a weaker notion of equality between two students.
   */
  public boolean weakEquals(ModelObject otherStudent) {
    if (otherStudent == this) {
      return true;
    }

    if (otherStudent instanceof Student == false) {
      return false;
    }

    Student otherStudentCast = (Student)otherStudent;
    return otherStudentCast != null
        && otherStudentCast.getName().equals(getName())
        && otherStudentCast.getId().equals(getId())
        && otherStudentCast.getTags().equals(getTags());
  }

  /**
   * Returns true if both students have the same identity and data fields. This defines a stronger
   * notion of equality between two students.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Student)) {
      return false;
    }

    Student otherStudent = (Student) other;
    return otherStudent.getId().equals(getId());
  }

  @Override
  public int hashCode() {
    // use this method for custom fields hashing instead of implementing your own
    return Objects.hash(name, id, tags);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(getName())
        .append(" ID: ")
        .append(getId())
        .append(" Tags: ");
    getTags().forEach(builder::append);
    return builder.toString();
  }

}
