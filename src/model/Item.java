package model;

public class Item {
    String name;
    String manufactName;
    int manufactNumber;
    int amount;
    String address;

    public Item() {}

    public Item(String name, String manufactName, int manufactNumber, int amount, String address) {
        this.name = name;
        this.manufactName = manufactName;
        this.manufactNumber = manufactNumber;
        this.amount = amount;
        this.address = address;
    }

    public String getName() { return name; }
    public String getManufactName() { return manufactName; }
    public int getManufactNumber() { return manufactNumber; }
    public int getAmount() { return amount; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setManufactName(String manufactName) { this.manufactName = manufactName; }
    public void setManufactNumber(int manufactNumber) { this.manufactNumber = manufactNumber; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setAddress(String address) { this.address = address; }
}
