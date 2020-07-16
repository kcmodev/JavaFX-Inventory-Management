package Model;

import javafx.beans.property.SimpleStringProperty;

public class OutsourcedPart extends Part {

    private String companyName;

    public OutsourcedPart(int partID, String partName, double partPrice, int partStock, int partStockMin, int partStockMax) {
        super(partID, partName, partPrice, partStock, partStockMin, partStockMax);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
