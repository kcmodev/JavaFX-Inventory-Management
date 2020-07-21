package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.ValidationException;

public class Product {
    /**
     * tracks all parts associated with the product object
     */
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID;
    private String productName;
    private double productPrice;
    private int productInvLevel, productInvMin, productInvMax;

    /**
     * product constructor
     */
    public Product() { }

    public Product(int productID, String productName, double productPrice,
                   int productInvLevel, int productInvMin, int productInvMax) {
        /**
         * product ID is unique and can't be changed by the user
         * is assigned by the Inventory.idGenerator method
         */
        this.productID = productID;
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

    public int getProductID() {return productID; }

    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public double getProductPrice() { return productPrice; }

    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public int getProductInvLevel() { return productInvLevel; }

    public void setProductInvLevel(int productInvLevel) { this.productInvLevel = productInvLevel; }

    public int getProductInvMin() { return productInvMin; }

    public void setProductInvMin(int productInvMin) { this.productInvMin = productInvMin; }

    public int getProductInvMax() { return productInvMax; }

    public void setProductInvMax(int productInvMax) { this.productInvMax = productInvMax; }

    public void productValidation () throws ValidationException {

        // checks for a name to be entered
        if (getProductName().isEmpty()){
            throw new ValidationException("Name field is blank");

            // checks that minimum stock isn't less than 0
        }else if (getProductInvMin() <= 0) {
            throw new ValidationException("Inventory minimum can't be less than 0");

        } else if (getProductInvMax() <= 0) {
            throw new ValidationException("Inventory max must be greater than 0");

            // checks to make sure max stock is not less than the minimum
        }else if (getProductInvMax() < getProductInvMin()) {
            throw new ValidationException("Max inventory can't be less than the minimum");

        } else if (getProductInvLevel() < getProductInvMin()){
            throw new ValidationException("Part inventory can't be less than the minimum");

            // part price can't be 0 or less
        }else if (getProductPrice() <= 0){
            throw new ValidationException("Price has to be a positive number");

            // max stock can't be less than what you already have
        }else if (getProductInvMax() < getProductInvLevel()){
            throw new ValidationException("Max inventory can't be less than what you have on hand");

            // check stock is between min and max
        } else if (getProductInvLevel() < getProductInvMin() || getProductInvLevel() > getProductInvMax()){
            throw new ValidationException("Inventory level must be between min and max");

        }
    }
}
