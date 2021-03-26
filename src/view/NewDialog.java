package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NewDialog {
    Stage dialogStage;
    Scene dialogScene;
    Group dialogPane;

    TextField nameField;
    TextField manufactNameField;
    TextField manufactNumberField;
    TextField amountField;
    TextField addressField;

    Button addButton;

    public NewDialog() {
        int width = 500;
        int height = 350;

        dialogPane = new Group();
        dialogScene = new Scene(dialogPane, width, height);

        createUI();

        dialogStage = new Stage();
        dialogStage.setResizable(false);
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

        Label idLabel = new Label("УНП\nпроизводителя");
        idLabel.setTranslateY(120);
        idLabel.setFont(mainFont);

        Label amountLabel = new Label("Количество\nна складе");
        amountLabel.setTranslateY(180);
        amountLabel.setFont(mainFont);

        Label addressLabel = new Label("Адрес склада");
        addressLabel.setTranslateY(250);
        addressLabel.setFont(mainFont);

        nameField = new TextField();
        nameField.setFont(mainFont);
        nameField.setPrefSize(300, 50);
        nameField.setTranslateX(200);

        manufactNameField = new TextField();
        manufactNameField.setFont(mainFont);
        manufactNameField.setPrefSize(300, 50);
        manufactNameField.setTranslateX(200);
        manufactNameField.setTranslateY(60);

        manufactNumberField = new TextField();
        manufactNumberField.setFont(mainFont);
        manufactNumberField.setPrefSize(300, 50);
        manufactNumberField.setTranslateX(200);
        manufactNumberField.setTranslateY(120);

        amountField = new TextField();
        amountField.setFont(mainFont);
        amountField.setPrefSize(300, 50);
        amountField.setTranslateX(200);
        amountField.setTranslateY(180);

        addressField = new TextField();
        addressField.setFont(mainFont);
        addressField.setPrefSize(300, 50);
        addressField.setTranslateX(200);
        addressField.setTranslateY(240);

        addButton = new Button("Добавить");
        addButton.setPrefSize(500, 50);
        addButton.setTranslateY(300);

        dialogPane.getChildren().add(addButton);
        dialogPane.getChildren().addAll(nameLabel, manufacturerLabel, idLabel, amountLabel, addressLabel);
        dialogPane.getChildren().addAll(nameField, manufactNameField, manufactNumberField, amountField, addressField);
    }

    public Stage getDialogStage() { return dialogStage; }

    public Scene getDialogScene() { return dialogScene; }

    public TextField getNameField() { return nameField; }
    public TextField getAddressField() { return addressField; }
    public TextField getManufactNameField() { return manufactNameField; }
    public TextField getAmountField() { return amountField; }
    public TextField getManufactNumberField() { return manufactNumberField; }

    public Button getAddButton() { return addButton; }
}
