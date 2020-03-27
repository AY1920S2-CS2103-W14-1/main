package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TeacherAddressBookBuilder {

    private TeacherAddressBook teacherAddressBook;

    public TeacherAddressBookBuilder() {
        teacherAddressBook = new TeacherAddressBook();
    }

    public TeacherAddressBookBuilder(TeacherAddressBook teacherAddressBook) {
        this.teacherAddressBook = teacherAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TeacherAddressBookBuilder withTeacher(Teacher teacher) {
        teacherAddressBook.add(teacher);
        return this;
    }

    public TeacherAddressBook build() {
        return teacherAddressBook;
    }
}
