package controller;

import model.Item;
import model.ItemModel;
import view.ItemTableView;

import java.util.ArrayList;
import java.util.List;

public class ItemTableController {
    private ItemTableView controlledTableView;
    private ItemModel controlledModel;

    private int rowsOnPage;
    private int currentPage = 1;

    public ItemTableController(ItemTableView controlledTableView, ItemModel controlledModel, int rowsOnPage) {
        this.controlledTableView = controlledTableView;
        this.controlledModel = controlledModel;
        this.rowsOnPage = rowsOnPage;
    }

    public void toFirstPage() {
        currentPage = 1;
        updateTableView();
    }

    public void toLastPage() {
        currentPage = controlledModel.getItems().size() / rowsOnPage + 1;
        updateTableView();
    }

    public void nextPage() {
        if (currentPage <= controlledModel.getItems().size() / rowsOnPage) {
            currentPage++;
            updateTableView();
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
            updateTableView();
        }
    }

    private void clearTableView() { controlledTableView.getTable().getItems().clear(); }

    public void updateTableView() {
        clearTableView();
        int startIdx = (currentPage-1)*rowsOnPage;
        int endIdx = Math.min(currentPage * rowsOnPage, controlledModel.getItems().size());

        List<Item> tablesArray = controlledModel.getItems().subList(startIdx, endIdx);
        controlledTableView.getTable().getItems().addAll(tablesArray);

        updateTableStatus(tablesArray.size());
    }

    private void updateTableStatus(int entriesOnPage) {
        int pagesAmount = controlledModel.getItems().size() / rowsOnPage + 1;
        controlledTableView.getInfoLabel().setText("" +
                "Номер страницы: " + currentPage + "   " +
                "Всего страниц: " + pagesAmount + "   " +
                "Записей на странице: " + entriesOnPage + "   " +
                "Всего записей: " + controlledModel.getItems().size()
        );
   }

    public void updateModel(ArrayList<Item> newTableArray) { controlledModel.setItems(newTableArray); }
}
