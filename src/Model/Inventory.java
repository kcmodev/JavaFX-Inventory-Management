package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int generatedID = 1;

    /**
     * used to show filtered lists for parts and products table views respectively
     */
    private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

    public Inventory() {
        /**
         * set default in house part values in parts table
         */
        InHousePart graphicsCard = new InHousePart(idGenerator(), "Graphics Card", 800, 5, 2, 10, 24);
        allParts.add(graphicsCard);

        InHousePart motherboard = new InHousePart(idGenerator(), "Motherboard", 500, 3, 2, 10, 16);
        allParts.add(motherboard);

        InHousePart CPU = new InHousePart(idGenerator(), "CPU", 400, 7, 2, 10, 8);
        allParts.add(CPU);

        /**
         * set defualt outsourced part values in parts table
         */
        OutsourcedPart Monitor = new OutsourcedPart(idGenerator(), "Monitor", 400, 7, 2, 10, "Dell");
        allParts.add(Monitor);

        OutsourcedPart Speakers = new OutsourcedPart(idGenerator(), "Speakers", 400, 9, 2, 10, "Bose");
        allParts.add(Speakers);

        OutsourcedPart Dock = new OutsourcedPart(idGenerator(), "Dock", 400, 4, 2, 10, "Apple");
        allParts.add(Dock);

        OutsourcedPart Case = new OutsourcedPart(idGenerator(), "Case", 400, 10, 2, 10, "inCase");
        allParts.add(Case);

        OutsourcedPart travelBag = new OutsourcedPart(idGenerator(), "Travel Bag", 400, 8, 2, 10, "Oakley");
        allParts.add(travelBag);

        /**
         * set default values to product table
         */
        Product computer = new Product(idGenerator(), "Computer", 1700, 3, 1, 5);
        allProducts.add(computer);

        Product laptopSetup = new Product(idGenerator(), "Laptop Setup", 2500, 4, 1, 5);
        allProducts.add(laptopSetup);
    }

    /**
     * generates ID in numerical increasing order
     */
    public static int idGenerator() { return generatedID++; }

    /**
     * adds part to list
     */
    public static void addPart(Part part) { allParts.add(part); }

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
        filteredParts.clear();

        /**
         * parses all parts and checks text entered in search box
         * will return all related parts and filter out anything irrelevant
         */
        for (Part part : allParts){
            if (part.getPartID() == partID){
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }

    /**
     * search field to search by product ID
     */
    public static ObservableList<Product> searchByProductID(int productID) {
        filteredProducts.clear();

        /**
         * parses all products and checks text entered in search box
         * will return all related products and filter out anything irrelevant
         */
        for (Product product : allProducts){
            if (product.getProductID() == productID){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * search field to search by part name
     */
    public static ObservableList<Part> searchByPartName(String partName) {
        System.out.println("searching by part name with string/substring: \"" + partName + "\"");
        filteredParts.clear();

        /**
         * parses all parts and checks text entered in search box
         * will return all related parts and filter out anything irrelevant
         */
        for (Part part : allParts){
            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())){
                filteredParts.add(part);
            }
        }
        return filteredParts;
    }

    /**
     * search field to search by product name
     */
    public static ObservableList<Product> searchByProductName(String productName) {
        filteredProducts.clear(); // clears filtered list for a fresh start with every search

        /**
         * parses all products and checks text entered in search box
         * will return all related products and filter out anything irrelevant
         */
        for (Product product : allProducts){
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())){
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }

    /**
     * method takes passed in part, compares it to the current list, and updates the information of the
     * part with the matching part ID
     */
    public static void modifyPart (Part part) {
        for (int i = 0; i < allParts.size(); i++){
            if (part.getPartID() == allParts.get(i).getPartID()){
                allParts.set(i, part);
                break; // breaks when the part is found to avoid searching entire list unless necessary
            }
        }
    }

    /**
     * method takes passed in product, compares it to the current list, and updates the information of the
     * product with the matching product ID
     */
    public static void modifyProduct(Product product) {
        for (int i = 0; i < allProducts.size(); i++){
            if (product.getProductID() == allProducts.get(i).getProductID()){
                allProducts.set(i, product);
                break; // breaks when the product is found to avoid searching entire list unless necessary
            }
        }
    }

    /**
     * method removes instance of part from observable list allParts
     */
    public static void deletePart(Part part){ allParts.remove(part); }

    /**
     * method removes instance of product from observable list allProducts
     */
    public static void deleteProduct(Product product){ allProducts.remove(product); }

    /**
     * returns entire observable list allParts
     */
    public static ObservableList<Part> getAllParts(){ return allParts; }

    /**
     * returns entire observable list allProducts
     */
    public static ObservableList<Product> getAllProducts(){ return allProducts; }
}
