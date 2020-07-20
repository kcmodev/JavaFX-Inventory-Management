package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    /**
     * tracks all parts associated with the product object
     */
    static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID;
    private String productName;
    private double productPrice;
    private int productInvLevel, productInvMin, productInvMax;

    /**
     * product constructor
     */
    public Product(int productID, String productName, double productPrice,
                   int productInvLevel, int productInvMin, int productInvMax) {
        this.productID = productID; // product ID is unique and can't be changed by the user
        this.productName = productName;
        this.productPrice = productPrice;
        this.productInvLevel = productInvLevel;
        this.productInvMin = productInvMin;
        this.productInvMax = productInvMax;
    }

    /**
     * product getters and setters
     */
    public static ObservableList<Part> getAllAssociatedParts() { return associatedParts; }

    public static void addAssociatedPart(Part part) { associatedParts.add(part); }

    public static void deleteAssociatedPart(Part part){ associatedParts.remove(part);}

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

    public int getProductInvMin() { return productInvMin; }

    public void setProductInvMin(int productInvMin) {
        this.productInvMin = productInvMin;
    }

    public int getProductInvMax() { return productInvMax; }

    public void setProductInvMax(int productInvMax) {
        this.productInvMax = productInvMax;
    }
}
