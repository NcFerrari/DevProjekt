package lp.fe.fx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.enums.TextEnum;
import lp.be.service.ConfigFileService;
import lp.be.serviceimpl.ConfigFileServiceImpl;
import lp.fe.enums.Lang;

public class FXApplication extends Application {

    private final Manager manager = Manager.getInstance();
    private VBox mainPane;
    private HBox buttonsPane;
    private TextArea area;
    private final TextField firstNameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final TextField addressTextField = new TextField();
    private final TextField phoneTextField = new TextField();

    private static final ConfigFileService CONFIG = ConfigFileServiceImpl.getInstance();

    /**
     * Basic FX method
     *
     * @param stage default stage from FX container
     */
    @Override
    public void start(Stage stage) {
        mainPane = new VBox();
        mainPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(mainPane,
                CONFIG.getIntValue(TextEnum.APPLICATION_WIDTH.getText()),
                CONFIG.getIntValue(TextEnum.APPLICATION_HEIGHT.getText()));
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.show();

        addFormNodes();
        addButtons();
        addTextArea();
    }

    /**
     * Method for following conventions. Adding text area.
     */
    private void addTextArea() {
        area = new TextArea();
        mainPane.getChildren().add(area);
    }

    /**
     * Input nodes:
     * <ul>
     *     <li>4x Labels</li>
     *     <li>4x TextFields</li>
     *     <li>5x Buttons</li>
     * </ul>
     */
    private void addFormNodes() {
        GridPane componentsPane = new GridPane();
        mainPane.getChildren().add(componentsPane);
        componentsPane.setHgap(0);
        componentsPane.setVgap(10);
        componentsPane.add(new Label(Lang.FIRST_NAME.getText()), 0, 0);
        componentsPane.add(new Label(Lang.SURNAME.getText()), 0, 1);
        componentsPane.add(new Label(Lang.ADDRESS.getText()), 0, 2);
        componentsPane.add(new Label(Lang.PHONE.getText()), 0, 3);
        componentsPane.add(firstNameTextField, 1, 0);
        componentsPane.add(surnameTextField, 1, 1);
        componentsPane.add(addressTextField, 1, 2);
        componentsPane.add(phoneTextField, 1, 3);
    }

    /**
     * Add buttons for CRUD and text area control.
     */
    private void addButtons() {
        buttonsPane = new HBox();
        buttonsPane.setSpacing(5);
        mainPane.getChildren().add(buttonsPane);
        addButton(Lang.SAVE.getText(), evt -> createPerson());
        addButton(Lang.EDIT.getText(), evt -> editPerson());
        addButton(Lang.LOAD.getText(), evt -> {
            clearTextArea();
            manager.getData().forEach(person -> area.setText(area.getText() + person + Lang.NEW_LINE.getText()));
        });
        addButton(Lang.CLEAR_LOG.getText(), evt -> clearTextArea());
    }

    /**
     * CRUD update. Editing persons address and phone based on his first name and surname.
     */
    private void editPerson() {
        String key = firstNameTextField.getText().trim() + surnameTextField.getText().trim();
        if (manager.editPerson(key, addressTextField.getText(), phoneTextField.getText()) == 0) {
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, Lang.PERSON_NOT_EXISTS.getText()).show();
        }
    }

    /**
     * CRUD create. Create new person into database.
     */
    private void createPerson() {
        String key = firstNameTextField.getText().trim() + surnameTextField.getText().trim();
        int answer = manager.saveNewPerson(key, firstNameTextField.getText(), surnameTextField.getText(), addressTextField.getText(), phoneTextField.getText());
        if (answer == -2) {
            new Alert(Alert.AlertType.ERROR, Lang.EMPTY_INPUTS.getText()).show();
        } else if (answer == -1) {
            new Alert(Alert.AlertType.ERROR, Lang.PERSON_ALREADY_EXISTS.getText()).show();
        }
        clearTextFields();
    }

    /**
     * Clear text area.
     */
    private void clearTextArea() {
        area.setText(Lang.EMPTY.getText());
    }

    /**
     * Reset all input text fields.
     */
    private void clearTextFields() {
        firstNameTextField.clear();
        surnameTextField.clear();
        addressTextField.clear();
        phoneTextField.clear();
    }

    /**
     * Create new button for CRUD or editing text area.
     *
     * @param buttonText Don't use raw String, use Lang enumeration for button text.
     * @param evt        action for button
     */
    private void addButton(String buttonText, EventHandler<ActionEvent> evt) {
        Button button = new Button(buttonText);
        buttonsPane.getChildren().add(button);
        button.setOnAction(evt);
    }
}
