package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID;
    private String productName;
    private double productPrice;
    private int productInvLevel, productInvMin, productInvMax;

    public Product(/*ObservableList<Part> associatedParts,*/ int productID, String productName, double productPrice,
                   int productInvLevel, int productInvMin, int productInvMax) {
//        this.associatedParts = associatedParts;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInvLevel = productInvLevel;
        this.productInvMin = productInvMin;
        this.productInvMax = productInvMax;
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

    public int getProductInvLevel() {
        return productInvLevel;
    }

    public void setProductInvLevel(int productInvLevel) {
        this.productInvLevel = productInvLevel;
    }

    public int getProductInvMin() {
        return productInvMin;
    }

    public void setProductInvMin(int productInvMin) {
        this.productInvMin = productInvMin;
    }

    public int getProductInvMax() {
        return productInvMax;
    }

    public void setProductInvMax(int productInvMax) {
        this.productInvMax = productInvMax;
    }
}
