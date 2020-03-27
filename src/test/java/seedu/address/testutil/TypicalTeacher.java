package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SALARY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import seedu.address.model.AddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;

/**
 * A utility class containing a list of {@code Teacher} objects to be used in tests.
 */
public class TypicalTeacher {

    public static final Teacher TEACHER_ALICE = new TeacherBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withSalary("1000")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Teacher TEACHER_BENSON = new TeacherBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withSalary("1000")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Teacher CARL = new TeacherBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withSalary("1000").withAddress("wall street").build();
    public static final Teacher DANIEL = new TeacherBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withSalary("1000").withAddress("10th street").withTags("friends").build();
    public static final Teacher ELLE = new TeacherBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withSalary("1000").withAddress("michegan ave").build();
    public static final Teacher FIONA = new TeacherBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withSalary("1000").withAddress("little tokyo").build();
    public static final Teacher GEORGE = new TeacherBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withSalary("1000").withAddress("4th street").build();

    // Manually added
    public static final Teacher HOON = new TeacherBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withSalary("1000").withAddress("little india").build();
    public static final Teacher IDA = new TeacherBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withSalary("1000").withAddress("chicago ave").build();

    // Manually added - Teacher's details found in {@code CommandTestUtil}
    public static final Teacher AMY = new TeacherBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withSalary(VALID_SALARY_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Teacher BOB = new TeacherBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withSalary(VALID_SALARY_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTeacher() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical teachers.
     */
    public static TeacherAddressBook getTypicalTeacherAddressBook() {
        TeacherAddressBook ab = new TeacherAddressBook();
        for (Teacher teacher : getTypicalTeachers()) {
            ab.add(teacher);
        }
        return ab;
    }

    public static List<Teacher> getTypicalTeachers() {
        return new ArrayList<>(Arrays.asList(TEACHER_ALICE, TEACHER_BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
