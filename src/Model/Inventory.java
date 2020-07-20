package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    static ObservableList<Part> allParts = FXCollections.observableArrayList();
    static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * used to show filtered lists for parts and products table views respectively
     */
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public Inventory() {
        /**
         * set default values to start with in parts table
         */
        InHousePart graphicsCard = new InHousePart(1, "Graphics Card", 800, 5, 2, 10, 24);
        allParts.add(graphicsCard);

        InHousePart motherboard = new InHousePart(2, "Motherboard", 500, 3, 2, 10, 16);
        allParts.add(motherboard);

        InHousePart CPU = new InHousePart(3, "CPU", 400, 7, 2, 10, 8);
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
     */
    public static ObservableList<Part> searchByPartID(int partID) {
        System.out.println("Searching by part ID. ID Entered = " + partID);
        filteredParts.clear();


        for (Part part : allParts){
            System.out.println("part being checked: " + part.getPartID());

            if (part.getPartID() == partID){
                System.out.println("match found. ID of part being added: " + part.getPartID());
                filteredParts.add(part);
                continue;
            }
            System.out.println("\"" + part.getPartID() + "\" not a match, continuing");
        }
        return filteredParts;
    }

    /**
     * search field to search by product ID
     */
    public static ObservableList<Product> searchByProductID(int productID) {
        System.out.println("Searching by part ID. ID Entered = " + productID);
        filteredProducts.clear();

        for (Product product : allProducts){
            System.out.println("product being checked: " + product.getProductID());

            if (product.getProductID() == productID){
                System.out.println("match found. ID of product being added: " + product.getProductID());
                filteredProducts.add(product);
                continue;
            }
            System.out.println("\"" + product.getProductID() + "\" not a match, continuing");
        }
        return filteredProducts;
    }

    /**
     * search field to search by part name
     */
    public static ObservableList<Part> searchByPartName(String partName) {
        System.out.println("searching by part name with string/substring: \"" + partName + "\"");
        filteredParts.clear();

        for (Part part : allParts){
            System.out.println("part being checked: " + part.getPartName());

            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())){
                System.out.println("match found. name of part being added: " + part.getPartName());
                filteredParts.add(part);
                continue;
            }
            System.out.println("\"" + part.getPartName() + "\" not a match, continuing");
        }
        return filteredParts;
    }

    /**
     * search  field to search by product name
     */
    public static ObservableList<Product> searchByProductName(String productName) {
        System.out.println("searching by part name with string/substring: \"" + productName + "\"");
        filteredProducts.clear();

        for (Product product : allProducts){
            System.out.println("product being checked: " + product.getProductName());


            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())){
                System.out.println("match found. name of product being added: " + product.getProductName());
                filteredProducts.add(product);
                continue;
            }
            System.out.println("\"" + product.getProductName() + "\" not a match, continuing");
        }
        return filteredProducts;
    }

    public static void modifyPart (Part part) {
        System.out.println("passing part back to modify");
        System.out.println("new class object type: " + part.getClass());
        System.out.println("new part name: " + part.getPartName());
        for (int i = 0; i < allParts.size(); i++){
            if (part.getPartID() == allParts.get(i).getPartID()){
                allParts.set(i, part);
                break;
            }
        }
    }

    public static void modifyProduct(int index, Product product) {
        for (int i = 0; i < allProducts.size(); i++){
            if (product.getProductID() == allProducts.get(i).getProductID()){
                allProducts.set(i, product);
                break;
            }
        }
    }

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
