package seedu.address.testutil;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.modelProgress.Progress;
import seedu.address.model.modelProgress.ProgressAddressBook;
import seedu.address.model.person.CompositeID;
import seedu.address.model.person.CompositeIDBuilder;
import seedu.address.model.person.ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Progress} objects to be used in tests.
 */
public class TypicalProgress {
    public static final ID student1 = new ID("1");
    public static final ID student2 = new ID("2");
    public static final ID assignment100 = new ID("100");
    public static final ID assignment200 = new ID("200");
    public static final ID assignment300 = new ID("300");

    public static CompositeID CompositeID_S1_A1 = null;
    public static Progress Progress_S1_A1 = null;
    public static CompositeID CompositeID_S1_A2 = null;
    public static Progress Progress_S1_A2 = null;
    public static CompositeID CompositeID_S1_A3 = null;
    public static Progress Progress_S1_A3 = null;

    public static CompositeID CompositeID_S2_A1 = null;
    public static Progress Progress_S2_A1 = null;
    public static CompositeID CompositeID_S2_A2 = null;
    public static Progress Progress_S2_A2 = null;
    public static CompositeID CompositeID_S2_A3 = null;
    public static Progress Progress_S2_A3 = null;


    static {
        try {
            CompositeID_S1_A1 = new CompositeIDBuilder().addStudentID(student1).addAssignmentID(assignment100).createCompositeID();
            Progress_S1_A1 = new ProgressBuilder().withCompositeID(CompositeID_S1_A1).build();

            CompositeID_S1_A2 = new CompositeIDBuilder().addStudentID(student1).addAssignmentID(assignment200).createCompositeID();
            Progress_S1_A2 = new ProgressBuilder().withCompositeID(CompositeID_S1_A2).build();

            CompositeID_S1_A3 = new CompositeIDBuilder().addStudentID(student1).addAssignmentID(assignment300).createCompositeID();
            Progress_S1_A3 = new ProgressBuilder().withCompositeID(CompositeID_S1_A3).build();

            CompositeID_S2_A1 = new CompositeIDBuilder().addStudentID(student2).addAssignmentID(assignment100).createCompositeID();
            Progress_S2_A1 = new ProgressBuilder().withCompositeID(CompositeID_S2_A1).build();

            CompositeID_S2_A2 = new CompositeIDBuilder().addStudentID(student2).addAssignmentID(assignment200).createCompositeID();
            Progress_S2_A2 = new ProgressBuilder().withCompositeID(CompositeID_S2_A2).build();

            CompositeID_S2_A3 = new CompositeIDBuilder().addStudentID(student2).addAssignmentID(assignment300).createCompositeID();
            Progress_S2_A3 = new ProgressBuilder().withCompositeID(CompositeID_S2_A3).build();
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    private TypicalProgress() {};

    /**
     * Returns an {@code AddressBook} with all the typical Progress.
     */
    public static ProgressAddressBook getTypicalProgressAddressBook() {
        ProgressAddressBook ab = new ProgressAddressBook();
        for (Progress Progress : getTypicalProgress()) {
            ab.add(Progress);
        }
        return ab;
    }

    public static List<Progress> getTypicalProgress() {
        return new ArrayList<>(Arrays.asList(Progress_S1_A1, Progress_S1_A2, Progress_S1_A3,
                                                Progress_S2_A1, Progress_S2_A2, Progress_S2_A3));
    }
}