package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.testutil.TypicalProgress.*;

public class CompositeIDTest {
    @Test
    public void equals_sameCompositeID_success() throws CommandException {
        CompositeID first = CompositeID_S1_A1;
        CompositeID second = new CompositeIDBuilder().addAssignmentID(assignment100).addStudentID(student1).createCompositeID();

        assertTrue(first.equals(second));
    }

    @Test
    public void equals_differentCompositeID_failure() {
        CompositeID first = CompositeID_S1_A1;
        CompositeID second = CompositeID_S1_A2;
        assertFalse(first.equals(second));
    }
}
