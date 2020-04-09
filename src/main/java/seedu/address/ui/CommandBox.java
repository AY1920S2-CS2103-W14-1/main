package seedu.address.ui;

import java.util.Stack;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

  public static final String ERROR_STYLE_CLASS = "error";
  private static final String FXML = "CommandBox.fxml";

  private final CommandExecutor commandExecutor;
  private final MainWindow mainWindow;
  private Stack<String> leftCommandStack;
  private Stack<String> rightCommandStack;
  private Stack<String> mainCommandStack;
  @FXML
  private TextField commandTextField;

  public CommandBox(CommandExecutor commandExecutor, MainWindow mainWindow) {
    super(FXML);
    this.commandExecutor = commandExecutor;
    this.mainWindow = mainWindow;
    // calls #setStyleToDefault() whenever there is a change to the text of the command box.
    commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    leftCommandStack = new Stack<>();
    rightCommandStack = new Stack<>();
    mainCommandStack = new Stack<>();

    getRoot().setOnKeyReleased(new EventHandler<KeyEvent>() {

      public void handle(KeyEvent ke) {
        boolean repeat = false;
        if (ke.getCode() == KeyCode.DOWN) {
          if (!rightCommandStack.isEmpty()){
            if (rightCommandStack.peek().equals(commandTextField.getText())){
              repeat = true;
            }
            String commandStr = rightCommandStack.pop();
            commandTextField.setText(commandStr);
            leftCommandStack.push(commandStr);
          }
        } else if (ke.getCode() == KeyCode.UP) {
          if (!leftCommandStack.isEmpty()){
            if (leftCommandStack.peek().equals(commandTextField.getText())){
              repeat = true;
            }
            String commandStr = leftCommandStack.pop();
            commandTextField.setText(commandStr);
            rightCommandStack.push(commandStr);
          }
        }
        if (repeat){
          handle(ke);
        }
      }
    });
  }

  /**
   * Handles the Enter button pressed event.
   */
  @FXML
  private void handleCommandEntered() {
    try {
      commandExecutor.execute(commandTextField.getText());
      if (mainCommandStack.isEmpty() || !mainCommandStack.peek()
          .equals(commandTextField.getText())) {
        mainCommandStack.push(commandTextField.getText());
        leftCommandStack.clear();
        leftCommandStack.addAll(mainCommandStack);
        rightCommandStack.clear();
      } else if (!mainCommandStack.isEmpty() && mainCommandStack.peek()
          .equals(commandTextField.getText())){
        if (!rightCommandStack.isEmpty()){
          String commandStr = rightCommandStack.pop();
          commandTextField.setText(commandStr);
          leftCommandStack.push(commandStr);
        }
      }
      commandTextField.setText("");
    } catch (CommandException | ParseException e) {
      setStyleToIndicateCommandFailure();
    }
  }

  /**
   * Handles custom command from user clicks. Only runs the select command if in the proper view
   */
  public void runCommand(String commandText, String currentView) {
    if (currentView.equals(mainWindow.getCurrentView())) {
      try {
        commandExecutor.execute(commandText);
      } catch (CommandException | ParseException e) {
        setStyleToIndicateCommandFailure();
      }
    }
  }

  /**
   * Sets the command box style to use the default style.
   */
  private void setStyleToDefault() {
    commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
  }

  /**
   * Sets the command box style to indicate a failed command.
   */
  private void setStyleToIndicateCommandFailure() {
    ObservableList<String> styleClass = commandTextField.getStyleClass();

    if (styleClass.contains(ERROR_STYLE_CLASS)) {
      return;
    }

    styleClass.add(ERROR_STYLE_CLASS);
  }

  /**
   * Represents a function that can execute commands.
   */
  @FunctionalInterface
  public interface CommandExecutor {

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;
  }

}
