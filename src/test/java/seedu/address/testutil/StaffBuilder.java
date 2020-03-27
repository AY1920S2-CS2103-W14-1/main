package seedu.address.testutil;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.modelStaff.Staff;
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
public class StaffBuilder {

    public static final String DEFAULT_NAME = "Muted Alice";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_SALARY = "3000";
    public static final String DEFAULT_ADDRESS = "CityHall";

    private Name name;
    private Phone phone;
    private Email email;
    private Salary salary;
    private Address address;
    private Set<Tag> tags;

    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        salary = new Salary(DEFAULT_SALARY);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the TeacherBuilder with the data of {@code teacherToCopy}.
     */
    public StaffBuilder(Teacher teacherToCopy) {
        name = teacherToCopy.getName();
        phone = teacherToCopy.getPhone();
        email = teacherToCopy.getEmail();
        salary = teacherToCopy.getSalary();
        address = teacherToCopy.getAddress();
        tags = new HashSet<>(teacherToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Teacher} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Teacher} that we are building.
     */
    public StaffBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Staff} that we are building.
     */
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Staff} that we are building.
     */
    public StaffBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, salary, address, tags);
    }

}
