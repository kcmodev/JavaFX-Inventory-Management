package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public Inventory() {
        /**
         * set default values to start with in parts table
         */
        InHousePart graphicsCard = new InHousePart(1, "Graphics Card", 800, 5, 2, 10);
        graphicsCard.setPartMachineID(24);
        allParts.add(graphicsCard);

        InHousePart motherboard = new InHousePart(2, "Motherboard", 500, 3, 2, 10);
        motherboard.setPartMachineID(16);
        allParts.add(motherboard);

        InHousePart CPU = new InHousePart(3, "CPU", 400, 7, 2, 10);
        CPU.setPartMachineID(8);
        allParts.add(CPU);

        /**
         * set default values to product table
         * used to associate the other 3 default parts to 1 product
         */
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
     * @return
     */
    public static Part searchByPartID(int partID) {
        System.out.println("Searching by part ID. ID Entered = " + partID);

        /**
         * loop through observable list allParts
         * check each part for a match with given partID
         * return first part object found
         */
        for (Part part : allParts){
            System.out.println("part being checked: " + part.getPartName());
            if (part.getPartID() == partID){
                System.out.println("part being returned: " + part.getPartName());
                return part;
            }
            System.out.println("not a match, continuing");
        }
        return null;
    }

    /**
     * search field to search by product ID
     */
    public static Product searchByProductID(int productID) {
        System.out.println("Searching by part ID. ID Entered = " + productID);

        /**
         * loop through observable list allProducts
         * check each part for a match with given productID
         * return first pproduct object found
         */
        for (Product product : allProducts){
            System.out.println("product being checked " + product.getProductName());
            if (product.getProductID() == productID){
                System.out.println("product being returned: " + product.getProductName());
                return product;
            }
            System.out.println("not a match, continuing");
        }
        return null;
    }

    /**
     * search field to search by part name
     */
    public static Part searchByPartName(String partName) {
        System.out.println("searching by part name with string/substring: \"" + partName + "\"");

        for (Part part : allParts){
            System.out.println("part being checked: " + part.getPartName());

            /**
             * checking if user input is either a full or partial match
             * returns first instance if not an exact match
             * also converts both string to lower case to ease in locating the appropriate object
             * without case sensitivity
             */
            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())){
                System.out.println("part name being returned: " + part.getPartName());
                return part;
            }
            System.out.println("not a match, continuing");
        }
        return null;
    }

    /**
     * search  field to search by product name
     */
    public static Product searchByProductName(String productName) {
        System.out.println("searching by part namne with strin/substring: \"" + productName + "\"");

        for (Product product : allProducts){
            System.out.println("product being checked: " + product.getProductName());

            /**
             * checking if user input is either a full or partial match
             * returns first instance if not an exact match
             * also converts both string to lower case to ease in locating the appropriate object
             * without case sensitivity
             */
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())){
                System.out.println("part name found: " + product.getProductName());
                return product;
            }
            System.out.println("not a match, continuing");
        }
        return null;
    }

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
