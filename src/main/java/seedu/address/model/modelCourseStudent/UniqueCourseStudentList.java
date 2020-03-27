package seedu.address.model.modelCourseStudent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.modelGeneric.UniqueList;
import seedu.address.model.person.exceptions.CourseStudentNotFoundException;
import seedu.address.model.person.exceptions.DuplicateCourseStudentException;

public class UniqueCourseStudentList extends UniqueList<CourseStudent> {
}
