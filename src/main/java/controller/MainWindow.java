package controller;

import java.time.format.DateTimeParseException;

import command.Command;
import fridayexception.FridayException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.UiGui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image bossImage = new Image(this.getClass().getResourceAsStream("/images/Bo$$.png"));
    private final Image fridayImage = new Image(this.getClass().getResourceAsStream("/images/Friday.png"));
    private UiGui gui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should be initialized in the FXML file";
        assert dialogContainer != null : "VBox dialogContainer should be initialized in the FXML file";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the storage for the main window.
     * @param storage The storage to save the task list to file.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    /**
     * Sets the GUI for the main window.
     * @param gui The GUI to display messages to the user.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setGui(UiGui gui) {
        this.gui = gui;
        return this;
    }

    /**
     * Sets the task list for the main window.
     * @param tasks The task list to be modified by the commands.
     * @return The main window object for easy chaining of the other setter methods
     */
    public MainWindow setTasks(TaskList tasks) {
        this.tasks = tasks;
        return this;
    }

    public void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getFridayDialog(fridayImage, gui.showWelcome()));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Friday's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        try {
            Command command = Parser.parseUserInput(input);
            response = command.executeGui(tasks, gui, storage);
            if ("bye".equals(input)) {
                Platform.exit();
            }
        } catch (FridayException | DateTimeParseException | IndexOutOfBoundsException e) {
            if (e instanceof DateTimeParseException) {
                response = gui.showError("Please enter a valid date in the format yyyy-mm-dd.");
            } else {
                response = gui.showError(e.getMessage());
            }
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(bossImage, input),
                    DialogBox.getFridayDialog(fridayImage, response)
            );
            userInput.clear();
        }
    }
}
