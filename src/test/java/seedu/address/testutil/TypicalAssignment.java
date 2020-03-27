package seedu.address.testutil;

import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignment {

    public static final Assignment ASSIGNMENT_EASY = new AssignmentBuilder().withName("Easy Assignment")
            .withID("1000")
            .withDeadline("2020-12-30")
            .withTags("easy").build();
    public static final Assignment ASSIGNMENT_INTERMEDIATE = new AssignmentBuilder().withName("Intermediate Assignment")
            .withID("1000")
            .withDeadline("2020-11-30")
            .withTags("intermediate").build();
    public static final Assignment ASSIGNMENT_HARD = new AssignmentBuilder().withName("Hard Assignment")
            .withID("1000")
            .withDeadline("2020-10-30")
            .withTags("difficult").build();

    /*
    // Manually added - Assignment's details found in {@code CommandTestUtil}
    public static final Assignment AMY = new AssignmentBuilder().withName(VALID_NAME_AMY).withID(VALID_ID_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Assignment BOB = new AssignmentBuilder().withName(VALID_NAME_BOB).withID(VALID_ID_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();
    */

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAssignment() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical Assignments.
     */
    public static AssignmentAddressBook getTypicalAssignmentAddressBook() {
        AssignmentAddressBook ab = new AssignmentAddressBook();
        for (Assignment Assignment : getTypicalAssignments()) {
            ab.add(Assignment);
        }
        return ab;
    }

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT_EASY, ASSIGNMENT_INTERMEDIATE, ASSIGNMENT_HARD));
    }
}
