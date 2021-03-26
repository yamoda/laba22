package controller;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import model.Item;
import model.ItemModel;
import model.ItemParser;
import view.MainView;
import view.NewDialog;
import view.RemoveDialog;
import view.SearchDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApplicationController {
    ItemModel model;
    MainView view;

    ItemTableController tableController;

    public MainApplicationController(MainView view, ItemModel model) {
        this.view = view;
        this.model = model;

        tableController = new ItemTableController(view.getTableView(), model, 15);
        initController();
    }

    private void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("untitled.xml");
        File saveFile = fileChooser.showSaveDialog(null);

        if(saveFile != null) {
            ItemParser xmlModelWriter = new ItemParser();
            ArrayList<Item> entriesArray = model.getItems();
            xmlModelWriter.parseAndWrite("Items", "Item", entriesArray, saveFile.getAbsolutePath());
        }
    }

    private void load() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("untitled.xml");
        File loadFile = fileChooser.showOpenDialog(null);

        if(loadFile != null) {
            ItemParser xmlDocumentReader = new ItemParser();
            ArrayList<Item> items = xmlDocumentReader.readAndParse(loadFile.getAbsolutePath());
            model.setItems(items);
            tableController.updateModel(items);
            tableController.updateTableView();
        }
    }

    private void initController() {
        view.getSaveButton().setOnAction(event -> save());
        view.getLoadButton().setOnAction(event -> load());

        view.getNewButton().setOnAction(event -> initNewDialog());
        view.getFindButton().setOnAction(event -> initSearchDialog());
        view.getRemoveButton().setOnAction(event -> initRemoveDialog());

        view.getSaveItem().setOnAction(event -> save());
        view.getLoadItem().setOnAction(event -> load());

        view.getNewItem().setOnAction(event -> initNewDialog());
        view.getFindItem().setOnAction(event -> initSearchDialog());
        view.getRemoveItem().setOnAction(event -> initRemoveDialog());

        view.getTableView().getFirstPageButton().setOnAction(event -> tableController.toFirstPage());
        view.getTableView().getLastPageButton().setOnAction(event -> tableController.toLastPage());
        view.getTableView().getNextPageButton().setOnAction(event -> tableController.nextPage());
        view.getTableView().getPrevPageButton().setOnAction(event -> tableController.prevPage());
    }

    private void initNewDialog() {
        var dialog = new NewDialog();
        dialog.getAddButton().setOnAction(event -> {
            var name = dialog.getNameField().getText();
            var manufactName = dialog.getManufactNameField().getText();
            var manufactIdText = dialog.getManufactNumberField().getText();
            var amountText = dialog.getAmountField().getText();
            var address = dialog.getAddressField().getText();

            boolean inputCorrect = true;
            int manufactId=0, amount=0;
            try {
                manufactId = Integer.parseInt(manufactIdText);
                amount = Integer.parseInt(amountText);
            }
            catch (NumberFormatException e) { inputCorrect = false; }

            if (inputCorrect && !name.isEmpty() && !manufactName.isEmpty() && !address.isEmpty()){
                model.getItems().add(new Item(name, manufactName, manufactId, amount, address));
                tableController.updateModel(model.getItems());
                tableController.updateTableView();

                dialog.getDialogStage().hide();
            }
            else {
                Alert incorrectInput = new Alert(Alert.AlertType.WARNING);
                incorrectInput.initStyle(StageStyle.UTILITY);
                incorrectInput.setTitle("");
                incorrectInput.setHeaderText("Ошибка");
                incorrectInput.setContentText("Некорректный ввод");
                incorrectInput.showAndWait();
            }
        });
    }

    private void initSearchDialog() {
        var dialog = new SearchDialog();
        dialog.getFindButton().setOnAction(event -> {
            var name = dialog.getNameField().getText();
            var manufactName = dialog.getManufactNameField().getText();
            var address = dialog.getAddressField().getText();

            var foundArr = (ArrayList<Item>) find(name, manufactName, address);
            ItemTableController dialogTableController = new ItemTableController(
                    dialog.getFoundTable(), new ItemModel(foundArr), 3);

            dialog.getFoundTable().getFirstPageButton().setOnAction(e -> dialogTableController.toFirstPage());
            dialog.getFoundTable().getLastPageButton().setOnAction(e -> dialogTableController.toLastPage());
            dialog.getFoundTable().getNextPageButton().setOnAction(e -> dialogTableController.nextPage());
            dialog.getFoundTable().getPrevPageButton().setOnAction(e -> dialogTableController.prevPage());

            dialogTableController.updateTableView();
        });
    }

    private void initRemoveDialog() {
        var dialog = new RemoveDialog();
        dialog.getRemoveButton().setOnAction(event -> {
            var name = dialog.getNameField().getText();
            var manufactName = dialog.getManufactNameField().getText();
            var address = dialog.getAddressField().getText();

            var foundArr = (ArrayList<Item>) find(name, manufactName, address);
            model.getItems().removeAll(foundArr);

            tableController.updateModel(model.getItems());
            tableController.updateTableView();

            if(foundArr.size() == 0) dialog.getRemovalInfo().setText("Ничего не было удалено");
            else dialog.getRemovalInfo().setText("Было удалено записей: "+foundArr.size());
        });
    }


    private List<Item> find(String name, String manufactName, String address) {
        var matchesArray = model.getItems().stream()
                .filter(row -> row.getName().equals(name) || name.equals(""))
                .filter(row -> row.getManufactName().equals(manufactName) || manufactName.equals(""))
                .filter(row -> row.getAddress().equals(address) || address.equals(""))
                .collect(Collectors.toList());

        return matchesArray;
    }
}
