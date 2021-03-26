package model;

import java.util.ArrayList;

public class ItemModel {
    ArrayList<Item> items;

    public ItemModel() { items = new ArrayList<Item>(); }

    public ItemModel(ArrayList<Item> items) { this.items = items; }

    public ArrayList<Item> getItems() { return items; }

    public void setItems(ArrayList<Item> items) { this.items = items; }
}

