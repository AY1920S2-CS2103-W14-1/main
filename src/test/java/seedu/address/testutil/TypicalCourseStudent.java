package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelCourseStudent.CourseStudentAddressBook;

/**
 * A utility class containing a list of {@code CourseStudent} objects to be used in tests.
 */
public class TypicalCourseStudent {

    public static final CourseStudent COURSE_STUDENT_ONE = new CourseStudentBuilder()
            .withCourseID("829")
            .withStudentID("44")
            .withTags("Processing").build();
    public static final CourseStudent COURSE_STUDENT_TWO = new CourseStudentBuilder()
            .withCourseID("182")
            .withStudentID("33")
            .withTags("Done").build();
    public static final CourseStudent COURSE_STUDENT_THREE = new CourseStudentBuilder()
            .withCourseID("345")
            .withStudentID("1")
            .withTags("Confirmed").build();

    /*
    // Manually added - CourseStudent's details found in {@code CommandTestUtil}
    public static final CourseStudent AMY = new CourseStudentBuilder().withName(VALID_NAME_AMY).withID(VALID_ID_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final CourseStudent BOB = new CourseStudentBuilder().withName(VALID_NAME_BOB).withID(VALID_ID_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    */

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalCourseStudent() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical CourseStudents.
     */
    public static CourseStudentAddressBook getTypicalCourseStudentAddressBook() {
        CourseStudentAddressBook ab = new CourseStudentAddressBook();
        for (CourseStudent CourseStudent : getTypicalCourseStudents()) {
            ab.add(CourseStudent);
        }
        return ab;
    }

    public static List<CourseStudent> getTypicalCourseStudents() {
        return new ArrayList<>(Arrays.asList(COURSE_STUDENT_ONE, COURSE_STUDENT_TWO, COURSE_STUDENT_THREE));
    }
}
