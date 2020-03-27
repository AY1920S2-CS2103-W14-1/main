package seedu.address.testutil;

import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelCourseStudent.CourseStudentAddressBook;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class CourseStudentAddressBookBuilder {

    private CourseStudentAddressBook courseStudentAddressBook;

    public CourseStudentAddressBookBuilder() {
        courseStudentAddressBook = new CourseStudentAddressBook();
    }

    public CourseStudentAddressBookBuilder(CourseStudentAddressBook courseStudentAddressBook) {
        this.courseStudentAddressBook = courseStudentAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public CourseStudentAddressBookBuilder withCourseStudent(CourseStudent courseStudent) {
        courseStudentAddressBook.add(courseStudent);
        return this;
    }

    public CourseStudentAddressBook build() {
        return courseStudentAddressBook;
    }
}
