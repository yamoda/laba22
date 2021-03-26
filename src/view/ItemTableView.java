package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
//import javafx.scene.layout.Pane;
import model.Item;

public class ItemTableView extends BorderPane {
    TableView<Item> itemTable;

    Button nextPageButton;
    Button prevPageButton;
    Button firstPageButton;
    Button lastPageButton;

    Label infoLabel;

    public ItemTableView() {
        createTable();
        createButtons();
    }

    public void createButtons() {
        var buttonPane = new GridPane();
        infoLabel = new Label("" +
                "Номер страницы: 1  " +
                "Всего страниц: 1  " +
                "Записей на странице: 0  " +
                "Всего записей: 0"
        );

        firstPageButton = new Button("Первая страница");
        firstPageButton.setMaxSize(1200, 50);

        lastPageButton = new Button("Последняя страница");
        lastPageButton.setMaxSize(1200, 50);

        nextPageButton = new Button("Следующая страница");
        nextPageButton.setMaxSize(1200, 50);

        prevPageButton = new Button("Предыдущая страница");
        prevPageButton.setMaxSize(1200, 50);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setFillWidth(true);
        column1.setHgrow(Priority.ALWAYS);
        column1.setPercentWidth(25);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setFillWidth(true);
        column2.setHgrow(Priority.ALWAYS);
        column2.setPercentWidth(25);

        ColumnConstraints column3 = new ColumnConstraints();
        column3.setFillWidth(true);
        column3.setHgrow(Priority.ALWAYS);
        column3.setPercentWidth(25);

        ColumnConstraints column4 = new ColumnConstraints();
        column4.setFillWidth(true);
        column4.setHgrow(Priority.ALWAYS);
        column4.setPercentWidth(25);

        buttonPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        buttonPane.getColumnConstraints().addAll(column1, column2, column3, column4);

        buttonPane.add(firstPageButton, 0, 0, 1, 1);
        buttonPane.add(lastPageButton, 1, 0, 1, 1);
        buttonPane.add(prevPageButton, 2, 0, 1, 1);
        buttonPane.add(nextPageButton, 3, 0, 1, 1);

        setTop(buttonPane);
        setBottom(infoLabel);
    }

    private void createTable() {
        itemTable = new TableView<>();

        TableColumn nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn manufactNameColumn = new TableColumn<>("Имя производителя");
        manufactNameColumn.setCellValueFactory(new PropertyValueFactory<>("manufactName"));

        TableColumn manufactNumberColumn = new TableColumn<>("Номер производителя");
        manufactNumberColumn.setCellValueFactory(new PropertyValueFactory<>("manufactNumber"));

        TableColumn amountColumn = new TableColumn<>("Кол-во на складе");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn addressColumn = new TableColumn<>("Адресс склада");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        itemTable.getColumns().addAll(nameColumn, manufactNameColumn, manufactNumberColumn, amountColumn, addressColumn);
        setCenter(itemTable);
    }

    public TableView getTable() { return itemTable; }

    public Label getInfoLabel() { return infoLabel; }

    public Button getFirstPageButton() { return firstPageButton; }

    public Button getLastPageButton() { return lastPageButton; }

    public Button getNextPageButton() { return nextPageButton; }

    public Button getPrevPageButton() { return prevPageButton; }
}
