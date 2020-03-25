package seedu.address.model.modelStaff;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Represents a Teacher in the address book. Guarantees: details are present and not null, field
 * values are validated, immutable.
 */
public class Staff extends Person {

    private final Salary salary;

    /**
     * Every field must be present and not null.
     */
    public Staff(Name name, Phone phone, Email email, Salary salary, Address address,
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
    public boolean isSameStaff(seedu.address.model.modelStaff.Staff otherStaff) {
        if (otherStaff == this) {
            return true;
        }

        return otherStaff != null
                && otherStaff.getName().equals(getName())
                && (otherStaff.getPhone().equals(getPhone()) || (
                otherStaff.getSalary().equals(getSalary()) || otherStaff.getEmail()
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

        if (!(other instanceof seedu.address.model.modelStaff.Staff)) {
            return false;
        }

        seedu.address.model.modelStaff.Staff otherStaff = (seedu.address.model.modelStaff.Staff) other;
        return otherStaff.getName().equals(getName())
                && otherStaff.getPhone().equals(getPhone())
                && otherStaff.getEmail().equals(getEmail())
                && otherStaff.getSalary().equals(getSalary())
                && otherStaff.getAddress().equals(getAddress())
                && otherStaff.getTags().equals(getTags());
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
