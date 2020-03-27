package seedu.address.model.modelCourse;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Amount;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.AssignedStudents;
import seedu.address.model.person.AssignedTeacher;
import seedu.address.model.person.Courseid;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Studentid;
import seedu.address.model.person.Teacherid;
import seedu.address.model.person.exceptions.CourseNotFoundException;
import seedu.address.model.person.exceptions.DuplicateCourseException;
import seedu.address.model.tag.Tag;

/**
 * Represents a Course in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Course extends ModelObject {

  // Identity fields
  private final String ENTITY_NAME = "Course";
  private final Name name;
  private final ID id;
  private final Set<Tag> tags = new HashSet<>();
  private Amount amount;
  private AssignedTeacher assignedTeacher;
  private AssignedStudents assignedStudents;
  private String assignedTeacherWithName;
  private String assignedStudentsWithNames;
  /**
   * Every field must be present and not null.
   */
  public Course(Name name, ID id, Amount amount, AssignedTeacher assignedTeacher, AssignedStudents assignedStudents, Set<Tag> tags) {
    requireAllNonNull(name, id, tags);
    this.name = name;
    this.id = id;
    this.amount = amount;
    this.assignedTeacher = assignedTeacher;
    this.assignedStudents = assignedStudents;
    this.tags.addAll(tags);
    this.assignedTeacherWithName = "None";
    this.assignedStudentsWithNames = "None";

    if (assignedStudents == null){
      this.assignedStudents = new AssignedStudents("");
    }
  }

  public Name getName() {
    return name;
  }

  public ID getId() {
    return id;
  }

  public Amount getAmount() {
    return amount;
  }

  public AssignedTeacher getAssignedTeacher() {
    return assignedTeacher;
  }


  public void addStudent(Studentid studentid) {
    if (this.assignedStudents.toString().equals("")) {
      this.assignedStudents = new AssignedStudents(studentid.toString());
    } else {
      this.assignedStudents = new AssignedStudents(this.assignedStudents.toString() + "," + studentid.toString());
    }
  }

  public void assignTeacher(Teacherid teacherid) {
    this.assignedTeacher = new AssignedTeacher(teacherid.toString());
  }

  public AssignedStudents getAssignedStudents(){
    return this.assignedStudents;
  }

  public String getAssignedStudentsWithNames(){
    return this.assignedStudentsWithNames;
  }

  public String getAssignedTeacherWithName(){
    return this.assignedTeacherWithName;
  }
  /**
   * Converts internal list of assigned teacher ID into the name with the ID
   */
  public void processAssignedTeacher(FilteredList<Teacher> filteredTeachers){
    this.assignedTeacherWithName = "None";
    for (Teacher teacher : filteredTeachers) {
      if (teacher.getID().toString().equals(this.assignedTeacher.toString())) {
        this.assignedTeacherWithName = teacher.getName().toString() + "(" + teacher.getID().toString() + ")";
      }
    }
  }

  /**
   * Converts internal list of assigned student IDs into the name with the IDs
   */
  public void processAssignedStudents(FilteredList<Student> filteredStudents){
    String[] studentids = this.assignedStudents.toString().split(",");
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < studentids.length; i++) {
      String studentid = studentids[i];
      for (Student student : filteredStudents) {
        if (studentid.equals(student.getID().toString())) {
          String comma = ", ";
          if (i == studentids.length - 1) {
            comma = "";
          }
          s.append(student.getName().toString()).append("(").append(studentid).append(")").append(comma);
        }
      }
    }

    if (s.toString().equals("")) {
      this.assignedStudentsWithNames = "None";
    } else {
      this.assignedStudentsWithNames = "[" + s.toString() + "]";
    }
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
  public boolean weakEquals(ModelObject otherCourse) {
    if (otherCourse == this) {
      return true;
    }

    if (otherCourse instanceof Course == false) {
      return false;
    }
    Course otherCourseCast = (Course) otherCourse;
    return otherCourseCast != null
//        && otherCourse.getName().equals(getName())
            && otherCourseCast.getId().equals(getId());
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

    if (!(other instanceof Course)) {
      return false;
    }

    Course otherCourse = (Course) other;
    return otherCourse.getName().equals(getName())
        && otherCourse.getId().equals(getId())
        && otherCourse.getAmount().equals(getAmount())
        && otherCourse.getAssignedTeacher().equals(getAssignedTeacher())
        && otherCourse.getAssignedStudents().equals(getAssignedStudents())
        && otherCourse.getTags().equals(getTags());
  }

  @Override
  public int hashCode() {
    // use this method for custom fields hashing instead of implementing your own
    return Objects.hash(name, id, amount, tags);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(getName())
        .append(" ID: ")
        .append(getId())
        .append(" Amount: ")
        .append(getAmount())
        .append(" AssignedTeacher: ")
        .append(getAssignedTeacher())
        .append(" Tags: ");
    getTags().forEach(builder::append);
    return builder.toString();
  }

}
