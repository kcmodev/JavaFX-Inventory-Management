package Model;

import javafx.beans.property.SimpleStringProperty;

public abstract class Part {
    private int partID;
    private SimpleStringProperty partName;
    private double partPrice;
    private int partStock;
    private int partStockMin;
    private int partStockMax;

    /**
     * part constructor
     */
    public Part(int partID, String partName, double partPrice, int partStock,
                int partStockMin, int partStockMax) {
        this.partID = partID; // part ID is unique and can't be changed by the user
        this.partName = new SimpleStringProperty(partName);
        this.partPrice = partPrice;
        this.partStock = partStock;
        this.partStockMin = partStockMin;
        this.partStockMax = partStockMax;
    }

    /**
     * Part getters and setters
     */
    public int getPartID() { return partID; }

    public void setPartID(int partID) { this.partID = partID; }

    public String getPartName() { return partName.get(); }

    public void setPartName(String partName) { this.partName = new SimpleStringProperty(partName); }

    public double getPartPrice() { return partPrice; }

    public void setPartPrice(double partPrice) { this.partPrice = partPrice; }

    public int getPartStock() { return partStock; }

    public void setPartStock(int partStock) { this.partStock = partStock; }

    public int getPartStockMin() { return partStockMin; }

    public void setPartStockMin(int partStockMin) { this.partStockMin = partStockMin; }

    public int getPartStockMax() { return partStockMax; }

    public void setPartStockMax(int partStockMax) { this.partStockMax = partStockMax; }
}

