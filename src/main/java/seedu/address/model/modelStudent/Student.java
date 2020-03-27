package seedu.address.model.modelStudent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.AssignedCourse;
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
  private final Set<Tag> tags = new HashSet<>();
  private String assignedCourses = "";

  /**
   * Every field must be present and not null.
   */
  public Student(Name name, ID id, Set<Tag> tags) {
    requireAllNonNull(name, id, tags);
    this.name = name;
    this.id = id;
    this.tags.addAll(tags);
  }

  public Name getName() {
    return name;
  }

  public ID getID() {
    return id;
  }

  /**
   * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
   * modification is attempted.
   */
  public Set<Tag> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  public void setAssignedCourses(String assignedCourses){
    this.assignedCourses = assignedCourses;
  }

  public String getAssignedCourses(){
    return this.assignedCourses;
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
        && otherStudentCast.getID().equals(getID());
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
    return otherStudent.getName().equals(getName())
        && otherStudent.getID().equals(getID())
        && otherStudent.getTags().equals(getTags());
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
        .append(getID())
        .append(" Tags: ");
    getTags().forEach(builder::append);
    return builder.toString();
  }

}
