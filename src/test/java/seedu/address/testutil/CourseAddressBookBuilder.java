package seedu.address.testutil;

import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class CourseAddressBookBuilder {

    private CourseAddressBook courseAddressBook;

    public CourseAddressBookBuilder() {
        courseAddressBook = new CourseAddressBook();
    }

    public CourseAddressBookBuilder(CourseAddressBook courseAddressBook) {
        this.courseAddressBook = courseAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public CourseAddressBookBuilder withCourse(Course course) {
        courseAddressBook.add(course);
        return this;
    }

    public CourseAddressBook build() {
        return courseAddressBook;
    }
}
