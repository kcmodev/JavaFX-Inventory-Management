package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID;
    private String productName;
    private double productPrice;
    private int productStockLevel, productStockMin, productStockMax;

    public Product(ObservableList<Part> associatedParts, int productID, String productName, double productPrice,
                   int productStockLevel, int productStockMin, int productStockMax) {
        this.associatedParts = associatedParts;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStockLevel = productStockLevel;
        this.productStockMin = productStockMin;
        this.productStockMax = productStockMax;
    }

//    public ObservableList<Part> getAllAssociatedParts() {
//        return associatedParts;
//    }

//    public void addAssociatedPart(/* selected part */) {
//        this.associatedParts = associatedParts;
//    }

//    public boolean deleteAssociatedPart(/* selected associated part */){
//
//    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStockLevel() {
        return productStockLevel;
    }

    public void setProductStockLevel(int productStockLevel) {
        this.productStockLevel = productStockLevel;
    }

    public int getProductStockMin() {
        return productStockMin;
    }

    public void setProductStockMin(int productStockMin) {
        this.productStockMin = productStockMin;
    }

    public int getProductStockMax() {
        return productStockMax;
    }

    public void setProductStockMax(int productStockMax) {
        this.productStockMax = productStockMax;
    }
}
