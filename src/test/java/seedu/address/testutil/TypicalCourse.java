package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;

/**
 * A utility class containing a list of {@code Course} objects to be used in tests.
 */
public class TypicalCourse {

    public static final Course COURSE_ALICE = new CourseBuilder().withName("Alice Pauline")
            .withID("1000")
            .withTags("friends").build();
    public static final Course COURSE_BENSON = new CourseBuilder().withName("Benson Meier")
            .withID("1001")
            .withTags("owesMoney", "friends").build();
    public static final Course CARL = new CourseBuilder().withName("Carl Kurz")
            .withID("1002").build();
    public static final Course DANIEL = new CourseBuilder().withName("Daniel Meier")
            .withID("1003").withTags("friends").build();
    public static final Course ELLE = new CourseBuilder().withName("Elle Meyer")
            .withID("1004").build();
    public static final Course FIONA = new CourseBuilder().withName("Fiona Kunz")
            .withID("1005").build();
    public static final Course GEORGE = new CourseBuilder().withName("George Best")
            .withID("1006").build();

    // Manually added
    public static final Course HOON = new CourseBuilder().withName("Hoon Meier").withID("1000").build();
    public static final Course IDA = new CourseBuilder().withName("Ida Mueller").withID("1000").build();

    // Manually added - Assignment's details found in {@code CommandTestUtil}
    public static final Course AMY = new CourseBuilder().withName(VALID_NAME_AMY).withID(VALID_ID_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Course BOB = new CourseBuilder().withName(VALID_NAME_BOB).withID(VALID_ID_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCourse() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical courses.
     */
    public static CourseAddressBook getTypicalCourseAddressBook() {
        CourseAddressBook ab = new CourseAddressBook();
        for (Course course : getTypicalCourses()) {
            ab.add(course);
        }
        return ab;
    }

    public static List<Course> getTypicalCourses() {
        return new ArrayList<>(Arrays.asList(COURSE_ALICE, COURSE_BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
