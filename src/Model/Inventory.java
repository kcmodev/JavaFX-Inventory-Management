package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public Inventory() {
        InHousePart graphicsCard = new InHousePart(1, "Graphics Card", 800, 5, 2, 10);
        allParts.add(graphicsCard);

        InHousePart motherboard = new InHousePart(2, "Motherboard", 500, 3, 2, 10);
        allParts.add(motherboard);

        InHousePart CPU = new InHousePart(3, "CPU", 400, 7, 2, 10);
        allParts.add(CPU);

        Product computer = new Product(1, "Computer", 1700, 2, 1, 5);
        allProducts.add(computer);
    }

    public static void addPart(Part part) {
        System.out.println("allParts before add: " + allParts);

        // check if part is an instance of in house
        if (part instanceof InHousePart) {
            // instantiate new in house object then cast method variable part as an in house part
            InHousePart partAsInHouse = (InHousePart) part;
            allParts.add(partAsInHouse);
        }

        // check if part is an instance of outsourced
        if (part instanceof OutsourcedPart){
            // instantiate new outsourced object then cast method variable part as an outsourced part
            OutsourcedPart partAsOutsourced = (OutsourcedPart) part;
            allParts.add(partAsOutsourced);
        }

        System.out.println("allParts after add: " + allParts);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static void searchByPartID(int partID) { }

    public static void searchByProductID(int productID) { }

    public static void searchByPartName(String partName) { }

    public static void searchByProductName(String productName) { }

    // list used to search for parts related to a product
//    public ObservableList<Part> searchByPart(Part part){
//
//    }
    // list used to search for products related to a part
//    public ObservableList<Part> searchByProduct(Product product){
//
//    }

    void modifyPart (int index /* selected part */) { }

    void modifyProduct(int index /* selected product */) { }

    // remove instance of part from observable list
    public static void deletePart(Part part){
        System.out.println("allParts before delete: " + allParts);

        // check if part is an instance of in house
        if (part instanceof InHousePart) {
            // instantiate new in house object then cast method variable part as an in house part
            InHousePart partAsInHouse = (InHousePart) part;
            allParts.remove(partAsInHouse);
        }

        // check if part is an instance of outsourced
        if (part instanceof  OutsourcedPart){
            // instantiate new outsourced object then cast method variable part as an outsourced part
            OutsourcedPart partAsOutsourced = (OutsourcedPart) part;
            allParts.remove(partAsOutsourced);
        }
        System.out.println("allParts after delete: " + allParts);
    }

    // remove instance of product from observable list
    public static void deleteProduct(Product product){ allProducts.remove(product); }

    public static ObservableList<Part> getAllParts(){ return allParts; }

    public static ObservableList<Product> getAllProducts(){ return allProducts; }
}
