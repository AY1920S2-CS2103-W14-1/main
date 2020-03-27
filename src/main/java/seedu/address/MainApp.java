package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.modelAssignment.Assignment;
import seedu.address.model.modelAssignment.AssignmentAddressBook;
import seedu.address.model.modelCourse.Course;
import seedu.address.model.modelCourse.CourseAddressBook;
import seedu.address.model.modelFinance.Finance;
import seedu.address.model.modelFinance.FinanceAddressBook;
import seedu.address.model.modelGeneric.ReadOnlyAddressBookGeneric;
import seedu.address.model.modelStaff.Staff;
import seedu.address.model.modelStaff.StaffAddressBook;
import seedu.address.model.modelStudent.Student;
import seedu.address.model.modelStudent.StudentAddressBook;
import seedu.address.model.modelTeacher.Teacher;
import seedu.address.model.modelTeacher.TeacherAddressBook;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.storage.storageAssignments.AssignmentAddressBookStorage;
import seedu.address.storage.storageAssignments.JsonAssignmentAddressBookStorage;
import seedu.address.storage.storageCourse.CourseAddressBookStorage;
import seedu.address.storage.storageCourse.JsonCourseAddressBookStorage;
import seedu.address.storage.storageFinance.FinanceAddressBookStorage;
import seedu.address.storage.storageFinance.JsonFinanceAddressBookStorage;
import seedu.address.storage.storageStaff.JsonStaffAddressBookStorage;
import seedu.address.storage.storageStaff.StaffAddressBookStorage;
import seedu.address.storage.storageStudent.JsonStudentAddressBookStorage;
import seedu.address.storage.storageStudent.StudentAddressBookStorage;
import seedu.address.storage.storageTeacher.JsonTeacherAddressBookStorage;
import seedu.address.storage.storageTeacher.TeacherAddressBookStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

  public static final Version VERSION = new Version(0, 6, 0, true);

  private static final Logger logger = LogsCenter.getLogger(MainApp.class);

  protected Ui ui;
  protected Logic logic;
  protected Storage storage;
  protected Model model;
  protected Config config;

  @Override
  public void init() throws Exception {
    logger.info(
        "=============================[ Initializing AddressBook ]===========================");
    super.init();

    AppParameters appParameters = AppParameters.parse(getParameters());
    config = initConfig(appParameters.getConfigPath());

    UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
    UserPrefs userPrefs = initPrefs(userPrefsStorage);
    AddressBookStorage addressBookStorage = new JsonAddressBookStorage(
        userPrefs.getAddressBookFilePath());
    TeacherAddressBookStorage teacherAddressBookStorage = new JsonTeacherAddressBookStorage(
        userPrefs.getTeacherAddressBookFilePath());
    StaffAddressBookStorage staffAddressBookStorage = new JsonStaffAddressBookStorage(
            userPrefs.getStaffAddressBookFilePath());
    StudentAddressBookStorage studentAddressBookStorage = new JsonStudentAddressBookStorage(
        userPrefs.getStudentAddressBookFilePath());
    CourseAddressBookStorage courseAddressBookStorage = new JsonCourseAddressBookStorage(
        userPrefs.getCourseAddressBookFilePath());
    FinanceAddressBookStorage financeAddressBookStorage = new JsonFinanceAddressBookStorage(
        userPrefs.getFinanceAddressBookFilePath());
    AssignmentAddressBookStorage assignmentAddressBookStorage = new JsonAssignmentAddressBookStorage(
            userPrefs.getAssignmentAddressBookFilePath());


    storage = new StorageManager(addressBookStorage, teacherAddressBookStorage, staffAddressBookStorage,
        studentAddressBookStorage, financeAddressBookStorage, courseAddressBookStorage,
            assignmentAddressBookStorage, userPrefsStorage);

    initLogging(config);

    model = initModelManager(storage, userPrefs);

    logic = new LogicManager(model, storage);

    ui = new UiManager(logic);
  }

  /**
   * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code
   * userPrefs}. <br> The data from the sample address book will be used instead if {@code
   * storage}'s address book is not found, or an empty address book will be used instead if errors
   * occur when reading {@code storage}'s address book.
   */
  private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
    Optional<ReadOnlyAddressBook> addressBookOptional;
    ReadOnlyAddressBook initialData;
    try {
      addressBookOptional = storage.readAddressBook();
      if (!addressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      initialData = addressBookOptional.orElseGet(SampleDataUtil::getSampleAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
          "Data file not in the correct format. Will be starting with an empty AddressBook");
      initialData = new AddressBook();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      initialData = new AddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Teacher>> teacherAddressBookOptional;
    ReadOnlyAddressBookGeneric<Teacher> teacherInitialData;
    try {
      teacherAddressBookOptional = storage.readTeacherAddressBook();
      if (!teacherAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      teacherInitialData = teacherAddressBookOptional
          .orElseGet(SampleDataUtil::getSampleTeacherAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
          "Data file not in the correct format. Will be starting with an empty AddressBook");
      teacherInitialData = new TeacherAddressBook();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      teacherInitialData = new TeacherAddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Staff>> staffAddressBookOptional;
    ReadOnlyAddressBookGeneric<Staff> staffInitialData;
    try {
      staffAddressBookOptional = storage.readStaffAddressBook();
      if (!staffAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      staffInitialData = staffAddressBookOptional
              .orElseGet(SampleDataUtil::getSampleStaffAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
              "Data file not in the correct format. Will be starting with an empty AddressBook");
      staffInitialData = new StaffAddressBook();
    } catch (IOException e) {
      logger.warning(
              "Problem while reading from the file. Will be starting with an empty AddressBook");
      staffInitialData = new StaffAddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Student>> studentAddressBookOptional;
    ReadOnlyAddressBookGeneric<Student> studentInitialData;
    try {
      studentAddressBookOptional = storage.readStudentAddressBook();
      if (!studentAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      studentInitialData = studentAddressBookOptional
          .orElseGet(SampleDataUtil::getSampleStudentAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
          "Data file not in the correct format. Will be starting with an empty AddressBook");
      studentInitialData = new StudentAddressBook();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      studentInitialData = new StudentAddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Finance>> financeAddressBookOptional;
    ReadOnlyAddressBookGeneric<Finance> financeInitialData;
    try {
      financeAddressBookOptional = storage.readFinanceAddressBook();
      if (!financeAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      financeInitialData = financeAddressBookOptional
          .orElseGet(SampleDataUtil::getSampleFinanceAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
          "Data file not in the correct format. Will be starting with an empty AddressBook");
      financeInitialData = new FinanceAddressBook();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      financeInitialData = new FinanceAddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Course>> courseAddressBookOptional;
    ReadOnlyAddressBookGeneric<Course> courseInitialData;
    try {
      courseAddressBookOptional = storage.readCourseAddressBook();
      if (!courseAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      courseInitialData = courseAddressBookOptional
          .orElseGet(SampleDataUtil::getSampleCourseAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
          "Data file not in the correct format. Will be starting with an empty AddressBook");
      courseInitialData = new CourseAddressBook();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      courseInitialData = new CourseAddressBook();
    }

    Optional<ReadOnlyAddressBookGeneric<Assignment>> assignmentAddressBookOptional;
    ReadOnlyAddressBookGeneric<Assignment> assignmentInitialData;

    try {
      assignmentAddressBookOptional = storage.readAssignmentAddressBook();
      if (!assignmentAddressBookOptional.isPresent()) {
        logger.info("Data file not found. Will be starting with a sample AddressBook");
      }
      assignmentInitialData = assignmentAddressBookOptional
              .orElseGet(SampleDataUtil::getSampleAssignmentAddressBook);
    } catch (DataConversionException e) {
      logger.warning(
              "Data file not in the correct format. Will be starting with an empty AddressBook");
      assignmentInitialData = new AssignmentAddressBook();
    } catch (IOException e) {
      logger.warning(
              "Problem while reading from the file. Will be starting with an empty AddressBook");
      assignmentInitialData = new AssignmentAddressBook();
    }

    return new ModelManager(initialData, teacherInitialData, staffInitialData, studentInitialData, financeInitialData,
        courseInitialData, assignmentInitialData, userPrefs);
  /*
    return new ModelManager(initialData, teacherInitialData, studentInitialData, financeInitialData,
            courseInitialData, userPrefs);
  */
  }

  private void initLogging(Config config) {
    LogsCenter.init(config);
  }

  /**
   * Returns a {@code Config} using the file at {@code configFilePath}. <br> The default file path
   * {@code Config#DEFAULT_CONFIG_FILE} will be used instead if {@code configFilePath} is null.
   */
  protected Config initConfig(Path configFilePath) {
    Config initializedConfig;
    Path configFilePathUsed;

    configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

    if (configFilePath != null) {
      logger.info("Custom Config file specified " + configFilePath);
      configFilePathUsed = configFilePath;
    }

    logger.info("Using config file : " + configFilePathUsed);

    try {
      Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
      initializedConfig = configOptional.orElse(new Config());
    } catch (DataConversionException e) {
      logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
          + "Using default config properties");
      initializedConfig = new Config();
    }

    //Update config file in case it was missing to begin with or there are new/unused fields
    try {
      ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
    } catch (IOException e) {
      logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
    }
    return initializedConfig;
  }

  /**
   * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path, or a new
   * {@code UserPrefs} with default configuration if errors occur when reading from the file.
   */
  protected UserPrefs initPrefs(UserPrefsStorage storage) {
    Path prefsFilePath = storage.getUserPrefsFilePath();
    logger.info("Using prefs file : " + prefsFilePath);

    UserPrefs initializedPrefs;
    try {
      Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
      initializedPrefs = prefsOptional.orElse(new UserPrefs());
    } catch (DataConversionException e) {
      logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
          + "Using default user prefs");
      initializedPrefs = new UserPrefs();
    } catch (IOException e) {
      logger.warning(
          "Problem while reading from the file. Will be starting with an empty AddressBook");
      initializedPrefs = new UserPrefs();
    }

    //Update prefs file in case it was missing to begin with or there are new/unused fields
    try {
      storage.saveUserPrefs(initializedPrefs);
    } catch (IOException e) {
      logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
    }

    return initializedPrefs;
  }

  @Override
  public void start(Stage primaryStage) {
    logger.info("Starting AddressBook " + MainApp.VERSION);
    ui.start(primaryStage);
  }

  @Override
  public void stop() {
    logger.info(
        "============================ [ Stopping Address Book ] =============================");
    try {
      storage.saveUserPrefs(model.getUserPrefs());
    } catch (IOException e) {
      logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
    }
  }
}
