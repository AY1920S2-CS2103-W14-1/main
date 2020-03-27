package seedu.address.testutil;

import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalStaff {
    public static final Staff STAFF_ALICE = new StaffBuilder().withName("Muted Alice")
            .withAddress("CityHall").withEmail("alice@example.com")
            .withSalary("3000")
            .withPhone("94351253")
            .withTags("staff").build();

    public static final Staff STAFF_BENSON = new StaffBuilder().withName("Muted Benson")
            .withAddress("CityHall").withEmail("benson@example.com")
            .withSalary("3000")
            .withPhone("12345678")
            .withTags("staff").build();

    private TypicalStaff() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical staffs.
     */
    public static StaffAddressBook getTypicalStaffAddressBook() {
        StaffAddressBook ab = new StaffAddressBook();
        for (Staff staff : getTypicalStaffs()) {
            ab.add(staff);
        }
        return ab;
    }

    public static List<Staff> getTypicalStaffs() {
        return new ArrayList<>(Arrays.asList(STAFF_ALICE, STAFF_BENSON));
    }
}
