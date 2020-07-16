package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        System.out.println("allParts before add: " + allParts);

        // check if part is an instance of in house
        if (part instanceof InHousePart) {
            // instantiate new in house object then cast method variable part as an in house part
            InHousePart partAsInHouse = (InHousePart) part;
            allParts.add(partAsInHouse);
        }

        // check if part is an instance of outsourced
        if (part instanceof  OutsourcedPart){
            // instantiate new outsourced object then cast method variable part as an outsourced part
            OutsourcedPart partAsOutsourced = (OutsourcedPart) part;
            allParts.add(partAsOutsourced);
        }
        System.out.println("allParts after add: " + allParts);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public void lookupPart(int partID) {

    }

    public void lookupProduct(int productID) {

    }

//    public ObservableList<Part> lookupPart(String name){
//
//    }
//
//    public ObservableList<Part> lookupProduct(String name){
//
//    }

    void updatePart (int index /* selected part */) {

    }

//    public boolean deletePart(/* selected part */){
//
//    }

//    public boolean deleteProduct(/* selected product */){
//
//    }

    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
