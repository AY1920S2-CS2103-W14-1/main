package seedu.address.testutil;

import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class StudentAddressBookBuilder {

    private StudentAddressBook studentAddressBook;

    public StudentAddressBookBuilder() {
        studentAddressBook = new StudentAddressBook();
    }

    public StudentAddressBookBuilder(StudentAddressBook studentAddressBook) {
        this.studentAddressBook = studentAddressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public StudentAddressBookBuilder withStudent(Student student) {
        studentAddressBook.add(student);
        return this;
    }

    public StudentAddressBook build() {
        return studentAddressBook;
    }
}
