package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelAssignment.ReadOnlyAssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelCourse.ReadOnlyCourseAddressBook;
import seedu.address.model.modelCourseStudent.CourseStudent;
import seedu.address.model.modelCourseStudent.CourseStudentAddressBook;
import seedu.address.model.modelCourseStudent.ReadOnlyCourseStudentAddressBook;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelFinance.ReadOnlyFinanceAddressBook;
import seedu.address.model.modelStaff.ReadOnlyStaffAddressBook;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.modelStudent.ReadOnlyStudentAddressBook;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelTeacher.ReadOnlyTeacherAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

  public static Person[] getSamplePersons() {
    return new Person[]{
        new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
            new Address("Blk 30 Geylang Street 29, #06-40"),
            getTagSet("friends")),
        new Person(new Name("Bernice Yu"), new Phone("99272758"),
            new Email("berniceyu@example.com"),
            new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
            getTagSet("colleagues", "friends")),
    };
  }

  public static ReadOnlyAddressBook getSampleAddressBook() {
    AddressBook sampleAb = new AddressBook();
    for (Person samplePerson : getSamplePersons()) {
      sampleAb.addPerson(samplePerson);
    }
    return sampleAb;
  }

  public static Teacher[] getSampleTeachers() {
    return new Teacher[]{
        new Teacher(new Name("Bob Ross"), new Phone("88283902"), new Email("bob.ross@gmail.com"),
            new Salary("9000"),
            new Address("Serangoon"),
            getTagSet("Friendly", "LovesArt")),
        new Teacher(new Name("Martin Henz"), new Phone("98765432"), new Email("henz@gmail.com"),
            new Salary("100"),
            new Address("311, Clementi Ave 2, #02-25"),
            getTagSet("WishfulThinking", "Experienced")),
    };
  }

  public static Staff[] getSampleStaffs() {
    return new Staff[]{
            new Staff(new Name("StaffenWolf Star"), new Phone("97231208"), new Email("star.wolf@gmail.com"),
                    new Salary("2500"),
                    new Address("Jupiter"),
                    getTagSet("DataEntry", "Detail")),
            new Staff(new Name("Stafford Stark"), new Phone("91232718"), new Email("stark.ford@gmail.com"),
                    new Salary("2600"),
                    new Address("Mars"),
                    getTagSet("Multitasking", "Financing")),
    };
  }

  public static ReadOnlyTeacherAddressBook getSampleTeacherAddressBook() {
    TeacherAddressBook sampleAb = new TeacherAddressBook();
    for (Teacher sampleTeacher : getSampleTeachers()) {
      sampleAb.addTeacher(sampleTeacher);
    }
    return sampleAb;
  }

  public static ReadOnlyStaffAddressBook getSampleStaffAddressBook() {
    StaffAddressBook sampleAb = new StaffAddressBook();
    for (Staff sampleStaff : getSampleStaffs()) {
      sampleAb.addStaff(sampleStaff);
    }
    return sampleAb;
  }

  public static Student[] getSampleStudents() {
    return new Student[]{
        new Student(new Name("Sim Sheng Xue"), new ID("33"),
            getTagSet("Loyal", "10Year")),
        new Student(new Name("John AppleSeed"), new ID("44"),
            getTagSet("Lazy", "Old"))
    };
  }

  public static ReadOnlyStudentAddressBook getSampleStudentAddressBook() {
    StudentAddressBook sampleAb = new StudentAddressBook();
    for (Student sampleStudent : getSampleStudents()) {
      sampleAb.addStudent(sampleStudent);
    }
    return sampleAb;
  }

  public static Finance[] getSampleFinances() {
    return new Finance[]{
        new Finance(new Name("Renovated Staff Lounge"), new Amount("2000"),
            getTagSet("BLK71", "AirCon")),
        new Finance(new Name("Received Payment From NUS"), new Amount("1000"),
            getTagSet("Contract"))
    };
  }

  public static ReadOnlyFinanceAddressBook getSampleFinanceAddressBook() {
    FinanceAddressBook sampleAb = new FinanceAddressBook();
    for (Finance sampleFinance : getSampleFinances()) {
      sampleAb.addFinance(sampleFinance);
    }
    return sampleAb;
  }

  public static Course[] getSampleCourses() {
    return new Course[]{
        new Course(new Name("Cozmo Programming"), new ID("829"), new Amount("1000"),
            getTagSet("Robot", "Fun")),
        new Course(new Name("Advanced Java"), new ID("182"), new Amount("2000"),
            getTagSet("OOP", "Difficult"))
    };
  }

  public static ReadOnlyCourseAddressBook getSampleCourseAddressBook() {
    CourseAddressBook sampleAb = new CourseAddressBook();
    for (Course sampleCourse : getSampleCourses()) {
      sampleAb.addCourse(sampleCourse);
    }
    return sampleAb;
  }

  public static Assignment[] getSampleAssignments() {
    return new Assignment[]{
            new Assignment(new Name("Informed Search"), new ID("900"),
                    new Deadline("2020-03-28"), getTagSet("AI", "Fun")),
            new Assignment(new Name("Adversarial Search"), new ID("901"),
                    new Deadline("2020-04-01"), getTagSet("AI", "Challenging")),
            new Assignment(new Name("Reinforcement Search"), new ID("902"),
                    new Deadline("2020-04-03"), getTagSet("AI", "Difficult"))
    };
  }

  public static ReadOnlyAssignmentAddressBook getSampleAssignmentAddressBook() {
    AssignmentAddressBook sampleAb = new AssignmentAddressBook();
    for (Assignment sampleAssignment : getSampleAssignments()) {
      sampleAb.addAssignment(sampleAssignment);
    }
    return sampleAb;
  }

  public static CourseStudent[] getSampleCourseStudents() {
    return new CourseStudent[]{
        new CourseStudent(new Courseid("829"), new Studentid("33"),
            getTagSet("Processing")),
        new CourseStudent(new Courseid("182"), new Studentid("44"),
            getTagSet("Confirmed")),
        new CourseStudent(new Courseid("829"), new Studentid("44"),
            getTagSet("Confirmed"))
    };
  }

  public static ReadOnlyCourseStudentAddressBook getSampleCourseStudentAddressBook() {
    CourseStudentAddressBook sampleAb = new CourseStudentAddressBook();
    for (CourseStudent sampleCourseStudent : getSampleCourseStudents()) {
      sampleAb.addCourseStudent(sampleCourseStudent);
    }
    return sampleAb;
  }

  /**
   * Returns a tag set containing the list of strings given.
   */
  public static Set<Tag> getTagSet(String... strings) {
    return Arrays.stream(strings)
        .map(Tag::new)
        .collect(Collectors.toSet());
  }

}
