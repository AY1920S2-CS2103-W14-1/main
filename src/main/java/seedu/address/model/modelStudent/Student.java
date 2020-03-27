package seedu.address.model.modelStudent;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.Courseid;
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
  private AssignedCourses assignedCourses;
  private String assignedCoursesWithNames;
  private final Set<Tag> tags = new HashSet<>();

  /**
   * Every field must be present and not null.
   */
  public Student(Name name, ID id, AssignedCourses assignedCourses,  Set<Tag> tags) {
    requireAllNonNull(name, id, tags);
    this.name = name;
    this.id = id;
    this.assignedCourses = assignedCourses;
    this.assignedCoursesWithNames = "None";
    this.tags.addAll(tags);

    if (assignedCourses == null){
      this.assignedCourses = new AssignedCourses("");
    }
  }

  public Name getName() {
    return name;
  }

  public ID getID() {
    return id;
  }

  public AssignedCourses getAssignedCourses() {
    return assignedCourses;
  }

  public String getAssignedCoursesWithNames(){
    return this.assignedCoursesWithNames;
  }
  /**
   * Converts internal list of assigned student IDs into the name with the IDs
   */
  public void processAssignedCourses(FilteredList<Course> filteredCourses){
    String[] courseids = this.assignedCourses.toString().split(",");
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < courseids.length; i++) {
      String courseid = courseids[i];
      for (Course course : filteredCourses) {
        if (courseid.equals(course.getId().toString())) {
          String comma = ", ";
          if (i == courseids.length - 1) {
            comma = "";
          }
          s.append(course.getName().toString()).append("(").append(courseid).append(")").append(comma);
        }
      }
    }

    if (s.toString().equals("")) {
      this.assignedCoursesWithNames = "None";
    } else {
      this.assignedCoursesWithNames = "[" + s.toString() + "]";
    }
  }

  /**
   * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if
   * modification is attempted.
   */
  public Set<Tag> getTags() {
    return Collections.unmodifiableSet(tags);
  }

  public void addCourse(Courseid courseid) {
    if (this.assignedCourses.toString().equals("")) {
      this.assignedCourses = new AssignedCourses(courseid.toString());
    } else {
      this.assignedCourses = new AssignedCourses(this.assignedCourses.toString() + "," + courseid.toString());
    }
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
