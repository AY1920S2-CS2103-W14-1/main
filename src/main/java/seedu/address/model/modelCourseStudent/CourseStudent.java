package seedu.address.model.modelCourseStudent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.exceptions.DuplicateException;
import seedu.address.commons.exceptions.NotFoundException;
import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.Courseid;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Studentid;
import seedu.address.model.person.exceptions.CourseNotFoundException;
import seedu.address.model.person.exceptions.CourseStudentNotFoundException;
import seedu.address.model.person.exceptions.DuplicateCourseException;
import seedu.address.model.person.exceptions.DuplicateCourseStudentException;
import seedu.address.model.tag.Tag;

/**
 * Represents a CourseStudent in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class CourseStudent extends ModelObject {

  // Identity fields
  private final String ENTITY_NAME = "CourseStudent";
  private final Courseid courseid;
  private final Studentid studentid;
  private final Set<Tag> tags = new HashSet<>();

  /**
   * Every field must be present and not null.
   */
  public CourseStudent(Courseid courseid, Studentid studentid, Set<Tag> tags) {
    requireAllNonNull(courseid, studentid, tags);
    this.courseid = courseid;
    this.studentid = studentid;
    this.tags.addAll(tags);
  }

  public Courseid getCourseid() {
    return courseid;
  }

  public Studentid getStudentid() {
    return studentid;
  }



  /**
   * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
   * modification is attempted.
   */
  public Set<Tag> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  /**
   * Returns true if both courses of the same name have at least one other identity field that is
   * the same. This defines a weaker notion of equality between two courses.
   */
  @Override
  public boolean weakEquals(ModelObject otherCourseStudent) {
    if (otherCourseStudent == this) {
      return true;
    }

    if (otherCourseStudent instanceof CourseStudent == false) {
      return false;
    }

    CourseStudent otherCourseStudentCast = (CourseStudent) otherCourseStudent;
    return otherCourseStudentCast != null
        && otherCourseStudentCast.getCourseid().equals(getCourseid())
        && otherCourseStudentCast.getStudentid().equals(getStudentid());
  }

  /**
   * Returns true if both courses have the same identity and data fields. This defines a stronger
   * notion of equality between two courses.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof CourseStudent)) {
      return false;
    }

    CourseStudent otherCourseStudent = (CourseStudent) other;
    return otherCourseStudent.getCourseid().equals(getCourseid())
        && otherCourseStudent.getStudentid().equals(getStudentid())
        && otherCourseStudent.getTags().equals(getTags());
  }

  @Override
  public int hashCode() {
    // use this method for custom fields hashing instead of implementing your own
    return Objects.hash(courseid, studentid, tags);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("\n")
            .append("Course ID: ")
            .append(getCourseid())
            .append("\n")
            .append("Student ID: ")
            .append(getStudentid())
            .append("\n")
            .append("\n")
            .append(" Tags: ");
    getTags().forEach(builder::append);
    return builder.toString();
  }
}
