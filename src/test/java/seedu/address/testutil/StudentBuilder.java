package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ID = "100";
    public static final String DEFAULT_ASSIGNEDCOURSES = "";

    private Name name;
    private ID id;
    private AssignedCourses assignedCourses;
    private Set<Tag> tags;

    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new ID(DEFAULT_ID);
        assignedCourses = new AssignedCourses(DEFAULT_ASSIGNEDCOURSES);
        tags = new HashSet<>();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        id = studentToCopy.getID();
        assignedCourses = studentToCopy.getAssignedCourses();
        tags = new HashSet<>(studentToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code Student} that we are building.
     */
    public StudentBuilder withID(String id) {
        this.id = new ID(id);
        return this;
    }

    /**
     * Sets the {@code AssignedCourse} of the {@code Student} that we are building.
     */
    public StudentBuilder withAssignedCourse(String assignedCourse) {
        this.assignedCourses = new AssignedCourses(assignedCourse);
        return this;
    }

    public Student build() {
        return new Student(name, id, assignedCourses, tags);
    }

}
