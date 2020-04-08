package seedu.address.manager;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FINANCEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEACHERID;

import com.google.common.eventbus.Subscribe;

import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.address.commons.core.BaseManager;
import seedu.address.commons.events.DataStorageChangeEvent;
import seedu.address.commons.util.Constants;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelProgress.Progress;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.person.ID;

// TODO: Think of better name?
public class DetailManager extends BaseManager {
    enum TYPE {
        STUDENT_DETAILS,
        STUDENT_COURSE_DETAILS,
        COURSE_DETAILS,
        COURSE_STUDENT_DETAILS,
        STAFF_DETAILS,
        FINANCE_DETAILS,
        ASSIGNMENT_DETAILS,
    }

    public TYPE type;

    private static DetailManager instance;

    public static DetailManager getInstance() {
        return instance;
    }

    private static Model model = ModelManager.getInstance();
    // Detail Manager will have ObservableLists that LogicManager will also contain.

    private ObservableMap<String, Object> studentDetailsMap;
    private ObservableMap<String, Object> courseDetailsMap;
    private ObservableMap<String, Object> staffDetailsMap;
    private ObservableMap<String, Object> financeDetailsMap;
    private ObservableMap<String, Object> assignmentDetailsMap;

    HashMap<Prefix, ID> IdMapping;

    public DetailManager() {
        studentDetailsMap = FXCollections.observableMap(new HashMap<String, Object>());
        courseDetailsMap = FXCollections.observableMap(new HashMap<String, Object>());
        staffDetailsMap = FXCollections.observableMap(new HashMap<String, Object>());
        financeDetailsMap = FXCollections.observableMap(new HashMap<String, Object>());
        assignmentDetailsMap = FXCollections.observableMap(new HashMap<String, Object>());
        IdMapping = new HashMap<Prefix, ID>();
        instance = this;
    }

    // ###################### Getters for details map of different page ####################################
    public ObservableMap<String, Object> getFilteredStudentDetailsMap() {
        return this.studentDetailsMap;
    }

    public ObservableMap<String, Object> getFilteredCourseDetailsMap() {
        return this.courseDetailsMap;
    }

    public ObservableMap<String, Object> getFilteredStaffDetailsMap() {
        return this.staffDetailsMap;
    }

    public ObservableMap<String, Object> getFilteredFinanceDetailsMap() {
        return this.financeDetailsMap;
    }

    public ObservableMap<String, Object> getFilteredAssignmentDetailsMap() {
        return this.assignmentDetailsMap;
    }
    // #####################################################################################################


    // Select commands will update the observable lists here
    public void updateType(List<ArgumentTokenizer.PrefixPosition> positions,
                         List<ID> selectMetaDataIDs) {
        this.type = this.getType(positions);
        for (int i = 0; i < positions.size(); i ++) {
            IdMapping.put(positions.get(i).getPrefix(), selectMetaDataIDs.get(i));
        }
    }

    public TYPE getType(List<ArgumentTokenizer.PrefixPosition> positions) {
        if (positions.get(0).getPrefix().equals(PREFIX_STUDENTID)) {
            if (positions.size() == 1) {
                return TYPE.STUDENT_DETAILS;
            }

            if (positions.get(1).getPrefix().equals(PREFIX_COURSEID)) {
                return TYPE.STUDENT_COURSE_DETAILS;
            }

        } else if (positions.get(0).getPrefix().equals(PREFIX_COURSEID)) {
            if (positions.size() == 1) {
                return TYPE.COURSE_DETAILS;
            }

            if (positions.get(1).getPrefix().equals(PREFIX_STUDENTID)) {
                return TYPE.COURSE_STUDENT_DETAILS;
            }
        } else if (positions.get(0).getPrefix().equals(PREFIX_TEACHERID)) {
            if (positions.size() == 1) {
                return TYPE.STAFF_DETAILS;
            }
        } else if (positions.get(0).getPrefix().equals(PREFIX_FINANCEID)) {
            if (positions.size() == 1) {
                return TYPE.FINANCE_DETAILS;
            }
        } else if (positions.get(0).getPrefix().equals(PREFIX_ASSIGNMENTID)) {
            if (positions.size() == 1) {
                return TYPE.ASSIGNMENT_DETAILS;
            }
        }
        return TYPE.COURSE_DETAILS;
    }

    public void updateDetails(List<ArgumentTokenizer.PrefixPosition> positions,
                             List<ID> selectMetaDataIDs) throws CommandException {
        updateType(positions, selectMetaDataIDs);
        if (type.equals(TYPE.STUDENT_DETAILS)) {
            studentDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_STUDENTID))));
            updateStudentDetailsMap(IdMapping.get(PREFIX_STUDENTID));
        } else if (type.equals(TYPE.STUDENT_COURSE_DETAILS)) {
            studentDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_STUDENTID),
                                                                                IdMapping.get(PREFIX_COURSEID))));
            updateStudentDetailsMap(IdMapping.get(PREFIX_STUDENTID));
            updateProgressStudentCourse(IdMapping.get(PREFIX_STUDENTID), IdMapping.get(PREFIX_COURSEID));
        } else if (type.equals(TYPE.COURSE_DETAILS)) {
            courseDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_COURSEID))));
            updateCourseDetailsMap(IdMapping.get(PREFIX_COURSEID));
        } else if (type.equals(TYPE.COURSE_STUDENT_DETAILS)) {
            courseDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_COURSEID),
                                                                                IdMapping.get(PREFIX_STUDENTID))));
            updateCourseDetailsMap(IdMapping.get(PREFIX_COURSEID));
            updateProgressCourseStudent(IdMapping.get(PREFIX_COURSEID), IdMapping.get(PREFIX_STUDENTID));
        } else if (type.equals(TYPE.STAFF_DETAILS)) {
            staffDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_TEACHERID))));
            updateStaffDetailsMap(IdMapping.get(PREFIX_TEACHERID));
        } else if (type.equals(TYPE.FINANCE_DETAILS)) {
            financeDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_FINANCEID))));
            updateFinanceDetailsMap(IdMapping.get(PREFIX_FINANCEID));
        } else if (type.equals(TYPE.ASSIGNMENT_DETAILS)) {
            assignmentDetailsMap.put("select_ids", new ArrayList<ID>(Arrays.asList(IdMapping.get(PREFIX_ASSIGNMENTID))));
            updateAssignmentDetailsMap(IdMapping.get(PREFIX_ASSIGNMENTID));
        }
    }

    // ################# Update student details map ##################################################3
    public void updateStudentDetailsMap(ID studentID) throws CommandException {
        Student student = (Student)model.get(studentID, Constants.ENTITY_TYPE.STUDENT);
        studentDetailsMap.put("details", student);

        ObservableList<HashMap> courses = FXCollections.observableArrayList();
        Set<ID> courseIDs = student.getAssignedCoursesID();
        for (ID courseID: courseIDs) {
            HashMap<String, Object> courseDetail = new HashMap<String, Object>();
            courseDetail.put("selected_studentID", studentID);
            courseDetail.put("info", (Course)model.getAddressBook(Constants.ENTITY_TYPE.COURSE).get(courseID));
            courseDetail.put("progress_list",
                    FXCollections.observableArrayList(new ArrayList<Progress>()));
            courseDetail.put("number_of_done_progress", ProgressManager.getNumberOfProgressesDone(courseID, studentID));
            courses.add(courseDetail);
        }
        studentDetailsMap.put("courses", courses);
    }

    public void updateProgressStudentCourse(ID studentID, ID courseID) throws CommandException {
        for (HashMap<String, Object> courseDetail: (ObservableList<HashMap>)studentDetailsMap.get("courses")) {
            Course course = (Course)courseDetail.get("info");
            if (course.getId().equals(courseID)) {
                courseDetail.put("progress_list",
                        FXCollections.observableArrayList(new ArrayList<Progress>(ProgressManager.getAllProgressesForOneStudent(courseID, studentID))));
            } else {
                courseDetail.put("progress_list",
                        FXCollections.observableArrayList(new ArrayList<Progress>()));
            }
        }
    }
    // #######################################################################################################



    // ###############  Update course details map ###########################################################
    public void updateCourseDetailsMap(ID courseID) throws CommandException {
        Course course = (Course)model.get(courseID, Constants.ENTITY_TYPE.COURSE);
        courseDetailsMap.put("details", course);

        ObservableList<HashMap> students = FXCollections.observableArrayList();
        Set<ID> studentIDs = course.getAssignedStudentsID();
        for (ID studentID: studentIDs) {
            HashMap<String, Object> studentDetail = new HashMap<String, Object>();
            studentDetail.put("selected_courseID", courseID);
            studentDetail.put("info", (Student)model.getAddressBook(Constants.ENTITY_TYPE.STUDENT).get(studentID));
            studentDetail.put("progress_list",
                    FXCollections.observableArrayList(new ArrayList<Progress>()));
            studentDetail.put("number_of_done_progress", ProgressManager.getNumberOfProgressesDone(courseID, studentID));
            students.add(studentDetail);
        }
        courseDetailsMap.put("students", students);
    }

    public void updateProgressCourseStudent(ID courseID, ID studentID) throws CommandException {
        for (HashMap<String, Object> studentDetail: (ObservableList<HashMap>)courseDetailsMap.get("students")) {
            Student student = (Student)studentDetail.get("info");
            if (student.getId().equals(studentID)) {
                studentDetail.put("progress_list",
                        FXCollections.observableArrayList(new ArrayList<Progress>(ProgressManager.getAllProgressesForOneStudent(courseID, studentID))));
            } else {
                studentDetail.put("progress_list",
                        FXCollections.observableArrayList(new ArrayList<Progress>()));
            }
        }
    }
    // #######################################################################################################


    // ################# Update staff details map ###########################################################
    public void updateStaffDetailsMap(ID staffID) throws CommandException {
        Staff staff = (Staff)model.get(staffID, Constants.ENTITY_TYPE.STAFF);
        staffDetailsMap.put("details", staff);

        ObservableList<Course> courses = FXCollections.observableArrayList();
        Set<ID> courseIDs = staff.getAssignedCoursesID();
        for (ID courseID: courseIDs) {
            courses.add((Course)model.getAddressBook(Constants.ENTITY_TYPE.COURSE).get(courseID));
        }
        staffDetailsMap.put("courses", courses);
    }
    // ####################################################################################################



    // ################### Update Finance Details Map #####################################################
    public void updateFinanceDetailsMap(ID financeID) throws CommandException {
        Finance finance = (Finance)model.get(financeID, Constants.ENTITY_TYPE.FINANCE);
        financeDetailsMap.put("details", finance);
    }
    // ####################################################################################################


    // ##################### Update Assignment Details Map ################################################
    public void updateAssignmentDetailsMap(ID assignmentID) throws CommandException {
        Assignment assignment = (Assignment)model.get(assignmentID, Constants.ENTITY_TYPE.ASSIGNMENT);
        assignmentDetailsMap.put("details", assignment);
    }
    // ####################################################################################################


    // #################### Handle sub-view map data update on model data update ##########################
    private void resetStudentDetailsMap() throws CommandException {
        if (studentDetailsMap.containsKey("select_ids")) {
            ArrayList<ID> idsList = (ArrayList<ID>)studentDetailsMap.get("select_ids");
            updateStudentDetailsMap(idsList.get(0));
            if (idsList.size() == 2) {
                updateProgressStudentCourse(idsList.get(0), idsList.get(1));
            }
        }
    }

    private void resetCourseDetailsMap() throws CommandException {
        if (courseDetailsMap.containsKey("select_ids")) {
            ArrayList<ID> idsList = (ArrayList<ID>)courseDetailsMap.get("select_ids");
            updateCourseDetailsMap(idsList.get(0));
            if (idsList.size() == 2) {
                updateProgressCourseStudent(idsList.get(0), idsList.get(1));
            }
        }
    }

    private void resetStaffDetailsMap() throws CommandException {
        if (staffDetailsMap.containsKey("select_ids")) {
            ArrayList<ID> idsList = (ArrayList<ID>)staffDetailsMap.get("select_ids");
            updateStaffDetailsMap(idsList.get(0));
        }
    }

    private void resetFinanceDetailsMap() throws CommandException {
        if (financeDetailsMap.containsKey("select_ids")) {
            ArrayList<ID> idsList = (ArrayList<ID>)financeDetailsMap.get("select_ids");
            updateFinanceDetailsMap(idsList.get(0));
        }
    }

    private void resetAssignmentDetailsMap() throws CommandException {
        if (assignmentDetailsMap.containsKey("select_ids")) {
            ArrayList<ID> idsList = (ArrayList<ID>)assignmentDetailsMap.get("select_ids");
            updateAssignmentDetailsMap(idsList.get(0));
        }
    }

    private void resetAllSubViewMaps() throws CommandException {
        resetStudentDetailsMap();
        resetCourseDetailsMap();
        resetStaffDetailsMap();
        resetFinanceDetailsMap();
        resetAssignmentDetailsMap();
    }

    @Subscribe
    public void handleDataStorageChangeEvent(DataStorageChangeEvent event) {
        try {
            resetAllSubViewMaps();
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
