/**
 * Author: kcmodev
 * Class: C482 Software 1
 * Email: @wgu.edu
 * Date Submitted: 7/21/2020
 */

package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.ValidationException;

public class Product {
    /**
     * tracks all parts associated with the product object
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int productID, productInvLevel, productInvMin, productInvMax;
    private double productPrice;
    private String productName;

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
        this.productPrice = Math.round(productPrice * 100.00)/100.00;
        this.productInvLevel = productInvLevel;
        this.productInvMin = productInvMin;
        this.productInvMax = productInvMax;
    }

    /**
     * product getters and setters
     */
    public ObservableList<Part> getAllAssociatedParts() { return associatedParts; }

    public void addAssociatedPart(Part part) { associatedParts.add(part); }

    public void deleteAssociatedPart(Part part){ associatedParts.remove(part);}

    public int getProductID() {return productID; }

    public void setProductID(int productID) { this.productID = productID; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public double getProductPrice() { return this.productPrice; }

    public void setProductPrice(double productPrice) { this.productPrice = Math.round(productPrice * 100.00)/100.00; }

    public int getProductInvLevel() { return productInvLevel; }

    public void setProductInvLevel(int productInvLevel) { this.productInvLevel = productInvLevel; }

    public int getProductInvMin() { return productInvMin; }

    public void setProductInvMin(int productInvMin) { this.productInvMin = productInvMin; }

    public int getProductInvMax() { return productInvMax; }

    public void setProductInvMax(int productInvMax) { this.productInvMax = productInvMax; }

    /**
     * method is used for input validation when a product is added or modified
     */
    public void productValidation () throws ValidationException {

        double totalCost = 0;

        for (Part p : getAllAssociatedParts()){
            totalCost += p.getPartPrice();
        }

        totalCost = Math.round(totalCost * 100.00) / 100.00;

        // checks for a name to be entered
        if (getProductName().isEmpty() || !getProductName().matches("^[a-zA-Z0-9_ ]*$")){
            throw new ValidationException("Name field is invalid. Can't be blank. Must be alphanumeric.");

        // checks that minimum stock isn't less than 0
        }else if (getProductInvMin() < 0) {
            throw new ValidationException("Inventory minimum can't be less than 0");

        } else if (getProductInvMax() < 0) {
            throw new ValidationException("Inventory max must be greater than 0");

        // checks to make sure max stock is not less than the minimum
        }else if (getProductInvMax() < getProductInvMin()) {
            throw new ValidationException("Max inventory can't be set to less than the minimum");

        } else if (getProductInvLevel() < getProductInvMin()){
            throw new ValidationException("Part inventory can't be less than the minimum");

        // part price can't be less than 0
        }else if (getProductPrice() < 0){
            throw new ValidationException("Price has to be a positive number");

        // max stock can't be less than what you already have
        }else if (getProductInvMax() < getProductInvLevel()){
            throw new ValidationException("Max inventory can't be less set to than what you have on hand");

        // check stock is between min and max
        } else if (getProductInvLevel() < getProductInvMin() || getProductInvLevel() > getProductInvMax()){
            throw new ValidationException("Inventory level must be between min and max");

        // checks that product price is not less than the cost of the parts
        } else if (getProductPrice() < totalCost){
            throw new ValidationException("You can't sell the cost of the product for less than the cost of parts." +
                    " Please ensure your price is at least $" + totalCost);
        }
    }
}
