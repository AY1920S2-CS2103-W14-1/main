package seedu.address.testutil;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.HashSet;
import java.util.Set;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.person.Address;
import seedu.address.model.person.AssignedCourses;
import seedu.address.model.person.Email;
import seedu.address.model.person.ID;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Teacher objects.
 */
public class TeacherBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ID = "21";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_SALARY = "1000";
    public static final String DEFAULT_ASSIGNEDCOURSES = "";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private ID id;
    private Phone phone;
    private Email email;
    private Salary salary;
    private Address address;
    private AssignedCourses assignedCourses;
    private Set<Tag> tags;

    public TeacherBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new ID(DEFAULT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        salary = new Salary(DEFAULT_SALARY);
        assignedCourses = new AssignedCourses(DEFAULT_ASSIGNEDCOURSES);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TeacherBuilder with the data of {@code teacherToCopy}.
     */
    public TeacherBuilder(Teacher teacherToCopy) {
        name = teacherToCopy.getName();
        id = teacherToCopy.getID();
        phone = teacherToCopy.getPhone();
        email = teacherToCopy.getEmail();
        salary = teacherToCopy.getSalary();
        assignedCourses = teacherToCopy.getAssignedCourses();
        address = teacherToCopy.getAddress();
        tags = new HashSet<>(teacherToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code ID} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withID(String id) {
        this.id = new ID(id);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Teacher} that we are building.
     */
    public TeacherBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code AssignedCourses} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withAssignedCourses(String assignedCourses) {
        this.assignedCourses = new AssignedCourses(assignedCourses);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Teacher} that we are building.
     */
    public TeacherBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    public Teacher build() {
        return new Teacher(name, id, phone, email, salary, address, assignedCourses, tags);
    }

}
