package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Part> allProducts = FXCollections.observableArrayList();

    public static void addPart(/* new part */) {
        InHousePart part = new InHousePart(1, "inhouse1", 2, 3, 4, 5);
        System.out.println("allParts before add: " + allParts);
        allParts.add(part);
        System.out.println("allParts after add: " + allParts);
    }

    void addProduct(/* new product */) {

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

//    public ObservableList<Part> getAllProducts(){
//
//    }
}
