package Model;

import javafx.beans.property.SimpleStringProperty;

import javax.xml.bind.ValidationException;

public abstract class Part {
    protected int partID;
    protected SimpleStringProperty partName;
    protected double partPrice;
    protected int partStock;
    protected int partStockMin;
    protected int partStockMax;

    /**
     * part constructor
     */
    public Part(int partID, String partName, double partPrice, int partStock,
                int partStockMin, int partStockMax) {
        /**
         * product ID is unique and can't be changed by the user
         * is assigned by the Inventory.idGenerator method
         */
        this.partID = partID;
        this.partName = new SimpleStringProperty(partName);
        this.partPrice = partPrice;
        this.partStock = partStock;
        this.partStockMin = partStockMin;
        this.partStockMax = partStockMax;
    }

    public Part() { }

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

    public void partValidation () throws ValidationException {

        // checks for a name to be entered
        if (getPartName().isEmpty()){
            throw new ValidationException("Name field is blank");

        // checks that minimum stock isn't less than 0
        }else if (getPartStockMin() <= 0) {
            throw new ValidationException("Inventory minimum can't be less than 0");

        } else if (getPartStockMax() <= 0) {
            throw new ValidationException("Inventory max must be greater than 0");

        // checks to make sure max stock is not less than the minimum
        }else if (getPartStockMax() < getPartStockMin()) {
            throw new ValidationException("Max inventory can't be less than the minimum");

        } else if (getPartStock() < getPartStockMin()){
            throw new ValidationException("Part inventory can't be less than the minimum");

        // part price can't be 0 or less
        }else if (getPartPrice() <= 0){
            throw new ValidationException("Price has to be a positive number");

        // max stock can't be less than what you already have
        }else if (getPartStockMax() < getPartStock()){
            throw new ValidationException("Max inventory can't be less than what you have on hand");

        // check stock is between min and max
        } else if (getPartStock() < getPartStockMin() || getPartStock() > getPartStockMax()){
            throw new ValidationException("Inventory level must be between min and max");

        }
    }
}

