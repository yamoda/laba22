package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class MainView {
    VBox mainRoot;
    Scene mainScene;

    MenuBar appMenuBar;
    ToolBar appToolbar;

    MenuItem loadItem;
    MenuItem saveItem;
    MenuItem newItem;
    MenuItem findItem;
    MenuItem removeItem;

    Button loadButton;
    Button saveButton;
    Button newButton;
    Button findButton;
    Button removeButton;

    ItemTableView tableView;

    public MainView(int width, int height) {
        mainRoot = new VBox();
        mainScene = new Scene(mainRoot, width, height);

        createMenu();
        createToolbar();
        createTable();
    }

    private void createMenu() {
        appMenuBar = new MenuBar();

        Menu fileMenu = new Menu("Файл");

        loadItem = new MenuItem("Загрузить");
        saveItem = new MenuItem("Сохранить");

        fileMenu.getItems().addAll(loadItem, saveItem);

        Menu toolsMenu = new Menu("Инструменты");

        newItem = new MenuItem("Новая строка");
        findItem = new MenuItem("Найти");
        removeItem = new MenuItem("Удалить");

        toolsMenu.getItems().addAll(newItem, findItem, removeItem);

        appMenuBar.getMenus().addAll(fileMenu, toolsMenu);
        mainRoot.getChildren().add(appMenuBar);
    }

    private void createToolbar() {
        appToolbar = new ToolBar();

        loadButton = new Button("Загрузить");
        saveButton = new Button("Сохранить");
        newButton = new Button("Новая строка");
        findButton = new Button("Найти");
        removeButton = new Button("Удалить");

        appToolbar.getItems().addAll(loadButton, saveButton, newButton, findButton, removeButton);
        mainRoot.getChildren().add(appToolbar);
    }

    private void createTable() {
        tableView = new ItemTableView();
        mainRoot.getChildren().add(tableView);
    }

    public VBox getRoot() { return mainRoot; }

    public Scene getScene() { return mainScene; }

    public MenuItem getLoadItem() { return loadItem; }
    public MenuItem getSaveItem() { return saveItem; }
    public MenuItem getNewItem() { return newItem; }
    public MenuItem getFindItem() { return findItem; }
    public MenuItem getRemoveItem() { return removeItem; }

    public Button getLoadButton() { return loadButton; }
    public Button getSaveButton() { return saveButton; }
    public Button getNewButton() { return newButton; }
    public Button getFindButton() { return findButton; }
    public Button getRemoveButton() { return removeButton; }

    public ItemTableView getTableView() { return tableView; }
}
