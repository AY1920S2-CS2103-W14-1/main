package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.person.Amount;
import seedu.address.model.person.AssignedStudents;
import seedu.address.model.person.AssignedTeacher;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Course objects.
 */
public class CourseBuilder {

    public static final String DEFAULT_NAME = "Java Programming";
    public static final String DEFAULT_ID = "102";
    public static final String DEFAULT_AMOUNT = "100";
    public static final String DEFAULT_ASSIGNEDTEACHER = "";
    public static final String DEFAULT_ASSIGNEDSTUDENTS = "";

    private Name name;
    private ID id;
    private Amount amount;
    private AssignedTeacher assignedTeacher;
    private AssignedStudents assignedStudents;
    private Set<Tag> tags;

    public CourseBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new ID(DEFAULT_ID);
        amount = new Amount(DEFAULT_AMOUNT);
        assignedTeacher = new AssignedTeacher(DEFAULT_ASSIGNEDTEACHER);
        assignedStudents = new AssignedStudents(DEFAULT_ASSIGNEDSTUDENTS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the CourseBuilder with the data of {@code courseToCopy}.
     */
    public CourseBuilder(Course courseToCopy) {
        name = courseToCopy.getName();
        id = courseToCopy.getId();
        amount = courseToCopy.getAmount();
        assignedTeacher = courseToCopy.getAssignedTeacher();
        assignedStudents = courseToCopy.getAssignedStudents();
        tags = new HashSet<>(courseToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public CourseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Assignment} that we are building.
     */
    public CourseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code Assignment} that we are building.
     */
    public CourseBuilder withID(String id) {
        this.id = new ID(id);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Assignment} that we are building.
     */
    public CourseBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code AssignedStudents} of the {@code Assignment} that we are building.
     */
    public CourseBuilder withAssignedStudent(String assignedStudent) {
        this.assignedStudents = new AssignedStudents(assignedStudent);
        return this;
    }

    /**
     * Sets the {@code AssignedTeacher} of the {@code Assignment} that we are building.
     */
    public CourseBuilder withAssignedTeacher(String assignedTeacher) {
        this.assignedTeacher = new AssignedTeacher(assignedTeacher);
        return this;
    }

    public Course build() {
        return new Course(name, id, amount, assignedTeacher, assignedStudents, tags);
    }

}
