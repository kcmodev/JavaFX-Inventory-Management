package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Part {
    int partID;
    SimpleStringProperty partName;
    double partPrice;
    int partStock;
    int partStockMin;
    int partStockMax;

    public Part(int partID, String partName, double partPrice, int partStock,
                int partStockMin, int partStockMax) {
        this.partID = partID;
        this.partName = new SimpleStringProperty(partName);
        this.partPrice = partPrice;
        this.partStock = partStock;
        this.partStockMin = partStockMin;
        this.partStockMax = partStockMax;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName.get();
    }

    public void setPartName(SimpleStringProperty partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartStock() {
        return partStock;
    }

    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    public int getPartStockMin() {
        return partStockMin;
    }

    public void setPartStockMin(int partStockMin) {
        this.partStockMin = partStockMin;
    }

    public int getPartStockMax() {
        return partStockMax;
    }

    public void setPartStockMax(int partStockMax) {
        this.partStockMax = partStockMax;
    }

}

