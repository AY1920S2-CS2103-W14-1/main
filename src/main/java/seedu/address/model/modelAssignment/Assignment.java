package seedu.address.model.modelAssignment;

import seedu.address.model.modelGeneric.ModelObject;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Assignment in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Assignment extends ModelObject {

  // Identity fields
  private final String ENTITY_NAME = "assignment";
  private final Name name;
  private final ID assignmentId;
  private final Deadline deadline;
  private final Set<Tag> tags = new HashSet<>();

  /**
   * Every field must be present and not null.
   */
  public Assignment(Name name, ID id, Deadline deadline, Set<Tag> tags) {
    requireAllNonNull(name, id, deadline, tags);
    this.name = name;
    this.assignmentId = id;
    this.deadline = deadline;
    this.tags.addAll(tags);
  }

  public Name getName() {
    return name;
  }

  public ID getId() {
    return assignmentId;
  }

  public Deadline getDeadline() {
    return deadline;
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
  public boolean weakEquals(ModelObject otherAssignment) {
    if (otherAssignment == this) {
      return true;
    }

    if (otherAssignment instanceof Assignment == false) {
      return false;
    }
    Assignment otherAssignmentCast = (Assignment)otherAssignment;
    return otherAssignmentCast != null
        && otherAssignmentCast.getName().equals(getName())
        && otherAssignmentCast.getId().equals(getId());
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

    if (!(other instanceof Assignment)) {
      return false;
    }

    Assignment otherAssignment = (Assignment) other;
    return otherAssignment.getName().equals(getName())
        && otherAssignment.getId().equals(getId())
        && otherAssignment.getDeadline().equals(getDeadline())
        && otherAssignment.getTags().equals(getTags());
  }

  @Override
  public int hashCode() {
    // use this method for custom fields hashing instead of implementing your own
    return Objects.hash(name, assignmentId, deadline, tags);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("\n")
            .append("Assignment: ")
            .append(getName())
            .append("\n")
            .append("Assignment ID: ")
            .append(getId())
            .append("\n")
            .append("Deadline: ")
            .append(getDeadline().toString())
            .append("\n")
            .append(" Tags: ");
    getTags().forEach(builder::append);
    return builder.toString();
  }
}
