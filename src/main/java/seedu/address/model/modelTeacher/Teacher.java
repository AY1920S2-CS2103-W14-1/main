package seedu.address.model.modelTeacher;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Represents a Teacher in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Teacher extends Person {

  private final Salary salary;

  /**
   * Every field must be present and not null.
   */
  public Teacher(Name name, Phone phone, Email email, Salary salary, Address address,
      Set<Tag> tags) {
    super(name, phone, email, address, tags);
    this.salary = salary;
  }

  /**
   * Get salary of the teacher.
   */
  public Salary getSalary() {
    return salary;
  }

  /**
   * Returns true if both teachers of the same name have at least one other identity field that is
   * the same. This defines a weaker notion of equality between two teachers.
   */
  @Override
  public boolean weakEquals(ModelObject otherTeacher) {
    if (otherTeacher == this) {
      return true;
    }
    if (otherTeacher instanceof Teacher == false) {
      return false;
    }
    Teacher otherTeacherCast = (Teacher)otherTeacher;
    return otherTeacherCast != null
        && otherTeacherCast.getName().equals(getName())
        && (otherTeacherCast.getPhone().equals(getPhone()) || (
        otherTeacherCast.getSalary().equals(getSalary()) || otherTeacherCast.getEmail()
            .equals(getEmail())));
  }


  /**
   * Returns true if both teachers have the same identity and data fields. This defines a stronger
   * notion of equality between two teachers.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof Teacher)) {
      return false;
    }

    Teacher otherTeacher = (Teacher) other;
    return otherTeacher.getName().equals(getName())
        && otherTeacher.getPhone().equals(getPhone())
        && otherTeacher.getEmail().equals(getEmail())
        && otherTeacher.getSalary().equals(getSalary())
        && otherTeacher.getAddress().equals(getAddress())
        && otherTeacher.getTags().equals(getTags());
  }

/*
  How to write hashCode ???
  @Override
  public int hashCode() {
    // use this method for custom fields hashing instead of implementing your own
    return Objects.hash(name, phone, email, salary, address, tags);
  }
*/
  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(getName());
    if (getPhone().isKnown()) {
      builder.append(" Phone: ").append(getPhone());
    }
    if (getEmail().isKnown()) {
      builder.append(" Email: ").append(getEmail());
    }
    builder.append(" Salary: ").append(getSalary());
    if (getAddress().isKnown()) {
      builder.append(" Address: ").append(getAddress());
    }
    if (hasTags()) {
      builder.append(" Tags: ");
      getTags().forEach(builder::append);
    }
    return builder.toString();
  }
}
