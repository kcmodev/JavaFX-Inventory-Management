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

        /**
         * check if part is an instance of in house
         * instantiate new in house object then cast method variable part as an in house part
         */
        if (part instanceof InHousePart) {
            InHousePart partAsInHouse = (InHousePart) part;
            allParts.add(partAsInHouse);
        }
        /**
         * check if part is an instance of outsourced
         * instantiate new outsourced object then cast method variable part as an outsourced part
         */
        if (part instanceof OutsourcedPart){
            OutsourcedPart partAsOutsourced = (OutsourcedPart) part;
            allParts.add(partAsOutsourced);
        }

        System.out.println("allParts after add: " + allParts);
    }

    /**
     * adds product to list
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * search field to search by part ID
     */
    public static boolean searchByPartID(int partID) {
        System.out.println("Searching by part ID");

        return allParts.contains(partID);
    }

    /**
     * search field to search by product ID
     */
    public static void searchByProductID(int productID) { }

    /**
     * search field to search by part name
     */
    public static void searchByPartName(String partName) { }

    /**
     * search  field to search by product name
     */
    public static void searchByProductName(String productName) { }

    /**
     * list used to search for parts related to a product
     */
//    public ObservableList<Part> searchByPart(Part part){
//
//    }

    /**
     * list used to search for products related to a part
     */
//    public ObservableList<Part> searchByProduct(Product product){
//
//    }

    void modifyPart (int index /* selected part */) { }

    void modifyProduct(int index /* selected product */) { }

    /**
     * remove instance of part from observable list
     */
    public static void deletePart(Part part){
        System.out.println("allParts before delete: " + allParts);

        /**
         * check if part is an instance of in house
         */
        if (part instanceof InHousePart) {
            // instantiate new in house object then cast method variable part as an in house part
            InHousePart partAsInHouse = (InHousePart) part;
            allParts.remove(partAsInHouse);
        }

        /**
         * check if part is an instance of outsourced
         */
        if (part instanceof  OutsourcedPart){
            // instantiate new outsourced object then cast method variable part as an outsourced part
            OutsourcedPart partAsOutsourced = (OutsourcedPart) part;
            allParts.remove(partAsOutsourced);
        }
        System.out.println("allParts after delete: " + allParts);
    }

    /**
     * remove instance of product from observable list
     */
    public static void deleteProduct(Product product){ allProducts.remove(product); }

    /**
     * returns entire observable list of all parts
     */
    public static ObservableList<Part> getAllParts(){ return allParts; }

    /**
     * returns entire observable list of all products
     */
    public static ObservableList<Product> getAllProducts(){ return allProducts; }
}
