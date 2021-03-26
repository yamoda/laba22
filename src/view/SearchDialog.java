package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SearchDialog {
    ItemTableView foundTable;

    Stage dialogStage;
    Scene dialogScene;
    Group dialogPane;

    TextField nameField;
    TextField manufactNameField;
    TextField addressField;

    Button findButton;

    public SearchDialog() {
        int width = 800;
        int height = 400;

        dialogPane = new Group();
        dialogScene = new Scene(dialogPane, width, height);

        createUI();

        dialogStage = new Stage();
        dialogStage.setResizable(true);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }

    private void createUI() {
        Font mainFont = Font.font("Arial", 20);

        Label nameLabel = new Label("Название товара");
        nameLabel.setTranslateY(10);
        nameLabel.setFont(mainFont);

        Label manufacturerLabel = new Label("Название\nпроизводителя");
        manufacturerLabel.setTranslateY(60);
        manufacturerLabel.setFont(mainFont);

        Label addressLabel = new Label("Адрес склада");
        addressLabel.setTranslateY(130);
        addressLabel.setFont(mainFont);

        nameField = new TextField();
        nameField.setFont(mainFont);
        nameField.setPrefSize(600, 50);
        nameField.setTranslateX(200);

        manufactNameField = new TextField();
        manufactNameField.setFont(mainFont);
        manufactNameField.setPrefSize(600, 50);
        manufactNameField.setTranslateX(200);
        manufactNameField.setTranslateY(60);

        addressField = new TextField();
        addressField.setFont(mainFont);
        addressField.setPrefSize(600, 50);
        addressField.setTranslateX(200);
        addressField.setTranslateY(120);

        findButton = new Button("Найти");
        findButton.setPrefSize(800, 50);
        findButton.setTranslateY(180);

        foundTable = new ItemTableView();
        foundTable.setPrefSize(800, 150);
        foundTable.setTranslateY(240);

        dialogPane.getChildren().addAll(findButton, foundTable);
        dialogPane.getChildren().addAll(nameLabel, manufacturerLabel, addressLabel);
        dialogPane.getChildren().addAll(nameField, manufactNameField, addressField);
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public Scene getDialogScene() {
        return dialogScene;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public TextField getManufactNameField() {
        return manufactNameField;
    }

    public Button getFindButton() { return findButton; }

    public ItemTableView getFoundTable() { return foundTable; }
}

