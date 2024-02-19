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
import lp.fe.Lang;

public class App extends Application {

    private final Manager manager = Manager.getInstance();
    private VBox mainPane;
    private HBox buttonsPane;
    private TextArea area;
    private TextField firstNameTextField;
    private TextField surnameTextField;
    private TextField addressTextField;
    private TextField phoneTextField;

    @Override
    public void start(Stage stage) {
        mainPane = new VBox();
        mainPane.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(mainPane, 450, 500);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.show();

        addFormNodes();
        addButtons();
        addTextArea();
    }

    private void addTextArea() {
        area = new TextArea();
        mainPane.getChildren().add(area);
    }

    private void addFormNodes() {
        GridPane componentsPane = new GridPane();
        mainPane.getChildren().add(componentsPane);
        componentsPane.setHgap(0);
        componentsPane.setVgap(10);
        componentsPane.add(new Label(Lang.FIRST_NAME.getText()), 0, 0);
        componentsPane.add(new Label(Lang.SURNAME.getText()), 0, 1);
        componentsPane.add(new Label(Lang.ADDRESS.getText()), 0, 2);
        componentsPane.add(new Label(Lang.PHONE.getText()), 0, 3);
        firstNameTextField = new TextField();
        surnameTextField = new TextField();
        addressTextField = new TextField();
        phoneTextField = new TextField();
        componentsPane.add(firstNameTextField, 1, 0);
        componentsPane.add(surnameTextField, 1, 1);
        componentsPane.add(addressTextField, 1, 2);
        componentsPane.add(phoneTextField, 1, 3);
    }

    private void addButtons() {
        buttonsPane = new HBox();
        mainPane.getChildren().add(buttonsPane);
        addButton(Lang.SAVE.getText(), evt -> {
            int answer = manager.saveNewPerson(obtainKey(), firstNameTextField.getText(), surnameTextField.getText(), addressTextField.getText(), phoneTextField.getText());
            if (answer == -2) {
                new Alert(Alert.AlertType.ERROR, Lang.EMPTY_INPUTS.getText()).show();
            } else if (answer == -1) {
                new Alert(Alert.AlertType.ERROR, Lang.PERSON_ALREADY_EXISTS.getText()).show();
            }
            clearTextFields();
        });
        addButton(Lang.EDIT.getText(), evt -> {
            if (manager.editPerson(obtainKey(), addressTextField.getText(), phoneTextField.getText()) == -1) {
                new Alert(Alert.AlertType.ERROR, Lang.PERSON_NOT_EXISTS.getText()).show();
            }
            clearTextFields();
        });
        addButton(Lang.LOAD.getText(), evt -> {
            clearTextArea();
            manager.getData().forEach(person -> area.setText(area.getText() + person + Lang.NEW_LINE.getText()));
        });
        addButton(Lang.CLEAR_LOG.getText(), evt -> clearTextArea());
    }

    private void clearTextArea() {
        area.setText(Lang.EMPTY.getText());
    }

    private void clearTextFields() {
        firstNameTextField.clear();
        surnameTextField.clear();
        addressTextField.clear();
        phoneTextField.clear();
    }

    private String obtainKey() {
        return firstNameTextField.getText().trim() + surnameTextField.getText().trim();
    }

    private void addButton(String buttonText, EventHandler<ActionEvent> evt) {
        Button button = new Button(buttonText);
        buttonsPane.getChildren().add(button);
        button.setOnAction(evt);
    }
}
